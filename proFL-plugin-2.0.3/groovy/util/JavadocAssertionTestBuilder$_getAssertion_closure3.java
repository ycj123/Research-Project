// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestBuilder$_getAssertion_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> assertion;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public JavadocAssertionTestBuilder$_getAssertion_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> assertion) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.assertion = assertion;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final String value3 = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].call(this.assertion.get(), new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { "(?i)&", ";" }), ((Reference<Object>)value2).get()), $get$$class$java$lang$String());
        this.assertion.set(value3);
        return value3;
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public String getAssertion() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.assertion.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder$_getAssertion_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder$_getAssertion_closure3.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder$_getAssertion_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder$_getAssertion_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestBuilder$_getAssertion_closure3.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestBuilder$_getAssertion_closure3.$class$java$lang$String = class$("java.lang.String"));
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
