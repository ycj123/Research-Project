// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.util.List;
import com.mks.api.commands.ide.WorkingFileFactory;
import java.util.ArrayList;
import com.mks.api.response.InvalidCommandOptionException;
import java.io.File;
import java.util.Iterator;
import com.mks.api.commands.ide.SandboxInfo;
import com.mks.api.commands.ide.WorkingDirectoryFactory;
import com.mks.api.commands.ide.WorkingDirectory;
import java.util.Map;
import com.mks.api.commands.ide.WorkingFile;
import java.util.HashMap;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class WFTrackMovedFile extends WFTrackCommandBase
{
    private String[] fromNames;
    private String[] toNames;
    
    WFTrackMovedFile(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final Map fileNameMapping = this.convertToWorkingFileMap(this.fromNames, this.toNames);
        final HashMap targetMoveCpDirBuckets = new HashMap();
        final HashMap targetSandboxMap = new HashMap();
        final HashMap dirMap = new HashMap();
        final WorkingFileList toBeReverted = new WorkingFileList();
        final HashMap toBeAdded = new HashMap();
        final HashMap toBeDropped = new HashMap();
        final WorkingFileList toBeRenamed = new WorkingFileList();
        final HashMap toBeLocked = new HashMap();
        for (final WorkingFile wf : workingFiles) {
            final WorkingFile target = fileNameMapping.get(wf.getFile());
            if (wf.getAPIException() != null) {
                continue;
            }
            if (!wf.isControlled() && wf.isInSandboxDir()) {
                continue;
            }
            if (wf.isDropped()) {
                continue;
            }
            if (wf.isAdded() || wf.isMoved()) {
                toBeReverted.add(wf);
                if (wf.isLockedByMe()) {
                    this.addToWorkingFileList(toBeLocked, wf);
                }
            }
            if (wf.isAdded()) {
                if (!target.isInSandboxDir()) {
                    continue;
                }
                this.addToWorkingFileList(toBeAdded, target);
            }
            else {
                if (!wf.isInSandboxDir() && target.isInSandboxDir()) {
                    this.addToWorkingFileList(toBeAdded, target);
                }
                if (wf.isControlled() && !target.isInSandboxDir()) {
                    if (wf.isAdded()) {
                        continue;
                    }
                    this.addToWorkingFileList(toBeDropped, wf);
                }
                else {
                    File targetFile = target.getFile();
                    if (target.isMoved()) {
                        targetFile = target.getMemberName();
                    }
                    if (!wf.getMemberName().getName().equals(targetFile.getName())) {
                        toBeRenamed.add(wf);
                    }
                    final File targetDir = targetFile.getParentFile();
                    if (wf.getMemberName().getParentFile().equals(targetDir)) {
                        continue;
                    }
                    this.addToTargetBucket(targetMoveCpDirBuckets, wf, targetDir);
                    if (targetSandboxMap.containsKey(targetDir)) {
                        continue;
                    }
                    String sandboxPath = target.getSandbox();
                    final File preferredRoot = this.getPreferredRoot();
                    if (target.getWorkingRoot() == null && preferredRoot != null) {
                        WorkingDirectory wd = dirMap.get(targetDir);
                        if (wd == null) {
                            wd = WorkingDirectoryFactory.getWorkingDirectory(this.getCmdRunnerCreator(), targetDir);
                            dirMap.put(targetDir, wd);
                        }
                        SandboxInfo sandbox = null;
                        final Iterator sandboxes = wd.getSandboxes().iterator();
                        while (sandboxes.hasNext()) {
                            sandbox = sandboxes.next();
                            if (sandbox.isRelatedTo(this.getCmdRunnerCreator(), preferredRoot)) {
                                break;
                            }
                        }
                        if (sandbox != null) {
                            sandboxPath = sandbox.getSandboxName();
                        }
                    }
                    targetSandboxMap.put(targetDir, sandboxPath);
                }
            }
        }
        Response response = null;
        response = this.revertDeferred(toBeReverted, true);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        response = this.addFiles(toBeAdded);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        response = this.dropFiles(toBeDropped);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        response = this.lockFiles(toBeLocked);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        response = this.renameFiles(fileNameMapping, toBeRenamed);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        response = this.moveFiles(targetMoveCpDirBuckets, targetSandboxMap);
        if (response != null && response.getAPIException() != null) {
            return response;
        }
        return null;
    }
    
    private Response dropFiles(final HashMap toBeDropped) throws APIException {
        if (toBeDropped.size() == 0) {
            return null;
        }
        for (final String cpid : toBeDropped.keySet()) {
            final WFTrackDeletedFile wfDeleted = new WFTrackDeletedFile(this.getCmdRunnerCreator());
            wfDeleted.setCpid(cpid);
            final Response response = wfDeleted.execute(toBeDropped.get(cpid), this.interactive);
            if (response != null && response.getAPIException() != null) {
                return response;
            }
        }
        return null;
    }
    
    private Response addFiles(final HashMap toBeAdded) throws APIException {
        if (toBeAdded.size() == 0) {
            return null;
        }
        for (final String cpid : toBeAdded.keySet()) {
            final WFTrackNewFile wfNew = new WFTrackNewFile(this.getCmdRunnerCreator());
            wfNew.setCpid(cpid);
            wfNew.setPreferredRoot(this.getPreferredRoot());
            wfNew.setAllowCreateSubs(this.allowCreateSubs);
            final Response response = wfNew.execute(toBeAdded.get(cpid), this.interactive);
            if (response != null && response.getAPIException() != null) {
                return response;
            }
        }
        return null;
    }
    
    private Response lockFiles(final HashMap toBeLocked) throws APIException {
        if (toBeLocked.isEmpty()) {
            return null;
        }
        for (final String cpid : toBeLocked.keySet()) {
            final SILockCommand siLock = new SILockCommand(this.getCmdRunnerCreator());
            siLock.setCpid(cpid);
            siLock.setAllowPrompting(false);
            final GenericWFCommandRunner wfLock = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siLock);
            final Response response = wfLock.execute(toBeLocked.get(cpid), this.interactive);
            if (response != null && response.getAPIException() != null) {
                return response;
            }
        }
        return null;
    }
    
    private Response renameFiles(final Map fileNameMapping, final WorkingFileList toBeRenamed) throws APIException {
        Response[] response = null;
        for (final WorkingFile wf : toBeRenamed) {
            final WorkingFileList selection = new WorkingFileList();
            selection.add(wf);
            final WorkingFile target = fileNameMapping.get(wf.getFile());
            String newName = target.getFile().getName();
            if (target.isMoved()) {
                newName = target.getMemberName().getName();
            }
            String workingCpId;
            if (wf.getWorkingCpid() != null && wf.getWorkingCpid().length() > 0) {
                workingCpId = wf.getWorkingCpid();
            }
            else {
                workingCpId = this.cpid;
            }
            final SIRenameCommand siRename = new SIRenameCommand(this.getCmdRunnerCreator());
            siRename.setDeferred(true);
            siRename.setCpid(workingCpId);
            siRename.setCloseCP(false);
            siRename.setNewName(newName);
            siRename.setRenameFile(true);
            response = this.runApiCommand(siRename, selection, this.interactive);
            for (int j = 0; j < response.length; ++j) {
                if (response[j] != null && response[j].getAPIException() != null) {
                    return response[j];
                }
            }
            if (wf != null) {
                File file;
                if (wf.getMemberName() != null) {
                    file = wf.getMemberName();
                }
                else {
                    file = wf.getFile();
                }
                if (file == null || !file.exists()) {
                    continue;
                }
                try {
                    file.delete();
                }
                catch (SecurityException ex) {}
            }
        }
        return null;
    }
    
    private Response moveFiles(final HashMap targetMoveCpDirBuckets, final HashMap targetSandboxMap) throws APIException {
        Response[] response = null;
        for (final String cpid : targetMoveCpDirBuckets.keySet()) {
            final Map targetMoveDirBuckets = targetMoveCpDirBuckets.get(cpid);
            for (final File targetDir : targetMoveDirBuckets.keySet()) {
                final WorkingFileList filesInDir = targetMoveDirBuckets.get(targetDir);
                final SIMoveCommand siMove = new SIMoveCommand(this.getCmdRunnerCreator());
                siMove.setCpid(cpid);
                siMove.setDeferred(true);
                siMove.setCloseCP(false);
                siMove.setAllowCreateSubs(this.allowCreateSubs);
                siMove.setMoveWorking(true);
                siMove.setTargetDir(targetDir.getAbsolutePath());
                final String targetSandbox = targetSandboxMap.get(targetDir);
                siMove.setTargetSandbox(targetSandbox);
                response = this.runApiCommand(siMove, filesInDir, this.interactive);
                for (int j = 0; j < response.length; ++j) {
                    if (response[j] != null && response[j].getAPIException() != null) {
                        return response[j];
                    }
                }
                for (final WorkingFile wf : filesInDir) {
                    if (wf != null && wf.getFile() != null) {
                        if (wf.isMoved()) {
                            for (int l = 0; l < this.fromNames.length; ++l) {
                                if (this.fromNames[l] != null && this.toNames[l] != null && this.fromNames[l].equalsIgnoreCase(wf.getFile().getAbsolutePath())) {
                                    final String toName = new File(this.toNames[l]).getName();
                                    final File dir = new File(wf.getMemberName().getParent());
                                    final File toDelete = new File(dir, toName);
                                    try {
                                        toDelete.delete();
                                    }
                                    catch (SecurityException ex) {}
                                }
                            }
                        }
                        try {
                            wf.getFile().delete();
                        }
                        catch (SecurityException ex2) {}
                    }
                }
            }
        }
        return null;
    }
    
    private void addToTargetBucket(final Map targetCpDirBuckets, final WorkingFile source, final File targetRoot) {
        String workingCpid = source.getWorkingCpid();
        if (workingCpid == null || workingCpid.length() == 0) {
            workingCpid = this.cpid;
        }
        Map targetDirBuckets;
        if (targetCpDirBuckets.containsKey(workingCpid)) {
            targetDirBuckets = targetCpDirBuckets.get(workingCpid);
        }
        else {
            targetDirBuckets = new HashMap();
            targetCpDirBuckets.put(workingCpid, targetDirBuckets);
        }
        if (targetDirBuckets.containsKey(targetRoot)) {
            final WorkingFileList bucket = targetDirBuckets.get(targetRoot);
            bucket.add(source);
        }
        else {
            final WorkingFileList bucket = new WorkingFileList();
            bucket.add(source);
            targetDirBuckets.put(targetRoot, bucket);
        }
    }
    
    private Map convertToWorkingFileMap(final String[] from, final String[] to) throws APIException {
        if (from == null || to == null) {
            throw new InvalidCommandOptionException("No mapping specified for move members");
        }
        if (from.length != to.length) {
            throw new InvalidCommandOptionException("Unmatched filename mappings for move members.");
        }
        final Map fileNameMapping = new HashMap();
        final ArrayList toList = new ArrayList();
        for (int i = 0; i < to.length; ++i) {
            toList.add(to[i]);
        }
        final WorkingFileList destWorkingFiles = WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), toList);
        final Iterator destIterator = destWorkingFiles.iterator();
        for (int j = 0; j < from.length; ++j) {
            final WorkingFile dest = destIterator.next();
            fileNameMapping.put(new File(from[j]), dest);
        }
        return fileNameMapping;
    }
    
    public void setMapping(final String[] from, final String[] to) {
        this.fromNames = from;
        this.toNames = to;
    }
    
    private void addToWorkingFileList(final Map list, final WorkingFile wf) {
        String cpid = wf.getWorkingCpid();
        if (cpid == null || cpid.length() == 0) {
            cpid = this.cpid;
        }
        if (!list.containsKey(cpid)) {
            final WorkingFileList wflist = new WorkingFileList();
            wflist.add(wf);
            list.put(cpid, wflist);
        }
        else {
            list.get(cpid).add(wf);
        }
    }
}
