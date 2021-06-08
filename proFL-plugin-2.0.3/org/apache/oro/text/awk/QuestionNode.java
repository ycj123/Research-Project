// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

final class QuestionNode extends OrNode
{
    static final SyntaxNode _epsilon;
    
    QuestionNode(final SyntaxNode syntaxNode) {
        super(syntaxNode, QuestionNode._epsilon);
    }
    
    boolean _nullable() {
        return true;
    }
    
    SyntaxNode _clone(final int[] array) {
        return new QuestionNode(this._left._clone(array));
    }
    
    static {
        _epsilon = new EpsilonNode();
    }
}
