// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import java.util.Date;
import org.apache.log.format.PatternFormatter;

public class VelocityFormatter extends PatternFormatter
{
    public VelocityFormatter(final String format) {
        super(format);
    }
    
    protected String getTime(final long time, final String format) {
        return new Date().toString();
    }
}
