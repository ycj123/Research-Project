// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.net.URL;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.Shell;
import groovy.lang.GroovyObject;

public class XmlCommandRegistrar implements GroovyObject
{
    private final Logger log;
    private final Shell shell;
    private final ClassLoader classLoader;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204100;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Shell;
    private static /* synthetic */ Class $class$java$lang$ClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
    
    public XmlCommandRegistrar(final Shell shell, final ClassLoader classLoader) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(shell, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert shell", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder2.record(classLoader, 8))) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert classLoader", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        this.shell = (Shell)ScriptBytecodeAdapter.castToType(shell, $get$$class$org$codehaus$groovy$tools$shell$Shell());
        this.classLoader = (ClassLoader)ScriptBytecodeAdapter.castToType(classLoader, $get$$class$java$lang$ClassLoader());
    }
    
    public void register(final URL url) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(url, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert url", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetProperty(this.log))) {
            $getCallSiteArray[3].call(this.log, new GStringImpl(new Object[] { url }, new String[] { "Registering commands from: ", "" }));
        }
        $getCallSiteArray[4].call(url, new XmlCommandRegistrar$_register_closure1(this, this));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = XmlCommandRegistrar.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (XmlCommandRegistrar.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        XmlCommandRegistrar.__timeStamp__239_neverHappen1292524204100 = 0L;
        XmlCommandRegistrar.__timeStamp = 1292524204100L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (XmlCommandRegistrar.$callSiteArray == null || ($createCallSiteArray = XmlCommandRegistrar.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            XmlCommandRegistrar.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = XmlCommandRegistrar.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (XmlCommandRegistrar.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Shell() {
        Class $class$org$codehaus$groovy$tools$shell$Shell;
        if (($class$org$codehaus$groovy$tools$shell$Shell = XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$Shell) == null) {
            $class$org$codehaus$groovy$tools$shell$Shell = (XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$Shell = class$("org.codehaus.groovy.tools.shell.Shell"));
        }
        return $class$org$codehaus$groovy$tools$shell$Shell;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$ClassLoader() {
        Class $class$java$lang$ClassLoader;
        if (($class$java$lang$ClassLoader = XmlCommandRegistrar.$class$java$lang$ClassLoader) == null) {
            $class$java$lang$ClassLoader = (XmlCommandRegistrar.$class$java$lang$ClassLoader = class$("java.lang.ClassLoader"));
        }
        return $class$java$lang$ClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar() {
        Class $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
        if (($class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar) == null) {
            $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = (XmlCommandRegistrar.$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = class$("org.codehaus.groovy.tools.shell.util.XmlCommandRegistrar"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
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
