// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class LookAndFeelHelper$_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    
    public LookAndFeelHelper$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object laf, final Object bold) {
        final Object bold2 = new Reference(bold);
        return $getCallSiteArray()[0].call($get$$class$javax$swing$UIManager(), "swing.boldMetal", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(((Reference<Object>)bold2).get(), $get$$class$java$lang$Boolean()), $get$$class$java$lang$Boolean()));
    }
    
    public Object call(final Object laf, final Object bold) {
        final Object bold2 = new Reference(bold);
        return $getCallSiteArray()[1].callCurrent(this, laf, ((Reference<Object>)bold2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$LookAndFeelHelper$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LookAndFeelHelper$_closure2.$callSiteArray == null || ($createCallSiteArray = LookAndFeelHelper$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LookAndFeelHelper$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = LookAndFeelHelper$_closure2.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (LookAndFeelHelper$_closure2.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = LookAndFeelHelper$_closure2.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (LookAndFeelHelper$_closure2.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
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
