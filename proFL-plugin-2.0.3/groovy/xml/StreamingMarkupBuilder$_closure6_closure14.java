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

class StreamingMarkupBuilder$_closure6_closure14 extends Closure implements GeneratedClosure
{
    private Reference<Object> doc;
    private Reference<Object> out;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14;
    
    public StreamingMarkupBuilder$_closure6_closure14(final Object _outerInstance, final Object _thisObject, final Reference<Object> doc, final Reference<Object> out) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.doc = doc;
        this.out = out;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)it2).get() instanceof Closure) {
            final Object body1 = $getCallSiteArray[0].call(((Reference<Object>)it2).get());
            ScriptBytecodeAdapter.setProperty(this.doc.get(), $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14(), body1, "delegate");
            return $getCallSiteArray[1].call(body1, this.doc.get());
        }
        if (((Reference<Object>)it2).get() instanceof Buildable) {
            return $getCallSiteArray[2].call(((Reference<Object>)it2).get(), this.doc.get());
        }
        return $getCallSiteArray[3].call($getCallSiteArray[4].call(this.out.get()), ((Reference<Object>)it2).get());
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object getOut() {
        $getCallSiteArray();
        return this.out.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[5].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure6_closure14.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure6_closure14.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure6_closure14.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingMarkupBuilder$_closure6_closure14.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingMarkupBuilder$_closure6_closure14.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14() {
        Class $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14;
        if (($class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14 = StreamingMarkupBuilder$_closure6_closure14.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14) == null) {
            $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14 = (StreamingMarkupBuilder$_closure6_closure14.$class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14 = class$("groovy.xml.StreamingMarkupBuilder$_closure6_closure14"));
        }
        return $class$groovy$xml$StreamingMarkupBuilder$_closure6_closure14;
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
