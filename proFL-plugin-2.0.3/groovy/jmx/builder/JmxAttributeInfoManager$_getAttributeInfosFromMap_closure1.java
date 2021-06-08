// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> attribs;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
    private static /* synthetic */ Class $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1;
    
    public JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> attribs) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.attribs = attribs;
    }
    
    public Object doCall(final Object attribName, final Object map) {
        final Object attribName2 = new Reference(attribName);
        final Object map2 = new Reference(map);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty(((Reference<Object>)attribName2).get(), $get$$class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1(), ((Reference<Object>)map2).get(), "name");
        final ModelMBeanAttributeInfo info = (ModelMBeanAttributeInfo)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callStatic($get$$class$groovy$jmx$builder$JmxAttributeInfoManager(), ((Reference<Object>)map2).get()), $get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo());
        return $getCallSiteArray[1].call(this.attribs.get(), info);
    }
    
    public Object call(final Object attribName, final Object map) {
        final Object attribName2 = new Reference(attribName);
        final Object map2 = new Reference(map);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)attribName2).get(), ((Reference<Object>)map2).get());
    }
    
    public Object getAttribs() {
        $getCallSiteArray();
        return this.attribs.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$callSiteArray == null || ($createCallSiteArray = JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxAttributeInfoManager() {
        Class $class$groovy$jmx$builder$JmxAttributeInfoManager;
        if (($class$groovy$jmx$builder$JmxAttributeInfoManager = JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$groovy$jmx$builder$JmxAttributeInfoManager) == null) {
            $class$groovy$jmx$builder$JmxAttributeInfoManager = (JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$groovy$jmx$builder$JmxAttributeInfoManager = class$("groovy.jmx.builder.JmxAttributeInfoManager"));
        }
        return $class$groovy$jmx$builder$JmxAttributeInfoManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$modelmbean$ModelMBeanAttributeInfo() {
        Class $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
        if (($class$javax$management$modelmbean$ModelMBeanAttributeInfo = JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$javax$management$modelmbean$ModelMBeanAttributeInfo) == null) {
            $class$javax$management$modelmbean$ModelMBeanAttributeInfo = (JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$javax$management$modelmbean$ModelMBeanAttributeInfo = class$("javax.management.modelmbean.ModelMBeanAttributeInfo"));
        }
        return $class$javax$management$modelmbean$ModelMBeanAttributeInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1() {
        Class $class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1;
        if (($class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1 = JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1) == null) {
            $class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1 = (JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1.$class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1 = class$("groovy.jmx.builder.JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1"));
        }
        return $class$groovy$jmx$builder$JmxAttributeInfoManager$_getAttributeInfosFromMap_closure1;
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
