// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16 extends Closure implements GeneratedClosure
{
    private Reference<Object> count;
    private Reference<Object> showTypes;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16(final Object _outerInstance, final Object _thisObject, final Reference<Object> count, final Reference<Object> showTypes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.count = count;
        this.showTypes = showTypes;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(this.showTypes.get())) {
            $getCallSiteArray[0].callCurrent(this, $getCallSiteArray[1].callGetProperty(((Reference<Object>)it2).get()));
            $getCallSiteArray[2].callCurrent(this, " ");
        }
        if (((Reference<Object>)it2).get() instanceof VariableExpression) {
            $getCallSiteArray[3].callCurrent(this, ((Reference<Object>)it2).get(), Boolean.FALSE);
        }
        else if (((Reference<Object>)it2).get() instanceof ConstantExpression) {
            $getCallSiteArray[4].callCurrent(this, ((Reference<Object>)it2).get(), Boolean.FALSE);
        }
        else {
            $getCallSiteArray[5].call(((Reference<Object>)it2).get(), this.getThisObject());
        }
        this.count.get();
        this.count.set($getCallSiteArray[6].call(this.count.get()));
        if (DefaultTypeTransformation.booleanUnbox(this.count.get())) {
            return $getCallSiteArray[7].callCurrent(this, ", ");
        }
        return null;
    }
    
    public Integer getCount() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.count.get(), $get$$class$java$lang$Integer());
    }
    
    public boolean getShowTypes() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(this.showTypes.get(), $get$$class$java$lang$Boolean()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[8].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
