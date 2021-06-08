// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class ButtonGroupFactory extends BeanFactory
{
    public static final String DELEGATE_PROPERTY_BUTTON_GROUP;
    public static final String DEFAULT_DELEGATE_PROPERTY_BUTTON_GROUP;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204967;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    private static /* synthetic */ Class $class$javax$swing$ButtonGroup;
    private static /* synthetic */ Class $class$groovy$swing$factory$ButtonGroupFactory;
    
    public ButtonGroupFactory() {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = $get$$class$javax$swing$ButtonGroup();
        arguments[1] = Boolean.TRUE;
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
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object callGroovyObjectGetProperty = $getCallSiteArray[1].callGroovyObjectGetProperty(builder);
        final String delegate_PROPERTY_BUTTON_GROUP = ButtonGroupFactory.DELEGATE_PROPERTY_BUTTON_GROUP;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[2].call(attributes, "buttonGroupProperty"))) {
            o = ButtonGroupFactory.DEFAULT_DELEGATE_PROPERTY_BUTTON_GROUP;
        }
        callSite.call(callGroovyObjectGetProperty, delegate_PROPERTY_BUTTON_GROUP, o);
        return ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$BeanFactory(), this, "newInstance", new Object[] { builder, name, value, attributes }), $get$$class$java$lang$Object());
    }
    
    public static Object buttonGroupAttributeDelegate(final Object builder, final Object node, final Object attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[3].callSafe($getCallSiteArray[4].callGetPropertySafe(builder), ButtonGroupFactory.DELEGATE_PROPERTY_BUTTON_GROUP))) {
            o2 = ButtonGroupFactory.DEFAULT_DELEGATE_PROPERTY_BUTTON_GROUP;
        }
        final Object buttonGroupAttr = o2;
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(attributes, buttonGroupAttr))) {
            return null;
        }
        final Object o = $getCallSiteArray[6].call(attributes, buttonGroupAttr);
        if (DefaultTypeTransformation.booleanUnbox((o instanceof ButtonGroup && node instanceof AbstractButton) ? Boolean.TRUE : Boolean.FALSE)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$ButtonGroupFactory(), $getCallSiteArray[7].callGetProperty(node), "group");
            return $getCallSiteArray[8].call(attributes, buttonGroupAttr);
        }
        return null;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ButtonGroupFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ButtonGroupFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ButtonGroupFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ButtonGroupFactory.__timeStamp__239_neverHappen1292524204967 = 0L;
        ButtonGroupFactory.__timeStamp = 1292524204967L;
        DEFAULT_DELEGATE_PROPERTY_BUTTON_GROUP = "buttonGroup";
        DELEGATE_PROPERTY_BUTTON_GROUP = "_delegateProperty:buttonGroup";
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ButtonGroupFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ButtonGroupFactory.$callSiteArray == null || ($createCallSiteArray = ButtonGroupFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ButtonGroupFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ButtonGroupFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ButtonGroupFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ButtonGroupFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ButtonGroupFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = ButtonGroupFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (ButtonGroupFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ButtonGroup() {
        Class $class$javax$swing$ButtonGroup;
        if (($class$javax$swing$ButtonGroup = ButtonGroupFactory.$class$javax$swing$ButtonGroup) == null) {
            $class$javax$swing$ButtonGroup = (ButtonGroupFactory.$class$javax$swing$ButtonGroup = class$("javax.swing.ButtonGroup"));
        }
        return $class$javax$swing$ButtonGroup;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ButtonGroupFactory() {
        Class $class$groovy$swing$factory$ButtonGroupFactory;
        if (($class$groovy$swing$factory$ButtonGroupFactory = ButtonGroupFactory.$class$groovy$swing$factory$ButtonGroupFactory) == null) {
            $class$groovy$swing$factory$ButtonGroupFactory = (ButtonGroupFactory.$class$groovy$swing$factory$ButtonGroupFactory = class$("groovy.swing.factory.ButtonGroupFactory"));
        }
        return $class$groovy$swing$factory$ButtonGroupFactory;
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
