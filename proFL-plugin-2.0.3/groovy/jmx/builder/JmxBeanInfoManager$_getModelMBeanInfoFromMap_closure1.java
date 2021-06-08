// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaProperty;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> operations;
    private Reference<Object> object;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxOperationInfoManager;
    private static /* synthetic */ Class $class$groovy$lang$MetaProperty;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> operations, final Reference<Object> object) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.operations = operations;
        this.object = object;
    }
    
    public Object doCall(final Object info) {
        final Object info2 = new Reference(info);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final MetaProperty prop = (MetaProperty)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(this.object.get()), $getCallSiteArray[2].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[3].callGetProperty(((Reference<Object>)info2).get()))), $get$$class$groovy$lang$MetaProperty()));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)prop).get()) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(((Reference<Object>)info2).get()))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[5].call(this.operations.get(), $getCallSiteArray[6].call($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), ((Reference<Object>)prop).get()));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)prop).get()) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(((Reference<Object>)info2).get()))) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[8].call(this.operations.get(), $getCallSiteArray[9].call($get$$class$groovy$jmx$builder$JmxOperationInfoManager(), ((Reference<Object>)prop).get()));
        }
        return null;
    }
    
    public Object getOperations() {
        $getCallSiteArray();
        return this.operations.get();
    }
    
    public Object getObject() {
        $getCallSiteArray();
        return this.object.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$callSiteArray == null || ($createCallSiteArray = JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxOperationInfoManager() {
        Class $class$groovy$jmx$builder$JmxOperationInfoManager;
        if (($class$groovy$jmx$builder$JmxOperationInfoManager = JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$jmx$builder$JmxOperationInfoManager) == null) {
            $class$groovy$jmx$builder$JmxOperationInfoManager = (JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$jmx$builder$JmxOperationInfoManager = class$("groovy.jmx.builder.JmxOperationInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxOperationInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaProperty() {
        Class $class$groovy$lang$MetaProperty;
        if (($class$groovy$lang$MetaProperty = JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$lang$MetaProperty) == null) {
            $class$groovy$lang$MetaProperty = (JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$lang$MetaProperty = class$("groovy.lang.MetaProperty"));
        }
        return $class$groovy$lang$MetaProperty;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBeanInfoManager$_getModelMBeanInfoFromMap_closure1.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
