// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$__clinit__closure23 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$JFrame;
    
    public Console$__clinit__closure23(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "title", "GroovyConsole", "iconImage", $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callCurrent(this, "/groovy/ui/ConsoleIcon.png")), "defaultCloseOperation", $getCallSiteArray[3].callGetProperty($get$$class$javax$swing$JFrame()) }), new Console$__clinit__closure23_closure37(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$__clinit__closure23(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$__clinit__closure23.$callSiteArray == null || ($createCallSiteArray = Console$__clinit__closure23.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$__clinit__closure23.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$__clinit__closure23.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$__clinit__closure23.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFrame() {
        Class $class$javax$swing$JFrame;
        if (($class$javax$swing$JFrame = Console$__clinit__closure23.$class$javax$swing$JFrame) == null) {
            $class$javax$swing$JFrame = (Console$__clinit__closure23.$class$javax$swing$JFrame = class$("javax.swing.JFrame"));
        }
        return $class$javax$swing$JFrame;
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
