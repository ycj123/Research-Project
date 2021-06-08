// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import java.awt.Window;
import java.awt.Component;
import javax.swing.JSplitPane;
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

public class SplitPaneFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204508;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$JSplitPane;
    private static /* synthetic */ Class $class$groovy$swing$factory$SplitPaneFactory;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    
    public SplitPaneFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JSplitPane()))) {
            return ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        }
        final JSplitPane answer = (JSplitPane)$getCallSiteArray[1].callConstructor($get$$class$javax$swing$JSplitPane());
        $getCallSiteArray[2].call(answer, (Object)null);
        $getCallSiteArray[3].call(answer, (Object)null);
        $getCallSiteArray[4].call(answer, (Object)null);
        $getCallSiteArray[5].call(answer, (Object)null);
        return ScriptBytecodeAdapter.castToType(answer, $get$$class$java$lang$Object());
    }
    
    public void setChild(final FactoryBuilderSupport factory, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((child instanceof Component && !(child instanceof Window)) ? Boolean.FALSE : Boolean.TRUE)) {
            return;
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[6].call(parent), $getCallSiteArray[7].callGetProperty($get$$class$javax$swing$JSplitPane()))) {
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[8].call(parent), null)) {
                $getCallSiteArray[9].call(parent, child);
            }
            else {
                $getCallSiteArray[10].call(parent, child);
            }
        }
        else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[11].call(parent), null)) {
            $getCallSiteArray[12].call(parent, child);
        }
        else {
            $getCallSiteArray[13].call(parent, child);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$SplitPaneFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SplitPaneFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SplitPaneFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        SplitPaneFactory.__timeStamp__239_neverHappen1292524204508 = 0L;
        SplitPaneFactory.__timeStamp = 1292524204508L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$SplitPaneFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SplitPaneFactory.$callSiteArray == null || ($createCallSiteArray = SplitPaneFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SplitPaneFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SplitPaneFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SplitPaneFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SplitPaneFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SplitPaneFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSplitPane() {
        Class $class$javax$swing$JSplitPane;
        if (($class$javax$swing$JSplitPane = SplitPaneFactory.$class$javax$swing$JSplitPane) == null) {
            $class$javax$swing$JSplitPane = (SplitPaneFactory.$class$javax$swing$JSplitPane = class$("javax.swing.JSplitPane"));
        }
        return $class$javax$swing$JSplitPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$SplitPaneFactory() {
        Class $class$groovy$swing$factory$SplitPaneFactory;
        if (($class$groovy$swing$factory$SplitPaneFactory = SplitPaneFactory.$class$groovy$swing$factory$SplitPaneFactory) == null) {
            $class$groovy$swing$factory$SplitPaneFactory = (SplitPaneFactory.$class$groovy$swing$factory$SplitPaneFactory = class$("groovy.swing.factory.SplitPaneFactory"));
        }
        return $class$groovy$swing$factory$SplitPaneFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = SplitPaneFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (SplitPaneFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
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
