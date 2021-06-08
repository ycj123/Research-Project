// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$xml$sax$helpers$AttributesImpl;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingSAXBuilder$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object attributes = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$xml$sax$helpers$AttributesImpl()));
        $getCallSiteArray[1].call(((Reference<Object>)attrs2).get(), new StreamingSAXBuilder$_closure4_closure9(this, this.getThisObject(), (Reference<Object>)attributes, (Reference<Object>)namespaces2));
        final Object hiddenNamespaces = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        $getCallSiteArray[2].call(((Reference<Object>)pendingNamespaces2).get(), new StreamingSAXBuilder$_closure4_closure10(this, this.getThisObject(), (Reference<Object>)contentHandler2, (Reference<Object>)attributes, (Reference<Object>)namespaces2, (Reference<Object>)hiddenNamespaces));
        final Object uri = new Reference("");
        final Object qualifiedName = new Reference(((Reference<Object>)tag2).get());
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), "")) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get()))) {
                ((Reference<Object>)uri).set($getCallSiteArray[4].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get()));
            }
            else {
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()))) {
                    throw (Throwable)$getCallSiteArray[7].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)prefix2).get() }, new String[] { "Namespace prefix: ", " is not bound to a URI" }));
                }
                ((Reference<Object>)uri).set($getCallSiteArray[6].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()));
            }
            if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), ":")) {
                ((Reference<Object>)qualifiedName).set($getCallSiteArray[8].call($getCallSiteArray[9].call(((Reference<Object>)prefix2).get(), ":"), ((Reference<Object>)tag2).get()));
            }
        }
        $getCallSiteArray[10].call(((Reference<Object>)contentHandler2).get(), ((Reference<Object>)uri).get(), ((Reference<Object>)tag2).get(), ((Reference<Object>)qualifiedName).get(), ((Reference<Object>)attributes).get());
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)body2).get(), null)) {
            $getCallSiteArray[11].call($getCallSiteArray[12].callGroovyObjectGetProperty(this), $getCallSiteArray[13].call(((Reference<Object>)pendingNamespaces2).get()));
            $getCallSiteArray[14].call(((Reference<Object>)pendingNamespaces2).get());
            $getCallSiteArray[15].callCurrent(this, ((Reference<Object>)body2).get(), ((Reference<Object>)doc2).get(), ((Reference<Object>)contentHandler2).get());
            $getCallSiteArray[16].call(((Reference<Object>)pendingNamespaces2).get());
            $getCallSiteArray[17].call(((Reference<Object>)pendingNamespaces2).get(), $getCallSiteArray[18].call($getCallSiteArray[19].callGroovyObjectGetProperty(this)));
        }
        $getCallSiteArray[20].call(((Reference<Object>)contentHandler2).get(), ((Reference<Object>)uri).get(), ((Reference<Object>)tag2).get(), ((Reference<Object>)qualifiedName).get());
        return $getCallSiteArray[21].call(((Reference<Object>)hiddenNamespaces).get(), new StreamingSAXBuilder$_closure4_closure11(this, this.getThisObject(), (Reference<Object>)contentHandler2, (Reference<Object>)namespaces2));
    }
    
    public Object call(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        return $getCallSiteArray()[22].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)tag2).get(), ((Reference<Object>)doc2).get(), ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), namespaceSpecificTags, ((Reference<Object>)prefix2).get(), ((Reference<Object>)attrs2).get(), ((Reference<Object>)body2).get(), ((Reference<Object>)contentHandler2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure4.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$xml$sax$helpers$AttributesImpl() {
        Class $class$org$xml$sax$helpers$AttributesImpl;
        if (($class$org$xml$sax$helpers$AttributesImpl = StreamingSAXBuilder$_closure4.$class$org$xml$sax$helpers$AttributesImpl) == null) {
            $class$org$xml$sax$helpers$AttributesImpl = (StreamingSAXBuilder$_closure4.$class$org$xml$sax$helpers$AttributesImpl = class$("org.xml.sax.helpers.AttributesImpl"));
        }
        return $class$org$xml$sax$helpers$AttributesImpl;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingSAXBuilder$_closure4.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingSAXBuilder$_closure4.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
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
