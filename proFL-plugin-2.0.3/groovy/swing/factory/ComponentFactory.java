// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Map;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.MissingPropertyException;
import java.awt.Window;
import java.awt.Component;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class ComponentFactory extends BeanFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204970;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComponentFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    
    public ComponentFactory(final Class beanClass) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[] { null }));
        arguments[0] = beanClass;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$BeanFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Class)array[0]);
                break;
            }
            case 1: {
                super((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public ComponentFactory(final Class beanClass, final boolean leaf) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = beanClass;
        arguments[1] = DefaultTypeTransformation.box(leaf);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$BeanFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Class)array[0]);
                break;
            }
            case 1: {
                super((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        try {
            final Object constraints = $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(builder));
            if (ScriptBytecodeAdapter.compareNotEqual(constraints, null)) {
                $getCallSiteArray[2].call($getCallSiteArray[3].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child, constraints);
                $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(builder), "constraints");
            }
            else {
                $getCallSiteArray[6].call($getCallSiteArray[7].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child);
            }
        }
        catch (MissingPropertyException mpe) {
            $getCallSiteArray[8].call($getCallSiteArray[9].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ComponentFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ComponentFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ComponentFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ComponentFactory.__timeStamp__239_neverHappen1292524204970 = 0L;
        ComponentFactory.__timeStamp = 1292524204970L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ComponentFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ComponentFactory.$callSiteArray == null || ($createCallSiteArray = ComponentFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ComponentFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = ComponentFactory.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (ComponentFactory.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ComponentFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ComponentFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComponentFactory() {
        Class $class$groovy$swing$factory$ComponentFactory;
        if (($class$groovy$swing$factory$ComponentFactory = ComponentFactory.$class$groovy$swing$factory$ComponentFactory) == null) {
            $class$groovy$swing$factory$ComponentFactory = (ComponentFactory.$class$groovy$swing$factory$ComponentFactory = class$("groovy.swing.factory.ComponentFactory"));
        }
        return $class$groovy$swing$factory$ComponentFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = ComponentFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (ComponentFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
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
