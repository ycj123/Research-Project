// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public class MissingFieldException extends GroovyRuntimeException
{
    private final String field;
    private final Class type;
    
    public MissingFieldException(final String field, final Class type) {
        super("No such field: " + field + " for class: " + type.getName());
        this.field = field;
        this.type = type;
    }
    
    public MissingFieldException(final String field, final Class type, final Throwable e) {
        super("No such field: " + field + " for class: " + type.getName() + ". Reason: " + e, e);
        this.field = field;
        this.type = type;
    }
    
    public MissingFieldException(final String message, final String field, final Class type) {
        super(message);
        this.field = field;
        this.type = type;
    }
    
    public String getField() {
        return this.field;
    }
    
    public Class getType() {
        return this.type;
    }
}
