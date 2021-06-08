// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure2_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> dom;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public StreamingDOMBuilder$_closure2_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> dom) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.dom = dom;
    }
    
    public Object doCall(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object pi = new Reference(null);
        if (((Reference<Object>)instruction2).get() instanceof Map) {
            final Object buf = new Reference($getCallSiteArray[0].callConstructor($get$$class$java$lang$StringBuffer()));
            $getCallSiteArray[1].call(((Reference<Object>)instruction2).get(), new StreamingDOMBuilder$_closure2_closure6_closure7(this, this.getThisObject(), (Reference<Object>)buf));
            ((Reference<Object>)pi).set($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(this.dom.get()), ((Reference<Object>)target2).get(), $getCallSiteArray[4].call(((Reference<Object>)buf).get())));
        }
        else {
            ((Reference<Object>)pi).set($getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(this.dom.get()), ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get()));
        }
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)pi).get(), null)) {
            return $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty(this.dom.get()), ((Reference<Object>)pi).get());
        }
        return null;
    }
    
    public Object call(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        return $getCallSiteArray()[9].callCurrent(this, ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get());
    }
    
    public Object getDom() {
        $getCallSiteArray();
        return this.dom.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure2_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure2_closure6.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure2_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure2_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = StreamingDOMBuilder$_closure2_closure6.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (StreamingDOMBuilder$_closure2_closure6.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
