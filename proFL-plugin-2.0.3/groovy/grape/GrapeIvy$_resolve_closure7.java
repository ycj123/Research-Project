// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Set;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_resolve_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> keys;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    
    public GrapeIvy$_resolve_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> keys) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.keys = keys;
    }
    
    public Object doCall(final Object a) {
        final Object a2 = new Reference(a);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Set badArgs = (Set)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)a2).get()), $get$$class$java$util$Set()));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference)badArgs).get()) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(((Reference)badArgs).get(), this.keys.get()))) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { $getCallSiteArray[4].call($getCallSiteArray[5].call(this.keys.get(), ((Reference)badArgs).get()), ((Reference<Object>)a2).get()) }, new String[] { "Mutually exclusive arguments passed into grab: ", "" }));
        }
        return null;
    }
    
    public Set getKeys() {
        $getCallSiteArray();
        return (Set)ScriptBytecodeAdapter.castToType(this.keys.get(), $get$$class$java$util$Set());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_resolve_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_resolve_closure7.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_resolve_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_resolve_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = GrapeIvy$_resolve_closure7.$class$java$util$Set) == null) {
            $class$java$util$Set = (GrapeIvy$_resolve_closure7.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = GrapeIvy$_resolve_closure7.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (GrapeIvy$_resolve_closure7.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
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
