// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.text.Format;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JFormattedTextField;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class FormattedTextFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204463;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$FormattedTextFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$text$Format;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$javax$swing$JFormattedTextField;
    
    public FormattedTextFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        JFormattedTextField ftf = (JFormattedTextField)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JFormattedTextField());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(attributes, "format"))) {
            ftf = (JFormattedTextField)$getCallSiteArray[2].callConstructor($get$$class$javax$swing$JFormattedTextField(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(attributes, "format"), $get$$class$java$text$Format()), $get$$class$java$text$Format()));
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(attributes, "value"))) {
            ftf = (JFormattedTextField)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$JFormattedTextField(), $getCallSiteArray[6].call(attributes, "value"));
        }
        else {
            ftf = (JFormattedTextField)$getCallSiteArray[7].callConstructor($get$$class$javax$swing$JFormattedTextField());
        }
        return ScriptBytecodeAdapter.castToType(ftf, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$FormattedTextFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = FormattedTextFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (FormattedTextFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        FormattedTextFactory.__timeStamp__239_neverHappen1292524204463 = 0L;
        FormattedTextFactory.__timeStamp = 1292524204463L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$FormattedTextFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (FormattedTextFactory.$callSiteArray == null || ($createCallSiteArray = FormattedTextFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            FormattedTextFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$FormattedTextFactory() {
        Class $class$groovy$swing$factory$FormattedTextFactory;
        if (($class$groovy$swing$factory$FormattedTextFactory = FormattedTextFactory.$class$groovy$swing$factory$FormattedTextFactory) == null) {
            $class$groovy$swing$factory$FormattedTextFactory = (FormattedTextFactory.$class$groovy$swing$factory$FormattedTextFactory = class$("groovy.swing.factory.FormattedTextFactory"));
        }
        return $class$groovy$swing$factory$FormattedTextFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = FormattedTextFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (FormattedTextFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = FormattedTextFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (FormattedTextFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$text$Format() {
        Class $class$java$text$Format;
        if (($class$java$text$Format = FormattedTextFactory.$class$java$text$Format) == null) {
            $class$java$text$Format = (FormattedTextFactory.$class$java$text$Format = class$("java.text.Format"));
        }
        return $class$java$text$Format;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = FormattedTextFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (FormattedTextFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFormattedTextField() {
        Class $class$javax$swing$JFormattedTextField;
        if (($class$javax$swing$JFormattedTextField = FormattedTextFactory.$class$javax$swing$JFormattedTextField) == null) {
            $class$javax$swing$JFormattedTextField = (FormattedTextFactory.$class$javax$swing$JFormattedTextField = class$("javax.swing.JFormattedTextField"));
        }
        return $class$javax$swing$JFormattedTextField;
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
