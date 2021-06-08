// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.List;
import groovy.lang.GroovyObject;
import javax.management.MBeanParameterInfo;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxOperationInfoManager$_buildParamInfosFromMaps_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$javax$management$MBeanParameterInfo;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxOperationInfoManager$_buildParamInfosFromMaps_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object paramType, final Object param) {
        final Object param2 = new Reference(param);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String type = (String)ScriptBytecodeAdapter.castToType(($getCallSiteArray[0].callGetProperty(((Reference<Object>)param2).get()) instanceof String) ? $getCallSiteArray[1].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[2].callGetProperty(((Reference<Object>)param2).get())) : $getCallSiteArray[3].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGetProperty(((Reference<Object>)param2).get()))), $get$$class$java$lang$String());
        final MBeanParameterInfo info = (MBeanParameterInfo)$getCallSiteArray[6].callConstructor($get$$class$javax$management$MBeanParameterInfo(), $getCallSiteArray[7].callGetProperty(((Reference<Object>)param2).get()), type, $getCallSiteArray[8].callGetProperty(((Reference<Object>)param2).get()));
        return $getCallSiteArray[9].call(this.result.get(), info);
    }
    
    public Object call(final Object paramType, final Object param) {
        final Object param2 = new Reference(param);
        return $getCallSiteArray()[10].callCurrent(this, paramType, ((Reference<Object>)param2).get());
    }
    
    public List<MBeanParameterInfo> getResult() {
        $getCallSiteArray();
        return (List<MBeanParameterInfo>)ScriptBytecodeAdapter.castToType(this.result.get(), $get$$class$java$util$List());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxOperationInfoManager$_buildParamInfosFromMaps_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$callSiteArray == null || ($createCallSiteArray = JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$java$util$List) == null) {
            $class$java$util$List = (JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanParameterInfo() {
        Class $class$javax$management$MBeanParameterInfo;
        if (($class$javax$management$MBeanParameterInfo = JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$javax$management$MBeanParameterInfo) == null) {
            $class$javax$management$MBeanParameterInfo = (JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$javax$management$MBeanParameterInfo = class$("javax.management.MBeanParameterInfo"));
        }
        return $class$javax$management$MBeanParameterInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxOperationInfoManager$_buildParamInfosFromMaps_closure4.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
