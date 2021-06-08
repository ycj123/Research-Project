// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import java.awt.event.ComponentEvent;
import org.codehaus.groovy.binding.PropertyBinding;
import org.codehaus.groovy.binding.FullBinding;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.SourceBinding;
import javax.swing.JComponent;
import java.util.HashMap;
import org.codehaus.groovy.binding.TriggerBinding;
import java.util.Map;

public class JComponentProperties
{
    public static Map<String, TriggerBinding> getSyntheticProperties() {
        final Map<String, TriggerBinding> result = new HashMap<String, TriggerBinding>();
        result.put(JComponent.class.getName() + "#size", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "size") {
                    @Override
                    public void componentResized(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#width", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "width") {
                    @Override
                    public void componentResized(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#height", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "height") {
                    @Override
                    public void componentResized(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#bounds", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "bounds") {
                    @Override
                    public void componentResized(final ComponentEvent event) {
                        this.update();
                    }
                    
                    @Override
                    public void componentMoved(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#x", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "x") {
                    @Override
                    public void componentMoved(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#y", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "y") {
                    @Override
                    public void componentMoved(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        result.put(JComponent.class.getName() + "#visible", new TriggerBinding() {
            public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
                return new AbstractJComponentBinding((PropertyBinding)source, target, "visible") {
                    @Override
                    public void componentHidden(final ComponentEvent event) {
                        this.update();
                    }
                    
                    @Override
                    public void componentShown(final ComponentEvent event) {
                        this.update();
                    }
                };
            }
        });
        return result;
    }
}
