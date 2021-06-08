// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Closure;
import groovy.model.DefaultTableModel;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ClosureColumnFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204545;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$model$DefaultTableModel;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$ClosureColumnFactory;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public ClosureColumnFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object current = $getCallSiteArray[1].call(builder);
        if (!(current instanceof DefaultTableModel)) {
            throw (Throwable)$getCallSiteArray[8].callConstructor($get$$class$java$lang$RuntimeException(), "closureColumn must be a child of a tableModel");
        }
        final DefaultTableModel model = (DefaultTableModel)ScriptBytecodeAdapter.castToType(current, $get$$class$groovy$model$DefaultTableModel());
        Object header = $getCallSiteArray[2].call(attributes, "header");
        if (ScriptBytecodeAdapter.compareEqual(header, null)) {
            header = "";
        }
        final Closure readClosure = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(attributes, "read"), $get$$class$groovy$lang$Closure());
        if (ScriptBytecodeAdapter.compareEqual(readClosure, null)) {
            throw (Throwable)$getCallSiteArray[4].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Must specify 'read' Closure property for a closureColumn");
        }
        final Closure writeClosure = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(attributes, "write"), $get$$class$groovy$lang$Closure());
        Class type = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(attributes, "type"), $get$$class$java$lang$Class());
        if (ScriptBytecodeAdapter.compareEqual(type, null)) {
            type = (Class)ScriptBytecodeAdapter.castToType($get$$class$java$lang$Object(), $get$$class$java$lang$Class());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(model, header, readClosure, writeClosure, type), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ClosureColumnFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ClosureColumnFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ClosureColumnFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ClosureColumnFactory.__timeStamp__239_neverHappen1292524204545 = 0L;
        ClosureColumnFactory.__timeStamp = 1292524204545L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ClosureColumnFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ClosureColumnFactory.$callSiteArray == null || ($createCallSiteArray = ClosureColumnFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ClosureColumnFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$model$DefaultTableModel() {
        Class $class$groovy$model$DefaultTableModel;
        if (($class$groovy$model$DefaultTableModel = ClosureColumnFactory.$class$groovy$model$DefaultTableModel) == null) {
            $class$groovy$model$DefaultTableModel = (ClosureColumnFactory.$class$groovy$model$DefaultTableModel = class$("groovy.model.DefaultTableModel"));
        }
        return $class$groovy$model$DefaultTableModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ClosureColumnFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ClosureColumnFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = ClosureColumnFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (ClosureColumnFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ClosureColumnFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ClosureColumnFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = ClosureColumnFactory.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (ClosureColumnFactory.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = ClosureColumnFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (ClosureColumnFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ClosureColumnFactory() {
        Class $class$groovy$swing$factory$ClosureColumnFactory;
        if (($class$groovy$swing$factory$ClosureColumnFactory = ClosureColumnFactory.$class$groovy$swing$factory$ClosureColumnFactory) == null) {
            $class$groovy$swing$factory$ClosureColumnFactory = (ClosureColumnFactory.$class$groovy$swing$factory$ClosureColumnFactory = class$("groovy.swing.factory.ClosureColumnFactory"));
        }
        return $class$groovy$swing$factory$ClosureColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = ClosureColumnFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (ClosureColumnFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = ClosureColumnFactory.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (ClosureColumnFactory.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
