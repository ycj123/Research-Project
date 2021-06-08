// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.EventObject;
import javax.swing.event.TreeSelectionListener;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class AstBrowser implements GroovyObject
{
    private Object inputArea;
    private Object rootElement;
    private Object decompiledSource;
    private Object jTree;
    private Object propertyTable;
    private boolean showScriptFreeForm;
    private boolean showScriptClass;
    private GroovyClassLoader classLoader;
    private Object prefs;
    private Object swing;
    private Object frame;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202609;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$EventObject;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$java$awt$Cursor;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$javax$swing$tree$TreeSelectionModel;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$ui$Console;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowserUiPreferences;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    private static /* synthetic */ Class $class$javax$swing$event$TreeSelectionListener;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    private static /* synthetic */ Class $class$javax$swing$WindowConstants;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowser;
    
    public AstBrowser(final Object inputArea, final Object rootElement, final Object classLoader) {
        this.prefs = $getCallSiteArray()[0].callConstructor($get$$class$groovy$inspect$swingui$AstBrowserUiPreferences());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.inputArea = inputArea;
        this.rootElement = rootElement;
        this.classLoader = (GroovyClassLoader)ScriptBytecodeAdapter.castToType(classLoader, $get$$class$groovy$lang$GroovyClassLoader());
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(args)) {
            $getCallSiteArray[1].callStatic($get$$class$groovy$inspect$swingui$AstBrowser(), "Usage: java groovy.inspect.swingui.AstBrowser [filename]\nwhere [filename] is a Groovy script");
        }
        else {
            final Object file = new Reference($getCallSiteArray[2].callConstructor($get$$class$java$io$File(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(args, AstBrowser.$const$0), $get$$class$java$lang$String()), $get$$class$java$lang$String())));
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(((Reference<Object>)file).get()))) {
                $getCallSiteArray[5].callStatic($get$$class$groovy$inspect$swingui$AstBrowser(), new GStringImpl(new Object[] { args }, new String[] { "File ", "[0] cannot be found." }));
            }
            else {
                $getCallSiteArray[6].call($get$$class$javax$swing$UIManager(), $getCallSiteArray[7].call($get$$class$javax$swing$UIManager()));
                $getCallSiteArray[8].call($getCallSiteArray[9].callConstructor($get$$class$groovy$inspect$swingui$AstBrowser(), null, null, $getCallSiteArray[10].callConstructor($get$$class$groovy$lang$GroovyClassLoader())), new AstBrowser$_main_closure1($get$$class$groovy$inspect$swingui$AstBrowser(), $get$$class$groovy$inspect$swingui$AstBrowser(), (Reference<Object>)file), $getCallSiteArray[11].callGetProperty(((Reference<Object>)file).get()));
            }
        }
    }
    
    public void run(final Closure script) {
        $getCallSiteArray()[12].callCurrent(this, script, null);
    }
    
    public void run(final Closure script, final String name) {
        final Closure script2 = (Closure)new Reference(script);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.swing = $getCallSiteArray[13].callConstructor($get$$class$groovy$swing$SwingBuilder());
        final Object phasePicker = new Reference(null);
        final Object splitterPane = new Reference(null);
        final Object mainSplitter = new Reference(null);
        this.showScriptFreeForm = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].callGetProperty(this.prefs));
        this.showScriptClass = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[15].callGetProperty(this.prefs));
        this.frame = $getCallSiteArray[16].call(this.swing, ScriptBytecodeAdapter.createMap(new Object[] { "title", $getCallSiteArray[17].call("Groovy AST Browser", DefaultTypeTransformation.booleanUnbox(name) ? new GStringImpl(new Object[] { name }, new String[] { " - ", "" }) : ""), "location", $getCallSiteArray[18].callGetProperty(this.prefs), "size", $getCallSiteArray[19].callGetProperty(this.prefs), "iconImage", $getCallSiteArray[20].callGetProperty($getCallSiteArray[21].call(this.swing, $getCallSiteArray[22].callGetProperty($get$$class$groovy$ui$Console()))), "defaultCloseOperation", $getCallSiteArray[23].callGetProperty($get$$class$javax$swing$WindowConstants()), "windowClosing", new AstBrowser$_run_closure2(this, this, (Reference<Object>)phasePicker, (Reference<Object>)splitterPane, (Reference<Object>)mainSplitter) }), new AstBrowser$_run_closure3(this, this, (Reference<Object>)phasePicker, (Reference<Object>)splitterPane, (Reference<Object>)mainSplitter, (Reference<Object>)script2));
        $getCallSiteArray[24].call($getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGetProperty(this.propertyTable)));
        $getCallSiteArray[27].call($getCallSiteArray[28].callGetProperty(this.jTree), $getCallSiteArray[29].call(this.swing, $getCallSiteArray[30].callGetProperty($get$$class$groovy$ui$Console())));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[31].callGetProperty($get$$class$javax$swing$tree$TreeSelectionModel()), $get$$class$groovy$inspect$swingui$AstBrowser(), $getCallSiteArray[32].callGetProperty(this.jTree), "selectionMode");
        $getCallSiteArray[33].call(this.jTree, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(new AstBrowser$_run_closure4(this, this), $get$$class$javax$swing$event$TreeSelectionListener()), $get$$class$javax$swing$event$TreeSelectionListener()));
        $getCallSiteArray[34].callCurrent(this, $getCallSiteArray[35].callGetProperty(this.prefs));
        $getCallSiteArray[36].call(this.frame);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[37].callGetProperty(this.prefs), $get$$class$groovy$inspect$swingui$AstBrowser(), this.frame, "location");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[38].callGetProperty(this.prefs), $get$$class$groovy$inspect$swingui$AstBrowser(), this.frame, "size");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[39].callGetProperty(this.prefs), $get$$class$groovy$inspect$swingui$AstBrowser(), ((Reference<Object>)splitterPane).get(), "dividerLocation");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[40].callGetProperty(this.prefs), $get$$class$groovy$inspect$swingui$AstBrowser(), ((Reference<Object>)mainSplitter).get(), "dividerLocation");
        $getCallSiteArray[41].call(this.frame);
        final String source = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[42].call(((Reference<Object>)script2).get()), $get$$class$java$lang$String());
        $getCallSiteArray[43].callCurrent(this, $getCallSiteArray[44].callGetProperty($getCallSiteArray[45].callGetProperty(((Reference<Object>)phasePicker).get())), source);
        $getCallSiteArray[46].callCurrent(this, this.jTree, source, $getCallSiteArray[47].callGetProperty($getCallSiteArray[48].callGetProperty(((Reference<Object>)phasePicker).get())));
        ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$inspect$swingui$AstBrowser(), this.jTree, "rootVisible");
        ScriptBytecodeAdapter.setProperty(Boolean.TRUE, $get$$class$groovy$inspect$swingui$AstBrowser(), this.jTree, "showsRootHandles");
    }
    
    public void largerFont(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[49].callCurrent(this, $getCallSiteArray[50].call($getCallSiteArray[51].callGetProperty($getCallSiteArray[52].callGetProperty(this.decompiledSource)), AstBrowser.$const$1));
    }
    
    public void smallerFont(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[53].callCurrent(this, $getCallSiteArray[54].call($getCallSiteArray[55].callGetProperty($getCallSiteArray[56].callGetProperty(this.decompiledSource)), AstBrowser.$const$1));
    }
    
    private Object updateFontSize(Object newFontSize) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareGreaterThan(newFontSize, AstBrowser.$const$2)) {
            newFontSize = AstBrowser.$const$2;
        }
        else if (ScriptBytecodeAdapter.compareLessThan(newFontSize, AstBrowser.$const$3)) {
            newFontSize = AstBrowser.$const$3;
        }
        ScriptBytecodeAdapter.setProperty(newFontSize, $get$$class$groovy$inspect$swingui$AstBrowser(), this.prefs, "decompiledSourceFontSize");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[57].callConstructor($get$$class$java$awt$Font(), $getCallSiteArray[58].callGetProperty($getCallSiteArray[59].callGetProperty(this.decompiledSource)), $getCallSiteArray[60].callGetProperty($getCallSiteArray[61].callGetProperty(this.decompiledSource)), newFontSize), $get$$class$groovy$inspect$swingui$AstBrowser(), this.decompiledSource, "font");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[62].callConstructor($get$$class$java$awt$Font(), $getCallSiteArray[63].callGetProperty($getCallSiteArray[64].callGetProperty(this.decompiledSource)), $getCallSiteArray[65].callGetProperty($getCallSiteArray[66].callGetProperty(this.decompiledSource)), newFontSize), $get$$class$groovy$inspect$swingui$AstBrowser(), $getCallSiteArray[67].callGetProperty(this.jTree), "font");
        $getCallSiteArray[68].call($getCallSiteArray[69].callGetProperty(this.jTree), $getCallSiteArray[70].callGetProperty($getCallSiteArray[71].callGetProperty(this.jTree)));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[72].callConstructor($get$$class$java$awt$Font(), $getCallSiteArray[73].callGetProperty($getCallSiteArray[74].callGetProperty(this.decompiledSource)), $getCallSiteArray[75].callGetProperty($getCallSiteArray[76].callGetProperty(this.decompiledSource)), newFontSize), $get$$class$groovy$inspect$swingui$AstBrowser(), $getCallSiteArray[77].callGetProperty(this.propertyTable), "font");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[78].callConstructor($get$$class$java$awt$Font(), $getCallSiteArray[79].callGetProperty($getCallSiteArray[80].callGetProperty(this.decompiledSource)), $getCallSiteArray[81].callGetProperty($getCallSiteArray[82].callGetProperty(this.decompiledSource)), newFontSize), $get$$class$groovy$inspect$swingui$AstBrowser(), this.propertyTable, "font");
        final Object call = $getCallSiteArray[83].call(newFontSize, AstBrowser.$const$1);
        ScriptBytecodeAdapter.setProperty(call, $get$$class$groovy$inspect$swingui$AstBrowser(), this.propertyTable, "rowHeight");
        return call;
    }
    
    public void showAbout(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object pane = $getCallSiteArray[84].call(this.swing);
        $getCallSiteArray[85].call(pane, "An interactive GUI to explore AST capabilities.");
        final Object dialog = $getCallSiteArray[86].call(pane, this.frame, "About Groovy AST Browser");
        $getCallSiteArray[87].call(dialog);
    }
    
    public void showScriptFreeForm(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.showScriptFreeForm = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[88].callGetProperty($getCallSiteArray[89].callGetProperty(evt)));
    }
    
    public void showScriptClass(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.showScriptClass = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[90].callGetProperty($getCallSiteArray[91].callGetProperty(evt)));
    }
    
    public void decompile(final Object phaseId, final Object source) {
        final Object phaseId2 = new Reference(phaseId);
        final Object source2 = new Reference(source);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[92].call(this.decompiledSource, $getCallSiteArray[93].call($get$$class$java$awt$Cursor(), $getCallSiteArray[94].callGetProperty($get$$class$java$awt$Cursor())));
        ScriptBytecodeAdapter.setProperty("Loading...", $get$$class$groovy$inspect$swingui$AstBrowser(), this.decompiledSource, "text");
        $getCallSiteArray[95].call(this.swing, new AstBrowser$_decompile_closure5(this, this, (Reference<Object>)source2, (Reference<Object>)phaseId2));
    }
    
    public void compile(final Object jTree, final String script, final int compilePhase) {
        final Object jTree2 = new Reference(jTree);
        final String script2 = (String)new Reference(script);
        final Integer compilePhase2 = (Integer)new Reference(DefaultTypeTransformation.box(compilePhase));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[96].call(((Reference<Object>)jTree2).get(), $getCallSiteArray[97].call($get$$class$java$awt$Cursor(), $getCallSiteArray[98].callGetProperty($get$$class$java$awt$Cursor())));
        final Object model = new Reference($getCallSiteArray[99].callGetProperty(((Reference<Object>)jTree2).get()));
        $getCallSiteArray[100].call(this.swing, new AstBrowser$_compile_closure6(this, this, (Reference<Object>)model));
        $getCallSiteArray[101].call(this.swing, new AstBrowser$_compile_closure7(this, this, (Reference<Object>)model, (Reference<Object>)compilePhase2, (Reference<Object>)script2, (Reference<Object>)jTree2));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$AstBrowser()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstBrowser.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstBrowser.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public void largerFont() {
        $getCallSiteArray()[102].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    public void smallerFont() {
        $getCallSiteArray()[103].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$EventObject()), $get$$class$java$util$EventObject()));
    }
    
    static {
        AstBrowser.__timeStamp__239_neverHappen1292524202609 = 0L;
        AstBrowser.__timeStamp = 1292524202609L;
        $const$3 = 4;
        $const$2 = 40;
        $const$1 = 2;
        $const$0 = 0;
    }
    
    public boolean getShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public boolean isShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public void setShowScriptFreeForm(final boolean showScriptFreeForm) {
        this.showScriptFreeForm = showScriptFreeForm;
    }
    
    public boolean getShowScriptClass() {
        return this.showScriptClass;
    }
    
    public boolean isShowScriptClass() {
        return this.showScriptClass;
    }
    
    public void setShowScriptClass(final boolean showScriptClass) {
        this.showScriptClass = showScriptClass;
    }
    
    public GroovyClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public void setClassLoader(final GroovyClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public Object getPrefs() {
        return this.prefs;
    }
    
    public void setPrefs(final Object prefs) {
        this.prefs = prefs;
    }
    
    public Object getSwing() {
        return this.swing;
    }
    
    public void setSwing(final Object swing) {
        this.swing = swing;
    }
    
    public Object getFrame() {
        return this.frame;
    }
    
    public void setFrame(final Object frame) {
        this.frame = frame;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[104];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser.$callSiteArray == null || ($createCallSiteArray = AstBrowser.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$EventObject() {
        Class $class$java$util$EventObject;
        if (($class$java$util$EventObject = AstBrowser.$class$java$util$EventObject) == null) {
            $class$java$util$EventObject = (AstBrowser.$class$java$util$EventObject = class$("java.util.EventObject"));
        }
        return $class$java$util$EventObject;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = AstBrowser.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (AstBrowser.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Cursor() {
        Class $class$java$awt$Cursor;
        if (($class$java$awt$Cursor = AstBrowser.$class$java$awt$Cursor) == null) {
            $class$java$awt$Cursor = (AstBrowser.$class$java$awt$Cursor = class$("java.awt.Cursor"));
        }
        return $class$java$awt$Cursor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = AstBrowser.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (AstBrowser.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$TreeSelectionModel() {
        Class $class$javax$swing$tree$TreeSelectionModel;
        if (($class$javax$swing$tree$TreeSelectionModel = AstBrowser.$class$javax$swing$tree$TreeSelectionModel) == null) {
            $class$javax$swing$tree$TreeSelectionModel = (AstBrowser.$class$javax$swing$tree$TreeSelectionModel = class$("javax.swing.tree.TreeSelectionModel"));
        }
        return $class$javax$swing$tree$TreeSelectionModel;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstBrowser.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstBrowser.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console() {
        Class $class$groovy$ui$Console;
        if (($class$groovy$ui$Console = AstBrowser.$class$groovy$ui$Console) == null) {
            $class$groovy$ui$Console = (AstBrowser.$class$groovy$ui$Console = class$("groovy.ui.Console"));
        }
        return $class$groovy$ui$Console;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = AstBrowser.$class$java$io$File) == null) {
            $class$java$io$File = (AstBrowser.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstBrowser.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstBrowser.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowserUiPreferences() {
        Class $class$groovy$inspect$swingui$AstBrowserUiPreferences;
        if (($class$groovy$inspect$swingui$AstBrowserUiPreferences = AstBrowser.$class$groovy$inspect$swingui$AstBrowserUiPreferences) == null) {
            $class$groovy$inspect$swingui$AstBrowserUiPreferences = (AstBrowser.$class$groovy$inspect$swingui$AstBrowserUiPreferences = class$("groovy.inspect.swingui.AstBrowserUiPreferences"));
        }
        return $class$groovy$inspect$swingui$AstBrowserUiPreferences;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = AstBrowser.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (AstBrowser.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$event$TreeSelectionListener() {
        Class $class$javax$swing$event$TreeSelectionListener;
        if (($class$javax$swing$event$TreeSelectionListener = AstBrowser.$class$javax$swing$event$TreeSelectionListener) == null) {
            $class$javax$swing$event$TreeSelectionListener = (AstBrowser.$class$javax$swing$event$TreeSelectionListener = class$("javax.swing.event.TreeSelectionListener"));
        }
        return $class$javax$swing$event$TreeSelectionListener;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = AstBrowser.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (AstBrowser.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$WindowConstants() {
        Class $class$javax$swing$WindowConstants;
        if (($class$javax$swing$WindowConstants = AstBrowser.$class$javax$swing$WindowConstants) == null) {
            $class$javax$swing$WindowConstants = (AstBrowser.$class$javax$swing$WindowConstants = class$("javax.swing.WindowConstants"));
        }
        return $class$javax$swing$WindowConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowser() {
        Class $class$groovy$inspect$swingui$AstBrowser;
        if (($class$groovy$inspect$swingui$AstBrowser = AstBrowser.$class$groovy$inspect$swingui$AstBrowser) == null) {
            $class$groovy$inspect$swingui$AstBrowser = (AstBrowser.$class$groovy$inspect$swingui$AstBrowser = class$("groovy.inspect.swingui.AstBrowser"));
        }
        return $class$groovy$inspect$swingui$AstBrowser;
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
