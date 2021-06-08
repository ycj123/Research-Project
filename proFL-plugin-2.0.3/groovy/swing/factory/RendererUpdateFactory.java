// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class RendererUpdateFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204496;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$Collections;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$swing$factory$RendererUpdateFactory;
    
    public RendererUpdateFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        return ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].call($get$$class$java$util$Collections()), $get$$class$java$lang$Object());
    }
    
    public boolean isHandlesNodeChildren() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean onNodeChildren(final FactoryBuilderSupport builder, final Object node, final Closure childContent) {
        ScriptBytecodeAdapter.setProperty(childContent, $get$$class$groovy$swing$factory$RendererUpdateFactory(), $getCallSiteArray()[1].callGroovyObjectGetProperty(builder), "updateClosure");
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$RendererUpdateFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RendererUpdateFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RendererUpdateFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RendererUpdateFactory.__timeStamp__239_neverHappen1292524204496 = 0L;
        RendererUpdateFactory.__timeStamp = 1292524204496L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RendererUpdateFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RendererUpdateFactory.$callSiteArray == null || ($createCallSiteArray = RendererUpdateFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RendererUpdateFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RendererUpdateFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RendererUpdateFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RendererUpdateFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RendererUpdateFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Collections() {
        Class $class$java$util$Collections;
        if (($class$java$util$Collections = RendererUpdateFactory.$class$java$util$Collections) == null) {
            $class$java$util$Collections = (RendererUpdateFactory.$class$java$util$Collections = class$("java.util.Collections"));
        }
        return $class$java$util$Collections;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = RendererUpdateFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (RendererUpdateFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RendererUpdateFactory() {
        Class $class$groovy$swing$factory$RendererUpdateFactory;
        if (($class$groovy$swing$factory$RendererUpdateFactory = RendererUpdateFactory.$class$groovy$swing$factory$RendererUpdateFactory) == null) {
            $class$groovy$swing$factory$RendererUpdateFactory = (RendererUpdateFactory.$class$groovy$swing$factory$RendererUpdateFactory = class$("groovy.swing.factory.RendererUpdateFactory"));
        }
        return $class$groovy$swing$factory$RendererUpdateFactory;
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
