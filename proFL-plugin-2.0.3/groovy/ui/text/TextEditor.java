// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.Document;
import javax.swing.text.Utilities;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.print.PrinterJob;
import java.awt.event.KeyEvent;
import javax.swing.plaf.ComponentUI;
import java.awt.Component;
import javax.swing.JComponent;
import java.awt.print.PrinterException;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.util.Calendar;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.print.Paper;
import javax.swing.text.StyledDocument;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseListener;
import javax.swing.InputMap;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.print.PageFormat;
import javax.swing.text.Caret;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.regex.Pattern;
import java.awt.print.Printable;
import java.awt.print.Pageable;
import javax.swing.JTextPane;

public class TextEditor extends JTextPane implements Pageable, Printable
{
    public static final String FIND = "Find...";
    public static final String FIND_NEXT = "Find Next";
    public static final String FIND_PREVIOUS = "Find Previous";
    public static final String REPLACE = "Replace...";
    public static final String AUTO_INDENT = "AutoIndent";
    private static final String TABBED_SPACES = "    ";
    private static final Pattern TAB_BACK_PATTERN;
    private static final Pattern LINE_START;
    private static final JTextPane PRINT_PANE;
    private static final Dimension PRINT_SIZE;
    private static Toolkit toolkit;
    private static boolean isOvertypeMode;
    private Caret defaultCaret;
    private Caret overtypeCaret;
    private static final PageFormat PAGE_FORMAT;
    private int numPages;
    private int lastUpdate;
    private MouseAdapter mouseAdapter;
    private boolean unwrapped;
    private boolean tabsAsSpaces;
    private boolean multiLineTab;
    private boolean searchable;
    
    public TextEditor() {
        this(false);
    }
    
    public TextEditor(final boolean tabsAsSpaces) {
        this(tabsAsSpaces, false);
    }
    
    public TextEditor(final boolean tabsAsSpaces, final boolean multiLineTab) {
        this(multiLineTab, tabsAsSpaces, false);
    }
    
    public TextEditor(final boolean tabsAsSpaces, final boolean multiLineTab, final boolean unwrapped) {
        this.mouseAdapter = new MouseAdapter() {
            Cursor cursor;
            
            @Override
            public void mouseEntered(final MouseEvent me) {
                if (TextEditor.this.contains(me.getPoint())) {
                    this.cursor = TextEditor.this.getCursor();
                    final Cursor curs = Cursor.getPredefinedCursor(2);
                    TextEditor.this.getRootPane().getLayeredPane().setCursor(curs);
                }
                else {
                    TextEditor.this.getRootPane().getLayeredPane().setCursor(this.cursor);
                }
            }
            
            @Override
            public void mouseExited(final MouseEvent me) {
                TextEditor.this.getRootPane().getLayeredPane().setCursor(null);
            }
        };
        this.searchable = true;
        this.tabsAsSpaces = tabsAsSpaces;
        this.multiLineTab = multiLineTab;
        this.unwrapped = unwrapped;
        ActionMap aMap = this.getActionMap();
        Action action = null;
        do {
            action = ((action == null) ? aMap.get("delete-previous") : null);
            aMap.remove("delete-previous");
            aMap = aMap.getParent();
        } while (aMap != null);
        aMap = this.getActionMap();
        InputMap iMap = this.getInputMap();
        KeyStroke keyStroke = KeyStroke.getKeyStroke(8, 0, false);
        iMap.put(keyStroke, "delete");
        keyStroke = KeyStroke.getKeyStroke(8, 1, false);
        iMap.put(keyStroke, "delete");
        aMap.put("delete", action);
        action = new FindAction();
        aMap.put("Find...", action);
        keyStroke = KeyStroke.getKeyStroke(70, 2, false);
        iMap.put(keyStroke, "Find...");
        aMap.put("Find Next", FindReplaceUtility.FIND_ACTION);
        keyStroke = KeyStroke.getKeyStroke(114, 0, false);
        iMap.put(keyStroke, "Find Next");
        aMap.put("Find Previous", FindReplaceUtility.FIND_ACTION);
        keyStroke = KeyStroke.getKeyStroke(114, 1, false);
        iMap.put(keyStroke, "Find Previous");
        action = new TabAction();
        aMap.put("TextEditor-tabAction", action);
        keyStroke = KeyStroke.getKeyStroke(9, 0, false);
        iMap.put(keyStroke, "TextEditor-tabAction");
        action = new ShiftTabAction();
        aMap.put("TextEditor-shiftTabAction", action);
        keyStroke = KeyStroke.getKeyStroke(9, 1, false);
        iMap.put(keyStroke, "TextEditor-shiftTabAction");
        action = new ReplaceAction();
        this.getActionMap().put("Replace...", action);
        keyStroke = KeyStroke.getKeyStroke(72, 2, false);
        do {
            iMap.remove(keyStroke);
            iMap = iMap.getParent();
        } while (iMap != null);
        this.getInputMap().put(keyStroke, "Replace...");
        action = new AutoIndentAction();
        this.getActionMap().put("AutoIndent", action);
        keyStroke = KeyStroke.getKeyStroke(10, 0, false);
        this.getInputMap().put(keyStroke, "AutoIndent");
        this.setAutoscrolls(true);
        this.defaultCaret = this.getCaret();
        (this.overtypeCaret = new OvertypeCaret()).setBlinkRate(this.defaultCaret.getBlinkRate());
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        this.addMouseListener(this.mouseAdapter);
        FindReplaceUtility.registerTextComponent(this);
    }
    
