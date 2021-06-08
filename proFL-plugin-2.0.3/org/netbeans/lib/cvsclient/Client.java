// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient;

import org.netbeans.lib.cvsclient.request.ExpandModulesRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import org.netbeans.lib.cvsclient.request.WrapperSendRequest;
import java.util.ArrayList;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import org.netbeans.lib.cvsclient.util.StringPattern;
import org.netbeans.lib.cvsclient.request.ValidResponsesRequest;
import org.netbeans.lib.cvsclient.request.ValidRequestsRequest;
import org.netbeans.lib.cvsclient.request.UseUnchangedRequest;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.admin.Entry;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.response.Response;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.response.ErrorMessageResponse;
import java.io.InterruptedIOException;
import org.netbeans.lib.cvsclient.response.ResponseException;
import org.netbeans.lib.cvsclient.request.UnconfiguredRequestException;
import java.io.IOException;
import java.io.File;
import org.netbeans.lib.cvsclient.file.FileDetails;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.util.Logger;
import org.netbeans.lib.cvsclient.request.RootRequest;
import java.util.LinkedList;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.EnhancedMessageEvent;
import org.netbeans.lib.cvsclient.request.GzipFileContentsRequest;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.util.List;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.util.BugLog;
import org.netbeans.lib.cvsclient.util.DefaultIgnoreFileFilter;
import java.util.HashMap;
import org.netbeans.lib.cvsclient.file.DefaultFileHandler;
import org.netbeans.lib.cvsclient.file.GzippedFileHandler;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.util.Set;
import java.util.Map;
import org.netbeans.lib.cvsclient.util.IgnoreFileFilter;
import org.netbeans.lib.cvsclient.response.ResponseFactory;
import java.io.PrintStream;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.admin.AdminHandler;
import java.util.Date;
import org.netbeans.lib.cvsclient.file.FileHandler;
import org.netbeans.lib.cvsclient.connection.Connection;
import org.netbeans.lib.cvsclient.response.ResponseServices;

public class Client implements ClientServices, ResponseServices
{
    private Connection connection;
    private FileHandler transmitFileHandler;
    private FileHandler gzipFileHandler;
    private FileHandler uncompFileHandler;
    private boolean dontUseGzipFileHandler;
    private Date modifiedDate;
    private AdminHandler adminHandler;
    private String localPath;
    private boolean isFirstCommand;
    private final EventManager eventManager;
    private GlobalOptions globalOptions;
    private PrintStream stderr;
    private boolean abort;
    private ResponseFactory responseFactory;
    private IgnoreFileFilter ignoreFileFilter;
    private Map validRequests;
    private Map wrappersMap;
    private boolean initialRequestsSent;
    private boolean printConnectionReuseWarning;
    private static final Set ALLOWED_CONNECTION_REUSE_REQUESTS;
    private LoggedDataInputStream loggedDataInputStream;
    private LoggedDataOutputStream loggedDataOutputStream;
    private boolean warned;
    
    public Client(final Connection connection, final AdminHandler adminHandler) {
        this.gzipFileHandler = new GzippedFileHandler();
        this.uncompFileHandler = new DefaultFileHandler();
        this.isFirstCommand = true;
        this.eventManager = new EventManager(this);
        this.stderr = System.err;
        this.validRequests = new HashMap();
        this.wrappersMap = null;
        this.initialRequestsSent = false;
        this.printConnectionReuseWarning = false;
        this.setConnection(connection);
        this.setAdminHandler(adminHandler);
        this.ignoreFileFilter = new DefaultIgnoreFileFilter();
        this.dontUseGzipFileHandler = false;
    }
    
    public void setErrorStream(final PrintStream stderr) {
        this.stderr = stderr;
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
        this.initialRequestsSent = false;
        this.setIsFirstCommand(true);
    }
    
    public AdminHandler getAdminHandler() {
        return this.adminHandler;
    }
    
    public void setAdminHandler(final AdminHandler adminHandler) {
        this.adminHandler = adminHandler;
    }
    
    public String getLocalPath() {
        return this.localPath;
    }
    
    public void setLocalPath(String localPath) {
        for (localPath = localPath.replace('\\', '/'); localPath.endsWith("/"); localPath = localPath.substring(0, localPath.length() - 1)) {}
        this.localPath = localPath;
    }
    
