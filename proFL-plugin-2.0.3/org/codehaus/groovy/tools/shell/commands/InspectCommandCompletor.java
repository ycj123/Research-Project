// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.List;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.util.SortedSet;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.tools.shell.util.SimpleCompletor;

public class InspectCommandCompletor extends SimpleCompletor implements GroovyObject
{
    private final Binding binding;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205504;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
    private static /* synthetic */ Class $class$java$util$SortedSet;
    private static /* synthetic */ Class $class$java$util$TreeSet;
    
    public InspectCommandCompletor(final Binding binding) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(binding, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert binding", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.binding = (Binding)ScriptBytecodeAdapter.castToType(binding, $get$$class$groovy$lang$Binding());
    }
    
    public SortedSet getCandidates() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object set = new Reference($getCallSiteArray[0].callConstructor($get$$class$java$util$TreeSet()));
        $getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this.binding)), new InspectCommandCompletor$_getCandidates_closure1(this, this, (Reference<Object>)set));
        return (SortedSet)ScriptBytecodeAdapter.castToType(((Reference<Object>)set).get(), $get$$class$java$util$SortedSet());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = InspectCommandCompletor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (InspectCommandCompletor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        InspectCommandCompletor.__timeStamp__239_neverHappen1292524205504 = 0L;
        InspectCommandCompletor.__timeStamp = 1292524205504L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (InspectCommandCompletor.$callSiteArray == null || ($createCallSiteArray = InspectCommandCompletor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            InspectCommandCompletor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = InspectCommandCompletor.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (InspectCommandCompletor.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = InspectCommandCompletor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (InspectCommandCompletor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = InspectCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = (InspectCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.InspectCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$SortedSet() {
        Class $class$java$util$SortedSet;
        if (($class$java$util$SortedSet = InspectCommandCompletor.$class$java$util$SortedSet) == null) {
            $class$java$util$SortedSet = (InspectCommandCompletor.$class$java$util$SortedSet = class$("java.util.SortedSet"));
        }
        return $class$java$util$SortedSet;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$TreeSet() {
        Class $class$java$util$TreeSet;
        if (($class$java$util$TreeSet = InspectCommandCompletor.$class$java$util$TreeSet) == null) {
            $class$java$util$TreeSet = (InspectCommandCompletor.$class$java$util$TreeSet = class$("java.util.TreeSet"));
        }
        return $class$java$util$TreeSet;
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
