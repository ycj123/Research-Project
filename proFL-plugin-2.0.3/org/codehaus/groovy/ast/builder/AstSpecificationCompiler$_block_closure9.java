// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_block_closure9 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$VariableScope;
    
    public AstSpecificationCompiler$_block_closure9(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$BlockStatement(), $getCallSiteArray[1].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[2].callGroovyObjectGetProperty(this)), $getCallSiteArray[3].callConstructor($get$$class$org$codehaus$groovy$ast$VariableScope()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_block_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_block_closure9.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_block_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_block_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_block_closure9.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_block_closure9.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$BlockStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
        if (($class$org$codehaus$groovy$ast$stmt$BlockStatement = AstSpecificationCompiler$_block_closure9.$class$org$codehaus$groovy$ast$stmt$BlockStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$BlockStatement = (AstSpecificationCompiler$_block_closure9.$class$org$codehaus$groovy$ast$stmt$BlockStatement = class$("org.codehaus.groovy.ast.stmt.BlockStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$BlockStatement;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_block_closure9.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_block_closure9.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$VariableScope() {
        Class $class$org$codehaus$groovy$ast$VariableScope;
        if (($class$org$codehaus$groovy$ast$VariableScope = AstSpecificationCompiler$_block_closure9.$class$org$codehaus$groovy$ast$VariableScope) == null) {
            $class$org$codehaus$groovy$ast$VariableScope = (AstSpecificationCompiler$_block_closure9.$class$org$codehaus$groovy$ast$VariableScope = class$("org.codehaus.groovy.ast.VariableScope"));
        }
        return $class$org$codehaus$groovy$ast$VariableScope;
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
