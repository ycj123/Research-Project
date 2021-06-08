// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Collection;
import java.util.Set;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Properties;
import java.util.Map;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.io.Writer;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.net.URL;
import groovy.lang.GroovyObject;
import groovy.lang.Writable;
import java.util.LinkedHashMap;

public class ConfigObject extends LinkedHashMap implements Writable, GroovyObject
{
    private static final Object KEYWORDS;
    private static final Object TAB_CHARACTER;
    private URL configFile;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205694;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$syntax$Types;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$util$ConfigObject;
    private static /* synthetic */ Class $class$java$io$Writer;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$io$BufferedWriter;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$java$util$Properties;
    private static /* synthetic */ Class $class$java$net$URL;
    
    public ConfigObject(final URL file) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.configFile = (URL)ScriptBytecodeAdapter.castToType(file, $get$$class$java$net$URL());
    }
    
    public ConfigObject() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Writer writeTo(final Writer outArg) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object out = null;
        try {
            out = $getCallSiteArray[0].callConstructor($get$$class$java$io$BufferedWriter(), outArg);
            $getCallSiteArray[1].callCurrent(this, ArrayUtil.createArray("", this, out, ConfigObject.$const$0, Boolean.FALSE));
            $getCallSiteArray[2].call(out);
        }
        finally {
            $getCallSiteArray[3].call(out);
        }
        return (Writer)ScriptBytecodeAdapter.castToType(outArg, $get$$class$java$io$Writer());
    }
    
    public Object getProperty(final String name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(name, "configFile")) {
            return this.configFile;
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].callCurrent(this, name))) {
            final ConfigObject prop = (ConfigObject)$getCallSiteArray[5].callConstructor($get$$class$groovy$util$ConfigObject(), this.configFile);
            $getCallSiteArray[6].callCurrent(this, name, prop);
            return prop;
        }
        return $getCallSiteArray[7].callCurrent(this, name);
    }
    
    public Map flatten() {
        return (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray()[8].callCurrent(this, (Object)null), $get$$class$java$util$Map());
    }
    
    public Map flatten(Map target) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(target, null)) {
            target = $getCallSiteArray[9].callConstructor($get$$class$groovy$util$ConfigObject());
        }
        $getCallSiteArray[10].callCurrent(this, "", target, this);
        return (Map)ScriptBytecodeAdapter.castToType(target, $get$$class$java$util$Map());
    }
    
    public Map merge(final ConfigObject other) {
        return (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray()[11].callCurrent(this, this, other), $get$$class$java$util$Map());
    }
    
    public Properties toProperties() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object props = $getCallSiteArray[12].callConstructor($get$$class$java$util$Properties());
        $getCallSiteArray[13].callCurrent(this, props);
        props = $getCallSiteArray[14].callCurrent(this, props);
        return (Properties)ScriptBytecodeAdapter.castToType(props, $get$$class$java$util$Properties());
    }
    
    public Properties toProperties(final String prefix) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object props = $getCallSiteArray[15].callConstructor($get$$class$java$util$Properties());
        $getCallSiteArray[16].callCurrent(this, new GStringImpl(new Object[] { prefix }, new String[] { "", "." }), props, this);
        props = $getCallSiteArray[17].callCurrent(this, props);
        return (Properties)ScriptBytecodeAdapter.castToType(props, $get$$class$java$util$Properties());
    }
    
    private Object merge(final Map config, final Map other) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object entry = null;
        final Object call = $getCallSiteArray[18].call(other);
        while (((Iterator)call).hasNext()) {
            entry = ((Iterator<Object>)call).next();
            final Object configEntry = $getCallSiteArray[19].call(config, $getCallSiteArray[20].callGetProperty(entry));
            if (ScriptBytecodeAdapter.compareEqual(configEntry, null)) {
                $getCallSiteArray[21].call(config, $getCallSiteArray[22].callGetProperty(entry), $getCallSiteArray[23].callGetProperty(entry));
            }
            else if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((configEntry instanceof Map && ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[24].call(configEntry), ConfigObject.$const$0)) ? Boolean.TRUE : Boolean.FALSE) && $getCallSiteArray[25].callGetProperty(entry) instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[26].callCurrent(this, configEntry, $getCallSiteArray[27].callGetProperty(entry));
            }
            else {
                $getCallSiteArray[28].call(config, $getCallSiteArray[29].callGetProperty(entry), $getCallSiteArray[30].callGetProperty(entry));
            }
        }
        return config;
    }
    
    private Object writeConfig(final String prefix, final ConfigObject map, final Object out, final Integer tab, final boolean apply) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object space = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(apply)) ? $getCallSiteArray[31].call(ConfigObject.TAB_CHARACTER, tab) : "";
        Object key = null;
        final Object call = $getCallSiteArray[32].call($getCallSiteArray[33].call(map));
        while (((Iterator)call).hasNext()) {
            key = ((Iterator<Object>)call).next();
            final Object value = $getCallSiteArray[34].call(map, key);
            if (value instanceof ConfigObject) {
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[35].call(value))) {
                    continue;
                }
                final Object dotsInKeys = $getCallSiteArray[36].call(value, new ConfigObject$_writeConfig_closure1(this, this));
                final Object configSize = $getCallSiteArray[37].call(value);
                final Object firstKey = $getCallSiteArray[38].call($getCallSiteArray[39].call($getCallSiteArray[40].call(value)));
                final Object firstValue = $getCallSiteArray[41].call($getCallSiteArray[42].call($getCallSiteArray[43].call(value)));
                Object firstSize = null;
                if (firstValue instanceof ConfigObject) {
                    firstSize = $getCallSiteArray[44].call(firstValue);
                }
                else {
                    firstSize = ConfigObject.$const$1;
                }
                if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(configSize, ConfigObject.$const$1) && !DefaultTypeTransformation.booleanUnbox(dotsInKeys)) ? Boolean.FALSE : Boolean.TRUE)) {
                    if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(firstSize, ConfigObject.$const$1) && firstValue instanceof ConfigObject) ? Boolean.TRUE : Boolean.FALSE)) {
                        key = (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[45].call(ConfigObject.KEYWORDS, key)) ? $getCallSiteArray[46].call(key) : key);
                        final Object writePrefix = new GStringImpl(new Object[] { prefix, key, firstKey }, new String[] { "", "", ".", "." });
                        $getCallSiteArray[47].callCurrent(this, ArrayUtil.createArray(writePrefix, firstValue, out, tab, Boolean.TRUE));
                    }
                    else if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(dotsInKeys) && firstValue instanceof ConfigObject) ? Boolean.TRUE : Boolean.FALSE)) {
                        $getCallSiteArray[48].callCurrent(this, ArrayUtil.createArray(key, space, tab, value, out));
                    }
                    else {
                        Object j = null;
                        final Object call2 = $getCallSiteArray[49].call($getCallSiteArray[50].call(value));
                        while (((Iterator)call2).hasNext()) {
                            j = ((Iterator<Object>)call2).next();
                            final Object v2 = $getCallSiteArray[51].call(value, j);
                            final Object k2 = ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[52].call(j, "."), ConfigObject.$const$2) ? $getCallSiteArray[53].call(j) : j;
                            if (v2 instanceof ConfigObject) {
                                key = (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[54].call(ConfigObject.KEYWORDS, key)) ? $getCallSiteArray[55].call(key) : key);
                                $getCallSiteArray[56].callCurrent(this, ArrayUtil.createArray(new GStringImpl(new Object[] { prefix, key }, new String[] { "", "", "" }), v2, out, tab, Boolean.FALSE));
                            }
                            else {
                                $getCallSiteArray[57].callCurrent(this, ArrayUtil.createArray(new GStringImpl(new Object[] { key, k2 }, new String[] { "", ".", "" }), space, prefix, v2, out));
                            }
                        }
                    }
                }
                else {
                    $getCallSiteArray[58].callCurrent(this, ArrayUtil.createArray(key, space, tab, value, out));
                }
            }
            else {
                $getCallSiteArray[59].callCurrent(this, ArrayUtil.createArray(key, space, prefix, value, out));
            }
        }
        return null;
    }
    
    private Object writeValue(Object key, final Object space, Object prefix, final Object value, final Object out) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        key = (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[60].call(key, "."), ConfigObject.$const$2) ? $getCallSiteArray[61].call(key) : key);
        final Boolean isKeyword = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[62].call(ConfigObject.KEYWORDS, key), $get$$class$java$lang$Boolean());
        key = (DefaultTypeTransformation.booleanUnbox(isKeyword) ? $getCallSiteArray[63].call(key) : key);
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(prefix) && DefaultTypeTransformation.booleanUnbox(isKeyword)) ? Boolean.TRUE : Boolean.FALSE)) {
            prefix = "this.";
        }
        $getCallSiteArray[64].call(out, new GStringImpl(new Object[] { space, prefix, key, $getCallSiteArray[65].call(value) }, new String[] { "", "", "", "=", "" }));
        return $getCallSiteArray[66].call(out);
    }
    
    private Object writeNode(Object key, final Object space, final Object tab, final Object value, final Object out) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        key = (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[67].call(ConfigObject.KEYWORDS, key)) ? $getCallSiteArray[68].call(key) : key);
        $getCallSiteArray[69].call(out, new GStringImpl(new Object[] { space, key }, new String[] { "", "", " {" }));
        $getCallSiteArray[70].call(out);
        $getCallSiteArray[71].callCurrent(this, ArrayUtil.createArray("", value, out, $getCallSiteArray[72].call(tab, ConfigObject.$const$1), Boolean.TRUE));
        final Object last = new GStringImpl(new Object[] { space }, new String[] { "", "}" });
        $getCallSiteArray[73].call(out, last);
        return $getCallSiteArray[74].call(out);
    }
    
    private Object convertValuesToString(final Object props) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object newProps = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object e = null;
        final Object call = $getCallSiteArray[75].call(props);
        while (((Iterator)call).hasNext()) {
            e = ((Iterator<Object>)call).next();
            $getCallSiteArray[76].call(newProps, $getCallSiteArray[77].callGetProperty(e), $getCallSiteArray[78].callSafe($getCallSiteArray[79].callGetProperty(e)));
        }
        return newProps;
    }
    
    private Object populate(final Object suffix, final Object config, final Object map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object key = null;
        final Object call = $getCallSiteArray[80].call($getCallSiteArray[81].call(map));
        while (((Iterator)call).hasNext()) {
            key = ((Iterator<Object>)call).next();
            final Object value = $getCallSiteArray[82].call(map, key);
            if (value instanceof Map) {
                $getCallSiteArray[83].callCurrent(this, $getCallSiteArray[84].call(suffix, new GStringImpl(new Object[] { key }, new String[] { "", "." })), config, value);
            }
            else {
                try {
                    $getCallSiteArray[85].call(config, $getCallSiteArray[86].call(suffix, key), value);
                }
                catch (NullPointerException e) {}
            }
        }
        return null;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$ConfigObject()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ConfigObject.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ConfigObject.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ConfigObject.__timeStamp__239_neverHappen1292524205694 = 0L;
        ConfigObject.__timeStamp = 1292524205694L;
        $const$2 = -1;
        $const$1 = 1;
        $const$0 = 0;
        TAB_CHARACTER = "\t";
        KEYWORDS = $getCallSiteArray()[87].call($get$$class$org$codehaus$groovy$syntax$Types());
    }
    
    public static final Object getKEYWORDS() {
        return ConfigObject.KEYWORDS;
    }
    
    public static final Object getTAB_CHARACTER() {
        return ConfigObject.TAB_CHARACTER;
    }
    
    public URL getConfigFile() {
        return this.configFile;
    }
    
    public void setConfigFile(final URL configFile) {
        this.configFile = configFile;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[88];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigObject(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigObject.$callSiteArray == null || ($createCallSiteArray = ConfigObject.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigObject.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$syntax$Types() {
        Class $class$org$codehaus$groovy$syntax$Types;
        if (($class$org$codehaus$groovy$syntax$Types = ConfigObject.$class$org$codehaus$groovy$syntax$Types) == null) {
            $class$org$codehaus$groovy$syntax$Types = (ConfigObject.$class$org$codehaus$groovy$syntax$Types = class$("org.codehaus.groovy.syntax.Types"));
        }
        return $class$org$codehaus$groovy$syntax$Types;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ConfigObject.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ConfigObject.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigObject() {
        Class $class$groovy$util$ConfigObject;
        if (($class$groovy$util$ConfigObject = ConfigObject.$class$groovy$util$ConfigObject) == null) {
            $class$groovy$util$ConfigObject = (ConfigObject.$class$groovy$util$ConfigObject = class$("groovy.util.ConfigObject"));
        }
        return $class$groovy$util$ConfigObject;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$Writer() {
        Class $class$java$io$Writer;
        if (($class$java$io$Writer = ConfigObject.$class$java$io$Writer) == null) {
            $class$java$io$Writer = (ConfigObject.$class$java$io$Writer = class$("java.io.Writer"));
        }
        return $class$java$io$Writer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = ConfigObject.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (ConfigObject.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$BufferedWriter() {
        Class $class$java$io$BufferedWriter;
        if (($class$java$io$BufferedWriter = ConfigObject.$class$java$io$BufferedWriter) == null) {
            $class$java$io$BufferedWriter = (ConfigObject.$class$java$io$BufferedWriter = class$("java.io.BufferedWriter"));
        }
        return $class$java$io$BufferedWriter;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = ConfigObject.$class$java$util$Map) == null) {
            $class$java$util$Map = (ConfigObject.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Properties() {
        Class $class$java$util$Properties;
        if (($class$java$util$Properties = ConfigObject.$class$java$util$Properties) == null) {
            $class$java$util$Properties = (ConfigObject.$class$java$util$Properties = class$("java.util.Properties"));
        }
        return $class$java$util$Properties;
    }
    
    private static /* synthetic */ Class $get$$class$java$net$URL() {
        Class $class$java$net$URL;
        if (($class$java$net$URL = ConfigObject.$class$java$net$URL) == null) {
            $class$java$net$URL = (ConfigObject.$class$java$net$URL = class$("java.net.URL"));
        }
        return $class$java$net$URL;
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
