// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Set;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_resolve_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> localDeps;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$grape$IvyGrabRecord;
    
    public GrapeIvy$_resolve_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> localDeps) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.localDeps = localDeps;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final IvyGrabRecord igr = (IvyGrabRecord)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callCurrent(this, ((Reference<Object>)it2).get()), $get$$class$groovy$grape$IvyGrabRecord());
        $getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), igr);
        return $getCallSiteArray[3].call(this.localDeps.get(), igr);
    }
    
    public Set<IvyGrabRecord> getLocalDeps() {
        $getCallSiteArray();
        return (Set<IvyGrabRecord>)ScriptBytecodeAdapter.castToType(this.localDeps.get(), $get$$class$java$util$Set());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_resolve_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_resolve_closure8.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_resolve_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_resolve_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = GrapeIvy$_resolve_closure8.$class$java$util$Set) == null) {
            $class$java$util$Set = (GrapeIvy$_resolve_closure8.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GrapeIvy$_resolve_closure8.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GrapeIvy$_resolve_closure8.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$IvyGrabRecord() {
        Class $class$groovy$grape$IvyGrabRecord;
        if (($class$groovy$grape$IvyGrabRecord = GrapeIvy$_resolve_closure8.$class$groovy$grape$IvyGrabRecord) == null) {
            $class$groovy$grape$IvyGrabRecord = (GrapeIvy$_resolve_closure8.$class$groovy$grape$IvyGrabRecord = class$("groovy.grape.IvyGrabRecord"));
        }
        return $class$groovy$grape$IvyGrabRecord;
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
