// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeListener;

class JTextComponentTextBinding extends AbstractSyntheticBinding implements PropertyChangeListener, DocumentListener
{
    JTextComponent boundTextComponent;
    
    public JTextComponentTextBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, JTextComponent.class, "text");
        source.setNonChangeCheck(true);
    }
    
    public synchronized void syntheticBind() {
        (this.boundTextComponent = (JTextComponent)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("document", this);
        this.boundTextComponent.getDocument().addDocumentListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundTextComponent.removePropertyChangeListener("document", this);
        this.boundTextComponent.getDocument().removeDocumentListener(this);
        this.boundTextComponent = null;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((Document)event.getOldValue()).removeDocumentListener(this);
        ((Document)event.getNewValue()).addDocumentListener(this);
    }
    
    public void changedUpdate(final DocumentEvent event) {
        this.update();
    }
    
    public void insertUpdate(final DocumentEvent event) {
        this.update();
    }
    
    public void removeUpdate(final DocumentEvent event) {
        this.update();
    }
}