    public boolean isFirstCommand() {
        return this.isFirstCommand;
    }
    
    public void setIsFirstCommand(final boolean isFirstCommand) {
        this.isFirstCommand = isFirstCommand;
    }
    
    public FileHandler getUncompressedFileHandler() {
        return this.uncompFileHandler;
    }
    
    public void setUncompressedFileHandler(final FileHandler uncompFileHandler) {
        this.uncompFileHandler = uncompFileHandler;
    }
    
    public FileHandler getGzipFileHandler() {
        return this.gzipFileHandler;
    }
    
    public void setGzipFileHandler(final FileHandler gzipFileHandler) {
        this.gzipFileHandler = gzipFileHandler;
    }
    
    public void dontUseGzipFileHandler() {
        this.setGzipFileHandler(new DefaultFileHandler());
        this.dontUseGzipFileHandler = true;
    }
    
    public boolean isAborted() {
        return this.abort;
    }
    
    public void ensureConnection() throws AuthenticationException {
        BugLog.getInstance().assertNotNull(this.getConnection());
        if (this.getConnection().isOpen()) {
            return;
        }
        final Throwable[] array = { null };
        final boolean[] array2 = { false };
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Client.this.getConnection().open();
                    synchronized (array2) {
                        array2[0] = true;
                    }
                }
                catch (Throwable t) {
                    synchronized (array) {
                        array[0] = t;
                    }
                }
            }
        }, "CVS Server Probe");
        thread.start();
        try {
            thread.join(60000L);
            final Throwable detailMessage;
            synchronized (array) {
                detailMessage = array[0];
            }
            if (detailMessage != null) {
                if (detailMessage instanceof CommandAbortedException) {
                    this.abort();
                    return;
                }
                if (detailMessage instanceof AuthenticationException) {
                    throw (AuthenticationException)detailMessage;
                }
                if (detailMessage instanceof RuntimeException) {
                    throw (RuntimeException)detailMessage;
                }
                if (detailMessage instanceof Error) {
                    throw (Error)detailMessage;
                }
                assert false : detailMessage;
            }
            final boolean b;
            synchronized (array2) {
                b = array2[0];
            }
            if (!b) {
                thread.interrupt();
                throw new AuthenticationException("Timeout, no response from server.", "Timeout, no response from server.");
            }
        }
        catch (InterruptedException ex) {
            thread.interrupt();
            this.abort();
        }
    }
    
    public void processRequests(final List list) throws IOException, UnconfiguredRequestException, ResponseException, CommandAbortedException {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("[processRequests] requests was either null or empty.");
        }
        if (this.abort) {
            throw new CommandAbortedException("Aborted during request processing", CommandException.getLocalMessage("Client.commandAborted", null));
        }
        this.loggedDataInputStream = null;
        this.loggedDataOutputStream = null;
        int n = 1;
        if (this.isFirstCommand()) {
            this.setIsFirstCommand(false);
            int fillInitialRequests = 0;
            if (!this.initialRequestsSent) {
                fillInitialRequests = this.fillInitialRequests(list);
                this.initialRequestsSent = true;
                n = 0;
            }
            if (this.globalOptions != null) {
                final Iterator<Request> iterator = (Iterator<Request>)this.globalOptions.createRequestList().iterator();
                while (iterator.hasNext()) {
                    list.add(fillInitialRequests++, iterator.next());
                }
                if (this.globalOptions.isUseGzip() && this.globalOptions.getCompressionLevel() != 0) {
                    list.add(fillInitialRequests++, new GzipFileContentsRequest(this.globalOptions.getCompressionLevel()));
                }
            }
        }
        else if (this.printConnectionReuseWarning && System.getProperty("javacvs.multiple_commands_warning") == null) {
            System.err.println("WARNING TO DEVELOPERS:");
            System.err.println("Please be warned that attempting to reuse one open connection for more commands is not supported by cvs servers very well.");
            System.err.println("You are advised to open a new Connection each time.");
            System.err.println("If you still want to proceed, please do: System.setProperty(\"javacvs.multiple_commands_warning\", \"false\")");
            System.err.println("That will disable this message.");
        }
        if (!Client.ALLOWED_CONNECTION_REUSE_REQUESTS.contains(list.get(list.size() - 1).getClass())) {
            this.printConnectionReuseWarning = true;
        }
        final boolean fireEnhancedEventSet = this.getEventManager().isFireEnhancedEventSet();
        int value = 0;
        if (fireEnhancedEventSet) {
            final Iterator<GzipFileContentsRequest> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                final FileDetails fileForTransmission = iterator2.next().getFileForTransmission();
                if (fileForTransmission != null && fileForTransmission.getFile().exists()) {
                    ++value;
                }
            }
            this.getEventManager().fireCVSEvent(new EnhancedMessageEvent(this, "Requests_Count", new Integer(value)));
        }
        LoggedDataOutputStream loggedDataOutputStream = this.connection.getOutputStream();
        this.loggedDataOutputStream = loggedDataOutputStream;
        final LinkedList<GzipFileContentsRequest> list2 = new LinkedList<GzipFileContentsRequest>();
        this.transmitFileHandler = this.getUncompressedFileHandler();
        final Iterator<GzipFileContentsRequest> iterator3 = list.iterator();
        while (iterator3.hasNext()) {
            if (this.abort) {
                throw new CommandAbortedException("Aborted during request processing", CommandException.getLocalMessage("Client.commandAborted", null));
            }
            final GzipFileContentsRequest gzipFileContentsRequest = iterator3.next();
            if (gzipFileContentsRequest instanceof GzipFileContentsRequest && this.dontUseGzipFileHandler) {
                this.stderr.println("Warning: The server is not supporting gzip-file-contents request, no compression is used.");
            }
            else {
                if (gzipFileContentsRequest instanceof RootRequest) {
                    if (n != 0) {
                        continue;
                    }
                    n = 1;
                }
                loggedDataOutputStream.writeBytes(gzipFileContentsRequest.getRequestString());
                gzipFileContentsRequest.modifyOutputStream(this.connection);
                if (gzipFileContentsRequest.modifiesInputStream()) {
                    list2.add(gzipFileContentsRequest);
                }
                loggedDataOutputStream = this.connection.getOutputStream();
                final FileDetails fileForTransmission2 = gzipFileContentsRequest.getFileForTransmission();
                if (fileForTransmission2 != null) {
                    final File file = fileForTransmission2.getFile();
                    if (file.exists()) {
                        Logger.logOutput(new String("<Sending file: " + file.getAbsolutePath() + ">\n").getBytes("utf8"));
                        if (fireEnhancedEventSet) {
                            this.getEventManager().fireCVSEvent(new EnhancedMessageEvent(this, "File_Sent_To_Server", file));
                            --value;
                        }
                        if (fileForTransmission2.isBinary()) {
                            this.transmitFileHandler.transmitBinaryFile(file, loggedDataOutputStream);
                        }
                        else {
                            this.transmitFileHandler.transmitTextFile(file, loggedDataOutputStream);
                        }
                        if (fireEnhancedEventSet && value == 0) {
                            this.getEventManager().fireCVSEvent(new EnhancedMessageEvent(this, "All_Requests_Were_Sent", "Ok"));
                        }
                    }
                }
                if (!gzipFileContentsRequest.isResponseExpected()) {
                    continue;
                }
                loggedDataOutputStream.flush();
                final Iterator<Object> iterator4 = list2.iterator();
                while (iterator4.hasNext()) {
                    System.err.println("Modifying the inputstream...");
                    final GzipFileContentsRequest gzipFileContentsRequest2 = iterator4.next();
                    System.err.println("Request is a: " + gzipFileContentsRequest2.getClass().getName());
                    gzipFileContentsRequest2.modifyInputStream(this.connection);
                }
                list2.clear();
                this.handleResponse();
            }
        }
        loggedDataOutputStream.flush();
        this.transmitFileHandler = null;
    }
    
    private ResponseFactory getResponseFactory() {
        if (this.responseFactory == null) {
            this.responseFactory = new ResponseFactory();
        }
        return this.responseFactory;
    }
    
    private void handleResponse() throws ResponseException, CommandAbortedException {
        try {
            final LoggedDataInputStream inputStream = this.connection.getInputStream();
            this.loggedDataInputStream = inputStream;
            int n = -1;
            try {
                n = inputStream.read();
            }
            catch (InterruptedIOException ex3) {
                this.abort();
            }
            while (!this.abort && n != -1) {
                final StringBuffer sb = new StringBuffer();
                while (n != -1 && (char)n != '\n' && (char)n != ' ') {
                    sb.append((char)n);
                    try {
                        n = inputStream.read();
                        continue;
                    }
                    catch (InterruptedIOException ex4) {
                        this.abort();
                    }
                    break;
                }
                final Response response = this.getResponseFactory().createResponse(sb.toString());
                response.process(inputStream, this);
                final boolean terminalResponse = response.isTerminalResponse();
                if (terminalResponse && response instanceof ErrorMessageResponse) {
                    final String message = ((ErrorMessageResponse)response).getMessage();
                    throw new CommandAbortedException(message, message);
                }
                if (terminalResponse) {
                    break;
                }
                if (this.abort) {
                    break;
                }
                try {
                    n = inputStream.read();
                    continue;
                }
                catch (InterruptedIOException ex5) {
                    this.abort();
                }
                break;
            }
            if (this.abort) {
                throw new CommandAbortedException("Aborted during request processing", CommandException.getLocalMessage("Client.commandAborted", null));
            }
        }
        catch (EOFException ex) {
            throw new ResponseException(ex, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (IOException ex2) {
            throw new ResponseException(ex2);
        }
    }
    
    public boolean executeCommand(final Command command, final GlobalOptions globalOptions) throws CommandException, CommandAbortedException, AuthenticationException {
        BugLog.getInstance().assertNotNull(command);
        BugLog.getInstance().assertNotNull(globalOptions);
        this.globalOptions = globalOptions;
        this.getUncompressedFileHandler().setGlobalOptions(globalOptions);
        this.getGzipFileHandler().setGlobalOptions(globalOptions);
        try {
            this.eventManager.addCVSListener(command);
            command.execute(this, this.eventManager);
        }
        finally {
            this.eventManager.removeCVSListener(command);
        }
        return !command.hasFailed();
    }
    
    public long getCounter() {
        long n = 0L;
        if (this.loggedDataInputStream != null) {
            n += this.loggedDataInputStream.getCounter();
        }
        if (this.loggedDataOutputStream != null) {
            n += this.loggedDataOutputStream.getCounter();
        }
        return n;
    }
    
    public String convertPathname(String str, final String s) {
        final String substring = s.substring(s.lastIndexOf(47) + 1);
        if (str.startsWith("./")) {
            str = str.substring(1);
        }
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        return this.getLocalPath() + '/' + str + substring;
    }
    
    public String getRepository() {
        return this.connection.getRepository();
    }
    
    public void updateAdminData(final String str, String substring, final Entry entry) throws IOException {
        final String string = this.localPath + '/' + str;
        if (substring.startsWith(this.getRepository())) {
            substring = substring.substring(this.getRepository().length() + 1);
        }
        else if (!this.warned) {
            System.err.println("#65188 warning C/S protocol error (section 5.10). It's regurarly observed with cvs 1.12.xx servers.\n" + "  unexpected pathname=" + substring + " missing root prefix=" + this.getRepository() + "\n" + "  relaxing, but who knows all consequences....");
            this.warned = true;
        }
        this.adminHandler.updateAdminData(string, substring, entry, this.globalOptions);
    }
    
    public void setNextFileDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public Date getNextFileDate() {
        final Date modifiedDate = this.modifiedDate;
        this.modifiedDate = null;
        return modifiedDate;
    }
    
    public Entry getEntry(final File file) throws IOException {
        return this.adminHandler.getEntry(file);
    }
    
    public Iterator getEntries(final File file) throws IOException {
        return this.adminHandler.getEntries(file);
    }
    
    public boolean exists(final File file) {
        return this.adminHandler.exists(file);
    }
    
    public String getRepositoryForDirectory(String pathname) throws IOException {
        try {
            return this.adminHandler.getRepositoryForDirectory(pathname, this.getRepository());
        }
        catch (IOException ex) {
            try {
                pathname = new File(pathname).getCanonicalPath();
            }
            catch (IOException ex2) {}
            for (pathname = pathname.replace('\\', '/'); pathname.endsWith("/"); pathname = pathname.substring(0, pathname.length() - 1)) {}
            String s = this.getLocalPath();
            try {
                s = new File(this.getLocalPath()).getCanonicalPath();
            }
            catch (IOException ex3) {}
            String s2;
            for (s2 = s.replace('\\', '/'); s2.endsWith("/"); s2 = s2.substring(0, s2.length() - 1)) {}
            final int length = s2.length();
            String s3;
            if (pathname.length() >= length) {
                s3 = this.getRepository() + pathname.substring(length);
            }
            else {
                s3 = this.getRepository();
            }
            return s3;
        }
    }
    
    public String getRepositoryForDirectory(final File file) throws IOException {
        return this.adminHandler.getRepositoryForDirectory(file.getAbsolutePath(), this.getRepository());
    }
    
    public void setEntry(final File file, final Entry entry) throws IOException {
        this.adminHandler.setEntry(file, entry);
    }
    
    public void removeEntry(final File file) throws IOException {
        this.adminHandler.removeEntry(file);
    }
    
    public void removeLocalFile(final String s) throws IOException {
        this.transmitFileHandler.removeLocalFile(s);
    }
    
    public void removeLocalFile(final String str, final String s) throws IOException {
        final int lastIndex = s.lastIndexOf(47);
        if (lastIndex <= 0) {
            return;
        }
        final File file = new File(this.getLocalPath(), str + s.substring(lastIndex + 1));
        this.removeLocalFile(file.getAbsolutePath());
        this.removeEntry(file);
    }
    
    public void renameLocalFile(final String s, final String s2) throws IOException {
        this.transmitFileHandler.renameLocalFile(s, s2);
    }
    
    public EventManager getEventManager() {
        return this.eventManager;
    }
    
    public GlobalOptions getGlobalOptions() {
        return this.globalOptions;
    }
    
    public synchronized void abort() {
        this.abort = true;
    }
    
    public Set getAllFiles(final File file) throws IOException {
        return this.adminHandler.getAllFiles(file);
    }
    
    public void setIgnoreFileFilter(final IgnoreFileFilter ignoreFileFilter) {
        this.ignoreFileFilter = ignoreFileFilter;
    }
    
    public IgnoreFileFilter getIgnoreFileFilter() {
        return this.ignoreFileFilter;
    }
    
    public boolean shouldBeIgnored(final File file, final String s) {
        return this.ignoreFileFilter != null && this.ignoreFileFilter.shouldBeIgnored(file, s);
    }
    
    public String getStickyTagForDirectory(final File file) {
        return this.adminHandler.getStickyTagForDirectory(file);
    }
    
    public void setValidRequests(final String str) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            this.validRequests.put(stringTokenizer.nextToken(), this);
        }
    }
    
    private int fillInitialRequests(final List list) {
        int n = 0;
        list.add(n++, new RootRequest(this.getRepository()));
        list.add(n++, new UseUnchangedRequest());
        list.add(n++, new ValidRequestsRequest());
        list.add(n++, new ValidResponsesRequest());
        return n;
    }
    
    public void addWrapper(final StringPattern stringPattern, final KeywordSubstitutionOptions keywordSubstitutionOptions) {
        if (this.wrappersMap == null) {
            throw new IllegalArgumentException("This method should be called by WrapperSendResponse only.");
        }
        this.wrappersMap.put(stringPattern, keywordSubstitutionOptions);
    }
    
    public Map getWrappersMap() throws CommandException {
        if (this.wrappersMap == null) {
            this.wrappersMap = new HashMap();
            final ArrayList<WrapperSendRequest> list = new ArrayList<WrapperSendRequest>();
            list.add(new WrapperSendRequest());
            final boolean firstCommand = this.isFirstCommand();
            try {
                this.processRequests(list);
            }
            catch (Exception ex) {
                throw new CommandException(ex, "An error during obtaining server wrappers.");
            }
            finally {
                this.setIsFirstCommand(firstCommand);
            }
            this.wrappersMap = Collections.unmodifiableMap((Map<?, ?>)this.wrappersMap);
        }
        return this.wrappersMap;
    }
    
    static {
        ALLOWED_CONNECTION_REUSE_REQUESTS = new HashSet(Arrays.asList(ExpandModulesRequest.class, WrapperSendRequest.class));
    }
    
    public interface Factory
    {
        Client createClient();
    }
}
