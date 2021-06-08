// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AbstractStreamingBuilder$_closure2_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> pendingNamespaces;
    private Reference<Object> namespaceSpecificTags;
    private Reference<Object> namespaces;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AbstractStreamingBuilder$_closure2_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> pendingNamespaces, final Reference<Object> namespaceSpecificTags, final Reference<Object> namespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.pendingNamespaces = pendingNamespaces;
        this.namespaceSpecificTags = namespaceSpecificTags;
        this.namespaces = namespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)key2).get(), "")) {
            ((Reference<String>)key2).set(":");
        }
        ((Reference<Object>)value2).set($getCallSiteArray[0].call(((Reference<Object>)value2).get()));
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[1].call(this.namespaces.get(), ((Reference<Object>)key2).get()), ((Reference<Object>)value2).get())) {
            $getCallSiteArray[2].call(this.pendingNamespaces.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(this.namespaceSpecificTags.get(), ((Reference<Object>)value2).get()))) {
            final Object baseEntry = $getCallSiteArray[4].call(this.namespaceSpecificTags.get(), ":");
            final CallSite callSite = $getCallSiteArray[5];
            final Object value3 = this.namespaceSpecificTags.get();
            final Object value4 = ((Reference<Object>)value2).get();
            final Object call = $getCallSiteArray[6].call(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[7].call(baseEntry, AbstractStreamingBuilder$_closure2_closure5.$const$0), $getCallSiteArray[8].call(baseEntry, AbstractStreamingBuilder$_closure2_closure5.$const$1), ScriptBytecodeAdapter.createMap(new Object[0]) }));
            callSite.call(value3, value4, call);
            return call;
        }
        return null;
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[9].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getPendingNamespaces() {
        $getCallSiteArray();
        return this.pendingNamespaces.get();
    }
    
    public Object getNamespaceSpecificTags() {
        $getCallSiteArray();
        return this.namespaceSpecificTags.get();
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
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure2_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure2_closure5.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure2_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure2_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
