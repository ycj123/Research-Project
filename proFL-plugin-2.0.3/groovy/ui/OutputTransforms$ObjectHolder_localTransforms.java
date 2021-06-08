// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

private static class OutputTransforms$ObjectHolder_localTransforms implements GroovyObject
{
    private static final Object INSTANCE;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms;
    
    public OutputTransforms$ObjectHolder_localTransforms() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = OutputTransforms$ObjectHolder_localTransforms.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (OutputTransforms$ObjectHolder_localTransforms.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        INSTANCE = $getCallSiteArray()[0].callStatic($get$$class$groovy$ui$OutputTransforms());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OutputTransforms$ObjectHolder_localTransforms.$callSiteArray == null || ($createCallSiteArray = OutputTransforms$ObjectHolder_localTransforms.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OutputTransforms$ObjectHolder_localTransforms.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = OutputTransforms$ObjectHolder_localTransforms.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (OutputTransforms$ObjectHolder_localTransforms.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms() {
        Class $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
        if (($class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = OutputTransforms$ObjectHolder_localTransforms.$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms) == null) {
            $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = (OutputTransforms$ObjectHolder_localTransforms.$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = class$("groovy.ui.OutputTransforms$ObjectHolder_localTransforms"));
        }
        return $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms() {
        Class $class$groovy$ui$OutputTransforms;
        if (($class$groovy$ui$OutputTransforms = OutputTransforms$ObjectHolder_localTransforms.$class$groovy$ui$OutputTransforms) == null) {
            $class$groovy$ui$OutputTransforms = (OutputTransforms$ObjectHolder_localTransforms.$class$groovy$ui$OutputTransforms = class$("groovy.ui.OutputTransforms"));
        }
        return $class$groovy$ui$OutputTransforms;
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
