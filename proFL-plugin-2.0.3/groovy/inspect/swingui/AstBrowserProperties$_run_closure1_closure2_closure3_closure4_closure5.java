// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5;
    
    public AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty("BlockStatement - (${expression.statements ? expression.statements.size() : 0})", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), this, "BlockStatement");
        ScriptBytecodeAdapter.setGroovyObjectProperty("ExpressionStatement - ${expression?.expression.getClass().simpleName}", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), this, "ExpressionStatement");
        ScriptBytecodeAdapter.setGroovyObjectProperty("ReturnStatement - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), this, "ReturnStatement");
        ScriptBytecodeAdapter.setGroovyObjectProperty("TryCatchStatement - ${expression.catchStatements?.size ?: 0} catch, ${expression.finallyStatement ? 1 : 0} finally", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), this, "TryCatchStatement");
        final String messageArgument = "CatchStatement - $expression.exceptionType]";
        ScriptBytecodeAdapter.setGroovyObjectProperty(messageArgument, $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), this, "CatchStatement");
        return messageArgument;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$callSiteArray == null || ($createCallSiteArray = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5() {
        Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5;
        if (($class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5 = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5) == null) {
            $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5 = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5 = class$("groovy.inspect.swingui.AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5"));
        }
        return $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5;
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
