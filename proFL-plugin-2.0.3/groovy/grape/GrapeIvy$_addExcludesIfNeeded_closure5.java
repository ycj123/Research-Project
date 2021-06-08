// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_addExcludesIfNeeded_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> md;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
    private static /* synthetic */ Class $class$org$apache$ivy$plugins$matcher$PatternMatcher;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$id$ArtifactId;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$id$ModuleId;
    private static /* synthetic */ Class $class$org$apache$ivy$plugins$matcher$ExactPatternMatcher;
    
    public GrapeIvy$_addExcludesIfNeeded_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> md) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.md = md;
    }
    
    public Object doCall(final Object map) {
        final Object map2 = new Reference(map);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object excludeRule = $getCallSiteArray[0].callConstructor($get$$class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule(), $getCallSiteArray[1].callConstructor($get$$class$org$apache$ivy$core$module$id$ArtifactId(), $getCallSiteArray[2].callConstructor($get$$class$org$apache$ivy$core$module$id$ModuleId(), $getCallSiteArray[3].callGetProperty(((Reference<Object>)map2).get()), $getCallSiteArray[4].callGetProperty(((Reference<Object>)map2).get())), $getCallSiteArray[5].callGetProperty($get$$class$org$apache$ivy$plugins$matcher$PatternMatcher()), $getCallSiteArray[6].callGetProperty($get$$class$org$apache$ivy$plugins$matcher$PatternMatcher()), $getCallSiteArray[7].callGetProperty($get$$class$org$apache$ivy$plugins$matcher$PatternMatcher())), $getCallSiteArray[8].callGetProperty($get$$class$org$apache$ivy$plugins$matcher$ExactPatternMatcher()), null);
        $getCallSiteArray[9].call(excludeRule, "default");
        return $getCallSiteArray[10].call(this.md.get(), excludeRule);
    }
    
    public DefaultModuleDescriptor getMd() {
        $getCallSiteArray();
        return (DefaultModuleDescriptor)ScriptBytecodeAdapter.castToType(this.md.get(), $get$$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_addExcludesIfNeeded_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_addExcludesIfNeeded_closure5.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_addExcludesIfNeeded_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_addExcludesIfNeeded_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = class$("org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$plugins$matcher$PatternMatcher() {
        Class $class$org$apache$ivy$plugins$matcher$PatternMatcher;
        if (($class$org$apache$ivy$plugins$matcher$PatternMatcher = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$plugins$matcher$PatternMatcher) == null) {
            $class$org$apache$ivy$plugins$matcher$PatternMatcher = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$plugins$matcher$PatternMatcher = class$("org.apache.ivy.plugins.matcher.PatternMatcher"));
        }
        return $class$org$apache$ivy$plugins$matcher$PatternMatcher;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$id$ArtifactId() {
        Class $class$org$apache$ivy$core$module$id$ArtifactId;
        if (($class$org$apache$ivy$core$module$id$ArtifactId = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$id$ArtifactId) == null) {
            $class$org$apache$ivy$core$module$id$ArtifactId = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$id$ArtifactId = class$("org.apache.ivy.core.module.id.ArtifactId"));
        }
        return $class$org$apache$ivy$core$module$id$ArtifactId;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule = class$("org.apache.ivy.core.module.descriptor.DefaultExcludeRule"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultExcludeRule;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$id$ModuleId() {
        Class $class$org$apache$ivy$core$module$id$ModuleId;
        if (($class$org$apache$ivy$core$module$id$ModuleId = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$id$ModuleId) == null) {
            $class$org$apache$ivy$core$module$id$ModuleId = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$core$module$id$ModuleId = class$("org.apache.ivy.core.module.id.ModuleId"));
        }
        return $class$org$apache$ivy$core$module$id$ModuleId;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$plugins$matcher$ExactPatternMatcher() {
        Class $class$org$apache$ivy$plugins$matcher$ExactPatternMatcher;
        if (($class$org$apache$ivy$plugins$matcher$ExactPatternMatcher = GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$plugins$matcher$ExactPatternMatcher) == null) {
            $class$org$apache$ivy$plugins$matcher$ExactPatternMatcher = (GrapeIvy$_addExcludesIfNeeded_closure5.$class$org$apache$ivy$plugins$matcher$ExactPatternMatcher = class$("org.apache.ivy.plugins.matcher.ExactPatternMatcher"));
        }
        return $class$org$apache$ivy$plugins$matcher$ExactPatternMatcher;
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
