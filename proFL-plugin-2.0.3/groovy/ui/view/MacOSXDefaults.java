// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

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

public class MacOSXDefaults extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205385;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$text$GroovyFilter;
    private static /* synthetic */ Class $class$javax$swing$text$StyleConstants;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$view$MacOSXMenuBar;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$ui$view$Defaults;
    private static /* synthetic */ Class $class$groovy$ui$view$MacOSXDefaults;
    private static /* synthetic */ Class $class$java$awt$Color;
    
    public MacOSXDefaults() {
        $getCallSiteArray();
    }
    
    public MacOSXDefaults(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$MacOSXDefaults(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[1].callCurrent(this, $get$$class$groovy$ui$view$Defaults());
        $getCallSiteArray[2].call($get$$class$java$lang$System(), "apple.laf.useScreenMenuBar", "true");
        $getCallSiteArray[3].call($get$$class$java$lang$System(), "com.apple.mrj.application.apple.menu.about.name", "GroovyConsole");
        ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.createMap(new Object[] { "regular", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[4].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "Monaco" }), "prompt", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[5].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[6].callGetProperty($get$$class$java$awt$Color()) }), "command", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[7].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[8].callGetProperty($get$$class$java$awt$Color()) }), "stacktrace", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[9].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty($get$$class$java$awt$Color())) }), "hyperlink", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[12].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[13].callGetProperty($get$$class$java$awt$Color()), $getCallSiteArray[14].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), "output", ScriptBytecodeAdapter.createMap(new Object[0]), "result", ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[15].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[16].callGetProperty($get$$class$java$awt$Color()), $getCallSiteArray[17].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[18].callGetProperty($get$$class$java$awt$Color()) }), $getCallSiteArray[19].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[20].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[21].call($getCallSiteArray[22].call($getCallSiteArray[23].callGetProperty($get$$class$java$awt$Color()))), $getCallSiteArray[24].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), $getCallSiteArray[25].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[26].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[27].call($getCallSiteArray[28].call($getCallSiteArray[29].callGetProperty($get$$class$java$awt$Color()))) }), $getCallSiteArray[30].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[31].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[32].call($getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty($get$$class$java$awt$Color()))) }), $getCallSiteArray[35].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[36].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[37].call($getCallSiteArray[38].callGetProperty($get$$class$java$awt$Color())) }), $getCallSiteArray[39].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[40].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[41].call($getCallSiteArray[42].callGetProperty($get$$class$java$awt$Color())) }), $getCallSiteArray[43].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[44].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE }), $getCallSiteArray[45].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[0]), $getCallSiteArray[46].callGetProperty($get$$class$groovy$ui$text$GroovyFilter()), ScriptBytecodeAdapter.createMap(new Object[] { $getCallSiteArray[47].callGetProperty($get$$class$javax$swing$text$StyleConstants()), Boolean.TRUE, $getCallSiteArray[48].callGetProperty($get$$class$javax$swing$text$StyleConstants()), $getCallSiteArray[49].call($getCallSiteArray[50].call($getCallSiteArray[51].callGetProperty($get$$class$java$awt$Color()))) }) }), $get$$class$groovy$ui$view$MacOSXDefaults(), this, "styles");
        final Class $get$$class$groovy$ui$view$MacOSXMenuBar = $get$$class$groovy$ui$view$MacOSXMenuBar();
        ScriptBytecodeAdapter.setGroovyObjectProperty($get$$class$groovy$ui$view$MacOSXMenuBar, $get$$class$groovy$ui$view$MacOSXDefaults(), this, "menuBarClass");
        return $get$$class$groovy$ui$view$MacOSXMenuBar;
    }
    
    static {
        MacOSXDefaults.__timeStamp__239_neverHappen1292524205385 = 0L;
        MacOSXDefaults.__timeStamp = 1292524205385L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[52];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$MacOSXDefaults(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MacOSXDefaults.$callSiteArray == null || ($createCallSiteArray = MacOSXDefaults.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MacOSXDefaults.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$text$GroovyFilter() {
        Class $class$groovy$ui$text$GroovyFilter;
        if (($class$groovy$ui$text$GroovyFilter = MacOSXDefaults.$class$groovy$ui$text$GroovyFilter) == null) {
            $class$groovy$ui$text$GroovyFilter = (MacOSXDefaults.$class$groovy$ui$text$GroovyFilter = class$("groovy.ui.text.GroovyFilter"));
        }
        return $class$groovy$ui$text$GroovyFilter;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleConstants() {
        Class $class$javax$swing$text$StyleConstants;
        if (($class$javax$swing$text$StyleConstants = MacOSXDefaults.$class$javax$swing$text$StyleConstants) == null) {
            $class$javax$swing$text$StyleConstants = (MacOSXDefaults.$class$javax$swing$text$StyleConstants = class$("javax.swing.text.StyleConstants"));
        }
        return $class$javax$swing$text$StyleConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = MacOSXDefaults.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (MacOSXDefaults.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$MacOSXMenuBar() {
        Class $class$groovy$ui$view$MacOSXMenuBar;
        if (($class$groovy$ui$view$MacOSXMenuBar = MacOSXDefaults.$class$groovy$ui$view$MacOSXMenuBar) == null) {
            $class$groovy$ui$view$MacOSXMenuBar = (MacOSXDefaults.$class$groovy$ui$view$MacOSXMenuBar = class$("groovy.ui.view.MacOSXMenuBar"));
        }
        return $class$groovy$ui$view$MacOSXMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = MacOSXDefaults.$class$java$lang$System) == null) {
            $class$java$lang$System = (MacOSXDefaults.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = MacOSXDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (MacOSXDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$Defaults() {
        Class $class$groovy$ui$view$Defaults;
        if (($class$groovy$ui$view$Defaults = MacOSXDefaults.$class$groovy$ui$view$Defaults) == null) {
            $class$groovy$ui$view$Defaults = (MacOSXDefaults.$class$groovy$ui$view$Defaults = class$("groovy.ui.view.Defaults"));
        }
        return $class$groovy$ui$view$Defaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$MacOSXDefaults() {
        Class $class$groovy$ui$view$MacOSXDefaults;
        if (($class$groovy$ui$view$MacOSXDefaults = MacOSXDefaults.$class$groovy$ui$view$MacOSXDefaults) == null) {
            $class$groovy$ui$view$MacOSXDefaults = (MacOSXDefaults.$class$groovy$ui$view$MacOSXDefaults = class$("groovy.ui.view.MacOSXDefaults"));
        }
        return $class$groovy$ui$view$MacOSXDefaults;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Color() {
        Class $class$java$awt$Color;
        if (($class$java$awt$Color = MacOSXDefaults.$class$java$awt$Color) == null) {
            $class$java$awt$Color = (MacOSXDefaults.$class$java$awt$Color = class$("java.awt.Color"));
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
