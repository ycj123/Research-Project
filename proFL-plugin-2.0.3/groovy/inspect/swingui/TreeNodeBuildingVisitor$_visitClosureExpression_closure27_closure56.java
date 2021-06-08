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
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object parameter) {
        final Object parameter2 = new Reference(parameter);
        return $getCallSiteArray()[0].callCurrent(this, ((Reference<Object>)parameter2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor$_visitClosureExpression_closure27_closure56.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
