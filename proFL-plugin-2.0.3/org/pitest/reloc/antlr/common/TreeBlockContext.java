// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common;

class TreeBlockContext extends BlockContext
{
    protected boolean nextElementIsRoot;
    
    TreeBlockContext() {
        this.nextElementIsRoot = true;
    }
    
    public void addAlternativeElement(final AlternativeElement alternativeElement) {
        final TreeElement treeElement = (TreeElement)this.block;
        if (this.nextElementIsRoot) {
            treeElement.root = (GrammarAtom)alternativeElement;
            this.nextElementIsRoot = false;
        }
        else {
            super.addAlternativeElement(alternativeElement);
        }
    }
}
