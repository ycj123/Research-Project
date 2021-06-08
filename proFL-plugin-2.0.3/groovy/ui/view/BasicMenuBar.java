// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class BasicMenuBar extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205134;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicMenuBar;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    
    public BasicMenuBar() {
        $getCallSiteArray();
    }
    
    public BasicMenuBar(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$BasicMenuBar(), args);
    }
    
    @Override
    public Object run() {
        return $getCallSiteArray()[1].callCurrent(this, new BasicMenuBar$_run_closure1(this, this));
    }
    
    static {
        BasicMenuBar.__timeStamp__239_neverHappen1292524205134 = 0L;
        BasicMenuBar.__timeStamp = 1292524205134L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicMenuBar(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicMenuBar.$callSiteArray == null || ($createCallSiteArray = BasicMenuBar.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicMenuBar.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = BasicMenuBar.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (BasicMenuBar.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicMenuBar() {
        Class $class$groovy$ui$view$BasicMenuBar;
        if (($class$groovy$ui$view$BasicMenuBar = BasicMenuBar.$class$groovy$ui$view$BasicMenuBar) == null) {
            $class$groovy$ui$view$BasicMenuBar = (BasicMenuBar.$class$groovy$ui$view$BasicMenuBar = class$("groovy.ui.view.BasicMenuBar"));
        }
        return $class$groovy$ui$view$BasicMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = BasicMenuBar.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (BasicMenuBar.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
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
