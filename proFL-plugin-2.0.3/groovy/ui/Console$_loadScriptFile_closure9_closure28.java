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

class Console$_loadScriptFile_closure9_closure28 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$Console$_loadScriptFile_closure9_closure28;
    
    public Console$_loadScriptFile_closure9_closure28(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Boolean true = Boolean.TRUE;
        ScriptBytecodeAdapter.setProperty(true, $get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure28(), $getCallSiteArray[0].callGroovyObjectGetProperty(this), "editable");
        return true;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure28(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_loadScriptFile_closure9_closure28.$callSiteArray == null || ($createCallSiteArray = Console$_loadScriptFile_closure9_closure28.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_loadScriptFile_closure9_closure28.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_loadScriptFile_closure9_closure28.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_loadScriptFile_closure9_closure28.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_loadScriptFile_closure9_closure28() {
        Class $class$groovy$ui$Console$_loadScriptFile_closure9_closure28;
        if (($class$groovy$ui$Console$_loadScriptFile_closure9_closure28 = Console$_loadScriptFile_closure9_closure28.$class$groovy$ui$Console$_loadScriptFile_closure9_closure28) == null) {
            $class$groovy$ui$Console$_loadScriptFile_closure9_closure28 = (Console$_loadScriptFile_closure9_closure28.$class$groovy$ui$Console$_loadScriptFile_closure9_closure28 = class$("groovy.ui.Console$_loadScriptFile_closure9_closure28"));
        }
        return $class$groovy$ui$Console$_loadScriptFile_closure9_closure28;
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
