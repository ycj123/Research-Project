// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConsoleView$_run_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleView$_run_closure1;
    
    public ConsoleView$_run_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView$_run_closure1(), $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(this)), "delegate");
        $getCallSiteArray[3].call($getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), "menuBarDelegate"), $getCallSiteArray[6].callGroovyObjectGetProperty(this));
        $getCallSiteArray[7].callCurrent(this, $getCallSiteArray[8].callGroovyObjectGetProperty(this));
        $getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[11].callCurrent(this, $getCallSiteArray[12].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[13].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView$_run_closure1.$callSiteArray == null || ($createCallSiteArray = ConsoleView$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ConsoleView$_run_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ConsoleView$_run_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleView$_run_closure1() {
        Class $class$groovy$ui$ConsoleView$_run_closure1;
        if (($class$groovy$ui$ConsoleView$_run_closure1 = ConsoleView$_run_closure1.$class$groovy$ui$ConsoleView$_run_closure1) == null) {
            $class$groovy$ui$ConsoleView$_run_closure1 = (ConsoleView$_run_closure1.$class$groovy$ui$ConsoleView$_run_closure1 = class$("groovy.ui.ConsoleView$_run_closure1"));
        }
        return $class$groovy$ui$ConsoleView$_run_closure1;
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
