// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import javax.swing.JApplet;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_run_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> applet;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$JApplet;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$SwingUtilities;
    private static /* synthetic */ Class $class$groovy$ui$Console$_run_closure2;
    
    public Console$_run_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> applet) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.applet = applet;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].call($get$$class$javax$swing$SwingUtilities(), $getCallSiteArray[3].call(this.applet.get()))), $get$$class$groovy$ui$Console$_run_closure2(), this, "containingWindows");
        return this.applet.get();
    }
    
    public JApplet getApplet() {
        $getCallSiteArray();
        return (JApplet)ScriptBytecodeAdapter.castToType(this.applet.get(), $get$$class$javax$swing$JApplet());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_run_closure2.$callSiteArray == null || ($createCallSiteArray = Console$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JApplet() {
        Class $class$javax$swing$JApplet;
        if (($class$javax$swing$JApplet = Console$_run_closure2.$class$javax$swing$JApplet) == null) {
            $class$javax$swing$JApplet = (Console$_run_closure2.$class$javax$swing$JApplet = class$("javax.swing.JApplet"));
        }
        return $class$javax$swing$JApplet;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_run_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_run_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SwingUtilities() {
        Class $class$javax$swing$SwingUtilities;
        if (($class$javax$swing$SwingUtilities = Console$_run_closure2.$class$javax$swing$SwingUtilities) == null) {
            $class$javax$swing$SwingUtilities = (Console$_run_closure2.$class$javax$swing$SwingUtilities = class$("javax.swing.SwingUtilities"));
        }
        return $class$javax$swing$SwingUtilities;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_run_closure2() {
        Class $class$groovy$ui$Console$_run_closure2;
        if (($class$groovy$ui$Console$_run_closure2 = Console$_run_closure2.$class$groovy$ui$Console$_run_closure2) == null) {
            $class$groovy$ui$Console$_run_closure2 = (Console$_run_closure2.$class$groovy$ui$Console$_run_closure2 = class$("groovy.ui.Console$_run_closure2"));
        }
        return $class$groovy$ui$Console$_run_closure2;
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
