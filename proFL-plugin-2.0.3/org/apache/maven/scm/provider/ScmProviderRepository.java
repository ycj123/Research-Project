// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider;

public abstract class ScmProviderRepository
{
    private String user;
    private String password;
    private boolean persistCheckout;
    private boolean pushChanges;
    
    public ScmProviderRepository() {
        this.persistCheckout = false;
        this.pushChanges = true;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public void setUser(final String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public boolean isPushChanges() {
        return this.pushChanges;
    }
    
    public void setPushChanges(final boolean pushChanges) {
        this.pushChanges = pushChanges;
    }
    
    public boolean isPersistCheckout() {
        final String persist = System.getProperty("maven.scm.persistcheckout");
        if (persist != null) {
            return Boolean.valueOf(persist);
        }
        return this.persistCheckout;
    }
    
    public void setPersistCheckout(final boolean persistCheckout) {
        this.persistCheckout = persistCheckout;
    }
    
    public ScmProviderRepository getParent() {
        throw new UnsupportedOperationException();
    }
    
    public String getRelativePath(final ScmProviderRepository ancestor) {
        throw new UnsupportedOperationException();
    }
}
