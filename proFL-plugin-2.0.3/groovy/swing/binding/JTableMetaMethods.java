// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JTable;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JTableMetaMethods implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203124;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$table$TableColumn;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$javax$swing$table$TableModel;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTableMetaMethods;
    private static /* synthetic */ Class $class$javax$swing$table$TableColumnModel;
    
    public JTableMetaMethods() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void enhanceMetaClass(final Object table) {
        $getCallSiteArray()[0].call($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), table, ScriptBytecodeAdapter.createMap(new Object[] { "getElements", new JTableMetaMethods$_enhanceMetaClass_closure1($get$$class$groovy$swing$binding$JTableMetaMethods(), $get$$class$groovy$swing$binding$JTableMetaMethods()), "getSelectedElement", new JTableMetaMethods$_enhanceMetaClass_closure2($get$$class$groovy$swing$binding$JTableMetaMethods(), $get$$class$groovy$swing$binding$JTableMetaMethods()), "getSelectedElements", new JTableMetaMethods$_enhanceMetaClass_closure3($get$$class$groovy$swing$binding$JTableMetaMethods(), $get$$class$groovy$swing$binding$JTableMetaMethods()) }));
    }
    
    public static Object getElement(final JTable table, final int row) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(DefaultTypeTransformation.box(row), JTableMetaMethods.$const$0)) {
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        final TableModel model = (TableModel)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGetProperty(table), $get$$class$javax$swing$table$TableModel());
        if (model instanceof DefaultTableModel) {
            final Map value = ScriptBytecodeAdapter.createMap(new Object[0]);
            final TableColumnModel cmodel = (TableColumnModel)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callGetProperty(table), $get$$class$javax$swing$table$TableColumnModel());
            for (Integer i = JTableMetaMethods.$const$1; ScriptBytecodeAdapter.compareLessThan(i, $getCallSiteArray[3].call(cmodel)); i = (Integer)$getCallSiteArray[9].call(i)) {
                final TableColumn c = (TableColumn)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(cmodel, i), $get$$class$javax$swing$table$TableColumn());
                $getCallSiteArray[5].call(value, $getCallSiteArray[6].call(c), $getCallSiteArray[7].call(table, DefaultTypeTransformation.box(row), $getCallSiteArray[8].call(c)));
            }
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        if (!(model instanceof groovy.model.DefaultTableModel)) {
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        final Object rowValue = $getCallSiteArray[10].callGetProperty($getCallSiteArray[11].call(model));
        if (ScriptBytecodeAdapter.compareEqual(rowValue, null)) {
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), rowValue), DefaultTypeTransformation.box(row)), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JTableMetaMethods()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JTableMetaMethods.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JTableMetaMethods.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JTableMetaMethods.__timeStamp__239_neverHappen1292524203124 = 0L;
        JTableMetaMethods.__timeStamp = 1292524203124L;
        $const$1 = 0;
        $const$0 = -1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JTableMetaMethods(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JTableMetaMethods.$callSiteArray == null || ($createCallSiteArray = JTableMetaMethods.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JTableMetaMethods.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableColumn() {
        Class $class$javax$swing$table$TableColumn;
        if (($class$javax$swing$table$TableColumn = JTableMetaMethods.$class$javax$swing$table$TableColumn) == null) {
            $class$javax$swing$table$TableColumn = (JTableMetaMethods.$class$javax$swing$table$TableColumn = class$("javax.swing.table.TableColumn"));
        }
        return $class$javax$swing$table$TableColumn;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JTableMetaMethods.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JTableMetaMethods.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JTableMetaMethods.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JTableMetaMethods.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = JTableMetaMethods.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (JTableMetaMethods.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableModel() {
        Class $class$javax$swing$table$TableModel;
        if (($class$javax$swing$table$TableModel = JTableMetaMethods.$class$javax$swing$table$TableModel) == null) {
            $class$javax$swing$table$TableModel = (JTableMetaMethods.$class$javax$swing$table$TableModel = class$("javax.swing.table.TableModel"));
        }
        return $class$javax$swing$table$TableModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods() {
        Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
        if (($class$groovy$swing$binding$AbstractSyntheticMetaMethods = JTableMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods) == null) {
            $class$groovy$swing$binding$AbstractSyntheticMetaMethods = (JTableMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods = class$("groovy.swing.binding.AbstractSyntheticMetaMethods"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTableMetaMethods() {
        Class $class$groovy$swing$binding$JTableMetaMethods;
        if (($class$groovy$swing$binding$JTableMetaMethods = JTableMetaMethods.$class$groovy$swing$binding$JTableMetaMethods) == null) {
            $class$groovy$swing$binding$JTableMetaMethods = (JTableMetaMethods.$class$groovy$swing$binding$JTableMetaMethods = class$("groovy.swing.binding.JTableMetaMethods"));
        }
        return $class$groovy$swing$binding$JTableMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableColumnModel() {
        Class $class$javax$swing$table$TableColumnModel;
        if (($class$javax$swing$table$TableColumnModel = JTableMetaMethods.$class$javax$swing$table$TableColumnModel) == null) {
            $class$javax$swing$table$TableColumnModel = (JTableMetaMethods.$class$javax$swing$table$TableColumnModel = class$("javax.swing.table.TableColumnModel"));
        }
        return $class$javax$swing$table$TableColumnModel;
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
