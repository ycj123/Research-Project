// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_run_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> prefs;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public BasicContentPane$_run_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> prefs) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.prefs = prefs;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "inputArea", "font", $getCallSiteArray[1].callConstructor($get$$class$java$awt$Font(), "Monospaced", $getCallSiteArray[2].callGetProperty($get$$class$java$awt$Font()), $getCallSiteArray[3].call(this.prefs.get(), "fontSize", BasicContentPane$_run_closure3.$const$0)), "border", $getCallSiteArray[4].callCurrent(this, BasicContentPane$_run_closure3.$const$1) }), $getCallSiteArray[5].callGroovyObjectGetProperty(this), new BasicContentPane$_run_closure3_closure7(this, this.getThisObject()));
        return $getCallSiteArray[6].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "outputArea" }), $getCallSiteArray[7].callGroovyObjectGetProperty(this), new BasicContentPane$_run_closure3_closure8(this, this.getThisObject()));
    }
    
    public Object getPrefs() {
        $getCallSiteArray();
        return this.prefs.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[8].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 4;
        $const$0 = 12;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure3.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = BasicContentPane$_run_closure3.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (BasicContentPane$_run_closure3.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicContentPane$_run_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicContentPane$_run_closure3.$class$java$lang$Object = class$("java.lang.Object"));
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
