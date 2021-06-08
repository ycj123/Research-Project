// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import javax.swing.JComponent;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class SwingBuilder$_createKeyStrokeAction_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> condition;
    private Reference<Object> component;
    private Reference<Object> actionKey;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Number;
    private static /* synthetic */ Class $class$javax$swing$KeyStroke;
    private static /* synthetic */ Class $class$javax$swing$JComponent;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public SwingBuilder$_createKeyStrokeAction_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> condition, final Reference<Object> component, final Reference<Object> actionKey) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.condition = condition;
        this.component = component;
        this.actionKey = actionKey;
    }
    
    public Object doCall(final Object ks) {
        final Object ks2 = new Reference(ks);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object value = ((Reference<Object>)ks2).get();
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$javax$swing$KeyStroke())) {
            return $getCallSiteArray[0].call($getCallSiteArray[1].call(this.component.get(), this.condition.get()), ((Reference<Object>)ks2).get(), this.actionKey.get());
        }
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$java$lang$String())) {
            return $getCallSiteArray[2].call($getCallSiteArray[3].call(this.component.get(), this.condition.get()), $getCallSiteArray[4].call($get$$class$javax$swing$KeyStroke(), ((Reference<Object>)ks2).get()), this.actionKey.get());
        }
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$java$lang$Number())) {
            return $getCallSiteArray[5].call($getCallSiteArray[6].call(this.component.get(), this.condition.get()), $getCallSiteArray[7].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[8].call(((Reference<Object>)ks2).get())), this.actionKey.get());
        }
        throw (Throwable)$getCallSiteArray[9].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)ks2).get() }, new String[] { "Cannot apply ", " as a KeyStroke value." }));
    }
    
    public Object getCondition() {
        $getCallSiteArray();
        return this.condition.get();
    }
    
    public JComponent getComponent() {
        $getCallSiteArray();
        return (JComponent)ScriptBytecodeAdapter.castToType(this.component.get(), $get$$class$javax$swing$JComponent());
    }
    
    public Object getActionKey() {
        $getCallSiteArray();
        return this.actionKey.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$SwingBuilder$_createKeyStrokeAction_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingBuilder$_createKeyStrokeAction_closure5.$callSiteArray == null || ($createCallSiteArray = SwingBuilder$_createKeyStrokeAction_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingBuilder$_createKeyStrokeAction_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Number() {
        Class $class$java$lang$Number;
        if (($class$java$lang$Number = SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$Number) == null) {
            $class$java$lang$Number = (SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$Number = class$("java.lang.Number"));
        }
        return $class$java$lang$Number;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$KeyStroke() {
        Class $class$javax$swing$KeyStroke;
        if (($class$javax$swing$KeyStroke = SwingBuilder$_createKeyStrokeAction_closure5.$class$javax$swing$KeyStroke) == null) {
            $class$javax$swing$KeyStroke = (SwingBuilder$_createKeyStrokeAction_closure5.$class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke"));
        }
        return $class$javax$swing$KeyStroke;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JComponent() {
        Class $class$javax$swing$JComponent;
        if (($class$javax$swing$JComponent = SwingBuilder$_createKeyStrokeAction_closure5.$class$javax$swing$JComponent) == null) {
            $class$javax$swing$JComponent = (SwingBuilder$_createKeyStrokeAction_closure5.$class$javax$swing$JComponent = class$("javax.swing.JComponent"));
        }
        return $class$javax$swing$JComponent;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$String) == null) {
            $class$java$lang$String = (SwingBuilder$_createKeyStrokeAction_closure5.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
