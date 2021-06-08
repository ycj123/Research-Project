// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_runScriptImpl_closure17_closure32 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public Console$_runScriptImpl_closure17_closure32(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object it) {
        return $getCallSiteArray()[0].callCurrent(this, this.result.get());
    }
    
    public Object getResult() {
        $getCallSiteArray();
        return this.result.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_runScriptImpl_closure17_closure32(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_runScriptImpl_closure17_closure32.$callSiteArray == null || ($createCallSiteArray = Console$_runScriptImpl_closure17_closure32.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_runScriptImpl_closure17_closure32.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_runScriptImpl_closure17_closure32.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_runScriptImpl_closure17_closure32.$class$java$lang$Object = class$("java.lang.Object"));
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
