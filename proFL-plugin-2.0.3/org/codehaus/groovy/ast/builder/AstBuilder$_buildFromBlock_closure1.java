// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilder$_buildFromBlock_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
    
    public AstBuilder$_buildFromBlock_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object node) {
        final Object node2 = new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)node2).get() instanceof BlockStatement) {
            return $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(ScriptBytecodeAdapter.castToType(((Reference<Object>)node2).get(), $get$$class$org$codehaus$groovy$ast$stmt$BlockStatement())), AstBuilder$_buildFromBlock_closure1.$const$0);
        }
        return ((Reference<Object>)node2).get();
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilder$_buildFromBlock_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilder$_buildFromBlock_closure1.$callSiteArray == null || ($createCallSiteArray = AstBuilder$_buildFromBlock_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilder$_buildFromBlock_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$BlockStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
        if (($class$org$codehaus$groovy$ast$stmt$BlockStatement = AstBuilder$_buildFromBlock_closure1.$class$org$codehaus$groovy$ast$stmt$BlockStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$BlockStatement = (AstBuilder$_buildFromBlock_closure1.$class$org$codehaus$groovy$ast$stmt$BlockStatement = class$("org.codehaus.groovy.ast.stmt.BlockStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$BlockStatement;
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
