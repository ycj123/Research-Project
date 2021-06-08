// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import org.xml.sax.Attributes;

public abstract class AbstractObjectCreationFactory implements ObjectCreationFactory
{
    protected Digester digester;
    
    public AbstractObjectCreationFactory() {
        this.digester = null;
    }
    
    public abstract Object createObject(final Attributes p0) throws Exception;
    
    public Digester getDigester() {
        return this.digester;
    }
    
    public void setDigester(final Digester digester) {
        this.digester = digester;
    }
}
