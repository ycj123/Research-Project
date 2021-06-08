// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ScriptToTreeNodeAdapter$_getPropertyTable_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> node;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ScriptToTreeNodeAdapter$_getPropertyTable_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> node) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.node = node;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(((Reference<Object>)it2).get())));
        final Object value = new Reference(null);
        try {
            if (DefaultTypeTransformation.booleanUnbox((this.node.get() instanceof DeclarationExpression && DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(((Reference<Object>)name).get(), "variableExpression") && !ScriptBytecodeAdapter.compareEqual(((Reference<Object>)name).get(), "tupleExpression")) ? Boolean.FALSE : Boolean.TRUE)) ? Boolean.TRUE : Boolean.FALSE)) {
                ((Reference<Object>)value).set($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(this.node.get())));
            }
            else {
                ((Reference<Object>)value).set($getCallSiteArray[4].call($getCallSiteArray[5].call(((Reference<Object>)it2).get(), this.node.get())));
            }
        }
        catch (GroovyBugError reflectionArtefact) {
            ((Reference<Object>)value).set(null);
        }
        final Object type = $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGetProperty(((Reference<Object>)it2).get())));
        return ScriptBytecodeAdapter.createList(new Object[] { ((Reference<Object>)name).get(), ((Reference<Object>)value).get(), type });
    }
    
    public Object getNode() {
        $getCallSiteArray();
        return this.node.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter$_getPropertyTable_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ScriptToTreeNodeAdapter$_getPropertyTable_closure3.$callSiteArray == null || ($createCallSiteArray = ScriptToTreeNodeAdapter$_getPropertyTable_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ScriptToTreeNodeAdapter$_getPropertyTable_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ScriptToTreeNodeAdapter$_getPropertyTable_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ScriptToTreeNodeAdapter$_getPropertyTable_closure3.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
