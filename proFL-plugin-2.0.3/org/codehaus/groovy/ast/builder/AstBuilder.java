// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import groovy.lang.Closure;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class AstBuilder implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203695;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstBuilder;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$java$lang$IllegalStateException;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilePhase;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstBuilder() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public List<ASTNode> buildFromCode(final CompilePhase phase, final boolean statementsOnly, final Closure block) {
        throw (Throwable)$getCallSiteArray()[0].callConstructor($get$$class$java$lang$IllegalStateException(), "AstBuilder.build(CompilePhase, boolean, Closure):List<ASTNode> should never be called at runtime.\nAre you sure you are using it correctly?\n");
    }
    
    public List<ASTNode> buildFromString(final CompilePhase phase, final boolean statementsOnly, final String source) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(source) && !ScriptBytecodeAdapter.compareEqual("", $getCallSiteArray[1].call(source))) ? Boolean.FALSE : Boolean.TRUE)) {
            throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$IllegalArgumentException(), "A source must be specified");
        }
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].callConstructor($get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler()), source, phase, DefaultTypeTransformation.box(statementsOnly)), $get$$class$java$util$List());
    }
    
    private List<ASTNode> buildFromBlock(final CompilePhase phase, final boolean statementsOnly, final String source) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(source) && !ScriptBytecodeAdapter.compareEqual("", $getCallSiteArray[5].call(source))) ? Boolean.FALSE : Boolean.TRUE)) {
            throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$java$lang$IllegalArgumentException(), "A source must be specified");
        }
        final Object labelledSource = $getCallSiteArray[7].call(new GStringImpl(new Object[] { $getCallSiteArray[8].call($get$$class$java$lang$System()) }, new String[] { "__synthesized__label__", "__:" }), source);
        final List result = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call($getCallSiteArray[10].callConstructor($get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler()), labelledSource, phase, DefaultTypeTransformation.box(statementsOnly)), $get$$class$java$util$List());
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[11].call(result, new AstBuilder$_buildFromBlock_closure1(this, this)), $get$$class$java$util$List());
    }
    
    public List<ASTNode> buildFromSpec(final Closure specification) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(specification, null)) {
            throw (Throwable)$getCallSiteArray[12].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: specification");
        }
        final Object properties = $getCallSiteArray[13].callConstructor($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler(), specification);
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[14].callGetProperty(properties), $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$ast$builder$AstBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public List<ASTNode> buildFromCode(final CompilePhase phase, final Closure block) {
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray()[15].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(phase, $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createGroovyObjectWrapper(block, $get$$class$groovy$lang$Closure())), $get$$class$java$util$List());
    }
    
    public List<ASTNode> buildFromCode(final Closure block) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[17].callGetProperty($get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createGroovyObjectWrapper(block, $get$$class$groovy$lang$Closure())), $get$$class$java$util$List());
    }
    
    public List<ASTNode> buildFromString(final CompilePhase phase, final String source) {
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray()[18].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(phase, $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(source, $get$$class$java$lang$String())), $get$$class$java$util$List());
    }
    
    public List<ASTNode> buildFromString(final String source) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[19].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[20].callGetProperty($get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(source, $get$$class$java$lang$String())), $get$$class$java$util$List());
    }
    
    private List<ASTNode> buildFromBlock(final CompilePhase phase, final String source) {
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray()[21].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(phase, $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(source, $get$$class$java$lang$String())), $get$$class$java$util$List());
    }
    
    private List<ASTNode> buildFromBlock(final String source) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[22].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[23].callGetProperty($get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), $get$$class$org$codehaus$groovy$control$CompilePhase()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(source, $get$$class$java$lang$String())), $get$$class$java$util$List());
    }
    
    static {
        AstBuilder.__timeStamp__239_neverHappen1292524203695 = 0L;
        AstBuilder.__timeStamp = 1292524203695L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilder.$callSiteArray == null || ($createCallSiteArray = AstBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstBuilder() {
        Class $class$org$codehaus$groovy$ast$builder$AstBuilder;
        if (($class$org$codehaus$groovy$ast$builder$AstBuilder = AstBuilder.$class$org$codehaus$groovy$ast$builder$AstBuilder) == null) {
            $class$org$codehaus$groovy$ast$builder$AstBuilder = (AstBuilder.$class$org$codehaus$groovy$ast$builder$AstBuilder = class$("org.codehaus.groovy.ast.builder.AstBuilder"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler() {
        Class $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
        if (($class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = AstBuilder.$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler) == null) {
            $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = (AstBuilder.$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = class$("org.codehaus.groovy.ast.builder.AstSpecificationCompiler"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = AstBuilder.$class$java$lang$System) == null) {
            $class$java$lang$System = (AstBuilder.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalStateException() {
        Class $class$java$lang$IllegalStateException;
        if (($class$java$lang$IllegalStateException = AstBuilder.$class$java$lang$IllegalStateException) == null) {
            $class$java$lang$IllegalStateException = (AstBuilder.$class$java$lang$IllegalStateException = class$("java.lang.IllegalStateException"));
        }
        return $class$java$lang$IllegalStateException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = AstBuilder.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (AstBuilder.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilePhase() {
        Class $class$org$codehaus$groovy$control$CompilePhase;
        if (($class$org$codehaus$groovy$control$CompilePhase = AstBuilder.$class$org$codehaus$groovy$control$CompilePhase) == null) {
            $class$org$codehaus$groovy$control$CompilePhase = (AstBuilder.$class$org$codehaus$groovy$control$CompilePhase = class$("org.codehaus.groovy.control.CompilePhase"));
        }
        return $class$org$codehaus$groovy$control$CompilePhase;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstStringCompiler() {
        Class $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
        if (($class$org$codehaus$groovy$ast$builder$AstStringCompiler = AstBuilder.$class$org$codehaus$groovy$ast$builder$AstStringCompiler) == null) {
            $class$org$codehaus$groovy$ast$builder$AstStringCompiler = (AstBuilder.$class$org$codehaus$groovy$ast$builder$AstStringCompiler = class$("org.codehaus.groovy.ast.builder.AstStringCompiler"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstStringCompiler;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstBuilder.$class$java$util$List) == null) {
            $class$java$util$List = (AstBuilder.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstBuilder.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstBuilder.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstBuilder.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstBuilder.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
