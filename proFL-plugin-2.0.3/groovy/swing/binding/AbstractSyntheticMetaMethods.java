// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import groovy.lang.MetaClassRegistry;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class AbstractSyntheticMetaMethods implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203044;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$MetaClassRegistry;
    private static /* synthetic */ Class $class$groovy$lang$GroovySystem;
    private static /* synthetic */ Class $class$groovy$lang$ExpandoMetaClass;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AbstractSyntheticMetaMethods() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void enhance(final Object o, final Map enhancedMethods) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Class klass = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(o), $get$$class$java$lang$Class());
        final MetaClassRegistry mcr = (MetaClassRegistry)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGetProperty($get$$class$groovy$lang$GroovySystem()), $get$$class$groovy$lang$MetaClassRegistry());
        final MetaClass mc = (MetaClass)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(mcr, klass), $get$$class$groovy$lang$MetaClass()));
        Boolean init = Boolean.FALSE;
        $getCallSiteArray[3].call(mcr, klass);
        ((Reference<Object>)mc).set($getCallSiteArray[4].callConstructor($get$$class$groovy$lang$ExpandoMetaClass(), klass));
        init = Boolean.TRUE;
        $getCallSiteArray[5].call(enhancedMethods, new AbstractSyntheticMetaMethods$_enhance_closure1($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), (Reference<Object>)mc));
        if (DefaultTypeTransformation.booleanUnbox(init)) {
            $getCallSiteArray[6].call(((Reference<Object>)mc).get());
            $getCallSiteArray[7].call(mcr, klass, ((Reference<Object>)mc).get());
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AbstractSyntheticMetaMethods.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AbstractSyntheticMetaMethods.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AbstractSyntheticMetaMethods.__timeStamp__239_neverHappen1292524203044 = 0L;
        AbstractSyntheticMetaMethods.__timeStamp = 1292524203044L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractSyntheticMetaMethods.$callSiteArray == null || ($createCallSiteArray = AbstractSyntheticMetaMethods.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractSyntheticMetaMethods.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AbstractSyntheticMetaMethods.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AbstractSyntheticMetaMethods.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClassRegistry() {
        Class $class$groovy$lang$MetaClassRegistry;
        if (($class$groovy$lang$MetaClassRegistry = AbstractSyntheticMetaMethods.$class$groovy$lang$MetaClassRegistry) == null) {
            $class$groovy$lang$MetaClassRegistry = (AbstractSyntheticMetaMethods.$class$groovy$lang$MetaClassRegistry = class$("groovy.lang.MetaClassRegistry"));
        }
        return $class$groovy$lang$MetaClassRegistry;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovySystem() {
        Class $class$groovy$lang$GroovySystem;
        if (($class$groovy$lang$GroovySystem = AbstractSyntheticMetaMethods.$class$groovy$lang$GroovySystem) == null) {
            $class$groovy$lang$GroovySystem = (AbstractSyntheticMetaMethods.$class$groovy$lang$GroovySystem = class$("groovy.lang.GroovySystem"));
        }
        return $class$groovy$lang$GroovySystem;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$ExpandoMetaClass() {
        Class $class$groovy$lang$ExpandoMetaClass;
        if (($class$groovy$lang$ExpandoMetaClass = AbstractSyntheticMetaMethods.$class$groovy$lang$ExpandoMetaClass) == null) {
            $class$groovy$lang$ExpandoMetaClass = (AbstractSyntheticMetaMethods.$class$groovy$lang$ExpandoMetaClass = class$("groovy.lang.ExpandoMetaClass"));
        }
        return $class$groovy$lang$ExpandoMetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods() {
        Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
        if (($class$groovy$swing$binding$AbstractSyntheticMetaMethods = AbstractSyntheticMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods) == null) {
            $class$groovy$swing$binding$AbstractSyntheticMetaMethods = (AbstractSyntheticMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods = class$("groovy.swing.binding.AbstractSyntheticMetaMethods"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AbstractSyntheticMetaMethods.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AbstractSyntheticMetaMethods.$class$java$lang$Class = class$("java.lang.Class"));
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
