// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxBuilder$_registerFactories_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxClientConnectorFactory;
    
    public JmxBuilder$_registerFactories_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        return $getCallSiteArray()[0].callConstructor($get$$class$groovy$jmx$builder$JmxClientConnectorFactory());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBuilder$_registerFactories_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBuilder$_registerFactories_closure1.$callSiteArray == null || ($createCallSiteArray = JmxBuilder$_registerFactories_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBuilder$_registerFactories_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBuilder$_registerFactories_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBuilder$_registerFactories_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxClientConnectorFactory() {
        Class $class$groovy$jmx$builder$JmxClientConnectorFactory;
        if (($class$groovy$jmx$builder$JmxClientConnectorFactory = JmxBuilder$_registerFactories_closure1.$class$groovy$jmx$builder$JmxClientConnectorFactory) == null) {
            $class$groovy$jmx$builder$JmxClientConnectorFactory = (JmxBuilder$_registerFactories_closure1.$class$groovy$jmx$builder$JmxClientConnectorFactory = class$("groovy.jmx.builder.JmxClientConnectorFactory"));
        }
        return $class$groovy$jmx$builder$JmxClientConnectorFactory;
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
