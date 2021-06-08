// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_processBody_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> contentHandler;
    private Reference<Object> doc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public StreamingSAXBuilder$_processBody_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> contentHandler, final Reference<Object> doc) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.contentHandler = contentHandler;
        this.doc = doc;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].callCurrent(this, ((Reference<Object>)it2).get(), this.doc.get(), this.contentHandler.get());
    }
    
    public Object getContentHandler() {
        $getCallSiteArray();
        return this.contentHandler.get();
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_processBody_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_processBody_closure5.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_processBody_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_processBody_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StreamingSAXBuilder$_processBody_closure5.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StreamingSAXBuilder$_processBody_closure5.$class$java$lang$Object = class$("java.lang.Object"));
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
