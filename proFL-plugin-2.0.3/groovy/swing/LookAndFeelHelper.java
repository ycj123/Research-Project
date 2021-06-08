// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GString;
import groovy.lang.Reference;
import javax.swing.LookAndFeel;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import groovy.lang.GroovyObject;

public class LookAndFeelHelper implements GroovyObject
{
    protected static LookAndFeelHelper instance;
    private Map lafCodeNames;
    private Map extendedAttributes;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202985;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$LookAndFeel;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$swing$LookAndFeelHelper;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    private static /* synthetic */ Class $class$groovy$util$FactoryBuilderSupport;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$util$Map;
    
    private LookAndFeelHelper() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.lafCodeNames = (Map)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createMap(new Object[] { "metal", "javax.swing.plaf.metal.MetalLookAndFeel", "nimbus", $getCallSiteArray[0].callStatic($get$$class$groovy$swing$LookAndFeelHelper()), "mac", $getCallSiteArray[1].callStatic($get$$class$groovy$swing$LookAndFeelHelper()), "motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel", "windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel", "win2k", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", "gtk", "com.sun.java.swing.plaf.gtk.GTKLookAndFeel", "synth", "javax.swing.plaf.synth.SynthLookAndFeel", "system", $getCallSiteArray[2].call($get$$class$javax$swing$UIManager()), "crossPlatform", $getCallSiteArray[3].call($get$$class$javax$swing$UIManager()), "plastic", "com.jgoodies.looks.plastic.PlasticLookAndFeel", "plastic3D", "com.jgoodies.looks.plastic.Plastic3DLookAndFeel", "plasticXP", "com.jgoodies.looks.plastic.PlasticXPLookAndFeel", "substance", $getCallSiteArray[4].callStatic($get$$class$groovy$swing$LookAndFeelHelper()), "napkin", "net.sourceforge.napkinlaf.NapkinLookAndFeel" }), $get$$class$java$util$Map());
        this.extendedAttributes = (Map)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createMap(new Object[] { "javax.swing.plaf.metal.MetalLookAndFeel", ScriptBytecodeAdapter.createMap(new Object[] { "theme", new LookAndFeelHelper$_closure1(this, this), "boldFonts", new LookAndFeelHelper$_closure2(this, this), "noxp", new LookAndFeelHelper$_closure3(this, this) }), "org.jvnet.substance.SubstanceLookAndFeel", ScriptBytecodeAdapter.createMap(new Object[] { "theme", new LookAndFeelHelper$_closure4(this, this), "skin", new LookAndFeelHelper$_closure5(this, this), "watermark", new LookAndFeelHelper$_closure6(this, this) }) }), $get$$class$java$util$Map());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[5].call($get$$class$javax$swing$UIManager());
    }
    
