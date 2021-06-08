// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.Window;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JMenuBar;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JFrame;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class FrameFactory extends RootPaneContainerFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204986;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$FrameFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$JFrame;
    private static /* synthetic */ Class $class$groovy$swing$factory$RootPaneContainerFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public FrameFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        JFrame frame = (JFrame)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JFrame());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JFrame()))) {
            frame = (JFrame)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$JFrame());
        }
        else {
            frame = (JFrame)$getCallSiteArray[1].callConstructor($get$$class$javax$swing$JFrame());
        }
        $getCallSiteArray[2].callCurrent(this, builder, frame, attributes);
        return ScriptBytecodeAdapter.castToType(frame, $get$$class$java$lang$Object());
    }
    
    public void setChild(final FactoryBuilderSupport build, final Object parent, final Object child) {
        $getCallSiteArray();
        if (child instanceof JMenuBar) {
            ScriptBytecodeAdapter.setProperty(child, $get$$class$groovy$swing$factory$FrameFactory(), parent, "JMenuBar");
        }
        else {
            ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$RootPaneContainerFactory(), this, "setChild", new Object[] { build, parent, child });
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$FrameFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = FrameFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (FrameFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        FrameFactory.__timeStamp__239_neverHappen1292524204986 = 0L;
        FrameFactory.__timeStamp = 1292524204986L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$FrameFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (FrameFactory.$callSiteArray == null || ($createCallSiteArray = FrameFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            FrameFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = FrameFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (FrameFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$FrameFactory() {
        Class $class$groovy$swing$factory$FrameFactory;
        if (($class$groovy$swing$factory$FrameFactory = FrameFactory.$class$groovy$swing$factory$FrameFactory) == null) {
            $class$groovy$swing$factory$FrameFactory = (FrameFactory.$class$groovy$swing$factory$FrameFactory = class$("groovy.swing.factory.FrameFactory"));
        }
        return $class$groovy$swing$factory$FrameFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = FrameFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (FrameFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFrame() {
        Class $class$javax$swing$JFrame;
        if (($class$javax$swing$JFrame = FrameFactory.$class$javax$swing$JFrame) == null) {
            $class$javax$swing$JFrame = (FrameFactory.$class$javax$swing$JFrame = class$("javax.swing.JFrame"));
        }
        return $class$javax$swing$JFrame;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RootPaneContainerFactory() {
        Class $class$groovy$swing$factory$RootPaneContainerFactory;
        if (($class$groovy$swing$factory$RootPaneContainerFactory = FrameFactory.$class$groovy$swing$factory$RootPaneContainerFactory) == null) {
            $class$groovy$swing$factory$RootPaneContainerFactory = (FrameFactory.$class$groovy$swing$factory$RootPaneContainerFactory = class$("groovy.swing.factory.RootPaneContainerFactory"));
        }
        return $class$groovy$swing$factory$RootPaneContainerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = FrameFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (FrameFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
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
