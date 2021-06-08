// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class MacOSXMenuBar$_run_closure1_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public MacOSXMenuBar$_run_closure1_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        $getCallSiteArray[2].callCurrent(this);
        $getCallSiteArray[3].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[4].callGroovyObjectGetProperty(this));
        $getCallSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[6].callGroovyObjectGetProperty(this));
        $getCallSiteArray[7].callCurrent(this);
        $getCallSiteArray[8].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[9].callGetProperty($getCallSiteArray[10].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[11].callGroovyObjectGetProperty(this));
        $getCallSiteArray[12].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[13].callGetProperty($getCallSiteArray[14].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[15].callGroovyObjectGetProperty(this));
        $getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[17].callGetProperty($getCallSiteArray[18].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[19].callGroovyObjectGetProperty(this));
        $getCallSiteArray[20].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[23].callGroovyObjectGetProperty(this));
        $getCallSiteArray[24].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[27].callGroovyObjectGetProperty(this));
        $getCallSiteArray[28].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[29].callGetProperty($getCallSiteArray[30].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[31].callGroovyObjectGetProperty(this));
        $getCallSiteArray[32].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[33].callGetProperty($getCallSiteArray[34].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[35].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[36].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "selected", $getCallSiteArray[37].callGetProperty($getCallSiteArray[38].callGroovyObjectGetProperty(this)) }), $getCallSiteArray[39].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[40].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[41];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$MacOSXMenuBar$_run_closure1_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MacOSXMenuBar$_run_closure1_closure4.$callSiteArray == null || ($createCallSiteArray = MacOSXMenuBar$_run_closure1_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MacOSXMenuBar$_run_closure1_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = MacOSXMenuBar$_run_closure1_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (MacOSXMenuBar$_run_closure1_closure4.$class$java$lang$Object = class$("java.lang.Object"));
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
