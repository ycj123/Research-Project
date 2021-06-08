// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

public class ScmTag extends ScmBranch
{
    private static final long serialVersionUID = 2286671802987769257L;
    
    @Override
    public String getType() {
        return "Tag";
    }
    
    public ScmTag(final String name) {
        super(name);
    }
}
