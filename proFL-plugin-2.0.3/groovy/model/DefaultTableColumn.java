// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import javax.swing.table.TableColumn;

public class DefaultTableColumn extends TableColumn
{
    private ValueModel valueModel;
    
    public DefaultTableColumn(final ValueModel valueModel) {
        this.valueModel = valueModel;
    }
    
    public DefaultTableColumn(final Object header, final ValueModel valueModel) {
        this(valueModel);
        this.setHeaderValue(header);
    }
    
    public DefaultTableColumn(final Object headerValue, final Object identifier, final ValueModel columnValueModel) {
        this(headerValue, columnValueModel);
        this.setIdentifier(identifier);
    }
    
    @Override
    public String toString() {
        return super.toString() + "[header:" + this.getHeaderValue() + " valueModel:" + this.valueModel + "]";
    }
    
    public Object getValue(final Object row, final int rowIndex, final int columnIndex) {
        if (this.valueModel instanceof NestedValueModel) {
            final NestedValueModel nestedModel = (NestedValueModel)this.valueModel;
            nestedModel.getSourceModel().setValue(row);
        }
        return this.valueModel.getValue();
    }
    
    public void setValue(final Object row, final Object value, final int rowIndex, final int columnIndex) {
        if (this.valueModel instanceof NestedValueModel) {
            final NestedValueModel nestedModel = (NestedValueModel)this.valueModel;
            nestedModel.getSourceModel().setValue(row);
        }
        this.valueModel.setValue(value);
    }
    
    public Class getType() {
        return this.valueModel.getType();
    }
    
    public ValueModel getValueModel() {
        return this.valueModel;
    }
}
