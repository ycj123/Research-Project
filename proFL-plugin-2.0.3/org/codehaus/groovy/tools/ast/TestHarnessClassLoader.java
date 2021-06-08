// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.ast;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.security.ProtectionDomain;
import org.codehaus.groovy.control.SourceUnit;
import groovy.lang.GroovyResourceLoader;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Manifest;
import java.io.File;
import org.codehaus.groovy.ast.ClassNode;
import java.net.URL;
import java.nio.ByteBuffer;
import groovy.lang.GroovyCodeSource;
import java.io.InputStream;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.control.CompilationUnit;
import java.security.CodeSource;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.ASTTransformation;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyClassLoader;

private class TestHarnessClassLoader extends GroovyClassLoader implements GroovyObject
{
    private ASTTransformation transform;
    private CompilePhase phase;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205708;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$transform$ASTTransformation;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilePhase;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilationUnit;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$ast$TestHarnessOperation;
    
    public TestHarnessClassLoader(final ASTTransformation transform, final CompilePhase phase) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.transform = (ASTTransformation)ScriptBytecodeAdapter.castToType(transform, $get$$class$org$codehaus$groovy$transform$ASTTransformation());
        this.phase = (CompilePhase)ScriptBytecodeAdapter.castToType(phase, $get$$class$org$codehaus$groovy$control$CompilePhase());
    }
    
    protected CompilationUnit createCompilationUnit(final CompilerConfiguration config, final CodeSource codeSource) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CompilationUnit cu = (CompilationUnit)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$GroovyClassLoader(), this, "createCompilationUnit", new Object[] { config, codeSource }), $get$$class$org$codehaus$groovy$control$CompilationUnit());
        $getCallSiteArray[0].call(cu, $getCallSiteArray[1].callConstructor($get$$class$org$codehaus$groovy$tools$ast$TestHarnessOperation(), this.transform), $getCallSiteArray[2].call(this.phase));
        return (CompilationUnit)ScriptBytecodeAdapter.castToType(cu, $get$$class$org$codehaus$groovy$control$CompilationUnit());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TestHarnessClassLoader.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TestHarnessClassLoader.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TestHarnessClassLoader.__timeStamp__239_neverHappen1292524205708 = 0L;
        TestHarnessClassLoader.__timeStamp = 1292524205708L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TestHarnessClassLoader.$callSiteArray == null || ($createCallSiteArray = TestHarnessClassLoader.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TestHarnessClassLoader.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TestHarnessClassLoader.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TestHarnessClassLoader.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$transform$ASTTransformation() {
        Class $class$org$codehaus$groovy$transform$ASTTransformation;
        if (($class$org$codehaus$groovy$transform$ASTTransformation = TestHarnessClassLoader.$class$org$codehaus$groovy$transform$ASTTransformation) == null) {
            $class$org$codehaus$groovy$transform$ASTTransformation = (TestHarnessClassLoader.$class$org$codehaus$groovy$transform$ASTTransformation = class$("org.codehaus.groovy.transform.ASTTransformation"));
        }
        return $class$org$codehaus$groovy$transform$ASTTransformation;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = TestHarnessClassLoader.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (TestHarnessClassLoader.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader() {
        Class $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
        if (($class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = TestHarnessClassLoader.$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader) == null) {
            $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = (TestHarnessClassLoader.$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = class$("org.codehaus.groovy.tools.ast.TestHarnessClassLoader"));
        }
        return $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilePhase() {
        Class $class$org$codehaus$groovy$control$CompilePhase;
        if (($class$org$codehaus$groovy$control$CompilePhase = TestHarnessClassLoader.$class$org$codehaus$groovy$control$CompilePhase) == null) {
            $class$org$codehaus$groovy$control$CompilePhase = (TestHarnessClassLoader.$class$org$codehaus$groovy$control$CompilePhase = class$("org.codehaus.groovy.control.CompilePhase"));
        }
        return $class$org$codehaus$groovy$control$CompilePhase;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilationUnit() {
        Class $class$org$codehaus$groovy$control$CompilationUnit;
        if (($class$org$codehaus$groovy$control$CompilationUnit = TestHarnessClassLoader.$class$org$codehaus$groovy$control$CompilationUnit) == null) {
            $class$org$codehaus$groovy$control$CompilationUnit = (TestHarnessClassLoader.$class$org$codehaus$groovy$control$CompilationUnit = class$("org.codehaus.groovy.control.CompilationUnit"));
        }
        return $class$org$codehaus$groovy$control$CompilationUnit;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$ast$TestHarnessOperation() {
        Class $class$org$codehaus$groovy$tools$ast$TestHarnessOperation;
        if (($class$org$codehaus$groovy$tools$ast$TestHarnessOperation = TestHarnessClassLoader.$class$org$codehaus$groovy$tools$ast$TestHarnessOperation) == null) {
            $class$org$codehaus$groovy$tools$ast$TestHarnessOperation = (TestHarnessClassLoader.$class$org$codehaus$groovy$tools$ast$TestHarnessOperation = class$("org.codehaus.groovy.tools.ast.TestHarnessOperation"));
        }
        return $class$org$codehaus$groovy$tools$ast$TestHarnessOperation;
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
