// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Map;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class SwingBuilder$_edt_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> c;
    private Reference<Object> continuationData;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public SwingBuilder$_edt_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> c, final Reference<Object> continuationData) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.c = c;
        this.continuationData = continuationData;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, this.continuationData.get());
        $getCallSiteArray[1].call(this.c.get());
        final Map value = (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callCurrent(this), $get$$class$java$util$Map());
        this.continuationData.set(value);
        return value;
    }
    
    public Closure getC() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(this.c.get(), $get$$class$groovy$lang$Closure());
    }
    
    public Map<String, Object> getContinuationData() {
        $getCallSiteArray();
        return (Map<String, Object>)ScriptBytecodeAdapter.castToType(this.continuationData.get(), $get$$class$java$util$Map());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$SwingBuilder$_edt_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingBuilder$_edt_closure1.$callSiteArray == null || ($createCallSiteArray = SwingBuilder$_edt_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingBuilder$_edt_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SwingBuilder$_edt_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SwingBuilder$_edt_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = SwingBuilder$_edt_closure1.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (SwingBuilder$_edt_closure1.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = SwingBuilder$_edt_closure1.$class$java$util$Map) == null) {
            $class$java$util$Map = (SwingBuilder$_edt_closure1.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
