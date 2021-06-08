// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.binding.SourceBinding;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.event.ListDataEvent;
import javax.swing.ListModel;
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
import java.beans.PropertyChangeListener;
import javax.swing.event.ListDataListener;

public class JListElementsBinding extends AbstractSyntheticBinding implements ListDataListener, PropertyChangeListener, GroovyObject
{
    private JList boundList;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204935;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$binding$JListElementsBinding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$PropertyBinding;
    private static /* synthetic */ Class $class$javax$swing$ListModel;
    private static /* synthetic */ Class $class$groovy$swing$binding$AbstractSyntheticBinding;
    private static /* synthetic */ Class $class$javax$swing$JList;
    
    public JListElementsBinding(final PropertyBinding propertyBinding, final TargetBinding target) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[4])));
        arguments[0] = propertyBinding;
        arguments[1] = target;
        arguments[2] = $get$$class$javax$swing$JList();
        arguments[3] = "elements";
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
    
    protected void syntheticBind() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.boundList = (JList)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGroovyObjectGetProperty(this), $get$$class$org$codehaus$groovy$binding$PropertyBinding())), $get$$class$javax$swing$JList()), $get$$class$javax$swing$JList());
        $getCallSiteArray[2].call(this.boundList, "model", this);
        $getCallSiteArray[3].call($getCallSiteArray[4].call(this.boundList), this);
    }
    
    protected void syntheticUnbind() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[5].call(this.boundList, "model", this);
        $getCallSiteArray[6].call($getCallSiteArray[7].call(this.boundList), this);
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[8].callCurrent(this);
        $getCallSiteArray[9].call(ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(event), $get$$class$javax$swing$ListModel()), this);
        $getCallSiteArray[11].call(ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call(event), $get$$class$javax$swing$ListModel()), this);
    }
    
    public void intervalAdded(final ListDataEvent e) {
        $getCallSiteArray()[13].callCurrent(this);
    }
    
    public void intervalRemoved(final ListDataEvent e) {
        $getCallSiteArray()[14].callCurrent(this);
    }
    
    public void contentsChanged(final ListDataEvent e) {
        $getCallSiteArray()[15].callCurrent(this);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$binding$JListElementsBinding()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JListElementsBinding.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JListElementsBinding.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JListElementsBinding.__timeStamp__239_neverHappen1292524204935 = 0L;
        JListElementsBinding.__timeStamp = 1292524204935L;
    }
    
    public JList getBoundList() {
        return this.boundList;
    }
    
    public void setBoundList(final JList boundList) {
        this.boundList = boundList;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListElementsBinding(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListElementsBinding.$callSiteArray == null || ($createCallSiteArray = JListElementsBinding.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListElementsBinding.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JListElementsBinding.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JListElementsBinding.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JListElementsBinding() {
        Class $class$groovy$swing$binding$JListElementsBinding;
        if (($class$groovy$swing$binding$JListElementsBinding = JListElementsBinding.$class$groovy$swing$binding$JListElementsBinding) == null) {
            $class$groovy$swing$binding$JListElementsBinding = (JListElementsBinding.$class$groovy$swing$binding$JListElementsBinding = class$("groovy.swing.binding.JListElementsBinding"));
        }
        return $class$groovy$swing$binding$JListElementsBinding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$PropertyBinding() {
        Class $class$org$codehaus$groovy$binding$PropertyBinding;
        if (($class$org$codehaus$groovy$binding$PropertyBinding = JListElementsBinding.$class$org$codehaus$groovy$binding$PropertyBinding) == null) {
            $class$org$codehaus$groovy$binding$PropertyBinding = (JListElementsBinding.$class$org$codehaus$groovy$binding$PropertyBinding = class$("org.codehaus.groovy.binding.PropertyBinding"));
        }
        return $class$org$codehaus$groovy$binding$PropertyBinding;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ListModel() {
        Class $class$javax$swing$ListModel;
        if (($class$javax$swing$ListModel = JListElementsBinding.$class$javax$swing$ListModel) == null) {
            $class$javax$swing$ListModel = (JListElementsBinding.$class$javax$swing$ListModel = class$("javax.swing.ListModel"));
        }
        return $class$javax$swing$ListModel;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$AbstractSyntheticBinding() {
        Class $class$groovy$swing$binding$AbstractSyntheticBinding;
        if (($class$groovy$swing$binding$AbstractSyntheticBinding = JListElementsBinding.$class$groovy$swing$binding$AbstractSyntheticBinding) == null) {
            $class$groovy$swing$binding$AbstractSyntheticBinding = (JListElementsBinding.$class$groovy$swing$binding$AbstractSyntheticBinding = class$("groovy.swing.binding.AbstractSyntheticBinding"));
        }
        return $class$groovy$swing$binding$AbstractSyntheticBinding;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JList() {
        Class $class$javax$swing$JList;
        if (($class$javax$swing$JList = JListElementsBinding.$class$javax$swing$JList) == null) {
            $class$javax$swing$JList = (JListElementsBinding.$class$javax$swing$JList = class$("javax.swing.JList"));
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
