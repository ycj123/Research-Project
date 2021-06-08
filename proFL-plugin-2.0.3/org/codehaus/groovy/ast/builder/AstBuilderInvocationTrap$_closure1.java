// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.ImportNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderInvocationTrap$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBuilderInvocationTrap$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final ImportNode importStatement) {
        final ImportNode importStatement2 = (ImportNode)new Reference(importStatement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGetProperty(((Reference<Object>)importStatement2).get())), "org.codehaus.groovy.ast.builder.AstBuilder")) {
            return $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGetProperty(((Reference<Object>)importStatement2).get()));
        }
        return null;
    }
    
    public Object call(final ImportNode importStatement) {
        final ImportNode importStatement2 = (ImportNode)new Reference(importStatement);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)importStatement2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderInvocationTrap$_closure1.$callSiteArray == null || ($createCallSiteArray = AstBuilderInvocationTrap$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderInvocationTrap$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