    public static LookAndFeelHelper getInstance() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = LookAndFeelHelper.instance)) {
            LookAndFeelHelper.instance = (LookAndFeelHelper)(object = $getCallSiteArray[6].callConstructor($get$$class$groovy$swing$LookAndFeelHelper()));
        }
        return (LookAndFeelHelper)ScriptBytecodeAdapter.castToType(object, $get$$class$groovy$swing$LookAndFeelHelper());
    }
    
    public String addLookAndFeelAlias(final String alias, final String className) {
        $getCallSiteArray()[7].call(this.lafCodeNames, alias, className);
        return (String)ScriptBytecodeAdapter.castToType(className, $get$$class$java$lang$String());
    }
    
    public String addLookAndFeelAttributeHandler(final String className, final String attr, final Closure handler) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Map attrs = (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(this.extendedAttributes, className), $get$$class$java$util$Map());
        if (ScriptBytecodeAdapter.compareEqual(attrs, null)) {
            attrs = ScriptBytecodeAdapter.createMap(new Object[0]);
            $getCallSiteArray[9].call(this.extendedAttributes, className, attrs);
        }
        $getCallSiteArray[10].call(attrs, attr, handler);
        return (String)ScriptBytecodeAdapter.castToType(handler, $get$$class$java$lang$String());
    }
    
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public LookAndFeel lookAndFeel(Object value, final Map attributes, Closure initClosure) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final LookAndFeel lafInstance = (LookAndFeel)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$LookAndFeel()));
        final String lafClassName = (String)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String()));
        if (DefaultTypeTransformation.booleanUnbox((value instanceof Closure && ScriptBytecodeAdapter.compareEqual(initClosure, null)) ? Boolean.TRUE : Boolean.FALSE)) {
            initClosure = (Closure)ScriptBytecodeAdapter.castToType(value, $get$$class$groovy$lang$Closure());
            value = null;
        }
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            value = $getCallSiteArray[11].call(attributes, "lookAndFeel");
        }
        if (value instanceof GString) {
            value = ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call($get$$class$groovy$util$FactoryBuilderSupport(), value, "lookAndFeel", $get$$class$javax$swing$LookAndFeel()))) {
            ((Reference<LookAndFeel>)lafInstance).set((LookAndFeel)ScriptBytecodeAdapter.castToType(value, $get$$class$javax$swing$LookAndFeel()));
            ((Reference<String>)lafClassName).set((String)ScriptBytecodeAdapter.castToType($getCallSiteArray[13].callGetProperty($getCallSiteArray[14].callGetProperty(((Reference<Object>)lafInstance).get())), $get$$class$java$lang$String()));
        }
        else if (ScriptBytecodeAdapter.compareNotEqual(value, null)) {
            Object call;
            if (!DefaultTypeTransformation.booleanUnbox(call = $getCallSiteArray[15].call(this.lafCodeNames, value))) {
                call = value;
            }
            ((Reference<String>)lafClassName).set((String)ScriptBytecodeAdapter.castToType(call, $get$$class$java$lang$String()));
            ((Reference<LookAndFeel>)lafInstance).set((LookAndFeel)ScriptBytecodeAdapter.castToType($getCallSiteArray[16].call($getCallSiteArray[17].call($get$$class$java$lang$Class(), ((Reference<Object>)lafClassName).get(), Boolean.TRUE, $getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callCurrent(this)))), $get$$class$javax$swing$LookAndFeel()));
        }
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[20].call(this.extendedAttributes, ((Reference<Object>)lafClassName).get()))) {
            object = ScriptBytecodeAdapter.createMap(new Object[0]);
        }
        final Map possibleAttributes = (Map)new Reference(ScriptBytecodeAdapter.castToType(object, $get$$class$java$util$Map()));
        $getCallSiteArray[21].call(attributes, new LookAndFeelHelper$_lookAndFeel_closure7(this, this, (Reference<Object>)lafInstance, (Reference<Object>)possibleAttributes, (Reference<Object>)lafClassName));
        if (DefaultTypeTransformation.booleanUnbox(initClosure)) {
            $getCallSiteArray[22].call(initClosure, ((Reference<Object>)lafInstance).get());
        }
        $getCallSiteArray[23].call($get$$class$javax$swing$UIManager(), ((Reference<Object>)lafInstance).get());
        return (LookAndFeel)ScriptBytecodeAdapter.castToType(((Reference<Object>)lafInstance).get(), $get$$class$javax$swing$LookAndFeel());
    }
    
    public static String getNimbusLAFName() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object klass = null;
        final Object call = $getCallSiteArray[24].call(ScriptBytecodeAdapter.createList(new Object[] { "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", "sun.swing.plaf.nimbus.NimbusLookAndFeel", "org.jdesktop.swingx.plaf.nimbus.NimbusLookAndFeel" }));
        while (((Iterator)call).hasNext()) {
            klass = ((Iterator<Object>)call).next();
            try {
                final String s = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[25].call($getCallSiteArray[26].call($get$$class$java$lang$Class(), klass)), $get$$class$java$lang$String());
                try {
                    return s;
                }
                catch (Throwable t) {}
            }
            catch (Throwable t2) {}
        }
        return (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
    }
    
    public static String getAquaLAFName() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object klass = null;
        final Object call = $getCallSiteArray[27].call(ScriptBytecodeAdapter.createList(new Object[] { "com.apple.laf.AquaLookAndFeel", "apple.laf.AquaLookAndFeel" }));
        while (((Iterator)call).hasNext()) {
            klass = ((Iterator<Object>)call).next();
            try {
                final String s = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[28].call($getCallSiteArray[29].call($get$$class$java$lang$Class(), klass)), $get$$class$java$lang$String());
                try {
                    return s;
                }
                catch (Throwable t) {}
            }
            catch (Throwable t2) {}
        }
        return (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
    }
    
    public static String getSubstanceLAFName() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object klass = null;
        final Object call = $getCallSiteArray[30].call(ScriptBytecodeAdapter.createList(new Object[] { "org.pushingpixels.substance.api.SubstanceLookAndFeel", "org.jvnet.substance.SubstanceLookAndFeel" }));
        while (((Iterator)call).hasNext()) {
            klass = ((Iterator<Object>)call).next();
            try {
                final String s = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[31].call($getCallSiteArray[32].call($get$$class$java$lang$Class(), klass)), $get$$class$java$lang$String());
                try {
                    return s;
                }
                catch (Throwable t) {}
            }
            catch (Throwable t2) {}
        }
        return (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$LookAndFeelHelper()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = LookAndFeelHelper.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (LookAndFeelHelper.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        LookAndFeelHelper.__timeStamp__239_neverHappen1292524202985 = 0L;
        LookAndFeelHelper.__timeStamp = 1292524202985L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[33];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$LookAndFeelHelper(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LookAndFeelHelper.$callSiteArray == null || ($createCallSiteArray = LookAndFeelHelper.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LookAndFeelHelper.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$LookAndFeel() {
        Class $class$javax$swing$LookAndFeel;
        if (($class$javax$swing$LookAndFeel = LookAndFeelHelper.$class$javax$swing$LookAndFeel) == null) {
            $class$javax$swing$LookAndFeel = (LookAndFeelHelper.$class$javax$swing$LookAndFeel = class$("javax.swing.LookAndFeel"));
        }
        return $class$javax$swing$LookAndFeel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = LookAndFeelHelper.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (LookAndFeelHelper.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = LookAndFeelHelper.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (LookAndFeelHelper.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$LookAndFeelHelper() {
        Class $class$groovy$swing$LookAndFeelHelper;
        if (($class$groovy$swing$LookAndFeelHelper = LookAndFeelHelper.$class$groovy$swing$LookAndFeelHelper) == null) {
            $class$groovy$swing$LookAndFeelHelper = (LookAndFeelHelper.$class$groovy$swing$LookAndFeelHelper = class$("groovy.swing.LookAndFeelHelper"));
        }
        return $class$groovy$swing$LookAndFeelHelper;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = LookAndFeelHelper.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (LookAndFeelHelper.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FactoryBuilderSupport() {
        Class $class$groovy$util$FactoryBuilderSupport;
        if (($class$groovy$util$FactoryBuilderSupport = LookAndFeelHelper.$class$groovy$util$FactoryBuilderSupport) == null) {
            $class$groovy$util$FactoryBuilderSupport = (LookAndFeelHelper.$class$groovy$util$FactoryBuilderSupport = class$("groovy.util.FactoryBuilderSupport"));
        }
        return $class$groovy$util$FactoryBuilderSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = LookAndFeelHelper.$class$java$lang$String) == null) {
            $class$java$lang$String = (LookAndFeelHelper.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = LookAndFeelHelper.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (LookAndFeelHelper.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = LookAndFeelHelper.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (LookAndFeelHelper.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = LookAndFeelHelper.$class$java$util$Map) == null) {
            $class$java$util$Map = (LookAndFeelHelper.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
