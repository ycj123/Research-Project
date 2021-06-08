// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Collection;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ObjectBrowser$_run_closure1_closure3_closure7 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ObjectBrowser$_run_closure1_closure3_closure7(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if ($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(this)) instanceof Collection) {
            $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", " Collection data " }), new ObjectBrowser$_run_closure1_closure3_closure7_closure8(this, this.getThisObject()));
        }
        if ($getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGroovyObjectGetProperty(this)) instanceof Map) {
            $getCallSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", " Map data " }), new ObjectBrowser$_run_closure1_closure3_closure7_closure9(this, this.getThisObject()));
        }
        $getCallSiteArray[6].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", " Public Fields and Properties " }), new ObjectBrowser$_run_closure1_closure3_closure7_closure10(this, this.getThisObject()));
        return $getCallSiteArray[7].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", " (Meta) Methods " }), new ObjectBrowser$_run_closure1_closure3_closure7_closure11(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[8].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure7.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ObjectBrowser$_run_closure1_closure3_closure7.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ObjectBrowser$_run_closure1_closure3_closure7.$class$java$lang$Object = class$("java.lang.Object"));
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
