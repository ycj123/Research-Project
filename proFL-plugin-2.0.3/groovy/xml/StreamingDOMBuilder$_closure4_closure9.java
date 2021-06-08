// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure4_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> nsAttributes;
    private Reference<Object> attributes;
    private Reference<Object> namespaces;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingDOMBuilder$_closure4_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> nsAttributes, final Reference<Object> attributes, final Reference<Object> namespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.nsAttributes = nsAttributes;
        this.attributes = attributes;
        this.namespaces = namespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(((Reference<Object>)key2).get(), "$"))) {
            return $getCallSiteArray[10].call(this.attributes.get(), ScriptBytecodeAdapter.createList(new Object[] { ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get() }));
        }
        final Object parts = new Reference($getCallSiteArray[1].call(((Reference<Object>)key2).get(), "$"));
        final Object namespaceUri = new Reference(null);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(this.namespaces.get(), $getCallSiteArray[3].call(((Reference<Object>)parts).get(), StreamingDOMBuilder$_closure4_closure9.$const$0)))) {
            ((Reference<Object>)namespaceUri).set($getCallSiteArray[4].call(this.namespaces.get(), $getCallSiteArray[5].call(((Reference<Object>)parts).get(), StreamingDOMBuilder$_closure4_closure9.$const$0)));
            return $getCallSiteArray[6].call(this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[] { ((Reference<Object>)namespaceUri).get(), new GStringImpl(new Object[] { $getCallSiteArray[7].call(((Reference<Object>)parts).get(), StreamingDOMBuilder$_closure4_closure9.$const$0), $getCallSiteArray[8].call(((Reference<Object>)parts).get(), StreamingDOMBuilder$_closure4_closure9.$const$1) }, new String[] { "", ":", "" }), new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" }) }));
        }
        throw (Throwable)$getCallSiteArray[9].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { "bad attribute namespace tag in ", "" }));
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[11].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getNsAttributes() {
        $getCallSiteArray();
        return this.nsAttributes.get();
    }
    
    public Object getAttributes() {
        $getCallSiteArray();
        return this.attributes.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure4_closure9.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure4_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure4_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingDOMBuilder$_closure4_closure9.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingDOMBuilder$_closure4_closure9.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
        }
        return $class$groovy$lang$GroovyRuntimeException;
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
