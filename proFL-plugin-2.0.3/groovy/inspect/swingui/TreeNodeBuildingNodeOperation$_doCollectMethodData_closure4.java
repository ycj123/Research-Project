// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.tree.DefaultMutableTreeNode;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.MethodNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> allMethods;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    
    public TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> allMethods) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.allMethods = allMethods;
    }
    
    public Object doCall(final MethodNode methodNode) {
        final MethodNode methodNode2 = (MethodNode)new Reference(methodNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ggrandchild = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)methodNode2).get()));
        $getCallSiteArray[2].call(this.allMethods.get(), ((Reference<Object>)ggrandchild).get());
        $getCallSiteArray[3].callSafe($getCallSiteArray[4].callGetProperty(((Reference<Object>)methodNode2).get()), new TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6(this, this.getThisObject(), (Reference<Object>)ggrandchild));
        final TreeNodeBuildingVisitor visitor = (TreeNodeBuildingVisitor)new Reference($getCallSiteArray[5].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), $getCallSiteArray[6].callGroovyObjectGetProperty(this)));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callGetProperty(((Reference<Object>)methodNode2).get()))) {
            return null;
        }
        $getCallSiteArray[8].call($getCallSiteArray[9].callGetProperty(((Reference<Object>)methodNode2).get()), ((Reference<Object>)visitor).get());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].callGetProperty(((Reference<Object>)visitor).get()))) {
            return $getCallSiteArray[11].call(((Reference<Object>)ggrandchild).get(), $getCallSiteArray[12].callGetProperty(((Reference<Object>)visitor).get()));
        }
        return null;
    }
    
    public Object call(final MethodNode methodNode) {
        final MethodNode methodNode2 = (MethodNode)new Reference(methodNode);
        return $getCallSiteArray()[13].callCurrent(this, ((Reference<Object>)methodNode2).get());
    }
    
    public DefaultMutableTreeNode getAllMethods() {
        $getCallSiteArray();
        return (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType(this.allMethods.get(), $get$$class$javax$swing$tree$DefaultMutableTreeNode());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
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
