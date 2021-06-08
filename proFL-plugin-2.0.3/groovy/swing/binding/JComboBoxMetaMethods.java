// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JComboBox;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JComboBoxMetaMethods implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203049;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$binding$JComboBoxMetaMethods;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    
    public JComboBoxMetaMethods() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void enhanceMetaClass(final JComboBox comboBox) {
        $getCallSiteArray()[0].call($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), comboBox, ScriptBytecodeAdapter.createMap(new Object[] { "getElements", new JComboBoxMetaMethods$_enhanceMetaClass_closure1($get$$class$groovy$swing$binding$JComboBoxMetaMethods(), $get$$class$groovy$swing$binding$JComboBoxMetaMethods()), "getSelectedElement", new JComboBoxMetaMethods$_enhanceMetaClass_closure2($get$$class$groovy$swing$binding$JComboBoxMetaMethods(), $get$$class$groovy$swing$binding$JComboBoxMetaMethods()), "setSelectedElement", new JComboBoxMetaMethods$_enhanceMetaClass_closure3($get$$class$groovy$swing$binding$JComboBoxMetaMethods(), $get$$class$groovy$swing$binding$JComboBoxMetaMethods()) }));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JComboBoxMetaMethods()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JComboBoxMetaMethods.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JComboBoxMetaMethods.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JComboBoxMetaMethods.__timeStamp__239_neverHappen1292524203049 = 0L;
        JComboBoxMetaMethods.__timeStamp = 1292524203049L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JComboBoxMetaMethods(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JComboBoxMetaMethods.$callSiteArray == null || ($createCallSiteArray = JComboBoxMetaMethods.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JComboBoxMetaMethods.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JComboBoxMetaMethods.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JComboBoxMetaMethods.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JComboBoxMetaMethods() {
        Class $class$groovy$swing$binding$JComboBoxMetaMethods;
        if (($class$groovy$swing$binding$JComboBoxMetaMethods = JComboBoxMetaMethods.$class$groovy$swing$binding$JComboBoxMetaMethods) == null) {
            $class$groovy$swing$binding$JComboBoxMetaMethods = (JComboBoxMetaMethods.$class$groovy$swing$binding$JComboBoxMetaMethods = class$("groovy.swing.binding.JComboBoxMetaMethods"));
        }
        return $class$groovy$swing$binding$JComboBoxMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods() {
        Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
        if (($class$groovy$swing$binding$AbstractSyntheticMetaMethods = JComboBoxMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods) == null) {
            $class$groovy$swing$binding$AbstractSyntheticMetaMethods = (JComboBoxMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods = class$("groovy.swing.binding.AbstractSyntheticMetaMethods"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
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
