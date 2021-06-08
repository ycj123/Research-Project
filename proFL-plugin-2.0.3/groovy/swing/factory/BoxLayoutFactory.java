// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.BoxLayout;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Container;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class BoxLayoutFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204421;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Container;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$swing$factory$BoxLayoutFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$javax$swing$BoxLayout;
    
    public BoxLayoutFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object parent = $getCallSiteArray[1].call(builder);
        if (parent instanceof Container) {
            final Object axisObject = $getCallSiteArray[2].call(attributes, "axis");
            Integer axis = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callGetProperty($get$$class$javax$swing$BoxLayout()), $get$$class$java$lang$Integer());
            if (ScriptBytecodeAdapter.compareNotEqual(axisObject, null)) {
                final Integer i = (Integer)ScriptBytecodeAdapter.castToType(axisObject, $get$$class$java$lang$Integer());
                axis = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(i), $get$$class$java$lang$Integer());
            }
            final Container target = (Container)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), $get$$class$java$awt$Container());
            final BoxLayout answer = (BoxLayout)$getCallSiteArray[6].callConstructor($get$$class$javax$swing$BoxLayout(), target, axis);
            $getCallSiteArray[7].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), target, "layout", answer);
            return ScriptBytecodeAdapter.castToType(answer, $get$$class$java$lang$Object());
        }
        throw (Throwable)$getCallSiteArray[8].callConstructor($get$$class$java$lang$RuntimeException(), "Must be nested inside a Container");
    }
    
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (parent instanceof Container) {
            final Container target = (Container)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), $get$$class$java$awt$Container());
            $getCallSiteArray[10].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), target, "layout", child);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BoxLayoutFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BoxLayoutFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BoxLayoutFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BoxLayoutFactory.__timeStamp__239_neverHappen1292524204421 = 0L;
        BoxLayoutFactory.__timeStamp = 1292524204421L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BoxLayoutFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BoxLayoutFactory.$callSiteArray == null || ($createCallSiteArray = BoxLayoutFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BoxLayoutFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = BoxLayoutFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (BoxLayoutFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = BoxLayoutFactory.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (BoxLayoutFactory.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BoxLayoutFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BoxLayoutFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = BoxLayoutFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (BoxLayoutFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BoxLayoutFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BoxLayoutFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Container() {
        Class $class$java$awt$Container;
        if (($class$java$awt$Container = BoxLayoutFactory.$class$java$awt$Container) == null) {
            $class$java$awt$Container = (BoxLayoutFactory.$class$java$awt$Container = class$("java.awt.Container"));
        }
        return $class$java$awt$Container;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = BoxLayoutFactory.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (BoxLayoutFactory.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BoxLayoutFactory() {
        Class $class$groovy$swing$factory$BoxLayoutFactory;
        if (($class$groovy$swing$factory$BoxLayoutFactory = BoxLayoutFactory.$class$groovy$swing$factory$BoxLayoutFactory) == null) {
            $class$groovy$swing$factory$BoxLayoutFactory = (BoxLayoutFactory.$class$groovy$swing$factory$BoxLayoutFactory = class$("groovy.swing.factory.BoxLayoutFactory"));
        }
        return $class$groovy$swing$factory$BoxLayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = BoxLayoutFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (BoxLayoutFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$BoxLayout() {
        Class $class$javax$swing$BoxLayout;
        if (($class$javax$swing$BoxLayout = BoxLayoutFactory.$class$javax$swing$BoxLayout) == null) {
            $class$javax$swing$BoxLayout = (BoxLayoutFactory.$class$javax$swing$BoxLayout = class$("javax.swing.BoxLayout"));
        }
        return $class$javax$swing$BoxLayout;
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
