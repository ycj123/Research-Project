// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.awt.peer.ComponentPeer;
import java.awt.event.KeyEvent;
import java.awt.MenuComponent;
import javax.accessibility.AccessibleContext;
import java.awt.image.VolatileImage;
import java.awt.ImageCapabilities;
import java.applet.AppletContext;
import java.awt.event.InputMethodEvent;
import java.awt.AWTKeyStroke;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.im.InputContext;
import java.awt.event.KeyListener;
import java.awt.event.HierarchyEvent;
import javax.swing.JMenuBar;
import java.awt.image.ImageProducer;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.GraphicsConfiguration;
import java.io.PrintStream;
import java.awt.im.InputMethodRequests;
import java.awt.Toolkit;
import javax.swing.TransferHandler;
import java.io.PrintWriter;
import java.awt.Insets;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseWheelEvent;
import java.awt.PopupMenu;
import java.util.Set;
import java.awt.event.HierarchyBoundsListener;
import java.awt.Color;
import java.awt.FocusTraversalPolicy;
import java.applet.AppletStub;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import javax.swing.JLayeredPane;
import java.awt.event.ComponentListener;
import java.awt.image.ColorModel;
import java.awt.event.ContainerListener;
import java.awt.event.ContainerEvent;
import javax.swing.JRootPane;
import java.util.Locale;
import java.beans.PropertyChangeListener;
import java.awt.Image;
import java.awt.Container;
import java.awt.Event;
import java.awt.dnd.DropTarget;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelListener;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.event.HierarchyListener;
import java.applet.AudioClip;
import java.net.URL;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import javax.swing.JApplet;

public class ConsoleApplet extends JApplet implements GroovyObject
{
    private Console console;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205731;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$ui$ConsoleApplet;
    private static /* synthetic */ Class $class$groovy$ui$Console;
    
    public ConsoleApplet() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void start() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.console = (Console)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$groovy$ui$Console()), $get$$class$groovy$ui$Console());
        $getCallSiteArray[1].call(this.console, this);
    }
    
    public void stop() {
        $getCallSiteArray()[2].call(this.console);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$ConsoleApplet()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ConsoleApplet.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ConsoleApplet.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ConsoleApplet.__timeStamp__239_neverHappen1292524205731 = 0L;
        ConsoleApplet.__timeStamp = 1292524205731L;
    }
    
    public Console getConsole() {
        return this.console;
    }
    
    public void setConsole(final Console console) {
        this.console = console;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$ConsoleApplet(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConsoleApplet.$callSiteArray == null || ($createCallSiteArray = ConsoleApplet.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConsoleApplet.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ConsoleApplet.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ConsoleApplet.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$ConsoleApplet() {
        Class $class$groovy$ui$ConsoleApplet;
        if (($class$groovy$ui$ConsoleApplet = ConsoleApplet.$class$groovy$ui$ConsoleApplet) == null) {
            $class$groovy$ui$ConsoleApplet = (ConsoleApplet.$class$groovy$ui$ConsoleApplet = class$("groovy.ui.ConsoleApplet"));
        }
        return $class$groovy$ui$ConsoleApplet;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console() {
        Class $class$groovy$ui$Console;
        if (($class$groovy$ui$Console = ConsoleApplet.$class$groovy$ui$Console) == null) {
            $class$groovy$ui$Console = (ConsoleApplet.$class$groovy$ui$Console = class$("groovy.ui.Console"));
        }
        return $class$groovy$ui$Console;
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
