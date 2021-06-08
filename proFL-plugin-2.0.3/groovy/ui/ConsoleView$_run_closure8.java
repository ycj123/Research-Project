// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConsoleView$_run_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> dtListener;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$dnd$DropTarget;
    private static /* synthetic */ Class $class$java$awt$dnd$DnDConstants;
    
    public ConsoleView$_run_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> dtListener) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.dtListener = dtListener;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$java$awt$dnd$DropTarget(), ((Reference<Object>)it2).get(), $getCallSiteArray[1].callGetProperty($get$$class$java$awt$dnd$DnDConstants()), this.dtListener.get());
    }
    
    public Object getDtListener() {
        $getCallSiteArray();
        return this.dtListener.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView$_run_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView$_run_closure8.$callSiteArray == null || ($createCallSiteArray = ConsoleView$_run_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView$_run_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ConsoleView$_run_closure8.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ConsoleView$_run_closure8.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$dnd$DropTarget() {
        Class $class$java$awt$dnd$DropTarget;
        if (($class$java$awt$dnd$DropTarget = ConsoleView$_run_closure8.$class$java$awt$dnd$DropTarget) == null) {
            $class$java$awt$dnd$DropTarget = (ConsoleView$_run_closure8.$class$java$awt$dnd$DropTarget = class$("java.awt.dnd.DropTarget"));
        }
        return $class$java$awt$dnd$DropTarget;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$dnd$DnDConstants() {
        Class $class$java$awt$dnd$DnDConstants;
        if (($class$java$awt$dnd$DnDConstants = ConsoleView$_run_closure8.$class$java$awt$dnd$DnDConstants) == null) {
            $class$java$awt$dnd$DnDConstants = (ConsoleView$_run_closure8.$class$java$awt$dnd$DnDConstants = class$("java.awt.dnd.DnDConstants"));
        }
        return $class$java$awt$dnd$DnDConstants;
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
