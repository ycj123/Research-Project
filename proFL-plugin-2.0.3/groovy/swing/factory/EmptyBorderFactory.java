// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class EmptyBorderFactory extends SwingBorderFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204980;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$javax$swing$BorderFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$EmptyBorderFactory;
    
    public EmptyBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$EmptyBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        if (!DefaultTypeTransformation.booleanUnbox(attributes)) {
            if (value instanceof Integer) {
                return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($get$$class$javax$swing$BorderFactory(), value, value, value, value), $get$$class$java$lang$Object());
            }
            if (DefaultTypeTransformation.booleanUnbox((value instanceof List && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[3].call(value), EmptyBorderFactory.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) {
                final Boolean ints = (Boolean)new Reference(Boolean.TRUE);
                $getCallSiteArray[4].call(value, new EmptyBorderFactory$_newInstance_closure1(this, this, (Reference<Object>)ints));
                if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)ints).get())) {
                    return ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call($get$$class$javax$swing$BorderFactory(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { value }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) })), $get$$class$java$lang$Object());
                }
            }
            throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " only accepts a single integer or an array of four integers as a value argument" }));
        }
        else {
            if (!ScriptBytecodeAdapter.compareEqual(value, null)) {
                throw (Throwable)$getCallSiteArray[13].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " cannot be called with both an argulent value and attributes" }));
            }
            final Integer top = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(attributes, "top"), $get$$class$java$lang$Integer());
            final Integer left = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(attributes, "left"), $get$$class$java$lang$Integer());
            final Integer bottom = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(attributes, "bottom"), $get$$class$java$lang$Integer());
            final Integer right = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(attributes, "right"), $get$$class$java$lang$Integer());
            if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(top, null) && !ScriptBytecodeAdapter.compareEqual(top, null)) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(top, null)) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(top, null)) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox(attributes)) ? Boolean.FALSE : Boolean.TRUE)) {
                throw (Throwable)$getCallSiteArray[11].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "When ", " is called it must be call with top:, left:, bottom:, right:, and no other attributes" }));
            }
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($get$$class$javax$swing$BorderFactory(), top, left, bottom, right), $get$$class$java$lang$Object());
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$EmptyBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = EmptyBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (EmptyBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        EmptyBorderFactory.__timeStamp__239_neverHappen1292524204980 = 0L;
        EmptyBorderFactory.__timeStamp = 1292524204980L;
        $const$0 = 4;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$EmptyBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (EmptyBorderFactory.$callSiteArray == null || ($createCallSiteArray = EmptyBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            EmptyBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = EmptyBorderFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (EmptyBorderFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$BorderFactory() {
        Class $class$javax$swing$BorderFactory;
        if (($class$javax$swing$BorderFactory = EmptyBorderFactory.$class$javax$swing$BorderFactory) == null) {
            $class$javax$swing$BorderFactory = (EmptyBorderFactory.$class$javax$swing$BorderFactory = class$("javax.swing.BorderFactory"));
        }
        return $class$javax$swing$BorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = EmptyBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (EmptyBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = EmptyBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (EmptyBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = EmptyBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (EmptyBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$EmptyBorderFactory() {
        Class $class$groovy$swing$factory$EmptyBorderFactory;
        if (($class$groovy$swing$factory$EmptyBorderFactory = EmptyBorderFactory.$class$groovy$swing$factory$EmptyBorderFactory) == null) {
            $class$groovy$swing$factory$EmptyBorderFactory = (EmptyBorderFactory.$class$groovy$swing$factory$EmptyBorderFactory = class$("groovy.swing.factory.EmptyBorderFactory"));
        }
        return $class$groovy$swing$factory$EmptyBorderFactory;
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
