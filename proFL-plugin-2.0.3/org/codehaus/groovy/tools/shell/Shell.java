// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public class Shell implements GroovyObject
{
    protected final Logger log;
    private final CommandRegistry registry;
    private final IO io;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204061;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Shell;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    public Shell(final IO io) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.registry = (CommandRegistry)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$tools$shell$CommandRegistry()), $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(io, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert io", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.io = (IO)ScriptBytecodeAdapter.castToType(io, $get$$class$org$codehaus$groovy$tools$shell$IO());
    }
    
    public Shell() {
        Object[] array;
        final Object[] arguments = array = new Object[] { $getCallSiteArray()[3].callConstructor($get$$class$org$codehaus$groovy$tools$shell$IO()) };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 2, $get$$class$org$codehaus$groovy$tools$shell$Shell());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this();
                break;
            }
            case 1: {
                this((IO)array[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    protected List parseLine(final String line) {
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
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($getCallSiteArray[5].call(line)), $get$$class$java$util$List());
    }
    
    public Command findCommand(final String line) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(line, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert line", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Object args = $getCallSiteArray[6].callCurrent(this, line);
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            final boolean compareGreaterThan = ScriptBytecodeAdapter.compareGreaterThan(recorder2.record($getCallSiteArray[7].call(recorder2.record(args, 8)), 13), Shell.$const$0);
            recorder2.record(compareGreaterThan ? Boolean.TRUE : Boolean.FALSE, 20);
            if (compareGreaterThan) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert args.size() > 0", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        final Object name = $getCallSiteArray[8].call(args, Shell.$const$0);
        final Object command = $getCallSiteArray[9].call(this.registry, name);
        return (Command)ScriptBytecodeAdapter.castToType(command, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    public boolean isExecutable(final String line) {
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray()[10].callCurrent(this, line), null) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public Object execute(final String line) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(line, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert line", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Object command = $getCallSiteArray[11].callCurrent(this, line);
        Object result = null;
        if (DefaultTypeTransformation.booleanUnbox(command)) {
            Object args = $getCallSiteArray[12].callCurrent(this, line);
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[13].call(args), Shell.$const$1)) {
                args = ScriptBytecodeAdapter.createList(new Object[0]);
            }
            else {
                args = $getCallSiteArray[14].call(args, ScriptBytecodeAdapter.createRange(Shell.$const$1, Shell.$const$2, true));
            }
            $getCallSiteArray[15].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[16].callGetProperty(command), command, args }, new String[] { "Executing command(", "): ", "; w/args: ", "" }));
            result = $getCallSiteArray[17].call(command, args);
            $getCallSiteArray[18].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[19].call($get$$class$java$lang$String(), result) }, new String[] { "Result: ", "" }));
        }
        return ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Object());
    }
    
    public Command register(final Command command) {
        return (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray()[20].call(this.registry, command), $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    public Object leftShift(final String line) {
        return $getCallSiteArray()[21].callCurrent(this, line);
    }
    
    public Object leftShift(final Command command) {
        return $getCallSiteArray()[22].callCurrent(this, command);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$Shell()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Shell.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Shell.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Shell.__timeStamp__239_neverHappen1292524204061 = 0L;
        Shell.__timeStamp = 1292524204061L;
        $const$2 = -1;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public final CommandRegistry getRegistry() {
        return this.registry;
    }
    
    public final IO getIo() {
        return this.io;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Shell(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Shell.$callSiteArray == null || ($createCallSiteArray = Shell.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Shell.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Shell.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Shell.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry() {
        Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
        if (($class$org$codehaus$groovy$tools$shell$CommandRegistry = Shell.$class$org$codehaus$groovy$tools$shell$CommandRegistry) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandRegistry = (Shell.$class$org$codehaus$groovy$tools$shell$CommandRegistry = class$("org.codehaus.groovy.tools.shell.CommandRegistry"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Shell.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Shell.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Shell.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Shell.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = Shell.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (Shell.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Shell.$class$java$util$List) == null) {
            $class$java$util$List = (Shell.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Shell.$class$java$lang$String) == null) {
            $class$java$lang$String = (Shell.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Shell() {
        Class $class$org$codehaus$groovy$tools$shell$Shell;
        if (($class$org$codehaus$groovy$tools$shell$Shell = Shell.$class$org$codehaus$groovy$tools$shell$Shell) == null) {
            $class$org$codehaus$groovy$tools$shell$Shell = (Shell.$class$org$codehaus$groovy$tools$shell$Shell = class$("org.codehaus.groovy.tools.shell.Shell"));
        }
        return $class$org$codehaus$groovy$tools$shell$Shell;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = Shell.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (Shell.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO() {
        Class $class$org$codehaus$groovy$tools$shell$IO;
        if (($class$org$codehaus$groovy$tools$shell$IO = Shell.$class$org$codehaus$groovy$tools$shell$IO) == null) {
            $class$org$codehaus$groovy$tools$shell$IO = (Shell.$class$org$codehaus$groovy$tools$shell$IO = class$("org.codehaus.groovy.tools.shell.IO"));
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
