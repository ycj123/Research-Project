// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.util.GroovyMBean;
import groovy.lang.Reference;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import groovy.lang.GroovyObject;

public class JmxBuilderTools implements GroovyObject
{
    private static String DEFAULT_DOMAIN;
    private static String DEFAULT_NAME_TYPE;
    private static String NODE_NAME_ATTRIBUTES;
    private static String NODE_NAME_ATTRIBS;
    private static String NODE_NAME_CONSTRUCTORS;
    private static String NODE_NAME_CTORS;
    private static String NODE_NAME_OPERATIONS;
    private static String NODE_NAME_OPS;
    private static String ATTRIB_KEY_DESCRIPTION;
    private static String ATTRIB_KEY_DESC;
    private static String ATTRIB_KEY_TYPE;
    private static String ATTRIB_KEY_DEFAULT;
    private static String JMX_KEY;
    private static String DESC_KEY;
    private static String DESC_KEY_MBEAN_RESOURCE;
    private static String DESC_KEY_MBEAN_RESOURCE_TYPE;
    private static String DESC_KEY_MBEAN_ATTRIBS;
    private static String DESC_KEY_MBEAN_OPS;
    private static String DESC_KEY_MBEAN_CTORS;
    private static String DESC_KEY_MBEAN_NOTES;
    private static String DESC_KEY_NAME;
    private static String DESC_KEY_JMX_NAME;
    private static String DESC_KEY_DISPLAY_NAME;
    private static String DESC_KEY_TYPE;
    private static String DESC_KEY_GETMETHOD;
    private static String DESC_KEY_SETMETHOD;
    private static String DESC_KEY_EVENT_TYPE;
    private static String DESC_KEY_EVENT_NAME;
    private static String DESC_KEY_EVENT_SOURCE;
    private static String DESC_KEY_EVENT_MESSAGE;
    private static String DESC_VAL_TYPE_ATTRIB;
    private static String DESC_VAL_TYPE_GETTER;
    private static String DESC_VAL_TYPE_SETTER;
    private static String DESC_VAL_TYPE_OP;
    private static String DESC_VAL_TYPE_NOTIFICATION;
    private static String DESC_VAL_TYPE_CTOR;
    private static String DESC_VAL_TYPE_MBEAN;
    private static String DESC_KEY_ROLE;
    private static String DESC_KEY_READABLE;
    private static String DESC_KEY_WRITABLE;
    private static String DESC_KEY_SIGNATURE;
    private static String EVENT_KEY_CONTEXTS;
    private static String EVENT_KEY_CALLBACK;
    private static String EVENT_KEY_CALLBACK_RESULT;
    private static String EVENT_KEY_METHOD;
    private static String EVENT_KEY_METHOD_RESULT;
    private static String EVENT_KEY_ISATTRIB;
    private static String EVENT_KEY_NAME;
    private static String EVENT_KEY_MESSAGE;
    private static String EVENT_KEY_TYPE;
    private static String EVENT_KEY_NODE_TYPE;
    private static String EVENT_VAL_NODETYPE_BROADCASTER;
    private static String EVENT_VAL_NODETYPE_LISTENER;
    private static String EVENT_KEY_TARGETS;
    private static Map PRIMITIVE_TYPES;
    private static Map TYPE_MAP;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202801;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Long;
    private static /* synthetic */ Class $class$javax$management$MBeanServerFactory;
    private static /* synthetic */ Class $class$java$lang$Character;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderModelMBean;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanInfoManager;
    private static /* synthetic */ Class $class$java$math$BigDecimal;
    private static /* synthetic */ Class $class$javax$management$DynamicMBean;
    private static /* synthetic */ Class $class$groovy$util$GroovyMBean;
    private static /* synthetic */ Class $class$javax$management$MBeanServerConnection;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class array$$class$java$lang$Class;
    private static /* synthetic */ Class $class$java$lang$Short;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Double;
    private static /* synthetic */ Class $class$java$lang$Byte;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$util$Date;
    private static /* synthetic */ Class $class$java$lang$management$ManagementFactory;
    private static /* synthetic */ Class $class$java$lang$Float;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    private static /* synthetic */ Class $class$java$math$BigInteger;
    
