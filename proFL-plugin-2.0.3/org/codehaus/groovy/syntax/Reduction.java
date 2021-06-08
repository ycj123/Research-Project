// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import org.codehaus.groovy.GroovyBugError;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Reduction extends CSTNode
{
    public static final Reduction EMPTY;
    private List elements;
    private boolean marked;
    
    public Reduction(final Token root) {
        this.elements = null;
        this.marked = false;
        this.elements = new ArrayList();
        this.set(0, root);
    }
    
    private Reduction() {
        this.elements = null;
        this.marked = false;
        this.elements = Collections.EMPTY_LIST;
    }
    
    public static Reduction newContainer() {
        return new Reduction(Token.NULL);
    }
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    @Override
    public int size() {
        return this.elements.size();
    }
    
    @Override
    public CSTNode get(final int index) {
        CSTNode element = null;
        if (index < this.size()) {
            element = this.elements.get(index);
        }
        return element;
    }
    
    @Override
    public Token getRoot() {
        if (this.size() > 0) {
            return this.elements.get(0);
        }
        return null;
    }
    
    @Override
    public void markAsExpression() {
        this.marked = true;
    }
    
    @Override
    public boolean isAnExpression() {
        return this.isA(1911) || this.marked;
    }
    
    @Override
    public CSTNode add(final CSTNode element) {
        return this.set(this.size(), element);
    }
    
    @Override
    public CSTNode set(final int index, final CSTNode element) {
        if (this.elements == null) {
            throw new GroovyBugError("attempt to set() on a EMPTY Reduction");
        }
        if (index == 0 && !(element instanceof Token)) {
            throw new GroovyBugError("attempt to set() a non-Token as root of a Reduction");
        }
        final int count = this.elements.size();
        if (index >= count) {
            for (int i = count; i <= index; ++i) {
                this.elements.add(null);
            }
        }
        this.elements.set(index, element);
        return element;
    }
    
    public CSTNode remove(final int index) {
        if (index < 1) {
            throw new GroovyBugError("attempt to remove() root node of Reduction");
        }
        return this.elements.remove(index);
    }
    
    @Override
    public Reduction asReduction() {
        return this;
    }
    
    static {
        EMPTY = new Reduction();
    }
}
