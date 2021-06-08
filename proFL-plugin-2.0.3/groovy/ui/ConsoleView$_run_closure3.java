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
import java.awt.dnd.DropTargetDragEvent;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConsoleView$_run_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$datatransfer$DataFlavor;
    private static /* synthetic */ Class $class$java$awt$dnd$DnDConstants;
    
    public ConsoleView$_run_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final DropTargetDragEvent evt) {
        final DropTargetDragEvent evt2 = (DropTargetDragEvent)new Reference(evt);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(((Reference<Object>)evt2).get()), $getCallSiteArray[2].callGetProperty($get$$class$java$awt$datatransfer$DataFlavor())))) {
            return $getCallSiteArray[3].call(((Reference<Object>)evt2).get(), $getCallSiteArray[4].callGetProperty($get$$class$java$awt$dnd$DnDConstants()));
        }
        return $getCallSiteArray[5].call(((Reference<Object>)evt2).get());
    }
    
    public Object call(final DropTargetDragEvent evt) {
        final DropTargetDragEvent evt2 = (DropTargetDragEvent)new Reference(evt);
        return $getCallSiteArray()[6].callCurrent(this, ((Reference<Object>)evt2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView$_run_closure3.$callSiteArray == null || ($createCallSiteArray = ConsoleView$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$datatransfer$DataFlavor() {
        Class $class$java$awt$datatransfer$DataFlavor;
        if (($class$java$awt$datatransfer$DataFlavor = ConsoleView$_run_closure3.$class$java$awt$datatransfer$DataFlavor) == null) {
            $class$java$awt$datatransfer$DataFlavor = (ConsoleView$_run_closure3.$class$java$awt$datatransfer$DataFlavor = class$("java.awt.datatransfer.DataFlavor"));
        }
        return $class$java$awt$datatransfer$DataFlavor;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$dnd$DnDConstants() {
        Class $class$java$awt$dnd$DnDConstants;
        if (($class$java$awt$dnd$DnDConstants = ConsoleView$_run_closure3.$class$java$awt$dnd$DnDConstants) == null) {
            $class$java$awt$dnd$DnDConstants = (ConsoleView$_run_closure3.$class$java$awt$dnd$DnDConstants = class$("java.awt.dnd.DnDConstants"));
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
