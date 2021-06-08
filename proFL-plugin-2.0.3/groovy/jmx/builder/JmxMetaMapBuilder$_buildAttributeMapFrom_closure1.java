// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.MetaProperty;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildAttributeMapFrom_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> attribs;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> attribs) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.attribs = attribs;
    }
    
    public Object doCall(final MetaProperty prop) {
        final MetaProperty prop2 = (MetaProperty)new Reference(prop);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].callGetProperty(((Reference<Object>)prop2).get())))) {
            final Object attrib = ScriptBytecodeAdapter.createMap(new Object[0]);
            final Object getterPrefix = DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual($getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGetProperty(((Reference<Object>)prop2).get())), "java.lang.Boolean") && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGetProperty(((Reference<Object>)prop2).get())), "boolean")) ? Boolean.FALSE : Boolean.TRUE) ? "is" : "get";
            final Object name = $getCallSiteArray[7].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[8].callGetProperty(((Reference<Object>)prop2).get()));
            ScriptBytecodeAdapter.setProperty(name, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "name");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[9].call(new GStringImpl(new Object[] { $getCallSiteArray[10].callGetProperty(((Reference<Object>)prop2).get()) }, new String[] { "Property ", "" })), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "displayName");
            ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "readable");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[11].call(getterPrefix, name), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "getMethod");
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "writable");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[12].callGetProperty($getCallSiteArray[13].callGetProperty(((Reference<Object>)prop2).get())), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "type");
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)prop2).get(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), attrib, "property");
            return $getCallSiteArray[14].call(this.attribs.get(), name, attrib);
        }
        return null;
    }
    
    public Object call(final MetaProperty prop) {
        final MetaProperty prop2 = (MetaProperty)new Reference(prop);
        return $getCallSiteArray()[15].callCurrent(this, ((Reference<Object>)prop2).get());
    }
    
    public Object getAttribs() {
        $getCallSiteArray();
        return this.attribs.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1 = JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1 = (JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1 = class$("groovy.jmx.builder.JmxMetaMapBuilder$_buildAttributeMapFrom_closure1"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildAttributeMapFrom_closure1;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildAttributeMapFrom_closure1.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
