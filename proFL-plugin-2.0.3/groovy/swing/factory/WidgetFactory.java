// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import groovy.lang.MissingPropertyException;
import java.awt.Window;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class WidgetFactory extends AbstractFactory implements GroovyObject
{
    private final Class restrictedType;
    protected final boolean leaf;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204549;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$WidgetFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public WidgetFactory(final Class restrictedType, final boolean leaf) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.restrictedType = (Class)ScriptBytecodeAdapter.castToType(restrictedType, $get$$class$java$lang$Class());
        this.leaf = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(leaf));
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(this.leaf), $get$$class$java$lang$Boolean()));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            value = $getCallSiteArray[0].call(attributes, name);
        }
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(value, null) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, this.restrictedType))) ? Boolean.TRUE : Boolean.FALSE)) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name, name, $getCallSiteArray[3].callGetProperty(this.restrictedType) }, new String[] { "", " must have either a value argument or an attribute named ", " that must be of type ", "" }));
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        try {
            final Object constraints = $getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(builder));
            if (ScriptBytecodeAdapter.compareNotEqual(constraints, null)) {
                $getCallSiteArray[6].call($getCallSiteArray[7].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child, constraints);
                $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(builder), "constraints");
            }
            else {
                $getCallSiteArray[10].call($getCallSiteArray[11].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child);
            }
        }
        catch (MissingPropertyException mpe) {
            $getCallSiteArray[12].call($getCallSiteArray[13].call($get$$class$groovy$swing$factory$LayoutFactory(), parent), child);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$WidgetFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = WidgetFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (WidgetFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        WidgetFactory.__timeStamp__239_neverHappen1292524204549 = 0L;
        WidgetFactory.__timeStamp = 1292524204549L;
    }
    
    public final Class getRestrictedType() {
        return this.restrictedType;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$WidgetFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (WidgetFactory.$callSiteArray == null || ($createCallSiteArray = WidgetFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            WidgetFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$WidgetFactory() {
        Class $class$groovy$swing$factory$WidgetFactory;
        if (($class$groovy$swing$factory$WidgetFactory = WidgetFactory.$class$groovy$swing$factory$WidgetFactory) == null) {
            $class$groovy$swing$factory$WidgetFactory = (WidgetFactory.$class$groovy$swing$factory$WidgetFactory = class$("groovy.swing.factory.WidgetFactory"));
        }
        return $class$groovy$swing$factory$WidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = WidgetFactory.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (WidgetFactory.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = WidgetFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (WidgetFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = WidgetFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (WidgetFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = WidgetFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (WidgetFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = WidgetFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (WidgetFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = WidgetFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (WidgetFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = WidgetFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (WidgetFactory.$class$java$lang$Class = class$("java.lang.Class"));
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
