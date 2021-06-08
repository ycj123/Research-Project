// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.FieldNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderTransformation$_visit_closure2_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> transformer;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBuilderTransformation$_visit_closure2_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> transformer) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.transformer = transformer;
    }
    
    public Object doCall(final FieldNode node) {
        final FieldNode node2 = (FieldNode)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetPropertySafe(((Reference<Object>)node2).get()), this.transformer.get());
    }
    
    public Object call(final FieldNode node) {
        final FieldNode node2 = (FieldNode)new Reference(node);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)node2).get());
    }
    
    public Object getTransformer() {
        $getCallSiteArray();
        return this.transformer.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation$_visit_closure2_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderTransformation$_visit_closure2_closure6.$callSiteArray == null || ($createCallSiteArray = AstBuilderTransformation$_visit_closure2_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderTransformation$_visit_closure2_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
