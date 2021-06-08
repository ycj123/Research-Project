// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestBuilder$_getAssertionTags_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> assertions;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    
    public JavadocAssertionTestBuilder$_getAssertionTags_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> assertions) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.assertions = assertions;
    }
    
    public Object doCall(final Object javadoc) {
        final Object javadoc2 = new Reference(javadoc);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.assertions.get(), $getCallSiteArray[1].call(((Reference<Object>)javadoc2).get(), $getCallSiteArray[2].callGroovyObjectGetProperty(this)));
    }
    
    public List getAssertions() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.assertions.get(), $get$$class$java$util$List());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder$_getAssertionTags_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder$_getAssertionTags_closure1.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder$_getAssertionTags_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder$_getAssertionTags_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = JavadocAssertionTestBuilder$_getAssertionTags_closure1.$class$java$util$List) == null) {
            $class$java$util$List = (JavadocAssertionTestBuilder$_getAssertionTags_closure1.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
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
