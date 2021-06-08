// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.text.StyleContext;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class BasicContentPane extends Script
{
    private static final /* synthetic */ BigDecimal $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Integer $const$4;
    private static final /* synthetic */ Integer $const$5;
    private static final /* synthetic */ Integer $const$6;
    private static final /* synthetic */ BigDecimal $const$7;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205096;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$BorderLayout;
    private static /* synthetic */ Class $class$java$awt$Dimension;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$java$awt$Font;
    private static /* synthetic */ Class $class$java$util$prefs$Preferences;
    private static /* synthetic */ Class $class$java$awt$image$BufferedImage;
    private static /* synthetic */ Class $class$javax$swing$JSplitPane;
    private static /* synthetic */ Class $class$groovy$ui$Console;
    private static /* synthetic */ Class $class$javax$swing$text$Style;
    private static /* synthetic */ Class $class$java$awt$GraphicsEnvironment;
    private static /* synthetic */ Class $class$java$awt$Graphics;
    private static /* synthetic */ Class $class$java$awt$FontMetrics;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$javax$swing$WindowConstants;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane;
    private static /* synthetic */ Class $class$javax$swing$text$StyledDocument;
    private static /* synthetic */ Class $class$javax$swing$text$StyleContext;
    
    public BasicContentPane() {
        $getCallSiteArray();
    }
    
    public BasicContentPane(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$BasicContentPane(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object prefs = new Reference($getCallSiteArray[1].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$ui$Console()));
        final Object detachedOutputFlag = $getCallSiteArray[2].call(((Reference<Object>)prefs).get(), "detachedOutput", Boolean.FALSE);
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[3].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "visible", Boolean.FALSE, "defaultCloseOperation", $getCallSiteArray[4].callGetProperty($get$$class$javax$swing$WindowConstants()) }), new BasicContentPane$_run_closure1(this, this)), $get$$class$groovy$ui$view$BasicContentPane(), this, "outputWindow");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "resizeWeight", BasicContentPane.$const$0, "orientation", $getCallSiteArray[6].callGetProperty($get$$class$javax$swing$JSplitPane()) }), new BasicContentPane$_run_closure2(this, this, (Reference<Object>)prefs)), $get$$class$groovy$ui$view$BasicContentPane(), this, "splitPane");
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGroovyObjectGetProperty(this)), $get$$class$groovy$ui$view$BasicContentPane(), this, "inputArea");
        $getCallSiteArray[9].callCurrent(this, new BasicContentPane$_run_closure3(this, this, (Reference<Object>)prefs));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[10].callConstructor($get$$class$java$awt$Font(), "Monospaced", $getCallSiteArray[11].callGetProperty($getCallSiteArray[12].callGetProperty($getCallSiteArray[13].callGroovyObjectGetProperty(this))), $getCallSiteArray[14].callGetProperty($getCallSiteArray[15].callGetProperty($getCallSiteArray[16].callGroovyObjectGetProperty(this)))), $get$$class$groovy$ui$view$BasicContentPane(), $getCallSiteArray[17].callGroovyObjectGetProperty(this), "font");
        StyledDocument doc = (StyledDocument)ScriptBytecodeAdapter.castToType($getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callGroovyObjectGetProperty(this)), $get$$class$javax$swing$text$StyledDocument());
        final Style defStyle = (Style)ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call($getCallSiteArray[21].callGetProperty($get$$class$javax$swing$text$StyleContext()), $getCallSiteArray[22].callGetProperty($get$$class$javax$swing$text$StyleContext())), $get$$class$javax$swing$text$Style());
        final Object applyStyle = new Reference(new BasicContentPane$_run_closure4(this, this));
        final Style regular = (Style)ScriptBytecodeAdapter.castToType($getCallSiteArray[23].call(doc, "regular", defStyle), $get$$class$javax$swing$text$Style());
        $getCallSiteArray[24].call(((Reference<Object>)applyStyle).get(), regular, $getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[27].call(doc, "prompt", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "promptStyle");
        $getCallSiteArray[28].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[29].callGroovyObjectGetProperty(this), $getCallSiteArray[30].callGetProperty($getCallSiteArray[31].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[32].call(doc, "command", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "commandStyle");
        $getCallSiteArray[33].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[34].callGroovyObjectGetProperty(this), $getCallSiteArray[35].callGetProperty($getCallSiteArray[36].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[37].call(doc, "output", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "outputStyle");
        $getCallSiteArray[38].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[39].callGroovyObjectGetProperty(this), $getCallSiteArray[40].callGetProperty($getCallSiteArray[41].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[42].call(doc, "result", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "resultStyle");
        $getCallSiteArray[43].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[44].callGroovyObjectGetProperty(this), $getCallSiteArray[45].callGetProperty($getCallSiteArray[46].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[47].call(doc, "stacktrace", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "stacktraceStyle");
        $getCallSiteArray[48].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[49].callGroovyObjectGetProperty(this), $getCallSiteArray[50].callGetProperty($getCallSiteArray[51].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[52].call(doc, "hyperlink", regular), $get$$class$groovy$ui$view$BasicContentPane(), this, "hyperlinkStyle");
        $getCallSiteArray[53].call(((Reference<Object>)applyStyle).get(), $getCallSiteArray[54].callGroovyObjectGetProperty(this), $getCallSiteArray[55].callGetProperty($getCallSiteArray[56].callGroovyObjectGetProperty(this)));
        doc = (StyledDocument)ScriptBytecodeAdapter.castToType($getCallSiteArray[57].callGetProperty($getCallSiteArray[58].callGroovyObjectGetProperty(this)), $get$$class$javax$swing$text$StyledDocument());
        final StyleContext styleContext = (StyleContext)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[59].callGetProperty($get$$class$javax$swing$text$StyleContext()), $get$$class$javax$swing$text$StyleContext()));
        $getCallSiteArray[60].call($getCallSiteArray[61].callGroovyObjectGetProperty(this), new BasicContentPane$_run_closure5(this, this, (Reference<Object>)styleContext, (Reference<Object>)applyStyle));
        final Graphics g = (Graphics)ScriptBytecodeAdapter.castToType($getCallSiteArray[62].call($getCallSiteArray[63].callGetProperty($get$$class$java$awt$GraphicsEnvironment()), $getCallSiteArray[64].callConstructor($get$$class$java$awt$image$BufferedImage(), BasicContentPane.$const$1, BasicContentPane.$const$1, $getCallSiteArray[65].callGetProperty($get$$class$java$awt$image$BufferedImage()))), $get$$class$java$awt$Graphics());
        final FontMetrics fm = (FontMetrics)ScriptBytecodeAdapter.castToType($getCallSiteArray[66].call(g, $getCallSiteArray[67].callGetProperty($getCallSiteArray[68].callGroovyObjectGetProperty(this))), $get$$class$java$awt$FontMetrics());
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[69].call(((Reference<Object>)prefs).get(), "outputAreaWidth", $getCallSiteArray[70].call($getCallSiteArray[71].call(fm, BasicContentPane.$const$2), BasicContentPane.$const$3)), $getCallSiteArray[72].call(((Reference<Object>)prefs).get(), "outputAreaHeight", $getCallSiteArray[73].call($getCallSiteArray[74].call($getCallSiteArray[75].call(fm), $getCallSiteArray[76].callGetProperty(fm)), BasicContentPane.$const$4)) }), $get$$class$java$awt$Dimension()), $get$$class$groovy$ui$view$BasicContentPane(), $getCallSiteArray[77].callGroovyObjectGetProperty(this), "preferredSize");
        ScriptBytecodeAdapter.setProperty(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[78].call(((Reference<Object>)prefs).get(), "inputAreaWidth", $getCallSiteArray[79].call($getCallSiteArray[80].call(fm, BasicContentPane.$const$2), BasicContentPane.$const$3)), $getCallSiteArray[81].call(((Reference<Object>)prefs).get(), "inputAreaHeight", $getCallSiteArray[82].call($getCallSiteArray[83].call($getCallSiteArray[84].call(fm), $getCallSiteArray[85].callGetProperty(fm)), BasicContentPane.$const$4)) }), $get$$class$java$awt$Dimension()), $get$$class$groovy$ui$view$BasicContentPane(), $getCallSiteArray[86].callGroovyObjectGetProperty(this), "preferredSize");
        ScriptBytecodeAdapter.setGroovyObjectProperty(BasicContentPane.$const$5, $get$$class$groovy$ui$view$BasicContentPane(), this, "origDividerSize");
        if (DefaultTypeTransformation.booleanUnbox(detachedOutputFlag)) {
            $getCallSiteArray[87].call($getCallSiteArray[88].callGroovyObjectGetProperty(this), $getCallSiteArray[89].callGroovyObjectGetProperty(this), $getCallSiteArray[90].callGetProperty($get$$class$javax$swing$JSplitPane()));
            ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[91].callGetProperty($getCallSiteArray[92].callGroovyObjectGetProperty(this)), $get$$class$groovy$ui$view$BasicContentPane(), this, "origDividerSize");
            ScriptBytecodeAdapter.setProperty(BasicContentPane.$const$6, $get$$class$groovy$ui$view$BasicContentPane(), $getCallSiteArray[93].callGroovyObjectGetProperty(this), "dividerSize");
            ScriptBytecodeAdapter.setProperty(BasicContentPane.$const$7, $get$$class$groovy$ui$view$BasicContentPane(), $getCallSiteArray[94].callGroovyObjectGetProperty(this), "resizeWeight");
            return $getCallSiteArray[95].call($getCallSiteArray[96].callGroovyObjectGetProperty(this), $getCallSiteArray[97].callGroovyObjectGetProperty(this), $getCallSiteArray[98].callGetProperty($get$$class$java$awt$BorderLayout()));
        }
        return null;
    }
    
    private Object buildOutputArea(final Object prefs) {
        final Object prefs2 = new Reference(prefs);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callCurrent = $getCallSiteArray[99].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "border", $getCallSiteArray[100].callCurrent(this, BasicContentPane.$const$6) }), new BasicContentPane$_buildOutputArea_closure6(this, this, (Reference<Object>)prefs2));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$ui$view$BasicContentPane(), this, "scrollArea");
        return callCurrent;
    }
    
    static {
        BasicContentPane.__timeStamp__239_neverHappen1292524205096 = 0L;
        BasicContentPane.__timeStamp = 1292524205096L;
        $const$7 = new BigDecimal("1.0");
        $const$6 = 0;
        $const$5 = -1;
        $const$4 = 12;
        $const$3 = 81;
        $const$2 = 119;
        $const$1 = 100;
        $const$0 = new BigDecimal("0.5");
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[101];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicContentPane(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicContentPane.$callSiteArray == null || ($createCallSiteArray = BasicContentPane.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicContentPane.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$BorderLayout() {
        Class $class$java$awt$BorderLayout;
        if (($class$java$awt$BorderLayout = BasicContentPane.$class$java$awt$BorderLayout) == null) {
            $class$java$awt$BorderLayout = (BasicContentPane.$class$java$awt$BorderLayout = class$("java.awt.BorderLayout"));
        }
        return $class$java$awt$BorderLayout;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dimension() {
        Class $class$java$awt$Dimension;
        if (($class$java$awt$Dimension = BasicContentPane.$class$java$awt$Dimension) == null) {
            $class$java$awt$Dimension = (BasicContentPane.$class$java$awt$Dimension = class$("java.awt.Dimension"));
        }
        return $class$java$awt$Dimension;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = BasicContentPane.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (BasicContentPane.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Font() {
        Class $class$java$awt$Font;
        if (($class$java$awt$Font = BasicContentPane.$class$java$awt$Font) == null) {
            $class$java$awt$Font = (BasicContentPane.$class$java$awt$Font = class$("java.awt.Font"));
        }
        return $class$java$awt$Font;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$prefs$Preferences() {
        Class $class$java$util$prefs$Preferences;
        if (($class$java$util$prefs$Preferences = BasicContentPane.$class$java$util$prefs$Preferences) == null) {
            $class$java$util$prefs$Preferences = (BasicContentPane.$class$java$util$prefs$Preferences = class$("java.util.prefs.Preferences"));
        }
        return $class$java$util$prefs$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$image$BufferedImage() {
        Class $class$java$awt$image$BufferedImage;
        if (($class$java$awt$image$BufferedImage = BasicContentPane.$class$java$awt$image$BufferedImage) == null) {
            $class$java$awt$image$BufferedImage = (BasicContentPane.$class$java$awt$image$BufferedImage = class$("java.awt.image.BufferedImage"));
        }
        return $class$java$awt$image$BufferedImage;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSplitPane() {
        Class $class$javax$swing$JSplitPane;
        if (($class$javax$swing$JSplitPane = BasicContentPane.$class$javax$swing$JSplitPane) == null) {
            $class$javax$swing$JSplitPane = (BasicContentPane.$class$javax$swing$JSplitPane = class$("javax.swing.JSplitPane"));
        }
        return $class$javax$swing$JSplitPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console() {
        Class $class$groovy$ui$Console;
        if (($class$groovy$ui$Console = BasicContentPane.$class$groovy$ui$Console) == null) {
            $class$groovy$ui$Console = (BasicContentPane.$class$groovy$ui$Console = class$("groovy.ui.Console"));
        }
        return $class$groovy$ui$Console;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$Style() {
        Class $class$javax$swing$text$Style;
        if (($class$javax$swing$text$Style = BasicContentPane.$class$javax$swing$text$Style) == null) {
            $class$javax$swing$text$Style = (BasicContentPane.$class$javax$swing$text$Style = class$("javax.swing.text.Style"));
        }
        return $class$javax$swing$text$Style;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GraphicsEnvironment() {
        Class $class$java$awt$GraphicsEnvironment;
        if (($class$java$awt$GraphicsEnvironment = BasicContentPane.$class$java$awt$GraphicsEnvironment) == null) {
            $class$java$awt$GraphicsEnvironment = (BasicContentPane.$class$java$awt$GraphicsEnvironment = class$("java.awt.GraphicsEnvironment"));
        }
        return $class$java$awt$GraphicsEnvironment;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Graphics() {
        Class $class$java$awt$Graphics;
        if (($class$java$awt$Graphics = BasicContentPane.$class$java$awt$Graphics) == null) {
            $class$java$awt$Graphics = (BasicContentPane.$class$java$awt$Graphics = class$("java.awt.Graphics"));
        }
        return $class$java$awt$Graphics;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$FontMetrics() {
        Class $class$java$awt$FontMetrics;
        if (($class$java$awt$FontMetrics = BasicContentPane.$class$java$awt$FontMetrics) == null) {
            $class$java$awt$FontMetrics = (BasicContentPane.$class$java$awt$FontMetrics = class$("java.awt.FontMetrics"));
        }
        return $class$java$awt$FontMetrics;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = BasicContentPane.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (BasicContentPane.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$WindowConstants() {
        Class $class$javax$swing$WindowConstants;
        if (($class$javax$swing$WindowConstants = BasicContentPane.$class$javax$swing$WindowConstants) == null) {
            $class$javax$swing$WindowConstants = (BasicContentPane.$class$javax$swing$WindowConstants = class$("javax.swing.WindowConstants"));
        }
        return $class$javax$swing$WindowConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane() {
        Class $class$groovy$ui$view$BasicContentPane;
        if (($class$groovy$ui$view$BasicContentPane = BasicContentPane.$class$groovy$ui$view$BasicContentPane) == null) {
            $class$groovy$ui$view$BasicContentPane = (BasicContentPane.$class$groovy$ui$view$BasicContentPane = class$("groovy.ui.view.BasicContentPane"));
        }
        return $class$groovy$ui$view$BasicContentPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyledDocument() {
        Class $class$javax$swing$text$StyledDocument;
        if (($class$javax$swing$text$StyledDocument = BasicContentPane.$class$javax$swing$text$StyledDocument) == null) {
            $class$javax$swing$text$StyledDocument = (BasicContentPane.$class$javax$swing$text$StyledDocument = class$("javax.swing.text.StyledDocument"));
        }
        return $class$javax$swing$text$StyledDocument;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleContext() {
        Class $class$javax$swing$text$StyleContext;
        if (($class$javax$swing$text$StyleContext = BasicContentPane.$class$javax$swing$text$StyleContext) == null) {
            $class$javax$swing$text$StyleContext = (BasicContentPane.$class$javax$swing$text$StyleContext = class$("javax.swing.text.StyleContext"));
        }
        return $class$javax$swing$text$StyleContext;
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
