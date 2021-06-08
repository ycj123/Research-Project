// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.text.Style;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_run_closure4_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> style;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$text$Style;
    
    public BasicContentPane$_run_closure4_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> style) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.style = style;
    }
    
    public Object doCall(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[0].call(this.style.get(), ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public Object call(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public Style getStyle() {
        $getCallSiteArray();
        return (Style)ScriptBytecodeAdapter.castToType(this.style.get(), $get$$class$javax$swing$text$Style());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure4_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure4_closure9.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure4_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure4_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$Style() {
        Class $class$javax$swing$text$Style;
        if (($class$javax$swing$text$Style = BasicContentPane$_run_closure4_closure9.$class$javax$swing$text$Style) == null) {
            $class$javax$swing$text$Style = (BasicContentPane$_run_closure4_closure9.$class$javax$swing$text$Style = class$("javax.swing.text.Style"));
        }
        return $class$javax$swing$text$Style;
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
