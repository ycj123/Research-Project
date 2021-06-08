// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.ast.expr.NamedArgumentListExpression;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.expr.Expression;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingVisitor$_visitListOfExpressions_closure55 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
    
    public TreeNodeBuildingVisitor$_visitListOfExpressions_closure55(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Expression node) {
        final Expression node2 = (Expression)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)node2).get() instanceof NamedArgumentListExpression) {
            return $getCallSiteArray[0].callCurrent(this, ((Reference<Object>)node2).get(), $get$$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression(), new TreeNodeBuildingVisitor$_visitListOfExpressions_closure55_closure58(this, this.getThisObject()));
        }
        return $getCallSiteArray[1].call(((Reference<Object>)node2).get(), this.getThisObject());
    }
    
    public Object call(final Expression node) {
        final Expression node2 = (Expression)new Reference(node);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)node2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitListOfExpressions_closure55(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitListOfExpressions_closure55.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitListOfExpressions_closure55.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitListOfExpressions_closure55.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = TreeNodeBuildingVisitor$_visitListOfExpressions_closure55.$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = (TreeNodeBuildingVisitor$_visitListOfExpressions_closure55.$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = class$("org.codehaus.groovy.ast.expr.NamedArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
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
