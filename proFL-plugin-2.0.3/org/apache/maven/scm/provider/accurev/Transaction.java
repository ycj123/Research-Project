// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import java.util.HashSet;
import java.util.Date;
import java.util.Collection;

public class Transaction
{
    private Collection<Version> versions;
    private long id;
    private Date when;
    private String tranType;
    private String author;
    private String comment;
    
    public Transaction(final Long id, final Date when, final String tranType, final String user) {
        this.versions = new HashSet<Version>();
        this.id = id;
        this.tranType = tranType;
        this.when = when;
        this.author = user;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getTranType() {
        return this.tranType;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(final String comment) {
        this.comment = comment;
    }
    
    public long getTranId() {
        return this.id;
    }
    
    private Transaction getOuterTransaction() {
        return this;
    }
    
    public Collection<Version> getVersions() {
        return this.versions;
    }
    
    public Date getWhen() {
        return this.when;
    }
    
    public String getType() {
        return this.tranType;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void addVersion(final Long id, final String name, final String virtualSpec, final String realSpec, final String ancestor) {
        final Version v = new Version(id, name, virtualSpec, realSpec, ancestor);
        this.versions.add(v);
    }
    
    @Override
    public String toString() {
        return String.format("Transaction: %d, %s at %tc by %s -'%s'", this.getId(), this.getTranType(), this.getWhen(), this.getAuthor(), this.getComment());
    }
    
    public class Version
    {
        private String realSpec;
        private String virtualSpec;
        private String ancestorSpec;
        private Long elementId;
        private String elementName;
        
        private Version(final Long id, final String elementName, final String virtualSpec, final String realSpec, final String ancestor) {
            this.elementId = id;
            this.virtualSpec = virtualSpec;
            this.realSpec = realSpec;
            this.ancestorSpec = ancestor;
            this.elementName = elementName;
        }
        
        public String getVirtualSpec() {
            return this.virtualSpec;
        }
        
        public void setVirtualSpec(final String virtualSpec) {
            this.virtualSpec = virtualSpec;
        }
        
        public String getAncestorSpec() {
            return this.ancestorSpec;
        }
        
        public void setAncestorSpec(final String ancestorSpec) {
            this.ancestorSpec = ancestorSpec;
        }
        
        public void setRealSpec(final String realSpec) {
            this.realSpec = realSpec;
        }
        
        public void setElementId(final Long elementId) {
            this.elementId = elementId;
        }
        
        public String getRealSpec() {
            return this.realSpec;
        }
        
        public Long getElementId() {
            return this.elementId;
        }
        
        public Transaction getTransaction() {
            return Transaction.this.getOuterTransaction();
        }
        
        public String getElementName() {
            return this.elementName;
        }
        
        @Override
        public String toString() {
            return String.format("Version: %s (%d) %s (%s) anc=%s", this.elementName, this.elementId, this.virtualSpec, this.realSpec, this.ancestorSpec);
        }
    }
}
