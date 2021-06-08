// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.build;

import java.io.FilenameFilter;
import java.io.File;

public class ANTLR
{
    public static String compiler;
    public static String jarName;
    public static String root;
    public static String[] srcdir;
    
    public ANTLR() {
        ANTLR.compiler = System.getProperty("org.pitest.reloc.antlr.common.build.compiler", ANTLR.compiler);
        ANTLR.root = System.getProperty("org.pitest.reloc.antlr.common.build.root", ANTLR.root);
    }
    
    public String getName() {
        return "ANTLR";
    }
    
    public void build(final Tool tool) {
        if (!this.rootIsValidANTLRDir(tool)) {
            return;
        }
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/antlr.g");
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/tokdef.g");
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/preprocessor/preproc.g");
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/actions/java/action.g");
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/actions/cpp/action.g");
        tool.antlr(ANTLR.root + "/org/pitest/reloc/antlr/common/actions/csharp/action.g");
        for (int i = 0; i < ANTLR.srcdir.length; ++i) {
            tool.system(ANTLR.compiler + " -d " + ANTLR.root + " " + ANTLR.root + "/" + ANTLR.srcdir[i] + "/*.java");
        }
    }
    
    public void jar(final Tool tool) {
        if (!this.rootIsValidANTLRDir(tool)) {
            return;
        }
        final StringBuffer sb = new StringBuffer(2000);
        sb.append("jar cvf " + ANTLR.root + "/" + ANTLR.jarName);
        for (int i = 0; i < ANTLR.srcdir.length; ++i) {
            sb.append(" " + ANTLR.root + "/" + ANTLR.srcdir[i] + "/*.class");
        }
        tool.system(sb.toString());
    }
    
    protected boolean rootIsValidANTLRDir(final Tool tool) {
        if (ANTLR.root == null) {
            return false;
        }
        final File file = new File(ANTLR.root);
        if (!file.exists()) {
            tool.error("Property antlr.build.root==" + ANTLR.root + " does not exist");
            return false;
        }
        if (!file.isDirectory()) {
            tool.error("Property antlr.build.root==" + ANTLR.root + " is not a directory");
            return false;
        }
        final String[] list = file.list(new FilenameFilter() {
            public boolean accept(final File file, final String s) {
                return file.isDirectory() && s.equals("org.pitest.reloc.antlr.common");
            }
        });
        if (list == null || list.length == 0) {
            tool.error("Property antlr.build.root==" + ANTLR.root + " does not appear to be a valid ANTLR project root (no antlr subdir)");
            return false;
        }
        final String[] list2 = new File(ANTLR.root + "/org/pitest/reloc/antlr/common").list();
        if (list2 == null || list2.length == 0) {
            tool.error("Property antlr.build.root==" + ANTLR.root + " does not appear to be a valid ANTLR project root (no .java files in antlr subdir");
            return false;
        }
        return true;
    }
    
    static {
        ANTLR.compiler = "javac";
        ANTLR.jarName = "org.pitest.reloc.antlr.common.jar";
        ANTLR.root = ".";
        ANTLR.srcdir = new String[] { "org.pitest.reloc.antlr.common", "org/pitest/reloc/antlr/common/actions/cpp", "org/pitest/reloc/antlr/common/actions/java", "org/pitest/reloc/antlr/common/actions/csharp", "org/pitest/reloc/antlr/common/collections", "org/pitest/reloc/antlr/common/collections/impl", "org/pitest/reloc/antlr/common/debug", "org/pitest/reloc/antlr/common/ASdebug", "org/pitest/reloc/antlr/common/debug/misc", "org/pitest/reloc/antlr/common/preprocessor" };
    }
}
