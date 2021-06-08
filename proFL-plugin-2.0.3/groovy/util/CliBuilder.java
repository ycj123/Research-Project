// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.io.IOException;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.cli.Option;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.mudebug.prapr.reloc.commons.cli.Options;
import java.io.PrintWriter;
import org.mudebug.prapr.reloc.commons.cli.HelpFormatter;
import org.mudebug.prapr.reloc.commons.cli.CommandLineParser;
import groovy.lang.GroovyObject;

public class CliBuilder implements GroovyObject
{
    private String usage;
    private CommandLineParser parser;
    private boolean posix;
    private boolean expandArgumentFiles;
    private HelpFormatter formatter;
    private PrintWriter writer;
    private String header;
    private String footer;
    private boolean stopAtNonOption;
    private int width;
    private Options options;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203566;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$commons$cli$OptionBuilder;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$apache$commons$cli$Options;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$org$apache$commons$cli$HelpFormatter;
    private static /* synthetic */ Class $class$org$apache$commons$cli$CommandLineParser;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$apache$commons$cli$Option;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$groovy$util$CliBuilder;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    
    public CliBuilder() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.usage = (String)ScriptBytecodeAdapter.castToType("groovy", $get$$class$java$lang$String());
        this.parser = (CommandLineParser)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$org$apache$commons$cli$CommandLineParser()), $get$$class$org$apache$commons$cli$CommandLineParser());
        this.posix = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        this.expandArgumentFiles = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        this.formatter = (HelpFormatter)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$org$apache$commons$cli$HelpFormatter()), $get$$class$org$apache$commons$cli$HelpFormatter());
        this.writer = (PrintWriter)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$java$io$PrintWriter(), $getCallSiteArray[2].callGetProperty($get$$class$java$lang$System())), $get$$class$java$io$PrintWriter());
        this.header = (String)ScriptBytecodeAdapter.castToType("", $get$$class$java$lang$String());
        this.footer = (String)ScriptBytecodeAdapter.castToType("", $get$$class$java$lang$String());
        this.stopAtNonOption = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        this.width = DefaultTypeTransformation.intUnbox($getCallSiteArray[3].callGetProperty(this.formatter));
        this.options = (Options)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callConstructor($get$$class$org$apache$commons$cli$Options()), $get$$class$org$apache$commons$cli$Options());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object invokeMethod(final String name, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (args instanceof Object[]) {
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[5].call(args), CliBuilder.$const$0) && $getCallSiteArray[6].call(args, CliBuilder.$const$1) instanceof String) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[7].call(this.options, $getCallSiteArray[8].callCurrent(this, name, ScriptBytecodeAdapter.createMap(new Object[0]), $getCallSiteArray[9].call(args, CliBuilder.$const$1)));
                return null;
            }
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[10].call(args), CliBuilder.$const$0) && $getCallSiteArray[11].call(args, CliBuilder.$const$1) instanceof Option) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(name, "leftShift")) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[12].call(this.options, $getCallSiteArray[13].call(args, CliBuilder.$const$1));
                return null;
            }
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[14].call(args), CliBuilder.$const$2) && $getCallSiteArray[15].call(args, CliBuilder.$const$1) instanceof Map) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[16].call(this.options, $getCallSiteArray[17].callCurrent(this, name, $getCallSiteArray[18].call(args, CliBuilder.$const$1), $getCallSiteArray[19].call(args, CliBuilder.$const$0)));
                return null;
            }
        }
        return $getCallSiteArray[20].call($getCallSiteArray[21].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), this), this, name, args);
    }
    
    public OptionAccessor parse(final Object args) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: aload_0         /* this */
        //     5: getfield        groovy/util/CliBuilder.expandArgumentFiles:Z
        //     8: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(Z)Ljava/lang/Object;
        //    11: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    14: ifeq            36
        //    17: aload_2        
        //    18: ldc             22
        //    20: aaload         
        //    21: invokestatic    groovy/util/CliBuilder.$get$$class$groovy$util$CliBuilder:()Ljava/lang/Class;
        //    24: aload_1         /* args */
        //    25: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callStatic:(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
        //    30: dup            
        //    31: astore_1        /* args */
        //    32: pop            
        //    33: goto            36
        //    36: aload_0         /* this */
        //    37: getfield        groovy/util/CliBuilder.parser:Lorg/mudebug/prapr/reloc/commons/cli/CommandLineParser;
        //    40: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    43: ifne            50
        //    46: iconst_1       
        //    47: goto            51
        //    50: iconst_0       
        //    51: ifeq            124
        //    54: aload_0         /* this */
        //    55: getfield        groovy/util/CliBuilder.posix:Z
        //    58: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(Z)Ljava/lang/Object;
        //    61: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    64: ifeq            83
        //    67: aload_2        
        //    68: ldc_w           23
        //    71: aaload         
        //    72: invokestatic    groovy/util/CliBuilder.$get$$class$org$apache$commons$cli$PosixParser:()Ljava/lang/Class;
        //    75: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    80: goto            96
        //    83: aload_2        
        //    84: ldc_w           24
        //    87: aaload         
        //    88: invokestatic    groovy/util/CliBuilder.$get$$class$org$apache$commons$cli$GnuParser:()Ljava/lang/Class;
        //    91: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    96: invokestatic    groovy/util/CliBuilder.$get$$class$org$apache$commons$cli$CommandLineParser:()Ljava/lang/Class;
        //    99: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   102: checkcast       Lorg/mudebug/prapr/reloc/commons/cli/CommandLineParser;
        //   105: dup            
        //   106: invokestatic    groovy/util/CliBuilder.$get$$class$org$apache$commons$cli$CommandLineParser:()Ljava/lang/Class;
        //   109: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   112: checkcast       Lorg/mudebug/prapr/reloc/commons/cli/CommandLineParser;
        //   115: aload_0         /* this */
        //   116: swap           
        //   117: putfield        groovy/util/CliBuilder.parser:Lorg/mudebug/prapr/reloc/commons/cli/CommandLineParser;
        //   120: pop            
        //   121: goto            124
        //   124: aload_2        
        //   125: ldc_w           25
        //   128: aaload         
        //   129: invokestatic    groovy/util/CliBuilder.$get$$class$groovy$util$OptionAccessor:()Ljava/lang/Class;
        //   132: aload_2        
        //   133: ldc_w           26
        //   136: aaload         
        //   137: aload_0         /* this */
        //   138: getfield        groovy/util/CliBuilder.parser:Lorg/mudebug/prapr/reloc/commons/cli/CommandLineParser;
        //   141: aload_0         /* this */
        //   142: getfield        groovy/util/CliBuilder.options:Lorg/mudebug/prapr/reloc/commons/cli/Options;
        //   145: aload_1         /* args */
        //   146: invokestatic    groovy/util/CliBuilder.$get$array$$class$java$lang$String:()Ljava/lang/Class;
        //   149: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.asType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   152: checkcast       [Ljava/lang/String;
        //   155: invokestatic    groovy/util/CliBuilder.$get$array$$class$java$lang$String:()Ljava/lang/Class;
        //   158: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createPojoWrapper:(Ljava/lang/Object;Ljava/lang/Class;)Lorg/codehaus/groovy/runtime/wrappers/Wrapper;
        //   161: aload_0         /* this */
        //   162: getfield        groovy/util/CliBuilder.stopAtNonOption:Z
        //   165: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(Z)Ljava/lang/Object;
        //   168: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   173: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   178: invokestatic    groovy/util/CliBuilder.$get$$class$groovy$util$OptionAccessor:()Ljava/lang/Class;
        //   181: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   184: checkcast       Lgroovy/util/OptionAccessor;
        //   187: astore_3       
        //   188: nop            
        //   189: nop            
        //   190: aload_3        
        //   191: areturn        
        //   192: goto            268
        //   195: astore_3       
        //   196: aload_2        
        //   197: ldc_w           27
        //   200: aaload         
        //   201: aload_0         /* this */
        //   202: getfield        groovy/util/CliBuilder.writer:Ljava/io/PrintWriter;
        //   205: aload_2        
        //   206: ldc_w           28
        //   209: aaload         
        //   210: ldc_w           "error: "
        //   213: aload_2        
        //   214: ldc_w           29
        //   217: aaload         
        //   218: aload_3        
        //   219: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   224: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   229: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   234: pop            
        //   235: aload_2        
        //   236: ldc_w           30
        //   239: aaload         
        //   240: aload_0         /* this */
        //   241: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;)Ljava/lang/Object;
        //   246: pop            
        //   247: aconst_null    
        //   248: invokestatic    groovy/util/CliBuilder.$get$$class$groovy$util$OptionAccessor:()Ljava/lang/Class;
        //   251: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   254: checkcast       Lgroovy/util/OptionAccessor;
        //   257: astore          4
        //   259: nop            
        //   260: nop            
        //   261: aload           4
        //   263: areturn        
        //   264: nop            
        //   265: goto            268
        //   268: nop            
        //   269: goto            275
        //   272: astore_3       
        //   273: aload_3        
        //   274: athrow         
        //   275: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                
        //  -----  -----  -----  -----  ----------------------------------------------------
        //  124    189    195    264    Lorg/mudebug/prapr/reloc/commons/cli/ParseException;
        //  190    195    195    264    Lorg/mudebug/prapr/reloc/commons/cli/ParseException;
        //  124    189    272    275    Any
        //  190    195    272    275    Any
        //  195    260    272    275    Any
        //  261    265    272    275    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void usage() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[31].call(this.formatter, ArrayUtil.createArray(this.writer, DefaultTypeTransformation.box(this.width), this.usage, this.header, this.options, $getCallSiteArray[32].callGetProperty(this.formatter), $getCallSiteArray[33].callGetProperty(this.formatter), this.footer));
        $getCallSiteArray[34].call(this.writer);
    }
    
    public Option option(final Object shortname, final Map details, final Object info) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Option option = (Option)new Reference(ScriptBytecodeAdapter.castToType(null, $get$$class$org$apache$commons$cli$Option()));
        if (ScriptBytecodeAdapter.compareEqual(shortname, "_")) {
            ((Reference<Option>)option).set((Option)ScriptBytecodeAdapter.castToType($getCallSiteArray[35].call($getCallSiteArray[36].call($getCallSiteArray[37].call($get$$class$org$apache$commons$cli$OptionBuilder(), info), $getCallSiteArray[38].callGetProperty(details))), $get$$class$org$apache$commons$cli$Option()));
            $getCallSiteArray[39].call(details, "longOpt");
        }
        else {
            ((Reference<Object>)option).set($getCallSiteArray[40].callConstructor($get$$class$org$apache$commons$cli$Option(), shortname, info));
        }
        $getCallSiteArray[41].call(details, new CliBuilder$_option_closure1(this, this, (Reference<Object>)option));
        return (Option)ScriptBytecodeAdapter.castToType(((Reference<Object>)option).get(), $get$$class$org$apache$commons$cli$Option());
    }
    
    public static Object expandArgumentFiles(final Object args) throws IOException {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object result = ScriptBytecodeAdapter.createList(new Object[0]);
        Object arg = null;
        final Object call = $getCallSiteArray[42].call(args);
        while (((Iterator)call).hasNext()) {
            arg = ((Iterator<Object>)call).next();
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[43].call(arg, CliBuilder.$const$1), "@")) {
                arg = $getCallSiteArray[44].call(arg, CliBuilder.$const$0);
                if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[45].call(arg, CliBuilder.$const$1), "@")) {
                    $getCallSiteArray[46].callStatic($get$$class$groovy$util$CliBuilder(), arg, result);
                    continue;
                }
            }
            $getCallSiteArray[47].call(result, arg);
        }
        return result;
    }
    
    private static Object expandArgumentFile(final Object name, final Object args) throws IOException {
        final Object args2 = new Reference(args);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object charAsInt = new Reference(new CliBuilder$_expandArgumentFile_closure2($get$$class$groovy$util$CliBuilder(), $get$$class$groovy$util$CliBuilder()));
        return $getCallSiteArray[48].call($getCallSiteArray[49].callConstructor($get$$class$java$io$File(), name), new CliBuilder$_expandArgumentFile_closure3($get$$class$groovy$util$CliBuilder(), $get$$class$groovy$util$CliBuilder(), (Reference<Object>)args2, (Reference<Object>)charAsInt));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$CliBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CliBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CliBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CliBuilder.__timeStamp__239_neverHappen1292524203566 = 0L;
        CliBuilder.__timeStamp = 1292524203566L;
        $const$2 = 2;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public void setUsage(final String usage) {
        this.usage = usage;
    }
    
    public CommandLineParser getParser() {
        return this.parser;
    }
    
    public void setParser(final CommandLineParser parser) {
        this.parser = parser;
    }
    
    public boolean getPosix() {
        return this.posix;
    }
    
    public boolean isPosix() {
        return this.posix;
    }
    
    public void setPosix(final boolean posix) {
        this.posix = posix;
    }
    
    public boolean getExpandArgumentFiles() {
        return this.expandArgumentFiles;
    }
    
    public boolean isExpandArgumentFiles() {
        return this.expandArgumentFiles;
    }
    
    public void setExpandArgumentFiles(final boolean expandArgumentFiles) {
        this.expandArgumentFiles = expandArgumentFiles;
    }
    
    public HelpFormatter getFormatter() {
        return this.formatter;
    }
    
    public void setFormatter(final HelpFormatter formatter) {
        this.formatter = formatter;
    }
    
    public PrintWriter getWriter() {
        return this.writer;
    }
    
    public void setWriter(final PrintWriter writer) {
        this.writer = writer;
    }
    
    public String getHeader() {
        return this.header;
    }
    
    public void setHeader(final String header) {
        this.header = header;
    }
    
    public String getFooter() {
        return this.footer;
    }
    
    public void setFooter(final String footer) {
        this.footer = footer;
    }
    
    public boolean getStopAtNonOption() {
        return this.stopAtNonOption;
    }
    
    public boolean isStopAtNonOption() {
        return this.stopAtNonOption;
    }
    
    public void setStopAtNonOption(final boolean stopAtNonOption) {
        this.stopAtNonOption = stopAtNonOption;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public Options getOptions() {
        return this.options;
    }
    
    public void setOptions(final Options options) {
        this.options = options;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[50];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$CliBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CliBuilder.$callSiteArray == null || ($createCallSiteArray = CliBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CliBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$OptionBuilder() {
        Class $class$org$apache$commons$cli$OptionBuilder;
        if (($class$org$apache$commons$cli$OptionBuilder = CliBuilder.$class$org$apache$commons$cli$OptionBuilder) == null) {
            $class$org$apache$commons$cli$OptionBuilder = (CliBuilder.$class$org$apache$commons$cli$OptionBuilder = class$("org.mudebug.prapr.reloc.commons.cli.OptionBuilder"));
        }
        return $class$org$apache$commons$cli$OptionBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = CliBuilder.$class$java$lang$String) == null) {
            $class$java$lang$String = (CliBuilder.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$Options() {
        Class $class$org$apache$commons$cli$Options;
        if (($class$org$apache$commons$cli$Options = CliBuilder.$class$org$apache$commons$cli$Options) == null) {
            $class$org$apache$commons$cli$Options = (CliBuilder.$class$org$apache$commons$cli$Options = class$("org.mudebug.prapr.reloc.commons.cli.Options"));
        }
        return $class$org$apache$commons$cli$Options;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = CliBuilder.$class$java$io$File) == null) {
            $class$java$io$File = (CliBuilder.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$HelpFormatter() {
        Class $class$org$apache$commons$cli$HelpFormatter;
        if (($class$org$apache$commons$cli$HelpFormatter = CliBuilder.$class$org$apache$commons$cli$HelpFormatter) == null) {
            $class$org$apache$commons$cli$HelpFormatter = (CliBuilder.$class$org$apache$commons$cli$HelpFormatter = class$("org.mudebug.prapr.reloc.commons.cli.HelpFormatter"));
        }
        return $class$org$apache$commons$cli$HelpFormatter;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$CommandLineParser() {
        Class $class$org$apache$commons$cli$CommandLineParser;
        if (($class$org$apache$commons$cli$CommandLineParser = CliBuilder.$class$org$apache$commons$cli$CommandLineParser) == null) {
            $class$org$apache$commons$cli$CommandLineParser = (CliBuilder.$class$org$apache$commons$cli$CommandLineParser = class$("org.mudebug.prapr.reloc.commons.cli.CommandLineParser"));
        }
        return $class$org$apache$commons$cli$CommandLineParser;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CliBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CliBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = CliBuilder.$class$java$lang$System) == null) {
            $class$java$lang$System = (CliBuilder.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$Option() {
        Class $class$org$apache$commons$cli$Option;
        if (($class$org$apache$commons$cli$Option = CliBuilder.$class$org$apache$commons$cli$Option) == null) {
            $class$org$apache$commons$cli$Option = (CliBuilder.$class$org$apache$commons$cli$Option = class$("org.mudebug.prapr.reloc.commons.cli.Option"));
        }
        return $class$org$apache$commons$cli$Option;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = CliBuilder.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (CliBuilder.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$CliBuilder() {
        Class $class$groovy$util$CliBuilder;
        if (($class$groovy$util$CliBuilder = CliBuilder.$class$groovy$util$CliBuilder) == null) {
            $class$groovy$util$CliBuilder = (CliBuilder.$class$groovy$util$CliBuilder = class$("groovy.util.CliBuilder"));
        }
        return $class$groovy$util$CliBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = CliBuilder.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (CliBuilder.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
        }
        return $class$java$io$PrintWriter;
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
