// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure4_closure12 extends Closure implements GeneratedClosure
{
    private Reference<Object> element;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public StreamingDOMBuilder$_closure4_closure12(final Object _outerInstance, final Object _thisObject, final Reference<Object> element) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.element = element;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.element.get(), $getCallSiteArray[1].call(((Reference<Object>)it2).get(), StreamingDOMBuilder$_closure4_closure12.$const$0), $getCallSiteArray[2].call(((Reference<Object>)it2).get(), StreamingDOMBuilder$_closure4_closure12.$const$1));
    }
    
    public Object getElement() {
        $getCallSiteArray();
        return this.element.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure12(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure4_closure12.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure4_closure12.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure4_closure12.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingDOMBuilder$_closure4_closure12.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingDOMBuilder$_closure4_closure12.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
