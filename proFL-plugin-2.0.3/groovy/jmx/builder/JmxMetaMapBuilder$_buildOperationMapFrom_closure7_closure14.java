// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14 extends Closure implements GeneratedClosure
{
    private Reference<Object> paramTypes;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14(final Object _outerInstance, final Object _thisObject, final Reference<Object> paramTypes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.paramTypes = paramTypes;
    }
    
    public Object doCall(final Object key) {
        final Object key2 = new Reference(key);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.paramTypes.get(), $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[3].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), ((Reference<Object>)key2).get())));
    }
    
    public Object getParamTypes() {
        $getCallSiteArray();
        return this.paramTypes.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
