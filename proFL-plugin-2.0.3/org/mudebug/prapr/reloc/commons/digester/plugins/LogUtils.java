// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

import org.mudebug.prapr.reloc.commons.logging.impl.NoOpLog;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.Digester;

class LogUtils
{
    static Log getLogger(final Digester digester) {
        if (digester == null) {
            return new NoOpLog();
        }
        return digester.getLogger();
    }
}
