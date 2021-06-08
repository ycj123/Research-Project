// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_tryCatch_closure19_closure32 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstSpecificationCompiler$_tryCatch_closure19_closure32(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object statement) {
        final Object statement2 = new Reference(statement);
        return $getCallSiteArray()[0].call(this.result.get(), ((Reference<Object>)statement2).get());
    }
    
    public Object getResult() {
        $getCallSiteArray();
        return this.result.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_tryCatch_closure19_closure32(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_tryCatch_closure19_closure32.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_tryCatch_closure19_closure32.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_tryCatch_closure19_closure32.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
