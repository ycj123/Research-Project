// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.analysis;

import org.pitest.reloc.asm.Type;
import org.pitest.classinfo.ClassName;
import java.io.Serializable;

public class FactoryMethod implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final ClassName declaringClassName;
    private final String name;
    private final String desc;
    private final boolean itf;
    
    protected FactoryMethod(final String className, final String name, final String desc, final boolean itf) {
        this.declaringClassName = ClassName.fromString(className);
        this.name = name;
        this.desc = desc;
        this.itf = itf;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescriptor() {
        return this.desc;
    }
    
    public Type getReturnType() {
        return Type.getReturnType(this.desc);
    }
    
    public ClassName getDeclaringClassName() {
        return this.declaringClassName;
    }
    
    @Override
    public String toString() {
        return String.format("%s.%s%s", this.declaringClassName.asJavaName(), this.name, this.desc);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.declaringClassName == null) ? 0 : this.declaringClassName.hashCode());
        result = 31 * result + ((this.desc == null) ? 0 : this.desc.hashCode());
        result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final FactoryMethod other = (FactoryMethod)obj;
        if (this.declaringClassName == null) {
            if (other.declaringClassName != null) {
                return false;
            }
        }
        else if (!this.declaringClassName.equals(other.declaringClassName)) {
            return false;
        }
        if (this.desc == null) {
            if (other.desc != null) {
                return false;
            }
        }
        else if (!this.desc.equals(other.desc)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    public boolean isOwnerAnInterface() {
        return this.itf;
    }
}
