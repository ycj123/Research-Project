// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.LinkedList;
import groovy.lang.GroovyClassLoader;
import java.util.Set;
import groovy.lang.Script;
import groovy.util.Factory;
import groovy.lang.MetaClass;
import groovy.lang.GString;
import javax.swing.JComponent;
import java.util.List;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.ArrayList;
import javax.swing.LookAndFeel;
import javax.swing.KeyStroke;
import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.runtime.MethodClosure;
import java.util.Map;
import groovy.lang.Reference;
import groovy.swing.factory.RendererFactory;
import groovy.swing.factory.BindFactory;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Random;
import java.util.logging.Logger;
import groovy.util.FactoryBuilderSupport;

public class SwingBuilder extends FactoryBuilderSupport
{
    private static final Logger LOG;
    private static boolean headless;
    public static final String DELEGATE_PROPERTY_OBJECT_ID;
    public static final String DEFAULT_DELEGATE_PROPERTY_OBJECT_ID;
    private static final Random random;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205610;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$SpinnerListModel;
    private static /* synthetic */ Class $class$groovy$swing$factory$TextArgWidgetFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TRFactory;
    private static /* synthetic */ Class $class$javax$swing$JTree;
    private static /* synthetic */ Class $class$javax$swing$JPopupMenu;
    private static /* synthetic */ Class $class$java$lang$Math;
    private static /* synthetic */ Class $class$javax$swing$JLayeredPane;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$groovy$swing$factory$FormattedTextFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$ImageIconFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$ActionFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$InternalFrameFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$HGlueFactory;
    private static /* synthetic */ Class $class$javax$swing$JToolBar;
    private static /* synthetic */ Class $class$javax$swing$JLabel;
    private static /* synthetic */ Class $class$javax$swing$JPasswordField;
    private static /* synthetic */ Class $class$groovy$swing$factory$VGlueFactory;
    private static /* synthetic */ Class $class$javax$swing$JApplet;
    private static /* synthetic */ Class $class$javax$swing$JComponent;
    private static /* synthetic */ Class $class$groovy$swing$factory$FrameFactory;
    private static /* synthetic */ Class $class$javax$swing$JRadioButton;
    private static /* synthetic */ Class $class$javax$swing$SwingUtilities;
    private static /* synthetic */ Class $class$javax$swing$JPanel;
    private static /* synthetic */ Class $class$groovy$swing$factory$BoxLayoutFactory;
    private static /* synthetic */ Class $class$javax$swing$JProgressBar;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    private static /* synthetic */ Class $class$javax$swing$SpringLayout;
    private static /* synthetic */ Class $class$java$awt$FlowLayout;
    private static /* synthetic */ Class $class$groovy$swing$factory$ListFactory;
    private static /* synthetic */ Class $class$javax$swing$SpinnerNumberModel;
    private static /* synthetic */ Class $class$javax$swing$JScrollBar;
    private static /* synthetic */ Class $class$groovy$swing$factory$PropertyColumnFactory;
    private static /* synthetic */ Class $class$javax$swing$JMenuBar;
    private static /* synthetic */ Class $class$groovy$swing$factory$VStrutFactory;
    private static /* synthetic */ Class $class$javax$swing$JSpinner;
    private static /* synthetic */ Class $class$groovy$swing$factory$RigidAreaFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableLayoutFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$RendererUpdateFactory;
    private static /* synthetic */ Class $class$java$util$Random;
    private static /* synthetic */ Class $class$java$awt$GridLayout;
    private static /* synthetic */ Class $class$javax$swing$JRadioButtonMenuItem;
    private static /* synthetic */ Class $class$java$awt$GraphicsEnvironment;
    private static /* synthetic */ Class $class$javax$swing$JWindow;
    private static /* synthetic */ Class $class$groovy$swing$factory$ColumnModelFactory;
    private static /* synthetic */ Class $class$javax$swing$JDialog;
    private static /* synthetic */ Class $class$javax$swing$JMenuItem;
    private static /* synthetic */ Class $class$groovy$swing$factory$LayoutFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$DialogFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$ColumnFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$VBoxFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindGroupFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$RendererFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$LineBorderFactory;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$javax$swing$JEditorPane;
    private static /* synthetic */ Class $class$groovy$swing$factory$CompoundBorderFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TDFactory;
    private static /* synthetic */ Class $class$javax$swing$LookAndFeel;
    private static /* synthetic */ Class $class$javax$swing$OverlayLayout;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$factory$EmptyBorderFactory;
    private static /* synthetic */ Class $class$javax$swing$JOptionPane;
    private static /* synthetic */ Class $class$javax$swing$JTable;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$MatteBorderFactory;
    private static /* synthetic */ Class $class$java$awt$CardLayout;
    private static /* synthetic */ Class $class$javax$swing$JTabbedPane;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComponentFactory;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$swing$factory$CellEditorFactory;
    private static /* synthetic */ Class $class$javax$swing$KeyStroke;
    private static /* synthetic */ Class $class$javax$swing$table$TableColumn;
    private static /* synthetic */ Class $class$groovy$swing$factory$ScrollPaneFactory;
    private static /* synthetic */ Class $class$javax$swing$JScrollPane;
    private static /* synthetic */ Class $class$javax$swing$JSlider;
    private static /* synthetic */ Class $class$groovy$swing$factory$HBoxFactory;
    private static /* synthetic */ Class $class$java$lang$Thread;
    private static /* synthetic */ Class $class$javax$swing$JTextField;
    private static /* synthetic */ Class $class$groovy$lang$MissingMethodException;
    private static /* synthetic */ Class $class$javax$swing$JViewport;
    private static /* synthetic */ Class $class$groovy$swing$factory$BoxFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$BevelBorderFactory;
    private static /* synthetic */ Class $class$java$awt$Toolkit;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$groovy$swing$factory$CellEditorGetValueFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$GlueFactory;
    private static /* synthetic */ Class $class$java$awt$Component;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableModelFactory;
    private static /* synthetic */ Class $class$java$awt$GridBagConstraints;
    private static /* synthetic */ Class $class$groovy$swing$factory$SeparatorFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TitledBorderFactory;
    private static /* synthetic */ Class $class$javax$swing$JTextArea;
    private static /* synthetic */ Class $class$javax$swing$JDesktopPane;
    private static /* synthetic */ Class $class$javax$swing$JButton;
    private static /* synthetic */ Class $class$groovy$swing$factory$ClosureColumnFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$ComboBoxFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$MapFactory;
    private static /* synthetic */ Class $class$javax$swing$border$BevelBorder;
    private static /* synthetic */ Class $class$javax$swing$JFrame;
    private static /* synthetic */ Class $class$javax$swing$SpinnerDateModel;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$groovy$swing$factory$EtchedBorderFactory;
    private static /* synthetic */ Class $class$javax$swing$border$EtchedBorder;
    private static /* synthetic */ Class $class$groovy$swing$factory$WidgetFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$GridBagFactory;
    private static /* synthetic */ Class $class$javax$swing$JMenu;
    private static /* synthetic */ Class $class$javax$swing$JToggleButton;
    private static /* synthetic */ Class $class$javax$swing$JCheckBox;
    private static /* synthetic */ Class $class$javax$swing$JFileChooser;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindProxyFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$HStrutFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$TabbedPaneFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$WindowFactory;
    private static /* synthetic */ Class $class$javax$swing$JTextPane;
    private static /* synthetic */ Class $class$javax$swing$DefaultBoundedRangeModel;
    private static /* synthetic */ Class $class$javax$swing$JColorChooser;
    private static /* synthetic */ Class $class$java$awt$BorderLayout;
    private static /* synthetic */ Class $class$groovy$swing$LookAndFeelHelper;
    private static /* synthetic */ Class $class$groovy$swing$factory$CollectionFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$RichActionWidgetFactory;
    private static /* synthetic */ Class $class$javax$swing$JCheckBoxMenuItem;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    private static /* synthetic */ Class $class$groovy$swing$factory$SplitPaneFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$CellEditorPrepareFactory;
    private static /* synthetic */ Class $class$groovy$swing$factory$ButtonGroupFactory;
    private static /* synthetic */ Class $class$java$awt$LayoutManager;
    
