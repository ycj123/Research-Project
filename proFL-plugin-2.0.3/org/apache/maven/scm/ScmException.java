// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

public class ScmException extends Exception
{
    static final long serialVersionUID = 5041965569154385323L;
    
    public ScmException(final String message) {
        super(message);
    }
    
    public ScmException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
