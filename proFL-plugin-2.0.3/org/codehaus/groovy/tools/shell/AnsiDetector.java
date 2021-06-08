// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import java.util.concurrent.Callable;

public class AnsiDetector implements Callable<Boolean>, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204024;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    private static /* synthetic */ Class $class$jline$Terminal;
    
    public AnsiDetector() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Boolean call() throws Exception {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].call($get$$class$jline$Terminal())), $get$$class$java$lang$Boolean());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$AnsiDetector()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AnsiDetector.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AnsiDetector.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AnsiDetector.__timeStamp__239_neverHappen1292524204024 = 0L;
        AnsiDetector.__timeStamp = 1292524204024L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$AnsiDetector(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AnsiDetector.$callSiteArray == null || ($createCallSiteArray = AnsiDetector.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AnsiDetector.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AnsiDetector.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AnsiDetector.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AnsiDetector.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AnsiDetector.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$AnsiDetector() {
        Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
        if (($class$org$codehaus$groovy$tools$shell$AnsiDetector = AnsiDetector.$class$org$codehaus$groovy$tools$shell$AnsiDetector) == null) {
            $class$org$codehaus$groovy$tools$shell$AnsiDetector = (AnsiDetector.$class$org$codehaus$groovy$tools$shell$AnsiDetector = class$("org.codehaus.groovy.tools.shell.AnsiDetector"));
        }
        return $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    }
    
    private static /* synthetic */ Class $get$$class$jline$Terminal() {
        Class $class$jline$Terminal;
        if (($class$jline$Terminal = AnsiDetector.$class$jline$Terminal) == null) {
            $class$jline$Terminal = (AnsiDetector.$class$jline$Terminal = class$("jline.Terminal"));
        }
        return $class$jline$Terminal;
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
