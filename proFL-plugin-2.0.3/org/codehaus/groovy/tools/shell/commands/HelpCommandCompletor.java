// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.List;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.SortedSet;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandRegistry;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.tools.shell.util.SimpleCompletor;

public class HelpCommandCompletor extends SimpleCompletor implements GroovyObject
{
    private final CommandRegistry registry;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205476;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
    private static /* synthetic */ Class $class$java$util$SortedSet;
    private static /* synthetic */ Class $class$java$util$TreeSet;
    
    public HelpCommandCompletor(final CommandRegistry registry) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(registry, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert registry", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.registry = (CommandRegistry)ScriptBytecodeAdapter.castToType(registry, $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry());
    }
    
    public SortedSet getCandidates() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object set = $getCallSiteArray[0].callConstructor($get$$class$java$util$TreeSet());
        Object command = null;
        final Object call = $getCallSiteArray[1].call(this.registry);
        while (((Iterator)call).hasNext()) {
            command = ((Iterator<Object>)call).next();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetProperty(command))) {
                continue;
            }
            $getCallSiteArray[3].call(set, $getCallSiteArray[4].callGetProperty(command));
            $getCallSiteArray[5].call(set, $getCallSiteArray[6].callGetProperty(command));
        }
        return (SortedSet)ScriptBytecodeAdapter.castToType(set, $get$$class$java$util$SortedSet());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HelpCommandCompletor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HelpCommandCompletor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HelpCommandCompletor.__timeStamp__239_neverHappen1292524205476 = 0L;
        HelpCommandCompletor.__timeStamp = 1292524205476L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpCommandCompletor.$callSiteArray == null || ($createCallSiteArray = HelpCommandCompletor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpCommandCompletor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HelpCommandCompletor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HelpCommandCompletor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry() {
        Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
        if (($class$org$codehaus$groovy$tools$shell$CommandRegistry = HelpCommandCompletor.$class$org$codehaus$groovy$tools$shell$CommandRegistry) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandRegistry = (HelpCommandCompletor.$class$org$codehaus$groovy$tools$shell$CommandRegistry = class$("org.codehaus.groovy.tools.shell.CommandRegistry"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = HelpCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = (HelpCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.HelpCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$SortedSet() {
        Class $class$java$util$SortedSet;
        if (($class$java$util$SortedSet = HelpCommandCompletor.$class$java$util$SortedSet) == null) {
            $class$java$util$SortedSet = (HelpCommandCompletor.$class$java$util$SortedSet = class$("java.util.SortedSet"));
        }
        return $class$java$util$SortedSet;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$TreeSet() {
        Class $class$java$util$TreeSet;
        if (($class$java$util$TreeSet = HelpCommandCompletor.$class$java$util$TreeSet) == null) {
            $class$java$util$TreeSet = (HelpCommandCompletor.$class$java$util$TreeSet = class$("java.util.TreeSet"));
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
