// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Reference;
import java.util.LinkedList;
import groovy.lang.GroovyClassLoader;
import java.util.List;
import java.util.Set;
import groovy.lang.Script;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import groovy.util.Factory;
import java.util.Map;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import javax.management.MBeanServerConnection;
import groovy.util.FactoryBuilderSupport;

public class JmxBuilder extends FactoryBuilderSupport
{
    private MBeanServerConnection server;
    private String defaultNameDomain;
    private String defaultNameType;
    private String mode;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205579;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxListenerFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxEmitterFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeansFactory;
    private static /* synthetic */ Class $class$javax$management$MBeanServerConnection;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanExportFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxServerConnectorFactory;
    private static /* synthetic */ Class $class$groovy$util$Factory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxTimerFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxBuilder() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.defaultNameDomain = (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $get$$class$java$lang$String()), $get$$class$java$lang$String());
        this.defaultNameType = (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $get$$class$java$lang$String()), $get$$class$java$lang$String());
        this.mode = (String)ScriptBytecodeAdapter.castToType("markup", $get$$class$java$lang$String());
        $getCallSiteArray[2].callCurrent(this);
    }
    
    public JmxBuilder(final MBeanServerConnection svrConnection) {
        $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[0];
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$jmx$builder$JmxBuilder());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((MBeanServerConnection)array[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.server = (MBeanServerConnection)ScriptBytecodeAdapter.castToType(svrConnection, $get$$class$javax$management$MBeanServerConnection());
    }
    
    protected void registerFactories() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[3].callCurrent(this, "export", $getCallSiteArray[4].callConstructor($get$$class$groovy$jmx$builder$JmxBeanExportFactory()));
        $getCallSiteArray[5].callCurrent(this, "bean", $getCallSiteArray[6].callConstructor($get$$class$groovy$jmx$builder$JmxBeanFactory()));
        $getCallSiteArray[7].callCurrent(this, "beans", $getCallSiteArray[8].callConstructor($get$$class$groovy$jmx$builder$JmxBeansFactory()));
        $getCallSiteArray[9].callCurrent(this, "timer", $getCallSiteArray[10].callConstructor($get$$class$groovy$jmx$builder$JmxTimerFactory()));
        $getCallSiteArray[11].callCurrent(this, "listener", $getCallSiteArray[12].callConstructor($get$$class$groovy$jmx$builder$JmxListenerFactory()));
        $getCallSiteArray[13].callCurrent(this, "emitter", $getCallSiteArray[14].callConstructor($get$$class$groovy$jmx$builder$JmxEmitterFactory()));
        final JmxServerConnectorFactory svrFactory = (JmxServerConnectorFactory)$getCallSiteArray[15].callConstructor($get$$class$groovy$jmx$builder$JmxServerConnectorFactory());
        $getCallSiteArray[16].callCurrent(this, "server", svrFactory);
        $getCallSiteArray[17].callCurrent(this, "connectorServer", svrFactory);
        $getCallSiteArray[18].callCurrent(this, "serverConnector", svrFactory);
        final Object newClientFactory = new JmxBuilder$_registerFactories_closure1(this, this);
        $getCallSiteArray[19].callCurrent(this, "client", $getCallSiteArray[20].call(newClientFactory));
        $getCallSiteArray[21].callCurrent(this, "connector", $getCallSiteArray[22].call(newClientFactory));
        $getCallSiteArray[23].callCurrent(this, "clientConnector", $getCallSiteArray[24].call(newClientFactory));
        $getCallSiteArray[25].callCurrent(this, "connectorClient", $getCallSiteArray[26].call(newClientFactory));
    }
    
    public MBeanServerConnection getMBeanServer() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.server)) {
            this.server = (MBeanServerConnection)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[27].call($get$$class$groovy$jmx$builder$JmxBuilderTools()), $get$$class$javax$management$MBeanServerConnection()), $get$$class$javax$management$MBeanServerConnection());
        }
        return (MBeanServerConnection)ScriptBytecodeAdapter.castToType(this.server, $get$$class$javax$management$MBeanServerConnection());
    }
    
    public void setDefaultJmxNameDomain(final String domain) {
        $getCallSiteArray();
        this.defaultNameDomain = (String)ScriptBytecodeAdapter.castToType(domain, $get$$class$java$lang$String());
    }
    
    public String getDefaultJmxNameDomain() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.defaultNameDomain, $get$$class$java$lang$String());
    }
    
    public void setDefaultJmxNameType(final String type) {
        $getCallSiteArray();
        this.defaultNameType = (String)ScriptBytecodeAdapter.castToType(type, $get$$class$java$lang$String());
    }
    
    public String getDefaultJmxNameType() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.defaultNameType, $get$$class$java$lang$String());
    }
    
    public void setMBeanServer(final MBeanServerConnection svr) {
        $getCallSiteArray();
        this.server = (MBeanServerConnection)ScriptBytecodeAdapter.castToType(svr, $get$$class$javax$management$MBeanServerConnection());
    }
    
    public void setMode(final String mode) {
        $getCallSiteArray();
        this.mode = (String)ScriptBytecodeAdapter.castToType(mode, $get$$class$java$lang$String());
    }
    
    public String getMode() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.mode, $get$$class$java$lang$String());
    }
    
    protected Factory resolveFactory(final Object name, final Map attributes, final Object value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Factory factory = (Factory)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$util$FactoryBuilderSupport(), this, "resolveFactory", new Object[] { name, attributes, value }), $get$$class$groovy$util$Factory());
        if (!DefaultTypeTransformation.booleanUnbox(factory)) {
            factory = (Factory)ScriptBytecodeAdapter.castToType($getCallSiteArray[28].callGetPropertySafe($getCallSiteArray[29].callCurrent(this)), $get$$class$groovy$util$Factory());
        }
        return (Factory)ScriptBytecodeAdapter.castToType(factory, $get$$class$groovy$util$Factory());
    }
    
    static {
        JmxBuilder.__timeStamp__239_neverHappen1292524205579 = 0L;
        JmxBuilder.__timeStamp = 1292524205579L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[30];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBuilder.$callSiteArray == null || ($createCallSiteArray = JmxBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxListenerFactory() {
        Class $class$groovy$jmx$builder$JmxListenerFactory;
        if (($class$groovy$jmx$builder$JmxListenerFactory = JmxBuilder.$class$groovy$jmx$builder$JmxListenerFactory) == null) {
            $class$groovy$jmx$builder$JmxListenerFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxListenerFactory = class$("groovy.jmx.builder.JmxListenerFactory"));
        }
        return $class$groovy$jmx$builder$JmxListenerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxEmitterFactory() {
        Class $class$groovy$jmx$builder$JmxEmitterFactory;
        if (($class$groovy$jmx$builder$JmxEmitterFactory = JmxBuilder.$class$groovy$jmx$builder$JmxEmitterFactory) == null) {
            $class$groovy$jmx$builder$JmxEmitterFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxEmitterFactory = class$("groovy.jmx.builder.JmxEmitterFactory"));
        }
        return $class$groovy$jmx$builder$JmxEmitterFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanFactory() {
        Class $class$groovy$jmx$builder$JmxBeanFactory;
        if (($class$groovy$jmx$builder$JmxBeanFactory = JmxBuilder.$class$groovy$jmx$builder$JmxBeanFactory) == null) {
            $class$groovy$jmx$builder$JmxBeanFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxBeanFactory = class$("groovy.jmx.builder.JmxBeanFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeansFactory() {
        Class $class$groovy$jmx$builder$JmxBeansFactory;
        if (($class$groovy$jmx$builder$JmxBeansFactory = JmxBuilder.$class$groovy$jmx$builder$JmxBeansFactory) == null) {
            $class$groovy$jmx$builder$JmxBeansFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxBeansFactory = class$("groovy.jmx.builder.JmxBeansFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeansFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServerConnection() {
        Class $class$javax$management$MBeanServerConnection;
        if (($class$javax$management$MBeanServerConnection = JmxBuilder.$class$javax$management$MBeanServerConnection) == null) {
            $class$javax$management$MBeanServerConnection = (JmxBuilder.$class$javax$management$MBeanServerConnection = class$("javax.management.MBeanServerConnection"));
        }
        return $class$javax$management$MBeanServerConnection;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxBuilder.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxBuilder.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = JmxBuilder.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (JmxBuilder.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxBuilder.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxBuilder.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanExportFactory() {
        Class $class$groovy$jmx$builder$JmxBeanExportFactory;
        if (($class$groovy$jmx$builder$JmxBeanExportFactory = JmxBuilder.$class$groovy$jmx$builder$JmxBeanExportFactory) == null) {
            $class$groovy$jmx$builder$JmxBeanExportFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxBeanExportFactory = class$("groovy.jmx.builder.JmxBeanExportFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeanExportFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxServerConnectorFactory() {
        Class $class$groovy$jmx$builder$JmxServerConnectorFactory;
        if (($class$groovy$jmx$builder$JmxServerConnectorFactory = JmxBuilder.$class$groovy$jmx$builder$JmxServerConnectorFactory) == null) {
            $class$groovy$jmx$builder$JmxServerConnectorFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxServerConnectorFactory = class$("groovy.jmx.builder.JmxServerConnectorFactory"));
        }
        return $class$groovy$jmx$builder$JmxServerConnectorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$Factory() {
        Class $class$groovy$util$Factory;
        if (($class$groovy$util$Factory = JmxBuilder.$class$groovy$util$Factory) == null) {
            $class$groovy$util$Factory = (JmxBuilder.$class$groovy$util$Factory = class$("groovy.util.Factory"));
        }
        return $class$groovy$util$Factory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxTimerFactory() {
        Class $class$groovy$jmx$builder$JmxTimerFactory;
        if (($class$groovy$jmx$builder$JmxTimerFactory = JmxBuilder.$class$groovy$jmx$builder$JmxTimerFactory) == null) {
            $class$groovy$jmx$builder$JmxTimerFactory = (JmxBuilder.$class$groovy$jmx$builder$JmxTimerFactory = class$("groovy.jmx.builder.JmxTimerFactory"));
        }
        return $class$groovy$jmx$builder$JmxTimerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBuilder.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBuilder.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
