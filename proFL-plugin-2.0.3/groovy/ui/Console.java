// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentEvent;
import javax.swing.event.HyperlinkEvent;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.awt.Dimension;
import java.io.StringWriter;
import org.codehaus.groovy.control.ErrorCollector;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import javax.swing.event.CaretEvent;
import java.util.EventObject;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.Icon;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.AttributeSet;
import java.awt.Window;
import java.util.Map;
import javax.swing.JApplet;
import org.apache.ivy.core.event.IvyListener;
import groovy.grape.GrapeIvy;
import groovy.lang.Reference;
import java.util.Set;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Binding;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import java.io.File;
import java.util.List;
import javax.swing.text.Style;
import javax.swing.text.Element;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.RootPaneContainer;
import groovy.swing.SwingBuilder;
import java.awt.Component;
import javax.swing.Action;
import groovy.lang.GroovyObject;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.CaretListener;

public class Console implements CaretListener, HyperlinkListener, ComponentListener, FocusListener, GroovyObject
{
    private static final String DEFAULT_SCRIPT_NAME_START;
    private static Object prefs;
    private static boolean captureStdOut;
    private static boolean captureStdErr;
    private static Object consoleControllers;
    private boolean fullStackTraces;
    private Action fullStackTracesAction;
    private boolean showScriptInOutput;
    private Action showScriptInOutputAction;
    private boolean visualizeScriptResults;
    private Action visualizeScriptResultsAction;
    private boolean showToolbar;
    private Component toolbar;
    private Action showToolbarAction;
    private boolean detachedOutput;
    private Action detachedOutputAction;
    private Action showOutputWindowAction;
    private Action hideOutputWindowAction1;
    private Action hideOutputWindowAction2;
    private Action hideOutputWindowAction3;
    private Action hideOutputWindowAction4;
    private int origDividerSize;
    private Component outputWindow;
    private Component copyFromComponent;
    private Component blank;
    private Component scrollArea;
    private boolean autoClearOutput;
    private Action autoClearOutputAction;
    private int maxHistory;
    private int maxOutputChars;
    private SwingBuilder swing;
    private RootPaneContainer frame;
    private ConsoleTextEditor inputEditor;
    private JSplitPane splitPane;
    private JTextPane inputArea;
    private JTextPane outputArea;
    private JLabel statusLabel;
    private JLabel rowNumAndColNum;
    private Element rootElement;
    private int cursorPos;
    private int rowNum;
    private int colNum;
    private Style promptStyle;
    private Style commandStyle;
    private Style outputStyle;
    private Style stacktraceStyle;
    private Style hyperlinkStyle;
    private Style resultStyle;
    private List history;
    private int historyIndex;
    private HistoryRecord pendingRecord;
    private Action prevHistoryAction;
    private Action nextHistoryAction;
    private boolean dirty;
    private Action saveAction;
    private int textSelectionStart;
    private int textSelectionEnd;
    private Object scriptFile;
    private File currentFileChooserDir;
    private File currentClasspathJarDir;
    private File currentClasspathDir;
    private GroovyShell shell;
    private int scriptNameCounter;
    private SystemOutputInterceptor systemOutInterceptor;
    private SystemOutputInterceptor systemErrorInterceptor;
    private Thread runThread;
    private Closure beforeExecution;
    private Closure afterExecution;
    public static String ICON_PATH;
    public static String NODE_ICON_PATH;
    private static Object groovyFileFilter;
    private boolean scriptRunning;
    private boolean stackOverFlowError;
    private Action interruptAction;
    private static Object frameConsoleDelegates;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ BigDecimal $const$4;
    private static final /* synthetic */ BigDecimal $const$5;
    private static final /* synthetic */ Integer $const$6;
    private static final /* synthetic */ Integer $const$7;
    private static final /* synthetic */ Integer $const$8;
    private static final /* synthetic */ Integer $const$9;
    private static final /* synthetic */ Integer $const$10;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203161;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$IvyListener;
    private static /* synthetic */ Class $class$java$awt$Dimension;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleView;
    private static /* synthetic */ Class $class$javax$swing$JSplitPane;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ObjectBrowser;
    private static /* synthetic */ Class $class$java$lang$Math;
    private static /* synthetic */ Class $class$groovy$ui$Console;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$MultipleCompilationErrorsException;
    private static /* synthetic */ Class $class$java$lang$Thread;
    private static /* synthetic */ Class $class$java$awt$event$ActionEvent;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$javax$swing$text$SimpleAttributeSet;
    private static /* synthetic */ Class $class$java$util$prefs$Preferences;
    private static /* synthetic */ Class $class$javax$swing$SwingUtilities;
    private static /* synthetic */ Class $class$groovy$ui$SystemOutputInterceptor;
    private static /* synthetic */ Class $class$java$awt$Toolkit;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
    private static /* synthetic */ Class $class$java$awt$Component;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$ui$text$FindReplaceUtility;
    private static /* synthetic */ Class $class$java$util$HashMap;
    private static /* synthetic */ Class $class$java$awt$EventQueue;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowser;
    private static /* synthetic */ Class $class$java$util$EventObject;
    private static /* synthetic */ Class $class$java$io$StringWriter;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleActions;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$groovy$ui$GroovyFileFilter;
    private static /* synthetic */ Class $class$javax$swing$text$StyleConstants;
    private static /* synthetic */ Class $class$groovy$grape$GrapeIvy;
    private static /* synthetic */ Class $class$javax$swing$JFileChooser;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$ui$HistoryRecord;
    private static /* synthetic */ Class $class$java$awt$BorderLayout;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$ErrorCollector;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$grape$Grape;
    private static /* synthetic */ Class $class$javax$swing$event$HyperlinkEvent$EventType;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$javax$swing$JOptionPane;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    private static /* synthetic */ Class $class$groovy$lang$GroovyShell;
    
