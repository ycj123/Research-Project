// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import org.apache.maven.scm.util.ThreadSafeDateFormat;
import java.util.Map;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.Collection;
import java.io.File;
import java.text.DateFormat;

public interface AccuRev
{
    public static final String DEFAULT_ACCUREV_EXECUTABLE = "accurev";
    public static final int DEFAULT_PORT = 5050;
    public static final String ACCUREV_TIME_FORMAT_STRING = "yyyy/MM/dd HH:mm:ss";
    public static final DateFormat ACCUREV_TIME_SPEC = new ThreadSafeDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final String DEFAULT_REMOVE_MESSAGE = "removed (maven-scm)";
    public static final String DEFAULT_ADD_MESSAGE = "initial version (maven-scm)";
    public static final String DEFAULT_PROMOTE_MESSAGE = "promote (maven-scm)";
    
    void reset();
    
    List<File> popExternal(final File p0, final String p1, final String p2, final Collection<File> p3) throws AccuRevException;
    
    List<File> pop(final File p0, final Collection<File> p1) throws AccuRevException;
    
    boolean mkws(final String p0, final String p1, final File p2) throws AccuRevException;
    
    List<File> update(final File p0, final String p1) throws AccuRevException;
    
    AccuRevInfo info(final File p0) throws AccuRevException;
    
    boolean rmws(final String p0) throws AccuRevException;
    
    boolean reactivate(final String p0) throws AccuRevException;
    
    String getCommandLines();
    
    String getErrorOutput();
    
    List<File> add(final File p0, final List<File> p1, final String p2) throws AccuRevException;
    
    List<File> defunct(final File p0, final List<File> p1, final String p2) throws AccuRevException;
    
    List<File> promoteAll(final File p0, final String p1) throws AccuRevException;
    
    List<File> promote(final File p0, final List<File> p1, final String p2) throws AccuRevException;
    
    boolean chws(final File p0, final String p1, final String p2) throws AccuRevException;
    
    boolean mksnap(final String p0, final String p1) throws AccuRevException;
    
    List<File> statTag(final String p0) throws AccuRevException;
    
    CategorisedElements statBackingStream(final File p0, final Collection<File> p1) throws AccuRevException;
    
    List<File> stat(final File p0, final Collection<File> p1, final AccuRevStat p2) throws AccuRevException;
    
    String stat(final File p0) throws AccuRevException;
    
    List<Transaction> history(final String p0, final String p1, final String p2, final int p3, final boolean p4, final boolean p5) throws AccuRevException;
    
    List<FileDifference> diff(final String p0, final String p1, final String p2) throws AccuRevException;
    
    List<BlameLine> annotate(final File p0, final File p1) throws AccuRevException;
    
    boolean login(final String p0, final String p1) throws AccuRevException;
    
    Map<String, WorkSpace> showWorkSpaces() throws AccuRevException;
    
    Map<String, WorkSpace> showRefTrees() throws AccuRevException;
    
    Stream showStream(final String p0) throws AccuRevException;
    
    String getExecutable();
    
    String getClientVersion() throws AccuRevException;
    
    boolean syncReplica() throws AccuRevException;
}
