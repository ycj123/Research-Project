// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class TableFactory extends BeanFactory
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205048;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$BeanFactory;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTableMetaMethods;
    private static /* synthetic */ Class $class$javax$swing$JTable;
    
    public TableFactory() {
        $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[] { $get$$class$javax$swing$JTable() };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$TableFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Class)array[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public TableFactory(final Class klass) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = klass;
        arguments[1] = Boolean.FALSE;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$factory$BeanFactory());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Class)array[0]);
                break;
            }
            case 1: {
                super((Class)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object table = ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$factory$BeanFactory(), this, "newInstance", new Object[] { builder, name, value, attributes });
        $getCallSiteArray[0].call($get$$class$groovy$swing$binding$JTableMetaMethods(), table);
        return ScriptBytecodeAdapter.castToType(table, $get$$class$java$lang$Object());
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (child instanceof TableColumn) {
            $getCallSiteArray[1].call(parent, child);
        }
        else if (child instanceof TableModel) {
            ScriptBytecodeAdapter.setProperty(child, $get$$class$groovy$swing$factory$TableFactory(), parent, "model");
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TableFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TableFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TableFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TableFactory.__timeStamp__239_neverHappen1292524205048 = 0L;
        TableFactory.__timeStamp = 1292524205048L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TableFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TableFactory.$callSiteArray == null || ($createCallSiteArray = TableFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TableFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TableFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TableFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableFactory() {
        Class $class$groovy$swing$factory$TableFactory;
        if (($class$groovy$swing$factory$TableFactory = TableFactory.$class$groovy$swing$factory$TableFactory) == null) {
            $class$groovy$swing$factory$TableFactory = (TableFactory.$class$groovy$swing$factory$TableFactory = class$("groovy.swing.factory.TableFactory"));
        }
        return $class$groovy$swing$factory$TableFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TableFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TableFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BeanFactory() {
        Class $class$groovy$swing$factory$BeanFactory;
        if (($class$groovy$swing$factory$BeanFactory = TableFactory.$class$groovy$swing$factory$BeanFactory) == null) {
            $class$groovy$swing$factory$BeanFactory = (TableFactory.$class$groovy$swing$factory$BeanFactory = class$("groovy.swing.factory.BeanFactory"));
        }
        return $class$groovy$swing$factory$BeanFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTableMetaMethods() {
        Class $class$groovy$swing$binding$JTableMetaMethods;
        if (($class$groovy$swing$binding$JTableMetaMethods = TableFactory.$class$groovy$swing$binding$JTableMetaMethods) == null) {
            $class$groovy$swing$binding$JTableMetaMethods = (TableFactory.$class$groovy$swing$binding$JTableMetaMethods = class$("groovy.swing.binding.JTableMetaMethods"));
        }
        return $class$groovy$swing$binding$JTableMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTable() {
        Class $class$javax$swing$JTable;
        if (($class$javax$swing$JTable = TableFactory.$class$javax$swing$JTable) == null) {
            $class$javax$swing$JTable = (TableFactory.$class$javax$swing$JTable = class$("javax.swing.JTable"));
        }
        return $class$javax$swing$JTable;
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
