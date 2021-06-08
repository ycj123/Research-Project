// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ScriptToTreeNodeAdapter$_compile_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> operation;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
    
    public ScriptToTreeNodeAdapter$_compile_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> operation) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.operation = operation;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(this.operation.get()), $getCallSiteArray[2].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), ((Reference<Object>)it2).get()));
    }
    
    public TreeNodeBuildingNodeOperation getOperation() {
        $getCallSiteArray();
        return (TreeNodeBuildingNodeOperation)ScriptBytecodeAdapter.castToType(this.operation.get(), $get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter$_compile_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ScriptToTreeNodeAdapter$_compile_closure1.$callSiteArray == null || ($createCallSiteArray = ScriptToTreeNodeAdapter$_compile_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ScriptToTreeNodeAdapter$_compile_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ScriptToTreeNodeAdapter$_compile_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ScriptToTreeNodeAdapter$_compile_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = ScriptToTreeNodeAdapter$_compile_closure1.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (ScriptToTreeNodeAdapter$_compile_closure1.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = ScriptToTreeNodeAdapter$_compile_closure1.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = (ScriptToTreeNodeAdapter$_compile_closure1.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = class$("groovy.inspect.swingui.TreeNodeBuildingNodeOperation"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
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
