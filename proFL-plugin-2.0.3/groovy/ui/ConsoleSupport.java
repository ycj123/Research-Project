// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import javax.swing.text.StyledDocument;
import java.awt.Color;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.JTextPane;
import groovy.lang.GroovyShell;
import javax.swing.text.Style;

public abstract class ConsoleSupport
{
    Style promptStyle;
    Style commandStyle;
    Style outputStyle;
    private GroovyShell shell;
    int counter;
    
    protected void addStylesToDocument(final JTextPane outputArea) {
        final StyledDocument doc = outputArea.getStyledDocument();
        final Style def = StyleContext.getDefaultStyleContext().getStyle("default");
        final Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "Monospaced");
        StyleConstants.setForeground(this.promptStyle = doc.addStyle("prompt", regular), Color.BLUE);
        StyleConstants.setForeground(this.commandStyle = doc.addStyle("command", regular), Color.MAGENTA);
        StyleConstants.setBold(this.outputStyle = doc.addStyle("output", regular), true);
    }
    
    public Style getCommandStyle() {
        return this.commandStyle;
    }
    
    public Style getOutputStyle() {
        return this.outputStyle;
    }
    
    public Style getPromptStyle() {
        return this.promptStyle;
    }
    
    public GroovyShell getShell() {
        if (this.shell == null) {
            this.shell = new GroovyShell();
        }
        return this.shell;
    }
    
    protected Object evaluate(final String text) {
        final String name = "Script" + this.counter++;
        try {
            return this.getShell().evaluate(text, name);
        }
        catch (Exception e) {
            this.handleException(text, e);
            return null;
        }
    }
    
    protected abstract void handleException(final String p0, final Exception p1);
}
