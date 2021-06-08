// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.repository;

import java.util.Collections;
import java.util.List;
import org.apache.maven.scm.ScmException;

public class ScmRepositoryException extends ScmException
{
    static final long serialVersionUID = -2191549774722212492L;
    private List<String> validationMessages;
    
    public ScmRepositoryException(final String msg) {
        super(msg);
        this.validationMessages = Collections.emptyList();
    }
    
    public ScmRepositoryException(final String msg, final Throwable cause) {
        super(msg, cause);
        this.validationMessages = Collections.emptyList();
    }
    
    public ScmRepositoryException(final String msg, final List<String> validationMessages) {
        super(msg);
        this.validationMessages = Collections.emptyList();
        this.validationMessages = validationMessages;
    }
    
    public List<String> getValidationMessages() {
        return this.validationMessages;
    }
}
