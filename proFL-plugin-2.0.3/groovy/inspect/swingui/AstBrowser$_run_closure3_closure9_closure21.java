// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure3_closure9_closure21 extends Closure implements GeneratedClosure
{
    private Reference<Object> phasePicker;
    private Reference<Object> script;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstBrowser$_run_closure3_closure9_closure21(final Object _outerInstance, final Object _thisObject, final Reference<Object> phasePicker, final Reference<Object> script) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phasePicker = phasePicker;
        this.script = script;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGetProperty(this.phasePicker.get())), $getCallSiteArray[3].call(this.script.get()));
        return $getCallSiteArray[4].callCurrent(this, $getCallSiteArray[5].callGroovyObjectGetProperty(this), $getCallSiteArray[6].call(this.script.get()), $getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGetProperty(this.phasePicker.get())));
    }
    
    public Object getPhasePicker() {
        $getCallSiteArray();
        return this.phasePicker.get();
    }
    
    public Closure getScript() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(this.script.get(), $get$$class$groovy$lang$Closure());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure21(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3_closure9_closure21.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3_closure9_closure21.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3_closure9_closure21.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3_closure9_closure21.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3_closure9_closure21.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstBrowser$_run_closure3_closure9_closure21.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstBrowser$_run_closure3_closure9_closure21.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
