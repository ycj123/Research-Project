// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.AnnotationNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> allAnnotations;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> allAnnotations) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.allAnnotations = allAnnotations;
    }
    
    public Object doCall(final AnnotationNode annotationNode) {
        final AnnotationNode annotationNode2 = (AnnotationNode)new Reference(annotationNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ggrandchild = $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)annotationNode2).get());
        return $getCallSiteArray[2].call(this.allAnnotations.get(), ggrandchild);
    }
    
    public Object call(final AnnotationNode annotationNode) {
        final AnnotationNode annotationNode2 = (AnnotationNode)new Reference(annotationNode);
        return $getCallSiteArray()[3].callCurrent(this, ((Reference<Object>)annotationNode2).get());
    }
    
    public Object getAllAnnotations() {
        $getCallSiteArray();
        return this.allAnnotations.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
