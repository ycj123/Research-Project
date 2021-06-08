// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_getDependencies_closure4_closure13 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$System;
    
    public GrapeIvy$_getDependencies_closure4_closure13(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new Reference($getCallSiteArray[0].call(((Reference<Object>)it2).get()));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), ((Reference<Object>)name).get()))) {
            $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), ((Reference<Object>)name).get());
            return $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty($get$$class$java$lang$System()), $getCallSiteArray[7].call("Preparing to download artifact ", ((Reference<Object>)name).get()));
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_getDependencies_closure4_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_getDependencies_closure4_closure13.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_getDependencies_closure4_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_getDependencies_closure4_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = GrapeIvy$_getDependencies_closure4_closure13.$class$java$lang$System) == null) {
            $class$java$lang$System = (GrapeIvy$_getDependencies_closure4_closure13.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
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
