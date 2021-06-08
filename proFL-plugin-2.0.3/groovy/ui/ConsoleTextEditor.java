// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.beans.PropertyChangeEvent;
import java.awt.print.Pageable;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import javax.swing.text.BadLocationException;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import groovy.ui.text.StructuredSyntaxResources;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.beans.PropertyChangeListener;
import javax.swing.event.UndoableEditListener;
import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import groovy.ui.text.GroovyFilter;
import javax.swing.text.DefaultStyledDocument;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.prefs.Preferences;
import java.awt.Graphics;
import groovy.ui.text.TextUndoManager;
import groovy.ui.text.TextEditor;
import java.awt.print.PrinterJob;
import javax.swing.JScrollPane;

public class ConsoleTextEditor extends JScrollPane
{
    private String defaultFamily;
    private static final PrinterJob PRINTER_JOB;
    private LineNumbersPanel numbersPanel;
    private boolean documentChangedSinceLastRepaint;
    private TextEditor textEditor;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private PrintAction printAction;
    private boolean editable;
    private TextUndoManager undoManager;
    
    public String getDefaultFamily() {
        return this.defaultFamily;
    }
    
    public void setDefaultFamily(final String defaultFamily) {
        this.defaultFamily = defaultFamily;
    }
    
    public ConsoleTextEditor() {
        this.defaultFamily = "Monospaced";
        this.numbersPanel = new LineNumbersPanel();
        this.documentChangedSinceLastRepaint = false;
        this.textEditor = new TextEditor(true, true, true) {
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (ConsoleTextEditor.this.documentChangedSinceLastRepaint) {
                    ConsoleTextEditor.this.numbersPanel.repaint();
                    ConsoleTextEditor.this.documentChangedSinceLastRepaint = false;
                }
            }
        };
        this.undoAction = new UndoAction();
        this.redoAction = new RedoAction();
        this.printAction = new PrintAction();
        this.editable = true;
        this.textEditor.setFont(new Font(this.defaultFamily, 0, Preferences.userNodeForPackage(Console.class).getInt("fontSize", 12)));
        this.setViewportView(new JPanel(new BorderLayout()) {
            {
                this.add(ConsoleTextEditor.this.numbersPanel, "West");
                this.add(ConsoleTextEditor.this.textEditor, "Center");
            }
        });
        this.textEditor.setDragEnabled(this.editable);
        this.getVerticalScrollBar().setUnitIncrement(10);
        this.initActions();
        final DefaultStyledDocument doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new GroovyFilter(doc));
        this.textEditor.setDocument(doc);
        doc.addDocumentListener(new DocumentListener() {
            public void insertUpdate(final DocumentEvent documentEvent) {
                ConsoleTextEditor.this.documentChangedSinceLastRepaint = true;
            }
            
            public void removeUpdate(final DocumentEvent documentEvent) {
                ConsoleTextEditor.this.documentChangedSinceLastRepaint = true;
            }
            
            public void changedUpdate(final DocumentEvent documentEvent) {
                ConsoleTextEditor.this.documentChangedSinceLastRepaint = true;
                final int width = 3 * Preferences.userNodeForPackage(Console.class).getInt("fontSize", 12);
                ConsoleTextEditor.this.numbersPanel.setPreferredSize(new Dimension(width, width));
            }
        });
        doc.addUndoableEditListener(this.undoManager = new TextUndoManager());
        this.undoManager.addPropertyChangeListener(this.undoAction);
        this.undoManager.addPropertyChangeListener(this.redoAction);
        doc.addDocumentListener(this.undoAction);
        doc.addDocumentListener(this.redoAction);
        final InputMap im = this.textEditor.getInputMap(2);
        KeyStroke ks = KeyStroke.getKeyStroke(90, 2, false);
        im.put(ks, "Undo");
        final ActionMap am = this.textEditor.getActionMap();
        am.put("Undo", this.undoAction);
        ks = KeyStroke.getKeyStroke(89, 2, false);
        im.put(ks, "Redo");
        am.put("Redo", this.redoAction);
        ks = KeyStroke.getKeyStroke(80, 2, false);
        im.put(ks, "Print");
        am.put("Print", this.printAction);
    }
    
    public boolean clipBoardAvailable() {
        final Transferable t = StructuredSyntaxResources.SYSTEM_CLIPBOARD.getContents(this);
        return t.isDataFlavorSupported(DataFlavor.stringFlavor);
    }
    
    public TextEditor getTextEditor() {
        return this.textEditor;
    }
    
    protected void initActions() {
        final ActionMap map = this.getActionMap();
        final PrintAction printAction = new PrintAction();
        map.put("Print", printAction);
    }
    
    public Action getUndoAction() {
        return this.undoAction;
    }
    
    public Action getRedoAction() {
        return this.redoAction;
    }
    
    public Action getPrintAction() {
        return this.printAction;
    }
    
    static {
        PRINTER_JOB = PrinterJob.getPrinterJob();
    }
    
    private class LineNumbersPanel extends JPanel
    {
        public LineNumbersPanel() {
            final int initialSize = 3 * Preferences.userNodeForPackage(Console.class).getInt("fontSize", 12);
            this.setMinimumSize(new Dimension(initialSize, initialSize));
            this.setPreferredSize(new Dimension(initialSize, initialSize));
        }
        
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final int start = ConsoleTextEditor.this.textEditor.viewToModel(ConsoleTextEditor.this.getViewport().getViewPosition());
            final int end = ConsoleTextEditor.this.textEditor.viewToModel(new Point(10, ConsoleTextEditor.this.getViewport().getViewPosition().y + (int)ConsoleTextEditor.this.textEditor.getVisibleRect().getHeight()));
            final Document doc = ConsoleTextEditor.this.textEditor.getDocument();
            final int startline = doc.getDefaultRootElement().getElementIndex(start) + 1;
            final int endline = doc.getDefaultRootElement().getElementIndex(end) + 1;
            final Font f = ConsoleTextEditor.this.textEditor.getFont();
            final int fontHeight = g.getFontMetrics(f).getHeight();
            final int fontDesc = g.getFontMetrics(f).getDescent();
            int startingY = -1;
            try {
                startingY = ConsoleTextEditor.this.textEditor.modelToView(start).y + fontHeight - fontDesc;
            }
            catch (BadLocationException e1) {
                System.err.println(e1.getMessage());
            }
            g.setFont(f);
            int line = startline;
            int y = startingY;
            while (line <= endline) {
                final String lineNumber = DefaultGroovyMethods.padLeft(Integer.toString(line), 4, " ");
                g.drawString(lineNumber, 0, y);
                y += fontHeight;
                ++line;
            }
        }
    }
    
    private class PrintAction extends AbstractAction
    {
        public PrintAction() {
            this.setEnabled(true);
        }
        
        public void actionPerformed(final ActionEvent ae) {
            ConsoleTextEditor.PRINTER_JOB.setPageable(ConsoleTextEditor.this.textEditor);
            try {
                if (ConsoleTextEditor.PRINTER_JOB.printDialog()) {
                    ConsoleTextEditor.PRINTER_JOB.print();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private class RedoAction extends UpdateCaretListener implements PropertyChangeListener
    {
        public RedoAction() {
            this.setEnabled(false);
        }
        
        @Override
        public void actionPerformed(final ActionEvent ae) {
            ConsoleTextEditor.this.undoManager.redo();
            this.setEnabled(ConsoleTextEditor.this.undoManager.canRedo());
            ConsoleTextEditor.this.undoAction.setEnabled(ConsoleTextEditor.this.undoManager.canUndo());
            super.actionPerformed(ae);
        }
        
        public void propertyChange(final PropertyChangeEvent pce) {
            this.setEnabled(ConsoleTextEditor.this.undoManager.canRedo());
        }
    }
    
    private abstract class UpdateCaretListener extends AbstractAction implements DocumentListener
    {
        protected int lastUpdate;
        
        public void changedUpdate(final DocumentEvent de) {
        }
        
        public void insertUpdate(final DocumentEvent de) {
            this.lastUpdate = de.getOffset() + de.getLength();
        }
        
        public void removeUpdate(final DocumentEvent de) {
            this.lastUpdate = de.getOffset();
        }
        
        public void actionPerformed(final ActionEvent ae) {
            ConsoleTextEditor.this.textEditor.setCaretPosition(this.lastUpdate);
        }
    }
    
    private class UndoAction extends UpdateCaretListener implements PropertyChangeListener
    {
        public UndoAction() {
            this.setEnabled(false);
        }
        
        @Override
        public void actionPerformed(final ActionEvent ae) {
            ConsoleTextEditor.this.undoManager.undo();
            this.setEnabled(ConsoleTextEditor.this.undoManager.canUndo());
            ConsoleTextEditor.this.redoAction.setEnabled(ConsoleTextEditor.this.undoManager.canRedo());
            super.actionPerformed(ae);
        }
        
        public void propertyChange(final PropertyChangeEvent pce) {
            this.setEnabled(ConsoleTextEditor.this.undoManager.canUndo());
        }
    }
}
