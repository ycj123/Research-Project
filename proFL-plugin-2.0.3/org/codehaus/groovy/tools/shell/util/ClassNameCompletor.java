// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

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
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class ClassNameCompletor extends SimpleCompletor implements GroovyObject
{
    private final GroovyClassLoader classLoader;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205549;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$java$util$SortedSet;
    private static /* synthetic */ Class $class$java$util$TreeSet;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
    
    public ClassNameCompletor(final GroovyClassLoader classLoader) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(classLoader, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert classLoader", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.classLoader = (GroovyClassLoader)ScriptBytecodeAdapter.castToType(classLoader, $get$$class$groovy$lang$GroovyClassLoader());
        ScriptBytecodeAdapter.setGroovyObjectProperty(".", $get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor(), this, "delimiter");
    }
    
    public SortedSet getCandidates() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object set = $getCallSiteArray[0].callConstructor($get$$class$java$util$TreeSet());
        $getCallSiteArray[1].call(set, "java.lang.System");
        $getCallSiteArray[2].call(set, "groovy.lang.GroovyObject");
        return (SortedSet)ScriptBytecodeAdapter.castToType(set, $get$$class$java$util$SortedSet());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ClassNameCompletor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ClassNameCompletor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ClassNameCompletor.__timeStamp__239_neverHappen1292524205549 = 0L;
        ClassNameCompletor.__timeStamp = 1292524205549L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ClassNameCompletor.$callSiteArray == null || ($createCallSiteArray = ClassNameCompletor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ClassNameCompletor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ClassNameCompletor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ClassNameCompletor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = ClassNameCompletor.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (ClassNameCompletor.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$SortedSet() {
        Class $class$java$util$SortedSet;
        if (($class$java$util$SortedSet = ClassNameCompletor.$class$java$util$SortedSet) == null) {
            $class$java$util$SortedSet = (ClassNameCompletor.$class$java$util$SortedSet = class$("java.util.SortedSet"));
        }
        return $class$java$util$SortedSet;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$TreeSet() {
        Class $class$java$util$TreeSet;
        if (($class$java$util$TreeSet = ClassNameCompletor.$class$java$util$TreeSet) == null) {
            $class$java$util$TreeSet = (ClassNameCompletor.$class$java$util$TreeSet = class$("java.util.TreeSet"));
        }
        return $class$java$util$TreeSet;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = ClassNameCompletor.$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = (ClassNameCompletor.$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = class$("org.codehaus.groovy.tools.shell.util.ClassNameCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
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
