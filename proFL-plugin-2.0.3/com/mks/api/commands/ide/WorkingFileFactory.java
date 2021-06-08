// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.HashSet;
import java.util.Collection;
import com.mks.api.response.Field;
import com.mks.api.response.Item;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Response;
import java.util.Map;
import com.mks.api.response.InvalidItemException;
import com.mks.api.Option;
import com.mks.api.response.ItemNotFoundException;
import com.mks.api.OptionList;
import java.util.HashMap;
import java.util.Iterator;
import com.mks.api.response.APIException;
import java.util.Date;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import com.mks.api.CmdRunnerCreator;
import com.mks.api.Session;
import com.mks.api.commands.SICommands;

public final class WorkingFileFactory
{
    private static final String VIEWSANDBOX_FIELDS = "context,memberrev,workingrev,lockrecord,newrevdelta,revsyncdelta,wfdelta,workingcpid,locker,deferred";
    private static long ordinal;
    
    private WorkingFileFactory() {
    }
    
    private static WorkingFileCache getWorkingFileCache(final SICommands si) {
        final boolean retainedCache = !(si.getCmdRunnerCreator() instanceof Session);
        return WorkingFileCache.getInstance(retainedCache);
    }
    
    public static WorkingFile getWorkingFile(final CmdRunnerCreator session, final String fileName) {
        final List fileNames = new ArrayList(1);
        fileNames.add(fileName);
        return getWorkingFiles(session, fileNames).getList().get(0);
    }
    
    public static WorkingFile refreshWorkingFile(final CmdRunnerCreator session, final String fileName) {
        final List fileNames = new ArrayList(1);
        fileNames.add(fileName);
        return refreshWorkingFiles(session, fileNames).getList().get(0);
    }
    
    public static WorkingFileList getWorkingFiles(final CmdRunnerCreator session, final List fileNames) {
        return getWorkingFiles(session, fileNames, false);
    }
    
    private static WorkingFileList getWorkingFiles(final CmdRunnerCreator session, final List fileNames, final boolean refresh) {
        WorkingFileList wfs;
        try {
            final SICommands si = new SICommands(session, false);
            final WorkingFileCache wfCache = getWorkingFileCache(si);
            if (refresh) {
                final Iterator it = fileNames.iterator();
                while (it.hasNext()) {
                    wfCache.removeWorkingFile(new File(it.next()));
                }
            }
            wfs = getWorkingFiles(si, wfCache, fileNames);
        }
        catch (APIException apix) {
            wfs = invalidate(fileNames, new Date());
        }
        return wfs;
    }
    
    public static WorkingFileList refreshWorkingFiles(final CmdRunnerCreator session, final List fileNames) {
        return getWorkingFiles(session, fileNames, true);
    }
    
    public static WorkingFileList getWorkingFiles(final SICommands si, final List fileNames) {
        return getWorkingFiles(si, getWorkingFileCache(si), fileNames);
    }
    
    public static synchronized long getNewOrdinal() {
        return WorkingFileFactory.ordinal++;
    }
    
