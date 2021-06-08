// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.lang.PropertyAccessInterceptor;

public class MockInterceptor implements PropertyAccessInterceptor, GroovyObject
{
    private Object expectation;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202969;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$IllegalStateException;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$MockInterceptor;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$MockProxyMetaClass;
    private static /* synthetic */ Class array$$class$java$lang$Object;
    
    public MockInterceptor() {
        $getCallSiteArray();
        this.expectation = null;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object beforeInvoke(final Object object, final String methodName, final Object... arguments) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.expectation)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$java$lang$IllegalStateException(), "Property 'expectation' must be set before use.");
        }
        final Object result = $getCallSiteArray[1].call(this.expectation, methodName);
        if (ScriptBytecodeAdapter.compareEqual(result, $getCallSiteArray[2].callGetProperty($get$$class$groovy$mock$interceptor$MockProxyMetaClass()))) {
            return result;
        }
        return $getCallSiteArray[3].call(result, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { arguments }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public Object beforeGet(final Object object, final String property) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.expectation)) {
            throw (Throwable)$getCallSiteArray[4].callConstructor($get$$class$java$lang$IllegalStateException(), "Property 'expectation' must be set before use.");
        }
        final String name = (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { $getCallSiteArray[5].call($getCallSiteArray[6].call(property, MockInterceptor.$const$0)), $getCallSiteArray[7].call(property, ScriptBytecodeAdapter.createRange(MockInterceptor.$const$1, MockInterceptor.$const$2, true)) }, new String[] { "get", "", "" }), $get$$class$java$lang$String());
        final Object result = $getCallSiteArray[8].call(this.expectation, name);
        if (ScriptBytecodeAdapter.compareEqual(result, $getCallSiteArray[9].callGetProperty($get$$class$groovy$mock$interceptor$MockProxyMetaClass()))) {
            return result;
        }
        return $getCallSiteArray[10].call(result);
    }
    
    public void beforeSet(final Object object, final String property, final Object newValue) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.expectation)) {
            throw (Throwable)$getCallSiteArray[11].callConstructor($get$$class$java$lang$IllegalStateException(), "Property 'expectation' must be set before use.");
        }
        final String name = (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { $getCallSiteArray[12].call($getCallSiteArray[13].call(property, MockInterceptor.$const$0)), $getCallSiteArray[14].call(property, ScriptBytecodeAdapter.createRange(MockInterceptor.$const$1, MockInterceptor.$const$2, true)) }, new String[] { "set", "", "" }), $get$$class$java$lang$String());
        Object result = $getCallSiteArray[15].call(this.expectation, name);
        if (ScriptBytecodeAdapter.compareNotEqual(result, $getCallSiteArray[16].callGetProperty($get$$class$groovy$mock$interceptor$MockProxyMetaClass()))) {
            $getCallSiteArray[17].call(result, newValue);
            result = null;
        }
        if (object instanceof Object[]) {
            $getCallSiteArray[18].call(ScriptBytecodeAdapter.castToType(object, $get$array$$class$java$lang$Object()), MockInterceptor.$const$0, result);
        }
    }
    
    public Object afterInvoke(final Object object, final String methodName, final Object[] arguments, final Object result) {
        $getCallSiteArray();
        return null;
    }
    
    public boolean doInvoke() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$MockInterceptor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = MockInterceptor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (MockInterceptor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        MockInterceptor.__timeStamp__239_neverHappen1292524202969 = 0L;
        MockInterceptor.__timeStamp = 1292524202969L;
        $const$2 = -1;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public Object getExpectation() {
        return this.expectation;
    }
    
    public void setExpectation(final Object expectation) {
        this.expectation = expectation;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$MockInterceptor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MockInterceptor.$callSiteArray == null || ($createCallSiteArray = MockInterceptor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MockInterceptor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = MockInterceptor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (MockInterceptor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = MockInterceptor.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (MockInterceptor.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalStateException() {
        Class $class$java$lang$IllegalStateException;
        if (($class$java$lang$IllegalStateException = MockInterceptor.$class$java$lang$IllegalStateException) == null) {
            $class$java$lang$IllegalStateException = (MockInterceptor.$class$java$lang$IllegalStateException = class$("java.lang.IllegalStateException"));
        }
        return $class$java$lang$IllegalStateException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$MockInterceptor() {
        Class $class$groovy$mock$interceptor$MockInterceptor;
        if (($class$groovy$mock$interceptor$MockInterceptor = MockInterceptor.$class$groovy$mock$interceptor$MockInterceptor) == null) {
            $class$groovy$mock$interceptor$MockInterceptor = (MockInterceptor.$class$groovy$mock$interceptor$MockInterceptor = class$("groovy.mock.interceptor.MockInterceptor"));
        }
        return $class$groovy$mock$interceptor$MockInterceptor;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = MockInterceptor.$class$java$lang$String) == null) {
            $class$java$lang$String = (MockInterceptor.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$MockProxyMetaClass() {
        Class $class$groovy$mock$interceptor$MockProxyMetaClass;
        if (($class$groovy$mock$interceptor$MockProxyMetaClass = MockInterceptor.$class$groovy$mock$interceptor$MockProxyMetaClass) == null) {
            $class$groovy$mock$interceptor$MockProxyMetaClass = (MockInterceptor.$class$groovy$mock$interceptor$MockProxyMetaClass = class$("groovy.mock.interceptor.MockProxyMetaClass"));
        }
        return $class$groovy$mock$interceptor$MockProxyMetaClass;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Object() {
        Class array$$class$java$lang$Object;
        if ((array$$class$java$lang$Object = MockInterceptor.array$$class$java$lang$Object) == null) {
            array$$class$java$lang$Object = (MockInterceptor.array$$class$java$lang$Object = class$("[Ljava.lang.Object;"));
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
