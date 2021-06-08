// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.util.MessageSource;
import groovy.lang.GroovyObject;

public class Main implements GroovyObject
{
    private static final MessageSource messages;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204013;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$SecurityManager;
    private static /* synthetic */ Class $class$org$fusesource$jansi$Ansi;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Main;
    private static /* synthetic */ Class $class$org$fusesource$jansi$AnsiConsole;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class array$$class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Groovysh;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$util$CliBuilder;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    public Main() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    static {
        Main.__timeStamp__239_neverHappen1292524204013 = 0L;
        Main.__timeStamp = 1292524204013L;
        $const$2 = 2;
        $const$1 = 0;
        $const$0 = 1;
        messages = (MessageSource)$getCallSiteArray()[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$MessageSource(), $get$$class$org$codehaus$groovy$tools$shell$Main());
        $getCallSiteArray()[1].call($get$$class$org$fusesource$jansi$AnsiConsole());
        $getCallSiteArray()[2].call($get$$class$org$fusesource$jansi$Ansi(), $getCallSiteArray()[3].callConstructor($get$$class$org$codehaus$groovy$tools$shell$AnsiDetector()));
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final IO io = (IO)new Reference($getCallSiteArray[4].callConstructor($get$$class$org$codehaus$groovy$tools$shell$IO()));
        ScriptBytecodeAdapter.setProperty(((Reference<Object>)io).get(), $get$$class$org$codehaus$groovy$tools$shell$Main(), $get$$class$org$codehaus$groovy$tools$shell$util$Logger(), "io");
        final Object cli = $getCallSiteArray[5].callConstructor($get$$class$groovy$util$CliBuilder(), ScriptBytecodeAdapter.createMap(new Object[] { "usage", "groovysh [options] [...]", "formatter", $getCallSiteArray[6].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter()), "writer", $getCallSiteArray[7].callGetProperty(((Reference<Object>)io).get()) }));
        $getCallSiteArray[8].call(cli, $getCallSiteArray[9].call(Main.messages, "cli.option.classpath.description"));
        $getCallSiteArray[10].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "classpath" }), $getCallSiteArray[11].call(Main.messages, "cli.option.cp.description"));
        $getCallSiteArray[12].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "help" }), $getCallSiteArray[13].call(Main.messages, "cli.option.help.description"));
        $getCallSiteArray[14].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "version" }), $getCallSiteArray[15].call(Main.messages, "cli.option.version.description"));
        $getCallSiteArray[16].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "verbose" }), $getCallSiteArray[17].call(Main.messages, "cli.option.verbose.description"));
        $getCallSiteArray[18].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "quiet" }), $getCallSiteArray[19].call(Main.messages, "cli.option.quiet.description"));
        $getCallSiteArray[20].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "debug" }), $getCallSiteArray[21].call(Main.messages, "cli.option.debug.description"));
        $getCallSiteArray[22].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "color", "args", Main.$const$0, "argName", "FLAG", "optionalArg", Boolean.TRUE }), $getCallSiteArray[23].call(Main.messages, "cli.option.color.description"));
        $getCallSiteArray[24].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "define", "args", Main.$const$0, "argName", "NAME=VALUE" }), $getCallSiteArray[25].call(Main.messages, "cli.option.define.description"));
        $getCallSiteArray[26].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "terminal", "args", Main.$const$0, "argName", "TYPE" }), $getCallSiteArray[27].call(Main.messages, "cli.option.terminal.description"));
        final Object options = $getCallSiteArray[28].call(cli, (Object)args);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[29].callGetProperty(options))) {
            $getCallSiteArray[30].call(cli);
            $getCallSiteArray[31].call($get$$class$java$lang$System(), Main.$const$1);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[32].callGetProperty(options))) {
            $getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty(((Reference<Object>)io).get()), $getCallSiteArray[35].call(Main.messages, "cli.info.version", $getCallSiteArray[36].callGetProperty($get$$class$org$codehaus$groovy$runtime$InvokerHelper())));
            $getCallSiteArray[37].call($get$$class$java$lang$System(), Main.$const$1);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[38].call(options, "T"))) {
            final Object type = $getCallSiteArray[39].call(options, "T");
            $getCallSiteArray[40].callStatic($get$$class$org$codehaus$groovy$tools$shell$Main(), type);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[41].call(options, "D"))) {
            final Object values = $getCallSiteArray[42].call(options, "D");
            $getCallSiteArray[43].call(values, new Main$_main_closure1($get$$class$org$codehaus$groovy$tools$shell$Main(), $get$$class$org$codehaus$groovy$tools$shell$Main()));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[44].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[45].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$shell$Main(), ((Reference<Object>)io).get(), "verbosity");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[46].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[47].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$shell$Main(), ((Reference<Object>)io).get(), "verbosity");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[48].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[49].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$shell$Main(), ((Reference<Object>)io).get(), "verbosity");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[50].call(options, "C"))) {
            final Object value = $getCallSiteArray[51].call(options, "C");
            $getCallSiteArray[52].callStatic($get$$class$org$codehaus$groovy$tools$shell$Main(), value);
        }
        final Object code = new Reference(null);
        $getCallSiteArray[53].callStatic($get$$class$org$codehaus$groovy$tools$shell$Main(), new Main$_main_closure2($get$$class$org$codehaus$groovy$tools$shell$Main(), $get$$class$org$codehaus$groovy$tools$shell$Main(), (Reference<Object>)code, (Reference<Object>)io));
        final Groovysh shell = (Groovysh)$getCallSiteArray[54].callConstructor($get$$class$org$codehaus$groovy$tools$shell$Groovysh(), ((Reference<Object>)io).get());
        final SecurityManager psm = (SecurityManager)ScriptBytecodeAdapter.castToType($getCallSiteArray[55].call($get$$class$java$lang$System()), $get$$class$java$lang$SecurityManager());
        $getCallSiteArray[56].call($get$$class$java$lang$System(), $getCallSiteArray[57].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager()));
        try {
            ((Reference<Object>)code).set($getCallSiteArray[58].call(shell, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[59].call(options), $get$array$$class$java$lang$String()), $get$array$$class$java$lang$String())));
            $getCallSiteArray[60].call($get$$class$java$lang$System(), psm);
        }
        finally {
            $getCallSiteArray[61].call($get$$class$java$lang$System(), psm);
        }
        $getCallSiteArray[62].call($get$$class$java$lang$System(), ((Reference<Object>)code).get());
    }
    
    public static void setTerminalType(String type) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(type, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert type != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final String switchValue;
        type = (switchValue = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[63].call(type), $get$$class$java$lang$String()));
        Label_0247: {
            if (ScriptBytecodeAdapter.isCase(switchValue, "auto")) {
                type = (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
            }
            else if (ScriptBytecodeAdapter.isCase(switchValue, "unix")) {
                type = "jline.UnixTerminal";
            }
            else {
                if (!ScriptBytecodeAdapter.isCase(switchValue, "win")) {
                    if (!ScriptBytecodeAdapter.isCase(switchValue, "windows")) {
                        if (!ScriptBytecodeAdapter.isCase(switchValue, "false")) {
                            if (!ScriptBytecodeAdapter.isCase(switchValue, "off")) {
                                if (!ScriptBytecodeAdapter.isCase(switchValue, "none")) {
                                    break Label_0247;
                                }
                            }
                        }
                        type = "jline.UnsupportedTerminal";
                        ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$org$codehaus$groovy$tools$shell$Main(), $get$$class$org$fusesource$jansi$Ansi(), "enabled");
                        break Label_0247;
                    }
                }
                type = "jline.WindowsTerminal";
            }
        }
        if (ScriptBytecodeAdapter.compareNotEqual(type, null)) {
            $getCallSiteArray[64].call($get$$class$java$lang$System(), "jline.terminal", type);
        }
    }
    
    public static void setColor(Object value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            value = Boolean.TRUE;
        }
        else {
            value = $getCallSiteArray[65].call($getCallSiteArray[66].call($get$$class$java$lang$Boolean(), value));
        }
        ScriptBytecodeAdapter.setProperty(value, $get$$class$org$codehaus$groovy$tools$shell$Main(), $get$$class$org$fusesource$jansi$Ansi(), "enabled");
    }
    
    public static void setSystemProperty(final String nameValue) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        String name = (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        String value = (String)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String());
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[67].call(nameValue, "="), Main.$const$1)) {
            final Object tmp = $getCallSiteArray[68].call(nameValue, "=", Main.$const$2);
            name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[69].call(tmp, Main.$const$1), $get$$class$java$lang$String());
            value = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[70].call(tmp, Main.$const$0), $get$$class$java$lang$String());
        }
        else {
            name = nameValue;
            value = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[71].call($getCallSiteArray[72].callGetProperty($get$$class$java$lang$Boolean())), $get$$class$java$lang$String());
        }
        $getCallSiteArray[73].call($get$$class$java$lang$System(), name, value);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$Main()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Main.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Main.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[74];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Main(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main.$callSiteArray == null || ($createCallSiteArray = Main.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$SecurityManager() {
        Class $class$java$lang$SecurityManager;
        if (($class$java$lang$SecurityManager = Main.$class$java$lang$SecurityManager) == null) {
            $class$java$lang$SecurityManager = (Main.$class$java$lang$SecurityManager = class$("java.lang.SecurityManager"));
        }
        return $class$java$lang$SecurityManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$fusesource$jansi$Ansi() {
        Class $class$org$fusesource$jansi$Ansi;
        if (($class$org$fusesource$jansi$Ansi = Main.$class$org$fusesource$jansi$Ansi) == null) {
            $class$org$fusesource$jansi$Ansi = (Main.$class$org$fusesource$jansi$Ansi = class$("org.fusesource.jansi.Ansi"));
        }
        return $class$org$fusesource$jansi$Ansi;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Main() {
        Class $class$org$codehaus$groovy$tools$shell$Main;
        if (($class$org$codehaus$groovy$tools$shell$Main = Main.$class$org$codehaus$groovy$tools$shell$Main) == null) {
            $class$org$codehaus$groovy$tools$shell$Main = (Main.$class$org$codehaus$groovy$tools$shell$Main = class$("org.codehaus.groovy.tools.shell.Main"));
        }
        return $class$org$codehaus$groovy$tools$shell$Main;
    }
    
    private static /* synthetic */ Class $get$$class$org$fusesource$jansi$AnsiConsole() {
        Class $class$org$fusesource$jansi$AnsiConsole;
        if (($class$org$fusesource$jansi$AnsiConsole = Main.$class$org$fusesource$jansi$AnsiConsole) == null) {
            $class$org$fusesource$jansi$AnsiConsole = (Main.$class$org$fusesource$jansi$AnsiConsole = class$("org.fusesource.jansi.AnsiConsole"));
        }
        return $class$org$fusesource$jansi$AnsiConsole;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$AnsiDetector() {
        Class $class$org$codehaus$groovy$tools$shell$AnsiDetector;
        if (($class$org$codehaus$groovy$tools$shell$AnsiDetector = Main.$class$org$codehaus$groovy$tools$shell$AnsiDetector) == null) {
            $class$org$codehaus$groovy$tools$shell$AnsiDetector = (Main.$class$org$codehaus$groovy$tools$shell$AnsiDetector = class$("org.codehaus.groovy.tools.shell.AnsiDetector"));
        }
        return $class$org$codehaus$groovy$tools$shell$AnsiDetector;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity() {
        Class $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
        if (($class$org$codehaus$groovy$tools$shell$IO$Verbosity = Main.$class$org$codehaus$groovy$tools$shell$IO$Verbosity) == null) {
            $class$org$codehaus$groovy$tools$shell$IO$Verbosity = (Main.$class$org$codehaus$groovy$tools$shell$IO$Verbosity = class$("org.codehaus.groovy.tools.shell.IO$Verbosity"));
        }
        return $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Main.$class$java$lang$String) == null) {
            $class$java$lang$String = (Main.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$String() {
        Class array$$class$java$lang$String;
        if ((array$$class$java$lang$String = Main.array$$class$java$lang$String) == null) {
            array$$class$java$lang$String = (Main.array$$class$java$lang$String = class$("[Ljava.lang.String;"));
        }
        return array$$class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$MessageSource() {
        Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
        if (($class$org$codehaus$groovy$tools$shell$util$MessageSource = Main.$class$org$codehaus$groovy$tools$shell$util$MessageSource) == null) {
            $class$org$codehaus$groovy$tools$shell$util$MessageSource = (Main.$class$org$codehaus$groovy$tools$shell$util$MessageSource = class$("org.codehaus.groovy.tools.shell.util.MessageSource"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Main.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Main.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter() {
        Class $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
        if (($class$org$codehaus$groovy$tools$shell$util$HelpFormatter = Main.$class$org$codehaus$groovy$tools$shell$util$HelpFormatter) == null) {
            $class$org$codehaus$groovy$tools$shell$util$HelpFormatter = (Main.$class$org$codehaus$groovy$tools$shell$util$HelpFormatter = class$("org.codehaus.groovy.tools.shell.util.HelpFormatter"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Main.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Main.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = Main.$class$java$lang$System) == null) {
            $class$java$lang$System = (Main.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager() {
        Class $class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager;
        if (($class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager = Main.$class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager) == null) {
            $class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager = (Main.$class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager = class$("org.codehaus.groovy.tools.shell.util.NoExitSecurityManager"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$NoExitSecurityManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Groovysh() {
        Class $class$org$codehaus$groovy$tools$shell$Groovysh;
        if (($class$org$codehaus$groovy$tools$shell$Groovysh = Main.$class$org$codehaus$groovy$tools$shell$Groovysh) == null) {
            $class$org$codehaus$groovy$tools$shell$Groovysh = (Main.$class$org$codehaus$groovy$tools$shell$Groovysh = class$("org.codehaus.groovy.tools.shell.Groovysh"));
        }
        return $class$org$codehaus$groovy$tools$shell$Groovysh;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = Main.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (Main.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = Main.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (Main.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$CliBuilder() {
        Class $class$groovy$util$CliBuilder;
        if (($class$groovy$util$CliBuilder = Main.$class$groovy$util$CliBuilder) == null) {
            $class$groovy$util$CliBuilder = (Main.$class$groovy$util$CliBuilder = class$("groovy.util.CliBuilder"));
        }
        return $class$groovy$util$CliBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO() {
        Class $class$org$codehaus$groovy$tools$shell$IO;
        if (($class$org$codehaus$groovy$tools$shell$IO = Main.$class$org$codehaus$groovy$tools$shell$IO) == null) {
            $class$org$codehaus$groovy$tools$shell$IO = (Main.$class$org$codehaus$groovy$tools$shell$IO = class$("org.codehaus.groovy.tools.shell.IO"));
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
