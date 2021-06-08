// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.model.DefaultTableModel;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class PropertyColumnFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204543;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$model$DefaultTableModel;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$PropertyColumnFactory;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public PropertyColumnFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object current = $getCallSiteArray[1].call(builder);
        if (!(current instanceof DefaultTableModel)) {
            throw (Throwable)$getCallSiteArray[10].callConstructor($get$$class$java$lang$RuntimeException(), "propertyColumn must be a child of a tableModel");
        }
        final DefaultTableModel model = (DefaultTableModel)ScriptBytecodeAdapter.castToType(current, $get$$class$groovy$model$DefaultTableModel());
        final String property = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(attributes, "propertyName"), $get$$class$java$lang$String());
        if (ScriptBytecodeAdapter.compareEqual(property, null)) {
            throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Must specify a property for a propertyColumn");
        }
        Object header = $getCallSiteArray[4].call(attributes, "header");
        if (ScriptBytecodeAdapter.compareEqual(header, null)) {
            header = "";
        }
        Class type = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(attributes, "type"), $get$$class$java$lang$Class());
        if (ScriptBytecodeAdapter.compareEqual(type, null)) {
            type = (Class)ScriptBytecodeAdapter.castToType($get$$class$java$lang$Object(), $get$$class$java$lang$Class());
        }
        Boolean editable = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(attributes, "editable"), $get$$class$java$lang$Boolean());
        if (ScriptBytecodeAdapter.compareEqual(editable, null)) {
            editable = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].callGetProperty($get$$class$java$lang$Boolean()), $get$$class$java$lang$Boolean());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(model, header, property, type, $getCallSiteArray[9].call(editable)), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$PropertyColumnFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = PropertyColumnFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (PropertyColumnFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        PropertyColumnFactory.__timeStamp__239_neverHappen1292524204543 = 0L;
        PropertyColumnFactory.__timeStamp = 1292524204543L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$PropertyColumnFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (PropertyColumnFactory.$callSiteArray == null || ($createCallSiteArray = PropertyColumnFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            PropertyColumnFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$model$DefaultTableModel() {
        Class $class$groovy$model$DefaultTableModel;
        if (($class$groovy$model$DefaultTableModel = PropertyColumnFactory.$class$groovy$model$DefaultTableModel) == null) {
            $class$groovy$model$DefaultTableModel = (PropertyColumnFactory.$class$groovy$model$DefaultTableModel = class$("groovy.model.DefaultTableModel"));
        }
        return $class$groovy$model$DefaultTableModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = PropertyColumnFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (PropertyColumnFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$PropertyColumnFactory() {
        Class $class$groovy$swing$factory$PropertyColumnFactory;
        if (($class$groovy$swing$factory$PropertyColumnFactory = PropertyColumnFactory.$class$groovy$swing$factory$PropertyColumnFactory) == null) {
            $class$groovy$swing$factory$PropertyColumnFactory = (PropertyColumnFactory.$class$groovy$swing$factory$PropertyColumnFactory = class$("groovy.swing.factory.PropertyColumnFactory"));
        }
        return $class$groovy$swing$factory$PropertyColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = PropertyColumnFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (PropertyColumnFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = PropertyColumnFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (PropertyColumnFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = PropertyColumnFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (PropertyColumnFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = PropertyColumnFactory.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (PropertyColumnFactory.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = PropertyColumnFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (PropertyColumnFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = PropertyColumnFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (PropertyColumnFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = PropertyColumnFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (PropertyColumnFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
