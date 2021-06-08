// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public final class QuestionableRequest extends Request
{
    private String questionFile;
    
    public QuestionableRequest(final String questionFile) {
        this.questionFile = questionFile;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.questionFile == null) {
            throw new UnconfiguredRequestException("Questionable request has not been configured");
        }
        return "Questionable " + this.questionFile + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
