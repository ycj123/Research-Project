// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class CellEditorPrepareFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204451;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$Collections;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$swing$factory$CellEditorPrepareFactory;
    
    public CellEditorPrepareFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) {
        return ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].call($get$$class$java$util$Collections()), $get$$class$java$lang$Object());
    }
    
    public boolean isHandlesNodeChildren() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean onNodeChildren(final FactoryBuilderSupport builder, final Object node, final Closure childContent) {
        ScriptBytecodeAdapter.setProperty(childContent, $get$$class$groovy$swing$factory$CellEditorPrepareFactory(), $getCallSiteArray()[1].callGroovyObjectGetProperty(builder), "prepareEditorClosure");
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$CellEditorPrepareFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CellEditorPrepareFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CellEditorPrepareFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CellEditorPrepareFactory.__timeStamp__239_neverHappen1292524204451 = 0L;
        CellEditorPrepareFactory.__timeStamp = 1292524204451L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$CellEditorPrepareFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CellEditorPrepareFactory.$callSiteArray == null || ($createCallSiteArray = CellEditorPrepareFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CellEditorPrepareFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CellEditorPrepareFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CellEditorPrepareFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CellEditorPrepareFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CellEditorPrepareFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Collections() {
        Class $class$java$util$Collections;
        if (($class$java$util$Collections = CellEditorPrepareFactory.$class$java$util$Collections) == null) {
            $class$java$util$Collections = (CellEditorPrepareFactory.$class$java$util$Collections = class$("java.util.Collections"));
        }
        return $class$java$util$Collections;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = CellEditorPrepareFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (CellEditorPrepareFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CellEditorPrepareFactory() {
        Class $class$groovy$swing$factory$CellEditorPrepareFactory;
        if (($class$groovy$swing$factory$CellEditorPrepareFactory = CellEditorPrepareFactory.$class$groovy$swing$factory$CellEditorPrepareFactory) == null) {
            $class$groovy$swing$factory$CellEditorPrepareFactory = (CellEditorPrepareFactory.$class$groovy$swing$factory$CellEditorPrepareFactory = class$("groovy.swing.factory.CellEditorPrepareFactory"));
        }
        return $class$groovy$swing$factory$CellEditorPrepareFactory;
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
