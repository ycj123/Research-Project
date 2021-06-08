// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.EventObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class ObjectBrowser implements GroovyObject
{
    private Object inspector;
    private Object swing;
    private Object frame;
    private Object fieldTable;
    private Object methodTable;
    private Object itemTable;
    private Object mapTable;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202711;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$Inspector;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ObjectBrowser;
    private static /* synthetic */ Class $class$groovy$swing$SwingBuilder;
    private static /* synthetic */ Class $class$javax$swing$WindowConstants;
    private static /* synthetic */ Class $class$groovy$ui$Console;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TableSorter;
    
    public ObjectBrowser() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].callStatic($get$$class$groovy$inspect$swingui$ObjectBrowser(), "some String");
    }
    
    public static void inspect(final Object objectUnderInspection) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object browser = $getCallSiteArray[1].callConstructor($get$$class$groovy$inspect$swingui$ObjectBrowser());
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].callConstructor($get$$class$groovy$inspect$Inspector(), objectUnderInspection), $get$$class$groovy$inspect$swingui$ObjectBrowser(), browser, "inspector");
        $getCallSiteArray[3].call(browser);
    }
    
    public void run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.swing = $getCallSiteArray[4].callConstructor($get$$class$groovy$swing$SwingBuilder());
        this.frame = $getCallSiteArray[5].call(this.swing, ScriptBytecodeAdapter.createMap(new Object[] { "title", "Groovy Object Browser", "location", ScriptBytecodeAdapter.createList(new Object[] { ObjectBrowser.$const$0, ObjectBrowser.$const$0 }), "size", ScriptBytecodeAdapter.createList(new Object[] { ObjectBrowser.$const$1, ObjectBrowser.$const$2 }), "pack", Boolean.TRUE, "show", Boolean.TRUE, "iconImage", $getCallSiteArray[6].callGetProperty($getCallSiteArray[7].call(this.swing, $getCallSiteArray[8].callGetProperty($get$$class$groovy$ui$Console()))), "defaultCloseOperation", $getCallSiteArray[9].callGetProperty($get$$class$javax$swing$WindowConstants()) }), new ObjectBrowser$_run_closure1(this, this));
        $getCallSiteArray[10].callCurrent(this, this.itemTable);
        $getCallSiteArray[11].callCurrent(this, this.mapTable);
        $getCallSiteArray[12].callCurrent(this, this.fieldTable);
        $getCallSiteArray[13].callCurrent(this, this.methodTable);
        $getCallSiteArray[14].call(this.frame);
    }
    
    public void addSorter(final Object table) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(table, null)) {
            final Object sorter = $getCallSiteArray[15].callConstructor($get$$class$groovy$inspect$swingui$TableSorter(), $getCallSiteArray[16].callGetProperty(table));
            ScriptBytecodeAdapter.setProperty(sorter, $get$$class$groovy$inspect$swingui$ObjectBrowser(), table, "model");
            $getCallSiteArray[17].call(sorter, table);
        }
    }
    
    public void showAbout(final EventObject evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object pane = $getCallSiteArray[18].call(this.swing);
        $getCallSiteArray[19].call(pane, "An interactive GUI to explore object capabilities.");
        final Object dialog = $getCallSiteArray[20].call(pane, this.frame, "About Groovy Object Browser");
        $getCallSiteArray[21].call(dialog);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$ObjectBrowser()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ObjectBrowser.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ObjectBrowser.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ObjectBrowser.__timeStamp__239_neverHappen1292524202711 = 0L;
        ObjectBrowser.__timeStamp = 1292524202711L;
        $const$2 = 600;
        $const$1 = 800;
        $const$0 = 200;
    }
    
    public Object getInspector() {
        return this.inspector;
    }
    
    public void setInspector(final Object inspector) {
        this.inspector = inspector;
    }
    
    public Object getSwing() {
        return this.swing;
    }
    
    public void setSwing(final Object swing) {
        this.swing = swing;
    }
    
    public Object getFrame() {
        return this.frame;
    }
    
    public void setFrame(final Object frame) {
        this.frame = frame;
    }
    
    public Object getFieldTable() {
        return this.fieldTable;
    }
    
    public void setFieldTable(final Object fieldTable) {
        this.fieldTable = fieldTable;
    }
    
    public Object getMethodTable() {
        return this.methodTable;
    }
    
    public void setMethodTable(final Object methodTable) {
        this.methodTable = methodTable;
    }
    
    public Object getItemTable() {
        return this.itemTable;
    }
    
    public void setItemTable(final Object itemTable) {
        this.itemTable = itemTable;
    }
    
    public Object getMapTable() {
        return this.mapTable;
    }
    
    public void setMapTable(final Object mapTable) {
        this.mapTable = mapTable;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[22];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$Inspector() {
        Class $class$groovy$inspect$Inspector;
        if (($class$groovy$inspect$Inspector = ObjectBrowser.$class$groovy$inspect$Inspector) == null) {
            $class$groovy$inspect$Inspector = (ObjectBrowser.$class$groovy$inspect$Inspector = class$("groovy.inspect.Inspector"));
        }
        return $class$groovy$inspect$Inspector;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ObjectBrowser.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ObjectBrowser.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ObjectBrowser() {
        Class $class$groovy$inspect$swingui$ObjectBrowser;
        if (($class$groovy$inspect$swingui$ObjectBrowser = ObjectBrowser.$class$groovy$inspect$swingui$ObjectBrowser) == null) {
            $class$groovy$inspect$swingui$ObjectBrowser = (ObjectBrowser.$class$groovy$inspect$swingui$ObjectBrowser = class$("groovy.inspect.swingui.ObjectBrowser"));
        }
        return $class$groovy$inspect$swingui$ObjectBrowser;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$SwingBuilder() {
        Class $class$groovy$swing$SwingBuilder;
        if (($class$groovy$swing$SwingBuilder = ObjectBrowser.$class$groovy$swing$SwingBuilder) == null) {
            $class$groovy$swing$SwingBuilder = (ObjectBrowser.$class$groovy$swing$SwingBuilder = class$("groovy.swing.SwingBuilder"));
        }
        return $class$groovy$swing$SwingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$WindowConstants() {
        Class $class$javax$swing$WindowConstants;
        if (($class$javax$swing$WindowConstants = ObjectBrowser.$class$javax$swing$WindowConstants) == null) {
            $class$javax$swing$WindowConstants = (ObjectBrowser.$class$javax$swing$WindowConstants = class$("javax.swing.WindowConstants"));
        }
        return $class$javax$swing$WindowConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console() {
        Class $class$groovy$ui$Console;
        if (($class$groovy$ui$Console = ObjectBrowser.$class$groovy$ui$Console) == null) {
            $class$groovy$ui$Console = (ObjectBrowser.$class$groovy$ui$Console = class$("groovy.ui.Console"));
        }
        return $class$groovy$ui$Console;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TableSorter() {
        Class $class$groovy$inspect$swingui$TableSorter;
        if (($class$groovy$inspect$swingui$TableSorter = ObjectBrowser.$class$groovy$inspect$swingui$TableSorter) == null) {
            $class$groovy$inspect$swingui$TableSorter = (ObjectBrowser.$class$groovy$inspect$swingui$TableSorter = class$("groovy.inspect.swingui.TableSorter"));
        }
        return $class$groovy$inspect$swingui$TableSorter;
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
