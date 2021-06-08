// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AbstractStreamingBuilder$_closure3_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> pendingNamespaces;
    private Reference<Object> namespaceSpecificTags;
    private Reference<Object> namespaces;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public AbstractStreamingBuilder$_closure3_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> pendingNamespaces, final Reference<Object> namespaceSpecificTags, final Reference<Object> namespaces) {
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
        if (((Reference<Object>)value2).get() instanceof Map) {
            final Object info = new Reference(null);
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(this.namespaces.get(), ((Reference<Object>)key2).get()))) {
                ((Reference<Object>)info).set($getCallSiteArray[1].call(this.namespaceSpecificTags.get(), $getCallSiteArray[2].call(this.namespaces.get(), ((Reference<Object>)key2).get())));
            }
            else {
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(this.pendingNamespaces.get(), ((Reference<Object>)key2).get()))) {
                    throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { "namespace prefix ", " has not been declared" }));
                }
                ((Reference<Object>)info).set($getCallSiteArray[4].call(this.namespaceSpecificTags.get(), $getCallSiteArray[5].call(this.pendingNamespaces.get(), ((Reference<Object>)key2).get())));
            }
            return $getCallSiteArray[7].call(((Reference<Object>)value2).get(), new AbstractStreamingBuilder$_closure3_closure6_closure7(this, this.getThisObject(), (Reference<Object>)info));
        }
        final Object info = $getCallSiteArray[8].call(this.namespaceSpecificTags.get(), ":");
        final CallSite callSite = $getCallSiteArray[9];
        final Object call = $getCallSiteArray[10].call(info, AbstractStreamingBuilder$_closure3_closure6.$const$0);
        final Object value3 = ((Reference<Object>)key2).get();
        final Object call2 = $getCallSiteArray[11].call($getCallSiteArray[12].call(info, AbstractStreamingBuilder$_closure3_closure6.$const$1), ((Reference<Object>)value2).get());
        callSite.call(call, value3, call2);
        return call2;
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[13].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
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
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure3_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure3_closure6.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure3_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure3_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = AbstractStreamingBuilder$_closure3_closure6.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (AbstractStreamingBuilder$_closure3_closure6.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
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
