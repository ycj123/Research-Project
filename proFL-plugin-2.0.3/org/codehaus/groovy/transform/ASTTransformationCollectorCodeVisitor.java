// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.lang.reflect.Method;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.messages.SimpleMessage;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class ASTTransformationCollectorCodeVisitor extends ClassCodeVisitorSupport
{
    private SourceUnit source;
    private ClassNode classNode;
    private GroovyClassLoader transformLoader;
    
    public ASTTransformationCollectorCodeVisitor(final SourceUnit source, final GroovyClassLoader transformLoader) {
        this.source = source;
        this.transformLoader = transformLoader;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClass(final ClassNode klassNode) {
        final ClassNode oldClass = this.classNode;
        super.visitClass(this.classNode = klassNode);
        this.classNode = oldClass;
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        super.visitAnnotations(node);
        for (final AnnotationNode annotation : node.getAnnotations()) {
            final Annotation transformClassAnnotation = getTransformClassAnnotation(annotation.getClassNode());
            if (transformClassAnnotation == null) {
                continue;
            }
            this.addTransformsToClassNode(annotation, transformClassAnnotation);
        }
    }
    
    private void addTransformsToClassNode(final AnnotationNode annotation, final Annotation transformClassAnnotation) {
        final String[] transformClassNames = this.getTransformClassNames(transformClassAnnotation);
        final Class[] transformClasses = this.getTransformClasses(transformClassAnnotation);
        if (transformClassNames.length == 0 && transformClasses.length == 0) {
            this.source.getErrorCollector().addError(new SimpleMessage("@GroovyASTTransformationClass in " + annotation.getClassNode().getName() + " does not specify any transform class names/classes", this.source));
        }
        if (transformClassNames.length > 0 && transformClasses.length > 0) {
            this.source.getErrorCollector().addError(new SimpleMessage("@GroovyASTTransformationClass in " + annotation.getClassNode().getName() + " should specify transforms only by class names or by classes and not by both", this.source));
        }
        for (final String transformClass : transformClassNames) {
            try {
                final Class klass = this.transformLoader.loadClass(transformClass, false, true, false);
                this.verifyClassAndAddTransform(annotation, klass);
            }
            catch (ClassNotFoundException e) {
                this.source.getErrorCollector().addErrorAndContinue(new SimpleMessage("Could not find class for Transformation Processor " + transformClass + " declared by " + annotation.getClassNode().getName(), this.source));
            }
        }
        for (final Class klass2 : transformClasses) {
            this.verifyClassAndAddTransform(annotation, klass2);
        }
    }
    
    private void verifyClassAndAddTransform(final AnnotationNode annotation, final Class klass) {
        if (ASTTransformation.class.isAssignableFrom(klass)) {
            this.classNode.addTransform(klass, annotation);
        }
        else {
            this.source.getErrorCollector().addError(new SimpleMessage("Not an ASTTransformation: " + klass.getName() + " declared by " + annotation.getClassNode().getName(), this.source));
        }
    }
    
    private static Annotation getTransformClassAnnotation(final ClassNode annotatedType) {
        if (!annotatedType.isResolved()) {
            return null;
        }
        for (final Annotation ann : annotatedType.getTypeClass().getAnnotations()) {
            if (ann.annotationType().getName().equals(GroovyASTTransformationClass.class.getName())) {
                return ann;
            }
        }
        return null;
    }
    
    private String[] getTransformClassNames(final Annotation transformClassAnnotation) {
        try {
            final Method valueMethod = transformClassAnnotation.getClass().getMethod("value", (Class<?>[])new Class[0]);
            return (String[])valueMethod.invoke(transformClassAnnotation, new Object[0]);
        }
        catch (Exception e) {
            this.source.addException(e);
            return new String[0];
        }
    }
    
    private Class[] getTransformClasses(final Annotation transformClassAnnotation) {
        try {
            final Method classesMethod = transformClassAnnotation.getClass().getMethod("classes", (Class<?>[])new Class[0]);
            return (Class[])classesMethod.invoke(transformClassAnnotation, new Object[0]);
        }
        catch (Exception e) {
            this.source.addException(e);
            return new Class[0];
        }
    }
}
