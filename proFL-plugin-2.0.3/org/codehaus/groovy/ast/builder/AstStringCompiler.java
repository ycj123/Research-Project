// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.control.CompilationUnit;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.PackageScope;
import groovy.lang.GroovyObject;

@PackageScope
public class AstStringCompiler implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203889;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilerConfiguration;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$lang$GroovyCodeSource;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilationUnit;
    
    public AstStringCompiler() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public List<ASTNode> compile(final String script, final CompilePhase compilePhase, final boolean statementsOnly) {
        final Boolean statementsOnly2 = (Boolean)new Reference(DefaultTypeTransformation.box(statementsOnly));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object scriptClassName = new Reference($getCallSiteArray[0].call("script", $getCallSiteArray[1].call($get$$class$java$lang$System())));
        final GroovyClassLoader classLoader = (GroovyClassLoader)$getCallSiteArray[2].callConstructor($get$$class$groovy$lang$GroovyClassLoader());
        final GroovyCodeSource codeSource = (GroovyCodeSource)$getCallSiteArray[3].callConstructor($get$$class$groovy$lang$GroovyCodeSource(), script, $getCallSiteArray[4].call(((Reference<Object>)scriptClassName).get(), ".groovy"), "/groovy/script");
        final CompilationUnit cu = (CompilationUnit)$getCallSiteArray[5].callConstructor($get$$class$org$codehaus$groovy$control$CompilationUnit(), $getCallSiteArray[6].callGetProperty($get$$class$org$codehaus$groovy$control$CompilerConfiguration()), $getCallSiteArray[7].callGetProperty(codeSource), classLoader);
        $getCallSiteArray[8].call(cu, $getCallSiteArray[9].call(codeSource), script);
        $getCallSiteArray[10].call(cu, $getCallSiteArray[11].call(compilePhase));
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGetProperty($getCallSiteArray[14].callGetProperty(cu)), ScriptBytecodeAdapter.createList(new Object[0]), new AstStringCompiler$_compile_closure1(this, this, (Reference<Object>)scriptClassName, (Reference<Object>)statementsOnly2)), $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstStringCompiler.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstStringCompiler.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AstStringCompiler.__timeStamp__239_neverHappen1292524203889 = 0L;
        AstStringCompiler.__timeStamp = 1292524203889L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstStringCompiler.$callSiteArray == null || ($createCallSiteArray = AstStringCompiler.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstStringCompiler.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstStringCompiler.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstStringCompiler.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilerConfiguration() {
        Class $class$org$codehaus$groovy$control$CompilerConfiguration;
        if (($class$org$codehaus$groovy$control$CompilerConfiguration = AstStringCompiler.$class$org$codehaus$groovy$control$CompilerConfiguration) == null) {
            $class$org$codehaus$groovy$control$CompilerConfiguration = (AstStringCompiler.$class$org$codehaus$groovy$control$CompilerConfiguration = class$("org.codehaus.groovy.control.CompilerConfiguration"));
        }
        return $class$org$codehaus$groovy$control$CompilerConfiguration;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = AstStringCompiler.$class$java$lang$System) == null) {
            $class$java$lang$System = (AstStringCompiler.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = AstStringCompiler.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (AstStringCompiler.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler() {
        Class $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
        if (($class$org$codehaus$groovy$ast$builder$AstStringCompiler = AstStringCompiler.$class$org$codehaus$groovy$ast$builder$AstStringCompiler) == null) {
            $class$org$codehaus$groovy$ast$builder$AstStringCompiler = (AstStringCompiler.$class$org$codehaus$groovy$ast$builder$AstStringCompiler = class$("org.codehaus.groovy.ast.builder.AstStringCompiler"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstStringCompiler.$class$java$util$List) == null) {
            $class$java$util$List = (AstStringCompiler.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyCodeSource() {
        Class $class$groovy$lang$GroovyCodeSource;
        if (($class$groovy$lang$GroovyCodeSource = AstStringCompiler.$class$groovy$lang$GroovyCodeSource) == null) {
            $class$groovy$lang$GroovyCodeSource = (AstStringCompiler.$class$groovy$lang$GroovyCodeSource = class$("groovy.lang.GroovyCodeSource"));
        }
        return $class$groovy$lang$GroovyCodeSource;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilationUnit() {
        Class $class$org$codehaus$groovy$control$CompilationUnit;
        if (($class$org$codehaus$groovy$control$CompilationUnit = AstStringCompiler.$class$org$codehaus$groovy$control$CompilationUnit) == null) {
            $class$org$codehaus$groovy$control$CompilationUnit = (AstStringCompiler.$class$org$codehaus$groovy$control$CompilationUnit = class$("org.codehaus.groovy.control.CompilationUnit"));
        }
        return $class$org$codehaus$groovy$control$CompilationUnit;
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
