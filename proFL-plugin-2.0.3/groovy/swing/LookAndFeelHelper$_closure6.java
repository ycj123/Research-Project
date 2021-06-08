// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class LookAndFeelHelper$_closure6 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public LookAndFeelHelper$_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object laf, final Object watermark) {
        final Object laf2 = new Reference(laf);
        final Object watermark2 = new Reference(watermark);
        return $getCallSiteArray()[0].call(((Reference<Object>)laf2).get(), ((Reference<Object>)watermark2).get());
    }
    
    public Object call(final Object laf, final Object watermark) {
        final Object laf2 = new Reference(laf);
        final Object watermark2 = new Reference(watermark);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)laf2).get(), ((Reference<Object>)watermark2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$LookAndFeelHelper$_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LookAndFeelHelper$_closure6.$callSiteArray == null || ($createCallSiteArray = LookAndFeelHelper$_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LookAndFeelHelper$_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
