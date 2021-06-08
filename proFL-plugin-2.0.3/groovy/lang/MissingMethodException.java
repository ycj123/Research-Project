// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.MethodRankHelper;
import org.codehaus.groovy.runtime.InvokerHelper;

public class MissingMethodException extends GroovyRuntimeException
{
    private final String method;
    private final Class type;
    private final boolean isStatic;
    private final Object[] arguments;
    
    public Object[] getArguments() {
        return this.arguments;
    }
    
    public MissingMethodException(final String method, final Class type, final Object[] arguments) {
        this(method, type, arguments, false);
    }
    
    public MissingMethodException(final String method, final Class type, final Object[] arguments, final boolean isStatic) {
        this.method = method;
        this.type = type;
        this.isStatic = isStatic;
        this.arguments = arguments;
    }
    
    @Override
    public String getMessage() {
        return "No signature of method: " + (this.isStatic ? "static " : "") + this.type.getName() + "." + this.method + "() is applicable for argument types: (" + InvokerHelper.toTypeString(this.arguments) + ") values: " + InvokerHelper.toString(this.arguments) + MethodRankHelper.getMethodSuggestionString(this.method, this.type, this.arguments);
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isStatic() {
        return this.isStatic;
    }
}
