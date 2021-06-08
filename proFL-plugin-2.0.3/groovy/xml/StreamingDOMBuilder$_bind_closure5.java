// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.w3c.dom.Node;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_bind_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> boundClosure;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$xml$parsers$DocumentBuilderFactory;
    private static /* synthetic */ Class $class$groovy$xml$StreamingDOMBuilder$_bind_closure5;
    
    public StreamingDOMBuilder$_bind_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> boundClosure) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.boundClosure = boundClosure;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)it2).get() instanceof Node) {
            final Object document = $getCallSiteArray[0].call(((Reference<Object>)it2).get());
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.createMap(new Object[] { "document", document, "element", ((Reference<Object>)it2).get() }), $get$$class$groovy$xml$StreamingDOMBuilder$_bind_closure5(), this.boundClosure.get(), "trigger");
            return document;
        }
        final Object dBuilder = $getCallSiteArray[1].call($get$$class$javax$xml$parsers$DocumentBuilderFactory());
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$xml$StreamingDOMBuilder$_bind_closure5(), dBuilder, "namespaceAware");
        final Object newDocument = $getCallSiteArray[2].call($getCallSiteArray[3].call(dBuilder));
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.createMap(new Object[] { "document", newDocument, "element", newDocument }), $get$$class$groovy$xml$StreamingDOMBuilder$_bind_closure5(), this.boundClosure.get(), "trigger");
        return newDocument;
    }
    
    public Object getBoundClosure() {
        $getCallSiteArray();
        return this.boundClosure.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_bind_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_bind_closure5.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_bind_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_bind_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingDOMBuilder$_bind_closure5.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingDOMBuilder$_bind_closure5.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$xml$parsers$DocumentBuilderFactory() {
        Class $class$javax$xml$parsers$DocumentBuilderFactory;
        if (($class$javax$xml$parsers$DocumentBuilderFactory = StreamingDOMBuilder$_bind_closure5.$class$javax$xml$parsers$DocumentBuilderFactory) == null) {
            $class$javax$xml$parsers$DocumentBuilderFactory = (StreamingDOMBuilder$_bind_closure5.$class$javax$xml$parsers$DocumentBuilderFactory = class$("javax.xml.parsers.DocumentBuilderFactory"));
        }
        return $class$javax$xml$parsers$DocumentBuilderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingDOMBuilder$_bind_closure5() {
        Class $class$groovy$xml$StreamingDOMBuilder$_bind_closure5;
        if (($class$groovy$xml$StreamingDOMBuilder$_bind_closure5 = StreamingDOMBuilder$_bind_closure5.$class$groovy$xml$StreamingDOMBuilder$_bind_closure5) == null) {
            $class$groovy$xml$StreamingDOMBuilder$_bind_closure5 = (StreamingDOMBuilder$_bind_closure5.$class$groovy$xml$StreamingDOMBuilder$_bind_closure5 = class$("groovy.xml.StreamingDOMBuilder$_bind_closure5"));
        }
        return $class$groovy$xml$StreamingDOMBuilder$_bind_closure5;
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
