// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingMarkupBuilder$_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object out2 = new Reference(out);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].call(((Reference<Object>)out2).get()), $getCallSiteArray[2].call($getCallSiteArray[3].call($getCallSiteArray[4].call("<?xml version=", $getCallSiteArray[5].callGroovyObjectGetProperty(this)), "1.0"), $getCallSiteArray[6].callGroovyObjectGetProperty(this)));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callGetProperty(((Reference<Object>)out2).get()))) {
            $getCallSiteArray[8].call($getCallSiteArray[9].call(((Reference<Object>)out2).get()), $getCallSiteArray[10].call($getCallSiteArray[11].call($getCallSiteArray[12].call(" encoding=", $getCallSiteArray[13].callGroovyObjectGetProperty(this)), $getCallSiteArray[14].callGetProperty(((Reference<Object>)out2).get())), $getCallSiteArray[15].callGroovyObjectGetProperty(this)));
        }
        return $getCallSiteArray[16].call($getCallSiteArray[17].call(((Reference<Object>)out2).get()), "?>\n");
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object out2 = new Reference(out);
        return $getCallSiteArray()[18].callCurrent(this, ArrayUtil.createArray(doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, ((Reference<Object>)out2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure3.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