    public JmxBuilderTools() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static String capitalize(final String value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(value)) {
            return (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].call(value), JmxBuilderTools.$const$0)) {
            return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(value), $get$$class$java$lang$String());
        }
        return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[2].call(value), JmxBuilderTools.$const$0) ? $getCallSiteArray[3].call($getCallSiteArray[4].call($getCallSiteArray[5].call(value, JmxBuilderTools.$const$1)), $getCallSiteArray[6].call(value, ScriptBytecodeAdapter.createRange(JmxBuilderTools.$const$0, JmxBuilderTools.$const$2, true))) : $getCallSiteArray[7].call(value), $get$$class$java$lang$String());
    }
    
    public static String uncapitalize(final String value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(value)) {
            return (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[8].call(value), JmxBuilderTools.$const$0)) {
            return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(value), $get$$class$java$lang$String());
        }
        return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[10].call(value), JmxBuilderTools.$const$0) ? $getCallSiteArray[11].call($getCallSiteArray[12].call($getCallSiteArray[13].call(value, JmxBuilderTools.$const$1)), $getCallSiteArray[14].call(value, ScriptBytecodeAdapter.createRange(JmxBuilderTools.$const$0, JmxBuilderTools.$const$2, true))) : $getCallSiteArray[15].call(value), $get$$class$java$lang$String());
    }
    
    public static ObjectName getDefaultObjectName(final Object obj) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: aload_1        
        //     5: ldc             16
        //     7: aaload         
        //     8: getstatic       groovy/jmx/builder/JmxBuilderTools.DEFAULT_DOMAIN:Ljava/lang/String;
        //    11: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //    14: dup            
        //    15: iconst_2       
        //    16: anewarray       Ljava/lang/Object;
        //    19: dup            
        //    20: iconst_0       
        //    21: aload_1        
        //    22: ldc             17
        //    24: aaload         
        //    25: aload_1        
        //    26: ldc             18
        //    28: aaload         
        //    29: aload_0         /* obj */
        //    30: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    40: aastore        
        //    41: dup            
        //    42: iconst_1       
        //    43: aload_1        
        //    44: ldc             19
        //    46: aaload         
        //    47: aload_0         /* obj */
        //    48: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    53: aastore        
        //    54: iconst_3       
        //    55: anewarray       Ljava/lang/String;
        //    58: dup            
        //    59: iconst_0       
        //    60: ldc             ":name="
        //    62: aastore        
        //    63: dup            
        //    64: iconst_1       
        //    65: ldc             ",hashCode="
        //    67: aastore        
        //    68: dup            
        //    69: iconst_2       
        //    70: ldc             ""
        //    72: aastore        
        //    73: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //    76: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    81: invokestatic    groovy/jmx/builder/JmxBuilderTools.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    84: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    87: checkcast       Ljava/lang/String;
        //    90: astore_2        /* name */
        //    91: aload_1        
        //    92: ldc             20
        //    94: aaload         
        //    95: invokestatic    groovy/jmx/builder/JmxBuilderTools.$get$$class$javax$management$ObjectName:()Ljava/lang/Class;
        //    98: aload_2         /* name */
        //    99: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   104: invokestatic    groovy/jmx/builder/JmxBuilderTools.$get$$class$javax$management$ObjectName:()Ljava/lang/Class;
        //   107: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   110: checkcast       Ljavax/management/ObjectName;
        //   113: astore_3       
        //   114: nop            
        //   115: nop            
        //   116: aload_3        
        //   117: areturn        
        //   118: goto            172
        //   121: astore_3       
        //   122: aload_1        
        //   123: ldc             21
        //   125: aaload         
        //   126: invokestatic    groovy/jmx/builder/JmxBuilderTools.$get$$class$groovy$jmx$builder$JmxBuilderException:()Ljava/lang/Class;
        //   129: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   132: dup            
        //   133: iconst_1       
        //   134: anewarray       Ljava/lang/Object;
        //   137: dup            
        //   138: iconst_0       
        //   139: aload_2         /* name */
        //   140: aastore        
        //   141: iconst_2       
        //   142: anewarray       Ljava/lang/String;
        //   145: dup            
        //   146: iconst_0       
        //   147: ldc             "Unable to create JMX ObjectName "
        //   149: aastore        
        //   150: dup            
        //   151: iconst_1       
        //   152: ldc             ""
        //   154: aastore        
        //   155: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   158: aload_3        
        //   159: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   164: checkcast       Ljava/lang/Throwable;
        //   167: athrow         
        //   168: nop            
        //   169: goto            172
        //   172: nop            
        //   173: goto            179
        //   176: astore_3       
        //   177: aload_3        
        //   178: athrow         
        //   179: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  91     115    121    168    Ljava/lang/Exception;
        //  116    121    121    168    Ljava/lang/Exception;
        //  91     115    176    179    Any
        //  116    121    176    179    Any
        //  121    169    176    179    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static MBeanServerConnection getMBeanServer() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object servers = $getCallSiteArray[22].call($get$$class$javax$management$MBeanServerFactory(), (Object)null);
        final Object server = ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[23].call(servers), JmxBuilderTools.$const$1) ? $getCallSiteArray[24].call(servers, JmxBuilderTools.$const$1) : $getCallSiteArray[25].call($get$$class$java$lang$management$ManagementFactory());
        return (MBeanServerConnection)ScriptBytecodeAdapter.castToType(server, $get$$class$javax$management$MBeanServerConnection());
    }
    
    public static Class[] getSignatureFromParamInfo(final Object params) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(params, null) && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[26].call(params), JmxBuilderTools.$const$1)) ? Boolean.FALSE : Boolean.TRUE)) {
            return (Class[])ScriptBytecodeAdapter.castToType(null, $get$array$$class$java$lang$Class());
        }
        final Object[] result = (Object)new Reference(new Object[DefaultTypeTransformation.intUnbox($getCallSiteArray[27].call(params))]);
        $getCallSiteArray[28].call(params, new JmxBuilderTools$_getSignatureFromParamInfo_closure1($get$$class$groovy$jmx$builder$JmxBuilderTools(), $get$$class$groovy$jmx$builder$JmxBuilderTools(), (Reference<Object>)result));
        return (Class[])ScriptBytecodeAdapter.castToType(((Reference<Object>)result).get(), $get$array$$class$java$lang$Class());
    }
    
    public static String getNormalizedType(final String type) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[29].callStatic($get$$class$groovy$jmx$builder$JmxBuilderTools(), type))) {
            return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[30].callGetProperty($getCallSiteArray[31].call(JmxBuilderTools.PRIMITIVE_TYPES, type)), $get$$class$java$lang$String());
        }
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[32].callGetPropertySafe($getCallSiteArray[33].call(JmxBuilderTools.TYPE_MAP, type)))) {
            if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[34].callGetPropertySafe($getCallSiteArray[35].call($get$$class$java$lang$Class(), type)))) {
                object = null;
            }
        }
        return (String)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$String());
    }
    
    private static boolean typeIsPrimitive(final String typeName) {
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray()[36].call(JmxBuilderTools.PRIMITIVE_TYPES, typeName), $get$$class$java$lang$Boolean()));
    }
    
    public static boolean isClassMBean(final Class cls) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Boolean result = Boolean.FALSE;
        if (ScriptBytecodeAdapter.compareEqual(cls, null)) {
            result = Boolean.FALSE;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[37].call($get$$class$javax$management$DynamicMBean(), cls))) {
            result = Boolean.TRUE;
        }
        Object face = null;
        final Object call = $getCallSiteArray[38].call($getCallSiteArray[39].call(cls));
        while (((Iterator)call).hasNext()) {
            face = ((Iterator<Object>)call).next();
            final String name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[40].call(face), $get$$class$java$lang$String());
            if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[41].call(name, "MBean")) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[42].call(name, "MXBean"))) ? Boolean.FALSE : Boolean.TRUE)) {
                result = Boolean.TRUE;
                break;
            }
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Boolean()));
    }
    
    public static GroovyMBean registerMBeanFromMap(final String regPolicy, final Map metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object info = $getCallSiteArray[43].call($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), metaMap);
        Object mbean = null;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[44].callGetProperty(metaMap))) {
            mbean = $getCallSiteArray[45].callGetProperty(metaMap);
        }
        else {
            mbean = $getCallSiteArray[46].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderModelMBean(), info);
            $getCallSiteArray[47].call(mbean, $getCallSiteArray[48].callGetProperty(metaMap));
            $getCallSiteArray[49].call(mbean, $getCallSiteArray[50].callGetProperty(metaMap));
            $getCallSiteArray[51].call(mbean, $getCallSiteArray[52].callGetProperty(metaMap));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[53].callGetProperty(metaMap))) {
                $getCallSiteArray[54].call(mbean, $getCallSiteArray[55].callGetProperty(metaMap), $getCallSiteArray[56].callGetProperty(metaMap));
            }
        }
        Object gbean = null;
        if (ScriptBytecodeAdapter.isCase(regPolicy, "replace")) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[57].call($getCallSiteArray[58].callGetProperty(metaMap), $getCallSiteArray[59].callGetProperty(metaMap)))) {
                $getCallSiteArray[60].call($getCallSiteArray[61].callGetProperty(metaMap), $getCallSiteArray[62].callGetProperty(metaMap));
            }
            $getCallSiteArray[63].call($getCallSiteArray[64].callGetProperty(metaMap), mbean, $getCallSiteArray[65].callGetProperty(metaMap));
            gbean = $getCallSiteArray[66].callConstructor($get$$class$groovy$util$GroovyMBean(), $getCallSiteArray[67].callGetProperty(metaMap), $getCallSiteArray[68].callGetProperty(metaMap));
        }
        else {
            if (ScriptBytecodeAdapter.isCase(regPolicy, "ignore")) {
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[69].call($getCallSiteArray[70].callGetProperty(metaMap), $getCallSiteArray[71].callGetProperty(metaMap)))) {
                    return (GroovyMBean)ScriptBytecodeAdapter.castToType(gbean, $get$$class$groovy$util$GroovyMBean());
                }
            }
            else if (ScriptBytecodeAdapter.isCase(regPolicy, "error")) {}
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[72].call($getCallSiteArray[73].callGetProperty(metaMap), $getCallSiteArray[74].callGetProperty(metaMap)))) {
                throw (Throwable)$getCallSiteArray[75].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { $getCallSiteArray[76].callGetProperty(metaMap) }, new String[] { "A Bean with name ", " is already registered on the server." }));
            }
            $getCallSiteArray[77].call($getCallSiteArray[78].callGetProperty(metaMap), mbean, $getCallSiteArray[79].callGetProperty(metaMap));
            gbean = $getCallSiteArray[80].callConstructor($get$$class$groovy$util$GroovyMBean(), $getCallSiteArray[81].callGetProperty(metaMap), $getCallSiteArray[82].callGetProperty(metaMap));
        }
        return (GroovyMBean)ScriptBytecodeAdapter.castToType(gbean, $get$$class$groovy$util$GroovyMBean());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxBuilderTools()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxBuilderTools.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxBuilderTools.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxBuilderTools.__timeStamp__239_neverHappen1292524202801 = 0L;
        JmxBuilderTools.__timeStamp = 1292524202801L;
        $const$2 = -1;
        $const$1 = 0;
        $const$0 = 1;
        JmxBuilderTools.EVENT_KEY_TARGETS = "eventListeners";
        JmxBuilderTools.EVENT_VAL_NODETYPE_LISTENER = "listener";
        JmxBuilderTools.EVENT_VAL_NODETYPE_BROADCASTER = "broadcaster";
        JmxBuilderTools.EVENT_KEY_NODE_TYPE = "eventNodeType";
        JmxBuilderTools.EVENT_KEY_TYPE = "eventType";
        JmxBuilderTools.EVENT_KEY_MESSAGE = "eventMessage";
        JmxBuilderTools.EVENT_KEY_NAME = "eventName";
        JmxBuilderTools.EVENT_KEY_ISATTRIB = "eventIsAttrib";
        JmxBuilderTools.EVENT_KEY_METHOD_RESULT = "eventMethodResult";
        JmxBuilderTools.EVENT_KEY_METHOD = "eventMethod";
        JmxBuilderTools.EVENT_KEY_CALLBACK_RESULT = "eventCallbackResult";
        JmxBuilderTools.EVENT_KEY_CALLBACK = "eventCallback";
        JmxBuilderTools.EVENT_KEY_CONTEXTS = "eventContexts";
        JmxBuilderTools.DESC_KEY_SIGNATURE = "signature";
        JmxBuilderTools.DESC_KEY_WRITABLE = "writable";
        JmxBuilderTools.DESC_KEY_READABLE = "readable";
        JmxBuilderTools.DESC_KEY_ROLE = "role";
        JmxBuilderTools.DESC_VAL_TYPE_MBEAN = "mbean";
        JmxBuilderTools.DESC_VAL_TYPE_CTOR = "constructor";
        JmxBuilderTools.DESC_VAL_TYPE_NOTIFICATION = "notification";
        JmxBuilderTools.DESC_VAL_TYPE_OP = "operation";
        JmxBuilderTools.DESC_VAL_TYPE_SETTER = "setter";
        JmxBuilderTools.DESC_VAL_TYPE_GETTER = "getter";
        JmxBuilderTools.DESC_VAL_TYPE_ATTRIB = "attribute";
        JmxBuilderTools.DESC_KEY_EVENT_MESSAGE = "messageText";
        JmxBuilderTools.DESC_KEY_EVENT_SOURCE = "eventSource";
        JmxBuilderTools.DESC_KEY_EVENT_NAME = "eventName";
        JmxBuilderTools.DESC_KEY_EVENT_TYPE = "eventType";
        JmxBuilderTools.DESC_KEY_SETMETHOD = "setMethod";
        JmxBuilderTools.DESC_KEY_GETMETHOD = "getMethod";
        JmxBuilderTools.DESC_KEY_TYPE = "descriptorType";
        JmxBuilderTools.DESC_KEY_DISPLAY_NAME = "displayName";
        JmxBuilderTools.DESC_KEY_JMX_NAME = "jmxName";
        JmxBuilderTools.DESC_KEY_NAME = "name";
        JmxBuilderTools.DESC_KEY_MBEAN_NOTES = "notifications";
        JmxBuilderTools.DESC_KEY_MBEAN_CTORS = "constructors";
        JmxBuilderTools.DESC_KEY_MBEAN_OPS = "operations";
        JmxBuilderTools.DESC_KEY_MBEAN_ATTRIBS = "attributes";
        JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE_TYPE = "ObjectReference";
        JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE = "resource";
        JmxBuilderTools.DESC_KEY = "descriptor";
        JmxBuilderTools.JMX_KEY = "jmx";
        JmxBuilderTools.ATTRIB_KEY_DEFAULT = "defaultValue";
        JmxBuilderTools.ATTRIB_KEY_TYPE = "type";
        JmxBuilderTools.ATTRIB_KEY_DESC = "desc";
        JmxBuilderTools.ATTRIB_KEY_DESCRIPTION = "description";
        JmxBuilderTools.NODE_NAME_OPS = "ops";
        JmxBuilderTools.NODE_NAME_OPERATIONS = "operations";
        JmxBuilderTools.NODE_NAME_CTORS = "ctors";
        JmxBuilderTools.NODE_NAME_CONSTRUCTORS = "constructors";
        JmxBuilderTools.NODE_NAME_ATTRIBS = "attribs";
        JmxBuilderTools.NODE_NAME_ATTRIBUTES = "attributes";
        JmxBuilderTools.DEFAULT_NAME_TYPE = "ExportedObject";
        JmxBuilderTools.DEFAULT_DOMAIN = "jmx.builder";
        JmxBuilderTools.PRIMITIVE_TYPES = ScriptBytecodeAdapter.createMap(new Object[] { "char", $getCallSiteArray()[83].callGetProperty($get$$class$java$lang$Integer()), "byte", $getCallSiteArray()[84].callGetProperty($get$$class$java$lang$Byte()), "short", $getCallSiteArray()[85].callGetProperty($get$$class$java$lang$Short()), "int", $getCallSiteArray()[86].callGetProperty($get$$class$java$lang$Integer()), "long", $getCallSiteArray()[87].callGetProperty($get$$class$java$lang$Long()), "float", $getCallSiteArray()[88].callGetProperty($get$$class$java$lang$Float()), "double", $getCallSiteArray()[89].callGetProperty($get$$class$java$lang$Double()), "boolean", $getCallSiteArray()[90].callGetProperty($get$$class$java$lang$Boolean()) });
        JmxBuilderTools.TYPE_MAP = ScriptBytecodeAdapter.createMap(new Object[] { "object", $get$$class$java$lang$Object(), "Object", $get$$class$java$lang$Object(), "java.lang.Object", $get$$class$java$lang$Object(), "string", $get$$class$java$lang$String(), "String", $get$$class$java$lang$String(), "java.lang.String", $get$$class$java$lang$String(), "char", Character.TYPE, "character", $get$$class$java$lang$Character(), "Character", $get$$class$java$lang$Character(), "java.lang.Character", $get$$class$java$lang$Character(), "byte", Byte.TYPE, "Byte", $get$$class$java$lang$Byte(), "java.lang.Byte", $get$$class$java$lang$Byte(), "short", Short.TYPE, "Short", $get$$class$java$lang$Short(), "java.lang.Short", $get$$class$java$lang$Short(), "int", Integer.TYPE, "integer", $get$$class$java$lang$Integer(), "Integer", $get$$class$java$lang$Integer(), "java.lang.Integer", $get$$class$java$lang$Integer(), "long", Long.TYPE, "Long", $get$$class$java$lang$Long(), "java.lang.Long", $get$$class$java$lang$Long(), "float", Float.TYPE, "Float", $get$$class$java$lang$Float(), "java.lang.Float", $get$$class$java$lang$Float(), "double", Double.TYPE, "Double", $get$$class$java$lang$Double(), "java.lang.Double", $get$$class$java$lang$Double(), "boolean", Boolean.TYPE, "Boolean", $get$$class$java$lang$Boolean(), "java.lang.Boolean", $get$$class$java$lang$Boolean(), "bigDec", $get$$class$java$math$BigDecimal(), "bigDecimal", $get$$class$java$math$BigDecimal(), "BigDecimal", $get$$class$java$math$BigDecimal(), "java.math.BigDecimal", $get$$class$java$math$BigDecimal(), "bigInt", $get$$class$java$math$BigInteger(), "bigInteger", $get$$class$java$math$BigInteger(), "BigInteger", $get$$class$java$math$BigInteger(), "java.math.BigInteger", $get$$class$java$math$BigInteger(), "date", $get$$class$java$util$Date(), "java.util.Date", $get$$class$java$util$Date() });
    }
    
    public static String getDEFAULT_DOMAIN() {
        return JmxBuilderTools.DEFAULT_DOMAIN;
    }
    
    public static void setDEFAULT_DOMAIN(final String default_DOMAIN) {
        JmxBuilderTools.DEFAULT_DOMAIN = default_DOMAIN;
    }
    
    public static String getDEFAULT_NAME_TYPE() {
        return JmxBuilderTools.DEFAULT_NAME_TYPE;
    }
    
    public static void setDEFAULT_NAME_TYPE(final String default_NAME_TYPE) {
        JmxBuilderTools.DEFAULT_NAME_TYPE = default_NAME_TYPE;
    }
    
    public static String getNODE_NAME_ATTRIBUTES() {
        return JmxBuilderTools.NODE_NAME_ATTRIBUTES;
    }
    
    public static void setNODE_NAME_ATTRIBUTES(final String node_NAME_ATTRIBUTES) {
        JmxBuilderTools.NODE_NAME_ATTRIBUTES = node_NAME_ATTRIBUTES;
    }
    
    public static String getNODE_NAME_ATTRIBS() {
        return JmxBuilderTools.NODE_NAME_ATTRIBS;
    }
    
    public static void setNODE_NAME_ATTRIBS(final String node_NAME_ATTRIBS) {
        JmxBuilderTools.NODE_NAME_ATTRIBS = node_NAME_ATTRIBS;
    }
    
    public static String getNODE_NAME_CONSTRUCTORS() {
        return JmxBuilderTools.NODE_NAME_CONSTRUCTORS;
    }
    
    public static void setNODE_NAME_CONSTRUCTORS(final String node_NAME_CONSTRUCTORS) {
        JmxBuilderTools.NODE_NAME_CONSTRUCTORS = node_NAME_CONSTRUCTORS;
    }
    
    public static String getNODE_NAME_CTORS() {
        return JmxBuilderTools.NODE_NAME_CTORS;
    }
    
    public static void setNODE_NAME_CTORS(final String node_NAME_CTORS) {
        JmxBuilderTools.NODE_NAME_CTORS = node_NAME_CTORS;
    }
    
    public static String getNODE_NAME_OPERATIONS() {
        return JmxBuilderTools.NODE_NAME_OPERATIONS;
    }
    
    public static void setNODE_NAME_OPERATIONS(final String node_NAME_OPERATIONS) {
        JmxBuilderTools.NODE_NAME_OPERATIONS = node_NAME_OPERATIONS;
    }
    
    public static String getNODE_NAME_OPS() {
        return JmxBuilderTools.NODE_NAME_OPS;
    }
    
    public static void setNODE_NAME_OPS(final String node_NAME_OPS) {
        JmxBuilderTools.NODE_NAME_OPS = node_NAME_OPS;
    }
    
    public static String getATTRIB_KEY_DESCRIPTION() {
        return JmxBuilderTools.ATTRIB_KEY_DESCRIPTION;
    }
    
    public static void setATTRIB_KEY_DESCRIPTION(final String attrib_KEY_DESCRIPTION) {
        JmxBuilderTools.ATTRIB_KEY_DESCRIPTION = attrib_KEY_DESCRIPTION;
    }
    
    public static String getATTRIB_KEY_DESC() {
        return JmxBuilderTools.ATTRIB_KEY_DESC;
    }
    
    public static void setATTRIB_KEY_DESC(final String attrib_KEY_DESC) {
        JmxBuilderTools.ATTRIB_KEY_DESC = attrib_KEY_DESC;
    }
    
    public static String getATTRIB_KEY_TYPE() {
        return JmxBuilderTools.ATTRIB_KEY_TYPE;
    }
    
    public static void setATTRIB_KEY_TYPE(final String attrib_KEY_TYPE) {
        JmxBuilderTools.ATTRIB_KEY_TYPE = attrib_KEY_TYPE;
    }
    
    public static String getATTRIB_KEY_DEFAULT() {
        return JmxBuilderTools.ATTRIB_KEY_DEFAULT;
    }
    
    public static void setATTRIB_KEY_DEFAULT(final String attrib_KEY_DEFAULT) {
        JmxBuilderTools.ATTRIB_KEY_DEFAULT = attrib_KEY_DEFAULT;
    }
    
    public static String getJMX_KEY() {
        return JmxBuilderTools.JMX_KEY;
    }
    
    public static void setJMX_KEY(final String jmx_KEY) {
        JmxBuilderTools.JMX_KEY = jmx_KEY;
    }
    
    public static String getDESC_KEY() {
        return JmxBuilderTools.DESC_KEY;
    }
    
    public static void setDESC_KEY(final String desc_KEY) {
        JmxBuilderTools.DESC_KEY = desc_KEY;
    }
    
    public static String getDESC_KEY_MBEAN_RESOURCE() {
        return JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE;
    }
    
    public static void setDESC_KEY_MBEAN_RESOURCE(final String desc_KEY_MBEAN_RESOURCE) {
        JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE = desc_KEY_MBEAN_RESOURCE;
    }
    
    public static String getDESC_KEY_MBEAN_RESOURCE_TYPE() {
        return JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE_TYPE;
    }
    
    public static void setDESC_KEY_MBEAN_RESOURCE_TYPE(final String desc_KEY_MBEAN_RESOURCE_TYPE) {
        JmxBuilderTools.DESC_KEY_MBEAN_RESOURCE_TYPE = desc_KEY_MBEAN_RESOURCE_TYPE;
    }
    
    public static String getDESC_KEY_MBEAN_ATTRIBS() {
        return JmxBuilderTools.DESC_KEY_MBEAN_ATTRIBS;
    }
    
    public static void setDESC_KEY_MBEAN_ATTRIBS(final String desc_KEY_MBEAN_ATTRIBS) {
        JmxBuilderTools.DESC_KEY_MBEAN_ATTRIBS = desc_KEY_MBEAN_ATTRIBS;
    }
    
    public static String getDESC_KEY_MBEAN_OPS() {
        return JmxBuilderTools.DESC_KEY_MBEAN_OPS;
    }
    
    public static void setDESC_KEY_MBEAN_OPS(final String desc_KEY_MBEAN_OPS) {
        JmxBuilderTools.DESC_KEY_MBEAN_OPS = desc_KEY_MBEAN_OPS;
    }
    
    public static String getDESC_KEY_MBEAN_CTORS() {
        return JmxBuilderTools.DESC_KEY_MBEAN_CTORS;
    }
    
    public static void setDESC_KEY_MBEAN_CTORS(final String desc_KEY_MBEAN_CTORS) {
        JmxBuilderTools.DESC_KEY_MBEAN_CTORS = desc_KEY_MBEAN_CTORS;
    }
    
    public static String getDESC_KEY_MBEAN_NOTES() {
        return JmxBuilderTools.DESC_KEY_MBEAN_NOTES;
    }
    
    public static void setDESC_KEY_MBEAN_NOTES(final String desc_KEY_MBEAN_NOTES) {
        JmxBuilderTools.DESC_KEY_MBEAN_NOTES = desc_KEY_MBEAN_NOTES;
    }
    
    public static String getDESC_KEY_NAME() {
        return JmxBuilderTools.DESC_KEY_NAME;
    }
    
    public static void setDESC_KEY_NAME(final String desc_KEY_NAME) {
        JmxBuilderTools.DESC_KEY_NAME = desc_KEY_NAME;
    }
    
    public static String getDESC_KEY_JMX_NAME() {
        return JmxBuilderTools.DESC_KEY_JMX_NAME;
    }
    
    public static void setDESC_KEY_JMX_NAME(final String desc_KEY_JMX_NAME) {
        JmxBuilderTools.DESC_KEY_JMX_NAME = desc_KEY_JMX_NAME;
    }
    
    public static String getDESC_KEY_DISPLAY_NAME() {
        return JmxBuilderTools.DESC_KEY_DISPLAY_NAME;
    }
    
    public static void setDESC_KEY_DISPLAY_NAME(final String desc_KEY_DISPLAY_NAME) {
        JmxBuilderTools.DESC_KEY_DISPLAY_NAME = desc_KEY_DISPLAY_NAME;
    }
    
    public static String getDESC_KEY_TYPE() {
        return JmxBuilderTools.DESC_KEY_TYPE;
    }
    
    public static void setDESC_KEY_TYPE(final String desc_KEY_TYPE) {
        JmxBuilderTools.DESC_KEY_TYPE = desc_KEY_TYPE;
    }
    
    public static String getDESC_KEY_GETMETHOD() {
        return JmxBuilderTools.DESC_KEY_GETMETHOD;
    }
    
    public static void setDESC_KEY_GETMETHOD(final String desc_KEY_GETMETHOD) {
        JmxBuilderTools.DESC_KEY_GETMETHOD = desc_KEY_GETMETHOD;
    }
    
    public static String getDESC_KEY_SETMETHOD() {
        return JmxBuilderTools.DESC_KEY_SETMETHOD;
    }
    
    public static void setDESC_KEY_SETMETHOD(final String desc_KEY_SETMETHOD) {
        JmxBuilderTools.DESC_KEY_SETMETHOD = desc_KEY_SETMETHOD;
    }
    
    public static String getDESC_KEY_EVENT_TYPE() {
        return JmxBuilderTools.DESC_KEY_EVENT_TYPE;
    }
    
    public static void setDESC_KEY_EVENT_TYPE(final String desc_KEY_EVENT_TYPE) {
        JmxBuilderTools.DESC_KEY_EVENT_TYPE = desc_KEY_EVENT_TYPE;
    }
    
    public static String getDESC_KEY_EVENT_NAME() {
        return JmxBuilderTools.DESC_KEY_EVENT_NAME;
    }
    
    public static void setDESC_KEY_EVENT_NAME(final String desc_KEY_EVENT_NAME) {
        JmxBuilderTools.DESC_KEY_EVENT_NAME = desc_KEY_EVENT_NAME;
    }
    
    public static String getDESC_KEY_EVENT_SOURCE() {
        return JmxBuilderTools.DESC_KEY_EVENT_SOURCE;
    }
    
    public static void setDESC_KEY_EVENT_SOURCE(final String desc_KEY_EVENT_SOURCE) {
        JmxBuilderTools.DESC_KEY_EVENT_SOURCE = desc_KEY_EVENT_SOURCE;
    }
    
    public static String getDESC_KEY_EVENT_MESSAGE() {
        return JmxBuilderTools.DESC_KEY_EVENT_MESSAGE;
    }
    
    public static void setDESC_KEY_EVENT_MESSAGE(final String desc_KEY_EVENT_MESSAGE) {
        JmxBuilderTools.DESC_KEY_EVENT_MESSAGE = desc_KEY_EVENT_MESSAGE;
    }
    
    public static String getDESC_VAL_TYPE_ATTRIB() {
        return JmxBuilderTools.DESC_VAL_TYPE_ATTRIB;
    }
    
    public static void setDESC_VAL_TYPE_ATTRIB(final String desc_VAL_TYPE_ATTRIB) {
        JmxBuilderTools.DESC_VAL_TYPE_ATTRIB = desc_VAL_TYPE_ATTRIB;
    }
    
    public static String getDESC_VAL_TYPE_GETTER() {
        return JmxBuilderTools.DESC_VAL_TYPE_GETTER;
    }
    
    public static void setDESC_VAL_TYPE_GETTER(final String desc_VAL_TYPE_GETTER) {
        JmxBuilderTools.DESC_VAL_TYPE_GETTER = desc_VAL_TYPE_GETTER;
    }
    
    public static String getDESC_VAL_TYPE_SETTER() {
        return JmxBuilderTools.DESC_VAL_TYPE_SETTER;
    }
    
    public static void setDESC_VAL_TYPE_SETTER(final String desc_VAL_TYPE_SETTER) {
        JmxBuilderTools.DESC_VAL_TYPE_SETTER = desc_VAL_TYPE_SETTER;
    }
    
    public static String getDESC_VAL_TYPE_OP() {
        return JmxBuilderTools.DESC_VAL_TYPE_OP;
    }
    
    public static void setDESC_VAL_TYPE_OP(final String desc_VAL_TYPE_OP) {
        JmxBuilderTools.DESC_VAL_TYPE_OP = desc_VAL_TYPE_OP;
    }
    
    public static String getDESC_VAL_TYPE_NOTIFICATION() {
        return JmxBuilderTools.DESC_VAL_TYPE_NOTIFICATION;
    }
    
    public static void setDESC_VAL_TYPE_NOTIFICATION(final String desc_VAL_TYPE_NOTIFICATION) {
        JmxBuilderTools.DESC_VAL_TYPE_NOTIFICATION = desc_VAL_TYPE_NOTIFICATION;
    }
    
    public static String getDESC_VAL_TYPE_CTOR() {
        return JmxBuilderTools.DESC_VAL_TYPE_CTOR;
    }
    
    public static void setDESC_VAL_TYPE_CTOR(final String desc_VAL_TYPE_CTOR) {
        JmxBuilderTools.DESC_VAL_TYPE_CTOR = desc_VAL_TYPE_CTOR;
    }
    
    public static String getDESC_VAL_TYPE_MBEAN() {
        return JmxBuilderTools.DESC_VAL_TYPE_MBEAN;
    }
    
    public static void setDESC_VAL_TYPE_MBEAN(final String desc_VAL_TYPE_MBEAN) {
        JmxBuilderTools.DESC_VAL_TYPE_MBEAN = desc_VAL_TYPE_MBEAN;
    }
    
    public static String getDESC_KEY_ROLE() {
        return JmxBuilderTools.DESC_KEY_ROLE;
    }
    
    public static void setDESC_KEY_ROLE(final String desc_KEY_ROLE) {
        JmxBuilderTools.DESC_KEY_ROLE = desc_KEY_ROLE;
    }
    
    public static String getDESC_KEY_READABLE() {
        return JmxBuilderTools.DESC_KEY_READABLE;
    }
    
    public static void setDESC_KEY_READABLE(final String desc_KEY_READABLE) {
        JmxBuilderTools.DESC_KEY_READABLE = desc_KEY_READABLE;
    }
    
    public static String getDESC_KEY_WRITABLE() {
        return JmxBuilderTools.DESC_KEY_WRITABLE;
    }
    
    public static void setDESC_KEY_WRITABLE(final String desc_KEY_WRITABLE) {
        JmxBuilderTools.DESC_KEY_WRITABLE = desc_KEY_WRITABLE;
    }
    
    public static String getDESC_KEY_SIGNATURE() {
        return JmxBuilderTools.DESC_KEY_SIGNATURE;
    }
    
    public static void setDESC_KEY_SIGNATURE(final String desc_KEY_SIGNATURE) {
        JmxBuilderTools.DESC_KEY_SIGNATURE = desc_KEY_SIGNATURE;
    }
    
    public static String getEVENT_KEY_CONTEXTS() {
        return JmxBuilderTools.EVENT_KEY_CONTEXTS;
    }
    
    public static void setEVENT_KEY_CONTEXTS(final String event_KEY_CONTEXTS) {
        JmxBuilderTools.EVENT_KEY_CONTEXTS = event_KEY_CONTEXTS;
    }
    
    public static String getEVENT_KEY_CALLBACK() {
        return JmxBuilderTools.EVENT_KEY_CALLBACK;
    }
    
    public static void setEVENT_KEY_CALLBACK(final String event_KEY_CALLBACK) {
        JmxBuilderTools.EVENT_KEY_CALLBACK = event_KEY_CALLBACK;
    }
    
    public static String getEVENT_KEY_CALLBACK_RESULT() {
        return JmxBuilderTools.EVENT_KEY_CALLBACK_RESULT;
    }
    
    public static void setEVENT_KEY_CALLBACK_RESULT(final String event_KEY_CALLBACK_RESULT) {
        JmxBuilderTools.EVENT_KEY_CALLBACK_RESULT = event_KEY_CALLBACK_RESULT;
    }
    
    public static String getEVENT_KEY_METHOD() {
        return JmxBuilderTools.EVENT_KEY_METHOD;
    }
    
    public static void setEVENT_KEY_METHOD(final String event_KEY_METHOD) {
        JmxBuilderTools.EVENT_KEY_METHOD = event_KEY_METHOD;
    }
    
    public static String getEVENT_KEY_METHOD_RESULT() {
        return JmxBuilderTools.EVENT_KEY_METHOD_RESULT;
    }
    
    public static void setEVENT_KEY_METHOD_RESULT(final String event_KEY_METHOD_RESULT) {
        JmxBuilderTools.EVENT_KEY_METHOD_RESULT = event_KEY_METHOD_RESULT;
    }
    
    public static String getEVENT_KEY_ISATTRIB() {
        return JmxBuilderTools.EVENT_KEY_ISATTRIB;
    }
    
    public static void setEVENT_KEY_ISATTRIB(final String event_KEY_ISATTRIB) {
        JmxBuilderTools.EVENT_KEY_ISATTRIB = event_KEY_ISATTRIB;
    }
    
    public static String getEVENT_KEY_NAME() {
        return JmxBuilderTools.EVENT_KEY_NAME;
    }
    
    public static void setEVENT_KEY_NAME(final String event_KEY_NAME) {
        JmxBuilderTools.EVENT_KEY_NAME = event_KEY_NAME;
    }
    
    public static String getEVENT_KEY_MESSAGE() {
        return JmxBuilderTools.EVENT_KEY_MESSAGE;
    }
    
    public static void setEVENT_KEY_MESSAGE(final String event_KEY_MESSAGE) {
        JmxBuilderTools.EVENT_KEY_MESSAGE = event_KEY_MESSAGE;
    }
    
    public static String getEVENT_KEY_TYPE() {
        return JmxBuilderTools.EVENT_KEY_TYPE;
    }
    
    public static void setEVENT_KEY_TYPE(final String event_KEY_TYPE) {
        JmxBuilderTools.EVENT_KEY_TYPE = event_KEY_TYPE;
    }
    
    public static String getEVENT_KEY_NODE_TYPE() {
        return JmxBuilderTools.EVENT_KEY_NODE_TYPE;
    }
    
    public static void setEVENT_KEY_NODE_TYPE(final String event_KEY_NODE_TYPE) {
        JmxBuilderTools.EVENT_KEY_NODE_TYPE = event_KEY_NODE_TYPE;
    }
    
    public static String getEVENT_VAL_NODETYPE_BROADCASTER() {
        return JmxBuilderTools.EVENT_VAL_NODETYPE_BROADCASTER;
    }
    
    public static void setEVENT_VAL_NODETYPE_BROADCASTER(final String event_VAL_NODETYPE_BROADCASTER) {
        JmxBuilderTools.EVENT_VAL_NODETYPE_BROADCASTER = event_VAL_NODETYPE_BROADCASTER;
    }
    
    public static String getEVENT_VAL_NODETYPE_LISTENER() {
        return JmxBuilderTools.EVENT_VAL_NODETYPE_LISTENER;
    }
    
    public static void setEVENT_VAL_NODETYPE_LISTENER(final String event_VAL_NODETYPE_LISTENER) {
        JmxBuilderTools.EVENT_VAL_NODETYPE_LISTENER = event_VAL_NODETYPE_LISTENER;
    }
    
    public static String getEVENT_KEY_TARGETS() {
        return JmxBuilderTools.EVENT_KEY_TARGETS;
    }
    
    public static void setEVENT_KEY_TARGETS(final String event_KEY_TARGETS) {
        JmxBuilderTools.EVENT_KEY_TARGETS = event_KEY_TARGETS;
    }
    
    public static Map getPRIMITIVE_TYPES() {
        return JmxBuilderTools.PRIMITIVE_TYPES;
    }
    
    public static void setPRIMITIVE_TYPES(final Map primitive_TYPES) {
        JmxBuilderTools.PRIMITIVE_TYPES = primitive_TYPES;
    }
    
    public static Map getTYPE_MAP() {
        return JmxBuilderTools.TYPE_MAP;
    }
    
    public static void setTYPE_MAP(final Map type_MAP) {
        JmxBuilderTools.TYPE_MAP = type_MAP;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[91];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBuilderTools(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBuilderTools.$callSiteArray == null || ($createCallSiteArray = JmxBuilderTools.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBuilderTools.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Long() {
        Class $class$java$lang$Long;
        if (($class$java$lang$Long = JmxBuilderTools.$class$java$lang$Long) == null) {
            $class$java$lang$Long = (JmxBuilderTools.$class$java$lang$Long = class$("java.lang.Long"));
        }
        return $class$java$lang$Long;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServerFactory() {
        Class $class$javax$management$MBeanServerFactory;
        if (($class$javax$management$MBeanServerFactory = JmxBuilderTools.$class$javax$management$MBeanServerFactory) == null) {
            $class$javax$management$MBeanServerFactory = (JmxBuilderTools.$class$javax$management$MBeanServerFactory = class$("javax.management.MBeanServerFactory"));
        }
        return $class$javax$management$MBeanServerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Character() {
        Class $class$java$lang$Character;
        if (($class$java$lang$Character = JmxBuilderTools.$class$java$lang$Character) == null) {
            $class$java$lang$Character = (JmxBuilderTools.$class$java$lang$Character = class$("java.lang.Character"));
        }
        return $class$java$lang$Character;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxBuilderTools.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxBuilderTools.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderModelMBean() {
        Class $class$groovy$jmx$builder$JmxBuilderModelMBean;
        if (($class$groovy$jmx$builder$JmxBuilderModelMBean = JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderModelMBean) == null) {
            $class$groovy$jmx$builder$JmxBuilderModelMBean = (JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderModelMBean = class$("groovy.jmx.builder.JmxBuilderModelMBean"));
        }
        return $class$groovy$jmx$builder$JmxBuilderModelMBean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanInfoManager() {
        Class $class$groovy$jmx$builder$JmxBeanInfoManager;
        if (($class$groovy$jmx$builder$JmxBeanInfoManager = JmxBuilderTools.$class$groovy$jmx$builder$JmxBeanInfoManager) == null) {
            $class$groovy$jmx$builder$JmxBeanInfoManager = (JmxBuilderTools.$class$groovy$jmx$builder$JmxBeanInfoManager = class$("groovy.jmx.builder.JmxBeanInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxBeanInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$java$math$BigDecimal() {
        Class $class$java$math$BigDecimal;
        if (($class$java$math$BigDecimal = JmxBuilderTools.$class$java$math$BigDecimal) == null) {
            $class$java$math$BigDecimal = (JmxBuilderTools.$class$java$math$BigDecimal = class$("java.math.BigDecimal"));
        }
        return $class$java$math$BigDecimal;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$DynamicMBean() {
        Class $class$javax$management$DynamicMBean;
        if (($class$javax$management$DynamicMBean = JmxBuilderTools.$class$javax$management$DynamicMBean) == null) {
            $class$javax$management$DynamicMBean = (JmxBuilderTools.$class$javax$management$DynamicMBean = class$("javax.management.DynamicMBean"));
        }
        return $class$javax$management$DynamicMBean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$GroovyMBean() {
        Class $class$groovy$util$GroovyMBean;
        if (($class$groovy$util$GroovyMBean = JmxBuilderTools.$class$groovy$util$GroovyMBean) == null) {
            $class$groovy$util$GroovyMBean = (JmxBuilderTools.$class$groovy$util$GroovyMBean = class$("groovy.util.GroovyMBean"));
        }
        return $class$groovy$util$GroovyMBean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServerConnection() {
        Class $class$javax$management$MBeanServerConnection;
        if (($class$javax$management$MBeanServerConnection = JmxBuilderTools.$class$javax$management$MBeanServerConnection) == null) {
            $class$javax$management$MBeanServerConnection = (JmxBuilderTools.$class$javax$management$MBeanServerConnection = class$("javax.management.MBeanServerConnection"));
        }
        return $class$javax$management$MBeanServerConnection;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JmxBuilderTools.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JmxBuilderTools.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Class() {
        Class array$$class$java$lang$Class;
        if ((array$$class$java$lang$Class = JmxBuilderTools.array$$class$java$lang$Class) == null) {
            array$$class$java$lang$Class = (JmxBuilderTools.array$$class$java$lang$Class = class$("[Ljava.lang.Class;"));
        }
        return array$$class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Short() {
        Class $class$java$lang$Short;
        if (($class$java$lang$Short = JmxBuilderTools.$class$java$lang$Short) == null) {
            $class$java$lang$Short = (JmxBuilderTools.$class$java$lang$Short = class$("java.lang.Short"));
        }
        return $class$java$lang$Short;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = JmxBuilderTools.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (JmxBuilderTools.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxBuilderTools.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxBuilderTools.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Double() {
        Class $class$java$lang$Double;
        if (($class$java$lang$Double = JmxBuilderTools.$class$java$lang$Double) == null) {
            $class$java$lang$Double = (JmxBuilderTools.$class$java$lang$Double = class$("java.lang.Double"));
        }
        return $class$java$lang$Double;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Byte() {
        Class $class$java$lang$Byte;
        if (($class$java$lang$Byte = JmxBuilderTools.$class$java$lang$Byte) == null) {
            $class$java$lang$Byte = (JmxBuilderTools.$class$java$lang$Byte = class$("java.lang.Byte"));
        }
        return $class$java$lang$Byte;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBuilderTools.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBuilderTools.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxBuilderTools.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxBuilderTools.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Date() {
        Class $class$java$util$Date;
        if (($class$java$util$Date = JmxBuilderTools.$class$java$util$Date) == null) {
            $class$java$util$Date = (JmxBuilderTools.$class$java$util$Date = class$("java.util.Date"));
        }
        return $class$java$util$Date;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$management$ManagementFactory() {
        Class $class$java$lang$management$ManagementFactory;
        if (($class$java$lang$management$ManagementFactory = JmxBuilderTools.$class$java$lang$management$ManagementFactory) == null) {
            $class$java$lang$management$ManagementFactory = (JmxBuilderTools.$class$java$lang$management$ManagementFactory = class$("java.lang.management.ManagementFactory"));
        }
        return $class$java$lang$management$ManagementFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Float() {
        Class $class$java$lang$Float;
        if (($class$java$lang$Float = JmxBuilderTools.$class$java$lang$Float) == null) {
            $class$java$lang$Float = (JmxBuilderTools.$class$java$lang$Float = class$("java.lang.Float"));
        }
        return $class$java$lang$Float;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBuilderTools.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
    }
    
    private static /* synthetic */ Class $get$$class$java$math$BigInteger() {
        Class $class$java$math$BigInteger;
        if (($class$java$math$BigInteger = JmxBuilderTools.$class$java$math$BigInteger) == null) {
            $class$java$math$BigInteger = (JmxBuilderTools.$class$java$math$BigInteger = class$("java.math.BigInteger"));
        }
        return $class$java$math$BigInteger;
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
