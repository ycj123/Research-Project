// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.LinkedList;
import java.net.URL;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.util.Properties;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class ConfigSlurper implements GroovyObject
{
    private static final Object ENV_METHOD;
    private static final Object ENV_SETTINGS;
    private GroovyClassLoader classLoader;
    private String environment;
    private Object envMode;
    private Map bindingVars;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203603;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$GroovySystem;
    private static /* synthetic */ Class $class$groovy$util$ConfigObject;
    private static /* synthetic */ Class $class$groovy$util$ConfigBinding;
    private static /* synthetic */ Class $class$groovy$util$ConfigSlurper;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    
    public ConfigSlurper() {
        this.classLoader = (GroovyClassLoader)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$groovy$lang$GroovyClassLoader()), $get$$class$groovy$lang$GroovyClassLoader());
        this.envMode = Boolean.FALSE;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public ConfigSlurper(final String env) {
        this.classLoader = (GroovyClassLoader)ScriptBytecodeAdapter.castToType($getCallSiteArray()[1].callConstructor($get$$class$groovy$lang$GroovyClassLoader()), $get$$class$groovy$lang$GroovyClassLoader());
        this.envMode = Boolean.FALSE;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.environment = (String)ScriptBytecodeAdapter.castToType(env, $get$$class$java$lang$String());
    }
    
    public void setBinding(final Map vars) {
        $getCallSiteArray();
        this.bindingVars = (Map)ScriptBytecodeAdapter.castToType(vars, $get$$class$java$util$Map());
    }
    
    public ConfigObject parse(final Properties properties) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ConfigObject config = (ConfigObject)$getCallSiteArray[2].callConstructor($get$$class$groovy$util$ConfigObject());
        Object key = null;
        final Object call = $getCallSiteArray[3].call($getCallSiteArray[4].call(properties));
        while (((Iterator)call).hasNext()) {
            key = ((Iterator<Object>)call).next();
            final Object tokens = $getCallSiteArray[5].call(key, "\\.");
            Object current = config;
            final Object currentToken = null;
            final Object last = new Reference(null);
            Object lastToken = null;
            Object foundBase = Boolean.FALSE;
            Object token = null;
            final Object call2 = $getCallSiteArray[6].call(tokens);
            while (((Iterator)call2).hasNext()) {
                token = ((Iterator<Object>)call2).next();
                if (DefaultTypeTransformation.booleanUnbox(foundBase)) {
                    lastToken = $getCallSiteArray[7].call(lastToken, $getCallSiteArray[8].call(".", token));
                    current = ((Reference<Object>)last).get();
                }
                else {
                    ((Reference<Object>)last).set(current);
                    lastToken = token;
                    current = ScriptBytecodeAdapter.getProperty($get$$class$groovy$util$ConfigSlurper(), current, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { token }, new String[] { "", "" }), $get$$class$java$lang$String()));
                    if (current instanceof ConfigObject) {
                        continue;
                    }
                    foundBase = Boolean.TRUE;
                }
            }
            if (current instanceof ConfigObject) {
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(((Reference<Object>)last).get(), lastToken))) {
                    final Object flattened = $getCallSiteArray[10].call(((Reference<Object>)last).get());
                    $getCallSiteArray[11].call(((Reference<Object>)last).get());
                    $getCallSiteArray[12].call(flattened, new ConfigSlurper$_parse_closure1(this, this, (Reference<Object>)last));
                    $getCallSiteArray[13].call(((Reference<Object>)last).get(), lastToken, $getCallSiteArray[14].call(properties, key));
                }
                else {
                    $getCallSiteArray[15].call(((Reference<Object>)last).get(), lastToken, $getCallSiteArray[16].call(properties, key));
                }
            }
            current = config;
        }
        return (ConfigObject)ScriptBytecodeAdapter.castToType(config, $get$$class$groovy$util$ConfigObject());
    }
    
    public ConfigObject parse(final String script) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (ConfigObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[17].callCurrent(this, $getCallSiteArray[18].call(this.classLoader, script)), $get$$class$groovy$util$ConfigObject());
    }
    
    public ConfigObject parse(final Class scriptClass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (ConfigObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[19].callCurrent(this, $getCallSiteArray[20].call(scriptClass)), $get$$class$groovy$util$ConfigObject());
    }
    
    public ConfigObject parse(final Script script) {
        return (ConfigObject)ScriptBytecodeAdapter.castToType($getCallSiteArray()[21].callCurrent(this, script, null), $get$$class$groovy$util$ConfigObject());
    }
    
    public ConfigObject parse(final URL scriptLocation) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (ConfigObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[22].callCurrent(this, $getCallSiteArray[23].call($getCallSiteArray[24].call(this.classLoader, $getCallSiteArray[25].callGetProperty(scriptLocation))), scriptLocation), $get$$class$groovy$util$ConfigObject());
    }
    
    public ConfigObject parse(final Script script, final URL location) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object config = new Reference(DefaultTypeTransformation.booleanUnbox(location) ? $getCallSiteArray[26].callConstructor($get$$class$groovy$util$ConfigObject(), location) : $getCallSiteArray[27].callConstructor($get$$class$groovy$util$ConfigObject()));
        $getCallSiteArray[28].call($getCallSiteArray[29].callGetProperty($get$$class$groovy$lang$GroovySystem()), $getCallSiteArray[30].callGroovyObjectGetProperty(script));
        final Object mc = new Reference($getCallSiteArray[31].callGetProperty($getCallSiteArray[32].callGroovyObjectGetProperty(script)));
        final Object prefix = new Reference("");
        final LinkedList stack = (LinkedList)new Reference($getCallSiteArray[33].callConstructor($get$$class$java$util$LinkedList()));
        $getCallSiteArray[34].call(((Reference)stack).get(), ScriptBytecodeAdapter.createMap(new Object[] { "config", ((Reference<Object>)config).get(), "scope", ScriptBytecodeAdapter.createMap(new Object[0]) }));
        final Object pushStack = new Reference(new ConfigSlurper$_parse_closure2(this, this, (Reference<Object>)stack));
        final Object assignName = new Reference(new ConfigSlurper$_parse_closure3(this, this, (Reference<Object>)stack));
        final Object getPropertyClosure = new ConfigSlurper$_parse_closure4(this, this, (Reference<Object>)assignName, (Reference<Object>)stack);
        ScriptBytecodeAdapter.setProperty(getPropertyClosure, $get$$class$groovy$util$ConfigSlurper(), ((Reference<Object>)mc).get(), "getProperty");
        ScriptBytecodeAdapter.setProperty(new ConfigSlurper$_parse_closure5(this, this, (Reference<Object>)mc, (Reference<Object>)prefix, (Reference<Object>)assignName, (Reference<Object>)config, (Reference<Object>)stack, (Reference<Object>)pushStack), $get$$class$groovy$util$ConfigSlurper(), ((Reference<Object>)mc).get(), "invokeMethod");
        ScriptBytecodeAdapter.setGroovyObjectProperty(((Reference<Object>)mc).get(), $get$$class$groovy$util$ConfigSlurper(), script, "metaClass");
        final Object setProperty = new ConfigSlurper$_parse_closure6(this, this, (Reference<Object>)prefix, (Reference<Object>)assignName);
        final Object binding = $getCallSiteArray[35].callConstructor($get$$class$groovy$util$ConfigBinding(), setProperty);
        if (DefaultTypeTransformation.booleanUnbox(this.bindingVars)) {
            $getCallSiteArray[36].call($getCallSiteArray[37].call(binding), this.bindingVars);
        }
        ScriptBytecodeAdapter.setGroovyObjectProperty(binding, $get$$class$groovy$util$ConfigSlurper(), script, "binding");
        $getCallSiteArray[38].call(script);
        final Object envSettings = $getCallSiteArray[39].call(((Reference<Object>)config).get(), ConfigSlurper.ENV_SETTINGS);
        if (DefaultTypeTransformation.booleanUnbox(envSettings)) {
            $getCallSiteArray[40].call(((Reference<Object>)config).get(), envSettings);
        }
        return (ConfigObject)ScriptBytecodeAdapter.castToType(((Reference<Object>)config).get(), $get$$class$groovy$util$ConfigObject());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$ConfigSlurper()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ConfigSlurper.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ConfigSlurper.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ConfigSlurper.__timeStamp__239_neverHappen1292524203603 = 0L;
        ConfigSlurper.__timeStamp = 1292524203603L;
        ENV_SETTINGS = "__env_settings__";
        ENV_METHOD = "environments";
    }
    
    public static final Object getENV_SETTINGS() {
        return ConfigSlurper.ENV_SETTINGS;
    }
    
    public GroovyClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public void setClassLoader(final GroovyClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public String getEnvironment() {
        return this.environment;
    }
    
    public void setEnvironment(final String environment) {
        this.environment = environment;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[41];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ConfigSlurper.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ConfigSlurper.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovySystem() {
        Class $class$groovy$lang$GroovySystem;
        if (($class$groovy$lang$GroovySystem = ConfigSlurper.$class$groovy$lang$GroovySystem) == null) {
            $class$groovy$lang$GroovySystem = (ConfigSlurper.$class$groovy$lang$GroovySystem = class$("groovy.lang.GroovySystem"));
        }
        return $class$groovy$lang$GroovySystem;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigObject() {
        Class $class$groovy$util$ConfigObject;
        if (($class$groovy$util$ConfigObject = ConfigSlurper.$class$groovy$util$ConfigObject) == null) {
            $class$groovy$util$ConfigObject = (ConfigSlurper.$class$groovy$util$ConfigObject = class$("groovy.util.ConfigObject"));
        }
        return $class$groovy$util$ConfigObject;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigBinding() {
        Class $class$groovy$util$ConfigBinding;
        if (($class$groovy$util$ConfigBinding = ConfigSlurper.$class$groovy$util$ConfigBinding) == null) {
            $class$groovy$util$ConfigBinding = (ConfigSlurper.$class$groovy$util$ConfigBinding = class$("groovy.util.ConfigBinding"));
        }
        return $class$groovy$util$ConfigBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigSlurper() {
        Class $class$groovy$util$ConfigSlurper;
        if (($class$groovy$util$ConfigSlurper = ConfigSlurper.$class$groovy$util$ConfigSlurper) == null) {
            $class$groovy$util$ConfigSlurper = (ConfigSlurper.$class$groovy$util$ConfigSlurper = class$("groovy.util.ConfigSlurper"));
        }
        return $class$groovy$util$ConfigSlurper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = ConfigSlurper.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (ConfigSlurper.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ConfigSlurper.$class$java$lang$String) == null) {
            $class$java$lang$String = (ConfigSlurper.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = ConfigSlurper.$class$java$util$Map) == null) {
            $class$java$util$Map = (ConfigSlurper.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = ConfigSlurper.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (ConfigSlurper.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
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
