// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure2_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> contentHandler;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public StreamingSAXBuilder$_closure2_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> contentHandler) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.contentHandler = contentHandler;
    }
    
    public Object doCall(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)instruction2).get() instanceof Map) {
            final Object buf = new Reference($getCallSiteArray[0].callConstructor($get$$class$java$lang$StringBuffer()));
            $getCallSiteArray[1].call(((Reference<Object>)instruction2).get(), new StreamingSAXBuilder$_closure2_closure7_closure8(this, this.getThisObject(), (Reference<Object>)buf));
            return $getCallSiteArray[2].call(this.contentHandler.get(), ((Reference<Object>)target2).get(), $getCallSiteArray[3].call(((Reference<Object>)buf).get()));
        }
        return $getCallSiteArray[4].call(this.contentHandler.get(), ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get());
    }
    
    public Object call(final Object target, final Object instruction) {
        final Object target2 = new Reference(target);
        final Object instruction2 = new Reference(instruction);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)target2).get(), ((Reference<Object>)instruction2).get());
    }
    
    public Object getContentHandler() {
        $getCallSiteArray();
        return this.contentHandler.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure2_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure2_closure7.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure2_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure2_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = StreamingSAXBuilder$_closure2_closure7.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (StreamingSAXBuilder$_closure2_closure7.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
