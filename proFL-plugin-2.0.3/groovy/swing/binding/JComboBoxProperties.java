// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.binding.PropertyBinding;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.SourceBinding;
import javax.swing.JComboBox;
import java.util.HashMap;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;

public class JComboBoxProperties
{
    public static Map<String, TriggerBinding> getSyntheticProperties() {
        final Map<String, TriggerBinding> result = new HashMap<String, TriggerBinding>();
        result.put(JComboBox.class.getName() + "#selectedItem", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JComboBoxSelectedElementBinding((PropertyBinding)source, target, "selectedItem");
            }
        });
        result.put(JComboBox.class.getName() + "#selectedElement", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JComboBoxSelectedElementBinding((PropertyBinding)source, target, "selectedElement");
            }
        });
        result.put(JComboBox.class.getName() + "#selectedIndex", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JComboBoxSelectedIndexBinding((PropertyBinding)source, target);
            }
        });
        result.put(JComboBox.class.getName() + "#elements", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JComboBoxElementsBinding((PropertyBinding)source, target);
            }
        });
        return result;
    }
}
