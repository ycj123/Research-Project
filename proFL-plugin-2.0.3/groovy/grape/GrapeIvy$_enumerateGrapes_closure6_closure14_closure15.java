// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.regex.Pattern;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.io.File;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_enumerateGrapes_closure6_closure14_closure15 extends Closure implements GeneratedClosure
{
    private Reference<Object> versions;
    private Reference<Object> ivyFilePattern;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    
    public GrapeIvy$_enumerateGrapes_closure6_closure14_closure15(final Object _outerInstance, final Object _thisObject, final Reference<Object> versions, final Reference<Object> ivyFilePattern) {
        final Reference ivyFilePattern2 = new Reference((T)ivyFilePattern);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.versions = versions;
        this.ivyFilePattern = ivyFilePattern2.get();
    }
    
    public Object doCall(final File ivyFile) {
        final File ivyFile2 = (File)new Reference(ivyFile);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object m = $getCallSiteArray[0].call(this.ivyFilePattern.get(), $getCallSiteArray[1].callGetProperty(((Reference<Object>)ivyFile2).get()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(m))) {
            final Object call = $getCallSiteArray[3].call(this.versions.get(), $getCallSiteArray[4].call(m, GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$const$0));
            this.versions.set(call);
            return call;
        }
        return null;
    }
    
    public Object call(final File ivyFile) {
        final File ivyFile2 = (File)new Reference(ivyFile);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)ivyFile2).get());
    }
    
    public Object getVersions() {
        $getCallSiteArray();
        return this.versions.get();
    }
    
    public Pattern getIvyFilePattern() {
        $getCallSiteArray();
        return (Pattern)ScriptBytecodeAdapter.castToType(this.ivyFilePattern.get(), $get$$class$java$util$regex$Pattern());
    }
    
    static {
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_enumerateGrapes_closure6_closure14_closure15(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (GrapeIvy$_enumerateGrapes_closure6_closure14_closure15.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
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
