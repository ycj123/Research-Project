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

public class VGlueFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204414;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$VGlueFactory;
    
    public VGlueFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call($get$$class$javax$swing$Box()), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$VGlueFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = VGlueFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (VGlueFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        VGlueFactory.__timeStamp__239_neverHappen1292524204414 = 0L;
        VGlueFactory.__timeStamp = 1292524204414L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$VGlueFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (VGlueFactory.$callSiteArray == null || ($createCallSiteArray = VGlueFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            VGlueFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = VGlueFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (VGlueFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = VGlueFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (VGlueFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = VGlueFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (VGlueFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = VGlueFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (VGlueFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$VGlueFactory() {
        Class $class$groovy$swing$factory$VGlueFactory;
        if (($class$groovy$swing$factory$VGlueFactory = VGlueFactory.$class$groovy$swing$factory$VGlueFactory) == null) {
            $class$groovy$swing$factory$VGlueFactory = (VGlueFactory.$class$groovy$swing$factory$VGlueFactory = class$("groovy.swing.factory.VGlueFactory"));
        }
        return $class$groovy$swing$factory$VGlueFactory;
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
