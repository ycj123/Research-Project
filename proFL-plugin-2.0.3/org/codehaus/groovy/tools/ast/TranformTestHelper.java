// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.ast;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.io.File;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.ASTTransformation;
import groovy.lang.GroovyObject;

public class TranformTestHelper implements GroovyObject
{
    private ASTTransformation transform;
    private CompilePhase phase;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203898;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$ast$TranformTestHelper;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$transform$ASTTransformation;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilePhase;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public TranformTestHelper(final ASTTransformation transform, final CompilePhase phase) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.transform = (ASTTransformation)ScriptBytecodeAdapter.castToType(transform, $get$$class$org$codehaus$groovy$transform$ASTTransformation());
        this.phase = (CompilePhase)ScriptBytecodeAdapter.castToType(phase, $get$$class$org$codehaus$groovy$control$CompilePhase());
    }
    
    public Class parse(final File input) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final TestHarnessClassLoader loader = (TestHarnessClassLoader)$getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader(), this.transform, this.phase);
        return (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(loader, input), $get$$class$java$lang$Class());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$ast$TranformTestHelper()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TranformTestHelper.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TranformTestHelper.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TranformTestHelper.__timeStamp__239_neverHappen1292524203898 = 0L;
        TranformTestHelper.__timeStamp = 1292524203898L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$ast$TranformTestHelper(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TranformTestHelper.$callSiteArray == null || ($createCallSiteArray = TranformTestHelper.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TranformTestHelper.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$ast$TranformTestHelper() {
        Class $class$org$codehaus$groovy$tools$ast$TranformTestHelper;
        if (($class$org$codehaus$groovy$tools$ast$TranformTestHelper = TranformTestHelper.$class$org$codehaus$groovy$tools$ast$TranformTestHelper) == null) {
            $class$org$codehaus$groovy$tools$ast$TranformTestHelper = (TranformTestHelper.$class$org$codehaus$groovy$tools$ast$TranformTestHelper = class$("org.codehaus.groovy.tools.ast.TranformTestHelper"));
        }
        return $class$org$codehaus$groovy$tools$ast$TranformTestHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TranformTestHelper.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TranformTestHelper.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$transform$ASTTransformation() {
        Class $class$org$codehaus$groovy$transform$ASTTransformation;
        if (($class$org$codehaus$groovy$transform$ASTTransformation = TranformTestHelper.$class$org$codehaus$groovy$transform$ASTTransformation) == null) {
            $class$org$codehaus$groovy$transform$ASTTransformation = (TranformTestHelper.$class$org$codehaus$groovy$transform$ASTTransformation = class$("org.codehaus.groovy.transform.ASTTransformation"));
        }
        return $class$org$codehaus$groovy$transform$ASTTransformation;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader() {
        Class $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
        if (($class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = TranformTestHelper.$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader) == null) {
            $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = (TranformTestHelper.$class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader = class$("org.codehaus.groovy.tools.ast.TestHarnessClassLoader"));
        }
        return $class$org$codehaus$groovy$tools$ast$TestHarnessClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilePhase() {
        Class $class$org$codehaus$groovy$control$CompilePhase;
        if (($class$org$codehaus$groovy$control$CompilePhase = TranformTestHelper.$class$org$codehaus$groovy$control$CompilePhase) == null) {
            $class$org$codehaus$groovy$control$CompilePhase = (TranformTestHelper.$class$org$codehaus$groovy$control$CompilePhase = class$("org.codehaus.groovy.control.CompilePhase"));
        }
        return $class$org$codehaus$groovy$control$CompilePhase;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = TranformTestHelper.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (TranformTestHelper.$class$java$lang$Class = class$("java.lang.Class"));
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
