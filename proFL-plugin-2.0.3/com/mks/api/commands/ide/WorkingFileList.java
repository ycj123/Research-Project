// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.HashMap;
import com.mks.api.response.APIException;
import com.mks.api.response.APIError;
import java.io.File;
import com.mks.api.CmdRunnerCreator;
import java.util.Map;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public final class WorkingFileList
{
    private List list;
    
    public WorkingFileList() {
    }
    
    public WorkingFileList(final WorkingFile[] wfArray) {
        this();
        this.add(wfArray);
    }
    
    public synchronized void add(final WorkingFile wf) {
        if (this.list == null) {
            this.list = new ArrayList(1);
        }
        this.list.add(wf);
    }
    
    public void add(final WorkingFile[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.add(array[i]);
        }
    }
    
    public Iterator iterator() {
        return this.getList().iterator();
    }
    
    public int size() {
        return this.getList().size();
    }
    
    public boolean isEmpty() {
        return this.getList().isEmpty();
    }
    
    public synchronized List getList() {
        return (this.list == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList((List<?>)this.list);
    }
    
    public Map getWorkingFileBuckets() {
        try {
            return this.getWorkingFileBuckets(null, null);
        }
        catch (APIException err) {
            throw new APIError(err);
        }
    }
    
    public Map getWorkingFileBuckets(final CmdRunnerCreator session, final File preferredRootSandbox) throws APIException {
        final Map wdMap = new HashMap();
        final Map workingFileBuckets = new HashMap();
        for (final WorkingFile wf : this) {
            if (!wf.isInSandboxDir()) {
                continue;
            }
            File root = wf.getWorkingRoot();
            if (root == null && preferredRootSandbox != null) {
                final File wfDir = wf.getFile().getParentFile();
                WorkingDirectory wd = wdMap.get(wfDir);
                if (wd == null) {
                    wd = WorkingDirectoryFactory.getWorkingDirectory(session, wfDir);
                    wdMap.put(wfDir, wd);
                }
                if (wd.isAmbiguous()) {
                    for (final SandboxInfo sandbox : wd.getSandboxes()) {
                        if (sandbox.isRelatedTo(session, preferredRootSandbox)) {
                            root = sandbox.getSandboxFile();
                            break;
                        }
                    }
                }
                else {
                    root = wd.getSandboxLocation();
                }
            }
            if (root == null) {
                continue;
            }
            WorkingFileList bucket = workingFileBuckets.get(root);
            if (bucket == null) {
                bucket = new WorkingFileList();
                workingFileBuckets.put(root, bucket);
            }
            bucket.add(wf);
        }
        return workingFileBuckets;
    }
    
    List getFileList() {
        final List fileList = new ArrayList();
        for (final WorkingFile wf : this) {
            fileList.add(wf.getFile());
        }
        return fileList;
    }
    
    public List getMemberList() {
        final List fileList = new ArrayList();
        for (final WorkingFile wf : this) {
            fileList.add(wf.getMemberName());
        }
        return fileList;
    }
    
    public void invalidate() {
        final WorkingFileCache wfCache = WorkingFileCache.getInstance();
        wfCache.invalidate(this, WorkingFileFactory.getNewOrdinal());
    }
}
