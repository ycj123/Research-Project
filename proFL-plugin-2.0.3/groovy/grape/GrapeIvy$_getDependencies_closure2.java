// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_getDependencies_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> dd;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
    
    public GrapeIvy$_getDependencies_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> dd) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.dd = dd;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].call(this.dd.get(), "default", ((Reference<Object>)it2).get());
    }
    
    public DefaultDependencyDescriptor getDd() {
        $getCallSiteArray();
        return (DefaultDependencyDescriptor)ScriptBytecodeAdapter.castToType(this.dd.get(), $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_getDependencies_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_getDependencies_closure2.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_getDependencies_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_getDependencies_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GrapeIvy$_getDependencies_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GrapeIvy$_getDependencies_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = GrapeIvy$_getDependencies_closure2.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = (GrapeIvy$_getDependencies_closure2.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = class$("org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
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
