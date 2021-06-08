// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

public class ScmRevision extends AbstractScmVersion
{
    private static final long serialVersionUID = 3831426256650754391L;
    
    public String getType() {
        return "Revision";
    }
    
    public ScmRevision(final String name) {
        super(name);
    }
}
