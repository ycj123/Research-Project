// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitCaseStatement_closure14 extends Closure implements GeneratedClosure
{
    private Reference<Object> statement;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
    
    public AstNodeToScriptVisitor$_visitCaseStatement_closure14(final Object _outerInstance, final Object _thisObject, final Reference<Object> statement) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.statement = statement;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetPropertySafe(this.statement.get()), this.getThisObject());
    }
    
    public CaseStatement getStatement() {
        $getCallSiteArray();
        return (CaseStatement)ScriptBytecodeAdapter.castToType(this.statement.get(), $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitCaseStatement_closure14(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitCaseStatement_closure14.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitCaseStatement_closure14.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitCaseStatement_closure14.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitCaseStatement_closure14.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitCaseStatement_closure14.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CaseStatement = AstNodeToScriptVisitor$_visitCaseStatement_closure14.$class$org$codehaus$groovy$ast$stmt$CaseStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CaseStatement = (AstNodeToScriptVisitor$_visitCaseStatement_closure14.$class$org$codehaus$groovy$ast$stmt$CaseStatement = class$("org.codehaus.groovy.ast.stmt.CaseStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CaseStatement;
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
