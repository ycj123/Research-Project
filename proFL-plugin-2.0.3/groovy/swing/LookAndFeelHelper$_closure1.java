// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.plaf.metal.MetalTheme;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class LookAndFeelHelper$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$LookAndFeelHelper$_closure1;
    private static /* synthetic */ Class $class$javax$swing$plaf$metal$MetalLookAndFeel;
    private static /* synthetic */ Class $class$javax$swing$plaf$metal$DefaultMetalTheme;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public LookAndFeelHelper$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object laf, final Object theme) {
        final Object theme2 = new Reference(theme);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!(((Reference<Object>)theme2).get() instanceof MetalTheme)) {
            if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)theme2).get(), "ocean")) {
                ((Reference<Object>)theme2).set($getCallSiteArray[0].call($getCallSiteArray[1].call($get$$class$java$lang$Class(), "javax.swing.plaf.metal.OceanTheme")));
            }
            else if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)theme2).get(), "steel")) {
                ((Reference<Object>)theme2).set($getCallSiteArray[2].callConstructor($get$$class$javax$swing$plaf$metal$DefaultMetalTheme()));
            }
            else {
                ((Reference<Object>)theme2).set($getCallSiteArray[3].call($getCallSiteArray[4].call($get$$class$java$lang$Class(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(((Reference<Object>)theme2).get(), $get$$class$java$lang$String()), $get$$class$java$lang$String()))));
            }
        }
        final Object value = ((Reference<Object>)theme2).get();
        ScriptBytecodeAdapter.setProperty(value, $get$$class$groovy$swing$LookAndFeelHelper$_closure1(), $get$$class$javax$swing$plaf$metal$MetalLookAndFeel(), "currentTheme");
        return value;
    }
    
    public Object call(final Object laf, final Object theme) {
        final Object theme2 = new Reference(theme);
        return $getCallSiteArray()[5].callCurrent(this, laf, ((Reference<Object>)theme2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$LookAndFeelHelper$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LookAndFeelHelper$_closure1.$callSiteArray == null || ($createCallSiteArray = LookAndFeelHelper$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LookAndFeelHelper$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$LookAndFeelHelper$_closure1() {
        Class $class$groovy$swing$LookAndFeelHelper$_closure1;
        if (($class$groovy$swing$LookAndFeelHelper$_closure1 = LookAndFeelHelper$_closure1.$class$groovy$swing$LookAndFeelHelper$_closure1) == null) {
            $class$groovy$swing$LookAndFeelHelper$_closure1 = (LookAndFeelHelper$_closure1.$class$groovy$swing$LookAndFeelHelper$_closure1 = class$("groovy.swing.LookAndFeelHelper$_closure1"));
        }
        return $class$groovy$swing$LookAndFeelHelper$_closure1;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$plaf$metal$MetalLookAndFeel() {
        Class $class$javax$swing$plaf$metal$MetalLookAndFeel;
        if (($class$javax$swing$plaf$metal$MetalLookAndFeel = LookAndFeelHelper$_closure1.$class$javax$swing$plaf$metal$MetalLookAndFeel) == null) {
            $class$javax$swing$plaf$metal$MetalLookAndFeel = (LookAndFeelHelper$_closure1.$class$javax$swing$plaf$metal$MetalLookAndFeel = class$("javax.swing.plaf.metal.MetalLookAndFeel"));
        }
        return $class$javax$swing$plaf$metal$MetalLookAndFeel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$plaf$metal$DefaultMetalTheme() {
        Class $class$javax$swing$plaf$metal$DefaultMetalTheme;
        if (($class$javax$swing$plaf$metal$DefaultMetalTheme = LookAndFeelHelper$_closure1.$class$javax$swing$plaf$metal$DefaultMetalTheme) == null) {
            $class$javax$swing$plaf$metal$DefaultMetalTheme = (LookAndFeelHelper$_closure1.$class$javax$swing$plaf$metal$DefaultMetalTheme = class$("javax.swing.plaf.metal.DefaultMetalTheme"));
        }
        return $class$javax$swing$plaf$metal$DefaultMetalTheme;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = LookAndFeelHelper$_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (LookAndFeelHelper$_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = LookAndFeelHelper$_closure1.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (LookAndFeelHelper$_closure1.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
