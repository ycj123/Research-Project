// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.management.modelmbean.DescriptorSupport;
import org.codehaus.groovy.runtime.ArrayUtil;
import groovy.lang.MetaProperty;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JmxAttributeInfoManager implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202782;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$modelmbean$DescriptorSupport;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    private static /* synthetic */ Class $class$groovy$lang$MetaProperty;
    
    public JmxAttributeInfoManager() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static List<ModelMBeanAttributeInfo> getAttributeInfosFromMap(final Map metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(metaMap)) {
            return (List<ModelMBeanAttributeInfo>)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$List());
        }
        final Object attribs = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        $getCallSiteArray[0].call(metaMap, new JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1($get$$class$groovy$jmx$builder$JmxAttributeInfoManager(), $get$$class$groovy$jmx$builder$JmxAttributeInfoManager(), (Reference<Object>)attribs));
        return (List<ModelMBeanAttributeInfo>)ScriptBytecodeAdapter.castToType(((Reference<Object>)attribs).get(), $get$$class$java$util$List());
    }
    
    public static ModelMBeanAttributeInfo getAttributeInfoFromMap(final Map map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(map)) {
            return (ModelMBeanAttributeInfo)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo());
        }
        final MetaProperty prop = (MetaProperty)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(map, "property"), $get$$class$groovy$lang$MetaProperty());
        if (!DefaultTypeTransformation.booleanUnbox(prop)) {
            throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable generate ModelMBeanAttributeInfo, missing property object.");
        }
        final DescriptorSupport desc = (DescriptorSupport)$getCallSiteArray[3].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport());
        $getCallSiteArray[4].call(desc, $getCallSiteArray[5].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[6].call(map, $getCallSiteArray[7].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        $getCallSiteArray[8].call(desc, $getCallSiteArray[9].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[10].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[11].call(map, $getCallSiteArray[12].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())))) {
            object = Boolean.TRUE;
        }
        final Boolean isReadable = (Boolean)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$Boolean());
        Object object2;
        if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[13].call(map, $getCallSiteArray[14].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())))) {
            object2 = Boolean.FALSE;
        }
        final Boolean isWritable = (Boolean)ScriptBytecodeAdapter.castToType(object2, $get$$class$java$lang$Boolean());
        $getCallSiteArray[15].call(desc, $getCallSiteArray[16].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), isReadable);
        $getCallSiteArray[17].call(desc, $getCallSiteArray[18].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), isWritable);
        if (DefaultTypeTransformation.booleanUnbox(isReadable)) {
            $getCallSiteArray[19].call(desc, $getCallSiteArray[20].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[21].call(map, $getCallSiteArray[22].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        }
        if (DefaultTypeTransformation.booleanUnbox(isWritable)) {
            $getCallSiteArray[23].call(desc, $getCallSiteArray[24].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[25].call(map, $getCallSiteArray[26].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        }
        $getCallSiteArray[27].call(desc, "default", $getCallSiteArray[28].call(map, "defaultValue"));
        $getCallSiteArray[29].call(desc, $getCallSiteArray[30].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[31].call(map, $getCallSiteArray[32].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())));
        final ModelMBeanAttributeInfo attrib = (ModelMBeanAttributeInfo)$getCallSiteArray[33].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo(), ArrayUtil.createArray(ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[34].call(desc, $getCallSiteArray[35].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), $getCallSiteArray[36].call($getCallSiteArray[37].callGetProperty(prop)), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[38].call(desc, $getCallSiteArray[39].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[40].call(desc, $getCallSiteArray[41].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$Boolean()), Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[42].call(desc, $getCallSiteArray[43].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$Boolean()), Boolean.TYPE), ($getCallSiteArray[44].callGetProperty(prop) instanceof Boolean) ? Boolean.TRUE : Boolean.FALSE));
        $getCallSiteArray[45].call(attrib, desc);
        return (ModelMBeanAttributeInfo)ScriptBytecodeAdapter.castToType(attrib, $get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxAttributeInfoManager()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxAttributeInfoManager.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxAttributeInfoManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxAttributeInfoManager.__timeStamp__239_neverHappen1292524202782 = 0L;
        JmxAttributeInfoManager.__timeStamp = 1292524202782L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[46];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxAttributeInfoManager(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxAttributeInfoManager.$callSiteArray == null || ($createCallSiteArray = JmxAttributeInfoManager.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxAttributeInfoManager.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$DescriptorSupport() {
        Class $class$javax$management$modelmbean$DescriptorSupport;
        if (($class$javax$management$modelmbean$DescriptorSupport = JmxAttributeInfoManager.$class$javax$management$modelmbean$DescriptorSupport) == null) {
            $class$javax$management$modelmbean$DescriptorSupport = (JmxAttributeInfoManager.$class$javax$management$modelmbean$DescriptorSupport = class$("javax.management.modelmbean.DescriptorSupport"));
        }
        return $class$javax$management$modelmbean$DescriptorSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxAttributeInfoManager.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxAttributeInfoManager.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxAttributeInfoManager() {
        Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
        if (($class$groovy$jmx$builder$JmxAttributeInfoManager = JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxAttributeInfoManager) == null) {
            $class$groovy$jmx$builder$JmxAttributeInfoManager = (JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxAttributeInfoManager = class$("groovy.jmx.builder.JmxAttributeInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxAttributeInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxAttributeInfoManager.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxAttributeInfoManager.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
        if (($class$javax$management$modelmbean$ModelMBeanAttributeInfo = JmxAttributeInfoManager.$class$javax$management$modelmbean$ModelMBeanAttributeInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanAttributeInfo = (JmxAttributeInfoManager.$class$javax$management$modelmbean$ModelMBeanAttributeInfo = class$("javax.management.modelmbean.ModelMBeanAttributeInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = JmxAttributeInfoManager.$class$java$util$List) == null) {
            $class$java$util$List = (JmxAttributeInfoManager.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxAttributeInfoManager.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxAttributeInfoManager.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxAttributeInfoManager.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaProperty() {
        Class $class$groovy$lang$MetaProperty;
        if (($class$groovy$lang$MetaProperty = JmxAttributeInfoManager.$class$groovy$lang$MetaProperty) == null) {
            $class$groovy$lang$MetaProperty = (JmxAttributeInfoManager.$class$groovy$lang$MetaProperty = class$("groovy.lang.MetaProperty"));
        }
        return $class$groovy$lang$MetaProperty;
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
