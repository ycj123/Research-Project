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
import org.codehaus.groovy.ast.ClassNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitGenerics_closure3_closure30 extends Closure implements GeneratedClosure
{
    private Reference<Object> innerFirst;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstNodeToScriptVisitor$_visitGenerics_closure3_closure30(final Object _outerInstance, final Object _thisObject, final Reference<Object> innerFirst) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.innerFirst = innerFirst;
    }
    
    public Object doCall(final ClassNode upperBound) {
        final ClassNode upperBound2 = (ClassNode)new Reference(upperBound);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.innerFirst.get())) {
            $getCallSiteArray[0].callCurrent(this, " & ");
        }
        this.innerFirst.set(Boolean.FALSE);
        return $getCallSiteArray[1].callCurrent(this, ((Reference<Object>)upperBound2).get());
    }
    
    public Object call(final ClassNode upperBound) {
        final ClassNode upperBound2 = (ClassNode)new Reference(upperBound);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)upperBound2).get());
    }
    
    public Boolean getInnerFirst() {
        $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType(this.innerFirst.get(), $get$$class$java$lang$Boolean());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitGenerics_closure3_closure30(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitGenerics_closure3_closure30.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitGenerics_closure3_closure30.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitGenerics_closure3_closure30.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor$_visitGenerics_closure3_closure30.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor$_visitGenerics_closure3_closure30.$class$java$lang$Boolean = class$("java.lang.Boolean"));
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
