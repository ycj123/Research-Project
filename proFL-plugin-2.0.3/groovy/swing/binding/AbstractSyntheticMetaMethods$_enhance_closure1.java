// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

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

class AbstractSyntheticMetaMethods$_enhance_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> mc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    
    public AbstractSyntheticMetaMethods$_enhance_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> mc) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.mc = mc;
    }
    
    public Object doCall(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].call(this.mc.get(), ((Reference<Object>)i).get()), null)) {
            return $getCallSiteArray[1].call(this.mc.get(), ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
        }
        return null;
    }
    
    public Object call(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public MetaClass getMc() {
        $getCallSiteArray();
        return (MetaClass)ScriptBytecodeAdapter.castToType(this.mc.get(), $get$$class$groovy$lang$MetaClass());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods$_enhance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractSyntheticMetaMethods$_enhance_closure1.$callSiteArray == null || ($createCallSiteArray = AbstractSyntheticMetaMethods$_enhance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractSyntheticMetaMethods$_enhance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AbstractSyntheticMetaMethods$_enhance_closure1.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AbstractSyntheticMetaMethods$_enhance_closure1.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
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
