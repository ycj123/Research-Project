// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.awt.dnd.DropTargetListener;
import javax.swing.event.DocumentListener;
import java.awt.Window;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class ConsoleView extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205083;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$view$GTKDefaults;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleView;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$java$awt$dnd$DropTargetListener;
    private static /* synthetic */ Class $class$javax$swing$event$DocumentListener;
    private static /* synthetic */ Class $class$groovy$ui$view$Defaults;
    private static /* synthetic */ Class $class$groovy$ui$view$WindowsDefaults;
    private static /* synthetic */ Class $class$groovy$ui$view$MacOSXDefaults;
    
    public ConsoleView() {
        $getCallSiteArray();
    }
    
    public ConsoleView(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$ConsoleView(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object call = $getCallSiteArray[1].call($get$$class$javax$swing$UIManager());
        Label_0142: {
            if (!ScriptBytecodeAdapter.isCase(call, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel")) {
                if (!ScriptBytecodeAdapter.isCase(call, "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")) {
                    if (!ScriptBytecodeAdapter.isCase(call, "apple.laf.AquaLookAndFeel")) {
                        if (!ScriptBytecodeAdapter.isCase(call, "com.apple.laf.AquaLookAndFeel")) {
                            if (ScriptBytecodeAdapter.isCase(call, "com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
                                $getCallSiteArray[4].callCurrent(this, $get$$class$groovy$ui$view$GTKDefaults());
                                break Label_0142;
                            }
                            $getCallSiteArray[5].callCurrent(this, $get$$class$groovy$ui$view$Defaults());
                            break Label_0142;
                        }
                    }
                    $getCallSiteArray[3].callCurrent(this, $get$$class$groovy$ui$view$MacOSXDefaults());
                    break Label_0142;
                }
            }
            $getCallSiteArray[2].callCurrent(this, $get$$class$groovy$ui$view$WindowsDefaults());
        }
        ScriptBytecodeAdapter.setProperty(this, $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this)), "delegate");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[8].call($getCallSiteArray[9].call($getCallSiteArray[10].callGroovyObjectGetProperty(this), "rootContainerDelegate")), $get$$class$groovy$ui$ConsoleView(), this, "consoleFrame");
        $getCallSiteArray[11].callCurrent(this, $getCallSiteArray[12].callGroovyObjectGetProperty(this), new ConsoleView$_run_closure1(this, this));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[13].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[14].callGroovyObjectGetProperty(this), "promptStyle");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[15].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[16].callGroovyObjectGetProperty(this), "commandStyle");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[17].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[18].callGroovyObjectGetProperty(this), "outputStyle");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[19].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[20].callGroovyObjectGetProperty(this), "stacktraceStyle");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[21].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[22].callGroovyObjectGetProperty(this), "hyperlinkStyle");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[23].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[24].callGroovyObjectGetProperty(this), "resultStyle");
        if ($getCallSiteArray[25].callGroovyObjectGetProperty(this) instanceof Window) {
            ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[26].callGroovyObjectGetProperty(this), "exit"), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[27].callGroovyObjectGetProperty(this), "windowClosing");
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[28].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[29].callGroovyObjectGetProperty(this), "inputEditor");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[30].callGetProperty($getCallSiteArray[31].callGroovyObjectGetProperty(this)), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[32].callGroovyObjectGetProperty(this), "inputArea");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[33].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[34].callGroovyObjectGetProperty(this), "outputArea");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[35].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[36].callGroovyObjectGetProperty(this), "outputWindow");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[37].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[38].callGroovyObjectGetProperty(this), "statusLabel");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[39].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[40].callGroovyObjectGetProperty(this), "frame");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[41].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[42].callGroovyObjectGetProperty(this), "rowNumAndColNum");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[43].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[44].callGroovyObjectGetProperty(this), "toolbar");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[45].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[46].callGroovyObjectGetProperty(this), "saveAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[47].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[48].callGroovyObjectGetProperty(this), "prevHistoryAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[49].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[50].callGroovyObjectGetProperty(this), "nextHistoryAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[51].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[52].callGroovyObjectGetProperty(this), "fullStackTracesAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[53].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[54].callGroovyObjectGetProperty(this), "showToolbarAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[55].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[56].callGroovyObjectGetProperty(this), "detachedOutputAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[57].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[58].callGroovyObjectGetProperty(this), "autoClearOutputAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[59].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[60].callGroovyObjectGetProperty(this), "showOutputWindowAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[61].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[62].callGroovyObjectGetProperty(this), "hideOutputWindowAction1");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[63].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[64].callGroovyObjectGetProperty(this), "hideOutputWindowAction2");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[65].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[66].callGroovyObjectGetProperty(this), "hideOutputWindowAction3");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[67].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[68].callGroovyObjectGetProperty(this), "hideOutputWindowAction4");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[69].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[70].callGroovyObjectGetProperty(this), "interruptAction");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[71].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[72].callGroovyObjectGetProperty(this), "origDividerSize");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[73].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[74].callGroovyObjectGetProperty(this), "splitPane");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[75].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[76].callGroovyObjectGetProperty(this), "blank");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[77].callGroovyObjectGetProperty(this), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[78].callGroovyObjectGetProperty(this), "scrollArea");
        $getCallSiteArray[79].call($getCallSiteArray[80].callGetProperty($getCallSiteArray[81].callGroovyObjectGetProperty(this)), $getCallSiteArray[82].callGroovyObjectGetProperty(this));
        $getCallSiteArray[83].call($getCallSiteArray[84].callGetProperty($getCallSiteArray[85].callGroovyObjectGetProperty(this)), $getCallSiteArray[86].callGroovyObjectGetProperty(this));
        $getCallSiteArray[87].call($getCallSiteArray[88].callGetProperty($getCallSiteArray[89].callGroovyObjectGetProperty(this)), $getCallSiteArray[90].callGroovyObjectGetProperty(this));
        $getCallSiteArray[91].call($getCallSiteArray[92].callGetProperty($getCallSiteArray[93].callGroovyObjectGetProperty(this)), $getCallSiteArray[94].callGroovyObjectGetProperty(this));
        $getCallSiteArray[95].call($getCallSiteArray[96].callGetProperty($getCallSiteArray[97].callGroovyObjectGetProperty(this)), $getCallSiteArray[98].callGroovyObjectGetProperty(this));
        $getCallSiteArray[99].call($getCallSiteArray[100].callGetProperty($getCallSiteArray[101].callGroovyObjectGetProperty(this)), $getCallSiteArray[102].callGroovyObjectGetProperty(this));
        $getCallSiteArray[103].call($getCallSiteArray[104].callGetProperty($getCallSiteArray[105].callGetProperty($getCallSiteArray[106].callGroovyObjectGetProperty(this))), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(new ConsoleView$_run_closure2(this, this), $get$$class$javax$swing$event$DocumentListener()), $get$$class$javax$swing$event$DocumentListener()));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[107].callGetProperty($getCallSiteArray[108].callGetProperty($getCallSiteArray[109].callGroovyObjectGetProperty(this))), $get$$class$groovy$ui$ConsoleView(), $getCallSiteArray[110].callGroovyObjectGetProperty(this), "rootElement");
        final Object dtListener = new Reference(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[] { "dragEnter", new ConsoleView$_run_closure3(this, this), "dragOver", new ConsoleView$_run_closure4(this, this), "dropActionChanged", new ConsoleView$_run_closure5(this, this), "dragExit", new ConsoleView$_run_closure6(this, this), "drop", new ConsoleView$_run_closure7(this, this) }), $get$$class$java$awt$dnd$DropTargetListener()));
        $getCallSiteArray[111].call(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[112].callGroovyObjectGetProperty(this), $getCallSiteArray[113].callGroovyObjectGetProperty(this), $getCallSiteArray[114].callGroovyObjectGetProperty(this) }), new ConsoleView$_run_closure8(this, this, (Reference<Object>)dtListener));
        return null;
    }
    
    static {
        ConsoleView.__timeStamp__239_neverHappen1292524205083 = 0L;
        ConsoleView.__timeStamp = 1292524205083L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[115];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleView(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleView.$callSiteArray == null || ($createCallSiteArray = ConsoleView.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleView.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$GTKDefaults() {
        Class $class$groovy$ui$view$GTKDefaults;
        if (($class$groovy$ui$view$GTKDefaults = ConsoleView.$class$groovy$ui$view$GTKDefaults) == null) {
            $class$groovy$ui$view$GTKDefaults = (ConsoleView.$class$groovy$ui$view$GTKDefaults = class$("groovy.ui.view.GTKDefaults"));
        }
        return $class$groovy$ui$view$GTKDefaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = ConsoleView.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (ConsoleView.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleView() {
        Class $class$groovy$ui$ConsoleView;
        if (($class$groovy$ui$ConsoleView = ConsoleView.$class$groovy$ui$ConsoleView) == null) {
            $class$groovy$ui$ConsoleView = (ConsoleView.$class$groovy$ui$ConsoleView = class$("groovy.ui.ConsoleView"));
        }
        return $class$groovy$ui$ConsoleView;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = ConsoleView.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (ConsoleView.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = ConsoleView.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (ConsoleView.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$dnd$DropTargetListener() {
        Class $class$java$awt$dnd$DropTargetListener;
        if (($class$java$awt$dnd$DropTargetListener = ConsoleView.$class$java$awt$dnd$DropTargetListener) == null) {
            $class$java$awt$dnd$DropTargetListener = (ConsoleView.$class$java$awt$dnd$DropTargetListener = class$("java.awt.dnd.DropTargetListener"));
        }
        return $class$java$awt$dnd$DropTargetListener;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$event$DocumentListener() {
        Class $class$javax$swing$event$DocumentListener;
        if (($class$javax$swing$event$DocumentListener = ConsoleView.$class$javax$swing$event$DocumentListener) == null) {
            $class$javax$swing$event$DocumentListener = (ConsoleView.$class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener"));
        }
        return $class$javax$swing$event$DocumentListener;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$Defaults() {
        Class $class$groovy$ui$view$Defaults;
        if (($class$groovy$ui$view$Defaults = ConsoleView.$class$groovy$ui$view$Defaults) == null) {
            $class$groovy$ui$view$Defaults = (ConsoleView.$class$groovy$ui$view$Defaults = class$("groovy.ui.view.Defaults"));
        }
        return $class$groovy$ui$view$Defaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$WindowsDefaults() {
        Class $class$groovy$ui$view$WindowsDefaults;
        if (($class$groovy$ui$view$WindowsDefaults = ConsoleView.$class$groovy$ui$view$WindowsDefaults) == null) {
            $class$groovy$ui$view$WindowsDefaults = (ConsoleView.$class$groovy$ui$view$WindowsDefaults = class$("groovy.ui.view.WindowsDefaults"));
        }
        return $class$groovy$ui$view$WindowsDefaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$MacOSXDefaults() {
        Class $class$groovy$ui$view$MacOSXDefaults;
        if (($class$groovy$ui$view$MacOSXDefaults = ConsoleView.$class$groovy$ui$view$MacOSXDefaults) == null) {
            $class$groovy$ui$view$MacOSXDefaults = (ConsoleView.$class$groovy$ui$view$MacOSXDefaults = class$("groovy.ui.view.MacOSXDefaults"));
        }
        return $class$groovy$ui$view$MacOSXDefaults;
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
