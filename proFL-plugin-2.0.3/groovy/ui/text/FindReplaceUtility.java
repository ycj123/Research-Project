// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JRootPane;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.KeyStroke;
import java.awt.event.FocusEvent;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.text.Document;
import java.awt.event.FocusListener;
import java.util.EventListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.FocusAdapter;
import javax.swing.text.Segment;
import javax.swing.event.EventListenerList;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.Action;

public final class FindReplaceUtility
{
    public static final String FIND_ACTION_COMMAND = "Find";
    public static final String REPLACE_ACTION_COMMAND = "Replace";
    public static final String REPLACE_ALL_ACTION_COMMAND = "Replace All";
    public static final String CLOSE_ACTION_COMMAND = "Close";
    public static final Action FIND_ACTION;
    private static final JDialog FIND_REPLACE_DIALOG;
    private static final JPanel TEXT_FIELD_PANEL;
    private static final JPanel ENTRY_PANEL;
    private static final JPanel FIND_PANEL;
    private static final JLabel FIND_LABEL;
    private static final JComboBox FIND_FIELD;
    private static final JPanel REPLACE_PANEL;
    private static final JLabel REPLACE_LABEL;
    private static final JComboBox REPLACE_FIELD;
    private static final JPanel BUTTON_PANEL;
    private static final JButton FIND_BUTTON;
    private static final JButton REPLACE_BUTTON;
    private static final JButton REPLACE_ALL_BUTTON;
    private static final JButton CLOSE_BUTTON;
    private static final Action CLOSE_ACTION;
    private static final Action REPLACE_ACTION;
    private static final JPanel CHECK_BOX_PANEL;
    private static final JCheckBox MATCH_CASE_CHECKBOX;
    private static final JCheckBox IS_BACKWARDS_CHECKBOX;
    private static final JCheckBox WRAP_SEARCH_CHECKBOX;
    private static JTextComponent textComponent;
    private static AttributeSet attributeSet;
    private static int findReplaceCount;
    private static String lastAction;
    private static final EventListenerList EVENT_LISTENER_LIST;
    private static final Segment SEGMENT;
    private static final FocusAdapter TEXT_FOCUS_LISTENER;
    
    private FindReplaceUtility() {
    }
    
    public static void addTextListener(final TextListener tl) {
        FindReplaceUtility.EVENT_LISTENER_LIST.add(TextListener.class, tl);
    }
    
    private static void fireTextEvent() {
        final EventListener[] lstrs = FindReplaceUtility.EVENT_LISTENER_LIST.getListeners(TextListener.class);
        if (lstrs != null && lstrs.length > 0) {
            final TextEvent te = new TextEvent(FindReplaceUtility.FIND_REPLACE_DIALOG, 900);
            for (int i = 0; i < lstrs.length; ++i) {
                ((TextListener)lstrs[i]).textValueChanged(te);
            }
        }
    }
    
    public static String getLastAction() {
        return FindReplaceUtility.lastAction;
    }
    
    public static int getReplacementCount() {
        return FindReplaceUtility.findReplaceCount;
    }
    
    public static String getSearchText() {
        return (String)FindReplaceUtility.FIND_FIELD.getSelectedItem();
    }
    
    public static void registerTextComponent(final JTextComponent textComponent) {
        textComponent.addFocusListener(FindReplaceUtility.TEXT_FOCUS_LISTENER);
    }
    
    public static void removeTextListener(final TextListener tl) {
        FindReplaceUtility.EVENT_LISTENER_LIST.remove(TextListener.class, tl);
    }
    
