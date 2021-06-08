// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GString;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class TextArgWidgetFactory extends AbstractFactory implements GroovyObject
{
    private final Class klass;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204547;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$TextArgWidgetFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public TextArgWidgetFactory(final Class klass) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.klass = (Class)ScriptBytecodeAdapter.castToType(klass, $get$$class$java$lang$Class());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (value instanceof GString) {
            value = ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, this.klass))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        final Object widget = $getCallSiteArray[1].call(this.klass);
        if (value instanceof String) {
            $getCallSiteArray[2].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), widget, "text", value);
        }
        return ScriptBytecodeAdapter.castToType(widget, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TextArgWidgetFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TextArgWidgetFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TextArgWidgetFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TextArgWidgetFactory.__timeStamp__239_neverHappen1292524204547 = 0L;
        TextArgWidgetFactory.__timeStamp = 1292524204547L;
    }
    
    public final Class getKlass() {
        return this.klass;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TextArgWidgetFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TextArgWidgetFactory.$callSiteArray == null || ($createCallSiteArray = TextArgWidgetFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TextArgWidgetFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TextArgWidgetFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TextArgWidgetFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TextArgWidgetFactory() {
        Class $class$groovy$swing$factory$TextArgWidgetFactory;
        if (($class$groovy$swing$factory$TextArgWidgetFactory = TextArgWidgetFactory.$class$groovy$swing$factory$TextArgWidgetFactory) == null) {
            $class$groovy$swing$factory$TextArgWidgetFactory = (TextArgWidgetFactory.$class$groovy$swing$factory$TextArgWidgetFactory = class$("groovy.swing.factory.TextArgWidgetFactory"));
        }
        return $class$groovy$swing$factory$TextArgWidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TextArgWidgetFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TextArgWidgetFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = TextArgWidgetFactory.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (TextArgWidgetFactory.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TextArgWidgetFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TextArgWidgetFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = TextArgWidgetFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (TextArgWidgetFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = TextArgWidgetFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (TextArgWidgetFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
