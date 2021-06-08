// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.awt.Window;
import java.awt.Component;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public abstract class RootPaneContainerFactory extends AbstractFactory implements GroovyObject
{
    public static final String DELEGATE_PROPERTY_DEFAULT_BUTTON;
    public static final String DEFAULT_DELEGATE_PROPERTY_DEFAULT_BUTTON;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204502;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$RootPaneContainerFactory;
    
    public RootPaneContainerFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        try {
            final Object constraints = $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(builder));
            if (ScriptBytecodeAdapter.compareNotEqual(constraints, null)) {
                $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(parent), child, constraints);
                $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(builder), "constraints");
            }
            else {
                $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty(parent), child);
            }
        }
        catch (MissingPropertyException mpe) {
            $getCallSiteArray[8].call($getCallSiteArray[9].callGetProperty(parent), child);
        }
    }
    
    public void handleRootPaneTasks(final FactoryBuilderSupport builder, final Window container, final Map attributes) {
        final FactoryBuilderSupport builder2 = (FactoryBuilderSupport)new Reference(builder);
        final Window container2 = (Window)new Reference(container);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[10];
        final Object callGroovyObjectGetProperty = $getCallSiteArray[11].callGroovyObjectGetProperty(((Reference<Object>)builder2).get());
        final String delegate_PROPERTY_DEFAULT_BUTTON = RootPaneContainerFactory.DELEGATE_PROPERTY_DEFAULT_BUTTON;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[12].call(attributes, "defaultButtonProperty"))) {
            o = RootPaneContainerFactory.DEFAULT_DELEGATE_PROPERTY_DEFAULT_BUTTON;
        }
        callSite.call(callGroovyObjectGetProperty, delegate_PROPERTY_DEFAULT_BUTTON, o);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[13].call(((Reference<Object>)builder2).get(), new RootPaneContainerFactory$_handleRootPaneTasks_closure1(this, this, (Reference<Object>)container2, (Reference<Object>)builder2)), $get$$class$groovy$swing$factory$RootPaneContainerFactory(), $getCallSiteArray[14].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "defaultButtonDelegate");
        $getCallSiteArray[15].call($getCallSiteArray[16].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), ((Reference<Object>)container2).get());
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[17].call(attributes, "pack"), $get$$class$groovy$swing$factory$RootPaneContainerFactory(), $getCallSiteArray[18].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "pack");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[19].call(attributes, "show"), $get$$class$groovy$swing$factory$RootPaneContainerFactory(), $getCallSiteArray[20].callGroovyObjectGetProperty(((Reference<Object>)builder2).get()), "show");
        $getCallSiteArray[21].call(((Reference<Object>)builder2).get(), ScriptBytecodeAdapter.getMethodPointer(((Reference<Object>)container2).get(), "dispose"));
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (node instanceof Window) {
            final Object containingWindows = $getCallSiteArray[22].callGroovyObjectGetProperty(builder);
            if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[23].callGetProperty(containingWindows)) && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[24].callGetProperty(containingWindows), node)) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[25].call(containingWindows);
            }
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[26].callGetProperty($getCallSiteArray[27].callGroovyObjectGetProperty(builder)))) {
            $getCallSiteArray[28].call(node);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[29].callGetProperty($getCallSiteArray[30].callGroovyObjectGetProperty(builder)))) {
            ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$swing$factory$RootPaneContainerFactory(), node, "visible");
        }
        $getCallSiteArray[31].call(builder, $getCallSiteArray[32].callGetProperty($getCallSiteArray[33].callGroovyObjectGetProperty(builder)));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$RootPaneContainerFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RootPaneContainerFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RootPaneContainerFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public MetaClass getMetaClass() {
        final MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
    
    public Object invokeMethod(final String s, final Object o) {
        return this.getMetaClass().invokeMethod(this, s, o);
    }
    
    public Object getProperty(final String s) {
        return this.getMetaClass().getProperty(this, s);
    }
    
    public void setProperty(final String s, final Object o) {
        this.getMetaClass().setProperty(this, s, o);
    }
    
    static {
        RootPaneContainerFactory.__timeStamp__239_neverHappen1292524204502 = 0L;
        RootPaneContainerFactory.__timeStamp = 1292524204502L;
        DEFAULT_DELEGATE_PROPERTY_DEFAULT_BUTTON = "defaultButton";
        DELEGATE_PROPERTY_DEFAULT_BUTTON = "_delegateProperty:defaultButton";
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[34];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RootPaneContainerFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RootPaneContainerFactory.$callSiteArray == null || ($createCallSiteArray = RootPaneContainerFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RootPaneContainerFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RootPaneContainerFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RootPaneContainerFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RootPaneContainerFactory() {
        Class $class$groovy$swing$factory$RootPaneContainerFactory;
        if (($class$groovy$swing$factory$RootPaneContainerFactory = RootPaneContainerFactory.$class$groovy$swing$factory$RootPaneContainerFactory) == null) {
            $class$groovy$swing$factory$RootPaneContainerFactory = (RootPaneContainerFactory.$class$groovy$swing$factory$RootPaneContainerFactory = class$("groovy.swing.factory.RootPaneContainerFactory"));
        }
        return $class$groovy$swing$factory$RootPaneContainerFactory;
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
