// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JListProperties implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203082;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$3;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$2;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$1;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$javax$swing$JList;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$4;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$5;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$6;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListProperties$7;
    private static /* synthetic */ Class $class$java$util$HashMap;
    
    public JListProperties() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static Map<String, TriggerBinding> getSyntheticProperties() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Map result = (Map)$getCallSiteArray[0].callConstructor($get$$class$java$util$HashMap());
        $getCallSiteArray[1].call(result, $getCallSiteArray[2].call($getCallSiteArray[3].call($get$$class$javax$swing$JList()), "#selectedValue"), $getCallSiteArray[4].callConstructor($get$$class$groovy$swing$binding$JListProperties$1(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[5].call(result, $getCallSiteArray[6].call($getCallSiteArray[7].call($get$$class$javax$swing$JList()), "#selectedElement"), $getCallSiteArray[8].callConstructor($get$$class$groovy$swing$binding$JListProperties$2(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[9].call(result, $getCallSiteArray[10].call($getCallSiteArray[11].call($get$$class$javax$swing$JList()), "#selectedValues"), $getCallSiteArray[12].callConstructor($get$$class$groovy$swing$binding$JListProperties$3(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[13].call(result, $getCallSiteArray[14].call($getCallSiteArray[15].call($get$$class$javax$swing$JList()), "#selectedElements"), $getCallSiteArray[16].callConstructor($get$$class$groovy$swing$binding$JListProperties$4(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[17].call(result, $getCallSiteArray[18].call($getCallSiteArray[19].call($get$$class$javax$swing$JList()), "#selectedIndex"), $getCallSiteArray[20].callConstructor($get$$class$groovy$swing$binding$JListProperties$5(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[21].call(result, $getCallSiteArray[22].call($getCallSiteArray[23].call($get$$class$javax$swing$JList()), "#selectedIndices"), $getCallSiteArray[24].callConstructor($get$$class$groovy$swing$binding$JListProperties$6(), $get$$class$groovy$swing$binding$JListProperties()));
        $getCallSiteArray[25].call(result, $getCallSiteArray[26].call($getCallSiteArray[27].call($get$$class$javax$swing$JList()), "#elements"), $getCallSiteArray[28].callConstructor($get$$class$groovy$swing$binding$JListProperties$7(), $get$$class$groovy$swing$binding$JListProperties()));
        return (Map<String, TriggerBinding>)ScriptBytecodeAdapter.castToType(result, $get$$class$java$util$Map());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListProperties()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListProperties.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListProperties.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JListProperties.__timeStamp__239_neverHappen1292524203082 = 0L;
        JListProperties.__timeStamp = 1292524203082L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[29];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListProperties(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListProperties.$callSiteArray == null || ($createCallSiteArray = JListProperties.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListProperties.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties() {
        Class $class$groovy$swing$binding$JListProperties;
        if (($class$groovy$swing$binding$JListProperties = JListProperties.$class$groovy$swing$binding$JListProperties) == null) {
            $class$groovy$swing$binding$JListProperties = (JListProperties.$class$groovy$swing$binding$JListProperties = class$("groovy.swing.binding.JListProperties"));
        }
        return $class$groovy$swing$binding$JListProperties;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$3() {
        Class $class$groovy$swing$binding$JListProperties$3;
        if (($class$groovy$swing$binding$JListProperties$3 = JListProperties.$class$groovy$swing$binding$JListProperties$3) == null) {
            $class$groovy$swing$binding$JListProperties$3 = (JListProperties.$class$groovy$swing$binding$JListProperties$3 = class$("groovy.swing.binding.JListProperties$3"));
        }
        return $class$groovy$swing$binding$JListProperties$3;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$2() {
        Class $class$groovy$swing$binding$JListProperties$2;
        if (($class$groovy$swing$binding$JListProperties$2 = JListProperties.$class$groovy$swing$binding$JListProperties$2) == null) {
            $class$groovy$swing$binding$JListProperties$2 = (JListProperties.$class$groovy$swing$binding$JListProperties$2 = class$("groovy.swing.binding.JListProperties$2"));
        }
        return $class$groovy$swing$binding$JListProperties$2;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$1() {
        Class $class$groovy$swing$binding$JListProperties$1;
        if (($class$groovy$swing$binding$JListProperties$1 = JListProperties.$class$groovy$swing$binding$JListProperties$1) == null) {
            $class$groovy$swing$binding$JListProperties$1 = (JListProperties.$class$groovy$swing$binding$JListProperties$1 = class$("groovy.swing.binding.JListProperties$1"));
        }
        return $class$groovy$swing$binding$JListProperties$1;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = JListProperties.$class$java$util$Map) == null) {
            $class$java$util$Map = (JListProperties.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JList() {
        Class $class$javax$swing$JList;
        if (($class$javax$swing$JList = JListProperties.$class$javax$swing$JList) == null) {
            $class$javax$swing$JList = (JListProperties.$class$javax$swing$JList = class$("javax.swing.JList"));
        }
        return $class$javax$swing$JList;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListProperties.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListProperties.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$4() {
        Class $class$groovy$swing$binding$JListProperties$4;
        if (($class$groovy$swing$binding$JListProperties$4 = JListProperties.$class$groovy$swing$binding$JListProperties$4) == null) {
            $class$groovy$swing$binding$JListProperties$4 = (JListProperties.$class$groovy$swing$binding$JListProperties$4 = class$("groovy.swing.binding.JListProperties$4"));
        }
        return $class$groovy$swing$binding$JListProperties$4;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$5() {
        Class $class$groovy$swing$binding$JListProperties$5;
        if (($class$groovy$swing$binding$JListProperties$5 = JListProperties.$class$groovy$swing$binding$JListProperties$5) == null) {
            $class$groovy$swing$binding$JListProperties$5 = (JListProperties.$class$groovy$swing$binding$JListProperties$5 = class$("groovy.swing.binding.JListProperties$5"));
        }
        return $class$groovy$swing$binding$JListProperties$5;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$6() {
        Class $class$groovy$swing$binding$JListProperties$6;
        if (($class$groovy$swing$binding$JListProperties$6 = JListProperties.$class$groovy$swing$binding$JListProperties$6) == null) {
            $class$groovy$swing$binding$JListProperties$6 = (JListProperties.$class$groovy$swing$binding$JListProperties$6 = class$("groovy.swing.binding.JListProperties$6"));
        }
        return $class$groovy$swing$binding$JListProperties$6;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListProperties$7() {
        Class $class$groovy$swing$binding$JListProperties$7;
        if (($class$groovy$swing$binding$JListProperties$7 = JListProperties.$class$groovy$swing$binding$JListProperties$7) == null) {
            $class$groovy$swing$binding$JListProperties$7 = (JListProperties.$class$groovy$swing$binding$JListProperties$7 = class$("groovy.swing.binding.JListProperties$7"));
        }
        return $class$groovy$swing$binding$JListProperties$7;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = JListProperties.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (JListProperties.$class$java$util$HashMap = class$("java.util.HashMap"));
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
