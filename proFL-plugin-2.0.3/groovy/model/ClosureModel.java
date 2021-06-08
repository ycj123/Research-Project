// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import groovy.lang.Closure;

public class ClosureModel implements ValueModel, NestedValueModel
{
    private final ValueModel sourceModel;
    private final Closure readClosure;
    private final Closure writeClosure;
    private final Class type;
    
    public ClosureModel(final ValueModel sourceModel, final Closure readClosure) {
        this(sourceModel, readClosure, null);
    }
    
    public ClosureModel(final ValueModel sourceModel, final Closure readClosure, final Closure writeClosure) {
        this(sourceModel, readClosure, writeClosure, Object.class);
    }
    
    public ClosureModel(final ValueModel sourceModel, final Closure readClosure, final Closure writeClosure, final Class type) {
        this.sourceModel = sourceModel;
        this.readClosure = readClosure;
        this.writeClosure = writeClosure;
        this.type = type;
    }
    
    public ValueModel getSourceModel() {
        return this.sourceModel;
    }
    
    public Object getValue() {
        final Object source = this.sourceModel.getValue();
        if (source != null) {
            return this.readClosure.call(source);
        }
        return null;
    }
    
    public void setValue(final Object value) {
        if (this.writeClosure != null) {
            final Object source = this.sourceModel.getValue();
            if (source != null) {
                this.writeClosure.call(new Object[] { source, value });
            }
        }
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isEditable() {
        return this.writeClosure != null;
    }
}
