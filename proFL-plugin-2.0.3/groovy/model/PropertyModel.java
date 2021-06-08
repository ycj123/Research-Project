// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import org.codehaus.groovy.runtime.InvokerHelper;

public class PropertyModel implements ValueModel, NestedValueModel
{
    private ValueModel sourceModel;
    private String property;
    private Class type;
    boolean editable;
    
    public PropertyModel(final ValueModel sourceModel, final String property) {
        this(sourceModel, property, Object.class, true);
    }
    
    public PropertyModel(final ValueModel sourceModel, final String property, final Class type) {
        this(sourceModel, property, type, true);
    }
    
    public PropertyModel(final ValueModel sourceModel, final String property, final Class type, final boolean editable) {
        this.sourceModel = sourceModel;
        this.property = property;
        this.type = type;
        this.editable = editable;
    }
    
    public String getProperty() {
        return this.property;
    }
    
    public ValueModel getSourceModel() {
        return this.sourceModel;
    }
    
    public Object getValue() {
        final Object source = this.sourceModel.getValue();
        if (source != null) {
            return InvokerHelper.getProperty(source, this.property);
        }
        return null;
    }
    
    public void setValue(final Object value) {
        final Object source = this.sourceModel.getValue();
        if (source != null) {
            InvokerHelper.setProperty(source, this.property, value);
        }
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
}
