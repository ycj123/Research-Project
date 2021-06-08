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

public class BoxFactory extends ComponentFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205664;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Number;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComponentFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$BoxFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$javax$swing$BoxLayout;
    
    public BoxFactory() {
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
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$Box()))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        Integer axis = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGetProperty($get$$class$javax$swing$BoxLayout()), $get$$class$java$lang$Integer());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(attributes, "axis"))) {
            final Object o = $getCallSiteArray[3].call(attributes, "axis");
            if (o instanceof Number) {
                axis = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Number())), $get$$class$java$lang$Integer());
            }
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callConstructor($get$$class$javax$swing$Box(), axis), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BoxFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BoxFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BoxFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BoxFactory.__timeStamp__239_neverHappen1292524205664 = 0L;
        BoxFactory.__timeStamp = 1292524205664L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BoxFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BoxFactory.$callSiteArray == null || ($createCallSiteArray = BoxFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BoxFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Number() {
        Class $class$java$lang$Number;
        if (($class$java$lang$Number = BoxFactory.$class$java$lang$Number) == null) {
            $class$java$lang$Number = (BoxFactory.$class$java$lang$Number = class$("java.lang.Number"));
        }
        return $class$java$lang$Number;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = BoxFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (BoxFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BoxFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BoxFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = BoxFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (BoxFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BoxFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BoxFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComponentFactory() {
        Class $class$groovy$swing$factory$ComponentFactory;
        if (($class$groovy$swing$factory$ComponentFactory = BoxFactory.$class$groovy$swing$factory$ComponentFactory) == null) {
            $class$groovy$swing$factory$ComponentFactory = (BoxFactory.$class$groovy$swing$factory$ComponentFactory = class$("groovy.swing.factory.ComponentFactory"));
        }
        return $class$groovy$swing$factory$ComponentFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BoxFactory() {
        Class $class$groovy$swing$factory$BoxFactory;
        if (($class$groovy$swing$factory$BoxFactory = BoxFactory.$class$groovy$swing$factory$BoxFactory) == null) {
            $class$groovy$swing$factory$BoxFactory = (BoxFactory.$class$groovy$swing$factory$BoxFactory = class$("groovy.swing.factory.BoxFactory"));
        }
        return $class$groovy$swing$factory$BoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = BoxFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (BoxFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$BoxLayout() {
        Class $class$javax$swing$BoxLayout;
        if (($class$javax$swing$BoxLayout = BoxFactory.$class$javax$swing$BoxLayout) == null) {
            $class$javax$swing$BoxLayout = (BoxFactory.$class$javax$swing$BoxLayout = class$("javax.swing.BoxLayout"));
        }
        return $class$javax$swing$BoxLayout;
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
