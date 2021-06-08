// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.DynamicVariable;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.expr.VariableExpression;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingVisitor$_visitVariableExpression_closure44 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$DynamicVariable;
    
    public TreeNodeBuildingVisitor$_visitVariableExpression_closure44(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final VariableExpression it) {
        final VariableExpression it2 = (VariableExpression)new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()))) {
            return null;
        }
        if ($getCallSiteArray[1].callGetProperty(((Reference<Object>)it2).get()) instanceof Parameter) {
            return $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callGetProperty(((Reference<Object>)it2).get()), $get$$class$org$codehaus$groovy$ast$Parameter()), $get$$class$org$codehaus$groovy$ast$Parameter()));
        }
        if ($getCallSiteArray[4].callGetProperty(((Reference<Object>)it2).get()) instanceof DynamicVariable) {
            return $getCallSiteArray[5].callCurrent(this, $getCallSiteArray[6].callGetProperty(((Reference<Object>)it2).get()), $get$$class$org$codehaus$groovy$ast$DynamicVariable(), new TreeNodeBuildingVisitor$_visitVariableExpression_closure44_closure57(this, this.getThisObject()));
        }
        return null;
    }
    
    public Object call(final VariableExpression it) {
        final VariableExpression it2 = (VariableExpression)new Reference(it);
        return $getCallSiteArray()[7].callCurrent(this, ((Reference<Object>)it2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitVariableExpression_closure44(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
        }
        return $class$org$codehaus$groovy$ast$Parameter;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$DynamicVariable() {
        Class $class$org$codehaus$groovy$ast$DynamicVariable;
        if (($class$org$codehaus$groovy$ast$DynamicVariable = TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$class$org$codehaus$groovy$ast$DynamicVariable) == null) {
            $class$org$codehaus$groovy$ast$DynamicVariable = (TreeNodeBuildingVisitor$_visitVariableExpression_closure44.$class$org$codehaus$groovy$ast$DynamicVariable = class$("org.codehaus.groovy.ast.DynamicVariable"));
        }
        return $class$org$codehaus$groovy$ast$DynamicVariable;
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
