// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import javax.management.MBeanServer;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxBeansFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204306;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeansFactory;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    
    public JmxBeansFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeParam, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(nodeParam) && nodeParam instanceof List) ? Boolean.FALSE : Boolean.TRUE)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", "' requires a list of object to be exported." }));
        }
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final MBeanServer server = (MBeanServer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(fsb), $get$$class$javax$management$MBeanServer()));
        final Object metaMaps = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final Object targets = nodeParam;
        final Object map = null;
        $getCallSiteArray[2].call(targets, new JmxBeansFactory$_newInstance_closure1(this, this, (Reference<Object>)server, (Reference<Object>)metaMaps));
        return ScriptBytecodeAdapter.castToType(((Reference<Object>)metaMaps).get(), $get$$class$java$lang$Object());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parentNode, final Object thisNode) {
        final Object parentNode2 = new Reference(parentNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final MBeanServer server = (MBeanServer)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(fsb), $get$$class$javax$management$MBeanServer());
        final Object metaMaps = thisNode;
        Object callGetPropertySafe;
        if (!DefaultTypeTransformation.booleanUnbox(callGetPropertySafe = $getCallSiteArray[4].callGetPropertySafe($getCallSiteArray[5].call(fsb)))) {
            callGetPropertySafe = "replace";
        }
        final Object regPolicy = new Reference(callGetPropertySafe);
        $getCallSiteArray[6].call(metaMaps, new JmxBeansFactory$_onNodeCompleted_closure2(this, this, (Reference<Object>)parentNode2, (Reference<Object>)regPolicy));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxBeansFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxBeansFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxBeansFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxBeansFactory.__timeStamp__239_neverHappen1292524204306 = 0L;
        JmxBeansFactory.__timeStamp = 1292524204306L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeansFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeansFactory.$callSiteArray == null || ($createCallSiteArray = JmxBeansFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeansFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxBeansFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxBeansFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBeansFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBeansFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxBeansFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxBeansFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxBeansFactory.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxBeansFactory.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeansFactory() {
        Class $class$groovy$jmx$builder$JmxBeansFactory;
        if (($class$groovy$jmx$builder$JmxBeansFactory = JmxBeansFactory.$class$groovy$jmx$builder$JmxBeansFactory) == null) {
            $class$groovy$jmx$builder$JmxBeansFactory = (JmxBeansFactory.$class$groovy$jmx$builder$JmxBeansFactory = class$("groovy.jmx.builder.JmxBeansFactory"));
        }
        return $class$groovy$jmx$builder$JmxBeansFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxBeansFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxBeansFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxBeansFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxBeansFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
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
