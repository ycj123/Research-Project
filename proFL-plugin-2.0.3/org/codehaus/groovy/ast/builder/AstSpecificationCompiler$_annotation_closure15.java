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
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_annotation_closure15 extends Closure implements GeneratedClosure
{
    private Reference<Object> target;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$AnnotationNode;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_annotation_closure15(final Object _outerInstance, final Object _thisObject, final Reference<Object> target) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.target = target;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object node = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$AnnotationNode(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.target.get())));
        $getCallSiteArray[2].callSafe($getCallSiteArray[3].callGroovyObjectGetProperty(this), new AstSpecificationCompiler$_annotation_closure15_closure31(this, this.getThisObject(), (Reference<Object>)node));
        return ((Reference<Object>)node).get();
    }
    
    public Class getTarget() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.target.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_annotation_closure15(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_annotation_closure15.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_annotation_closure15.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_annotation_closure15.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_annotation_closure15.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_annotation_closure15.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$AnnotationNode() {
        Class $class$org$codehaus$groovy$ast$AnnotationNode;
        if (($class$org$codehaus$groovy$ast$AnnotationNode = AstSpecificationCompiler$_annotation_closure15.$class$org$codehaus$groovy$ast$AnnotationNode) == null) {
            $class$org$codehaus$groovy$ast$AnnotationNode = (AstSpecificationCompiler$_annotation_closure15.$class$org$codehaus$groovy$ast$AnnotationNode = class$("org.codehaus.groovy.ast.AnnotationNode"));
        }
        return $class$org$codehaus$groovy$ast$AnnotationNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_annotation_closure15.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_annotation_closure15.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_annotation_closure15.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_annotation_closure15.$class$java$lang$Class = class$("java.lang.Class"));
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
