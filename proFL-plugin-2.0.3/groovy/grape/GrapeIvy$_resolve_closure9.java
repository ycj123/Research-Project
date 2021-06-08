// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_resolve_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> depsInfo;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    
    public GrapeIvy$_resolve_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> depsInfo) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.depsInfo = depsInfo;
    }
    
    public Object doCall(final Object depNode) {
        final Object depNode2 = new Reference(depNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object id = $getCallSiteArray[0].callGetProperty(((Reference<Object>)depNode2).get());
        return $getCallSiteArray[1].call(this.depsInfo.get(), ScriptBytecodeAdapter.createMap(new Object[] { "group", $getCallSiteArray[2].callGetProperty(id), "module", $getCallSiteArray[3].callGetProperty(id), "revision", $getCallSiteArray[4].callGetProperty(id) }));
    }
    
    public List getDepsInfo() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.depsInfo.get(), $get$$class$java$util$List());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_resolve_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_resolve_closure9.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_resolve_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_resolve_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = GrapeIvy$_resolve_closure9.$class$java$util$List) == null) {
            $class$java$util$List = (GrapeIvy$_resolve_closure9.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
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
