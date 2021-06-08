// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import com.mks.api.Session;
import com.mks.api.IntegrationPoint;
import com.mks.api.CmdRunner;
import com.mks.api.response.APIError;
import com.mks.api.response.impl.CommandException;
import com.mks.api.response.impl.InvalidValueException;
import com.mks.api.response.impl.InvalidDirectiveException;
import com.mks.api.response.InvalidIntegrationPointException;
import com.mks.api.response.InvalidHostException;
import com.mks.api.response.ApplicationConnectionException;
import com.mks.api.response.UnsupportedApplicationException;
import com.mks.api.response.APIException;
import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.Item;
import com.mks.api.response.ItemList;
import java.util.List;
import com.mks.api.response.Result;
import com.mks.api.response.Field;
import com.mks.api.response.Response;
import com.mks.api.response.impl.ResponseWalker;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.Option;
import com.mks.api.Command;

public class GenericCommandHandler
{
    private static final String IP_HOST = "--iphostname=";
    private static final String IP_PORT = "--ipport=";
    private static final String IP_LOCAL = "--iplocal";
    private static final String IP_AUTOSTART = "--ipautostart";
    private static final String IP_SECURE = "--ipsecure";
    private static final String NAMED_SESSION = "--namedsession";
    private static final String SESSION_USER = "--sessionuser=";
    private static final String SESSION_PASS = "--sessionpassword=";
    private static final String COMMAND_TYPE = "--commandtype=";
    private static final String RESPONSE_TYPE = "--responsetype=";
    private static final String DIRECTIVE = "--directive=";
    private static final String DEFAULT_HOST = "--defaulthostname=";
    private static final String DEFAULT_PORT = "--defaultport=";
    private static final String DEFAULT_USER = "--defaultuser=";
    private static final String DEFAULT_PASS = "--defaultpassword=";
    private static final String DEFAULT_IMP = "--defaultimpersonateduser=";
    private static final String SHOW_ALL_PROPS = "--showAllProperties=";
    private static final String OBJECT_CMD = "OBJECT";
    private static final String ARRAY_CMD = "ARRAY";
    private static final String NO_INTERIM = "NO_INTERIM";
    private static final String INTERIM_NO_CACHE = "INTERIM_NO_CACHE";
    private static final String INTERIM_CACHE = "INTERIM_CACHE";
    
    private static void printErrorMsg() {
        System.out.print("Invalid command.  Syntax: java GenericCommandHanlder <options> <Integrity CLI command>");
        System.out.println("where options are:");
        System.out.println("\t--iphostname=<hostname>");
        System.out.println("\t--ipport=<port>");
        System.out.println("\t--iplocal");
        System.out.println("\t--ipautostart (--iplocal must be specified)");
        System.out.println("\t--ipsecure (observed only if --iplocal is not specified)");
        System.out.println("\t--namedsession");
        System.out.println("\t--sessionuser=<username> (--namedsession must be specified)");
        System.out.println("\t--sessionpassword=<password> (--namedsession must be specified)");
        System.out.println("\t--responsetype=<NO_INTERIM | INTERIM_NO_CACHE | INTERIM_CACHE> (default: NO_INTERIM)");
        System.out.println("\t--commandtype=<OBJECT | ARRAY> (default: OBJECT)");
        System.out.println("\t--directive=<directives>");
        System.out.println("\t--defaulthostname=<hostname> (optional)");
        System.out.println("\t--defaultport=<port> (optional)");
        System.out.println("\t--defaultuser=<username> (optional)");
        System.out.println("\t--defaultpassword=<password> (optional)");
        System.out.println("\t--defaultimpersonateduser=<username> (optional)");
    }
    
