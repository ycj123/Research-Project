// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.codehaus.groovy.GroovyBugError;

public abstract class CSTNode
{
    public int getMeaning() {
        return this.getRoot(true).getMeaning();
    }
    
    public CSTNode setMeaning(final int meaning) {
        this.getRoot().setMeaning(meaning);
        return this;
    }
    
    public int getType() {
        return this.getRoot(true).getType();
    }
    
    public boolean canMean(final int type) {
        return Types.canMean(this.getMeaning(), type);
    }
    
    public boolean isA(final int type) {
        return Types.ofType(this.getMeaning(), type);
    }
    
    public boolean isOneOf(final int[] types) {
        final int meaning = this.getMeaning();
        for (int i = 0; i < types.length; ++i) {
            if (Types.ofType(meaning, types[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAllOf(final int[] types) {
        final int meaning = this.getMeaning();
        for (int i = 0; i < types.length; ++i) {
            if (!Types.ofType(meaning, types[i])) {
                return false;
            }
        }
        return true;
    }
    
    public int getMeaningAs(final int[] types) {
        for (int i = 0; i < types.length; ++i) {
            if (this.isA(types[i])) {
                return types[i];
            }
        }
        return 0;
    }
    
    boolean matches(final int type) {
        return this.isA(type);
    }
    
    boolean matches(final int type, final int child1) {
        return this.isA(type) && this.get(1, true).isA(child1);
    }
    
    boolean matches(final int type, final int child1, final int child2) {
        return this.matches(type, child1) && this.get(2, true).isA(child2);
    }
    
    boolean matches(final int type, final int child1, final int child2, final int child3) {
        return this.matches(type, child1, child2) && this.get(3, true).isA(child3);
    }
    
    boolean matches(final int type, final int child1, final int child2, final int child3, final int child4) {
        return this.matches(type, child1, child2, child3) && this.get(4, true).isA(child4);
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public abstract int size();
    
    public boolean hasChildren() {
        return this.children() > 0;
    }
    
    public int children() {
        final int size = this.size();
        if (size > 1) {
            return size - 1;
        }
        return 0;
    }
    
    public abstract CSTNode get(final int p0);
    
    public CSTNode get(final int index, final boolean safe) {
        CSTNode element = this.get(index);
        if (element == null && safe) {
            element = Token.NULL;
        }
        return element;
    }
    
    public abstract Token getRoot();
    
    public Token getRoot(final boolean safe) {
        Token root = this.getRoot();
        if (root == null && safe) {
            root = Token.NULL;
        }
        return root;
    }
    
    public String getRootText() {
        final Token root = this.getRoot(true);
        return root.getText();
    }
    
    public String getDescription() {
        return Types.getDescription(this.getMeaning());
    }
    
    public int getStartLine() {
        return this.getRoot(true).getStartLine();
    }
    
    public int getStartColumn() {
        return this.getRoot(true).getStartColumn();
    }
    
    public void markAsExpression() {
        throw new GroovyBugError("markAsExpression() not supported for this CSTNode type");
    }
    
    public boolean isAnExpression() {
        return this.isA(1910);
    }
    
    public CSTNode add(final CSTNode element) {
        throw new GroovyBugError("add() not supported for this CSTNode type");
    }
    
    public void addChildrenOf(final CSTNode of) {
        for (int i = 1; i < of.size(); ++i) {
            this.add(of.get(i));
        }
    }
    
    public CSTNode set(final int index, final CSTNode element) {
        throw new GroovyBugError("set() not supported for this CSTNode type");
    }
    
    public abstract Reduction asReduction();
    
    @Override
    public String toString() {
        final StringWriter string = new StringWriter();
        this.write(new PrintWriter(string));
        string.flush();
        return string.toString();
    }
    
    public void write(final PrintWriter writer) {
        this.write(writer, "");
    }
    
    protected void write(final PrintWriter writer, final String indent) {
        writer.print("(");
        if (!this.isEmpty()) {
            final Token root = this.getRoot(true);
            final int type = root.getType();
            final int meaning = root.getMeaning();
            writer.print(Types.getDescription(type));
            if (meaning != type) {
                writer.print(" as ");
                writer.print(Types.getDescription(meaning));
            }
            if (this.getStartLine() > -1) {
                writer.print(" at " + this.getStartLine() + ":" + this.getStartColumn());
            }
            String text = root.getText();
            final int length = text.length();
            if (length > 0) {
                writer.print(": ");
                if (length > 40) {
                    text = text.substring(0, 17) + "..." + text.substring(length - 17, length);
                }
                writer.print(" \"");
                writer.print(text);
                writer.print("\" ");
            }
            else if (this.children() > 0) {
                writer.print(": ");
            }
            final int count = this.size();
            if (count > 1) {
                writer.println("");
                final String indent2 = indent + "  ";
                final String indent3 = indent + "   ";
                for (int i = 1; i < count; ++i) {
                    writer.print(indent2);
                    writer.print(i);
                    writer.print(": ");
                    this.get(i, true).write(writer, indent3);
                }
                writer.print(indent);
            }
        }
        if (indent.length() > 0) {
            writer.println(")");
        }
        else {
            writer.print(")");
        }
    }
}
