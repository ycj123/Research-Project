// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.Task;

public class LoggingHelper
{
    private Task owner;
    
    public LoggingHelper(final Task owner) {
        assert owner != null;
        this.owner = owner;
    }
    
    public void error(final String msg) {
        this.owner.log(msg, 0);
    }
    
    public void warn(final String msg) {
        this.owner.log(msg, 1);
    }
    
    public void info(final String msg) {
        this.owner.log(msg, 2);
    }
    
    public void verbose(final String msg) {
        this.owner.log(msg, 3);
    }
    
    public void debug(final String msg) {
        this.owner.log(msg, 4);
    }
}