    private static int findNext(final boolean reverse, int pos) {
        boolean backwards = FindReplaceUtility.IS_BACKWARDS_CHECKBOX.isSelected();
        backwards = (backwards ? (!reverse) : reverse);
        final String pattern = (String)FindReplaceUtility.FIND_FIELD.getSelectedItem();
        if (pattern != null && pattern.length() > 0) {
            try {
                final Document doc = FindReplaceUtility.textComponent.getDocument();
                doc.getText(0, doc.getLength(), FindReplaceUtility.SEGMENT);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            pos += ((FindReplaceUtility.textComponent.getSelectedText() == null) ? (backwards ? -1 : 1) : 0);
            final char first = backwards ? pattern.charAt(pattern.length() - 1) : pattern.charAt(0);
            final char oppFirst = Character.isUpperCase(first) ? Character.toLowerCase(first) : Character.toUpperCase(first);
            final int start = pos;
            boolean wrapped = FindReplaceUtility.WRAP_SEARCH_CHECKBOX.isSelected();
            int end = backwards ? 0 : FindReplaceUtility.SEGMENT.getEndIndex();
            pos += (backwards ? -1 : 1);
            final int length = FindReplaceUtility.textComponent.getDocument().getLength();
            if (pos > length) {
                pos = (wrapped ? 0 : length);
            }
            boolean found = false;
            while (!found) {
                if (backwards) {
                    if (pos <= end) {
                        break;
                    }
                }
                else if (pos >= end) {
                    break;
                }
                found = (!FindReplaceUtility.MATCH_CASE_CHECKBOX.isSelected() && FindReplaceUtility.SEGMENT.array[pos] == oppFirst);
                found = (found ? found : (FindReplaceUtility.SEGMENT.array[pos] == first));
                if (found) {
                    pos += (backwards ? (-(pattern.length() - 1)) : 0);
                    for (int i = 0; found && i < pattern.length(); ++i) {
                        char c = pattern.charAt(i);
                        found = (FindReplaceUtility.SEGMENT.array[pos + i] == c);
                        if (!FindReplaceUtility.MATCH_CASE_CHECKBOX.isSelected() && !found) {
                            c = (Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c));
                            found = (FindReplaceUtility.SEGMENT.array[pos + i] == c);
                        }
                    }
                }
                if (!found) {
                    pos += (backwards ? -1 : 1);
                    if (pos != end || !wrapped) {
                        continue;
                    }
                    pos = (backwards ? FindReplaceUtility.SEGMENT.getEndIndex() : 0);
                    end = start;
                    wrapped = false;
                }
            }
            pos = (found ? pos : -1);
        }
        return pos;
    }
    
    private static void setListStrings() {
        final Object findObject = FindReplaceUtility.FIND_FIELD.getSelectedItem();
        final Object replaceObject = FindReplaceUtility.REPLACE_FIELD.isShowing() ? FindReplaceUtility.REPLACE_FIELD.getSelectedItem() : "";
        if (findObject != null && replaceObject != null) {
            boolean found = false;
            for (int i = 0; !found && i < FindReplaceUtility.FIND_FIELD.getItemCount(); found = FindReplaceUtility.FIND_FIELD.getItemAt(i).equals(findObject), ++i) {}
            if (!found) {
                FindReplaceUtility.FIND_FIELD.insertItemAt(findObject, 0);
                if (FindReplaceUtility.FIND_FIELD.getItemCount() > 7) {
                    FindReplaceUtility.FIND_FIELD.removeItemAt(7);
                }
            }
            if (FindReplaceUtility.REPLACE_FIELD.isShowing()) {
                found = false;
                for (int i = 0; !found && i < FindReplaceUtility.REPLACE_FIELD.getItemCount(); found = FindReplaceUtility.REPLACE_FIELD.getItemAt(i).equals(findObject), ++i) {}
                if (!found) {
                    FindReplaceUtility.REPLACE_FIELD.insertItemAt(replaceObject, 0);
                    if (FindReplaceUtility.REPLACE_FIELD.getItemCount() > 7) {
                        FindReplaceUtility.REPLACE_FIELD.removeItemAt(7);
                    }
                }
            }
        }
    }
    
    public static void showDialog() {
        showDialog(false);
    }
    
    public static void showDialog(final boolean isReplace) {
        final String title = isReplace ? "Replace" : "Find";
        FindReplaceUtility.FIND_REPLACE_DIALOG.setTitle(title);
        String text = FindReplaceUtility.textComponent.getSelectedText();
        if (text == null) {
            text = "";
        }
        FindReplaceUtility.FIND_FIELD.getEditor().setItem(text);
        FindReplaceUtility.FIND_FIELD.getEditor().selectAll();
        FindReplaceUtility.REPLACE_PANEL.setVisible(isReplace);
        FindReplaceUtility.REPLACE_ALL_BUTTON.setVisible(isReplace);
        FindReplaceUtility.CLOSE_BUTTON.setVisible(isReplace);
        final Action action = isReplace ? FindReplaceUtility.REPLACE_ACTION : FindReplaceUtility.CLOSE_ACTION;
        FindReplaceUtility.REPLACE_BUTTON.setAction(action);
        FindReplaceUtility.REPLACE_BUTTON.setPreferredSize(null);
        final Dimension d = isReplace ? FindReplaceUtility.REPLACE_ALL_BUTTON.getPreferredSize() : FindReplaceUtility.REPLACE_BUTTON.getPreferredSize();
        FindReplaceUtility.FIND_BUTTON.setPreferredSize(d);
        FindReplaceUtility.REPLACE_BUTTON.setPreferredSize(d);
        FindReplaceUtility.CLOSE_BUTTON.setPreferredSize(d);
        FindReplaceUtility.FIND_REPLACE_DIALOG.invalidate();
        FindReplaceUtility.FIND_REPLACE_DIALOG.repaint();
        FindReplaceUtility.FIND_REPLACE_DIALOG.pack();
        final Frame[] frames = Frame.getFrames();
        for (int i = 0; i < frames.length; ++i) {
            if (frames[i].isFocused()) {
                FindReplaceUtility.FIND_REPLACE_DIALOG.setLocationRelativeTo(frames[i]);
            }
        }
        FindReplaceUtility.FIND_REPLACE_DIALOG.setVisible(true);
        FindReplaceUtility.FIND_FIELD.requestFocusInWindow();
    }
    
