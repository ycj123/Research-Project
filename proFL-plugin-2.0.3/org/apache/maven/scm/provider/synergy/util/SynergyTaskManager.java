// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.util;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.log.ScmLogger;

public class SynergyTaskManager
{
    private static final short TASK_STATE_NONE = 0;
    private static final short TASK_STATE_CREATED = 1;
    private static final short TASK_STATE_COMPLETED = 2;
    private static final SynergyTaskManager INSTANCE;
    private int currentTaskNumber;
    private short currentTaskState;
    
    public SynergyTaskManager() {
        this.currentTaskState = 0;
    }
    
    public static SynergyTaskManager getInstance() {
        return SynergyTaskManager.INSTANCE;
    }
    
    public int createTask(final ScmLogger logger, final String synopsis, final String release, final boolean defaultTask, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering createTask method of SynergyTaskManager");
        }
        switch (this.currentTaskState) {
            case 1: {
                if (defaultTask && SynergyUtil.getDefaultTask(logger, ccmAddr) != this.currentTaskNumber) {
                    SynergyUtil.setDefaultTask(logger, this.currentTaskNumber, ccmAddr);
                    break;
                }
                break;
            }
            case 0:
            case 2: {
                this.currentTaskNumber = SynergyUtil.createTask(logger, synopsis, release, defaultTask, ccmAddr);
                this.currentTaskState = 1;
                break;
            }
            default: {
                throw new IllegalStateException("Programming error: SynergyTaskManager is in unkown state.");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("createTask returns " + this.currentTaskNumber);
        }
        return this.currentTaskNumber;
    }
    
    public void checkinDefaultTask(final ScmLogger logger, final String comment, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkinDefaultTask method of SynergyTaskManager");
        }
        switch (this.currentTaskState) {
            case 0: {
                if (SynergyUtil.getDefaultTask(logger, ccmAddr) != 0) {
                    SynergyUtil.checkinDefaultTask(logger, comment, ccmAddr);
                    break;
                }
                throw new ScmException("Check in not possible: no default task is set and no task has been created with SynergyTaskManager.");
            }
            case 1: {
                SynergyUtil.checkinTask(logger, this.currentTaskNumber, comment, ccmAddr);
                this.currentTaskState = 2;
                break;
            }
            case 2: {
                if (SynergyUtil.getDefaultTask(logger, ccmAddr) != 0) {
                    SynergyUtil.checkinDefaultTask(logger, comment, ccmAddr);
                    break;
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Synergy : No check in necessary as default task and all tasks created with SynergyTaskManager have already been checked in.");
                    break;
                }
                break;
            }
            default: {
                throw new IllegalStateException("Programming error: SynergyTaskManager is in unkown state.");
            }
        }
    }
    
    static {
        INSTANCE = new SynergyTaskManager();
    }
}
