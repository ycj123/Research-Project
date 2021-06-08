// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Buildable;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure4_closure13 extends Closure implements GeneratedClosure
{
    private Reference<Object> dom;
    private Reference<Object> doc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$xml$StreamingDOMBuilder$_closure4_closure13;
    
    public StreamingDOMBuilder$_closure4_closure13(final Object _outerInstance, final Object _thisObject, final Reference<Object> dom, final Reference<Object> doc) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.dom = dom;
        this.doc = doc;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)it2).get() instanceof Closure) {
            final Object body1 = $getCallSiteArray[0].call(((Reference<Object>)it2).get());
            ScriptBytecodeAdapter.setProperty(this.doc.get(), $get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure13(), body1, "delegate");
            return $getCallSiteArray[1].call(body1, this.doc.get());
        }
        if (((Reference<Object>)it2).get() instanceof Buildable) {
            return $getCallSiteArray[2].call(((Reference<Object>)it2).get(), this.doc.get());
        }
        return $getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(this.dom.get()), $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(this.dom.get()), ((Reference<Object>)it2).get()));
    }
    
    public Object getDom() {
        $getCallSiteArray();
        return this.dom.get();
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure4_closure13.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure4_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure4_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingDOMBuilder$_closure4_closure13.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingDOMBuilder$_closure4_closure13.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure13() {
        Class $class$groovy$xml$StreamingDOMBuilder$_closure4_closure13;
        if (($class$groovy$xml$StreamingDOMBuilder$_closure4_closure13 = StreamingDOMBuilder$_closure4_closure13.$class$groovy$xml$StreamingDOMBuilder$_closure4_closure13) == null) {
            $class$groovy$xml$StreamingDOMBuilder$_closure4_closure13 = (StreamingDOMBuilder$_closure4_closure13.$class$groovy$xml$StreamingDOMBuilder$_closure4_closure13 = class$("groovy.xml.StreamingDOMBuilder$_closure4_closure13"));
        }
        return $class$groovy$xml$StreamingDOMBuilder$_closure4_closure13;
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
