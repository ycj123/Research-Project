// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import java.util.List;
import java.io.File;

public class DirectoryScanner
{
    public static final String[] DEFAULTEXCLUDES;
    private File basedir;
    private String[] includes;
    private String[] excludes;
    private MatchPatterns excludesPatterns;
    private MatchPatterns includesPatterns;
    private List<String> filesIncluded;
    private List<String> filesNotIncluded;
    private List<String> filesExcluded;
    private List<String> dirsIncluded;
    private List<String> dirsNotIncluded;
    private List<String> dirsExcluded;
    private boolean haveSlowResults;
    private boolean isCaseSensitive;
    private boolean followSymlinks;
    private ScanConductor scanConductor;
    private ScanConductor.ScanAction scanAction;
    
    public DirectoryScanner() {
        this.haveSlowResults = false;
        this.isCaseSensitive = true;
        this.followSymlinks = true;
        this.scanConductor = null;
        this.scanAction = null;
    }
    
    public void setBasedir(final String basedir) {
        this.setBasedir(new File(basedir.replace('/', File.separatorChar).replace('\\', File.separatorChar)));
    }
    
    public void setBasedir(@Nonnull final File basedir) {
        this.basedir = basedir;
    }
    
    public File getBasedir() {
        return this.basedir;
    }
    
    public void setCaseSensitive(final boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }
    
    public void setFollowSymlinks(final boolean followSymlinks) {
        this.followSymlinks = followSymlinks;
    }
    
    public void setIncludes(final String... includes) {
        if (includes == null) {
            this.includes = null;
        }
        else {
            this.includes = new String[includes.length];
            for (int i = 0; i < includes.length; ++i) {
                String pattern = includes[i].trim().replace('/', File.separatorChar).replace('\\', File.separatorChar);
                if (pattern.endsWith(File.separator)) {
                    pattern += "**";
                }
                this.includes[i] = pattern;
            }
        }
    }
    
    public void setExcludes(final String... excludes) {
        if (excludes == null) {
            this.excludes = null;
        }
        else {
            this.excludes = new String[excludes.length];
            for (int i = 0; i < excludes.length; ++i) {
                String pattern = excludes[i].trim().replace('/', File.separatorChar).replace('\\', File.separatorChar);
                if (pattern.endsWith(File.separator)) {
                    pattern += "**";
                }
                this.excludes[i] = pattern;
            }
        }
    }
    
    public void setScanConductor(final ScanConductor scanConductor) {
        this.scanConductor = scanConductor;
    }
    
    public void scan() throws IllegalStateException {
        if (this.basedir == null) {
            throw new IllegalStateException("No basedir set");
        }
        if (!this.basedir.exists()) {
            throw new IllegalStateException("basedir " + this.basedir + " does not exist");
        }
        if (!this.basedir.isDirectory()) {
            throw new IllegalStateException("basedir " + this.basedir + " is not a directory");
        }
        this.setupDefaultFilters();
        this.setupMatchPatterns();
        this.filesIncluded = new ArrayList<String>();
        this.filesNotIncluded = new ArrayList<String>();
        this.filesExcluded = new ArrayList<String>();
        this.dirsIncluded = new ArrayList<String>();
        this.dirsNotIncluded = new ArrayList<String>();
        this.dirsExcluded = new ArrayList<String>();
        this.scanAction = ScanConductor.ScanAction.CONTINUE;
        if (this.isIncluded("")) {
            if (!this.isExcluded("")) {
                if (this.scanConductor != null) {
                    this.scanAction = this.scanConductor.visitDirectory("", this.basedir);
                    if (ScanConductor.ScanAction.ABORT.equals(this.scanAction) || ScanConductor.ScanAction.ABORT_DIRECTORY.equals(this.scanAction) || ScanConductor.ScanAction.NO_RECURSE.equals(this.scanAction)) {
                        return;
                    }
                }
                this.dirsIncluded.add("");
            }
            else {
                this.dirsExcluded.add("");
            }
        }
        else {
            this.dirsNotIncluded.add("");
        }
        this.scandir(this.basedir, "", true);
    }
    
    public DirectoryScanResult diffIncludedFiles(final String... oldFiles) {
        if (this.filesIncluded == null) {
            this.scan();
        }
        return diffFiles(oldFiles, this.filesIncluded.toArray(new String[this.filesIncluded.size()]));
    }
    
