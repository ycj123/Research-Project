// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import junit.framework.TestResult;
import junit.framework.Test;

public class ScriptTestAdapter implements Test
{
    private Class scriptClass;
    private String[] arguments;
    
    public ScriptTestAdapter(final Class scriptClass, final String[] arguments) {
        this.scriptClass = scriptClass;
        this.arguments = arguments;
    }
    
    public int countTestCases() {
        return 1;
    }
    
    public void run(final TestResult result) {
        try {
            result.startTest((Test)this);
            InvokerHelper.runScript(this.scriptClass, this.arguments);
            result.endTest((Test)this);
        }
        catch (Exception e) {
            result.addError((Test)this, (Throwable)e);
        }
    }
    
    @Override
    public String toString() {
        return "TestCase for script: " + this.scriptClass.getName();
    }
}
