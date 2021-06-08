// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Collections;
import java.beans.PropertyChangeListener;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Collection;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import groovy.lang.Closure;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ObservableList implements List
{
    private List delegate;
    private PropertyChangeSupport pcs;
    private Closure test;
    
    public ObservableList() {
        this(new ArrayList(), null);
    }
    
    public ObservableList(final List delegate) {
        this(delegate, null);
    }
    
    public ObservableList(final Closure test) {
        this(new ArrayList(), test);
    }
    
    public ObservableList(final List delegate, final Closure test) {
        this.delegate = delegate;
        this.test = test;
        this.pcs = new PropertyChangeSupport(this);
    }
    
    public void add(final int index, final Object element) {
        this.delegate.add(index, element);
        if (this.test != null) {
            final Object result = this.test.call(element);
            if (result != null && result instanceof Boolean && (boolean)result) {
                this.pcs.firePropertyChange(new ElementAddedEvent(this, element, index));
            }
        }
        else {
            this.pcs.firePropertyChange(new ElementAddedEvent(this, element, index));
        }
    }
    
    public boolean add(final Object o) {
        final boolean success = this.delegate.add(o);
        if (success) {
            if (this.test != null) {
                final Object result = this.test.call(o);
                if (result != null && result instanceof Boolean && (boolean)result) {
                    this.pcs.firePropertyChange(new ElementAddedEvent(this, o, this.size() - 1));
                }
            }
            else {
                this.pcs.firePropertyChange(new ElementAddedEvent(this, o, this.size() - 1));
            }
        }
        return success;
    }
    
    public boolean addAll(final Collection c) {
        int index = this.size() - 1;
        index = ((index < 0) ? 0 : index);
        final boolean success = this.delegate.addAll(c);
        if (success && c != null) {
            final List values = new ArrayList();
            for (final Object element : c) {
                if (this.test != null) {
                    final Object result = this.test.call(element);
                    if (result == null || !(result instanceof Boolean) || !(boolean)result) {
                        continue;
                    }
                    values.add(element);
                }
                else {
                    values.add(element);
                }
            }
            if (values.size() > 0) {
                this.pcs.firePropertyChange(new MultiElementAddedEvent(this, index, values));
            }
        }
        return success;
    }
    
    public boolean addAll(final int index, final Collection c) {
        final boolean success = this.delegate.addAll(index, c);
        if (success && c != null) {
            final List values = new ArrayList();
            for (final Object element : c) {
                if (this.test != null) {
                    final Object result = this.test.call(element);
                    if (result == null || !(result instanceof Boolean) || !(boolean)result) {
                        continue;
                    }
                    values.add(element);
                }
                else {
                    values.add(element);
                }
            }
            if (values.size() > 0) {
                this.pcs.firePropertyChange(new MultiElementAddedEvent(this, index, values));
            }
        }
        return success;
    }
    
    public void clear() {
        final List values = new ArrayList();
        values.addAll(this.delegate);
        this.delegate.clear();
        if (!values.isEmpty()) {
            this.pcs.firePropertyChange(new ElementClearedEvent(this, values));
        }
    }
    
    public boolean contains(final Object o) {
        return this.delegate.contains(o);
    }
    
    public boolean containsAll(final Collection c) {
        return this.delegate.containsAll(c);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.delegate.equals(o);
    }
    
    public Object get(final int index) {
        return this.delegate.get(index);
    }
    
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }
    
    public int indexOf(final Object o) {
        return this.delegate.indexOf(o);
    }
    
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }
    
    public Iterator iterator() {
        return new ObservableIterator(this.delegate.iterator());
    }
    
    public int lastIndexOf(final Object o) {
        return this.delegate.lastIndexOf(o);
    }
    
    public ListIterator listIterator() {
        return new ObservableListIterator(this.delegate.listIterator(), 0);
    }
    
    public ListIterator listIterator(final int index) {
        return new ObservableListIterator(this.delegate.listIterator(index), index);
    }
    
    public Object remove(final int index) {
        final Object element = this.delegate.remove(index);
        this.pcs.firePropertyChange(new ElementRemovedEvent(this, element, index));
        return element;
    }
    
    public boolean remove(final Object o) {
        final int index = this.delegate.indexOf(o);
        final boolean success = this.delegate.remove(o);
        if (success) {
            this.pcs.firePropertyChange(new ElementRemovedEvent(this, o, index));
        }
        return success;
    }
    
    public boolean removeAll(final Collection c) {
        if (c == null) {
            return false;
        }
        final List values = new ArrayList();
        if (c != null) {
            for (final Object element : c) {
                if (this.delegate.contains(element)) {
                    values.add(element);
                }
            }
        }
        final boolean success = this.delegate.removeAll(c);
        if (success && !values.isEmpty()) {
            this.pcs.firePropertyChange(new MultiElementRemovedEvent(this, values));
        }
        return success;
    }
    
    public boolean retainAll(final Collection c) {
        if (c == null) {
            return false;
        }
        final List values = new ArrayList();
        if (c != null) {
            for (final Object element : this.delegate) {
                if (!c.contains(element)) {
                    values.add(element);
                }
            }
        }
        final boolean success = this.delegate.retainAll(c);
        if (success && !values.isEmpty()) {
            this.pcs.firePropertyChange(new MultiElementRemovedEvent(this, values));
        }
        return success;
    }
    
    public Object set(final int index, final Object element) {
        final Object oldValue = this.delegate.set(index, element);
        if (this.test != null) {
            final Object result = this.test.call(element);
            if (result != null && result instanceof Boolean && (boolean)result) {
                this.pcs.firePropertyChange(new ElementUpdatedEvent(this, oldValue, element, index));
            }
        }
        else {
            this.pcs.firePropertyChange(new ElementUpdatedEvent(this, oldValue, element, index));
        }
        return oldValue;
    }
    
    public int size() {
        return this.delegate.size();
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        return this.delegate.subList(fromIndex, toIndex);
    }
    
    public Object[] toArray() {
        return this.delegate.toArray();
    }
    
    public Object[] toArray(final Object[] a) {
        return this.delegate.toArray(a);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
    
    public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(propertyName, listener);
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return this.pcs.getPropertyChangeListeners();
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners(final String propertyName) {
        return this.pcs.getPropertyChangeListeners(propertyName);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(propertyName, listener);
    }
    
    public boolean hasListeners(final String propertyName) {
        return this.pcs.hasListeners(propertyName);
    }
    
    private class ObservableIterator implements Iterator
    {
        private Iterator iterDelegate;
        protected int cursor;
        
        public ObservableIterator(final Iterator iterDelegate) {
            this.cursor = 0;
            this.iterDelegate = iterDelegate;
        }
        
        public Iterator getDelegate() {
            return this.iterDelegate;
        }
        
        public boolean hasNext() {
            return this.iterDelegate.hasNext();
        }
        
        public Object next() {
            ++this.cursor;
            return this.iterDelegate.next();
        }
        
        public void remove() {
            ObservableList.this.remove(this.cursor--);
        }
    }
    
    private class ObservableListIterator extends ObservableIterator implements ListIterator
    {
        public ObservableListIterator(final ListIterator iterDelegate, final int index) {
            super(iterDelegate);
            this.cursor = index;
        }
        
        public ListIterator getListIterator() {
            return (ListIterator)this.getDelegate();
        }
        
        public void add(final Object o) {
            ObservableList.this.add(o);
            ++this.cursor;
        }
        
        public boolean hasPrevious() {
            return this.getListIterator().hasPrevious();
        }
        
        public int nextIndex() {
            return this.getListIterator().nextIndex();
        }
        
        public Object previous() {
            return this.getListIterator().previous();
        }
        
        public int previousIndex() {
            return this.getListIterator().previousIndex();
        }
        
        public void set(final Object o) {
            ObservableList.this.set(this.cursor, o);
        }
    }
    
    public abstract static class ElementEvent extends PropertyChangeEvent
    {
        public static final int ADDED = 0;
        public static final int UPDATED = 1;
        public static final int REMOVED = 2;
        public static final int CLEARED = 3;
        public static final int MULTI_ADD = 4;
        public static final int MULTI_REMOVE = 5;
        private static final String PROPERTY_NAME = "groovy_util_ObservableList__element";
        protected static final Object OLDVALUE;
        protected static final Object NEWVALUE;
        private int type;
        private int index;
        
        public ElementEvent(final Object source, final Object oldValue, final Object newValue, final int index, final int type) {
            super(source, "groovy_util_ObservableList__element", oldValue, newValue);
            switch (type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    this.type = type;
                    break;
                }
                default: {
                    this.type = 1;
                    break;
                }
            }
            this.index = index;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public int getType() {
            return this.type;
        }
        
        public String getTypeAsString() {
            switch (this.type) {
                case 0: {
                    return "ADDED";
                }
                case 1: {
                    return "UPDATED";
                }
                case 2: {
                    return "REMOVED";
                }
                case 3: {
                    return "CLEARED";
                }
                case 4: {
                    return "MULTI_ADD";
                }
                case 5: {
                    return "MULTI_REMOVE";
                }
                default: {
                    return "UPDATED";
                }
            }
        }
        
        static {
            OLDVALUE = new Object();
            NEWVALUE = new Object();
        }
    }
    
    public static class ElementAddedEvent extends ElementEvent
    {
        public ElementAddedEvent(final Object source, final Object newValue, final int index) {
            super(source, null, newValue, index, 0);
        }
    }
    
    public static class ElementUpdatedEvent extends ElementEvent
    {
        public ElementUpdatedEvent(final Object source, final Object oldValue, final Object newValue, final int index) {
            super(source, oldValue, newValue, index, 1);
        }
    }
    
    public static class ElementRemovedEvent extends ElementEvent
    {
        public ElementRemovedEvent(final Object source, final Object newValue, final int index) {
            super(source, null, newValue, index, 2);
        }
    }
    
    public static class ElementClearedEvent extends ElementEvent
    {
        private List values;
        
        public ElementClearedEvent(final Object source, final List values) {
            super(source, ElementClearedEvent.OLDVALUE, ElementClearedEvent.NEWVALUE, 3, 0);
            this.values = new ArrayList();
            if (values != null) {
                this.values.addAll(values);
            }
        }
        
        public List getValues() {
            return Collections.unmodifiableList((List<?>)this.values);
        }
    }
    
    public static class MultiElementAddedEvent extends ElementEvent
    {
        private List values;
        
        public MultiElementAddedEvent(final Object source, final int index, final List values) {
            super(source, MultiElementAddedEvent.OLDVALUE, MultiElementAddedEvent.NEWVALUE, 4, index);
            this.values = new ArrayList();
            if (values != null) {
                this.values.addAll(values);
            }
        }
        
        public List getValues() {
            return Collections.unmodifiableList((List<?>)this.values);
        }
    }
    
    public static class MultiElementRemovedEvent extends ElementEvent
    {
        private List values;
        
        public MultiElementRemovedEvent(final Object source, final List values) {
            super(source, MultiElementRemovedEvent.OLDVALUE, MultiElementRemovedEvent.NEWVALUE, 4, 0);
            this.values = new ArrayList();
            if (values != null) {
                this.values.addAll(values);
            }
        }
        
        public List getValues() {
            return Collections.unmodifiableList((List<?>)this.values);
        }
    }
}
