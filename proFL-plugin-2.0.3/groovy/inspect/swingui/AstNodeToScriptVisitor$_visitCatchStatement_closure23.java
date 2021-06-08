// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitCatchStatement_closure23 extends Closure implements GeneratedClosure
{
    private Reference<Object> statement;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
    
    public AstNodeToScriptVisitor$_visitCatchStatement_closure23(final Object _outerInstance, final Object _thisObject, final Reference<Object> statement) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.statement = statement;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetProperty(this.statement.get()), this.getThisObject());
    }
    
    public CatchStatement getStatement() {
        $getCallSiteArray();
        return (CatchStatement)ScriptBytecodeAdapter.castToType(this.statement.get(), $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitCatchStatement_closure23(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitCatchStatement_closure23.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitCatchStatement_closure23.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitCatchStatement_closure23.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitCatchStatement_closure23.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitCatchStatement_closure23.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CatchStatement = AstNodeToScriptVisitor$_visitCatchStatement_closure23.$class$org$codehaus$groovy$ast$stmt$CatchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CatchStatement = (AstNodeToScriptVisitor$_visitCatchStatement_closure23.$class$org$codehaus$groovy$ast$stmt$CatchStatement = class$("org.codehaus.groovy.ast.stmt.CatchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CatchStatement;
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
