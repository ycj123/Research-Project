// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.Iterator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Collection;
import java.util.List;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.ArrayList;

public class Sequence extends ArrayList implements GroovyObject
{
    private MetaClass metaClass;
    private Class type;
    private int hashCode;
    
    public Sequence() {
        this((Class)null);
    }
    
    public Sequence(final Class type) {
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
        this.type = type;
    }
    
    public Sequence(final Class type, final List content) {
        super(content.size());
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
        this.type = type;
        this.addAll(content);
    }
    
    public void set(final Collection collection) {
        this.checkCollectionType(collection);
        this.clear();
        this.addAll(collection);
    }
    
    @Override
    public boolean equals(final Object that) {
        return that instanceof Sequence && this.equals((Sequence)that);
    }
    
    public boolean equals(final Sequence that) {
        if (this.size() == that.size()) {
            for (int i = 0; i < this.size(); ++i) {
                if (!DefaultTypeTransformation.compareEqual(this.get(i), that.get(i))) {
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
            for (int i = 0; i < this.size(); ++i) {
                final Object value = this.get(i);
                final int hash = (value != null) ? value.hashCode() : 47806;
                this.hashCode ^= hash;
            }
            if (this.hashCode == 0) {
                this.hashCode = 47806;
            }
        }
        return this.hashCode;
    }
    
    public int minimumSize() {
        return 0;
    }
    
    public Class type() {
        return this.type;
    }
    
    @Override
    public void add(final int index, final Object element) {
        this.checkType(element);
        this.hashCode = 0;
        super.add(index, element);
    }
    
    @Override
    public boolean add(final Object element) {
        this.checkType(element);
        this.hashCode = 0;
        return super.add(element);
    }
    
    @Override
    public boolean addAll(final Collection c) {
        this.checkCollectionType(c);
        this.hashCode = 0;
        return super.addAll(c);
    }
    
    @Override
    public boolean addAll(final int index, final Collection c) {
        this.checkCollectionType(c);
        this.hashCode = 0;
        return super.addAll(index, c);
    }
    
    @Override
    public void clear() {
        this.hashCode = 0;
        super.clear();
    }
    
    @Override
    public Object remove(final int index) {
        this.hashCode = 0;
        return super.remove(index);
    }
    
    @Override
    protected void removeRange(final int fromIndex, final int toIndex) {
        this.hashCode = 0;
        super.removeRange(fromIndex, toIndex);
    }
    
    @Override
    public Object set(final int index, final Object element) {
        this.hashCode = 0;
        return super.set(index, element);
    }
    
    public Object invokeMethod(final String name, final Object args) {
        try {
            return this.getMetaClass().invokeMethod(this, name, args);
        }
        catch (MissingMethodException e) {
            final List answer = new ArrayList(this.size());
            for (final Object element : this) {
                final Object value = InvokerHelper.invokeMethod(element, name, args);
                answer.add(value);
            }
            return answer;
        }
    }
    
    public Object getProperty(final String property) {
        return this.getMetaClass().getProperty(this, property);
    }
    
    public void setProperty(final String property, final Object newValue) {
        this.getMetaClass().setProperty(this, property, newValue);
    }
    
    public MetaClass getMetaClass() {
        return this.metaClass;
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
    
    protected void checkCollectionType(final Collection c) {
        if (this.type != null) {
            for (final Object element : c) {
                this.checkType(element);
            }
        }
    }
    
    protected void checkType(final Object object) {
        if (object == null) {
            throw new NullPointerException("Sequences cannot contain null, use a List instead");
        }
        if (this.type != null && !this.type.isInstance(object)) {
            throw new IllegalArgumentException("Invalid type of argument for sequence of type: " + this.type.getName() + " cannot add object: " + object);
        }
    }
}
