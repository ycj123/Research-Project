// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class EmptyBorderFactory$_newInstance_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> ints;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public EmptyBorderFactory$_newInstance_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> ints) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.ints = ints;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final Boolean value = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].call(this.ints.get(), (((Reference<Object>)it2).get() instanceof Integer) ? Boolean.TRUE : Boolean.FALSE), $get$$class$java$lang$Boolean());
        this.ints.set(value);
        return value;
    }
    
    public Boolean getInts() {
        $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType(this.ints.get(), $get$$class$java$lang$Boolean());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$EmptyBorderFactory$_newInstance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (EmptyBorderFactory$_newInstance_closure1.$callSiteArray == null || ($createCallSiteArray = EmptyBorderFactory$_newInstance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            EmptyBorderFactory$_newInstance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = EmptyBorderFactory$_newInstance_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (EmptyBorderFactory$_newInstance_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = EmptyBorderFactory$_newInstance_closure1.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (EmptyBorderFactory$_newInstance_closure1.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
