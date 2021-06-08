// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Image;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class OutputTransforms$_loadOutputTransforms_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$ImageIcon;
    
    public OutputTransforms$_loadOutputTransforms_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)it2).get() instanceof Image) {
            return $getCallSiteArray[0].callConstructor($get$$class$javax$swing$ImageIcon(), ((Reference<Object>)it2).get());
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OutputTransforms$_loadOutputTransforms_closure4.$callSiteArray == null || ($createCallSiteArray = OutputTransforms$_loadOutputTransforms_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OutputTransforms$_loadOutputTransforms_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ImageIcon() {
        Class $class$javax$swing$ImageIcon;
        if (($class$javax$swing$ImageIcon = OutputTransforms$_loadOutputTransforms_closure4.$class$javax$swing$ImageIcon) == null) {
            $class$javax$swing$ImageIcon = (OutputTransforms$_loadOutputTransforms_closure4.$class$javax$swing$ImageIcon = class$("javax.swing.ImageIcon"));
        }
        return $class$javax$swing$ImageIcon;
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
