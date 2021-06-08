// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AbstractStreamingBuilder$_closure3_closure6_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> info;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AbstractStreamingBuilder$_closure3_closure6_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> info) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.info = info;
    }
    
    public Object doCall(final Object to, final Object from) {
        final Object to2 = new Reference(to);
        final Object from2 = new Reference(from);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object call = $getCallSiteArray[1].call(this.info.get(), AbstractStreamingBuilder$_closure3_closure6_closure7.$const$0);
        final Object value = ((Reference<Object>)to2).get();
        final Object call2 = $getCallSiteArray[2].call($getCallSiteArray[3].call(this.info.get(), AbstractStreamingBuilder$_closure3_closure6_closure7.$const$1), ((Reference<Object>)from2).get());
        callSite.call(call, value, call2);
        return call2;
    }
    
    public Object call(final Object to, final Object from) {
        final Object to2 = new Reference(to);
        final Object from2 = new Reference(from);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)to2).get(), ((Reference<Object>)from2).get());
    }
    
    public Object getInfo() {
        $getCallSiteArray();
        return this.info.get();
    }
    
    static {
        $const$1 = 1;
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure3_closure6_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure3_closure6_closure7.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure3_closure6_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure3_closure6_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
