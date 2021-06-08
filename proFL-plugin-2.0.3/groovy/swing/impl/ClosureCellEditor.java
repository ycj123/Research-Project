// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.EventObject;
import javax.swing.event.CellEditorListener;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.awt.Component;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import javax.swing.JTree;
import javax.swing.JTable;
import java.util.List;
import groovy.lang.Closure;
import java.util.Map;
import groovy.lang.GroovyObject;
import javax.swing.tree.TreeCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class ClosureCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor, GroovyObject
{
    private Map<String, Closure> callbacks;
    private Closure prepareEditor;
    private Closure editorValue;
    private List children;
    private boolean defaultEditor;
    private JTable table;
    private JTree tree;
    private Object value;
    private boolean selected;
    private boolean expanded;
    private boolean leaf;
    private int row;
    private int column;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204572;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$DefaultCellEditor;
    private static /* synthetic */ Class $class$javax$swing$JTextField;
    private static /* synthetic */ Class $class$javax$swing$JTree;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$awt$Component;
    private static /* synthetic */ Class $class$javax$swing$table$TableCellEditor;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$impl$ClosureCellEditor;
    private static /* synthetic */ Class $class$javax$swing$JTable;
    
    public ClosureCellEditor(final Closure c, final Map<String, Closure> callbacks) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.callbacks = (Map<String, Closure>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map());
        this.children = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.editorValue = (Closure)ScriptBytecodeAdapter.castToType(c, $get$$class$groovy$lang$Closure());
        $getCallSiteArray[0].call(this.callbacks, callbacks);
    }
    
    public ClosureCellEditor(final Closure c) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = ScriptBytecodeAdapter.createGroovyObjectWrapper(c, $get$$class$groovy$lang$Closure());
        arguments[1] = ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$groovy$swing$impl$ClosureCellEditor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Closure)array[0]);
                break;
            }
            case 2: {
                this((Closure)array2[0], (Map<String, Closure>)array3[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public ClosureCellEditor() {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[2]));
        arguments[0] = ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure());
        arguments[1] = ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$groovy$swing$impl$ClosureCellEditor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Closure)array[0]);
                break;
            }
            case 2: {
                this((Closure)array2[0], (Map<String, Closure>)array3[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.table = (JTable)ScriptBytecodeAdapter.castToType(table, $get$$class$javax$swing$JTable());
        this.tree = (JTree)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JTree());
        this.value = ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        this.selected = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(isSelected));
        this.expanded = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.leaf = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.row = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(row));
        this.column = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(column));
        return (Component)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callCurrent(this), $get$$class$java$awt$Component());
    }
    
    public Component getTreeCellEditorComponent(final JTree tree, final Object value, final boolean isSelected, final boolean expanded, final boolean leaf, final int row) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.table = (JTable)ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JTable());
        this.tree = (JTree)ScriptBytecodeAdapter.castToType(tree, $get$$class$javax$swing$JTree());
        this.value = ScriptBytecodeAdapter.castToType(value, $get$$class$java$lang$Object());
        this.selected = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(isSelected));
        this.expanded = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(expanded));
        this.leaf = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(leaf));
        this.row = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(row));
        this.column = DefaultTypeTransformation.intUnbox(ClosureCellEditor.$const$0);
        return (Component)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callCurrent(this), $get$$class$java$awt$Component());
    }
    
    private Component prepare() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(this.children)) && !DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.defaultEditor))) ? Boolean.FALSE : Boolean.TRUE)) {
            this.defaultEditor = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
            $getCallSiteArray[4].call(this.children);
            if (DefaultTypeTransformation.booleanUnbox(this.table)) {
                final TableCellEditor tce = (TableCellEditor)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(this.table, $getCallSiteArray[6].call(this.table, DefaultTypeTransformation.box(this.column))), $get$$class$javax$swing$table$TableCellEditor());
                $getCallSiteArray[7].call(this.children, $getCallSiteArray[8].call(tce, ArrayUtil.createArray(this.table, this.value, DefaultTypeTransformation.box(this.selected), DefaultTypeTransformation.box(this.row), DefaultTypeTransformation.box(this.column))));
            }
            else if (DefaultTypeTransformation.booleanUnbox(this.tree)) {
                final TreeCellEditor tce2 = (TreeCellEditor)$getCallSiteArray[9].callConstructor($get$$class$javax$swing$DefaultCellEditor(), $getCallSiteArray[10].callConstructor($get$$class$javax$swing$JTextField()));
                $getCallSiteArray[11].call(this.children, $getCallSiteArray[12].call(tce2, ArrayUtil.createArray(this.tree, this.value, DefaultTypeTransformation.box(this.selected), DefaultTypeTransformation.box(this.expanded), DefaultTypeTransformation.box(this.leaf), DefaultTypeTransformation.box(this.row))));
            }
        }
        final Object o = $getCallSiteArray[13].call(this.prepareEditor);
        if (o instanceof Component) {
            return (Component)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(o, $get$$class$java$awt$Component()), $get$$class$java$awt$Component());
        }
        return (Component)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[14].call(this.children, ClosureCellEditor.$const$1), $get$$class$java$awt$Component()), $get$$class$java$awt$Component());
    }
    
    public Object getCellEditorValue() {
        return ScriptBytecodeAdapter.castToType($getCallSiteArray()[15].call(this.editorValue), $get$$class$java$lang$Object());
    }
    
    public void setEditorValue(final Closure editorValue) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(editorValue, null)) {
            ScriptBytecodeAdapter.setGroovyObjectProperty(this, $get$$class$groovy$swing$impl$ClosureCellEditor(), editorValue, "delegate");
            ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[16].callGetProperty($get$$class$groovy$lang$Closure()), $get$$class$groovy$swing$impl$ClosureCellEditor(), editorValue, "resolveStrategy");
        }
        this.editorValue = (Closure)ScriptBytecodeAdapter.castToType(editorValue, $get$$class$groovy$lang$Closure());
    }
    
    public void setPrepareEditor(final Closure prepareEditor) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(prepareEditor, null)) {
            ScriptBytecodeAdapter.setGroovyObjectProperty(this, $get$$class$groovy$swing$impl$ClosureCellEditor(), prepareEditor, "delegate");
            ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[17].callGetProperty($get$$class$groovy$lang$Closure()), $get$$class$groovy$swing$impl$ClosureCellEditor(), prepareEditor, "resolveStrategy");
        }
        this.prepareEditor = (Closure)ScriptBytecodeAdapter.castToType(prepareEditor, $get$$class$groovy$lang$Closure());
    }
    
    public Object invokeMethod(final String name, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object calledMethod = $getCallSiteArray[18].call($getCallSiteArray[19].callGetProperty($get$$class$groovy$swing$impl$ClosureCellEditor()), name, args);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.getProperty($get$$class$groovy$swing$impl$ClosureCellEditor(), this.callbacks, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { name }, new String[] { "", "" }), $get$$class$java$lang$String()))) && ScriptBytecodeAdapter.getProperty($get$$class$groovy$swing$impl$ClosureCellEditor(), this.callbacks, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { name }, new String[] { "", "" }), $get$$class$java$lang$String())) instanceof Closure) ? Boolean.TRUE : Boolean.FALSE)) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call(ScriptBytecodeAdapter.getProperty($get$$class$groovy$swing$impl$ClosureCellEditor(), this.callbacks, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { name }, new String[] { "", "" }), $get$$class$java$lang$String())), calledMethod, this, args), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[21].callSafe(calledMethod, this, args), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$impl$ClosureCellEditor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ClosureCellEditor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ClosureCellEditor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ClosureCellEditor.__timeStamp__239_neverHappen1292524204572 = 0L;
        ClosureCellEditor.__timeStamp = 1292524204572L;
        $const$1 = 0;
        $const$0 = -1;
    }
    
    public Map<String, Closure> getCallbacks() {
        return this.callbacks;
    }
    
    public void setCallbacks(final Map<String, Closure> callbacks) {
        this.callbacks = callbacks;
    }
    
    public Closure getPrepareEditor() {
        return this.prepareEditor;
    }
    
    public Closure getEditorValue() {
        return this.editorValue;
    }
    
    public List getChildren() {
        return this.children;
    }
    
    public void setChildren(final List children) {
        this.children = children;
    }
    
    public boolean getDefaultEditor() {
        return this.defaultEditor;
    }
    
    public boolean isDefaultEditor() {
        return this.defaultEditor;
    }
    
    public void setDefaultEditor(final boolean defaultEditor) {
        this.defaultEditor = defaultEditor;
    }
    
    public JTable getTable() {
        return this.table;
    }
    
    public void setTable(final JTable table) {
        this.table = table;
    }
    
    public JTree getTree() {
        return this.tree;
    }
    
    public void setTree(final JTree tree) {
        this.tree = tree;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public boolean getSelected() {
        return this.selected;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
    
    public boolean getExpanded() {
        return this.expanded;
    }
    
    public boolean isExpanded() {
        return this.expanded;
    }
    
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
    
    public boolean getLeaf() {
        return this.leaf;
    }
    
    public boolean isLeaf() {
        return this.leaf;
    }
    
    public void setLeaf(final boolean leaf) {
        this.leaf = leaf;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public void setRow(final int row) {
        this.row = row;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public void setColumn(final int column) {
        this.column = column;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[22];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$impl$ClosureCellEditor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ClosureCellEditor.$callSiteArray == null || ($createCallSiteArray = ClosureCellEditor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ClosureCellEditor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$DefaultCellEditor() {
        Class $class$javax$swing$DefaultCellEditor;
        if (($class$javax$swing$DefaultCellEditor = ClosureCellEditor.$class$javax$swing$DefaultCellEditor) == null) {
            $class$javax$swing$DefaultCellEditor = (ClosureCellEditor.$class$javax$swing$DefaultCellEditor = class$("javax.swing.DefaultCellEditor"));
        }
        return $class$javax$swing$DefaultCellEditor;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTextField() {
        Class $class$javax$swing$JTextField;
        if (($class$javax$swing$JTextField = ClosureCellEditor.$class$javax$swing$JTextField) == null) {
            $class$javax$swing$JTextField = (ClosureCellEditor.$class$javax$swing$JTextField = class$("javax.swing.JTextField"));
        }
        return $class$javax$swing$JTextField;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTree() {
        Class $class$javax$swing$JTree;
        if (($class$javax$swing$JTree = ClosureCellEditor.$class$javax$swing$JTree) == null) {
            $class$javax$swing$JTree = (ClosureCellEditor.$class$javax$swing$JTree = class$("javax.swing.JTree"));
        }
        return $class$javax$swing$JTree;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ClosureCellEditor.$class$java$util$List) == null) {
            $class$java$util$List = (ClosureCellEditor.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ClosureCellEditor.$class$java$lang$String) == null) {
            $class$java$lang$String = (ClosureCellEditor.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = ClosureCellEditor.$class$java$util$Map) == null) {
            $class$java$util$Map = (ClosureCellEditor.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = ClosureCellEditor.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (ClosureCellEditor.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Component() {
        Class $class$java$awt$Component;
        if (($class$java$awt$Component = ClosureCellEditor.$class$java$awt$Component) == null) {
            $class$java$awt$Component = (ClosureCellEditor.$class$java$awt$Component = class$("java.awt.Component"));
        }
        return $class$java$awt$Component;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$table$TableCellEditor() {
        Class $class$javax$swing$table$TableCellEditor;
        if (($class$javax$swing$table$TableCellEditor = ClosureCellEditor.$class$javax$swing$table$TableCellEditor) == null) {
            $class$javax$swing$table$TableCellEditor = (ClosureCellEditor.$class$javax$swing$table$TableCellEditor = class$("javax.swing.table.TableCellEditor"));
        }
        return $class$javax$swing$table$TableCellEditor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ClosureCellEditor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ClosureCellEditor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ClosureCellEditor.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ClosureCellEditor.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$impl$ClosureCellEditor() {
        Class $class$groovy$swing$impl$ClosureCellEditor;
        if (($class$groovy$swing$impl$ClosureCellEditor = ClosureCellEditor.$class$groovy$swing$impl$ClosureCellEditor) == null) {
            $class$groovy$swing$impl$ClosureCellEditor = (ClosureCellEditor.$class$groovy$swing$impl$ClosureCellEditor = class$("groovy.swing.impl.ClosureCellEditor"));
        }
        return $class$groovy$swing$impl$ClosureCellEditor;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JTable() {
        Class $class$javax$swing$JTable;
        if (($class$javax$swing$JTable = ClosureCellEditor.$class$javax$swing$JTable) == null) {
            $class$javax$swing$JTable = (ClosureCellEditor.$class$javax$swing$JTable = class$("javax.swing.JTable"));
        }
        return $class$javax$swing$JTable;
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
