// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import groovy.lang.GroovyObject;

public class MockFor implements GroovyObject
{
    private MockProxyMetaClass proxy;
    private Demand demand;
    private Ignore ignore;
    private Object expect;
    private Map instanceExpectations;
    private Class clazz;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202953;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$lang$reflect$Modifier;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$MockInterceptor;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$MockProxyMetaClass;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$MockFor;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$StrictExpectation;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Ignore;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Demand;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$groovy$util$ProxyGenerator;
    private static /* synthetic */ Class $class$java$util$HashMap;
    private static /* synthetic */ Class $class$groovy$lang$GroovyObject;
    
    public MockFor(final Class clazz, final boolean interceptConstruction) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.instanceExpectations = (Map)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(interceptConstruction)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$lang$GroovyObject(), clazz))) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[1].callConstructor($get$$class$java$lang$IllegalArgumentException(), $getCallSiteArray[2].call("MockFor with constructor interception enabled is only allowed for Groovy objects but found: ", $getCallSiteArray[3].callGetProperty(clazz)));
        }
        this.clazz = (Class)ScriptBytecodeAdapter.castToType(clazz, $get$$class$java$lang$Class());
        this.proxy = (MockProxyMetaClass)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($get$$class$groovy$mock$interceptor$MockProxyMetaClass(), clazz, DefaultTypeTransformation.box(interceptConstruction)), $get$$class$groovy$mock$interceptor$MockProxyMetaClass()), $get$$class$groovy$mock$interceptor$MockProxyMetaClass());
        this.demand = (Demand)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callConstructor($get$$class$groovy$mock$interceptor$Demand()), $get$$class$groovy$mock$interceptor$Demand());
        this.ignore = (Ignore)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].callConstructor($get$$class$groovy$mock$interceptor$Ignore(), ScriptBytecodeAdapter.createMap(new Object[] { "parent", this })), $get$$class$groovy$mock$interceptor$Ignore());
        this.expect = $getCallSiteArray[7].callConstructor($get$$class$groovy$mock$interceptor$StrictExpectation(), this.demand);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[8].callConstructor($get$$class$groovy$mock$interceptor$MockInterceptor(), ScriptBytecodeAdapter.createMap(new Object[] { "expectation", this.expect })), $get$$class$groovy$mock$interceptor$MockFor(), this.proxy, "interceptor");
    }
    
    public MockFor(final Class clazz) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = ScriptBytecodeAdapter.createPojoWrapper(clazz, $get$$class$java$lang$Class());
        arguments[1] = ScriptBytecodeAdapter.createPojoWrapper(Boolean.FALSE, Boolean.TYPE);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$mock$interceptor$MockFor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Class)array[0]);
                break;
            }
            case 1: {
                this((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public void use(final Closure closure) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[9].call(this.proxy, closure);
        $getCallSiteArray[10].call(this.expect);
    }
    
    public void use(final GroovyObject obj, final Closure closure) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[11].call(this.proxy, obj, closure);
        $getCallSiteArray[12].call(this.expect);
    }
    
    public void verify(final GroovyObject obj) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[13].call($getCallSiteArray[14].call(this.instanceExpectations, obj));
    }
    
    public Object ignore(Object filter, final Closure filterBehavior) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[15].callGetProperty(this.clazz), "java.lang.String") && filter instanceof String) ? Boolean.TRUE : Boolean.FALSE)) {
            filter = $getCallSiteArray[16].call($get$$class$java$util$regex$Pattern(), filter);
        }
        final CallSite callSite = $getCallSiteArray[17];
        final Object callGroovyObjectGetProperty = $getCallSiteArray[18].callGroovyObjectGetProperty(this.demand);
        final Object o = filter;
        Object callGetProperty = filterBehavior;
        if (!DefaultTypeTransformation.booleanUnbox(filterBehavior)) {
            callGetProperty = $getCallSiteArray[19].callGetProperty($get$$class$groovy$mock$interceptor$MockProxyMetaClass());
        }
        return callSite.call(callGroovyObjectGetProperty, o, callGetProperty);
    }
    
    public GroovyObject proxyInstance(final Object args) {
        return (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray()[20].callCurrent(this, args, Boolean.FALSE), $get$$class$groovy$lang$GroovyObject());
    }
    
    public GroovyObject proxyDelegateInstance(final Object args) {
        return (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray()[21].callCurrent(this, args, Boolean.TRUE), $get$$class$groovy$lang$GroovyObject());
    }
    
    public GroovyObject makeProxyInstance(final Object args, final boolean isDelegate) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object instance = $getCallSiteArray[22].callStatic($get$$class$groovy$mock$interceptor$MockFor(), this.clazz, args);
        final Object thisproxy = $getCallSiteArray[23].call($get$$class$groovy$mock$interceptor$MockProxyMetaClass(), DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(isDelegate)) ? $getCallSiteArray[24].call(instance) : this.clazz);
        final Object thisdemand = $getCallSiteArray[25].callConstructor($get$$class$groovy$mock$interceptor$Demand(), ScriptBytecodeAdapter.createMap(new Object[] { "recorded", $getCallSiteArray[26].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[27].callGroovyObjectGetProperty(this.demand)), "ignore", $getCallSiteArray[28].callConstructor($get$$class$java$util$HashMap(), $getCallSiteArray[29].callGroovyObjectGetProperty(this.demand)) }));
        final Object thisexpect = $getCallSiteArray[30].callConstructor($get$$class$groovy$mock$interceptor$StrictExpectation(), thisdemand);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[31].callConstructor($get$$class$groovy$mock$interceptor$MockInterceptor(), ScriptBytecodeAdapter.createMap(new Object[] { "expectation", thisexpect })), $get$$class$groovy$mock$interceptor$MockFor(), thisproxy, "interceptor");
        ScriptBytecodeAdapter.setProperty(thisproxy, $get$$class$groovy$mock$interceptor$MockFor(), instance, "metaClass");
        Object wrapped = instance;
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(isDelegate)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[32].call(this.clazz))) ? Boolean.TRUE : Boolean.FALSE)) {
            wrapped = $getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty($get$$class$groovy$util$ProxyGenerator()), ScriptBytecodeAdapter.createList(new Object[] { this.clazz }), instance);
        }
        $getCallSiteArray[35].call(this.instanceExpectations, wrapped, thisexpect);
        return (GroovyObject)ScriptBytecodeAdapter.castToType(wrapped, $get$$class$groovy$lang$GroovyObject());
    }
    
    public static GroovyObject getInstance(final Class clazz, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        GroovyObject instance = (GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$GroovyObject());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[36].call(clazz))) {
            instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[37].call($getCallSiteArray[38].callGetProperty($get$$class$groovy$util$ProxyGenerator()), clazz), $get$$class$groovy$lang$GroovyObject());
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[39].call($get$$class$java$lang$reflect$Modifier(), $getCallSiteArray[40].callGetProperty(clazz)))) {
            instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[41].call($getCallSiteArray[42].callGetProperty($get$$class$groovy$util$ProxyGenerator()), clazz, args), $get$$class$groovy$lang$GroovyObject());
        }
        else if (ScriptBytecodeAdapter.compareNotEqual(args, null)) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[43].call($get$$class$groovy$lang$GroovyObject(), clazz))) {
                instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[44].call(clazz, args), $get$$class$groovy$lang$GroovyObject());
            }
            else {
                instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[45].call($getCallSiteArray[46].callGetProperty($get$$class$groovy$util$ProxyGenerator()), $getCallSiteArray[47].call(clazz, args)), $get$$class$groovy$lang$GroovyObject());
            }
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[48].call($get$$class$groovy$lang$GroovyObject(), clazz))) {
            instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[49].call(clazz), $get$$class$groovy$lang$GroovyObject());
        }
        else {
            instance = (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[50].call($getCallSiteArray[51].callGetProperty($get$$class$groovy$util$ProxyGenerator()), $getCallSiteArray[52].call(clazz)), $get$$class$groovy$lang$GroovyObject());
        }
        return (GroovyObject)ScriptBytecodeAdapter.castToType(instance, $get$$class$groovy$lang$GroovyObject());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$MockFor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = MockFor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (MockFor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public Object ignore(final Object filter) {
        return $getCallSiteArray()[53].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(filter, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
    }
    
    public GroovyObject proxyInstance() {
        return (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray()[54].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object())), $get$$class$groovy$lang$GroovyObject());
    }
    
    public GroovyObject proxyDelegateInstance() {
        return (GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray()[55].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object())), $get$$class$groovy$lang$GroovyObject());
    }
    
    static {
        MockFor.__timeStamp__239_neverHappen1292524202953 = 0L;
        MockFor.__timeStamp = 1292524202953L;
    }
    
    public MockProxyMetaClass getProxy() {
        return this.proxy;
    }
    
    public void setProxy(final MockProxyMetaClass proxy) {
        this.proxy = proxy;
    }
    
    public Demand getDemand() {
        return this.demand;
    }
    
    public void setDemand(final Demand demand) {
        this.demand = demand;
    }
    
    public Ignore getIgnore() {
        return this.ignore;
    }
    
    public void setIgnore(final Ignore ignore) {
        this.ignore = ignore;
    }
    
    public Object getExpect() {
        return this.expect;
    }
    
    public void setExpect(final Object expect) {
        this.expect = expect;
    }
    
    public Map getInstanceExpectations() {
        return this.instanceExpectations;
    }
    
    public void setInstanceExpectations(final Map instanceExpectations) {
        this.instanceExpectations = instanceExpectations;
    }
    
    public Class getClazz() {
        return this.clazz;
    }
    
    public void setClazz(final Class clazz) {
        this.clazz = clazz;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[56];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$MockFor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MockFor.$callSiteArray == null || ($createCallSiteArray = MockFor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MockFor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = MockFor.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (MockFor.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$reflect$Modifier() {
        Class $class$java$lang$reflect$Modifier;
        if (($class$java$lang$reflect$Modifier = MockFor.$class$java$lang$reflect$Modifier) == null) {
            $class$java$lang$reflect$Modifier = (MockFor.$class$java$lang$reflect$Modifier = class$("java.lang.reflect.Modifier"));
        }
        return $class$java$lang$reflect$Modifier;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$MockInterceptor() {
        Class $class$groovy$mock$interceptor$MockInterceptor;
        if (($class$groovy$mock$interceptor$MockInterceptor = MockFor.$class$groovy$mock$interceptor$MockInterceptor) == null) {
            $class$groovy$mock$interceptor$MockInterceptor = (MockFor.$class$groovy$mock$interceptor$MockInterceptor = class$("groovy.mock.interceptor.MockInterceptor"));
        }
        return $class$groovy$mock$interceptor$MockInterceptor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = MockFor.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (MockFor.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = MockFor.$class$java$util$Map) == null) {
            $class$java$util$Map = (MockFor.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = MockFor.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (MockFor.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$MockProxyMetaClass() {
        Class $class$groovy$mock$interceptor$MockProxyMetaClass;
        if (($class$groovy$mock$interceptor$MockProxyMetaClass = MockFor.$class$groovy$mock$interceptor$MockProxyMetaClass) == null) {
            $class$groovy$mock$interceptor$MockProxyMetaClass = (MockFor.$class$groovy$mock$interceptor$MockProxyMetaClass = class$("groovy.mock.interceptor.MockProxyMetaClass"));
        }
        return $class$groovy$mock$interceptor$MockProxyMetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$MockFor() {
        Class $class$groovy$mock$interceptor$MockFor;
        if (($class$groovy$mock$interceptor$MockFor = MockFor.$class$groovy$mock$interceptor$MockFor) == null) {
            $class$groovy$mock$interceptor$MockFor = (MockFor.$class$groovy$mock$interceptor$MockFor = class$("groovy.mock.interceptor.MockFor"));
        }
        return $class$groovy$mock$interceptor$MockFor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$StrictExpectation() {
        Class $class$groovy$mock$interceptor$StrictExpectation;
        if (($class$groovy$mock$interceptor$StrictExpectation = MockFor.$class$groovy$mock$interceptor$StrictExpectation) == null) {
            $class$groovy$mock$interceptor$StrictExpectation = (MockFor.$class$groovy$mock$interceptor$StrictExpectation = class$("groovy.mock.interceptor.StrictExpectation"));
        }
        return $class$groovy$mock$interceptor$StrictExpectation;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = MockFor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (MockFor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Ignore() {
        Class $class$groovy$mock$interceptor$Ignore;
        if (($class$groovy$mock$interceptor$Ignore = MockFor.$class$groovy$mock$interceptor$Ignore) == null) {
            $class$groovy$mock$interceptor$Ignore = (MockFor.$class$groovy$mock$interceptor$Ignore = class$("groovy.mock.interceptor.Ignore"));
        }
        return $class$groovy$mock$interceptor$Ignore;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = MockFor.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (MockFor.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = MockFor.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (MockFor.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Demand() {
        Class $class$groovy$mock$interceptor$Demand;
        if (($class$groovy$mock$interceptor$Demand = MockFor.$class$groovy$mock$interceptor$Demand) == null) {
            $class$groovy$mock$interceptor$Demand = (MockFor.$class$groovy$mock$interceptor$Demand = class$("groovy.mock.interceptor.Demand"));
        }
        return $class$groovy$mock$interceptor$Demand;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = MockFor.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (MockFor.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ProxyGenerator() {
        Class $class$groovy$util$ProxyGenerator;
        if (($class$groovy$util$ProxyGenerator = MockFor.$class$groovy$util$ProxyGenerator) == null) {
            $class$groovy$util$ProxyGenerator = (MockFor.$class$groovy$util$ProxyGenerator = class$("groovy.util.ProxyGenerator"));
        }
        return $class$groovy$util$ProxyGenerator;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = MockFor.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (MockFor.$class$java$util$HashMap = class$("java.util.HashMap"));
        }
        return $class$java$util$HashMap;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyObject() {
        Class $class$groovy$lang$GroovyObject;
        if (($class$groovy$lang$GroovyObject = MockFor.$class$groovy$lang$GroovyObject) == null) {
            $class$groovy$lang$GroovyObject = (MockFor.$class$groovy$lang$GroovyObject = class$("groovy.lang.GroovyObject"));
        }
        return $class$groovy$lang$GroovyObject;
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