    public static DirectoryScanResult diffFiles(@Nullable final String[] oldFiles, @Nullable final String[] newFiles) {
        final Set<String> oldFileSet = arrayAsHashSet(oldFiles);
        final Set<String> newFileSet = arrayAsHashSet(newFiles);
        final List<String> added = new ArrayList<String>();
        final List<String> removed = new ArrayList<String>();
        for (final String oldFile : oldFileSet) {
            if (!newFileSet.contains(oldFile)) {
                removed.add(oldFile);
            }
        }
        for (final String newFile : newFileSet) {
            if (!oldFileSet.contains(newFile)) {
                added.add(newFile);
            }
        }
        final String[] filesAdded = added.toArray(new String[added.size()]);
        final String[] filesRemoved = removed.toArray(new String[removed.size()]);
        return new DirectoryScanResult(filesAdded, filesRemoved);
    }
    
    private static <T> Set<T> arrayAsHashSet(@Nullable final T[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptySet();
        }
        final Set<T> set = new HashSet<T>(array.length);
        Collections.addAll(set, array);
        return set;
    }
    
    void slowScan() {
        if (this.haveSlowResults) {
            return;
        }
        final String[] excl = this.dirsExcluded.toArray(new String[this.dirsExcluded.size()]);
        final String[] notIncl = this.dirsNotIncluded.toArray(new String[this.dirsNotIncluded.size()]);
        for (final String anExcl : excl) {
            if (!this.couldHoldIncluded(anExcl)) {
                this.scandir(new File(this.basedir, anExcl), anExcl + File.separator, false);
            }
        }
        for (final String aNotIncl : notIncl) {
            if (!this.couldHoldIncluded(aNotIncl)) {
                this.scandir(new File(this.basedir, aNotIncl), aNotIncl + File.separator, false);
            }
        }
        this.haveSlowResults = true;
    }
    
    void scandir(@Nonnull final File dir, @Nonnull final String vpath, final boolean fast) {
        String[] newfiles = dir.list();
        if (newfiles == null) {
            newfiles = new String[0];
        }
        if (!this.followSymlinks) {
            final List<String> noLinks = new ArrayList<String>();
            for (final String newfile : newfiles) {
                try {
                    if (this.isSymbolicLink(dir, newfile)) {
                        final String name = vpath + newfile;
                        final File file = new File(dir, newfile);
                        if (file.isDirectory()) {
                            this.dirsExcluded.add(name);
                        }
                        else {
                            this.filesExcluded.add(name);
                        }
                    }
                    else {
                        noLinks.add(newfile);
                    }
                }
                catch (IOException ioe) {
                    final String msg = "IOException caught while checking for links, couldn't get cannonical path!";
                    System.err.println("IOException caught while checking for links, couldn't get cannonical path!");
                    noLinks.add(newfile);
                }
            }
            newfiles = noLinks.toArray(new String[noLinks.size()]);
        }
        for (final String newfile2 : newfiles) {
            final String name2 = vpath + newfile2;
            final File file2 = new File(dir, newfile2);
            if (file2.isDirectory()) {
                if (this.isIncluded(name2)) {
                    if (!this.isExcluded(name2)) {
                        if (this.scanConductor != null) {
                            this.scanAction = this.scanConductor.visitDirectory(name2, file2);
                            if (ScanConductor.ScanAction.ABORT.equals(this.scanAction) || ScanConductor.ScanAction.ABORT_DIRECTORY.equals(this.scanAction)) {
                                return;
                            }
                        }
                        if (!ScanConductor.ScanAction.NO_RECURSE.equals(this.scanAction)) {
                            this.dirsIncluded.add(name2);
                            if (fast) {
                                this.scandir(file2, name2 + File.separator, fast);
                                if (ScanConductor.ScanAction.ABORT.equals(this.scanAction)) {
                                    return;
                                }
                            }
                        }
                        this.scanAction = null;
                    }
                    else {
                        this.dirsExcluded.add(name2);
                        if (fast && this.couldHoldIncluded(name2)) {
                            this.scandir(file2, name2 + File.separator, fast);
                            if (ScanConductor.ScanAction.ABORT.equals(this.scanAction)) {
                                return;
                            }
                            this.scanAction = null;
                        }
                    }
                }
                else if (fast && this.couldHoldIncluded(name2)) {
                    if (this.scanConductor != null) {
                        this.scanAction = this.scanConductor.visitDirectory(name2, file2);
                        if (ScanConductor.ScanAction.ABORT.equals(this.scanAction) || ScanConductor.ScanAction.ABORT_DIRECTORY.equals(this.scanAction)) {
                            return;
                        }
                    }
                    if (!ScanConductor.ScanAction.NO_RECURSE.equals(this.scanAction)) {
                        this.dirsNotIncluded.add(name2);
                        this.scandir(file2, name2 + File.separator, fast);
                        if (ScanConductor.ScanAction.ABORT.equals(this.scanAction)) {
                            return;
                        }
                    }
                    this.scanAction = null;
                }
                if (!fast) {
                    this.scandir(file2, name2 + File.separator, fast);
                    if (ScanConductor.ScanAction.ABORT.equals(this.scanAction)) {
                        return;
                    }
                    this.scanAction = null;
                }
            }
            else if (file2.isFile()) {
                if (this.isIncluded(name2)) {
                    if (!this.isExcluded(name2)) {
                        if (this.scanConductor != null) {
                            this.scanAction = this.scanConductor.visitFile(name2, file2);
                        }
                        if (ScanConductor.ScanAction.ABORT.equals(this.scanAction) || ScanConductor.ScanAction.ABORT_DIRECTORY.equals(this.scanAction)) {
                            return;
                        }
                        this.filesIncluded.add(name2);
                    }
                    else {
                        this.filesExcluded.add(name2);
                    }
                }
                else {
                    this.filesNotIncluded.add(name2);
                }
            }
        }
    }
    
