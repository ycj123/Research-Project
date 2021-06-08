// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.awt.Color;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class BevelBorderFactory extends SwingBorderFactory implements GroovyObject
{
    private final int type;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204964;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$BorderFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$BevelBorderFactory;
    private static /* synthetic */ Class $class$java$awt$Color;
    
    public BevelBorderFactory(final int newType) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.type = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(newType));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$BevelBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(attributes, "highlight"))) {
            final Color highlight = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(attributes, "highlight"), $get$$class$java$awt$Color());
            final Color shadow = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(attributes, "shadow"), $get$$class$java$awt$Color());
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(highlight) && DefaultTypeTransformation.booleanUnbox(shadow)) ? Boolean.TRUE : Boolean.FALSE) && !DefaultTypeTransformation.booleanUnbox(attributes)) ? Boolean.TRUE : Boolean.FALSE)) {
                return ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call($get$$class$javax$swing$BorderFactory(), DefaultTypeTransformation.box(this.type), highlight, shadow), $get$$class$java$lang$Object());
            }
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(attributes, "highlightOuter"))) {
            final Color highlightOuter = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(attributes, "highlightOuter"), $get$$class$java$awt$Color());
            final Color highlightInner = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(attributes, "highlightInner"), $get$$class$java$awt$Color());
            final Color shadowOuter = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(attributes, "shadowOuter"), $get$$class$java$awt$Color());
            final Color shadowInner = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(attributes, "shadowInner"), $get$$class$java$awt$Color());
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(highlightOuter) && DefaultTypeTransformation.booleanUnbox(highlightInner)) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox(shadowOuter)) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox(shadowInner)) ? Boolean.TRUE : Boolean.FALSE) && !DefaultTypeTransformation.booleanUnbox(attributes)) ? Boolean.TRUE : Boolean.FALSE)) {
                return ScriptBytecodeAdapter.castToType($getCallSiteArray[11].call($get$$class$javax$swing$BorderFactory(), ArrayUtil.createArray(DefaultTypeTransformation.box(this.type), highlightOuter, highlightInner, shadowOuter, shadowInner)), $get$$class$java$lang$Object());
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(attributes)) {
            throw (Throwable)$getCallSiteArray[12].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " only accepts no attributes, or highlight: and shadow: attributes, or highlightOuter: and highlightInner: and shadowOuter: and shadowInner: attributes" }));
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[13].call($get$$class$javax$swing$BorderFactory(), DefaultTypeTransformation.box(this.type)), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$BevelBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BevelBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BevelBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BevelBorderFactory.__timeStamp__239_neverHappen1292524204964 = 0L;
        BevelBorderFactory.__timeStamp = 1292524204964L;
    }
    
    public final int getType() {
        return this.type;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BevelBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BevelBorderFactory.$callSiteArray == null || ($createCallSiteArray = BevelBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BevelBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$BorderFactory() {
        Class $class$javax$swing$BorderFactory;
        if (($class$javax$swing$BorderFactory = BevelBorderFactory.$class$javax$swing$BorderFactory) == null) {
            $class$javax$swing$BorderFactory = (BevelBorderFactory.$class$javax$swing$BorderFactory = class$("javax.swing.BorderFactory"));
        }
        return $class$javax$swing$BorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BevelBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BevelBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = BevelBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (BevelBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BevelBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BevelBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BevelBorderFactory() {
        Class $class$groovy$swing$factory$BevelBorderFactory;
        if (($class$groovy$swing$factory$BevelBorderFactory = BevelBorderFactory.$class$groovy$swing$factory$BevelBorderFactory) == null) {
            $class$groovy$swing$factory$BevelBorderFactory = (BevelBorderFactory.$class$groovy$swing$factory$BevelBorderFactory = class$("groovy.swing.factory.BevelBorderFactory"));
        }
        return $class$groovy$swing$factory$BevelBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Color() {
        Class $class$java$awt$Color;
        if (($class$java$awt$Color = BevelBorderFactory.$class$java$awt$Color) == null) {
            $class$java$awt$Color = (BevelBorderFactory.$class$java$awt$Color = class$("java.awt.Color"));
        }
        return $class$java$awt$Color;
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