    public static void main(final String[] args) {
        if (args.length < 4) {
            printErrorMsg();
            System.exit(1);
        }
        boolean ipIsLocal = false;
        boolean ipAutoStart = false;
        String ipHost = null;
        int ipPort = 0;
        boolean ipSecure = false;
        boolean commonSession = true;
        String sessionUser = null;
        String sessionPass = null;
        String defaultHost = null;
        int defaultPort = 0;
        String defaultUser = null;
        String defaultPass = null;
        String defaultImp = null;
        String directive = null;
        boolean useArray = false;
        boolean useInterim = false;
        boolean cacheInterim = false;
        int i;
        for (i = 0; i < args.length && args[i].startsWith("--"); ++i) {
            if (args[i].startsWith("--iphostname=")) {
                ipHost = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--ipport=")) {
                final String t = args[i].substring(args[i].indexOf(61) + 1);
                ipPort = Integer.parseInt(t);
            }
            else if (args[i].startsWith("--iplocal")) {
                ipIsLocal = true;
            }
            else if (args[i].startsWith("--ipautostart")) {
                ipAutoStart = true;
            }
            else if (args[i].startsWith("--ipsecure")) {
                ipSecure = true;
            }
            else if (args[i].startsWith("--namedsession")) {
                commonSession = false;
            }
            else if (args[i].startsWith("--sessionuser=")) {
                sessionUser = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--sessionpassword=")) {
                sessionPass = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--defaulthostname=")) {
                defaultHost = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--defaultport=")) {
                final String t = args[i].substring(args[i].indexOf(61) + 1);
                defaultPort = Integer.parseInt(t);
            }
            else if (args[i].startsWith("--defaultuser=")) {
                defaultUser = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--defaultpassword=")) {
                defaultPass = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--defaultimpersonateduser=")) {
                defaultImp = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--commandtype=")) {
                final String t = args[i].substring(args[i].indexOf(61) + 1);
                useArray = t.equals("ARRAY");
            }
            else if (args[i].startsWith("--responsetype=")) {
                final String t = args[i].substring(args[i].indexOf(61) + 1);
                if (t.equals("INTERIM_NO_CACHE")) {
                    useInterim = true;
                    cacheInterim = false;
                }
                else if (t.equals("INTERIM_CACHE")) {
                    useInterim = true;
                    cacheInterim = true;
                }
                else {
                    useInterim = false;
                    cacheInterim = false;
                }
            }
            else if (args[i].startsWith("--directive=")) {
                directive = args[i].substring(args[i].indexOf(61) + 1);
            }
            else if (args[i].startsWith("--showAllProperties=")) {
                System.setProperty("com.mks.api.response.showAllProperties", args[i].substring(args[i].indexOf(61) + 1));
            }
            else {
                System.out.println("Ignoring unknown option: " + args[i]);
            }
        }
        if (args.length - i < 2) {
            printErrorMsg();
            System.exit(1);
        }
        String[] cmdArr = null;
        Command cmd = null;
        if (useArray) {
            cmdArr = new String[args.length - i];
            System.arraycopy(args, i, cmdArr, 0, cmdArr.length);
        }
        else {
            cmd = new Command(args[i], args[++i]);
            ++i;
            while (i < args.length) {
                if (args[i].startsWith("--")) {
                    final int idx = args[i].indexOf(61);
                    cmd.addOption(new Option(args[i].substring(2, idx), args[i].substring(idx + 1)));
                }
                else if (args[i].startsWith("-")) {
                    final int idx = args[i].indexOf(32);
                    if (idx < 0) {
                        cmd.addOption(new Option(args[i].substring(1)));
                    }
                    else {
                        cmd.addOption(new Option(args[i].substring(1, idx), args[i].substring(idx + 1)));
                    }
                }
                else {
                    cmd.addSelection(args[i]);
                }
                ++i;
            }
        }
        CmdRunner cr = null;
        try {
            final IntegrationPointFactory ipf = IntegrationPointFactory.getInstance();
            IntegrationPointFactory.getLogger().configure("API", 3, 10);
            IntegrationPoint ip = null;
            if (ipIsLocal) {
                ip = ipf.createLocalIntegrationPoint(0, 0);
                ip.setAutoStartIntegrityClient(ipAutoStart);
            }
            else {
                ip = ipf.createIntegrationPoint(ipHost, ipPort, ipSecure, 0, 0);
            }
            Session session = null;
            if (commonSession) {
                session = ip.getCommonSession();
            }
            else {
                session = ip.createSession(sessionUser, sessionPass);
            }
            cr = session.createCmdRunner();
            cr.setDefaultHostname(defaultHost);
            cr.setDefaultPort(defaultPort);
            cr.setDefaultUsername(defaultUser);
            cr.setDefaultPassword(defaultPass);
            cr.setDefaultImpersonationUser(defaultImp);
            Response response = null;
            if (useArray) {
                if (useInterim) {
                    response = cr.executeWithInterim(cmdArr, cacheInterim);
                }
                else {
                    response = cr.execute(cmdArr);
                }
            }
            else if (useInterim) {
                response = cr.executeWithInterim(cmd, cacheInterim);
            }
            else {
                response = cr.execute(cmd);
            }
            final ResponseWalker responseWalker = new ResponseWalker(response);
            if (directive == null) {
                directive = "response";
            }
            responseWalker.walk(directive);
            final Object objectVal = responseWalker.getCurrentObject();
            System.setProperty("com.mks.api.util.internal.TestHarnessOutput", "true");
            if (objectVal instanceof Response) {
                ResponseUtil.printResponse((Response)objectVal, 1, System.out);
            }
            else if (objectVal instanceof Field) {
                ResponseUtil.printField((Field)objectVal, 1, System.out);
            }
            else if (objectVal instanceof Result) {
                ResponseUtil.printResult((Result)objectVal, 1, System.out);
            }
            else if (objectVal instanceof List) {
                ResponseUtil.printList((List)objectVal, 1, System.out);
            }
            else if (objectVal instanceof ItemList) {
                ResponseUtil.printItemList((ItemList)objectVal, 1, System.out);
            }
            else if (objectVal instanceof Item) {
                ResponseUtil.printItem((Item)objectVal, 1, System.out);
            }
            else if (objectVal instanceof SubRoutine) {
                ResponseUtil.printSubRoutine((SubRoutine)objectVal, 1, System.out);
            }
            else if (objectVal instanceof WorkItem) {
                ResponseUtil.printWorkItem((WorkItem)objectVal, 1, System.out);
            }
            else {
                System.out.println("List Value:");
                System.out.println(objectVal);
            }
        }
        catch (UnsupportedApplicationException ex) {
            ResponseUtil.printAPIException("UnsupportedApplicationException:", ex, 0, System.out);
        }
        catch (ApplicationConnectionException ex2) {
            ResponseUtil.printAPIException("ApplicationConnectionException:", ex2, 0, System.out);
        }
        catch (InvalidHostException ex3) {
            ResponseUtil.printAPIException("InvalidHostException:", ex3, 0, System.out);
        }
        catch (InvalidIntegrationPointException ex4) {
            ResponseUtil.printAPIException("InvalidIntegrationPointException:", ex4, 0, System.out);
        }
        catch (APIException ex5) {
            final Response res = ex5.getResponse();
            if (res != null) {
                ResponseUtil.printResponse(res, 0, System.out);
            }
            else {
                ResponseUtil.printAPIException(ex5, 0, System.out);
            }
        }
        catch (InvalidDirectiveException ex6) {
            System.out.println("ERROR: Directive given is invalid.");
            System.out.println(ex6.getMessage());
        }
        catch (InvalidValueException ex7) {
            System.out.println("InvalidValueException:");
            System.out.println(ex7.getMessage());
        }
        catch (CommandException ex8) {
            ex8.printStackTrace(System.err);
        }
        catch (Exception ex9) {
            System.out.println(ex9.getClass());
            System.out.println(ex9.getMessage());
            ex9.printStackTrace(System.err);
        }
        catch (APIError err) {
            System.out.println("APIError:");
            System.out.println(err.getClass());
            System.out.println(err.getMessage());
        }
        finally {
            final Session uas = (cr == null) ? null : cr.getSession();
            try {
                if (cr != null) {
                    cr.release();
                }
                if (uas != null) {
                    uas.release();
                }
            }
            catch (Exception ex10) {}
        }
    }
}
