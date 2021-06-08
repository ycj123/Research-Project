// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import com.mks.api.response.CommandCancelledException;
import java.util.List;
import com.mks.api.SelectionList;
import java.util.Iterator;
import com.mks.api.commands.ide.WorkingFileFactory;
import com.mks.api.commands.ide.WorkingFile;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;
import java.io.File;

abstract class WorkingFileCommandBase extends CommandBase
{
    private File preferredSandboxRoot;
    
    protected WorkingFileCommandBase(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    public final void setPreferredRoot(final File rootSandbox) {
        this.preferredSandboxRoot = rootSandbox;
    }
    
    protected final File getPreferredRoot() {
        return this.preferredSandboxRoot;
    }
    
    protected abstract Response execute(final WorkingFileList p0) throws APIException;
    
    final Response execute(final WorkingFileList workingFiles, final boolean isInteractive) throws APIException {
        if (workingFiles == null) {
            throw new APIException(new IllegalArgumentException("Working files cannot be null"));
        }
        try {
            this.interactive = isInteractive;
            return this.execute(this.cleanInvalids(workingFiles));
        }
        finally {
            workingFiles.invalidate();
        }
    }
    
    protected WorkingFileList cleanInvalids(final WorkingFileList listToClean) {
        final WorkingFileList cleanList = new WorkingFileList();
        for (final WorkingFile wf : listToClean) {
            if (wf.isInvalid()) {
                cleanList.add(WorkingFileFactory.refreshWorkingFile(this.getCmdRunnerCreator(), wf.getMemberName().getAbsolutePath()));
            }
            else {
                cleanList.add(wf);
            }
        }
        return cleanList;
    }
    
    protected final Response execute(final SelectionList selection) throws APIException {
        final List fileNames = this.extractSelectionList(selection);
        return this.execute(WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), fileNames), this.interactive);
    }
    
    protected final Response[] runApiCommand(final IWorkingFileCompatibleCommand cmd, final WorkingFileList workingFiles, final boolean isInteractive, final boolean throwExceptions) throws APIException {
        if (workingFiles.isEmpty()) {
            return new Response[0];
        }
        try {
            return this._runApiCommand(cmd, workingFiles, isInteractive, throwExceptions);
        }
        finally {
            workingFiles.invalidate();
        }
    }
    
    private Response[] _runApiCommand(final IWorkingFileCompatibleCommand cmd, final WorkingFileList workingFiles, final boolean isInteractive, final boolean throwExceptions) throws APIException {
        int buckets = 1;
        Iterator workingRoots = null;
        final Map workingFileBuckets = workingFiles.getWorkingFileBuckets(this.getCmdRunnerCreator(), this.preferredSandboxRoot);
        if (workingFileBuckets != null && !workingFileBuckets.isEmpty()) {
            final Set keys = workingFileBuckets.keySet();
            buckets = keys.size();
            workingRoots = keys.iterator();
        }
        final Response[] responses = new Response[buckets];
        int r = 0;
        do {
            Response response = null;
            String[] memberNames = null;
            WorkingFileList sbxWorkingFiles = null;
            final File workingRoot = (workingRoots == null) ? null : workingRoots.next();
            File sandboxRootDir = null;
            if (workingRoot == null) {
                sbxWorkingFiles = workingFiles;
                memberNames = this.extractMemberNameArray(workingFiles);
                sandboxRootDir = new File(SICommands.getMemberListCWD(memberNames));
            }
            else {
                sbxWorkingFiles = workingFileBuckets.get(workingRoot);
                memberNames = this.extractMemberNameArray(sbxWorkingFiles);
                if (workingRoot.isDirectory()) {
                    sandboxRootDir = workingRoot;
                    cmd.setSandbox(null);
                }
                else {
                    sandboxRootDir = workingRoot.getParentFile();
                    cmd.setSandbox(workingRoot.getAbsolutePath());
                }
            }
            cmd.setCwd(sandboxRootDir.getAbsolutePath());
            if (cmd instanceof IHasChangePackage) {
                final IHasChangePackage cpCmd = (IHasChangePackage)cmd;
                if (!cpCmd.isCloseCPOverridden()) {
                    if (workingRoots != null && workingRoots.hasNext()) {
                        cpCmd.setCloseCP(false);
                    }
                    else {
                        cpCmd.resetCloseCP();
                    }
                }
            }
            try {
                response = cmd.execute(memberNames, isInteractive);
                if (response != null && response.getAPIException() != null && response.getAPIException() instanceof CommandCancelledException) {
                    throw response.getAPIException();
                }
            }
            catch (APIException ex) {
                response = ex.getResponse();
                if (throwExceptions) {
                    throw ex;
                }
            }
            responses[r++] = response;
        } while (workingRoots != null && workingRoots.hasNext());
        return responses;
    }
    
    protected final Response[] runApiCommand(final IWorkingFileCompatibleCommand cmd, final WorkingFileList workingFiles, final boolean isInteractive) throws APIException {
        return this.runApiCommand(cmd, workingFiles, isInteractive, true);
    }
    
    private List extractSelectionList(final SelectionList selection) {
        final List list = new ArrayList(selection.size());
        final Iterator i = selection.getSelections();
        while (i.hasNext()) {
            list.add(i.next());
        }
        return list;
    }
    
    private String[] extractMemberNameArray(final WorkingFileList workingFiles) {
        final String[] memberNames = new String[workingFiles.size()];
        final Iterator i = workingFiles.iterator();
        int j = 0;
        while (i.hasNext()) {
            final WorkingFile wf = i.next();
            if (wf.getMemberName() != null) {
                memberNames[j++] = wf.getMemberName().getAbsolutePath();
            }
            else {
                memberNames[j++] = wf.getFile().getAbsolutePath();
            }
        }
        return memberNames;
    }
}
