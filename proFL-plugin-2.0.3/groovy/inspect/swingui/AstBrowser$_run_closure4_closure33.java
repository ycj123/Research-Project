// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure4_closure33 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBrowser$_run_closure4_closure33(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object k, final Object v) {
        final Object v2 = new Reference(v);
        $getCallSiteArray();
        return ScriptBytecodeAdapter.compareEqual(((Reference<Object>)v2).get(), AstBrowser$_run_closure4_closure33.$const$0) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public Object call(final Object k, final Object v) {
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[0].callCurrent(this, k, ((Reference<Object>)v2).get());
    }
    
    static {
        $const$0 = -1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure4_closure33(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure4_closure33.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure4_closure33.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure4_closure33.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
