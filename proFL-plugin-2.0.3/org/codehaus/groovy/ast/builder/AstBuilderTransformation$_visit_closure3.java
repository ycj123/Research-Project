// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.MethodNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderTransformation$_visit_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> transformer;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBuilderTransformation$_visit_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> transformer) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.transformer = transformer;
    }
    
    public Object doCall(final MethodNode node) {
        final MethodNode node2 = (MethodNode)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object callGetPropertySafe = $getCallSiteArray[1].callGetPropertySafe($getCallSiteArray[2].callGetPropertySafe(((Reference<Object>)node2).get()));
        final Object thisObject = this.getThisObject();
        final Reference transformer = this.transformer;
        callSite.callSafe(callGetPropertySafe, new AstBuilderTransformation$_visit_closure3_closure8(this, thisObject, transformer));
        return $getCallSiteArray[3].callSafe($getCallSiteArray[4].callGetPropertySafe(((Reference<Object>)node2).get()), transformer.get());
    }
    
    public Object call(final MethodNode node) {
        final MethodNode node2 = (MethodNode)new Reference(node);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)node2).get());
    }
    
    public Object getTransformer() {
        $getCallSiteArray();
        return this.transformer.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation$_visit_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderTransformation$_visit_closure3.$callSiteArray == null || ($createCallSiteArray = AstBuilderTransformation$_visit_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderTransformation$_visit_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
