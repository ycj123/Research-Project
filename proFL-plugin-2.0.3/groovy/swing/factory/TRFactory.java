// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.swing.impl.TableLayout;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class TRFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204534;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$impl$TableLayoutRow;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$TRFactory;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$impl$TableLayout;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public TRFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object parent = $getCallSiteArray[1].call(builder);
        if (parent instanceof TableLayout) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$groovy$swing$impl$TableLayoutRow(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(parent, $get$$class$groovy$swing$impl$TableLayout()), $get$$class$groovy$swing$impl$TableLayout())), $get$$class$java$lang$Object());
        }
        throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$RuntimeException(), "'tr' must be within a 'tableLayout'");
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        $getCallSiteArray()[4].call(node);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$TRFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TRFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TRFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TRFactory.__timeStamp__239_neverHappen1292524204534 = 0L;
        TRFactory.__timeStamp = 1292524204534L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TRFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TRFactory.$callSiteArray == null || ($createCallSiteArray = TRFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TRFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$TableLayoutRow() {
        Class $class$groovy$swing$impl$TableLayoutRow;
        if (($class$groovy$swing$impl$TableLayoutRow = TRFactory.$class$groovy$swing$impl$TableLayoutRow) == null) {
            $class$groovy$swing$impl$TableLayoutRow = (TRFactory.$class$groovy$swing$impl$TableLayoutRow = class$("groovy.swing.impl.TableLayoutRow"));
        }
        return $class$groovy$swing$impl$TableLayoutRow;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TRFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TRFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TRFactory() {
        Class $class$groovy$swing$factory$TRFactory;
        if (($class$groovy$swing$factory$TRFactory = TRFactory.$class$groovy$swing$factory$TRFactory) == null) {
            $class$groovy$swing$factory$TRFactory = (TRFactory.$class$groovy$swing$factory$TRFactory = class$("groovy.swing.factory.TRFactory"));
        }
        return $class$groovy$swing$factory$TRFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = TRFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (TRFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TRFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TRFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$TableLayout() {
        Class $class$groovy$swing$impl$TableLayout;
        if (($class$groovy$swing$impl$TableLayout = TRFactory.$class$groovy$swing$impl$TableLayout) == null) {
            $class$groovy$swing$impl$TableLayout = (TRFactory.$class$groovy$swing$impl$TableLayout = class$("groovy.swing.impl.TableLayout"));
        }
        return $class$groovy$swing$impl$TableLayout;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = TRFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (TRFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
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
