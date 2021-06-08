// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ObjectBrowser$_run_closure1_closure3_closure7_closure9 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9;
    
    public ObjectBrowser$_run_closure1_closure3_closure7_closure9(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object callCurrent = $getCallSiteArray()[0].callCurrent(this, new ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17(this, this.getThisObject()));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9(), this, "itemTable");
        return callCurrent;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure7_closure9.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure7_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure7_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ObjectBrowser$_run_closure1_closure3_closure7_closure9.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ObjectBrowser$_run_closure1_closure3_closure7_closure9.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9() {
        Class $class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9;
        if (($class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9 = ObjectBrowser$_run_closure1_closure3_closure7_closure9.$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9) == null) {
            $class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9 = (ObjectBrowser$_run_closure1_closure3_closure7_closure9.$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9 = class$("groovy.inspect.swingui.ObjectBrowser$_run_closure1_closure3_closure7_closure9"));
        }
        return $class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9;
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
