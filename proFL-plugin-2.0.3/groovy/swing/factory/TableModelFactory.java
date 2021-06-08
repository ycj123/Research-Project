// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.model.ValueModel;
import javax.swing.table.TableModel;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class TableModelFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204538;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$model$ValueModel;
    private static /* synthetic */ Class $class$groovy$model$DefaultTableModel;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableModelFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$beans$PropertyChangeListener;
    private static /* synthetic */ Class $class$java$beans$PropertyChangeEvent;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$javax$swing$table$TableModel;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$model$ValueHolder;
    
    public TableModelFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$table$TableModel()))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        if ($getCallSiteArray[1].call(attributes, name) instanceof TableModel) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(attributes, name), $get$$class$java$lang$Object());
        }
        ValueModel model = (ValueModel)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(attributes, "model"), $get$$class$groovy$model$ValueModel());
        if (ScriptBytecodeAdapter.compareEqual(model, null)) {
            Object list = $getCallSiteArray[4].call(attributes, "list");
            if (ScriptBytecodeAdapter.compareEqual(list, null)) {
                list = $getCallSiteArray[5].callConstructor($get$$class$java$util$ArrayList());
            }
            model = (ValueModel)$getCallSiteArray[6].callConstructor($get$$class$groovy$model$ValueHolder(), list);
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[7].callConstructor($get$$class$groovy$model$DefaultTableModel(), model), $get$$class$java$lang$Object());
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final Object parent2 = new Reference(parent);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[8].callGetProperty(node), TableModelFactory.$const$0) && ((Reference<Object>)parent2).get() instanceof JTable) ? Boolean.TRUE : Boolean.FALSE)) {
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$swing$factory$TableModelFactory(), ((Reference<Object>)parent2).get(), "autoCreateColumnsFromModel");
            final PropertyChangeListener listener = (PropertyChangeListener)new Reference(ScriptBytecodeAdapter.asType(new TableModelFactory$_onNodeCompleted_closure1(this, this), $get$$class$java$beans$PropertyChangeListener()));
            $getCallSiteArray[9].call(((Reference<Object>)parent2).get(), "model", ((Reference<Object>)listener).get());
            $getCallSiteArray[10].call(builder, new TableModelFactory$_onNodeCompleted_closure2(this, this, (Reference<Object>)listener, (Reference<Object>)parent2));
            $getCallSiteArray[11].call(((Reference<Object>)listener).get(), $getCallSiteArray[12].callConstructor($get$$class$java$beans$PropertyChangeEvent(), ((Reference<Object>)parent2).get(), "model", null, node));
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TableModelFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TableModelFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TableModelFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TableModelFactory.__timeStamp__239_neverHappen1292524204538 = 0L;
        TableModelFactory.__timeStamp = 1292524204538L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TableModelFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TableModelFactory.$callSiteArray == null || ($createCallSiteArray = TableModelFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TableModelFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$model$ValueModel() {
        Class $class$groovy$model$ValueModel;
        if (($class$groovy$model$ValueModel = TableModelFactory.$class$groovy$model$ValueModel) == null) {
            $class$groovy$model$ValueModel = (TableModelFactory.$class$groovy$model$ValueModel = class$("groovy.model.ValueModel"));
        }
        return $class$groovy$model$ValueModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$model$DefaultTableModel() {
        Class $class$groovy$model$DefaultTableModel;
        if (($class$groovy$model$DefaultTableModel = TableModelFactory.$class$groovy$model$DefaultTableModel) == null) {
            $class$groovy$model$DefaultTableModel = (TableModelFactory.$class$groovy$model$DefaultTableModel = class$("groovy.model.DefaultTableModel"));
        }
        return $class$groovy$model$DefaultTableModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TableModelFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TableModelFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableModelFactory() {
        Class $class$groovy$swing$factory$TableModelFactory;
        if (($class$groovy$swing$factory$TableModelFactory = TableModelFactory.$class$groovy$swing$factory$TableModelFactory) == null) {
            $class$groovy$swing$factory$TableModelFactory = (TableModelFactory.$class$groovy$swing$factory$TableModelFactory = class$("groovy.swing.factory.TableModelFactory"));
        }
        return $class$groovy$swing$factory$TableModelFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TableModelFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TableModelFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$beans$PropertyChangeListener() {
        Class $class$java$beans$PropertyChangeListener;
        if (($class$java$beans$PropertyChangeListener = TableModelFactory.$class$java$beans$PropertyChangeListener) == null) {
            $class$java$beans$PropertyChangeListener = (TableModelFactory.$class$java$beans$PropertyChangeListener = class$("java.beans.PropertyChangeListener"));
        }
        return $class$java$beans$PropertyChangeListener;
    }
    
    private static /* synthetic */ Class $get$$class$java$beans$PropertyChangeEvent() {
        Class $class$java$beans$PropertyChangeEvent;
        if (($class$java$beans$PropertyChangeEvent = TableModelFactory.$class$java$beans$PropertyChangeEvent) == null) {
            $class$java$beans$PropertyChangeEvent = (TableModelFactory.$class$java$beans$PropertyChangeEvent = class$("java.beans.PropertyChangeEvent"));
        }
        return $class$java$beans$PropertyChangeEvent;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = TableModelFactory.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (TableModelFactory.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableModel() {
        Class $class$javax$swing$table$TableModel;
        if (($class$javax$swing$table$TableModel = TableModelFactory.$class$javax$swing$table$TableModel) == null) {
            $class$javax$swing$table$TableModel = (TableModelFactory.$class$javax$swing$table$TableModel = class$("javax.swing.table.TableModel"));
        }
        return $class$javax$swing$table$TableModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TableModelFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TableModelFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$model$ValueHolder() {
        Class $class$groovy$model$ValueHolder;
        if (($class$groovy$model$ValueHolder = TableModelFactory.$class$groovy$model$ValueHolder) == null) {
            $class$groovy$model$ValueHolder = (TableModelFactory.$class$groovy$model$ValueHolder = class$("groovy.model.ValueHolder"));
        }
        return $class$groovy$model$ValueHolder;
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
