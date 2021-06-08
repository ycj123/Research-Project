// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Collection;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import java.util.Vector;
import javax.swing.JList;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ListFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204469;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$ListFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListMetaMethods;
    private static /* synthetic */ Class $class$javax$swing$JList;
    private static /* synthetic */ Class array$$class$java$lang$Object;
    
    public ListFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        JList list = (JList)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JList());
        final Object items = $getCallSiteArray[0].call(attributes, "items");
        if (items instanceof Vector) {
            list = (JList)$getCallSiteArray[1].callConstructor($get$$class$javax$swing$JList(), $getCallSiteArray[2].call(attributes, "items"));
        }
        else if (items instanceof List) {
            final List l = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(attributes, "items"), $get$$class$java$util$List());
            list = (JList)$getCallSiteArray[4].callConstructor($get$$class$javax$swing$JList(), $getCallSiteArray[5].call(l));
        }
        else if (items instanceof Object[]) {
            list = (JList)$getCallSiteArray[6].callConstructor($get$$class$javax$swing$JList(), $getCallSiteArray[7].call(attributes, "items"));
        }
        else if (value instanceof JList) {
            list = (JList)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$JList());
        }
        else if (value instanceof Vector) {
            list = (JList)$getCallSiteArray[8].callConstructor($get$$class$javax$swing$JList(), value);
        }
        else if (value instanceof List) {
            final List l = (List)ScriptBytecodeAdapter.castToType(value, $get$$class$java$util$List());
            list = (JList)$getCallSiteArray[9].callConstructor($get$$class$javax$swing$JList(), $getCallSiteArray[10].call(l));
        }
        else if (value instanceof Object[]) {
            list = (JList)$getCallSiteArray[11].callConstructor($get$$class$javax$swing$JList(), value);
        }
        else {
            list = (JList)$getCallSiteArray[12].callConstructor($get$$class$javax$swing$JList());
        }
        $getCallSiteArray[13].call($get$$class$groovy$swing$binding$JListMetaMethods(), list);
        return ScriptBytecodeAdapter.castToType(list, $get$$class$java$lang$Object());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(attributes, "listData"))) {
            final Object listData = $getCallSiteArray[15].call(attributes, "listData");
            if (DefaultTypeTransformation.booleanUnbox((!(listData instanceof Vector) && !(listData instanceof Object[])) ? Boolean.FALSE : Boolean.TRUE)) {
                ScriptBytecodeAdapter.setProperty(listData, $get$$class$groovy$swing$factory$ListFactory(), node, "listData");
            }
            else if (listData instanceof Collection) {
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[16].call(listData), $get$$class$groovy$swing$factory$ListFactory(), node, "listData");
            }
            else {
                ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType($getCallSiteArray[17].call(listData, ScriptBytecodeAdapter.createList(new Object[0]), new ListFactory$_onHandleNodeAttributes_closure1(this, this)), $get$array$$class$java$lang$Object()), $get$$class$groovy$swing$factory$ListFactory(), node, "listData");
            }
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ListFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ListFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ListFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ListFactory.__timeStamp__239_neverHappen1292524204469 = 0L;
        ListFactory.__timeStamp = 1292524204469L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ListFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ListFactory.$callSiteArray == null || ($createCallSiteArray = ListFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ListFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ListFactory() {
        Class $class$groovy$swing$factory$ListFactory;
        if (($class$groovy$swing$factory$ListFactory = ListFactory.$class$groovy$swing$factory$ListFactory) == null) {
            $class$groovy$swing$factory$ListFactory = (ListFactory.$class$groovy$swing$factory$ListFactory = class$("groovy.swing.factory.ListFactory"));
        }
        return $class$groovy$swing$factory$ListFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ListFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ListFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ListFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ListFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = ListFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (ListFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ListFactory.$class$java$util$List) == null) {
            $class$java$util$List = (ListFactory.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListMetaMethods() {
        Class $class$groovy$swing$binding$JListMetaMethods;
        if (($class$groovy$swing$binding$JListMetaMethods = ListFactory.$class$groovy$swing$binding$JListMetaMethods) == null) {
            $class$groovy$swing$binding$JListMetaMethods = (ListFactory.$class$groovy$swing$binding$JListMetaMethods = class$("groovy.swing.binding.JListMetaMethods"));
        }
        return $class$groovy$swing$binding$JListMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JList() {
        Class $class$javax$swing$JList;
        if (($class$javax$swing$JList = ListFactory.$class$javax$swing$JList) == null) {
            $class$javax$swing$JList = (ListFactory.$class$javax$swing$JList = class$("javax.swing.JList"));
        }
        return $class$javax$swing$JList;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Object() {
        Class array$$class$java$lang$Object;
        if ((array$$class$java$lang$Object = ListFactory.array$$class$java$lang$Object) == null) {
            array$$class$java$lang$Object = (ListFactory.array$$class$java$lang$Object = class$("[Ljava.lang.Object;"));
        }
        return array$$class$java$lang$Object;
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
