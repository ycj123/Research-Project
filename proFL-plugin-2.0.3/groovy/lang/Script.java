// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.io.IOException;
import java.io.File;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;

public abstract class Script extends GroovyObjectSupport
{
    private Binding binding;
    
    protected Script() {
        this(new Binding());
    }
    
    protected Script(final Binding binding) {
        this.binding = binding;
    }
    
    public Binding getBinding() {
        return this.binding;
    }
    
    public void setBinding(final Binding binding) {
        this.binding = binding;
    }
    
    @Override
    public Object getProperty(final String property) {
        try {
            return this.binding.getVariable(property);
        }
        catch (MissingPropertyException e) {
            return super.getProperty(property);
        }
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        if ("binding".equals(property)) {
            this.setBinding((Binding)newValue);
        }
        else if ("metaClass".equals(property)) {
            this.setMetaClass((MetaClass)newValue);
        }
        else {
            this.binding.setVariable(property, newValue);
        }
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        try {
            return super.invokeMethod(name, args);
        }
        catch (MissingMethodException mme) {
            try {
                if (!name.equals(mme.getMethod())) {
                    throw mme;
                }
                final Object boundClosure = this.binding.getVariable(name);
                if (boundClosure != null && boundClosure instanceof Closure) {
                    return ((Closure)boundClosure).call((Object[])args);
                }
                throw mme;
            }
            catch (MissingPropertyException mpe) {
                throw mme;
            }
        }
    }
    
    public abstract Object run();
    
    public void println() {
        Object object;
        try {
            object = this.getProperty("out");
        }
        catch (MissingPropertyException e) {
            System.out.println();
            return;
        }
        InvokerHelper.invokeMethod(object, "println", ArgumentListExpression.EMPTY_ARRAY);
    }
    
    public void print(final Object value) {
        Object object;
        try {
            object = this.getProperty("out");
        }
        catch (MissingPropertyException e) {
            DefaultGroovyMethods.print(System.out, value);
            return;
        }
        InvokerHelper.invokeMethod(object, "print", new Object[] { value });
    }
    
    public void println(final Object value) {
        Object object;
        try {
            object = this.getProperty("out");
        }
        catch (MissingPropertyException e) {
            DefaultGroovyMethods.println(System.out, value);
            return;
        }
        InvokerHelper.invokeMethod(object, "println", new Object[] { value });
    }
    
    public void printf(final String format, final Object value) {
        Object object;
        try {
            object = this.getProperty("out");
        }
        catch (MissingPropertyException e) {
            DefaultGroovyMethods.printf((Object)System.out, format, value);
            return;
        }
        InvokerHelper.invokeMethod(object, "printf", new Object[] { format, value });
    }
    
    public void printf(final String format, final Object[] values) {
        Object object;
        try {
            object = this.getProperty("out");
        }
        catch (MissingPropertyException e) {
            DefaultGroovyMethods.printf((Object)System.out, format, values);
            return;
        }
        InvokerHelper.invokeMethod(object, "printf", new Object[] { format, values });
    }
    
    public Object evaluate(final String expression) throws CompilationFailedException {
        final GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), this.binding);
        return shell.evaluate(expression);
    }
    
    public Object evaluate(final File file) throws CompilationFailedException, IOException {
        final GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), this.binding);
        return shell.evaluate(file);
    }
    
    public void run(final File file, final String[] arguments) throws CompilationFailedException, IOException {
        final GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), this.binding);
        shell.run(file, arguments);
    }
}
