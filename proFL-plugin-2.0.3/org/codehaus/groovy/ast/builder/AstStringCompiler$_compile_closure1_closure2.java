// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstStringCompiler$_compile_closure1_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> scriptClassName;
    private Reference<Object> statementsOnly;
    private Reference<Object> acc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$util$List;
    
    public AstStringCompiler$_compile_closure1_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> scriptClassName, final Reference<Object> statementsOnly, final Reference<Object> acc) {
        final Reference scriptClassName2 = new Reference((T)scriptClassName);
        final Reference statementsOnly2 = new Reference((T)statementsOnly);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.scriptClassName = scriptClassName2.get();
        this.statementsOnly = statementsOnly2.get();
        this.acc = acc;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()), this.scriptClassName.get()) && DefaultTypeTransformation.booleanUnbox(this.statementsOnly.get())) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[1].call(this.acc.get(), ((Reference<Object>)it2).get());
        }
        return null;
    }
    
    public Object getScriptClassName() {
        $getCallSiteArray();
        return this.scriptClassName.get();
    }
    
    public boolean getStatementsOnly() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(this.statementsOnly.get(), $get$$class$java$lang$Boolean()));
    }
    
    public List getAcc() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.acc.get(), $get$$class$java$util$List());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler$_compile_closure1_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstStringCompiler$_compile_closure1_closure2.$callSiteArray == null || ($createCallSiteArray = AstStringCompiler$_compile_closure1_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstStringCompiler$_compile_closure1_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstStringCompiler$_compile_closure1_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstStringCompiler$_compile_closure1_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstStringCompiler$_compile_closure1_closure2.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstStringCompiler$_compile_closure1_closure2.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstStringCompiler$_compile_closure1_closure2.$class$java$util$List) == null) {
            $class$java$util$List = (AstStringCompiler$_compile_closure1_closure2.$class$java$util$List = class$("java.util.List"));
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
