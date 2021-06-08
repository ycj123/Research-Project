// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import javax.management.modelmbean.DescriptorSupport;
import org.codehaus.groovy.runtime.ArrayUtil;
import javax.management.modelmbean.ModelMBeanNotificationInfo;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.ModelMBeanConstructorInfo;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.management.modelmbean.ModelMBeanInfo;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.management.ObjectName;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JmxBeanInfoManager implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202792;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$modelmbean$DescriptorSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
    private static /* synthetic */ Class array$$class$javax$management$modelmbean$ModelMBeanOperationInfo;
    private static /* synthetic */ Class $class$javax$management$ObjectName;
    private static /* synthetic */ Class array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxOperationInfoManager;
    private static /* synthetic */ Class array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanInfoSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanInfoManager;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanInfo;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxBeanInfoManager() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static ObjectName buildDefaultObjectName(final String defaultDomain, final String defaultType, final Object object) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new GStringImpl(new Object[] { defaultDomain, defaultType, $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGetProperty(object)), $getCallSiteArray[2].call(object) }, new String[] { "", ":type=", ",name=", "@", "" });
        return (ObjectName)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callConstructor($get$$class$javax$management$ObjectName(), name), $get$$class$javax$management$ObjectName());
    }
    
    public static ModelMBeanInfo getModelMBeanInfoFromMap(final Map map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(map)) {
            throw (Throwable)$getCallSiteArray[4].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable to create default ModelMBeanInfo, missing meta map.");
        }
        final Object object = new Reference($getCallSiteArray[5].callGetProperty(map));
        if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)object).get())) {
            throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Unable to create default ModelMBeanInfo, missing target object.");
        }
        final Object attributes = $getCallSiteArray[7].call($get$$class$groovy$jmx$builder$JmxAttributeInfoManager(), $getCallSiteArray[8].callGetProperty(map));
        Object value;
        if (!DefaultTypeTransformation.booleanUnbox(value = $getCallSiteArray[9].call($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $getCallSiteArray[10].callGetProperty(map)))) {
            value = ScriptBytecodeAdapter.createList(new Object[0]);
        }
        final Object operations = new Reference(value);
        $getCallSiteArray[11].call(attributes, new JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), $get$$class$groovy$jmx$builder$JmxBeanInfoManager(), (Reference<Object>)operations, (Reference<Object>)object));
        final ModelMBeanAttributeInfo[] attribs = (ModelMBeanAttributeInfo[])ScriptBytecodeAdapter.castToType(attributes, $get$array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo());
        final ModelMBeanConstructorInfo[] ctors = (ModelMBeanConstructorInfo[])ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), $getCallSiteArray[13].callGetProperty(map)), $get$array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo());
        final ModelMBeanOperationInfo[] ops = (ModelMBeanOperationInfo[])ScriptBytecodeAdapter.castToType(((Reference<Object>)operations).get(), $get$array$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
        final ModelMBeanNotificationInfo[] notes = (ModelMBeanNotificationInfo[])ScriptBytecodeAdapter.castToType(null, $get$array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo());
        final DescriptorSupport desc = (DescriptorSupport)$getCallSiteArray[14].callConstructor($get$$class$javax$management$modelmbean$DescriptorSupport());
        $getCallSiteArray[15].call(desc, $getCallSiteArray[16].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[17].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()));
        $getCallSiteArray[18].call(desc, $getCallSiteArray[19].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[20].callGetProperty(map));
        $getCallSiteArray[21].call(desc, $getCallSiteArray[22].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[23].callGetProperty(map));
        return (ModelMBeanInfo)ScriptBytecodeAdapter.castToType($getCallSiteArray[24].callConstructor($get$$class$javax$management$modelmbean$ModelMBeanInfoSupport(), ArrayUtil.createArray(ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[25].callGetProperty($getCallSiteArray[26].call(((Reference<Object>)object).get())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[27].call(desc, $getCallSiteArray[28].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools())), $get$$class$java$lang$String()), $get$$class$java$lang$String()), attribs, ctors, ops, notes, desc)), $get$$class$javax$management$modelmbean$ModelMBeanInfo());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxBeanInfoManager()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxBeanInfoManager.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxBeanInfoManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxBeanInfoManager.__timeStamp__239_neverHappen1292524202792 = 0L;
        JmxBeanInfoManager.__timeStamp = 1292524202792L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[29];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeanInfoManager(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeanInfoManager.$callSiteArray == null || ($createCallSiteArray = JmxBeanInfoManager.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeanInfoManager.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$DescriptorSupport() {
        Class $class$javax$management$modelmbean$DescriptorSupport;
        if (($class$javax$management$modelmbean$DescriptorSupport = JmxBeanInfoManager.$class$javax$management$modelmbean$DescriptorSupport) == null) {
            $class$javax$management$modelmbean$DescriptorSupport = (JmxBeanInfoManager.$class$javax$management$modelmbean$DescriptorSupport = class$("javax.management.modelmbean.DescriptorSupport"));
        }
        return $class$javax$management$modelmbean$DescriptorSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxAttributeInfoManager() {
        Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
        if (($class$groovy$jmx$builder$JmxAttributeInfoManager = JmxBeanInfoManager.$class$groovy$jmx$builder$JmxAttributeInfoManager) == null) {
            $class$groovy$jmx$builder$JmxAttributeInfoManager = (JmxBeanInfoManager.$class$groovy$jmx$builder$JmxAttributeInfoManager = class$("groovy.jmx.builder.JmxAttributeInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxAttributeInfoManager;
    }
    
    private static /* synthetic */ Class $get$array$$class$javax$management$modelmbean$ModelMBeanOperationInfo() {
        Class array$$class$javax$management$modelmbean$ModelMBeanOperationInfo;
        if ((array$$class$javax$management$modelmbean$ModelMBeanOperationInfo = JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanOperationInfo) == null) {
            array$$class$javax$management$modelmbean$ModelMBeanOperationInfo = (JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanOperationInfo = class$("[Ljavax.management.modelmbean.ModelMBeanOperationInfo;"));
        }
        return array$$class$javax$management$modelmbean$ModelMBeanOperationInfo;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$ObjectName() {
        Class $class$javax$management$ObjectName;
        if (($class$javax$management$ObjectName = JmxBeanInfoManager.$class$javax$management$ObjectName) == null) {
            $class$javax$management$ObjectName = (JmxBeanInfoManager.$class$javax$management$ObjectName = class$("javax.management.ObjectName"));
        }
        return $class$javax$management$ObjectName;
    }
    
    private static /* synthetic */ Class $get$array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo() {
        Class array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo;
        if ((array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo = JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo) == null) {
            array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo = (JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo = class$("[Ljavax.management.modelmbean.ModelMBeanAttributeInfo;"));
        }
        return array$$class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxOperationInfoManager() {
        Class $class$groovy$jmx$builder$JmxOperationInfoManager;
        if (($class$groovy$jmx$builder$JmxOperationInfoManager = JmxBeanInfoManager.$class$groovy$jmx$builder$JmxOperationInfoManager) == null) {
            $class$groovy$jmx$builder$JmxOperationInfoManager = (JmxBeanInfoManager.$class$groovy$jmx$builder$JmxOperationInfoManager = class$("groovy.jmx.builder.JmxOperationInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxOperationInfoManager;
    }
    
    private static /* synthetic */ Class $get$array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo() {
        Class array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo;
        if ((array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo = JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo) == null) {
            array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo = (JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo = class$("[Ljavax.management.modelmbean.ModelMBeanConstructorInfo;"));
        }
        return array$$class$javax$management$modelmbean$ModelMBeanConstructorInfo;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxBeanInfoManager.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxBeanInfoManager.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo() {
        Class array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo;
        if ((array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo = JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo) == null) {
            array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo = (JmxBeanInfoManager.array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo = class$("[Ljavax.management.modelmbean.ModelMBeanNotificationInfo;"));
        }
        return array$$class$javax$management$modelmbean$ModelMBeanNotificationInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxBeanInfoManager.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxBeanInfoManager.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanInfoSupport() {
        Class $class$javax$management$modelmbean$ModelMBeanInfoSupport;
        if (($class$javax$management$modelmbean$ModelMBeanInfoSupport = JmxBeanInfoManager.$class$javax$management$modelmbean$ModelMBeanInfoSupport) == null) {
            $class$javax$management$modelmbean$ModelMBeanInfoSupport = (JmxBeanInfoManager.$class$javax$management$modelmbean$ModelMBeanInfoSupport = class$("javax.management.modelmbean.ModelMBeanInfoSupport"));
        }
        return $class$javax$management$modelmbean$ModelMBeanInfoSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanInfoManager() {
        Class $class$groovy$jmx$builder$JmxBeanInfoManager;
        if (($class$groovy$jmx$builder$JmxBeanInfoManager = JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBeanInfoManager) == null) {
            $class$groovy$jmx$builder$JmxBeanInfoManager = (JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBeanInfoManager = class$("groovy.jmx.builder.JmxBeanInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxBeanInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanInfo;
        if (($class$javax$management$modelmbean$ModelMBeanInfo = JmxBeanInfoManager.$class$javax$management$modelmbean$ModelMBeanInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanInfo = (JmxBeanInfoManager.$class$javax$management$modelmbean$ModelMBeanInfo = class$("javax.management.modelmbean.ModelMBeanInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBeanInfoManager.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
