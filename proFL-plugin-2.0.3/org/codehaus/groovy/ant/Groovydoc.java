// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.BuildException;
import java.io.IOException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.tools.groovydoc.OutputTool;
import org.codehaus.groovy.tools.groovydoc.FileOutputTool;
import org.codehaus.groovy.tools.groovydoc.ResourceManager;
import org.codehaus.groovy.tools.groovydoc.GroovyDocTool;
import org.codehaus.groovy.tools.groovydoc.gstringTemplates.GroovyDocTemplateInfo;
import org.codehaus.groovy.tools.groovydoc.ClasspathResourceManager;
import java.util.Properties;
import org.apache.tools.ant.DirectoryScanner;
import java.util.Iterator;
import java.util.Arrays;
import java.io.FilenameFilter;
import org.apache.tools.ant.types.PatternSet;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.ArrayList;
import org.codehaus.groovy.tools.groovydoc.LinkArgument;
import org.apache.tools.ant.types.DirSet;
import java.util.List;
import java.io.File;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.Task;

public class Groovydoc extends Task
{
    private final LoggingHelper log;
    private Path sourcePath;
    private File destDir;
    private List<String> packageNames;
    private List<String> excludePackageNames;
    private String windowTitle;
    private String docTitle;
    private String footer;
    private String header;
    private Boolean privateScope;
    private Boolean protectedScope;
    private Boolean packageScope;
    private Boolean publicScope;
    private Boolean author;
    private Boolean processScripts;
    private Boolean includeMainForScripts;
    private boolean useDefaultExcludes;
    private boolean includeNoSourcePackages;
    private List<DirSet> packageSets;
    private List<String> sourceFilesToDoc;
    private List<LinkArgument> links;
    private File overviewFile;
    private File styleSheetFile;
    private String extensions;
    
    public Groovydoc() {
        this.log = new LoggingHelper(this);
        this.windowTitle = "Groovy Documentation";
        this.docTitle = "Groovy Documentation";
        this.footer = "Groovy Documentation";
        this.header = "Groovy Documentation";
        this.links = new ArrayList<LinkArgument>();
        this.extensions = ".java:.groovy:.gv:.gvy:.gsh";
        this.packageNames = new ArrayList<String>();
        this.excludePackageNames = new ArrayList<String>();
        this.packageSets = new ArrayList<DirSet>();
        this.sourceFilesToDoc = new ArrayList<String>();
        this.privateScope = false;
        this.protectedScope = false;
        this.publicScope = false;
        this.packageScope = false;
        this.useDefaultExcludes = true;
        this.includeNoSourcePackages = false;
        this.author = true;
        this.processScripts = true;
        this.includeMainForScripts = true;
    }
    
    public void setSourcepath(final Path src) {
        if (this.sourcePath == null) {
            this.sourcePath = src;
        }
        else {
            this.sourcePath.append(src);
        }
    }
    
    public void setDestdir(final File dir) {
        this.destDir = dir;
    }
    
    public void setAuthor(final boolean author) {
        this.author = author;
    }
    
    public void setProcessScripts(final boolean processScripts) {
        this.processScripts = processScripts;
    }
    
    public void setIncludeMainForScripts(final boolean includeMainForScripts) {
        this.includeMainForScripts = includeMainForScripts;
    }
    
    public void setExtensions(final String extensions) {
        this.extensions = extensions;
    }
    
    public void setPackagenames(final String packages) {
        final StringTokenizer tok = new StringTokenizer(packages, ",");
        while (tok.hasMoreTokens()) {
            final String packageName = tok.nextToken();
            this.packageNames.add(packageName);
        }
    }
    
    public void setUse(final boolean b) {
    }
    
    public void setWindowtitle(final String title) {
        this.windowTitle = title;
    }
    
    public void setDoctitle(final String htmlTitle) {
        this.docTitle = htmlTitle;
    }
    
    public void setOverview(final File file) {
        this.overviewFile = file;
    }
    
    public void setAccess(final String access) {
        if ("public".equals(access)) {
            this.publicScope = true;
        }
        else if ("protected".equals(access)) {
            this.protectedScope = true;
        }
        else if ("package".equals(access)) {
            this.packageScope = true;
        }
        else if ("private".equals(access)) {
            this.privateScope = true;
        }
    }
    
    public void setPrivate(final boolean b) {
        this.privateScope = b;
    }
    
    public void setPublic(final boolean b) {
        this.publicScope = b;
    }
    
    public void setProtected(final boolean b) {
        this.protectedScope = b;
    }
    
    public void setPackage(final boolean b) {
        this.packageScope = b;
    }
    
    public void setFooter(final String footer) {
        this.footer = footer;
    }
    
    public void setHeader(final String header) {
        this.header = header;
    }
    
    public void setStyleSheetFile(final File styleSheetFile) {
        this.styleSheetFile = styleSheetFile;
    }
    