    public Console() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[] { null })));
        arguments[0] = $getCallSiteArray[0].callConstructor($get$$class$groovy$lang$Binding());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 4, $get$$class$groovy$ui$Console());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0]);
                break;
            }
            case 2: {
                this((ClassLoader)array2[0]);
                break;
            }
            case 3: {
                this((ClassLoader)array3[0], (Binding)array4[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Console(final Binding binding) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[2])));
        arguments[0] = null;
        arguments[1] = binding;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 4, $get$$class$groovy$ui$Console());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0]);
                break;
            }
            case 2: {
                this((ClassLoader)array2[0]);
                break;
            }
            case 3: {
                this((ClassLoader)array3[0], (Binding)array4[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Console(final ClassLoader parent) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[2])));
        arguments[0] = parent;
        arguments[1] = $getCallSiteArray[1].callConstructor($get$$class$groovy$lang$Binding());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 4, $get$$class$groovy$ui$Console());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0]);
                break;
            }
            case 2: {
                this((ClassLoader)array2[0]);
                break;
            }
            case 3: {
                this((ClassLoader)array3[0], (Binding)array4[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Console(final ClassLoader parent, final Binding binding) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.fullStackTraces = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(Console.prefs, "fullStackTraces", $getCallSiteArray[3].call($get$$class$java$lang$Boolean(), $getCallSiteArray[4].call($get$$class$java$lang$System(), "groovy.full.stacktrace", "false"))));
        this.showScriptInOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(Console.prefs, "showScriptInOutput", Boolean.TRUE));
        this.visualizeScriptResults = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(Console.prefs, "visualizeScriptResults", Boolean.FALSE));
        this.showToolbar = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(Console.prefs, "showToolbar", Boolean.TRUE));
        this.detachedOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(Console.prefs, "detachedOutput", Boolean.FALSE));
        this.autoClearOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(Console.prefs, "autoClearOutput", Boolean.FALSE));
        this.maxHistory = DefaultTypeTransformation.intUnbox(Console.$const$0);
        this.maxOutputChars = DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.asType($getCallSiteArray[10].call($get$$class$java$lang$System(), "groovy.console.output.limit", "20000"), $get$$class$java$lang$Integer()));
        this.history = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.historyIndex = DefaultTypeTransformation.intUnbox(Console.$const$1);
        this.pendingRecord = (HistoryRecord)ScriptBytecodeAdapter.castToType($getCallSiteArray[11].callConstructor($get$$class$groovy$ui$HistoryRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "allText", "", "selectionStart", Console.$const$2, "selectionEnd", Console.$const$2 })), $get$$class$groovy$ui$HistoryRecord());
        this.currentFileChooserDir = (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].callConstructor($get$$class$java$io$File(), $getCallSiteArray[13].call($getCallSiteArray[14].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentFileChooserDir", ".")), $get$$class$java$io$File());
        this.currentClasspathJarDir = (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[15].callConstructor($get$$class$java$io$File(), $getCallSiteArray[16].call($getCallSiteArray[17].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentClasspathJarDir", ".")), $get$$class$java$io$File());
        this.currentClasspathDir = (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[18].callConstructor($get$$class$java$io$File(), $getCallSiteArray[19].call($getCallSiteArray[20].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentClasspathDir", ".")), $get$$class$java$io$File());
        this.scriptNameCounter = DefaultTypeTransformation.intUnbox(Console.$const$2);
        this.runThread = (Thread)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Thread()), $get$$class$java$lang$Thread());
        this.scriptRunning = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.stackOverFlowError = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[21].callCurrent(this, parent, binding);
        try {
            $getCallSiteArray[22].call($get$$class$java$lang$System(), "groovy.full.stacktrace", $getCallSiteArray[23].call($get$$class$java$lang$System(), "groovy.full.stacktrace", $getCallSiteArray[24].call($get$$class$java$lang$Boolean(), $getCallSiteArray[25].call(Console.prefs, "fullStackTraces", Boolean.FALSE))));
        }
        catch (SecurityException se) {
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$ui$Console(), this.fullStackTracesAction, "enabled");
        }
        Console.consoleControllers = $getCallSiteArray[26].call(Console.consoleControllers, this);
        final Set resolvedDependencies = (Set)new Reference(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()));
        final Set downloadedArtifacts = (Set)new Reference(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()));
        $getCallSiteArray[27].call($getCallSiteArray[28].callGetProperty($getCallSiteArray[29].callGroovyObjectGetProperty(ScriptBytecodeAdapter.castToType($getCallSiteArray[30].callGetProperty($get$$class$groovy$grape$Grape()), $get$$class$groovy$grape$GrapeIvy()))), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[] { "progress", new Console$_closure1(this, this, (Reference<Object>)resolvedDependencies, (Reference<Object>)downloadedArtifacts) }), $get$$class$org$apache$ivy$core$event$IvyListener()), $get$$class$org$apache$ivy$core$event$IvyListener()));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[31].call($get$$class$groovy$ui$OutputTransforms()), $get$$class$groovy$ui$Console(), $getCallSiteArray[32].callGroovyObjectGetProperty(binding), "_outputTransforms");
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[33].callGetProperty(args), Console.$const$1) && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[34].call(args, Console.$const$2), "--help")) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[35].callStatic($get$$class$groovy$ui$Console(), "usage: groovyConsole [options] [filename]\noptions:\n  --help                               This Help message\n  -cp,-classpath,--classpath <path>    Specify classpath");
            return;
        }
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console(), $getCallSiteArray[36].call($get$$class$java$util$logging$Logger(), $getCallSiteArray[37].callGetProperty($get$$class$org$codehaus$groovy$runtime$StackTraceUtils())), "useParentHandlers");
        $getCallSiteArray[38].call($get$$class$javax$swing$UIManager(), $getCallSiteArray[39].call($get$$class$javax$swing$UIManager()));
        final Object console = $getCallSiteArray[40].callConstructor($get$$class$groovy$ui$Console(), $getCallSiteArray[41].callSafe($getCallSiteArray[42].callGetProperty($get$$class$groovy$ui$Console())));
        $getCallSiteArray[43].call(console);
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[44].callGetProperty(args), Console.$const$1)) {
            $getCallSiteArray[45].call(console, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[46].call(args, Console.$const$2), $get$$class$java$io$File()), $get$$class$java$io$File()));
        }
    }
    
    public void newScript(final ClassLoader parent, final Binding binding) {
        this.shell = (GroovyShell)ScriptBytecodeAdapter.castToType($getCallSiteArray()[47].callConstructor($get$$class$groovy$lang$GroovyShell(), parent, binding), $get$$class$groovy$lang$GroovyShell());
    }
    
    public void run() {
        $getCallSiteArray()[48].callCurrent(this, Console.frameConsoleDelegates);
    }
    
    public void run(final JApplet applet) {
        final JApplet applet2 = (JApplet)new Reference(applet);
        $getCallSiteArray()[49].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "rootContainerDelegate", new Console$_run_closure2(this, this, (Reference<Object>)applet2), "menuBarDelegate", new Console$_run_closure3(this, this) }));
    }
    
    public void run(final Map defaults) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.swing = (SwingBuilder)ScriptBytecodeAdapter.castToType($getCallSiteArray[50].callConstructor($get$$class$groovy$swing$SwingBuilder()), $get$$class$groovy$swing$SwingBuilder());
        $getCallSiteArray[51].call(defaults, new Console$_run_closure4(this, this));
        $getCallSiteArray[52].call($get$$class$java$lang$System(), "groovy.sanitized.stacktraces", "org.codehaus.groovy.runtime.\n                org.codehaus.groovy.\n                groovy.lang.\n                gjdk.groovy.lang.\n                sun.\n                java.lang.reflect.\n                java.lang.Thread\n                groovy.ui.Console");
        ScriptBytecodeAdapter.setGroovyObjectProperty(this, $get$$class$groovy$ui$Console(), this.swing, "controller");
        $getCallSiteArray[53].call(this.swing, $get$$class$groovy$ui$ConsoleActions());
        $getCallSiteArray[54].call(this.swing, $get$$class$groovy$ui$ConsoleView());
        $getCallSiteArray[55].callCurrent(this);
        $getCallSiteArray[56].call(this.swing, ScriptBytecodeAdapter.createMap(new Object[] { "source", $getCallSiteArray[57].callGetProperty($getCallSiteArray[58].callGroovyObjectGetProperty(this.swing)), "sourceProperty", "enabled", "target", $getCallSiteArray[59].callGroovyObjectGetProperty(this.swing), "targetProperty", "enabled" }));
        $getCallSiteArray[60].call(this.swing, ScriptBytecodeAdapter.createMap(new Object[] { "source", $getCallSiteArray[61].callGetProperty($getCallSiteArray[62].callGroovyObjectGetProperty(this.swing)), "sourceProperty", "enabled", "target", $getCallSiteArray[63].callGroovyObjectGetProperty(this.swing), "targetProperty", "enabled" }));
        if ($getCallSiteArray[64].callGroovyObjectGetProperty(this.swing) instanceof Window) {
            $getCallSiteArray[65].call($getCallSiteArray[66].callGroovyObjectGetProperty(this.swing));
            $getCallSiteArray[67].call($getCallSiteArray[68].callGroovyObjectGetProperty(this.swing));
        }
        $getCallSiteArray[69].callCurrent(this);
        $getCallSiteArray[70].call(this.swing, ScriptBytecodeAdapter.getMethodPointer(this.inputArea, "requestFocus"));
    }
    
    public void installInterceptor() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.systemOutInterceptor = (SystemOutputInterceptor)ScriptBytecodeAdapter.castToType($getCallSiteArray[71].callConstructor($get$$class$groovy$ui$SystemOutputInterceptor(), ScriptBytecodeAdapter.getMethodPointer(this, "notifySystemOut"), Boolean.TRUE), $get$$class$groovy$ui$SystemOutputInterceptor());
        $getCallSiteArray[72].call(this.systemOutInterceptor);
        this.systemErrorInterceptor = (SystemOutputInterceptor)ScriptBytecodeAdapter.castToType($getCallSiteArray[73].callConstructor($get$$class$groovy$ui$SystemOutputInterceptor(), ScriptBytecodeAdapter.getMethodPointer(this, "notifySystemErr"), Boolean.FALSE), $get$$class$groovy$ui$SystemOutputInterceptor());
        $getCallSiteArray[74].call(this.systemErrorInterceptor);
    }
    
    public void addToHistory(final Object record) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[75].call(this.history, record);
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[76].call(this.history), DefaultTypeTransformation.box(this.maxHistory))) {
            $getCallSiteArray[77].call(this.history, Console.$const$2);
        }
        this.historyIndex = DefaultTypeTransformation.intUnbox($getCallSiteArray[78].call(this.history));
        $getCallSiteArray[79].callCurrent(this);
    }
    
    private Object ensureNoDocLengthOverflow(final Object doc) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Integer offset = (Integer)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.stackOverFlowError)) ? DefaultTypeTransformation.box(this.maxOutputChars) : Console.$const$2, $get$$class$java$lang$Integer());
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[80].callGetProperty(doc), DefaultTypeTransformation.box(this.maxOutputChars))) {
            return $getCallSiteArray[81].call(doc, offset, $getCallSiteArray[82].call($getCallSiteArray[83].callGetProperty(doc), DefaultTypeTransformation.box(this.maxOutputChars)));
        }
        return null;
    }
    
    public void appendOutput(final String text, final AttributeSet style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object doc = $getCallSiteArray[84].callGetProperty(this.outputArea);
        $getCallSiteArray[85].call(doc, $getCallSiteArray[86].callGetProperty(doc), text, style);
        $getCallSiteArray[87].callCurrent(this, doc);
    }
    
    public void appendOutput(final Window window, final AttributeSet style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[88].callCurrent(this, $getCallSiteArray[89].call(window), style);
    }
    
    public void appendOutput(final Object object, final AttributeSet style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[90].callCurrent(this, $getCallSiteArray[91].call(object), style);
    }
    
    public void appendOutput(final Component component, final AttributeSet style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final SimpleAttributeSet sas = (SimpleAttributeSet)$getCallSiteArray[92].callConstructor($get$$class$javax$swing$text$SimpleAttributeSet());
        $getCallSiteArray[93].call(sas, $getCallSiteArray[94].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "component");
        $getCallSiteArray[95].call($get$$class$javax$swing$text$StyleConstants(), sas, component);
        $getCallSiteArray[96].callCurrent(this, $getCallSiteArray[97].call(component), sas);
    }
    
    public void appendOutput(final Icon icon, final AttributeSet style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final SimpleAttributeSet sas = (SimpleAttributeSet)$getCallSiteArray[98].callConstructor($get$$class$javax$swing$text$SimpleAttributeSet());
        $getCallSiteArray[99].call(sas, $getCallSiteArray[100].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "icon");
        $getCallSiteArray[101].call($get$$class$javax$swing$text$StyleConstants(), sas, icon);
        $getCallSiteArray[102].callCurrent(this, $getCallSiteArray[103].call(icon), sas);
    }
    
    public void appendStacktrace(final Object text) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object doc = new Reference($getCallSiteArray[104].callGetProperty(this.outputArea));
        final Object lines = $getCallSiteArray[105].call(text, "(\\n|\\r|\\r\\n|\u0085|\u2028|\u2029)");
        final Object ji = "([\\p{Alnum}_\\$][\\p{Alnum}_\\$]*)";
        final Object stacktracePattern = new Reference(new GStringImpl(new Object[] { ji, ji, ji }, new String[] { "\\tat ", "(\\.", ")+\\(((", "(\\.(java|groovy))?):(\\d+))\\)" }));
        $getCallSiteArray[106].call(lines, new Console$_appendStacktrace_closure5(this, this, (Reference<Object>)doc, (Reference<Object>)stacktracePattern));
        $getCallSiteArray[107].callCurrent(this, ((Reference<Object>)doc).get());
    }
    
    public void appendOutputNl(final Object text, final Object style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object doc = $getCallSiteArray[108].callGetProperty(this.outputArea);
        final Object len = $getCallSiteArray[109].callGetProperty(doc);
        final Object alreadyNewLine = (!ScriptBytecodeAdapter.compareEqual(len, Console.$const$2) && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[110].call(doc, $getCallSiteArray[111].call(len, Console.$const$1), Console.$const$1), "\n")) ? Boolean.FALSE : Boolean.TRUE;
        $getCallSiteArray[112].call(doc, $getCallSiteArray[113].callGetProperty(doc), " \n", style);
        if (DefaultTypeTransformation.booleanUnbox(alreadyNewLine)) {
            $getCallSiteArray[114].call(doc, len, Console.$const$3);
        }
        $getCallSiteArray[115].callCurrent(this, text, style);
    }
    
    public void appendOutputLines(final Object text, final Object style) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[116].callCurrent(this, text, style);
        final Object doc = $getCallSiteArray[117].callGetProperty(this.outputArea);
        final Object len = $getCallSiteArray[118].callGetProperty(doc);
        $getCallSiteArray[119].call(doc, len, " \n", style);
        $getCallSiteArray[120].call(doc, len, Console.$const$3);
    }
    
    public boolean askToSaveFile() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(this.scriptFile, null) && DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.dirty))) ? Boolean.FALSE : Boolean.TRUE)) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        final Object call = $getCallSiteArray[121].call($get$$class$javax$swing$JOptionPane(), this.frame, $getCallSiteArray[122].call($getCallSiteArray[123].call("Save changes to ", $getCallSiteArray[124].callGetProperty(this.scriptFile)), "?"), "GroovyConsole", $getCallSiteArray[125].callGetProperty($get$$class$javax$swing$JOptionPane()));
        if (ScriptBytecodeAdapter.isCase(call, $getCallSiteArray[126].callGetProperty($get$$class$javax$swing$JOptionPane()))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[127].callCurrent(this), $get$$class$java$lang$Boolean()));
        }
        if (ScriptBytecodeAdapter.isCase(call, $getCallSiteArray[128].callGetProperty($get$$class$javax$swing$JOptionPane()))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public void beep() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[129].call($getCallSiteArray[130].callGetProperty($get$$class$java$awt$Toolkit()));
    }
    
    public void bindResults() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[131].call(this.shell, "_", $getCallSiteArray[132].callCurrent(this));
        $getCallSiteArray[133].call(this.shell, "__", $getCallSiteArray[134].call(this.history, new Console$_bindResults_closure6(this, this)));
    }
    
    public static void captureStdOut(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Console.captureStdOut = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[135].callGetProperty($getCallSiteArray[136].callGetProperty(evt)));
        $getCallSiteArray[137].call(Console.prefs, "captureStdOut", DefaultTypeTransformation.box(Console.captureStdOut));
    }
    
    public static void captureStdErr(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Console.captureStdErr = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[138].callGetProperty($getCallSiteArray[139].callGetProperty(evt)));
        $getCallSiteArray[140].call(Console.prefs, "captureStdErr", DefaultTypeTransformation.box(Console.captureStdErr));
    }
    
    public void fullStackTraces(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.fullStackTraces = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[141].callGetProperty($getCallSiteArray[142].callGetProperty(evt)));
        $getCallSiteArray[143].call($get$$class$java$lang$System(), "groovy.full.stacktrace", $getCallSiteArray[144].call($get$$class$java$lang$Boolean(), DefaultTypeTransformation.box(this.fullStackTraces)));
        $getCallSiteArray[145].call(Console.prefs, "fullStackTraces", DefaultTypeTransformation.box(this.fullStackTraces));
    }
    
    public void showScriptInOutput(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.showScriptInOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[146].callGetProperty($getCallSiteArray[147].callGetProperty(evt)));
        $getCallSiteArray[148].call(Console.prefs, "showScriptInOutput", DefaultTypeTransformation.box(this.showScriptInOutput));
    }
    
    public void visualizeScriptResults(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.visualizeScriptResults = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[149].callGetProperty($getCallSiteArray[150].callGetProperty(evt)));
        $getCallSiteArray[151].call(Console.prefs, "visualizeScriptResults", DefaultTypeTransformation.box(this.visualizeScriptResults));
    }
    
    public void showToolbar(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.showToolbar = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[152].callGetProperty($getCallSiteArray[153].callGetProperty(evt)));
        $getCallSiteArray[154].call(Console.prefs, "showToolbar", DefaultTypeTransformation.box(this.showToolbar));
        ScriptBytecodeAdapter.setProperty(DefaultTypeTransformation.box(this.showToolbar), $get$$class$groovy$ui$Console(), this.toolbar, "visible");
    }
    
    public void detachedOutput(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object oldDetachedOutput = DefaultTypeTransformation.box(this.detachedOutput);
        this.detachedOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[155].callGetProperty($getCallSiteArray[156].callGetProperty(evt)));
        $getCallSiteArray[157].call(Console.prefs, "detachedOutput", DefaultTypeTransformation.box(this.detachedOutput));
        if (ScriptBytecodeAdapter.compareNotEqual(oldDetachedOutput, DefaultTypeTransformation.box(this.detachedOutput))) {
            if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.detachedOutput))) {
                $getCallSiteArray[158].call(this.splitPane, this.blank, $getCallSiteArray[159].callGetProperty($get$$class$javax$swing$JSplitPane()));
                this.origDividerSize = DefaultTypeTransformation.intUnbox($getCallSiteArray[160].callGetProperty(this.splitPane));
                ScriptBytecodeAdapter.setProperty(Console.$const$2, $get$$class$groovy$ui$Console(), this.splitPane, "dividerSize");
                ScriptBytecodeAdapter.setProperty(Console.$const$4, $get$$class$groovy$ui$Console(), this.splitPane, "resizeWeight");
                $getCallSiteArray[161].call(this.outputWindow, this.scrollArea, $getCallSiteArray[162].callGetProperty($get$$class$java$awt$BorderLayout()));
                $getCallSiteArray[163].callCurrent(this);
            }
            else {
                $getCallSiteArray[164].call(this.splitPane, this.scrollArea, $getCallSiteArray[165].callGetProperty($get$$class$javax$swing$JSplitPane()));
                ScriptBytecodeAdapter.setProperty(DefaultTypeTransformation.box(this.origDividerSize), $get$$class$groovy$ui$Console(), this.splitPane, "dividerSize");
                $getCallSiteArray[166].call(this.outputWindow, this.blank, $getCallSiteArray[167].callGetProperty($get$$class$java$awt$BorderLayout()));
                ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$ui$Console(), this.outputWindow, "visible");
                ScriptBytecodeAdapter.setProperty(Console.$const$5, $get$$class$groovy$ui$Console(), this.splitPane, "resizeWeight");
            }
        }
    }
    
    public void autoClearOutput(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.autoClearOutput = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[168].callGetProperty($getCallSiteArray[169].callGetProperty(evt)));
        $getCallSiteArray[170].call(Console.prefs, "autoClearOutput", DefaultTypeTransformation.box(this.autoClearOutput));
    }
    
    public void caretUpdate(final CaretEvent e) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.textSelectionStart = DefaultTypeTransformation.intUnbox($getCallSiteArray[171].call($get$$class$java$lang$Math(), $getCallSiteArray[172].callGetProperty(e), $getCallSiteArray[173].callGetProperty(e)));
        this.textSelectionEnd = DefaultTypeTransformation.intUnbox($getCallSiteArray[174].call($get$$class$java$lang$Math(), $getCallSiteArray[175].callGetProperty(e), $getCallSiteArray[176].callGetProperty(e)));
        $getCallSiteArray[177].callCurrent(this);
    }
    
    public void clearOutput(final EventObject evt) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty("", $get$$class$groovy$ui$Console(), this.outputArea, "text");
    }
    
    public Object askToInterruptScript() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.scriptRunning))) {
            return Boolean.TRUE;
        }
        final Object rc = $getCallSiteArray[178].call($get$$class$javax$swing$JOptionPane(), this.frame, "Script executing. Press 'OK' to attempt to interrupt it before exiting.", "GroovyConsole", $getCallSiteArray[179].callGetProperty($get$$class$javax$swing$JOptionPane()));
        if (ScriptBytecodeAdapter.compareEqual(rc, $getCallSiteArray[180].callGetProperty($get$$class$javax$swing$JOptionPane()))) {
            $getCallSiteArray[181].callCurrent(this);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public void doInterrupt(final EventObject evt) {
        $getCallSiteArray()[182].callSafe(this.runThread);
    }
    
    public void exit(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[183].callCurrent(this)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[184].callCurrent(this))) {
            if (this.frame instanceof Window) {
                $getCallSiteArray[185].call(this.frame);
                $getCallSiteArray[186].call(this.frame);
                $getCallSiteArray[187].callSafe(this.outputWindow);
            }
            $getCallSiteArray[188].call($get$$class$groovy$ui$text$FindReplaceUtility());
            $getCallSiteArray[189].call(Console.consoleControllers, this);
            Label_0175: {
                if (!DefaultTypeTransformation.booleanUnbox(Console.consoleControllers)) {
                    $getCallSiteArray[190].call(this.systemOutInterceptor);
                    $getCallSiteArray[191].call(this.systemErrorInterceptor);
                    break Label_0175;
                }
                break Label_0175;
            }
            goto Label_0178;
        }
    }
    
    public void fileNewFile(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[192].callCurrent(this))) {
            this.scriptFile = null;
            $getCallSiteArray[193].callCurrent(this, Boolean.FALSE);
            ScriptBytecodeAdapter.setProperty("", $get$$class$groovy$ui$Console(), this.inputArea, "text");
        }
    }
    
    public void fileNewWindow(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Console consoleController = (Console)$getCallSiteArray[194].callConstructor($get$$class$groovy$ui$Console(), $getCallSiteArray[195].callConstructor($get$$class$groovy$lang$Binding(), $getCallSiteArray[196].callConstructor($get$$class$java$util$HashMap(), $getCallSiteArray[197].callGetProperty($getCallSiteArray[198].callGroovyObjectGetProperty(this.shell)))));
        ScriptBytecodeAdapter.setGroovyObjectProperty(this.systemOutInterceptor, $get$$class$groovy$ui$Console(), consoleController, "systemOutInterceptor");
        ScriptBytecodeAdapter.setGroovyObjectProperty(this.systemErrorInterceptor, $get$$class$groovy$ui$Console(), consoleController, "systemErrorInterceptor");
        final SwingBuilder swing = (SwingBuilder)new Reference($getCallSiteArray[199].callConstructor($get$$class$groovy$swing$SwingBuilder()));
        ScriptBytecodeAdapter.setGroovyObjectProperty(((Reference<Object>)swing).get(), $get$$class$groovy$ui$Console(), consoleController, "swing");
        $getCallSiteArray[200].call(Console.frameConsoleDelegates, new Console$_fileNewWindow_closure7(this, this, (Reference<Object>)swing));
        ScriptBytecodeAdapter.setGroovyObjectProperty(consoleController, $get$$class$groovy$ui$Console(), ((Reference<GroovyObject>)swing).get(), "controller");
        $getCallSiteArray[201].call(((Reference<Object>)swing).get(), $get$$class$groovy$ui$ConsoleActions());
        $getCallSiteArray[202].call(((Reference<Object>)swing).get(), $get$$class$groovy$ui$ConsoleView());
        $getCallSiteArray[203].callCurrent(this);
        $getCallSiteArray[204].call($getCallSiteArray[205].callGroovyObjectGetProperty(((Reference<Object>)swing).get()));
        $getCallSiteArray[206].call($getCallSiteArray[207].callGroovyObjectGetProperty(((Reference<Object>)swing).get()));
        $getCallSiteArray[208].call(((Reference<Object>)swing).get(), ScriptBytecodeAdapter.getMethodPointer($getCallSiteArray[209].callGroovyObjectGetProperty(((Reference<Object>)swing).get()), "requestFocus"));
    }
    
    public void fileOpen(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object scriptName = $getCallSiteArray[210].callCurrent(this);
        if (ScriptBytecodeAdapter.compareNotEqual(scriptName, null)) {
            $getCallSiteArray[211].callCurrent(this, scriptName);
        }
    }
    
    public void loadScriptFile(final File file) {
        final File file2 = (File)new Reference(file);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[212].call(this.swing, new Console$_loadScriptFile_closure8(this, this));
        $getCallSiteArray[213].call(this.swing, new Console$_loadScriptFile_closure9(this, this, (Reference<Object>)file2));
    }
    
    public boolean fileSave(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.scriptFile, null)) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[214].callCurrent(this, evt), $get$$class$java$lang$Boolean()));
        }
        $getCallSiteArray[215].call(this.scriptFile, $getCallSiteArray[216].callGetProperty(this.inputArea));
        $getCallSiteArray[217].callCurrent(this, Boolean.FALSE);
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public boolean fileSaveAs(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.scriptFile = $getCallSiteArray[218].callCurrent(this, "Save");
        if (ScriptBytecodeAdapter.compareNotEqual(this.scriptFile, null)) {
            $getCallSiteArray[219].call(this.scriptFile, $getCallSiteArray[220].callGetProperty(this.inputArea));
            $getCallSiteArray[221].callCurrent(this, Boolean.FALSE);
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public Object finishException(final Throwable t, final boolean executing) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(executing))) {
            ScriptBytecodeAdapter.setProperty("Execution terminated with exception.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            ScriptBytecodeAdapter.setProperty(t, $get$$class$groovy$ui$Console(), $getCallSiteArray[222].call(this.history, Console.$const$6), "exception");
        }
        else {
            ScriptBytecodeAdapter.setProperty("Compilation failed.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
        }
        if (t instanceof MultipleCompilationErrorsException) {
            final MultipleCompilationErrorsException mcee = (MultipleCompilationErrorsException)ScriptBytecodeAdapter.castToType(t, $get$$class$org$codehaus$groovy$control$MultipleCompilationErrorsException());
            final ErrorCollector collector = (ErrorCollector)ScriptBytecodeAdapter.castToType($getCallSiteArray[223].callGetProperty(mcee), $get$$class$org$codehaus$groovy$control$ErrorCollector());
            final Integer count = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[224].callGetProperty(collector), $get$$class$java$lang$Integer());
            $getCallSiteArray[225].callCurrent(this, new GStringImpl(new Object[] { count, ScriptBytecodeAdapter.compareGreaterThan(count, Console.$const$1) ? "s" : "" }, new String[] { "", " compilation error", ":\n\n" }), this.commandStyle);
            $getCallSiteArray[226].call($getCallSiteArray[227].callGetProperty(collector), new Console$_finishException_closure10(this, this));
        }
        else {
            $getCallSiteArray[228].callCurrent(this, t);
        }
        if (!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(executing))) {
            $getCallSiteArray[229].callCurrent(this);
        }
        ScriptBytecodeAdapter.setProperty(Console.$const$2, $get$$class$groovy$ui$Console(), this.outputArea, "caretPosition");
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.detachedOutput))) {
            $getCallSiteArray[230].callCurrent(this);
            return $getCallSiteArray[231].callCurrent(this);
        }
        return null;
    }
    
    private Object calcPreferredSize(final Object a, final Object b, final Object c) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[232].call(ScriptBytecodeAdapter.createList(new Object[] { c, $getCallSiteArray[233].call(ScriptBytecodeAdapter.createList(new Object[] { a, b })) }));
    }
    
    private Object reportException(final Throwable t) {
        final Throwable t2 = (Throwable)new Reference(t);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[234].callCurrent(this, "Exception thrown\n", this.commandStyle);
        final StringWriter sw = (StringWriter)$getCallSiteArray[235].callConstructor($get$$class$java$io$StringWriter());
        $getCallSiteArray[236].call($getCallSiteArray[237].callConstructor($get$$class$java$io$PrintWriter(), sw), new Console$_reportException_closure11(this, this, (Reference<Object>)t2));
        return $getCallSiteArray[238].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[239].callGetProperty(sw) }, new String[] { "\n", "\n" }));
    }
    
    public Object finishNormal(final Object result) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty(result, $get$$class$groovy$ui$Console(), $getCallSiteArray[240].call(this.history, Console.$const$6), "result");
        if (ScriptBytecodeAdapter.compareNotEqual(result, null)) {
            ScriptBytecodeAdapter.setProperty("Execution complete.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            $getCallSiteArray[241].callCurrent(this, "Result: ", this.promptStyle);
            final Object obj = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.visualizeScriptResults)) ? $getCallSiteArray[242].call($get$$class$groovy$ui$OutputTransforms(), result, $getCallSiteArray[243].callGetProperty($getCallSiteArray[244].callGroovyObjectGetProperty(this.shell))) : $getCallSiteArray[245].call(result);
            $getCallSiteArray[246].callCurrent(this, obj, this.resultStyle);
        }
        else {
            ScriptBytecodeAdapter.setProperty("Execution complete. Result was null.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
        }
        $getCallSiteArray[247].callCurrent(this);
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.detachedOutput))) {
            $getCallSiteArray[248].callCurrent(this);
            return $getCallSiteArray[249].callCurrent(this);
        }
        return null;
    }
    
    public Object compileFinishNormal() {
        $getCallSiteArray();
        final String messageArgument = "Compilation complete.";
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$ui$Console(), this.statusLabel, "text");
        return messageArgument;
    }
    
    private Object prepareOutputWindow() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[250].call(this.outputArea, (Object)null);
        $getCallSiteArray[251].call(this.outputWindow);
        $getCallSiteArray[252].call(this.outputArea, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[253].callCurrent(this, $getCallSiteArray[254].call(this.outputWindow), $getCallSiteArray[255].call(this.inputEditor), Console.$const$7), $getCallSiteArray[256].callCurrent(this, $getCallSiteArray[257].call(this.outputWindow), $getCallSiteArray[258].call(this.inputEditor), Console.$const$8) }), $get$$class$java$awt$Dimension()), $get$$class$java$awt$Dimension()));
        return $getCallSiteArray[259].call(this.outputWindow);
    }
    
    public Object getLastResult() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.history)) {
            return null;
        }
        Object i = null;
        final Object call = $getCallSiteArray[260].call(ScriptBytecodeAdapter.createRange($getCallSiteArray[261].call($getCallSiteArray[262].call(this.history), Console.$const$1), Console.$const$2, true));
        while (((Iterator)call).hasNext()) {
            i = ((Iterator<Object>)call).next();
            if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[263].callGetProperty($getCallSiteArray[264].call(this.history, i)), null)) {
                return $getCallSiteArray[265].callGetProperty($getCallSiteArray[266].call(this.history, i));
            }
        }
        return null;
    }
    
    public void historyNext(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareLessThan(DefaultTypeTransformation.box(this.historyIndex), $getCallSiteArray[267].call(this.history))) {
            $getCallSiteArray[268].callCurrent(this, $getCallSiteArray[269].call(DefaultTypeTransformation.box(this.historyIndex), Console.$const$1));
        }
        else {
            ScriptBytecodeAdapter.setProperty("Can't go past end of history (time travel not allowed)", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            $getCallSiteArray[270].callCurrent(this);
        }
    }
    
    public void historyPrev(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareGreaterThan(DefaultTypeTransformation.box(this.historyIndex), Console.$const$2)) {
            $getCallSiteArray[271].callCurrent(this, $getCallSiteArray[272].call(DefaultTypeTransformation.box(this.historyIndex), Console.$const$1));
        }
        else {
            ScriptBytecodeAdapter.setProperty("Can't go past start of history", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            $getCallSiteArray[273].callCurrent(this);
        }
    }
    
    public void inspectLast(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(null, $getCallSiteArray[274].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[275].call($get$$class$javax$swing$JOptionPane(), this.frame, "The last result is null.", "Cannot Inspect", $getCallSiteArray[276].callGetProperty($get$$class$javax$swing$JOptionPane()));
            return;
        }
        $getCallSiteArray[277].call($get$$class$groovy$inspect$swingui$ObjectBrowser(), $getCallSiteArray[278].callGroovyObjectGetProperty(this));
    }
    
    public void inspectVariables(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[279].call($get$$class$groovy$inspect$swingui$ObjectBrowser(), $getCallSiteArray[280].callGetProperty($getCallSiteArray[281].callGroovyObjectGetProperty(this.shell)));
    }
    
    public void inspectAst(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[282].call($getCallSiteArray[283].callConstructor($get$$class$groovy$inspect$swingui$AstBrowser(), this.inputArea, this.rootElement, $getCallSiteArray[284].call(this.shell)), new Console$_inspectAst_closure12(this, this));
    }
    
    public void largerFont(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[285].callCurrent(this, $getCallSiteArray[286].call($getCallSiteArray[287].callGetProperty($getCallSiteArray[288].callGetProperty(this.inputArea)), Console.$const$3));
    }
    
    public static boolean notifySystemOut(final String str) {
        final String str2 = (String)new Reference(str);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(Console.captureStdOut))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[289].call($get$$class$java$awt$EventQueue()))) {
            $getCallSiteArray[290].call(Console.consoleControllers, new Console$_notifySystemOut_closure13($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console(), (Reference<Object>)str2));
        }
        else {
            $getCallSiteArray[291].call($get$$class$javax$swing$SwingUtilities(), new Console$_notifySystemOut_closure14($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console(), (Reference<Object>)str2));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public static boolean notifySystemErr(final String str) {
        final String str2 = (String)new Reference(str);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(Console.captureStdErr))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[292].call($get$$class$java$awt$EventQueue()))) {
            $getCallSiteArray[293].call(Console.consoleControllers, new Console$_notifySystemErr_closure15($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console(), (Reference<Object>)str2));
        }
        else {
            $getCallSiteArray[294].call($get$$class$javax$swing$SwingUtilities(), new Console$_notifySystemErr_closure16($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console(), (Reference<Object>)str2));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public void runScript(final EventObject evt) {
        $getCallSiteArray()[295].callCurrent(this, Boolean.FALSE);
    }
    
    public void runSelectedScript(final EventObject evt) {
        $getCallSiteArray()[296].callCurrent(this, Boolean.TRUE);
    }
    
    public void addClasspathJar(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object fc = $getCallSiteArray[297].callConstructor($get$$class$javax$swing$JFileChooser(), this.currentClasspathJarDir);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[298].callGetProperty($get$$class$javax$swing$JFileChooser()), $get$$class$groovy$ui$Console(), fc, "fileSelectionMode");
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console(), fc, "acceptAllFileFilterUsed");
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[299].call(fc, this.frame, "Add"), $getCallSiteArray[300].callGetProperty($get$$class$javax$swing$JFileChooser()))) {
            this.currentClasspathJarDir = (File)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[301].callGetProperty(fc), $get$$class$java$io$File()), $get$$class$java$io$File());
            $getCallSiteArray[302].call($getCallSiteArray[303].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentClasspathJarDir", $getCallSiteArray[304].callGetProperty(this.currentClasspathJarDir));
            $getCallSiteArray[305].call($getCallSiteArray[306].call(this.shell), $getCallSiteArray[307].call($getCallSiteArray[308].callGetProperty(fc)));
        }
    }
    
    public void addClasspathDir(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object fc = $getCallSiteArray[309].callConstructor($get$$class$javax$swing$JFileChooser(), this.currentClasspathDir);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[310].callGetProperty($get$$class$javax$swing$JFileChooser()), $get$$class$groovy$ui$Console(), fc, "fileSelectionMode");
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console(), fc, "acceptAllFileFilterUsed");
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[311].call(fc, this.frame, "Add"), $getCallSiteArray[312].callGetProperty($get$$class$javax$swing$JFileChooser()))) {
            this.currentClasspathDir = (File)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[313].callGetProperty(fc), $get$$class$java$io$File()), $get$$class$java$io$File());
            $getCallSiteArray[314].call($getCallSiteArray[315].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentClasspathDir", $getCallSiteArray[316].callGetProperty(this.currentClasspathDir));
            $getCallSiteArray[317].call($getCallSiteArray[318].call(this.shell), $getCallSiteArray[319].call($getCallSiteArray[320].callGetProperty(fc)));
        }
    }
    
    public void clearContext(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object binding = $getCallSiteArray[321].callConstructor($get$$class$groovy$lang$Binding());
        $getCallSiteArray[322].callCurrent(this, null, binding);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[323].call($get$$class$groovy$ui$OutputTransforms()), $get$$class$groovy$ui$Console(), $getCallSiteArray[324].callGetProperty(binding), "_outputTransforms");
    }
    
    private void runScriptImpl(final boolean selected) {
        final Boolean selected2 = (Boolean)new Reference(DefaultTypeTransformation.box(selected));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.scriptRunning))) {
            ScriptBytecodeAdapter.setProperty("Cannot run script now as a script is already running. Please wait or use \"Interrupt Script\" option.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            return;
        }
        this.scriptRunning = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console(), this.interruptAction, "enabled");
        this.stackOverFlowError = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        final Object endLine = $getCallSiteArray[325].call($get$$class$java$lang$System(), "line.separator");
        final Object record = new Reference($getCallSiteArray[326].callConstructor($get$$class$groovy$ui$HistoryRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "allText", $getCallSiteArray[327].call($getCallSiteArray[328].call(this.inputArea), endLine, "\n"), "selectionStart", DefaultTypeTransformation.box(this.textSelectionStart), "selectionEnd", DefaultTypeTransformation.box(this.textSelectionEnd) })));
        $getCallSiteArray[329].callCurrent(this, ((Reference<Object>)record).get());
        this.pendingRecord = (HistoryRecord)ScriptBytecodeAdapter.castToType($getCallSiteArray[330].callConstructor($get$$class$groovy$ui$HistoryRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "allText", "", "selectionStart", Console.$const$2, "selectionEnd", Console.$const$2 })), $get$$class$groovy$ui$HistoryRecord());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[331].call(Console.prefs, "autoClearOutput", Boolean.FALSE))) {
            $getCallSiteArray[332].callCurrent(this);
        }
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.showScriptInOutput))) {
            Object line = null;
            final Object call = $getCallSiteArray[333].call($getCallSiteArray[334].call($getCallSiteArray[335].call(((Reference<Object>)record).get(), ((Reference<Object>)selected2).get()), "\n"));
            while (((Iterator)call).hasNext()) {
                line = ((Iterator<Object>)call).next();
                $getCallSiteArray[336].callCurrent(this, "groovy> ", this.promptStyle);
                $getCallSiteArray[337].callCurrent(this, line, this.commandStyle);
            }
            $getCallSiteArray[338].callCurrent(this, " \n", this.promptStyle);
        }
        this.runThread = (Thread)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[339].call($get$$class$java$lang$Thread(), new Console$_runScriptImpl_closure17(this, this, (Reference<Object>)record, (Reference<Object>)selected2)), $get$$class$java$lang$Thread()), $get$$class$java$lang$Thread());
    }
    
    public void compileScript(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.scriptRunning))) {
            ScriptBytecodeAdapter.setProperty("Cannot compile script now as a script is already running. Please wait or use \"Interrupt Script\" option.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
            return;
        }
        this.stackOverFlowError = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        final Object endLine = $getCallSiteArray[340].call($get$$class$java$lang$System(), "line.separator");
        final Object record = new Reference($getCallSiteArray[341].callConstructor($get$$class$groovy$ui$HistoryRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "allText", $getCallSiteArray[342].call($getCallSiteArray[343].call(this.inputArea), endLine, "\n"), "selectionStart", DefaultTypeTransformation.box(this.textSelectionStart), "selectionEnd", DefaultTypeTransformation.box(this.textSelectionEnd) })));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[344].call(Console.prefs, "autoClearOutput", Boolean.FALSE))) {
            $getCallSiteArray[345].callCurrent(this);
        }
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.showScriptInOutput))) {
            Object line = null;
            final Object call = $getCallSiteArray[346].call($getCallSiteArray[347].call($getCallSiteArray[348].callGetProperty(((Reference<Object>)record).get()), "\n"));
            while (((Iterator)call).hasNext()) {
                line = ((Iterator<Object>)call).next();
                $getCallSiteArray[349].callCurrent(this, "groovy> ", this.promptStyle);
                $getCallSiteArray[350].callCurrent(this, line, this.commandStyle);
            }
            $getCallSiteArray[351].callCurrent(this, " \n", this.promptStyle);
        }
        this.runThread = (Thread)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[352].call($get$$class$java$lang$Thread(), new Console$_compileScript_closure18(this, this, (Reference<Object>)record)), $get$$class$java$lang$Thread()), $get$$class$java$lang$Thread());
    }
    
    public Object selectFilename(final Object name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object fc = $getCallSiteArray[353].callConstructor($get$$class$javax$swing$JFileChooser(), this.currentFileChooserDir);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[354].callGetProperty($get$$class$javax$swing$JFileChooser()), $get$$class$groovy$ui$Console(), fc, "fileSelectionMode");
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$ui$Console(), fc, "acceptAllFileFilterUsed");
        ScriptBytecodeAdapter.setProperty(Console.groovyFileFilter, $get$$class$groovy$ui$Console(), fc, "fileFilter");
        if (ScriptBytecodeAdapter.compareEqual(name, "Save")) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[355].callConstructor($get$$class$java$io$File(), "*.groovy"), $get$$class$groovy$ui$Console(), fc, "selectedFile");
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[356].call(fc, this.frame, name), $getCallSiteArray[357].callGetProperty($get$$class$javax$swing$JFileChooser()))) {
            this.currentFileChooserDir = (File)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[358].callGetProperty(fc), $get$$class$java$io$File()), $get$$class$java$io$File());
            $getCallSiteArray[359].call($getCallSiteArray[360].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()), "currentFileChooserDir", $getCallSiteArray[361].callGetProperty(this.currentFileChooserDir));
            return $getCallSiteArray[362].callGetProperty(fc);
        }
        return null;
    }
    
    public void setDirty(final boolean newDirty) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.dirty = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(newDirty));
        ScriptBytecodeAdapter.setProperty(DefaultTypeTransformation.box(newDirty), $get$$class$groovy$ui$Console(), this.saveAction, "enabled");
        $getCallSiteArray[363].callCurrent(this);
    }
    
    private void setInputTextFromHistory(final Object newIndex) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object endLine = $getCallSiteArray[364].call($get$$class$java$lang$System(), "line.separator");
        if (ScriptBytecodeAdapter.compareGreaterThanEqual(DefaultTypeTransformation.box(this.historyIndex), $getCallSiteArray[365].call(this.history))) {
            this.pendingRecord = (HistoryRecord)ScriptBytecodeAdapter.castToType($getCallSiteArray[366].callConstructor($get$$class$groovy$ui$HistoryRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "allText", $getCallSiteArray[367].call($getCallSiteArray[368].call(this.inputArea), endLine, "\n"), "selectionStart", DefaultTypeTransformation.box(this.textSelectionStart), "selectionEnd", DefaultTypeTransformation.box(this.textSelectionEnd) })), $get$$class$groovy$ui$HistoryRecord());
        }
        this.historyIndex = DefaultTypeTransformation.intUnbox(newIndex);
        Object record = null;
        if (ScriptBytecodeAdapter.compareLessThan(DefaultTypeTransformation.box(this.historyIndex), $getCallSiteArray[369].call(this.history))) {
            record = $getCallSiteArray[370].call(this.history, DefaultTypeTransformation.box(this.historyIndex));
            ScriptBytecodeAdapter.setProperty(new GStringImpl(new Object[] { $getCallSiteArray[371].call($getCallSiteArray[372].call(this.history), DefaultTypeTransformation.box(this.historyIndex)) }, new String[] { "command history ", "" }), $get$$class$groovy$ui$Console(), this.statusLabel, "text");
        }
        else {
            record = this.pendingRecord;
            ScriptBytecodeAdapter.setProperty("at end of history", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[373].callGetProperty(record), $get$$class$groovy$ui$Console(), this.inputArea, "text");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[374].callGetProperty(record), $get$$class$groovy$ui$Console(), this.inputArea, "selectionStart");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[375].callGetProperty(record), $get$$class$groovy$ui$Console(), this.inputArea, "selectionEnd");
        $getCallSiteArray[376].callCurrent(this, Boolean.TRUE);
        $getCallSiteArray[377].callCurrent(this);
    }
    
    private void updateHistoryActions() {
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.compareLessThan(DefaultTypeTransformation.box(this.historyIndex), $getCallSiteArray()[378].call(this.history)) ? Boolean.TRUE : Boolean.FALSE, $get$$class$groovy$ui$Console(), this.nextHistoryAction, "enabled");
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.compareGreaterThan(DefaultTypeTransformation.box(this.historyIndex), Console.$const$2) ? Boolean.TRUE : Boolean.FALSE, $get$$class$groovy$ui$Console(), this.prevHistoryAction, "enabled");
    }
    
    public void setVariable(final String name, final Object value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[379].call($getCallSiteArray[380].callGroovyObjectGetProperty(this.shell), name, value);
    }
    
    public void showAbout(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object version = $getCallSiteArray[381].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper());
        final Object pane = $getCallSiteArray[382].call(this.swing);
        $getCallSiteArray[383].call(pane, $getCallSiteArray[384].call("Welcome to the Groovy Console for evaluating Groovy scripts\nVersion ", version));
        final Object dialog = $getCallSiteArray[385].call(pane, this.frame, "About GroovyConsole");
        $getCallSiteArray[386].call(dialog);
    }
    
    public void find(final EventObject evt) {
        $getCallSiteArray()[387].call($get$$class$groovy$ui$text$FindReplaceUtility());
    }
    
    public void findNext(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[388].call($getCallSiteArray[389].callGetProperty($get$$class$groovy$ui$text$FindReplaceUtility()), evt);
    }
    
    public void findPrevious(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object reverseEvt = $getCallSiteArray[390].callConstructor($get$$class$java$awt$event$ActionEvent(), ArrayUtil.createArray($getCallSiteArray[391].call(evt), $getCallSiteArray[392].call(evt), $getCallSiteArray[393].call(evt), $getCallSiteArray[394].call(evt), $getCallSiteArray[395].callGetProperty($get$$class$java$awt$event$ActionEvent())));
        $getCallSiteArray[396].call($getCallSiteArray[397].callGetProperty($get$$class$groovy$ui$text$FindReplaceUtility()), reverseEvt);
    }
    
    public void replace(final EventObject evt) {
        $getCallSiteArray()[398].call($get$$class$groovy$ui$text$FindReplaceUtility(), Boolean.TRUE);
    }
    
    public void showExecutingMessage() {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty("Script executing now. Please wait or use \"Interrupt Script\" option.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
    }
    
    public void showCompilingMessage() {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty("Script compiling now. Please wait.", $get$$class$groovy$ui$Console(), this.statusLabel, "text");
    }
    
    public void showOutputWindow(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.detachedOutput))) {
            $getCallSiteArray[399].call(this.outputWindow, this.frame);
            $getCallSiteArray[400].call(this.outputWindow);
        }
    }
    
    public void hideOutputWindow(final EventObject evt) {
        $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.detachedOutput))) {
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$ui$Console(), this.outputWindow, "visible");
        }
    }
    
    public void hideAndClearOutputWindow(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[401].callCurrent(this);
        $getCallSiteArray[402].callCurrent(this);
    }
    
    public void smallerFont(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[403].callCurrent(this, $getCallSiteArray[404].call($getCallSiteArray[405].callGetProperty($getCallSiteArray[406].callGetProperty(this.inputArea)), Console.$const$3));
    }
    
    public void updateTitle() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[407].call($getCallSiteArray[408].callGetProperty(this.frame), "title"))) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.scriptFile, null)) {
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[409].call($getCallSiteArray[410].call($getCallSiteArray[411].callGetProperty(this.scriptFile), DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.dirty)) ? " * " : ""), " - GroovyConsole"), $get$$class$groovy$ui$Console(), this.frame, "title");
            }
            else {
                ScriptBytecodeAdapter.setProperty("GroovyConsole", $get$$class$groovy$ui$Console(), this.frame, "title");
            }
        }
    }
    
    private Object updateFontSize(Object newFontSize) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareGreaterThan(newFontSize, Console.$const$9)) {
            newFontSize = Console.$const$9;
        }
        else if (ScriptBytecodeAdapter.compareLessThan(newFontSize, Console.$const$10)) {
            newFontSize = Console.$const$10;
        }
        $getCallSiteArray[412].call(Console.prefs, "fontSize", newFontSize);
        final Object newFont = $getCallSiteArray[413].callConstructor($get$$class$java$awt$Font(), $getCallSiteArray[414].callGetProperty(this.inputEditor), $getCallSiteArray[415].callGetProperty($get$$class$java$awt$Font()), newFontSize);
        ScriptBytecodeAdapter.setProperty(newFont, $get$$class$groovy$ui$Console(), this.inputArea, "font");
        final Object messageArgument = newFont;
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$ui$Console(), this.outputArea, "font");
        return messageArgument;
    }
    
    public void invokeTextAction(final Object evt, final Object closure, final Object area) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object source = $getCallSiteArray[416].call(evt);
        if (ScriptBytecodeAdapter.compareNotEqual(source, null)) {
            $getCallSiteArray[417].call(closure, area);
        }
    }
    
    public void cut(final EventObject evt) {
        $getCallSiteArray()[418].callCurrent(this, evt, new Console$_cut_closure19(this, this));
    }
    
    public void copy(final EventObject evt) {
        final CallSite callSite = $getCallSiteArray()[419];
        final Console$_copy_closure20 console$_copy_closure20 = new Console$_copy_closure20(this, this);
        Component component;
        if (!DefaultTypeTransformation.booleanUnbox(component = this.copyFromComponent)) {
            component = this.inputArea;
        }
        callSite.callCurrent(this, evt, console$_copy_closure20, component);
    }
    
    public void paste(final EventObject evt) {
        $getCallSiteArray()[420].callCurrent(this, evt, new Console$_paste_closure21(this, this));
    }
    
    public void selectAll(final EventObject evt) {
        $getCallSiteArray()[421].callCurrent(this, evt, new Console$_selectAll_closure22(this, this));
    }
    
    public void setRowNumAndColNum() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.cursorPos = DefaultTypeTransformation.intUnbox($getCallSiteArray[422].call(this.inputArea));
        this.rowNum = DefaultTypeTransformation.intUnbox($getCallSiteArray[423].call($getCallSiteArray[424].call(this.rootElement, DefaultTypeTransformation.box(this.cursorPos)), Console.$const$1));
        final Object rowElement = $getCallSiteArray[425].call(this.rootElement, $getCallSiteArray[426].call(DefaultTypeTransformation.box(this.rowNum), Console.$const$1));
        this.colNum = DefaultTypeTransformation.intUnbox($getCallSiteArray[427].call($getCallSiteArray[428].call(DefaultTypeTransformation.box(this.cursorPos), $getCallSiteArray[429].call(rowElement)), Console.$const$1));
        $getCallSiteArray[430].call(this.rowNumAndColNum, new GStringImpl(new Object[] { DefaultTypeTransformation.box(this.rowNum), DefaultTypeTransformation.box(this.colNum) }, new String[] { "", ":", "" }));
    }
    
    public void print(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[431].call($getCallSiteArray[432].callGetProperty(this.inputEditor), evt);
    }
    
    public void undo(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[433].call($getCallSiteArray[434].callGetProperty(this.inputEditor), evt);
    }
    
    public void redo(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[435].call($getCallSiteArray[436].callGetProperty(this.inputEditor), evt);
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent e) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[437].callGetProperty(e), $getCallSiteArray[438].callGetProperty($get$$class$javax$swing$event$HyperlinkEvent$EventType()))) {
            final String url = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[439].call(e), $get$$class$java$lang$String());
            final Integer lineNumber = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[440].call($getCallSiteArray[441].call(url, ScriptBytecodeAdapter.createRange($getCallSiteArray[442].call($getCallSiteArray[443].call(url, ":"), Console.$const$1), Console.$const$6, true))), $get$$class$java$lang$Integer());
            final Object editor = $getCallSiteArray[444].callGetProperty(this.inputEditor);
            final Object text = $getCallSiteArray[445].callGetProperty(editor);
            Integer newlineBefore = Console.$const$2;
            Integer newlineAfter = Console.$const$2;
            Integer currentLineNumber = Console.$const$1;
            Integer i = Console.$const$2;
            Object ch = null;
            final Object call = $getCallSiteArray[446].call(text);
            while (((Iterator)call).hasNext()) {
                ch = ((Iterator<Object>)call).next();
                if (ScriptBytecodeAdapter.compareEqual(ch, "\n")) {
                    currentLineNumber = (Integer)$getCallSiteArray[447].call(currentLineNumber);
                }
                if (ScriptBytecodeAdapter.compareEqual(currentLineNumber, lineNumber)) {
                    newlineBefore = i;
                    final Object nextNewline = $getCallSiteArray[448].call(text, "\n", $getCallSiteArray[449].call(i, Console.$const$1));
                    newlineAfter = (Integer)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareGreaterThan(nextNewline, Console.$const$6) ? nextNewline : $getCallSiteArray[450].call(text), $get$$class$java$lang$Integer());
                    break;
                }
                i = (Integer)$getCallSiteArray[451].call(i);
            }
            $getCallSiteArray[452].call(editor, newlineBefore);
            $getCallSiteArray[453].call(editor, newlineAfter);
        }
    }
    
    public void componentHidden(final ComponentEvent e) {
        $getCallSiteArray();
    }
    
    public void componentMoved(final ComponentEvent e) {
        $getCallSiteArray();
    }
    
    public void componentResized(final ComponentEvent e) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object component = $getCallSiteArray[454].call(e);
        $getCallSiteArray[455].call(Console.prefs, new GStringImpl(new Object[] { $getCallSiteArray[456].callGetProperty(component) }, new String[] { "", "Width" }), $getCallSiteArray[457].callGetProperty(component));
        $getCallSiteArray[458].call(Console.prefs, new GStringImpl(new Object[] { $getCallSiteArray[459].callGetProperty(component) }, new String[] { "", "Height" }), $getCallSiteArray[460].callGetProperty(component));
    }
    
    public void componentShown(final ComponentEvent e) {
        $getCallSiteArray();
    }
    
    public void focusGained(final FocusEvent e) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual($getCallSiteArray[461].callGetProperty(e), this.outputArea) && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[462].callGetProperty(e), this.inputArea)) ? Boolean.FALSE : Boolean.TRUE)) {
            this.copyFromComponent = (Component)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[463].callGetProperty(e), $get$$class$java$awt$Component()), $get$$class$java$awt$Component());
        }
    }
    
    public void focusLost(final FocusEvent e) {
        $getCallSiteArray();
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$Console()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Console.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Console.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public void clearOutput() {
        $getCallSiteArray()[464].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void doInterrupt() {
        $getCallSiteArray()[465].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void exit() {
        $getCallSiteArray()[466].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void fileNewFile() {
        $getCallSiteArray()[467].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void fileNewWindow() {
        $getCallSiteArray()[468].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void fileOpen() {
        $getCallSiteArray()[469].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public boolean fileSave() {
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray()[470].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject())), $get$$class$java$lang$Boolean()));
    }
    
    public boolean fileSaveAs() {
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray()[471].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject())), $get$$class$java$lang$Boolean()));
    }
    
    public void historyNext() {
        $getCallSiteArray()[472].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void historyPrev() {
        $getCallSiteArray()[473].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void inspectLast() {
        $getCallSiteArray()[474].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void inspectVariables() {
        $getCallSiteArray()[475].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void inspectAst() {
        $getCallSiteArray()[476].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void largerFont() {
        $getCallSiteArray()[477].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void runScript() {
        $getCallSiteArray()[478].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void runSelectedScript() {
        $getCallSiteArray()[479].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void addClasspathJar() {
        $getCallSiteArray()[480].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void addClasspathDir() {
        $getCallSiteArray()[481].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void clearContext() {
        $getCallSiteArray()[482].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void compileScript() {
        $getCallSiteArray()[483].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public Object selectFilename() {
        return $getCallSiteArray()[484].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper("Open", $get$$class$java$lang$Object()));
    }
    
    public void showAbout() {
        $getCallSiteArray()[485].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void find() {
        $getCallSiteArray()[486].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void findNext() {
        $getCallSiteArray()[487].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void findPrevious() {
        $getCallSiteArray()[488].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void replace() {
        $getCallSiteArray()[489].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void showOutputWindow() {
        $getCallSiteArray()[490].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void hideOutputWindow() {
        $getCallSiteArray()[491].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void hideAndClearOutputWindow() {
        $getCallSiteArray()[492].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void smallerFont() {
        $getCallSiteArray()[493].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void invokeTextAction(final Object evt, final Object closure) {
        $getCallSiteArray()[494].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(evt, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createPojoWrapper(closure, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createPojoWrapper(this.inputArea, $get$$class$java$lang$Object()));
    }
    
    public void cut() {
        $getCallSiteArray()[495].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void copy() {
        $getCallSiteArray()[496].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void paste() {
        $getCallSiteArray()[497].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void selectAll() {
        $getCallSiteArray()[498].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void print() {
        $getCallSiteArray()[499].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void undo() {
        $getCallSiteArray()[500].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void redo() {
        $getCallSiteArray()[501].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    static {
        Console.__timeStamp__239_neverHappen1292524203161 = 0L;
        Console.__timeStamp = 1292524203161L;
        $const$10 = 4;
        $const$9 = 40;
        $const$8 = 60;
        $const$7 = 120;
        $const$6 = -1;
        $const$5 = new BigDecimal("0.5");
        $const$4 = new BigDecimal("1.0");
        $const$3 = 2;
        $const$2 = 0;
        $const$1 = 1;
        $const$0 = 10;
        Console.NODE_ICON_PATH = "/groovy/ui/icons/bullet_green.png";
        Console.ICON_PATH = "/groovy/ui/ConsoleIcon.png";
        DEFAULT_SCRIPT_NAME_START = "ConsoleScript";
        Console.prefs = $getCallSiteArray()[502].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console());
        Console.captureStdOut = DefaultTypeTransformation.booleanUnbox($getCallSiteArray()[503].call(Console.prefs, "captureStdOut", Boolean.TRUE));
        Console.captureStdErr = DefaultTypeTransformation.booleanUnbox($getCallSiteArray()[504].call(Console.prefs, "captureStdErr", Boolean.TRUE));
        Console.consoleControllers = ScriptBytecodeAdapter.createList(new Object[0]);
        Console.groovyFileFilter = $getCallSiteArray()[505].callConstructor($get$$class$groovy$ui$GroovyFileFilter());
        Console.frameConsoleDelegates = ScriptBytecodeAdapter.createMap(new Object[] { "rootContainerDelegate", new Console$__clinit__closure23($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console()), "menuBarDelegate", new Console$__clinit__closure24($get$$class$groovy$ui$Console(), $get$$class$groovy$ui$Console()) });
    }
    
    public static final String getDEFAULT_SCRIPT_NAME_START() {
        return Console.DEFAULT_SCRIPT_NAME_START;
    }
    
    public static boolean getCaptureStdOut() {
        return Console.captureStdOut;
    }
    
    public static boolean isCaptureStdOut() {
        return Console.captureStdOut;
    }
    
    public static void setCaptureStdOut(final boolean captureStdOut) {
        Console.captureStdOut = captureStdOut;
    }
    
    public static boolean getCaptureStdErr() {
        return Console.captureStdErr;
    }
    
    public static boolean isCaptureStdErr() {
        return Console.captureStdErr;
    }
    
    public static void setCaptureStdErr(final boolean captureStdErr) {
        Console.captureStdErr = captureStdErr;
    }
    
    public static Object getConsoleControllers() {
        return Console.consoleControllers;
    }
    
    public static void setConsoleControllers(final Object consoleControllers) {
        Console.consoleControllers = consoleControllers;
    }
    
    public boolean getFullStackTraces() {
        return this.fullStackTraces;
    }
    
    public boolean isFullStackTraces() {
        return this.fullStackTraces;
    }
    
    public void setFullStackTraces(final boolean fullStackTraces) {
        this.fullStackTraces = fullStackTraces;
    }
    
    public Action getFullStackTracesAction() {
        return this.fullStackTracesAction;
    }
    
    public void setFullStackTracesAction(final Action fullStackTracesAction) {
        this.fullStackTracesAction = fullStackTracesAction;
    }
    
    public boolean getShowScriptInOutput() {
        return this.showScriptInOutput;
    }
    
    public boolean isShowScriptInOutput() {
        return this.showScriptInOutput;
    }
    
    public void setShowScriptInOutput(final boolean showScriptInOutput) {
        this.showScriptInOutput = showScriptInOutput;
    }
    
    public Action getShowScriptInOutputAction() {
        return this.showScriptInOutputAction;
    }
    
    public void setShowScriptInOutputAction(final Action showScriptInOutputAction) {
        this.showScriptInOutputAction = showScriptInOutputAction;
    }
    
    public boolean getVisualizeScriptResults() {
        return this.visualizeScriptResults;
    }
    
    public boolean isVisualizeScriptResults() {
        return this.visualizeScriptResults;
    }
    
    public void setVisualizeScriptResults(final boolean visualizeScriptResults) {
        this.visualizeScriptResults = visualizeScriptResults;
    }
    
    public Action getVisualizeScriptResultsAction() {
        return this.visualizeScriptResultsAction;
    }
    
    public void setVisualizeScriptResultsAction(final Action visualizeScriptResultsAction) {
        this.visualizeScriptResultsAction = visualizeScriptResultsAction;
    }
    
    public boolean getShowToolbar() {
        return this.showToolbar;
    }
    
    public boolean isShowToolbar() {
        return this.showToolbar;
    }
    
    public void setShowToolbar(final boolean showToolbar) {
        this.showToolbar = showToolbar;
    }
    
    public Component getToolbar() {
        return this.toolbar;
    }
    
    public void setToolbar(final Component toolbar) {
        this.toolbar = toolbar;
    }
    
    public Action getShowToolbarAction() {
        return this.showToolbarAction;
    }
    
    public void setShowToolbarAction(final Action showToolbarAction) {
        this.showToolbarAction = showToolbarAction;
    }
    
    public boolean getDetachedOutput() {
        return this.detachedOutput;
    }
    
    public boolean isDetachedOutput() {
        return this.detachedOutput;
    }
    
    public void setDetachedOutput(final boolean detachedOutput) {
        this.detachedOutput = detachedOutput;
    }
    
    public Action getDetachedOutputAction() {
        return this.detachedOutputAction;
    }
    
    public void setDetachedOutputAction(final Action detachedOutputAction) {
        this.detachedOutputAction = detachedOutputAction;
    }
    
    public Action getShowOutputWindowAction() {
        return this.showOutputWindowAction;
    }
    
    public void setShowOutputWindowAction(final Action showOutputWindowAction) {
        this.showOutputWindowAction = showOutputWindowAction;
    }
    
    public Action getHideOutputWindowAction1() {
        return this.hideOutputWindowAction1;
    }
    
    public void setHideOutputWindowAction1(final Action hideOutputWindowAction1) {
        this.hideOutputWindowAction1 = hideOutputWindowAction1;
    }
    
    public Action getHideOutputWindowAction2() {
        return this.hideOutputWindowAction2;
    }
    
    public void setHideOutputWindowAction2(final Action hideOutputWindowAction2) {
        this.hideOutputWindowAction2 = hideOutputWindowAction2;
    }
    
    public Action getHideOutputWindowAction3() {
        return this.hideOutputWindowAction3;
    }
    
    public void setHideOutputWindowAction3(final Action hideOutputWindowAction3) {
        this.hideOutputWindowAction3 = hideOutputWindowAction3;
    }
    
    public Action getHideOutputWindowAction4() {
        return this.hideOutputWindowAction4;
    }
    
    public void setHideOutputWindowAction4(final Action hideOutputWindowAction4) {
        this.hideOutputWindowAction4 = hideOutputWindowAction4;
    }
    
    public int getOrigDividerSize() {
        return this.origDividerSize;
    }
    
    public void setOrigDividerSize(final int origDividerSize) {
        this.origDividerSize = origDividerSize;
    }
    
    public Component getOutputWindow() {
        return this.outputWindow;
    }
    
    public void setOutputWindow(final Component outputWindow) {
        this.outputWindow = outputWindow;
    }
    
    public Component getCopyFromComponent() {
        return this.copyFromComponent;
    }
    
    public void setCopyFromComponent(final Component copyFromComponent) {
        this.copyFromComponent = copyFromComponent;
    }
    
    public Component getBlank() {
        return this.blank;
    }
    
    public void setBlank(final Component blank) {
        this.blank = blank;
    }
    
    public Component getScrollArea() {
        return this.scrollArea;
    }
    
    public void setScrollArea(final Component scrollArea) {
        this.scrollArea = scrollArea;
    }
    
    public boolean getAutoClearOutput() {
        return this.autoClearOutput;
    }
    
    public boolean isAutoClearOutput() {
        return this.autoClearOutput;
    }
    
    public void setAutoClearOutput(final boolean autoClearOutput) {
        this.autoClearOutput = autoClearOutput;
    }
    
    public Action getAutoClearOutputAction() {
        return this.autoClearOutputAction;
    }
    
    public void setAutoClearOutputAction(final Action autoClearOutputAction) {
        this.autoClearOutputAction = autoClearOutputAction;
    }
    
    public int getMaxHistory() {
        return this.maxHistory;
    }
    
    public void setMaxHistory(final int maxHistory) {
        this.maxHistory = maxHistory;
    }
    
    public int getMaxOutputChars() {
        return this.maxOutputChars;
    }
    
    public void setMaxOutputChars(final int maxOutputChars) {
        this.maxOutputChars = maxOutputChars;
    }
    
    public SwingBuilder getSwing() {
        return this.swing;
    }
    
    public void setSwing(final SwingBuilder swing) {
        this.swing = swing;
    }
    
    public RootPaneContainer getFrame() {
        return this.frame;
    }
    
    public void setFrame(final RootPaneContainer frame) {
        this.frame = frame;
    }
    
    public ConsoleTextEditor getInputEditor() {
        return this.inputEditor;
    }
    
    public void setInputEditor(final ConsoleTextEditor inputEditor) {
        this.inputEditor = inputEditor;
    }
    
    public JSplitPane getSplitPane() {
        return this.splitPane;
    }
    
    public void setSplitPane(final JSplitPane splitPane) {
        this.splitPane = splitPane;
    }
    
    public JTextPane getInputArea() {
        return this.inputArea;
    }
    
    public void setInputArea(final JTextPane inputArea) {
        this.inputArea = inputArea;
    }
    
    public JTextPane getOutputArea() {
        return this.outputArea;
    }
    
    public void setOutputArea(final JTextPane outputArea) {
        this.outputArea = outputArea;
    }
    
    public JLabel getStatusLabel() {
        return this.statusLabel;
    }
    
    public void setStatusLabel(final JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }
    
    public JLabel getRowNumAndColNum() {
        return this.rowNumAndColNum;
    }
    
    public void setRowNumAndColNum(final JLabel rowNumAndColNum) {
        this.rowNumAndColNum = rowNumAndColNum;
    }
    
    public Element getRootElement() {
        return this.rootElement;
    }
    
    public void setRootElement(final Element rootElement) {
        this.rootElement = rootElement;
    }
    
    public int getCursorPos() {
        return this.cursorPos;
    }
    
    public void setCursorPos(final int cursorPos) {
        this.cursorPos = cursorPos;
    }
    
    public int getRowNum() {
        return this.rowNum;
    }
    
    public void setRowNum(final int rowNum) {
        this.rowNum = rowNum;
    }
    
    public int getColNum() {
        return this.colNum;
    }
    
    public void setColNum(final int colNum) {
        this.colNum = colNum;
    }
    
    public Style getPromptStyle() {
        return this.promptStyle;
    }
    
    public void setPromptStyle(final Style promptStyle) {
        this.promptStyle = promptStyle;
    }
    
    public Style getCommandStyle() {
        return this.commandStyle;
    }
    
    public void setCommandStyle(final Style commandStyle) {
        this.commandStyle = commandStyle;
    }
    
    public Style getOutputStyle() {
        return this.outputStyle;
    }
    
    public void setOutputStyle(final Style outputStyle) {
        this.outputStyle = outputStyle;
    }
    
    public Style getStacktraceStyle() {
        return this.stacktraceStyle;
    }
    
    public void setStacktraceStyle(final Style stacktraceStyle) {
        this.stacktraceStyle = stacktraceStyle;
    }
    
    public Style getHyperlinkStyle() {
        return this.hyperlinkStyle;
    }
    
    public void setHyperlinkStyle(final Style hyperlinkStyle) {
        this.hyperlinkStyle = hyperlinkStyle;
    }
    
    public Style getResultStyle() {
        return this.resultStyle;
    }
    
    public void setResultStyle(final Style resultStyle) {
        this.resultStyle = resultStyle;
    }
    
    public List getHistory() {
        return this.history;
    }
    
    public void setHistory(final List history) {
        this.history = history;
    }
    
    public int getHistoryIndex() {
        return this.historyIndex;
    }
    
    public void setHistoryIndex(final int historyIndex) {
        this.historyIndex = historyIndex;
    }
    
    public HistoryRecord getPendingRecord() {
        return this.pendingRecord;
    }
    
    public void setPendingRecord(final HistoryRecord pendingRecord) {
        this.pendingRecord = pendingRecord;
    }
    
    public Action getPrevHistoryAction() {
        return this.prevHistoryAction;
    }
    
    public void setPrevHistoryAction(final Action prevHistoryAction) {
        this.prevHistoryAction = prevHistoryAction;
    }
    
    public Action getNextHistoryAction() {
        return this.nextHistoryAction;
    }
    
    public void setNextHistoryAction(final Action nextHistoryAction) {
        this.nextHistoryAction = nextHistoryAction;
    }
    
    public boolean getDirty() {
        return this.dirty;
    }
    
    public boolean isDirty() {
        return this.dirty;
    }
    
    public Action getSaveAction() {
        return this.saveAction;
    }
    
    public void setSaveAction(final Action saveAction) {
        this.saveAction = saveAction;
    }
    
    public int getTextSelectionStart() {
        return this.textSelectionStart;
    }
    
    public void setTextSelectionStart(final int textSelectionStart) {
        this.textSelectionStart = textSelectionStart;
    }
    
    public int getTextSelectionEnd() {
        return this.textSelectionEnd;
    }
    
    public void setTextSelectionEnd(final int textSelectionEnd) {
        this.textSelectionEnd = textSelectionEnd;
    }
    
    public Object getScriptFile() {
        return this.scriptFile;
    }
    
    public void setScriptFile(final Object scriptFile) {
        this.scriptFile = scriptFile;
    }
    
    public File getCurrentFileChooserDir() {
        return this.currentFileChooserDir;
    }
    
    public void setCurrentFileChooserDir(final File currentFileChooserDir) {
        this.currentFileChooserDir = currentFileChooserDir;
    }
    
    public File getCurrentClasspathJarDir() {
        return this.currentClasspathJarDir;
    }
    
    public void setCurrentClasspathJarDir(final File currentClasspathJarDir) {
        this.currentClasspathJarDir = currentClasspathJarDir;
    }
    
    public File getCurrentClasspathDir() {
        return this.currentClasspathDir;
    }
    
    public void setCurrentClasspathDir(final File currentClasspathDir) {
        this.currentClasspathDir = currentClasspathDir;
    }
    
    public GroovyShell getShell() {
        return this.shell;
    }
    
    public void setShell(final GroovyShell shell) {
        this.shell = shell;
    }
    
    public int getScriptNameCounter() {
        return this.scriptNameCounter;
    }
    
    public void setScriptNameCounter(final int scriptNameCounter) {
        this.scriptNameCounter = scriptNameCounter;
    }
    
    public SystemOutputInterceptor getSystemOutInterceptor() {
        return this.systemOutInterceptor;
    }
    
    public void setSystemOutInterceptor(final SystemOutputInterceptor systemOutInterceptor) {
        this.systemOutInterceptor = systemOutInterceptor;
    }
    
    public SystemOutputInterceptor getSystemErrorInterceptor() {
        return this.systemErrorInterceptor;
    }
    
    public void setSystemErrorInterceptor(final SystemOutputInterceptor systemErrorInterceptor) {
        this.systemErrorInterceptor = systemErrorInterceptor;
    }
    
    public Thread getRunThread() {
        return this.runThread;
    }
    
    public void setRunThread(final Thread runThread) {
        this.runThread = runThread;
    }
    
    public Closure getBeforeExecution() {
        return this.beforeExecution;
    }
    
    public void setBeforeExecution(final Closure beforeExecution) {
        this.beforeExecution = beforeExecution;
    }
    
    public Closure getAfterExecution() {
        return this.afterExecution;
    }
    
    public void setAfterExecution(final Closure afterExecution) {
        this.afterExecution = afterExecution;
    }
    
    public Action getInterruptAction() {
        return this.interruptAction;
    }
    
    public void setInterruptAction(final Action interruptAction) {
        this.interruptAction = interruptAction;
    }
    
    public static Object getFrameConsoleDelegates() {
        return Console.frameConsoleDelegates;
    }
    
    public static void setFrameConsoleDelegates(final Object frameConsoleDelegates) {
        Console.frameConsoleDelegates = frameConsoleDelegates;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[506];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console.$callSiteArray == null || ($createCallSiteArray = Console.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$IvyListener() {
        Class $class$org$apache$ivy$core$event$IvyListener;
        if (($class$org$apache$ivy$core$event$IvyListener = Console.$class$org$apache$ivy$core$event$IvyListener) == null) {
            $class$org$apache$ivy$core$event$IvyListener = (Console.$class$org$apache$ivy$core$event$IvyListener = class$("org.apache.ivy.core.event.IvyListener"));
        }
        return $class$org$apache$ivy$core$event$IvyListener;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dimension() {
        Class $class$java$awt$Dimension;
        if (($class$java$awt$Dimension = Console.$class$java$awt$Dimension) == null) {
            $class$java$awt$Dimension = (Console.$class$java$awt$Dimension = class$("java.awt.Dimension"));
        }
        return $class$java$awt$Dimension;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = Console.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (Console.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleView() {
        Class $class$groovy$ui$ConsoleView;
        if (($class$groovy$ui$ConsoleView = Console.$class$groovy$ui$ConsoleView) == null) {
            $class$groovy$ui$ConsoleView = (Console.$class$groovy$ui$ConsoleView = class$("groovy.ui.ConsoleView"));
        }
        return $class$groovy$ui$ConsoleView;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSplitPane() {
        Class $class$javax$swing$JSplitPane;
        if (($class$javax$swing$JSplitPane = Console.$class$javax$swing$JSplitPane) == null) {
            $class$javax$swing$JSplitPane = (Console.$class$javax$swing$JSplitPane = class$("javax.swing.JSplitPane"));
        }
        return $class$javax$swing$JSplitPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ObjectBrowser() {
        Class $class$groovy$inspect$swingui$ObjectBrowser;
        if (($class$groovy$inspect$swingui$ObjectBrowser = Console.$class$groovy$inspect$swingui$ObjectBrowser) == null) {
            $class$groovy$inspect$swingui$ObjectBrowser = (Console.$class$groovy$inspect$swingui$ObjectBrowser = class$("groovy.inspect.swingui.ObjectBrowser"));
        }
        return $class$groovy$inspect$swingui$ObjectBrowser;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Math() {
        Class $class$java$lang$Math;
        if (($class$java$lang$Math = Console.$class$java$lang$Math) == null) {
            $class$java$lang$Math = (Console.$class$java$lang$Math = class$("java.lang.Math"));
        }
        return $class$java$lang$Math;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console() {
        Class $class$groovy$ui$Console;
        if (($class$groovy$ui$Console = Console.$class$groovy$ui$Console) == null) {
            $class$groovy$ui$Console = (Console.$class$groovy$ui$Console = class$("groovy.ui.Console"));
        }
        return $class$groovy$ui$Console;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Console.$class$java$lang$String) == null) {
            $class$java$lang$String = (Console.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = Console.$class$java$util$Set) == null) {
            $class$java$util$Set = (Console.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = Console.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (Console.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$MultipleCompilationErrorsException() {
        Class $class$org$codehaus$groovy$control$MultipleCompilationErrorsException;
        if (($class$org$codehaus$groovy$control$MultipleCompilationErrorsException = Console.$class$org$codehaus$groovy$control$MultipleCompilationErrorsException) == null) {
            $class$org$codehaus$groovy$control$MultipleCompilationErrorsException = (Console.$class$org$codehaus$groovy$control$MultipleCompilationErrorsException = class$("org.codehaus.groovy.control.MultipleCompilationErrorsException"));
        }
        return $class$org$codehaus$groovy$control$MultipleCompilationErrorsException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Thread() {
        Class $class$java$lang$Thread;
        if (($class$java$lang$Thread = Console.$class$java$lang$Thread) == null) {
            $class$java$lang$Thread = (Console.$class$java$lang$Thread = class$("java.lang.Thread"));
        }
        return $class$java$lang$Thread;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$event$ActionEvent() {
        Class $class$java$awt$event$ActionEvent;
        if (($class$java$awt$event$ActionEvent = Console.$class$java$awt$event$ActionEvent) == null) {
            $class$java$awt$event$ActionEvent = (Console.$class$java$awt$event$ActionEvent = class$("java.awt.event.ActionEvent"));
        }
        return $class$java$awt$event$ActionEvent;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = Console.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (Console.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$SimpleAttributeSet() {
        Class $class$javax$swing$text$SimpleAttributeSet;
        if (($class$javax$swing$text$SimpleAttributeSet = Console.$class$javax$swing$text$SimpleAttributeSet) == null) {
            $class$javax$swing$text$SimpleAttributeSet = (Console.$class$javax$swing$text$SimpleAttributeSet = class$("javax.swing.text.SimpleAttributeSet"));
        }
        return $class$javax$swing$text$SimpleAttributeSet;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$prefs$Preferences() {
        Class $class$java$util$prefs$Preferences;
        if (($class$java$util$prefs$Preferences = Console.$class$java$util$prefs$Preferences) == null) {
            $class$java$util$prefs$Preferences = (Console.$class$java$util$prefs$Preferences = class$("java.util.prefs.Preferences"));
        }
        return $class$java$util$prefs$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SwingUtilities() {
        Class $class$javax$swing$SwingUtilities;
        if (($class$javax$swing$SwingUtilities = Console.$class$javax$swing$SwingUtilities) == null) {
            $class$javax$swing$SwingUtilities = (Console.$class$javax$swing$SwingUtilities = class$("javax.swing.SwingUtilities"));
        }
        return $class$javax$swing$SwingUtilities;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$SystemOutputInterceptor() {
        Class $class$groovy$ui$SystemOutputInterceptor;
        if (($class$groovy$ui$SystemOutputInterceptor = Console.$class$groovy$ui$SystemOutputInterceptor) == null) {
            $class$groovy$ui$SystemOutputInterceptor = (Console.$class$groovy$ui$SystemOutputInterceptor = class$("groovy.ui.SystemOutputInterceptor"));
        }
        return $class$groovy$ui$SystemOutputInterceptor;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Toolkit() {
        Class $class$java$awt$Toolkit;
        if (($class$java$awt$Toolkit = Console.$class$java$awt$Toolkit) == null) {
            $class$java$awt$Toolkit = (Console.$class$java$awt$Toolkit = class$("java.awt.Toolkit"));
        }
        return $class$java$awt$Toolkit;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$StackTraceUtils() {
        Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
        if (($class$org$codehaus$groovy$runtime$StackTraceUtils = Console.$class$org$codehaus$groovy$runtime$StackTraceUtils) == null) {
            $class$org$codehaus$groovy$runtime$StackTraceUtils = (Console.$class$org$codehaus$groovy$runtime$StackTraceUtils = class$("org.codehaus.groovy.runtime.StackTraceUtils"));
        }
        return $class$org$codehaus$groovy$runtime$StackTraceUtils;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Component() {
        Class $class$java$awt$Component;
        if (($class$java$awt$Component = Console.$class$java$awt$Component) == null) {
            $class$java$awt$Component = (Console.$class$java$awt$Component = class$("java.awt.Component"));
        }
        return $class$java$awt$Component;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = Console.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (Console.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$text$FindReplaceUtility() {
        Class $class$groovy$ui$text$FindReplaceUtility;
        if (($class$groovy$ui$text$FindReplaceUtility = Console.$class$groovy$ui$text$FindReplaceUtility) == null) {
            $class$groovy$ui$text$FindReplaceUtility = (Console.$class$groovy$ui$text$FindReplaceUtility = class$("groovy.ui.text.FindReplaceUtility"));
        }
        return $class$groovy$ui$text$FindReplaceUtility;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = Console.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (Console.$class$java$util$HashMap = class$("java.util.HashMap"));
        }
        return $class$java$util$HashMap;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$EventQueue() {
        Class $class$java$awt$EventQueue;
        if (($class$java$awt$EventQueue = Console.$class$java$awt$EventQueue) == null) {
            $class$java$awt$EventQueue = (Console.$class$java$awt$EventQueue = class$("java.awt.EventQueue"));
        }
        return $class$java$awt$EventQueue;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowser() {
        Class $class$groovy$inspect$swingui$AstBrowser;
        if (($class$groovy$inspect$swingui$AstBrowser = Console.$class$groovy$inspect$swingui$AstBrowser) == null) {
            $class$groovy$inspect$swingui$AstBrowser = (Console.$class$groovy$inspect$swingui$AstBrowser = class$("groovy.inspect.swingui.AstBrowser"));
        }
        return $class$groovy$inspect$swingui$AstBrowser;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$EventObject() {
        Class $class$java$util$EventObject;
        if (($class$java$util$EventObject = Console.$class$java$util$EventObject) == null) {
            $class$java$util$EventObject = (Console.$class$java$util$EventObject = class$("java.util.EventObject"));
        }
        return $class$java$util$EventObject;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$StringWriter() {
        Class $class$java$io$StringWriter;
        if (($class$java$io$StringWriter = Console.$class$java$io$StringWriter) == null) {
            $class$java$io$StringWriter = (Console.$class$java$io$StringWriter = class$("java.io.StringWriter"));
        }
        return $class$java$io$StringWriter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms() {
        Class $class$groovy$ui$OutputTransforms;
        if (($class$groovy$ui$OutputTransforms = Console.$class$groovy$ui$OutputTransforms) == null) {
            $class$groovy$ui$OutputTransforms = (Console.$class$groovy$ui$OutputTransforms = class$("groovy.ui.OutputTransforms"));
        }
        return $class$groovy$ui$OutputTransforms;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleActions() {
        Class $class$groovy$ui$ConsoleActions;
        if (($class$groovy$ui$ConsoleActions = Console.$class$groovy$ui$ConsoleActions) == null) {
            $class$groovy$ui$ConsoleActions = (Console.$class$groovy$ui$ConsoleActions = class$("groovy.ui.ConsoleActions"));
        }
        return $class$groovy$ui$ConsoleActions;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Console.$class$java$util$List) == null) {
            $class$java$util$List = (Console.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Console.$class$java$io$File) == null) {
            $class$java$io$File = (Console.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$GroovyFileFilter() {
        Class $class$groovy$ui$GroovyFileFilter;
        if (($class$groovy$ui$GroovyFileFilter = Console.$class$groovy$ui$GroovyFileFilter) == null) {
            $class$groovy$ui$GroovyFileFilter = (Console.$class$groovy$ui$GroovyFileFilter = class$("groovy.ui.GroovyFileFilter"));
        }
        return $class$groovy$ui$GroovyFileFilter;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleConstants() {
        Class $class$javax$swing$text$StyleConstants;
        if (($class$javax$swing$text$StyleConstants = Console.$class$javax$swing$text$StyleConstants) == null) {
            $class$javax$swing$text$StyleConstants = (Console.$class$javax$swing$text$StyleConstants = class$("javax.swing.text.StyleConstants"));
        }
        return $class$javax$swing$text$StyleConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$GrapeIvy() {
        Class $class$groovy$grape$GrapeIvy;
        if (($class$groovy$grape$GrapeIvy = Console.$class$groovy$grape$GrapeIvy) == null) {
            $class$groovy$grape$GrapeIvy = (Console.$class$groovy$grape$GrapeIvy = class$("groovy.grape.GrapeIvy"));
        }
        return $class$groovy$grape$GrapeIvy;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFileChooser() {
        Class $class$javax$swing$JFileChooser;
        if (($class$javax$swing$JFileChooser = Console.$class$javax$swing$JFileChooser) == null) {
            $class$javax$swing$JFileChooser = (Console.$class$javax$swing$JFileChooser = class$("javax.swing.JFileChooser"));
        }
        return $class$javax$swing$JFileChooser;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = Console.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (Console.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$HistoryRecord() {
        Class $class$groovy$ui$HistoryRecord;
        if (($class$groovy$ui$HistoryRecord = Console.$class$groovy$ui$HistoryRecord) == null) {
            $class$groovy$ui$HistoryRecord = (Console.$class$groovy$ui$HistoryRecord = class$("groovy.ui.HistoryRecord"));
        }
        return $class$groovy$ui$HistoryRecord;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$BorderLayout() {
        Class $class$java$awt$BorderLayout;
        if (($class$java$awt$BorderLayout = Console.$class$java$awt$BorderLayout) == null) {
            $class$java$awt$BorderLayout = (Console.$class$java$awt$BorderLayout = class$("java.awt.BorderLayout"));
        }
        return $class$java$awt$BorderLayout;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = Console.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (Console.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$ErrorCollector() {
        Class $class$org$codehaus$groovy$control$ErrorCollector;
        if (($class$org$codehaus$groovy$control$ErrorCollector = Console.$class$org$codehaus$groovy$control$ErrorCollector) == null) {
            $class$org$codehaus$groovy$control$ErrorCollector = (Console.$class$org$codehaus$groovy$control$ErrorCollector = class$("org.codehaus.groovy.control.ErrorCollector"));
        }
        return $class$org$codehaus$groovy$control$ErrorCollector;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Console.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Console.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$Grape() {
        Class $class$groovy$grape$Grape;
        if (($class$groovy$grape$Grape = Console.$class$groovy$grape$Grape) == null) {
            $class$groovy$grape$Grape = (Console.$class$groovy$grape$Grape = class$("groovy.grape.Grape"));
        }
        return $class$groovy$grape$Grape;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$event$HyperlinkEvent$EventType() {
        Class $class$javax$swing$event$HyperlinkEvent$EventType;
        if (($class$javax$swing$event$HyperlinkEvent$EventType = Console.$class$javax$swing$event$HyperlinkEvent$EventType) == null) {
            $class$javax$swing$event$HyperlinkEvent$EventType = (Console.$class$javax$swing$event$HyperlinkEvent$EventType = class$("javax.swing.event.HyperlinkEvent$EventType"));
        }
        return $class$javax$swing$event$HyperlinkEvent$EventType;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Console.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Console.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = Console.$class$java$lang$System) == null) {
            $class$java$lang$System = (Console.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JOptionPane() {
        Class $class$javax$swing$JOptionPane;
        if (($class$javax$swing$JOptionPane = Console.$class$javax$swing$JOptionPane) == null) {
            $class$javax$swing$JOptionPane = (Console.$class$javax$swing$JOptionPane = class$("javax.swing.JOptionPane"));
        }
        return $class$javax$swing$JOptionPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = Console.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (Console.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = Console.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (Console.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
        }
        return $class$java$io$PrintWriter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyShell() {
        Class $class$groovy$lang$GroovyShell;
        if (($class$groovy$lang$GroovyShell = Console.$class$groovy$lang$GroovyShell) == null) {
            $class$groovy$lang$GroovyShell = (Console.$class$groovy$lang$GroovyShell = class$("groovy.lang.GroovyShell"));
        }
        return $class$groovy$lang$GroovyShell;
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
