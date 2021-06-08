// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Integer i = (Integer)new Reference(ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$const$0);
        final Object data = $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(this)), new ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18(this, this.getThisObject(), (Reference<Object>)i));
        return $getCallSiteArray[3].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "list", data }), new ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure19(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17.$class$java$lang$Object = class$("java.lang.Object"));
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