    private void parsePackages(final List<String> resultantPackages, final Path sourcePath) {
        final List<String> addedPackages = new ArrayList<String>();
        final List<DirSet> dirSets = new ArrayList<DirSet>(this.packageSets);
        if (this.sourcePath != null) {
            final PatternSet ps = new PatternSet();
            if (this.packageNames.size() > 0) {
                for (final String pn : this.packageNames) {
                    String pkg = pn.replace('.', '/');
                    if (pkg.endsWith("*")) {
                        pkg += "*";
                    }
                    ps.createInclude().setName(pkg);
                }
            }
            else {
                ps.createInclude().setName("**");
            }
            for (final String epn : this.excludePackageNames) {
                String pkg = epn.replace('.', '/');
                if (pkg.endsWith("*")) {
                    pkg += "*";
                }
                ps.createExclude().setName(pkg);
            }
            final String[] arr$;
            final String[] pathElements = arr$ = this.sourcePath.list();
            for (final String pathElement : arr$) {
                final File dir = new File(pathElement);
                if (dir.isDirectory()) {
                    final DirSet ds = new DirSet();
                    ds.setDefaultexcludes(this.useDefaultExcludes);
                    ds.setDir(dir);
                    ds.createPatternSet().addConfiguredPatternset(ps);
                    dirSets.add(ds);
                }
                else {
                    this.log.warn("Skipping " + pathElement + " since it is no directory.");
                }
            }
        }
        for (final DirSet ds2 : dirSets) {
            final File baseDir = ds2.getDir(this.getProject());
            this.log.debug("scanning " + baseDir + " for packages.");
            final DirectoryScanner dsc = ds2.getDirectoryScanner(this.getProject());
            final String[] dirs = dsc.getIncludedDirectories();
            boolean containsPackages = false;
            for (final String dir2 : dirs) {
                final File pd = new File(baseDir, dir2);
                final String[] files = pd.list(new FilenameFilter() {
                    public boolean accept(final File dir1, final String name) {
                        if (!Groovydoc.this.includeNoSourcePackages && name.equals("package.html")) {
                            return true;
                        }
                        final StringTokenizer tokenizer = new StringTokenizer(Groovydoc.this.extensions, ":");
                        while (tokenizer.hasMoreTokens()) {
                            final String ext = tokenizer.nextToken();
                            if (name.endsWith(ext)) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
                for (final String filename : Arrays.asList(files)) {
                    this.sourceFilesToDoc.add(dir2 + File.separator + filename);
                }
                if (files.length > 0) {
                    if ("".equals(dir2)) {
                        this.log.warn(baseDir + " contains source files in the default package," + " you must specify them as source files not packages.");
                    }
                    else {
                        containsPackages = true;
                        final String pn2 = dir2.replace(File.separatorChar, '.');
                        if (!addedPackages.contains(pn2)) {
                            addedPackages.add(pn2);
                            resultantPackages.add(pn2);
                        }
                    }
                }
            }
            if (containsPackages) {
                sourcePath.createPathElement().setLocation(baseDir);
            }
            else {
                this.log.verbose(baseDir + " doesn't contain any packages, dropping it.");
            }
        }
    }
    
    public void execute() throws BuildException {
        final List<String> packagesToDoc = new ArrayList<String>();
        final Path sourceDirs = new Path(this.getProject());
        final Properties properties = new Properties();
        properties.setProperty("windowTitle", this.windowTitle);
        properties.setProperty("docTitle", this.docTitle);
        properties.setProperty("footer", this.footer);
        properties.setProperty("header", this.header);
        this.checkScopeProperties(properties);
        properties.setProperty("publicScope", this.publicScope.toString());
        properties.setProperty("protectedScope", this.protectedScope.toString());
        properties.setProperty("packageScope", this.packageScope.toString());
        properties.setProperty("privateScope", this.privateScope.toString());
        properties.setProperty("author", this.author.toString());
        properties.setProperty("processScripts", this.processScripts.toString());
        properties.setProperty("includeMainForScripts", this.includeMainForScripts.toString());
        properties.setProperty("overviewFile", (this.overviewFile != null) ? this.overviewFile.getAbsolutePath() : "");
        if (this.sourcePath != null) {
            sourceDirs.addExisting(this.sourcePath);
        }
        this.parsePackages(packagesToDoc, sourceDirs);
        final GroovyDocTool htmlTool = new GroovyDocTool(new ClasspathResourceManager(), this.sourcePath.list(), GroovyDocTemplateInfo.DEFAULT_DOC_TEMPLATES, GroovyDocTemplateInfo.DEFAULT_PACKAGE_TEMPLATES, GroovyDocTemplateInfo.DEFAULT_CLASS_TEMPLATES, this.links, properties);
        try {
            htmlTool.add(this.sourceFilesToDoc);
            final FileOutputTool output = new FileOutputTool();
            htmlTool.renderToOutput(output, this.destDir.getCanonicalPath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.styleSheetFile != null) {
            try {
                final String css = DefaultGroovyMethods.getText(this.styleSheetFile);
                final File outfile = new File(this.destDir, "stylesheet.css");
                DefaultGroovyMethods.setText(outfile, css);
            }
            catch (IOException e2) {
                System.out.println("Warning: Unable to copy specified stylesheet '" + this.styleSheetFile.getAbsolutePath() + "'. Using default stylesheet instead. Due to: " + e2.getMessage());
            }
        }
    }
    
    private void checkScopeProperties(final Properties properties) {
        int scopeCount = 0;
        if (this.packageScope) {
            ++scopeCount;
        }
        if (this.privateScope) {
            ++scopeCount;
        }
        if (this.protectedScope) {
            ++scopeCount;
        }
        if (this.publicScope) {
            ++scopeCount;
        }
        if (scopeCount == 0) {
            this.protectedScope = true;
        }
        else if (scopeCount > 1) {
            throw new BuildException("More than one of public, private, package, or protected scopes specified.");
        }
    }
    
    public LinkArgument createLink() {
        final LinkArgument result = new LinkArgument();
        this.links.add(result);
        return result;
    }
}
