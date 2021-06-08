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

public class BasicStatusBar extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205371;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$BorderLayout;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicStatusBar;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    
    public BasicStatusBar() {
        $getCallSiteArray();
    }
    
    public BasicStatusBar(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$BasicStatusBar(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callCurrent = $getCallSiteArray[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "constraints", $getCallSiteArray[2].callGetProperty($get$$class$java$awt$BorderLayout()) }), new BasicStatusBar$_run_closure1(this, this));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$ui$view$BasicStatusBar(), this, "statusPanel");
        return callCurrent;
    }
    
    static {
        BasicStatusBar.__timeStamp__239_neverHappen1292524205371 = 0L;
        BasicStatusBar.__timeStamp = 1292524205371L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicStatusBar(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicStatusBar.$callSiteArray == null || ($createCallSiteArray = BasicStatusBar.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicStatusBar.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$BorderLayout() {
        Class $class$java$awt$BorderLayout;
        if (($class$java$awt$BorderLayout = BasicStatusBar.$class$java$awt$BorderLayout) == null) {
            $class$java$awt$BorderLayout = (BasicStatusBar.$class$java$awt$BorderLayout = class$("java.awt.BorderLayout"));
        }
        return $class$java$awt$BorderLayout;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = BasicStatusBar.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (BasicStatusBar.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicStatusBar() {
        Class $class$groovy$ui$view$BasicStatusBar;
        if (($class$groovy$ui$view$BasicStatusBar = BasicStatusBar.$class$groovy$ui$view$BasicStatusBar) == null) {
            $class$groovy$ui$view$BasicStatusBar = (BasicStatusBar.$class$groovy$ui$view$BasicStatusBar = class$("groovy.ui.view.BasicStatusBar"));
        }
        return $class$groovy$ui$view$BasicStatusBar;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = BasicStatusBar.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (BasicStatusBar.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
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
