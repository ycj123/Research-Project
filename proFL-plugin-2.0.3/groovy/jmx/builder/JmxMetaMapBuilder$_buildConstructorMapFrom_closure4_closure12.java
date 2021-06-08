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

class JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12 extends Closure implements GeneratedClosure
{
    private Reference<Object> params;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12(final Object _outerInstance, final Object _thisObject, final Reference<Object> params) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.params = params;
    }
    
    public Object doCall(final Object param) {
        final Object param2 = new Reference(param);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.params.get(), $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty($get$$class$groovy$jmx$builder$JmxBuilderTools()), $getCallSiteArray[3].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), ((Reference<Object>)param2).get())));
    }
    
    public Object getParams() {
        $getCallSiteArray();
        return this.params.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
