// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.swing.table.DefaultTableModel;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JTableMetaMethods$_enhanceMetaClass_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Collections;
    
    public JTableMetaMethods$_enhanceMetaClass_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object model = new Reference($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(this)));
        if (((Reference<Object>)model).get() instanceof DefaultTableModel) {
            return $getCallSiteArray[2].call($get$$class$java$util$Collections(), $getCallSiteArray[3].call(((Reference<Object>)model).get()));
        }
        if (((Reference<Object>)model).get() instanceof groovy.model.DefaultTableModel) {
            return $getCallSiteArray[4].call($get$$class$java$util$Collections(), $getCallSiteArray[5].callGetProperty(((Reference<Object>)model).get()));
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JTableMetaMethods$_enhanceMetaClass_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JTableMetaMethods$_enhanceMetaClass_closure1.$callSiteArray == null || ($createCallSiteArray = JTableMetaMethods$_enhanceMetaClass_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JTableMetaMethods$_enhanceMetaClass_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Collections() {
        Class $class$java$util$Collections;
        if (($class$java$util$Collections = JTableMetaMethods$_enhanceMetaClass_closure1.$class$java$util$Collections) == null) {
            $class$java$util$Collections = (JTableMetaMethods$_enhanceMetaClass_closure1.$class$java$util$Collections = class$("java.util.Collections"));
        }
        return $class$java$util$Collections;
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
