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
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class WindowsDefaults extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205398;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$text$StyleConstants;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$ui$view$Defaults;
    private static /* synthetic */ Class $class$groovy$ui$view$WindowsDefaults;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$javax$swing$text$StyleContext;
    
    public WindowsDefaults() {
        $getCallSiteArray();
    }
    
    public WindowsDefaults(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$WindowsDefaults(), args);
    }
    
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[1].callCurrent(this, $get$$class$groovy$ui$view$Defaults());
        if (!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty($get$$class$java$lang$System()), "os.version"), "6\\."))) {
            return null;
        }
        $getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), $getCallSiteArray[7].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "Consolas");
        $getCallSiteArray[8].call($getCallSiteArray[9].call($getCallSiteArray[10].callGroovyObjectGetProperty(this), $getCallSiteArray[11].callGetProperty($get$$class$javax$swing$text$StyleContext())), $getCallSiteArray[12].callGetProperty($get$$class$javax$swing$text$StyleConstants()), "Consolas");
        if (DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex($getCallSiteArray[13].call($getCallSiteArray[14].callGetProperty($get$$class$java$lang$System()), "java.version"), "^1\\.5"))) {
            ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[15].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType("com.sun.java.swing.SwingUtilities2", $get$$class$java$lang$Class()), $get$$class$java$lang$Class()), "AA_TEXT_PROPERTY_KEY"), $get$$class$groovy$ui$view$WindowsDefaults(), this, "key");
            return $getCallSiteArray[16].callCurrent(this, new WindowsDefaults$_run_closure1(this, this));
        }
        return null;
    }
    
    static {
        WindowsDefaults.__timeStamp__239_neverHappen1292524205398 = 0L;
        WindowsDefaults.__timeStamp = 1292524205398L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[17];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$WindowsDefaults(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (WindowsDefaults.$callSiteArray == null || ($createCallSiteArray = WindowsDefaults.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            WindowsDefaults.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleConstants() {
        Class $class$javax$swing$text$StyleConstants;
        if (($class$javax$swing$text$StyleConstants = WindowsDefaults.$class$javax$swing$text$StyleConstants) == null) {
            $class$javax$swing$text$StyleConstants = (WindowsDefaults.$class$javax$swing$text$StyleConstants = class$("javax.swing.text.StyleConstants"));
        }
        return $class$javax$swing$text$StyleConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = WindowsDefaults.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (WindowsDefaults.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = WindowsDefaults.$class$java$lang$System) == null) {
            $class$java$lang$System = (WindowsDefaults.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = WindowsDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (WindowsDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$Defaults() {
        Class $class$groovy$ui$view$Defaults;
        if (($class$groovy$ui$view$Defaults = WindowsDefaults.$class$groovy$ui$view$Defaults) == null) {
            $class$groovy$ui$view$Defaults = (WindowsDefaults.$class$groovy$ui$view$Defaults = class$("groovy.ui.view.Defaults"));
        }
        return $class$groovy$ui$view$Defaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$WindowsDefaults() {
        Class $class$groovy$ui$view$WindowsDefaults;
        if (($class$groovy$ui$view$WindowsDefaults = WindowsDefaults.$class$groovy$ui$view$WindowsDefaults) == null) {
            $class$groovy$ui$view$WindowsDefaults = (WindowsDefaults.$class$groovy$ui$view$WindowsDefaults = class$("groovy.ui.view.WindowsDefaults"));
        }
        return $class$groovy$ui$view$WindowsDefaults;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = WindowsDefaults.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (WindowsDefaults.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$StyleContext() {
        Class $class$javax$swing$text$StyleContext;
        if (($class$javax$swing$text$StyleContext = WindowsDefaults.$class$javax$swing$text$StyleContext) == null) {
            $class$javax$swing$text$StyleContext = (WindowsDefaults.$class$javax$swing$text$StyleContext = class$("javax.swing.text.StyleContext"));
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
