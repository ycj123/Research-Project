// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_run_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$Console$_run_closure3;
    
    public Console$_run_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object arg) {
        final Object arg2 = new Reference(arg);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callCurrent = $getCallSiteArray[0].callCurrent(this, ((Reference<Object>)arg2).get());
        ScriptBytecodeAdapter.setProperty(callCurrent, $get$$class$groovy$ui$Console$_run_closure3(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), "JMenuBar");
        return callCurrent;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_run_closure3.$callSiteArray == null || ($createCallSiteArray = Console$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_run_closure3() {
        Class $class$groovy$ui$Console$_run_closure3;
        if (($class$groovy$ui$Console$_run_closure3 = Console$_run_closure3.$class$groovy$ui$Console$_run_closure3) == null) {
            $class$groovy$ui$Console$_run_closure3 = (Console$_run_closure3.$class$groovy$ui$Console$_run_closure3 = class$("groovy.ui.Console$_run_closure3"));
        }
        return $class$groovy$ui$Console$_run_closure3;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
