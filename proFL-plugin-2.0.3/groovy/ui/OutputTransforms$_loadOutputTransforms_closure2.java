// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.awt.Dimension;
import javax.swing.JComponent;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class OutputTransforms$_loadOutputTransforms_closure2 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$ImageIcon;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$awt$Dimension;
    private static /* synthetic */ Class $class$java$awt$Graphics2D;
    private static /* synthetic */ Class $class$java$awt$image$BufferedImage;
    private static /* synthetic */ Class $class$java$awt$Transparency;
    private static /* synthetic */ Class $class$java$awt$GraphicsConfiguration;
    private static /* synthetic */ Class $class$java$awt$GraphicsEnvironment;
    private static /* synthetic */ Class $class$java$awt$GraphicsDevice;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2;
    
    public OutputTransforms$_loadOutputTransforms_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)it2).get() instanceof JComponent) {
            final Dimension d = (Dimension)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()), $get$$class$java$awt$Dimension()));
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[1].callGetProperty(((Reference<Object>)d).get()), OutputTransforms$_loadOutputTransforms_closure2.$const$0)) {
                ((Reference<Dimension>)d).set((Dimension)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callGetProperty(((Reference<Object>)it2).get()), $get$$class$java$awt$Dimension()));
                ScriptBytecodeAdapter.setProperty(((Reference<Object>)d).get(), $get$$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2(), ((Reference<Object>)it2).get(), "size");
            }
            final GraphicsEnvironment ge = (GraphicsEnvironment)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callGetProperty($get$$class$java$awt$GraphicsEnvironment()), $get$$class$java$awt$GraphicsEnvironment());
            final GraphicsDevice gs = (GraphicsDevice)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callGetProperty(ge), $get$$class$java$awt$GraphicsDevice());
            final GraphicsConfiguration gc = (GraphicsConfiguration)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callGetProperty(gs), $get$$class$java$awt$GraphicsConfiguration());
            final BufferedImage image = (BufferedImage)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(gc, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[7].callGetProperty(((Reference<Object>)d).get()), $get$$class$java$lang$Integer()), Integer.TYPE), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[8].callGetProperty(((Reference<Object>)d).get()), $get$$class$java$lang$Integer()), Integer.TYPE), $getCallSiteArray[9].callGetProperty($get$$class$java$awt$Transparency())), $get$$class$java$awt$image$BufferedImage());
            final Graphics2D g2 = (Graphics2D)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(image), $get$$class$java$awt$Graphics2D());
            $getCallSiteArray[11].call(((Reference<Object>)it2).get(), g2);
            $getCallSiteArray[12].call(g2);
            return $getCallSiteArray[13].callConstructor($get$$class$javax$swing$ImageIcon(), image);
        }
        return null;
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OutputTransforms$_loadOutputTransforms_closure2.$callSiteArray == null || ($createCallSiteArray = OutputTransforms$_loadOutputTransforms_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OutputTransforms$_loadOutputTransforms_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ImageIcon() {
        Class $class$javax$swing$ImageIcon;
        if (($class$javax$swing$ImageIcon = OutputTransforms$_loadOutputTransforms_closure2.$class$javax$swing$ImageIcon) == null) {
            $class$javax$swing$ImageIcon = (OutputTransforms$_loadOutputTransforms_closure2.$class$javax$swing$ImageIcon = class$("javax.swing.ImageIcon"));
        }
        return $class$javax$swing$ImageIcon;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = OutputTransforms$_loadOutputTransforms_closure2.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Dimension() {
        Class $class$java$awt$Dimension;
        if (($class$java$awt$Dimension = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Dimension) == null) {
            $class$java$awt$Dimension = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Dimension = class$("java.awt.Dimension"));
        }
        return $class$java$awt$Dimension;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Graphics2D() {
        Class $class$java$awt$Graphics2D;
        if (($class$java$awt$Graphics2D = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Graphics2D) == null) {
            $class$java$awt$Graphics2D = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Graphics2D = class$("java.awt.Graphics2D"));
        }
        return $class$java$awt$Graphics2D;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$image$BufferedImage() {
        Class $class$java$awt$image$BufferedImage;
        if (($class$java$awt$image$BufferedImage = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$image$BufferedImage) == null) {
            $class$java$awt$image$BufferedImage = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$image$BufferedImage = class$("java.awt.image.BufferedImage"));
        }
        return $class$java$awt$image$BufferedImage;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Transparency() {
        Class $class$java$awt$Transparency;
        if (($class$java$awt$Transparency = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Transparency) == null) {
            $class$java$awt$Transparency = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$Transparency = class$("java.awt.Transparency"));
        }
        return $class$java$awt$Transparency;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GraphicsConfiguration() {
        Class $class$java$awt$GraphicsConfiguration;
        if (($class$java$awt$GraphicsConfiguration = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsConfiguration) == null) {
            $class$java$awt$GraphicsConfiguration = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsConfiguration = class$("java.awt.GraphicsConfiguration"));
        }
        return $class$java$awt$GraphicsConfiguration;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GraphicsEnvironment() {
        Class $class$java$awt$GraphicsEnvironment;
        if (($class$java$awt$GraphicsEnvironment = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsEnvironment) == null) {
            $class$java$awt$GraphicsEnvironment = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsEnvironment = class$("java.awt.GraphicsEnvironment"));
        }
        return $class$java$awt$GraphicsEnvironment;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GraphicsDevice() {
        Class $class$java$awt$GraphicsDevice;
        if (($class$java$awt$GraphicsDevice = OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsDevice) == null) {
            $class$java$awt$GraphicsDevice = (OutputTransforms$_loadOutputTransforms_closure2.$class$java$awt$GraphicsDevice = class$("java.awt.GraphicsDevice"));
        }
        return $class$java$awt$GraphicsDevice;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2() {
        Class $class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2;
        if (($class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2 = OutputTransforms$_loadOutputTransforms_closure2.$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2) == null) {
            $class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2 = (OutputTransforms$_loadOutputTransforms_closure2.$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2 = class$("groovy.ui.OutputTransforms$_loadOutputTransforms_closure2"));
        }
        return $class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure2;
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
