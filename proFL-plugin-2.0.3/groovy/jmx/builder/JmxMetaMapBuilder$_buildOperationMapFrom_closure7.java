// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Map;
import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildOperationMapFrom_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private Reference<Object> object;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class array$$class$java$lang$Object;
    
    public JmxMetaMapBuilder$_buildOperationMapFrom_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> map, final Reference<Object> object) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
        this.object = object;
    }
    
    public Object doCall(final Object opName, final Object descriptor) {
        final Object opName2 = new Reference(opName);
        final Object descriptor2 = new Reference(descriptor);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object params = new Reference(null);
        final Object method = new Reference(null);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && DefaultTypeTransformation.booleanUnbox((((Reference<Object>)descriptor2).get() instanceof String && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(((Reference<Object>)descriptor2).get(), "*"))) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Object>)method).set($getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(this.object.get()), this.object.get(), ((Reference<Object>)opName2).get()), JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$const$0));
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && ((Reference<Object>)descriptor2).get() instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
                ((Reference<Object>)params).set(((Reference<Object>)descriptor2).get());
            }
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && ((Reference<Object>)descriptor2).get() instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
                if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].callGetProperty(((Reference<Object>)descriptor2).get())) && $getCallSiteArray[5].callGetProperty(((Reference<Object>)descriptor2).get()) instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
                    ((Reference<Object>)params).set($getCallSiteArray[6].call($getCallSiteArray[7].call($getCallSiteArray[8].callGetPropertySafe(((Reference<Object>)descriptor2).get()))));
                }
                if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].callGetProperty(((Reference<Object>)descriptor2).get())) && $getCallSiteArray[10].callGetProperty(((Reference<Object>)descriptor2).get()) instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
                    ((Reference<Object>)params).set($getCallSiteArray[11].callGetProperty(((Reference<Object>)descriptor2).get()));
                }
            }
            if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)params).get())) {
                final Object paramTypes = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
                $getCallSiteArray[12].callSafe(((Reference<Object>)params).get(), new JmxMetaMapBuilder$_buildOperationMapFrom_closure7_closure14(this, this.getThisObject(), (Reference<Object>)paramTypes));
                Object value;
                if (!DefaultTypeTransformation.booleanUnbox(value = ((Reference<Object>)paramTypes).get())) {
                    value = null;
                }
                ((Reference<Object>)params).set(value);
            }
            final Object signature = ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)params).get(), null) ? ScriptBytecodeAdapter.castToType(((Reference<Object>)params).get(), $get$array$$class$java$lang$Object()) : null;
            final Object methods = $getCallSiteArray[13].call($getCallSiteArray[14].callGetProperty(this.object.get()), this.object.get(), ((Reference<Object>)opName2).get(), signature);
            Object call;
            if (!DefaultTypeTransformation.booleanUnbox(call = $getCallSiteArray[15].call(methods, JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$const$0))) {
                call = null;
            }
            ((Reference<Object>)method).set(call);
        }
        if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)method).get())) {
            return $getCallSiteArray[16].call(this.map.get(), ((Reference<Object>)opName2).get(), $getCallSiteArray[17].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), this.object.get(), ((Reference<Object>)method).get(), ((Reference<Object>)descriptor2).get()));
        }
        return null;
    }
    
    public Object call(final Object opName, final Object descriptor) {
        final Object opName2 = new Reference(opName);
        final Object descriptor2 = new Reference(descriptor);
        return $getCallSiteArray()[18].callCurrent(this, ((Reference<Object>)opName2).get(), ((Reference<Object>)descriptor2).get());
    }
    
    public Object getMap() {
        $getCallSiteArray();
        return this.map.get();
    }
    
    public Object getObject() {
        $getCallSiteArray();
        return this.object.get();
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildOperationMapFrom_closure7.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Object() {
        Class array$$class$java$lang$Object;
        if ((array$$class$java$lang$Object = JmxMetaMapBuilder$_buildOperationMapFrom_closure7.array$$class$java$lang$Object) == null) {
            array$$class$java$lang$Object = (JmxMetaMapBuilder$_buildOperationMapFrom_closure7.array$$class$java$lang$Object = class$("[Ljava.lang.Object;"));
        }
        return array$$class$java$lang$Object;
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
