// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.management.MBeanServer;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxBeanFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204303;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanFactory;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$java$util$Iterator;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxBeanFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeParam, final Map nodeAttributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final MBeanServer server = (MBeanServer)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(fsb), $get$$class$javax$management$MBeanServer());
        Object metaMap = null;
        Object target = null;
        if (DefaultTypeTransformation.booleanUnbox(nodeParam)) {
            target = nodeParam;
            metaMap = $getCallSiteArray[1].callCurrent(this, target);
            metaMap = $getCallSiteArray[2].call($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), target);
        }
        else if (DefaultTypeTransformation.booleanUnbox(nodeAttributes)) {
            target = $getCallSiteArray[3].callGetProperty(nodeAttributes);
            metaMap = $getCallSiteArray[4].callCurrent(this, target);
            metaMap = $getCallSiteArray[5].call($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), target, nodeAttributes);
        }
        Object callGetProperty;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty = $getCallSiteArray[6].callGetProperty(metaMap))) {
            callGetProperty = server;
        }
        ScriptBytecodeAdapter.setProperty(callGetProperty, $get$$class$groovy$jmx$builder$JmxBeanFactory(), metaMap, "server");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[7].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[8].call(target)), $get$$class$groovy$jmx$builder$JmxBeanFactory(), metaMap, "isMBean");
        return ScriptBytecodeAdapter.castToType(metaMap, $get$$class$java$lang$Object());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parentNode, final Object thisNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final MBeanServer server = (MBeanServer)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(fsb), $get$$class$javax$management$MBeanServer());
        final Object metaMap = thisNode;
        Object callGetPropertySafe;
        if (!DefaultTypeTransformation.booleanUnbox(callGetPropertySafe = $getCallSiteArray[10].callGetPropertySafe($getCallSiteArray[11].call(fsb)))) {
            callGetPropertySafe = "replace";
        }
        final Object regPolicy = callGetPropertySafe;
        final Object registeredBean = $getCallSiteArray[12].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), regPolicy, metaMap);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(registeredBean) && ScriptBytecodeAdapter.compareEqual(regPolicy, "replace")) ? Boolean.TRUE : Boolean.FALSE)) {
            final Iterator i = (Iterator)ScriptBytecodeAdapter.castToType($getCallSiteArray[13].call(parentNode), $get$$class$java$util$Iterator());
            while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(i))) {
                final Object exportedBean = $getCallSiteArray[15].call(i);
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call($getCallSiteArray[17].call(exportedBean), $getCallSiteArray[18].callGetProperty(metaMap)))) {
                    $getCallSiteArray[19].call(i);
                }
            }
        }
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(parentNode, null) && DefaultTypeTransformation.booleanUnbox(registeredBean)) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[20].call(parentNode, registeredBean);
        }
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    private Object initMetaMap(final Object target) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(target)) {
            throw (Throwable)$getCallSiteArray[21].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), $getCallSiteArray[22].call($getCallSiteArray[23].call("You must specify a target object to ", " export as MBean i.e. JmxBuilder.bean(targetInstance), "), " JmxBuilder.bean([target:instance]), JmxBuilder.beans([instanceList])."));
        }
        final Object metaMap = ScriptBytecodeAdapter.createMap(new Object[0]);
        ScriptBytecodeAdapter.setProperty(target, $get$$class$groovy$jmx$builder$JmxBeanFactory(), metaMap, "target");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[24].callGetProperty($getCallSiteArray[25].callGetProperty(target)), $get$$class$groovy$jmx$builder$JmxBeanFactory(), metaMap, "name");
        return metaMap;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxBeanFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxBeanFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxBeanFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxBeanFactory.__timeStamp__239_neverHappen1292524204303 = 0L;
        JmxBeanFactory.__timeStamp = 1292524204303L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[26];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeanFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeanFactory.$callSiteArray == null || ($createCallSiteArray = JmxBeanFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeanFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxBeanFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxBeanFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBeanFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBeanFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxBeanFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxBeanFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanFactory() {
        Class $class$groovy$jmx$builder$JmxBeanFactory;
        if (($class$groovy$jmx$builder$JmxBeanFactory = JmxBeanFactory.$class$groovy$jmx$builder$JmxBeanFactory) == null) {
            $class$groovy$jmx$builder$JmxBeanFactory = (JmxBeanFactory.$class$groovy$jmx$builder$JmxBeanFactory = class$("groovy.jmx.builder.JmxBeanFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxBeanFactory.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxBeanFactory.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxBeanFactory.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxBeanFactory.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Iterator() {
        Class $class$java$util$Iterator;
        if (($class$java$util$Iterator = JmxBeanFactory.$class$java$util$Iterator) == null) {
            $class$java$util$Iterator = (JmxBeanFactory.$class$java$util$Iterator = class$("java.util.Iterator"));
        }
        return $class$java$util$Iterator;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBeanFactory.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
