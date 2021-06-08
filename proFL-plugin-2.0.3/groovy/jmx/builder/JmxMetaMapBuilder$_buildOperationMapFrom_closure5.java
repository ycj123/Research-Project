// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaProperty;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildOperationMapFrom_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> declaredMethods;
    private Reference<Object> object;
    private Reference<Object> ops;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$lang$MetaProperty;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildOperationMapFrom_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> declaredMethods, final Reference<Object> object, final Reference<Object> ops) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.declaredMethods = declaredMethods;
        this.object = object;
        this.ops = ops;
    }
    
    public Object doCall(final Object method) {
        final Object method2 = new Reference(method);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(this.declaredMethods.get(), $getCallSiteArray[1].callGetProperty(((Reference<Object>)method2).get()))) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGetProperty(((Reference<Object>)method2).get())))) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), $getCallSiteArray[7].callGetProperty(((Reference<Object>)method2).get())))) ? Boolean.FALSE : Boolean.TRUE)) {
            return null;
        }
        final String mName = (String)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[8].callGetProperty(((Reference<Object>)method2).get()), $get$$class$java$lang$String()));
        final MetaProperty prop = (MetaProperty)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(((Reference<Object>)mName).get(), "get")) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].call(((Reference<Object>)mName).get(), "set"))) ? Boolean.FALSE : Boolean.TRUE) ? $getCallSiteArray[11].call($getCallSiteArray[12].callGetProperty(this.object.get()), $getCallSiteArray[13].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[14].call(((Reference<Object>)mName).get(), ScriptBytecodeAdapter.createRange(JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$const$0, JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$const$1, true)))) : null, $get$$class$groovy$lang$MetaProperty());
        if (!DefaultTypeTransformation.booleanUnbox(prop)) {
            final Object map = ScriptBytecodeAdapter.createMap(new Object[0]);
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)mName).get(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5(), map, "name");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[15].call(new GStringImpl(new Object[] { $getCallSiteArray[16].callGetProperty(((Reference<Object>)method2).get()), $getCallSiteArray[17].call($getCallSiteArray[18].call(this.object.get())) }, new String[] { "Method ", " for class ", "" })), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5(), map, "displayName");
            ScriptBytecodeAdapter.setProperty("operation", $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5(), map, "role");
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)method2).get(), $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5(), map, "method");
            $getCallSiteArray[19].call(map, "params", $getCallSiteArray[20].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)method2).get()));
            return $getCallSiteArray[21].call(this.ops.get(), ((Reference<Object>)mName).get(), map);
        }
        return null;
    }
    
    public Object getDeclaredMethods() {
        $getCallSiteArray();
        return this.declaredMethods.get();
    }
    
    public Object getObject() {
        $getCallSiteArray();
        return this.object.get();
    }
    
    public Object getOps() {
        $getCallSiteArray();
        return this.ops.get();
    }
    
    static {
        $const$1 = -1;
        $const$0 = 3;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[22];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5 = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5 = (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5 = class$("groovy.jmx.builder.JmxMetaMapBuilder$_buildOperationMapFrom_closure5"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure5;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$java$lang$String) == null) {
            $class$java$lang$String = (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaProperty() {
        Class $class$groovy$lang$MetaProperty;
        if (($class$groovy$lang$MetaProperty = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$lang$MetaProperty) == null) {
            $class$groovy$lang$MetaProperty = (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$lang$MetaProperty = class$("groovy.lang.MetaProperty"));
        }
        return $class$groovy$lang$MetaProperty;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildOperationMapFrom_closure5.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
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
