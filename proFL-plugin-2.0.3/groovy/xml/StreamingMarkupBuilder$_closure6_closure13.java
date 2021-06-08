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
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure6_closure13 extends Closure implements GeneratedClosure
{
    private Reference<Object> doc;
    private Reference<Object> namespaces;
    private Reference<Object> hiddenNamespaces;
    private Reference<Object> out;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13;
    
    public StreamingMarkupBuilder$_closure6_closure13(final Object _outerInstance, final Object _thisObject, final Reference<Object> doc, final Reference<Object> namespaces, final Reference<Object> hiddenNamespaces, final Reference<Object> out) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.doc = doc;
        this.namespaces = namespaces;
        this.hiddenNamespaces = hiddenNamespaces;
        this.out = out;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(this.hiddenNamespaces.get(), ((Reference<Object>)key2).get(), $getCallSiteArray[1].call(this.namespaces.get(), ((Reference<Object>)key2).get()));
        $getCallSiteArray[2].call(this.namespaces.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
        $getCallSiteArray[3].call(this.out.get(), ScriptBytecodeAdapter.compareEqual(((Reference<Object>)key2).get(), ":") ? $getCallSiteArray[4].call(" xmlns=", $getCallSiteArray[5].callGroovyObjectGetProperty(this)) : $getCallSiteArray[6].call(new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { " xmlns:", "=" }), $getCallSiteArray[7].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13(), this.out.get(), "writingAttribute");
        $getCallSiteArray[8].call(new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" }), this.doc.get());
        ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13(), this.out.get(), "writingAttribute");
        return $getCallSiteArray[9].call(this.out.get(), $getCallSiteArray[10].callGroovyObjectGetProperty(this));
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[11].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    public Object getHiddenNamespaces() {
        $getCallSiteArray();
        return this.hiddenNamespaces.get();
    }
    
    public Object getOut() {
        $getCallSiteArray();
        return this.out.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure6_closure13.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure6_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure6_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13() {
        Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13;
        if (($class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13 = StreamingMarkupBuilder$_closure6_closure13.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13) == null) {
            $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13 = (StreamingMarkupBuilder$_closure6_closure13.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13 = class$("groovy.xml.StreamingMarkupBuilder$_closure6_closure13"));
        }
        return $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure13;
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