    private static WorkingFileList getWorkingFiles(final SICommands si, final WorkingFileCache wfCache, final List fileNames) {
        final Date timestamp = new Date();
        final long ordinal = getNewOrdinal();
        final Map dirMap = new HashMap();
        final Map sandboxContentMap = new HashMap();
        final Map sandboxBuckets = new HashMap();
        final Map indexMap = new HashMap();
        final List deferredMoves = new ArrayList();
        final Object[] fileNameArray = fileNames.toArray();
        final File[] files = new File[fileNameArray.length];
        final WorkingFile[] wfArray = new WorkingFile[fileNameArray.length];
        for (int i = 0; i < fileNameArray.length; ++i) {
            final String fileName = (String)fileNameArray[i];
            indexMap.put(files[i] = new File(fileName), new Integer(i));
            WorkingFile wf = wfCache.getWorkingFile(files[i]);
            if (wf != null) {
                wfArray[i] = wf;
                if (!wf.isInvalid()) {
                    continue;
                }
            }
            else {
                wf = new WorkingFile(files[i], null, null, false, timestamp, ordinal);
                wf.invalidate();
                wfArray[i] = wf;
                wfCache.addWorkingFile(si.getCmdRunnerCreator(), wf);
            }
            File workingRoot;
            try {
                workingRoot = getWorkingRoot(si, files[i], dirMap, sandboxContentMap);
            }
            catch (APIException apix) {
                wfArray[i] = new WorkingFile(files[i], apix, timestamp, ordinal);
                continue;
            }
            if (workingRoot == null) {
                wfArray[i] = new WorkingFile(files[i], null, null, false, timestamp, ordinal);
                wfCache.addWorkingFile(si.getCmdRunnerCreator(), wfArray[i]);
            }
            else {
                addToSbxBucket(workingRoot, files[i], sandboxBuckets);
            }
        }
        final Iterator workingRoots = sandboxBuckets.keySet().iterator();
        while (workingRoots != null && workingRoots.hasNext()) {
            final File workingRoot2 = workingRoots.next();
            final List sbxFiles = sandboxBuckets.get(workingRoot2);
            final List diffFiles = new ArrayList();
            boolean potentialDeferredRename = false;
            Response sbxView = null;
            sbxView = viewSandbox(si, workingRoot2, sbxFiles, null);
            if (sbxView != null) {
                try {
                    final WorkItemIterator wis = sbxView.getWorkItems();
                    final String userId = sbxView.getConnectionUsername();
                    final Iterator fileIterator = sbxFiles.iterator();
                    while (fileIterator.hasNext() && wis.hasNext()) {
                        final File file = fileIterator.next();
                        File sandbox = null;
                        final int index = indexMap.get(file);
                        try {
                            sandbox = getWorkingSandbox(si, file, dirMap, sandboxContentMap);
                            final WorkItem wi = wis.next();
                            String sandboxPath = null;
                            if (sandbox != null) {
                                sandboxPath = sandbox.getAbsolutePath();
                            }
                            final WorkingFile wf2 = new WorkingFile(file, sandboxPath, workingRoot2, wi, timestamp, userId, ordinal);
                            wfArray[index] = wf2;
                            if (wf2.isMoved()) {
                                potentialDeferredRename = true;
                                if (indexMap.containsKey(wf2.getFile())) {
                                    continue;
                                }
                                indexMap.put(wf2.getFile(), new Integer(index));
                                files[index] = wf2.getFile();
                            }
                            else {
                                if (!wf2.hasWorkingDelta() || wf2.isModified()) {
                                    continue;
                                }
                                diffFiles.add(file);
                            }
                        }
                        catch (ItemNotFoundException infx) {
                            WorkingFile wf3;
                            if (sandbox == null) {
                                wf3 = new WorkingFile(file, infx, timestamp, ordinal);
                            }
                            else if (file.equals(sandbox)) {
                                wf3 = new WorkingFile(file, sandbox.getAbsolutePath(), workingRoot2, true, timestamp, ordinal);
                            }
                            else {
                                final WorkItem wi2 = wis.getLast();
                                File nonMemberRoot = null;
                                try {
                                    final WorkingDirectory wd = getWorkingDirectory(si, workingRoot2, dirMap);
                                    if (wd.isSandboxAware() && !wd.isAmbiguous()) {
                                        nonMemberRoot = workingRoot2;
                                    }
                                }
                                catch (APIException ex) {}
                                wf3 = new WorkingFile(file, sandbox.getAbsolutePath(), nonMemberRoot, wi2, timestamp, userId, ordinal);
                                if (!wf3.isFormerMember()) {
                                    potentialDeferredRename = true;
                                }
                            }
                            wfArray[index] = wf3;
                        }
                        catch (APIException apix2) {
                            wfArray[index] = new WorkingFile(file, apix2, timestamp, ordinal);
                        }
                    }
                }
                finally {
                    try {
                        sbxView.release();
                    }
                    catch (APIException ex2) {}
                }
            }
            if (!diffFiles.isEmpty()) {
                final Response diff = diffStatus(si, workingRoot2, diffFiles);
                try {
                    final WorkItemIterator wis = (diff != null) ? diff.getWorkItems() : null;
                    for (final File file : diffFiles) {
                        final int index2 = indexMap.get(file);
                        boolean modified = true;
                        if (wis != null) {
                            try {
                                final WorkItem wi = wis.next();
                                final Item result = wi.getResult().getPrimaryValue();
                                final Field status = result.getField("different");
                                modified = status.getBoolean();
                            }
                            catch (Throwable t) {}
                        }
                        if (modified) {
                            wfArray[index2].markModified();
                        }
                    }
                }
                finally {
                    try {
                        if (diff != null) {
                            diff.release();
                        }
                    }
                    catch (APIException ex3) {}
                }
            }
            if (potentialDeferredRename) {
                final OptionList options = new OptionList();
                options.add(new Option("filterSubs"));
                options.add("filter", "deferred:move,deferred:rename");
                options.add(new Option("recurse"));
                sbxView = viewSandbox(si, workingRoot2, null, options);
                if (sbxView == null) {
                    continue;
                }
                final String userId2 = sbxView.getConnectionUsername();
                final WorkItemIterator wis = sbxView.getWorkItems();
                while (wis.hasNext()) {
                    try {
                        final WorkItem wi3 = wis.next();
                        if (wi3.getModelType() == null) {
                            continue;
                        }
                        final boolean isDestinedMember = wi3.getModelType().equals("si.DestinedMember");
                        final boolean isMember = wi3.getModelType().equals("si.Member");
                        if (!isDestinedMember && !isMember) {
                            continue;
                        }
                        final String fileName2 = wi3.getField("name").getString();
                        if (fileName2 == null) {
                            continue;
                        }
                        final File file2 = new File(fileName2);
                        File myWorkingRoot = workingRoot2;
                        if (isDestinedMember) {
                            final File canonicalSandbox = new File(wi3.getField("canonicalSandbox").getString());
                            myWorkingRoot = getWorkingRoot(si, canonicalSandbox, dirMap, sandboxContentMap);
                        }
                        final File sandbox2 = getWorkingSandbox(si, file2, dirMap, sandboxContentMap);
                        final WorkingFile wf4 = new WorkingFile(file2, sandbox2.getAbsolutePath(), myWorkingRoot, wi3, timestamp, userId2, ordinal);
                        if (!wf4.isMoved()) {
                            continue;
                        }
                        final Integer indexMarker = indexMap.get(file2);
                        if (indexMarker == null) {
                            continue;
                        }
                        final int index3 = indexMarker;
                        if (wfArray[index3].isModified()) {
                            wf4.markModified();
                        }
                        wfArray[index3] = wf4;
                        getWorkingRoot(si, wf4.getMemberName(), dirMap, sandboxContentMap);
                        if (!isDestinedMember || !wf4.isMoved()) {
                            continue;
                        }
                        deferredMoves.add(wf4);
                    }
                    catch (APIException apix4) {}
                }
            }
        }
        final WorkingFileList deferredMoveWFList = new WorkingFileList();
        final Iterator mv = deferredMoves.iterator();
        while (mv.hasNext()) {
            deferredMoveWFList.add(mv.next());
        }
        final Map deferredMoveSandboxBuckets = deferredMoveWFList.getWorkingFileBuckets();
        for (final File workingRoot3 : deferredMoveSandboxBuckets.keySet()) {
            final WorkingFileList sbxWorkingFiles = deferredMoveSandboxBuckets.get(workingRoot3);
            Response sbxView2 = null;
            sbxView2 = viewSandbox(si, workingRoot3, sbxWorkingFiles.getMemberList(), null);
            if (sbxView2 != null) {
                try {
                    final String userId = sbxView2.getConnectionUsername();
                    final WorkItemIterator wis2 = sbxView2.getWorkItems();
                    final Iterator workingFileIterator = sbxWorkingFiles.iterator();
                    while (workingFileIterator.hasNext() && wis2.hasNext()) {
                        final WorkingFile movedWorkingFile = workingFileIterator.next();
                        final int index4 = indexMap.get(movedWorkingFile.getFile());
                        try {
                            final WorkItem wi4 = wis2.next();
                            final WorkingFile wf2 = new WorkingFile(movedWorkingFile.getFile(), movedWorkingFile.getSandbox(), workingRoot3, wi4, timestamp, userId, ordinal);
                            if (wfArray[index4].isModified()) {
                                wf2.markModified();
                            }
                            wfArray[index4] = wf2;
                        }
                        catch (APIException apix3) {
                            wfArray[index4] = new WorkingFile(movedWorkingFile.getFile(), apix3, timestamp, ordinal);
                        }
                    }
                }
                finally {
                    try {
                        sbxView2.release();
                    }
                    catch (APIException ex4) {}
                }
            }
        }
        for (int j = 0; j < wfArray.length; ++j) {
            if (wfArray[j] == null) {
                wfArray[j] = new WorkingFile(files[j], new InvalidItemException(), timestamp, ordinal);
            }
            wfCache.addWorkingFile(si.getCmdRunnerCreator(), wfArray[j]);
            final WorkingFile cachedWF = wfCache.getWorkingFile(wfArray[j].getFile());
            if (!cachedWF.isInvalid()) {
                wfArray[j] = cachedWF;
            }
        }
        final WorkingFileList list = new WorkingFileList(wfArray);
        return list;
    }
    
