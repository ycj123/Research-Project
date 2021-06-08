// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class GlueFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204418;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$GlueFactory;
    
    public GlueFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call($get$$class$javax$swing$Box()), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$GlueFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GlueFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GlueFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        GlueFactory.__timeStamp__239_neverHappen1292524204418 = 0L;
        GlueFactory.__timeStamp = 1292524204418L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$GlueFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GlueFactory.$callSiteArray == null || ($createCallSiteArray = GlueFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GlueFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GlueFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GlueFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = GlueFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (GlueFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GlueFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GlueFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = GlueFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (GlueFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$GlueFactory() {
        Class $class$groovy$swing$factory$GlueFactory;
        if (($class$groovy$swing$factory$GlueFactory = GlueFactory.$class$groovy$swing$factory$GlueFactory) == null) {
            $class$groovy$swing$factory$GlueFactory = (GlueFactory.$class$groovy$swing$factory$GlueFactory = class$("groovy.swing.factory.GlueFactory"));
        }
        return $class$groovy$swing$factory$GlueFactory;
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
