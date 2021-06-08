// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_buildOutputArea_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> prefs;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Color;
    
    public BasicContentPane$_buildOutputArea_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> prefs) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.prefs = prefs;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callCurrent = $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "editable", Boolean.FALSE, "name", "outputArea", "contentType", "text/html", "background", $getCallSiteArray[1].callConstructor($get$$class$java$awt$Color(), BasicContentPane$_buildOutputArea_closure6.$const$0, BasicContentPane$_buildOutputArea_closure6.$const$0, BasicContentPane$_buildOutputArea_closure6.$const$1), "font", $getCallSiteArray[2].callConstructor($get$$class$java$awt$Font(), "Monospaced", $getCallSiteArray[3].callGetProperty($get$$class$java$awt$Font()), $getCallSiteArray[4].call(this.prefs.get(), "fontSize", BasicContentPane$_buildOutputArea_closure6.$const$2)), "border", $getCallSiteArray[5].callCurrent(this, BasicContentPane$_buildOutputArea_closure6.$const$3) }));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6(), this, "outputArea");
        return callCurrent;
    }
    
    public Object getPrefs() {
        $getCallSiteArray();
        return this.prefs.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$3 = 4;
        $const$2 = 12;
        $const$1 = 218;
        $const$0 = 255;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_buildOutputArea_closure6.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_buildOutputArea_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_buildOutputArea_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6() {
        Class $class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6;
        if (($class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6 = BasicContentPane$_buildOutputArea_closure6.$class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6) == null) {
            $class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6 = (BasicContentPane$_buildOutputArea_closure6.$class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6 = class$("groovy.ui.view.BasicContentPane$_buildOutputArea_closure6"));
        }
        return $class$groovy$ui$view$BasicContentPane$_buildOutputArea_closure6;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = BasicContentPane$_buildOutputArea_closure6.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (BasicContentPane$_buildOutputArea_closure6.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicContentPane$_buildOutputArea_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicContentPane$_buildOutputArea_closure6.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Color() {
        Class $class$java$awt$Color;
        if (($class$java$awt$Color = BasicContentPane$_buildOutputArea_closure6.$class$java$awt$Color) == null) {
            $class$java$awt$Color = (BasicContentPane$_buildOutputArea_closure6.$class$java$awt$Color = class$("java.awt.Color"));
        }
        return $class$java$awt$Color;
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
