// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.table.TableCellRenderer;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Collection;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.logging.Logger;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ColumnFactory extends AbstractFactory implements GroovyObject
{
    private static final Logger log;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204454;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$javax$swing$table$TableColumn;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$groovy$swing$factory$ColumnFactory;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public ColumnFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (value instanceof TableColumn) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        TableColumn node = (TableColumn)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$table$TableColumn());
        Class jxTableClass = (Class)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Class());
        try {
            jxTableClass = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$java$lang$Class(), "org.jdesktop.swingx.JXTable"), $get$$class$java$lang$Class());
        }
        catch (ClassNotFoundException ex) {}
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(jxTableClass, null) && $getCallSiteArray[1].callGroovyObjectGetProperty(builder) instanceof TableColumnModel) ? Boolean.TRUE : Boolean.FALSE)) {
            node = (TableColumn)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($getCallSiteArray[3].call($get$$class$java$lang$Class(), "org.jdesktop.swingx.table.TableColumnExt")), $get$$class$javax$swing$table$TableColumn());
        }
        else {
            node = (TableColumn)$getCallSiteArray[4].callConstructor($get$$class$javax$swing$table$TableColumn());
        }
        if (ScriptBytecodeAdapter.compareNotEqual(value, null)) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[5].call(value), $get$$class$groovy$swing$factory$ColumnFactory(), node, "identifier");
            $getCallSiteArray[6].call(attributes, "identifier");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callGetProperty(attributes))) {
            if ($getCallSiteArray[8].callGetProperty(attributes) instanceof Collection) {
                final Object callGetProperty = $getCallSiteArray[9].callGetProperty(attributes);
                final Object min = $getCallSiteArray[10].call(callGetProperty, 0);
                final Object pref = $getCallSiteArray[11].call(callGetProperty, 1);
                final Object max = $getCallSiteArray[12].call(callGetProperty, 2);
                if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(pref) && !DefaultTypeTransformation.booleanUnbox(max)) ? Boolean.TRUE : Boolean.FALSE)) {
                    ScriptBytecodeAdapter.setProperty(ColumnFactory.$const$0, $get$$class$groovy$swing$factory$ColumnFactory(), node, "minWidth");
                    ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(min, $get$$class$java$lang$Integer()), $get$$class$groovy$swing$factory$ColumnFactory(), node, "preferredWidth");
                    ScriptBytecodeAdapter.setProperty($getCallSiteArray[13].callGetProperty($get$$class$java$lang$Integer()), $get$$class$groovy$swing$factory$ColumnFactory(), node, "maxWidth");
                }
                else {
                    if (DefaultTypeTransformation.booleanUnbox(min)) {
                        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(min, $get$$class$java$lang$Integer()), $get$$class$groovy$swing$factory$ColumnFactory(), node, "minWidth");
                    }
                    if (DefaultTypeTransformation.booleanUnbox(pref)) {
                        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(pref, $get$$class$java$lang$Integer()), $get$$class$groovy$swing$factory$ColumnFactory(), node, "preferredWidth");
                    }
                    if (DefaultTypeTransformation.booleanUnbox(max)) {
                        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(max, $get$$class$java$lang$Integer()), $get$$class$groovy$swing$factory$ColumnFactory(), node, "maxWidth");
                    }
                }
            }
            else if ($getCallSiteArray[14].callGetProperty(attributes) instanceof Number) {
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[15].call($getCallSiteArray[16].callGetProperty(attributes)), $get$$class$groovy$swing$factory$ColumnFactory(), node, "minWidth");
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[17].call($getCallSiteArray[18].callGetProperty(attributes)), $get$$class$groovy$swing$factory$ColumnFactory(), node, "preferredWidth");
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[19].call($getCallSiteArray[20].callGetProperty(attributes)), $get$$class$groovy$swing$factory$ColumnFactory(), node, "maxWidth");
            }
            $getCallSiteArray[21].call(attributes, "width");
        }
        return ScriptBytecodeAdapter.castToType(node, $get$$class$java$lang$Object());
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(parent instanceof TableColumnModel)) {
            $getCallSiteArray[22].call(ColumnFactory.log, $getCallSiteArray[23].call("Column must be a child of a columnModel. Found ", $getCallSiteArray[24].call(parent)));
        }
        $getCallSiteArray[25].call(parent, node);
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(parent instanceof TableColumn)) {
            $getCallSiteArray[26].call(ColumnFactory.log, $getCallSiteArray[27].call("Renderer must be a child of a tableColumn. Found ", $getCallSiteArray[28].call(parent)));
        }
        if (child instanceof TableCellRenderer) {
            final Object call = $getCallSiteArray[29].call(builder);
            if (ScriptBytecodeAdapter.isCase(call, "headerRenderer")) {
                ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$swing$factory$ColumnFactory(), child, "tableHeader");
                ScriptBytecodeAdapter.setProperty(child, $get$$class$groovy$swing$factory$ColumnFactory(), parent, "headerRenderer");
            }
            else if (ScriptBytecodeAdapter.isCase(call, "cellRenderer")) {
                ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$swing$factory$ColumnFactory(), child, "tableHeader");
                ScriptBytecodeAdapter.setProperty(child, $get$$class$groovy$swing$factory$ColumnFactory(), parent, "cellRenderer");
            }
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ColumnFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ColumnFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ColumnFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ColumnFactory.__timeStamp__239_neverHappen1292524204454 = 0L;
        ColumnFactory.__timeStamp = 1292524204454L;
        $const$0 = 0;
        log = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray()[30].call($get$$class$java$util$logging$Logger(), $getCallSiteArray()[31].callGetProperty($get$$class$groovy$swing$factory$ColumnFactory())), $get$$class$java$util$logging$Logger());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[32];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ColumnFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ColumnFactory.$callSiteArray == null || ($createCallSiteArray = ColumnFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ColumnFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = ColumnFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (ColumnFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableColumn() {
        Class $class$javax$swing$table$TableColumn;
        if (($class$javax$swing$table$TableColumn = ColumnFactory.$class$javax$swing$table$TableColumn) == null) {
            $class$javax$swing$table$TableColumn = (ColumnFactory.$class$javax$swing$table$TableColumn = class$("javax.swing.table.TableColumn"));
        }
        return $class$javax$swing$table$TableColumn;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ColumnFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ColumnFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ColumnFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ColumnFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = ColumnFactory.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (ColumnFactory.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ColumnFactory() {
        Class $class$groovy$swing$factory$ColumnFactory;
        if (($class$groovy$swing$factory$ColumnFactory = ColumnFactory.$class$groovy$swing$factory$ColumnFactory) == null) {
            $class$groovy$swing$factory$ColumnFactory = (ColumnFactory.$class$groovy$swing$factory$ColumnFactory = class$("groovy.swing.factory.ColumnFactory"));
        }
        return $class$groovy$swing$factory$ColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = ColumnFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (ColumnFactory.$class$java$lang$Class = class$("java.lang.Class"));
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
