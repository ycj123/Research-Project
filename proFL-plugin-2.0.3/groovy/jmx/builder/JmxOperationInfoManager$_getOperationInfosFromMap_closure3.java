// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxOperationInfoManager$_getOperationInfosFromMap_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> ops;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxOperationInfoManager;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanOperationInfo;
    
    public JmxOperationInfoManager$_getOperationInfosFromMap_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> ops) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.ops = ops;
    }
    
    public Object doCall(final Object opNames, final Object map) {
        final Object map2 = new Reference(map);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ModelMBeanOperationInfo info = (ModelMBeanOperationInfo)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callStatic($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), ((Reference<Object>)map2).get()), $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo());
        return $getCallSiteArray[1].call(this.ops.get(), info);
    }
    
    public Object call(final Object opNames, final Object map) {
        final Object map2 = new Reference(map);
        return $getCallSiteArray()[2].callCurrent(this, opNames, ((Reference<Object>)map2).get());
    }
    
    public Object getOps() {
        $getCallSiteArray();
        return this.ops.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxOperationInfoManager$_getOperationInfosFromMap_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$callSiteArray == null || ($createCallSiteArray = JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxOperationInfoManager() {
        Class $class$groovy$jmx$builder$JmxOperationInfoManager;
        if (($class$groovy$jmx$builder$JmxOperationInfoManager = JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$class$groovy$jmx$builder$JmxOperationInfoManager) == null) {
            $class$groovy$jmx$builder$JmxOperationInfoManager = (JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$class$groovy$jmx$builder$JmxOperationInfoManager = class$("groovy.jmx.builder.JmxOperationInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxOperationInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanOperationInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanOperationInfo;
        if (($class$javax$management$modelmbean$ModelMBeanOperationInfo = JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$class$javax$management$modelmbean$ModelMBeanOperationInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanOperationInfo = (JmxOperationInfoManager$_getOperationInfosFromMap_closure3.$class$javax$management$modelmbean$ModelMBeanOperationInfo = class$("javax.management.modelmbean.ModelMBeanOperationInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanOperationInfo;
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
