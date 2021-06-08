// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JComboBoxMetaMethods$_enhanceMetaClass_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3;
    
    public JComboBoxMetaMethods$_enhanceMetaClass_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object item) {
        final Object item2 = new Reference(item);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object value = ((Reference<Object>)item2).get();
        ScriptBytecodeAdapter.setProperty(value, $get$$class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3(), $getCallSiteArray[0].callGroovyObjectGetProperty(this), "selectedItem");
        return value;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JComboBoxMetaMethods$_enhanceMetaClass_closure3.$callSiteArray == null || ($createCallSiteArray = JComboBoxMetaMethods$_enhanceMetaClass_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JComboBoxMetaMethods$_enhanceMetaClass_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3() {
        Class $class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3;
        if (($class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3 = JComboBoxMetaMethods$_enhanceMetaClass_closure3.$class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3) == null) {
            $class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3 = (JComboBoxMetaMethods$_enhanceMetaClass_closure3.$class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3 = class$("groovy.swing.binding.JComboBoxMetaMethods$_enhanceMetaClass_closure3"));
        }
        return $class$groovy$swing$binding$JComboBoxMetaMethods$_enhanceMetaClass_closure3;
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
