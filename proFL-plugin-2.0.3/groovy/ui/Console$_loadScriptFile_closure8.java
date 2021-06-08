// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_loadScriptFile_closure8 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$Console$_loadScriptFile_closure8;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public Console$_loadScriptFile_closure8(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Boolean false = Boolean.FALSE;
        ScriptBytecodeAdapter.setProperty(false, $get$$class$groovy$ui$Console$_loadScriptFile_closure8(), $getCallSiteArray[0].callGroovyObjectGetProperty(this), "editable");
        return false;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_loadScriptFile_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_loadScriptFile_closure8.$callSiteArray == null || ($createCallSiteArray = Console$_loadScriptFile_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_loadScriptFile_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_loadScriptFile_closure8() {
        Class $class$groovy$ui$Console$_loadScriptFile_closure8;
        if (($class$groovy$ui$Console$_loadScriptFile_closure8 = Console$_loadScriptFile_closure8.$class$groovy$ui$Console$_loadScriptFile_closure8) == null) {
            $class$groovy$ui$Console$_loadScriptFile_closure8 = (Console$_loadScriptFile_closure8.$class$groovy$ui$Console$_loadScriptFile_closure8 = class$("groovy.ui.Console$_loadScriptFile_closure8"));
        }
        return $class$groovy$ui$Console$_loadScriptFile_closure8;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_loadScriptFile_closure8.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_loadScriptFile_closure8.$class$java$lang$Object = class$("java.lang.Object"));
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
