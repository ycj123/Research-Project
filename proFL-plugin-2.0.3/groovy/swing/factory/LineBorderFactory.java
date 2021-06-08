// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class LineBorderFactory extends SwingBorderFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205016;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$javax$swing$border$LineBorder;
    private static /* synthetic */ Class $class$groovy$swing$factory$LineBorderFactory;
    
    public LineBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$LineBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        final Object color = $getCallSiteArray[2].call(attributes, "color");
        if (ScriptBytecodeAdapter.compareEqual(color, null)) {
            throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "color: is a required attribute for ", "" }));
        }
        Object thickness = $getCallSiteArray[4].call(attributes, "thickness");
        if (ScriptBytecodeAdapter.compareEqual(thickness, null)) {
            thickness = LineBorderFactory.$const$0;
        }
        Object roundedCorners = $getCallSiteArray[5].call(attributes, "roundedCorners");
        if (ScriptBytecodeAdapter.compareEqual(roundedCorners, null)) {
            roundedCorners = Boolean.FALSE;
        }
        if (DefaultTypeTransformation.booleanUnbox(attributes)) {
            throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name, $getCallSiteArray[7].call(attributes) }, new String[] { "", " does not know how to handle the remaining attibutes: ", "" }));
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[8].callConstructor($get$$class$javax$swing$border$LineBorder(), color, thickness, roundedCorners), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$LineBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = LineBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (LineBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        LineBorderFactory.__timeStamp__239_neverHappen1292524205016 = 0L;
        LineBorderFactory.__timeStamp = 1292524205016L;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$LineBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LineBorderFactory.$callSiteArray == null || ($createCallSiteArray = LineBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LineBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = LineBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (LineBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = LineBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (LineBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = LineBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (LineBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$LineBorder() {
        Class $class$javax$swing$border$LineBorder;
        if (($class$javax$swing$border$LineBorder = LineBorderFactory.$class$javax$swing$border$LineBorder) == null) {
            $class$javax$swing$border$LineBorder = (LineBorderFactory.$class$javax$swing$border$LineBorder = class$("javax.swing.border.LineBorder"));
        }
        return $class$javax$swing$border$LineBorder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LineBorderFactory() {
        Class $class$groovy$swing$factory$LineBorderFactory;
        if (($class$groovy$swing$factory$LineBorderFactory = LineBorderFactory.$class$groovy$swing$factory$LineBorderFactory) == null) {
            $class$groovy$swing$factory$LineBorderFactory = (LineBorderFactory.$class$groovy$swing$factory$LineBorderFactory = class$("groovy.swing.factory.LineBorderFactory"));
        }
        return $class$groovy$swing$factory$LineBorderFactory;
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
