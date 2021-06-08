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

class MacOSXMenuBar$_run_closure1_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public MacOSXMenuBar$_run_closure1_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[3].callGroovyObjectGetProperty(this));
        $getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[5].callGroovyObjectGetProperty(this));
        $getCallSiteArray[6].callCurrent(this);
        $getCallSiteArray[7].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[8].callGroovyObjectGetProperty(this));
        $getCallSiteArray[9].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[10].callGroovyObjectGetProperty(this));
        $getCallSiteArray[11].callCurrent(this);
        return $getCallSiteArray[12].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "icon", null }), $getCallSiteArray[13].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[14].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$MacOSXMenuBar$_run_closure1_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MacOSXMenuBar$_run_closure1_closure2.$callSiteArray == null || ($createCallSiteArray = MacOSXMenuBar$_run_closure1_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MacOSXMenuBar$_run_closure1_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = MacOSXMenuBar$_run_closure1_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (MacOSXMenuBar$_run_closure1_closure2.$class$java$lang$Object = class$("java.lang.Object"));
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