    public int getNumberOfPages() {
        final StyledDocument doc = (StyledDocument)this.getDocument();
        final Paper paper = TextEditor.PAGE_FORMAT.getPaper();
        return this.numPages = (int)Math.ceil(this.getSize().getHeight() / paper.getImageableHeight());
    }
    
    public PageFormat getPageFormat(final int pageIndex) throws IndexOutOfBoundsException {
        return TextEditor.PAGE_FORMAT;
    }
    
    public Printable getPrintable(final int param) throws IndexOutOfBoundsException {
        return this;
    }
    
    public int print(final Graphics graphics, final PageFormat pageFormat, final int page) throws PrinterException {
        if (page < this.numPages) {
            final StyledDocument doc = (StyledDocument)this.getDocument();
            final Paper paper = pageFormat.getPaper();
            TextEditor.PRINT_PANE.setDocument(this.getDocument());
            TextEditor.PRINT_PANE.setFont(this.getFont());
            TextEditor.PRINT_SIZE.setSize(paper.getImageableWidth(), this.getSize().getHeight());
            TextEditor.PRINT_PANE.setSize(TextEditor.PRINT_SIZE);
            final double y = -(page * paper.getImageableHeight()) + paper.getImageableY();
            ((Graphics2D)graphics).translate(paper.getImageableX(), y);
            TextEditor.PRINT_PANE.print(graphics);
            ((Graphics2D)graphics).translate(0.0, -y);
            final Rectangle rect = graphics.getClipBounds();
            graphics.setClip(rect.x, 0, rect.width, (int)paper.getHeight() + 100);
            final Calendar cal = Calendar.getInstance();
            final String header = cal.getTime().toString().trim();
            final String name = (this.getName() == null) ? System.getProperty("user.name").trim() : this.getName().trim();
            final String pageStr = String.valueOf(page + 1);
            final Font font = Font.decode("Monospaced 8");
            graphics.setFont(font);
            final FontMetrics fm = graphics.getFontMetrics(font);
            int width = SwingUtilities.computeStringWidth(fm, header);
            ((Graphics2D)graphics).drawString(header, (float)(paper.getImageableWidth() / 2.0 - width / 2), (float)paper.getImageableY() / 2.0f + fm.getHeight());
            ((Graphics2D)graphics).translate(0.0, paper.getImageableY() - fm.getHeight());
            final double height = paper.getImageableHeight() + paper.getImageableY() / 2.0;
            width = SwingUtilities.computeStringWidth(fm, name);
            ((Graphics2D)graphics).drawString(name, (float)(paper.getImageableWidth() / 2.0 - width / 2), (float)height - fm.getHeight() / 2);
            ((Graphics2D)graphics).translate(0, fm.getHeight());
            width = SwingUtilities.computeStringWidth(fm, pageStr);
            ((Graphics2D)graphics).drawString(pageStr, (float)(paper.getImageableWidth() / 2.0 - width / 2), (float)height - fm.getHeight() / 2);
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean getScrollableTracksViewportWidth() {
        boolean bool = super.getScrollableTracksViewportWidth();
        if (this.unwrapped) {
            final Component parent = this.getParent();
            final ComponentUI ui = this.getUI();
            final int uiWidth = ui.getPreferredSize(this).width;
            final int parentWidth = parent.getSize().width;
            bool = (parent == null || ui.getPreferredSize(this).width < parent.getSize().width);
        }
        return bool;
    }
    
    public boolean isMultiLineTabbed() {
        return this.multiLineTab;
    }
    
    public static boolean isOvertypeMode() {
        return TextEditor.isOvertypeMode;
    }
    
    public boolean isTabsAsSpaces() {
        return this.tabsAsSpaces;
    }
    
    public boolean isUnwrapped() {
        return this.unwrapped;
    }
    
    @Override
    protected void processKeyEvent(final KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == 402 && e.getKeyCode() == 155) {
            this.setOvertypeMode(!isOvertypeMode());
        }
    }
    
    @Override
    public void removeNotify() {
        super.removeNotify();
        this.removeMouseListener(this.mouseAdapter);
        FindReplaceUtility.unregisterTextComponent(this);
    }
    
    @Override
    public void replaceSelection(final String text) {
        if (isOvertypeMode()) {
            final int pos = this.getCaretPosition();
            if (this.getSelectedText() == null && pos < this.getDocument().getLength()) {
                this.moveCaretPosition(pos + 1);
            }
        }
        super.replaceSelection(text);
    }
    
    @Override
    public void setBounds(final int x, final int y, final int width, final int height) {
        if (this.unwrapped) {
            final Dimension size = this.getPreferredSize();
            super.setBounds(x, y, Math.max(size.width, width), Math.max(size.height, height));
        }
        else {
            super.setBounds(x, y, width, height);
        }
    }
    
    public void isMultiLineTabbed(final boolean multiLineTab) {
        this.multiLineTab = multiLineTab;
    }
    
    public void isTabsAsSpaces(final boolean tabsAsSpaces) {
        this.tabsAsSpaces = tabsAsSpaces;
    }
    
    public void setOvertypeMode(final boolean isOvertypeMode) {
        TextEditor.isOvertypeMode = isOvertypeMode;
        final int pos = this.getCaretPosition();
        if (isOvertypeMode()) {
            this.setCaret(this.overtypeCaret);
        }
        else {
            this.setCaret(this.defaultCaret);
        }
        this.setCaretPosition(pos);
    }
    
    public void setUnwrapped(final boolean unwrapped) {
        this.unwrapped = unwrapped;
    }
    
    static {
        TAB_BACK_PATTERN = Pattern.compile("^(([\t])|(    )|(   )|(  )|( ))", 8);
        LINE_START = Pattern.compile("^", 8);
        PRINT_PANE = new JTextPane();
        PRINT_SIZE = new Dimension();
        TextEditor.toolkit = Toolkit.getDefaultToolkit();
        final PrinterJob job = PrinterJob.getPrinterJob();
        PAGE_FORMAT = job.defaultPage();
    }
    
    private class FindAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.showDialog();
        }
    }
    
    private class ReplaceAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.showDialog(true);
        }
    }
    
    private class ShiftTabAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent ae) {
            try {
                if (TextEditor.this.multiLineTab && TextEditor.this.getSelectedText() != null) {
                    final Document doc = TextEditor.this.getDocument();
                    final int end = Utilities.getRowEnd(TextEditor.this, TextEditor.this.getSelectionEnd());
                    TextEditor.this.setSelectionEnd(end);
                    final Element el = Utilities.getParagraphElement(TextEditor.this, TextEditor.this.getSelectionStart());
                    final int start = el.getStartOffset();
                    TextEditor.this.setSelectionStart(start);
                    final String text = TextEditor.this.tabsAsSpaces ? TextEditor.TAB_BACK_PATTERN.matcher(TextEditor.this.getSelectedText()).replaceAll("") : TextEditor.this.getSelectedText().replaceAll("^\t", "");
                    TextEditor.this.replaceSelection(text);
                    TextEditor.this.select(start, start + text.length());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private class TabAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent ae) {
            try {
                final Document doc = TextEditor.this.getDocument();
                final String text = TextEditor.this.tabsAsSpaces ? "    " : "\t";
                if (TextEditor.this.multiLineTab && TextEditor.this.getSelectedText() != null) {
                    final int end = Utilities.getRowEnd(TextEditor.this, TextEditor.this.getSelectionEnd());
                    TextEditor.this.setSelectionEnd(end);
                    final Element el = Utilities.getParagraphElement(TextEditor.this, TextEditor.this.getSelectionStart());
                    final int start = el.getStartOffset();
                    TextEditor.this.setSelectionStart(start);
                    String toReplace = TextEditor.this.getSelectedText();
                    toReplace = TextEditor.LINE_START.matcher(toReplace).replaceAll(text);
                    TextEditor.this.replaceSelection(toReplace);
                    TextEditor.this.select(start, start + toReplace.length());
                }
                else {
                    final int pos = TextEditor.this.getCaretPosition();
                    doc.insertString(pos, text, null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private class OvertypeCaret extends DefaultCaret
    {
        @Override
        public void paint(final Graphics g) {
            if (this.isVisible()) {
                try {
                    final JTextComponent component = this.getComponent();
                    final Rectangle r = component.getUI().modelToView(component, this.getDot());
                    final Color c = g.getColor();
                    g.setColor(component.getBackground());
                    g.setXORMode(component.getCaretColor());
                    r.setBounds(r.x, r.y, g.getFontMetrics().charWidth('w'), g.getFontMetrics().getHeight());
                    g.fillRect(r.x, r.y, r.width, r.height);
                    g.setPaintMode();
                    g.setColor(c);
                }
                catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
        
        @Override
        protected synchronized void damage(final Rectangle r) {
            if (r != null) {
                final JTextComponent component = this.getComponent();
                this.x = r.x;
                this.y = r.y;
                final Font font = component.getFont();
                this.width = component.getFontMetrics(font).charWidth('w');
                this.height = r.height;
                this.repaint();
            }
        }
    }
}
