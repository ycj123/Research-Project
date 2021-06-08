// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.binding.PropertyBinding;
import groovy.lang.Reference;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.SourceBinding;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.binding.TriggerBinding;

public class JListProperties$7 implements TriggerBinding, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$PropertyBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListElementsBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$7;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$FullBinding;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
        final SourceBinding source2 = (SourceBinding)new Reference(source);
        final TargetBinding target2 = (TargetBinding)new Reference(target);
        return (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$groovy$swing$binding$JListElementsBinding(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(((Reference<Object>)source2).get(), $get$$class$org$codehaus$groovy$binding$PropertyBinding()), $get$$class$org$codehaus$groovy$binding$PropertyBinding()), ((Reference<Object>)target2).get()), $get$$class$org$codehaus$groovy$binding$FullBinding());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListProperties$7()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListProperties$7.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListProperties$7.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListProperties$7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListProperties$7.$callSiteArray == null || ($createCallSiteArray = JListProperties$7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListProperties$7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListProperties$7.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListProperties$7.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$PropertyBinding() {
        Class $class$org$codehaus$groovy$binding$PropertyBinding;
        if (($class$org$codehaus$groovy$binding$PropertyBinding = JListProperties$7.$class$org$codehaus$groovy$binding$PropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$PropertyBinding = (JListProperties$7.$class$org$codehaus$groovy$binding$PropertyBinding = class$("org.codehaus.groovy.binding.PropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$PropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListElementsBinding() {
        Class $class$groovy$swing$binding$JListElementsBinding;
        if (($class$groovy$swing$binding$JListElementsBinding = JListProperties$7.$class$groovy$swing$binding$JListElementsBinding) == null) {
            $class$groovy$swing$binding$JListElementsBinding = (JListProperties$7.$class$groovy$swing$binding$JListElementsBinding = class$("groovy.swing.binding.JListElementsBinding"));
        }
        return $class$groovy$swing$binding$JListElementsBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$7() {
        Class $class$groovy$swing$binding$JListProperties$7;
        if (($class$groovy$swing$binding$JListProperties$7 = JListProperties$7.$class$groovy$swing$binding$JListProperties$7) == null) {
            $class$groovy$swing$binding$JListProperties$7 = (JListProperties$7.$class$groovy$swing$binding$JListProperties$7 = class$("groovy.swing.binding.JListProperties$7"));
        }
        return $class$groovy$swing$binding$JListProperties$7;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$FullBinding() {
        Class $class$org$codehaus$groovy$binding$FullBinding;
        if (($class$org$codehaus$groovy$binding$FullBinding = JListProperties$7.$class$org$codehaus$groovy$binding$FullBinding) == null) {
            $class$org$codehaus$groovy$binding$FullBinding = (JListProperties$7.$class$org$codehaus$groovy$binding$FullBinding = class$("org.codehaus.groovy.binding.FullBinding"));
        }
        return $class$org$codehaus$groovy$binding$FullBinding;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JListProperties$7.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JListProperties$7.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
