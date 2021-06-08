// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;

class SwingTimerFullBinding extends AbstractFullBinding implements ActionListener
{
    Timer timer;
    long startTime;
    long duration;
    int stepSize;
    boolean reportSteps;
    boolean reportFraction;
    boolean reportElapsed;
    boolean repeat;
    boolean bound;
    
    SwingTimerFullBinding(final ClosureSourceBinding source, final TargetBinding target) {
        this(source, target, 50, 1000);
    }
    
    SwingTimerFullBinding(final SourceBinding source, final TargetBinding target, final int interval, final int duration) {
        this.setSourceBinding(source);
        this.setTargetBinding(target);
        (this.timer = new Timer(interval, this)).setInitialDelay(0);
        this.timer.setRepeats(true);
        this.duration = duration;
    }
    
    void resetTimer() {
        this.timer.stop();
        this.startTime = System.currentTimeMillis();
        this.timer.start();
    }
    
    public void bind() {
        if (!this.bound) {
            this.resetTimer();
            this.bound = true;
        }
    }
    
    public void unbind() {
        if (this.bound) {
            this.timer.stop();
            this.bound = false;
        }
    }
    
    public void rebind() {
        if (this.bound) {
            this.resetTimer();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        final long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - this.startTime;
        if (elapsed >= this.duration) {
            if (this.repeat) {
                this.startTime = currentTime;
            }
            else {
                this.timer.stop();
            }
            elapsed = this.duration;
        }
        if (this.reportSteps) {
            ((ClosureSourceBinding)this.sourceBinding).setClosureArgument((int)(elapsed / this.stepSize));
        }
        else if (this.reportFraction) {
            ((ClosureSourceBinding)this.sourceBinding).setClosureArgument(new Float(elapsed / (float)this.duration));
        }
        else if (this.reportElapsed) {
            ((ClosureSourceBinding)this.sourceBinding).setClosureArgument(new Long(elapsed));
        }
        this.update();
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public void setDuration(final long duration) {
        this.duration = duration;
    }
    
    public int getInterval() {
        return this.timer.getDelay();
    }
    
    public void setInterval(final int interval) {
        this.timer.setDelay(interval);
    }
    
    public int getStepSize() {
        return this.stepSize;
    }
    
    public void setStepSize(final int stepSize) {
        this.stepSize = stepSize;
    }
    
    public boolean isCoalesce() {
        return this.timer.isCoalesce();
    }
    
    public void setCoalesce(final boolean coalesce) {
        this.timer.setCoalesce(coalesce);
    }
    
    public boolean isReportSteps() {
        return this.reportSteps;
    }
    
    public void setReportSteps(final boolean reportSteps) {
        this.reportSteps = reportSteps;
    }
    
    public boolean isReportFraction() {
        return this.reportFraction;
    }
    
    public void setReportFraction(final boolean reportFraction) {
        this.reportFraction = reportFraction;
    }
    
    public boolean isReportElapsed() {
        return this.reportElapsed;
    }
    
    public void setReportElapsed(final boolean reportElapsed) {
        this.reportElapsed = reportElapsed;
    }
    
    public boolean isRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(final boolean repeat) {
        this.repeat = repeat;
    }
}
