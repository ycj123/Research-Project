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
import org.codehaus.groovy.ast.PropertyNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingNodeOperation$_collectPropertyData_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> allProperties;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    
    public TreeNodeBuildingNodeOperation$_collectPropertyData_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> allProperties) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.allProperties = allProperties;
    }
    
    public Object doCall(final PropertyNode propertyNode) {
        final PropertyNode propertyNode2 = (PropertyNode)new Reference(propertyNode);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ggrandchild = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)propertyNode2).get()));
        $getCallSiteArray[2].call(this.allProperties.get(), ((Reference<Object>)ggrandchild).get());
        final TreeNodeBuildingVisitor visitor = (TreeNodeBuildingVisitor)new Reference($getCallSiteArray[3].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), $getCallSiteArray[4].callGroovyObjectGetProperty(this)));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callGetPropertySafe($getCallSiteArray[6].callGetProperty(((Reference<Object>)propertyNode2).get())))) {
            $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callGetProperty(((Reference<Object>)propertyNode2).get())), ((Reference<Object>)visitor).get());
            return $getCallSiteArray[10].call(((Reference<Object>)ggrandchild).get(), $getCallSiteArray[11].callGetProperty(((Reference<Object>)visitor).get()));
        }
        return null;
    }
    
    public Object call(final PropertyNode propertyNode) {
        final PropertyNode propertyNode2 = (PropertyNode)new Reference(propertyNode);
        return $getCallSiteArray()[12].callCurrent(this, ((Reference<Object>)propertyNode2).get());
    }
    
    public Object getAllProperties() {
        $getCallSiteArray();
        return this.allProperties.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation$_collectPropertyData_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation$_collectPropertyData_closure2.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation$_collectPropertyData_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation$_collectPropertyData_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingNodeOperation$_collectPropertyData_closure2.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingNodeOperation$_collectPropertyData_closure2.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
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
