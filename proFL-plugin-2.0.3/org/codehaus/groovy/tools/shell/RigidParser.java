// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public final class RigidParser implements GroovyObject
{
    private static final String SCRIPT_FILENAME;
    private final Logger log;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204043;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$RigidParser;
    
    public RigidParser() {
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
        //    55: aload_1         /* buffer */
        //    56: aload_2        
        //    57: ldc             3
        //    59: aaload         
        //    60: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$Parser:()Ljava/lang/Class;
        //    63: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    68: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    73: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    76: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    79: checkcast       Ljava/lang/String;
        //    82: astore          source
        //    84: aload_2        
        //    85: ldc             4
        //    87: aaload         
        //    88: aload_0         /* this */
        //    89: getfield        org/codehaus/groovy/tools/shell/RigidParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //    92: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //    95: dup            
        //    96: iconst_1       
        //    97: anewarray       Ljava/lang/Object;
        //   100: dup            
        //   101: iconst_0       
        //   102: aload           source
        //   104: aastore        
        //   105: iconst_2       
        //   106: anewarray       Ljava/lang/String;
        //   109: dup            
        //   110: iconst_0       
        //   111: ldc             "Parsing: "
        //   113: aastore        
        //   114: dup            
        //   115: iconst_1       
        //   116: ldc             ""
        //   118: aastore        
        //   119: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   122: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   127: pop            
        //   128: aconst_null    
        //   129: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$control$SourceUnit:()Ljava/lang/Class;
        //   132: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   135: checkcast       Lorg/codehaus/groovy/control/SourceUnit;
        //   138: astore          parser
        //   140: aconst_null    
        //   141: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$java$lang$Throwable:()Ljava/lang/Class;
        //   144: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   147: checkcast       Ljava/lang/Throwable;
        //   150: astore          error
        //   152: aload_2        
        //   153: ldc             5
        //   155: aaload         
        //   156: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$control$SourceUnit:()Ljava/lang/Class;
        //   159: getstatic       org/codehaus/groovy/tools/shell/RigidParser.SCRIPT_FILENAME:Ljava/lang/String;
        //   162: aload           source
        //   164: getstatic       org/codehaus/groovy/tools/shell/RigidParser.$const$0:Ljava/lang/Integer;
        //   167: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   172: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$control$SourceUnit:()Ljava/lang/Class;
        //   175: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   178: checkcast       Lorg/codehaus/groovy/control/SourceUnit;
        //   181: dup            
        //   182: astore          parser
        //   184: pop            
        //   185: aload_2        
        //   186: ldc             6
        //   188: aaload         
        //   189: aload           parser
        //   191: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   196: pop            
        //   197: aload_2        
        //   198: ldc             7
        //   200: aaload         
        //   201: aload_0         /* this */
        //   202: getfield        org/codehaus/groovy/tools/shell/RigidParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   205: ldc             "Parse complete"
        //   207: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   212: pop            
        //   213: aload_2        
        //   214: ldc             8
        //   216: aaload         
        //   217: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   220: aload_2        
        //   221: ldc             9
        //   223: aaload         
        //   224: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseCode:()Ljava/lang/Class;
        //   227: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   232: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   237: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   240: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   243: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   246: astore          7
        //   248: nop            
        //   249: nop            
        //   250: aload           7
        //   252: areturn        
        //   253: goto            587
        //   256: astore          e
        //   258: aload_2        
        //   259: ldc             10
        //   261: aaload         
        //   262: aload_2        
        //   263: ldc             11
        //   265: aaload         
        //   266: aload           parser
        //   268: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   273: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   278: getstatic       org/codehaus/groovy/tools/shell/RigidParser.$const$0:Ljava/lang/Integer;
        //   281: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareGreaterThan:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   284: ifeq            293
        //   287: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   290: goto            321
        //   293: aload_2        
        //   294: ldc             12
        //   296: aaload         
        //   297: aload           parser
        //   299: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   304: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   307: ifne            314
        //   310: iconst_1       
        //   311: goto            315
        //   314: iconst_0       
        //   315: ifne            287
        //   318: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   321: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   324: ifeq            571
        //   327: aload_2        
        //   328: ldc             13
        //   330: aaload         
        //   331: aload_2        
        //   332: ldc             14
        //   334: aaload         
        //   335: aload_1         /* buffer */
        //   336: getstatic       org/codehaus/groovy/tools/shell/RigidParser.$const$1:Ljava/lang/Integer;
        //   339: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   344: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   349: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$java$lang$String:()Ljava/lang/Class;
        //   352: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   355: checkcast       Ljava/lang/String;
        //   358: astore          tmp
        //   360: aload_2        
        //   361: ldc             15
        //   363: aaload         
        //   364: aload           tmp
        //   366: ldc             "{"
        //   368: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   373: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   376: ifeq            385
        //   379: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   382: goto            407
        //   385: aload_2        
        //   386: ldc             16
        //   388: aaload         
        //   389: aload           tmp
        //   391: ldc             "["
        //   393: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   398: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   401: ifne            379
        //   404: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   407: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   410: ifeq            419
        //   413: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   416: goto            441
        //   419: aload_2        
        //   420: ldc             17
        //   422: aaload         
        //   423: aload           tmp
        //   425: ldc             "'''"
        //   427: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   432: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   435: ifne            413
        //   438: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   441: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   444: ifeq            453
        //   447: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   450: goto            475
        //   453: aload_2        
        //   454: ldc             18
        //   456: aaload         
        //   457: aload           tmp
        //   459: ldc             "\"\"\""
        //   461: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   466: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   469: ifne            447
        //   472: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   475: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   478: ifeq            487
        //   481: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   484: goto            509
        //   487: aload_2        
        //   488: ldc             19
        //   490: aaload         
        //   491: aload           tmp
        //   493: ldc             "\\"
        //   495: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   500: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   503: ifne            481
        //   506: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   509: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   512: ifeq            562
        //   515: aload_2        
        //   516: ldc             20
        //   518: aaload         
        //   519: aload_0         /* this */
        //   520: getfield        org/codehaus/groovy/tools/shell/RigidParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   523: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   526: dup            
        //   527: iconst_1       
        //   528: anewarray       Ljava/lang/Object;
        //   531: dup            
        //   532: iconst_0       
        //   533: aload           e
        //   535: aastore        
        //   536: iconst_2       
        //   537: anewarray       Ljava/lang/String;
        //   540: dup            
        //   541: iconst_0       
        //   542: ldc             "Ignoring parse failure; might be valid: "
        //   544: aastore        
        //   545: dup            
        //   546: iconst_1       
        //   547: ldc             ""
        //   549: aastore        
        //   550: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   553: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   558: pop            
        //   559: goto            568
        //   562: aload           e
        //   564: dup            
        //   565: astore          error
        //   567: pop            
        //   568: goto            571
        //   571: nop            
        //   572: goto            587
        //   575: astore          e
        //   577: aload           e
        //   579: dup            
        //   580: astore          error
        //   582: pop            
        //   583: nop            
        //   584: goto            587
        //   587: nop            
        //   588: goto            596
        //   591: astore          7
        //   593: aload           7
        //   595: athrow         
        //   596: aload           error
        //   598: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   601: ifeq            675
        //   604: aload_2        
        //   605: ldc             21
        //   607: aaload         
        //   608: aload_0         /* this */
        //   609: getfield        org/codehaus/groovy/tools/shell/RigidParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   612: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   615: dup            
        //   616: iconst_1       
        //   617: anewarray       Ljava/lang/Object;
        //   620: dup            
        //   621: iconst_0       
        //   622: aload           error
        //   624: aastore        
        //   625: iconst_2       
        //   626: anewarray       Ljava/lang/String;
        //   629: dup            
        //   630: iconst_0       
        //   631: ldc             "Parse error: "
        //   633: aastore        
        //   634: dup            
        //   635: iconst_1       
        //   636: ldc             ""
        //   638: aastore        
        //   639: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   642: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   647: pop            
        //   648: aload_2        
        //   649: ldc             22
        //   651: aaload         
        //   652: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   655: aload           error
        //   657: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   662: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   665: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   668: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   671: areturn        
        //   672: goto            725
        //   675: aload_2        
        //   676: ldc             23
        //   678: aaload         
        //   679: aload_0         /* this */
        //   680: getfield        org/codehaus/groovy/tools/shell/RigidParser.log:Lorg/codehaus/groovy/tools/shell/util/Logger;
        //   683: ldc             "Parse incomplete"
        //   685: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   690: pop            
        //   691: aload_2        
        //   692: ldc             24
        //   694: aaload         
        //   695: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   698: aload_2        
        //   699: ldc             25
        //   701: aaload         
        //   702: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseCode:()Ljava/lang/Class;
        //   705: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   710: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   715: invokestatic    org/codehaus/groovy/tools/shell/RigidParser.$get$$class$org$codehaus$groovy$tools$shell$ParseStatus:()Ljava/lang/Class;
        //   718: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   721: checkcast       Lorg/codehaus/groovy/tools/shell/ParseStatus;
        //   724: areturn        
        //   725: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  152    249    256    575    Lorg/codehaus/groovy/control/CompilationFailedException;
        //  250    256    256    575    Lorg/codehaus/groovy/control/CompilationFailedException;
        //  152    249    575    587    Ljava/lang/Throwable;
        //  250    256    575    587    Ljava/lang/Throwable;
        //  152    249    591    596    Any
        //  250    256    591    596    Any
        //  256    572    591    596    Any
        //  575    584    591    596    Any
        //  12     43     46     51     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0287:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$RigidParser()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RigidParser.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RigidParser.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RigidParser.__timeStamp__239_neverHappen1292524204043 = 0L;
        RigidParser.__timeStamp = 1292524204043L;
        $const$1 = -1;
        $const$0 = 1;
        SCRIPT_FILENAME = "groovysh_parse";
    }
    
    public static final String getSCRIPT_FILENAME() {
        return RigidParser.SCRIPT_FILENAME;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[26];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$RigidParser(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RigidParser.$callSiteArray == null || ($createCallSiteArray = RigidParser.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RigidParser.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RigidParser.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RigidParser.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = RigidParser.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (RigidParser.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$RigidParser() {
        Class $class$org$codehaus$groovy$tools$shell$RigidParser;
        if (($class$org$codehaus$groovy$tools$shell$RigidParser = RigidParser.$class$org$codehaus$groovy$tools$shell$RigidParser) == null) {
            $class$org$codehaus$groovy$tools$shell$RigidParser = (RigidParser.$class$org$codehaus$groovy$tools$shell$RigidParser = class$("org.codehaus.groovy.tools.shell.RigidParser"));
        }
        return $class$org$codehaus$groovy$tools$shell$RigidParser;
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
