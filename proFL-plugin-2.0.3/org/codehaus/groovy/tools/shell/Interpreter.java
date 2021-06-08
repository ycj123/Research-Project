// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Script;
import groovy.lang.Reference;
import java.util.List;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public class Interpreter implements GroovyObject
{
    private static final String SCRIPT_FILENAME;
    private final Logger log;
    private final GroovyShell shell;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204005;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Parser;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Interpreter;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$groovy$lang$GroovyShell;
    
    public Interpreter(final ClassLoader classLoader, final Binding binding) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
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
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder2.record(binding, 8))) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert binding", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        this.shell = (GroovyShell)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$groovy$lang$GroovyShell(), classLoader, binding), $get$$class$groovy$lang$GroovyShell());
    }
    
    public Binding getContext() {
        return (Binding)ScriptBytecodeAdapter.castToType($getCallSiteArray()[3].call(this.shell), $get$$class$groovy$lang$Binding());
    }
    
    public GroovyClassLoader getClassLoader() {
        return (GroovyClassLoader)ScriptBytecodeAdapter.castToType($getCallSiteArray()[4].call(this.shell), $get$$class$groovy$lang$GroovyClassLoader());
    }
    
    public Object evaluate(final List buffer) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(buffer, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert buffer", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Object source = $getCallSiteArray[5].call(buffer, $getCallSiteArray[6].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$Parser()));
        Object result = null;
        final Class type = (Class)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Class()));
        try {
            final Script script = (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(this.shell, source, Interpreter.SCRIPT_FILENAME), $get$$class$groovy$lang$Script());
            ((Reference)type).set(ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(script), $get$$class$java$lang$Class()));
            $getCallSiteArray[9].call(this.log, new GStringImpl(new Object[] { script }, new String[] { "Compiled script: ", "" }));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty(((Reference)type).get()), new Interpreter$_evaluate_closure1(this, this)))) {
                result = $getCallSiteArray[12].call(script);
            }
            $getCallSiteArray[13].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[14].call($get$$class$java$lang$String(), result), $getCallSiteArray[15].callSafe(result) }, new String[] { "Evaluation result: ", " (", ")" }));
            $getCallSiteArray[16].call($getCallSiteArray[17].callGetProperty(((Reference)type).get()), new Interpreter$_evaluate_closure2(this, this, (Reference<Object>)type));
            final Object cache = $getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callGroovyObjectGetProperty(this));
            $getCallSiteArray[20].call(cache, $getCallSiteArray[21].callGetPropertySafe(((Reference)type).get()));
            $getCallSiteArray[22].call(cache, "$_run_closure");
        }
        finally {
            final Object cache2 = $getCallSiteArray[23].callGetProperty($getCallSiteArray[24].callGroovyObjectGetProperty(this));
            $getCallSiteArray[25].call(cache2, $getCallSiteArray[26].callGetPropertySafe(((Reference)type).get()));
            $getCallSiteArray[27].call(cache2, "$_run_closure");
        }
        return result;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$Interpreter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Interpreter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Interpreter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Interpreter.__timeStamp__239_neverHappen1292524204005 = 0L;
        Interpreter.__timeStamp = 1292524204005L;
        SCRIPT_FILENAME = "groovysh_evaluate";
    }
    
    public static final String getSCRIPT_FILENAME() {
        return Interpreter.SCRIPT_FILENAME;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[28];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Interpreter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Interpreter.$callSiteArray == null || ($createCallSiteArray = Interpreter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Interpreter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = Interpreter.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (Interpreter.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Interpreter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Interpreter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = Interpreter.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (Interpreter.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Parser() {
        Class $class$org$codehaus$groovy$tools$shell$Parser;
        if (($class$org$codehaus$groovy$tools$shell$Parser = Interpreter.$class$org$codehaus$groovy$tools$shell$Parser) == null) {
            $class$org$codehaus$groovy$tools$shell$Parser = (Interpreter.$class$org$codehaus$groovy$tools$shell$Parser = class$("org.codehaus.groovy.tools.shell.Parser"));
        }
        return $class$org$codehaus$groovy$tools$shell$Parser;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Interpreter() {
        Class $class$org$codehaus$groovy$tools$shell$Interpreter;
        if (($class$org$codehaus$groovy$tools$shell$Interpreter = Interpreter.$class$org$codehaus$groovy$tools$shell$Interpreter) == null) {
            $class$org$codehaus$groovy$tools$shell$Interpreter = (Interpreter.$class$org$codehaus$groovy$tools$shell$Interpreter = class$("org.codehaus.groovy.tools.shell.Interpreter"));
        }
        return $class$org$codehaus$groovy$tools$shell$Interpreter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = Interpreter.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (Interpreter.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = Interpreter.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (Interpreter.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Interpreter.$class$java$lang$String) == null) {
            $class$java$lang$String = (Interpreter.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = Interpreter.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (Interpreter.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyShell() {
        Class $class$groovy$lang$GroovyShell;
        if (($class$groovy$lang$GroovyShell = Interpreter.$class$groovy$lang$GroovyShell) == null) {
            $class$groovy$lang$GroovyShell = (Interpreter.$class$groovy$lang$GroovyShell = class$("groovy.lang.GroovyShell"));
        }
        return $class$groovy$lang$GroovyShell;
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
