// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Set;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_closure1_closure11 extends Closure implements GeneratedClosure
{
    private Reference<Object> g;
    private Reference<Object> m;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Set;
    
    public GrapeIvy$_closure1_closure11(final Object _outerInstance, final Object _thisObject, final Reference<Object> g, final Reference<Object> m) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.g = g;
        this.m = m;
    }
    
    public Object doCall(final Object a) {
        final Object a2 = new Reference(a);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object value = this.m.get();
        final Object value2 = ((Reference<Object>)a2).get();
        final Set set = (Set)ScriptBytecodeAdapter.asType($getCallSiteArray[1].call(this.g.get(), ((Reference<Object>)a2).get()), $get$$class$java$util$Set());
        callSite.call(value, value2, set);
        return set;
    }
    
    public Object getG() {
        $getCallSiteArray();
        return this.g.get();
    }
    
    public Object getM() {
        $getCallSiteArray();
        return this.m.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_closure1_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_closure1_closure11.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_closure1_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_closure1_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = GrapeIvy$_closure1_closure11.$class$java$util$Set) == null) {
            $class$java$util$Set = (GrapeIvy$_closure1_closure11.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
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
