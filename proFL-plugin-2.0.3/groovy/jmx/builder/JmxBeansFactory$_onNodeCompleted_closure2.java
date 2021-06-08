// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Iterator;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxBeansFactory$_onNodeCompleted_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> parentNode;
    private Reference<Object> regPolicy;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$Iterator;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxBeansFactory$_onNodeCompleted_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> parentNode, final Reference<Object> regPolicy) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.parentNode = parentNode;
        this.regPolicy = regPolicy;
    }
    
    public Object doCall(final Object metaMap) {
        final Object metaMap2 = new Reference(metaMap);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object registeredBean = new Reference($getCallSiteArray[0].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), this.regPolicy.get(), ((Reference<Object>)metaMap2).get()));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)registeredBean).get()) && ScriptBytecodeAdapter.compareEqual(this.regPolicy.get(), "replace")) ? Boolean.TRUE : Boolean.FALSE)) {
            final Iterator i = (Iterator)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(this.parentNode.get()), $get$$class$java$util$Iterator()));
            while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(((Reference)i).get()))) {
                final Object exportedBean = $getCallSiteArray[3].call(((Reference)i).get());
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call($getCallSiteArray[5].call(exportedBean), $getCallSiteArray[6].callGetProperty(((Reference<Object>)metaMap2).get())))) {
                    $getCallSiteArray[7].call(((Reference)i).get());
                }
            }
        }
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(this.parentNode.get(), null) && DefaultTypeTransformation.booleanUnbox(((Reference<Object>)registeredBean).get())) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[8].call(this.parentNode.get(), ((Reference<Object>)registeredBean).get());
        }
        return null;
    }
    
    public Object getParentNode() {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType(this.parentNode.get(), $get$$class$java$lang$Object());
    }
    
    public Object getRegPolicy() {
        $getCallSiteArray();
        return this.regPolicy.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxBeansFactory$_onNodeCompleted_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxBeansFactory$_onNodeCompleted_closure2.$callSiteArray == null || ($createCallSiteArray = JmxBeansFactory$_onNodeCompleted_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxBeansFactory$_onNodeCompleted_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxBeansFactory$_onNodeCompleted_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxBeansFactory$_onNodeCompleted_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Iterator() {
        Class $class$java$util$Iterator;
        if (($class$java$util$Iterator = JmxBeansFactory$_onNodeCompleted_closure2.$class$java$util$Iterator) == null) {
            $class$java$util$Iterator = (JmxBeansFactory$_onNodeCompleted_closure2.$class$java$util$Iterator = class$("java.util.Iterator"));
        }
        return $class$java$util$Iterator;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxBeansFactory$_onNodeCompleted_closure2.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxBeansFactory$_onNodeCompleted_closure2.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
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
