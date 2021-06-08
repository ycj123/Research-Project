// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public class ParameterArray
{
    private Object parameters;
    
    public ParameterArray(final Object data) {
        this.parameters = this.packArray(data);
    }
    
    private Object packArray(final Object object) {
        if (object instanceof Object[]) {
            return object;
        }
        return object;
    }
    
    public Object get() {
        return this.parameters;
    }
    
    @Override
    public String toString() {
        if (this.parameters == null) {
            return "<null parameter>";
        }
        return this.parameters.toString();
    }
}
