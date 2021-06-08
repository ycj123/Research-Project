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

class AstBrowser$_run_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> phasePicker;
    private Reference<Object> splitterPane;
    private Reference<Object> mainSplitter;
    private Reference<Object> script;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstBrowser$_run_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> phasePicker, final Reference<Object> splitterPane, final Reference<Object> mainSplitter, final Reference<Object> script) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phasePicker = phasePicker;
        this.splitterPane = splitterPane;
        this.mainSplitter = mainSplitter;
        this.script = script;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object thisObject = this.getThisObject();
        final Reference<Object> phasePicker2;
        final Reference phasePicker = phasePicker2 = this.phasePicker;
        final Reference script = this.script;
        callSite.callCurrent(this, new AstBrowser$_run_closure3_closure8(this, thisObject, phasePicker2, script));
        final CallSite callSite2 = $getCallSiteArray[1];
        final Object thisObject2 = this.getThisObject();
        final Reference phasePicker3 = phasePicker;
        final Reference<Object> splitterPane2;
        final Reference splitterPane = splitterPane2 = this.splitterPane;
        final Reference mainSplitter = this.mainSplitter;
        return callSite2.callCurrent(this, new AstBrowser$_run_closure3_closure9(this, thisObject2, phasePicker3, splitterPane2, mainSplitter, script));
    }
    
    public Object getPhasePicker() {
        $getCallSiteArray();
        return this.phasePicker.get();
    }
    
    public Object getSplitterPane() {
        $getCallSiteArray();
        return this.splitterPane.get();
    }
    
    public Object getMainSplitter() {
        $getCallSiteArray();
        return this.mainSplitter.get();
    }
    
    public Closure getScript() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(this.script.get(), $get$$class$groovy$lang$Closure());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstBrowser$_run_closure3.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstBrowser$_run_closure3.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
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
