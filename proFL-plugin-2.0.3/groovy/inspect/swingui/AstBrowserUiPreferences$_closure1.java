// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

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

class AstBrowserUiPreferences$_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> phase;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public AstBrowserUiPreferences$_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> phase) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phase = phase;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return ScriptBytecodeAdapter.compareEqual($getCallSiteArray()[0].callGetProperty(((Reference<Object>)it2).get()), this.phase.get()) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public Integer getPhase() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.phase.get(), $get$$class$java$lang$Integer());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowserUiPreferences$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowserUiPreferences$_closure1.$callSiteArray == null || ($createCallSiteArray = AstBrowserUiPreferences$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowserUiPreferences$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstBrowserUiPreferences$_closure1.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstBrowserUiPreferences$_closure1.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowserUiPreferences$_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowserUiPreferences$_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
