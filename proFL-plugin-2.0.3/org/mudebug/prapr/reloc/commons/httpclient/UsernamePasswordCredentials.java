// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

public class UsernamePasswordCredentials implements Credentials
{
    private String userName;
    private String password;
    
    public UsernamePasswordCredentials() {
    }
    
    public UsernamePasswordCredentials(final String usernamePassword) {
        final int atColon = usernamePassword.indexOf(58);
        if (atColon >= 0) {
            this.userName = usernamePassword.substring(0, atColon);
            this.password = usernamePassword.substring(atColon + 1);
        }
        else {
            this.userName = usernamePassword;
        }
    }
    
    public UsernamePasswordCredentials(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String toString() {
        final StringBuffer result = new StringBuffer();
        result.append((this.userName == null) ? "null" : this.userName);
        result.append(":");
        result.append((this.password == null) ? "null" : this.password);
        return result.toString();
    }
}
