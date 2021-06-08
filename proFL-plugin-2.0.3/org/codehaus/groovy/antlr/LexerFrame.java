// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.lang.reflect.Constructor;
import javax.swing.text.AttributeSet;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.io.FileInputStream;
import groovyjarjarantlr.CharScanner;
import java.io.InputStream;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import javax.swing.text.BadLocationException;
import javax.swing.JComponent;
import groovyjarjarantlr.Token;
import java.lang.reflect.Field;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Action;
import java.util.Hashtable;
import javax.swing.border.Border;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LexerFrame extends JFrame implements ActionListener
{
    JSplitPane jSplitPane1;
    JScrollPane jScrollPane1;
    JScrollPane jScrollPane2;
    JTextPane tokenPane;
    JButton jbutton;
    JPanel mainPanel;
    JTextArea scriptPane;
    Border border1;
    Border border2;
    Class lexerClass;
    Hashtable tokens;
    private Action loadFileAction;
    
    public LexerFrame(final Class lexerClass, final Class tokenTypesClass) {
        super("Token Steam Viewer");
        this.jSplitPane1 = new JSplitPane();
        this.jScrollPane1 = new JScrollPane();
        this.jScrollPane2 = new JScrollPane();
        this.tokenPane = new HScrollableTextPane();
        this.jbutton = new JButton("open");
        this.mainPanel = new JPanel(new BorderLayout());
        this.scriptPane = new JTextArea();
        this.tokens = new Hashtable();
        this.loadFileAction = new AbstractAction("Open File...") {
            public void actionPerformed(final ActionEvent ae) {
                final JFileChooser jfc = new JFileChooser();
                final int response = jfc.showOpenDialog(LexerFrame.this);
                if (response != 0) {
                    return;
                }
                try {
                    LexerFrame.this.scanScript(jfc.getSelectedFile());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        this.lexerClass = lexerClass;
        try {
            this.jbInit();
            this.setSize(500, 500);
            this.listTokens(tokenTypesClass);
            final JPopupMenu popup = new JPopupMenu();
            popup.add(this.loadFileAction);
            this.jbutton.setSize(30, 30);
            this.jbutton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(final MouseEvent e) {
                    popup.show(LexerFrame.this.scriptPane, e.getX(), e.getY());
                }
            });
            this.setDefaultCloseOperation(3);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void listTokens(final Class tokenTypes) throws Exception {
        final Field[] field = tokenTypes.getDeclaredFields();
        for (int i = 0; i < field.length; ++i) {
            this.tokens.put(field[i].get(null), field[i].getName());
        }
    }
    
    public void actionPerformed(final ActionEvent ae) {
        final Token token = (Token)((JComponent)ae.getSource()).getClientProperty("token");
        if (token.getType() == 1) {
            this.scriptPane.select(0, 0);
            return;
        }
        try {
            final int start = this.scriptPane.getLineStartOffset(token.getLine() - 1) + token.getColumn() - 1;
            this.scriptPane.select(start, start + token.getText().length());
            this.scriptPane.requestFocus();
        }
        catch (BadLocationException ex) {}
    }
    
    private void scanScript(final File file) throws Exception {
        this.scriptPane.read(new FileReader(file), null);
        final Constructor constructor = this.lexerClass.getConstructor(InputStream.class);
        final CharScanner lexer = constructor.newInstance(new FileInputStream(file));
        this.tokenPane.setEditable(true);
        this.tokenPane.setText("");
        int line = 1;
        final ButtonGroup bg = new ButtonGroup();
        Token token = null;
        do {
            token = lexer.nextToken();
            final JToggleButton tokenButton = new JToggleButton(this.tokens.get(token.getType()));
            bg.add(tokenButton);
            tokenButton.addActionListener(this);
            tokenButton.setToolTipText(token.getText());
            tokenButton.putClientProperty("token", token);
            tokenButton.setMargin(new Insets(0, 1, 0, 1));
            tokenButton.setFocusPainted(false);
            if (token.getLine() > line) {
                this.tokenPane.getDocument().insertString(this.tokenPane.getDocument().getLength(), "\n", null);
                line = token.getLine();
            }
            this.insertComponent(tokenButton);
        } while (token.getType() != 1);
        this.tokenPane.setEditable(false);
        this.tokenPane.setCaretPosition(0);
    }
    
    private void insertComponent(final JComponent comp) {
        try {
            this.tokenPane.getDocument().insertString(this.tokenPane.getDocument().getLength(), " ", null);
        }
        catch (BadLocationException ex2) {}
        try {
            this.tokenPane.setCaretPosition(this.tokenPane.getDocument().getLength() - 1);
        }
        catch (Exception ex) {
            this.tokenPane.setCaretPosition(0);
        }
        this.tokenPane.insertComponent(comp);
    }
    
    private void jbInit() throws Exception {
        this.border1 = BorderFactory.createEmptyBorder();
        this.border2 = BorderFactory.createEmptyBorder();
        this.jSplitPane1.setOrientation(0);
        this.tokenPane.setEditable(false);
        this.tokenPane.setText("");
        this.scriptPane.setFont(new Font("DialogInput", 0, 12));
        this.scriptPane.setEditable(false);
        this.scriptPane.setMargin(new Insets(5, 5, 5, 5));
        this.scriptPane.setText("");
        this.jScrollPane1.setBorder(this.border1);
        this.jScrollPane2.setBorder(this.border1);
        this.jSplitPane1.setMinimumSize(new Dimension(800, 600));
        this.mainPanel.add(this.jSplitPane1, "Center");
        this.mainPanel.add(this.jbutton, "North");
        this.getContentPane().add(this.mainPanel);
        this.jSplitPane1.add(this.jScrollPane1, "left");
        this.jScrollPane1.getViewport().add(this.tokenPane, null);
        this.jSplitPane1.add(this.jScrollPane2, "right");
        this.jScrollPane2.getViewport().add(this.scriptPane, null);
        this.jScrollPane1.setColumnHeaderView(new JLabel(" Token Stream:"));
        this.jScrollPane2.setColumnHeaderView(new JLabel(" Input Script:"));
        this.jSplitPane1.setResizeWeight(0.5);
    }
    
    public static void main(final String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        new LexerFrame(GroovyLexer.class, GroovyTokenTypes.class).setVisible(true);
    }
}
