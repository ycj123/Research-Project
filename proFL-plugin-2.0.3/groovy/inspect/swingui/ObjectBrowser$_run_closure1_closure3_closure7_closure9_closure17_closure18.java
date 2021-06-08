// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18 extends Closure implements GeneratedClosure
{
    private Reference<Object> i;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    
    public ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18(final Object _outerInstance, final Object _thisObject, final Reference<Object> i) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.i = i;
    }
    
    public Object doCall(final Object key, final Object val) {
        final Object key2 = new Reference(key);
        final Object val2 = new Reference(val);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object[] values = new Object[3];
        final int n = 0;
        final Object value = this.i.get();
        this.i.set($getCallSiteArray[0].call(this.i.get()));
        values[n] = value;
        values[1] = ((Reference<Object>)key2).get();
        values[2] = ((Reference<Object>)val2).get();
        return ScriptBytecodeAdapter.createList(values);
    }
    
    public Object call(final Object key, final Object val) {
        final Object key2 = new Reference(key);
        final Object val2 = new Reference(val);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)val2).get());
    }
    
    public Integer getI() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.i.get(), $get$$class$java$lang$Integer());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (ObjectBrowser$_run_closure1_closure3_closure7_closure9_closure17_closure18.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
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
