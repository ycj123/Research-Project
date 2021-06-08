// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Dimension;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicContentPane$_run_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$Dimension;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane$_run_closure1;
    
    public BasicContentPane$_run_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[0].callCurrent(this), $get$$class$groovy$ui$view$BasicContentPane$_run_closure1(), this, "blank");
        final Dimension messageArgument = (Dimension)ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[] { BasicContentPane$_run_closure1.$const$0, BasicContentPane$_run_closure1.$const$0 }), $get$$class$java$awt$Dimension());
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$ui$view$BasicContentPane$_run_closure1(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), "preferredSize");
        return messageArgument;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane$_run_closure1.$callSiteArray == null || ($createCallSiteArray = BasicContentPane$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dimension() {
        Class $class$java$awt$Dimension;
        if (($class$java$awt$Dimension = BasicContentPane$_run_closure1.$class$java$awt$Dimension) == null) {
            $class$java$awt$Dimension = (BasicContentPane$_run_closure1.$class$java$awt$Dimension = class$("java.awt.Dimension"));
        }
        return $class$java$awt$Dimension;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicContentPane$_run_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicContentPane$_run_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane$_run_closure1() {
        Class $class$groovy$ui$view$BasicContentPane$_run_closure1;
        if (($class$groovy$ui$view$BasicContentPane$_run_closure1 = BasicContentPane$_run_closure1.$class$groovy$ui$view$BasicContentPane$_run_closure1) == null) {
            $class$groovy$ui$view$BasicContentPane$_run_closure1 = (BasicContentPane$_run_closure1.$class$groovy$ui$view$BasicContentPane$_run_closure1 = class$("groovy.ui.view.BasicContentPane$_run_closure1"));
        }
        return $class$groovy$ui$view$BasicContentPane$_run_closure1;
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
