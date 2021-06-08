// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.List;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_enforceConstraints_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> spec;
    private Reference<Object> methodName;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public AstSpecificationCompiler$_enforceConstraints_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> spec, final Reference<Object> methodName) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.spec = spec;
        this.methodName = methodName;
    }
    
    public Object doCall(final int it) {
        final Integer it2 = (Integer)new Reference(DefaultTypeTransformation.box(it));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object actualClass = $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), ((Reference<Object>)it2).get()));
        final Object expectedClass = $getCallSiteArray[3].call(this.spec.get(), ((Reference<Object>)it2).get());
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(expectedClass, actualClass))) {
            throw (Throwable)$getCallSiteArray[5].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { this.methodName.get(), this.spec.get(), $getCallSiteArray[6].callSafe($getCallSiteArray[7].callGroovyObjectGetProperty(this), new AstSpecificationCompiler$_enforceConstraints_closure2_closure29(this, this.getThisObject())) }, new String[] { "", " could not be invoked. Expected to receive parameters ", " but found ", "" }));
        }
        return $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), ((Reference<Object>)it2).get());
    }
    
    public Object call(final int it) {
        final Integer it2 = (Integer)new Reference(DefaultTypeTransformation.box(it));
        return $getCallSiteArray()[10].callCurrent(this, ((Reference<Object>)it2).get());
    }
    
    public List<Class> getSpec() {
        $getCallSiteArray();
        return (List<Class>)ScriptBytecodeAdapter.castToType(this.spec.get(), $get$$class$java$util$List());
    }
    
    public String getMethodName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.methodName.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_enforceConstraints_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_enforceConstraints_closure2.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_enforceConstraints_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_enforceConstraints_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$util$List) == null) {
            $class$java$util$List = (AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_enforceConstraints_closure2.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
