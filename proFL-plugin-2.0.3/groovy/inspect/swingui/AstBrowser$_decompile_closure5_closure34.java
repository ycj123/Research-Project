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

class AstBrowser$_decompile_closure5_closure34 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Cursor;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34;
    
    public AstBrowser$_decompile_closure5_closure34(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty(this.result.get(), $get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34(), $getCallSiteArray[0].callGroovyObjectGetProperty(this), "text");
        $getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), AstBrowser$_decompile_closure5_closure34.$const$0);
        return $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), $getCallSiteArray[5].callGetProperty($get$$class$java$awt$Cursor()));
    }
    
    public String getResult() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.result.get(), $get$$class$java$lang$String());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_decompile_closure5_closure34.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_decompile_closure5_closure34.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_decompile_closure5_closure34.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_decompile_closure5_closure34.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_decompile_closure5_closure34.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Cursor() {
        Class $class$java$awt$Cursor;
        if (($class$java$awt$Cursor = AstBrowser$_decompile_closure5_closure34.$class$java$awt$Cursor) == null) {
            $class$java$awt$Cursor = (AstBrowser$_decompile_closure5_closure34.$class$java$awt$Cursor = class$("java.awt.Cursor"));
        }
        return $class$java$awt$Cursor;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstBrowser$_decompile_closure5_closure34.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstBrowser$_decompile_closure5_closure34.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34() {
        Class $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34;
        if (($class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34 = AstBrowser$_decompile_closure5_closure34.$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34) == null) {
            $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34 = (AstBrowser$_decompile_closure5_closure34.$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34 = class$("groovy.inspect.swingui.AstBrowser$_decompile_closure5_closure34"));
        }
        return $class$groovy$inspect$swingui$AstBrowser$_decompile_closure5_closure34;
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
