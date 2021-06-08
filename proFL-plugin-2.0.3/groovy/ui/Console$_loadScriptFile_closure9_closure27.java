// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_loadScriptFile_closure9_closure27 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$Console$_loadScriptFile_closure9_closure27;
    
    public Console$_loadScriptFile_closure9_closure27(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this);
        $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)), Console$_loadScriptFile_closure9_closure27.$const$0, $getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this))));
        $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callGroovyObjectGetProperty(this)), Console$_loadScriptFile_closure9_closure27.$const$0, $getCallSiteArray[10].callGroovyObjectGetProperty(this), null);
        $getCallSiteArray[11].callCurrent(this, Boolean.FALSE);
        final Integer $const$0 = Console$_loadScriptFile_closure9_closure27.$const$0;
        ScriptBytecodeAdapter.setProperty($const$0, $get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure27(), $getCallSiteArray[12].callGroovyObjectGetProperty(this), "caretPosition");
        return $const$0;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[13].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure27(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_loadScriptFile_closure9_closure27.$callSiteArray == null || ($createCallSiteArray = Console$_loadScriptFile_closure9_closure27.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_loadScriptFile_closure9_closure27.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_loadScriptFile_closure9_closure27.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_loadScriptFile_closure9_closure27.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure27() {
        Class $class$groovy$ui$Console$_loadScriptFile_closure9_closure27;
        if (($class$groovy$ui$Console$_loadScriptFile_closure9_closure27 = Console$_loadScriptFile_closure9_closure27.$class$groovy$ui$Console$_loadScriptFile_closure9_closure27) == null) {
            $class$groovy$ui$Console$_loadScriptFile_closure9_closure27 = (Console$_loadScriptFile_closure9_closure27.$class$groovy$ui$Console$_loadScriptFile_closure9_closure27 = class$("groovy.ui.Console$_loadScriptFile_closure9_closure27"));
        }
        return $class$groovy$ui$Console$_loadScriptFile_closure9_closure27;
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
