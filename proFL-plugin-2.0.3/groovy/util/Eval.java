// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GroovyShell;
import groovy.lang.Binding;
import org.codehaus.groovy.control.CompilationFailedException;

public class Eval
{
    public static Object me(final String expression) throws CompilationFailedException {
        return me(null, null, expression);
    }
    
    public static Object me(final String symbol, final Object object, final String expression) throws CompilationFailedException {
        final Binding b = new Binding();
        b.setVariable(symbol, object);
        final GroovyShell sh = new GroovyShell(b);
        return sh.evaluate(expression);
    }
    
    public static Object x(final Object x, final String expression) throws CompilationFailedException {
        return me("x", x, expression);
    }
    
    public static Object xy(final Object x, final Object y, final String expression) throws CompilationFailedException {
        final Binding b = new Binding();
        b.setVariable("x", x);
        b.setVariable("y", y);
        final GroovyShell sh = new GroovyShell(b);
        return sh.evaluate(expression);
    }
    
    public static Object xyz(final Object x, final Object y, final Object z, final String expression) throws CompilationFailedException {
        final Binding b = new Binding();
        b.setVariable("x", x);
        b.setVariable("y", y);
        b.setVariable("z", z);
        final GroovyShell sh = new GroovyShell(b);
        return sh.evaluate(expression);
    }
}
