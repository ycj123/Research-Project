// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.fml.model;

import java.io.Serializable;

public class Faq implements Serializable
{
    private String id;
    private String question;
    private String answer;
    private String modelEncoding;
    
    public Faq() {
        this.modelEncoding = "UTF-8";
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Faq)) {
            return false;
        }
        final Faq that = (Faq)other;
        boolean result = true;
        result = (result && ((this.getId() != null) ? this.getId().equals(that.getId()) : (that.getId() == null)));
        result = (result && ((this.getQuestion() != null) ? this.getQuestion().equals(that.getQuestion()) : (that.getQuestion() == null)));
        result = (result && ((this.getAnswer() != null) ? this.getAnswer().equals(that.getAnswer()) : (that.getAnswer() == null)));
        return result;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.id != null) ? this.id.hashCode() : 0);
        result = 37 * result + ((this.question != null) ? this.question.hashCode() : 0);
        result = 37 * result + ((this.answer != null) ? this.answer.hashCode() : 0);
        return result;
    }
    
    public void setAnswer(final String answer) {
        this.answer = answer;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setQuestion(final String question) {
        this.question = question;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("id = '");
        buf.append(this.getId());
        buf.append("'");
        buf.append("\n");
        buf.append("question = '");
        buf.append(this.getQuestion());
        buf.append("'");
        buf.append("\n");
        buf.append("answer = '");
        buf.append(this.getAnswer());
        buf.append("'");
        return buf.toString();
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
