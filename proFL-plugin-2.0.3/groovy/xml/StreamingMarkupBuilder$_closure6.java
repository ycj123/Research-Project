// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure6 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingMarkupBuilder$_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object out2 = new Reference(out);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), "")) {
            if (!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get())) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()))) ? Boolean.FALSE : Boolean.TRUE)) {
                throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)prefix2).get() }, new String[] { "Namespace prefix: ", " is not bound to a URI" }));
            }
            if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), ":")) {
                ((Reference<Object>)tag2).set($getCallSiteArray[3].call($getCallSiteArray[4].call(((Reference<Object>)prefix2).get(), ":"), ((Reference<Object>)tag2).get()));
            }
        }
        ((Reference<Object>)out2).set($getCallSiteArray[5].call($getCallSiteArray[6].call(((Reference<Object>)out2).get()), new GStringImpl(new Object[] { ((Reference<Object>)tag2).get() }, new String[] { "<", "" })));
        $getCallSiteArray[7].call(((Reference<Object>)attrs2).get(), new StreamingMarkupBuilder$_closure6_closure12(this, this.getThisObject(), (Reference<Object>)pendingNamespaces2, (Reference<Object>)doc2, (Reference<Object>)namespaces2, (Reference<Object>)out2));
        final Object hiddenNamespaces = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        $getCallSiteArray[8].call(((Reference<Object>)pendingNamespaces2).get(), new StreamingMarkupBuilder$_closure6_closure13(this, this.getThisObject(), (Reference<Object>)doc2, (Reference<Object>)namespaces2, (Reference<Object>)hiddenNamespaces, (Reference<Object>)out2));
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)body2).get(), null)) {
            $getCallSiteArray[9].call(((Reference<Object>)out2).get(), "/>");
        }
        else {
            $getCallSiteArray[10].call(((Reference<Object>)out2).get(), ">");
            $getCallSiteArray[11].call($getCallSiteArray[12].callGroovyObjectGetProperty(this), $getCallSiteArray[13].call(((Reference<Object>)pendingNamespaces2).get()));
            $getCallSiteArray[14].call(((Reference<Object>)pendingNamespaces2).get());
            $getCallSiteArray[15].call(((Reference<Object>)body2).get(), new StreamingMarkupBuilder$_closure6_closure14(this, this.getThisObject(), (Reference<Object>)doc2, (Reference<Object>)out2));
            $getCallSiteArray[16].call(((Reference<Object>)pendingNamespaces2).get());
            $getCallSiteArray[17].call(((Reference<Object>)pendingNamespaces2).get(), $getCallSiteArray[18].call($getCallSiteArray[19].callGroovyObjectGetProperty(this)));
            $getCallSiteArray[20].call(((Reference<Object>)out2).get(), new GStringImpl(new Object[] { ((Reference<Object>)tag2).get() }, new String[] { "</", ">" }));
        }
        return $getCallSiteArray[21].call(((Reference<Object>)hiddenNamespaces).get(), new StreamingMarkupBuilder$_closure6_closure15(this, this.getThisObject(), (Reference<Object>)namespaces2));
    }
    
    public Object call(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object out2 = new Reference(out);
        return $getCallSiteArray()[22].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)tag2).get(), ((Reference<Object>)doc2).get(), ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), namespaceSpecificTags, ((Reference<Object>)prefix2).get(), ((Reference<Object>)attrs2).get(), ((Reference<Object>)body2).get(), ((Reference<Object>)out2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure6.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingMarkupBuilder$_closure6.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingMarkupBuilder$_closure6.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
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
