// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.lang.reflect.Constructor;
import java.util.List;
import groovy.lang.Reference;
import javax.management.ObjectName;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JmxMetaMapBuilder implements GroovyObject
{
    private static Object ATTRIB_EXCEPTION_LIST;
    private static Object OPS_EXCEPTION_LIST;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202835;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanInfoManager;
    private static /* synthetic */ Class $class$javax$management$ObjectName;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static Map buildObjectMapFrom(final Object object) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(object)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable to create MBean, missing target object.");
        }
        Object map = null;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty(object), "descriptor"))) {
            o = $getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(object), "jmx");
        }
        final Object metaProp = o;
        if (DefaultTypeTransformation.booleanUnbox(metaProp)) {
            final Object descriptor = $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(object), $getCallSiteArray[7].call(object), $getCallSiteArray[8].callGetPropertySafe(metaProp));
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[9].call(descriptor), JmxMetaMapBuilder.$const$0) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].callGetProperty(descriptor))) ? Boolean.TRUE : Boolean.FALSE)) {
                map = ScriptBytecodeAdapter.createMap(new Object[] { "target", object, "name", $getCallSiteArray[11].callGetProperty($getCallSiteArray[12].call(object)), "jmxName", $getCallSiteArray[13].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), descriptor), "displayName", $getCallSiteArray[14].call(new GStringImpl(new Object[] { $getCallSiteArray[15].callGetProperty($getCallSiteArray[16].callGetProperty(object)) }, new String[] { "JMX Managed Object ", "" })), "attributes", $getCallSiteArray[17].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "constructors", $getCallSiteArray[18].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "operations", $getCallSiteArray[19].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object) });
            }
            else {
                final Object[] values = new Object[16];
                values[0] = "target";
                values[1] = object;
                values[2] = "name";
                values[3] = $getCallSiteArray[20].callGetProperty($getCallSiteArray[21].call(object));
                values[4] = "displayName";
                final int n = 5;
                Object o2;
                if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[22].callGetProperty(descriptor))) {
                    o2 = $getCallSiteArray[23].callGetProperty(descriptor);
                }
                values[n] = o2;
                values[6] = "attributes";
                final int n2 = 7;
                final CallSite callSite = $getCallSiteArray[24];
                final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
                Object o3;
                if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[25].callGetProperty(descriptor))) {
                    o3 = $getCallSiteArray[26].callGetProperty(descriptor);
                }
                values[n2] = callSite.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder, object, o3);
                values[8] = "constructors";
                final int n3 = 9;
                final CallSite callSite2 = $getCallSiteArray[27];
                final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder2 = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
                Object o4;
                if (!DefaultTypeTransformation.booleanUnbox(o4 = $getCallSiteArray[28].callGetProperty(descriptor))) {
                    o4 = $getCallSiteArray[29].callGetProperty(descriptor);
                }
                values[n3] = callSite2.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder2, object, o4);
                values[10] = "operations";
                final int n4 = 11;
                final CallSite callSite3 = $getCallSiteArray[30];
                final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder3 = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
                Object o5;
                if (!DefaultTypeTransformation.booleanUnbox(o5 = $getCallSiteArray[31].callGetProperty(descriptor))) {
                    o5 = $getCallSiteArray[32].callGetProperty(descriptor);
                }
                values[n4] = callSite3.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder3, object, o5);
                values[12] = "listeners";
                values[13] = $getCallSiteArray[33].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[34].callGetProperty(descriptor));
                values[14] = "mbeanServer";
                final int n5 = 15;
                Object o6;
                if (!DefaultTypeTransformation.booleanUnbox(o6 = $getCallSiteArray[35].callGetProperty(descriptor))) {
                    o6 = $getCallSiteArray[36].callGetProperty(descriptor);
                }
                values[n5] = o6;
                map = ScriptBytecodeAdapter.createMap(values);
                Object messageArgument;
                if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[37].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), descriptor))) {
                    messageArgument = $getCallSiteArray[38].call($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), $getCallSiteArray[39].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[40].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), object);
                }
                ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "jmxName");
            }
        }
        else {
            map = ScriptBytecodeAdapter.createMap(new Object[] { "target", object, "name", $getCallSiteArray[41].callGetProperty($getCallSiteArray[42].call(object)), "jmxName", $getCallSiteArray[43].call($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), $getCallSiteArray[44].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[45].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), object), "displayName", $getCallSiteArray[46].call(new GStringImpl(new Object[] { $getCallSiteArray[47].callGetProperty($getCallSiteArray[48].callGetProperty(object)) }, new String[] { "JMX Managed Object ", "" })), "attributes", $getCallSiteArray[49].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "constructors", $getCallSiteArray[50].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "operations", $getCallSiteArray[51].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object) });
        }
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    public static Map buildObjectMapFrom(final Object object, final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(object)) {
            throw (Throwable)$getCallSiteArray[52].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable to create MBean, missing target object.");
        }
        Object map = null;
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[53].call(descriptor), JmxMetaMapBuilder.$const$1) && DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[54].callGetProperty(descriptor)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[55].callGetProperty(descriptor))) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.TRUE : Boolean.FALSE)) {
            map = ScriptBytecodeAdapter.createMap(new Object[] { "target", object, "jmxName", $getCallSiteArray[56].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), descriptor), "name", $getCallSiteArray[57].callGetProperty($getCallSiteArray[58].call(object)), "displayName", $getCallSiteArray[59].call(new GStringImpl(new Object[] { $getCallSiteArray[60].callGetProperty($getCallSiteArray[61].callGetProperty(object)) }, new String[] { "JMX Managed Object ", "" })), "attributes", $getCallSiteArray[62].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "constructors", $getCallSiteArray[63].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object), "operations", $getCallSiteArray[64].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), object) });
        }
        else {
            final Object[] values = new Object[16];
            values[0] = "target";
            values[1] = object;
            values[2] = "name";
            values[3] = $getCallSiteArray[65].callGetProperty($getCallSiteArray[66].call(object));
            values[4] = "displayName";
            final int n = 5;
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[67].callGetProperty(descriptor))) {
                o = $getCallSiteArray[68].callGetProperty(descriptor);
            }
            values[n] = o;
            values[6] = "attributes";
            final int n2 = 7;
            final CallSite callSite = $getCallSiteArray[69];
            final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
            Object o2;
            if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[70].callGetProperty(descriptor))) {
                o2 = $getCallSiteArray[71].callGetProperty(descriptor);
            }
            values[n2] = callSite.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder, object, o2);
            values[8] = "constructors";
            final int n3 = 9;
            final CallSite callSite2 = $getCallSiteArray[72];
            final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder2 = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
            Object o3;
            if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[73].callGetProperty(descriptor))) {
                o3 = $getCallSiteArray[74].callGetProperty(descriptor);
            }
            values[n3] = callSite2.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder2, object, o3);
            values[10] = "operations";
            final int n4 = 11;
            final CallSite callSite3 = $getCallSiteArray[75];
            final Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder3 = $get$$class$groovy$jmx$builder$JmxMetaMapBuilder();
            Object o4;
            if (!DefaultTypeTransformation.booleanUnbox(o4 = $getCallSiteArray[76].callGetProperty(descriptor))) {
                o4 = $getCallSiteArray[77].callGetProperty(descriptor);
            }
            values[n4] = callSite3.callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder3, object, o4);
            values[12] = "listeners";
            values[13] = $getCallSiteArray[78].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[79].callGetProperty(descriptor));
            values[14] = "mbeanServer";
            final int n5 = 15;
            Object o5;
            if (!DefaultTypeTransformation.booleanUnbox(o5 = $getCallSiteArray[80].callGetProperty(descriptor))) {
                o5 = $getCallSiteArray[81].callGetProperty(descriptor);
            }
            values[n5] = o5;
            map = ScriptBytecodeAdapter.createMap(values);
            Object messageArgument;
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[82].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), descriptor))) {
                messageArgument = $getCallSiteArray[83].call($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), $getCallSiteArray[84].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[85].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), object);
            }
            ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "jmxName");
        }
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    private static ObjectName getObjectName(final Object map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(map)) {
            return (ObjectName)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$ObjectName());
        }
        Object jmxName = null;
        if ($getCallSiteArray[86].callGetProperty(map) instanceof String) {
            jmxName = $getCallSiteArray[87].callConstructor($get$$class$javax$management$ObjectName(), $getCallSiteArray[88].callGetProperty(map));
        }
        else if ($getCallSiteArray[89].callGetProperty(map) instanceof ObjectName) {
            jmxName = $getCallSiteArray[90].callGetProperty(map);
        }
        return (ObjectName)ScriptBytecodeAdapter.castToType(jmxName, $get$$class$javax$management$ObjectName());
    }
    
    public static Map buildAttributeMapFrom(final Object object) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object properties = $getCallSiteArray[91].call($getCallSiteArray[92].callGetProperty(object));
        final Object attribs = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        $getCallSiteArray[93].call(properties, new JmxMetaMapBuilder$_buildAttributeMapFrom_closure1($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)attribs));
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)attribs).get(), $get$$class$java$util$Map());
    }
    
    public static Map buildAttributeMapFrom(final Object object, final Object descCollection) {
        final Object object2 = new Reference(object);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox((descCollection instanceof String && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[94].call(descCollection, "*"))) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Object>)map).set($getCallSiteArray[95].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)object2).get()));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[96].call(descCollection, new JmxMetaMapBuilder$_buildAttributeMapFrom_closure2($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)object2));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[97].call(descCollection, new JmxMetaMapBuilder$_buildAttributeMapFrom_closure3($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)object2));
        }
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
    }
    
    private static Map createAttributeMap(final Object prop, final Object attribName, final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object desc = (descriptor instanceof Map) ? descriptor : ScriptBytecodeAdapter.createMap(new Object[0]);
        final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
        final Object name = $getCallSiteArray[98].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), attribName);
        final Object getterPrefix = DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual($getCallSiteArray[99].callGetProperty($getCallSiteArray[100].callGetProperty(prop)), "java.lang.Boolean") && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[101].callGetProperty($getCallSiteArray[102].callGetProperty(prop)), "boolean")) ? Boolean.FALSE : Boolean.TRUE) ? "is" : "get";
        ScriptBytecodeAdapter.setProperty(name, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "name");
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[103].callGetProperty(desc))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[104].callGetProperty(desc))) {
                messageArgument = $getCallSiteArray[105].call(new GStringImpl(new Object[] { name }, new String[] { "Property ", "" }));
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "displayName");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[106].callGetProperty($getCallSiteArray[107].callGetProperty(prop)), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "type");
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[108].callGetProperty(desc), null) ? $getCallSiteArray[109].callGetProperty(desc) : Boolean.TRUE, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "readable");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[110].callGetProperty(map))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[111].call(getterPrefix, name), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "getMethod");
        }
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[112].callGetProperty(desc), null) ? $getCallSiteArray[113].callGetProperty(desc) : Boolean.FALSE, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "writable");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[114].callGetProperty(map))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[115].call("set", name), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "setMethod");
        }
        Object messageArgument2;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument2 = $getCallSiteArray[116].callGetProperty(desc))) {
            messageArgument2 = $getCallSiteArray[117].callGetProperty(desc);
        }
        ScriptBytecodeAdapter.setProperty(messageArgument2, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "defaultValue");
        ScriptBytecodeAdapter.setProperty(prop, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "property");
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[118].callGetProperty(desc))) {
            o = $getCallSiteArray[119].callGetProperty(desc);
        }
        final Object listener = o;
        if (DefaultTypeTransformation.booleanUnbox(listener)) {
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "methodListener");
            ScriptBytecodeAdapter.setProperty(listener, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[120].callGetProperty(map), "callback");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[121].call("set", name), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[122].callGetProperty(map), "target");
            ScriptBytecodeAdapter.setProperty("attributeChangeListener", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[123].callGetProperty(map), "type");
            ScriptBytecodeAdapter.setProperty(name, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[124].callGetProperty(map), "attribute");
        }
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    public static Map buildConstructorMapFrom(final Object object) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object methods = $getCallSiteArray[125].call($getCallSiteArray[126].call(object));
        final Object ctors = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object cntr = JmxMetaMapBuilder.$const$2;
        Constructor ctor = null;
        final Object call = $getCallSiteArray[127].call(methods);
        while (((Iterator)call).hasNext()) {
            ctor = ((Iterator<Constructor>)call).next();
            final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[128].callGetProperty(ctor), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "name");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[129].call(new GStringImpl(new Object[] { $getCallSiteArray[130].call($getCallSiteArray[131].call(object)) }, new String[] { "Constructor for class ", "" })), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "displayName");
            ScriptBytecodeAdapter.setProperty("constructor", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "role");
            ScriptBytecodeAdapter.setProperty(ctor, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "constructor");
            $getCallSiteArray[132].call(map, "params", $getCallSiteArray[133].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ctor));
            final CallSite callSite = $getCallSiteArray[134];
            final Object o = ctors;
            final CallSite callSite2 = $getCallSiteArray[135];
            final Object call2 = $getCallSiteArray[136].call($getCallSiteArray[137].call($getCallSiteArray[138].callGetProperty(ctor), ScriptBytecodeAdapter.createRange($getCallSiteArray[139].call($getCallSiteArray[140].call($getCallSiteArray[141].callGetProperty(ctor), "."), JmxMetaMapBuilder.$const$0), JmxMetaMapBuilder.$const$3, true)), "@");
            final Object o2 = cntr;
            cntr = $getCallSiteArray[142].call(cntr);
            callSite.call(o, callSite2.call(call2, o2), map);
        }
        return (Map)ScriptBytecodeAdapter.castToType(ctors, $get$$class$java$util$Map());
    }
    
    public static Map buildConstructorMapFrom(final Object object, final Object descCollection) {
        final Object object2 = new Reference(object);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof String) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[143].call(descCollection, "*"))) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Object>)map).set($getCallSiteArray[144].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)object2).get()));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[145].call(descCollection, new JmxMetaMapBuilder$_buildConstructorMapFrom_closure4($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)object2));
        }
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
    }
    
    private static Map createConstructorMap(final Object ctor, final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object desc = DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descriptor) && descriptor instanceof Map) ? Boolean.TRUE : Boolean.FALSE) ? descriptor : ScriptBytecodeAdapter.createMap(new Object[0]);
        final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[146].callGetProperty(ctor), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "name");
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[147].callGetProperty(desc))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[148].callGetProperty(desc))) {
                messageArgument = "Class constructor";
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "displayName");
        ScriptBytecodeAdapter.setProperty("constructor", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "role");
        ScriptBytecodeAdapter.setProperty(ctor, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "constructor");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[149].callGetProperty(desc))) {
            $getCallSiteArray[150].call(map, "params", $getCallSiteArray[151].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ctor, $getCallSiteArray[152].callGetProperty(desc)));
        }
        else {
            $getCallSiteArray[153].call(map, "params", $getCallSiteArray[154].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ctor));
        }
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    public static Map buildOperationMapFrom(final Object object) {
        final Object object2 = new Reference(object);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object methods = $getCallSiteArray[155].call($getCallSiteArray[156].callGetProperty(((Reference<Object>)object2).get()));
        final Object ops = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        final Object declaredMethods = new Reference(ScriptBytecodeAdapter.getPropertySpreadSafe($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[157].call($getCallSiteArray[158].call(((Reference<Object>)object2).get())), "name"));
        $getCallSiteArray[159].call(methods, new JmxMetaMapBuilder$_buildOperationMapFrom_closure5($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)declaredMethods, (Reference<Object>)object2, (Reference<Object>)ops));
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)ops).get(), $get$$class$java$util$Map());
    }
    
    public static Map buildOperationMapFrom(final Object object, final Object descCollection) {
        final Object object2 = new Reference(object);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof String) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[160].call(descCollection, "*"))) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Object>)map).set($getCallSiteArray[161].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)object2).get()));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[162].call(descCollection, new JmxMetaMapBuilder$_buildOperationMapFrom_closure6($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)object2));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[163].call(descCollection, new JmxMetaMapBuilder$_buildOperationMapFrom_closure7($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)object2));
        }
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
    }
    
    private static Map createOperationMap(final Object object, final Object method, final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object desc = DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descriptor) && descriptor instanceof Map) ? Boolean.TRUE : Boolean.FALSE) ? descriptor : ScriptBytecodeAdapter.createMap(new Object[0]);
        final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[164].callGetProperty(method), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "name");
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[165].callGetProperty(desc))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[166].callGetProperty(desc))) {
                messageArgument = $getCallSiteArray[167].call(new GStringImpl(new Object[] { $getCallSiteArray[168].callGetProperty(method), $getCallSiteArray[169].call($getCallSiteArray[170].call(object)) }, new String[] { "Method ", " for class ", "" }));
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "displayName");
        ScriptBytecodeAdapter.setProperty("operation", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "role");
        ScriptBytecodeAdapter.setProperty(method, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "method");
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[171].call(desc), JmxMetaMapBuilder.$const$2) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[172].callGetProperty(desc))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[173].call(map, "params", $getCallSiteArray[174].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), method, $getCallSiteArray[175].callGetProperty(desc)));
        }
        else {
            $getCallSiteArray[176].call(map, "params", $getCallSiteArray[177].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), method));
        }
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[178].callGetProperty(desc))) {
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[179].callGetProperty(desc))) {
                if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[180].callGetProperty(desc))) {
                    o = $getCallSiteArray[181].callGetProperty(desc);
                }
            }
        }
        final Object listener = o;
        if (DefaultTypeTransformation.booleanUnbox(listener)) {
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "methodListener");
            ScriptBytecodeAdapter.setProperty(listener, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[182].callGetProperty(map), "callback");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[183].callGetProperty(method), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[184].callGetProperty(map), "target");
            ScriptBytecodeAdapter.setProperty("operationCallListener", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $getCallSiteArray[185].callGetProperty(map), "type");
        }
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    public static Map buildParameterMapFrom(final Object method) {
        final Object method2 = new Reference(method);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)method2).get())) {
            return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
        }
        final Object params = $getCallSiteArray[186].call(((Reference<Object>)method2).get());
        if (DefaultTypeTransformation.booleanUnbox(params)) {
            $getCallSiteArray[187].call(params, new JmxMetaMapBuilder$_buildParameterMapFrom_closure8($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)method2));
        }
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
    }
    
    public static Map buildParameterMapFrom(final Object method, final Object descCollection) {
        final Object method2 = new Reference(method);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)method2).get())) {
            return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[188].call(descCollection, new JmxMetaMapBuilder$_buildParameterMapFrom_closure9($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)method2));
        }
        else if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[189].call(descCollection, new JmxMetaMapBuilder$_buildParameterMapFrom_closure10($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map, (Reference<Object>)method2));
        }
        return (Map)ScriptBytecodeAdapter.castToType(((Reference<Object>)map).get(), $get$$class$java$util$Map());
    }
    
    private static Map createParameterMap(final Object method, final Object type, final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object desc = (descriptor instanceof Map) ? descriptor : ScriptBytecodeAdapter.createMap(new Object[0]);
        final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[190].callGetProperty(desc))) {
            messageArgument = $getCallSiteArray[191].callGetProperty(type);
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "name");
        Object messageArgument2;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument2 = $getCallSiteArray[192].callGetProperty(desc))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument2 = $getCallSiteArray[193].callGetProperty(desc))) {
                messageArgument2 = $getCallSiteArray[194].call(new GStringImpl(new Object[] { $getCallSiteArray[195].callGetProperty(type), $getCallSiteArray[196].callGetProperty(method) }, new String[] { "Parameter ", " for ", "" }));
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument2, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "displayName");
        ScriptBytecodeAdapter.setProperty(type, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "type");
        ScriptBytecodeAdapter.setProperty(method, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "method");
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    private static Object getParamTypeByName(final Object method, final Object typeName) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object type = null;
        final Object call = $getCallSiteArray[197].call($getCallSiteArray[198].call(method));
        while (((Iterator)call).hasNext()) {
            type = ((Iterator<Object>)call).next();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[199].call($getCallSiteArray[200].callGetProperty(type), typeName))) {
                return type;
            }
        }
        return null;
    }
    
    public static Object buildListenerMapFrom(final Object descCollection) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(descCollection) && descCollection instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[201].call(descCollection, new JmxMetaMapBuilder$_buildListenerMapFrom_closure11($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), (Reference<Object>)map));
        }
        return ((Reference<Object>)map).get();
    }
    
    public static Map createListenerMap(final Object descriptor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
        ScriptBytecodeAdapter.setProperty("eventListener", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "type");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[202].callGetProperty(descriptor), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "event");
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[203].callGetProperty(descriptor))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[204].callGetProperty(descriptor))) {
                messageArgument = $getCallSiteArray[205].callGetProperty(descriptor);
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "from");
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[206].callGetProperty(map))) {
            throw (Throwable)$getCallSiteArray[207].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Missing event source: specify source ObjectName (i.e. from:'...').");
        }
        try {
            ScriptBytecodeAdapter.setProperty(($getCallSiteArray[208].callGetProperty(map) instanceof String) ? $getCallSiteArray[209].callConstructor($get$$class$javax$management$ObjectName(), $getCallSiteArray[210].callGetProperty(map)) : $getCallSiteArray[211].callGetProperty(map), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "from");
        }
        catch (Exception e) {
            throw (Throwable)$getCallSiteArray[212].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), e);
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[213].callGetProperty(descriptor), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), map, "callback");
        return (Map)ScriptBytecodeAdapter.castToType(map, $get$$class$java$util$Map());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxMetaMapBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxMetaMapBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxMetaMapBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxMetaMapBuilder.__timeStamp__239_neverHappen1292524202835 = 0L;
        JmxMetaMapBuilder.__timeStamp = 1292524202835L;
        $const$3 = -1;
        $const$2 = 0;
        $const$1 = 2;
        $const$0 = 1;
        JmxMetaMapBuilder.ATTRIB_EXCEPTION_LIST = ScriptBytecodeAdapter.createList(new Object[] { "class", "descriptor", "jmx", "metaClass" });
        JmxMetaMapBuilder.OPS_EXCEPTION_LIST = ScriptBytecodeAdapter.createList(new Object[] { "clone", "equals", "finalize", "getClass", "getProperty", "hashCode", "invokeMethod", "notify", "notifyAll", "setProperty", "toString", "wait" });
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[214];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxMetaMapBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxMetaMapBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanInfoManager() {
        Class $class$groovy$jmx$builder$JmxBeanInfoManager;
        if (($class$groovy$jmx$builder$JmxBeanInfoManager = JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBeanInfoManager) == null) {
            $class$groovy$jmx$builder$JmxBeanInfoManager = (JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBeanInfoManager = class$("groovy.jmx.builder.JmxBeanInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxBeanInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$ObjectName() {
        Class $class$javax$management$ObjectName;
        if (($class$javax$management$ObjectName = JmxMetaMapBuilder.$class$javax$management$ObjectName) == null) {
            $class$javax$management$ObjectName = (JmxMetaMapBuilder.$class$javax$management$ObjectName = class$("javax.management.ObjectName"));
        }
        return $class$javax$management$ObjectName;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = JmxMetaMapBuilder.$class$java$util$Map) == null) {
            $class$java$util$Map = (JmxMetaMapBuilder.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
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
