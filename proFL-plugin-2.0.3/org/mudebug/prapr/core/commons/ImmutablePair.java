// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.commons;

public final class ImmutablePair<F, S>
{
    private final F first;
    private final S second;
    
    public ImmutablePair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }
    
    public F getFirst() {
        return this.first;
    }
    
    public S getSecond() {
        return this.second;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.first == null) ? 0 : this.first.hashCode());
        result = 31 * result + ((this.second == null) ? 0 : this.second.hashCode());
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
        final ImmutablePair<?, ?> other = (ImmutablePair<?, ?>)obj;
        if (this.first == null) {
            if (other.first != null) {
                return false;
            }
        }
        else if (!this.first.equals(other.first)) {
            return false;
        }
        if (this.second == null) {
            if (other.second != null) {
                return false;
            }
        }
        else if (!this.second.equals(other.second)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}
