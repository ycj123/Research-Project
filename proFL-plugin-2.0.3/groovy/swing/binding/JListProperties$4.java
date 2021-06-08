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

public class JListProperties$4 implements TriggerBinding, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$PropertyBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$4;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListSelectedElementBinding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$FullBinding;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
        final SourceBinding source2 = (SourceBinding)new Reference(source);
        final TargetBinding target2 = (TargetBinding)new Reference(target);
        return (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$groovy$swing$binding$JListSelectedElementBinding(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(((Reference<Object>)source2).get(), $get$$class$org$codehaus$groovy$binding$PropertyBinding()), $get$$class$org$codehaus$groovy$binding$PropertyBinding()), ((Reference<Object>)target2).get(), "selectedElements"), $get$$class$org$codehaus$groovy$binding$FullBinding());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListProperties$4()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListProperties$4.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListProperties$4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListProperties$4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListProperties$4.$callSiteArray == null || ($createCallSiteArray = JListProperties$4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListProperties$4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListProperties$4.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListProperties$4.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$PropertyBinding() {
        Class $class$org$codehaus$groovy$binding$PropertyBinding;
        if (($class$org$codehaus$groovy$binding$PropertyBinding = JListProperties$4.$class$org$codehaus$groovy$binding$PropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$PropertyBinding = (JListProperties$4.$class$org$codehaus$groovy$binding$PropertyBinding = class$("org.codehaus.groovy.binding.PropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$PropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$4() {
        Class $class$groovy$swing$binding$JListProperties$4;
        if (($class$groovy$swing$binding$JListProperties$4 = JListProperties$4.$class$groovy$swing$binding$JListProperties$4) == null) {
            $class$groovy$swing$binding$JListProperties$4 = (JListProperties$4.$class$groovy$swing$binding$JListProperties$4 = class$("groovy.swing.binding.JListProperties$4"));
        }
        return $class$groovy$swing$binding$JListProperties$4;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListSelectedElementBinding() {
        Class $class$groovy$swing$binding$JListSelectedElementBinding;
        if (($class$groovy$swing$binding$JListSelectedElementBinding = JListProperties$4.$class$groovy$swing$binding$JListSelectedElementBinding) == null) {
            $class$groovy$swing$binding$JListSelectedElementBinding = (JListProperties$4.$class$groovy$swing$binding$JListSelectedElementBinding = class$("groovy.swing.binding.JListSelectedElementBinding"));
        }
        return $class$groovy$swing$binding$JListSelectedElementBinding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$FullBinding() {
        Class $class$org$codehaus$groovy$binding$FullBinding;
        if (($class$org$codehaus$groovy$binding$FullBinding = JListProperties$4.$class$org$codehaus$groovy$binding$FullBinding) == null) {
            $class$org$codehaus$groovy$binding$FullBinding = (JListProperties$4.$class$org$codehaus$groovy$binding$FullBinding = class$("org.codehaus.groovy.binding.FullBinding"));
        }
        return $class$org$codehaus$groovy$binding$FullBinding;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JListProperties$4.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JListProperties$4.$class$java$lang$Class = class$("java.lang.Class"));
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
