// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.Iterator;
import java.io.File;
import com.mks.api.response.APIException;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.CmdRunnerCreator;
import java.util.HashMap;
import java.util.Map;

public class WorkingFileCache
{
    private static final long serialVersionUID = 1L;
    private static WorkingFileCache singleton;
    private static InheritableThreadLocal tempInstance;
    private Map cache;
    
    protected WorkingFileCache() {
        this.cache = new HashMap();
    }
    
    static WorkingFileCache getInstance() {
        return getInstance(false);
    }
    
    static synchronized WorkingFileCache getInstance(final boolean retain) {
        WorkingFileCache cache = WorkingFileCache.singleton;
        if (cache == null) {
            cache = (WorkingFileCache)WorkingFileCache.tempInstance.get();
            if (cache == null) {
                cache = new WorkingFileCache();
            }
        }
        if (retain) {
            WorkingFileCache.tempInstance.set(cache);
        }
        return cache;
    }
    
    protected static synchronized void setInstance(final WorkingFileCache instance) {
        WorkingFileCache.singleton = instance;
    }
    
    final synchronized void addWorkingFile(final CmdRunnerCreator session, final WorkingFile wf) {
        final WorkingFile cachedWF = this.cache.get(wf.getFile());
        if (cachedWF == null || cachedWF.getOrdinal() <= wf.getOrdinal()) {
            this.cache.put(wf.getFile(), wf);
            try {
                this.monitorSandbox(session, wf.getSandbox());
            }
            catch (APIException ex) {
                IntegrationPointFactory.getLogger().exception("WARNING", 0, ex);
                wf.invalidate();
            }
            finally {
                this.notifyAdded(wf);
            }
        }
    }
    
    protected void notifyAdded(final WorkingFile addedWF) {
    }
    
    final synchronized WorkingFile getWorkingFile(final File file) {
        return this.cache.get(file);
    }
    
    final synchronized void removeWorkingFile(final File file) {
        final WorkingFile wf = this.cache.remove(file);
        if (wf != null) {
            this.notifyRemoved(wf);
        }
    }
    
    protected void notifyRemoved(final WorkingFile removedWF) {
    }
    
    private synchronized WorkingFileList getWorkingFiles(final File directory) {
        final WorkingFileList wfList = new WorkingFileList();
        String dirPath = directory.getAbsolutePath();
        if (WorkingFileFactory.isWin32()) {
            dirPath = dirPath.toLowerCase();
        }
        for (final WorkingFile wf : this.cache.values()) {
            String name = wf.getName();
            if (WorkingFileFactory.isWin32()) {
                name = name.toLowerCase();
            }
            if (name.startsWith(dirPath)) {
                wfList.add(wf);
            }
        }
        return wfList;
    }
    
    protected void notifyInvalidated(final WorkingFileList invalidatedWFs) {
    }
    
    void invalidate(final WorkingFileList workingFiles, final long ordinal) {
        final WorkingFileList invalidatedList = new WorkingFileList();
        for (final WorkingFile wf : workingFiles) {
            WorkingFile cachedWF = this.getWorkingFile(wf.getFile());
            if (cachedWF == null) {
                cachedWF = wf;
            }
            if (wf != cachedWF) {
                wf.invalidate();
            }
            if (cachedWF.getOrdinal() <= ordinal) {
                if (cachedWF.isInvalid()) {
                    continue;
                }
                cachedWF.invalidate();
                invalidatedList.add(cachedWF);
            }
        }
        this.notifyInvalidated(invalidatedList);
    }
    
    void invalidate(final File directory, final long ordinal) {
        this.invalidate(this.getWorkingFiles(directory), ordinal);
    }
    
    final void monitorDirectory(final CmdRunnerCreator session, final WorkingDirectory wd) throws APIException {
        for (final ISandboxInfo si : wd.getSandboxes()) {
            this.monitorSandbox(session, si.getSandboxName());
        }
    }
    
    final void unmonitorDirectory(final CmdRunnerCreator session, final WorkingDirectory wd) throws APIException {
        for (final ISandboxInfo si : wd.getSandboxes()) {
            this.unmonitorSandbox(session, si.getSandboxName());
        }
    }
    
    protected void monitorSandbox(final CmdRunnerCreator session, final String sandbox) throws APIException {
    }
    
    protected void unmonitorSandbox(final CmdRunnerCreator session, final String sandbox) throws APIException {
    }
    
    static {
        WorkingFileCache.singleton = null;
        WorkingFileCache.tempInstance = new InheritableThreadLocal();
    }
}
