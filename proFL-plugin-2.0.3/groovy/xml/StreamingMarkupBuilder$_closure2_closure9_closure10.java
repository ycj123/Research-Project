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

class StreamingMarkupBuilder$_closure2_closure9_closure10 extends Closure implements GeneratedClosure
{
    private Reference<Object> out;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingMarkupBuilder$_closure2_closure9_closure10(final Object _outerInstance, final Object _thisObject, final Reference<Object> out) {
        final Reference out2 = new Reference((T)out);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.out = out2.get();
    }
    
    public Object doCall(final Object name, final Object value) {
        final Object name2 = new Reference(name);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].call(((Reference<Object>)value2).get()), "'")) && !DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGroovyObjectGetProperty(this)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call($getCallSiteArray[4].call(((Reference<Object>)value2).get()), "\""))) ? Boolean.TRUE : Boolean.FALSE)) ? Boolean.FALSE : Boolean.TRUE)) {
            return $getCallSiteArray[5].call($getCallSiteArray[6].call(this.out.get()), new GStringImpl(new Object[] { ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get() }, new String[] { " ", "=\"", "\"" }));
        }
        return $getCallSiteArray[7].call($getCallSiteArray[8].call(this.out.get()), new GStringImpl(new Object[] { ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get() }, new String[] { " ", "='", "'" }));
    }
    
    public Object call(final Object name, final Object value) {
        final Object name2 = new Reference(name);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[9].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getOut() {
        $getCallSiteArray();
        return this.out.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure2_closure9_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure2_closure9_closure10.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure2_closure9_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure2_closure9_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
