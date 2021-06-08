// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.expr.Expression;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitAnnotationNode_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> first;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstNodeToScriptVisitor$_visitAnnotationNode_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> first) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.first = first;
    }
    
    public Object doCall(final String name, final Expression value) {
        final String name2 = (String)new Reference(name);
        final Expression value2 = (Expression)new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(this.first.get())) {
            this.first.set(Boolean.FALSE);
        }
        else {
            $getCallSiteArray[0].callCurrent(this, ", ");
        }
        $getCallSiteArray[1].callCurrent(this, $getCallSiteArray[2].call(((Reference<Object>)name2).get(), " = "));
        return $getCallSiteArray[3].call(((Reference<Object>)value2).get(), this.getThisObject());
    }
    
    public Object call(final String name, final Expression value) {
        final String name2 = (String)new Reference(name);
        final Expression value2 = (Expression)new Reference(value);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get());
    }
    
    public Boolean getFirst() {
        $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType(this.first.get(), $get$$class$java$lang$Boolean());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitAnnotationNode_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitAnnotationNode_closure8.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitAnnotationNode_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitAnnotationNode_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor$_visitAnnotationNode_closure8.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor$_visitAnnotationNode_closure8.$class$java$lang$Boolean = class$("java.lang.Boolean"));
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
