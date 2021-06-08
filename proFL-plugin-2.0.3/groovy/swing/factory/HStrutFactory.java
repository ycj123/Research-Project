// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class HStrutFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204412;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Number;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$Box;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$HStrutFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public HStrutFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$java$lang$Number());
        Object num = null;
        if (ScriptBytecodeAdapter.compareNotEqual(value, null)) {
            num = value;
        }
        else {
            num = $getCallSiteArray[1].call(attributes, "width");
        }
        if (num instanceof Number) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($get$$class$javax$swing$Box(), $getCallSiteArray[3].call(ScriptBytecodeAdapter.castToType(num, $get$$class$java$lang$Number()))), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($get$$class$javax$swing$Box(), HStrutFactory.$const$0), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$HStrutFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HStrutFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HStrutFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HStrutFactory.__timeStamp__239_neverHappen1292524204412 = 0L;
        HStrutFactory.__timeStamp = 1292524204412L;
        $const$0 = 6;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$HStrutFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HStrutFactory.$callSiteArray == null || ($createCallSiteArray = HStrutFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HStrutFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Number() {
        Class $class$java$lang$Number;
        if (($class$java$lang$Number = HStrutFactory.$class$java$lang$Number) == null) {
            $class$java$lang$Number = (HStrutFactory.$class$java$lang$Number = class$("java.lang.Number"));
        }
        return $class$java$lang$Number;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HStrutFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HStrutFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Box() {
        Class $class$javax$swing$Box;
        if (($class$javax$swing$Box = HStrutFactory.$class$javax$swing$Box) == null) {
            $class$javax$swing$Box = (HStrutFactory.$class$javax$swing$Box = class$("javax.swing.Box"));
        }
        return $class$javax$swing$Box;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HStrutFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HStrutFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$HStrutFactory() {
        Class $class$groovy$swing$factory$HStrutFactory;
        if (($class$groovy$swing$factory$HStrutFactory = HStrutFactory.$class$groovy$swing$factory$HStrutFactory) == null) {
            $class$groovy$swing$factory$HStrutFactory = (HStrutFactory.$class$groovy$swing$factory$HStrutFactory = class$("groovy.swing.factory.HStrutFactory"));
        }
        return $class$groovy$swing$factory$HStrutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = HStrutFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (HStrutFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
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