    public SwingBuilder(final boolean init) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = new Object[] { null });
        arguments[0] = DefaultTypeTransformation.box(init);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$groovy$util$FactoryBuilderSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (Object[])arguments[0]);
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                break;
            }
            case 1: {
                super((Closure)array[0]);
                break;
            }
            case 2: {
                super(DefaultTypeTransformation.booleanUnbox(array2[0]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        SwingBuilder.headless = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($get$$class$java$awt$GraphicsEnvironment()));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[1].callConstructor($get$$class$java$util$LinkedList()), $get$$class$groovy$swing$SwingBuilder(), this, "containingWindows");
        $getCallSiteArray[2].call(this, SwingBuilder.DELEGATE_PROPERTY_OBJECT_ID, SwingBuilder.DEFAULT_DELEGATE_PROPERTY_OBJECT_ID);
    }
    
    public SwingBuilder() {
        $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[] { ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE) };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$groovy$swing$SwingBuilder());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this(DefaultTypeTransformation.booleanUnbox(array[0]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Object registerSupportNodes() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[3].callCurrent(this, "action", $getCallSiteArray[4].callConstructor($get$$class$groovy$swing$factory$ActionFactory()));
        $getCallSiteArray[5].callCurrent(this, "actions", $getCallSiteArray[6].callConstructor($get$$class$groovy$swing$factory$CollectionFactory()));
        $getCallSiteArray[7].callCurrent(this, "map", $getCallSiteArray[8].callConstructor($get$$class$groovy$swing$factory$MapFactory()));
        $getCallSiteArray[9].callCurrent(this, "imageIcon", $getCallSiteArray[10].callConstructor($get$$class$groovy$swing$factory$ImageIconFactory()));
        $getCallSiteArray[11].callCurrent(this, "buttonGroup", $getCallSiteArray[12].callConstructor($get$$class$groovy$swing$factory$ButtonGroupFactory()));
        $getCallSiteArray[13].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer($get$$class$groovy$swing$factory$ButtonGroupFactory(), "buttonGroupAttributeDelegate"));
        $getCallSiteArray[14].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer($get$$class$groovy$swing$SwingBuilder(), "objectIDAttributeDelegate"));
        $getCallSiteArray[15].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer($get$$class$groovy$swing$SwingBuilder(), "clientPropertyAttributeDelegate"));
        $getCallSiteArray[16].callCurrent(this, "noparent", $getCallSiteArray[17].callConstructor($get$$class$groovy$swing$factory$CollectionFactory()));
        $getCallSiteArray[18].callCurrent(this, "keyStrokeAction", ScriptBytecodeAdapter.getMethodPointer(this, "createKeyStrokeAction"));
        return $getCallSiteArray[19].callCurrent(this, "shortcut", ScriptBytecodeAdapter.getMethodPointer(this, "shortcut"));
    }
    
    public Object registerBinding() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final BindFactory bindFactory = (BindFactory)$getCallSiteArray[20].callConstructor($get$$class$groovy$swing$factory$BindFactory());
        $getCallSiteArray[21].callCurrent(this, "bind", bindFactory);
        $getCallSiteArray[22].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer(bindFactory, "bindingAttributeDelegate"));
        $getCallSiteArray[23].callCurrent(this, "bindProxy", $getCallSiteArray[24].callConstructor($get$$class$groovy$swing$factory$BindProxyFactory()));
        return $getCallSiteArray[25].callCurrent(this, "bindGroup", $getCallSiteArray[26].callConstructor($get$$class$groovy$swing$factory$BindGroupFactory()));
    }
    
    public Object registerPassThruNodes() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[27].callCurrent(this, "widget", $getCallSiteArray[28].callConstructor($get$$class$groovy$swing$factory$WidgetFactory(), $get$$class$java$awt$Component(), Boolean.TRUE));
        $getCallSiteArray[29].callCurrent(this, "container", $getCallSiteArray[30].callConstructor($get$$class$groovy$swing$factory$WidgetFactory(), $get$$class$java$awt$Component(), Boolean.FALSE));
        return $getCallSiteArray[31].callCurrent(this, "bean", $getCallSiteArray[32].callConstructor($get$$class$groovy$swing$factory$WidgetFactory(), $get$$class$java$lang$Object(), Boolean.TRUE));
    }
    
    public Object registerWindows() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[33].callCurrent(this, "dialog", $getCallSiteArray[34].callConstructor($get$$class$groovy$swing$factory$DialogFactory()));
        $getCallSiteArray[35].callCurrent(this, "fileChooser", $get$$class$javax$swing$JFileChooser());
        $getCallSiteArray[36].callCurrent(this, "frame", $getCallSiteArray[37].callConstructor($get$$class$groovy$swing$factory$FrameFactory()));
        $getCallSiteArray[38].callCurrent(this, "optionPane", $get$$class$javax$swing$JOptionPane());
        return $getCallSiteArray[39].callCurrent(this, "window", $getCallSiteArray[40].callConstructor($get$$class$groovy$swing$factory$WindowFactory()));
    }
    
    public Object registerActionButtonWidgets() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[41].callCurrent(this, "button", $getCallSiteArray[42].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JButton()));
        $getCallSiteArray[43].callCurrent(this, "checkBox", $getCallSiteArray[44].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JCheckBox()));
        $getCallSiteArray[45].callCurrent(this, "checkBoxMenuItem", $getCallSiteArray[46].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JCheckBoxMenuItem()));
        $getCallSiteArray[47].callCurrent(this, "menuItem", $getCallSiteArray[48].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JMenuItem()));
        $getCallSiteArray[49].callCurrent(this, "radioButton", $getCallSiteArray[50].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JRadioButton()));
        $getCallSiteArray[51].callCurrent(this, "radioButtonMenuItem", $getCallSiteArray[52].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JRadioButtonMenuItem()));
        return $getCallSiteArray[53].callCurrent(this, "toggleButton", $getCallSiteArray[54].callConstructor($get$$class$groovy$swing$factory$RichActionWidgetFactory(), $get$$class$javax$swing$JToggleButton()));
    }
    
    public Object registerTextWidgets() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[55].callCurrent(this, "editorPane", $getCallSiteArray[56].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JEditorPane()));
        $getCallSiteArray[57].callCurrent(this, "label", $getCallSiteArray[58].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JLabel()));
        $getCallSiteArray[59].callCurrent(this, "passwordField", $getCallSiteArray[60].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JPasswordField()));
        $getCallSiteArray[61].callCurrent(this, "textArea", $getCallSiteArray[62].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JTextArea()));
        $getCallSiteArray[63].callCurrent(this, "textField", $getCallSiteArray[64].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JTextField()));
        $getCallSiteArray[65].callCurrent(this, "formattedTextField", $getCallSiteArray[66].callConstructor($get$$class$groovy$swing$factory$FormattedTextFactory()));
        return $getCallSiteArray[67].callCurrent(this, "textPane", $getCallSiteArray[68].callConstructor($get$$class$groovy$swing$factory$TextArgWidgetFactory(), $get$$class$javax$swing$JTextPane()));
    }
    
    public Object registerMDIWidgets() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[69].callCurrent(this, "desktopPane", $get$$class$javax$swing$JDesktopPane());
        return $getCallSiteArray[70].callCurrent(this, "internalFrame", $getCallSiteArray[71].callConstructor($get$$class$groovy$swing$factory$InternalFrameFactory()));
    }
    
    public Object registerBasicWidgets() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[72].callCurrent(this, "colorChooser", $get$$class$javax$swing$JColorChooser());
        $getCallSiteArray[73].callCurrent(this, "comboBox", $getCallSiteArray[74].callConstructor($get$$class$groovy$swing$factory$ComboBoxFactory()));
        $getCallSiteArray[75].callCurrent(this, "list", $getCallSiteArray[76].callConstructor($get$$class$groovy$swing$factory$ListFactory()));
        $getCallSiteArray[77].callCurrent(this, "progressBar", $get$$class$javax$swing$JProgressBar());
        $getCallSiteArray[78].callCurrent(this, "separator", $getCallSiteArray[79].callConstructor($get$$class$groovy$swing$factory$SeparatorFactory()));
        $getCallSiteArray[80].callCurrent(this, "scrollBar", $get$$class$javax$swing$JScrollBar());
        $getCallSiteArray[81].callCurrent(this, "slider", $get$$class$javax$swing$JSlider());
        $getCallSiteArray[82].callCurrent(this, "spinner", $get$$class$javax$swing$JSpinner());
        return $getCallSiteArray[83].callCurrent(this, "tree", $get$$class$javax$swing$JTree());
    }
    
    public Object registerMenuWidgets() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[84].callCurrent(this, "menu", $get$$class$javax$swing$JMenu());
        $getCallSiteArray[85].callCurrent(this, "menuBar", $get$$class$javax$swing$JMenuBar());
        return $getCallSiteArray[86].callCurrent(this, "popupMenu", $get$$class$javax$swing$JPopupMenu());
    }
    
    public Object registerContainers() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[87].callCurrent(this, "panel", $get$$class$javax$swing$JPanel());
        $getCallSiteArray[88].callCurrent(this, "scrollPane", $getCallSiteArray[89].callConstructor($get$$class$groovy$swing$factory$ScrollPaneFactory()));
        $getCallSiteArray[90].callCurrent(this, "splitPane", $getCallSiteArray[91].callConstructor($get$$class$groovy$swing$factory$SplitPaneFactory()));
        $getCallSiteArray[92].callCurrent(this, "tabbedPane", $getCallSiteArray[93].callConstructor($get$$class$groovy$swing$factory$TabbedPaneFactory(), $get$$class$javax$swing$JTabbedPane()));
        $getCallSiteArray[94].callCurrent(this, "toolBar", $get$$class$javax$swing$JToolBar());
        $getCallSiteArray[95].callCurrent(this, "viewport", $get$$class$javax$swing$JViewport());
        return $getCallSiteArray[96].callCurrent(this, "layeredPane", $get$$class$javax$swing$JLayeredPane());
    }
    
    public Object registerDataModels() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[97].callCurrent(this, "boundedRangeModel", $get$$class$javax$swing$DefaultBoundedRangeModel());
        $getCallSiteArray[98].callCurrent(this, "spinnerDateModel", $get$$class$javax$swing$SpinnerDateModel());
        $getCallSiteArray[99].callCurrent(this, "spinnerListModel", $get$$class$javax$swing$SpinnerListModel());
        return $getCallSiteArray[100].callCurrent(this, "spinnerNumberModel", $get$$class$javax$swing$SpinnerNumberModel());
    }
    
    public Object registerTableComponents() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[101].callCurrent(this, "table", $getCallSiteArray[102].callConstructor($get$$class$groovy$swing$factory$TableFactory()));
        $getCallSiteArray[103].callCurrent(this, "tableColumn", $get$$class$javax$swing$table$TableColumn());
        $getCallSiteArray[104].callCurrent(this, "tableModel", $getCallSiteArray[105].callConstructor($get$$class$groovy$swing$factory$TableModelFactory()));
        $getCallSiteArray[106].callCurrent(this, "propertyColumn", $getCallSiteArray[107].callConstructor($get$$class$groovy$swing$factory$PropertyColumnFactory()));
        $getCallSiteArray[108].callCurrent(this, "closureColumn", $getCallSiteArray[109].callConstructor($get$$class$groovy$swing$factory$ClosureColumnFactory()));
        $getCallSiteArray[110].callCurrent(this, "columnModel", $getCallSiteArray[111].callConstructor($get$$class$groovy$swing$factory$ColumnModelFactory()));
        return $getCallSiteArray[112].callCurrent(this, "column", $getCallSiteArray[113].callConstructor($get$$class$groovy$swing$factory$ColumnFactory()));
    }
    
    public Object registerBasicLayouts() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[114].callCurrent(this, "borderLayout", $getCallSiteArray[115].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$java$awt$BorderLayout()));
        $getCallSiteArray[116].callCurrent(this, "cardLayout", $getCallSiteArray[117].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$java$awt$CardLayout()));
        $getCallSiteArray[118].callCurrent(this, "flowLayout", $getCallSiteArray[119].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$java$awt$FlowLayout()));
        $getCallSiteArray[120].callCurrent(this, "gridLayout", $getCallSiteArray[121].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$java$awt$GridLayout()));
        $getCallSiteArray[122].callCurrent(this, "overlayLayout", $getCallSiteArray[123].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$javax$swing$OverlayLayout()));
        $getCallSiteArray[124].callCurrent(this, "springLayout", $getCallSiteArray[125].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), $get$$class$javax$swing$SpringLayout()));
        $getCallSiteArray[126].callCurrent(this, "gridBagLayout", $getCallSiteArray[127].callConstructor($get$$class$groovy$swing$factory$GridBagFactory()));
        $getCallSiteArray[128].callCurrent(this, "gridBagConstraints", $get$$class$java$awt$GridBagConstraints());
        $getCallSiteArray[129].callCurrent(this, "gbc", $get$$class$java$awt$GridBagConstraints());
        $getCallSiteArray[130].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer($get$$class$groovy$swing$factory$GridBagFactory(), "processGridBagConstraintsAttributes"));
        return $getCallSiteArray[131].callCurrent(this, ScriptBytecodeAdapter.getMethodPointer($get$$class$groovy$swing$factory$LayoutFactory(), "constraintsAttributeDelegate"));
    }
    
    public Object registerBoxLayout() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[132].callCurrent(this, "boxLayout", $getCallSiteArray[133].callConstructor($get$$class$groovy$swing$factory$BoxLayoutFactory()));
        $getCallSiteArray[134].callCurrent(this, "box", $getCallSiteArray[135].callConstructor($get$$class$groovy$swing$factory$BoxFactory()));
        $getCallSiteArray[136].callCurrent(this, "hbox", $getCallSiteArray[137].callConstructor($get$$class$groovy$swing$factory$HBoxFactory()));
        $getCallSiteArray[138].callCurrent(this, "hglue", $getCallSiteArray[139].callConstructor($get$$class$groovy$swing$factory$HGlueFactory()));
        $getCallSiteArray[140].callCurrent(this, "hstrut", $getCallSiteArray[141].callConstructor($get$$class$groovy$swing$factory$HStrutFactory()));
        $getCallSiteArray[142].callCurrent(this, "vbox", $getCallSiteArray[143].callConstructor($get$$class$groovy$swing$factory$VBoxFactory()));
        $getCallSiteArray[144].callCurrent(this, "vglue", $getCallSiteArray[145].callConstructor($get$$class$groovy$swing$factory$VGlueFactory()));
        $getCallSiteArray[146].callCurrent(this, "vstrut", $getCallSiteArray[147].callConstructor($get$$class$groovy$swing$factory$VStrutFactory()));
        $getCallSiteArray[148].callCurrent(this, "glue", $getCallSiteArray[149].callConstructor($get$$class$groovy$swing$factory$GlueFactory()));
        return $getCallSiteArray[150].callCurrent(this, "rigidArea", $getCallSiteArray[151].callConstructor($get$$class$groovy$swing$factory$RigidAreaFactory()));
    }
    
    public Object registerTableLayout() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[152].callCurrent(this, "tableLayout", $getCallSiteArray[153].callConstructor($get$$class$groovy$swing$factory$TableLayoutFactory()));
        $getCallSiteArray[154].callCurrent(this, "tr", $getCallSiteArray[155].callConstructor($get$$class$groovy$swing$factory$TRFactory()));
        return $getCallSiteArray[156].callCurrent(this, "td", $getCallSiteArray[157].callConstructor($get$$class$groovy$swing$factory$TDFactory()));
    }
    
    public Object registerBorders() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[158].callCurrent(this, "lineBorder", $getCallSiteArray[159].callConstructor($get$$class$groovy$swing$factory$LineBorderFactory()));
        $getCallSiteArray[160].callCurrent(this, "loweredBevelBorder", $getCallSiteArray[161].callConstructor($get$$class$groovy$swing$factory$BevelBorderFactory(), $getCallSiteArray[162].callGetProperty($get$$class$javax$swing$border$BevelBorder())));
        $getCallSiteArray[163].callCurrent(this, "raisedBevelBorder", $getCallSiteArray[164].callConstructor($get$$class$groovy$swing$factory$BevelBorderFactory(), $getCallSiteArray[165].callGetProperty($get$$class$javax$swing$border$BevelBorder())));
        $getCallSiteArray[166].callCurrent(this, "etchedBorder", $getCallSiteArray[167].callConstructor($get$$class$groovy$swing$factory$EtchedBorderFactory(), $getCallSiteArray[168].callGetProperty($get$$class$javax$swing$border$EtchedBorder())));
        $getCallSiteArray[169].callCurrent(this, "loweredEtchedBorder", $getCallSiteArray[170].callConstructor($get$$class$groovy$swing$factory$EtchedBorderFactory(), $getCallSiteArray[171].callGetProperty($get$$class$javax$swing$border$EtchedBorder())));
        $getCallSiteArray[172].callCurrent(this, "raisedEtchedBorder", $getCallSiteArray[173].callConstructor($get$$class$groovy$swing$factory$EtchedBorderFactory(), $getCallSiteArray[174].callGetProperty($get$$class$javax$swing$border$EtchedBorder())));
        $getCallSiteArray[175].callCurrent(this, "titledBorder", $getCallSiteArray[176].callConstructor($get$$class$groovy$swing$factory$TitledBorderFactory()));
        $getCallSiteArray[177].callCurrent(this, "emptyBorder", $getCallSiteArray[178].callConstructor($get$$class$groovy$swing$factory$EmptyBorderFactory()));
        $getCallSiteArray[179].callCurrent(this, "compoundBorder", $getCallSiteArray[180].callConstructor($get$$class$groovy$swing$factory$CompoundBorderFactory()));
        return $getCallSiteArray[181].callCurrent(this, "matteBorder", $getCallSiteArray[182].callConstructor($get$$class$groovy$swing$factory$MatteBorderFactory()));
    }
    
    public Object registerRenderers() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final RendererFactory renderFactory = (RendererFactory)$getCallSiteArray[183].callConstructor($get$$class$groovy$swing$factory$RendererFactory());
        $getCallSiteArray[184].callCurrent(this, "tableCellRenderer", renderFactory);
        $getCallSiteArray[185].callCurrent(this, "listCellRenderer", renderFactory);
        $getCallSiteArray[186].callCurrent(this, "onRender", $getCallSiteArray[187].callConstructor($get$$class$groovy$swing$factory$RendererUpdateFactory()));
        $getCallSiteArray[188].callCurrent(this, "cellRenderer", renderFactory);
        return $getCallSiteArray[189].callCurrent(this, "headerRenderer", renderFactory);
    }
    
    public Object registerEditors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[190].callCurrent(this, "cellEditor", $getCallSiteArray[191].callConstructor($get$$class$groovy$swing$factory$CellEditorFactory()));
        $getCallSiteArray[192].callCurrent(this, "editorValue", $getCallSiteArray[193].callConstructor($get$$class$groovy$swing$factory$CellEditorGetValueFactory()));
        return $getCallSiteArray[194].callCurrent(this, "prepareEditor", $getCallSiteArray[195].callConstructor($get$$class$groovy$swing$factory$CellEditorPrepareFactory()));
    }
    
    public Object registerThreading() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[196].callCurrent(this, "edt", ScriptBytecodeAdapter.getMethodPointer(this, "edt"));
        $getCallSiteArray[197].callCurrent(this, "doOutside", ScriptBytecodeAdapter.getMethodPointer(this, "doOutside"));
        return $getCallSiteArray[198].callCurrent(this, "doLater", ScriptBytecodeAdapter.getMethodPointer(this, "doLater"));
    }
    
    @Override
    public void registerBeanFactory(final String nodeName, final String groupName, final Class klass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[199].call($get$$class$java$awt$LayoutManager(), klass))) {
            $getCallSiteArray[200].callCurrent(this, nodeName, groupName, $getCallSiteArray[201].callConstructor($get$$class$groovy$swing$factory$LayoutFactory(), klass));
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[202].call($get$$class$javax$swing$JScrollPane(), klass))) {
            $getCallSiteArray[203].callCurrent(this, nodeName, groupName, $getCallSiteArray[204].callConstructor($get$$class$groovy$swing$factory$ScrollPaneFactory(), klass));
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[205].call($get$$class$javax$swing$JTable(), klass))) {
            $getCallSiteArray[206].callCurrent(this, nodeName, groupName, $getCallSiteArray[207].callConstructor($get$$class$groovy$swing$factory$TableFactory(), klass));
        }
        else if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[208].call($get$$class$javax$swing$JComponent(), klass)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[209].call($get$$class$javax$swing$JApplet(), klass))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[210].call($get$$class$javax$swing$JDialog(), klass))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[211].call($get$$class$javax$swing$JFrame(), klass))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[212].call($get$$class$javax$swing$JWindow(), klass))) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[213].callCurrent(this, nodeName, groupName, $getCallSiteArray[214].callConstructor($get$$class$groovy$swing$factory$ComponentFactory(), klass));
        }
        else {
            ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$util$FactoryBuilderSupport(), this, "registerBeanFactory", new Object[] { nodeName, groupName, klass });
        }
    }
    
    public SwingBuilder edt(final Closure c) {
        final Closure c2 = (Closure)new Reference(c);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[215].call(((Reference<Object>)c2).get(), this);
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(SwingBuilder.headless)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[216].call($get$$class$javax$swing$SwingUtilities()))) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[217].call(((Reference<Object>)c2).get(), this);
        }
        else {
            final Map continuationData = (Map)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[218].callCurrent(this), $get$$class$java$util$Map()));
            try {
                try {
                    if (!(((Reference<Object>)c2).get() instanceof MethodClosure)) {
                        ((Reference<Closure>)c2).set((Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[219].call(((Reference<Object>)c2).get(), ScriptBytecodeAdapter.createList(new Object[] { this })), $get$$class$groovy$lang$Closure()));
                    }
                    $getCallSiteArray[220].call($get$$class$javax$swing$SwingUtilities(), new SwingBuilder$_edt_closure1(this, this, (Reference<Object>)c2, (Reference<Object>)continuationData));
                }
                catch (InterruptedException e) {
                    throw (Throwable)$getCallSiteArray[221].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), "interrupted swing interaction", e);
                }
                catch (InvocationTargetException e2) {
                    throw (Throwable)$getCallSiteArray[222].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), "exception in event dispatch thread", $getCallSiteArray[223].call(e2));
                }
                $getCallSiteArray[224].callCurrent(this, ((Reference)continuationData).get());
            }
            finally {
                $getCallSiteArray[225].callCurrent(this, ((Reference)continuationData).get());
            }
        }
        return (SwingBuilder)ScriptBytecodeAdapter.castToType(this, $get$$class$groovy$swing$SwingBuilder());
    }
    
    public SwingBuilder doLater(Closure c) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[226].call(c, this);
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(SwingBuilder.headless))) {
            $getCallSiteArray[227].call(c);
        }
        else {
            if (!(c instanceof MethodClosure)) {
                c = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[228].call(c, ScriptBytecodeAdapter.createList(new Object[] { this })), $get$$class$groovy$lang$Closure());
            }
            $getCallSiteArray[229].call($get$$class$javax$swing$SwingUtilities(), c);
        }
        return (SwingBuilder)ScriptBytecodeAdapter.castToType(this, $get$$class$groovy$swing$SwingBuilder());
    }
    
    public SwingBuilder doOutside(Closure c) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[230].call(c, this);
        if (!(c instanceof MethodClosure)) {
            c = (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[231].call(c, ScriptBytecodeAdapter.createList(new Object[] { this })), $get$$class$groovy$lang$Closure());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[232].call($get$$class$javax$swing$SwingUtilities()))) {
            $getCallSiteArray[233].call($get$$class$java$lang$Thread(), c);
        }
        else {
            $getCallSiteArray[234].call(c);
        }
        return (SwingBuilder)ScriptBytecodeAdapter.castToType(this, $get$$class$groovy$swing$SwingBuilder());
    }
    
    public static SwingBuilder edtBuilder(final Closure c) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final SwingBuilder builder = (SwingBuilder)$getCallSiteArray[235].callConstructor($get$$class$groovy$swing$SwingBuilder());
        return (SwingBuilder)ScriptBytecodeAdapter.castToType($getCallSiteArray[236].call(builder, c), $get$$class$groovy$swing$SwingBuilder());
    }
    
    @Deprecated
    public static SwingBuilder $static_methodMissing(final String method, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(method, "build") && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[237].callGetProperty(args), SwingBuilder.$const$0)) ? Boolean.TRUE : Boolean.FALSE) && $getCallSiteArray[238].call(args, SwingBuilder.$const$1) instanceof Closure) ? Boolean.TRUE : Boolean.FALSE)) {
            return (SwingBuilder)ScriptBytecodeAdapter.castToType($getCallSiteArray[239].callStatic($get$$class$groovy$swing$SwingBuilder(), $getCallSiteArray[240].call(args, SwingBuilder.$const$1)), $get$$class$groovy$swing$SwingBuilder());
        }
        throw (Throwable)$getCallSiteArray[241].callConstructor($get$$class$groovy$lang$MissingMethodException(), method, $get$$class$groovy$swing$SwingBuilder(), args, Boolean.TRUE);
    }
    
    public Object build(final Closure c) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[242].call(c, this);
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[243].call(c), $get$$class$java$lang$Object());
    }
    
    public KeyStroke shortcut(final Object key, final Object modifier) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray[244].call($get$$class$javax$swing$KeyStroke(), key, $getCallSiteArray[245].call($getCallSiteArray[246].call($getCallSiteArray[247].call($get$$class$java$awt$Toolkit())), modifier)), $get$$class$javax$swing$KeyStroke());
    }
    
    public KeyStroke shortcut(final String key, final Object modifier) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final KeyStroke ks = (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray[248].call($get$$class$javax$swing$KeyStroke(), key), $get$$class$javax$swing$KeyStroke());
        if (ScriptBytecodeAdapter.compareEqual(ks, null)) {
            return (KeyStroke)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$KeyStroke());
        }
        return (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray[249].call($get$$class$javax$swing$KeyStroke(), $getCallSiteArray[250].call(ks), $getCallSiteArray[251].call($getCallSiteArray[252].call($getCallSiteArray[253].call(ks), modifier), $getCallSiteArray[254].call($getCallSiteArray[255].call($get$$class$java$awt$Toolkit())))), $get$$class$javax$swing$KeyStroke());
    }
    
    public static LookAndFeel lookAndFeel(final Object laf, final Closure initCode) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[256].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createMap(new Object[0]), laf, initCode), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static LookAndFeel lookAndFeel(final Map attributes, final Object laf, final Closure initCode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray[257].call($getCallSiteArray[258].callGetProperty($get$$class$groovy$swing$LookAndFeelHelper()), laf, attributes, initCode), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static LookAndFeel lookAndFeel(final Object... lafs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[259].callGetProperty(lafs), SwingBuilder.$const$0)) {
            $getCallSiteArray[260].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createMap(new Object[0]), $getCallSiteArray[261].call(lafs, SwingBuilder.$const$1), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.asType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
        }
        Object laf = null;
        final Object call = $getCallSiteArray[262].call(lafs);
        while (((Iterator)call).hasNext()) {
            laf = ((Iterator<Object>)call).next();
            try {
                Label_0186: {
                    if (!(laf instanceof ArrayList)) {
                        break Label_0186;
                    }
                    final LookAndFeel lookAndFeel = (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray[263].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { laf }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) })), $get$$class$javax$swing$LookAndFeel());
                    try {
                        return lookAndFeel;
                        final LookAndFeel lookAndFeel2 = (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray[264].callStatic($get$$class$groovy$swing$SwingBuilder(), laf), $get$$class$javax$swing$LookAndFeel());
                        try {
                            return lookAndFeel2;
                        }
                        catch (Throwable t) {
                            $getCallSiteArray[265].call(SwingBuilder.LOG, new GStringImpl(new Object[] { laf, t }, new String[] { "Could not instantiate Look and Feel ", " because of ", ". Attemting next option." }));
                        }
                    }
                    catch (Throwable t2) {}
                }
            }
            catch (Throwable t3) {}
        }
        $getCallSiteArray[266].call(SwingBuilder.LOG, new GStringImpl(new Object[] { lafs }, new String[] { "All Look and Feel options failed: ", "" }));
        return (LookAndFeel)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$LookAndFeel());
    }
    
    private static LookAndFeel _laf(final List s) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[267].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { s }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) })), $get$$class$javax$swing$LookAndFeel());
    }
    
    private static LookAndFeel _laf(final String s, final Map m) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[268].callStatic($get$$class$groovy$swing$SwingBuilder(), m, s, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.asType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    private static LookAndFeel _laf(final LookAndFeel laf, final Map m) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[269].callStatic($get$$class$groovy$swing$SwingBuilder(), m, laf, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.asType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    private static LookAndFeel _laf(final String s) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[270].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createMap(new Object[0]), s, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.asType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    private static LookAndFeel _laf(final LookAndFeel laf) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[271].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createMap(new Object[0]), laf, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.asType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static Object objectIDAttributeDelegate(final Object builder, final Object node, final Object attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[272].call(builder, SwingBuilder.DELEGATE_PROPERTY_OBJECT_ID))) {
            o = SwingBuilder.DEFAULT_DELEGATE_PROPERTY_OBJECT_ID;
        }
        final Object idAttr = o;
        final Object theID = $getCallSiteArray[273].call(attributes, idAttr);
        if (DefaultTypeTransformation.booleanUnbox(theID)) {
            return $getCallSiteArray[274].call(builder, theID, node);
        }
        return null;
    }
    
    public static Object clientPropertyAttributeDelegate(final Object builder, final Object node, final Object attributes) {
        final Object node2 = new Reference(node);
        final Object attributes2 = new Reference(attributes);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object clientPropertyMap = $getCallSiteArray[275].call(((Reference<Object>)attributes2).get(), "clientProperties");
        $getCallSiteArray[276].call(clientPropertyMap, new SwingBuilder$_clientPropertyAttributeDelegate_closure2($get$$class$groovy$swing$SwingBuilder(), $get$$class$groovy$swing$SwingBuilder(), (Reference<Object>)node2));
        return $getCallSiteArray[277].call($getCallSiteArray[278].call(((Reference<Object>)attributes2).get(), new SwingBuilder$_clientPropertyAttributeDelegate_closure3($get$$class$groovy$swing$SwingBuilder(), $get$$class$groovy$swing$SwingBuilder())), new SwingBuilder$_clientPropertyAttributeDelegate_closure4($get$$class$groovy$swing$SwingBuilder(), $get$$class$groovy$swing$SwingBuilder(), (Reference<Object>)node2, (Reference<Object>)attributes2));
    }
    
    public void createKeyStrokeAction(final Map attributes, final JComponent component) {
        final JComponent component2 = (JComponent)new Reference(component);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ((Reference<JComponent>)component2).set((JComponent)ScriptBytecodeAdapter.castToType($getCallSiteArray[279].callCurrent(this, attributes, ((Reference<Object>)component2).get()), $get$$class$javax$swing$JComponent()));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[280].call(attributes, "keyStroke"))) {
            throw (Throwable)$getCallSiteArray[281].callConstructor($get$$class$java$lang$RuntimeException(), "You must define a value for keyStroke:");
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[282].call(attributes, "action"))) {
            throw (Throwable)$getCallSiteArray[283].callConstructor($get$$class$java$lang$RuntimeException(), "You must define a value for action:");
        }
        Object value;
        if (!DefaultTypeTransformation.booleanUnbox(value = $getCallSiteArray[284].call(attributes, "condition"))) {
            value = $getCallSiteArray[285].callGetProperty($get$$class$javax$swing$JComponent());
        }
        final Object condition = new Reference(value);
        if (((Reference<Object>)condition).get() instanceof GString) {
            ((Reference<String>)condition).set((String)ScriptBytecodeAdapter.asType(((Reference<Object>)condition).get(), $get$$class$java$lang$String()));
        }
        if (((Reference<Object>)condition).get() instanceof String) {
            ((Reference<Object>)condition).set($getCallSiteArray[286].call($getCallSiteArray[287].call(((Reference<Object>)condition).get()), " ", "_"));
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[288].call(((Reference<Object>)condition).get(), "WHEN_"))) {
                ((Reference<Object>)condition).set($getCallSiteArray[289].call("WHEN_", ((Reference<Object>)condition).get()));
            }
        }
        final Object value2 = ((Reference<Object>)condition).get();
        if (!ScriptBytecodeAdapter.isCase(value2, $getCallSiteArray[290].callGetProperty($get$$class$javax$swing$JComponent()))) {
            if (!ScriptBytecodeAdapter.isCase(value2, $getCallSiteArray[291].callGetProperty($get$$class$javax$swing$JComponent()))) {
                if (!ScriptBytecodeAdapter.isCase(value2, $getCallSiteArray[292].callGetProperty($get$$class$javax$swing$JComponent()))) {
                    if (ScriptBytecodeAdapter.isCase(value2, "WHEN_FOCUSED")) {
                        ((Reference<Object>)condition).set($getCallSiteArray[293].callGetProperty($get$$class$javax$swing$JComponent()));
                    }
                    else if (ScriptBytecodeAdapter.isCase(value2, "WHEN_ANCESTOR_OF_FOCUSED_COMPONENT")) {
                        ((Reference<Object>)condition).set($getCallSiteArray[294].callGetProperty($get$$class$javax$swing$JComponent()));
                    }
                    else if (ScriptBytecodeAdapter.isCase(value2, "WHEN_IN_FOCUSED_WINDOW")) {
                        ((Reference<Object>)condition).set($getCallSiteArray[295].callGetProperty($get$$class$javax$swing$JComponent()));
                    }
                    else {
                        ((Reference<Object>)condition).set($getCallSiteArray[296].callGetProperty($get$$class$javax$swing$JComponent()));
                    }
                }
            }
        }
        final Object actionKey = new Reference($getCallSiteArray[297].call(attributes, "actionKey"));
        if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)actionKey).get())) {
            ((Reference<Object>)actionKey).set($getCallSiteArray[298].call("Action", $getCallSiteArray[299].call($get$$class$java$lang$Math(), $getCallSiteArray[300].call(SwingBuilder.random))));
        }
        Object keyStroke = $getCallSiteArray[301].call(attributes, "keyStroke");
        final Object action = $getCallSiteArray[302].call(attributes, "action");
        if (keyStroke instanceof GString) {
            keyStroke = ScriptBytecodeAdapter.asType(keyStroke, $get$$class$java$lang$String());
        }
        if (DefaultTypeTransformation.booleanUnbox((!(keyStroke instanceof String) && !(keyStroke instanceof Number)) ? Boolean.FALSE : Boolean.TRUE)) {
            keyStroke = ScriptBytecodeAdapter.createList(new Object[] { keyStroke });
        }
        $getCallSiteArray[303].call(keyStroke, new SwingBuilder$_createKeyStrokeAction_closure5(this, this, (Reference<Object>)condition, (Reference<Object>)component2, (Reference<Object>)actionKey));
        $getCallSiteArray[304].call($getCallSiteArray[305].callGetProperty(((Reference<Object>)component2).get()), ((Reference<Object>)actionKey).get(), action);
    }
    
    private Object findTargetComponent(final Map attributes, final JComponent component) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(component)) {
            return component;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[306].call(attributes, "component"))) {
            final Object c = $getCallSiteArray[307].call(attributes, "component");
            if (!(c instanceof JComponent)) {
                throw (Throwable)$getCallSiteArray[308].callConstructor($get$$class$java$lang$RuntimeException(), "The property component: is not of type JComponent.");
            }
            return c;
        }
        else {
            final Object c = $getCallSiteArray[309].callCurrent(this);
            if (c instanceof JComponent) {
                return c;
            }
            throw (Throwable)$getCallSiteArray[310].callConstructor($get$$class$java$lang$RuntimeException(), "You must define one of the following: a value of type JComponent, a component: attribute or nest this node inside another one that produces a JComponent.");
        }
    }
    
    public KeyStroke shortcut(final Object key) {
        return (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray()[311].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(key, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createPojoWrapper(SwingBuilder.$const$1, $get$$class$java$lang$Object())), $get$$class$javax$swing$KeyStroke());
    }
    
    public KeyStroke shortcut(final String key) {
        return (KeyStroke)ScriptBytecodeAdapter.castToType($getCallSiteArray()[312].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(key, $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(SwingBuilder.$const$1, $get$$class$java$lang$Object())), $get$$class$javax$swing$KeyStroke());
    }
    
    public static LookAndFeel lookAndFeel(final Map attributes, final Object laf) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[313].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createPojoWrapper(attributes, $get$$class$java$util$Map()), ScriptBytecodeAdapter.createPojoWrapper(laf, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static LookAndFeel lookAndFeel(final Map attributes) {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[314].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createPojoWrapper(attributes, $get$$class$java$util$Map()), ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static LookAndFeel lookAndFeel() {
        return (LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray()[315].callStatic($get$$class$groovy$swing$SwingBuilder(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map()), ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$javax$swing$LookAndFeel());
    }
    
    public void createKeyStrokeAction(final Map attributes) {
        $getCallSiteArray()[316].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(attributes, $get$$class$java$util$Map()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JComponent()), $get$$class$javax$swing$JComponent()));
    }
    
    static {
        SwingBuilder.__timeStamp__239_neverHappen1292524205610 = 0L;
        SwingBuilder.__timeStamp = 1292524205610L;
        $const$1 = 0;
        $const$0 = 1;
        DEFAULT_DELEGATE_PROPERTY_OBJECT_ID = "id";
        DELEGATE_PROPERTY_OBJECT_ID = "_delegateProperty:id";
        SwingBuilder.headless = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        LOG = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray()[317].call($get$$class$java$util$logging$Logger(), $getCallSiteArray()[318].callGetProperty($get$$class$groovy$swing$SwingBuilder())), $get$$class$java$util$logging$Logger());
        random = (Random)$getCallSiteArray()[319].callConstructor($get$$class$java$util$Random());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[320];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$SwingBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingBuilder.$callSiteArray == null || ($createCallSiteArray = SwingBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SpinnerListModel() {
        Class $class$javax$swing$SpinnerListModel;
        if (($class$javax$swing$SpinnerListModel = SwingBuilder.$class$javax$swing$SpinnerListModel) == null) {
            $class$javax$swing$SpinnerListModel = (SwingBuilder.$class$javax$swing$SpinnerListModel = class$("javax.swing.SpinnerListModel"));
        }
        return $class$javax$swing$SpinnerListModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TextArgWidgetFactory() {
        Class $class$groovy$swing$factory$TextArgWidgetFactory;
        if (($class$groovy$swing$factory$TextArgWidgetFactory = SwingBuilder.$class$groovy$swing$factory$TextArgWidgetFactory) == null) {
            $class$groovy$swing$factory$TextArgWidgetFactory = (SwingBuilder.$class$groovy$swing$factory$TextArgWidgetFactory = class$("groovy.swing.factory.TextArgWidgetFactory"));
        }
        return $class$groovy$swing$factory$TextArgWidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TRFactory() {
        Class $class$groovy$swing$factory$TRFactory;
        if (($class$groovy$swing$factory$TRFactory = SwingBuilder.$class$groovy$swing$factory$TRFactory) == null) {
            $class$groovy$swing$factory$TRFactory = (SwingBuilder.$class$groovy$swing$factory$TRFactory = class$("groovy.swing.factory.TRFactory"));
        }
        return $class$groovy$swing$factory$TRFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTree() {
        Class $class$javax$swing$JTree;
        if (($class$javax$swing$JTree = SwingBuilder.$class$javax$swing$JTree) == null) {
            $class$javax$swing$JTree = (SwingBuilder.$class$javax$swing$JTree = class$("javax.swing.JTree"));
        }
        return $class$javax$swing$JTree;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JPopupMenu() {
        Class $class$javax$swing$JPopupMenu;
        if (($class$javax$swing$JPopupMenu = SwingBuilder.$class$javax$swing$JPopupMenu) == null) {
            $class$javax$swing$JPopupMenu = (SwingBuilder.$class$javax$swing$JPopupMenu = class$("javax.swing.JPopupMenu"));
        }
        return $class$javax$swing$JPopupMenu;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Math() {
        Class $class$java$lang$Math;
        if (($class$java$lang$Math = SwingBuilder.$class$java$lang$Math) == null) {
            $class$java$lang$Math = (SwingBuilder.$class$java$lang$Math = class$("java.lang.Math"));
        }
        return $class$java$lang$Math;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JLayeredPane() {
        Class $class$javax$swing$JLayeredPane;
        if (($class$javax$swing$JLayeredPane = SwingBuilder.$class$javax$swing$JLayeredPane) == null) {
            $class$javax$swing$JLayeredPane = (SwingBuilder.$class$javax$swing$JLayeredPane = class$("javax.swing.JLayeredPane"));
        }
        return $class$javax$swing$JLayeredPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = SwingBuilder.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (SwingBuilder.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$FormattedTextFactory() {
        Class $class$groovy$swing$factory$FormattedTextFactory;
        if (($class$groovy$swing$factory$FormattedTextFactory = SwingBuilder.$class$groovy$swing$factory$FormattedTextFactory) == null) {
            $class$groovy$swing$factory$FormattedTextFactory = (SwingBuilder.$class$groovy$swing$factory$FormattedTextFactory = class$("groovy.swing.factory.FormattedTextFactory"));
        }
        return $class$groovy$swing$factory$FormattedTextFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ImageIconFactory() {
        Class $class$groovy$swing$factory$ImageIconFactory;
        if (($class$groovy$swing$factory$ImageIconFactory = SwingBuilder.$class$groovy$swing$factory$ImageIconFactory) == null) {
            $class$groovy$swing$factory$ImageIconFactory = (SwingBuilder.$class$groovy$swing$factory$ImageIconFactory = class$("groovy.swing.factory.ImageIconFactory"));
        }
        return $class$groovy$swing$factory$ImageIconFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableFactory() {
        Class $class$groovy$swing$factory$TableFactory;
        if (($class$groovy$swing$factory$TableFactory = SwingBuilder.$class$groovy$swing$factory$TableFactory) == null) {
            $class$groovy$swing$factory$TableFactory = (SwingBuilder.$class$groovy$swing$factory$TableFactory = class$("groovy.swing.factory.TableFactory"));
        }
        return $class$groovy$swing$factory$TableFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ActionFactory() {
        Class $class$groovy$swing$factory$ActionFactory;
        if (($class$groovy$swing$factory$ActionFactory = SwingBuilder.$class$groovy$swing$factory$ActionFactory) == null) {
            $class$groovy$swing$factory$ActionFactory = (SwingBuilder.$class$groovy$swing$factory$ActionFactory = class$("groovy.swing.factory.ActionFactory"));
        }
        return $class$groovy$swing$factory$ActionFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$InternalFrameFactory() {
        Class $class$groovy$swing$factory$InternalFrameFactory;
        if (($class$groovy$swing$factory$InternalFrameFactory = SwingBuilder.$class$groovy$swing$factory$InternalFrameFactory) == null) {
            $class$groovy$swing$factory$InternalFrameFactory = (SwingBuilder.$class$groovy$swing$factory$InternalFrameFactory = class$("groovy.swing.factory.InternalFrameFactory"));
        }
        return $class$groovy$swing$factory$InternalFrameFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$HGlueFactory() {
        Class $class$groovy$swing$factory$HGlueFactory;
        if (($class$groovy$swing$factory$HGlueFactory = SwingBuilder.$class$groovy$swing$factory$HGlueFactory) == null) {
            $class$groovy$swing$factory$HGlueFactory = (SwingBuilder.$class$groovy$swing$factory$HGlueFactory = class$("groovy.swing.factory.HGlueFactory"));
        }
        return $class$groovy$swing$factory$HGlueFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JToolBar() {
        Class $class$javax$swing$JToolBar;
        if (($class$javax$swing$JToolBar = SwingBuilder.$class$javax$swing$JToolBar) == null) {
            $class$javax$swing$JToolBar = (SwingBuilder.$class$javax$swing$JToolBar = class$("javax.swing.JToolBar"));
        }
        return $class$javax$swing$JToolBar;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JLabel() {
        Class $class$javax$swing$JLabel;
        if (($class$javax$swing$JLabel = SwingBuilder.$class$javax$swing$JLabel) == null) {
            $class$javax$swing$JLabel = (SwingBuilder.$class$javax$swing$JLabel = class$("javax.swing.JLabel"));
        }
        return $class$javax$swing$JLabel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JPasswordField() {
        Class $class$javax$swing$JPasswordField;
        if (($class$javax$swing$JPasswordField = SwingBuilder.$class$javax$swing$JPasswordField) == null) {
            $class$javax$swing$JPasswordField = (SwingBuilder.$class$javax$swing$JPasswordField = class$("javax.swing.JPasswordField"));
        }
        return $class$javax$swing$JPasswordField;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$VGlueFactory() {
        Class $class$groovy$swing$factory$VGlueFactory;
        if (($class$groovy$swing$factory$VGlueFactory = SwingBuilder.$class$groovy$swing$factory$VGlueFactory) == null) {
            $class$groovy$swing$factory$VGlueFactory = (SwingBuilder.$class$groovy$swing$factory$VGlueFactory = class$("groovy.swing.factory.VGlueFactory"));
        }
        return $class$groovy$swing$factory$VGlueFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JApplet() {
        Class $class$javax$swing$JApplet;
        if (($class$javax$swing$JApplet = SwingBuilder.$class$javax$swing$JApplet) == null) {
            $class$javax$swing$JApplet = (SwingBuilder.$class$javax$swing$JApplet = class$("javax.swing.JApplet"));
        }
        return $class$javax$swing$JApplet;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JComponent() {
        Class $class$javax$swing$JComponent;
        if (($class$javax$swing$JComponent = SwingBuilder.$class$javax$swing$JComponent) == null) {
            $class$javax$swing$JComponent = (SwingBuilder.$class$javax$swing$JComponent = class$("javax.swing.JComponent"));
        }
        return $class$javax$swing$JComponent;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$FrameFactory() {
        Class $class$groovy$swing$factory$FrameFactory;
        if (($class$groovy$swing$factory$FrameFactory = SwingBuilder.$class$groovy$swing$factory$FrameFactory) == null) {
            $class$groovy$swing$factory$FrameFactory = (SwingBuilder.$class$groovy$swing$factory$FrameFactory = class$("groovy.swing.factory.FrameFactory"));
        }
        return $class$groovy$swing$factory$FrameFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JRadioButton() {
        Class $class$javax$swing$JRadioButton;
        if (($class$javax$swing$JRadioButton = SwingBuilder.$class$javax$swing$JRadioButton) == null) {
            $class$javax$swing$JRadioButton = (SwingBuilder.$class$javax$swing$JRadioButton = class$("javax.swing.JRadioButton"));
        }
        return $class$javax$swing$JRadioButton;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SwingUtilities() {
        Class $class$javax$swing$SwingUtilities;
        if (($class$javax$swing$SwingUtilities = SwingBuilder.$class$javax$swing$SwingUtilities) == null) {
            $class$javax$swing$SwingUtilities = (SwingBuilder.$class$javax$swing$SwingUtilities = class$("javax.swing.SwingUtilities"));
        }
        return $class$javax$swing$SwingUtilities;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JPanel() {
        Class $class$javax$swing$JPanel;
        if (($class$javax$swing$JPanel = SwingBuilder.$class$javax$swing$JPanel) == null) {
            $class$javax$swing$JPanel = (SwingBuilder.$class$javax$swing$JPanel = class$("javax.swing.JPanel"));
        }
        return $class$javax$swing$JPanel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BoxLayoutFactory() {
        Class $class$groovy$swing$factory$BoxLayoutFactory;
        if (($class$groovy$swing$factory$BoxLayoutFactory = SwingBuilder.$class$groovy$swing$factory$BoxLayoutFactory) == null) {
            $class$groovy$swing$factory$BoxLayoutFactory = (SwingBuilder.$class$groovy$swing$factory$BoxLayoutFactory = class$("groovy.swing.factory.BoxLayoutFactory"));
        }
        return $class$groovy$swing$factory$BoxLayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JProgressBar() {
        Class $class$javax$swing$JProgressBar;
        if (($class$javax$swing$JProgressBar = SwingBuilder.$class$javax$swing$JProgressBar) == null) {
            $class$javax$swing$JProgressBar = (SwingBuilder.$class$javax$swing$JProgressBar = class$("javax.swing.JProgressBar"));
        }
        return $class$javax$swing$JProgressBar;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = SwingBuilder.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (SwingBuilder.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SpringLayout() {
        Class $class$javax$swing$SpringLayout;
        if (($class$javax$swing$SpringLayout = SwingBuilder.$class$javax$swing$SpringLayout) == null) {
            $class$javax$swing$SpringLayout = (SwingBuilder.$class$javax$swing$SpringLayout = class$("javax.swing.SpringLayout"));
        }
        return $class$javax$swing$SpringLayout;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$FlowLayout() {
        Class $class$java$awt$FlowLayout;
        if (($class$java$awt$FlowLayout = SwingBuilder.$class$java$awt$FlowLayout) == null) {
            $class$java$awt$FlowLayout = (SwingBuilder.$class$java$awt$FlowLayout = class$("java.awt.FlowLayout"));
        }
        return $class$java$awt$FlowLayout;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ListFactory() {
        Class $class$groovy$swing$factory$ListFactory;
        if (($class$groovy$swing$factory$ListFactory = SwingBuilder.$class$groovy$swing$factory$ListFactory) == null) {
            $class$groovy$swing$factory$ListFactory = (SwingBuilder.$class$groovy$swing$factory$ListFactory = class$("groovy.swing.factory.ListFactory"));
        }
        return $class$groovy$swing$factory$ListFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SpinnerNumberModel() {
        Class $class$javax$swing$SpinnerNumberModel;
        if (($class$javax$swing$SpinnerNumberModel = SwingBuilder.$class$javax$swing$SpinnerNumberModel) == null) {
            $class$javax$swing$SpinnerNumberModel = (SwingBuilder.$class$javax$swing$SpinnerNumberModel = class$("javax.swing.SpinnerNumberModel"));
        }
        return $class$javax$swing$SpinnerNumberModel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JScrollBar() {
        Class $class$javax$swing$JScrollBar;
        if (($class$javax$swing$JScrollBar = SwingBuilder.$class$javax$swing$JScrollBar) == null) {
            $class$javax$swing$JScrollBar = (SwingBuilder.$class$javax$swing$JScrollBar = class$("javax.swing.JScrollBar"));
        }
        return $class$javax$swing$JScrollBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$PropertyColumnFactory() {
        Class $class$groovy$swing$factory$PropertyColumnFactory;
        if (($class$groovy$swing$factory$PropertyColumnFactory = SwingBuilder.$class$groovy$swing$factory$PropertyColumnFactory) == null) {
            $class$groovy$swing$factory$PropertyColumnFactory = (SwingBuilder.$class$groovy$swing$factory$PropertyColumnFactory = class$("groovy.swing.factory.PropertyColumnFactory"));
        }
        return $class$groovy$swing$factory$PropertyColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JMenuBar() {
        Class $class$javax$swing$JMenuBar;
        if (($class$javax$swing$JMenuBar = SwingBuilder.$class$javax$swing$JMenuBar) == null) {
            $class$javax$swing$JMenuBar = (SwingBuilder.$class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar"));
        }
        return $class$javax$swing$JMenuBar;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$VStrutFactory() {
        Class $class$groovy$swing$factory$VStrutFactory;
        if (($class$groovy$swing$factory$VStrutFactory = SwingBuilder.$class$groovy$swing$factory$VStrutFactory) == null) {
            $class$groovy$swing$factory$VStrutFactory = (SwingBuilder.$class$groovy$swing$factory$VStrutFactory = class$("groovy.swing.factory.VStrutFactory"));
        }
        return $class$groovy$swing$factory$VStrutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSpinner() {
        Class $class$javax$swing$JSpinner;
        if (($class$javax$swing$JSpinner = SwingBuilder.$class$javax$swing$JSpinner) == null) {
            $class$javax$swing$JSpinner = (SwingBuilder.$class$javax$swing$JSpinner = class$("javax.swing.JSpinner"));
        }
        return $class$javax$swing$JSpinner;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RigidAreaFactory() {
        Class $class$groovy$swing$factory$RigidAreaFactory;
        if (($class$groovy$swing$factory$RigidAreaFactory = SwingBuilder.$class$groovy$swing$factory$RigidAreaFactory) == null) {
            $class$groovy$swing$factory$RigidAreaFactory = (SwingBuilder.$class$groovy$swing$factory$RigidAreaFactory = class$("groovy.swing.factory.RigidAreaFactory"));
        }
        return $class$groovy$swing$factory$RigidAreaFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableLayoutFactory() {
        Class $class$groovy$swing$factory$TableLayoutFactory;
        if (($class$groovy$swing$factory$TableLayoutFactory = SwingBuilder.$class$groovy$swing$factory$TableLayoutFactory) == null) {
            $class$groovy$swing$factory$TableLayoutFactory = (SwingBuilder.$class$groovy$swing$factory$TableLayoutFactory = class$("groovy.swing.factory.TableLayoutFactory"));
        }
        return $class$groovy$swing$factory$TableLayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RendererUpdateFactory() {
        Class $class$groovy$swing$factory$RendererUpdateFactory;
        if (($class$groovy$swing$factory$RendererUpdateFactory = SwingBuilder.$class$groovy$swing$factory$RendererUpdateFactory) == null) {
            $class$groovy$swing$factory$RendererUpdateFactory = (SwingBuilder.$class$groovy$swing$factory$RendererUpdateFactory = class$("groovy.swing.factory.RendererUpdateFactory"));
        }
        return $class$groovy$swing$factory$RendererUpdateFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Random() {
        Class $class$java$util$Random;
        if (($class$java$util$Random = SwingBuilder.$class$java$util$Random) == null) {
            $class$java$util$Random = (SwingBuilder.$class$java$util$Random = class$("java.util.Random"));
        }
        return $class$java$util$Random;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridLayout() {
        Class $class$java$awt$GridLayout;
        if (($class$java$awt$GridLayout = SwingBuilder.$class$java$awt$GridLayout) == null) {
            $class$java$awt$GridLayout = (SwingBuilder.$class$java$awt$GridLayout = class$("java.awt.GridLayout"));
        }
        return $class$java$awt$GridLayout;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JRadioButtonMenuItem() {
        Class $class$javax$swing$JRadioButtonMenuItem;
        if (($class$javax$swing$JRadioButtonMenuItem = SwingBuilder.$class$javax$swing$JRadioButtonMenuItem) == null) {
            $class$javax$swing$JRadioButtonMenuItem = (SwingBuilder.$class$javax$swing$JRadioButtonMenuItem = class$("javax.swing.JRadioButtonMenuItem"));
        }
        return $class$javax$swing$JRadioButtonMenuItem;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GraphicsEnvironment() {
        Class $class$java$awt$GraphicsEnvironment;
        if (($class$java$awt$GraphicsEnvironment = SwingBuilder.$class$java$awt$GraphicsEnvironment) == null) {
            $class$java$awt$GraphicsEnvironment = (SwingBuilder.$class$java$awt$GraphicsEnvironment = class$("java.awt.GraphicsEnvironment"));
        }
        return $class$java$awt$GraphicsEnvironment;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JWindow() {
        Class $class$javax$swing$JWindow;
        if (($class$javax$swing$JWindow = SwingBuilder.$class$javax$swing$JWindow) == null) {
            $class$javax$swing$JWindow = (SwingBuilder.$class$javax$swing$JWindow = class$("javax.swing.JWindow"));
        }
        return $class$javax$swing$JWindow;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ColumnModelFactory() {
        Class $class$groovy$swing$factory$ColumnModelFactory;
        if (($class$groovy$swing$factory$ColumnModelFactory = SwingBuilder.$class$groovy$swing$factory$ColumnModelFactory) == null) {
            $class$groovy$swing$factory$ColumnModelFactory = (SwingBuilder.$class$groovy$swing$factory$ColumnModelFactory = class$("groovy.swing.factory.ColumnModelFactory"));
        }
        return $class$groovy$swing$factory$ColumnModelFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JDialog() {
        Class $class$javax$swing$JDialog;
        if (($class$javax$swing$JDialog = SwingBuilder.$class$javax$swing$JDialog) == null) {
            $class$javax$swing$JDialog = (SwingBuilder.$class$javax$swing$JDialog = class$("javax.swing.JDialog"));
        }
        return $class$javax$swing$JDialog;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JMenuItem() {
        Class $class$javax$swing$JMenuItem;
        if (($class$javax$swing$JMenuItem = SwingBuilder.$class$javax$swing$JMenuItem) == null) {
            $class$javax$swing$JMenuItem = (SwingBuilder.$class$javax$swing$JMenuItem = class$("javax.swing.JMenuItem"));
        }
        return $class$javax$swing$JMenuItem;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LayoutFactory() {
        Class $class$groovy$swing$factory$LayoutFactory;
        if (($class$groovy$swing$factory$LayoutFactory = SwingBuilder.$class$groovy$swing$factory$LayoutFactory) == null) {
            $class$groovy$swing$factory$LayoutFactory = (SwingBuilder.$class$groovy$swing$factory$LayoutFactory = class$("groovy.swing.factory.LayoutFactory"));
        }
        return $class$groovy$swing$factory$LayoutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$DialogFactory() {
        Class $class$groovy$swing$factory$DialogFactory;
        if (($class$groovy$swing$factory$DialogFactory = SwingBuilder.$class$groovy$swing$factory$DialogFactory) == null) {
            $class$groovy$swing$factory$DialogFactory = (SwingBuilder.$class$groovy$swing$factory$DialogFactory = class$("groovy.swing.factory.DialogFactory"));
        }
        return $class$groovy$swing$factory$DialogFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ColumnFactory() {
        Class $class$groovy$swing$factory$ColumnFactory;
        if (($class$groovy$swing$factory$ColumnFactory = SwingBuilder.$class$groovy$swing$factory$ColumnFactory) == null) {
            $class$groovy$swing$factory$ColumnFactory = (SwingBuilder.$class$groovy$swing$factory$ColumnFactory = class$("groovy.swing.factory.ColumnFactory"));
        }
        return $class$groovy$swing$factory$ColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$VBoxFactory() {
        Class $class$groovy$swing$factory$VBoxFactory;
        if (($class$groovy$swing$factory$VBoxFactory = SwingBuilder.$class$groovy$swing$factory$VBoxFactory) == null) {
            $class$groovy$swing$factory$VBoxFactory = (SwingBuilder.$class$groovy$swing$factory$VBoxFactory = class$("groovy.swing.factory.VBoxFactory"));
        }
        return $class$groovy$swing$factory$VBoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindGroupFactory() {
        Class $class$groovy$swing$factory$BindGroupFactory;
        if (($class$groovy$swing$factory$BindGroupFactory = SwingBuilder.$class$groovy$swing$factory$BindGroupFactory) == null) {
            $class$groovy$swing$factory$BindGroupFactory = (SwingBuilder.$class$groovy$swing$factory$BindGroupFactory = class$("groovy.swing.factory.BindGroupFactory"));
        }
        return $class$groovy$swing$factory$BindGroupFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RendererFactory() {
        Class $class$groovy$swing$factory$RendererFactory;
        if (($class$groovy$swing$factory$RendererFactory = SwingBuilder.$class$groovy$swing$factory$RendererFactory) == null) {
            $class$groovy$swing$factory$RendererFactory = (SwingBuilder.$class$groovy$swing$factory$RendererFactory = class$("groovy.swing.factory.RendererFactory"));
        }
        return $class$groovy$swing$factory$RendererFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$LineBorderFactory() {
        Class $class$groovy$swing$factory$LineBorderFactory;
        if (($class$groovy$swing$factory$LineBorderFactory = SwingBuilder.$class$groovy$swing$factory$LineBorderFactory) == null) {
            $class$groovy$swing$factory$LineBorderFactory = (SwingBuilder.$class$groovy$swing$factory$LineBorderFactory = class$("groovy.swing.factory.LineBorderFactory"));
        }
        return $class$groovy$swing$factory$LineBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = SwingBuilder.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (SwingBuilder.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JEditorPane() {
        Class $class$javax$swing$JEditorPane;
        if (($class$javax$swing$JEditorPane = SwingBuilder.$class$javax$swing$JEditorPane) == null) {
            $class$javax$swing$JEditorPane = (SwingBuilder.$class$javax$swing$JEditorPane = class$("javax.swing.JEditorPane"));
        }
        return $class$javax$swing$JEditorPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CompoundBorderFactory() {
        Class $class$groovy$swing$factory$CompoundBorderFactory;
        if (($class$groovy$swing$factory$CompoundBorderFactory = SwingBuilder.$class$groovy$swing$factory$CompoundBorderFactory) == null) {
            $class$groovy$swing$factory$CompoundBorderFactory = (SwingBuilder.$class$groovy$swing$factory$CompoundBorderFactory = class$("groovy.swing.factory.CompoundBorderFactory"));
        }
        return $class$groovy$swing$factory$CompoundBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TDFactory() {
        Class $class$groovy$swing$factory$TDFactory;
        if (($class$groovy$swing$factory$TDFactory = SwingBuilder.$class$groovy$swing$factory$TDFactory) == null) {
            $class$groovy$swing$factory$TDFactory = (SwingBuilder.$class$groovy$swing$factory$TDFactory = class$("groovy.swing.factory.TDFactory"));
        }
        return $class$groovy$swing$factory$TDFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$LookAndFeel() {
        Class $class$javax$swing$LookAndFeel;
        if (($class$javax$swing$LookAndFeel = SwingBuilder.$class$javax$swing$LookAndFeel) == null) {
            $class$javax$swing$LookAndFeel = (SwingBuilder.$class$javax$swing$LookAndFeel = class$("javax.swing.LookAndFeel"));
        }
        return $class$javax$swing$LookAndFeel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$OverlayLayout() {
        Class $class$javax$swing$OverlayLayout;
        if (($class$javax$swing$OverlayLayout = SwingBuilder.$class$javax$swing$OverlayLayout) == null) {
            $class$javax$swing$OverlayLayout = (SwingBuilder.$class$javax$swing$OverlayLayout = class$("javax.swing.OverlayLayout"));
        }
        return $class$javax$swing$OverlayLayout;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SwingBuilder.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SwingBuilder.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$EmptyBorderFactory() {
        Class $class$groovy$swing$factory$EmptyBorderFactory;
        if (($class$groovy$swing$factory$EmptyBorderFactory = SwingBuilder.$class$groovy$swing$factory$EmptyBorderFactory) == null) {
            $class$groovy$swing$factory$EmptyBorderFactory = (SwingBuilder.$class$groovy$swing$factory$EmptyBorderFactory = class$("groovy.swing.factory.EmptyBorderFactory"));
        }
        return $class$groovy$swing$factory$EmptyBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JOptionPane() {
        Class $class$javax$swing$JOptionPane;
        if (($class$javax$swing$JOptionPane = SwingBuilder.$class$javax$swing$JOptionPane) == null) {
            $class$javax$swing$JOptionPane = (SwingBuilder.$class$javax$swing$JOptionPane = class$("javax.swing.JOptionPane"));
        }
        return $class$javax$swing$JOptionPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTable() {
        Class $class$javax$swing$JTable;
        if (($class$javax$swing$JTable = SwingBuilder.$class$javax$swing$JTable) == null) {
            $class$javax$swing$JTable = (SwingBuilder.$class$javax$swing$JTable = class$("javax.swing.JTable"));
        }
        return $class$javax$swing$JTable;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = SwingBuilder.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (SwingBuilder.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
        }
        return $class$groovy$lang$GroovyRuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$MatteBorderFactory() {
        Class $class$groovy$swing$factory$MatteBorderFactory;
        if (($class$groovy$swing$factory$MatteBorderFactory = SwingBuilder.$class$groovy$swing$factory$MatteBorderFactory) == null) {
            $class$groovy$swing$factory$MatteBorderFactory = (SwingBuilder.$class$groovy$swing$factory$MatteBorderFactory = class$("groovy.swing.factory.MatteBorderFactory"));
        }
        return $class$groovy$swing$factory$MatteBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$CardLayout() {
        Class $class$java$awt$CardLayout;
        if (($class$java$awt$CardLayout = SwingBuilder.$class$java$awt$CardLayout) == null) {
            $class$java$awt$CardLayout = (SwingBuilder.$class$java$awt$CardLayout = class$("java.awt.CardLayout"));
        }
        return $class$java$awt$CardLayout;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTabbedPane() {
        Class $class$javax$swing$JTabbedPane;
        if (($class$javax$swing$JTabbedPane = SwingBuilder.$class$javax$swing$JTabbedPane) == null) {
            $class$javax$swing$JTabbedPane = (SwingBuilder.$class$javax$swing$JTabbedPane = class$("javax.swing.JTabbedPane"));
        }
        return $class$javax$swing$JTabbedPane;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = SwingBuilder.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (SwingBuilder.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComponentFactory() {
        Class $class$groovy$swing$factory$ComponentFactory;
        if (($class$groovy$swing$factory$ComponentFactory = SwingBuilder.$class$groovy$swing$factory$ComponentFactory) == null) {
            $class$groovy$swing$factory$ComponentFactory = (SwingBuilder.$class$groovy$swing$factory$ComponentFactory = class$("groovy.swing.factory.ComponentFactory"));
        }
        return $class$groovy$swing$factory$ComponentFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = SwingBuilder.$class$java$lang$String) == null) {
            $class$java$lang$String = (SwingBuilder.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CellEditorFactory() {
        Class $class$groovy$swing$factory$CellEditorFactory;
        if (($class$groovy$swing$factory$CellEditorFactory = SwingBuilder.$class$groovy$swing$factory$CellEditorFactory) == null) {
            $class$groovy$swing$factory$CellEditorFactory = (SwingBuilder.$class$groovy$swing$factory$CellEditorFactory = class$("groovy.swing.factory.CellEditorFactory"));
        }
        return $class$groovy$swing$factory$CellEditorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$KeyStroke() {
        Class $class$javax$swing$KeyStroke;
        if (($class$javax$swing$KeyStroke = SwingBuilder.$class$javax$swing$KeyStroke) == null) {
            $class$javax$swing$KeyStroke = (SwingBuilder.$class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke"));
        }
        return $class$javax$swing$KeyStroke;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableColumn() {
        Class $class$javax$swing$table$TableColumn;
        if (($class$javax$swing$table$TableColumn = SwingBuilder.$class$javax$swing$table$TableColumn) == null) {
            $class$javax$swing$table$TableColumn = (SwingBuilder.$class$javax$swing$table$TableColumn = class$("javax.swing.table.TableColumn"));
        }
        return $class$javax$swing$table$TableColumn;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ScrollPaneFactory() {
        Class $class$groovy$swing$factory$ScrollPaneFactory;
        if (($class$groovy$swing$factory$ScrollPaneFactory = SwingBuilder.$class$groovy$swing$factory$ScrollPaneFactory) == null) {
            $class$groovy$swing$factory$ScrollPaneFactory = (SwingBuilder.$class$groovy$swing$factory$ScrollPaneFactory = class$("groovy.swing.factory.ScrollPaneFactory"));
        }
        return $class$groovy$swing$factory$ScrollPaneFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JScrollPane() {
        Class $class$javax$swing$JScrollPane;
        if (($class$javax$swing$JScrollPane = SwingBuilder.$class$javax$swing$JScrollPane) == null) {
            $class$javax$swing$JScrollPane = (SwingBuilder.$class$javax$swing$JScrollPane = class$("javax.swing.JScrollPane"));
        }
        return $class$javax$swing$JScrollPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSlider() {
        Class $class$javax$swing$JSlider;
        if (($class$javax$swing$JSlider = SwingBuilder.$class$javax$swing$JSlider) == null) {
            $class$javax$swing$JSlider = (SwingBuilder.$class$javax$swing$JSlider = class$("javax.swing.JSlider"));
        }
        return $class$javax$swing$JSlider;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$HBoxFactory() {
        Class $class$groovy$swing$factory$HBoxFactory;
        if (($class$groovy$swing$factory$HBoxFactory = SwingBuilder.$class$groovy$swing$factory$HBoxFactory) == null) {
            $class$groovy$swing$factory$HBoxFactory = (SwingBuilder.$class$groovy$swing$factory$HBoxFactory = class$("groovy.swing.factory.HBoxFactory"));
        }
        return $class$groovy$swing$factory$HBoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Thread() {
        Class $class$java$lang$Thread;
        if (($class$java$lang$Thread = SwingBuilder.$class$java$lang$Thread) == null) {
            $class$java$lang$Thread = (SwingBuilder.$class$java$lang$Thread = class$("java.lang.Thread"));
        }
        return $class$java$lang$Thread;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTextField() {
        Class $class$javax$swing$JTextField;
        if (($class$javax$swing$JTextField = SwingBuilder.$class$javax$swing$JTextField) == null) {
            $class$javax$swing$JTextField = (SwingBuilder.$class$javax$swing$JTextField = class$("javax.swing.JTextField"));
        }
        return $class$javax$swing$JTextField;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MissingMethodException() {
        Class $class$groovy$lang$MissingMethodException;
        if (($class$groovy$lang$MissingMethodException = SwingBuilder.$class$groovy$lang$MissingMethodException) == null) {
            $class$groovy$lang$MissingMethodException = (SwingBuilder.$class$groovy$lang$MissingMethodException = class$("groovy.lang.MissingMethodException"));
        }
        return $class$groovy$lang$MissingMethodException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JViewport() {
        Class $class$javax$swing$JViewport;
        if (($class$javax$swing$JViewport = SwingBuilder.$class$javax$swing$JViewport) == null) {
            $class$javax$swing$JViewport = (SwingBuilder.$class$javax$swing$JViewport = class$("javax.swing.JViewport"));
        }
        return $class$javax$swing$JViewport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BoxFactory() {
        Class $class$groovy$swing$factory$BoxFactory;
        if (($class$groovy$swing$factory$BoxFactory = SwingBuilder.$class$groovy$swing$factory$BoxFactory) == null) {
            $class$groovy$swing$factory$BoxFactory = (SwingBuilder.$class$groovy$swing$factory$BoxFactory = class$("groovy.swing.factory.BoxFactory"));
        }
        return $class$groovy$swing$factory$BoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BevelBorderFactory() {
        Class $class$groovy$swing$factory$BevelBorderFactory;
        if (($class$groovy$swing$factory$BevelBorderFactory = SwingBuilder.$class$groovy$swing$factory$BevelBorderFactory) == null) {
            $class$groovy$swing$factory$BevelBorderFactory = (SwingBuilder.$class$groovy$swing$factory$BevelBorderFactory = class$("groovy.swing.factory.BevelBorderFactory"));
        }
        return $class$groovy$swing$factory$BevelBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Toolkit() {
        Class $class$java$awt$Toolkit;
        if (($class$java$awt$Toolkit = SwingBuilder.$class$java$awt$Toolkit) == null) {
            $class$java$awt$Toolkit = (SwingBuilder.$class$java$awt$Toolkit = class$("java.awt.Toolkit"));
        }
        return $class$java$awt$Toolkit;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = SwingBuilder.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (SwingBuilder.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CellEditorGetValueFactory() {
        Class $class$groovy$swing$factory$CellEditorGetValueFactory;
        if (($class$groovy$swing$factory$CellEditorGetValueFactory = SwingBuilder.$class$groovy$swing$factory$CellEditorGetValueFactory) == null) {
            $class$groovy$swing$factory$CellEditorGetValueFactory = (SwingBuilder.$class$groovy$swing$factory$CellEditorGetValueFactory = class$("groovy.swing.factory.CellEditorGetValueFactory"));
        }
        return $class$groovy$swing$factory$CellEditorGetValueFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$GlueFactory() {
        Class $class$groovy$swing$factory$GlueFactory;
        if (($class$groovy$swing$factory$GlueFactory = SwingBuilder.$class$groovy$swing$factory$GlueFactory) == null) {
            $class$groovy$swing$factory$GlueFactory = (SwingBuilder.$class$groovy$swing$factory$GlueFactory = class$("groovy.swing.factory.GlueFactory"));
        }
        return $class$groovy$swing$factory$GlueFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Component() {
        Class $class$java$awt$Component;
        if (($class$java$awt$Component = SwingBuilder.$class$java$awt$Component) == null) {
            $class$java$awt$Component = (SwingBuilder.$class$java$awt$Component = class$("java.awt.Component"));
        }
        return $class$java$awt$Component;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableModelFactory() {
        Class $class$groovy$swing$factory$TableModelFactory;
        if (($class$groovy$swing$factory$TableModelFactory = SwingBuilder.$class$groovy$swing$factory$TableModelFactory) == null) {
            $class$groovy$swing$factory$TableModelFactory = (SwingBuilder.$class$groovy$swing$factory$TableModelFactory = class$("groovy.swing.factory.TableModelFactory"));
        }
        return $class$groovy$swing$factory$TableModelFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridBagConstraints() {
        Class $class$java$awt$GridBagConstraints;
        if (($class$java$awt$GridBagConstraints = SwingBuilder.$class$java$awt$GridBagConstraints) == null) {
            $class$java$awt$GridBagConstraints = (SwingBuilder.$class$java$awt$GridBagConstraints = class$("java.awt.GridBagConstraints"));
        }
        return $class$java$awt$GridBagConstraints;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$SeparatorFactory() {
        Class $class$groovy$swing$factory$SeparatorFactory;
        if (($class$groovy$swing$factory$SeparatorFactory = SwingBuilder.$class$groovy$swing$factory$SeparatorFactory) == null) {
            $class$groovy$swing$factory$SeparatorFactory = (SwingBuilder.$class$groovy$swing$factory$SeparatorFactory = class$("groovy.swing.factory.SeparatorFactory"));
        }
        return $class$groovy$swing$factory$SeparatorFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TitledBorderFactory() {
        Class $class$groovy$swing$factory$TitledBorderFactory;
        if (($class$groovy$swing$factory$TitledBorderFactory = SwingBuilder.$class$groovy$swing$factory$TitledBorderFactory) == null) {
            $class$groovy$swing$factory$TitledBorderFactory = (SwingBuilder.$class$groovy$swing$factory$TitledBorderFactory = class$("groovy.swing.factory.TitledBorderFactory"));
        }
        return $class$groovy$swing$factory$TitledBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTextArea() {
        Class $class$javax$swing$JTextArea;
        if (($class$javax$swing$JTextArea = SwingBuilder.$class$javax$swing$JTextArea) == null) {
            $class$javax$swing$JTextArea = (SwingBuilder.$class$javax$swing$JTextArea = class$("javax.swing.JTextArea"));
        }
        return $class$javax$swing$JTextArea;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JDesktopPane() {
        Class $class$javax$swing$JDesktopPane;
        if (($class$javax$swing$JDesktopPane = SwingBuilder.$class$javax$swing$JDesktopPane) == null) {
            $class$javax$swing$JDesktopPane = (SwingBuilder.$class$javax$swing$JDesktopPane = class$("javax.swing.JDesktopPane"));
        }
        return $class$javax$swing$JDesktopPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JButton() {
        Class $class$javax$swing$JButton;
        if (($class$javax$swing$JButton = SwingBuilder.$class$javax$swing$JButton) == null) {
            $class$javax$swing$JButton = (SwingBuilder.$class$javax$swing$JButton = class$("javax.swing.JButton"));
        }
        return $class$javax$swing$JButton;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ClosureColumnFactory() {
        Class $class$groovy$swing$factory$ClosureColumnFactory;
        if (($class$groovy$swing$factory$ClosureColumnFactory = SwingBuilder.$class$groovy$swing$factory$ClosureColumnFactory) == null) {
            $class$groovy$swing$factory$ClosureColumnFactory = (SwingBuilder.$class$groovy$swing$factory$ClosureColumnFactory = class$("groovy.swing.factory.ClosureColumnFactory"));
        }
        return $class$groovy$swing$factory$ClosureColumnFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ComboBoxFactory() {
        Class $class$groovy$swing$factory$ComboBoxFactory;
        if (($class$groovy$swing$factory$ComboBoxFactory = SwingBuilder.$class$groovy$swing$factory$ComboBoxFactory) == null) {
            $class$groovy$swing$factory$ComboBoxFactory = (SwingBuilder.$class$groovy$swing$factory$ComboBoxFactory = class$("groovy.swing.factory.ComboBoxFactory"));
        }
        return $class$groovy$swing$factory$ComboBoxFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$MapFactory() {
        Class $class$groovy$swing$factory$MapFactory;
        if (($class$groovy$swing$factory$MapFactory = SwingBuilder.$class$groovy$swing$factory$MapFactory) == null) {
            $class$groovy$swing$factory$MapFactory = (SwingBuilder.$class$groovy$swing$factory$MapFactory = class$("groovy.swing.factory.MapFactory"));
        }
        return $class$groovy$swing$factory$MapFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$BevelBorder() {
        Class $class$javax$swing$border$BevelBorder;
        if (($class$javax$swing$border$BevelBorder = SwingBuilder.$class$javax$swing$border$BevelBorder) == null) {
            $class$javax$swing$border$BevelBorder = (SwingBuilder.$class$javax$swing$border$BevelBorder = class$("javax.swing.border.BevelBorder"));
        }
        return $class$javax$swing$border$BevelBorder;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFrame() {
        Class $class$javax$swing$JFrame;
        if (($class$javax$swing$JFrame = SwingBuilder.$class$javax$swing$JFrame) == null) {
            $class$javax$swing$JFrame = (SwingBuilder.$class$javax$swing$JFrame = class$("javax.swing.JFrame"));
        }
        return $class$javax$swing$JFrame;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SpinnerDateModel() {
        Class $class$javax$swing$SpinnerDateModel;
        if (($class$javax$swing$SpinnerDateModel = SwingBuilder.$class$javax$swing$SpinnerDateModel) == null) {
            $class$javax$swing$SpinnerDateModel = (SwingBuilder.$class$javax$swing$SpinnerDateModel = class$("javax.swing.SpinnerDateModel"));
        }
        return $class$javax$swing$SpinnerDateModel;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = SwingBuilder.$class$java$util$Map) == null) {
            $class$java$util$Map = (SwingBuilder.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$EtchedBorderFactory() {
        Class $class$groovy$swing$factory$EtchedBorderFactory;
        if (($class$groovy$swing$factory$EtchedBorderFactory = SwingBuilder.$class$groovy$swing$factory$EtchedBorderFactory) == null) {
            $class$groovy$swing$factory$EtchedBorderFactory = (SwingBuilder.$class$groovy$swing$factory$EtchedBorderFactory = class$("groovy.swing.factory.EtchedBorderFactory"));
        }
        return $class$groovy$swing$factory$EtchedBorderFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$border$EtchedBorder() {
        Class $class$javax$swing$border$EtchedBorder;
        if (($class$javax$swing$border$EtchedBorder = SwingBuilder.$class$javax$swing$border$EtchedBorder) == null) {
            $class$javax$swing$border$EtchedBorder = (SwingBuilder.$class$javax$swing$border$EtchedBorder = class$("javax.swing.border.EtchedBorder"));
        }
        return $class$javax$swing$border$EtchedBorder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$WidgetFactory() {
        Class $class$groovy$swing$factory$WidgetFactory;
        if (($class$groovy$swing$factory$WidgetFactory = SwingBuilder.$class$groovy$swing$factory$WidgetFactory) == null) {
            $class$groovy$swing$factory$WidgetFactory = (SwingBuilder.$class$groovy$swing$factory$WidgetFactory = class$("groovy.swing.factory.WidgetFactory"));
        }
        return $class$groovy$swing$factory$WidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$GridBagFactory() {
        Class $class$groovy$swing$factory$GridBagFactory;
        if (($class$groovy$swing$factory$GridBagFactory = SwingBuilder.$class$groovy$swing$factory$GridBagFactory) == null) {
            $class$groovy$swing$factory$GridBagFactory = (SwingBuilder.$class$groovy$swing$factory$GridBagFactory = class$("groovy.swing.factory.GridBagFactory"));
        }
        return $class$groovy$swing$factory$GridBagFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JMenu() {
        Class $class$javax$swing$JMenu;
        if (($class$javax$swing$JMenu = SwingBuilder.$class$javax$swing$JMenu) == null) {
            $class$javax$swing$JMenu = (SwingBuilder.$class$javax$swing$JMenu = class$("javax.swing.JMenu"));
        }
        return $class$javax$swing$JMenu;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JToggleButton() {
        Class $class$javax$swing$JToggleButton;
        if (($class$javax$swing$JToggleButton = SwingBuilder.$class$javax$swing$JToggleButton) == null) {
            $class$javax$swing$JToggleButton = (SwingBuilder.$class$javax$swing$JToggleButton = class$("javax.swing.JToggleButton"));
        }
        return $class$javax$swing$JToggleButton;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JCheckBox() {
        Class $class$javax$swing$JCheckBox;
        if (($class$javax$swing$JCheckBox = SwingBuilder.$class$javax$swing$JCheckBox) == null) {
            $class$javax$swing$JCheckBox = (SwingBuilder.$class$javax$swing$JCheckBox = class$("javax.swing.JCheckBox"));
        }
        return $class$javax$swing$JCheckBox;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JFileChooser() {
        Class $class$javax$swing$JFileChooser;
        if (($class$javax$swing$JFileChooser = SwingBuilder.$class$javax$swing$JFileChooser) == null) {
            $class$javax$swing$JFileChooser = (SwingBuilder.$class$javax$swing$JFileChooser = class$("javax.swing.JFileChooser"));
        }
        return $class$javax$swing$JFileChooser;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindProxyFactory() {
        Class $class$groovy$swing$factory$BindProxyFactory;
        if (($class$groovy$swing$factory$BindProxyFactory = SwingBuilder.$class$groovy$swing$factory$BindProxyFactory) == null) {
            $class$groovy$swing$factory$BindProxyFactory = (SwingBuilder.$class$groovy$swing$factory$BindProxyFactory = class$("groovy.swing.factory.BindProxyFactory"));
        }
        return $class$groovy$swing$factory$BindProxyFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindFactory() {
        Class $class$groovy$swing$factory$BindFactory;
        if (($class$groovy$swing$factory$BindFactory = SwingBuilder.$class$groovy$swing$factory$BindFactory) == null) {
            $class$groovy$swing$factory$BindFactory = (SwingBuilder.$class$groovy$swing$factory$BindFactory = class$("groovy.swing.factory.BindFactory"));
        }
        return $class$groovy$swing$factory$BindFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$HStrutFactory() {
        Class $class$groovy$swing$factory$HStrutFactory;
        if (($class$groovy$swing$factory$HStrutFactory = SwingBuilder.$class$groovy$swing$factory$HStrutFactory) == null) {
            $class$groovy$swing$factory$HStrutFactory = (SwingBuilder.$class$groovy$swing$factory$HStrutFactory = class$("groovy.swing.factory.HStrutFactory"));
        }
        return $class$groovy$swing$factory$HStrutFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TabbedPaneFactory() {
        Class $class$groovy$swing$factory$TabbedPaneFactory;
        if (($class$groovy$swing$factory$TabbedPaneFactory = SwingBuilder.$class$groovy$swing$factory$TabbedPaneFactory) == null) {
            $class$groovy$swing$factory$TabbedPaneFactory = (SwingBuilder.$class$groovy$swing$factory$TabbedPaneFactory = class$("groovy.swing.factory.TabbedPaneFactory"));
        }
        return $class$groovy$swing$factory$TabbedPaneFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$WindowFactory() {
        Class $class$groovy$swing$factory$WindowFactory;
        if (($class$groovy$swing$factory$WindowFactory = SwingBuilder.$class$groovy$swing$factory$WindowFactory) == null) {
            $class$groovy$swing$factory$WindowFactory = (SwingBuilder.$class$groovy$swing$factory$WindowFactory = class$("groovy.swing.factory.WindowFactory"));
        }
        return $class$groovy$swing$factory$WindowFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTextPane() {
        Class $class$javax$swing$JTextPane;
        if (($class$javax$swing$JTextPane = SwingBuilder.$class$javax$swing$JTextPane) == null) {
            $class$javax$swing$JTextPane = (SwingBuilder.$class$javax$swing$JTextPane = class$("javax.swing.JTextPane"));
        }
        return $class$javax$swing$JTextPane;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$DefaultBoundedRangeModel() {
        Class $class$javax$swing$DefaultBoundedRangeModel;
        if (($class$javax$swing$DefaultBoundedRangeModel = SwingBuilder.$class$javax$swing$DefaultBoundedRangeModel) == null) {
            $class$javax$swing$DefaultBoundedRangeModel = (SwingBuilder.$class$javax$swing$DefaultBoundedRangeModel = class$("javax.swing.DefaultBoundedRangeModel"));
        }
        return $class$javax$swing$DefaultBoundedRangeModel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JColorChooser() {
        Class $class$javax$swing$JColorChooser;
        if (($class$javax$swing$JColorChooser = SwingBuilder.$class$javax$swing$JColorChooser) == null) {
            $class$javax$swing$JColorChooser = (SwingBuilder.$class$javax$swing$JColorChooser = class$("javax.swing.JColorChooser"));
        }
        return $class$javax$swing$JColorChooser;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$BorderLayout() {
        Class $class$java$awt$BorderLayout;
        if (($class$java$awt$BorderLayout = SwingBuilder.$class$java$awt$BorderLayout) == null) {
            $class$java$awt$BorderLayout = (SwingBuilder.$class$java$awt$BorderLayout = class$("java.awt.BorderLayout"));
        }
        return $class$java$awt$BorderLayout;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$LookAndFeelHelper() {
        Class $class$groovy$swing$LookAndFeelHelper;
        if (($class$groovy$swing$LookAndFeelHelper = SwingBuilder.$class$groovy$swing$LookAndFeelHelper) == null) {
            $class$groovy$swing$LookAndFeelHelper = (SwingBuilder.$class$groovy$swing$LookAndFeelHelper = class$("groovy.swing.LookAndFeelHelper"));
        }
        return $class$groovy$swing$LookAndFeelHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CollectionFactory() {
        Class $class$groovy$swing$factory$CollectionFactory;
        if (($class$groovy$swing$factory$CollectionFactory = SwingBuilder.$class$groovy$swing$factory$CollectionFactory) == null) {
            $class$groovy$swing$factory$CollectionFactory = (SwingBuilder.$class$groovy$swing$factory$CollectionFactory = class$("groovy.swing.factory.CollectionFactory"));
        }
        return $class$groovy$swing$factory$CollectionFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RichActionWidgetFactory() {
        Class $class$groovy$swing$factory$RichActionWidgetFactory;
        if (($class$groovy$swing$factory$RichActionWidgetFactory = SwingBuilder.$class$groovy$swing$factory$RichActionWidgetFactory) == null) {
            $class$groovy$swing$factory$RichActionWidgetFactory = (SwingBuilder.$class$groovy$swing$factory$RichActionWidgetFactory = class$("groovy.swing.factory.RichActionWidgetFactory"));
        }
        return $class$groovy$swing$factory$RichActionWidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JCheckBoxMenuItem() {
        Class $class$javax$swing$JCheckBoxMenuItem;
        if (($class$javax$swing$JCheckBoxMenuItem = SwingBuilder.$class$javax$swing$JCheckBoxMenuItem) == null) {
            $class$javax$swing$JCheckBoxMenuItem = (SwingBuilder.$class$javax$swing$JCheckBoxMenuItem = class$("javax.swing.JCheckBoxMenuItem"));
        }
        return $class$javax$swing$JCheckBoxMenuItem;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = SwingBuilder.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (SwingBuilder.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$SplitPaneFactory() {
        Class $class$groovy$swing$factory$SplitPaneFactory;
        if (($class$groovy$swing$factory$SplitPaneFactory = SwingBuilder.$class$groovy$swing$factory$SplitPaneFactory) == null) {
            $class$groovy$swing$factory$SplitPaneFactory = (SwingBuilder.$class$groovy$swing$factory$SplitPaneFactory = class$("groovy.swing.factory.SplitPaneFactory"));
        }
        return $class$groovy$swing$factory$SplitPaneFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$CellEditorPrepareFactory() {
        Class $class$groovy$swing$factory$CellEditorPrepareFactory;
        if (($class$groovy$swing$factory$CellEditorPrepareFactory = SwingBuilder.$class$groovy$swing$factory$CellEditorPrepareFactory) == null) {
            $class$groovy$swing$factory$CellEditorPrepareFactory = (SwingBuilder.$class$groovy$swing$factory$CellEditorPrepareFactory = class$("groovy.swing.factory.CellEditorPrepareFactory"));
        }
        return $class$groovy$swing$factory$CellEditorPrepareFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ButtonGroupFactory() {
        Class $class$groovy$swing$factory$ButtonGroupFactory;
        if (($class$groovy$swing$factory$ButtonGroupFactory = SwingBuilder.$class$groovy$swing$factory$ButtonGroupFactory) == null) {
            $class$groovy$swing$factory$ButtonGroupFactory = (SwingBuilder.$class$groovy$swing$factory$ButtonGroupFactory = class$("groovy.swing.factory.ButtonGroupFactory"));
        }
        return $class$groovy$swing$factory$ButtonGroupFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$LayoutManager() {
        Class $class$java$awt$LayoutManager;
        if (($class$java$awt$LayoutManager = SwingBuilder.$class$java$awt$LayoutManager) == null) {
            $class$java$awt$LayoutManager = (SwingBuilder.$class$java$awt$LayoutManager = class$("java.awt.LayoutManager"));
        }
        return $class$java$awt$LayoutManager;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
