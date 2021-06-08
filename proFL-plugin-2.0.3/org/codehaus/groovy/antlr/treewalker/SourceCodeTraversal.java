// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import java.util.Collections;
import java.util.ArrayList;
import org.codehaus.groovy.antlr.GroovySourceAST;

public class SourceCodeTraversal extends TraversalHelper
{
    public SourceCodeTraversal(final Visitor visitor) {
        super(visitor);
    }
    
    public void setUp(final GroovySourceAST t) {
        super.setUp(t);
        this.unvisitedNodes = new ArrayList<GroovySourceAST>();
        this.traverse(t);
        Collections.sort(this.unvisitedNodes);
    }
    
    private void traverse(final GroovySourceAST t) {
        if (t == null) {
            return;
        }
        if (this.unvisitedNodes != null) {
            this.unvisitedNodes.add(t);
        }
        final GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
        if (child != null) {
            this.traverse(child);
        }
        final GroovySourceAST sibling = (GroovySourceAST)t.getNextSibling();
        if (sibling != null) {
            this.traverse(sibling);
        }
    }
    
    @Override
    protected void accept(final GroovySourceAST currentNode) {
        if (currentNode != null && this.unvisitedNodes != null && this.unvisitedNodes.size() > 0) {
            final GroovySourceAST t = currentNode;
            if (!this.unvisitedNodes.contains(currentNode)) {
                return;
            }
            this.push(t);
            switch (t.getType()) {
                case 93: {
                    this.accept_FirstChild_v_SecondChild_v_ThirdChild_v(t);
                    break;
                }
                case 31:
                case 153: {
                    this.accept_FirstChild_v_SecondChildsChildren_v(t);
                    break;
                }
                case 65: {
                    this.accept_v_FirstChild_2ndv_SecondChild_v___LastChild_v(t);
                    break;
                }
                case 19:
                case 32:
                case 47:
                case 69:
                case 71:
                case 72:
                case 74:
                case 76: {
                    this.accept_v_FirstChild_v_SecondChild_v___LastChild_v(t);
                    break;
                }
                case 46: {
                    this.accept_v_FirstChild_SecondChild_v_ThirdChild_v(t);
                    break;
                }
                case 23: {
                    this.accept_SecondChild_v_ThirdChild_v(t);
                    break;
                }
                case 6:
                case 7:
                case 8:
                case 9:
                case 20:
                case 27:
                case 28:
                case 59:
                case 61: {
                    this.accept_v_AllChildren_v(t);
                    break;
                }
                case 66:
                case 120:
                case 157:
                case 158:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 167:
                case 168:
                case 174:
                case 175:
                case 176:
                case 177:
                case 180: {
                    if (t.childAt(1) != null) {
                        this.accept_FirstChild_v_RestOfTheChildren(t);
                        break;
                    }
                    this.accept_v_FirstChild_v_RestOfTheChildren(t);
                    break;
                }
                case 67: {
                    this.accept_FirstSecondAndThirdChild_v_v_ForthChild(t);
                    break;
                }
                case 13:
                case 14:
                case 15:
                case 21:
                case 45:
                case 53:
                case 60:
                case 63:
                case 86:
                case 87:
                case 97:
                case 98:
                case 99:
                case 109:
                case 110:
                case 121:
                case 137:
                case 143:
                case 144:
                case 149:
                case 150:
                case 151:
                case 170:
                case 171:
                case 172:
                case 173:
                case 181:
                case 182:
                case 183:
                case 184:
                case 185:
                case 187:
                case 188:
                case 190: {
                    this.accept_FirstChild_v_RestOfTheChildren(t);
                    break;
                }
                case 26:
                case 44: {
                    if (t.getNumberOfChildren() == 2 && t.childAt(1) != null && t.childAt(1).getType() == 49) {
                        this.accept_FirstChild_v_SecondChild(t);
                        break;
                    }
                    final GroovySourceAST lastChild = t.childAt(t.getNumberOfChildren() - 1);
                    if (lastChild != null && lastChild.getType() == 49) {
                        this.accept_FirstChild_v_RestOfTheChildren_v_LastChild(t);
                    }
                    else {
                        this.accept_FirstChild_v_RestOfTheChildren_v(t);
                    }
                    break;
                }
                case 22:
                case 134: {
                    this.accept_v_FirstChildsFirstChild_v_RestOfTheChildren(t);
                    break;
                }
                case 132: {
                    this.accept_v_FirstChildsFirstChild_v_Child2_Child3_v_Child4_v___v_LastChild(t);
                    break;
                }
                case 49: {
                    if (t.childAt(0) != null && t.childAt(0).getType() == 50) {
                        this.accept_v_AllChildren_v(t);
                        break;
                    }
                    this.accept_v_FirstChild_v_RestOfTheChildren_v(t);
                    break;
                }
                case 58:
                case 135:
                case 136:
                case 154: {
                    this.accept_v_FirstChild_v_RestOfTheChildren_v(t);
                    break;
                }
                case 5:
                case 64:
                case 117:
                case 142:
                case 146:
                case 148: {
                    this.accept_v_FirstChild_v_RestOfTheChildren(t);
                    break;
                }
                case 73: {
                    this.accept_v_Siblings_v(t);
                    break;
                }
                default: {
                    this.accept_v_FirstChild_v(t);
                    break;
                }
            }
            this.pop();
        }
    }
}
