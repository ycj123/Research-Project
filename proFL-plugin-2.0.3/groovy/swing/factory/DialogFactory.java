// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.Window;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.LinkedList;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JDialog;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class DialogFactory extends RootPaneContainerFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204976;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Frame;
    private static /* synthetic */ Class $class$groovy$swing$factory$DialogFactory;
    private static /* synthetic */ Class $class$java$awt$Dialog;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    private static /* synthetic */ Class $class$javax$swing$JDialog;
    
    public DialogFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        JDialog dialog = (JDialog)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JDialog());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$JDialog()))) {
            dialog = (JDialog)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$JDialog());
        }
        else {
            Object owner = $getCallSiteArray[1].call(attributes, "owner");
            final LinkedList containingWindows = (LinkedList)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callGroovyObjectGetProperty(builder), $get$$class$java$util$LinkedList());
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(owner, null) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(containingWindows))) ? Boolean.TRUE : Boolean.FALSE)) {
                owner = $getCallSiteArray[4].call(containingWindows);
            }
            if (owner instanceof Frame) {
                dialog = (JDialog)$getCallSiteArray[5].callConstructor($get$$class$javax$swing$JDialog(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(owner, $get$$class$java$awt$Frame()), $get$$class$java$awt$Frame()));
            }
            else if (owner instanceof Dialog) {
                dialog = (JDialog)$getCallSiteArray[6].callConstructor($get$$class$javax$swing$JDialog(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(owner, $get$$class$java$awt$Dialog()), $get$$class$java$awt$Dialog()));
            }
            else {
                dialog = (JDialog)$getCallSiteArray[7].callConstructor($get$$class$javax$swing$JDialog());
            }
        }
        $getCallSiteArray[8].callCurrent(this, builder, dialog, attributes);
        return ScriptBytecodeAdapter.castToType(dialog, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$DialogFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = DialogFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (DialogFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        DialogFactory.__timeStamp__239_neverHappen1292524204976 = 0L;
        DialogFactory.__timeStamp = 1292524204976L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$DialogFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (DialogFactory.$callSiteArray == null || ($createCallSiteArray = DialogFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            DialogFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = DialogFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (DialogFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = DialogFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (DialogFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Frame() {
        Class $class$java$awt$Frame;
        if (($class$java$awt$Frame = DialogFactory.$class$java$awt$Frame) == null) {
            $class$java$awt$Frame = (DialogFactory.$class$java$awt$Frame = class$("java.awt.Frame"));
        }
        return $class$java$awt$Frame;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$DialogFactory() {
        Class $class$groovy$swing$factory$DialogFactory;
        if (($class$groovy$swing$factory$DialogFactory = DialogFactory.$class$groovy$swing$factory$DialogFactory) == null) {
            $class$groovy$swing$factory$DialogFactory = (DialogFactory.$class$groovy$swing$factory$DialogFactory = class$("groovy.swing.factory.DialogFactory"));
        }
        return $class$groovy$swing$factory$DialogFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dialog() {
        Class $class$java$awt$Dialog;
        if (($class$java$awt$Dialog = DialogFactory.$class$java$awt$Dialog) == null) {
            $class$java$awt$Dialog = (DialogFactory.$class$java$awt$Dialog = class$("java.awt.Dialog"));
        }
        return $class$java$awt$Dialog;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = DialogFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (DialogFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = DialogFactory.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (DialogFactory.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JDialog() {
        Class $class$javax$swing$JDialog;
        if (($class$javax$swing$JDialog = DialogFactory.$class$javax$swing$JDialog) == null) {
            $class$javax$swing$JDialog = (DialogFactory.$class$javax$swing$JDialog = class$("javax.swing.JDialog"));
        }
        return $class$javax$swing$JDialog;
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
