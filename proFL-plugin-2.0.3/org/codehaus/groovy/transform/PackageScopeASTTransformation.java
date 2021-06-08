// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.lang.PackageScope;
import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.PropertyNode;
import java.util.ArrayList;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class PackageScopeASTTransformation implements ASTTransformation, Opcodes
{
    private static final Class MY_CLASS;
    private static final ClassNode MY_TYPE;
    private static final String MY_TYPE_NAME;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: wrong types: $node.class / $parent.class");
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!PackageScopeASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        if (parent instanceof ClassNode) {
            this.visitClassNode((ClassNode)parent);
        }
        else if (parent instanceof FieldNode) {
            this.visitFieldNode((FieldNode)parent);
        }
    }
    
    private void visitClassNode(final ClassNode cNode) {
        final String cName = cNode.getName();
        if (cNode.isInterface()) {
            throw new RuntimeException("Error processing interface '" + cName + "'. " + PackageScopeASTTransformation.MY_TYPE_NAME + " not allowed for interfaces.");
        }
        final List<PropertyNode> pList = cNode.getProperties();
        final List<PropertyNode> foundProps = new ArrayList<PropertyNode>();
        final List<String> foundNames = new ArrayList<String>();
        for (final PropertyNode pNode : pList) {
            foundProps.add(pNode);
            foundNames.add(pNode.getName());
        }
        for (final PropertyNode pNode : foundProps) {
            pList.remove(pNode);
        }
        final List<FieldNode> fList = cNode.getFields();
        for (final FieldNode fNode : fList) {
            if (foundNames.contains(fNode.getName())) {
                this.revertVisibility(fNode);
            }
        }
    }
    
    private void visitFieldNode(final FieldNode fNode) {
        final ClassNode cNode = fNode.getDeclaringClass();
        final List<PropertyNode> pList = cNode.getProperties();
        PropertyNode foundProp = null;
        for (final PropertyNode pNode : pList) {
            if (pNode.getName().equals(fNode.getName())) {
                foundProp = pNode;
                break;
            }
        }
        if (foundProp != null) {
            this.revertVisibility(fNode);
            pList.remove(foundProp);
        }
    }
    
    private void revertVisibility(final FieldNode fNode) {
        fNode.setModifiers(fNode.getModifiers() & 0xFFFFFFFD);
    }
    
    static {
        MY_CLASS = PackageScope.class;
        MY_TYPE = new ClassNode(PackageScopeASTTransformation.MY_CLASS);
        MY_TYPE_NAME = "@" + PackageScopeASTTransformation.MY_TYPE.getNameWithoutPackage();
    }
}