    private static File getLocation(File filePath) {
        if (!filePath.isAbsolute()) {
            filePath = filePath.getAbsoluteFile();
        }
        if (!filePath.isDirectory()) {
            filePath = filePath.getParentFile();
        }
        return filePath;
    }
    
    private static File getWorkingRoot(final SICommands si, final File element, final Map dirMap, final Map sandboxContentMap) throws APIException {
        File root = null;
        final WorkingDirectory wd = getWorkingDirectory(si, element, dirMap);
        if (!wd.isSandboxAware()) {
            root = null;
        }
        else if (!wd.isAmbiguous()) {
            final ISandboxInfo sandbox = wd.getSandboxes().iterator().next();
            String parent = sandbox.getParentName();
            if (parent != null) {
                root = getWorkingRoot(si, new File(parent), dirMap, sandboxContentMap);
                if (!root.isDirectory()) {
                    parent = null;
                }
            }
            if (parent == null) {
                root = sandbox.getSandboxLocation();
            }
        }
        else {
            root = getWorkingSandbox(si, element, dirMap, sandboxContentMap);
        }
        return root;
    }
    
    private static File getWorkingSandbox(final SICommands si, final File element, final Map dirMap, final Map sandboxContentMap) throws APIException {
        final WorkingDirectory wd = getWorkingDirectory(si, element, dirMap);
        if (!wd.isSandboxAware()) {
            return null;
        }
        if (!wd.isAmbiguous()) {
            final ISandboxInfo sandbox = wd.getSandboxes().iterator().next();
            return sandbox.getSandboxFile();
        }
        File sandboxFile = null;
        for (final ISandboxInfo sandbox2 : wd.getSandboxes()) {
            sandboxFile = sandbox2.getSandboxFile();
            if (sandboxFile.equals(element)) {
                break;
            }
            Collection content = sandboxContentMap.get(sandboxFile);
            if (content == null) {
                final Response sbxView = viewSandbox(si, sandboxFile, null, null);
                content = new HashSet();
                if (sbxView != null) {
                    try {
                        final WorkItemIterator i = sbxView.getWorkItems();
                        while (i.hasNext()) {
                            WorkItem wi = null;
                            try {
                                wi = i.next();
                            }
                            catch (APIException ignored) {
                                wi = i.getLast();
                            }
                            File wiFile = new File(wi.getId());
                            if (!wiFile.isAbsolute()) {
                                wiFile = new File(sandboxFile.getParentFile(), wi.getId());
                            }
                            content.add(wiFile);
                        }
                        if (sbxView.getAPIException() != null) {
                            throw sbxView.getAPIException();
                        }
                    }
                    finally {
                        try {
                            sbxView.release();
                        }
                        catch (APIException ex) {}
                    }
                }
                sandboxContentMap.put(sandboxFile, content);
            }
            if (content.contains(element)) {
                break;
            }
        }
        return sandboxFile;
    }
    
