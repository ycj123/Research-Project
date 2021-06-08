// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JTableMetaMethods$_enhanceMetaClass_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTableMetaMethods;
    
    public JTableMetaMethods$_enhanceMetaClass_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callStatic($get$$class$groovy$swing$binding$JTableMetaMethods(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JTableMetaMethods$_enhanceMetaClass_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JTableMetaMethods$_enhanceMetaClass_closure2.$callSiteArray == null || ($createCallSiteArray = JTableMetaMethods$_enhanceMetaClass_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JTableMetaMethods$_enhanceMetaClass_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTableMetaMethods() {
        Class $class$groovy$swing$binding$JTableMetaMethods;
        if (($class$groovy$swing$binding$JTableMetaMethods = JTableMetaMethods$_enhanceMetaClass_closure2.$class$groovy$swing$binding$JTableMetaMethods) == null) {
            $class$groovy$swing$binding$JTableMetaMethods = (JTableMetaMethods$_enhanceMetaClass_closure2.$class$groovy$swing$binding$JTableMetaMethods = class$("groovy.swing.binding.JTableMetaMethods"));
        }
        return $class$groovy$swing$binding$JTableMetaMethods;
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
