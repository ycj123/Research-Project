// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConsoleView$_run_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ConsoleView$_run_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), Boolean.TRUE);
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView$_run_closure2.$callSiteArray == null || ($createCallSiteArray = ConsoleView$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ConsoleView$_run_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ConsoleView$_run_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
