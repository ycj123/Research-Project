// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.JComponent;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GTKDefaults$_run_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public GTKDefaults$_run_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object builder, final Object node, final Object attributes) {
        final Object node2 = new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)node2).get() instanceof JComponent) {
            return $getCallSiteArray[0].call(((Reference<Object>)node2).get(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].callConstructor($get$$class$java$lang$Boolean(), Boolean.TRUE));
        }
        return null;
    }
    
    public Object call(final Object builder, final Object node, final Object attributes) {
        final Object node2 = new Reference(node);
        return $getCallSiteArray()[3].callCurrent(this, builder, ((Reference<Object>)node2).get(), attributes);
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$GTKDefaults$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GTKDefaults$_run_closure1.$callSiteArray == null || ($createCallSiteArray = GTKDefaults$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GTKDefaults$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = GTKDefaults$_run_closure1.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (GTKDefaults$_run_closure1.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
