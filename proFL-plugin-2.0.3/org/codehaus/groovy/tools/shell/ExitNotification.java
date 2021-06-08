// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class ExitNotification extends Error implements GroovyObject
{
    private final int code;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205475;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ExitNotification;
    
    public ExitNotification(final int code) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.code = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(code));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$ExitNotification()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ExitNotification.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ExitNotification.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ExitNotification.__timeStamp__239_neverHappen1292524205475 = 0L;
        ExitNotification.__timeStamp = 1292524205475L;
    }
    
    public final int getCode() {
        return this.code;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ExitNotification(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ExitNotification.$callSiteArray == null || ($createCallSiteArray = ExitNotification.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ExitNotification.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ExitNotification.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ExitNotification.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ExitNotification() {
        Class $class$org$codehaus$groovy$tools$shell$ExitNotification;
        if (($class$org$codehaus$groovy$tools$shell$ExitNotification = ExitNotification.$class$org$codehaus$groovy$tools$shell$ExitNotification) == null) {
            $class$org$codehaus$groovy$tools$shell$ExitNotification = (ExitNotification.$class$org$codehaus$groovy$tools$shell$ExitNotification = class$("org.codehaus.groovy.tools.shell.ExitNotification"));
        }
        return $class$org$codehaus$groovy$tools$shell$ExitNotification;
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
