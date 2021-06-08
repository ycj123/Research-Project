// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class CollectionFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204452;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$CollectionFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$lang$MissingPropertyException;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public CollectionFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(attributes))) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$java$util$ArrayList()), $get$$class$java$lang$Object());
        }
        final Object item = $getCallSiteArray[3].call($getCallSiteArray[4].call($getCallSiteArray[5].call(attributes)));
        throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$groovy$lang$MissingPropertyException(), new GStringImpl(new Object[] { name }, new String[] { "The builder element '", "' is a collections element and accepts no attributes" }), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[7].callGetProperty(item), $get$$class$java$lang$String()), $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[8].callGetProperty(item), $get$$class$java$lang$Class()), $get$$class$java$lang$Class()));
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        $getCallSiteArray()[9].call(parent, child);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$CollectionFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CollectionFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CollectionFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CollectionFactory.__timeStamp__239_neverHappen1292524204452 = 0L;
        CollectionFactory.__timeStamp = 1292524204452L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$CollectionFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CollectionFactory.$callSiteArray == null || ($createCallSiteArray = CollectionFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CollectionFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CollectionFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CollectionFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CollectionFactory() {
        Class $class$groovy$swing$factory$CollectionFactory;
        if (($class$groovy$swing$factory$CollectionFactory = CollectionFactory.$class$groovy$swing$factory$CollectionFactory) == null) {
            $class$groovy$swing$factory$CollectionFactory = (CollectionFactory.$class$groovy$swing$factory$CollectionFactory = class$("groovy.swing.factory.CollectionFactory"));
        }
        return $class$groovy$swing$factory$CollectionFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CollectionFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CollectionFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = CollectionFactory.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (CollectionFactory.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = CollectionFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (CollectionFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MissingPropertyException() {
        Class $class$groovy$lang$MissingPropertyException;
        if (($class$groovy$lang$MissingPropertyException = CollectionFactory.$class$groovy$lang$MissingPropertyException) == null) {
            $class$groovy$lang$MissingPropertyException = (CollectionFactory.$class$groovy$lang$MissingPropertyException = class$("groovy.lang.MissingPropertyException"));
        }
        return $class$groovy$lang$MissingPropertyException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = CollectionFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (CollectionFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = CollectionFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (CollectionFactory.$class$java$lang$Class = class$("java.lang.Class"));
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
