// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitClass_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassNode;
    
    public AstNodeToScriptVisitor$_visitClass_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> node) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetPropertySafe(this.node.get()), new AstNodeToScriptVisitor$_visitClass_closure2_closure26(this, this.getThisObject()));
        $getCallSiteArray[2].callCurrent(this);
        $getCallSiteArray[3].callSafe($getCallSiteArray[4].callGetPropertySafe(this.node.get()), new AstNodeToScriptVisitor$_visitClass_closure2_closure27(this, this.getThisObject()));
        $getCallSiteArray[5].callCurrent(this);
        $getCallSiteArray[6].callSafe($getCallSiteArray[7].callGetPropertySafe(this.node.get()), new AstNodeToScriptVisitor$_visitClass_closure2_closure28(this, this.getThisObject()));
        $getCallSiteArray[8].callCurrent(this);
        return $getCallSiteArray[9].callSafe($getCallSiteArray[10].callGetPropertySafe(this.node.get()), new AstNodeToScriptVisitor$_visitClass_closure2_closure29(this, this.getThisObject()));
    }
    
    public ClassNode getNode() {
        $getCallSiteArray();
        return (ClassNode)ScriptBytecodeAdapter.castToType(this.node.get(), $get$$class$org$codehaus$groovy$ast$ClassNode());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[11].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitClass_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitClass_closure2.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitClass_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitClass_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitClass_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitClass_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassNode() {
        Class $class$org$codehaus$groovy$ast$ClassNode;
        if (($class$org$codehaus$groovy$ast$ClassNode = AstNodeToScriptVisitor$_visitClass_closure2.$class$org$codehaus$groovy$ast$ClassNode) == null) {
            $class$org$codehaus$groovy$ast$ClassNode = (AstNodeToScriptVisitor$_visitClass_closure2.$class$org$codehaus$groovy$ast$ClassNode = class$("org.codehaus.groovy.ast.ClassNode"));
        }
        return $class$org$codehaus$groovy$ast$ClassNode;
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
