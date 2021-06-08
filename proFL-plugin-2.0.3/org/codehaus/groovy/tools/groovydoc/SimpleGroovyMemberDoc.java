// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyMemberDoc;

public class SimpleGroovyMemberDoc extends SimpleGroovyAbstractableElementDoc implements GroovyMemberDoc
{
    protected GroovyClassDoc belongsToClass;
    
    public SimpleGroovyMemberDoc(final String name, final GroovyClassDoc belongsToClass) {
        super(name);
        this.belongsToClass = belongsToClass;
    }
    
    public boolean isSynthetic() {
        return false;
    }
    
    @Override
    public String firstSentenceCommentText() {
        if (super.firstSentenceCommentText() == null) {
            final SimpleGroovyClassDoc classDoc = (SimpleGroovyClassDoc)this.belongsToClass;
            this.setFirstSentenceCommentText(classDoc.replaceTags(SimpleGroovyDoc.calculateFirstSentence(this.getRawCommentText())));
        }
        return super.firstSentenceCommentText();
    }
    
    @Override
    public String commentText() {
        if (super.commentText() == null) {
            final SimpleGroovyClassDoc classDoc = (SimpleGroovyClassDoc)this.belongsToClass;
            this.setCommentText(classDoc.replaceTags(this.getRawCommentText()));
        }
        return super.commentText();
    }
}
