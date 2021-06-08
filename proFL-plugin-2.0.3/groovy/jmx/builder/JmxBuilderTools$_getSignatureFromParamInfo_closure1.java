// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxBuilderTools$_getSignatureFromParamInfo_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class array$$class$java$lang$Object;
    
    public JmxBuilderTools$_getSignatureFromParamInfo_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object param, final Object i) {
        final Object param2 = new Reference(param);
        final Object j = new Reference(i);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].call(((Reference<Object>)param2).get())))) {
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[3].call($get$$class$java$lang$Class(), $getCallSiteArray[4].call(((Reference<Object>)param2).get())))) {
                o = null;
            }
        }
        final Object type = o;
        return $getCallSiteArray[5].call(this.result.get(), ((Reference<Object>)j).get(), type);
    }
    
    public Object call(final Object param, final Object i) {
        final Object param2 = new Reference(param);
        final Object j = new Reference(i);
        return $getCallSiteArray()[6].callCurrent(this, ((Reference<Object>)param2).get(), ((Reference<Object>)j).get());
    }
    
    public Object[] getResult() {
        $getCallSiteArray();
        return (Object[])ScriptBytecodeAdapter.castToType(this.result.get(), $get$array$$class$java$lang$Object());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBuilderTools$_getSignatureFromParamInfo_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBuilderTools$_getSignatureFromParamInfo_closure1.$callSiteArray == null || ($createCallSiteArray = JmxBuilderTools$_getSignatureFromParamInfo_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBuilderTools$_getSignatureFromParamInfo_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JmxBuilderTools$_getSignatureFromParamInfo_closure1.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JmxBuilderTools$_getSignatureFromParamInfo_closure1.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Object() {
        Class array$$class$java$lang$Object;
        if ((array$$class$java$lang$Object = JmxBuilderTools$_getSignatureFromParamInfo_closure1.array$$class$java$lang$Object) == null) {
            array$$class$java$lang$Object = (JmxBuilderTools$_getSignatureFromParamInfo_closure1.array$$class$java$lang$Object = class$("[Ljava.lang.Object;"));
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
