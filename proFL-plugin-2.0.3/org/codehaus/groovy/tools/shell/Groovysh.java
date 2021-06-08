// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import jline.WindowsTerminal;
import java.io.File;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Closure;
import jline.History;
import java.util.List;
import org.codehaus.groovy.tools.shell.util.MessageSource;
import groovy.lang.GroovyObject;

public class Groovysh extends Shell implements GroovyObject
{
    private static final MessageSource messages;
    private final BufferManager buffers;
    private final Parser parser;
    private final Interpreter interp;
    private final List imports;
    private InteractiveShellRunner runner;
    private History history;
    private final Closure defaultResultHook;
    private Closure resultHook;
    private final Closure defaultErrorHook;
    private Closure errorHook;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204736;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ParseCode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
    private static /* synthetic */ Class $class$org$fusesource$jansi$Ansi;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Parser;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$jline$Terminal;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Groovysh;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$java$lang$Error;
    private static /* synthetic */ Class $class$java$lang$Thread;
    private static /* synthetic */ Class $class$jline$History;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$java$lang$IllegalStateException;
    private static /* synthetic */ Class $class$org$fusesource$jansi$AnsiConsole;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Interpreter;
    private static /* synthetic */ Class $class$java$lang$Throwable;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$org$fusesource$jansi$AnsiRenderer;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$BufferManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Shell;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    public Groovysh(final ClassLoader classLoader, final Binding binding, final IO io, final Closure registrar) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[] { io };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$org$codehaus$groovy$tools$shell$Shell());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                break;
            }
            case 1: {
                super((IO)array[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.buffers = (BufferManager)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$BufferManager()), $get$$class$org$codehaus$groovy$tools$shell$BufferManager());
        this.imports = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.defaultResultHook = (Closure)ScriptBytecodeAdapter.castToType(new Groovysh$_closure1(this, this), $get$$class$groovy$lang$Closure());
        this.resultHook = (Closure)ScriptBytecodeAdapter.castToType(this.defaultResultHook, $get$$class$groovy$lang$Closure());
        this.defaultErrorHook = (Closure)ScriptBytecodeAdapter.castToType(new Groovysh$_closure2(this, this), $get$$class$groovy$lang$Closure());
        this.errorHook = (Closure)ScriptBytecodeAdapter.castToType(this.defaultErrorHook, $get$$class$groovy$lang$Closure());
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
        final ValueRecorder recorder3 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder3.record(registrar, 8))) {
                recorder3.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert registrar", recorder3), null);
            }
        }
        finally {
            recorder3.clear();
        }
        this.parser = (Parser)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$org$codehaus$groovy$tools$shell$Parser()), $get$$class$org$codehaus$groovy$tools$shell$Parser());
        this.interp = (Interpreter)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$tools$shell$Interpreter(), classLoader, binding), $get$$class$org$codehaus$groovy$tools$shell$Interpreter());
        $getCallSiteArray[3].call(registrar, this);
    }
    
    public Groovysh(final ClassLoader classLoader, final Binding binding, final IO io) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array10;
        Object[] array9;
        Object[] array8;
        Object[] array7;
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = new Object[4])))))))));
        arguments[0] = classLoader;
        arguments[1] = binding;
        arguments[2] = io;
        arguments[3] = $getCallSiteArray[4].callStatic($get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 5, $get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (Object[])arguments[0])))))))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0], (IO)array2[1]);
                break;
            }
            case 2: {
                this((ClassLoader)array3[0], (Binding)array4[1], (IO)array5[2]);
                break;
            }
            case 3: {
                this((ClassLoader)array6[0], (Binding)array7[1], (IO)array8[2], (Closure)array9[3]);
                break;
            }
            case 4: {
                this((IO)array10[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Groovysh(final Binding binding, final IO io) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array10;
        Object[] array9;
        Object[] array8;
        Object[] array7;
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = new Object[3])))))))));
        arguments[0] = $getCallSiteArray[5].callGetProperty($getCallSiteArray[6].call($get$$class$java$lang$Thread()));
        arguments[1] = binding;
        arguments[2] = io;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 5, $get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (Object[])arguments[0])))))))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0], (IO)array2[1]);
                break;
            }
            case 2: {
                this((ClassLoader)array3[0], (Binding)array4[1], (IO)array5[2]);
                break;
            }
            case 3: {
                this((ClassLoader)array6[0], (Binding)array7[1], (IO)array8[2], (Closure)array9[3]);
                break;
            }
            case 4: {
                this((IO)array10[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Groovysh(final IO io) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array10;
        Object[] array9;
        Object[] array8;
        Object[] array7;
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = new Object[2])))))))));
        arguments[0] = $getCallSiteArray[7].callConstructor($get$$class$groovy$lang$Binding());
        arguments[1] = io;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 5, $get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (Object[])arguments[0])))))))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0], (IO)array2[1]);
                break;
            }
            case 2: {
                this((ClassLoader)array3[0], (Binding)array4[1], (IO)array5[2]);
                break;
            }
            case 3: {
                this((ClassLoader)array6[0], (Binding)array7[1], (IO)array8[2], (Closure)array9[3]);
                break;
            }
            case 4: {
                this((IO)array10[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Groovysh() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array10;
        Object[] array9;
        Object[] array8;
        Object[] array7;
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = new Object[] { null })))))))));
        arguments[0] = $getCallSiteArray[8].callConstructor($get$$class$org$codehaus$groovy$tools$shell$IO());
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 5, $get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (Object[])arguments[0])))))))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((Binding)array[0], (IO)array2[1]);
                break;
            }
            case 2: {
                this((ClassLoader)array3[0], (Binding)array4[1], (IO)array5[2]);
                break;
            }
            case 3: {
                this((ClassLoader)array6[0], (Binding)array7[1], (IO)array8[2], (Closure)array9[3]);
                break;
            }
            case 4: {
                this((IO)array10[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    static {
        Groovysh.__timeStamp__239_neverHappen1292524204736 = 0L;
        Groovysh.__timeStamp = 1292524204736L;
        $const$3 = 80;
        $const$2 = 1;
        $const$1 = 3;
        $const$0 = 0;
        messages = (MessageSource)$getCallSiteArray()[9].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$MessageSource(), $get$$class$org$codehaus$groovy$tools$shell$Groovysh());
        $getCallSiteArray()[10].call($get$$class$org$fusesource$jansi$AnsiConsole());
        $getCallSiteArray()[11].call($get$$class$org$fusesource$jansi$Ansi(), $getCallSiteArray()[12].callConstructor($get$$class$org$codehaus$groovy$tools$shell$AnsiDetector()));
    }
    
    private static Closure createDefaultRegistrar() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(new Groovysh$_createDefaultRegistrar_closure3($get$$class$org$codehaus$groovy$tools$shell$Groovysh(), $get$$class$org$codehaus$groovy$tools$shell$Groovysh()), $get$$class$groovy$lang$Closure());
    }
    
    @Override
    public Object execute(final String line) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(line, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert line != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[13].call($getCallSiteArray[14].call(line)), Groovysh.$const$0)) {
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        $getCallSiteArray[15].callCurrent(this, line);
        Object result = null;
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].callCurrent(this, line))) {
            result = $getCallSiteArray[17].callCurrent(this, line);
            if (DefaultTypeTransformation.booleanUnbox(result)) {
                ScriptBytecodeAdapter.setGroovyObjectProperty(result, $get$$class$org$codehaus$groovy$tools$shell$Groovysh(), this, "lastResult");
            }
            return ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Object());
        }
        Object current = ScriptBytecodeAdapter.createList(new Object[0]);
        current = $getCallSiteArray[18].call(current, $getCallSiteArray[19].call(this.buffers));
        $getCallSiteArray[20].call(current, line);
        final Object status = $getCallSiteArray[21].call(this.parser, $getCallSiteArray[22].call(this.imports, current));
        final Object callGetProperty = $getCallSiteArray[23].callGetProperty(status);
        if (ScriptBytecodeAdapter.isCase(callGetProperty, $getCallSiteArray[24].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$ParseCode()))) {
            $getCallSiteArray[25].call($getCallSiteArray[26].callGroovyObjectGetProperty(this), "Evaluating buffer...");
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[27].callGetProperty($getCallSiteArray[28].callGroovyObjectGetProperty(this)))) {
                $getCallSiteArray[29].callCurrent(this, $getCallSiteArray[30].callGroovyObjectGetProperty(this));
            }
            final Object buff = $getCallSiteArray[31].call($getCallSiteArray[32].call(this.imports, ScriptBytecodeAdapter.createList(new Object[] { "true" })), current);
            ScriptBytecodeAdapter.setGroovyObjectProperty(result = $getCallSiteArray[33].call(this.interp, buff), $get$$class$org$codehaus$groovy$tools$shell$Groovysh(), this, "lastResult");
            $getCallSiteArray[34].call(this.buffers);
        }
        else if (ScriptBytecodeAdapter.isCase(callGetProperty, $getCallSiteArray[35].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$ParseCode()))) {
            $getCallSiteArray[36].call(this.buffers, current);
        }
        else {
            if (ScriptBytecodeAdapter.isCase(callGetProperty, $getCallSiteArray[37].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$ParseCode()))) {
                throw (Throwable)$getCallSiteArray[38].callGetProperty(status);
            }
            throw (Throwable)$getCallSiteArray[39].callConstructor($get$$class$java$lang$Error(), new GStringImpl(new Object[] { $getCallSiteArray[40].callGetProperty(status) }, new String[] { "Invalid parse status: ", "" }));
        }
        return ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Object());
    }
    
    protected Object executeCommand(final String line) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$org$codehaus$groovy$tools$shell$Shell(), this, "execute", new Object[] { line }), $get$$class$java$lang$Object());
    }
    
    private void displayBuffer(final List buffer) {
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
        $getCallSiteArray[41].call(buffer, new Groovysh$_displayBuffer_closure4(this, this));
    }
    
    private String renderPrompt() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object lineNum = $getCallSiteArray[42].callCurrent(this, $getCallSiteArray[43].call($getCallSiteArray[44].call(this.buffers)));
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[45].call($get$$class$org$fusesource$jansi$AnsiRenderer(), new GStringImpl(new Object[] { lineNum }, new String[] { "@|bold groovy:|@", "@|bold >|@ " })), $get$$class$java$lang$String());
    }
    
    private String formatLineNumber(final int num) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareGreaterThanEqual = ScriptBytecodeAdapter.compareGreaterThanEqual(recorder.record(DefaultTypeTransformation.box(num), 8), Groovysh.$const$0);
            recorder.record(compareGreaterThanEqual ? Boolean.TRUE : Boolean.FALSE, 12);
            if (compareGreaterThanEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert num >= 0", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[46].call($getCallSiteArray[47].call(DefaultTypeTransformation.box(num)), Groovysh.$const$1, "0"), $get$$class$java$lang$String());
    }
    
    public File getUserStateDirectory() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object userHome = $getCallSiteArray[48].callConstructor($get$$class$java$io$File(), $getCallSiteArray[49].call($get$$class$java$lang$System(), "user.home"));
        final Object dir = $getCallSiteArray[50].callConstructor($get$$class$java$io$File(), userHome, ".groovy");
        return (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[51].callGetProperty(dir), $get$$class$java$io$File());
    }
    
    private void loadUserScript(final String filename) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(filename, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert filename", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Object file = $getCallSiteArray[52].callConstructor($get$$class$java$io$File(), $getCallSiteArray[53].callGroovyObjectGetProperty(this), filename);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[54].call(file))) {
            final Object command = $getCallSiteArray[55].call($getCallSiteArray[56].callGroovyObjectGetProperty(this), "load");
            if (DefaultTypeTransformation.booleanUnbox(command)) {
                $getCallSiteArray[57].call($getCallSiteArray[58].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { file }, new String[] { "Loading user-script: ", "" }));
                final Object previousHook = this.resultHook;
                this.resultHook = (Closure)ScriptBytecodeAdapter.castToType(new Groovysh$_loadUserScript_closure5(this, this), $get$$class$groovy$lang$Closure());
                try {
                    $getCallSiteArray[59].call(command, $getCallSiteArray[60].call($getCallSiteArray[61].call(file)));
                    this.resultHook = (Closure)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(previousHook, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure());
                }
                finally {
                    this.resultHook = (Closure)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(previousHook, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure());
                }
            }
            else {
                $getCallSiteArray[62].call($getCallSiteArray[63].callGroovyObjectGetProperty(this), "Unable to load user-script, missing 'load' command");
            }
        }
    }
    
    private void maybeRecordInput(final String line) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object record = $getCallSiteArray[64].call($getCallSiteArray[65].callGroovyObjectGetProperty(this), "record");
        if (ScriptBytecodeAdapter.compareNotEqual(record, null)) {
            $getCallSiteArray[66].call(record, line);
        }
    }
    
    private void maybeRecordResult(final Object result) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object record = $getCallSiteArray[67].call($getCallSiteArray[68].callGroovyObjectGetProperty(this), "record");
        if (ScriptBytecodeAdapter.compareNotEqual(record, null)) {
            $getCallSiteArray[69].call(record, result);
        }
    }
    
    private void maybeRecordError(Throwable cause) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object record = $getCallSiteArray[70].call($getCallSiteArray[71].callGroovyObjectGetProperty(this), "record");
        if (ScriptBytecodeAdapter.compareNotEqual(record, null)) {
            final Boolean sanitize = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[72].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$util$Preferences()), $get$$class$java$lang$Boolean());
            if (DefaultTypeTransformation.booleanUnbox(sanitize)) {
                cause = (Throwable)ScriptBytecodeAdapter.castToType($getCallSiteArray[73].call($get$$class$org$codehaus$groovy$runtime$StackTraceUtils(), cause), $get$$class$java$lang$Throwable());
            }
            $getCallSiteArray[74].call(record, cause);
        }
    }
    
    private void setLastResult(final Object result) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.resultHook, null)) {
            throw (Throwable)$getCallSiteArray[75].callConstructor($get$$class$java$lang$IllegalStateException(), "Result hook is not set");
        }
        $getCallSiteArray[76].call(this.resultHook, ScriptBytecodeAdapter.createPojoWrapper(result, $get$$class$java$lang$Object()));
        $getCallSiteArray[77].call($getCallSiteArray[78].callGroovyObjectGetProperty(this.interp), "_", result);
        $getCallSiteArray[79].callCurrent(this, result);
    }
    
    private Object getLastResult() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[80].call($getCallSiteArray[81].callGroovyObjectGetProperty(this.interp), "_"), $get$$class$java$lang$Object());
    }
    
    private void displayError(final Throwable cause) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.errorHook, null)) {
            throw (Throwable)$getCallSiteArray[82].callConstructor($get$$class$java$lang$IllegalStateException(), "Error hook is not set");
        }
        $getCallSiteArray[83].call(this.errorHook, cause);
    }
    
    public int run(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        String commandLine = (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(args, null) && ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[84].callGetProperty(args), Groovysh.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) {
            commandLine = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[85].call(args, " "), $get$$class$java$lang$String());
        }
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[86].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(commandLine, $get$$class$java$lang$String())), $get$$class$java$lang$Integer()));
    }
    
    public int run(final String commandLine) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object term = $getCallSiteArray[87].callGetProperty($get$$class$jline$Terminal());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[88].callGetProperty($getCallSiteArray[89].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[90].call($getCallSiteArray[91].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { term }, new String[] { "Terminal (", ")" }));
            $getCallSiteArray[92].call($getCallSiteArray[93].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[94].callGetProperty(term) }, new String[] { "    Supported:  ", "" }));
            $getCallSiteArray[95].call($getCallSiteArray[96].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[97].callGetProperty(term), $getCallSiteArray[98].callGetProperty(term) }, new String[] { "    ECHO:       ", " (enabled: ", ")" }));
            $getCallSiteArray[99].call($getCallSiteArray[100].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[101].callGetProperty(term), $getCallSiteArray[102].callGetProperty(term) }, new String[] { "    H x W:      ", " x ", "" }));
            $getCallSiteArray[103].call($getCallSiteArray[104].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[105].call(term) }, new String[] { "    ANSI:       ", "" }));
            if (term instanceof WindowsTerminal) {
                $getCallSiteArray[106].call($getCallSiteArray[107].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[108].callGetProperty(term) }, new String[] { "    Direct:     ", "" }));
            }
        }
        Object code = null;
        try {
            $getCallSiteArray[109].callCurrent(this, "groovysh.profile");
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(commandLine, null) && ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[110].call($getCallSiteArray[111].call(commandLine)), Groovysh.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[112].callCurrent(this, commandLine);
            }
            else {
                $getCallSiteArray[113].callCurrent(this, "groovysh.rc");
                this.runner = (InteractiveShellRunner)ScriptBytecodeAdapter.castToType($getCallSiteArray[114].callConstructor($get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner(), this, ScriptBytecodeAdapter.createGroovyObjectWrapper(ScriptBytecodeAdapter.getMethodPointer(this, "renderPrompt"), $get$$class$groovy$lang$Closure())), $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner());
                final Object callConstructor = $getCallSiteArray[115].callConstructor($get$$class$jline$History());
                this.history = (History)ScriptBytecodeAdapter.castToType(callConstructor, $get$$class$jline$History());
                ScriptBytecodeAdapter.setGroovyObjectProperty(callConstructor, $get$$class$org$codehaus$groovy$tools$shell$Groovysh(), this.runner, "history");
                ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[116].callConstructor($get$$class$java$io$File(), $getCallSiteArray[117].callGroovyObjectGetProperty(this), "groovysh.history"), $get$$class$org$codehaus$groovy$tools$shell$Groovysh(), this.runner, "historyFile");
                ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.getMethodPointer(this, "displayError"), $get$$class$org$codehaus$groovy$tools$shell$Groovysh(), this.runner, "errorHandler");
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[118].callGetProperty($getCallSiteArray[119].callGroovyObjectGetProperty(this)))) {
                    Object width = $getCallSiteArray[120].callGetProperty(term);
                    if (ScriptBytecodeAdapter.compareLessThan(width, Groovysh.$const$2)) {
                        width = Groovysh.$const$3;
                    }
                    $getCallSiteArray[121].call($getCallSiteArray[122].callGetProperty($getCallSiteArray[123].callGroovyObjectGetProperty(this)), $getCallSiteArray[124].call(Groovysh.messages, "startup_banner.0", $getCallSiteArray[125].callGetProperty($get$$class$org$codehaus$groovy$runtime$InvokerHelper()), $getCallSiteArray[126].call($getCallSiteArray[127].callGetProperty($get$$class$java$lang$System()), "java.version")));
                    $getCallSiteArray[128].call($getCallSiteArray[129].callGetProperty($getCallSiteArray[130].callGroovyObjectGetProperty(this)), $getCallSiteArray[131].call(Groovysh.messages, "startup_banner.1"));
                    $getCallSiteArray[132].call($getCallSiteArray[133].callGetProperty($getCallSiteArray[134].callGroovyObjectGetProperty(this)), $getCallSiteArray[135].call("-", $getCallSiteArray[136].call(width, Groovysh.$const$2)));
                }
                $getCallSiteArray[137].call(this.runner);
            }
            code = Groovysh.$const$0;
        }
        catch (ExitNotification n) {
            $getCallSiteArray[138].call($getCallSiteArray[139].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[140].callGroovyObjectGetProperty(n) }, new String[] { "Exiting w/code: ", "" }));
            code = $getCallSiteArray[141].callGroovyObjectGetProperty(n);
        }
        catch (Throwable t) {
            $getCallSiteArray[142].call($getCallSiteArray[143].callGetProperty($getCallSiteArray[144].callGroovyObjectGetProperty(this)), $getCallSiteArray[145].call(Groovysh.messages, "info.fatal", t));
            $getCallSiteArray[146].call(t, $getCallSiteArray[147].callGetProperty($getCallSiteArray[148].callGroovyObjectGetProperty(this)));
            code = Groovysh.$const$2;
        }
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(code, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert code != null // This should never happen", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(code, $get$$class$java$lang$Integer()));
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$Groovysh()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Groovysh.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Groovysh.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public final BufferManager getBuffers() {
        return this.buffers;
    }
    
    public final Parser getParser() {
        return this.parser;
    }
    
    public final Interpreter getInterp() {
        return this.interp;
    }
    
    public final List getImports() {
        return this.imports;
    }
    
    public InteractiveShellRunner getRunner() {
        return this.runner;
    }
    
    public void setRunner(final InteractiveShellRunner runner) {
        this.runner = runner;
    }
    
    public History getHistory() {
        return this.history;
    }
    
    public void setHistory(final History history) {
        this.history = history;
    }
    
    public final Closure getDefaultResultHook() {
        return this.defaultResultHook;
    }
    
    public Closure getResultHook() {
        return this.resultHook;
    }
    
    public void setResultHook(final Closure resultHook) {
        this.resultHook = resultHook;
    }
    
    public final Closure getDefaultErrorHook() {
        return this.defaultErrorHook;
    }
    
    public Closure getErrorHook() {
        return this.errorHook;
    }
    
    public void setErrorHook(final Closure errorHook) {
        this.errorHook = errorHook;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[149];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Groovysh(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Groovysh.$callSiteArray == null || ($createCallSiteArray = Groovysh.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Groovysh.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ParseCode() {
        Class $class$org$codehaus$groovy$tools$shell$ParseCode;
        if (($class$org$codehaus$groovy$tools$shell$ParseCode = Groovysh.$class$org$codehaus$groovy$tools$shell$ParseCode) == null) {
            $class$org$codehaus$groovy$tools$shell$ParseCode = (Groovysh.$class$org$codehaus$groovy$tools$shell$ParseCode = class$("org.codehaus.groovy.tools.shell.ParseCode"));
        }
        return $class$org$codehaus$groovy$tools$shell$ParseCode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner() {
        Class $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
        if (($class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = Groovysh.$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner) == null) {
            $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = (Groovysh.$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = class$("org.codehaus.groovy.tools.shell.InteractiveShellRunner"));
        }
        return $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
    }
    
    private static /* synthetic */ Class $get$$class$org$fusesource$jansi$Ansi() {
        Class $class$org$fusesource$jansi$Ansi;
        if (($class$org$fusesource$jansi$Ansi = Groovysh.$class$org$fusesource$jansi$Ansi) == null) {
            $class$org$fusesource$jansi$Ansi = (Groovysh.$class$org$fusesource$jansi$Ansi = class$("org.fusesource.jansi.Ansi"));
        }
        return $class$org$fusesource$jansi$Ansi;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Parser() {
        Class $class$org$codehaus$groovy$tools$shell$Parser;
        if (($class$org$codehaus$groovy$tools$shell$Parser = Groovysh.$class$org$codehaus$groovy$tools$shell$Parser) == null) {
            $class$org$codehaus$groovy$tools$shell$Parser = (Groovysh.$class$org$codehaus$groovy$tools$shell$Parser = class$("org.codehaus.groovy.tools.shell.Parser"));
        }
        return $class$org$codehaus$groovy$tools$shell$Parser;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$AnsiDetector() {
        Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
        if (($class$org$codehaus$groovy$tools$shell$AnsiDetector = Groovysh.$class$org$codehaus$groovy$tools$shell$AnsiDetector) == null) {
            $class$org$codehaus$groovy$tools$shell$AnsiDetector = (Groovysh.$class$org$codehaus$groovy$tools$shell$AnsiDetector = class$("org.codehaus.groovy.tools.shell.AnsiDetector"));
        }
        return $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Groovysh.$class$java$util$List) == null) {
            $class$java$util$List = (Groovysh.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Groovysh.$class$java$lang$String) == null) {
            $class$java$lang$String = (Groovysh.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Groovysh.$class$java$io$File) == null) {
            $class$java$io$File = (Groovysh.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$jline$Terminal() {
        Class $class$jline$Terminal;
        if (($class$jline$Terminal = Groovysh.$class$jline$Terminal) == null) {
            $class$jline$Terminal = (Groovysh.$class$jline$Terminal = class$("jline.Terminal"));
        }
        return $class$jline$Terminal;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$MessageSource() {
        Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
        if (($class$org$codehaus$groovy$tools$shell$util$MessageSource = Groovysh.$class$org$codehaus$groovy$tools$shell$util$MessageSource) == null) {
            $class$org$codehaus$groovy$tools$shell$util$MessageSource = (Groovysh.$class$org$codehaus$groovy$tools$shell$util$MessageSource = class$("org.codehaus.groovy.tools.shell.util.MessageSource"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Groovysh() {
        Class $class$org$codehaus$groovy$tools$shell$Groovysh;
        if (($class$org$codehaus$groovy$tools$shell$Groovysh = Groovysh.$class$org$codehaus$groovy$tools$shell$Groovysh) == null) {
            $class$org$codehaus$groovy$tools$shell$Groovysh = (Groovysh.$class$org$codehaus$groovy$tools$shell$Groovysh = class$("org.codehaus.groovy.tools.shell.Groovysh"));
        }
        return $class$org$codehaus$groovy$tools$shell$Groovysh;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = Groovysh.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (Groovysh.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Error() {
        Class $class$java$lang$Error;
        if (($class$java$lang$Error = Groovysh.$class$java$lang$Error) == null) {
            $class$java$lang$Error = (Groovysh.$class$java$lang$Error = class$("java.lang.Error"));
        }
        return $class$java$lang$Error;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Thread() {
        Class $class$java$lang$Thread;
        if (($class$java$lang$Thread = Groovysh.$class$java$lang$Thread) == null) {
            $class$java$lang$Thread = (Groovysh.$class$java$lang$Thread = class$("java.lang.Thread"));
        }
        return $class$java$lang$Thread;
    }
    
    private static /* synthetic */ Class $get$$class$jline$History() {
        Class $class$jline$History;
        if (($class$jline$History = Groovysh.$class$jline$History) == null) {
            $class$jline$History = (Groovysh.$class$jline$History = class$("jline.History"));
        }
        return $class$jline$History;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = Groovysh.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (Groovysh.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalStateException() {
        Class $class$java$lang$IllegalStateException;
        if (($class$java$lang$IllegalStateException = Groovysh.$class$java$lang$IllegalStateException) == null) {
            $class$java$lang$IllegalStateException = (Groovysh.$class$java$lang$IllegalStateException = class$("java.lang.IllegalStateException"));
        }
        return $class$java$lang$IllegalStateException;
    }
    
    private static /* synthetic */ Class $get$$class$org$fusesource$jansi$AnsiConsole() {
        Class $class$org$fusesource$jansi$AnsiConsole;
        if (($class$org$fusesource$jansi$AnsiConsole = Groovysh.$class$org$fusesource$jansi$AnsiConsole) == null) {
            $class$org$fusesource$jansi$AnsiConsole = (Groovysh.$class$org$fusesource$jansi$AnsiConsole = class$("org.fusesource.jansi.AnsiConsole"));
        }
        return $class$org$fusesource$jansi$AnsiConsole;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Interpreter() {
        Class $class$org$codehaus$groovy$tools$shell$Interpreter;
        if (($class$org$codehaus$groovy$tools$shell$Interpreter = Groovysh.$class$org$codehaus$groovy$tools$shell$Interpreter) == null) {
            $class$org$codehaus$groovy$tools$shell$Interpreter = (Groovysh.$class$org$codehaus$groovy$tools$shell$Interpreter = class$("org.codehaus.groovy.tools.shell.Interpreter"));
        }
        return $class$org$codehaus$groovy$tools$shell$Interpreter;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Throwable() {
        Class $class$java$lang$Throwable;
        if (($class$java$lang$Throwable = Groovysh.$class$java$lang$Throwable) == null) {
            $class$java$lang$Throwable = (Groovysh.$class$java$lang$Throwable = class$("java.lang.Throwable"));
        }
        return $class$java$lang$Throwable;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$StackTraceUtils() {
        Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
        if (($class$org$codehaus$groovy$runtime$StackTraceUtils = Groovysh.$class$org$codehaus$groovy$runtime$StackTraceUtils) == null) {
            $class$org$codehaus$groovy$runtime$StackTraceUtils = (Groovysh.$class$org$codehaus$groovy$runtime$StackTraceUtils = class$("org.codehaus.groovy.runtime.StackTraceUtils"));
        }
        return $class$org$codehaus$groovy$runtime$StackTraceUtils;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = Groovysh.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (Groovysh.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$org$fusesource$jansi$AnsiRenderer() {
        Class $class$org$fusesource$jansi$AnsiRenderer;
        if (($class$org$fusesource$jansi$AnsiRenderer = Groovysh.$class$org$fusesource$jansi$AnsiRenderer) == null) {
            $class$org$fusesource$jansi$AnsiRenderer = (Groovysh.$class$org$fusesource$jansi$AnsiRenderer = class$("org.fusesource.jansi.AnsiRenderer"));
        }
        return $class$org$fusesource$jansi$AnsiRenderer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = Groovysh.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (Groovysh.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Groovysh.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Groovysh.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Groovysh.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Groovysh.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = Groovysh.$class$java$lang$System) == null) {
            $class$java$lang$System = (Groovysh.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Groovysh.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Groovysh.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = Groovysh.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (Groovysh.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$BufferManager() {
        Class $class$org$codehaus$groovy$tools$shell$BufferManager;
        if (($class$org$codehaus$groovy$tools$shell$BufferManager = Groovysh.$class$org$codehaus$groovy$tools$shell$BufferManager) == null) {
            $class$org$codehaus$groovy$tools$shell$BufferManager = (Groovysh.$class$org$codehaus$groovy$tools$shell$BufferManager = class$("org.codehaus.groovy.tools.shell.BufferManager"));
        }
        return $class$org$codehaus$groovy$tools$shell$BufferManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Shell() {
        Class $class$org$codehaus$groovy$tools$shell$Shell;
        if (($class$org$codehaus$groovy$tools$shell$Shell = Groovysh.$class$org$codehaus$groovy$tools$shell$Shell) == null) {
            $class$org$codehaus$groovy$tools$shell$Shell = (Groovysh.$class$org$codehaus$groovy$tools$shell$Shell = class$("org.codehaus.groovy.tools.shell.Shell"));
        }
        return $class$org$codehaus$groovy$tools$shell$Shell;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO() {
        Class $class$org$codehaus$groovy$tools$shell$IO;
        if (($class$org$codehaus$groovy$tools$shell$IO = Groovysh.$class$org$codehaus$groovy$tools$shell$IO) == null) {
            $class$org$codehaus$groovy$tools$shell$IO = (Groovysh.$class$org$codehaus$groovy$tools$shell$IO = class$("org.codehaus.groovy.tools.shell.IO"));
        }
        return $class$org$codehaus$groovy$tools$shell$IO;
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
