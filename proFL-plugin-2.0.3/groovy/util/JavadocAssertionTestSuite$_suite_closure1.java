// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import junit.framework.TestSuite;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestSuite$_suite_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> suite;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$junit$framework$TestSuite;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$java$io$File;
    
    public JavadocAssertionTestSuite$_suite_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> suite) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.suite = suite;
    }
    
    public Object doCall(final Object filename) {
        final Object filename2 = new Reference(filename);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String code = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callConstructor($get$$class$java$io$File(), ((Reference<Object>)filename2).get())), $get$$class$java$lang$String());
        final Class test = (Class)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), ((Reference<Object>)filename2).get(), code), $get$$class$java$lang$Class()));
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference)test).get(), null)) {
            return $getCallSiteArray[4].call(this.suite.get(), ((Reference)test).get());
        }
        return null;
    }
    
    public TestSuite getSuite() {
        $getCallSiteArray();
        return (TestSuite)ScriptBytecodeAdapter.castToType(this.suite.get(), $get$$class$junit$framework$TestSuite());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestSuite$_suite_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestSuite$_suite_closure1.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestSuite$_suite_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestSuite$_suite_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$junit$framework$TestSuite() {
        Class $class$junit$framework$TestSuite;
        if (($class$junit$framework$TestSuite = JavadocAssertionTestSuite$_suite_closure1.$class$junit$framework$TestSuite) == null) {
            $class$junit$framework$TestSuite = (JavadocAssertionTestSuite$_suite_closure1.$class$junit$framework$TestSuite = class$("junit.framework.TestSuite"));
        }
        return $class$junit$framework$TestSuite;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestSuite$_suite_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestSuite$_suite_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JavadocAssertionTestSuite$_suite_closure1.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JavadocAssertionTestSuite$_suite_closure1.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = JavadocAssertionTestSuite$_suite_closure1.$class$java$io$File) == null) {
            $class$java$io$File = (JavadocAssertionTestSuite$_suite_closure1.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
