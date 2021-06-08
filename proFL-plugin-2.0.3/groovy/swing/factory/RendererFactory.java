// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JList;
import javax.swing.JTree;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class RendererFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204494;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$RendererFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$impl$ClosureRenderer;
    
    public RendererFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$groovy$swing$impl$ClosureRenderer()), $get$$class$java$lang$Object());
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (child instanceof Component) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(parent), child), $get$$class$groovy$swing$factory$RendererFactory(), parent, "children");
        }
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(builder)), $get$$class$groovy$swing$factory$RendererFactory(), node, "update");
        if (parent instanceof JTree) {
            ScriptBytecodeAdapter.setProperty(node, $get$$class$groovy$swing$factory$RendererFactory(), parent, "cellRenderer");
        }
        else if (parent instanceof JList) {
            ScriptBytecodeAdapter.setProperty(node, $get$$class$groovy$swing$factory$RendererFactory(), parent, "cellRenderer");
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$RendererFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RendererFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RendererFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RendererFactory.__timeStamp__239_neverHappen1292524204494 = 0L;
        RendererFactory.__timeStamp = 1292524204494L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RendererFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RendererFactory.$callSiteArray == null || ($createCallSiteArray = RendererFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RendererFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RendererFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RendererFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RendererFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RendererFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RendererFactory() {
        Class $class$groovy$swing$factory$RendererFactory;
        if (($class$groovy$swing$factory$RendererFactory = RendererFactory.$class$groovy$swing$factory$RendererFactory) == null) {
            $class$groovy$swing$factory$RendererFactory = (RendererFactory.$class$groovy$swing$factory$RendererFactory = class$("groovy.swing.factory.RendererFactory"));
        }
        return $class$groovy$swing$factory$RendererFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = RendererFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (RendererFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$ClosureRenderer() {
        Class $class$groovy$swing$impl$ClosureRenderer;
        if (($class$groovy$swing$impl$ClosureRenderer = RendererFactory.$class$groovy$swing$impl$ClosureRenderer) == null) {
            $class$groovy$swing$impl$ClosureRenderer = (RendererFactory.$class$groovy$swing$impl$ClosureRenderer = class$("groovy.swing.impl.ClosureRenderer"));
        }
        return $class$groovy$swing$impl$ClosureRenderer;
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
