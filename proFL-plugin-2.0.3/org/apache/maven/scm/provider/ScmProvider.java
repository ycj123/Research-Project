// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider;

import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.blame.BlameScmRequest;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import java.util.Date;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.ScmRepository;
import java.util.List;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.log.ScmLogger;

public interface ScmProvider
{
    public static final String ROLE = ScmProvider.class.getName();
    
    String getScmType();
    
    void addListener(final ScmLogger p0);
    
    boolean requiresEditMode();
    
    ScmProviderRepository makeProviderScmRepository(final String p0, final char p1) throws ScmRepositoryException;
    
    ScmProviderRepository makeProviderScmRepository(final File p0) throws ScmRepositoryException, UnknownRepositoryStructure;
    
    List<String> validateScmUrl(final String p0, final char p1);
    
    String getScmSpecificFilename();
    
    boolean validateTagName(final String p0);
    
    String sanitizeTagName(final String p0);
    
    AddScmResult add(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    AddScmResult add(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    AddScmResult add(final ScmRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException;
    
    @Deprecated
    BranchScmResult branch(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    @Deprecated
    BranchScmResult branch(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    BranchScmResult branch(final ScmRepository p0, final ScmFileSet p1, final String p2, final ScmBranchParameters p3) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final String p5) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final ScmBranch p5) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final String p5, final String p6) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final ScmBranch p5, final String p6) throws ScmException;
    
    ChangeLogScmResult changeLog(final ChangeLogScmRequest p0) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3, final String p4) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3, final String p4) throws ScmException;
    
    @Deprecated
    CheckInScmResult checkIn(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    CheckInScmResult checkIn(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    CheckInScmResult checkIn(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    @Deprecated
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    @Deprecated
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final boolean p2) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final boolean p3) throws ScmException;
    
    @Deprecated
    DiffScmResult diff(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    DiffScmResult diff(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3) throws ScmException;
    
    @Deprecated
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    @Deprecated
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    RemoveScmResult remove(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    StatusScmResult status(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    @Deprecated
    TagScmResult tag(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    @Deprecated
    TagScmResult tag(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    TagScmResult tag(final ScmRepository p0, final ScmFileSet p1, final String p2, final ScmTagParameters p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    @Deprecated
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    @Deprecated
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final boolean p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final boolean p3) throws ScmException;
    
    @Deprecated
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    @Deprecated
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2, final Date p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final Date p3) throws ScmException;
    
    @Deprecated
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2, final Date p3, final String p4) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final Date p3, final String p4) throws ScmException;
    
    EditScmResult edit(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    UnEditScmResult unedit(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    @Deprecated
    ListScmResult list(final ScmRepository p0, final ScmFileSet p1, final boolean p2, final String p3) throws ScmException;
    
    ListScmResult list(final ScmRepository p0, final ScmFileSet p1, final boolean p2, final ScmVersion p3) throws ScmException;
    
    @Deprecated
    BlameScmResult blame(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    BlameScmResult blame(final BlameScmRequest p0) throws ScmException;
    
    MkdirScmResult mkdir(final ScmRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    InfoScmResult info(final ScmProviderRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException;
    
    RemoteInfoScmResult remoteInfo(final ScmProviderRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException;
}
