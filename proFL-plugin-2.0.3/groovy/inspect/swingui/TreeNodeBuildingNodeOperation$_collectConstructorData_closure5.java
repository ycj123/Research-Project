// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.ConstructorNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingNodeOperation$_collectConstructorData_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> allCtors;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    
    public TreeNodeBuildingNodeOperation$_collectConstructorData_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> allCtors) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.allCtors = allCtors;
    }
    
    public Object doCall(final ConstructorNode ctorNode) {
        final ConstructorNode ctorNode2 = (ConstructorNode)new Reference(ctorNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ggrandchild = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)ctorNode2).get()));
        $getCallSiteArray[2].call(this.allCtors.get(), ((Reference<Object>)ggrandchild).get());
        final TreeNodeBuildingVisitor visitor = (TreeNodeBuildingVisitor)new Reference($getCallSiteArray[3].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), $getCallSiteArray[4].callGroovyObjectGetProperty(this)));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callGetProperty(((Reference<Object>)ctorNode2).get()))) {
            return null;
        }
        $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty(((Reference<Object>)ctorNode2).get()), ((Reference<Object>)visitor).get());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGetProperty(((Reference<Object>)visitor).get()))) {
            return $getCallSiteArray[9].call(((Reference<Object>)ggrandchild).get(), $getCallSiteArray[10].callGetProperty(((Reference<Object>)visitor).get()));
        }
        return null;
    }
    
    public Object call(final ConstructorNode ctorNode) {
        final ConstructorNode ctorNode2 = (ConstructorNode)new Reference(ctorNode);
        return $getCallSiteArray()[11].callCurrent(this, ((Reference<Object>)ctorNode2).get());
    }
    
    public Object getAllCtors() {
        $getCallSiteArray();
        return this.allCtors.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation$_collectConstructorData_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation$_collectConstructorData_closure5.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation$_collectConstructorData_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation$_collectConstructorData_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingNodeOperation$_collectConstructorData_closure5.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingNodeOperation$_collectConstructorData_closure5.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
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
