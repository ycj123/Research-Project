// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Reference;
import org.codehaus.groovy.binding.MutualPropertyBinding;
import java.util.Iterator;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.binding.ClosureTriggerBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import org.codehaus.groovy.binding.EventTriggerBinding;
import org.codehaus.groovy.binding.ClosureSourceBinding;
import org.codehaus.groovy.binding.BindingUpdatable;
import org.codehaus.groovy.binding.AggregateBinding;
import org.codehaus.groovy.binding.SourceBinding;
import groovy.lang.Closure;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class BindFactory extends AbstractFactory implements GroovyObject
{
    public static final String CONTEXT_DATA_KEY;
    private final Map<String, TriggerBinding> syntheticBindings;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204384;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$binding$JComponentProperties;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$binding$JSpinnerProperties;
    private static /* synthetic */ Class $class$groovy$util$AbstractFactory;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$groovy$swing$binding$JComboBoxProperties;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$PropertyBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTableProperties;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractButtonProperties;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindFactory;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTextComponentProperties;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$ClosureSourceBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$ClosureTriggerBinding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$SourceBinding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$EventTriggerBinding;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$FullBinding;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$util$Iterator;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$binding$JSliderProperties;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$util$Map$Entry;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$TargetBinding;
    private static /* synthetic */ Class $class$java$util$HashMap;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$MutualPropertyBinding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$TriggerBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JScrollBarProperties;
    
    public BindFactory() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.syntheticBindings = (Map<String, TriggerBinding>)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$java$util$HashMap()), $get$$class$java$util$Map());
        $getCallSiteArray[1].call(this.syntheticBindings, $getCallSiteArray[2].callGetProperty($get$$class$groovy$swing$binding$JTextComponentProperties()));
        $getCallSiteArray[3].call(this.syntheticBindings, $getCallSiteArray[4].callGetProperty($get$$class$groovy$swing$binding$AbstractButtonProperties()));
        $getCallSiteArray[5].call(this.syntheticBindings, $getCallSiteArray[6].callGetProperty($get$$class$groovy$swing$binding$JSliderProperties()));
        $getCallSiteArray[7].call(this.syntheticBindings, $getCallSiteArray[8].callGetProperty($get$$class$groovy$swing$binding$JScrollBarProperties()));
        $getCallSiteArray[9].call(this.syntheticBindings, $getCallSiteArray[10].callGetProperty($get$$class$groovy$swing$binding$JComboBoxProperties()));
        $getCallSiteArray[11].call(this.syntheticBindings, $getCallSiteArray[12].callGetProperty($get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[13].call(this.syntheticBindings, $getCallSiteArray[14].callGetProperty($get$$class$groovy$swing$binding$JSpinnerProperties()));
        $getCallSiteArray[15].call(this.syntheticBindings, $getCallSiteArray[16].callGetProperty($get$$class$groovy$swing$binding$JTableProperties()));
        $getCallSiteArray[17].call(this.syntheticBindings, $getCallSiteArray[18].callGetProperty($get$$class$groovy$swing$binding$JComponentProperties()));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object source = $getCallSiteArray[19].call(attributes, "source");
        final Object target = $getCallSiteArray[20].call(attributes, "target");
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[21].call($getCallSiteArray[22].callGroovyObjectGetProperty(builder), BindFactory.CONTEXT_DATA_KEY))) {
            object = ScriptBytecodeAdapter.createMap(new Object[0]);
        }
        final Map bindContext = (Map)ScriptBytecodeAdapter.castToType(object, $get$$class$java$util$Map());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[23].call(bindContext))) {
            $getCallSiteArray[24].call($getCallSiteArray[25].callGroovyObjectGetProperty(builder), BindFactory.CONTEXT_DATA_KEY, bindContext);
        }
        TargetBinding tb = (TargetBinding)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$binding$TargetBinding());
        if (ScriptBytecodeAdapter.compareNotEqual(target, null)) {
            Object call;
            if (!DefaultTypeTransformation.booleanUnbox(call = $getCallSiteArray[26].call(attributes, "targetProperty"))) {
                call = value;
            }
            final String targetProperty = (String)ScriptBytecodeAdapter.castToType(call, $get$$class$java$lang$String());
            tb = (TargetBinding)$getCallSiteArray[27].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), target, targetProperty);
            if (ScriptBytecodeAdapter.compareEqual(source, null)) {
                Object result = null;
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[28].call(attributes, "mutual"))) {
                    result = $getCallSiteArray[29].callConstructor($get$$class$org$codehaus$groovy$binding$MutualPropertyBinding(), null, null, tb, ScriptBytecodeAdapter.getMethodPointer(this, "getTriggerBinding"));
                }
                else {
                    result = tb;
                }
                final Object newAttributes = ScriptBytecodeAdapter.createMap(new Object[0]);
                $getCallSiteArray[30].call(newAttributes, attributes);
                $getCallSiteArray[31].call(bindContext, result, newAttributes);
                $getCallSiteArray[32].call(attributes);
                return ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Object());
            }
        }
        FullBinding fb = (FullBinding)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$binding$FullBinding());
        final Boolean sea = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[33].call(attributes, "sourceEvent"), $get$$class$java$lang$Boolean());
        final Boolean sva = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[34].call(attributes, "sourceValue"), $get$$class$java$lang$Boolean());
        final Boolean spa = (Boolean)ScriptBytecodeAdapter.castToType((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[35].call(attributes, "sourceProperty")) && !DefaultTypeTransformation.booleanUnbox(value)) ? Boolean.FALSE : Boolean.TRUE, $get$$class$java$lang$Boolean());
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(sea) && DefaultTypeTransformation.booleanUnbox(sva)) ? Boolean.TRUE : Boolean.FALSE) && !DefaultTypeTransformation.booleanUnbox(spa)) ? Boolean.TRUE : Boolean.FALSE)) {
            final Closure queryValue = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[36].call(attributes, "sourceValue"), $get$$class$groovy$lang$Closure());
            final ClosureSourceBinding csb = (ClosureSourceBinding)$getCallSiteArray[37].callConstructor($get$$class$org$codehaus$groovy$binding$ClosureSourceBinding(), queryValue);
            final String trigger = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[38].call(attributes, "sourceEvent"), $get$$class$java$lang$String());
            final EventTriggerBinding etb = (EventTriggerBinding)$getCallSiteArray[39].callConstructor($get$$class$org$codehaus$groovy$binding$EventTriggerBinding(), source, trigger);
            fb = (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray[40].call(etb, csb, tb), $get$$class$org$codehaus$groovy$binding$FullBinding());
        }
        else if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(spa) && !DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(sea) && DefaultTypeTransformation.booleanUnbox(sva)) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.TRUE : Boolean.FALSE)) {
            Object call2;
            if (!DefaultTypeTransformation.booleanUnbox(call2 = $getCallSiteArray[41].call(attributes, "sourceProperty"))) {
                call2 = value;
            }
            final String property = (String)ScriptBytecodeAdapter.castToType(call2, $get$$class$java$lang$String());
            final PropertyBinding pb = (PropertyBinding)$getCallSiteArray[42].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), source, property);
            TriggerBinding trigger2 = (TriggerBinding)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$binding$TriggerBinding());
            if (DefaultTypeTransformation.booleanUnbox(sea)) {
                final String triggerName = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[43].call(attributes, "sourceEvent"), $get$$class$java$lang$String());
                trigger2 = (TriggerBinding)$getCallSiteArray[44].callConstructor($get$$class$org$codehaus$groovy$binding$EventTriggerBinding(), source, triggerName);
            }
            else {
                trigger2 = (TriggerBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray[45].callCurrent(this, pb), $get$$class$org$codehaus$groovy$binding$TriggerBinding());
            }
            SourceBinding sb = (SourceBinding)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$binding$SourceBinding());
            if (DefaultTypeTransformation.booleanUnbox(sva)) {
                final Closure queryValue2 = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[46].call(attributes, "sourceValue"), $get$$class$groovy$lang$Closure());
                sb = (SourceBinding)$getCallSiteArray[47].callConstructor($get$$class$org$codehaus$groovy$binding$ClosureSourceBinding(), queryValue2);
            }
            else {
                sb = pb;
            }
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[48].call(attributes, "mutual"))) {
                fb = (FullBinding)$getCallSiteArray[49].callConstructor($get$$class$org$codehaus$groovy$binding$MutualPropertyBinding(), trigger2, sb, tb, ScriptBytecodeAdapter.getMethodPointer(this, "getTriggerBinding"));
            }
            else {
                fb = (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray[50].call(trigger2, sb, tb), $get$$class$org$codehaus$groovy$binding$FullBinding());
            }
        }
        else {
            if (!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(sea) && !DefaultTypeTransformation.booleanUnbox(sva)) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox(spa)) ? Boolean.FALSE : Boolean.TRUE)) {
                final Object newAttributes2 = ScriptBytecodeAdapter.createMap(new Object[0]);
                $getCallSiteArray[51].call(newAttributes2, attributes);
                $getCallSiteArray[52].call(bindContext, tb, newAttributes2);
                $getCallSiteArray[53].call(attributes);
                return ScriptBytecodeAdapter.castToType($getCallSiteArray[54].callConstructor($get$$class$org$codehaus$groovy$binding$ClosureTriggerBinding(), this.syntheticBindings), $get$$class$java$lang$Object());
            }
            throw (Throwable)$getCallSiteArray[55].callConstructor($get$$class$java$lang$RuntimeException(), "Both sourceEvent: and sourceValue: cannot be specified along with sourceProperty: or a value argument");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[56].call(attributes, "value"))) {
            $getCallSiteArray[57].call(bindContext, fb, ScriptBytecodeAdapter.createMap(new Object[] { "value", $getCallSiteArray[58].call(attributes, "value") }));
        }
        final Object o = $getCallSiteArray[59].call(attributes, "bind");
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(o, null) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[60].call(attributes, "group"))) ? Boolean.TRUE : Boolean.FALSE) && !DefaultTypeTransformation.booleanUnbox((o instanceof Boolean && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[61].call(ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Boolean())))) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[62].call(fb);
        }
        if (DefaultTypeTransformation.booleanUnbox(($getCallSiteArray[63].callGetProperty(attributes) instanceof AggregateBinding && fb instanceof BindingUpdatable) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[64].call($getCallSiteArray[65].call(attributes, "group"), fb);
        }
        $getCallSiteArray[66].call(builder, ScriptBytecodeAdapter.getMethodPointer(fb, "unbind"));
        return ScriptBytecodeAdapter.castToType(fb, $get$$class$java$lang$Object());
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$util$AbstractFactory(), this, "onNodeCompleted", new Object[] { builder, parent, node });
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((node instanceof FullBinding && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[67].callGetProperty(node))) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[68].callGetProperty(node))) ? Boolean.TRUE : Boolean.FALSE)) {
            try {
                $getCallSiteArray[69].call(node);
            }
            catch (Exception ignored) {}
            try {
                $getCallSiteArray[70].call(node);
            }
            catch (Exception ignored2) {}
        }
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean isHandlesNodeChildren() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean onNodeChildren(final FactoryBuilderSupport builder, final Object node, final Closure childContent) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((node instanceof FullBinding && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[71].callGetProperty(node), null)) ? Boolean.TRUE : Boolean.FALSE)) {
            ScriptBytecodeAdapter.setProperty(childContent, $get$$class$groovy$swing$factory$BindFactory(), node, "converter");
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
        }
        if (node instanceof ClosureTriggerBinding) {
            ScriptBytecodeAdapter.setProperty(childContent, $get$$class$groovy$swing$factory$BindFactory(), node, "closure");
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
        }
        if (node instanceof TriggerBinding) {
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[72].call($getCallSiteArray[73].call($getCallSiteArray[74].callGroovyObjectGetProperty(builder), BindFactory.CONTEXT_DATA_KEY), node))) {
                o = ScriptBytecodeAdapter.createMap(new Object[0]);
            }
            final Object bindAttrs = o;
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[75].call(bindAttrs, "converter"))) {
                $getCallSiteArray[76].call(bindAttrs, "converter", childContent);
                return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
            }
        }
        throw (Throwable)$getCallSiteArray[77].callConstructor($get$$class$java$lang$RuntimeException(), "Binding nodes do not accept child content when a converter is already specified");
    }
    
    public TriggerBinding getTriggerBinding(final PropertyBinding psb) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String property = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[78].callGetProperty(psb), $get$$class$java$lang$String());
        for (Class currentClass = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[79].call($getCallSiteArray[80].callGetProperty(psb)), $get$$class$java$lang$Class()); ScriptBytecodeAdapter.compareNotEqual(currentClass, null); currentClass = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[83].call(currentClass), $get$$class$java$lang$Class())) {
            final Object trigger = ScriptBytecodeAdapter.castToType($getCallSiteArray[81].call(this.syntheticBindings, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(new GStringImpl(new Object[] { $getCallSiteArray[82].callGetProperty(currentClass), property }, new String[] { "", "#", "" }), $get$$class$java$lang$String()), $get$$class$java$lang$String())), $get$$class$org$codehaus$groovy$binding$TriggerBinding());
            if (ScriptBytecodeAdapter.compareNotEqual(trigger, null)) {
                return (TriggerBinding)ScriptBytecodeAdapter.castToType(trigger, $get$$class$org$codehaus$groovy$binding$TriggerBinding());
            }
        }
        return (TriggerBinding)ScriptBytecodeAdapter.castToType(psb, $get$$class$org$codehaus$groovy$binding$TriggerBinding());
    }
    
    public Object bindingAttributeDelegate(final FactoryBuilderSupport builder, final Object node, final Object attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Iterator iter = (Iterator)ScriptBytecodeAdapter.castToType($getCallSiteArray[84].call($getCallSiteArray[85].call(attributes)), $get$$class$java$util$Iterator());
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[86].call($getCallSiteArray[87].callGroovyObjectGetProperty(builder), BindFactory.CONTEXT_DATA_KEY))) {
            object = ScriptBytecodeAdapter.createMap(new Object[0]);
        }
        final Map bindContext = (Map)ScriptBytecodeAdapter.castToType(object, $get$$class$java$util$Map());
        while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[88].call(iter))) {
            final Map.Entry entry = (Map.Entry)ScriptBytecodeAdapter.castToType($getCallSiteArray[89].call(iter), $get$$class$java$util$Map$Entry());
            final String property = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[90].call($getCallSiteArray[91].callGetProperty(entry)), $get$$class$java$lang$String());
            final Object value = $getCallSiteArray[92].callGetProperty(entry);
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[93].call(bindContext, value))) {
                o = ScriptBytecodeAdapter.createMap(new Object[0]);
            }
            final Object bindAttrs = o;
            Object o2;
            if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[94].call(builder, $getCallSiteArray[95].callGetProperty($get$$class$groovy$swing$SwingBuilder())))) {
                o2 = $getCallSiteArray[96].callGetProperty($get$$class$groovy$swing$SwingBuilder());
            }
            final Object idAttr = o2;
            final Object id = $getCallSiteArray[97].call(bindAttrs, idAttr);
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[98].call(bindAttrs, "value"))) {
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[99].call(bindAttrs, "value"), $get$$class$groovy$swing$factory$BindFactory(), node, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { property }, new String[] { "", "" }), $get$$class$java$lang$String()));
            }
            FullBinding fb = (FullBinding)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$binding$FullBinding());
            if (value instanceof MutualPropertyBinding) {
                fb = (FullBinding)ScriptBytecodeAdapter.castToType(value, $get$$class$org$codehaus$groovy$binding$FullBinding());
                final PropertyBinding psb = (PropertyBinding)$getCallSiteArray[100].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), node, property);
                if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[101].callGetProperty(fb), null)) {
                    ScriptBytecodeAdapter.setProperty(psb, $get$$class$groovy$swing$factory$BindFactory(), fb, "sourceBinding");
                    $getCallSiteArray[102].callCurrent(this, fb, builder, bindAttrs, id);
                }
                else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[103].callGetProperty(fb), null)) {
                    ScriptBytecodeAdapter.setProperty(psb, $get$$class$groovy$swing$factory$BindFactory(), fb, "targetBinding");
                }
            }
            else if (value instanceof FullBinding) {
                fb = (FullBinding)ScriptBytecodeAdapter.castToType(value, $get$$class$org$codehaus$groovy$binding$FullBinding());
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[104].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), node, property), $get$$class$groovy$swing$factory$BindFactory(), fb, "targetBinding");
            }
            else if (value instanceof TargetBinding) {
                final PropertyBinding psb = (PropertyBinding)$getCallSiteArray[105].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), node, property);
                fb = (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray[106].call($getCallSiteArray[107].callCurrent(this, psb), psb, value), $get$$class$org$codehaus$groovy$binding$FullBinding());
                $getCallSiteArray[108].callCurrent(this, fb, builder, bindAttrs, id);
            }
            else {
                if (!(value instanceof ClosureTriggerBinding)) {
                    continue;
                }
                final PropertyBinding psb = (PropertyBinding)$getCallSiteArray[109].callConstructor($get$$class$org$codehaus$groovy$binding$PropertyBinding(), node, property);
                fb = (FullBinding)ScriptBytecodeAdapter.castToType($getCallSiteArray[110].call(value, value, psb), $get$$class$org$codehaus$groovy$binding$FullBinding());
                $getCallSiteArray[111].callCurrent(this, fb, builder, bindAttrs, id);
            }
            try {
                $getCallSiteArray[112].call(fb);
            }
            catch (Exception e) {}
            try {
                $getCallSiteArray[113].call(fb);
            }
            catch (Exception e2) {}
            $getCallSiteArray[114].call(iter);
        }
        return null;
    }
    
    private Object finishContextualBinding(final FullBinding fb, final FactoryBuilderSupport builder, final Object bindAttrs, final Object id) {
        final FullBinding fb2 = (FullBinding)new Reference(fb);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object bindValue = $getCallSiteArray[115].call(bindAttrs, "bind");
        $getCallSiteArray[116].call(bindAttrs, new BindFactory$_finishContextualBinding_closure1(this, this, (Reference<Object>)fb2));
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(bindValue, null) && !DefaultTypeTransformation.booleanUnbox((bindValue instanceof Boolean && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[117].call(ScriptBytecodeAdapter.castToType(bindValue, $get$$class$java$lang$Boolean())))) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[118].call(((Reference<Object>)fb2).get());
        }
        $getCallSiteArray[119].call(builder, ScriptBytecodeAdapter.getMethodPointer(((Reference<Object>)fb2).get(), "unbind"));
        if (DefaultTypeTransformation.booleanUnbox(id)) {
            return $getCallSiteArray[120].call(builder, id, ((Reference<Object>)fb2).get());
        }
        return null;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BindFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BindFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BindFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BindFactory.__timeStamp__239_neverHappen1292524204384 = 0L;
        BindFactory.__timeStamp = 1292524204384L;
        CONTEXT_DATA_KEY = "BindFactoryData";
    }
    
    public final Map<String, TriggerBinding> getSyntheticBindings() {
        return this.syntheticBindings;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[121];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BindFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BindFactory.$callSiteArray == null || ($createCallSiteArray = BindFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BindFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JComponentProperties() {
        Class $class$groovy$swing$binding$JComponentProperties;
        if (($class$groovy$swing$binding$JComponentProperties = BindFactory.$class$groovy$swing$binding$JComponentProperties) == null) {
            $class$groovy$swing$binding$JComponentProperties = (BindFactory.$class$groovy$swing$binding$JComponentProperties = class$("groovy.swing.binding.JComponentProperties"));
        }
        return $class$groovy$swing$binding$JComponentProperties;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = BindFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (BindFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JSpinnerProperties() {
        Class $class$groovy$swing$binding$JSpinnerProperties;
        if (($class$groovy$swing$binding$JSpinnerProperties = BindFactory.$class$groovy$swing$binding$JSpinnerProperties) == null) {
            $class$groovy$swing$binding$JSpinnerProperties = (BindFactory.$class$groovy$swing$binding$JSpinnerProperties = class$("groovy.swing.binding.JSpinnerProperties"));
        }
        return $class$groovy$swing$binding$JSpinnerProperties;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$AbstractFactory() {
        Class $class$groovy$util$AbstractFactory;
        if (($class$groovy$util$AbstractFactory = BindFactory.$class$groovy$util$AbstractFactory) == null) {
            $class$groovy$util$AbstractFactory = (BindFactory.$class$groovy$util$AbstractFactory = class$("groovy.util.AbstractFactory"));
        }
        return $class$groovy$util$AbstractFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = BindFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (BindFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = BindFactory.$class$java$util$Map) == null) {
            $class$java$util$Map = (BindFactory.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JComboBoxProperties() {
        Class $class$groovy$swing$binding$JComboBoxProperties;
        if (($class$groovy$swing$binding$JComboBoxProperties = BindFactory.$class$groovy$swing$binding$JComboBoxProperties) == null) {
            $class$groovy$swing$binding$JComboBoxProperties = (BindFactory.$class$groovy$swing$binding$JComboBoxProperties = class$("groovy.swing.binding.JComboBoxProperties"));
        }
        return $class$groovy$swing$binding$JComboBoxProperties;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$PropertyBinding() {
        Class $class$org$codehaus$groovy$binding$PropertyBinding;
        if (($class$org$codehaus$groovy$binding$PropertyBinding = BindFactory.$class$org$codehaus$groovy$binding$PropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$PropertyBinding = (BindFactory.$class$org$codehaus$groovy$binding$PropertyBinding = class$("org.codehaus.groovy.binding.PropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$PropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTableProperties() {
        Class $class$groovy$swing$binding$JTableProperties;
        if (($class$groovy$swing$binding$JTableProperties = BindFactory.$class$groovy$swing$binding$JTableProperties) == null) {
            $class$groovy$swing$binding$JTableProperties = (BindFactory.$class$groovy$swing$binding$JTableProperties = class$("groovy.swing.binding.JTableProperties"));
        }
        return $class$groovy$swing$binding$JTableProperties;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractButtonProperties() {
        Class $class$groovy$swing$binding$AbstractButtonProperties;
        if (($class$groovy$swing$binding$AbstractButtonProperties = BindFactory.$class$groovy$swing$binding$AbstractButtonProperties) == null) {
            $class$groovy$swing$binding$AbstractButtonProperties = (BindFactory.$class$groovy$swing$binding$AbstractButtonProperties = class$("groovy.swing.binding.AbstractButtonProperties"));
        }
        return $class$groovy$swing$binding$AbstractButtonProperties;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindFactory() {
        Class $class$groovy$swing$factory$BindFactory;
        if (($class$groovy$swing$factory$BindFactory = BindFactory.$class$groovy$swing$factory$BindFactory) == null) {
            $class$groovy$swing$factory$BindFactory = (BindFactory.$class$groovy$swing$factory$BindFactory = class$("groovy.swing.factory.BindFactory"));
        }
        return $class$groovy$swing$factory$BindFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTextComponentProperties() {
        Class $class$groovy$swing$binding$JTextComponentProperties;
        if (($class$groovy$swing$binding$JTextComponentProperties = BindFactory.$class$groovy$swing$binding$JTextComponentProperties) == null) {
            $class$groovy$swing$binding$JTextComponentProperties = (BindFactory.$class$groovy$swing$binding$JTextComponentProperties = class$("groovy.swing.binding.JTextComponentProperties"));
        }
        return $class$groovy$swing$binding$JTextComponentProperties;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$ClosureSourceBinding() {
        Class $class$org$codehaus$groovy$binding$ClosureSourceBinding;
        if (($class$org$codehaus$groovy$binding$ClosureSourceBinding = BindFactory.$class$org$codehaus$groovy$binding$ClosureSourceBinding) == null) {
            $class$org$codehaus$groovy$binding$ClosureSourceBinding = (BindFactory.$class$org$codehaus$groovy$binding$ClosureSourceBinding = class$("org.codehaus.groovy.binding.ClosureSourceBinding"));
        }
        return $class$org$codehaus$groovy$binding$ClosureSourceBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties() {
        Class $class$groovy$swing$binding$JListProperties;
        if (($class$groovy$swing$binding$JListProperties = BindFactory.$class$groovy$swing$binding$JListProperties) == null) {
            $class$groovy$swing$binding$JListProperties = (BindFactory.$class$groovy$swing$binding$JListProperties = class$("groovy.swing.binding.JListProperties"));
        }
        return $class$groovy$swing$binding$JListProperties;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$ClosureTriggerBinding() {
        Class $class$org$codehaus$groovy$binding$ClosureTriggerBinding;
        if (($class$org$codehaus$groovy$binding$ClosureTriggerBinding = BindFactory.$class$org$codehaus$groovy$binding$ClosureTriggerBinding) == null) {
            $class$org$codehaus$groovy$binding$ClosureTriggerBinding = (BindFactory.$class$org$codehaus$groovy$binding$ClosureTriggerBinding = class$("org.codehaus.groovy.binding.ClosureTriggerBinding"));
        }
        return $class$org$codehaus$groovy$binding$ClosureTriggerBinding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$SourceBinding() {
        Class $class$org$codehaus$groovy$binding$SourceBinding;
        if (($class$org$codehaus$groovy$binding$SourceBinding = BindFactory.$class$org$codehaus$groovy$binding$SourceBinding) == null) {
            $class$org$codehaus$groovy$binding$SourceBinding = (BindFactory.$class$org$codehaus$groovy$binding$SourceBinding = class$("org.codehaus.groovy.binding.SourceBinding"));
        }
        return $class$org$codehaus$groovy$binding$SourceBinding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$EventTriggerBinding() {
        Class $class$org$codehaus$groovy$binding$EventTriggerBinding;
        if (($class$org$codehaus$groovy$binding$EventTriggerBinding = BindFactory.$class$org$codehaus$groovy$binding$EventTriggerBinding) == null) {
            $class$org$codehaus$groovy$binding$EventTriggerBinding = (BindFactory.$class$org$codehaus$groovy$binding$EventTriggerBinding = class$("org.codehaus.groovy.binding.EventTriggerBinding"));
        }
        return $class$org$codehaus$groovy$binding$EventTriggerBinding;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = BindFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (BindFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$FullBinding() {
        Class $class$org$codehaus$groovy$binding$FullBinding;
        if (($class$org$codehaus$groovy$binding$FullBinding = BindFactory.$class$org$codehaus$groovy$binding$FullBinding) == null) {
            $class$org$codehaus$groovy$binding$FullBinding = (BindFactory.$class$org$codehaus$groovy$binding$FullBinding = class$("org.codehaus.groovy.binding.FullBinding"));
        }
        return $class$org$codehaus$groovy$binding$FullBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = BindFactory.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (BindFactory.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Iterator() {
        Class $class$java$util$Iterator;
        if (($class$java$util$Iterator = BindFactory.$class$java$util$Iterator) == null) {
            $class$java$util$Iterator = (BindFactory.$class$java$util$Iterator = class$("java.util.Iterator"));
        }
        return $class$java$util$Iterator;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BindFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BindFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BindFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BindFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JSliderProperties() {
        Class $class$groovy$swing$binding$JSliderProperties;
        if (($class$groovy$swing$binding$JSliderProperties = BindFactory.$class$groovy$swing$binding$JSliderProperties) == null) {
            $class$groovy$swing$binding$JSliderProperties = (BindFactory.$class$groovy$swing$binding$JSliderProperties = class$("groovy.swing.binding.JSliderProperties"));
        }
        return $class$groovy$swing$binding$JSliderProperties;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = BindFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (BindFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map$Entry() {
        Class $class$java$util$Map$Entry;
        if (($class$java$util$Map$Entry = BindFactory.$class$java$util$Map$Entry) == null) {
            $class$java$util$Map$Entry = (BindFactory.$class$java$util$Map$Entry = class$("java.util.Map$Entry"));
        }
        return $class$java$util$Map$Entry;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = BindFactory.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (BindFactory.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$TargetBinding() {
        Class $class$org$codehaus$groovy$binding$TargetBinding;
        if (($class$org$codehaus$groovy$binding$TargetBinding = BindFactory.$class$org$codehaus$groovy$binding$TargetBinding) == null) {
            $class$org$codehaus$groovy$binding$TargetBinding = (BindFactory.$class$org$codehaus$groovy$binding$TargetBinding = class$("org.codehaus.groovy.binding.TargetBinding"));
        }
        return $class$org$codehaus$groovy$binding$TargetBinding;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = BindFactory.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (BindFactory.$class$java$util$HashMap = class$("java.util.HashMap"));
        }
        return $class$java$util$HashMap;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$MutualPropertyBinding() {
        Class $class$org$codehaus$groovy$binding$MutualPropertyBinding;
        if (($class$org$codehaus$groovy$binding$MutualPropertyBinding = BindFactory.$class$org$codehaus$groovy$binding$MutualPropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$MutualPropertyBinding = (BindFactory.$class$org$codehaus$groovy$binding$MutualPropertyBinding = class$("org.codehaus.groovy.binding.MutualPropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$MutualPropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$TriggerBinding() {
        Class $class$org$codehaus$groovy$binding$TriggerBinding;
        if (($class$org$codehaus$groovy$binding$TriggerBinding = BindFactory.$class$org$codehaus$groovy$binding$TriggerBinding) == null) {
            $class$org$codehaus$groovy$binding$TriggerBinding = (BindFactory.$class$org$codehaus$groovy$binding$TriggerBinding = class$("org.codehaus.groovy.binding.TriggerBinding"));
        }
        return $class$org$codehaus$groovy$binding$TriggerBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JScrollBarProperties() {
        Class $class$groovy$swing$binding$JScrollBarProperties;
        if (($class$groovy$swing$binding$JScrollBarProperties = BindFactory.$class$groovy$swing$binding$JScrollBarProperties) == null) {
            $class$groovy$swing$binding$JScrollBarProperties = (BindFactory.$class$groovy$swing$binding$JScrollBarProperties = class$("groovy.swing.binding.JScrollBarProperties"));
        }
        return $class$groovy$swing$binding$JScrollBarProperties;
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
