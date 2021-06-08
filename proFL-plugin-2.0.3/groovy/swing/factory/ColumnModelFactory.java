// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JTable;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.table.TableColumnModel;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.logging.Logger;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ColumnModelFactory extends AbstractFactory implements GroovyObject
{
    private static final Logger log;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204459;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$javax$swing$table$DefaultTableColumnModel;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$swing$factory$ColumnModelFactory;
    
    public ColumnModelFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (value instanceof TableColumnModel) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        Class jxTableClass = (Class)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Class());
        try {
            jxTableClass = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$java$lang$Class(), "org.jdesktop.swingx.JXTable"), $get$$class$java$lang$Class());
        }
        catch (ClassNotFoundException ex) {}
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(jxTableClass, null) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(jxTableClass, $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(builder))))) ? Boolean.TRUE : Boolean.FALSE)) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($getCallSiteArray[5].call($get$$class$java$lang$Class(), "org.jdesktop.swingx.table.DefaultTableColumnModelExt")), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[6].callConstructor($get$$class$javax$swing$table$DefaultTableColumnModel()), $get$$class$java$lang$Object());
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(parent instanceof JTable)) {
            $getCallSiteArray[7].call(ColumnModelFactory.log, $getCallSiteArray[8].call("ColumnModel must be a child of a table. Found: ", $getCallSiteArray[9].call(parent)));
        }
        ScriptBytecodeAdapter.setProperty(node, $get$$class$groovy$swing$factory$ColumnModelFactory(), parent, "columnModel");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ColumnModelFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ColumnModelFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ColumnModelFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ColumnModelFactory.__timeStamp__239_neverHappen1292524204459 = 0L;
        ColumnModelFactory.__timeStamp = 1292524204459L;
        log = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray()[10].call($get$$class$java$util$logging$Logger(), $getCallSiteArray()[11].callGetProperty($get$$class$groovy$swing$factory$ColumnModelFactory())), $get$$class$java$util$logging$Logger());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ColumnModelFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ColumnModelFactory.$callSiteArray == null || ($createCallSiteArray = ColumnModelFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ColumnModelFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ColumnModelFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ColumnModelFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ColumnModelFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ColumnModelFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = ColumnModelFactory.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (ColumnModelFactory.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$DefaultTableColumnModel() {
        Class $class$javax$swing$table$DefaultTableColumnModel;
        if (($class$javax$swing$table$DefaultTableColumnModel = ColumnModelFactory.$class$javax$swing$table$DefaultTableColumnModel) == null) {
            $class$javax$swing$table$DefaultTableColumnModel = (ColumnModelFactory.$class$javax$swing$table$DefaultTableColumnModel = class$("javax.swing.table.DefaultTableColumnModel"));
        }
        return $class$javax$swing$table$DefaultTableColumnModel;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = ColumnModelFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (ColumnModelFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ColumnModelFactory() {
        Class $class$groovy$swing$factory$ColumnModelFactory;
        if (($class$groovy$swing$factory$ColumnModelFactory = ColumnModelFactory.$class$groovy$swing$factory$ColumnModelFactory) == null) {
            $class$groovy$swing$factory$ColumnModelFactory = (ColumnModelFactory.$class$groovy$swing$factory$ColumnModelFactory = class$("groovy.swing.factory.ColumnModelFactory"));
        }
        return $class$groovy$swing$factory$ColumnModelFactory;
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
