// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

public class ScmBranch extends AbstractScmVersion
{
    private static final long serialVersionUID = 6305050785257168739L;
    
    public String getType() {
        return "Branch";
    }
    
    public ScmBranch(final String name) {
        super(name);
    }
}
