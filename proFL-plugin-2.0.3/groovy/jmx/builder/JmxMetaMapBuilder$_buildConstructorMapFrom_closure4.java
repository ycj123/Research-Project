// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildConstructorMapFrom_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private Reference<Object> object;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$reflect$Constructor;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class array$$class$java$lang$Class;
    
    public JmxMetaMapBuilder$_buildConstructorMapFrom_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> map, final Reference<Object> object) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
        this.object = object;
    }
    
    public Object doCall(final Object ctorKey, final Object descriptor) {
        final Object ctorKey2 = new Reference(ctorKey);
        final Object descriptor2 = new Reference(descriptor);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object params = new Reference(null);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && DefaultTypeTransformation.booleanUnbox((((Reference<Object>)descriptor2).get() instanceof List && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].call(((Reference<Object>)descriptor2).get()), JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Object>)params).set(null);
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && DefaultTypeTransformation.booleanUnbox((((Reference<Object>)descriptor2).get() instanceof List && ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[1].call(((Reference<Object>)descriptor2).get()), JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<List>)params).set(ScriptBytecodeAdapter.createList(new Object[0]));
            $getCallSiteArray[2].call(((Reference<Object>)descriptor2).get(), new JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure12(this, this.getThisObject(), (Reference<Object>)params));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)descriptor2).get()) && ((Reference<Object>)descriptor2).get() instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
            final Object paramTypes = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].callGetProperty(((Reference<Object>)descriptor2).get())) && $getCallSiteArray[4].callGetProperty(((Reference<Object>)descriptor2).get()) instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
                ((Reference<Object>)paramTypes).set($getCallSiteArray[5].call($getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty(((Reference<Object>)descriptor2).get()))));
            }
            else if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGetProperty(((Reference<Object>)descriptor2).get())) && $getCallSiteArray[9].callGetProperty(((Reference<Object>)descriptor2).get()) instanceof List) ? Boolean.TRUE : Boolean.FALSE)) {
                ((Reference<Object>)paramTypes).set($getCallSiteArray[10].callGetProperty(((Reference<Object>)descriptor2).get()));
            }
            ((Reference<List>)params).set(ScriptBytecodeAdapter.createList(new Object[0]));
            $getCallSiteArray[11].call(((Reference<Object>)paramTypes).get(), new JmxMetaMapBuilder$_buildConstructorMapFrom_closure4_closure13(this, this.getThisObject(), (Reference<Object>)params));
        }
        final Constructor ctor = (Constructor)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGetProperty(this.object.get()), ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)params).get(), null) ? ScriptBytecodeAdapter.castToType(((Reference<Object>)params).get(), $get$array$$class$java$lang$Class()) : null), $get$$class$java$lang$reflect$Constructor());
        return $getCallSiteArray[14].call(this.map.get(), ((Reference<Object>)ctorKey2).get(), $getCallSiteArray[15].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ctor, ((Reference<Object>)descriptor2).get()));
    }
    
    public Object call(final Object ctorKey, final Object descriptor) {
        final Object ctorKey2 = new Reference(ctorKey);
        final Object descriptor2 = new Reference(descriptor);
        return $getCallSiteArray()[16].callCurrent(this, ((Reference<Object>)ctorKey2).get(), ((Reference<Object>)descriptor2).get());
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
        final String[] names = new String[17];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildConstructorMapFrom_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$reflect$Constructor() {
        Class $class$java$lang$reflect$Constructor;
        if (($class$java$lang$reflect$Constructor = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$class$java$lang$reflect$Constructor) == null) {
            $class$java$lang$reflect$Constructor = (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$class$java$lang$reflect$Constructor = class$("java.lang.reflect.Constructor"));
        }
        return $class$java$lang$reflect$Constructor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Class() {
        Class array$$class$java$lang$Class;
        if ((array$$class$java$lang$Class = JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.array$$class$java$lang$Class) == null) {
            array$$class$java$lang$Class = (JmxMetaMapBuilder$_buildConstructorMapFrom_closure4.array$$class$java$lang$Class = class$("[Ljava.lang.Class;"));
        }
        return array$$class$java$lang$Class;
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
