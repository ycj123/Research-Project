// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.convert;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.velocity.util.StringUtils;
import java.io.IOException;
import java.io.FileWriter;
import org.apache.tools.ant.DirectoryScanner;
import java.io.File;

public class WebMacro
{
    protected static final String VM_EXT = ".vm";
    protected static final String WM_EXT = ".wm";
    protected static String[] perLineREs;
    
    public void convert(final String target) {
        final File file = new File(target);
        if (!file.exists()) {
            throw new RuntimeException("The specified template or directory does not exist");
        }
        if (file.isDirectory()) {
            final String basedir = file.getAbsolutePath();
            final String newBasedir = basedir + ".vm";
            final DirectoryScanner ds = new DirectoryScanner();
            ds.setBasedir(basedir);
            ds.addDefaultExcludes();
            ds.scan();
            final String[] files = ds.getIncludedFiles();
            for (int i = 0; i < files.length; ++i) {
                this.writeTemplate(files[i], basedir, newBasedir);
            }
        }
        else {
            this.writeTemplate(file.getAbsolutePath(), "", "");
        }
    }
    
    private boolean writeTemplate(final String file, final String basedir, final String newBasedir) {
        if (file.indexOf(".wm") < 0) {
            return false;
        }
        System.out.println("Converting " + file + "...");
        String template = file;
        String newTemplate = this.convertName(file);
        if (basedir.length() > 0) {
            final String templateDir = newBasedir + this.extractPath(file);
            final File outputDirectory = new File(templateDir);
            template = basedir + File.separator + file;
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }
            newTemplate = newBasedir + File.separator + this.convertName(file);
        }
        final String convertedTemplate = this.convertTemplate(template);
        FileWriter fw = null;
        try {
            fw = new FileWriter(newTemplate);
            fw.write(convertedTemplate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                }
                catch (IOException ex) {}
            }
        }
        return true;
    }
    
    private final String extractPath(final String file) {
        final int lastSepPos = file.lastIndexOf(File.separator);
        return (lastSepPos == -1) ? "" : (File.separator + file.substring(0, lastSepPos));
    }
    
    private String convertName(final String name) {
        return (name.indexOf(".wm") < 0) ? name : (name.substring(0, name.indexOf(".wm")) + ".vm");
    }
    
    private static final void usage() {
        System.err.println("Usage: convert-wm <template.wm | directory>");
    }
    
    public String convertTemplate(final String template) {
        String contents = StringUtils.fileContentsToString(template);
        if (!contents.endsWith("\n")) {
            contents += "\n";
        }
        final Perl5Util perl = new Perl5Util();
        for (int i = 0; i < WebMacro.perLineREs.length; i += 2) {
            contents = perl.substitute(this.makeSubstRE(i), contents);
        }
        if (perl.match("m/javascript/i", contents)) {
            contents = perl.substitute("s/\n}/\n#end/g", contents);
        }
        else {
            contents = perl.substitute("s/(\n\\s*)}/$1#end/g", contents);
            contents = perl.substitute("s/#end\\s*\n\\s*#else/#else/g", contents);
        }
        return contents;
    }
    
    private final String makeSubstRE(final int i) {
        return "s/" + WebMacro.perLineREs[i] + '/' + WebMacro.perLineREs[i + 1] + "/g";
    }
    
    public static void main(final String[] args) {
        if (args.length > 0) {
            for (int x = 0; x < args.length; ++x) {
                final WebMacro converter = new WebMacro();
                converter.convert(args[x]);
            }
        }
        else {
            usage();
        }
    }
    
    static {
        WebMacro.perLineREs = new String[] { "#if\\s*[(]\\s*(.*\\S)\\s*[)]\\s*(#begin|{)[ \\t]?", "#if( $1 )", "[ \\t]?(#end|})[ \\t]*\n(\\s*)#else\\s*(#begin|{)[ \\t]?(\\w)", "$2#else#**#$4", "[ \\t]?(#end|})[ \\t]*\n(\\s*)#else\\s*(#begin|{)[ \\t]?", "$2#else", "(#end|})(\\s*#else)\\s*(#begin|{)[ \\t]?", "$1\n$2", "#foreach\\s+(\\$\\w+)\\s+in\\s+(\\$[^\\s#]+)\\s*(#begin|{)[ \\t]?", "#foreach( $1 in $2 )", "#set\\s+(\\$[^\\s=]+)\\s*=\\s*([\\S \\t]+)", "#set( $1 = $2 )", "(##[# \\t\\w]*)\\)", ")$1", "#parse\\s+([^\\s#]+)[ \\t]?", "#parse( $1 )", "#include\\s+([^\\s#]+)[ \\t]?", "#include( $1 )", "\\$\\(([^\\)]+)\\)", "${$1}", "\\${([^}\\(]+)\\(([^}]+)}\\)", "${$1($2)}", "\\$_", "$l_", "\\${(_[^}]+)}", "${l$1}", "(#set\\s*\\([^;]+);(\\s*\\))", "$1$2", "(^|[^\\\\])\\$(\\w[^=\n;'\"]*);", "$1${$2}", "\\.wm", ".vm" };
    }
}
