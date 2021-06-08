// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Grape
{
    private static boolean enableGrapes;
    private static boolean enableAutoDownload;
    protected static GrapeEngine instance;
    
    public static boolean getEnableGrapes() {
        return Grape.enableGrapes;
    }
    
    public static void setEnableGrapes(final boolean enableGrapes) {
        Grape.enableGrapes = enableGrapes;
    }
    
    public static boolean getEnableAutoDownload() {
        return Grape.enableAutoDownload;
    }
    
    public static void setEnableAutoDownload(final boolean enableAutoDownload) {
        Grape.enableAutoDownload = enableAutoDownload;
    }
    
    public static synchronized GrapeEngine getInstance() {
        if (Grape.instance == null) {
            try {
                Grape.instance = (GrapeEngine)Class.forName("groovy.grape.GrapeIvy").newInstance();
            }
            catch (InstantiationException e) {}
            catch (IllegalAccessException e2) {}
            catch (ClassNotFoundException ex) {}
        }
        return Grape.instance;
    }
    
    public static void grab(final String endorsed) {
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                instance.grab(endorsed);
            }
        }
    }
    
    public static void grab(final Map<String, Object> dependency) {
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                if (!dependency.containsKey("autoDownload")) {
                    dependency.put("autoDownload", Grape.enableAutoDownload);
                }
                instance.grab(dependency);
            }
        }
    }
    
    public static void grab(final Map<String, Object> args, final Map... dependencies) {
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                if (!args.containsKey("autoDownload")) {
                    args.put("autoDownload", Grape.enableAutoDownload);
                }
                instance.grab(args, dependencies);
            }
        }
    }
    
    public static Map<String, Map<String, List<String>>> enumerateGrapes() {
        Map<String, Map<String, List<String>>> grapes = null;
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                grapes = instance.enumerateGrapes();
            }
        }
        if (grapes == null) {
            return Collections.emptyMap();
        }
        return grapes;
    }
    
    public static URI[] resolve(final Map<String, Object> args, final Map... dependencies) {
        return resolve(args, (List)null, dependencies);
    }
    
    public static URI[] resolve(final Map<String, Object> args, final List depsInfo, final Map... dependencies) {
        URI[] uris = null;
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                if (!args.containsKey("autoDownload")) {
                    args.put("autoDownload", Grape.enableAutoDownload);
                }
                uris = instance.resolve(args, depsInfo, dependencies);
            }
        }
        if (uris == null) {
            return new URI[0];
        }
        return uris;
    }
    
    public static Map[] listDependencies(final ClassLoader cl) {
        Map[] maps = null;
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                maps = instance.listDependencies(cl);
            }
        }
        if (maps == null) {
            return new Map[0];
        }
        return maps;
    }
    
    public static void addResolver(final Map<String, Object> args) {
        if (Grape.enableGrapes) {
            final GrapeEngine instance = getInstance();
            if (instance != null) {
                instance.addResolver(args);
            }
        }
    }
    
    static {
        Grape.enableGrapes = Boolean.valueOf(System.getProperties().getProperty("groovy.grape.enable", "true"));
        Grape.enableAutoDownload = Boolean.valueOf(System.getProperties().getProperty("groovy.grape.autoDownload", "true"));
    }
}
