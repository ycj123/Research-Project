// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import java.awt.GridBagConstraints;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Insets;
import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.RootPaneContainer;
import java.awt.Component;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class GridBagFactory extends LayoutFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205670;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Number;
    private static /* synthetic */ Class $class$groovy$swing$factory$GridBagFactory;
    private static /* synthetic */ Class $class$javax$swing$RootPaneContainer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$java$awt$GridBagConstraints;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Container;
    private static /* synthetic */ Class $class$java$awt$Insets;
    private static /* synthetic */ Class $class$java$awt$GridBagLayout;
    
    public GridBagFactory() {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = $get$$class$java$awt$GridBagLayout();
        arguments[1] = Boolean.TRUE;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$LayoutFactory());
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
    public void addLayoutProperties(final Object context) {
        $getCallSiteArray()[0].callCurrent(this, context, $get$$class$java$awt$GridBagConstraints());
    }
    
    public static void processGridBagConstraintsAttributes(final FactoryBuilderSupport builder, final Object node, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(node instanceof Component)) {
            return;
        }
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[1].callSafe($getCallSiteArray[2].callGroovyObjectGetPropertySafe(builder), $getCallSiteArray[3].callGetProperty($get$$class$groovy$swing$factory$LayoutFactory())))) {
            o2 = $getCallSiteArray[4].callGetProperty($get$$class$groovy$swing$factory$LayoutFactory());
        }
        final Object constraintsAttr = o2;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(attributes, constraintsAttr))) {
            return;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call($getCallSiteArray[7].call(builder), "constraints"))) {
            return;
        }
        final Object parent = $getCallSiteArray[8].call(builder);
        if (parent instanceof RootPaneContainer) {
            if (!($getCallSiteArray[9].call($getCallSiteArray[10].call(ScriptBytecodeAdapter.castToType(parent, $get$$class$javax$swing$RootPaneContainer()))) instanceof GridBagLayout)) {
                return;
            }
        }
        else {
            if (!(parent instanceof Container)) {
                return;
            }
            if (!($getCallSiteArray[11].call(ScriptBytecodeAdapter.castToType(parent, $get$$class$java$awt$Container())) instanceof GridBagLayout)) {
                return;
            }
        }
        Boolean anyAttrs = Boolean.FALSE;
        final GridBagConstraints gbc = (GridBagConstraints)$getCallSiteArray[12].callConstructor($get$$class$java$awt$GridBagConstraints());
        Object o = null;
        o = $getCallSiteArray[13].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "gridx", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "gridx");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[14].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "gridy", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "gridy");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[15].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "gridwidth", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "gridwidth");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[16].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "gridheight", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "gridheight");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[17].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "weightx", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "weightx");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[18].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "weighty", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "weighty");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[19].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "anchor", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "anchor");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[20].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "fill", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "fill");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[21].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "insets", $get$$class$java$lang$Object());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(o, $get$$class$java$awt$Insets()), $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "insets");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[22].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "ipadx", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "ipadx");
            anyAttrs = Boolean.TRUE;
        }
        o = $getCallSiteArray[23].callStatic($get$$class$groovy$swing$factory$GridBagFactory(), attributes, "ipady", $get$$class$java$lang$Number());
        if (ScriptBytecodeAdapter.compareNotEqual(o, null)) {
            ScriptBytecodeAdapter.setProperty(o, $get$$class$groovy$swing$factory$GridBagFactory(), gbc, "ipady");
            anyAttrs = Boolean.TRUE;
        }
        if (DefaultTypeTransformation.booleanUnbox(anyAttrs)) {
            $getCallSiteArray[24].call($getCallSiteArray[25].call(builder), "constraints", gbc);
        }
    }
    
    public static Object extractAttribute(final Map attrs, final String name, final Class type) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[26].call(attrs, name))) {
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        final Object o = $getCallSiteArray[27].call(attrs, name);
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(o, null) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[28].call(type, type))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[29].call(attrs, name);
            return ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$GridBagFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GridBagFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GridBagFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        GridBagFactory.__timeStamp__239_neverHappen1292524205670 = 0L;
        GridBagFactory.__timeStamp = 1292524205670L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[30];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$GridBagFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GridBagFactory.$callSiteArray == null || ($createCallSiteArray = GridBagFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GridBagFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Number() {
        Class $class$java$lang$Number;
        if (($class$java$lang$Number = GridBagFactory.$class$java$lang$Number) == null) {
            $class$java$lang$Number = (GridBagFactory.$class$java$lang$Number = class$("java.lang.Number"));
        }
        return $class$java$lang$Number;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$GridBagFactory() {
        Class $class$groovy$swing$factory$GridBagFactory;
        if (($class$groovy$swing$factory$GridBagFactory = GridBagFactory.$class$groovy$swing$factory$GridBagFactory) == null) {
            $class$groovy$swing$factory$GridBagFactory = (GridBagFactory.$class$groovy$swing$factory$GridBagFactory = class$("groovy.swing.factory.GridBagFactory"));
        }
        return $class$groovy$swing$factory$GridBagFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$RootPaneContainer() {
        Class $class$javax$swing$RootPaneContainer;
        if (($class$javax$swing$RootPaneContainer = GridBagFactory.$class$javax$swing$RootPaneContainer) == null) {
            $class$javax$swing$RootPaneContainer = (GridBagFactory.$class$javax$swing$RootPaneContainer = class$("javax.swing.RootPaneContainer"));
        }
        return $class$javax$swing$RootPaneContainer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GridBagFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GridBagFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = GridBagFactory.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (GridBagFactory.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridBagConstraints() {
        Class $class$java$awt$GridBagConstraints;
        if (($class$java$awt$GridBagConstraints = GridBagFactory.$class$java$awt$GridBagConstraints) == null) {
            $class$java$awt$GridBagConstraints = (GridBagFactory.$class$java$awt$GridBagConstraints = class$("java.awt.GridBagConstraints"));
        }
        return $class$java$awt$GridBagConstraints;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GridBagFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GridBagFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Container() {
        Class $class$java$awt$Container;
        if (($class$java$awt$Container = GridBagFactory.$class$java$awt$Container) == null) {
            $class$java$awt$Container = (GridBagFactory.$class$java$awt$Container = class$("java.awt.Container"));
        }
        return $class$java$awt$Container;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Insets() {
        Class $class$java$awt$Insets;
        if (($class$java$awt$Insets = GridBagFactory.$class$java$awt$Insets) == null) {
            $class$java$awt$Insets = (GridBagFactory.$class$java$awt$Insets = class$("java.awt.Insets"));
        }
        return $class$java$awt$Insets;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridBagLayout() {
        Class $class$java$awt$GridBagLayout;
        if (($class$java$awt$GridBagLayout = GridBagFactory.$class$java$awt$GridBagLayout) == null) {
            $class$java$awt$GridBagLayout = (GridBagFactory.$class$java$awt$GridBagLayout = class$("java.awt.GridBagLayout"));
        }
        return $class$java$awt$GridBagLayout;
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
