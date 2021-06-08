// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

public class LinkArgument
{
    private String href;
    private String packages;
    
    public LinkArgument() {
        this.href = "";
        this.packages = "";
    }
    
    public String getPackages() {
        return this.packages;
    }
    
    public void setPackages(final String packages) {
        this.packages = packages;
    }
    
    public String getHref() {
        return this.href;
    }
    
    public void setHref(final String hr) {
        this.href = hr;
    }
}
