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

class BasicContentPane$_run_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> prefs;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleTextEditor;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane$_run_closure2;
    
    public BasicContentPane$_run_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> prefs) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.prefs = prefs;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "border", $getCallSiteArray[1].callCurrent(this, BasicContentPane$_run_closure2.$const$0) }), $getCallSiteArray[2].callConstructor($get$$class$groovy$ui$ConsoleTextEditor())), $get$$class$groovy$ui$view$BasicContentPane$_run_closure2(), this, "inputEditor");
        return $getCallSiteArray[3].callCurrent(this, this.prefs.get());
    }
    
    public Object getPrefs() {
        $getCallSiteArray();
        return this.prefs.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure2.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicContentPane$_run_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicContentPane$_run_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleTextEditor() {
        Class $class$groovy$ui$ConsoleTextEditor;
        if (($class$groovy$ui$ConsoleTextEditor = BasicContentPane$_run_closure2.$class$groovy$ui$ConsoleTextEditor) == null) {
            $class$groovy$ui$ConsoleTextEditor = (BasicContentPane$_run_closure2.$class$groovy$ui$ConsoleTextEditor = class$("groovy.ui.ConsoleTextEditor"));
        }
        return $class$groovy$ui$ConsoleTextEditor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane$_run_closure2() {
        Class $class$groovy$ui$view$BasicContentPane$_run_closure2;
        if (($class$groovy$ui$view$BasicContentPane$_run_closure2 = BasicContentPane$_run_closure2.$class$groovy$ui$view$BasicContentPane$_run_closure2) == null) {
            $class$groovy$ui$view$BasicContentPane$_run_closure2 = (BasicContentPane$_run_closure2.$class$groovy$ui$view$BasicContentPane$_run_closure2 = class$("groovy.ui.view.BasicContentPane$_run_closure2"));
        }
        return $class$groovy$ui$view$BasicContentPane$_run_closure2;
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
