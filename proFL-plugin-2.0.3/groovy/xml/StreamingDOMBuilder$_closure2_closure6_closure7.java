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
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure2_closure6_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> buf;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingDOMBuilder$_closure2_closure6_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> buf) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.buf = buf;
    }
    
    public Object doCall(final Object name, final Object value) {
        final Object name2 = new Reference(name);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].call(((Reference<Object>)value2).get()), "\""))) {
            return $getCallSiteArray[2].call(this.buf.get(), new GStringImpl(new Object[] { ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get() }, new String[] { " ", "='", "'" }));
        }
        return $getCallSiteArray[3].call(this.buf.get(), new GStringImpl(new Object[] { ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get() }, new String[] { " ", "=\"", "\"" }));
    }
    
    public Object call(final Object name, final Object value) {
        final Object name2 = new Reference(name);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getBuf() {
        $getCallSiteArray();
        return this.buf.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure2_closure6_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure2_closure6_closure7.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure2_closure6_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure2_closure6_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
