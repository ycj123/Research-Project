// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.awt.Window;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.swing.impl.TableLayoutRow;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class TDFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204536;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$impl$TableLayoutRow;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$TDFactory;
    private static /* synthetic */ Class $class$groovy$swing$impl$TableLayoutCell;
    
    public TDFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object parent = $getCallSiteArray[1].call(builder);
        if (parent instanceof TableLayoutRow) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$groovy$swing$impl$TableLayoutCell(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(parent, $get$$class$groovy$swing$impl$TableLayoutRow()), $get$$class$groovy$swing$impl$TableLayoutRow())), $get$$class$java$lang$Object());
        }
        throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), "'td' must be within a 'tr'");
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        $getCallSiteArray[4].call(parent, child);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TDFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TDFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TDFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TDFactory.__timeStamp__239_neverHappen1292524204536 = 0L;
        TDFactory.__timeStamp = 1292524204536L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TDFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TDFactory.$callSiteArray == null || ($createCallSiteArray = TDFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TDFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$TableLayoutRow() {
        Class $class$groovy$swing$impl$TableLayoutRow;
        if (($class$groovy$swing$impl$TableLayoutRow = TDFactory.$class$groovy$swing$impl$TableLayoutRow) == null) {
            $class$groovy$swing$impl$TableLayoutRow = (TDFactory.$class$groovy$swing$impl$TableLayoutRow = class$("groovy.swing.impl.TableLayoutRow"));
        }
        return $class$groovy$swing$impl$TableLayoutRow;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TDFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TDFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = TDFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (TDFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TDFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TDFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TDFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TDFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TDFactory() {
        Class $class$groovy$swing$factory$TDFactory;
        if (($class$groovy$swing$factory$TDFactory = TDFactory.$class$groovy$swing$factory$TDFactory) == null) {
            $class$groovy$swing$factory$TDFactory = (TDFactory.$class$groovy$swing$factory$TDFactory = class$("groovy.swing.factory.TDFactory"));
        }
        return $class$groovy$swing$factory$TDFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$TableLayoutCell() {
        Class $class$groovy$swing$impl$TableLayoutCell;
        if (($class$groovy$swing$impl$TableLayoutCell = TDFactory.$class$groovy$swing$impl$TableLayoutCell) == null) {
            $class$groovy$swing$impl$TableLayoutCell = (TDFactory.$class$groovy$swing$impl$TableLayoutCell = class$("groovy.swing.impl.TableLayoutCell"));
        }
        return $class$groovy$swing$impl$TableLayoutCell;
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
