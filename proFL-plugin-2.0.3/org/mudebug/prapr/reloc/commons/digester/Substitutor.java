// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import org.xml.sax.Attributes;

public abstract class Substitutor
{
    public abstract Attributes substitute(final Attributes p0);
    
    public abstract String substitute(final String p0);
}
