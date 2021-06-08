// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Buildable;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingDOMBuilder$_closure3;
    
    public StreamingDOMBuilder$_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object doc2 = new Reference(doc);
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)body2).get() instanceof Closure) {
            final Object body3 = $getCallSiteArray[0].call(((Reference<Object>)body2).get());
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)doc2).get(), $get$$class$groovy$xml$StreamingDOMBuilder$_closure3(), body3, "delegate");
            return $getCallSiteArray[1].call(body3, ((Reference<Object>)doc2).get());
        }
        if (((Reference<Object>)body2).get() instanceof Buildable) {
            return $getCallSiteArray[2].call(((Reference<Object>)body2).get(), ((Reference<Object>)doc2).get());
        }
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)body2).get(), null)) {
            return $getCallSiteArray[3].call(((Reference<Object>)body2).get(), new StreamingDOMBuilder$_closure3_closure8(this, this.getThisObject(), (Reference<Object>)dom2, (Reference<Object>)doc2));
        }
        return null;
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object doc2 = new Reference(doc);
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        return $getCallSiteArray()[4].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)doc2).get(), pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, ((Reference<Object>)body2).get(), ((Reference<Object>)dom2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure3.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingDOMBuilder$_closure3() {
        Class $class$groovy$xml$StreamingDOMBuilder$_closure3;
        if (($class$groovy$xml$StreamingDOMBuilder$_closure3 = StreamingDOMBuilder$_closure3.$class$groovy$xml$StreamingDOMBuilder$_closure3) == null) {
            $class$groovy$xml$StreamingDOMBuilder$_closure3 = (StreamingDOMBuilder$_closure3.$class$groovy$xml$StreamingDOMBuilder$_closure3 = class$("groovy.xml.StreamingDOMBuilder$_closure3"));
        }
        return $class$groovy$xml$StreamingDOMBuilder$_closure3;
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
