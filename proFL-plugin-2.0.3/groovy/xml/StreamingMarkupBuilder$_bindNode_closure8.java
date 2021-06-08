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

class StreamingMarkupBuilder$_bindNode_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public StreamingMarkupBuilder$_bindNode_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> node) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), this.node.get());
    }
    
    public Object getNode() {
        $getCallSiteArray();
        return this.node.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_bindNode_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_bindNode_closure8.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_bindNode_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_bindNode_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingMarkupBuilder$_bindNode_closure8.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingMarkupBuilder$_bindNode_closure8.$class$java$lang$Object = class$("java.lang.Object"));
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
