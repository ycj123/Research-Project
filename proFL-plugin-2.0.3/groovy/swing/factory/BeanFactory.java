// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GString;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class BeanFactory extends AbstractFactory implements GroovyObject
{
    private final Class beanClass;
    protected final boolean leaf;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204381;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public BeanFactory(final Class beanClass) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = beanClass;
        arguments[1] = Boolean.FALSE;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$BeanFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Class)array[0]);
                break;
            }
            case 1: {
                this((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public BeanFactory(final Class beanClass, final boolean leaf) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.beanClass = (Class)ScriptBytecodeAdapter.castToType(beanClass, $get$$class$java$lang$Class());
        this.leaf = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(leaf));
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(this.leaf), $get$$class$java$lang$Boolean()));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (value instanceof GString) {
            value = ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, this.beanClass))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        final Object bean = $getCallSiteArray[1].call(this.beanClass);
        if (value instanceof String) {
            try {
                ScriptBytecodeAdapter.setProperty(value, $get$$class$groovy$swing$factory$BeanFactory(), bean, "text");
            }
            catch (MissingPropertyException mpe) {
                throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " value argument of type String cannot be applied to property text:" }));
            }
        }
        return ScriptBytecodeAdapter.castToType(bean, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BeanFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BeanFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BeanFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BeanFactory.__timeStamp__239_neverHappen1292524204381 = 0L;
        BeanFactory.__timeStamp = 1292524204381L;
    }
    
    public final Class getBeanClass() {
        return this.beanClass;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BeanFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BeanFactory.$callSiteArray == null || ($createCallSiteArray = BeanFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BeanFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BeanFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BeanFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = BeanFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (BeanFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BeanFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BeanFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = BeanFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (BeanFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = BeanFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (BeanFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = BeanFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (BeanFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = BeanFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (BeanFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = BeanFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (BeanFactory.$class$java$lang$Class = class$("java.lang.Class"));
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
