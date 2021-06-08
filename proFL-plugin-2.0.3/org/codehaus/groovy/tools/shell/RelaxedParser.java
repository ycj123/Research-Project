// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.antlr.UnicodeEscapingReader;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.antlr.SourceBuffer;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public final class RelaxedParser implements GroovyObject
{
    private final Logger log;
    private SourceBuffer sourceBuffer;
    private String[] tokenNames;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204029;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class array$$class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$antlr$parser$GroovyRecognizer;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$RelaxedParser;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$antlr$collections$AST;
    private static /* synthetic */ Class $class$org$codehaus$groovy$antlr$parser$GroovyLexer;
    
    public RelaxedParser() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public ParseStatus parse(final List buffer) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: new             Lorg/codehaus/groovy/transform/powerassert/ValueRecorder;
        //     7: dup            
        //     8: invokespecial   org/codehaus/groovy/transform/powerassert/ValueRecorder.<init>:()V
        //    11: astore_3       
        //    12: aload_1         /* buffer */
        //    13: aload_3        
        //    14: swap           
        //    15: ldc             8
        //    17: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.record:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    20: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    23: ifeq            33
        //    26: aload_3        
        //    27: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    30: goto            51
        //    33: ldc             "assert buffer"
        //    35: aload_3        
        //    36: invokestatic    org/codehaus/groovy/transform/powerassert/AssertionRenderer.render:(Ljava/lang/String;Lorg/codehaus/groovy/transform/powerassert/ValueRecorder;)Ljava/lang/String;
        //    39: aconst_null    
        //    40: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.assertFailed:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    43: goto            51
        //    46: aload_3        
        //    47: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    50: athrow         
        //    51: aload_2        
        //    52: ldc             2
        //    54: aaload         
        //    55: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$antlr$SourceBuffer:()Ljava/lang/Class;
        //    58: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    63: dup            
        //    64: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$antlr$SourceBuffer:()Ljava/lang/Class;
        //    67: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    70: checkcast       Lorg/codehaus/groovy/antlr/SourceBuffer;
        //    73: aload_0         /* this */
        //    74: swap           
        //    75: putfield        org/codehaus/groovy/tools/shell/RelaxedParser.sourceBuffer:Lorg/codehaus/groovy/antlr/SourceBuffer;
        //    78: pop            
        //    79: aload_2        
        //    80: ldc             3
        //    82: aaload         
        //    83: aload_1         /* buffer */
        //    84: aload_2        
        //    85: ldc             4
        //    87: aaload         
        //    88: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$Parser:()Ljava/lang/Class;
        //    91: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    96: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   101: astore          source
        //   103: aload_2        
        //   104: ldc             5
        //   106: aaload         
        //   107: aload_0         /* this */
        //   108: getfield        org/codehaus/groovy/tools/shell/RelaxedParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   111: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   114: dup            
        //   115: iconst_1       
        //   116: anewarray       Ljava/lang/Object;
        //   119: dup            
        //   120: iconst_0       
        //   121: aload           source
        //   123: aastore        
        //   124: iconst_2       
        //   125: anewarray       Ljava/lang/String;
        //   128: dup            
        //   129: iconst_0       
        //   130: ldc             "Parsing: "
        //   132: aastore        
        //   133: dup            
        //   134: iconst_1       
        //   135: ldc             ""
        //   137: aastore        
        //   138: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   141: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   146: pop            
        //   147: aload_2        
        //   148: ldc             6
        //   150: aaload         
        //   151: aload_0         /* this */
        //   152: aload_2        
        //   153: ldc             7
        //   155: aaload         
        //   156: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$antlr$UnicodeEscapingReader:()Ljava/lang/Class;
        //   159: aload_2        
        //   160: ldc             8
        //   162: aaload         
        //   163: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$java$io$StringReader:()Ljava/lang/Class;
        //   166: aload           source
        //   168: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   173: aload_0         /* this */
        //   174: getfield        org/codehaus/groovy/tools/shell/RelaxedParser.sourceBuffer:Lorg/codehaus/groovy/antlr/SourceBuffer;
        //   177: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   182: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   187: pop            
        //   188: aload_2        
        //   189: ldc             9
        //   191: aaload         
        //   192: aload_0         /* this */
        //   193: getfield        org/codehaus/groovy/tools/shell/RelaxedParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   196: ldc             "Parse complete"
        //   198: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   203: pop            
        //   204: aload_2        
        //   205: ldc             10
        //   207: aaload         
        //   208: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   211: aload_2        
        //   212: ldc             11
        //   214: aaload         
        //   215: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseCode:()Ljava/lang/Class;
        //   218: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   223: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   228: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   231: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   234: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   237: astore          5
        //   239: nop            
        //   240: nop            
        //   241: aload           5
        //   243: areturn        
        //   244: goto            522
        //   247: astore          e
        //   249: aload_2        
        //   250: ldc             12
        //   252: aaload         
        //   253: aload           e
        //   255: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   260: astore          6
        //   262: aload           6
        //   264: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$antlr$TokenStreamException:()Ljava/lang/Class;
        //   267: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.isCase:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   270: ifeq            276
        //   273: goto            287
        //   276: aload           6
        //   278: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$antlr$RecognitionException:()Ljava/lang/Class;
        //   281: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.isCase:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   284: ifeq            399
        //   287: aload_2        
        //   288: ldc             13
        //   290: aaload         
        //   291: aload_0         /* this */
        //   292: getfield        org/codehaus/groovy/tools/shell/RelaxedParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   295: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   298: dup            
        //   299: iconst_2       
        //   300: anewarray       Ljava/lang/Object;
        //   303: dup            
        //   304: iconst_0       
        //   305: aload           e
        //   307: aastore        
        //   308: dup            
        //   309: iconst_1       
        //   310: aload_2        
        //   311: ldc             14
        //   313: aaload         
        //   314: aload_2        
        //   315: ldc             15
        //   317: aaload         
        //   318: aload           e
        //   320: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   325: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   330: aastore        
        //   331: iconst_3       
        //   332: anewarray       Ljava/lang/String;
        //   335: dup            
        //   336: iconst_0       
        //   337: ldc             "Parse incomplete: "
        //   339: aastore        
        //   340: dup            
        //   341: iconst_1       
        //   342: ldc             " ("
        //   344: aastore        
        //   345: dup            
        //   346: iconst_2       
        //   347: ldc             ")"
        //   349: aastore        
        //   350: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   353: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   358: pop            
        //   359: aload_2        
        //   360: ldc             16
        //   362: aaload         
        //   363: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   366: aload_2        
        //   367: ldc             17
        //   369: aaload         
        //   370: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseCode:()Ljava/lang/Class;
        //   373: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   378: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   383: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   386: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   389: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   392: astore          7
        //   394: nop            
        //   395: nop            
        //   396: aload           7
        //   398: areturn        
        //   399: aload_2        
        //   400: ldc             18
        //   402: aaload         
        //   403: aload_0         /* this */
        //   404: getfield        org/codehaus/groovy/tools/shell/RelaxedParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   407: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   410: dup            
        //   411: iconst_2       
        //   412: anewarray       Ljava/lang/Object;
        //   415: dup            
        //   416: iconst_0       
        //   417: aload           e
        //   419: aastore        
        //   420: dup            
        //   421: iconst_1       
        //   422: aload_2        
        //   423: ldc             19
        //   425: aaload         
        //   426: aload_2        
        //   427: ldc             20
        //   429: aaload         
        //   430: aload           e
        //   432: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   437: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   442: aastore        
        //   443: iconst_3       
        //   444: anewarray       Ljava/lang/String;
        //   447: dup            
        //   448: iconst_0       
        //   449: ldc             "Parse error: "
        //   451: aastore        
        //   452: dup            
        //   453: iconst_1       
        //   454: ldc             " ("
        //   456: aastore        
        //   457: dup            
        //   458: iconst_2       
        //   459: ldc             ")"
        //   461: aastore        
        //   462: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   465: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   470: pop            
        //   471: aload_2        
        //   472: ldc             21
        //   474: aaload         
        //   475: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   478: aload           e
        //   480: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   485: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   488: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   491: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   494: astore          7
        //   496: nop            
        //   497: nop            
        //   498: aload           7
        //   500: areturn        
        //   501: aconst_null    
        //   502: invokestatic    org/codehaus/groovy/tools/shell/RelaxedParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   505: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   508: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   511: astore          6
        //   513: nop            
        //   514: nop            
        //   515: aload           6
        //   517: areturn        
        //   518: nop            
        //   519: goto            522
        //   522: nop            
        //   523: goto            531
        //   526: astore          5
        //   528: aload           5
        //   530: athrow         
        //   531: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  147    240    247    501    Ljava/lang/Exception;
        //  241    247    247    501    Ljava/lang/Exception;
        //  147    240    526    531    Any
        //  241    247    526    531    Any
        //  247    395    526    531    Any
        //  396    497    526    531    Any
        //  498    514    526    531    Any
        //  515    519    526    531    Any
        //  12     43     46     51     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected AST doParse(final UnicodeEscapingReader reader) throws Exception {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object lexer = $getCallSiteArray[22].callConstructor($get$$class$org$codehaus$groovy$antlr$parser$GroovyLexer(), reader);
        $getCallSiteArray[23].call(reader, lexer);
        final Object parser = $getCallSiteArray[24].call($get$$class$org$codehaus$groovy$antlr$parser$GroovyRecognizer(), lexer);
        $getCallSiteArray[25].call(parser, this.sourceBuffer);
        this.tokenNames = (String[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[26].call(parser), $get$array$$class$java$lang$String()), $get$array$$class$java$lang$String());
        $getCallSiteArray[27].call(parser);
        return (AST)ScriptBytecodeAdapter.castToType($getCallSiteArray[28].call(parser), $get$$class$antlr$collections$AST());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$RelaxedParser()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RelaxedParser.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RelaxedParser.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RelaxedParser.__timeStamp__239_neverHappen1292524204029 = 0L;
        RelaxedParser.__timeStamp = 1292524204029L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[29];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$RelaxedParser(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RelaxedParser.$callSiteArray == null || ($createCallSiteArray = RelaxedParser.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RelaxedParser.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$String() {
        Class array$$class$java$lang$String;
        if ((array$$class$java$lang$String = RelaxedParser.array$$class$java$lang$String) == null) {
            array$$class$java$lang$String = (RelaxedParser.array$$class$java$lang$String = class$("[Ljava.lang.String;"));
        }
        return array$$class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RelaxedParser.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RelaxedParser.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$antlr$parser$GroovyRecognizer() {
        Class $class$org$codehaus$groovy$antlr$parser$GroovyRecognizer;
        if (($class$org$codehaus$groovy$antlr$parser$GroovyRecognizer = RelaxedParser.$class$org$codehaus$groovy$antlr$parser$GroovyRecognizer) == null) {
            $class$org$codehaus$groovy$antlr$parser$GroovyRecognizer = (RelaxedParser.$class$org$codehaus$groovy$antlr$parser$GroovyRecognizer = class$("org.codehaus.groovy.antlr.parser.GroovyRecognizer"));
        }
        return $class$org$codehaus$groovy$antlr$parser$GroovyRecognizer;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$RelaxedParser() {
        Class $class$org$codehaus$groovy$tools$shell$RelaxedParser;
        if (($class$org$codehaus$groovy$tools$shell$RelaxedParser = RelaxedParser.$class$org$codehaus$groovy$tools$shell$RelaxedParser) == null) {
            $class$org$codehaus$groovy$tools$shell$RelaxedParser = (RelaxedParser.$class$org$codehaus$groovy$tools$shell$RelaxedParser = class$("org.codehaus.groovy.tools.shell.RelaxedParser"));
        }
        return $class$org$codehaus$groovy$tools$shell$RelaxedParser;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = RelaxedParser.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (RelaxedParser.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$antlr$collections$AST() {
        Class $class$antlr$collections$AST;
        if (($class$antlr$collections$AST = RelaxedParser.$class$antlr$collections$AST) == null) {
            $class$antlr$collections$AST = (RelaxedParser.$class$antlr$collections$AST = class$("groovyjarjarantlr.collections.AST"));
        }
        return $class$antlr$collections$AST;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$antlr$parser$GroovyLexer() {
        Class $class$org$codehaus$groovy$antlr$parser$GroovyLexer;
        if (($class$org$codehaus$groovy$antlr$parser$GroovyLexer = RelaxedParser.$class$org$codehaus$groovy$antlr$parser$GroovyLexer) == null) {
            $class$org$codehaus$groovy$antlr$parser$GroovyLexer = (RelaxedParser.$class$org$codehaus$groovy$antlr$parser$GroovyLexer = class$("org.codehaus.groovy.antlr.parser.GroovyLexer"));
        }
        return $class$org$codehaus$groovy$antlr$parser$GroovyLexer;
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
