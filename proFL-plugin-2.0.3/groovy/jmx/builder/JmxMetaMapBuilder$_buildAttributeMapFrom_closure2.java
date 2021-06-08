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

class JmxMetaMapBuilder$_buildAttributeMapFrom_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private Reference<Object> object;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$groovy$lang$MetaProperty;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildAttributeMapFrom_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> map, final Reference<Object> object) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
        this.object = object;
    }
    
    public Object doCall(final Object attrib) {
        final Object attrib2 = new Reference(attrib);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final MetaProperty prop = (MetaProperty)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(this.object.get()), $getCallSiteArray[2].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), ((Reference<Object>)attrib2).get())), $get$$class$groovy$lang$MetaProperty()));
        if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)prop).get())) {
            return $getCallSiteArray[3].call(this.map.get(), $getCallSiteArray[4].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), ((Reference<Object>)attrib2).get()), $getCallSiteArray[5].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)prop).get(), ((Reference<Object>)attrib2).get(), "*"));
        }
        return null;
    }
    
    public Object getMap() {
        $getCallSiteArray();
        return this.map.get();
    }
    
    public Object getObject() {
        $getCallSiteArray();
        return this.object.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaProperty() {
        Class $class$groovy$lang$MetaProperty;
        if (($class$groovy$lang$MetaProperty = JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$lang$MetaProperty) == null) {
            $class$groovy$lang$MetaProperty = (JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$lang$MetaProperty = class$("groovy.lang.MetaProperty"));
        }
        return $class$groovy$lang$MetaProperty;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildAttributeMapFrom_closure2.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
