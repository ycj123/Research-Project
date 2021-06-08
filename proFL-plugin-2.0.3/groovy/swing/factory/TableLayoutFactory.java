// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class TableLayoutFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204532;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableLayoutFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$impl$TableLayout;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public TableLayoutFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$groovy$swing$impl$TableLayout()))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$groovy$swing$impl$TableLayout()), $get$$class$java$lang$Object());
    }
    
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(builder))) {
            $getCallSiteArray[3].call($getCallSiteArray[4].call(builder), builder, parent, $getCallSiteArray[5].call(child));
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TableLayoutFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TableLayoutFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TableLayoutFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TableLayoutFactory.__timeStamp__239_neverHappen1292524204532 = 0L;
        TableLayoutFactory.__timeStamp = 1292524204532L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TableLayoutFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TableLayoutFactory.$callSiteArray == null || ($createCallSiteArray = TableLayoutFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TableLayoutFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TableLayoutFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TableLayoutFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableLayoutFactory() {
        Class $class$groovy$swing$factory$TableLayoutFactory;
        if (($class$groovy$swing$factory$TableLayoutFactory = TableLayoutFactory.$class$groovy$swing$factory$TableLayoutFactory) == null) {
            $class$groovy$swing$factory$TableLayoutFactory = (TableLayoutFactory.$class$groovy$swing$factory$TableLayoutFactory = class$("groovy.swing.factory.TableLayoutFactory"));
        }
        return $class$groovy$swing$factory$TableLayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TableLayoutFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TableLayoutFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$TableLayout() {
        Class $class$groovy$swing$impl$TableLayout;
        if (($class$groovy$swing$impl$TableLayout = TableLayoutFactory.$class$groovy$swing$impl$TableLayout) == null) {
            $class$groovy$swing$impl$TableLayout = (TableLayoutFactory.$class$groovy$swing$impl$TableLayout = class$("groovy.swing.impl.TableLayout"));
        }
        return $class$groovy$swing$impl$TableLayout;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TableLayoutFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TableLayoutFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
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
