// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.RootPaneContainer;
import javax.swing.JComponent;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public abstract class SwingBorderFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204530;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$SwingBorderFactory;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public SwingBorderFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map attributes) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(builder)))) {
            if (parent instanceof JComponent) {
                $getCallSiteArray[2].call(parent, child);
            }
            else {
                if (!(parent instanceof RootPaneContainer)) {
                    throw (Throwable)$getCallSiteArray[5].callConstructor($get$$class$java$lang$RuntimeException(), "Border cannot be applied to parent, it is neither a JComponent or a RootPaneContainer");
                }
                $getCallSiteArray[3].callCurrent(this, builder, $getCallSiteArray[4].callGetProperty(parent), child);
            }
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$SwingBorderFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SwingBorderFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SwingBorderFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public MetaClass getMetaClass() {
        final MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
    
    public Object invokeMethod(final String s, final Object o) {
        return this.getMetaClass().invokeMethod(this, s, o);
    }
    
    public Object getProperty(final String s) {
        return this.getMetaClass().getProperty(this, s);
    }
    
    public void setProperty(final String s, final Object o) {
        this.getMetaClass().setProperty(this, s, o);
    }
    
    static {
        SwingBorderFactory.__timeStamp__239_neverHappen1292524204530 = 0L;
        SwingBorderFactory.__timeStamp = 1292524204530L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$SwingBorderFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingBorderFactory.$callSiteArray == null || ($createCallSiteArray = SwingBorderFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingBorderFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SwingBorderFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SwingBorderFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = SwingBorderFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (SwingBorderFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$SwingBorderFactory() {
        Class $class$groovy$swing$factory$SwingBorderFactory;
        if (($class$groovy$swing$factory$SwingBorderFactory = SwingBorderFactory.$class$groovy$swing$factory$SwingBorderFactory) == null) {
            $class$groovy$swing$factory$SwingBorderFactory = (SwingBorderFactory.$class$groovy$swing$factory$SwingBorderFactory = class$("groovy.swing.factory.SwingBorderFactory"));
        }
        return $class$groovy$swing$factory$SwingBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = SwingBorderFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (SwingBorderFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
