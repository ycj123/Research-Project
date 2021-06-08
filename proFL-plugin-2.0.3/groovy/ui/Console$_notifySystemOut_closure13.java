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
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_notifySystemOut_closure13 extends Closure implements GeneratedClosure
{
    private Reference<Object> str;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public Console$_notifySystemOut_closure13(final Object _outerInstance, final Object _thisObject, final Reference<Object> str) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.str = str;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(((Reference<Object>)it2).get(), this.str.get(), $getCallSiteArray[1].callGetProperty(((Reference<Object>)it2).get()));
    }
    
    public String getStr() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.str.get(), $get$$class$java$lang$String());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_notifySystemOut_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_notifySystemOut_closure13.$callSiteArray == null || ($createCallSiteArray = Console$_notifySystemOut_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_notifySystemOut_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_notifySystemOut_closure13.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_notifySystemOut_closure13.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Console$_notifySystemOut_closure13.$class$java$lang$String) == null) {
            $class$java$lang$String = (Console$_notifySystemOut_closure13.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
