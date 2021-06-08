// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import javax.management.MBeanServer;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxBeansFactory$_newInstance_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> server;
    private Reference<Object> metaMaps;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1;
    
    public JmxBeansFactory$_newInstance_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> server, final Reference<Object> metaMaps) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.server = server;
        this.metaMaps = metaMaps;
    }
    
    public Object doCall(final Object target) {
        final Object target2 = new Reference(target);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object metaMap = $getCallSiteArray[0].call($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)target2).get());
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[1].callGetProperty(metaMap))) {
            messageArgument = this.server.get();
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1(), metaMap, "server");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), $getCallSiteArray[3].call(((Reference<Object>)target2).get())), $get$$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1(), metaMap, "isMBean");
        return $getCallSiteArray[4].call(this.metaMaps.get(), metaMap);
    }
    
    public MBeanServer getServer() {
        $getCallSiteArray();
        return (MBeanServer)ScriptBytecodeAdapter.castToType(this.server.get(), $get$$class$javax$management$MBeanServer());
    }
    
    public Object getMetaMaps() {
        $getCallSiteArray();
        return this.metaMaps.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeansFactory$_newInstance_closure1.$callSiteArray == null || ($createCallSiteArray = JmxBeansFactory$_newInstance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeansFactory$_newInstance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxBeansFactory$_newInstance_closure1.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxBeansFactory$_newInstance_closure1.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1() {
        Class $class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1;
        if (($class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1 = JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1) == null) {
            $class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1 = (JmxBeansFactory$_newInstance_closure1.$class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1 = class$("groovy.jmx.builder.JmxBeansFactory$_newInstance_closure1"));
        }
        return $class$groovy$jmx$builder$JmxBeansFactory$_newInstance_closure1;
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
