// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure3_closure8_closure11_closure17 extends Closure implements GeneratedClosure
{
    private Reference<Object> phasePicker;
    private Reference<Object> script;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$KeyStroke;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$event$KeyEvent;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstBrowser$_run_closure3_closure8_closure11_closure17(final Object _outerInstance, final Object _thisObject, final Reference<Object> phasePicker, final Reference<Object> script) {
        final Reference phasePicker2 = new Reference((T)phasePicker);
        final Reference script2 = new Reference((T)script);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phasePicker = phasePicker2.get();
        this.script = script2.get();
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object[] values = new Object[8];
        values[0] = "name";
        values[1] = "Refresh";
        values[2] = "closure";
        final int n = 3;
        final Object thisObject = this.getThisObject();
        final Reference<Object> phasePicker2;
        final Reference phasePicker = phasePicker2 = this.phasePicker;
        final Reference script = this.script;
        values[n] = new AstBrowser$_run_closure3_closure8_closure11_closure17_closure18(this, thisObject, phasePicker2, script);
        values[4] = "mnemonic";
        values[5] = "R";
        values[6] = "accelerator";
        values[7] = $getCallSiteArray[1].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[2].callGetProperty($get$$class$java$awt$event$KeyEvent()), AstBrowser$_run_closure3_closure8_closure11_closure17.$const$0);
        return callSite.callCurrent(this, ScriptBytecodeAdapter.createMap(values));
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
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure8_closure11_closure17(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3_closure8_closure11_closure17.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3_closure8_closure11_closure17.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3_closure8_closure11_closure17.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$KeyStroke() {
        Class $class$javax$swing$KeyStroke;
        if (($class$javax$swing$KeyStroke = AstBrowser$_run_closure3_closure8_closure11_closure17.$class$javax$swing$KeyStroke) == null) {
            $class$javax$swing$KeyStroke = (AstBrowser$_run_closure3_closure8_closure11_closure17.$class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke"));
        }
        return $class$javax$swing$KeyStroke;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3_closure8_closure11_closure17.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3_closure8_closure11_closure17.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$event$KeyEvent() {
        Class $class$java$awt$event$KeyEvent;
        if (($class$java$awt$event$KeyEvent = AstBrowser$_run_closure3_closure8_closure11_closure17.$class$java$awt$event$KeyEvent) == null) {
            $class$java$awt$event$KeyEvent = (AstBrowser$_run_closure3_closure8_closure11_closure17.$class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent"));
        }
        return $class$java$awt$event$KeyEvent;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstBrowser$_run_closure3_closure8_closure11_closure17.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstBrowser$_run_closure3_closure8_closure11_closure17.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
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
