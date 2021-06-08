// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedConstructor;
import java.lang.reflect.Modifier;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.util.FastArray;
import groovy.lang.GroovyRuntimeException;

public class MethodSelectionException extends GroovyRuntimeException
{
    private final String methodName;
    private final FastArray methods;
    private final Class[] arguments;
    
    public MethodSelectionException(final String methodName, final FastArray methods, final Class[] arguments) {
        super(methodName);
        this.methodName = methodName;
        this.arguments = arguments;
        this.methods = methods;
    }
    
    @Override
    public String getMessage() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Could not find which method ").append(this.methodName);
        this.appendClassNames(buffer, this.arguments);
        buffer.append(" to invoke from this list:");
        this.appendMethods(buffer);
        return buffer.toString();
    }
    
    private void appendClassNames(final StringBuffer argBuf, final Class[] classes) {
        argBuf.append("(");
        for (int i = 0; i < classes.length; ++i) {
            if (i > 0) {
                argBuf.append(", ");
            }
            final Class clazz = classes[i];
            final String name = (clazz == null) ? "null" : clazz.getName();
            argBuf.append(name);
        }
        argBuf.append(")");
    }
    
    private void appendMethods(final StringBuffer buffer) {
        for (int i = 0; i < this.methods.size; ++i) {
            buffer.append("\n  ");
            final Object methodOrConstructor = this.methods.get(i);
            if (methodOrConstructor instanceof MetaMethod) {
                final MetaMethod method = (MetaMethod)methodOrConstructor;
                buffer.append(Modifier.toString(method.getModifiers()));
                buffer.append(" ").append(method.getReturnType().getName());
                buffer.append(" ").append(method.getDeclaringClass().getName());
                buffer.append("#");
                buffer.append(method.getName());
                this.appendClassNames(buffer, method.getNativeParameterTypes());
            }
            else {
                final CachedConstructor method2 = (CachedConstructor)methodOrConstructor;
                buffer.append(Modifier.toString(method2.cachedConstructor.getModifiers()));
                buffer.append(" ").append(method2.cachedConstructor.getDeclaringClass().getName());
                buffer.append("#<init>");
                this.appendClassNames(buffer, method2.getNativeParameterTypes());
            }
        }
    }
}
