// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

public class PackageNode extends AnnotatedNode
{
    private String name;
    
    public PackageNode(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getText() {
        return "package " + this.name;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
    }
}
