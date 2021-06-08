// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import java.util.HashMap;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXServiceURL;
import javax.management.MBeanServer;
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

public class JmxServerConnectorFactory extends AbstractFactory implements GroovyObject
{
    private static List SUPPORTED_PROTOCOLS;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204362;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$remote$rmi$RMIConnectorServer;
    private static /* synthetic */ Class $class$javax$management$remote$JMXServiceURL;
    private static /* synthetic */ Class $class$javax$rmi$ssl$SslRMIClientSocketFactory;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$javax$management$remote$JMXConnectorServer;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxServerConnectorFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$javax$management$remote$JMXConnectorServerFactory;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$rmi$ssl$SslRMIServerSocketFactory;
    private static /* synthetic */ Class $class$java$util$HashMap;
    
    public JmxServerConnectorFactory() {
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
        final Object env = $getCallSiteArray[10].callCurrent(this, protocol, port, props);
        $getCallSiteArray[11].call(nodeAttribs);
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(port) && !DefaultTypeTransformation.booleanUnbox(url)) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[12].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", " requires attribute 'port' to specify server's port number." }));
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].call(JmxServerConnectorFactory.SUPPORTED_PROTOCOLS, protocol))) {
            throw (Throwable)$getCallSiteArray[14].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), $getCallSiteArray[15].call(new GStringImpl(new Object[] { protocol }, new String[] { "Connector protocol '", " is not supported at this time. " }), new GStringImpl(new Object[] { JmxServerConnectorFactory.SUPPORTED_PROTOCOLS }, new String[] { "Supported protocols are ", "." })));
        }
        final MBeanServer server = (MBeanServer)ScriptBytecodeAdapter.castToType($getCallSiteArray[16].call(fsb), $get$$class$javax$management$MBeanServer());
        final JMXServiceURL serviceUrl = (JMXServiceURL)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(url) ? $getCallSiteArray[17].callConstructor($get$$class$javax$management$remote$JMXServiceURL(), url) : $getCallSiteArray[18].callCurrent(this, protocol, host, port), $get$$class$javax$management$remote$JMXServiceURL());
        final JMXConnectorServer connector = (JMXConnectorServer)ScriptBytecodeAdapter.castToType($getCallSiteArray[19].call($get$$class$javax$management$remote$JMXConnectorServerFactory(), serviceUrl, env, server), $get$$class$javax$management$remote$JMXConnectorServer());
        return ScriptBytecodeAdapter.castToType(connector, $get$$class$java$lang$Object());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parentNode, final Object thisNode) {
        $getCallSiteArray();
    }
    
    private Map confiConnectorProperties(final String protocol, final int port, final Map props) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(props)) {
            return (Map)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$Map());
        }
        final HashMap env = (HashMap)new Reference($getCallSiteArray[20].callConstructor($get$$class$java$util$HashMap()));
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[21].call(props, "com.sun.management.jmxremote.authenticate"))) {
            o = $getCallSiteArray[22].call(props, "authenticate");
        }
        final Object auth = o;
        $getCallSiteArray[23].call(((Reference)env).get(), "com.sun.management.jmxremote.authenticate", auth);
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[24].call(props, "com.sun.management.jmxremote.password.file"))) {
            o2 = $getCallSiteArray[25].call(props, "passwordFile");
        }
        final Object pFile = o2;
        $getCallSiteArray[26].call(((Reference)env).get(), "com.sun.management.jmxremote.password.file", pFile);
        Object o3;
        if (!DefaultTypeTransformation.booleanUnbox(o3 = $getCallSiteArray[27].call(props, "com.sun.management.jmxremote.access.file"))) {
            o3 = $getCallSiteArray[28].call(props, "accessFile");
        }
        final Object aFile = o3;
        $getCallSiteArray[29].call(((Reference)env).get(), "com.sun.management.jmxremote.access.file", aFile);
        Object o4;
        if (!DefaultTypeTransformation.booleanUnbox(o4 = $getCallSiteArray[30].call(props, "com.sun.management.jmxremote. ssl"))) {
            o4 = $getCallSiteArray[31].call(props, "sslEnabled");
        }
        final Object ssl = o4;
        $getCallSiteArray[32].call(((Reference)env).get(), "com.sun.management.jmxremote.ssl", ssl);
        if (ScriptBytecodeAdapter.compareEqual(protocol, "rmi") && DefaultTypeTransformation.booleanUnbox(ssl)) {
            Object o5;
            if (!DefaultTypeTransformation.booleanUnbox(o5 = $getCallSiteArray[33].call(props, $getCallSiteArray[34].callGetProperty($get$$class$javax$management$remote$rmi$RMIConnectorServer())))) {
                o5 = $getCallSiteArray[35].callConstructor($get$$class$javax$rmi$ssl$SslRMIClientSocketFactory());
            }
            final Object csf = o5;
            Object o6;
            if (!DefaultTypeTransformation.booleanUnbox(o6 = $getCallSiteArray[36].call(props, $getCallSiteArray[37].callGetProperty($get$$class$javax$management$remote$rmi$RMIConnectorServer())))) {
                o6 = $getCallSiteArray[38].callConstructor($get$$class$javax$rmi$ssl$SslRMIServerSocketFactory());
            }
            final Object ssf = o6;
            $getCallSiteArray[39].call(((Reference)env).get(), $getCallSiteArray[40].callGetProperty($get$$class$javax$management$remote$rmi$RMIConnectorServer()), csf);
            $getCallSiteArray[41].call(((Reference)env).get(), $getCallSiteArray[42].callGetProperty($get$$class$javax$management$remote$rmi$RMIConnectorServer()), ssf);
            goto Label_0503;
        }
        $getCallSiteArray[43].call(props, new JmxServerConnectorFactory$_confiConnectorProperties_closure1(this, this, (Reference<Object>)env));
        return (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray[44].call(props), $get$$class$java$util$Map());
    }
    
    private JMXServiceURL generateServiceUrl(final Object protocol, final Object host, final Object port) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String url = (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { protocol, protocol, host, port }, new String[] { "service:jmx:", ":///jndi/", "://", ":", "/jmxrmi" }), $get$$class$java$lang$String());
        return (JMXServiceURL)ScriptBytecodeAdapter.castToType($getCallSiteArray[45].callConstructor($get$$class$javax$management$remote$JMXServiceURL(), url), $get$$class$javax$management$remote$JMXServiceURL());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxServerConnectorFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxServerConnectorFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxServerConnectorFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxServerConnectorFactory.__timeStamp__239_neverHappen1292524204362 = 0L;
        JmxServerConnectorFactory.__timeStamp = 1292524204362L;
        JmxServerConnectorFactory.SUPPORTED_PROTOCOLS = ScriptBytecodeAdapter.createList(new Object[] { "rmi", "jrmp", "iiop", "jmxmp" });
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[46];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxServerConnectorFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxServerConnectorFactory.$callSiteArray == null || ($createCallSiteArray = JmxServerConnectorFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxServerConnectorFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$rmi$RMIConnectorServer() {
        Class $class$javax$management$remote$rmi$RMIConnectorServer;
        if (($class$javax$management$remote$rmi$RMIConnectorServer = JmxServerConnectorFactory.$class$javax$management$remote$rmi$RMIConnectorServer) == null) {
            $class$javax$management$remote$rmi$RMIConnectorServer = (JmxServerConnectorFactory.$class$javax$management$remote$rmi$RMIConnectorServer = class$("javax.management.remote.rmi.RMIConnectorServer"));
        }
        return $class$javax$management$remote$rmi$RMIConnectorServer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXServiceURL() {
        Class $class$javax$management$remote$JMXServiceURL;
        if (($class$javax$management$remote$JMXServiceURL = JmxServerConnectorFactory.$class$javax$management$remote$JMXServiceURL) == null) {
            $class$javax$management$remote$JMXServiceURL = (JmxServerConnectorFactory.$class$javax$management$remote$JMXServiceURL = class$("javax.management.remote.JMXServiceURL"));
        }
        return $class$javax$management$remote$JMXServiceURL;
    }
    
    private static /* synthetic */ Class $get$$class$javax$rmi$ssl$SslRMIClientSocketFactory() {
        Class $class$javax$rmi$ssl$SslRMIClientSocketFactory;
        if (($class$javax$rmi$ssl$SslRMIClientSocketFactory = JmxServerConnectorFactory.$class$javax$rmi$ssl$SslRMIClientSocketFactory) == null) {
            $class$javax$rmi$ssl$SslRMIClientSocketFactory = (JmxServerConnectorFactory.$class$javax$rmi$ssl$SslRMIClientSocketFactory = class$("javax.rmi.ssl.SslRMIClientSocketFactory"));
        }
        return $class$javax$rmi$ssl$SslRMIClientSocketFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxServerConnectorFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxServerConnectorFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = JmxServerConnectorFactory.$class$java$util$Map) == null) {
            $class$java$util$Map = (JmxServerConnectorFactory.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXConnectorServer() {
        Class $class$javax$management$remote$JMXConnectorServer;
        if (($class$javax$management$remote$JMXConnectorServer = JmxServerConnectorFactory.$class$javax$management$remote$JMXConnectorServer) == null) {
            $class$javax$management$remote$JMXConnectorServer = (JmxServerConnectorFactory.$class$javax$management$remote$JMXConnectorServer = class$("javax.management.remote.JMXConnectorServer"));
        }
        return $class$javax$management$remote$JMXConnectorServer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxServerConnectorFactory() {
        Class $class$groovy$jmx$builder$JmxServerConnectorFactory;
        if (($class$groovy$jmx$builder$JmxServerConnectorFactory = JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxServerConnectorFactory) == null) {
            $class$groovy$jmx$builder$JmxServerConnectorFactory = (JmxServerConnectorFactory.$class$groovy$jmx$builder$JmxServerConnectorFactory = class$("groovy.jmx.builder.JmxServerConnectorFactory"));
        }
        return $class$groovy$jmx$builder$JmxServerConnectorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxServerConnectorFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxServerConnectorFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxServerConnectorFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxServerConnectorFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxServerConnectorFactory.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxServerConnectorFactory.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$remote$JMXConnectorServerFactory() {
        Class $class$javax$management$remote$JMXConnectorServerFactory;
        if (($class$javax$management$remote$JMXConnectorServerFactory = JmxServerConnectorFactory.$class$javax$management$remote$JMXConnectorServerFactory) == null) {
            $class$javax$management$remote$JMXConnectorServerFactory = (JmxServerConnectorFactory.$class$javax$management$remote$JMXConnectorServerFactory = class$("javax.management.remote.JMXConnectorServerFactory"));
        }
        return $class$javax$management$remote$JMXConnectorServerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxServerConnectorFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxServerConnectorFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$rmi$ssl$SslRMIServerSocketFactory() {
        Class $class$javax$rmi$ssl$SslRMIServerSocketFactory;
        if (($class$javax$rmi$ssl$SslRMIServerSocketFactory = JmxServerConnectorFactory.$class$javax$rmi$ssl$SslRMIServerSocketFactory) == null) {
            $class$javax$rmi$ssl$SslRMIServerSocketFactory = (JmxServerConnectorFactory.$class$javax$rmi$ssl$SslRMIServerSocketFactory = class$("javax.rmi.ssl.SslRMIServerSocketFactory"));
        }
        return $class$javax$rmi$ssl$SslRMIServerSocketFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = JmxServerConnectorFactory.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (JmxServerConnectorFactory.$class$java$util$HashMap = class$("java.util.HashMap"));
        }
        return $class$java$util$HashMap;
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
