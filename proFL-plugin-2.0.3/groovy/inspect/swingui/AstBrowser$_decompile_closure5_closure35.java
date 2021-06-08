// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_decompile_closure5_closure35 extends Closure implements GeneratedClosure
{
    private Reference<Object> t;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Cursor;
    private static /* synthetic */ Class $class$java$lang$Throwable;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35;
    
    public AstBrowser$_decompile_closure5_closure35(final Object _outerInstance, final Object _thisObject, final Reference<Object> t) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.t = t;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[0].call(this.t.get()), $get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), "text");
        $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), AstBrowser$_decompile_closure5_closure35.$const$0);
        return $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), $getCallSiteArray[6].callGetProperty($get$$class$java$awt$Cursor()));
    }
    
    public Throwable getT() {
        $getCallSiteArray();
        return (Throwable)ScriptBytecodeAdapter.castToType(this.t.get(), $get$$class$java$lang$Throwable());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_decompile_closure5_closure35.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_decompile_closure5_closure35.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_decompile_closure5_closure35.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_decompile_closure5_closure35.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_decompile_closure5_closure35.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Cursor() {
        Class $class$java$awt$Cursor;
        if (($class$java$awt$Cursor = AstBrowser$_decompile_closure5_closure35.$class$java$awt$Cursor) == null) {
            $class$java$awt$Cursor = (AstBrowser$_decompile_closure5_closure35.$class$java$awt$Cursor = class$("java.awt.Cursor"));
        }
        return $class$java$awt$Cursor;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Throwable() {
        Class $class$java$lang$Throwable;
        if (($class$java$lang$Throwable = AstBrowser$_decompile_closure5_closure35.$class$java$lang$Throwable) == null) {
            $class$java$lang$Throwable = (AstBrowser$_decompile_closure5_closure35.$class$java$lang$Throwable = class$("java.lang.Throwable"));
        }
        return $class$java$lang$Throwable;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35() {
        Class $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35;
        if (($class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35 = AstBrowser$_decompile_closure5_closure35.$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35) == null) {
            $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35 = (AstBrowser$_decompile_closure5_closure35.$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35 = class$("groovy.inspect.swingui.AstBrowser$_decompile_closure5_closure35"));
        }
        return $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure35;
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
