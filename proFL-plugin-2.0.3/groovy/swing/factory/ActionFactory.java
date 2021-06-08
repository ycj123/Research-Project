// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.GString;
import javax.swing.JComponent;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MissingPropertyException;
import javax.swing.KeyStroke;
import groovy.swing.impl.DefaultAction;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.Action;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ActionFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204377;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$JComponent;
    private static /* synthetic */ Class $class$javax$swing$Action;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$impl$DefaultAction;
    private static /* synthetic */ Class $class$java$lang$Character;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$javax$swing$KeyStroke;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$ActionFactory;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public ActionFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public boolean isHandlesNodeChildren() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Action action = (Action)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$Action());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$groovy$util$FactoryBuilderSupport(), value, name, $get$$class$javax$swing$Action()))) {
            action = (Action)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$Action());
        }
        else if ($getCallSiteArray[1].call(attributes, name) instanceof Action) {
            action = (Action)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(attributes, name), $get$$class$javax$swing$Action());
        }
        else {
            action = (Action)$getCallSiteArray[3].callConstructor($get$$class$groovy$swing$impl$DefaultAction());
        }
        return ScriptBytecodeAdapter.castToType(action, $get$$class$java$lang$Object());
    }
    
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object action, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(($getCallSiteArray[4].call(attributes, "closure") instanceof Closure && action instanceof DefaultAction) ? Boolean.TRUE : Boolean.FALSE)) {
            final Closure closure = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(attributes, "closure"), $get$$class$groovy$lang$Closure());
            $getCallSiteArray[6].call(ScriptBytecodeAdapter.castToType(action, $get$$class$groovy$swing$impl$DefaultAction()), closure);
        }
        final Object accel = $getCallSiteArray[7].call(attributes, "accelerator");
        if (ScriptBytecodeAdapter.compareNotEqual(accel, null)) {
            KeyStroke stroke = (KeyStroke)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$KeyStroke());
            if (accel instanceof KeyStroke) {
                stroke = (KeyStroke)ScriptBytecodeAdapter.castToType(accel, $get$$class$javax$swing$KeyStroke());
            }
            else {
                stroke = (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[9].call(accel)), $get$$class$javax$swing$KeyStroke());
            }
            $getCallSiteArray[10].call(action, $getCallSiteArray[11].callGetProperty($get$$class$javax$swing$Action()), stroke);
        }
        Object mnemonic = $getCallSiteArray[12].call(attributes, "mnemonic");
        if (ScriptBytecodeAdapter.compareNotEqual(mnemonic, null)) {
            if (!(mnemonic instanceof Number)) {
                mnemonic = $getCallSiteArray[13].call($getCallSiteArray[14].call(mnemonic), ActionFactory.$const$0);
            }
            $getCallSiteArray[15].call(action, $getCallSiteArray[16].callGetProperty($get$$class$javax$swing$Action()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(mnemonic, $get$$class$java$lang$Integer()), $get$$class$java$lang$Integer()));
        }
        Object entry = null;
        final Object call = $getCallSiteArray[17].call($getCallSiteArray[18].call(attributes));
        while (((Iterator)call).hasNext()) {
            entry = ((Iterator<Object>)call).next();
            String propertyName = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[19].call(entry), $get$$class$java$lang$String());
            try {
                $getCallSiteArray[20].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), action, propertyName, $getCallSiteArray[21].call(entry));
            }
            catch (MissingPropertyException mpe) {
                propertyName = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[22].callCurrent(this, propertyName), $get$$class$java$lang$String());
                $getCallSiteArray[23].call(action, propertyName, $getCallSiteArray[24].call(entry));
            }
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean onNodeChildren(final FactoryBuilderSupport builder, final Object node, final Closure childContent) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(node instanceof DefaultAction)) {
            throw (Throwable)$getCallSiteArray[25].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { $getCallSiteArray[26].callGroovyObjectGetProperty(builder) }, new String[] { "", " only accepts a closure content when the action is generated by the node" }));
        }
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[27].callGetProperty(node), null)) {
            throw (Throwable)$getCallSiteArray[28].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { $getCallSiteArray[29].callGroovyObjectGetProperty(builder) }, new String[] { "", " already has an action set via the closure attribute, child content as action not allowed" }));
        }
        ScriptBytecodeAdapter.setProperty(childContent, $get$$class$groovy$swing$factory$ActionFactory(), node, "closure");
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object action) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        try {
            $getCallSiteArray[30].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), parent, "action", action);
        }
        catch (RuntimeException re) {}
        Object keyStroke = $getCallSiteArray[31].call(action, "KeyStroke");
        if (parent instanceof JComponent) {
            final JComponent component = (JComponent)ScriptBytecodeAdapter.castToType(parent, $get$$class$javax$swing$JComponent());
            KeyStroke stroke = (KeyStroke)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$KeyStroke());
            if (keyStroke instanceof GString) {
                keyStroke = ScriptBytecodeAdapter.asType(keyStroke, $get$$class$java$lang$String());
            }
            if (keyStroke instanceof String) {
                stroke = (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray[32].call($get$$class$javax$swing$KeyStroke(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(keyStroke, $get$$class$java$lang$String()), $get$$class$java$lang$String())), $get$$class$javax$swing$KeyStroke());
            }
            else if (keyStroke instanceof KeyStroke) {
                stroke = (KeyStroke)ScriptBytecodeAdapter.castToType(keyStroke, $get$$class$javax$swing$KeyStroke());
            }
            if (ScriptBytecodeAdapter.compareNotEqual(stroke, null)) {
                final String key = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[33].call(action), $get$$class$java$lang$String());
                $getCallSiteArray[34].call($getCallSiteArray[35].call(component), stroke, key);
                $getCallSiteArray[36].call($getCallSiteArray[37].call(component), key, action);
            }
        }
    }
    
    public String capitalize(final String text) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Character ch = (Character)ScriptBytecodeAdapter.castToType($getCallSiteArray[38].call(text, ActionFactory.$const$0), $get$$class$java$lang$Character());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[39].call($get$$class$java$lang$Character(), ch))) {
            return (String)ScriptBytecodeAdapter.castToType(text, $get$$class$java$lang$String());
        }
        final StringBuffer buffer = (StringBuffer)$getCallSiteArray[40].callConstructor($get$$class$java$lang$StringBuffer(), $getCallSiteArray[41].call(text));
        $getCallSiteArray[42].call(buffer, $getCallSiteArray[43].call($get$$class$java$lang$Character(), ch));
        $getCallSiteArray[44].call(buffer, $getCallSiteArray[45].call(text, ActionFactory.$const$1));
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[46].call(buffer), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ActionFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ActionFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ActionFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ActionFactory.__timeStamp__239_neverHappen1292524204377 = 0L;
        ActionFactory.__timeStamp = 1292524204377L;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[47];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ActionFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ActionFactory.$callSiteArray == null || ($createCallSiteArray = ActionFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ActionFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JComponent() {
        Class $class$javax$swing$JComponent;
        if (($class$javax$swing$JComponent = ActionFactory.$class$javax$swing$JComponent) == null) {
            $class$javax$swing$JComponent = (ActionFactory.$class$javax$swing$JComponent = class$("javax.swing.JComponent"));
        }
        return $class$javax$swing$JComponent;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Action() {
        Class $class$javax$swing$Action;
        if (($class$javax$swing$Action = ActionFactory.$class$javax$swing$Action) == null) {
            $class$javax$swing$Action = (ActionFactory.$class$javax$swing$Action = class$("javax.swing.Action"));
        }
        return $class$javax$swing$Action;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = ActionFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (ActionFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$DefaultAction() {
        Class $class$groovy$swing$impl$DefaultAction;
        if (($class$groovy$swing$impl$DefaultAction = ActionFactory.$class$groovy$swing$impl$DefaultAction) == null) {
            $class$groovy$swing$impl$DefaultAction = (ActionFactory.$class$groovy$swing$impl$DefaultAction = class$("groovy.swing.impl.DefaultAction"));
        }
        return $class$groovy$swing$impl$DefaultAction;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Character() {
        Class $class$java$lang$Character;
        if (($class$java$lang$Character = ActionFactory.$class$java$lang$Character) == null) {
            $class$java$lang$Character = (ActionFactory.$class$java$lang$Character = class$("java.lang.Character"));
        }
        return $class$java$lang$Character;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = ActionFactory.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (ActionFactory.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ActionFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (ActionFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = ActionFactory.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (ActionFactory.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$KeyStroke() {
        Class $class$javax$swing$KeyStroke;
        if (($class$javax$swing$KeyStroke = ActionFactory.$class$javax$swing$KeyStroke) == null) {
            $class$javax$swing$KeyStroke = (ActionFactory.$class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke"));
        }
        return $class$javax$swing$KeyStroke;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = ActionFactory.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (ActionFactory.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ActionFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ActionFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ActionFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ActionFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ActionFactory() {
        Class $class$groovy$swing$factory$ActionFactory;
        if (($class$groovy$swing$factory$ActionFactory = ActionFactory.$class$groovy$swing$factory$ActionFactory) == null) {
            $class$groovy$swing$factory$ActionFactory = (ActionFactory.$class$groovy$swing$factory$ActionFactory = class$("groovy.swing.factory.ActionFactory"));
        }
        return $class$groovy$swing$factory$ActionFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = ActionFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (ActionFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = ActionFactory.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (ActionFactory.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = ActionFactory.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (ActionFactory.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
