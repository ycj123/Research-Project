// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class Defaults extends Script
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205375;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicToolBar;
    private static /* synthetic */ Class $class$groovy$ui$text$GroovyFilter;
    private static /* synthetic */ Class $class$javax$swing$text$StyleConstants;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicStatusBar;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicMenuBar;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$ui$view$Defaults;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicContentPane;
    private static /* synthetic */ Class $class$javax$swing$text$StyleContext;
    private static /* synthetic */ Class $class$java$awt$Color;
    
    public Defaults() {
        $getCallSiteArray();
    }
    
    public Defaults(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$Defaults(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty($get$$class$groovy$ui$view$BasicMenuBar(), $get$$class$groovy$ui$view$Defaults(), this, "menuBarClass");
        ScriptBytecodeAdapter.setGroovyObjectProperty($get$$class$groovy$ui$view$BasicContentPane(), $get$$class$groovy$ui$view$Defaults(), this, "contentPaneClass");
        ScriptBytecodeAdapter.setGroovyObjectProperty($get$$class$groovy$ui$view$BasicToolBar(), $get$$class$groovy$ui$view$Defaults(), this, "toolBarClass");
        ScriptBytecodeAdapter.setGroovyObjectProperty($get$$class$groovy$ui$view$BasicStatusBar(), $get$$class$groovy$ui$view$Defaults(), this, "statusBarClass");
        final Map map = ScriptBytecodeAdapter.createMap(new Object[] { "regular", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[1].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "Monospaced" }), "prompt", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[2].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[3].callConstructor($get$$class$java$awt$Color(), Defaults.$const$0, Defaults.$const$1, Defaults.$const$0) }), "command", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[4].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[5].callGetProperty($get$$class$java$awt$Color()) }), "stacktrace", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[6].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty($get$$class$java$awt$Color())) }), "hyperlink", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[9].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[10].callGetProperty($get$$class$java$awt$Color()), $getCallSiteArray[11].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), "output", ScriptBytecodeAdapter.createMap(new Object[0]), "result", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[12].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[13].callGetProperty($get$$class$java$awt$Color()), $getCallSiteArray[14].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[15].callGetProperty($get$$class$java$awt$Color()) }), $getCallSiteArray[16].callGetProperty($get$$class$javax$swing$text$StyleContext()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[17].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "Monospaced" }), $getCallSiteArray[18].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[19].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[20].call($getCallSiteArray[21].call($getCallSiteArray[22].callGetProperty($get$$class$java$awt$Color()))), $getCallSiteArray[23].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), $getCallSiteArray[24].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[25].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[26].call($getCallSiteArray[27].call($getCallSiteArray[28].callGetProperty($get$$class$java$awt$Color()))) }), $getCallSiteArray[29].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[30].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[31].call($getCallSiteArray[32].call($getCallSiteArray[33].callGetProperty($get$$class$java$awt$Color()))) }), $getCallSiteArray[34].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[35].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[36].call($getCallSiteArray[37].callGetProperty($get$$class$java$awt$Color())) }), $getCallSiteArray[38].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[39].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[40].call($getCallSiteArray[41].callGetProperty($get$$class$java$awt$Color())) }), $getCallSiteArray[42].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[43].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), $getCallSiteArray[44].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[0]), $getCallSiteArray[45].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[46].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE, $getCallSiteArray[47].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[48].call($getCallSiteArray[49].call($getCallSiteArray[50].callGetProperty($get$$class$java$awt$Color()))) }) });
        ScriptBytecodeAdapter.setGroovyObjectProperty(map, $get$$class$groovy$ui$view$Defaults(), this, "styles");
        return map;
    }
    
    static {
        Defaults.__timeStamp__239_neverHappen1292524205375 = 0L;
        Defaults.__timeStamp = 1292524205375L;
        $const$1 = 128;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[51];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$Defaults(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Defaults.$callSiteArray == null || ($createCallSiteArray = Defaults.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Defaults.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicToolBar() {
        Class $class$groovy$ui$view$BasicToolBar;
        if (($class$groovy$ui$view$BasicToolBar = Defaults.$class$groovy$ui$view$BasicToolBar) == null) {
            $class$groovy$ui$view$BasicToolBar = (Defaults.$class$groovy$ui$view$BasicToolBar = class$("groovy.ui.view.BasicToolBar"));
        }
        return $class$groovy$ui$view$BasicToolBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$text$GroovyFilter() {
        Class $class$groovy$ui$text$GroovyFilter;
        if (($class$groovy$ui$text$GroovyFilter = Defaults.$class$groovy$ui$text$GroovyFilter) == null) {
            $class$groovy$ui$text$GroovyFilter = (Defaults.$class$groovy$ui$text$GroovyFilter = class$("groovy.ui.text.GroovyFilter"));
        }
        return $class$groovy$ui$text$GroovyFilter;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleConstants() {
        Class $class$javax$swing$text$StyleConstants;
        if (($class$javax$swing$text$StyleConstants = Defaults.$class$javax$swing$text$StyleConstants) == null) {
            $class$javax$swing$text$StyleConstants = (Defaults.$class$javax$swing$text$StyleConstants = class$("javax.swing.text.StyleConstants"));
        }
        return $class$javax$swing$text$StyleConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = Defaults.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (Defaults.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicStatusBar() {
        Class $class$groovy$ui$view$BasicStatusBar;
        if (($class$groovy$ui$view$BasicStatusBar = Defaults.$class$groovy$ui$view$BasicStatusBar) == null) {
            $class$groovy$ui$view$BasicStatusBar = (Defaults.$class$groovy$ui$view$BasicStatusBar = class$("groovy.ui.view.BasicStatusBar"));
        }
        return $class$groovy$ui$view$BasicStatusBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicMenuBar() {
        Class $class$groovy$ui$view$BasicMenuBar;
        if (($class$groovy$ui$view$BasicMenuBar = Defaults.$class$groovy$ui$view$BasicMenuBar) == null) {
            $class$groovy$ui$view$BasicMenuBar = (Defaults.$class$groovy$ui$view$BasicMenuBar = class$("groovy.ui.view.BasicMenuBar"));
        }
        return $class$groovy$ui$view$BasicMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = Defaults.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (Defaults.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$Defaults() {
        Class $class$groovy$ui$view$Defaults;
        if (($class$groovy$ui$view$Defaults = Defaults.$class$groovy$ui$view$Defaults) == null) {
            $class$groovy$ui$view$Defaults = (Defaults.$class$groovy$ui$view$Defaults = class$("groovy.ui.view.Defaults"));
        }
        return $class$groovy$ui$view$Defaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicContentPane() {
        Class $class$groovy$ui$view$BasicContentPane;
        if (($class$groovy$ui$view$BasicContentPane = Defaults.$class$groovy$ui$view$BasicContentPane) == null) {
            $class$groovy$ui$view$BasicContentPane = (Defaults.$class$groovy$ui$view$BasicContentPane = class$("groovy.ui.view.BasicContentPane"));
        }
        return $class$groovy$ui$view$BasicContentPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleContext() {
        Class $class$javax$swing$text$StyleContext;
        if (($class$javax$swing$text$StyleContext = Defaults.$class$javax$swing$text$StyleContext) == null) {
            $class$javax$swing$text$StyleContext = (Defaults.$class$javax$swing$text$StyleContext = class$("javax.swing.text.StyleContext"));
        }
        return $class$javax$swing$text$StyleContext;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Color() {
        Class $class$java$awt$Color;
        if (($class$java$awt$Color = Defaults.$class$java$awt$Color) == null) {
            $class$java$awt$Color = (Defaults.$class$java$awt$Color = class$("java.awt.Color"));
        }
        return $class$java$awt$Color;
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
