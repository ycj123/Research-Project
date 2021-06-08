// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import groovy.lang.MetaClass;
import groovyjarjarcommonscli.Options;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovyjarjarcommonscli.CommandLine;
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class GrapeMain extends Script
{
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205456;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$commons$cli$CommandLine;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$apache$commons$cli$OptionBuilder;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$apache$commons$cli$Options;
    private static /* synthetic */ Class $class$org$apache$commons$cli$PosixParser;
    private static /* synthetic */ Class $class$org$apache$commons$cli$OptionGroup;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$GrapeMain;
    private static /* synthetic */ Class array$$class$java$lang$String;
    
    public GrapeMain() {
        $getCallSiteArray();
    }
    
    public GrapeMain(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$tools$GrapeMain(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(new GrapeMain$_run_closure1(this, this), $get$$class$org$codehaus$groovy$tools$GrapeMain(), this, "install");
        ScriptBytecodeAdapter.setGroovyObjectProperty(new GrapeMain$_run_closure2(this, this), $get$$class$org$codehaus$groovy$tools$GrapeMain(), this, "list");
        ScriptBytecodeAdapter.setGroovyObjectProperty(new GrapeMain$_run_closure3(this, this), $get$$class$org$codehaus$groovy$tools$GrapeMain(), this, "resolve");
        final Object commands = new Reference(ScriptBytecodeAdapter.createMap(new Object[] { "install", ScriptBytecodeAdapter.createMap(new Object[] { "closure", $getCallSiteArray[1].callGroovyObjectGetProperty(this), "shortHelp", "Installs a particular grape" }), "list", ScriptBytecodeAdapter.createMap(new Object[] { "closure", $getCallSiteArray[2].callGroovyObjectGetProperty(this), "shortHelp", "Lists all installed grapes" }), "resolve", ScriptBytecodeAdapter.createMap(new Object[] { "closure", $getCallSiteArray[3].callGroovyObjectGetProperty(this), "shortHelp", "Enumerates the jars used by a grape" }) }));
        final Options options = (Options)new Reference($getCallSiteArray[4].callConstructor($get$$class$org$apache$commons$cli$Options()));
        $getCallSiteArray[5].call(((Reference<Object>)options).get(), $getCallSiteArray[6].call($getCallSiteArray[7].call($getCallSiteArray[8].call($getCallSiteArray[9].call($getCallSiteArray[10].call($get$$class$org$apache$commons$cli$OptionBuilder(), "define"), "define a system property"), Boolean.TRUE), "name=value"), "D"));
        $getCallSiteArray[11].call(((Reference<Object>)options).get(), $getCallSiteArray[12].call($getCallSiteArray[13].call($getCallSiteArray[14].call($getCallSiteArray[15].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "usage information"), "help"), "h"));
        $getCallSiteArray[16].call(((Reference<Object>)options).get(), $getCallSiteArray[17].call($getCallSiteArray[18].call($getCallSiteArray[19].call($getCallSiteArray[20].call($getCallSiteArray[21].call($getCallSiteArray[22].callConstructor($get$$class$org$apache$commons$cli$OptionGroup()), $getCallSiteArray[23].call($getCallSiteArray[24].call($getCallSiteArray[25].call($getCallSiteArray[26].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "Log level 0 - only errors"), "quiet"), "q")), $getCallSiteArray[27].call($getCallSiteArray[28].call($getCallSiteArray[29].call($getCallSiteArray[30].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "Log level 1 - errors and warnings"), "warn"), "w")), $getCallSiteArray[31].call($getCallSiteArray[32].call($getCallSiteArray[33].call($getCallSiteArray[34].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "Log level 2 - info"), "info"), "i")), $getCallSiteArray[35].call($getCallSiteArray[36].call($getCallSiteArray[37].call($getCallSiteArray[38].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "Log level 3 - verbose"), "verbose"), "V")), $getCallSiteArray[39].call($getCallSiteArray[40].call($getCallSiteArray[41].call($getCallSiteArray[42].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "Log level 4 - debug"), "debug"), "d")));
        $getCallSiteArray[43].call(((Reference<Object>)options).get(), $getCallSiteArray[44].call($getCallSiteArray[45].call($getCallSiteArray[46].call($getCallSiteArray[47].call($get$$class$org$apache$commons$cli$OptionBuilder(), Boolean.FALSE), "display the Groovy and JVM versions"), "version"), "v"));
        final CommandLine cmd = (CommandLine)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[48].call($getCallSiteArray[49].callConstructor($get$$class$org$apache$commons$cli$PosixParser()), ((Reference<Object>)options).get(), $getCallSiteArray[50].callGroovyObjectGetProperty(this), Boolean.TRUE), $get$$class$org$apache$commons$cli$CommandLine()));
        ScriptBytecodeAdapter.setGroovyObjectProperty(new GrapeMain$_run_closure4(this, this, (Reference<Object>)commands, (Reference<Object>)options), $get$$class$org$codehaus$groovy$tools$GrapeMain(), this, "grapeHelp");
        ScriptBytecodeAdapter.setGroovyObjectProperty(new GrapeMain$_run_closure5(this, this, (Reference<Object>)cmd), $get$$class$org$codehaus$groovy$tools$GrapeMain(), this, "setupLogging");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[51].call(((Reference<Object>)cmd).get(), "h"))) {
            $getCallSiteArray[52].callCurrent(this);
            return null;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[53].call(((Reference<Object>)cmd).get(), "v"))) {
            final String version = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[54].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper()), $get$$class$java$lang$String());
            $getCallSiteArray[55].callCurrent(this, new GStringImpl(new Object[] { version, $getCallSiteArray[56].call($get$$class$java$lang$System(), "java.version") }, new String[] { "Groovy Version: ", " JVM: ", "" }));
            return null;
        }
        $getCallSiteArray[57].callSafe($getCallSiteArray[58].call(((Reference<Object>)cmd).get(), "D"), new GrapeMain$_run_closure6(this, this));
        final String[] arg = (String[])ScriptBytecodeAdapter.castToType($getCallSiteArray[59].callGetProperty(((Reference<Object>)cmd).get()), $get$array$$class$java$lang$String());
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[60].callGetPropertySafe(arg), GrapeMain.$const$0)) {
            return $getCallSiteArray[61].callCurrent(this);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[62].call(((Reference<Object>)commands).get(), $getCallSiteArray[63].call(arg, GrapeMain.$const$0)))) {
            return $getCallSiteArray[64].call($getCallSiteArray[65].call(((Reference<Object>)commands).get(), $getCallSiteArray[66].call(arg, GrapeMain.$const$0)), arg, ((Reference<Object>)cmd).get());
        }
        return $getCallSiteArray[67].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[68].call(arg, GrapeMain.$const$0) }, new String[] { "grape: '", "' is not a grape command. See 'grape --help'" }));
    }
    
    static {
        GrapeMain.__timeStamp__239_neverHappen1292524205456 = 0L;
        GrapeMain.__timeStamp = 1292524205456L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[69];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain.$callSiteArray == null || ($createCallSiteArray = GrapeMain.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$CommandLine() {
        Class $class$org$apache$commons$cli$CommandLine;
        if (($class$org$apache$commons$cli$CommandLine = GrapeMain.$class$org$apache$commons$cli$CommandLine) == null) {
            $class$org$apache$commons$cli$CommandLine = (GrapeMain.$class$org$apache$commons$cli$CommandLine = class$("groovyjarjarcommonscli.CommandLine"));
        }
        return $class$org$apache$commons$cli$CommandLine;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = GrapeMain.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (GrapeMain.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = GrapeMain.$class$java$lang$System) == null) {
            $class$java$lang$System = (GrapeMain.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = GrapeMain.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (GrapeMain.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$OptionBuilder() {
        Class $class$org$apache$commons$cli$OptionBuilder;
        if (($class$org$apache$commons$cli$OptionBuilder = GrapeMain.$class$org$apache$commons$cli$OptionBuilder) == null) {
            $class$org$apache$commons$cli$OptionBuilder = (GrapeMain.$class$org$apache$commons$cli$OptionBuilder = class$("groovyjarjarcommonscli.OptionBuilder"));
        }
        return $class$org$apache$commons$cli$OptionBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GrapeMain.$class$java$lang$String) == null) {
            $class$java$lang$String = (GrapeMain.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$Options() {
        Class $class$org$apache$commons$cli$Options;
        if (($class$org$apache$commons$cli$Options = GrapeMain.$class$org$apache$commons$cli$Options) == null) {
            $class$org$apache$commons$cli$Options = (GrapeMain.$class$org$apache$commons$cli$Options = class$("groovyjarjarcommonscli.Options"));
        }
        return $class$org$apache$commons$cli$Options;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$PosixParser() {
        Class $class$org$apache$commons$cli$PosixParser;
        if (($class$org$apache$commons$cli$PosixParser = GrapeMain.$class$org$apache$commons$cli$PosixParser) == null) {
            $class$org$apache$commons$cli$PosixParser = (GrapeMain.$class$org$apache$commons$cli$PosixParser = class$("groovyjarjarcommonscli.PosixParser"));
        }
        return $class$org$apache$commons$cli$PosixParser;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$OptionGroup() {
        Class $class$org$apache$commons$cli$OptionGroup;
        if (($class$org$apache$commons$cli$OptionGroup = GrapeMain.$class$org$apache$commons$cli$OptionGroup) == null) {
            $class$org$apache$commons$cli$OptionGroup = (GrapeMain.$class$org$apache$commons$cli$OptionGroup = class$("groovyjarjarcommonscli.OptionGroup"));
        }
        return $class$org$apache$commons$cli$OptionGroup;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$GrapeMain() {
        Class $class$org$codehaus$groovy$tools$GrapeMain;
        if (($class$org$codehaus$groovy$tools$GrapeMain = GrapeMain.$class$org$codehaus$groovy$tools$GrapeMain) == null) {
            $class$org$codehaus$groovy$tools$GrapeMain = (GrapeMain.$class$org$codehaus$groovy$tools$GrapeMain = class$("org.codehaus.groovy.tools.GrapeMain"));
        }
        return $class$org$codehaus$groovy$tools$GrapeMain;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$String() {
        Class array$$class$java$lang$String;
        if ((array$$class$java$lang$String = GrapeMain.array$$class$java$lang$String) == null) {
            array$$class$java$lang$String = (GrapeMain.array$$class$java$lang$String = class$("[Ljava.lang.String;"));
        }
        return array$$class$java$lang$String;
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
