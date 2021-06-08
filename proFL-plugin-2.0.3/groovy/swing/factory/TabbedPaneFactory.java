// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MissingPropertyException;
import groovy.lang.GString;
import java.awt.Window;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class TabbedPaneFactory extends BeanFactory
{
    public static final String DELEGATE_PROPERTY_TITLE;
    public static final String DEFAULT_DELEGATE_PROPERTY_TITLE;
    public static final String DELEGATE_PROPERTY_TAB_ICON;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_ICON;
    public static final String DELEGATE_PROPERTY_TAB_DISABLED_ICON;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_DISABLED_ICON;
    public static final String DELEGATE_PROPERTY_TAB_TOOL_TIP;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_TOOL_TIP;
    public static final String DELEGATE_PROPERTY_TAB_FOREGROUND;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_FOREGROUND;
    public static final String DELEGATE_PROPERTY_TAB_BACKGROUND;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_BACKGROUND;
    public static final String DELEGATE_PROPERTY_TAB_ENABLED;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_ENABLED;
    public static final String DELEGATE_PROPERTY_TAB_MNEMONIC;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_MNEMONIC;
    public static final String DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX;
    public static final String DEFAULT_DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX;
    public static final String CONTEXT_DATA_KEY;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Integer $const$4;
    private static final /* synthetic */ Integer $const$5;
    private static final /* synthetic */ Integer $const$6;
    private static final /* synthetic */ Integer $const$7;
    private static final /* synthetic */ Integer $const$8;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205034;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TabbedPaneFactory;
    
    public TabbedPaneFactory(final Class beanClass) {
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
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final FactoryBuilderSupport builder2 = (FactoryBuilderSupport)new Reference(builder);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object newChild = new Reference(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$BeanFactory(), this, "newInstance", new Object[] { ((Reference<Object>)builder2).get(), name, value, attributes }));
        ScriptBytecodeAdapter.setProperty(new TabbedPaneFactory$_newInstance_closure1(this, this, (Reference<Object>)builder2, (Reference<Object>)newChild), $get$$class$groovy$swing$factory$TabbedPaneFactory(), $getCallSiteArray[0].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "tabbedPaneFactoryClosure");
        $getCallSiteArray[1].call(((Reference<Object>)builder2).get(), $getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(((Reference<Object>)builder2).get())));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[4].call(attributes, "selectedIndex"), $get$$class$groovy$swing$factory$TabbedPaneFactory(), $getCallSiteArray[5].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "selectedIndex");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[6].call(attributes, "selectedComponent"), $get$$class$groovy$swing$factory$TabbedPaneFactory(), $getCallSiteArray[7].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "selectedComponent");
        final CallSite callSite = $getCallSiteArray[8];
        final Object callGroovyObjectGetProperty = $getCallSiteArray[9].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TITLE = TabbedPaneFactory.DELEGATE_PROPERTY_TITLE;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[10].call(attributes, "titleProperty"))) {
            o = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TITLE;
        }
        callSite.call(callGroovyObjectGetProperty, delegate_PROPERTY_TITLE, o);
        final CallSite callSite2 = $getCallSiteArray[11];
        final Object callGroovyObjectGetProperty2 = $getCallSiteArray[12].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_ICON = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_ICON;
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[13].call(attributes, "tabIconProperty"))) {
            o2 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_ICON;
        }
        callSite2.call(callGroovyObjectGetProperty2, delegate_PROPERTY_TAB_ICON, o2);
        final CallSite callSite3 = $getCallSiteArray[14];
        final Object callGroovyObjectGetProperty3 = $getCallSiteArray[15].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_DISABLED_ICON = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_DISABLED_ICON;
        Object o3;
        if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[16].call(attributes, "tabDisabledIconProperty"))) {
            o3 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_DISABLED_ICON;
        }
        callSite3.call(callGroovyObjectGetProperty3, delegate_PROPERTY_TAB_DISABLED_ICON, o3);
        final CallSite callSite4 = $getCallSiteArray[17];
        final Object callGroovyObjectGetProperty4 = $getCallSiteArray[18].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_TOOL_TIP = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_TOOL_TIP;
        Object o4;
        if (!DefaultTypeTransformation.booleanUnbox(o4 = $getCallSiteArray[19].call(attributes, "tabToolTipProperty"))) {
            o4 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_TOOL_TIP;
        }
        callSite4.call(callGroovyObjectGetProperty4, delegate_PROPERTY_TAB_TOOL_TIP, o4);
        final CallSite callSite5 = $getCallSiteArray[20];
        final Object callGroovyObjectGetProperty5 = $getCallSiteArray[21].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_BACKGROUND = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_BACKGROUND;
        Object o5;
        if (!DefaultTypeTransformation.booleanUnbox(o5 = $getCallSiteArray[22].call(attributes, "tabBackgroundProperty"))) {
            o5 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_BACKGROUND;
        }
        callSite5.call(callGroovyObjectGetProperty5, delegate_PROPERTY_TAB_BACKGROUND, o5);
        final CallSite callSite6 = $getCallSiteArray[23];
        final Object callGroovyObjectGetProperty6 = $getCallSiteArray[24].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_FOREGROUND = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_FOREGROUND;
        Object o6;
        if (!DefaultTypeTransformation.booleanUnbox(o6 = $getCallSiteArray[25].call(attributes, "tabForegroundProperty"))) {
            o6 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_FOREGROUND;
        }
        callSite6.call(callGroovyObjectGetProperty6, delegate_PROPERTY_TAB_FOREGROUND, o6);
        final CallSite callSite7 = $getCallSiteArray[26];
        final Object callGroovyObjectGetProperty7 = $getCallSiteArray[27].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_ENABLED = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_ENABLED;
        Object o7;
        if (!DefaultTypeTransformation.booleanUnbox(o7 = $getCallSiteArray[28].call(attributes, "tabEnabledProperty"))) {
            o7 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_ENABLED;
        }
        callSite7.call(callGroovyObjectGetProperty7, delegate_PROPERTY_TAB_ENABLED, o7);
        final CallSite callSite8 = $getCallSiteArray[29];
        final Object callGroovyObjectGetProperty8 = $getCallSiteArray[30].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_MNEMONIC = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_MNEMONIC;
        Object o8;
        if (!DefaultTypeTransformation.booleanUnbox(o8 = $getCallSiteArray[31].call(attributes, "tabMnemonicProperty"))) {
            o8 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_MNEMONIC;
        }
        callSite8.call(callGroovyObjectGetProperty8, delegate_PROPERTY_TAB_MNEMONIC, o8);
        final CallSite callSite9 = $getCallSiteArray[32];
        final Object callGroovyObjectGetProperty9 = $getCallSiteArray[33].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX = TabbedPaneFactory.DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX;
        Object o9;
        if (!DefaultTypeTransformation.booleanUnbox(o9 = $getCallSiteArray[34].call(attributes, "tabDisplayedMnemonicIndexProperty"))) {
            o9 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX;
        }
        callSite9.call(callGroovyObjectGetProperty9, delegate_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX, o9);
        return ScriptBytecodeAdapter.castToType(((Reference<Object>)newChild).get(), $get$$class$java$lang$Object());
    }
    
    public static void inspectChild(final FactoryBuilderSupport builder, final Object node, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((node instanceof Component && !(node instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        final CallSite callSite = $getCallSiteArray[35];
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[36].callSafe($getCallSiteArray[37].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TITLE))) {
            o = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TITLE;
        }
        final Object name = callSite.call(attributes, o);
        final CallSite callSite2 = $getCallSiteArray[38];
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[39].callSafe($getCallSiteArray[40].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_ICON))) {
            o2 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_ICON;
        }
        final Object icon = callSite2.call(attributes, o2);
        final CallSite callSite3 = $getCallSiteArray[41];
        Object o3;
        if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[42].callSafe($getCallSiteArray[43].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_DISABLED_ICON))) {
            o3 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_DISABLED_ICON;
        }
        final Object disabledIcon = callSite3.call(attributes, o3);
        final CallSite callSite4 = $getCallSiteArray[44];
        Object o4;
        if (!DefaultTypeTransformation.booleanUnbox(o4 = $getCallSiteArray[45].callSafe($getCallSiteArray[46].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_TOOL_TIP))) {
            o4 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_TOOL_TIP;
        }
        final Object toolTip = callSite4.call(attributes, o4);
        final CallSite callSite5 = $getCallSiteArray[47];
        Object o5;
        if (!DefaultTypeTransformation.booleanUnbox(o5 = $getCallSiteArray[48].callSafe($getCallSiteArray[49].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_BACKGROUND))) {
            o5 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_BACKGROUND;
        }
        final Object background = callSite5.call(attributes, o5);
        final CallSite callSite6 = $getCallSiteArray[50];
        Object o6;
        if (!DefaultTypeTransformation.booleanUnbox(o6 = $getCallSiteArray[51].callSafe($getCallSiteArray[52].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_FOREGROUND))) {
            o6 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_FOREGROUND;
        }
        final Object foreground = callSite6.call(attributes, o6);
        final CallSite callSite7 = $getCallSiteArray[53];
        Object o7;
        if (!DefaultTypeTransformation.booleanUnbox(o7 = $getCallSiteArray[54].callSafe($getCallSiteArray[55].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_ENABLED))) {
            o7 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_ENABLED;
        }
        final Object enabled = callSite7.call(attributes, o7);
        final CallSite callSite8 = $getCallSiteArray[56];
        Object o8;
        if (!DefaultTypeTransformation.booleanUnbox(o8 = $getCallSiteArray[57].callSafe($getCallSiteArray[58].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_MNEMONIC))) {
            o8 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_MNEMONIC;
        }
        final Object mnemonic = callSite8.call(attributes, o8);
        final CallSite callSite9 = $getCallSiteArray[59];
        Object o9;
        if (!DefaultTypeTransformation.booleanUnbox(o9 = $getCallSiteArray[60].callSafe($getCallSiteArray[61].callGroovyObjectGetPropertySafe(builder), TabbedPaneFactory.DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX))) {
            o9 = TabbedPaneFactory.DEFAULT_DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX;
        }
        final Object displayedMnemonicIndex = callSite9.call(attributes, o9);
        Object o10;
        if (!DefaultTypeTransformation.booleanUnbox(o10 = $getCallSiteArray[62].call($getCallSiteArray[63].callGroovyObjectGetProperty(builder), TabbedPaneFactory.CONTEXT_DATA_KEY))) {
            o10 = ScriptBytecodeAdapter.createMap(new Object[0]);
        }
        final Object tabbedPaneContext = o10;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[64].call(tabbedPaneContext))) {
            $getCallSiteArray[65].call($getCallSiteArray[66].callGroovyObjectGetProperty(builder), TabbedPaneFactory.CONTEXT_DATA_KEY, tabbedPaneContext);
        }
        $getCallSiteArray[67].call(tabbedPaneContext, node, ScriptBytecodeAdapter.createList(new Object[] { name, icon, disabledIcon, toolTip, background, foreground, enabled, mnemonic, displayedMnemonicIndex }));
    }
    
    @Override
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        try {
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[68].callSafe($getCallSiteArray[69].call($getCallSiteArray[70].callGroovyObjectGetProperty(builder), TabbedPaneFactory.CONTEXT_DATA_KEY), child))) {
                o = ScriptBytecodeAdapter.createList(new Object[] { null, null, null, null, null, null, null, null, null });
            }
            final Object title = o;
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[71].call(title, TabbedPaneFactory.$const$0), null)) {
                $getCallSiteArray[72].call(title, TabbedPaneFactory.$const$0, $getCallSiteArray[73].callGetProperty(child));
            }
            $getCallSiteArray[74].call(parent, $getCallSiteArray[75].call(title, TabbedPaneFactory.$const$0), $getCallSiteArray[76].call(title, TabbedPaneFactory.$const$1), child, $getCallSiteArray[77].call(title, TabbedPaneFactory.$const$2));
            final Integer index = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[78].call(parent, child), $get$$class$java$lang$Integer());
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[79].call(title, TabbedPaneFactory.$const$3))) {
                $getCallSiteArray[80].call(parent, index, $getCallSiteArray[81].call(title, TabbedPaneFactory.$const$3));
            }
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[82].call(title, TabbedPaneFactory.$const$4))) {
                $getCallSiteArray[83].call(parent, index, $getCallSiteArray[84].call(title, TabbedPaneFactory.$const$4));
            }
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[85].call(title, TabbedPaneFactory.$const$5))) {
                $getCallSiteArray[86].call(parent, index, $getCallSiteArray[87].call(title, TabbedPaneFactory.$const$5));
            }
            if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[88].call(title, TabbedPaneFactory.$const$6), null)) {
                $getCallSiteArray[89].call(parent, index, $getCallSiteArray[90].call(title, TabbedPaneFactory.$const$6));
            }
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[91].call(title, TabbedPaneFactory.$const$7))) {
                final Object mnemonic = $getCallSiteArray[92].call(title, TabbedPaneFactory.$const$7);
                if (DefaultTypeTransformation.booleanUnbox((!(mnemonic instanceof String) && !(mnemonic instanceof GString)) ? Boolean.FALSE : Boolean.TRUE)) {
                    $getCallSiteArray[93].call(parent, index, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[94].call(mnemonic, TabbedPaneFactory.$const$0), $get$$class$java$lang$Integer()), Integer.TYPE));
                }
                else {
                    $getCallSiteArray[95].call(parent, index, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(mnemonic, $get$$class$java$lang$Integer()), Integer.TYPE));
                }
            }
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[96].call(title, TabbedPaneFactory.$const$8))) {
                $getCallSiteArray[97].call(parent, index, $getCallSiteArray[98].call(title, TabbedPaneFactory.$const$8));
            }
        }
        catch (MissingPropertyException mpe) {
            $getCallSiteArray[99].call(parent, child);
        }
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$BeanFactory(), this, "onNodeCompleted", new Object[] { builder, parent, node });
        $getCallSiteArray[100].call(builder, $getCallSiteArray[101].callGetProperty($getCallSiteArray[102].callGroovyObjectGetProperty(builder)));
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[103].callGetProperty($getCallSiteArray[104].callGroovyObjectGetProperty(builder)), null)) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[105].callGetProperty($getCallSiteArray[106].callGroovyObjectGetProperty(builder)), $get$$class$groovy$swing$factory$TabbedPaneFactory(), node, "selectedComponent");
        }
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[107].callGetProperty($getCallSiteArray[108].callGroovyObjectGetProperty(builder)), null)) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[109].callGetProperty($getCallSiteArray[110].callGroovyObjectGetProperty(builder)), $get$$class$groovy$swing$factory$TabbedPaneFactory(), node, "selectedIndex");
        }
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TabbedPaneFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TabbedPaneFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TabbedPaneFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TabbedPaneFactory.__timeStamp__239_neverHappen1292524205034 = 0L;
        TabbedPaneFactory.__timeStamp = 1292524205034L;
        $const$8 = 8;
        $const$7 = 7;
        $const$6 = 6;
        $const$5 = 5;
        $const$4 = 4;
        $const$3 = 2;
        $const$2 = 3;
        $const$1 = 1;
        $const$0 = 0;
        CONTEXT_DATA_KEY = "TabbdePaneFactoryData";
        DEFAULT_DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX = "tabDisplayedMnemonicIndex";
        DELEGATE_PROPERTY_TAB_DISPLAYED_MNEMONIC_INDEX = "_delegateProperty:tabDisplayedMnemonicIndex";
        DEFAULT_DELEGATE_PROPERTY_TAB_MNEMONIC = "tabMnemonic";
        DELEGATE_PROPERTY_TAB_MNEMONIC = "_delegateProperty:tabMnemonic";
        DEFAULT_DELEGATE_PROPERTY_TAB_ENABLED = "tabEnabled";
        DELEGATE_PROPERTY_TAB_ENABLED = "_delegateProperty:tabEnabled";
        DEFAULT_DELEGATE_PROPERTY_TAB_BACKGROUND = "tabBackground";
        DELEGATE_PROPERTY_TAB_BACKGROUND = "_delegateProperty:tabBackground";
        DEFAULT_DELEGATE_PROPERTY_TAB_FOREGROUND = "tabForeground";
        DELEGATE_PROPERTY_TAB_FOREGROUND = "_delegateProperty:tabForeground";
        DEFAULT_DELEGATE_PROPERTY_TAB_TOOL_TIP = "tabToolTip";
        DELEGATE_PROPERTY_TAB_TOOL_TIP = "_delegateProperty:tabToolTip";
        DEFAULT_DELEGATE_PROPERTY_TAB_DISABLED_ICON = "tabDisabledIcon";
        DELEGATE_PROPERTY_TAB_DISABLED_ICON = "_delegateProperty:tabDisabledIcon";
        DEFAULT_DELEGATE_PROPERTY_TAB_ICON = "tabIcon";
        DELEGATE_PROPERTY_TAB_ICON = "_delegateProperty:tabIcon";
        DEFAULT_DELEGATE_PROPERTY_TITLE = "title";
        DELEGATE_PROPERTY_TITLE = "_delegateProperty:title";
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[111];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TabbedPaneFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TabbedPaneFactory.$callSiteArray == null || ($createCallSiteArray = TabbedPaneFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TabbedPaneFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = TabbedPaneFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (TabbedPaneFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TabbedPaneFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TabbedPaneFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TabbedPaneFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TabbedPaneFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = TabbedPaneFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (TabbedPaneFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TabbedPaneFactory() {
        Class $class$groovy$swing$factory$TabbedPaneFactory;
        if (($class$groovy$swing$factory$TabbedPaneFactory = TabbedPaneFactory.$class$groovy$swing$factory$TabbedPaneFactory) == null) {
            $class$groovy$swing$factory$TabbedPaneFactory = (TabbedPaneFactory.$class$groovy$swing$factory$TabbedPaneFactory = class$("groovy.swing.factory.TabbedPaneFactory"));
        }
        return $class$groovy$swing$factory$TabbedPaneFactory;
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
