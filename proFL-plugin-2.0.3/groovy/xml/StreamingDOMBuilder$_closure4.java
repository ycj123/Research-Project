// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Buildable;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingDOMBuilder$_closure4;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingDOMBuilder$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object attributes = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final Object nsAttributes = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final Object defaultNamespace = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[2].call(((Reference<Object>)attrs2).get(), new StreamingDOMBuilder$_closure4_closure9(this, this.getThisObject(), (Reference<Object>)nsAttributes, (Reference<Object>)attributes, (Reference<Object>)namespaces2));
        final Object hiddenNamespaces = new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        $getCallSiteArray[3].call(((Reference<Object>)pendingNamespaces2).get(), new StreamingDOMBuilder$_closure4_closure10(this, this.getThisObject(), (Reference<Object>)nsAttributes, (Reference<Object>)defaultNamespace, (Reference<Object>)namespaces2, (Reference<Object>)hiddenNamespaces));
        final Object uri = new Reference(((Reference<Object>)defaultNamespace).get());
        final Object qualifiedName = new Reference(((Reference<Object>)tag2).get());
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), "")) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get()))) {
                ((Reference<Object>)uri).set($getCallSiteArray[5].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get()));
            }
            else {
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()))) {
                    throw (Throwable)$getCallSiteArray[8].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)prefix2).get() }, new String[] { "Namespace prefix: ", " is not bound to a URI" }));
                }
                ((Reference<Object>)uri).set($getCallSiteArray[7].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()));
            }
            if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)prefix2).get(), ":")) {
                ((Reference<Object>)qualifiedName).set($getCallSiteArray[9].call($getCallSiteArray[10].call(((Reference<Object>)prefix2).get(), ":"), ((Reference<Object>)tag2).get()));
            }
        }
        final Object element = new Reference($getCallSiteArray[11].call($getCallSiteArray[12].callGetProperty(((Reference<Object>)dom2).get()), ((Reference<Object>)uri).get(), ((Reference<Object>)qualifiedName).get()));
        $getCallSiteArray[13].call(((Reference<Object>)nsAttributes).get(), new StreamingDOMBuilder$_closure4_closure11(this, this.getThisObject(), (Reference<Object>)element));
        $getCallSiteArray[14].call(((Reference<Object>)attributes).get(), new StreamingDOMBuilder$_closure4_closure12(this, this.getThisObject(), (Reference<Object>)element));
        $getCallSiteArray[15].call($getCallSiteArray[16].callGetProperty(((Reference<Object>)dom2).get()), ((Reference<Object>)element).get());
        ScriptBytecodeAdapter.setProperty(((Reference<Object>)element).get(), $get$$class$groovy$xml$StreamingDOMBuilder$_closure4(), ((Reference<Object>)dom2).get(), "element");
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)body2).get(), null)) {
            $getCallSiteArray[17].call($getCallSiteArray[18].callGroovyObjectGetProperty(this), ((Reference<Object>)defaultNamespace).get());
            $getCallSiteArray[19].call($getCallSiteArray[20].callGroovyObjectGetProperty(this), $getCallSiteArray[21].call(((Reference<Object>)pendingNamespaces2).get()));
            $getCallSiteArray[22].call(((Reference<Object>)pendingNamespaces2).get());
            if (((Reference<Object>)body2).get() instanceof Closure) {
                final Object body3 = $getCallSiteArray[23].call(((Reference<Object>)body2).get());
                ScriptBytecodeAdapter.setProperty(((Reference<Object>)doc2).get(), $get$$class$groovy$xml$StreamingDOMBuilder$_closure4(), body3, "delegate");
                $getCallSiteArray[24].call(body3, ((Reference<Object>)doc2).get());
            }
            else if (((Reference<Object>)body2).get() instanceof Buildable) {
                $getCallSiteArray[25].call(((Reference<Object>)body2).get(), ((Reference<Object>)doc2).get());
            }
            else {
                $getCallSiteArray[26].call(((Reference<Object>)body2).get(), new StreamingDOMBuilder$_closure4_closure13(this, this.getThisObject(), (Reference<Object>)dom2, (Reference<Object>)doc2));
            }
            $getCallSiteArray[27].call(((Reference<Object>)pendingNamespaces2).get());
            $getCallSiteArray[28].call(((Reference<Object>)pendingNamespaces2).get(), $getCallSiteArray[29].call($getCallSiteArray[30].callGroovyObjectGetProperty(this)));
            $getCallSiteArray[31].call($getCallSiteArray[32].callGroovyObjectGetProperty(this));
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty(((Reference<Object>)dom2).get())), $get$$class$groovy$xml$StreamingDOMBuilder$_closure4(), ((Reference<Object>)dom2).get(), "element");
        return $getCallSiteArray[35].call(((Reference<Object>)hiddenNamespaces).get(), new StreamingDOMBuilder$_closure4_closure14(this, this.getThisObject(), (Reference<Object>)namespaces2));
    }
    
    public Object call(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object tag2 = new Reference(tag);
        final Object doc2 = new Reference(doc);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final Object attrs2 = new Reference(attrs);
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        return $getCallSiteArray()[36].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)tag2).get(), ((Reference<Object>)doc2).get(), ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), namespaceSpecificTags, ((Reference<Object>)prefix2).get(), ((Reference<Object>)attrs2).get(), ((Reference<Object>)body2).get(), ((Reference<Object>)dom2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[37];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure4.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingDOMBuilder$_closure4() {
        Class $class$groovy$xml$StreamingDOMBuilder$_closure4;
        if (($class$groovy$xml$StreamingDOMBuilder$_closure4 = StreamingDOMBuilder$_closure4.$class$groovy$xml$StreamingDOMBuilder$_closure4) == null) {
            $class$groovy$xml$StreamingDOMBuilder$_closure4 = (StreamingDOMBuilder$_closure4.$class$groovy$xml$StreamingDOMBuilder$_closure4 = class$("groovy.xml.StreamingDOMBuilder$_closure4"));
        }
        return $class$groovy$xml$StreamingDOMBuilder$_closure4;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingDOMBuilder$_closure4.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingDOMBuilder$_closure4.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
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
