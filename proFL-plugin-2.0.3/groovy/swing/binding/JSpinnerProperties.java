// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.binding.PropertyBinding;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.SourceBinding;
import javax.swing.JSpinner;
import java.util.HashMap;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;

public class JSpinnerProperties
{
    public static Map<String, TriggerBinding> getSyntheticProperties() {
        final Map<String, TriggerBinding> result = new HashMap<String, TriggerBinding>();
        result.put(JSpinner.class.getName() + "#value", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JSpinnerValueBinding((PropertyBinding)source, target);
            }
        });
        return result;
    }
}
