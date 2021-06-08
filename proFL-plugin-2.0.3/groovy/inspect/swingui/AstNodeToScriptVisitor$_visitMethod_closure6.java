// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitMethod_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$MethodNode;
    
    public AstNodeToScriptVisitor$_visitMethod_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> node) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetPropertySafe(this.node.get()), this.getThisObject());
    }
    
    public MethodNode getNode() {
        $getCallSiteArray();
        return (MethodNode)ScriptBytecodeAdapter.castToType(this.node.get(), $get$$class$org$codehaus$groovy$ast$MethodNode());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitMethod_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitMethod_closure6.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitMethod_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitMethod_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitMethod_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitMethod_closure6.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$MethodNode() {
        Class $class$org$codehaus$groovy$ast$MethodNode;
        if (($class$org$codehaus$groovy$ast$MethodNode = AstNodeToScriptVisitor$_visitMethod_closure6.$class$org$codehaus$groovy$ast$MethodNode) == null) {
            $class$org$codehaus$groovy$ast$MethodNode = (AstNodeToScriptVisitor$_visitMethod_closure6.$class$org$codehaus$groovy$ast$MethodNode = class$("org.codehaus.groovy.ast.MethodNode"));
        }
        return $class$org$codehaus$groovy$ast$MethodNode;
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
