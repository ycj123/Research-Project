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
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ObjectBrowser$_run_closure1_closure3_closure6 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$FlowLayout;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ObjectBrowser$_run_closure1_closure3_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "alignment", $getCallSiteArray[1].callGetProperty($get$$class$java$awt$FlowLayout()) }));
        final Object props = $getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this));
        final Object classLabel = $getCallSiteArray[4].call("<html>", $getCallSiteArray[5].call(props, "<br>"));
        return $getCallSiteArray[6].callCurrent(this, classLabel);
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure6.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$FlowLayout() {
        Class $class$java$awt$FlowLayout;
        if (($class$java$awt$FlowLayout = ObjectBrowser$_run_closure1_closure3_closure6.$class$java$awt$FlowLayout) == null) {
            $class$java$awt$FlowLayout = (ObjectBrowser$_run_closure1_closure3_closure6.$class$java$awt$FlowLayout = class$("java.awt.FlowLayout"));
        }
        return $class$java$awt$FlowLayout;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ObjectBrowser$_run_closure1_closure3_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ObjectBrowser$_run_closure1_closure3_closure6.$class$java$lang$Object = class$("java.lang.Object"));
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
