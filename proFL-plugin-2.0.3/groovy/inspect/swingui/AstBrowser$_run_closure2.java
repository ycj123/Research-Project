// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> phasePicker;
    private Reference<Object> splitterPane;
    private Reference<Object> mainSplitter;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBrowser$_run_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> phasePicker, final Reference<Object> splitterPane, final Reference<Object> mainSplitter) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phasePicker = phasePicker;
        this.splitterPane = splitterPane;
        this.mainSplitter = mainSplitter;
    }
    
    public Object doCall(final Object event) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ArrayUtil.createArray($getCallSiteArray[2].callGroovyObjectGetProperty(this), this.splitterPane.get(), this.mainSplitter.get(), $getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGroovyObjectGetProperty(this), $getCallSiteArray[5].callGetProperty(this.phasePicker.get())));
    }
    
    public Object getPhasePicker() {
        $getCallSiteArray();
        return this.phasePicker.get();
    }
    
    public Object getSplitterPane() {
        $getCallSiteArray();
        return this.splitterPane.get();
    }
    
    public Object getMainSplitter() {
        $getCallSiteArray();
        return this.mainSplitter.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure2.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
