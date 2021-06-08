// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.bsf;

import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFEngine;
import org.apache.bsf.util.BSFFunctions;
import org.apache.bsf.BSFManager;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.apache.bsf.BSFException;
import groovy.lang.Closure;
import java.util.Vector;
import groovy.lang.GroovyShell;
import org.apache.bsf.util.BSFEngineImpl;

public class GroovyEngine extends BSFEngineImpl
{
    protected GroovyShell shell;
    
    private String convertToValidJavaClassname(final String inName) {
        if (inName == null || inName.equals("")) {
            return "_";
        }
        final StringBuffer output = new StringBuffer(inName.length());
        boolean firstChar = true;
        for (int i = 0; i < inName.length(); ++i) {
            char ch = inName.charAt(i);
            if (firstChar && !Character.isJavaIdentifierStart(ch)) {
                ch = '_';
            }
            else if (!firstChar && !Character.isJavaIdentifierPart(ch) && ch != '.') {
                ch = '_';
            }
            firstChar = (ch == '.');
            output.append(ch);
        }
        return output.toString();
    }
    
    public Object apply(final String source, final int lineNo, final int columnNo, final Object funcBody, final Vector paramNames, final Vector arguments) throws BSFException {
        final Object object = this.eval(source, lineNo, columnNo, funcBody);
        if (object instanceof Closure) {
            final Closure closure = (Closure)object;
            return closure.call(arguments.toArray());
        }
        return object;
    }
    
    public Object call(final Object object, final String method, final Object[] args) throws BSFException {
        return InvokerHelper.invokeMethod(object, method, args);
    }
    
    public Object eval(String source, final int lineNo, final int columnNo, final Object script) throws BSFException {
        try {
            source = this.convertToValidJavaClassname(source);
            return this.getEvalShell().evaluate(script.toString(), source);
        }
        catch (Exception e) {
            throw new BSFException(100, "exception from Groovy: " + e, (Throwable)e);
        }
    }
    
    public void exec(String source, final int lineNo, final int columnNo, final Object script) throws BSFException {
        try {
            source = this.convertToValidJavaClassname(source);
            this.getEvalShell().evaluate(script.toString(), source);
        }
        catch (Exception e) {
            throw new BSFException(100, "exception from Groovy: " + e, (Throwable)e);
        }
    }
    
    public void initialize(final BSFManager mgr, final String lang, final Vector declaredBeans) throws BSFException {
        super.initialize(mgr, lang, declaredBeans);
        (this.shell = new GroovyShell(mgr.getClassLoader())).setVariable("bsf", new BSFFunctions(mgr, (BSFEngine)this));
        for (int size = declaredBeans.size(), i = 0; i < size; ++i) {
            this.declareBean(declaredBeans.elementAt(i));
        }
    }
    
    public void declareBean(final BSFDeclaredBean bean) throws BSFException {
        this.shell.setVariable(bean.name, bean.bean);
    }
    
    public void undeclareBean(final BSFDeclaredBean bean) throws BSFException {
        this.shell.setVariable(bean.name, null);
    }
    
    protected GroovyShell getEvalShell() {
        return new GroovyShell(this.shell);
    }
}
