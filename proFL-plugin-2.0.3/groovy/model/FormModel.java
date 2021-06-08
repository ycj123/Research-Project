// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import java.util.HashMap;
import java.util.Map;

public class FormModel
{
    private Map fieldModels;
    
    public FormModel() {
        this(new HashMap());
    }
    
    public FormModel(final Map fieldModels) {
        this.fieldModels = fieldModels;
    }
    
    public void addModel(final String name, final Object model) {
        this.fieldModels.put(name, model);
    }
    
    public Object getModel(final String name) {
        return this.fieldModels.get(name);
    }
}
