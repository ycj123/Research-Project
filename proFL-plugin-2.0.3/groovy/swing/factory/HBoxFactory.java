// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class HBoxFactory extends ComponentFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205666;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComponentFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$HBoxFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public HBoxFactory() {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[] { null }));
        arguments[0] = null;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$ComponentFactory());
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
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call($get$$class$javax$swing$Box()), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$HBoxFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HBoxFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HBoxFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HBoxFactory.__timeStamp__239_neverHappen1292524205666 = 0L;
        HBoxFactory.__timeStamp = 1292524205666L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$HBoxFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HBoxFactory.$callSiteArray == null || ($createCallSiteArray = HBoxFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HBoxFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HBoxFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HBoxFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = HBoxFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (HBoxFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HBoxFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HBoxFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComponentFactory() {
        Class $class$groovy$swing$factory$ComponentFactory;
        if (($class$groovy$swing$factory$ComponentFactory = HBoxFactory.$class$groovy$swing$factory$ComponentFactory) == null) {
            $class$groovy$swing$factory$ComponentFactory = (HBoxFactory.$class$groovy$swing$factory$ComponentFactory = class$("groovy.swing.factory.ComponentFactory"));
        }
        return $class$groovy$swing$factory$ComponentFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$HBoxFactory() {
        Class $class$groovy$swing$factory$HBoxFactory;
        if (($class$groovy$swing$factory$HBoxFactory = HBoxFactory.$class$groovy$swing$factory$HBoxFactory) == null) {
            $class$groovy$swing$factory$HBoxFactory = (HBoxFactory.$class$groovy$swing$factory$HBoxFactory = class$("groovy.swing.factory.HBoxFactory"));
        }
        return $class$groovy$swing$factory$HBoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = HBoxFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (HBoxFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
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
