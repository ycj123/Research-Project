// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure3_closure8_closure10_closure14 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public AstBrowser$_run_closure3_closure8_closure10_closure14(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        return $getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Class Form", "closure", ScriptBytecodeAdapter.getMethodPointer(this.getThisObject(), "showScriptClass"), "mnemonic", "C" }));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure8_closure10_closure14(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3_closure8_closure10_closure14.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3_closure8_closure10_closure14.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3_closure8_closure10_closure14.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3_closure8_closure10_closure14.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3_closure8_closure10_closure14.$class$java$lang$Object = class$("java.lang.Object"));
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
