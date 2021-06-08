// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.io.File;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_enumerateGrapes_closure6_closure14 extends Closure implements GeneratedClosure
{
    private Reference<Object> grapes;
    private Reference<Object> ivyFilePattern;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public GrapeIvy$_enumerateGrapes_closure6_closure14(final Object _outerInstance, final Object _thisObject, final Reference<Object> grapes, final Reference<Object> ivyFilePattern) {
        final Reference ivyFilePattern2 = new Reference((T)ivyFilePattern);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.grapes = grapes;
        this.ivyFilePattern = ivyFilePattern2.get();
    }
    
    public Object doCall(final File moduleDir) {
        final File moduleDir2 = (File)new Reference(moduleDir);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object versions = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final CallSite callSite = $getCallSiteArray[0];
        final Object value = ((Reference<Object>)moduleDir2).get();
        final Object value2 = this.ivyFilePattern.get();
        final Object thisObject = this.getThisObject();
        final Object versions2 = versions;
        final Reference ivyFilePattern = this.ivyFilePattern;
        callSite.call(value, value2, new GrapeIvy$_enumerateGrapes_closure6_closure14_closure15(this, thisObject, (Reference<Object>)versions2, ivyFilePattern));
        final CallSite callSite2 = $getCallSiteArray[1];
        final Object value3 = this.grapes.get();
        final Object callGetProperty = $getCallSiteArray[2].callGetProperty(((Reference<Object>)moduleDir2).get());
        final Object value4 = ((Reference<Object>)versions).get();
        callSite2.call(value3, callGetProperty, value4);
        return value4;
    }
    
    public Object call(final File moduleDir) {
        final File moduleDir2 = (File)new Reference(moduleDir);
        return $getCallSiteArray()[3].callCurrent(this, ((Reference<Object>)moduleDir2).get());
    }
    
    public Map<String, List<String>> getGrapes() {
        $getCallSiteArray();
        return (Map<String, List<String>>)ScriptBytecodeAdapter.castToType(this.grapes.get(), $get$$class$java$util$Map());
    }
    
    public Pattern getIvyFilePattern() {
        $getCallSiteArray();
        return (Pattern)ScriptBytecodeAdapter.castToType(this.ivyFilePattern.get(), $get$$class$java$util$regex$Pattern());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_enumerateGrapes_closure6_closure14(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_enumerateGrapes_closure6_closure14.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_enumerateGrapes_closure6_closure14.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_enumerateGrapes_closure6_closure14.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = GrapeIvy$_enumerateGrapes_closure6_closure14.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (GrapeIvy$_enumerateGrapes_closure6_closure14.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = GrapeIvy$_enumerateGrapes_closure6_closure14.$class$java$util$Map) == null) {
            $class$java$util$Map = (GrapeIvy$_enumerateGrapes_closure6_closure14.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
