// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxClientConnectorFactory extends AbstractFactory implements GroovyObject
{
    private static List SUPPORTED_PROTOCOLS;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204311;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$management$remote$JMXServiceURL;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$management$remote$JMXConnectorFactory;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxClientConnectorFactory;
    private static /* synthetic */ Class $class$javax$management$remote$JMXConnector;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    
    public JmxClientConnectorFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeArgs, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(nodeArgs)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", "' only supports named attributes." }));
        }
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[1].callSafe(nodeAttribs, "protocol"))) {
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[2].callSafe(nodeAttribs, "transport"))) {
                o = "rmi";
            }
        }
        final Object protocol = o;
        final Object port = $getCallSiteArray[3].callSafe(nodeAttribs, "port");
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[4].callSafe(nodeAttribs, "host"))) {
            if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[5].callSafe(nodeAttribs, "address"))) {
                o2 = "localhost";
            }
        }
        final Object host = o2;
        final Object url = $getCallSiteArray[6].callSafe(nodeAttribs, "url");
        Object o3;
        if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[7].callSafe(nodeAttribs, "properties"))) {
            if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[8].callSafe(nodeAttribs, "props"))) {
                o3 = $getCallSiteArray[9].callSafe(nodeAttribs, "env");
            }
        }
        final Object props = o3;
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(port) && !DefaultTypeTransformation.booleanUnbox(url)) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[10].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", " requires attribute 'port' to specify server's port number." }));
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[11].call(JmxClientConnectorFactory.SUPPORTED_PROTOCOLS, protocol))) {
            throw (Throwable)$getCallSiteArray[12].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), $getCallSiteArray[13].call(new GStringImpl(new Object[] { protocol }, new String[] { "Connector protocol '", " is not supported at this time. " }), new GStringImpl(new Object[] { JmxClientConnectorFactory.SUPPORTED_PROTOCOLS }, new String[] { "Supported protocols are ", "." })));
        }
        final JMXServiceURL serviceUrl = (JMXServiceURL)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(url) ? $getCallSiteArray[14].callConstructor($get$$class$javax$management$remote$JMXServiceURL(), url) : $getCallSiteArray[15].callCurrent(this, protocol, host, port), $get$$class$javax$management$remote$JMXServiceURL());
        final JMXConnector connector = (JMXConnector)ScriptBytecodeAdapter.castToType($getCallSiteArray[16].call($get$$class$javax$management$remote$JMXConnectorFactory(), serviceUrl, props), $get$$class$javax$management$remote$JMXConnector());
        return ScriptBytecodeAdapter.castToType(connector, $get$$class$java$lang$Object());
    }
    
    private JMXServiceURL generateServiceUrl(final Object protocol, final Object host, final Object port) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String url = (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { protocol, protocol, host, port }, new String[] { "service:jmx:", ":///jndi/", "://", ":", "/jmxrmi" }), $get$$class$java$lang$String());
        return (JMXServiceURL)ScriptBytecodeAdapter.castToType($getCallSiteArray[17].callConstructor($get$$class$javax$management$remote$JMXServiceURL(), url), $get$$class$javax$management$remote$JMXServiceURL());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxClientConnectorFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxClientConnectorFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxClientConnectorFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxClientConnectorFactory.__timeStamp__239_neverHappen1292524204311 = 0L;
        JmxClientConnectorFactory.__timeStamp = 1292524204311L;
        JmxClientConnectorFactory.SUPPORTED_PROTOCOLS = ScriptBytecodeAdapter.createList(new Object[] { "rmi", "jrmp", "iiop", "jmxmp" });
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxClientConnectorFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxClientConnectorFactory.$callSiteArray == null || ($createCallSiteArray = JmxClientConnectorFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxClientConnectorFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxClientConnectorFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxClientConnectorFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXServiceURL() {
        Class $class$javax$management$remote$JMXServiceURL;
        if (($class$javax$management$remote$JMXServiceURL = JmxClientConnectorFactory.$class$javax$management$remote$JMXServiceURL) == null) {
            $class$javax$management$remote$JMXServiceURL = (JmxClientConnectorFactory.$class$javax$management$remote$JMXServiceURL = class$("javax.management.remote.JMXServiceURL"));
        }
        return $class$javax$management$remote$JMXServiceURL;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxClientConnectorFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxClientConnectorFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXConnectorFactory() {
        Class $class$javax$management$remote$JMXConnectorFactory;
        if (($class$javax$management$remote$JMXConnectorFactory = JmxClientConnectorFactory.$class$javax$management$remote$JMXConnectorFactory) == null) {
            $class$javax$management$remote$JMXConnectorFactory = (JmxClientConnectorFactory.$class$javax$management$remote$JMXConnectorFactory = class$("javax.management.remote.JMXConnectorFactory"));
        }
        return $class$javax$management$remote$JMXConnectorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxClientConnectorFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxClientConnectorFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxClientConnectorFactory() {
        Class $class$groovy$jmx$builder$JmxClientConnectorFactory;
        if (($class$groovy$jmx$builder$JmxClientConnectorFactory = JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxClientConnectorFactory) == null) {
            $class$groovy$jmx$builder$JmxClientConnectorFactory = (JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxClientConnectorFactory = class$("groovy.jmx.builder.JmxClientConnectorFactory"));
        }
        return $class$groovy$jmx$builder$JmxClientConnectorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXConnector() {
        Class $class$javax$management$remote$JMXConnector;
        if (($class$javax$management$remote$JMXConnector = JmxClientConnectorFactory.$class$javax$management$remote$JMXConnector) == null) {
            $class$javax$management$remote$JMXConnector = (JmxClientConnectorFactory.$class$javax$management$remote$JMXConnector = class$("javax.management.remote.JMXConnector"));
        }
        return $class$javax$management$remote$JMXConnector;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxClientConnectorFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
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
