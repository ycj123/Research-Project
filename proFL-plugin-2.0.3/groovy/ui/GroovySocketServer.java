// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.InetAddress;
import groovy.lang.GroovyShell;
import java.net.URL;

public class GroovySocketServer implements Runnable
{
    private URL url;
    private GroovyShell groovy;
    private boolean isScriptFile;
    private String scriptFilenameOrText;
    private boolean autoOutput;
    
    public GroovySocketServer(final GroovyShell groovy, final boolean isScriptFile, final String scriptFilenameOrText, final boolean autoOutput, final int port) {
        this.groovy = groovy;
        this.isScriptFile = isScriptFile;
        this.scriptFilenameOrText = scriptFilenameOrText;
        this.autoOutput = autoOutput;
        try {
            this.url = new URL("http", InetAddress.getLocalHost().getHostAddress(), port, "/");
            System.out.println("groovy is listening on port " + port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }
    
    public void run() {
        try {
            final ServerSocket serverSocket = new ServerSocket(this.url.getPort());
            while (true) {
                Script script;
                if (this.isScriptFile) {
                    final GroovyMain gm = new GroovyMain();
                    script = this.groovy.parse(DefaultGroovyMethods.getText(gm.huntForTheScriptFile(this.scriptFilenameOrText)));
                }
                else {
                    script = this.groovy.parse(this.scriptFilenameOrText);
                }
                new GroovyClientConnection(script, this.autoOutput, serverSocket.accept());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    class GroovyClientConnection implements Runnable
    {
        private Script script;
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private boolean autoOutputFlag;
        
        GroovyClientConnection(final Script script, final boolean autoOutput, final Socket socket) throws IOException {
            this.script = script;
            this.autoOutputFlag = autoOutput;
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream());
            new Thread(this, "Groovy client connection - " + socket.getInetAddress().getHostAddress()).start();
        }
        
        public void run() {
            try {
                String line = null;
                this.script.setProperty("out", this.writer);
                this.script.setProperty("socket", this.socket);
                this.script.setProperty("init", Boolean.TRUE);
                while ((line = this.reader.readLine()) != null) {
                    this.script.setProperty("line", line);
                    final Object o = this.script.run();
                    this.script.setProperty("init", Boolean.FALSE);
                    if (o != null) {
                        if ("success".equals(o)) {
                            break;
                        }
                        if (this.autoOutputFlag) {
                            this.writer.println(o);
                        }
                    }
                    this.writer.flush();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                try {
                    this.writer.flush();
                    this.writer.close();
                }
                finally {
                    try {
                        this.socket.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    this.writer.flush();
                    this.writer.close();
                }
                finally {
                    try {
                        this.socket.close();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }
}
