// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.binding.BindingProxy;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class BindProxyFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204397;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$BindingProxy;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindProxyFactory;
    
    public BindProxyFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " requires a value argument." }));
        }
        final BindingProxy mb = (BindingProxy)$getCallSiteArray[1].callConstructor($get$$class$org$codehaus$groovy$binding$BindingProxy(), value);
        final Object o = $getCallSiteArray[2].call(attributes, "bind");
        ScriptBytecodeAdapter.setProperty((o instanceof Boolean && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Boolean())))) ? Boolean.TRUE : Boolean.FALSE, $get$$class$groovy$swing$factory$BindProxyFactory(), $getCallSiteArray[4].callGroovyObjectGetProperty(builder), "bind");
        return ScriptBytecodeAdapter.castToType(mb, $get$$class$java$lang$Object());
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(builder)))) {
            $getCallSiteArray[7].call(node);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BindProxyFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BindProxyFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BindProxyFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BindProxyFactory.__timeStamp__239_neverHappen1292524204397 = 0L;
        BindProxyFactory.__timeStamp = 1292524204397L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BindProxyFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BindProxyFactory.$callSiteArray == null || ($createCallSiteArray = BindProxyFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BindProxyFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BindProxyFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BindProxyFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BindProxyFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BindProxyFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = BindProxyFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (BindProxyFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$BindingProxy() {
        Class $class$org$codehaus$groovy$binding$BindingProxy;
        if (($class$org$codehaus$groovy$binding$BindingProxy = BindProxyFactory.$class$org$codehaus$groovy$binding$BindingProxy) == null) {
            $class$org$codehaus$groovy$binding$BindingProxy = (BindProxyFactory.$class$org$codehaus$groovy$binding$BindingProxy = class$("org.codehaus.groovy.binding.BindingProxy"));
        }
        return $class$org$codehaus$groovy$binding$BindingProxy;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = BindProxyFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (BindProxyFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindProxyFactory() {
        Class $class$groovy$swing$factory$BindProxyFactory;
        if (($class$groovy$swing$factory$BindProxyFactory = BindProxyFactory.$class$groovy$swing$factory$BindProxyFactory) == null) {
            $class$groovy$swing$factory$BindProxyFactory = (BindProxyFactory.$class$groovy$swing$factory$BindProxyFactory = class$("groovy.swing.factory.BindProxyFactory"));
        }
        return $class$groovy$swing$factory$BindProxyFactory;
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
