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

class Console$__clinit__closure23_closure37 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$Console$__clinit__closure23_closure37;
    
    public Console$__clinit__closure23_closure37(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        try {
            ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console$__clinit__closure23_closure37(), $getCallSiteArray[0].callGroovyObjectGetProperty(this), "locationByPlatform");
        }
        catch (Exception e) {
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.createList(new Object[] { Console$__clinit__closure23_closure37.$const$0, Console$__clinit__closure23_closure37.$const$0 }), $get$$class$groovy$ui$Console$__clinit__closure23_closure37(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), "location");
        }
        final Object call = $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGroovyObjectGetProperty(this));
        ScriptBytecodeAdapter.setGroovyObjectProperty(call, $get$$class$groovy$ui$Console$__clinit__closure23_closure37(), this, "containingWindows");
        return call;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[5].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 100;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$__clinit__closure23_closure37(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$__clinit__closure23_closure37.$callSiteArray == null || ($createCallSiteArray = Console$__clinit__closure23_closure37.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$__clinit__closure23_closure37.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$__clinit__closure23_closure37.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$__clinit__closure23_closure37.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$__clinit__closure23_closure37() {
        Class $class$groovy$ui$Console$__clinit__closure23_closure37;
        if (($class$groovy$ui$Console$__clinit__closure23_closure37 = Console$__clinit__closure23_closure37.$class$groovy$ui$Console$__clinit__closure23_closure37) == null) {
            $class$groovy$ui$Console$__clinit__closure23_closure37 = (Console$__clinit__closure23_closure37.$class$groovy$ui$Console$__clinit__closure23_closure37 = class$("groovy.ui.Console$__clinit__closure23_closure37"));
        }
        return $class$groovy$ui$Console$__clinit__closure23_closure37;
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
