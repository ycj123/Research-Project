// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure6_closure12 extends Closure implements GeneratedClosure
{
    private Reference<Object> pendingNamespaces;
    private Reference<Object> doc;
    private Reference<Object> namespaces;
    private Reference<Object> out;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingMarkupBuilder$_closure6_closure12(final Object _outerInstance, final Object _thisObject, final Reference<Object> pendingNamespaces, final Reference<Object> doc, final Reference<Object> namespaces, final Reference<Object> out) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.pendingNamespaces = pendingNamespaces;
        this.doc = doc;
        this.namespaces = namespaces;
        this.out = out;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(((Reference<Object>)key2).get(), "$"))) {
            final Object parts = new Reference($getCallSiteArray[1].call(((Reference<Object>)key2).get(), "$"));
            if (!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(this.namespaces.get(), $getCallSiteArray[3].call(((Reference<Object>)parts).get(), StreamingMarkupBuilder$_closure6_closure12.$const$0))) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(this.pendingNamespaces.get(), $getCallSiteArray[5].call(((Reference<Object>)parts).get(), StreamingMarkupBuilder$_closure6_closure12.$const$0)))) ? Boolean.FALSE : Boolean.TRUE)) {
                throw (Throwable)$getCallSiteArray[10].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { $getCallSiteArray[11].call(((Reference<Object>)parts).get(), StreamingMarkupBuilder$_closure6_closure12.$const$0), ((Reference<Object>)key2).get() }, new String[] { "bad attribute namespace tag: ", " in ", "" }));
            }
            ((Reference<Object>)key2).set($getCallSiteArray[6].call($getCallSiteArray[7].call($getCallSiteArray[8].call(((Reference<Object>)parts).get(), StreamingMarkupBuilder$_closure6_closure12.$const$0), ":"), $getCallSiteArray[9].call(((Reference<Object>)parts).get(), StreamingMarkupBuilder$_closure6_closure12.$const$1)));
        }
        $getCallSiteArray[12].call(this.out.get(), $getCallSiteArray[13].call(new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { " ", "=" }), $getCallSiteArray[14].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12(), this.out.get(), "writingAttribute");
        $getCallSiteArray[15].call(new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" }), this.doc.get());
        ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12(), this.out.get(), "writingAttribute");
        return $getCallSiteArray[16].call(this.out.get(), $getCallSiteArray[17].callGroovyObjectGetProperty(this));
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[18].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getPendingNamespaces() {
        $getCallSiteArray();
        return this.pendingNamespaces.get();
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    public Object getOut() {
        $getCallSiteArray();
        return this.out.get();
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure6_closure12.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure6_closure12.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure6_closure12.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12() {
        Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12;
        if (($class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12 = StreamingMarkupBuilder$_closure6_closure12.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12) == null) {
            $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12 = (StreamingMarkupBuilder$_closure6_closure12.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12 = class$("groovy.xml.StreamingMarkupBuilder$_closure6_closure12"));
        }
        return $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure12;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingMarkupBuilder$_closure6_closure12.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingMarkupBuilder$_closure6_closure12.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
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
