// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderInvocationTrap$_convertClosureToSource_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> expression;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
    
    public AstBuilderInvocationTrap$_convertClosureToSource_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> expression) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.expression = expression;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object line = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)it2).get(), null));
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)line).get(), null)) {
            $getCallSiteArray[2].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)it2).get(), $getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGroovyObjectGetProperty(this)) }, new String[] { "Error calculating source code for expression. Trying to read line ", " from ", "" }), this.expression.get());
        }
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)it2).get(), $getCallSiteArray[5].callGetProperty(this.expression.get()))) {
            ((Reference<Object>)line).set($getCallSiteArray[6].call(((Reference<Object>)line).get(), AstBuilderInvocationTrap$_convertClosureToSource_closure5.$const$0, $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty(this.expression.get()), AstBuilderInvocationTrap$_convertClosureToSource_closure5.$const$1)));
        }
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)it2).get(), $getCallSiteArray[9].callGetProperty(this.expression.get()))) {
            ((Reference<Object>)line).set($getCallSiteArray[10].call(((Reference<Object>)line).get(), $getCallSiteArray[11].call($getCallSiteArray[12].callGetProperty(this.expression.get()), AstBuilderInvocationTrap$_convertClosureToSource_closure5.$const$1)));
        }
        return ((Reference<Object>)line).get();
    }
    
    public ClosureExpression getExpression() {
        $getCallSiteArray();
        return (ClosureExpression)ScriptBytecodeAdapter.castToType(this.expression.get(), $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[13].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap$_convertClosureToSource_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderInvocationTrap$_convertClosureToSource_closure5.$callSiteArray == null || ($createCallSiteArray = AstBuilderInvocationTrap$_convertClosureToSource_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderInvocationTrap$_convertClosureToSource_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBuilderInvocationTrap$_convertClosureToSource_closure5.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBuilderInvocationTrap$_convertClosureToSource_closure5.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClosureExpression = AstBuilderInvocationTrap$_convertClosureToSource_closure5.$class$org$codehaus$groovy$ast$expr$ClosureExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClosureExpression = (AstBuilderInvocationTrap$_convertClosureToSource_closure5.$class$org$codehaus$groovy$ast$expr$ClosureExpression = class$("org.codehaus.groovy.ast.expr.ClosureExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClosureExpression;
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
