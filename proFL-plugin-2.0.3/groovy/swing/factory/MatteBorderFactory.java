// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class MatteBorderFactory extends SwingBorderFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205018;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$BorderFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$MatteBorderFactory;
    
    public MatteBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$MatteBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        Object matte = null;
        Object border = null;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(attributes, "icon"))) {
            matte = $getCallSiteArray[3].call(attributes, "icon");
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(attributes, "color"))) {
            matte = $getCallSiteArray[5].call(attributes, "color");
        }
        else {
            if (!ScriptBytecodeAdapter.compareNotEqual(value, null)) {
                throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " must have a matte defined, either as a value argument or as a color: or icon: attribute" }));
            }
            matte = value;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(attributes, "size"))) {
            border = $getCallSiteArray[8].call(attributes, "size");
            border = ScriptBytecodeAdapter.createList(new Object[] { border, border, border, border });
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(attributes, "top"))) {
            final Object top = $getCallSiteArray[10].call(attributes, "top");
            final Object left = $getCallSiteArray[11].call(attributes, "left");
            final Object bottom = $getCallSiteArray[12].call(attributes, "bottom");
            final Object right = $getCallSiteArray[13].call(attributes, "right");
            if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(top, null) && !ScriptBytecodeAdapter.compareEqual(left, null)) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(bottom, null)) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(right, null)) ? Boolean.FALSE : Boolean.TRUE)) {
                throw (Throwable)$getCallSiteArray[14].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " if one of top:, left:, bottom: or right: is specified all must be specified" }));
            }
            border = ScriptBytecodeAdapter.createList(new Object[] { top, left, bottom, right });
        }
        else if (ScriptBytecodeAdapter.compareNotEqual(value, null)) {
            if (ScriptBytecodeAdapter.compareEqual(matte, value)) {
                throw (Throwable)$getCallSiteArray[15].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " some attributes are required in addition to the value argument" }));
            }
            if (value instanceof Integer) {
                border = ScriptBytecodeAdapter.createList(new Object[] { value, value, value, value });
            }
            else {
                border = value;
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(attributes)) {
            throw (Throwable)$getCallSiteArray[16].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " only supports the attributes [ icon: | color:]  [ size: | ( top: left: bottom: right: ) }" }));
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[17].call($get$$class$javax$swing$BorderFactory(), ScriptBytecodeAdapter.despreadList(new Object[] { matte }, new Object[] { border }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) })), $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$MatteBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = MatteBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (MatteBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        MatteBorderFactory.__timeStamp__239_neverHappen1292524205018 = 0L;
        MatteBorderFactory.__timeStamp = 1292524205018L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$MatteBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MatteBorderFactory.$callSiteArray == null || ($createCallSiteArray = MatteBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MatteBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$BorderFactory() {
        Class $class$javax$swing$BorderFactory;
        if (($class$javax$swing$BorderFactory = MatteBorderFactory.$class$javax$swing$BorderFactory) == null) {
            $class$javax$swing$BorderFactory = (MatteBorderFactory.$class$javax$swing$BorderFactory = class$("javax.swing.BorderFactory"));
        }
        return $class$javax$swing$BorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = MatteBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (MatteBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = MatteBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (MatteBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = MatteBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (MatteBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$MatteBorderFactory() {
        Class $class$groovy$swing$factory$MatteBorderFactory;
        if (($class$groovy$swing$factory$MatteBorderFactory = MatteBorderFactory.$class$groovy$swing$factory$MatteBorderFactory) == null) {
            $class$groovy$swing$factory$MatteBorderFactory = (MatteBorderFactory.$class$groovy$swing$factory$MatteBorderFactory = class$("groovy.swing.factory.MatteBorderFactory"));
        }
        return $class$groovy$swing$factory$MatteBorderFactory;
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
