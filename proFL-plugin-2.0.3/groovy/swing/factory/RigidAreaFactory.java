// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Dimension;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class RigidAreaFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204419;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Number;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$swing$factory$RigidAreaFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$awt$Dimension;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public RigidAreaFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        Dimension dim = (Dimension)ScriptBytecodeAdapter.castToType(null, $get$$class$java$awt$Dimension());
        Object o = $getCallSiteArray[1].call(attributes, "size");
        if (o instanceof Dimension) {
            dim = (Dimension)ScriptBytecodeAdapter.castToType(o, $get$$class$java$awt$Dimension());
        }
        else {
            Integer w = (Integer)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Integer());
            Integer h = (Integer)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Integer());
            o = $getCallSiteArray[2].call(attributes, "width");
            w = (Integer)ScriptBytecodeAdapter.castToType((o instanceof Number) ? $getCallSiteArray[3].call(ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Number())) : RigidAreaFactory.$const$0, $get$$class$java$lang$Integer());
            o = $getCallSiteArray[4].call(attributes, "height");
            h = (Integer)ScriptBytecodeAdapter.castToType((o instanceof Number) ? $getCallSiteArray[5].call(ScriptBytecodeAdapter.castToType(o, $get$$class$java$lang$Number())) : RigidAreaFactory.$const$0, $get$$class$java$lang$Integer());
            dim = (Dimension)$getCallSiteArray[6].callConstructor($get$$class$java$awt$Dimension(), w, h);
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call($get$$class$javax$swing$Box(), dim), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$RigidAreaFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RigidAreaFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RigidAreaFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RigidAreaFactory.__timeStamp__239_neverHappen1292524204419 = 0L;
        RigidAreaFactory.__timeStamp = 1292524204419L;
        $const$0 = 6;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RigidAreaFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RigidAreaFactory.$callSiteArray == null || ($createCallSiteArray = RigidAreaFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RigidAreaFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Number() {
        Class $class$java$lang$Number;
        if (($class$java$lang$Number = RigidAreaFactory.$class$java$lang$Number) == null) {
            $class$java$lang$Number = (RigidAreaFactory.$class$java$lang$Number = class$("java.lang.Number"));
        }
        return $class$java$lang$Number;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = RigidAreaFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (RigidAreaFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RigidAreaFactory() {
        Class $class$groovy$swing$factory$RigidAreaFactory;
        if (($class$groovy$swing$factory$RigidAreaFactory = RigidAreaFactory.$class$groovy$swing$factory$RigidAreaFactory) == null) {
            $class$groovy$swing$factory$RigidAreaFactory = (RigidAreaFactory.$class$groovy$swing$factory$RigidAreaFactory = class$("groovy.swing.factory.RigidAreaFactory"));
        }
        return $class$groovy$swing$factory$RigidAreaFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RigidAreaFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RigidAreaFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = RigidAreaFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (RigidAreaFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dimension() {
        Class $class$java$awt$Dimension;
        if (($class$java$awt$Dimension = RigidAreaFactory.$class$java$awt$Dimension) == null) {
            $class$java$awt$Dimension = (RigidAreaFactory.$class$java$awt$Dimension = class$("java.awt.Dimension"));
        }
        return $class$java$awt$Dimension;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RigidAreaFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RigidAreaFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = RigidAreaFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (RigidAreaFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
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