    boolean isIncluded(final String name) {
        return this.includesPatterns.matches(name, this.isCaseSensitive);
    }
    
    boolean couldHoldIncluded(@Nonnull final String name) {
        return this.includesPatterns.matchesPatternStart(name, this.isCaseSensitive);
    }
    
    boolean isExcluded(@Nonnull final String name) {
        return this.excludesPatterns.matches(name, this.isCaseSensitive);
    }
    
    public String[] getIncludedFiles() {
        if (this.filesIncluded == null) {
            return new String[0];
        }
        return this.filesIncluded.toArray(new String[this.filesIncluded.size()]);
    }
    
    public String[] getNotIncludedFiles() {
        this.slowScan();
        return this.filesNotIncluded.toArray(new String[this.filesNotIncluded.size()]);
    }
    
    public String[] getExcludedFiles() {
        this.slowScan();
        return this.filesExcluded.toArray(new String[this.filesExcluded.size()]);
    }
    
    public String[] getIncludedDirectories() {
        return this.dirsIncluded.toArray(new String[this.dirsIncluded.size()]);
    }
    
    public String[] getNotIncludedDirectories() {
        this.slowScan();
        return this.dirsNotIncluded.toArray(new String[this.dirsNotIncluded.size()]);
    }
    
    public String[] getExcludedDirectories() {
        this.slowScan();
        return this.dirsExcluded.toArray(new String[this.dirsExcluded.size()]);
    }
    
    public void addDefaultExcludes() {
        final int excludesLength = (this.excludes == null) ? 0 : this.excludes.length;
        final String[] newExcludes = new String[excludesLength + DirectoryScanner.DEFAULTEXCLUDES.length];
        if (excludesLength > 0) {
            System.arraycopy(this.excludes, 0, newExcludes, 0, excludesLength);
        }
        for (int i = 0; i < DirectoryScanner.DEFAULTEXCLUDES.length; ++i) {
            newExcludes[i + excludesLength] = DirectoryScanner.DEFAULTEXCLUDES[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
        }
        this.excludes = newExcludes;
    }
    
    boolean isSymbolicLink(final File parent, final String name) throws IOException {
        if (Java7Support.isJava7()) {
            return Java7Support.isSymLink(parent);
        }
        final File resolvedParent = new File(parent.getCanonicalPath());
        final File toTest = new File(resolvedParent, name);
        return !toTest.getAbsolutePath().equals(toTest.getCanonicalPath());
    }
    
    private void setupDefaultFilters() {
        if (this.includes == null) {
            (this.includes = new String[1])[0] = "**";
        }
        if (this.excludes == null) {
            this.excludes = new String[0];
        }
    }
    
    private void setupMatchPatterns() {
        this.includesPatterns = MatchPatterns.from(this.includes);
        this.excludesPatterns = MatchPatterns.from(this.excludes);
    }
    
    static {
        DEFAULTEXCLUDES = new String[] { "**/*~", "**/#*#", "**/.#*", "**/%*%", "**/._*", "**/CVS", "**/CVS/**", "**/.cvsignore", "**/RCS", "**/RCS/**", "**/SCCS", "**/SCCS/**", "**/vssver.scc", "**/.svn", "**/.svn/**", "**/.arch-ids", "**/.arch-ids/**", "**/.bzr", "**/.bzr/**", "**/.MySCMServerInfo", "**/.DS_Store", "**/.metadata", "**/.metadata/**", "**/.hg", "**/.hg/**", "**/.git", "**/.git/**", "**/BitKeeper", "**/BitKeeper/**", "**/ChangeSet", "**/ChangeSet/**", "**/_darcs", "**/_darcs/**", "**/.darcsrepo", "**/.darcsrepo/**", "**/-darcs-backup*", "**/.darcs-temp-mail" };
    }
}