    public static void unregisterTextComponent(final JTextComponent textComponent) {
        textComponent.removeFocusListener(FindReplaceUtility.TEXT_FOCUS_LISTENER);
    }
    
    public static void dispose() {
        FindReplaceUtility.FIND_REPLACE_DIALOG.dispose();
    }
    
    static {
        FIND_ACTION = new FindAction();
        FIND_REPLACE_DIALOG = new JDialog();
        TEXT_FIELD_PANEL = new JPanel(new GridLayout(2, 1));
        ENTRY_PANEL = new JPanel();
        FIND_PANEL = new JPanel();
        FIND_LABEL = new JLabel("Find What:    ");
        FIND_FIELD = new JComboBox();
        REPLACE_PANEL = new JPanel();
        REPLACE_LABEL = new JLabel("Replace With:");
        REPLACE_FIELD = new JComboBox();
        BUTTON_PANEL = new JPanel();
        FIND_BUTTON = new JButton();
        REPLACE_BUTTON = new JButton();
        REPLACE_ALL_BUTTON = new JButton();
        CLOSE_BUTTON = new JButton();
        CLOSE_ACTION = new CloseAction();
        REPLACE_ACTION = new ReplaceAction();
        CHECK_BOX_PANEL = new JPanel(new GridLayout(3, 1));
        MATCH_CASE_CHECKBOX = new JCheckBox("Match Case      ");
        IS_BACKWARDS_CHECKBOX = new JCheckBox("Search Backwards");
        WRAP_SEARCH_CHECKBOX = new JCheckBox("Wrap Search     ");
        EVENT_LISTENER_LIST = new EventListenerList();
        SEGMENT = new Segment();
        TEXT_FOCUS_LISTENER = new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent fe) {
                FindReplaceUtility.textComponent = (JTextComponent)fe.getSource();
                FindReplaceUtility.attributeSet = FindReplaceUtility.textComponent.getDocument().getDefaultRootElement().getAttributes();
            }
        };
        FindReplaceUtility.FIND_REPLACE_DIALOG.setResizable(false);
        FindReplaceUtility.FIND_REPLACE_DIALOG.setDefaultCloseOperation(2);
        KeyStroke.getKeyStroke("enter");
        final KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent ke) {
                if (ke.getKeyChar() == '\n') {
                    FindReplaceUtility.FIND_BUTTON.doClick();
                }
            }
        };
        FindReplaceUtility.FIND_PANEL.setLayout(new FlowLayout(2));
        FindReplaceUtility.FIND_PANEL.add(FindReplaceUtility.FIND_LABEL);
        FindReplaceUtility.FIND_PANEL.add(FindReplaceUtility.FIND_FIELD);
        FindReplaceUtility.FIND_FIELD.addItem("");
        FindReplaceUtility.FIND_FIELD.setEditable(true);
        FindReplaceUtility.FIND_FIELD.getEditor().getEditorComponent().addKeyListener(keyAdapter);
        Dimension d = FindReplaceUtility.FIND_FIELD.getPreferredSize();
        d.width = 225;
        FindReplaceUtility.FIND_FIELD.setPreferredSize(d);
        FindReplaceUtility.REPLACE_PANEL.add(FindReplaceUtility.REPLACE_LABEL);
        FindReplaceUtility.REPLACE_PANEL.add(FindReplaceUtility.REPLACE_FIELD);
        FindReplaceUtility.REPLACE_FIELD.setEditable(true);
        FindReplaceUtility.REPLACE_FIELD.getEditor().getEditorComponent().addKeyListener(keyAdapter);
        FindReplaceUtility.REPLACE_FIELD.setPreferredSize(d);
        FindReplaceUtility.TEXT_FIELD_PANEL.setLayout(new BoxLayout(FindReplaceUtility.TEXT_FIELD_PANEL, 1));
        FindReplaceUtility.TEXT_FIELD_PANEL.add(FindReplaceUtility.FIND_PANEL);
        FindReplaceUtility.TEXT_FIELD_PANEL.add(FindReplaceUtility.REPLACE_PANEL);
        FindReplaceUtility.ENTRY_PANEL.add(FindReplaceUtility.TEXT_FIELD_PANEL);
        FindReplaceUtility.FIND_REPLACE_DIALOG.getContentPane().add(FindReplaceUtility.ENTRY_PANEL, "West");
        FindReplaceUtility.CHECK_BOX_PANEL.add(FindReplaceUtility.MATCH_CASE_CHECKBOX);
        FindReplaceUtility.CHECK_BOX_PANEL.add(FindReplaceUtility.IS_BACKWARDS_CHECKBOX);
        FindReplaceUtility.CHECK_BOX_PANEL.add(FindReplaceUtility.WRAP_SEARCH_CHECKBOX);
        FindReplaceUtility.ENTRY_PANEL.add(FindReplaceUtility.CHECK_BOX_PANEL);
        FindReplaceUtility.ENTRY_PANEL.setLayout(new BoxLayout(FindReplaceUtility.ENTRY_PANEL, 1));
        FindReplaceUtility.REPLACE_ALL_BUTTON.setAction(new ReplaceAllAction());
        FindReplaceUtility.REPLACE_ALL_BUTTON.setHorizontalAlignment(0);
        d = FindReplaceUtility.REPLACE_ALL_BUTTON.getPreferredSize();
        FindReplaceUtility.BUTTON_PANEL.setLayout(new BoxLayout(FindReplaceUtility.BUTTON_PANEL, 1));
        FindReplaceUtility.FIND_BUTTON.setAction(FindReplaceUtility.FIND_ACTION);
        FindReplaceUtility.FIND_BUTTON.setPreferredSize(d);
        FindReplaceUtility.FIND_BUTTON.setHorizontalAlignment(0);
        JPanel panel = new JPanel();
        panel.add(FindReplaceUtility.FIND_BUTTON);
        FindReplaceUtility.BUTTON_PANEL.add(panel);
        FindReplaceUtility.FIND_REPLACE_DIALOG.getRootPane().setDefaultButton(FindReplaceUtility.FIND_BUTTON);
        FindReplaceUtility.REPLACE_BUTTON.setAction(FindReplaceUtility.REPLACE_ACTION);
        FindReplaceUtility.REPLACE_BUTTON.setPreferredSize(d);
        FindReplaceUtility.REPLACE_BUTTON.setHorizontalAlignment(0);
        panel = new JPanel();
        panel.add(FindReplaceUtility.REPLACE_BUTTON);
        FindReplaceUtility.BUTTON_PANEL.add(panel);
        panel = new JPanel();
        panel.add(FindReplaceUtility.REPLACE_ALL_BUTTON);
        FindReplaceUtility.BUTTON_PANEL.add(panel);
        FindReplaceUtility.CLOSE_BUTTON.setAction(FindReplaceUtility.CLOSE_ACTION);
        FindReplaceUtility.CLOSE_BUTTON.setPreferredSize(d);
        FindReplaceUtility.CLOSE_BUTTON.setHorizontalAlignment(0);
        panel = new JPanel();
        panel.add(FindReplaceUtility.CLOSE_BUTTON);
        FindReplaceUtility.BUTTON_PANEL.add(panel);
        FindReplaceUtility.FIND_REPLACE_DIALOG.getContentPane().add(FindReplaceUtility.BUTTON_PANEL);
        final KeyStroke stroke = (KeyStroke)FindReplaceUtility.CLOSE_ACTION.getValue("AcceleratorKey");
        final JRootPane rPane = FindReplaceUtility.FIND_REPLACE_DIALOG.getRootPane();
        rPane.getInputMap(2).put(stroke, "exit");
        rPane.getActionMap().put("exit", FindReplaceUtility.CLOSE_ACTION);
    }
    
    private static class FindAction extends AbstractAction
    {
        public FindAction() {
            this.putValue("Name", "Find");
            this.putValue("ActionCommandKey", "Find");
            this.putValue("MnemonicKey", 70);
        }
        
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.lastAction = "Find";
            FindReplaceUtility.findReplaceCount = 0;
            if (!FindReplaceUtility.FIND_REPLACE_DIALOG.isVisible() || FindReplaceUtility.FIND_REPLACE_DIALOG.getTitle().equals("Find")) {}
            int pos = (FindReplaceUtility.textComponent.getSelectedText() == null) ? FindReplaceUtility.textComponent.getCaretPosition() : FindReplaceUtility.textComponent.getSelectionStart();
            final boolean reverse = (ae.getModifiers() & 0x1) != 0x0;
            pos = findNext(reverse, pos);
            if (pos > -1) {
                final String pattern = (String)FindReplaceUtility.FIND_FIELD.getSelectedItem();
                FindReplaceUtility.textComponent.select(pos, pos + pattern.length());
                FindReplaceUtility.findReplaceCount = 1;
            }
            setListStrings();
            fireTextEvent();
        }
    }
    
    private static class ReplaceAction extends AbstractAction
    {
        public ReplaceAction() {
            this.putValue("Name", "Replace");
            this.putValue("ActionCommandKey", "Replace");
            this.putValue("MnemonicKey", 82);
        }
        
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.lastAction = ae.getActionCommand();
            FindReplaceUtility.findReplaceCount = 0;
            int pos = (FindReplaceUtility.textComponent.getSelectedText() == null) ? FindReplaceUtility.textComponent.getCaretPosition() : FindReplaceUtility.textComponent.getSelectionStart();
            pos = findNext(false, pos - 1);
            if (pos > -1) {
                final String find = (String)FindReplaceUtility.FIND_FIELD.getSelectedItem();
                String replace = (String)FindReplaceUtility.REPLACE_FIELD.getSelectedItem();
                replace = ((replace == null) ? "" : replace);
                final Document doc = FindReplaceUtility.textComponent.getDocument();
                try {
                    doc.remove(pos, find.length());
                    doc.insertString(pos, replace, FindReplaceUtility.attributeSet);
                    final int last = pos;
                    pos = findNext(false, pos);
                    if (pos > -1) {
                        FindReplaceUtility.textComponent.select(pos, pos + find.length());
                    }
                    else {
                        FindReplaceUtility.textComponent.setCaretPosition(last + replace.length());
                    }
                }
                catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
                FindReplaceUtility.findReplaceCount = 1;
            }
            setListStrings();
            fireTextEvent();
        }
    }
    
    private static class ReplaceAllAction extends AbstractAction
    {
        public ReplaceAllAction() {
            this.putValue("Name", "Replace All");
            this.putValue("ActionCommandKey", "Replace All");
            this.putValue("MnemonicKey", 65);
        }
        
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.lastAction = ae.getActionCommand();
            FindReplaceUtility.findReplaceCount = 0;
            int last = (FindReplaceUtility.textComponent.getSelectedText() == null) ? FindReplaceUtility.textComponent.getCaretPosition() : FindReplaceUtility.textComponent.getSelectionStart();
            int pos = findNext(false, last - 1);
            final String find = (String)FindReplaceUtility.FIND_FIELD.getSelectedItem();
            String replace = (String)FindReplaceUtility.REPLACE_FIELD.getSelectedItem();
            replace = ((replace == null) ? "" : replace);
            while (pos > -1) {
                final Document doc = FindReplaceUtility.textComponent.getDocument();
                try {
                    doc.remove(pos, find.length());
                    doc.insertString(pos, replace, FindReplaceUtility.attributeSet);
                    last = pos;
                    pos = findNext(false, pos);
                }
                catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
                FindReplaceUtility.findReplaceCount++;
            }
            if (pos > -1) {
                FindReplaceUtility.textComponent.select(pos, pos + find.length());
            }
            else {
                FindReplaceUtility.textComponent.setCaretPosition(last + replace.length());
            }
            setListStrings();
            fireTextEvent();
        }
    }
    
    private static class CloseAction extends AbstractAction
    {
        public CloseAction() {
            this.putValue("Name", "Close");
            this.putValue("ActionCommandKey", "Close");
            this.putValue("MnemonicKey", 67);
            this.putValue("AcceleratorKey", KeyStroke.getKeyStroke("ESCAPE"));
        }
        
        public void actionPerformed(final ActionEvent ae) {
            FindReplaceUtility.FIND_REPLACE_DIALOG.dispose();
        }
    }
}
