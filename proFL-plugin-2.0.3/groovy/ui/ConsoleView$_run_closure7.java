// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.awt.dnd.DropTargetDropEvent;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConsoleView$_run_closure7 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$datatransfer$DataFlavor;
    private static /* synthetic */ Class $class$java$awt$dnd$DnDConstants;
    
    public ConsoleView$_run_closure7(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final DropTargetDropEvent evt) {
        final DropTargetDropEvent evt2 = (DropTargetDropEvent)new Reference(evt);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(((Reference<Object>)evt2).get(), $getCallSiteArray[1].callGetProperty($get$$class$java$awt$dnd$DnDConstants()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this)))) {
            return $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), $getCallSiteArray[6].call($getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty(((Reference<Object>)evt2).get()), $getCallSiteArray[9].callGetProperty($get$$class$java$awt$datatransfer$DataFlavor())), ConsoleView$_run_closure7.$const$0));
        }
        return null;
    }
    
    public Object call(final DropTargetDropEvent evt) {
        final DropTargetDropEvent evt2 = (DropTargetDropEvent)new Reference(evt);
        return $getCallSiteArray()[10].callCurrent(this, ((Reference<Object>)evt2).get());
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView$_run_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView$_run_closure7.$callSiteArray == null || ($createCallSiteArray = ConsoleView$_run_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView$_run_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$datatransfer$DataFlavor() {
        Class $class$java$awt$datatransfer$DataFlavor;
        if (($class$java$awt$datatransfer$DataFlavor = ConsoleView$_run_closure7.$class$java$awt$datatransfer$DataFlavor) == null) {
            $class$java$awt$datatransfer$DataFlavor = (ConsoleView$_run_closure7.$class$java$awt$datatransfer$DataFlavor = class$("java.awt.datatransfer.DataFlavor"));
        }
        return $class$java$awt$datatransfer$DataFlavor;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$dnd$DnDConstants() {
        Class $class$java$awt$dnd$DnDConstants;
        if (($class$java$awt$dnd$DnDConstants = ConsoleView$_run_closure7.$class$java$awt$dnd$DnDConstants) == null) {
            $class$java$awt$dnd$DnDConstants = (ConsoleView$_run_closure7.$class$java$awt$dnd$DnDConstants = class$("java.awt.dnd.DnDConstants"));
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
