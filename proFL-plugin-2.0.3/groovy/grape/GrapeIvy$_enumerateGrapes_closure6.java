// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.regex.Pattern;
import java.util.List;
import groovy.lang.GroovyObject;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.io.File;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_enumerateGrapes_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> bunches;
    private Reference<Object> ivyFilePattern;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public GrapeIvy$_enumerateGrapes_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> bunches, final Reference<Object> ivyFilePattern) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.bunches = bunches;
        this.ivyFilePattern = ivyFilePattern;
    }
    
    public Object doCall(final File groupDir) {
        final File groupDir2 = (File)new Reference(groupDir);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Map grapes = (Map)new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        $getCallSiteArray[0].call(this.bunches.get(), $getCallSiteArray[1].callGetProperty(((Reference<Object>)groupDir2).get()), ((Reference)grapes).get());
        final CallSite callSite = $getCallSiteArray[2];
        final Object value = ((Reference<Object>)groupDir2).get();
        final Object thisObject = this.getThisObject();
        final Map grapes2 = grapes;
        final Reference ivyFilePattern = this.ivyFilePattern;
        return callSite.call(value, new GrapeIvy$_enumerateGrapes_closure6_closure14(this, thisObject, (Reference<Object>)grapes2, ivyFilePattern));
    }
    
    public Object call(final File groupDir) {
        final File groupDir2 = (File)new Reference(groupDir);
        return $getCallSiteArray()[3].callCurrent(this, ((Reference<Object>)groupDir2).get());
    }
    
    public Map<String, Map<String, List<String>>> getBunches() {
        $getCallSiteArray();
        return (Map<String, Map<String, List<String>>>)ScriptBytecodeAdapter.castToType(this.bunches.get(), $get$$class$java$util$Map());
    }
    
    public Pattern getIvyFilePattern() {
        $getCallSiteArray();
        return (Pattern)ScriptBytecodeAdapter.castToType(this.ivyFilePattern.get(), $get$$class$java$util$regex$Pattern());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_enumerateGrapes_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_enumerateGrapes_closure6.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_enumerateGrapes_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_enumerateGrapes_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = GrapeIvy$_enumerateGrapes_closure6.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (GrapeIvy$_enumerateGrapes_closure6.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = GrapeIvy$_enumerateGrapes_closure6.$class$java$util$Map) == null) {
            $class$java$util$Map = (GrapeIvy$_enumerateGrapes_closure6.$class$java$util$Map = class$("java.util.Map"));
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