    private static WorkingDirectory getWorkingDirectory(final SICommands si, final File element, final Map dirMap) throws APIException {
        WorkingDirectory wd = null;
        final File path = getLocation(element);
        if (dirMap.containsKey(path)) {
            wd = dirMap.get(path);
        }
        if (wd == null) {
            wd = WorkingDirectoryFactory.getWorkingDirectory(si.getCmdRunnerCreator(), path);
            dirMap.put(path, wd);
        }
        return wd;
    }
    
    private static void addToSbxBucket(final File root, final File file, final Map sandboxBuckets) {
        List bucket = null;
        if (sandboxBuckets.containsKey(root)) {
            bucket = sandboxBuckets.get(root);
            bucket.add(file);
        }
        else {
            bucket = new ArrayList();
            bucket.add(file);
            sandboxBuckets.put(root, bucket);
        }
    }
    
    private static String[] memberNames(final Collection members) {
        String[] memberNames;
        if (members == null) {
            memberNames = null;
        }
        else {
            memberNames = new String[members.size()];
            final Iterator i = members.iterator();
            int j = 0;
            while (i.hasNext()) {
                final File member = i.next();
                memberNames[j] = member.getAbsolutePath();
                ++j;
            }
        }
        return memberNames;
    }
    
    private static Response viewSandbox(final SICommands si, final File sandboxRoot, final List members, final OptionList options) {
        final File sandboxDir = getLocation(sandboxRoot);
        final OptionList viewOptions = new OptionList();
        viewOptions.add(new Option("fields", "context,memberrev,workingrev,lockrecord,newrevdelta,revsyncdelta,wfdelta,workingcpid,locker,deferred"));
        viewOptions.add(new Option("norecurse"));
        if (!sandboxRoot.isDirectory()) {
            viewOptions.add(new Option("sandbox", sandboxRoot.getAbsolutePath()));
        }
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                viewOptions.add(it.next());
            }
        }
        Response r;
        try {
            final String cwdPath = sandboxDir.getAbsolutePath();
            r = si.getSandboxMemberStatus(cwdPath, memberNames(members), viewOptions, true);
        }
        catch (APIException apix) {
            r = apix.getResponse();
        }
        return r;
    }
    
    private static Response diffStatus(final SICommands si, final File workingRoot, final List members) {
        Response r;
        try {
            OptionList options = null;
            String cwdPath = null;
            if (workingRoot != null) {
                cwdPath = getLocation(workingRoot).getAbsolutePath();
                if (!workingRoot.isDirectory()) {
                    options = new OptionList();
                    options.add(new Option("sandbox", workingRoot.getAbsolutePath()));
                }
            }
            r = si.getSandboxMemberDifferenceStatus(cwdPath, memberNames(members), options);
        }
        catch (APIException apix) {
            r = apix.getResponse();
        }
        return r;
    }
    
    private static WorkingFileList invalidate(final List fileNames, final Date timestamp) {
        final WorkingFile[] wfArray = new WorkingFile[fileNames.size()];
        for (int i = 0; i < fileNames.size(); ++i) {
            wfArray[i] = new WorkingFile(fileNames.get(i), new InvalidItemException(), timestamp, getNewOrdinal());
        }
        return new WorkingFileList(wfArray);
    }
    
    static boolean isWin32() {
        return System.getProperty("os.name").indexOf("Windows") != -1;
    }
    
    static {
        WorkingFileFactory.ordinal = 0L;
    }
}
