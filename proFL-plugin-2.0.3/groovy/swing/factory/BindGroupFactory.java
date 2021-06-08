// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.binding.AggregateBinding;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class BindGroupFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204395;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindGroupFactory;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$AggregateBinding;
    
    public BindGroupFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final AggregateBinding bindGroup = (AggregateBinding)$getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$binding$AggregateBinding());
        final Object bind = $getCallSiteArray[1].call(attributes, "bind");
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(bind, null) && !DefaultTypeTransformation.booleanUnbox(bind)) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[2].call(bindGroup);
        }
        return ScriptBytecodeAdapter.castToType(bindGroup, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BindGroupFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BindGroupFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BindGroupFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BindGroupFactory.__timeStamp__239_neverHappen1292524204395 = 0L;
        BindGroupFactory.__timeStamp = 1292524204395L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BindGroupFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BindGroupFactory.$callSiteArray == null || ($createCallSiteArray = BindGroupFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BindGroupFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BindGroupFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BindGroupFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BindGroupFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BindGroupFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindGroupFactory() {
        Class $class$groovy$swing$factory$BindGroupFactory;
        if (($class$groovy$swing$factory$BindGroupFactory = BindGroupFactory.$class$groovy$swing$factory$BindGroupFactory) == null) {
            $class$groovy$swing$factory$BindGroupFactory = (BindGroupFactory.$class$groovy$swing$factory$BindGroupFactory = class$("groovy.swing.factory.BindGroupFactory"));
        }
        return $class$groovy$swing$factory$BindGroupFactory;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$AggregateBinding() {
        Class $class$org$codehaus$groovy$binding$AggregateBinding;
        if (($class$org$codehaus$groovy$binding$AggregateBinding = BindGroupFactory.$class$org$codehaus$groovy$binding$AggregateBinding) == null) {
            $class$org$codehaus$groovy$binding$AggregateBinding = (BindGroupFactory.$class$org$codehaus$groovy$binding$AggregateBinding = class$("org.codehaus.groovy.binding.AggregateBinding"));
        }
        return $class$org$codehaus$groovy$binding$AggregateBinding;
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
