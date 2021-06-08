// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import javax.management.ObjectName;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import javax.management.NotificationFilterSupport;
import groovy.lang.Reference;
import javax.management.MBeanServer;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxEmitterFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204314;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxEmitterFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$management$ObjectName;
    private static /* synthetic */ Class $class$groovy$util$GroovyMBean;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$javax$management$NotificationFilterSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxEventEmitter;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    
    public JmxEmitterFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeParam, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(nodeParam)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", "' only supports named attributes." }));
        }
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final Object server = new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(fsb), $get$$class$javax$management$MBeanServer()));
        final Object emitter = new Reference($getCallSiteArray[2].callConstructor($get$$class$groovy$jmx$builder$JmxEventEmitter()));
        final Object name = new Reference($getCallSiteArray[3].callCurrent(this, fsb, ((Reference<Object>)emitter).get(), $getCallSiteArray[4].call(nodeAttribs, "name")));
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[5].call(nodeAttribs, "event"))) {
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[6].call(nodeAttribs, "type"))) {
                o = "jmx.builder.event.emitter";
            }
        }
        final Object event = o;
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[7].call(nodeAttribs, "listeners"))) {
            o2 = $getCallSiteArray[8].call(nodeAttribs, "recipients");
        }
        final Object listeners = o2;
        final NotificationFilterSupport filter = (NotificationFilterSupport)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$NotificationFilterSupport()));
        if (DefaultTypeTransformation.booleanUnbox(event)) {
            ((Reference<Object>)filter).set($getCallSiteArray[9].callConstructor($get$$class$javax$management$NotificationFilterSupport()));
            $getCallSiteArray[10].call(((Reference<Object>)filter).get(), event);
            $getCallSiteArray[11].call(((Reference<Object>)emitter).get(), event);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call(((Reference<Object>)server).get(), ((Reference<Object>)name).get()))) {
            $getCallSiteArray[13].call(((Reference<Object>)server).get(), ((Reference<Object>)name).get());
        }
        $getCallSiteArray[14].call(((Reference<Object>)server).get(), ((Reference<Object>)emitter).get(), ((Reference<Object>)name).get());
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(listeners) && !(listeners instanceof List)) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[15].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), "Listeners must be provided as a list [listner1,...,listenerN]");
        }
        $getCallSiteArray[16].call(listeners, new JmxEmitterFactory$_newInstance_closure1(this, this, (Reference<Object>)name, (Reference<Object>)server, (Reference<Object>)emitter, (Reference<Object>)filter));
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[17].callConstructor($get$$class$groovy$util$GroovyMBean(), $getCallSiteArray[18].call(fsb), ((Reference<Object>)name).get()), $get$$class$java$lang$Object());
    }
    
    private ObjectName getObjectName(final Object fsb, final Object emitter, final Object name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(name) && name instanceof ObjectName) ? Boolean.TRUE : Boolean.FALSE)) {
            return (ObjectName)ScriptBytecodeAdapter.castToType(name, $get$$class$javax$management$ObjectName());
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(name) && name instanceof String) ? Boolean.TRUE : Boolean.FALSE)) {
            return (ObjectName)ScriptBytecodeAdapter.castToType($getCallSiteArray[19].callConstructor($get$$class$javax$management$ObjectName(), name), $get$$class$javax$management$ObjectName());
        }
        if (!DefaultTypeTransformation.booleanUnbox(name)) {
            return (ObjectName)ScriptBytecodeAdapter.castToType($getCallSiteArray[20].callConstructor($get$$class$javax$management$ObjectName(), new GStringImpl(new Object[] { $getCallSiteArray[21].call(fsb), $getCallSiteArray[22].call(emitter) }, new String[] { "", ":type=Emitter,name=Emitter@", "" })), $get$$class$javax$management$ObjectName());
        }
        return (ObjectName)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$management$ObjectName());
    }
    
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parentNode, final Object thisNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(parentNode, null)) {
            $getCallSiteArray[23].call(parentNode, thisNode);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxEmitterFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxEmitterFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxEmitterFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxEmitterFactory.__timeStamp__239_neverHappen1292524204314 = 0L;
        JmxEmitterFactory.__timeStamp = 1292524204314L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxEmitterFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxEmitterFactory.$callSiteArray == null || ($createCallSiteArray = JmxEmitterFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxEmitterFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxEmitterFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxEmitterFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxEmitterFactory() {
        Class $class$groovy$jmx$builder$JmxEmitterFactory;
        if (($class$groovy$jmx$builder$JmxEmitterFactory = JmxEmitterFactory.$class$groovy$jmx$builder$JmxEmitterFactory) == null) {
            $class$groovy$jmx$builder$JmxEmitterFactory = (JmxEmitterFactory.$class$groovy$jmx$builder$JmxEmitterFactory = class$("groovy.jmx.builder.JmxEmitterFactory"));
        }
        return $class$groovy$jmx$builder$JmxEmitterFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxEmitterFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxEmitterFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxEmitterFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxEmitterFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$ObjectName() {
        Class $class$javax$management$ObjectName;
        if (($class$javax$management$ObjectName = JmxEmitterFactory.$class$javax$management$ObjectName) == null) {
            $class$javax$management$ObjectName = (JmxEmitterFactory.$class$javax$management$ObjectName = class$("javax.management.ObjectName"));
        }
        return $class$javax$management$ObjectName;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$GroovyMBean() {
        Class $class$groovy$util$GroovyMBean;
        if (($class$groovy$util$GroovyMBean = JmxEmitterFactory.$class$groovy$util$GroovyMBean) == null) {
            $class$groovy$util$GroovyMBean = (JmxEmitterFactory.$class$groovy$util$GroovyMBean = class$("groovy.util.GroovyMBean"));
        }
        return $class$groovy$util$GroovyMBean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxEmitterFactory.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxEmitterFactory.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$NotificationFilterSupport() {
        Class $class$javax$management$NotificationFilterSupport;
        if (($class$javax$management$NotificationFilterSupport = JmxEmitterFactory.$class$javax$management$NotificationFilterSupport) == null) {
            $class$javax$management$NotificationFilterSupport = (JmxEmitterFactory.$class$javax$management$NotificationFilterSupport = class$("javax.management.NotificationFilterSupport"));
        }
        return $class$javax$management$NotificationFilterSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxEmitterFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxEmitterFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxEventEmitter() {
        Class $class$groovy$jmx$builder$JmxEventEmitter;
        if (($class$groovy$jmx$builder$JmxEventEmitter = JmxEmitterFactory.$class$groovy$jmx$builder$JmxEventEmitter) == null) {
            $class$groovy$jmx$builder$JmxEventEmitter = (JmxEmitterFactory.$class$groovy$jmx$builder$JmxEventEmitter = class$("groovy.jmx.builder.JmxEventEmitter"));
        }
        return $class$groovy$jmx$builder$JmxEventEmitter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxEmitterFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxEmitterFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
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
