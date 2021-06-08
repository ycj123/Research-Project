// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_makeNode_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> ctorArgs;
    private Reference<Object> typeAlias;
    private Reference<Object> target;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_makeNode_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> ctorArgs, final Reference<Object> typeAlias, final Reference<Object> target) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.ctorArgs = ctorArgs;
        this.typeAlias = typeAlias;
        this.target = target;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.target.get(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[1].callCurrent(this, this.typeAlias.get(), this.ctorArgs.get()) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public List<Class<? super ASTNode>> getCtorArgs() {
        $getCallSiteArray();
        return (List<Class<? super ASTNode>>)ScriptBytecodeAdapter.castToType(this.ctorArgs.get(), $get$$class$java$util$List());
    }
    
    public String getTypeAlias() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.typeAlias.get(), $get$$class$java$lang$String());
    }
    
    public Class getTarget() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.target.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_makeNode_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_makeNode_closure3.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_makeNode_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_makeNode_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstSpecificationCompiler$_makeNode_closure3.$class$java$util$List) == null) {
            $class$java$util$List = (AstSpecificationCompiler$_makeNode_closure3.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_makeNode_closure3.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
