// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import javax.swing.border.Border;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class CompoundBorderFactory extends SwingBorderFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Integer $const$4;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204972;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$javax$swing$border$CompoundBorder;
    private static /* synthetic */ Class $class$javax$swing$border$Border;
    private static /* synthetic */ Class $class$groovy$swing$factory$CompoundBorderFactory;
    
    public CompoundBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$CompoundBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        Border border = (Border)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$border$Border());
        if (value instanceof List) {
            final Object call = $getCallSiteArray[2].call(value);
            if (ScriptBytecodeAdapter.isCase(call, CompoundBorderFactory.$const$0)) {
                throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " does not accept an empty array as an value argument" }));
            }
            if (ScriptBytecodeAdapter.isCase(call, CompoundBorderFactory.$const$1)) {
                border = (Border)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(value, CompoundBorderFactory.$const$0), $get$$class$javax$swing$border$Border());
            }
            else if (ScriptBytecodeAdapter.isCase(call, CompoundBorderFactory.$const$2)) {
                border = (Border)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$border$CompoundBorder(), $getCallSiteArray[6].call(value, CompoundBorderFactory.$const$0), $getCallSiteArray[7].call(value, CompoundBorderFactory.$const$1));
            }
            else {
                if (ScriptBytecodeAdapter.isCase(call, CompoundBorderFactory.$const$3)) {}
                border = (Border)$getCallSiteArray[8].callConstructor($get$$class$javax$swing$border$CompoundBorder(), $getCallSiteArray[9].call(value, CompoundBorderFactory.$const$0), $getCallSiteArray[10].call(value, CompoundBorderFactory.$const$1));
                border = (Border)ScriptBytecodeAdapter.castToType($getCallSiteArray[11].call($getCallSiteArray[12].call(value, ScriptBytecodeAdapter.createRange(CompoundBorderFactory.$const$2, CompoundBorderFactory.$const$4, true)), border, new CompoundBorderFactory$_newInstance_closure1(this, this)), $get$$class$javax$swing$border$Border());
            }
        }
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(border) && DefaultTypeTransformation.booleanUnbox(attributes)) ? Boolean.TRUE : Boolean.FALSE)) {
            if (DefaultTypeTransformation.booleanUnbox(value)) {
                throw (Throwable)$getCallSiteArray[13].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " only accepts an array of borders as a value argument" }));
            }
            final Object inner = $getCallSiteArray[14].call(attributes, "inner");
            final Object outer = $getCallSiteArray[15].call(attributes, "outer");
            if (DefaultTypeTransformation.booleanUnbox((inner instanceof Border && outer instanceof Border) ? Boolean.TRUE : Boolean.FALSE)) {
                border = (Border)$getCallSiteArray[16].callConstructor($get$$class$javax$swing$border$CompoundBorder(), outer, inner);
            }
        }
        if (!DefaultTypeTransformation.booleanUnbox(border)) {
            throw (Throwable)$getCallSiteArray[17].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " only accepts an array of javax.swing.border.Border or an inner: and outer: attribute" }));
        }
        return ScriptBytecodeAdapter.castToType(border, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$CompoundBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CompoundBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CompoundBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CompoundBorderFactory.__timeStamp__239_neverHappen1292524204972 = 0L;
        CompoundBorderFactory.__timeStamp = 1292524204972L;
        $const$4 = -1;
        $const$3 = 3;
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$CompoundBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CompoundBorderFactory.$callSiteArray == null || ($createCallSiteArray = CompoundBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CompoundBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CompoundBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CompoundBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CompoundBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CompoundBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = CompoundBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (CompoundBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$CompoundBorder() {
        Class $class$javax$swing$border$CompoundBorder;
        if (($class$javax$swing$border$CompoundBorder = CompoundBorderFactory.$class$javax$swing$border$CompoundBorder) == null) {
            $class$javax$swing$border$CompoundBorder = (CompoundBorderFactory.$class$javax$swing$border$CompoundBorder = class$("javax.swing.border.CompoundBorder"));
        }
        return $class$javax$swing$border$CompoundBorder;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$Border() {
        Class $class$javax$swing$border$Border;
        if (($class$javax$swing$border$Border = CompoundBorderFactory.$class$javax$swing$border$Border) == null) {
            $class$javax$swing$border$Border = (CompoundBorderFactory.$class$javax$swing$border$Border = class$("javax.swing.border.Border"));
        }
        return $class$javax$swing$border$Border;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CompoundBorderFactory() {
        Class $class$groovy$swing$factory$CompoundBorderFactory;
        if (($class$groovy$swing$factory$CompoundBorderFactory = CompoundBorderFactory.$class$groovy$swing$factory$CompoundBorderFactory) == null) {
            $class$groovy$swing$factory$CompoundBorderFactory = (CompoundBorderFactory.$class$groovy$swing$factory$CompoundBorderFactory = class$("groovy.swing.factory.CompoundBorderFactory"));
        }
        return $class$groovy$swing$factory$CompoundBorderFactory;
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
