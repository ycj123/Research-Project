// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class CompoundBorderFactory$_newInstance_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$border$CompoundBorder;
    
    public CompoundBorderFactory$_newInstance_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object that, final Object it) {
        final Object that2 = new Reference(that);
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].callConstructor($get$$class$javax$swing$border$CompoundBorder(), ((Reference<Object>)that2).get(), ((Reference<Object>)it2).get());
    }
    
    public Object call(final Object that, final Object it) {
        final Object that2 = new Reference(that);
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)that2).get(), ((Reference<Object>)it2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$CompoundBorderFactory$_newInstance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CompoundBorderFactory$_newInstance_closure1.$callSiteArray == null || ($createCallSiteArray = CompoundBorderFactory$_newInstance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CompoundBorderFactory$_newInstance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$CompoundBorder() {
        Class $class$javax$swing$border$CompoundBorder;
        if (($class$javax$swing$border$CompoundBorder = CompoundBorderFactory$_newInstance_closure1.$class$javax$swing$border$CompoundBorder) == null) {
            $class$javax$swing$border$CompoundBorder = (CompoundBorderFactory$_newInstance_closure1.$class$javax$swing$border$CompoundBorder = class$("javax.swing.border.CompoundBorder"));
        }
        return $class$javax$swing$border$CompoundBorder;
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
