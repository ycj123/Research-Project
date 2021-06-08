// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import javax.swing.text.Style;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_run_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public BasicContentPane$_run_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Style style, final Object values) {
        final Style style2 = (Style)new Reference(style);
        final Object values2 = new Reference(values);
        return $getCallSiteArray()[0].call(((Reference<Object>)values2).get(), new BasicContentPane$_run_closure4_closure9(this, this.getThisObject(), (Reference<Object>)style2));
    }
    
    public Object call(final Style style, final Object values) {
        final Style style2 = (Style)new Reference(style);
        final Object values2 = new Reference(values);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)style2).get(), ((Reference<Object>)values2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure4.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
