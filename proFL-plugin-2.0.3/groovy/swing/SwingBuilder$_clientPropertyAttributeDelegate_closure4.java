// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class SwingBuilder$_clientPropertyAttributeDelegate_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private Reference<Object> attributes;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public SwingBuilder$_clientPropertyAttributeDelegate_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> node, final Reference<Object> attributes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
        this.attributes = attributes;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(this.attributes.get(), ((Reference<Object>)key2).get());
        return $getCallSiteArray[1].call(this.node.get(), $getCallSiteArray[2].call(((Reference<Object>)key2).get(), "clientProperty"), ((Reference<Object>)value2).get());
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[3].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getNode() {
        $getCallSiteArray();
        return this.node.get();
    }
    
    public Object getAttributes() {
        $getCallSiteArray();
        return this.attributes.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$SwingBuilder$_clientPropertyAttributeDelegate_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingBuilder$_clientPropertyAttributeDelegate_closure4.$callSiteArray == null || ($createCallSiteArray = SwingBuilder$_clientPropertyAttributeDelegate_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingBuilder$_clientPropertyAttributeDelegate_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
