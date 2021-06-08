// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_reportException_closure11 extends Closure implements GeneratedClosure
{
    private Reference<Object> t;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Throwable;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
    
    public Console$_reportException_closure11(final Object _outerInstance, final Object _thisObject, final Reference<Object> t) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.t = t;
    }
    
    public Object doCall(final Object pw) {
        final Object pw2 = new Reference(pw);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].call($get$$class$org$codehaus$groovy$runtime$StackTraceUtils(), this.t.get()), ((Reference<Object>)pw2).get());
    }
    
    public Throwable getT() {
        $getCallSiteArray();
        return (Throwable)ScriptBytecodeAdapter.castToType(this.t.get(), $get$$class$java$lang$Throwable());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_reportException_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_reportException_closure11.$callSiteArray == null || ($createCallSiteArray = Console$_reportException_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_reportException_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Throwable() {
        Class $class$java$lang$Throwable;
        if (($class$java$lang$Throwable = Console$_reportException_closure11.$class$java$lang$Throwable) == null) {
            $class$java$lang$Throwable = (Console$_reportException_closure11.$class$java$lang$Throwable = class$("java.lang.Throwable"));
        }
        return $class$java$lang$Throwable;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$StackTraceUtils() {
        Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
        if (($class$org$codehaus$groovy$runtime$StackTraceUtils = Console$_reportException_closure11.$class$org$codehaus$groovy$runtime$StackTraceUtils) == null) {
            $class$org$codehaus$groovy$runtime$StackTraceUtils = (Console$_reportException_closure11.$class$org$codehaus$groovy$runtime$StackTraceUtils = class$("org.codehaus.groovy.runtime.StackTraceUtils"));
        }
        return $class$org$codehaus$groovy$runtime$StackTraceUtils;
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
