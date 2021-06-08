// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.ast;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.transform.ASTTransformation;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilationUnit;

private class TestHarnessOperation extends PrimaryClassNodeOperation implements GroovyObject
{
    private ASTTransformation transform;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204706;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$transform$ASTTransformation;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$ast$TestHarnessOperation;
    
    public TestHarnessOperation(final Object transform) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.transform = (ASTTransformation)ScriptBytecodeAdapter.castToType(transform, $get$$class$org$codehaus$groovy$transform$ASTTransformation());
    }
    
    public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) {
        $getCallSiteArray()[0].call(this.transform, null, source);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$ast$TestHarnessOperation()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TestHarnessOperation.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TestHarnessOperation.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TestHarnessOperation.__timeStamp__239_neverHappen1292524204706 = 0L;
        TestHarnessOperation.__timeStamp = 1292524204706L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$ast$TestHarnessOperation(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TestHarnessOperation.$callSiteArray == null || ($createCallSiteArray = TestHarnessOperation.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TestHarnessOperation.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TestHarnessOperation.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TestHarnessOperation.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$transform$ASTTransformation() {
        Class $class$org$codehaus$groovy$transform$ASTTransformation;
        if (($class$org$codehaus$groovy$transform$ASTTransformation = TestHarnessOperation.$class$org$codehaus$groovy$transform$ASTTransformation) == null) {
            $class$org$codehaus$groovy$transform$ASTTransformation = (TestHarnessOperation.$class$org$codehaus$groovy$transform$ASTTransformation = class$("org.codehaus.groovy.transform.ASTTransformation"));
        }
        return $class$org$codehaus$groovy$transform$ASTTransformation;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$ast$TestHarnessOperation() {
        Class $class$org$codehaus$groovy$tools$ast$TestHarnessOperation;
        if (($class$org$codehaus$groovy$tools$ast$TestHarnessOperation = TestHarnessOperation.$class$org$codehaus$groovy$tools$ast$TestHarnessOperation) == null) {
            $class$org$codehaus$groovy$tools$ast$TestHarnessOperation = (TestHarnessOperation.$class$org$codehaus$groovy$tools$ast$TestHarnessOperation = class$("org.codehaus.groovy.tools.ast.TestHarnessOperation"));
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
