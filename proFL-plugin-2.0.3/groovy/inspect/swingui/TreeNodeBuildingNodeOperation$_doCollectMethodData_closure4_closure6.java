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
import org.codehaus.groovy.ast.Parameter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> ggrandchild;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    
    public TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> ggrandchild) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.ggrandchild = ggrandchild;
    }
    
    public Object doCall(final Parameter parameter) {
        final Parameter parameter2 = (Parameter)new Reference(parameter);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object gggrandchild = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)parameter2).get()));
        $getCallSiteArray[2].call(this.ggrandchild.get(), ((Reference<Object>)gggrandchild).get());
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].callGetProperty(((Reference<Object>)parameter2).get()))) {
            return null;
        }
        final TreeNodeBuildingVisitor visitor = (TreeNodeBuildingVisitor)$getCallSiteArray[4].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), $getCallSiteArray[5].callGroovyObjectGetProperty(this));
        $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty(((Reference<Object>)parameter2).get()), visitor);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGetProperty(visitor))) {
            return $getCallSiteArray[9].call(((Reference<Object>)gggrandchild).get(), $getCallSiteArray[10].callGetProperty(visitor));
        }
        return null;
    }
    
    public Object call(final Parameter parameter) {
        final Parameter parameter2 = (Parameter)new Reference(parameter);
        return $getCallSiteArray()[11].callCurrent(this, ((Reference<Object>)parameter2).get());
    }
    
    public Object getGgrandchild() {
        $getCallSiteArray();
        return this.ggrandchild.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4_closure6.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
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
