// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaProperty;
import org.codehaus.groovy.runtime.ArrayUtil;
import groovy.lang.MetaMethod;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.Descriptor;
import javax.management.MBeanParameterInfo;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.management.modelmbean.ModelMBeanConstructorInfo;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JmxOperationInfoManager implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202884;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$modelmbean$DescriptorSupport;
    private static /* synthetic */ Class array$$class$javax$management$MBeanParameterInfo;
    private static /* synthetic */ Class $class$java$lang$Void;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxOperationInfoManager;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanOperationInfo;
    private static /* synthetic */ Class $class$javax$management$MBeanParameterInfo;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$groovy$lang$MetaMethod;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxOperationInfoManager() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static List<ModelMBeanConstructorInfo> getConstructorInfosFromMap(final Map metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(metaMap)) {
            return (List<ModelMBeanConstructorInfo>)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$List());
        }
        final Object ctors = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        $getCallSiteArray[0].call(metaMap, new JmxOperationInfoManager$_getConstructorInfosFromMap_closure1($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $get$$class$groovy$jmx$builder$JmxOperationInfoManager(), (Reference<Object>)ctors));
        return (List<ModelMBeanConstructorInfo>)ScriptBytecodeAdapter.castToType(((Reference<Object>)ctors).get(), $get$$class$java$util$List());
    }
    
    public static ModelMBeanConstructorInfo getConstructorInfoFromMap(final Map map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(map)) {
            return (ModelMBeanConstructorInfo)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo());
        }
        final Object ctor = $getCallSiteArray[1].call(map, "constructor");
        if (!DefaultTypeTransformation.booleanUnbox(ctor)) {
            throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable generate ModelMBeanConstructorInfo, missing constructor reference.");
        }
        final MBeanParameterInfo[] params = (MBeanParameterInfo[])ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callStatic($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $getCallSiteArray[4].call(map, "params")), $get$array$$class$javax$management$MBeanParameterInfo());
        final Descriptor desc = (Descriptor)new Reference($getCallSiteArray[5].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport()));
        $getCallSiteArray[6].call(((Reference<Object>)desc).get(), $getCallSiteArray[7].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[8].call(map, $getCallSiteArray[9].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[10].call(((Reference<Object>)desc).get(), $getCallSiteArray[11].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[12].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[13].call(((Reference<Object>)desc).get(), $getCallSiteArray[14].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[15].call(map, $getCallSiteArray[16].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[17].call(((Reference<Object>)desc).get(), $getCallSiteArray[18].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[19].call(map, $getCallSiteArray[20].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[21].call(map, new JmxOperationInfoManager$_getConstructorInfoFromMap_closure2($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $get$$class$groovy$jmx$builder$JmxOperationInfoManager(), (Reference<Object>)desc));
        final ModelMBeanConstructorInfo info = (ModelMBeanConstructorInfo)$getCallSiteArray[22].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo(), $getCallSiteArray[23].callGetProperty(ctor), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[24].call(((Reference<Object>)desc).get(), $getCallSiteArray[25].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), params, ((Reference<Object>)desc).get());
        return (ModelMBeanConstructorInfo)ScriptBytecodeAdapter.castToType(info, $get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo());
    }
    
    public static List<ModelMBeanOperationInfo> getOperationInfosFromMap(final Map metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(metaMap)) {
            return (List<ModelMBeanOperationInfo>)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$List());
        }
        final Object ops = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        $getCallSiteArray[26].call(metaMap, new JmxOperationInfoManager$_getOperationInfosFromMap_closure3($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $get$$class$groovy$jmx$builder$JmxOperationInfoManager(), (Reference<Object>)ops));
        return (List<ModelMBeanOperationInfo>)ScriptBytecodeAdapter.castToType(((Reference<Object>)ops).get(), $get$$class$java$util$List());
    }
    
    public static ModelMBeanOperationInfo getOperationInfoFromMap(final Map map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(map)) {
            return (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
        }
        final MetaMethod method = (MetaMethod)ScriptBytecodeAdapter.castToType($getCallSiteArray[27].call(map, "method"), $get$$class$groovy$lang$MetaMethod());
        if (!DefaultTypeTransformation.booleanUnbox(method)) {
            throw (Throwable)$getCallSiteArray[28].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable to generate ModelMBeanOperationInfo, missing method reference.");
        }
        final MBeanParameterInfo[] params = (MBeanParameterInfo[])ScriptBytecodeAdapter.castToType($getCallSiteArray[29].callStatic($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $getCallSiteArray[30].call(map, "params")), $get$array$$class$javax$management$MBeanParameterInfo());
        final Descriptor desc = (Descriptor)$getCallSiteArray[31].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport());
        $getCallSiteArray[32].call(desc, $getCallSiteArray[33].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[34].call(map, $getCallSiteArray[35].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[36].call(desc, $getCallSiteArray[37].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[38].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[39].call(desc, $getCallSiteArray[40].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[41].call(map, $getCallSiteArray[42].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[43].call(desc, $getCallSiteArray[44].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[45].call(map, $getCallSiteArray[46].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        final ModelMBeanOperationInfo info = (ModelMBeanOperationInfo)$getCallSiteArray[47].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo(), ArrayUtil.createArray($getCallSiteArray[48].callGetProperty(method), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[49].call(desc, $getCallSiteArray[50].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), params, $getCallSiteArray[51].callGetProperty($getCallSiteArray[52].callGetProperty(method)), $getCallSiteArray[53].callGetProperty($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo()), desc));
        return (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType(info, $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
    }
    
    public static List<MBeanParameterInfo> buildParamInfosFromMaps(final Map metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(metaMap) && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[54].call(metaMap), JmxOperationInfoManager.$const$0)) ? Boolean.FALSE : Boolean.TRUE)) {
            return (List<MBeanParameterInfo>)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$List());
        }
        final List result = (List)new Reference($getCallSiteArray[55].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[56].call(metaMap)));
        $getCallSiteArray[57].call(metaMap, new JmxOperationInfoManager$_buildParamInfosFromMaps_closure4($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $get$$class$groovy$jmx$builder$JmxOperationInfoManager(), (Reference<Object>)result));
        return (List<MBeanParameterInfo>)ScriptBytecodeAdapter.castToType(((Reference)result).get(), $get$$class$java$util$List());
    }
    
    public static ModelMBeanOperationInfo createGetterOperationInfoFromProperty(final MetaProperty prop) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(prop, null)) {
            return (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
        }
        final Descriptor desc = (Descriptor)$getCallSiteArray[58].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport());
        final String opType = (String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[59].call($getCallSiteArray[60].call($getCallSiteArray[61].callGetProperty(prop)), "Boolean")) ? "is" : "get", $get$$class$java$lang$String());
        final String name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[62].call(opType, $getCallSiteArray[63].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[64].callGetProperty(prop))), $get$$class$java$lang$String());
        $getCallSiteArray[65].call(desc, $getCallSiteArray[66].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), name);
        $getCallSiteArray[67].call(desc, $getCallSiteArray[68].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[69].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[70].call(desc, $getCallSiteArray[71].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[72].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[73].call(desc, $getCallSiteArray[74].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[75].call(new GStringImpl(new Object[] { $getCallSiteArray[76].callGetProperty(prop) }, new String[] { "Getter for attribute ", "" })));
        final ModelMBeanOperationInfo op = (ModelMBeanOperationInfo)$getCallSiteArray[77].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo(), ArrayUtil.createArray(name, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[78].call(desc, $getCallSiteArray[79].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), null, $getCallSiteArray[80].call($getCallSiteArray[81].callGetProperty(prop)), $getCallSiteArray[82].callGetProperty($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo()), desc));
        return (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType(op, $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
    }
    
    public static ModelMBeanOperationInfo createSetterOperationInfoFromProperty(final MetaProperty prop) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Descriptor desc = (Descriptor)$getCallSiteArray[83].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport());
        final String name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[84].call("set", $getCallSiteArray[85].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[86].callGetProperty(prop))), $get$$class$java$lang$String());
        $getCallSiteArray[87].call(desc, $getCallSiteArray[88].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), name);
        $getCallSiteArray[89].call(desc, $getCallSiteArray[90].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[91].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[92].call(desc, $getCallSiteArray[93].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[94].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[95].call(desc, $getCallSiteArray[96].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[97].call(new GStringImpl(new Object[] { $getCallSiteArray[98].callGetProperty(prop) }, new String[] { "Getter for attribute ", "" })));
        final MBeanParameterInfo[] params = (MBeanParameterInfo[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[99].callConstructor($get$$class$javax$management$MBeanParameterInfo(), $getCallSiteArray[100].call(new GStringImpl(new Object[] { $getCallSiteArray[101].call(prop) }, new String[] { "", "" })), $getCallSiteArray[102].callGetProperty($getCallSiteArray[103].callGetProperty(prop)), "Parameter for setter") }), $get$array$$class$javax$management$MBeanParameterInfo());
        final ModelMBeanOperationInfo op = (ModelMBeanOperationInfo)$getCallSiteArray[104].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo(), ArrayUtil.createArray(name, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[105].call(desc, $getCallSiteArray[106].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), params, $getCallSiteArray[107].callGetProperty($getCallSiteArray[108].callGetProperty($get$$class$java$lang$Void())), $getCallSiteArray[109].callGetProperty($get$$class$javax$management$modelmbean$ModelMBeanOperationInfo()), desc));
        return (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType(op, $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxOperationInfoManager()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxOperationInfoManager.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxOperationInfoManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxOperationInfoManager.__timeStamp__239_neverHappen1292524202884 = 0L;
        JmxOperationInfoManager.__timeStamp = 1292524202884L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[110];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxOperationInfoManager.$callSiteArray == null || ($createCallSiteArray = JmxOperationInfoManager.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxOperationInfoManager.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$DescriptorSupport() {
        Class $class$javax$management$modelmbean$DescriptorSupport;
        if (($class$javax$management$modelmbean$DescriptorSupport = JmxOperationInfoManager.$class$javax$management$modelmbean$DescriptorSupport) == null) {
            $class$javax$management$modelmbean$DescriptorSupport = (JmxOperationInfoManager.$class$javax$management$modelmbean$DescriptorSupport = class$("javax.management.modelmbean.DescriptorSupport"));
        }
        return $class$javax$management$modelmbean$DescriptorSupport;
    }
    
    private static /* synthetic */ Class $get$array$$class$javax$management$MBeanParameterInfo() {
        Class array$$class$javax$management$MBeanParameterInfo;
        if ((array$$class$javax$management$MBeanParameterInfo = JmxOperationInfoManager.array$$class$javax$management$MBeanParameterInfo) == null) {
            array$$class$javax$management$MBeanParameterInfo = (JmxOperationInfoManager.array$$class$javax$management$MBeanParameterInfo = class$("[Ljavax.management.MBeanParameterInfo;"));
        }
        return array$$class$javax$management$MBeanParameterInfo;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Void() {
        Class $class$java$lang$Void;
        if (($class$java$lang$Void = JmxOperationInfoManager.$class$java$lang$Void) == null) {
            $class$java$lang$Void = (JmxOperationInfoManager.$class$java$lang$Void = class$("java.lang.Void"));
        }
        return $class$java$lang$Void;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = JmxOperationInfoManager.$class$java$util$List) == null) {
            $class$java$util$List = (JmxOperationInfoManager.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxOperationInfoManager() {
        Class $class$groovy$jmx$builder$JmxOperationInfoManager;
        if (($class$groovy$jmx$builder$JmxOperationInfoManager = JmxOperationInfoManager.$class$groovy$jmx$builder$JmxOperationInfoManager) == null) {
            $class$groovy$jmx$builder$JmxOperationInfoManager = (JmxOperationInfoManager.$class$groovy$jmx$builder$JmxOperationInfoManager = class$("groovy.jmx.builder.JmxOperationInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxOperationInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxOperationInfoManager.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxOperationInfoManager.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxOperationInfoManager.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxOperationInfoManager.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
        if (($class$javax$management$modelmbean$ModelMBeanConstructorInfo = JmxOperationInfoManager.$class$javax$management$modelmbean$ModelMBeanConstructorInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanConstructorInfo = (JmxOperationInfoManager.$class$javax$management$modelmbean$ModelMBeanConstructorInfo = class$("javax.management.modelmbean.ModelMBeanConstructorInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanOperationInfo;
        if (($class$javax$management$modelmbean$ModelMBeanOperationInfo = JmxOperationInfoManager.$class$javax$management$modelmbean$ModelMBeanOperationInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanOperationInfo = (JmxOperationInfoManager.$class$javax$management$modelmbean$ModelMBeanOperationInfo = class$("javax.management.modelmbean.ModelMBeanOperationInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanOperationInfo;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanParameterInfo() {
        Class $class$javax$management$MBeanParameterInfo;
        if (($class$javax$management$MBeanParameterInfo = JmxOperationInfoManager.$class$javax$management$MBeanParameterInfo) == null) {
            $class$javax$management$MBeanParameterInfo = (JmxOperationInfoManager.$class$javax$management$MBeanParameterInfo = class$("javax.management.MBeanParameterInfo"));
        }
        return $class$javax$management$MBeanParameterInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxOperationInfoManager.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxOperationInfoManager.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = JmxOperationInfoManager.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (JmxOperationInfoManager.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaMethod() {
        Class $class$groovy$lang$MetaMethod;
        if (($class$groovy$lang$MetaMethod = JmxOperationInfoManager.$class$groovy$lang$MetaMethod) == null) {
            $class$groovy$lang$MetaMethod = (JmxOperationInfoManager.$class$groovy$lang$MetaMethod = class$("groovy.lang.MetaMethod"));
        }
        return $class$groovy$lang$MetaMethod;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxOperationInfoManager.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxOperationInfoManager.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
