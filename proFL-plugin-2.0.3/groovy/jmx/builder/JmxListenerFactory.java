// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import javax.management.NotificationFilterSupport;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.management.InstanceNotFoundException;
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

public class JmxListenerFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204359;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxListenerFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxEventListener;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$javax$management$NotificationFilterSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    
    public JmxListenerFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeParam, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(nodeParam)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", "' only supports named attributes." }));
        }
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final Object server = $getCallSiteArray[1].call(fsb);
        final Object map = $getCallSiteArray[2].call($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), nodeAttribs);
        final Object broadcaster = $getCallSiteArray[3].call(map, "from");
        try {
            final Object eventType = ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(map, "event"), $get$$class$java$lang$String());
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(server, broadcaster))) {
                throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { $getCallSiteArray[7].call(broadcaster) }, new String[] { "MBean '", "' is not registered in server." }));
            }
            if (DefaultTypeTransformation.booleanUnbox(eventType)) {
                final NotificationFilterSupport filter = (NotificationFilterSupport)$getCallSiteArray[8].callConstructor($get$$class$javax$management$NotificationFilterSupport());
                $getCallSiteArray[9].call(filter, eventType);
                $getCallSiteArray[10].call(server, broadcaster, $getCallSiteArray[11].call($get$$class$groovy$jmx$builder$JmxEventListener()), filter, map);
            }
            else {
                $getCallSiteArray[12].call(server, broadcaster, $getCallSiteArray[13].call($get$$class$groovy$jmx$builder$JmxEventListener()), null, map);
            }
        }
        catch (InstanceNotFoundException e) {
            throw (Throwable)$getCallSiteArray[14].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), e);
        }
        return ScriptBytecodeAdapter.castToType(map, $get$$class$java$lang$Object());
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
            $getCallSiteArray[15].call(parentNode, thisNode);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxListenerFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxListenerFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxListenerFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxListenerFactory.__timeStamp__239_neverHappen1292524204359 = 0L;
        JmxListenerFactory.__timeStamp = 1292524204359L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxListenerFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxListenerFactory.$callSiteArray == null || ($createCallSiteArray = JmxListenerFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxListenerFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxListenerFactory() {
        Class $class$groovy$jmx$builder$JmxListenerFactory;
        if (($class$groovy$jmx$builder$JmxListenerFactory = JmxListenerFactory.$class$groovy$jmx$builder$JmxListenerFactory) == null) {
            $class$groovy$jmx$builder$JmxListenerFactory = (JmxListenerFactory.$class$groovy$jmx$builder$JmxListenerFactory = class$("groovy.jmx.builder.JmxListenerFactory"));
        }
        return $class$groovy$jmx$builder$JmxListenerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxEventListener() {
        Class $class$groovy$jmx$builder$JmxEventListener;
        if (($class$groovy$jmx$builder$JmxEventListener = JmxListenerFactory.$class$groovy$jmx$builder$JmxEventListener) == null) {
            $class$groovy$jmx$builder$JmxEventListener = (JmxListenerFactory.$class$groovy$jmx$builder$JmxEventListener = class$("groovy.jmx.builder.JmxEventListener"));
        }
        return $class$groovy$jmx$builder$JmxEventListener;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxListenerFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxListenerFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxListenerFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxListenerFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxListenerFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxListenerFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxListenerFactory.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxListenerFactory.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxListenerFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxListenerFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$NotificationFilterSupport() {
        Class $class$javax$management$NotificationFilterSupport;
        if (($class$javax$management$NotificationFilterSupport = JmxListenerFactory.$class$javax$management$NotificationFilterSupport) == null) {
            $class$javax$management$NotificationFilterSupport = (JmxListenerFactory.$class$javax$management$NotificationFilterSupport = class$("javax.management.NotificationFilterSupport"));
        }
        return $class$javax$management$NotificationFilterSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxListenerFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxListenerFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxListenerFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxListenerFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
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
