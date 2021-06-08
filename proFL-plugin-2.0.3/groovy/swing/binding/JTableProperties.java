// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.binding.PropertyBinding;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.SourceBinding;
import javax.swing.JTable;
import java.util.HashMap;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;

public class JTableProperties
{
    public static Map<String, TriggerBinding> getSyntheticProperties() {
        final Map<String, TriggerBinding> result = new HashMap<String, TriggerBinding>();
        result.put(JTable.class.getName() + "#elements", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JTableElementsBinding((PropertyBinding)source, target);
            }
        });
        result.put(JTable.class.getName() + "#selectedElement", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JTableSelectedElementBinding((PropertyBinding)source, target, "selectedElement");
            }
        });
        result.put(JTable.class.getName() + "#selectedElements", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new JTableSelectedElementBinding((PropertyBinding)source, target, "selectedElements");
            }
        });
        return result;
    }
}
