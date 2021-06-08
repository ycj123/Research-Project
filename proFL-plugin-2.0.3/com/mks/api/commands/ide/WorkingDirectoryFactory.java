// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.Iterator;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import java.util.Set;
import com.mks.api.response.APIException;
import java.util.HashSet;
import com.mks.api.commands.SICommands;
import java.io.File;
import com.mks.api.CmdRunnerCreator;

public final class WorkingDirectoryFactory
{
    private WorkingDirectoryFactory() {
    }
    
    public static WorkingDirectory getWorkingDirectory(final CmdRunnerCreator session, final File directory) throws APIException {
        final SICommands si = new SICommands(session, false);
        final String directoryPath = directory.getAbsolutePath();
        final WorkingDirectory workingDirectory = new WorkingDirectory(directory);
        try {
            final ISandboxInfo info = SandboxInfo.getSandboxInfo(si, directory);
            if (info != null) {
                workingDirectory.addSandbox(info);
                WorkingFileCache.getInstance().monitorSandbox(si.getCmdRunnerCreator(), info.getSandboxName());
            }
            return workingDirectory;
        }
        catch (APIException ex) {
            if (!"common.AmbiguousParentSandbox".equals(ex.getExceptionId())) {
                throw ex;
            }
            Set closestSandboxes = null;
            File closestSandboxDir = null;
            String directoryTestPath = directoryPath;
            if (isWin32()) {
                directoryTestPath = directoryTestPath.toLowerCase();
            }
            final WorkItemIterator wii = si.getSandboxes(true).getWorkItems();
            while (wii.hasNext()) {
                final WorkItem wi = wii.next();
                final String sandboxName = wi.getField("sandboxName").getValueAsString();
                final File sandboxLocation = new File(sandboxName).getParentFile();
                String testPath = sandboxLocation.getAbsolutePath();
                if (isWin32()) {
                    testPath = testPath.toLowerCase();
                }
                final int closestSandboxPathLength = (closestSandboxDir != null) ? closestSandboxDir.getPath().length() : 0;
                if (testPath.length() > closestSandboxPathLength && directoryTestPath.startsWith(testPath)) {
                    closestSandboxDir = sandboxLocation;
                    closestSandboxes = new HashSet();
                }
                if (sandboxLocation.equals(closestSandboxDir)) {
                    closestSandboxes.add(sandboxName);
                }
            }
            if (closestSandboxes != null) {
                for (final String sandboxName : closestSandboxes) {
                    final ISandboxInfo info2 = SandboxInfo.getSandboxInfo(si, new File(sandboxName));
                    if (info2 != null) {
                        workingDirectory.addSandbox(info2);
                    }
                }
            }
            WorkingFileCache.getInstance().monitorDirectory(si.getCmdRunnerCreator(), workingDirectory);
            return workingDirectory;
        }
    }
    
    static boolean isWin32() {
        return WorkingFileFactory.isWin32();
    }
    
    public static void refresh(final File directory) {
        refresh(directory, WorkingFileFactory.getNewOrdinal());
    }
    
    public static void refresh(final File directory, final long ordinal) {
        WorkingFileCache.getInstance().invalidate(directory, ordinal);
    }
}
