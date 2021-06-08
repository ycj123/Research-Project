// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import javax.swing.text.StyleContext;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.text.Style;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_run_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> styleContext;
    private Reference<Object> applyStyle;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane$_run_closure5;
    private static /* synthetic */ Class $class$javax$swing$text$StyleConstants;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$javax$swing$text$Style;
    private static /* synthetic */ Class $class$javax$swing$text$StyleContext;
    
    public BasicContentPane$_run_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> styleContext, final Reference<Object> applyStyle) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.styleContext = styleContext;
        this.applyStyle = applyStyle;
    }
    
    public Object doCall(final Object styleName, final Object defs) {
        final Object styleName2 = new Reference(styleName);
        final Object defs2 = new Reference(defs);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Style style = (Style)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(this.styleContext.get(), ((Reference<Object>)styleName2).get()), $get$$class$javax$swing$text$Style()));
        if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)style).get())) {
            return null;
        }
        $getCallSiteArray[1].call(this.applyStyle.get(), ((Reference<Object>)style).get(), ((Reference<Object>)defs2).get());
        final String family = (String)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(((Reference<Object>)defs2).get(), $getCallSiteArray[3].callGetProperty($get$$class$javax$swing$text$StyleConstants())), $get$$class$java$lang$String()));
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[4].callGetProperty(((Reference<Object>)style).get()), "default") && DefaultTypeTransformation.booleanUnbox(((Reference<Object>)family).get())) ? Boolean.TRUE : Boolean.FALSE)) {
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)family).get(), $get$$class$groovy$ui$view$BasicContentPane$_run_closure5(), $getCallSiteArray[5].callGroovyObjectGetProperty(this), "defaultFamily");
            final Object callConstructor = $getCallSiteArray[6].callConstructor($get$$class$java$awt$Font(), ((Reference<Object>)family).get(), $getCallSiteArray[7].callGetProperty($get$$class$java$awt$Font()), $getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callGetProperty($getCallSiteArray[10].callGroovyObjectGetProperty(this))));
            ScriptBytecodeAdapter.setProperty(callConstructor, $get$$class$groovy$ui$view$BasicContentPane$_run_closure5(), $getCallSiteArray[11].callGroovyObjectGetProperty(this), "font");
            return callConstructor;
        }
        return null;
    }
    
    public Object call(final Object styleName, final Object defs) {
        final Object styleName2 = new Reference(styleName);
        final Object defs2 = new Reference(defs);
        return $getCallSiteArray()[12].callCurrent(this, ((Reference<Object>)styleName2).get(), ((Reference<Object>)defs2).get());
    }
    
    public StyleContext getStyleContext() {
        $getCallSiteArray();
        return (StyleContext)ScriptBytecodeAdapter.castToType(this.styleContext.get(), $get$$class$javax$swing$text$StyleContext());
    }
    
    public Object getApplyStyle() {
        $getCallSiteArray();
        return this.applyStyle.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure5.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = BasicContentPane$_run_closure5.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (BasicContentPane$_run_closure5.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane$_run_closure5() {
        Class $class$groovy$ui$view$BasicContentPane$_run_closure5;
        if (($class$groovy$ui$view$BasicContentPane$_run_closure5 = BasicContentPane$_run_closure5.$class$groovy$ui$view$BasicContentPane$_run_closure5) == null) {
            $class$groovy$ui$view$BasicContentPane$_run_closure5 = (BasicContentPane$_run_closure5.$class$groovy$ui$view$BasicContentPane$_run_closure5 = class$("groovy.ui.view.BasicContentPane$_run_closure5"));
        }
        return $class$groovy$ui$view$BasicContentPane$_run_closure5;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleConstants() {
        Class $class$javax$swing$text$StyleConstants;
        if (($class$javax$swing$text$StyleConstants = BasicContentPane$_run_closure5.$class$javax$swing$text$StyleConstants) == null) {
            $class$javax$swing$text$StyleConstants = (BasicContentPane$_run_closure5.$class$javax$swing$text$StyleConstants = class$("javax.swing.text.StyleConstants"));
        }
        return $class$javax$swing$text$StyleConstants;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = BasicContentPane$_run_closure5.$class$java$lang$String) == null) {
            $class$java$lang$String = (BasicContentPane$_run_closure5.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$Style() {
        Class $class$javax$swing$text$Style;
        if (($class$javax$swing$text$Style = BasicContentPane$_run_closure5.$class$javax$swing$text$Style) == null) {
            $class$javax$swing$text$Style = (BasicContentPane$_run_closure5.$class$javax$swing$text$Style = class$("javax.swing.text.Style"));
        }
        return $class$javax$swing$text$Style;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleContext() {
        Class $class$javax$swing$text$StyleContext;
        if (($class$javax$swing$text$StyleContext = BasicContentPane$_run_closure5.$class$javax$swing$text$StyleContext) == null) {
            $class$javax$swing$text$StyleContext = (BasicContentPane$_run_closure5.$class$javax$swing$text$StyleContext = class$("javax.swing.text.StyleContext"));
        }
        return $class$javax$swing$text$StyleContext;
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
