// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Map;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.JViewport;
import java.awt.Window;
import java.awt.Component;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class ScrollPaneFactory extends BeanFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205031;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$ScrollPaneFactory;
    private static /* synthetic */ Class $class$javax$swing$JScrollPane;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    
    public ScrollPaneFactory() {
        $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[] { $get$$class$javax$swing$JScrollPane() };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$ScrollPaneFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Class)array[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public ScrollPaneFactory(final Class klass) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = klass;
        arguments[1] = Boolean.FALSE;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$BeanFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Class)array[0]);
                break;
            }
            case 1: {
                super((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void setChild(final FactoryBuilderSupport factory, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[0].callSafe($getCallSiteArray[1].call(parent)), null)) {
            throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$RuntimeException(), "ScrollPane can only have one child component");
        }
        if (child instanceof JViewport) {
            $getCallSiteArray[3].call(parent, child);
        }
        else {
            $getCallSiteArray[4].call(parent, child);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ScrollPaneFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ScrollPaneFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ScrollPaneFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ScrollPaneFactory.__timeStamp__239_neverHappen1292524205031 = 0L;
        ScrollPaneFactory.__timeStamp = 1292524205031L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ScrollPaneFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ScrollPaneFactory.$callSiteArray == null || ($createCallSiteArray = ScrollPaneFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ScrollPaneFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ScrollPaneFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ScrollPaneFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ScrollPaneFactory() {
        Class $class$groovy$swing$factory$ScrollPaneFactory;
        if (($class$groovy$swing$factory$ScrollPaneFactory = ScrollPaneFactory.$class$groovy$swing$factory$ScrollPaneFactory) == null) {
            $class$groovy$swing$factory$ScrollPaneFactory = (ScrollPaneFactory.$class$groovy$swing$factory$ScrollPaneFactory = class$("groovy.swing.factory.ScrollPaneFactory"));
        }
        return $class$groovy$swing$factory$ScrollPaneFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JScrollPane() {
        Class $class$javax$swing$JScrollPane;
        if (($class$javax$swing$JScrollPane = ScrollPaneFactory.$class$javax$swing$JScrollPane) == null) {
            $class$javax$swing$JScrollPane = (ScrollPaneFactory.$class$javax$swing$JScrollPane = class$("javax.swing.JScrollPane"));
        }
        return $class$javax$swing$JScrollPane;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = ScrollPaneFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (ScrollPaneFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = ScrollPaneFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (ScrollPaneFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
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
