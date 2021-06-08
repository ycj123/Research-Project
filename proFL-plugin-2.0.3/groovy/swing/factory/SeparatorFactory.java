// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class SeparatorFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204506;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$SeparatorFactory;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$JSeparator;
    private static /* synthetic */ Class $class$javax$swing$JPopupMenu$Separator;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$javax$swing$JToolBar$Separator;
    
    public SeparatorFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        final Object parent = $getCallSiteArray[1].call(builder);
        if (parent instanceof JMenu) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$javax$swing$JPopupMenu$Separator()), $get$$class$java$lang$Object());
        }
        if (parent instanceof JToolBar) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callConstructor($get$$class$javax$swing$JToolBar$Separator()), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callConstructor($get$$class$javax$swing$JSeparator()), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$SeparatorFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SeparatorFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SeparatorFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        SeparatorFactory.__timeStamp__239_neverHappen1292524204506 = 0L;
        SeparatorFactory.__timeStamp = 1292524204506L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$SeparatorFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SeparatorFactory.$callSiteArray == null || ($createCallSiteArray = SeparatorFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SeparatorFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SeparatorFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SeparatorFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$SeparatorFactory() {
        Class $class$groovy$swing$factory$SeparatorFactory;
        if (($class$groovy$swing$factory$SeparatorFactory = SeparatorFactory.$class$groovy$swing$factory$SeparatorFactory) == null) {
            $class$groovy$swing$factory$SeparatorFactory = (SeparatorFactory.$class$groovy$swing$factory$SeparatorFactory = class$("groovy.swing.factory.SeparatorFactory"));
        }
        return $class$groovy$swing$factory$SeparatorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SeparatorFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SeparatorFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSeparator() {
        Class $class$javax$swing$JSeparator;
        if (($class$javax$swing$JSeparator = SeparatorFactory.$class$javax$swing$JSeparator) == null) {
            $class$javax$swing$JSeparator = (SeparatorFactory.$class$javax$swing$JSeparator = class$("javax.swing.JSeparator"));
        }
        return $class$javax$swing$JSeparator;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JPopupMenu$Separator() {
        Class $class$javax$swing$JPopupMenu$Separator;
        if (($class$javax$swing$JPopupMenu$Separator = SeparatorFactory.$class$javax$swing$JPopupMenu$Separator) == null) {
            $class$javax$swing$JPopupMenu$Separator = (SeparatorFactory.$class$javax$swing$JPopupMenu$Separator = class$("javax.swing.JPopupMenu$Separator"));
        }
        return $class$javax$swing$JPopupMenu$Separator;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = SeparatorFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (SeparatorFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JToolBar$Separator() {
        Class $class$javax$swing$JToolBar$Separator;
        if (($class$javax$swing$JToolBar$Separator = SeparatorFactory.$class$javax$swing$JToolBar$Separator) == null) {
            $class$javax$swing$JToolBar$Separator = (SeparatorFactory.$class$javax$swing$JToolBar$Separator = class$("javax.swing.JToolBar$Separator"));
        }
        return $class$javax$swing$JToolBar$Separator;
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
