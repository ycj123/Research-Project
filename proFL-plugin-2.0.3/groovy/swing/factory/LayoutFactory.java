// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.RootPaneContainer;
import java.awt.Container;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class LayoutFactory extends BeanFactory
{
    private Object contextProps;
    public static final String DELEGATE_PROPERTY_CONSTRAINT;
    public static final String DEFAULT_DELEGATE_PROPERTY_CONSTRAINT;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205011;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$RootPaneContainer;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Container;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    
    public LayoutFactory(final Class klass) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[] { null }));
        arguments[0] = klass;
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
    
    public LayoutFactory(final Class klass, final boolean leaf) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = klass;
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
    
    @Override
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object callGroovyObjectGetProperty = $getCallSiteArray[1].callGroovyObjectGetProperty(builder);
        final String delegate_PROPERTY_CONSTRAINT = LayoutFactory.DELEGATE_PROPERTY_CONSTRAINT;
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[2].call(attributes, "constraintsProperty"))) {
            o2 = LayoutFactory.DEFAULT_DELEGATE_PROPERTY_CONSTRAINT;
        }
        callSite.call(callGroovyObjectGetProperty, delegate_PROPERTY_CONSTRAINT, o2);
        final Object o = ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$BeanFactory(), this, "newInstance", new Object[] { builder, name, value, attributes });
        $getCallSiteArray[3].callCurrent(this, $getCallSiteArray[4].call(builder));
        return ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Object());
    }
    
    public void addLayoutProperties(final Object context, final Class layoutClass) {
        final Class layoutClass2 = (Class)new Reference(layoutClass);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.contextProps, null)) {
            this.contextProps = ScriptBytecodeAdapter.createMap(new Object[0]);
            $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(((Reference)layoutClass2).get()), new LayoutFactory$_addLayoutProperties_closure1(this, this, (Reference<Object>)layoutClass2));
        }
        $getCallSiteArray[7].call(context, this.contextProps);
    }
    
    public void addLayoutProperties(final Object context) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[8].callCurrent(this, context, $getCallSiteArray[9].callGroovyObjectGetProperty(this));
    }
    
    @Override
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (parent instanceof Container) {
            ScriptBytecodeAdapter.setProperty(child, $get$$class$groovy$swing$factory$LayoutFactory(), $getCallSiteArray[10].callStatic($get$$class$groovy$swing$factory$LayoutFactory(), parent), "layout");
        }
    }
    
    public static Container getLayoutTarget(Container parent) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (parent instanceof RootPaneContainer) {
            final RootPaneContainer rpc = (RootPaneContainer)ScriptBytecodeAdapter.castToType(parent, $get$$class$javax$swing$RootPaneContainer());
            parent = (Container)ScriptBytecodeAdapter.castToType($getCallSiteArray[11].call(rpc), $get$$class$java$awt$Container());
        }
        return (Container)ScriptBytecodeAdapter.castToType(parent, $get$$class$java$awt$Container());
    }
    
    public static Object constraintsAttributeDelegate(final Object builder, final Object node, final Object attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[12].callSafe($getCallSiteArray[13].callGetPropertySafe(builder), LayoutFactory.DELEGATE_PROPERTY_CONSTRAINT))) {
            o = LayoutFactory.DEFAULT_DELEGATE_PROPERTY_CONSTRAINT;
        }
        final Object constraintsAttr = o;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(attributes, constraintsAttr))) {
            final Object call = $getCallSiteArray[15].call(attributes, constraintsAttr);
            ScriptBytecodeAdapter.setProperty(call, $get$$class$groovy$swing$factory$LayoutFactory(), $getCallSiteArray[16].callGetProperty(builder), "constraints");
            return call;
        }
        return null;
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$LayoutFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = LayoutFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (LayoutFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        LayoutFactory.__timeStamp__239_neverHappen1292524205011 = 0L;
        LayoutFactory.__timeStamp = 1292524205011L;
        DEFAULT_DELEGATE_PROPERTY_CONSTRAINT = "constraints";
        DELEGATE_PROPERTY_CONSTRAINT = "_delegateProperty:Constrinat";
    }
    
    public Object getContextProps() {
        return this.contextProps;
    }
    
    public void setContextProps(final Object contextProps) {
        this.contextProps = contextProps;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[17];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$LayoutFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LayoutFactory.$callSiteArray == null || ($createCallSiteArray = LayoutFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LayoutFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$RootPaneContainer() {
        Class $class$javax$swing$RootPaneContainer;
        if (($class$javax$swing$RootPaneContainer = LayoutFactory.$class$javax$swing$RootPaneContainer) == null) {
            $class$javax$swing$RootPaneContainer = (LayoutFactory.$class$javax$swing$RootPaneContainer = class$("javax.swing.RootPaneContainer"));
        }
        return $class$javax$swing$RootPaneContainer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = LayoutFactory.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (LayoutFactory.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = LayoutFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (LayoutFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = LayoutFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (LayoutFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Container() {
        Class $class$java$awt$Container;
        if (($class$java$awt$Container = LayoutFactory.$class$java$awt$Container) == null) {
            $class$java$awt$Container = (LayoutFactory.$class$java$awt$Container = class$("java.awt.Container"));
        }
        return $class$java$awt$Container;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = LayoutFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (LayoutFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
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
