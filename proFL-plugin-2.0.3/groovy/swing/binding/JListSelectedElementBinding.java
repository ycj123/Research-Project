// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.binding.SourceBinding;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.event.ListSelectionEvent;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import javax.swing.JList;
import groovy.lang.GroovyObject;
import javax.swing.event.ListSelectionListener;
import java.beans.PropertyChangeListener;

public class JListSelectedElementBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ListSelectionListener, GroovyObject
{
    private JList boundList;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204961;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$PropertyBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListSelectedElementBinding;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticBinding;
    private static /* synthetic */ Class $class$javax$swing$JList;
    
    protected JListSelectedElementBinding(final PropertyBinding source, final TargetBinding target, final String propertyName) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[4])));
        arguments[0] = source;
        arguments[1] = target;
        arguments[2] = $get$$class$javax$swing$JList();
        arguments[3] = propertyName;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$groovy$swing$binding$AbstractSyntheticBinding());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((PropertyBinding)array[0], (TargetBinding)array2[1], (Class)array3[2], (String)array4[3]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public synchronized void syntheticBind() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.boundList = (JList)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGroovyObjectGetProperty(this), $get$$class$org$codehaus$groovy$binding$PropertyBinding())), $get$$class$javax$swing$JList()), $get$$class$javax$swing$JList());
        $getCallSiteArray[2].call(this.boundList, "selectionModel", this);
        $getCallSiteArray[3].call(this.boundList, this);
    }
    
    public synchronized void syntheticUnbind() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[4].call(this.boundList, "selectionModel", this);
        $getCallSiteArray[5].call(this.boundList, this);
        this.boundList = (JList)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$javax$swing$JList()), $get$$class$javax$swing$JList());
    }
    
    public void setTargetBinding(final TargetBinding target) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$swing$binding$AbstractSyntheticBinding(), this, "setTargetBinding", new Object[] { target });
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        $getCallSiteArray()[6].callCurrent(this);
    }
    
    public void valueChanged(final ListSelectionEvent e) {
        $getCallSiteArray()[7].callCurrent(this);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListSelectedElementBinding()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListSelectedElementBinding.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListSelectedElementBinding.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JListSelectedElementBinding.__timeStamp__239_neverHappen1292524204961 = 0L;
        JListSelectedElementBinding.__timeStamp = 1292524204961L;
    }
    
    public JList getBoundList() {
        return this.boundList;
    }
    
    public void setBoundList(final JList boundList) {
        this.boundList = boundList;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListSelectedElementBinding(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListSelectedElementBinding.$callSiteArray == null || ($createCallSiteArray = JListSelectedElementBinding.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListSelectedElementBinding.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListSelectedElementBinding.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListSelectedElementBinding.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$PropertyBinding() {
        Class $class$org$codehaus$groovy$binding$PropertyBinding;
        if (($class$org$codehaus$groovy$binding$PropertyBinding = JListSelectedElementBinding.$class$org$codehaus$groovy$binding$PropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$PropertyBinding = (JListSelectedElementBinding.$class$org$codehaus$groovy$binding$PropertyBinding = class$("org.codehaus.groovy.binding.PropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$PropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListSelectedElementBinding() {
        Class $class$groovy$swing$binding$JListSelectedElementBinding;
        if (($class$groovy$swing$binding$JListSelectedElementBinding = JListSelectedElementBinding.$class$groovy$swing$binding$JListSelectedElementBinding) == null) {
            $class$groovy$swing$binding$JListSelectedElementBinding = (JListSelectedElementBinding.$class$groovy$swing$binding$JListSelectedElementBinding = class$("groovy.swing.binding.JListSelectedElementBinding"));
        }
        return $class$groovy$swing$binding$JListSelectedElementBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticBinding() {
        Class $class$groovy$swing$binding$AbstractSyntheticBinding;
        if (($class$groovy$swing$binding$AbstractSyntheticBinding = JListSelectedElementBinding.$class$groovy$swing$binding$AbstractSyntheticBinding) == null) {
            $class$groovy$swing$binding$AbstractSyntheticBinding = (JListSelectedElementBinding.$class$groovy$swing$binding$AbstractSyntheticBinding = class$("groovy.swing.binding.AbstractSyntheticBinding"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticBinding;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JList() {
        Class $class$javax$swing$JList;
        if (($class$javax$swing$JList = JListSelectedElementBinding.$class$javax$swing$JList) == null) {
            $class$javax$swing$JList = (JListSelectedElementBinding.$class$javax$swing$JList = class$("javax.swing.JList"));
        }
        return $class$javax$swing$JList;
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
