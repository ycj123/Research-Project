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

class MacOSXMenuBar$_run_closure1_closure6 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public MacOSXMenuBar$_run_closure1_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[3].callGroovyObjectGetProperty(this));
        $getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[5].callGroovyObjectGetProperty(this));
        $getCallSiteArray[6].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[7].callGroovyObjectGetProperty(this));
        $getCallSiteArray[8].callCurrent(this);
        $getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].callGroovyObjectGetProperty(this));
        $getCallSiteArray[11].callCurrent(this, $getCallSiteArray[12].callGroovyObjectGetProperty(this));
        $getCallSiteArray[13].callCurrent(this, $getCallSiteArray[14].callGroovyObjectGetProperty(this));
        $getCallSiteArray[15].callCurrent(this);
        $getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[17].callGroovyObjectGetProperty(this));
        $getCallSiteArray[18].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[19].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[20].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[21].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[22].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$MacOSXMenuBar$_run_closure1_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MacOSXMenuBar$_run_closure1_closure6.$callSiteArray == null || ($createCallSiteArray = MacOSXMenuBar$_run_closure1_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MacOSXMenuBar$_run_closure1_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = MacOSXMenuBar$_run_closure1_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (MacOSXMenuBar$_run_closure1_closure6.$class$java$lang$Object = class$("java.lang.Object"));
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
