// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> differentiator;
    private Reference<Object> assertions;
    private Reference<Object> filename;
    private Reference<Object> lineNumber;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Character;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> differentiator, final Reference<Object> assertions, final Reference<Object> filename, final Reference<Object> lineNumber) {
        final Reference filename2 = new Reference((T)filename);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.differentiator = differentiator;
        this.assertions = assertions;
        this.filename = filename2.get();
        this.lineNumber = lineNumber;
    }
    
    public Object doCall(final Object assertion) {
        final Object assertion2 = new Reference(assertion);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String suffix = (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[0].call(this.assertions.get()), JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$const$0) ? new GStringImpl(new Object[] { this.lineNumber.get(), this.differentiator.get() }, new String[] { "", "", "" }) : this.lineNumber.get(), $get$$class$java$lang$String());
        this.differentiator.get();
        this.differentiator.set($getCallSiteArray[1].call(this.differentiator.get()));
        return $getCallSiteArray[2].callCurrent(this, suffix, ((Reference<Object>)assertion2).get(), $getCallSiteArray[3].callCurrent(this, this.filename.get()));
    }
    
    public Character getDifferentiator() {
        $getCallSiteArray();
        return (Character)ScriptBytecodeAdapter.castToType(this.differentiator.get(), $get$$class$java$lang$Character());
    }
    
    public Object getAssertions() {
        $getCallSiteArray();
        return this.assertions.get();
    }
    
    public String getFilename() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.filename.get(), $get$$class$java$lang$String());
    }
    
    public Object getLineNumber() {
        $getCallSiteArray();
        return this.lineNumber.get();
    }
    
    static {
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Character() {
        Class $class$java$lang$Character;
        if (($class$java$lang$Character = JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$class$java$lang$Character) == null) {
            $class$java$lang$Character = (JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$class$java$lang$Character = class$("java.lang.Character"));
        }
        return $class$java$lang$Character;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestBuilder$_getTestMethods_closure4_closure5.$class$java$lang$String = class$("java.lang.String"));
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
