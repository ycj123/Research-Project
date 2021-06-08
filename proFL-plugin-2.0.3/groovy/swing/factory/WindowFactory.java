// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.Window;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.util.LinkedList;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JWindow;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class WindowFactory extends RootPaneContainerFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205054;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$WindowFactory;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    private static /* synthetic */ Class $class$javax$swing$JWindow;
    
    public WindowFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        JWindow window = (JWindow)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JWindow());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JWindow()))) {
            window = (JWindow)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$JWindow());
        }
        else {
            final LinkedList containingWindows = (LinkedList)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGroovyObjectGetProperty(builder), $get$$class$java$util$LinkedList());
            Object owner = $getCallSiteArray[2].call(attributes, "owner");
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(owner, null) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].callGetProperty(containingWindows))) ? Boolean.TRUE : Boolean.FALSE)) {
                owner = $getCallSiteArray[4].callGetProperty(containingWindows);
            }
            if (DefaultTypeTransformation.booleanUnbox(owner)) {
                window = (JWindow)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$JWindow(), owner);
            }
            else {
                window = (JWindow)$getCallSiteArray[6].callConstructor($get$$class$javax$swing$JWindow());
            }
        }
        $getCallSiteArray[7].callCurrent(this, builder, window, attributes);
        return ScriptBytecodeAdapter.castToType(window, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$WindowFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = WindowFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (WindowFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        WindowFactory.__timeStamp__239_neverHappen1292524205054 = 0L;
        WindowFactory.__timeStamp = 1292524205054L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$WindowFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (WindowFactory.$callSiteArray == null || ($createCallSiteArray = WindowFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            WindowFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = WindowFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (WindowFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = WindowFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (WindowFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = WindowFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (WindowFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$WindowFactory() {
        Class $class$groovy$swing$factory$WindowFactory;
        if (($class$groovy$swing$factory$WindowFactory = WindowFactory.$class$groovy$swing$factory$WindowFactory) == null) {
            $class$groovy$swing$factory$WindowFactory = (WindowFactory.$class$groovy$swing$factory$WindowFactory = class$("groovy.swing.factory.WindowFactory"));
        }
        return $class$groovy$swing$factory$WindowFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = WindowFactory.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (WindowFactory.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JWindow() {
        Class $class$javax$swing$JWindow;
        if (($class$javax$swing$JWindow = WindowFactory.$class$javax$swing$JWindow) == null) {
            $class$javax$swing$JWindow = (WindowFactory.$class$javax$swing$JWindow = class$("javax.swing.JWindow"));
        }
        return $class$javax$swing$JWindow;
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
