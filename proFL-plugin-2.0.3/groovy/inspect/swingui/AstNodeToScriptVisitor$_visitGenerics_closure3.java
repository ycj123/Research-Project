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
import org.codehaus.groovy.ast.GenericsType;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstNodeToScriptVisitor$_visitGenerics_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> first;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public AstNodeToScriptVisitor$_visitGenerics_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> first) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.first = first;
    }
    
    public Object doCall(final GenericsType it) {
        final GenericsType it2 = (GenericsType)new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(this.first.get())) {
            $getCallSiteArray[0].callCurrent(this, ", ");
        }
        this.first.set(Boolean.FALSE);
        $getCallSiteArray[1].callCurrent(this, $getCallSiteArray[2].callGetProperty(((Reference<Object>)it2).get()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].callGetProperty(((Reference<Object>)it2).get()))) {
            $getCallSiteArray[4].callCurrent(this, " extends ");
            final Boolean innerFirst = (Boolean)new Reference(Boolean.TRUE);
            $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(((Reference<Object>)it2).get()), new AstNodeToScriptVisitor$_visitGenerics_closure3_closure30(this, this.getThisObject(), (Reference<Object>)innerFirst));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callGetProperty(((Reference<Object>)it2).get()))) {
            $getCallSiteArray[8].callCurrent(this, " super ");
            return $getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].callGetProperty(((Reference<Object>)it2).get()));
        }
        return null;
    }
    
    public Object call(final GenericsType it) {
        final GenericsType it2 = (GenericsType)new Reference(it);
        return $getCallSiteArray()[11].callCurrent(this, ((Reference<Object>)it2).get());
    }
    
    public Boolean getFirst() {
        $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType(this.first.get(), $get$$class$java$lang$Boolean());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor$_visitGenerics_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor$_visitGenerics_closure3.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor$_visitGenerics_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor$_visitGenerics_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor$_visitGenerics_closure3.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor$_visitGenerics_closure3.$class$java$lang$Boolean = class$("java.lang.Boolean"));
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
