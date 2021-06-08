// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingVisitor$_visitCastExpression_closure41 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$CodeVisitorSupport;
    
    public TreeNodeBuildingVisitor$_visitCastExpression_closure41(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        $getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$org$codehaus$groovy$ast$CodeVisitorSupport(), (GroovyObject)this.getThisObject(), "visitCastExpression", new Object[] { ((Reference<Object>)it2).get() });
    }
    
    public Object doCall() {
        return $getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitCastExpression_closure41(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitCastExpression_closure41.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitCastExpression_closure41.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitCastExpression_closure41.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TreeNodeBuildingVisitor$_visitCastExpression_closure41.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TreeNodeBuildingVisitor$_visitCastExpression_closure41.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$CodeVisitorSupport() {
        Class $class$org$codehaus$groovy$ast$CodeVisitorSupport;
        if (($class$org$codehaus$groovy$ast$CodeVisitorSupport = TreeNodeBuildingVisitor$_visitCastExpression_closure41.$class$org$codehaus$groovy$ast$CodeVisitorSupport) == null) {
            $class$org$codehaus$groovy$ast$CodeVisitorSupport = (TreeNodeBuildingVisitor$_visitCastExpression_closure41.$class$org$codehaus$groovy$ast$CodeVisitorSupport = class$("org.codehaus.groovy.ast.CodeVisitorSupport"));
        }
        return $class$org$codehaus$groovy$ast$CodeVisitorSupport;
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
