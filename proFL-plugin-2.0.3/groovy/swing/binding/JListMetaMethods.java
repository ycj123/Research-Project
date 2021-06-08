// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.JList;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class JListMetaMethods implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203065;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListMetaMethods;
    
    public JListMetaMethods() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void enhanceMetaClass(final JList list) {
        $getCallSiteArray()[0].call($get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods(), list, ScriptBytecodeAdapter.createMap(new Object[] { "getElements", new JListMetaMethods$_enhanceMetaClass_closure1($get$$class$groovy$swing$binding$JListMetaMethods(), $get$$class$groovy$swing$binding$JListMetaMethods()), "getSelectedElement", new JListMetaMethods$_enhanceMetaClass_closure2($get$$class$groovy$swing$binding$JListMetaMethods(), $get$$class$groovy$swing$binding$JListMetaMethods()), "getSelectedElements", new JListMetaMethods$_enhanceMetaClass_closure3($get$$class$groovy$swing$binding$JListMetaMethods(), $get$$class$groovy$swing$binding$JListMetaMethods()), "setSelectedElement", new JListMetaMethods$_enhanceMetaClass_closure4($get$$class$groovy$swing$binding$JListMetaMethods(), $get$$class$groovy$swing$binding$JListMetaMethods()), "setSelectedValue", new JListMetaMethods$_enhanceMetaClass_closure5($get$$class$groovy$swing$binding$JListMetaMethods(), $get$$class$groovy$swing$binding$JListMetaMethods()) }));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListMetaMethods()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListMetaMethods.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListMetaMethods.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JListMetaMethods.__timeStamp__239_neverHappen1292524203065 = 0L;
        JListMetaMethods.__timeStamp = 1292524203065L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListMetaMethods(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListMetaMethods.$callSiteArray == null || ($createCallSiteArray = JListMetaMethods.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListMetaMethods.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListMetaMethods.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListMetaMethods.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticMetaMethods() {
        Class $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
        if (($class$groovy$swing$binding$AbstractSyntheticMetaMethods = JListMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods) == null) {
            $class$groovy$swing$binding$AbstractSyntheticMetaMethods = (JListMetaMethods.$class$groovy$swing$binding$AbstractSyntheticMetaMethods = class$("groovy.swing.binding.AbstractSyntheticMetaMethods"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticMetaMethods;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListMetaMethods() {
        Class $class$groovy$swing$binding$JListMetaMethods;
        if (($class$groovy$swing$binding$JListMetaMethods = JListMetaMethods.$class$groovy$swing$binding$JListMetaMethods) == null) {
            $class$groovy$swing$binding$JListMetaMethods = (JListMetaMethods.$class$groovy$swing$binding$JListMetaMethods = class$("groovy.swing.binding.JListMetaMethods"));
        }
        return $class$groovy$swing$binding$JListMetaMethods;
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
