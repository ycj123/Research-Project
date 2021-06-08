// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.util.FactoryBuilderSupport;
import java.awt.Window;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import java.util.ListIterator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.JButton;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class RootPaneContainerFactory$_handleRootPaneTasks_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> container;
    private Reference<Object> builder;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$Window;
    private static /* synthetic */ Class $class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1;
    private static /* synthetic */ Class $class$java$util$ListIterator;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public RootPaneContainerFactory$_handleRootPaneTasks_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> container, final Reference<Object> builder) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.container = container;
        this.builder = builder;
    }
    
    public Object doCall(final Object myBuilder, final Object node, final Object myAttributes) {
        final Object node2 = new Reference(node);
        final Object myAttributes2 = new Reference(myAttributes);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox((((Reference<Object>)node2).get() instanceof JButton && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this.builder.get()), RootPaneContainerFactory$_handleRootPaneTasks_closure1.$const$0), this.container.get())) ? Boolean.TRUE : Boolean.FALSE)) {
            return null;
        }
        final ListIterator li = (ListIterator)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this.builder.get())), $get$$class$java$util$ListIterator()));
        final Map context = (Map)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$Map()));
        while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(((Reference)li).get()))) {
            ((Reference)context).set(ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(((Reference)li).get()), $get$$class$java$util$Map()));
        }
        while (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(((Reference)context).get()) && ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[6].call(((Reference)context).get(), $getCallSiteArray[7].callGetProperty($get$$class$groovy$util$FactoryBuilderSupport())), this.container.get())) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference)context).set(ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(((Reference)li).get()), $get$$class$java$util$Map()));
        }
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[9].call(((Reference)context).get(), $getCallSiteArray[10].callGroovyObjectGetProperty(this)))) {
            o = $getCallSiteArray[11].callGroovyObjectGetProperty(this);
        }
        final Object defaultButtonProperty = o;
        final Object defaultButton = $getCallSiteArray[12].call(((Reference<Object>)myAttributes2).get(), defaultButtonProperty);
        if (DefaultTypeTransformation.booleanUnbox(defaultButton)) {
            final Object value = ((Reference<Object>)node2).get();
            ScriptBytecodeAdapter.setProperty(value, $get$$class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1(), $getCallSiteArray[13].callGetProperty(this.container.get()), "defaultButton");
            return value;
        }
        return null;
    }
    
    public Object call(final Object myBuilder, final Object node, final Object myAttributes) {
        final Object node2 = new Reference(node);
        final Object myAttributes2 = new Reference(myAttributes);
        return $getCallSiteArray()[14].callCurrent(this, myBuilder, ((Reference<Object>)node2).get(), ((Reference<Object>)myAttributes2).get());
    }
    
    public Window getContainer() {
        $getCallSiteArray();
        return (Window)ScriptBytecodeAdapter.castToType(this.container.get(), $get$$class$java$awt$Window());
    }
    
    public FactoryBuilderSupport getBuilder() {
        $getCallSiteArray();
        return (FactoryBuilderSupport)ScriptBytecodeAdapter.castToType(this.builder.get(), $get$$class$groovy$util$FactoryBuilderSupport());
    }
    
    static {
        $const$0 = -1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$callSiteArray == null || ($createCallSiteArray = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RootPaneContainerFactory$_handleRootPaneTasks_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Window() {
        Class $class$java$awt$Window;
        if (($class$java$awt$Window = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$awt$Window) == null) {
            $class$java$awt$Window = (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$awt$Window = class$("java.awt.Window"));
        }
        return $class$java$awt$Window;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1() {
        Class $class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1;
        if (($class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1 = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1) == null) {
            $class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1 = (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1 = class$("groovy.swing.factory.RootPaneContainerFactory$_handleRootPaneTasks_closure1"));
        }
        return $class$groovy$swing$factory$RootPaneContainerFactory$_handleRootPaneTasks_closure1;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ListIterator() {
        Class $class$java$util$ListIterator;
        if (($class$java$util$ListIterator = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$util$ListIterator) == null) {
            $class$java$util$ListIterator = (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$util$ListIterator = class$("java.util.ListIterator"));
        }
        return $class$java$util$ListIterator;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$util$Map) == null) {
            $class$java$util$Map = (RootPaneContainerFactory$_handleRootPaneTasks_closure1.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
