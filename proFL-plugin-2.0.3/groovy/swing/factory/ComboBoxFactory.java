// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ComboBoxFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204461;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComboBoxFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$binding$JComboBoxMetaMethods;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$javax$swing$JComboBox;
    
    public ComboBoxFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JComboBox());
        final Object items = $getCallSiteArray[1].call(attributes, "items");
        JComboBox comboBox = (JComboBox)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JComboBox());
        if (items instanceof Vector) {
            comboBox = (JComboBox)$getCallSiteArray[2].callConstructor($get$$class$javax$swing$JComboBox(), $getCallSiteArray[3].call(attributes, "items"));
        }
        else if (items instanceof List) {
            final List list = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(attributes, "items"), $get$$class$java$util$List());
            comboBox = (JComboBox)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$JComboBox(), $getCallSiteArray[6].call(list));
        }
        else if (items instanceof Object[]) {
            comboBox = (JComboBox)$getCallSiteArray[7].callConstructor($get$$class$javax$swing$JComboBox(), $getCallSiteArray[8].call(attributes, "items"));
        }
        else if (value instanceof JComboBox) {
            comboBox = (JComboBox)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$JComboBox());
        }
        else {
            comboBox = (JComboBox)$getCallSiteArray[9].callConstructor($get$$class$javax$swing$JComboBox());
        }
        $getCallSiteArray[10].call($get$$class$groovy$swing$binding$JComboBoxMetaMethods(), comboBox);
        return ScriptBytecodeAdapter.castToType(comboBox, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ComboBoxFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ComboBoxFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ComboBoxFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ComboBoxFactory.__timeStamp__239_neverHappen1292524204461 = 0L;
        ComboBoxFactory.__timeStamp = 1292524204461L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ComboBoxFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ComboBoxFactory.$callSiteArray == null || ($createCallSiteArray = ComboBoxFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ComboBoxFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComboBoxFactory() {
        Class $class$groovy$swing$factory$ComboBoxFactory;
        if (($class$groovy$swing$factory$ComboBoxFactory = ComboBoxFactory.$class$groovy$swing$factory$ComboBoxFactory) == null) {
            $class$groovy$swing$factory$ComboBoxFactory = (ComboBoxFactory.$class$groovy$swing$factory$ComboBoxFactory = class$("groovy.swing.factory.ComboBoxFactory"));
        }
        return $class$groovy$swing$factory$ComboBoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ComboBoxFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ComboBoxFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ComboBoxFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ComboBoxFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JComboBoxMetaMethods() {
        Class $class$groovy$swing$binding$JComboBoxMetaMethods;
        if (($class$groovy$swing$binding$JComboBoxMetaMethods = ComboBoxFactory.$class$groovy$swing$binding$JComboBoxMetaMethods) == null) {
            $class$groovy$swing$binding$JComboBoxMetaMethods = (ComboBoxFactory.$class$groovy$swing$binding$JComboBoxMetaMethods = class$("groovy.swing.binding.JComboBoxMetaMethods"));
        }
        return $class$groovy$swing$binding$JComboBoxMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ComboBoxFactory.$class$java$util$List) == null) {
            $class$java$util$List = (ComboBoxFactory.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = ComboBoxFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (ComboBoxFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JComboBox() {
        Class $class$javax$swing$JComboBox;
        if (($class$javax$swing$JComboBox = ComboBoxFactory.$class$javax$swing$JComboBox) == null) {
            $class$javax$swing$JComboBox = (ComboBoxFactory.$class$javax$swing$JComboBox = class$("javax.swing.JComboBox"));
        }
        return $class$javax$swing$JComboBox;
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
