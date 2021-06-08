// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_bind_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> boundClosure;
    private Reference<Object> enc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingMarkupBuilder$_bind_closure7;
    private static /* synthetic */ Class $class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter;
    
    public StreamingMarkupBuilder$_bind_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> boundClosure, final Reference<Object> enc) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.boundClosure = boundClosure;
        this.enc = enc;
    }
    
    public Object doCall(final Object out) {
        final Object out2 = new Reference(out);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ((Reference<Object>)out2).set($getCallSiteArray[0].callConstructor($get$$class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter(), ((Reference<Object>)out2).get(), this.enc.get()));
        ScriptBytecodeAdapter.setProperty(((Reference<Object>)out2).get(), $get$$class$groovy$xml$StreamingMarkupBuilder$_bind_closure7(), this.boundClosure.get(), "trigger");
        return $getCallSiteArray[1].call(((Reference<Object>)out2).get());
    }
    
    public Object getBoundClosure() {
        $getCallSiteArray();
        return this.boundClosure.get();
    }
    
    public Object getEnc() {
        $getCallSiteArray();
        return this.enc.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_bind_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_bind_closure7.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_bind_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_bind_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingMarkupBuilder$_bind_closure7() {
        Class $class$groovy$xml$StreamingMarkupBuilder$_bind_closure7;
        if (($class$groovy$xml$StreamingMarkupBuilder$_bind_closure7 = StreamingMarkupBuilder$_bind_closure7.$class$groovy$xml$StreamingMarkupBuilder$_bind_closure7) == null) {
            $class$groovy$xml$StreamingMarkupBuilder$_bind_closure7 = (StreamingMarkupBuilder$_bind_closure7.$class$groovy$xml$StreamingMarkupBuilder$_bind_closure7 = class$("groovy.xml.StreamingMarkupBuilder$_bind_closure7"));
        }
        return $class$groovy$xml$StreamingMarkupBuilder$_bind_closure7;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter() {
        Class $class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter;
        if (($class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter = StreamingMarkupBuilder$_bind_closure7.$class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter) == null) {
            $class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter = (StreamingMarkupBuilder$_bind_closure7.$class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter = class$("groovy.xml.streamingmarkupsupport.StreamingMarkupWriter"));
        }
        return $class$groovy$xml$streamingmarkupsupport$StreamingMarkupWriter;
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
