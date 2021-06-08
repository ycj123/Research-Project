// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AutoIndentAction$_actionPerformed_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> whitespaceStr;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public AutoIndentAction$_actionPerformed_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> whitespaceStr) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.whitespaceStr = whitespaceStr;
    }
    
    public Object doCall(final Object all, final Object ws) {
        final Object ws2 = new Reference(ws);
        $getCallSiteArray();
        final String value = (String)ScriptBytecodeAdapter.castToType(((Reference<Object>)ws2).get(), $get$$class$java$lang$String());
        this.whitespaceStr.set(value);
        return value;
    }
    
    public Object call(final Object all, final Object ws) {
        final Object ws2 = new Reference(ws);
        return $getCallSiteArray()[0].callCurrent(this, all, ((Reference<Object>)ws2).get());
    }
    
    public String getWhitespaceStr() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.whitespaceStr.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$text$AutoIndentAction$_actionPerformed_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AutoIndentAction$_actionPerformed_closure1.$callSiteArray == null || ($createCallSiteArray = AutoIndentAction$_actionPerformed_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AutoIndentAction$_actionPerformed_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AutoIndentAction$_actionPerformed_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (AutoIndentAction$_actionPerformed_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
