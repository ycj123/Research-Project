// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicMenuBar$_run_closure1_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public BasicMenuBar$_run_closure1_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        $getCallSiteArray[2].callCurrent(this, $getCallSiteArray[3].callGroovyObjectGetProperty(this));
        $getCallSiteArray[4].callCurrent(this);
        $getCallSiteArray[5].callCurrent(this, $getCallSiteArray[6].callGroovyObjectGetProperty(this));
        $getCallSiteArray[7].callCurrent(this, $getCallSiteArray[8].callGroovyObjectGetProperty(this));
        $getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].callGroovyObjectGetProperty(this));
        $getCallSiteArray[11].callCurrent(this);
        $getCallSiteArray[12].callCurrent(this, $getCallSiteArray[13].callGroovyObjectGetProperty(this));
        $getCallSiteArray[14].callCurrent(this, $getCallSiteArray[15].callGroovyObjectGetProperty(this));
        $getCallSiteArray[16].callCurrent(this, $getCallSiteArray[17].callGroovyObjectGetProperty(this));
        $getCallSiteArray[18].callCurrent(this, $getCallSiteArray[19].callGroovyObjectGetProperty(this));
        $getCallSiteArray[20].callCurrent(this);
        return $getCallSiteArray[21].callCurrent(this, $getCallSiteArray[22].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[23].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicMenuBar$_run_closure1_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicMenuBar$_run_closure1_closure3.$callSiteArray == null || ($createCallSiteArray = BasicMenuBar$_run_closure1_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicMenuBar$_run_closure1_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicMenuBar$_run_closure1_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicMenuBar$_run_closure1_closure3.$class$java$lang$Object = class$("java.lang.Object"));
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
