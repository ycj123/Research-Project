// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager;

import org.apache.maven.scm.command.blame.BlameScmRequest;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProvider;
import java.util.List;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.repository.ScmRepository;

public interface ScmManager
{
    public static final String ROLE = ScmManager.class.getName();
    
    ScmRepository makeScmRepository(final String p0) throws ScmRepositoryException, NoSuchScmProviderException;
    
    ScmRepository makeProviderScmRepository(final String p0, final File p1) throws ScmRepositoryException, UnknownRepositoryStructure, NoSuchScmProviderException;
    
    List<String> validateScmRepository(final String p0);
    
    ScmProvider getProviderByUrl(final String p0) throws ScmRepositoryException, NoSuchScmProviderException;
    
    ScmProvider getProviderByType(final String p0) throws NoSuchScmProviderException;
    
    ScmProvider getProviderByRepository(final ScmRepository p0) throws NoSuchScmProviderException;
    
    void setScmProvider(final String p0, final ScmProvider p1);
    
    void setScmProviderImplementation(final String p0, final String p1);
    
    AddScmResult add(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    AddScmResult add(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    BranchScmResult branch(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    BranchScmResult branch(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final ScmBranch p5) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final int p4, final ScmBranch p5, final String p6) throws ScmException;
    
    ChangeLogScmResult changeLog(final ChangeLogScmRequest p0) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3) throws ScmException;
    
    @Deprecated
    ChangeLogScmResult changeLog(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3, final String p4) throws ScmException;
    
    CheckInScmResult checkIn(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    CheckInScmResult checkIn(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final boolean p2) throws ScmException;
    
    CheckOutScmResult checkOut(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final boolean p3) throws ScmException;
    
    DiffScmResult diff(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3) throws ScmException;
    
    EditScmResult edit(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    ExportScmResult export(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    ListScmResult list(final ScmRepository p0, final ScmFileSet p1, final boolean p2, final ScmVersion p3) throws ScmException;
    
    MkdirScmResult mkdir(final ScmRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    RemoveScmResult remove(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    StatusScmResult status(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    TagScmResult tag(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    TagScmResult tag(final ScmRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    UnEditScmResult unedit(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final boolean p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final boolean p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final Date p2) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final Date p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final Date p2, final String p3) throws ScmException;
    
    UpdateScmResult update(final ScmRepository p0, final ScmFileSet p1, final ScmVersion p2, final Date p3, final String p4) throws ScmException;
    
    BlameScmResult blame(final ScmRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    BlameScmResult blame(final BlameScmRequest p0) throws ScmException;
}
