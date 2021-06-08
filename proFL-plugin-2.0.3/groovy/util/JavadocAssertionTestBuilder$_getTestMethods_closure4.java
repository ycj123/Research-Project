// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestBuilder$_getTestMethods_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> filename;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Character;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public JavadocAssertionTestBuilder$_getTestMethods_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> filename) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.filename = filename;
    }
    
    public Object doCall(final Object lineNumber, final Object assertions) {
        final Object lineNumber2 = new Reference(lineNumber);
        final Object assertions2 = new Reference(assertions);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Character differentiator = (Character)new Reference(ScriptBytecodeAdapter.castToType("a", $get$$class$java$lang$Character()));
        final CallSite callSite = $getCallSiteArray[0];
        final Object value = ((Reference<Object>)assertions2).get();
        final Object thisObject = this.getThisObject();
        final Character differentiator2 = differentiator;
        final Object assertions3 = assertions2;
        final Reference filename = this.filename;
        return callSite.call(value, new JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5(this, thisObject, (Reference<Object>)differentiator2, (Reference<Object>)assertions3, filename, (Reference<Object>)lineNumber2));
    }
    
    public Object call(final Object lineNumber, final Object assertions) {
        final Object lineNumber2 = new Reference(lineNumber);
        final Object assertions2 = new Reference(assertions);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)lineNumber2).get(), ((Reference<Object>)assertions2).get());
    }
    
    public String getFilename() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.filename.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder$_getTestMethods_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder$_getTestMethods_closure4.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder$_getTestMethods_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder$_getTestMethods_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Character() {
        Class $class$java$lang$Character;
        if (($class$java$lang$Character = JavadocAssertionTestBuilder$_getTestMethods_closure4.$class$java$lang$Character) == null) {
            $class$java$lang$Character = (JavadocAssertionTestBuilder$_getTestMethods_closure4.$class$java$lang$Character = class$("java.lang.Character"));
        }
        return $class$java$lang$Character;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestBuilder$_getTestMethods_closure4.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestBuilder$_getTestMethods_closure4.$class$java$lang$String = class$("java.lang.String"));
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
