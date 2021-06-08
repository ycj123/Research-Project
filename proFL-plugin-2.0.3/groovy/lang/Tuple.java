// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.AbstractList;

public class Tuple extends AbstractList
{
    private Object[] contents;
    private int hashCode;
    
    public Tuple(final Object[] contents) {
        this.contents = contents;
    }
    
    @Override
    public Object get(final int index) {
        return this.contents[index];
    }
    
    @Override
    public int size() {
        return this.contents.length;
    }
    
    @Override
    public boolean equals(final Object that) {
        return that instanceof Tuple && this.equals((Tuple)that);
    }
    
    public boolean equals(final Tuple that) {
        if (this.contents.length == that.contents.length) {
            for (int i = 0; i < this.contents.length; ++i) {
                if (!DefaultTypeTransformation.compareEqual(this.contents[i], that.contents[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            for (int i = 0; i < this.contents.length; ++i) {
                final Object value = this.contents[i];
                final int hash = (value != null) ? value.hashCode() : 47806;
                this.hashCode ^= hash;
            }
            if (this.hashCode == 0) {
                this.hashCode = 47806;
            }
        }
        return this.hashCode;
    }
    
    @Override
    public List subList(final int fromIndex, final int toIndex) {
        final int size = toIndex - fromIndex;
        final Object[] newContent = new Object[size];
        System.arraycopy(this.contents, fromIndex, newContent, 0, size);
        return new Tuple(newContent);
    }
}
