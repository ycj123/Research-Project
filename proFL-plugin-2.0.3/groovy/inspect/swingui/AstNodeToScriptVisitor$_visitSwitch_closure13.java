// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitSwitch_closure13 extends Closure implements GeneratedClosure
{
    private Reference<Object> statement;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
    
    public AstNodeToScriptVisitor$_visitSwitch_closure13(final Object _outerInstance, final Object _thisObject, final Reference<Object> statement) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.statement = statement;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callSafe($getCallSiteArray[1].callGetPropertySafe(this.statement.get()), new AstNodeToScriptVisitor$_visitSwitch_closure13_closure31(this, this.getThisObject()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetPropertySafe(this.statement.get()))) {
            $getCallSiteArray[3].callCurrent(this, "default: ");
            $getCallSiteArray[4].callCurrent(this);
            return $getCallSiteArray[5].callSafe($getCallSiteArray[6].callGetPropertySafe(this.statement.get()), this.getThisObject());
        }
        return null;
    }
    
    public SwitchStatement getStatement() {
        $getCallSiteArray();
        return (SwitchStatement)ScriptBytecodeAdapter.castToType(this.statement.get(), $get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitSwitch_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitSwitch_closure13.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitSwitch_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitSwitch_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitSwitch_closure13.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitSwitch_closure13.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$SwitchStatement = AstNodeToScriptVisitor$_visitSwitch_closure13.$class$org$codehaus$groovy$ast$stmt$SwitchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$SwitchStatement = (AstNodeToScriptVisitor$_visitSwitch_closure13.$class$org$codehaus$groovy$ast$stmt$SwitchStatement = class$("org.codehaus.groovy.ast.stmt.SwitchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
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
