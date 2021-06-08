// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure4_closure32 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    
    public AstBrowser$_run_closure4_closure32(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object map, final Object info) {
        final Object map2 = new Reference(map);
        final Object info2 = new Reference(info);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(((Reference<Object>)map2).get(), $getCallSiteArray[1].call(((Reference<Object>)info2).get(), AstBrowser$_run_closure4_closure32.$const$0), $getCallSiteArray[2].call($get$$class$java$lang$Integer(), $getCallSiteArray[3].call(((Reference<Object>)info2).get(), AstBrowser$_run_closure4_closure32.$const$1)));
        return ((Reference<Object>)map2).get();
    }
    
    public Object call(final Object map, final Object info) {
        final Object map2 = new Reference(map);
        final Object info2 = new Reference(info);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)map2).get(), ((Reference<Object>)info2).get());
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure4_closure32(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure4_closure32.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure4_closure32.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure4_closure32.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstBrowser$_run_closure4_closure32.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstBrowser$_run_closure4_closure32.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
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
