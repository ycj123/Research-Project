// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.ModuleNode;
import java.util.List;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstStringCompiler$_compile_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> scriptClassName;
    private Reference<Object> statementsOnly;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstStringCompiler$_compile_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> scriptClassName, final Reference<Object> statementsOnly) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.scriptClassName = scriptClassName;
        this.statementsOnly = statementsOnly;
    }
    
    public Object doCall(final List acc, final ModuleNode node) {
        final List acc2 = (List)new Reference(acc);
        final ModuleNode node2 = (ModuleNode)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGetProperty(((Reference<Object>)node2).get()))) {
            $getCallSiteArray[1].call(((Reference)acc2).get(), $getCallSiteArray[2].callGetProperty(((Reference<Object>)node2).get()));
        }
        final CallSite callSite = $getCallSiteArray[3];
        final Object callGetProperty = $getCallSiteArray[4].callGetProperty(((Reference<Object>)node2).get());
        final Object thisObject = this.getThisObject();
        final Reference<Object> scriptClassName2;
        final Reference scriptClassName = scriptClassName2 = this.scriptClassName;
        final Reference statementsOnly = this.statementsOnly;
        callSite.callSafe(callGetProperty, new AstStringCompiler$_compile_closure1_closure2(this, thisObject, scriptClassName2, statementsOnly, (Reference<Object>)acc2));
        return ((Reference)acc2).get();
    }
    
    public Object call(final List acc, final ModuleNode node) {
        final List acc2 = (List)new Reference(acc);
        final ModuleNode node2 = (ModuleNode)new Reference(node);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference)acc2).get(), ((Reference<Object>)node2).get());
    }
    
    public Object getScriptClassName() {
        $getCallSiteArray();
        return this.scriptClassName.get();
    }
    
    public boolean getStatementsOnly() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(this.statementsOnly.get(), $get$$class$java$lang$Boolean()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler$_compile_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstStringCompiler$_compile_closure1.$callSiteArray == null || ($createCallSiteArray = AstStringCompiler$_compile_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstStringCompiler$_compile_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstStringCompiler$_compile_closure1.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstStringCompiler$_compile_closure1.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
