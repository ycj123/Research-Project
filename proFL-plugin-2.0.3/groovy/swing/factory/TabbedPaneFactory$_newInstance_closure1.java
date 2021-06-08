// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TabbedPaneFactory$_newInstance_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> builder;
    private Reference<Object> newChild;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$TabbedPaneFactory;
    
    public TabbedPaneFactory$_newInstance_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> builder, final Reference<Object> newChild) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.builder = builder;
        this.newChild = newChild;
    }
    
    public Object doCall(final FactoryBuilderSupport cBuilder, final Object cNode, final Map cAttributes) {
        final FactoryBuilderSupport cBuilder2 = (FactoryBuilderSupport)new Reference(cBuilder);
        final Object cNode2 = new Reference(cNode);
        final Map cAttributes2 = (Map)new Reference(cAttributes);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGroovyObjectGetProperty(this.builder.get()), this.newChild.get())) {
            return $getCallSiteArray[1].callStatic($get$$class$groovy$swing$factory$TabbedPaneFactory(), ((Reference<Object>)cBuilder2).get(), ((Reference<Object>)cNode2).get(), ((Reference)cAttributes2).get());
        }
        return null;
    }
    
    public Object call(final FactoryBuilderSupport cBuilder, final Object cNode, final Map cAttributes) {
        final FactoryBuilderSupport cBuilder2 = (FactoryBuilderSupport)new Reference(cBuilder);
        final Object cNode2 = new Reference(cNode);
        final Map cAttributes2 = (Map)new Reference(cAttributes);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)cBuilder2).get(), ((Reference<Object>)cNode2).get(), ((Reference)cAttributes2).get());
    }
    
    public FactoryBuilderSupport getBuilder() {
        $getCallSiteArray();
        return (FactoryBuilderSupport)ScriptBytecodeAdapter.castToType(this.builder.get(), $get$$class$groovy$util$FactoryBuilderSupport());
    }
    
    public Object getNewChild() {
        $getCallSiteArray();
        return this.newChild.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TabbedPaneFactory$_newInstance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TabbedPaneFactory$_newInstance_closure1.$callSiteArray == null || ($createCallSiteArray = TabbedPaneFactory$_newInstance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TabbedPaneFactory$_newInstance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TabbedPaneFactory$_newInstance_closure1.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TabbedPaneFactory$_newInstance_closure1.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TabbedPaneFactory() {
        Class $class$groovy$swing$factory$TabbedPaneFactory;
        if (($class$groovy$swing$factory$TabbedPaneFactory = TabbedPaneFactory$_newInstance_closure1.$class$groovy$swing$factory$TabbedPaneFactory) == null) {
            $class$groovy$swing$factory$TabbedPaneFactory = (TabbedPaneFactory$_newInstance_closure1.$class$groovy$swing$factory$TabbedPaneFactory = class$("groovy.swing.factory.TabbedPaneFactory"));
        }
        return $class$groovy$swing$factory$TabbedPaneFactory;
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
