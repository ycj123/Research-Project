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

public class MacOSXMenuBar extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205388;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$view$MacOSXMenuBar;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicMenuBar;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    
    public MacOSXMenuBar() {
        $getCallSiteArray();
    }
    
    public MacOSXMenuBar(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$MacOSXMenuBar(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object handler = Boolean.FALSE;
        if (!DefaultTypeTransformation.booleanUnbox(handler)) {
            try {
                handler = $getCallSiteArray[1].callCurrent(this, "\npackage groovy.ui\n\nimport com.apple.mrj.*\n\nclass ConsoleMacOsSupport implements MRJQuitHandler, MRJAboutHandler {\n\n    def quitHandler\n    def aboutHandler\n\n    public void handleAbout() {\n        aboutHandler()\n    }\n\n    public void handleQuit() {\n        quitHandler()\n    }\n\n}\n\ndef handler = new ConsoleMacOsSupport(quitHandler:controller.&exit, aboutHandler:controller.&showAbout)\nMRJApplicationUtils.registerAboutHandler(handler)\nMRJApplicationUtils.registerQuitHandler(handler)\n\nreturn handler\n", $getCallSiteArray[2].callConstructor($get$$class$groovy$lang$GroovyClassLoader(), $getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGroovyObjectGetProperty(this))));
            }
            catch (Exception se) {
                $getCallSiteArray[5].call(se);
                $getCallSiteArray[6].callCurrent(this, $get$$class$groovy$ui$view$BasicMenuBar());
                return null;
            }
        }
        return $getCallSiteArray[7].callCurrent(this, new MacOSXMenuBar$_run_closure1(this, this));
    }
    
    static {
        MacOSXMenuBar.__timeStamp__239_neverHappen1292524205388 = 0L;
        MacOSXMenuBar.__timeStamp = 1292524205388L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$MacOSXMenuBar(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (MacOSXMenuBar.$callSiteArray == null || ($createCallSiteArray = MacOSXMenuBar.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            MacOSXMenuBar.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = MacOSXMenuBar.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (MacOSXMenuBar.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$MacOSXMenuBar() {
        Class $class$groovy$ui$view$MacOSXMenuBar;
        if (($class$groovy$ui$view$MacOSXMenuBar = MacOSXMenuBar.$class$groovy$ui$view$MacOSXMenuBar) == null) {
            $class$groovy$ui$view$MacOSXMenuBar = (MacOSXMenuBar.$class$groovy$ui$view$MacOSXMenuBar = class$("groovy.ui.view.MacOSXMenuBar"));
        }
        return $class$groovy$ui$view$MacOSXMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicMenuBar() {
        Class $class$groovy$ui$view$BasicMenuBar;
        if (($class$groovy$ui$view$BasicMenuBar = MacOSXMenuBar.$class$groovy$ui$view$BasicMenuBar) == null) {
            $class$groovy$ui$view$BasicMenuBar = (MacOSXMenuBar.$class$groovy$ui$view$BasicMenuBar = class$("groovy.ui.view.BasicMenuBar"));
        }
        return $class$groovy$ui$view$BasicMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = MacOSXMenuBar.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (MacOSXMenuBar.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = MacOSXMenuBar.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (MacOSXMenuBar.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
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
