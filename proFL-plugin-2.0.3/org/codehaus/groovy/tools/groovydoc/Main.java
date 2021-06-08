// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Properties;
import java.io.IOException;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.tools.shell.IO;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import java.io.File;
import org.codehaus.groovy.tools.shell.util.MessageSource;
import groovy.lang.GroovyObject;

public class Main implements GroovyObject
{
    private static final MessageSource messages;
    private static File styleSheetFile;
    private static File overviewFile;
    private static File destDir;
    private static String windowTitle;
    private static String docTitle;
    private static String header;
    private static String footer;
    private static Boolean author;
    private static Boolean noScripts;
    private static Boolean noMainForScripts;
    private static Boolean privateScope;
    private static Boolean packageScope;
    private static Boolean publicScope;
    private static Boolean protectedScope;
    private static Boolean debug;
    private static String[] sourcepath;
    private static List<String> sourceFilesToDoc;
    private static List<String> remainingArgs;
    private static List<String> exclusions;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203900;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$groovydoc$Main;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$groovydoc$FileOutputTool;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class array$$class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo;
    private static /* synthetic */ Class $class$groovy$util$CliBuilder;
    private static /* synthetic */ Class $class$java$util$Properties;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    public Main() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final IO io = (IO)$getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$IO());
        ScriptBytecodeAdapter.setProperty(io, $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $get$$class$org$codehaus$groovy$tools$shell$util$Logger(), "io");
        final Object cli = $getCallSiteArray[1].callConstructor($get$$class$groovy$util$CliBuilder(), ScriptBytecodeAdapter.createMap(new Object[] { "usage", "groovydoc [options] [packagenames] [sourcefiles]", "writer", $getCallSiteArray[2].callGetProperty(io), "posix", Boolean.FALSE }));
        $getCallSiteArray[3].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "help" }), $getCallSiteArray[4].call(Main.messages, "cli.option.help.description"));
        $getCallSiteArray[5].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "version" }), $getCallSiteArray[6].call(Main.messages, "cli.option.version.description"));
        $getCallSiteArray[7].call(cli, $getCallSiteArray[8].call(Main.messages, "cli.option.verbose.description"));
        $getCallSiteArray[9].call(cli, $getCallSiteArray[10].call(Main.messages, "cli.option.quiet.description"));
        $getCallSiteArray[11].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "debug" }), $getCallSiteArray[12].call(Main.messages, "cli.option.debug.description"));
        $getCallSiteArray[13].call(cli, $getCallSiteArray[14].call(Main.messages, "cli.option.classpath.description"));
        $getCallSiteArray[15].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "classpath" }), $getCallSiteArray[16].call(Main.messages, "cli.option.cp.description"));
        $getCallSiteArray[17].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "longOpt", "destdir", "args", Main.$const$0, "argName", "dir" }), $getCallSiteArray[18].call(Main.messages, "cli.option.destdir.description"));
        $getCallSiteArray[19].call(cli, $getCallSiteArray[20].call(Main.messages, "cli.option.author.description"));
        $getCallSiteArray[21].call(cli, $getCallSiteArray[22].call(Main.messages, "cli.option.noscripts.description"));
        $getCallSiteArray[23].call(cli, $getCallSiteArray[24].call(Main.messages, "cli.option.nomainforscripts.description"));
        $getCallSiteArray[25].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "file" }), $getCallSiteArray[26].call(Main.messages, "cli.option.overview.description"));
        $getCallSiteArray[27].call(cli, $getCallSiteArray[28].call(Main.messages, "cli.option.public.description"));
        $getCallSiteArray[29].call(cli, $getCallSiteArray[30].call(Main.messages, "cli.option.protected.description"));
        $getCallSiteArray[31].call(cli, $getCallSiteArray[32].call(Main.messages, "cli.option.package.description"));
        $getCallSiteArray[33].call(cli, $getCallSiteArray[34].call(Main.messages, "cli.option.private.description"));
        $getCallSiteArray[35].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "text" }), $getCallSiteArray[36].call(Main.messages, "cli.option.windowtitle.description"));
        $getCallSiteArray[37].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "html" }), $getCallSiteArray[38].call(Main.messages, "cli.option.doctitle.description"));
        $getCallSiteArray[39].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "html" }), $getCallSiteArray[40].call(Main.messages, "cli.option.header.description"));
        $getCallSiteArray[41].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "html" }), $getCallSiteArray[42].call(Main.messages, "cli.option.footer.description"));
        $getCallSiteArray[43].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "pkglist" }), $getCallSiteArray[44].call(Main.messages, "cli.option.exclude.description"));
        $getCallSiteArray[45].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "path" }), $getCallSiteArray[46].call(Main.messages, "cli.option.stylesheetfile.description"));
        $getCallSiteArray[47].call(cli, ScriptBytecodeAdapter.createMap(new Object[] { "args", Main.$const$0, "argName", "pathlist" }), $getCallSiteArray[48].call(Main.messages, "cli.option.sourcepath.description"));
        final Object options = $getCallSiteArray[49].call(cli, (Object)args);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[50].callGetProperty(options))) {
            $getCallSiteArray[51].call(cli);
            return;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[52].callGetProperty(options))) {
            $getCallSiteArray[53].call($getCallSiteArray[54].callGetProperty(io), $getCallSiteArray[55].call(Main.messages, "cli.info.version", $getCallSiteArray[56].callGetProperty($get$$class$org$codehaus$groovy$runtime$InvokerHelper())));
            return;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[57].callGetProperty(options))) {
            Main.styleSheetFile = (File)$getCallSiteArray[58].callConstructor($get$$class$java$io$File(), $getCallSiteArray[59].callGetProperty(options));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[60].callGetProperty(options))) {
            Main.overviewFile = (File)$getCallSiteArray[61].callConstructor($get$$class$java$io$File(), $getCallSiteArray[62].callGetProperty(options));
        }
        final CallSite callSite = $getCallSiteArray[63];
        final Class $get$$class$java$io$File = $get$$class$java$io$File();
        Object callGetProperty;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty = $getCallSiteArray[64].callGetProperty(options))) {
            callGetProperty = ".";
        }
        Main.destDir = (File)callSite.callConstructor($get$$class$java$io$File, callGetProperty);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[65].callGetProperty(options))) {
            Main.exclusions = (List<String>)ScriptBytecodeAdapter.castToType($getCallSiteArray[66].call($getCallSiteArray[67].callGetProperty(options), ":"), $get$$class$java$util$List());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[68].callGetProperty(options))) {
            final Object list = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
            $getCallSiteArray[69].call($getCallSiteArray[70].callGetProperty(options), new Main$_main_closure1($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), (Reference<Object>)list));
            Main.sourcepath = (String[])ScriptBytecodeAdapter.castToType($getCallSiteArray[71].call(((Reference<Object>)list).get()), $get$array$$class$java$lang$String());
        }
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[72].call($get$$class$java$lang$Boolean(), $getCallSiteArray[73].callGetProperty(options)))) {
            object = Boolean.FALSE;
        }
        Main.author = (Boolean)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$Boolean());
        Object object2;
        if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[74].call($get$$class$java$lang$Boolean(), $getCallSiteArray[75].callGetProperty(options)))) {
            object2 = Boolean.FALSE;
        }
        Main.noScripts = (Boolean)ScriptBytecodeAdapter.castToType(object2, $get$$class$java$lang$Boolean());
        Object object3;
        if (!DefaultTypeTransformation.booleanUnbox(object3 = $getCallSiteArray[76].call($get$$class$java$lang$Boolean(), $getCallSiteArray[77].callGetProperty(options)))) {
            object3 = Boolean.FALSE;
        }
        Main.noMainForScripts = (Boolean)ScriptBytecodeAdapter.castToType(object3, $get$$class$java$lang$Boolean());
        Object object4;
        if (!DefaultTypeTransformation.booleanUnbox(object4 = $getCallSiteArray[78].call($get$$class$java$lang$Boolean(), $getCallSiteArray[79].callGetProperty(options)))) {
            object4 = Boolean.FALSE;
        }
        Main.packageScope = (Boolean)ScriptBytecodeAdapter.castToType(object4, $get$$class$java$lang$Boolean());
        Object object5;
        if (!DefaultTypeTransformation.booleanUnbox(object5 = $getCallSiteArray[80].call($get$$class$java$lang$Boolean(), $getCallSiteArray[81].callGetProperty(options)))) {
            object5 = Boolean.FALSE;
        }
        Main.privateScope = (Boolean)ScriptBytecodeAdapter.castToType(object5, $get$$class$java$lang$Boolean());
        Object object6;
        if (!DefaultTypeTransformation.booleanUnbox(object6 = $getCallSiteArray[82].call($get$$class$java$lang$Boolean(), $getCallSiteArray[83].callGetProperty(options)))) {
            object6 = Boolean.FALSE;
        }
        Main.protectedScope = (Boolean)ScriptBytecodeAdapter.castToType(object6, $get$$class$java$lang$Boolean());
        Object object7;
        if (!DefaultTypeTransformation.booleanUnbox(object7 = $getCallSiteArray[84].call($get$$class$java$lang$Boolean(), $getCallSiteArray[85].callGetProperty(options)))) {
            object7 = Boolean.FALSE;
        }
        Main.publicScope = (Boolean)ScriptBytecodeAdapter.castToType(object7, $get$$class$java$lang$Boolean());
        Integer scopeCount = Main.$const$1;
        if (DefaultTypeTransformation.booleanUnbox(Main.packageScope)) {
            scopeCount = (Integer)$getCallSiteArray[86].call(scopeCount);
        }
        if (DefaultTypeTransformation.booleanUnbox(Main.privateScope)) {
            scopeCount = (Integer)$getCallSiteArray[87].call(scopeCount);
        }
        if (DefaultTypeTransformation.booleanUnbox(Main.protectedScope)) {
            scopeCount = (Integer)$getCallSiteArray[88].call(scopeCount);
        }
        if (DefaultTypeTransformation.booleanUnbox(Main.publicScope)) {
            scopeCount = (Integer)$getCallSiteArray[89].call(scopeCount);
        }
        if (ScriptBytecodeAdapter.compareEqual(scopeCount, Main.$const$1)) {
            Main.protectedScope = Boolean.TRUE;
        }
        else if (ScriptBytecodeAdapter.compareGreaterThan(scopeCount, Main.$const$0)) {
            $getCallSiteArray[90].call($getCallSiteArray[91].callGetProperty($get$$class$java$lang$System()), "groovydoc: Error - More than one of -public, -private, -package, or -protected specified.");
            $getCallSiteArray[92].call(cli);
            return;
        }
        Object callGetProperty2;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty2 = $getCallSiteArray[93].callGetProperty(options))) {
            callGetProperty2 = "";
        }
        Main.windowTitle = (String)ScriptBytecodeAdapter.castToType(callGetProperty2, $get$$class$java$lang$String());
        Object callGetProperty3;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty3 = $getCallSiteArray[94].callGetProperty(options))) {
            callGetProperty3 = "";
        }
        Main.docTitle = (String)ScriptBytecodeAdapter.castToType(callGetProperty3, $get$$class$java$lang$String());
        Object callGetProperty4;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty4 = $getCallSiteArray[95].callGetProperty(options))) {
            callGetProperty4 = "";
        }
        Main.header = (String)ScriptBytecodeAdapter.castToType(callGetProperty4, $get$$class$java$lang$String());
        Object callGetProperty5;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty5 = $getCallSiteArray[96].callGetProperty(options))) {
            callGetProperty5 = "";
        }
        Main.footer = (String)ScriptBytecodeAdapter.castToType(callGetProperty5, $get$$class$java$lang$String());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[97].callGetProperty(options))) {
            final Object values = $getCallSiteArray[98].callGetProperty(options);
            $getCallSiteArray[99].call(values, new Main$_main_closure2($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $get$$class$org$codehaus$groovy$tools$groovydoc$Main()));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[100].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[101].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), io, "verbosity");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[102].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[103].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), io, "verbosity");
            Main.debug = Boolean.TRUE;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[104].callGetProperty(options))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[105].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity()), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), io, "verbosity");
        }
        Main.remainingArgs = (List<String>)ScriptBytecodeAdapter.castToType($getCallSiteArray[106].call(options), $get$$class$java$util$List());
        if (!DefaultTypeTransformation.booleanUnbox(Main.remainingArgs)) {
            $getCallSiteArray[107].call($getCallSiteArray[108].callGetProperty($get$$class$java$lang$System()), "groovydoc: Error - No packages or classes specified.");
            $getCallSiteArray[109].call(cli);
            return;
        }
        $getCallSiteArray[110].callStatic($get$$class$org$codehaus$groovy$tools$groovydoc$Main());
    }
    
    public static void execute() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Properties properties = (Properties)$getCallSiteArray[111].callConstructor($get$$class$java$util$Properties());
        $getCallSiteArray[112].call(properties, "windowTitle", Main.windowTitle);
        $getCallSiteArray[113].call(properties, "docTitle", Main.docTitle);
        $getCallSiteArray[114].call(properties, "footer", Main.footer);
        $getCallSiteArray[115].call(properties, "header", Main.header);
        $getCallSiteArray[116].call(properties, "privateScope", $getCallSiteArray[117].call(Main.privateScope));
        $getCallSiteArray[118].call(properties, "protectedScope", $getCallSiteArray[119].call(Main.protectedScope));
        $getCallSiteArray[120].call(properties, "publicScope", $getCallSiteArray[121].call(Main.publicScope));
        $getCallSiteArray[122].call(properties, "packageScope", $getCallSiteArray[123].call(Main.packageScope));
        $getCallSiteArray[124].call(properties, "author", $getCallSiteArray[125].call(Main.author));
        $getCallSiteArray[126].call(properties, "processScripts", $getCallSiteArray[127].call(DefaultTypeTransformation.booleanUnbox(Main.noScripts) ? Boolean.FALSE : Boolean.TRUE));
        $getCallSiteArray[128].call(properties, "includeMainForScripts", $getCallSiteArray[129].call(DefaultTypeTransformation.booleanUnbox(Main.noMainForScripts) ? Boolean.FALSE : Boolean.TRUE));
        final CallSite callSite = $getCallSiteArray[130];
        final Properties properties2 = properties;
        final String s = "overviewFile";
        Object callGetPropertySafe;
        if (!DefaultTypeTransformation.booleanUnbox(callGetPropertySafe = $getCallSiteArray[131].callGetPropertySafe(Main.overviewFile))) {
            callGetPropertySafe = "";
        }
        callSite.call(properties2, s, callGetPropertySafe);
        final Object links = $getCallSiteArray[132].callConstructor($get$$class$java$util$ArrayList());
        $getCallSiteArray[133].callStatic($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), Main.remainingArgs, Main.sourcepath, Main.exclusions);
        final GroovyDocTool htmlTool = (GroovyDocTool)$getCallSiteArray[134].callConstructor($get$$class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool(), ArrayUtil.createArray($getCallSiteArray[135].callConstructor($get$$class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager()), Main.sourcepath, $getCallSiteArray[136].callGetProperty($get$$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo()), $getCallSiteArray[137].callGetProperty($get$$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo()), $getCallSiteArray[138].callGetProperty($get$$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo()), links, properties));
        $getCallSiteArray[139].call(htmlTool, Main.sourceFilesToDoc);
        final FileOutputTool output = (FileOutputTool)$getCallSiteArray[140].callConstructor($get$$class$org$codehaus$groovy$tools$groovydoc$FileOutputTool());
        $getCallSiteArray[141].call(htmlTool, output, $getCallSiteArray[142].callGetProperty(Main.destDir));
        if (ScriptBytecodeAdapter.compareNotEqual(Main.styleSheetFile, null)) {
            try {
                ScriptBytecodeAdapter.setProperty($getCallSiteArray[143].callGetProperty(Main.styleSheetFile), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $getCallSiteArray[144].callConstructor($get$$class$java$io$File(), Main.destDir, "stylesheet.css"), "text");
            }
            catch (IOException e) {
                $getCallSiteArray[145].callStatic($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $getCallSiteArray[146].call($getCallSiteArray[147].call($getCallSiteArray[148].call("Warning: Unable to copy specified stylesheet '", $getCallSiteArray[149].callGetProperty(Main.styleSheetFile)), "'. Using default stylesheet instead. Due to: "), $getCallSiteArray[150].callGetProperty(e)));
            }
        }
    }
    
    public static Object collectSourceFileNames(final List<String> remainingArgs, final String[] sourceDirs, final List<String> exclusions) {
        final String[] sourceDirs2 = (Object)new Reference((T)(Object)sourceDirs);
        final List exclusions2 = (List)new Reference(exclusions);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Main.sourceFilesToDoc = (List<String>)ScriptBytecodeAdapter.createList(new Object[0]);
        return $getCallSiteArray[151].call(remainingArgs, new Main$_collectSourceFileNames_closure3($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), $get$$class$org$codehaus$groovy$tools$groovydoc$Main(), (Reference<Object>)(Object)sourceDirs2, (Reference<Object>)exclusions2));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$groovydoc$Main()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Main.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Main.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Main.__timeStamp__239_neverHappen1292524203900 = 0L;
        Main.__timeStamp = 1292524203900L;
        $const$1 = 0;
        $const$0 = 1;
        Main.debug = Boolean.FALSE;
        messages = (MessageSource)$getCallSiteArray()[152].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$MessageSource(), $get$$class$org$codehaus$groovy$tools$groovydoc$Main());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[153];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$groovydoc$Main(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main.$callSiteArray == null || ($createCallSiteArray = Main.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$groovydoc$Main() {
        Class $class$org$codehaus$groovy$tools$groovydoc$Main;
        if (($class$org$codehaus$groovy$tools$groovydoc$Main = Main.$class$org$codehaus$groovy$tools$groovydoc$Main) == null) {
            $class$org$codehaus$groovy$tools$groovydoc$Main = (Main.$class$org$codehaus$groovy$tools$groovydoc$Main = class$("org.codehaus.groovy.tools.groovydoc.Main"));
        }
        return $class$org$codehaus$groovy$tools$groovydoc$Main;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$groovydoc$FileOutputTool() {
        Class $class$org$codehaus$groovy$tools$groovydoc$FileOutputTool;
        if (($class$org$codehaus$groovy$tools$groovydoc$FileOutputTool = Main.$class$org$codehaus$groovy$tools$groovydoc$FileOutputTool) == null) {
            $class$org$codehaus$groovy$tools$groovydoc$FileOutputTool = (Main.$class$org$codehaus$groovy$tools$groovydoc$FileOutputTool = class$("org.codehaus.groovy.tools.groovydoc.FileOutputTool"));
        }
        return $class$org$codehaus$groovy$tools$groovydoc$FileOutputTool;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager() {
        Class $class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager;
        if (($class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager = Main.$class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager) == null) {
            $class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager = (Main.$class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager = class$("org.codehaus.groovy.tools.groovydoc.ClasspathResourceManager"));
        }
        return $class$org$codehaus$groovy$tools$groovydoc$ClasspathResourceManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool() {
        Class $class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool;
        if (($class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool = Main.$class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool) == null) {
            $class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool = (Main.$class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool = class$("org.codehaus.groovy.tools.groovydoc.GroovyDocTool"));
        }
        return $class$org$codehaus$groovy$tools$groovydoc$GroovyDocTool;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO$Verbosity() {
        Class $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
        if (($class$org$codehaus$groovy$tools$shell$IO$Verbosity = Main.$class$org$codehaus$groovy$tools$shell$IO$Verbosity) == null) {
            $class$org$codehaus$groovy$tools$shell$IO$Verbosity = (Main.$class$org$codehaus$groovy$tools$shell$IO$Verbosity = class$("org.codehaus.groovy.tools.shell.IO$Verbosity"));
        }
        return $class$org$codehaus$groovy$tools$shell$IO$Verbosity;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Main.$class$java$util$List) == null) {
            $class$java$util$List = (Main.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Main.$class$java$lang$String) == null) {
            $class$java$lang$String = (Main.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Main.$class$java$io$File) == null) {
            $class$java$io$File = (Main.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = Main.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (Main.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
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
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo() {
        Class $class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo;
        if (($class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo = Main.$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo) == null) {
            $class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo = (Main.$class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo = class$("org.codehaus.groovy.tools.groovydoc.gstringTemplates.GroovyDocTemplateInfo"));
        }
        return $class$org$codehaus$groovy$tools$groovydoc$gstringTemplates$GroovyDocTemplateInfo;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$CliBuilder() {
        Class $class$groovy$util$CliBuilder;
        if (($class$groovy$util$CliBuilder = Main.$class$groovy$util$CliBuilder) == null) {
            $class$groovy$util$CliBuilder = (Main.$class$groovy$util$CliBuilder = class$("groovy.util.CliBuilder"));
        }
        return $class$groovy$util$CliBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Properties() {
        Class $class$java$util$Properties;
        if (($class$java$util$Properties = Main.$class$java$util$Properties) == null) {
            $class$java$util$Properties = (Main.$class$java$util$Properties = class$("java.util.Properties"));
        }
        return $class$java$util$Properties;
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
