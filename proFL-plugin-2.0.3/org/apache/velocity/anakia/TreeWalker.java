// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.jdom.Element;

public class TreeWalker
{
    public NodeList allElements(final Element e) {
        final ArrayList theElements = new ArrayList();
        this.treeWalk(e, theElements);
        return new NodeList(theElements, false);
    }
    
    private final void treeWalk(final Element e, final Collection theElements) {
        for (final Element child : e.getChildren()) {
            theElements.add(child);
            this.treeWalk(child, theElements);
        }
    }
}
