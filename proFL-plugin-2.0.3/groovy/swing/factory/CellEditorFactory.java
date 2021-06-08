// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class CellEditorFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204425;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$impl$ClosureCellEditor;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$CellEditorFactory;
    
    public CellEditorFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$groovy$swing$impl$ClosureCellEditor(), null, attributes), $get$$class$java$lang$Object());
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (child instanceof Component) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(parent), child), $get$$class$groovy$swing$factory$CellEditorFactory(), parent, "children");
        }
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(builder)), $get$$class$groovy$swing$factory$CellEditorFactory(), node, "editorValue");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(builder)), $get$$class$groovy$swing$factory$CellEditorFactory(), node, "prepareEditor");
        ScriptBytecodeAdapter.setProperty(node, $get$$class$groovy$swing$factory$CellEditorFactory(), parent, "cellEditor");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$CellEditorFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CellEditorFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CellEditorFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CellEditorFactory.__timeStamp__239_neverHappen1292524204425 = 0L;
        CellEditorFactory.__timeStamp = 1292524204425L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$CellEditorFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CellEditorFactory.$callSiteArray == null || ($createCallSiteArray = CellEditorFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CellEditorFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CellEditorFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CellEditorFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CellEditorFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CellEditorFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$ClosureCellEditor() {
        Class $class$groovy$swing$impl$ClosureCellEditor;
        if (($class$groovy$swing$impl$ClosureCellEditor = CellEditorFactory.$class$groovy$swing$impl$ClosureCellEditor) == null) {
            $class$groovy$swing$impl$ClosureCellEditor = (CellEditorFactory.$class$groovy$swing$impl$ClosureCellEditor = class$("groovy.swing.impl.ClosureCellEditor"));
        }
        return $class$groovy$swing$impl$ClosureCellEditor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = CellEditorFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (CellEditorFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CellEditorFactory() {
        Class $class$groovy$swing$factory$CellEditorFactory;
        if (($class$groovy$swing$factory$CellEditorFactory = CellEditorFactory.$class$groovy$swing$factory$CellEditorFactory) == null) {
            $class$groovy$swing$factory$CellEditorFactory = (CellEditorFactory.$class$groovy$swing$factory$CellEditorFactory = class$("groovy.swing.factory.CellEditorFactory"));
        }
        return $class$groovy$swing$factory$CellEditorFactory;
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
