// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import javax.swing.border.TitledBorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;

public class TitledBorderFactory extends SwingBorderFactory
{
    private static final Map positions;
    private static final Map justifications;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205050;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$TitledBorderFactory;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$javax$swing$border$Border;
    private static /* synthetic */ Class $class$java$awt$Color;
    private static /* synthetic */ Class $class$javax$swing$border$TitledBorder;
    
    public TitledBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(attributes, "parent"), $get$$class$groovy$swing$factory$TitledBorderFactory(), $getCallSiteArray[1].callGroovyObjectGetProperty(builder), "applyBorderToParent");
        String title = (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        if (DefaultTypeTransformation.booleanUnbox(value)) {
            title = (String)ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(attributes, "title"))) {
                throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " cannot have both a value attribute and an attribute title:" }));
            }
        }
        else {
            title = (String)ScriptBytecodeAdapter.asType($getCallSiteArray[4].call(attributes, "title"), $get$$class$java$lang$String());
        }
        final TitledBorder border = (TitledBorder)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$border$TitledBorder(), title);
        Object position = $getCallSiteArray[6].call(attributes, "position");
        Object call;
        if (!DefaultTypeTransformation.booleanUnbox(call = $getCallSiteArray[7].call(TitledBorderFactory.positions, position))) {
            call = position;
        }
        position = call;
        if (position instanceof Integer) {
            $getCallSiteArray[8].call(border, position);
        }
        Object justification = $getCallSiteArray[9].call(attributes, "justification");
        Object call2;
        if (!DefaultTypeTransformation.booleanUnbox(call2 = $getCallSiteArray[10].call(TitledBorderFactory.justifications, justification))) {
            call2 = justification;
        }
        justification = call2;
        if (justification instanceof Integer) {
            $getCallSiteArray[11].call(border, justification);
        }
        final Border otherBorder = (Border)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call(attributes, "border"), $get$$class$javax$swing$border$Border());
        if (ScriptBytecodeAdapter.compareNotEqual(otherBorder, null)) {
            $getCallSiteArray[13].call(border, otherBorder);
        }
        final Color color = (Color)ScriptBytecodeAdapter.castToType($getCallSiteArray[14].call(attributes, "color"), $get$$class$java$awt$Color());
        if (DefaultTypeTransformation.booleanUnbox(color)) {
            $getCallSiteArray[15].call(border, color);
        }
        final Font font = (Font)ScriptBytecodeAdapter.castToType($getCallSiteArray[16].call(attributes, "font"), $get$$class$java$awt$Font());
        if (DefaultTypeTransformation.booleanUnbox(font)) {
            $getCallSiteArray[17].call(border, font);
        }
        return ScriptBytecodeAdapter.castToType(border, $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TitledBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TitledBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TitledBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TitledBorderFactory.__timeStamp__239_neverHappen1292524205050 = 0L;
        TitledBorderFactory.__timeStamp = 1292524205050L;
        positions = ScriptBytecodeAdapter.createMap(new Object[] { "default", $getCallSiteArray()[18].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "aboveTop", $getCallSiteArray()[19].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "top", $getCallSiteArray()[20].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "belowTop", $getCallSiteArray()[21].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "aboveBottom", $getCallSiteArray()[22].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "bottom", $getCallSiteArray()[23].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "belowBottom", $getCallSiteArray()[24].callGetProperty($get$$class$javax$swing$border$TitledBorder()) });
        justifications = ScriptBytecodeAdapter.createMap(new Object[] { "default", $getCallSiteArray()[25].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "left", $getCallSiteArray()[26].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "center", $getCallSiteArray()[27].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "right", $getCallSiteArray()[28].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "leading", $getCallSiteArray()[29].callGetProperty($get$$class$javax$swing$border$TitledBorder()), "trailing", $getCallSiteArray()[30].callGetProperty($get$$class$javax$swing$border$TitledBorder()) });
    }
    
    public static final Map getPositions() {
        return TitledBorderFactory.positions;
    }
    
    public static final Map getJustifications() {
        return TitledBorderFactory.justifications;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[31];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TitledBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TitledBorderFactory.$callSiteArray == null || ($createCallSiteArray = TitledBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TitledBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TitledBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TitledBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = TitledBorderFactory.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (TitledBorderFactory.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TitledBorderFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TitledBorderFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = TitledBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (TitledBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TitledBorderFactory() {
        Class $class$groovy$swing$factory$TitledBorderFactory;
        if (($class$groovy$swing$factory$TitledBorderFactory = TitledBorderFactory.$class$groovy$swing$factory$TitledBorderFactory) == null) {
            $class$groovy$swing$factory$TitledBorderFactory = (TitledBorderFactory.$class$groovy$swing$factory$TitledBorderFactory = class$("groovy.swing.factory.TitledBorderFactory"));
        }
        return $class$groovy$swing$factory$TitledBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = TitledBorderFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (TitledBorderFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$Border() {
        Class $class$javax$swing$border$Border;
        if (($class$javax$swing$border$Border = TitledBorderFactory.$class$javax$swing$border$Border) == null) {
            $class$javax$swing$border$Border = (TitledBorderFactory.$class$javax$swing$border$Border = class$("javax.swing.border.Border"));
        }
        return $class$javax$swing$border$Border;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Color() {
        Class $class$java$awt$Color;
        if (($class$java$awt$Color = TitledBorderFactory.$class$java$awt$Color) == null) {
            $class$java$awt$Color = (TitledBorderFactory.$class$java$awt$Color = class$("java.awt.Color"));
        }
        return $class$java$awt$Color;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$TitledBorder() {
        Class $class$javax$swing$border$TitledBorder;
        if (($class$javax$swing$border$TitledBorder = TitledBorderFactory.$class$javax$swing$border$TitledBorder) == null) {
            $class$javax$swing$border$TitledBorder = (TitledBorderFactory.$class$javax$swing$border$TitledBorder = class$("javax.swing.border.TitledBorder"));
        }
        return $class$javax$swing$border$TitledBorder;
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
