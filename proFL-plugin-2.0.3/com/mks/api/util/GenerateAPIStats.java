// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.CmdRunner;
import com.mks.api.response.Response;
import java.io.IOException;
import com.mks.api.IntegrationPoint;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.Session;
import com.mks.api.response.APIException;

public class GenerateAPIStats
{
    private static String host;
    private static int port;
    private static String user;
    private static String pass;
    private static String command_domain;
    private static String command;
    private static int test_count;
    private static String impersonated_user;
    
    GenerateAPIStats(final int testCase) {
        try {
            switch (testCase) {
                case 1: {
                    this.testCommandClean();
                    break;
                }
                case 2: {
                    this.testCommandSlowClean();
                    break;
                }
                case 3: {
                    this.testCommandSlowDirty();
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Illegal test case.");
                }
            }
        }
        catch (APIException apie) {
            System.out.print(apie.getMessage());
        }
    }
    
    private Session connect() throws APIException {
        final IntegrationPointFactory ipf = IntegrationPointFactory.getInstance();
        final IntegrationPoint ip = ipf.createIntegrationPoint(GenerateAPIStats.host, GenerateAPIStats.port, false, 0, 0);
        return ip.createSession(GenerateAPIStats.user, GenerateAPIStats.pass);
    }
    
    private void disconnect(final Session session) throws APIException {
        if (session != null) {
            try {
                session.release();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void testCommandClean() throws APIException {
        Session session = null;
        Response response = null;
        CmdRunner runner = null;
        try {
            session = this.connect();
            runner = session.createCmdRunner();
            for (int i = 0; i < GenerateAPIStats.test_count; ++i) {
                response = this.executeCommand(runner);
            }
        }
        finally {
            if (response != null) {
                response.release();
            }
            if (runner != null) {
                runner.release();
            }
            this.disconnect(session);
        }
    }
    
    private void testCommandSlowClean() throws APIException {
        Session session = null;
        Response response = null;
        CmdRunner runner = null;
        for (int i = 0; i < GenerateAPIStats.test_count; ++i) {
            try {
                session = this.connect();
                runner = session.createCmdRunner();
                response = this.executeCommand(runner);
            }
            finally {
                if (response != null) {
                    response.release();
                }
                if (runner != null) {
                    runner.release();
                }
                this.disconnect(session);
            }
        }
    }
    
    private void testCommandSlowDirty() throws APIException {
        Session session = null;
        Response response = null;
        CmdRunner runner = null;
        for (int i = 0; i < GenerateAPIStats.test_count; ++i) {
            try {
                session = this.connect();
                runner = session.createCmdRunner();
                response = this.executeCommand(runner);
            }
            finally {}
        }
    }
    
    private Response executeCommand(final CmdRunner runner) throws APIException {
        final Command cmd = new Command(GenerateAPIStats.command_domain, GenerateAPIStats.command);
        if (GenerateAPIStats.impersonated_user != null) {
            cmd.addOption(new Option("impersonateuser", GenerateAPIStats.impersonated_user));
        }
        final Response response = runner.execute(cmd);
        final APIException apie = response.getAPIException();
        if (apie != null) {
            throw apie;
        }
        return response;
    }
    
    public static void main(final String[] args) {
        int testCase = 0;
        if (args == null || args.length < 8) {
            throw new IllegalArgumentException();
        }
        GenerateAPIStats.host = args[0];
        GenerateAPIStats.port = new Integer(args[1]);
        GenerateAPIStats.user = args[2];
        GenerateAPIStats.pass = args[3];
        GenerateAPIStats.command_domain = args[4];
        GenerateAPIStats.command = args[5];
        GenerateAPIStats.test_count = new Integer(args[6]);
        testCase = new Integer(args[7]);
        if (args[8] != null) {
            GenerateAPIStats.impersonated_user = args[8];
        }
        new GenerateAPIStats(testCase);
    }
    
    static {
        GenerateAPIStats.impersonated_user = null;
    }
}
