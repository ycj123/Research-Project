// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;

public class MissingClassException extends GroovyRuntimeException
{
    private final String type;
    
    public MissingClassException(final String type, final ASTNode node, final String message) {
        super("No such class: " + type + " " + message, node);
        this.type = type;
    }
    
    public MissingClassException(final ClassNode type, final String message) {
        super("No such class: " + type.getName() + " " + message);
        this.type = type.getName();
    }
    
    public String getType() {
        return this.type;
    }
}
