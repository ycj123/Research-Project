// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ASTNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderTransformation$_visit_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> transformer;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBuilderTransformation$_visit_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> transformer) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.transformer = transformer;
    }
    
    public Object doCall(final ASTNode it) {
        final ASTNode it2 = (ASTNode)new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(((DefaultTypeTransformation.booleanUnbox(((Reference<Object>)it2).get()) ? Boolean.FALSE : Boolean.TRUE) instanceof AnnotationNode && (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)it2).get()) ? Boolean.FALSE : Boolean.TRUE) instanceof ClassNode) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[0].call(((Reference<Object>)it2).get(), this.transformer.get());
        }
        return null;
    }
    
    public Object call(final ASTNode it) {
        final ASTNode it2 = (ASTNode)new Reference(it);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)it2).get());
    }
    
    public Object getTransformer() {
        $getCallSiteArray();
        return this.transformer.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation$_visit_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderTransformation$_visit_closure1.$callSiteArray == null || ($createCallSiteArray = AstBuilderTransformation$_visit_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderTransformation$_visit_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
