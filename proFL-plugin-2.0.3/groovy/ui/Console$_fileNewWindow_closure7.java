// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.swing.SwingBuilder;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_fileNewWindow_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> swing;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    
    public Console$_fileNewWindow_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> swing) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.swing = swing;
    }
    
    public Object doCall(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value = this.swing.get();
        final Object value2 = ((Reference<Object>)i).get();
        final Object value3 = ((Reference<Object>)v2).get();
        callSite.call(value, value2, value3);
        return value3;
    }
    
    public Object call(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public SwingBuilder getSwing() {
        $getCallSiteArray();
        return (SwingBuilder)ScriptBytecodeAdapter.castToType(this.swing.get(), $get$$class$groovy$swing$SwingBuilder());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_fileNewWindow_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_fileNewWindow_closure7.$callSiteArray == null || ($createCallSiteArray = Console$_fileNewWindow_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_fileNewWindow_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = Console$_fileNewWindow_closure7.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (Console$_fileNewWindow_closure7.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
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
