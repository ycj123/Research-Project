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

class TreeNodeBuildingVisitor$_visitForLoop_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$CodeVisitorSupport;
    
    public TreeNodeBuildingVisitor$_visitForLoop_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        $getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$org$codehaus$groovy$ast$CodeVisitorSupport(), (GroovyObject)this.getThisObject(), "visitForLoop", new Object[] { ((Reference<Object>)it2).get() });
    }
    
    public Object doCall() {
        return $getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitForLoop_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitForLoop_closure2.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitForLoop_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitForLoop_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TreeNodeBuildingVisitor$_visitForLoop_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TreeNodeBuildingVisitor$_visitForLoop_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$CodeVisitorSupport() {
        Class $class$org$codehaus$groovy$ast$CodeVisitorSupport;
        if (($class$org$codehaus$groovy$ast$CodeVisitorSupport = TreeNodeBuildingVisitor$_visitForLoop_closure2.$class$org$codehaus$groovy$ast$CodeVisitorSupport) == null) {
            $class$org$codehaus$groovy$ast$CodeVisitorSupport = (TreeNodeBuildingVisitor$_visitForLoop_closure2.$class$org$codehaus$groovy$ast$CodeVisitorSupport = class$("org.codehaus.groovy.ast.CodeVisitorSupport"));
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
