// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class LayoutFactory$_addLayoutProperties_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> layoutClass;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1;
    
    public LayoutFactory$_addLayoutProperties_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> layoutClass) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.layoutClass = layoutClass;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new Reference($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()));
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[1].call(((Reference<Object>)name).get()), ((Reference<Object>)name).get())) {
            return $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), ((Reference<Object>)name).get(), ScriptBytecodeAdapter.getProperty($get$$class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1(), this.layoutClass.get(), (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { ((Reference<Object>)name).get() }, new String[] { "", "" }), $get$$class$java$lang$String())));
        }
        return null;
    }
    
    public Class getLayoutClass() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.layoutClass.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LayoutFactory$_addLayoutProperties_closure1.$callSiteArray == null || ($createCallSiteArray = LayoutFactory$_addLayoutProperties_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LayoutFactory$_addLayoutProperties_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (LayoutFactory$_addLayoutProperties_closure1.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1() {
        Class $class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1;
        if (($class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1 = LayoutFactory$_addLayoutProperties_closure1.$class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1) == null) {
            $class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1 = (LayoutFactory$_addLayoutProperties_closure1.$class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1 = class$("groovy.swing.factory.LayoutFactory$_addLayoutProperties_closure1"));
        }
        return $class$groovy$swing$factory$LayoutFactory$_addLayoutProperties_closure1;
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
