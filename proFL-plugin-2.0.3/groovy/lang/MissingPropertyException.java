// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.MethodRankHelper;

public class MissingPropertyException extends GroovyRuntimeException
{
    public static final Object MPE;
    private final String property;
    private final Class type;
    
    public MissingPropertyException(final String property, final Class type) {
        this.property = property;
        this.type = type;
    }
    
    public MissingPropertyException(final String property, final Class type, final Throwable t) {
        super(t);
        this.property = property;
        this.type = type;
    }
    
    public MissingPropertyException(final String message) {
        super(message);
        this.property = null;
        this.type = null;
    }
    
    public MissingPropertyException(final String message, final String property, final Class type) {
        super(message);
        this.property = property;
        this.type = type;
    }
    
    @Override
    public String getMessageWithoutLocationText() {
        final Throwable cause = this.getCause();
        if (cause != null) {
            return "No such property: " + this.property + " for class: " + this.type.getName() + ". Reason: " + cause;
        }
        if (super.getMessageWithoutLocationText() != null) {
            return super.getMessageWithoutLocationText();
        }
        return "No such property: " + this.property + " for class: " + this.type.getName() + MethodRankHelper.getPropertySuggestionString(this.property, this.type);
    }
    
    public String getProperty() {
        return this.property;
    }
    
    public Class getType() {
        return this.type;
    }
    
    static {
        MPE = new Object();
    }
}
