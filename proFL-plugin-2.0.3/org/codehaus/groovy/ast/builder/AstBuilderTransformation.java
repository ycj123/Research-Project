// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.transform.ASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class AstBuilderTransformation implements ASTTransformation, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203722;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstBuilderTransformation;
    
    public AstBuilderTransformation() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void visit(final ASTNode[] nodes, final SourceUnit sourceUnit) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object transformer = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].call(sourceUnit)), $getCallSiteArray[3].callGetProperty($getCallSiteArray[4].call(sourceUnit)), $getCallSiteArray[5].callGetProperty(sourceUnit), sourceUnit));
        $getCallSiteArray[6].callSafe(nodes, new AstBuilderTransformation$_visit_closure1(this, this, (Reference<Object>)transformer));
        $getCallSiteArray[7].callSafe($getCallSiteArray[8].call(sourceUnit), ((Reference<Object>)transformer).get());
        $getCallSiteArray[9].callSafe($getCallSiteArray[10].callSafe($getCallSiteArray[11].call(sourceUnit)), ((Reference<Object>)transformer).get());
        $getCallSiteArray[12].callSafe($getCallSiteArray[13].callSafe($getCallSiteArray[14].call(sourceUnit)), new AstBuilderTransformation$_visit_closure2(this, this, (Reference<Object>)transformer));
        $getCallSiteArray[15].callSafe($getCallSiteArray[16].callSafe($getCallSiteArray[17].call(sourceUnit)), new AstBuilderTransformation$_visit_closure3(this, this, (Reference<Object>)transformer));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstBuilderTransformation.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstBuilderTransformation.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AstBuilderTransformation.__timeStamp__239_neverHappen1292524203722 = 0L;
        AstBuilderTransformation.__timeStamp = 1292524203722L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderTransformation.$callSiteArray == null || ($createCallSiteArray = AstBuilderTransformation.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderTransformation.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstBuilderTransformation.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstBuilderTransformation.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap() {
        Class $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
        if (($class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = AstBuilderTransformation.$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap) == null) {
            $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = (AstBuilderTransformation.$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = class$("org.codehaus.groovy.ast.builder.AstBuilderInvocationTrap"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation() {
        Class $class$org$codehaus$groovy$ast$builder$AstBuilderTransformation;
        if (($class$org$codehaus$groovy$ast$builder$AstBuilderTransformation = AstBuilderTransformation.$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation) == null) {
            $class$org$codehaus$groovy$ast$builder$AstBuilderTransformation = (AstBuilderTransformation.$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation = class$("org.codehaus.groovy.ast.builder.AstBuilderTransformation"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstBuilderTransformation;
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
