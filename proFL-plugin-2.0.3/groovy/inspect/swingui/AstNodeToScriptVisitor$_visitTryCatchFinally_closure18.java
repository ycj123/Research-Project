// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitTryCatchFinally_closure18 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstNodeToScriptVisitor$_visitTryCatchFinally_closure18(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final CatchStatement catchStatement) {
        final CatchStatement catchStatement2 = (CatchStatement)new Reference(catchStatement);
        return $getCallSiteArray()[0].callCurrent(this, ((Reference<Object>)catchStatement2).get());
    }
    
    public Object call(final CatchStatement catchStatement) {
        final CatchStatement catchStatement2 = (CatchStatement)new Reference(catchStatement);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)catchStatement2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitTryCatchFinally_closure18(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitTryCatchFinally_closure18.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitTryCatchFinally_closure18.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitTryCatchFinally_closure18.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
