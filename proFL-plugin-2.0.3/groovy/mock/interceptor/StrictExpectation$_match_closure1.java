// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

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

class StrictExpectation$_match_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$DefaultGroovyMethods;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public StrictExpectation$_match_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> name) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$DefaultGroovyMethods(), ScriptBytecodeAdapter.createList(new Object[] { this.name.get() }), ((Reference<Object>)it2).get());
    }
    
    public String getName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.name.get(), $get$$class$java$lang$String());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$StrictExpectation$_match_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StrictExpectation$_match_closure1.$callSiteArray == null || ($createCallSiteArray = StrictExpectation$_match_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StrictExpectation$_match_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = StrictExpectation$_match_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (StrictExpectation$_match_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$DefaultGroovyMethods() {
        Class $class$org$codehaus$groovy$runtime$DefaultGroovyMethods;
        if (($class$org$codehaus$groovy$runtime$DefaultGroovyMethods = StrictExpectation$_match_closure1.$class$org$codehaus$groovy$runtime$DefaultGroovyMethods) == null) {
            $class$org$codehaus$groovy$runtime$DefaultGroovyMethods = (StrictExpectation$_match_closure1.$class$org$codehaus$groovy$runtime$DefaultGroovyMethods = class$("org.codehaus.groovy.runtime.DefaultGroovyMethods"));
        }
        return $class$org$codehaus$groovy$runtime$DefaultGroovyMethods;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = StrictExpectation$_match_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (StrictExpectation$_match_closure1.$class$java$lang$String = class$("java.lang.String"));
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