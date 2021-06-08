// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Map;
import java.util.List;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_listDependencies_closure10 extends Closure implements GeneratedClosure
{
    private Reference<Object> results;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$grape$GrapeIvy$_listDependencies_closure10;
    
    public GrapeIvy$_listDependencies_closure10(final Object _outerInstance, final Object _thisObject, final Reference<Object> results) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.results = results;
    }
    
    public Object doCall(final IvyGrabRecord grabbed) {
        final IvyGrabRecord grabbed2 = (IvyGrabRecord)new Reference(grabbed);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object dep = new Reference(ScriptBytecodeAdapter.createMap(new Object[] { "group", $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get())), "module", $getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get())), "version", $getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get())) }));
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[6].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), ScriptBytecodeAdapter.createList(new Object[] { "default" }))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[7].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "conf");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[9].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "changing");
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[11].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "transitive");
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[13].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "force");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[15].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "classifier");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[17].callGroovyObjectGetProperty(((Reference<Object>)grabbed2).get()), $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), ((Reference<Object>)dep).get(), "ext");
        }
        return $getCallSiteArray[18].call(this.results.get(), ((Reference<Object>)dep).get());
    }
    
    public Object call(final IvyGrabRecord grabbed) {
        final IvyGrabRecord grabbed2 = (IvyGrabRecord)new Reference(grabbed);
        return $getCallSiteArray()[19].callCurrent(this, ((Reference<Object>)grabbed2).get());
    }
    
    public List<Map> getResults() {
        $getCallSiteArray();
        return (List<Map>)ScriptBytecodeAdapter.castToType(this.results.get(), $get$$class$java$util$List());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[20];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_listDependencies_closure10.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_listDependencies_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_listDependencies_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = GrapeIvy$_listDependencies_closure10.$class$java$util$List) == null) {
            $class$java$util$List = (GrapeIvy$_listDependencies_closure10.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$GrapeIvy$_listDependencies_closure10() {
        Class $class$groovy$grape$GrapeIvy$_listDependencies_closure10;
        if (($class$groovy$grape$GrapeIvy$_listDependencies_closure10 = GrapeIvy$_listDependencies_closure10.$class$groovy$grape$GrapeIvy$_listDependencies_closure10) == null) {
            $class$groovy$grape$GrapeIvy$_listDependencies_closure10 = (GrapeIvy$_listDependencies_closure10.$class$groovy$grape$GrapeIvy$_listDependencies_closure10 = class$("groovy.grape.GrapeIvy$_listDependencies_closure10"));
        }
        return $class$groovy$grape$GrapeIvy$_listDependencies_closure10;
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
