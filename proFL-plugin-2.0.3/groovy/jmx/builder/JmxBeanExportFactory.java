// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxBeanExportFactory extends AbstractFactory implements GroovyObject
{
    private Object registrationPolicy;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204301;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeanExportFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public JmxBeanExportFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeArgs, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object registrationPolicy;
        if (!DefaultTypeTransformation.booleanUnbox(registrationPolicy = $getCallSiteArray[0].callSafe(nodeAttribs, "policy"))) {
            if (!DefaultTypeTransformation.booleanUnbox(registrationPolicy = $getCallSiteArray[1].callSafe(nodeAttribs, "regPolicy"))) {
                registrationPolicy = "replace";
            }
        }
        this.registrationPolicy = registrationPolicy;
        return ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$lang$Object());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxBeanExportFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxBeanExportFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxBeanExportFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxBeanExportFactory.__timeStamp__239_neverHappen1292524204301 = 0L;
        JmxBeanExportFactory.__timeStamp = 1292524204301L;
    }
    
    public Object getRegistrationPolicy() {
        return this.registrationPolicy;
    }
    
    public void setRegistrationPolicy(final Object registrationPolicy) {
        this.registrationPolicy = registrationPolicy;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeanExportFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeanExportFactory.$callSiteArray == null || ($createCallSiteArray = JmxBeanExportFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeanExportFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeanExportFactory() {
        Class $class$groovy$jmx$builder$JmxBeanExportFactory;
        if (($class$groovy$jmx$builder$JmxBeanExportFactory = JmxBeanExportFactory.$class$groovy$jmx$builder$JmxBeanExportFactory) == null) {
            $class$groovy$jmx$builder$JmxBeanExportFactory = (JmxBeanExportFactory.$class$groovy$jmx$builder$JmxBeanExportFactory = class$("groovy.jmx.builder.JmxBeanExportFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeanExportFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxBeanExportFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxBeanExportFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBeanExportFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBeanExportFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxBeanExportFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxBeanExportFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
