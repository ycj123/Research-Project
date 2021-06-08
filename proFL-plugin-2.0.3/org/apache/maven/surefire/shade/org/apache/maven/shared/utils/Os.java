// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils;

import java.util.Iterator;
import java.util.Locale;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Os
{
    public static final String OS_NAME;
    public static final String OS_ARCH;
    public static final String OS_VERSION;
    public static final String PATH_SEP;
    public static final String LINE_SEP;
    public static final String OS_FAMILY;
    private static final Set<String> VALID_FAMILIES;
    private String family;
    public static final String FAMILY_WINDOWS = "windows";
    public static final String FAMILY_WIN9X = "win9x";
    public static final String FAMILY_NT = "winnt";
    public static final String FAMILY_OS2 = "os/2";
    public static final String FAMILY_NETWARE = "netware";
    public static final String FAMILY_DOS = "dos";
    public static final String FAMILY_MAC = "mac";
    public static final String FAMILY_TANDEM = "tandem";
    public static final String FAMILY_UNIX = "unix";
    public static final String FAMILY_OPENVMS = "openvms";
    public static final String FAMILY_ZOS = "z/os";
    public static final String FAMILY_OS400 = "os/400";
    private static final String DARWIN = "darwin";
    
    public static Set<String> getValidFamilies() {
        if (Os.VALID_FAMILIES != null) {
            return Os.VALID_FAMILIES;
        }
        final Set<String> valid = new HashSet<String>();
        valid.add("dos");
        valid.add("mac");
        valid.add("netware");
        valid.add("winnt");
        valid.add("openvms");
        valid.add("os/2");
        valid.add("os/400");
        valid.add("tandem");
        valid.add("unix");
        valid.add("win9x");
        valid.add("windows");
        valid.add("z/os");
        return Collections.unmodifiableSet((Set<? extends String>)valid);
    }
    
    public Os() {
    }
    
    public Os(final String family) {
        this.setFamily(family);
    }
    
    private void setFamily(final String f) {
        this.family = f.toLowerCase(Locale.ENGLISH);
    }
    
    boolean eval() {
        return isOs(this.family, null, null, null);
    }
    
    public static boolean isFamily(final String family) {
        return isOs(family, null, null, null);
    }
    
    public static boolean isName(final String name) {
        return isOs(null, name, null, null);
    }
    
    public static boolean isArch(final String arch) {
        return isOs(null, null, arch, null);
    }
    
    public static boolean isVersion(final String version) {
        return isOs(null, null, null, version);
    }
    
    private static boolean isOs(final String family, final String name, final String arch, final String version) {
        boolean retValue = false;
        if (family != null || name != null || arch != null || version != null) {
            boolean isFamily = true;
            boolean isName = true;
            boolean isArch = true;
            boolean isVersion = true;
            if (family != null) {
                final boolean isWindows = Os.OS_NAME.contains("windows");
                boolean is9x = false;
                boolean isNT = false;
                if (isWindows) {
                    is9x = (Os.OS_NAME.contains("95") || Os.OS_NAME.contains("98") || Os.OS_NAME.contains("me") || Os.OS_NAME.contains("ce"));
                    isNT = !is9x;
                }
                if (family.equals("windows")) {
                    isFamily = isWindows;
                }
                else if (family.equals("win9x")) {
                    isFamily = (isWindows && is9x);
                }
                else if (family.equals("winnt")) {
                    isFamily = (isWindows && isNT);
                }
                else if (family.equals("os/2")) {
                    isFamily = Os.OS_NAME.contains("os/2");
                }
                else if (family.equals("netware")) {
                    isFamily = Os.OS_NAME.contains("netware");
                }
                else if (family.equals("dos")) {
                    isFamily = (Os.PATH_SEP.equals(";") && !isFamily("netware"));
                }
                else if (family.equals("mac")) {
                    isFamily = (Os.OS_NAME.contains("mac") || Os.OS_NAME.contains("darwin"));
                }
                else if (family.equals("tandem")) {
                    isFamily = Os.OS_NAME.contains("nonstop_kernel");
                }
                else if (family.equals("unix")) {
                    isFamily = (Os.PATH_SEP.equals(":") && !isFamily("openvms") && (!isFamily("mac") || Os.OS_NAME.endsWith("x") || Os.OS_NAME.contains("darwin")));
                }
                else if (family.equals("z/os")) {
                    isFamily = (Os.OS_NAME.contains("z/os") || Os.OS_NAME.contains("os/390"));
                }
                else if (family.equals("os/400")) {
                    isFamily = Os.OS_NAME.contains("os/400");
                }
                else if (family.equals("openvms")) {
                    isFamily = Os.OS_NAME.contains("openvms");
                }
                else {
                    isFamily = Os.OS_NAME.contains(family.toLowerCase(Locale.US));
                }
            }
            if (name != null) {
                isName = name.equals(Os.OS_NAME);
            }
            if (arch != null) {
                isArch = arch.equals(Os.OS_ARCH);
            }
            if (version != null) {
                isVersion = version.equals(Os.OS_VERSION);
            }
            retValue = (isFamily && isName && isArch && isVersion);
        }
        return retValue;
    }
    
    private static String getOsFamily() {
        final Set<String> families = getValidFamilies();
        for (final String fam : families) {
            if (isFamily(fam)) {
                return fam;
            }
        }
        return null;
    }
    
    public static boolean isValidFamily(final String family) {
        return Os.VALID_FAMILIES.contains(family);
    }
    
    static {
        OS_NAME = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        OS_ARCH = System.getProperty("os.arch").toLowerCase(Locale.ENGLISH);
        OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.ENGLISH);
        PATH_SEP = System.getProperty("path.separator");
        LINE_SEP = System.getProperty("line.separator");
        OS_FAMILY = getOsFamily();
        VALID_FAMILIES = getValidFamilies();
    }
}
