// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> lineNumberToAssertions;
    private Reference<Object> codeIndex;
    private Reference<Object> code;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> lineNumberToAssertions, final Reference<Object> codeIndex, final Reference<Object> code) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.lineNumberToAssertions = lineNumberToAssertions;
        this.codeIndex = codeIndex;
        this.code = code;
    }
    
    public Object doCall(final Object tag) {
        final Object tag2 = new Reference(tag);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.codeIndex.set(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(this.code.get(), ((Reference<Object>)tag2).get(), this.codeIndex.get()), $get$$class$java$lang$Integer()));
        final Integer lineNumber = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].call(this.code.get(), JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$const$0, this.codeIndex.get()), "(?m)^")), $get$$class$java$lang$Integer());
        this.codeIndex.set(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(this.codeIndex.get(), $getCallSiteArray[5].call(((Reference<Object>)tag2).get())), $get$$class$java$lang$Integer()));
        final String assertion = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].callCurrent(this, ((Reference<Object>)tag2).get()), $get$$class$java$lang$String());
        return $getCallSiteArray[7].call($getCallSiteArray[8].call(this.lineNumberToAssertions.get(), lineNumber, ScriptBytecodeAdapter.createList(new Object[0])), assertion);
    }
    
    public Map getLineNumberToAssertions() {
        $getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(this.lineNumberToAssertions.get(), $get$$class$java$util$Map());
    }
    
    public Integer getCodeIndex() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.codeIndex.get(), $get$$class$java$lang$Integer());
    }
    
    public String getCode() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.code.get(), $get$$class$java$lang$String());
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$util$Map) == null) {
            $class$java$util$Map = (JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
