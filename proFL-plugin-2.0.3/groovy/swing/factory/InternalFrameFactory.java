// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.Window;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JInternalFrame;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class InternalFrameFactory extends RootPaneContainerFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204989;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$JInternalFrame;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$InternalFrameFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public InternalFrameFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JInternalFrame()))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        final JInternalFrame frame = (JInternalFrame)$getCallSiteArray[1].callConstructor($get$$class$javax$swing$JInternalFrame());
        return ScriptBytecodeAdapter.castToType(frame, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$InternalFrameFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = InternalFrameFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (InternalFrameFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        InternalFrameFactory.__timeStamp__239_neverHappen1292524204989 = 0L;
        InternalFrameFactory.__timeStamp = 1292524204989L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$InternalFrameFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (InternalFrameFactory.$callSiteArray == null || ($createCallSiteArray = InternalFrameFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            InternalFrameFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = InternalFrameFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (InternalFrameFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JInternalFrame() {
        Class $class$javax$swing$JInternalFrame;
        if (($class$javax$swing$JInternalFrame = InternalFrameFactory.$class$javax$swing$JInternalFrame) == null) {
            $class$javax$swing$JInternalFrame = (InternalFrameFactory.$class$javax$swing$JInternalFrame = class$("javax.swing.JInternalFrame"));
        }
        return $class$javax$swing$JInternalFrame;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = InternalFrameFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (InternalFrameFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$InternalFrameFactory() {
        Class $class$groovy$swing$factory$InternalFrameFactory;
        if (($class$groovy$swing$factory$InternalFrameFactory = InternalFrameFactory.$class$groovy$swing$factory$InternalFrameFactory) == null) {
            $class$groovy$swing$factory$InternalFrameFactory = (InternalFrameFactory.$class$groovy$swing$factory$InternalFrameFactory = class$("groovy.swing.factory.InternalFrameFactory"));
        }
        return $class$groovy$swing$factory$InternalFrameFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = InternalFrameFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (InternalFrameFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
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
