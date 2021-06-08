// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure2_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> out;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingMarkupBuilder$_closure2_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> out) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.out = out;
    }
    
    public Object doCall(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].call(this.out.get()), "<?");
        if (((Reference<Object>)instruction2).get() instanceof Map) {
            $getCallSiteArray[2].call($getCallSiteArray[3].call(this.out.get()), ((Reference<Object>)target2).get());
            final CallSite callSite = $getCallSiteArray[4];
            final Object value = ((Reference<Object>)instruction2).get();
            final Object thisObject = this.getThisObject();
            final Reference out = this.out;
            callSite.call(value, new StreamingMarkupBuilder$_closure2_closure9_closure10(this, thisObject, out));
        }
        else {
            $getCallSiteArray[5].call($getCallSiteArray[6].call(this.out.get()), new GStringImpl(new Object[] { ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get() }, new String[] { "", " ", "" }));
        }
        return $getCallSiteArray[7].call($getCallSiteArray[8].call(this.out.get()), "?>");
    }
    
    public Object call(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        return $getCallSiteArray()[9].callCurrent(this, ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get());
    }
    
    public Object getOut() {
        $getCallSiteArray();
        return this.out.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure2_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure2_closure9.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure2_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure2_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
