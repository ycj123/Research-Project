// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingVisitor$_visitParameter_closure28 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    
    public TreeNodeBuildingVisitor$_visitParameter_closure28(final Object _outerInstance, final Object _thisObject, final Reference<Object> node) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGetProperty(this.node.get()))) {
            return $getCallSiteArray[1].callSafe($getCallSiteArray[2].callGetProperty(this.node.get()), this.getThisObject());
        }
        return null;
    }
    
    public Parameter getNode() {
        $getCallSiteArray();
        return (Parameter)ScriptBytecodeAdapter.castToType(this.node.get(), $get$$class$org$codehaus$groovy$ast$Parameter());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitParameter_closure28(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitParameter_closure28.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitParameter_closure28.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitParameter_closure28.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TreeNodeBuildingVisitor$_visitParameter_closure28.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TreeNodeBuildingVisitor$_visitParameter_closure28.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = TreeNodeBuildingVisitor$_visitParameter_closure28.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (TreeNodeBuildingVisitor$_visitParameter_closure28.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
        }
        return $class$org$codehaus$groovy$ast$Parameter;
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
