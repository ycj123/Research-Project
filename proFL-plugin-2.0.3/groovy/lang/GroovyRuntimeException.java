// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ModuleNode;

public class GroovyRuntimeException extends RuntimeException
{
    private ModuleNode module;
    private ASTNode node;
    
    public GroovyRuntimeException() {
    }
    
    public GroovyRuntimeException(final String message) {
        super(message);
    }
    
    public GroovyRuntimeException(final String message, final ASTNode node) {
        super(message);
        this.node = node;
    }
    
    public GroovyRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public GroovyRuntimeException(final Throwable t) {
        this.initCause(t);
    }
    
    public void setModule(final ModuleNode module) {
        this.module = module;
    }
    
    public ModuleNode getModule() {
        return this.module;
    }
    
    @Override
    public String getMessage() {
        return this.getMessageWithoutLocationText() + this.getLocationText();
    }
    
    public ASTNode getNode() {
        return this.node;
    }
    
    public String getMessageWithoutLocationText() {
        return super.getMessage();
    }
    
    protected String getLocationText() {
        String answer = ". ";
        if (this.node != null) {
            answer = answer + "At [" + this.node.getLineNumber() + ":" + this.node.getColumnNumber() + "] ";
        }
        if (this.module != null) {
            answer += this.module.getDescription();
        }
        if (answer.equals(". ")) {
            return "";
        }
        return answer;
    }
}
