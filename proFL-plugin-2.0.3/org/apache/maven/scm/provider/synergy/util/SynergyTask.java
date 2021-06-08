// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.util;

import java.util.Date;

public class SynergyTask
{
    private int number;
    private String username;
    private Date modifiedTime;
    private String comment;
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(final String comment) {
        this.comment = comment;
    }
    
    public Date getModifiedTime() {
        return this.modifiedTime;
    }
    
    public void setModifiedTime(final Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(final int number) {
        this.number = number;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
}
