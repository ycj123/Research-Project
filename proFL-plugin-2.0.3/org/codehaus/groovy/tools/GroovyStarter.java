// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileInputStream;

public class GroovyStarter
{
    static void printUsage() {
        System.out.println("possible programs are 'groovyc','groovy','console', and 'groovysh'");
        System.exit(1);
    }
    
    public static void rootLoader(final String[] args) {
        String conf = System.getProperty("groovy.starter.conf", null);
        final LoaderConfiguration lc = new LoaderConfiguration();
        boolean hadMain = false;
        boolean hadConf = false;
        boolean hadCP = false;
        int argsOffset = 0;
        while (args.length - argsOffset > 0 && (!hadMain || !hadConf || !hadCP)) {
            if (args[argsOffset].equals("--classpath")) {
                if (hadCP) {
                    break;
                }
                if (args.length == argsOffset + 1) {
                    exit("classpath parameter needs argument");
                }
                lc.addClassPath(args[argsOffset + 1]);
                argsOffset += 2;
                hadCP = true;
            }
            else if (args[argsOffset].equals("--main")) {
                if (hadMain) {
                    break;
                }
                if (args.length == argsOffset + 1) {
                    exit("main parameter needs argument");
                }
                lc.setMainClass(args[argsOffset + 1]);
                argsOffset += 2;
                hadMain = true;
            }
            else {
                if (!args[argsOffset].equals("--conf")) {
                    break;
                }
                if (hadConf) {
                    break;
                }
                if (args.length == argsOffset + 1) {
                    exit("conf parameter needs argument");
                }
                conf = args[argsOffset + 1];
                argsOffset += 2;
                hadConf = true;
            }
        }
        final String confOverride = System.getProperty("groovy.starter.conf.override", null);
        if (confOverride != null) {
            conf = confOverride;
        }
        if (lc.getMainClass() == null && conf == null) {
            exit("no configuration file or main class specified");
        }
        final String[] newArgs = new String[args.length - argsOffset];
        for (int i = 0; i < newArgs.length; ++i) {
            newArgs[i] = args[i + argsOffset];
        }
        if (conf != null) {
            try {
                lc.configure(new FileInputStream(conf));
            }
            catch (Exception e) {
                System.err.println("exception while configuring main class loader:");
                exit(e);
            }
        }
        final ClassLoader loader = new RootLoader(lc);
        Method m = null;
        try {
            final Class c = loader.loadClass(lc.getMainClass());
            m = c.getMethod("main", String[].class);
        }
        catch (ClassNotFoundException e2) {
            exit(e2);
        }
        catch (SecurityException e3) {
            exit(e3);
        }
        catch (NoSuchMethodException e4) {
            exit(e4);
        }
        try {
            m.invoke(null, newArgs);
        }
        catch (IllegalArgumentException e5) {
            exit(e5);
        }
        catch (IllegalAccessException e6) {
            exit(e6);
        }
        catch (InvocationTargetException e7) {
            exit(e7);
        }
    }
    
    private static void exit(final Exception e) {
        e.printStackTrace();
        System.exit(1);
    }
    
    private static void exit(final String msg) {
        System.err.println(msg);
        System.exit(1);
    }
    
    public static void main(final String[] args) {
        try {
            rootLoader(args);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
