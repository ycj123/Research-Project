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
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25 extends Closure implements GeneratedClosure
{
    private Reference<Object> first;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25(final Object _outerInstance, final Object _thisObject, final Reference<Object> first) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.first = first;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.first.get())) {
            $getCallSiteArray[0].callCurrent(this, ", ");
        }
        this.first.set(Boolean.FALSE);
        return $getCallSiteArray[1].call(((Reference<Object>)it2).get(), this.getThisObject());
    }
    
    public Boolean getFirst() {
        $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType(this.first.get(), $get$$class$java$lang$Boolean());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25.$class$java$lang$Boolean = class$("java.lang.Boolean"));
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
