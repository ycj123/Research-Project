// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.management.modelmbean.ModelMBeanConstructorInfo;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxOperationInfoManager$_getConstructorInfosFromMap_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> ctors;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxOperationInfoManager;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
    
    public JmxOperationInfoManager$_getConstructorInfosFromMap_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> ctors) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.ctors = ctors;
    }
    
    public Object doCall(final Object ctorName, final Object map) {
        final Object map2 = new Reference(map);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ModelMBeanConstructorInfo info = (ModelMBeanConstructorInfo)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callStatic($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), ((Reference<Object>)map2).get()), $get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo());
        return $getCallSiteArray[1].call(this.ctors.get(), info);
    }
    
    public Object call(final Object ctorName, final Object map) {
        final Object map2 = new Reference(map);
        return $getCallSiteArray()[2].callCurrent(this, ctorName, ((Reference<Object>)map2).get());
    }
    
    public Object getCtors() {
        $getCallSiteArray();
        return this.ctors.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxOperationInfoManager$_getConstructorInfosFromMap_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$callSiteArray == null || ($createCallSiteArray = JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxOperationInfoManager() {
        Class $class$groovy$jmx$builder$JmxOperationInfoManager;
        if (($class$groovy$jmx$builder$JmxOperationInfoManager = JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$class$groovy$jmx$builder$JmxOperationInfoManager) == null) {
            $class$groovy$jmx$builder$JmxOperationInfoManager = (JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$class$groovy$jmx$builder$JmxOperationInfoManager = class$("groovy.jmx.builder.JmxOperationInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxOperationInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanConstructorInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
        if (($class$javax$management$modelmbean$ModelMBeanConstructorInfo = JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$class$javax$management$modelmbean$ModelMBeanConstructorInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanConstructorInfo = (JmxOperationInfoManager$_getConstructorInfosFromMap_closure1.$class$javax$management$modelmbean$ModelMBeanConstructorInfo = class$("javax.management.modelmbean.ModelMBeanConstructorInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanConstructorInfo;
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
