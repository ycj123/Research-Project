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
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class ConsoleActions extends Script
{
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205056;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$event$InputEvent;
    private static /* synthetic */ Class $class$javax$swing$KeyStroke;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$java$awt$event$KeyEvent;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleActions;
    
    public ConsoleActions() {
        $getCallSiteArray();
    }
    
    public ConsoleActions(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$ConsoleActions(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "New File", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[2].callGroovyObjectGetProperty(this), "fileNewFile"), "mnemonic", "N", "accelerator", $getCallSiteArray[3].callCurrent(this, "N"), "smallIcon", $getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/page.png", "class", this })), "shortDescription", "New Groovy Script" })), $get$$class$groovy$ui$ConsoleActions(), this, "newFileAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "New Window", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[6].callGroovyObjectGetProperty(this), "fileNewWindow"), "mnemonic", "W", "accelerator", $getCallSiteArray[7].callCurrent(this, "shift N") })), $get$$class$groovy$ui$ConsoleActions(), this, "newWindowAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[8].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Open", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[9].callGroovyObjectGetProperty(this), "fileOpen"), "mnemonic", "O", "accelerator", $getCallSiteArray[10].callCurrent(this, "O"), "smallIcon", $getCallSiteArray[11].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/folder_page.png", "class", this })), "shortDescription", "Open Groovy Script" })), $get$$class$groovy$ui$ConsoleActions(), this, "openAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[12].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Save", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[13].callGroovyObjectGetProperty(this), "fileSave"), "mnemonic", "S", "accelerator", $getCallSiteArray[14].callCurrent(this, "S"), "smallIcon", $getCallSiteArray[15].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/disk.png", "class", this })), "shortDescription", "Save Groovy Script", "enabled", Boolean.FALSE })), $get$$class$groovy$ui$ConsoleActions(), this, "saveAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Save As...", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[17].callGroovyObjectGetProperty(this), "fileSaveAs"), "mnemonic", "A" })), $get$$class$groovy$ui$ConsoleActions(), this, "saveAsAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[18].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Print...", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[19].callGroovyObjectGetProperty(this), "print"), "mnemonic", "P", "accelerator", $getCallSiteArray[20].callCurrent(this, "P") })), $get$$class$groovy$ui$ConsoleActions(), this, "printAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[21].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Exit", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[22].callGroovyObjectGetProperty(this), "exit"), "mnemonic", "X" })), $get$$class$groovy$ui$ConsoleActions(), this, "exitAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[23].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Undo", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[24].callGroovyObjectGetProperty(this), "undo"), "mnemonic", "U", "accelerator", $getCallSiteArray[25].callCurrent(this, "Z"), "smallIcon", $getCallSiteArray[26].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/arrow_undo.png", "class", this })), "shortDescription", "Undo" })), $get$$class$groovy$ui$ConsoleActions(), this, "undoAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[27].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Redo", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[28].callGroovyObjectGetProperty(this), "redo"), "mnemonic", "R", "accelerator", $getCallSiteArray[29].callCurrent(this, "shift Z"), "smallIcon", $getCallSiteArray[30].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/arrow_redo.png", "class", this })), "shortDescription", "Redo" })), $get$$class$groovy$ui$ConsoleActions(), this, "redoAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[31].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Find...", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[32].callGroovyObjectGetProperty(this), "find"), "mnemonic", "F", "accelerator", $getCallSiteArray[33].callCurrent(this, "F"), "smallIcon", $getCallSiteArray[34].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/find.png", "class", this })), "shortDescription", "Find" })), $get$$class$groovy$ui$ConsoleActions(), this, "findAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[35].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Find Next", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[36].callGroovyObjectGetProperty(this), "findNext"), "mnemonic", "N", "accelerator", $getCallSiteArray[37].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[38].callGetProperty($get$$class$java$awt$event$KeyEvent()), ConsoleActions.$const$0) })), $get$$class$groovy$ui$ConsoleActions(), this, "findNextAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[39].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Find Previous", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[40].callGroovyObjectGetProperty(this), "findPrevious"), "mnemonic", "V", "accelerator", $getCallSiteArray[41].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[42].callGetProperty($get$$class$java$awt$event$KeyEvent()), $getCallSiteArray[43].callGetProperty($get$$class$java$awt$event$InputEvent())) })), $get$$class$groovy$ui$ConsoleActions(), this, "findPreviousAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[44].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Replace...", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[45].callGroovyObjectGetProperty(this), "replace"), "mnemonic", "E", "accelerator", $getCallSiteArray[46].callCurrent(this, "H"), "smallIcon", $getCallSiteArray[47].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/text_replace.png", "class", this })), "shortDescription", "Replace" })), $get$$class$groovy$ui$ConsoleActions(), this, "replaceAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[48].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Cut", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[49].callGroovyObjectGetProperty(this), "cut"), "mnemonic", "T", "accelerator", $getCallSiteArray[50].callCurrent(this, "X"), "smallIcon", $getCallSiteArray[51].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/cut.png", "class", this })), "shortDescription", "Cut" })), $get$$class$groovy$ui$ConsoleActions(), this, "cutAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[52].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Copy", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[53].callGroovyObjectGetProperty(this), "copy"), "mnemonic", "C", "accelerator", $getCallSiteArray[54].callCurrent(this, "C"), "smallIcon", $getCallSiteArray[55].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/page_copy.png", "class", this })), "shortDescription", "Copy" })), $get$$class$groovy$ui$ConsoleActions(), this, "copyAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[56].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Paste", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[57].callGroovyObjectGetProperty(this), "paste"), "mnemonic", "P", "accelerator", $getCallSiteArray[58].callCurrent(this, "V"), "smallIcon", $getCallSiteArray[59].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/page_paste.png", "class", this })), "shortDescription", "Paste" })), $get$$class$groovy$ui$ConsoleActions(), this, "pasteAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[60].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Select All", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[61].callGroovyObjectGetProperty(this), "selectAll"), "mnemonic", "A", "accelerator", $getCallSiteArray[62].callCurrent(this, "A") })), $get$$class$groovy$ui$ConsoleActions(), this, "selectAllAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[63].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Previous", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[64].callGroovyObjectGetProperty(this), "historyPrev"), "mnemonic", "P", "accelerator", $getCallSiteArray[65].callCurrent(this, $getCallSiteArray[66].callGetProperty($get$$class$java$awt$event$KeyEvent())), "smallIcon", $getCallSiteArray[67].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/book_previous.png", "class", this })), "shortDescription", "Previous Groovy Script", "enabled", Boolean.FALSE })), $get$$class$groovy$ui$ConsoleActions(), this, "historyPrevAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[68].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Next", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[69].callGroovyObjectGetProperty(this), "historyNext"), "mnemonic", "N", "accelerator", $getCallSiteArray[70].callCurrent(this, $getCallSiteArray[71].callGetProperty($get$$class$java$awt$event$KeyEvent())), "smallIcon", $getCallSiteArray[72].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/book_next.png", "class", this })), "shortDescription", "Next Groovy Script", "enabled", Boolean.FALSE })), $get$$class$groovy$ui$ConsoleActions(), this, "historyNextAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[73].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Clear Output", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[74].callGroovyObjectGetProperty(this), "clearOutput"), "mnemonic", "C", "accelerator", $getCallSiteArray[75].callCurrent(this, "W") })), $get$$class$groovy$ui$ConsoleActions(), this, "clearOutputAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[76].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Run", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[77].callGroovyObjectGetProperty(this), "runScript"), "mnemonic", "R", "keyStroke", $getCallSiteArray[78].callCurrent(this, "ENTER"), "accelerator", $getCallSiteArray[79].callCurrent(this, "R"), "smallIcon", $getCallSiteArray[80].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/script_go.png", "class", this })), "shortDescription", "Execute Groovy Script" })), $get$$class$groovy$ui$ConsoleActions(), this, "runAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[81].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Run Selection", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[82].callGroovyObjectGetProperty(this), "runSelectedScript"), "mnemonic", "E", "keyStroke", $getCallSiteArray[83].callCurrent(this, "shift ENTER"), "accelerator", $getCallSiteArray[84].callCurrent(this, "shift R") })), $get$$class$groovy$ui$ConsoleActions(), this, "runSelectionAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[85].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Add Jar to ClassPath", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[86].callGroovyObjectGetProperty(this), "addClasspathJar"), "mnemonic", "J" })), $get$$class$groovy$ui$ConsoleActions(), this, "addClasspathJar");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[87].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Add Directory to ClassPath", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[88].callGroovyObjectGetProperty(this), "addClasspathDir"), "mnemonic", "D" })), $get$$class$groovy$ui$ConsoleActions(), this, "addClasspathDir");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[89].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Clear Script Context", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[90].callGroovyObjectGetProperty(this), "clearContext"), "mnemonic", "C" })), $get$$class$groovy$ui$ConsoleActions(), this, "clearClassloader");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[91].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Inspect Last", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[92].callGroovyObjectGetProperty(this), "inspectLast"), "mnemonic", "I", "accelerator", $getCallSiteArray[93].callCurrent(this, "I") })), $get$$class$groovy$ui$ConsoleActions(), this, "inspectLastAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[94].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Inspect Variables", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[95].callGroovyObjectGetProperty(this), "inspectVariables"), "mnemonic", "V", "accelerator", $getCallSiteArray[96].callCurrent(this, "J") })), $get$$class$groovy$ui$ConsoleActions(), this, "inspectVariablesAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[97].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Inspect Ast", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[98].callGroovyObjectGetProperty(this), "inspectAst"), "mnemonic", "A", "accelerator", $getCallSiteArray[99].callCurrent(this, "T") })), $get$$class$groovy$ui$ConsoleActions(), this, "inspectAstAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[100].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Capture Standard Output", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[101].callGroovyObjectGetProperty(this), "captureStdOut"), "mnemonic", "O" })), $get$$class$groovy$ui$ConsoleActions(), this, "captureStdOutAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[102].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Capture Standard Error Output", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[103].callGroovyObjectGetProperty(this), "captureStdErr"), "mnemonic", "E" })), $get$$class$groovy$ui$ConsoleActions(), this, "captureStdErrAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[104].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Show Full Stack Traces", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[105].callGroovyObjectGetProperty(this), "fullStackTraces"), "mnemonic", "F" })), $get$$class$groovy$ui$ConsoleActions(), this, "fullStackTracesAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[106].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Show Script in Output", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[107].callGroovyObjectGetProperty(this), "showScriptInOutput"), "mnemonic", "R" })), $get$$class$groovy$ui$ConsoleActions(), this, "showScriptInOutputAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[108].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Visualize Script Results", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[109].callGroovyObjectGetProperty(this), "visualizeScriptResults"), "mnemonic", "V" })), $get$$class$groovy$ui$ConsoleActions(), this, "visualizeScriptResultsAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[110].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Show Toolbar", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[111].callGroovyObjectGetProperty(this), "showToolbar"), "mnemonic", "T" })), $get$$class$groovy$ui$ConsoleActions(), this, "showToolbarAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[112].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Detached Output", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[113].callGroovyObjectGetProperty(this), "detachedOutput"), "mnemonic", "D" })), $get$$class$groovy$ui$ConsoleActions(), this, "detachedOutputAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[114].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[115].callGroovyObjectGetProperty(this), "showOutputWindow"), "keyStroke", $getCallSiteArray[116].callCurrent(this, "shift O") })), $get$$class$groovy$ui$ConsoleActions(), this, "showOutputWindowAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[117].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[118].callGroovyObjectGetProperty(this), "hideOutputWindow"), "keyStroke", "SPACE" })), $get$$class$groovy$ui$ConsoleActions(), this, "hideOutputWindowAction1");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[119].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[120].callGroovyObjectGetProperty(this), "hideOutputWindow"), "keyStroke", "ENTER" })), $get$$class$groovy$ui$ConsoleActions(), this, "hideOutputWindowAction2");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[121].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[122].callGroovyObjectGetProperty(this), "hideOutputWindow"), "keyStroke", "ESCAPE" })), $get$$class$groovy$ui$ConsoleActions(), this, "hideOutputWindowAction3");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[123].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[124].callGroovyObjectGetProperty(this), "hideAndClearOutputWindow"), "keyStroke", $getCallSiteArray[125].callCurrent(this, "W") })), $get$$class$groovy$ui$ConsoleActions(), this, "hideOutputWindowAction4");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[126].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Auto Clear Output On Run", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[127].callGroovyObjectGetProperty(this), "autoClearOutput"), "mnemonic", "A" })), $get$$class$groovy$ui$ConsoleActions(), this, "autoClearOutputAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[128].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Larger Font", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[129].callGroovyObjectGetProperty(this), "largerFont"), "mnemonic", "L", "accelerator", $getCallSiteArray[130].callCurrent(this, "shift L") })), $get$$class$groovy$ui$ConsoleActions(), this, "largerFontAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[131].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Smaller Font", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[132].callGroovyObjectGetProperty(this), "smallerFont"), "mnemonic", "S", "accelerator", $getCallSiteArray[133].callCurrent(this, "shift S") })), $get$$class$groovy$ui$ConsoleActions(), this, "smallerFontAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[134].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "About", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[135].callGroovyObjectGetProperty(this), "showAbout"), "mnemonic", "A" })), $get$$class$groovy$ui$ConsoleActions(), this, "aboutAction");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[136].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Interrupt", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[137].callGroovyObjectGetProperty(this), "doInterrupt"), "mnemonic", "T", "smallIcon", $getCallSiteArray[138].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resource", "icons/cross.png", "class", this })), "shortDescription", "Interrupt Running Script", "enabled", Boolean.FALSE })), $get$$class$groovy$ui$ConsoleActions(), this, "interruptAction");
        final Object callCurrent = $getCallSiteArray[139].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "Compile", "closure", ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[140].callGroovyObjectGetProperty(this), "compileScript"), "mnemonic", "L", "accelerator", $getCallSiteArray[141].callCurrent(this, "L"), "shortDescription", "Compile Groovy Script" }));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$ui$ConsoleActions(), this, "compileAction");
        return callCurrent;
    }
    
    static {
        ConsoleActions.__timeStamp__239_neverHappen1292524205056 = 0L;
        ConsoleActions.__timeStamp = 1292524205056L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[142];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleActions(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleActions.$callSiteArray == null || ($createCallSiteArray = ConsoleActions.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleActions.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$event$InputEvent() {
        Class $class$java$awt$event$InputEvent;
        if (($class$java$awt$event$InputEvent = ConsoleActions.$class$java$awt$event$InputEvent) == null) {
            $class$java$awt$event$InputEvent = (ConsoleActions.$class$java$awt$event$InputEvent = class$("java.awt.event.InputEvent"));
        }
        return $class$java$awt$event$InputEvent;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$KeyStroke() {
        Class $class$javax$swing$KeyStroke;
        if (($class$javax$swing$KeyStroke = ConsoleActions.$class$javax$swing$KeyStroke) == null) {
            $class$javax$swing$KeyStroke = (ConsoleActions.$class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke"));
        }
        return $class$javax$swing$KeyStroke;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = ConsoleActions.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (ConsoleActions.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$event$KeyEvent() {
        Class $class$java$awt$event$KeyEvent;
        if (($class$java$awt$event$KeyEvent = ConsoleActions.$class$java$awt$event$KeyEvent) == null) {
            $class$java$awt$event$KeyEvent = (ConsoleActions.$class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent"));
        }
        return $class$java$awt$event$KeyEvent;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = ConsoleActions.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (ConsoleActions.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleActions() {
        Class $class$groovy$ui$ConsoleActions;
        if (($class$groovy$ui$ConsoleActions = ConsoleActions.$class$groovy$ui$ConsoleActions) == null) {
            $class$groovy$ui$ConsoleActions = (ConsoleActions.$class$groovy$ui$ConsoleActions = class$("groovy.ui.ConsoleActions"));
        }
        return $class$groovy$ui$ConsoleActions;
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
