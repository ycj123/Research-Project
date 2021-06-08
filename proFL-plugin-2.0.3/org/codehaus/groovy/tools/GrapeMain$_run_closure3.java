// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public GrapeMain$_run_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object arg, final Object cmd) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lgroovy/lang/Reference;
        //     4: dup_x1         
        //     5: swap           
        //     6: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //     9: astore_3        /* arg */
        //    10: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$getCallSiteArray:()[Lorg/codehaus/groovy/runtime/callsite/CallSite;
        //    13: astore          4
        //    15: aload           4
        //    17: ldc             0
        //    19: aaload         
        //    20: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$Options:()Ljava/lang/Class;
        //    23: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    28: astore          options
        //    30: aload           4
        //    32: ldc             1
        //    34: aaload         
        //    35: aload           options
        //    37: aload           4
        //    39: ldc             2
        //    41: aaload         
        //    42: aload           4
        //    44: ldc             3
        //    46: aaload         
        //    47: aload           4
        //    49: ldc             4
        //    51: aaload         
        //    52: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$OptionBuilder:()Ljava/lang/Class;
        //    55: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    58: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    63: ldc             "ant"
        //    65: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    70: ldc             "a"
        //    72: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    77: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    82: pop            
        //    83: aload           4
        //    85: ldc             5
        //    87: aaload         
        //    88: aload           options
        //    90: aload           4
        //    92: ldc             6
        //    94: aaload         
        //    95: aload           4
        //    97: ldc             7
        //    99: aaload         
        //   100: aload           4
        //   102: ldc             8
        //   104: aaload         
        //   105: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$OptionBuilder:()Ljava/lang/Class;
        //   108: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   111: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   116: ldc             "dos"
        //   118: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   123: ldc             "d"
        //   125: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   130: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   135: pop            
        //   136: aload           4
        //   138: ldc             9
        //   140: aaload         
        //   141: aload           options
        //   143: aload           4
        //   145: ldc             10
        //   147: aaload         
        //   148: aload           4
        //   150: ldc             11
        //   152: aaload         
        //   153: aload           4
        //   155: ldc             12
        //   157: aaload         
        //   158: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$OptionBuilder:()Ljava/lang/Class;
        //   161: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   164: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   169: ldc             "shell"
        //   171: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   176: ldc             "s"
        //   178: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   183: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   188: pop            
        //   189: aload           4
        //   191: ldc             13
        //   193: aaload         
        //   194: aload           options
        //   196: aload           4
        //   198: ldc             14
        //   200: aaload         
        //   201: aload           4
        //   203: ldc             15
        //   205: aaload         
        //   206: aload           4
        //   208: ldc             16
        //   210: aaload         
        //   211: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$OptionBuilder:()Ljava/lang/Class;
        //   214: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   217: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   222: ldc             "ivy"
        //   224: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   229: ldc             "i"
        //   231: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   236: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   241: pop            
        //   242: aload           4
        //   244: ldc             17
        //   246: aaload         
        //   247: aload           4
        //   249: ldc             18
        //   251: aaload         
        //   252: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$PosixParser:()Ljava/lang/Class;
        //   255: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //   260: aload           options
        //   262: aload           4
        //   264: ldc             19
        //   266: aaload         
        //   267: aload_3         /* arg */
        //   268: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   271: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure3.$const$0:Ljava/lang/Integer;
        //   274: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure3.$const$1:Ljava/lang/Integer;
        //   277: iconst_1       
        //   278: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createRange:(Ljava/lang/Object;Ljava/lang/Object;Z)Ljava/util/List;
        //   281: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   286: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$array$$class$java$lang$String:()Ljava/lang/Class;
        //   289: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.asType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   292: checkcast       [Ljava/lang/String;
        //   295: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$array$$class$java$lang$String:()Ljava/lang/Class;
        //   298: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createPojoWrapper:(Ljava/lang/Object;Ljava/lang/Class;)Lorg/codehaus/groovy/runtime/wrappers/Wrapper;
        //   301: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   304: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   309: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$commons$cli$CommandLine:()Ljava/lang/Class;
        //   312: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   315: checkcast       Lgroovyjarjarcommonscli/CommandLine;
        //   318: astore          cmd2
        //   320: aload           4
        //   322: ldc             20
        //   324: aaload         
        //   325: aload           cmd2
        //   327: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   332: dup            
        //   333: aload_3         /* arg */
        //   334: swap           
        //   335: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   338: pop            
        //   339: aload           4
        //   341: ldc             21
        //   343: aaload         
        //   344: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$groovy$grape$Grape:()Ljava/lang/Class;
        //   347: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   352: pop            
        //   353: aload           4
        //   355: ldc             22
        //   357: aaload         
        //   358: aload_0         /* this */
        //   359: aload           4
        //   361: ldc             23
        //   363: aaload         
        //   364: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$apache$ivy$util$Message:()Ljava/lang/Class;
        //   367: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   372: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   377: pop            
        //   378: aload           4
        //   380: ldc             24
        //   382: aaload         
        //   383: aload           4
        //   385: ldc             25
        //   387: aaload         
        //   388: aload_3         /* arg */
        //   389: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   392: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   397: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure3.$const$2:Ljava/lang/Integer;
        //   400: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   405: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure3.$const$3:Ljava/lang/Integer;
        //   408: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareNotEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   411: ifeq            433
        //   414: aload           4
        //   416: ldc             26
        //   418: aaload         
        //   419: aload_0         /* this */
        //   420: ldc             "There need to be a multiple of three arguments: (group module version)+"
        //   422: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   427: pop            
        //   428: aconst_null    
        //   429: areturn        
        //   430: goto            433
        //   433: aload           4
        //   435: ldc             27
        //   437: aaload         
        //   438: aload           4
        //   440: ldc             28
        //   442: aaload         
        //   443: aload_0         /* this */
        //   444: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   449: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   454: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure3.$const$2:Ljava/lang/Integer;
        //   457: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareLessThan:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   460: ifeq            482
        //   463: aload           4
        //   465: ldc             29
        //   467: aaload         
        //   468: aload_0         /* this */
        //   469: ldc             "At least one Grape reference is required"
        //   471: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   476: pop            
        //   477: aconst_null    
        //   478: areturn        
        //   479: goto            482
        //   482: aconst_null    
        //   483: new             Lgroovy/lang/Reference;
        //   486: dup_x1         
        //   487: swap           
        //   488: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   491: astore          before
        //   493: aconst_null    
        //   494: new             Lgroovy/lang/Reference;
        //   497: dup_x1         
        //   498: swap           
        //   499: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   502: astore          between
        //   504: aconst_null    
        //   505: new             Lgroovy/lang/Reference;
        //   508: dup_x1         
        //   509: swap           
        //   510: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   513: astore          after
        //   515: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   518: new             Lgroovy/lang/Reference;
        //   521: dup_x1         
        //   522: swap           
        //   523: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   526: astore          ivyFormatRequested
        //   528: aload           4
        //   530: ldc             30
        //   532: aaload         
        //   533: aload           cmd2
        //   535: ldc             "a"
        //   537: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   542: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   545: ifeq            581
        //   548: ldc             "<pathelement location=\""
        //   550: dup            
        //   551: aload           before
        //   553: swap           
        //   554: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   557: pop            
        //   558: ldc             "\">\n<pathelement location=\""
        //   560: dup            
        //   561: aload           between
        //   563: swap           
        //   564: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   567: pop            
        //   568: ldc             "\">"
        //   570: dup            
        //   571: aload           after
        //   573: swap           
        //   574: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   577: pop            
        //   578: goto            781
        //   581: aload           4
        //   583: ldc             31
        //   585: aaload         
        //   586: aload           cmd2
        //   588: ldc             "d"
        //   590: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   595: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   598: ifeq            634
        //   601: ldc             "set CLASSPATH="
        //   603: dup            
        //   604: aload           before
        //   606: swap           
        //   607: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   610: pop            
        //   611: ldc             ";"
        //   613: dup            
        //   614: aload           between
        //   616: swap           
        //   617: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   620: pop            
        //   621: ldc             ""
        //   623: dup            
        //   624: aload           after
        //   626: swap           
        //   627: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   630: pop            
        //   631: goto            781
        //   634: aload           4
        //   636: ldc             32
        //   638: aaload         
        //   639: aload           cmd2
        //   641: ldc             "s"
        //   643: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   648: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   651: ifeq            687
        //   654: ldc             "export CLASSPATH="
        //   656: dup            
        //   657: aload           before
        //   659: swap           
        //   660: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   663: pop            
        //   664: ldc             ":"
        //   666: dup            
        //   667: aload           between
        //   669: swap           
        //   670: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   673: pop            
        //   674: ldc             ""
        //   676: dup            
        //   677: aload           after
        //   679: swap           
        //   680: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   683: pop            
        //   684: goto            781
        //   687: aload           4
        //   689: ldc             33
        //   691: aaload         
        //   692: aload           cmd2
        //   694: ldc             "i"
        //   696: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   701: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   704: ifeq            751
        //   707: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   710: dup            
        //   711: aload           ivyFormatRequested
        //   713: swap           
        //   714: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   717: pop            
        //   718: ldc             "<dependency "
        //   720: dup            
        //   721: aload           before
        //   723: swap           
        //   724: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   727: pop            
        //   728: ldc             "\">\n<dependency "
        //   730: dup            
        //   731: aload           between
        //   733: swap           
        //   734: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   737: pop            
        //   738: ldc             "\">"
        //   740: dup            
        //   741: aload           after
        //   743: swap           
        //   744: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   747: pop            
        //   748: goto            781
        //   751: ldc             ""
        //   753: dup            
        //   754: aload           before
        //   756: swap           
        //   757: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   760: pop            
        //   761: ldc             "\n"
        //   763: dup            
        //   764: aload           between
        //   766: swap           
        //   767: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   770: pop            
        //   771: ldc             "\n"
        //   773: dup            
        //   774: aload           after
        //   776: swap           
        //   777: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   780: pop            
        //   781: aload           4
        //   783: ldc             34
        //   785: aaload         
        //   786: aload_3         /* arg */
        //   787: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   790: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   795: dup            
        //   796: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure3:()Ljava/lang/Class;
        //   799: aload_0         /* this */
        //   800: ldc             "iter"
        //   802: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   805: pop            
        //   806: iconst_1       
        //   807: anewarray       Ljava/lang/Object;
        //   810: dup            
        //   811: iconst_0       
        //   812: iconst_0       
        //   813: anewarray       Ljava/lang/Object;
        //   816: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //   819: aastore        
        //   820: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createList:([Ljava/lang/Object;)Ljava/util/List;
        //   823: new             Lgroovy/lang/Reference;
        //   826: dup_x1         
        //   827: swap           
        //   828: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   831: astore          params
        //   833: iconst_0       
        //   834: anewarray       Ljava/lang/Object;
        //   837: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createList:([Ljava/lang/Object;)Ljava/util/List;
        //   840: new             Lgroovy/lang/Reference;
        //   843: dup_x1         
        //   844: swap           
        //   845: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   848: astore          depsInfo
        //   850: aload           ivyFormatRequested
        //   852: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   855: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   858: ifeq            885
        //   861: aload           4
        //   863: ldc             35
        //   865: aaload         
        //   866: aload           params
        //   868: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   871: aload           depsInfo
        //   873: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   876: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   881: pop            
        //   882: goto            885
        //   885: aload           4
        //   887: ldc             36
        //   889: aaload         
        //   890: aload           4
        //   892: ldc             37
        //   894: aaload         
        //   895: aload_0         /* this */
        //   896: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   901: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   906: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   909: ifeq            1035
        //   912: aload           4
        //   914: ldc             38
        //   916: aaload         
        //   917: aload           params
        //   919: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   922: bipush          6
        //   924: anewarray       Ljava/lang/Object;
        //   927: dup            
        //   928: iconst_0       
        //   929: ldc_w           "group"
        //   932: aastore        
        //   933: dup            
        //   934: iconst_1       
        //   935: aload           4
        //   937: ldc_w           39
        //   940: aaload         
        //   941: aload           4
        //   943: ldc_w           40
        //   946: aaload         
        //   947: aload_0         /* this */
        //   948: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   953: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   958: aastore        
        //   959: dup            
        //   960: iconst_2       
        //   961: ldc_w           "module"
        //   964: aastore        
        //   965: dup            
        //   966: iconst_3       
        //   967: aload           4
        //   969: ldc_w           41
        //   972: aaload         
        //   973: aload           4
        //   975: ldc_w           42
        //   978: aaload         
        //   979: aload_0         /* this */
        //   980: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   985: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   990: aastore        
        //   991: dup            
        //   992: iconst_4       
        //   993: ldc_w           "version"
        //   996: aastore        
        //   997: dup            
        //   998: iconst_5       
        //   999: aload           4
        //  1001: ldc_w           43
        //  1004: aaload         
        //  1005: aload           4
        //  1007: ldc_w           44
        //  1010: aaload         
        //  1011: aload_0         /* this */
        //  1012: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1017: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1022: aastore        
        //  1023: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //  1026: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1031: pop            
        //  1032: goto            885
        //  1035: iconst_0       
        //  1036: anewarray       Ljava/lang/Object;
        //  1039: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createList:([Ljava/lang/Object;)Ljava/util/List;
        //  1042: new             Lgroovy/lang/Reference;
        //  1045: dup_x1         
        //  1046: swap           
        //  1047: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //  1050: astore          results
        //  1052: aload           4
        //  1054: ldc_w           45
        //  1057: aaload         
        //  1058: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$groovy$grape$Grape:()Ljava/lang/Class;
        //  1061: iconst_0       
        //  1062: anewarray       Ljava/lang/Object;
        //  1065: iconst_1       
        //  1066: anewarray       Ljava/lang/Object;
        //  1069: dup            
        //  1070: iconst_0       
        //  1071: aload           params
        //  1073: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1076: aastore        
        //  1077: iconst_1       
        //  1078: newarray        I
        //  1080: dup            
        //  1081: iconst_0       
        //  1082: ldc             0
        //  1084: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1087: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //  1090: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //  1093: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.intUnbox:(Ljava/lang/Object;)I
        //  1096: iastore        
        //  1097: astore          14
        //  1099: aload           14
        //  1101: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.despreadList:([Ljava/lang/Object;[Ljava/lang/Object;[I)[Ljava/lang/Object;
        //  1104: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //  1109: new             Lgroovy/lang/Reference;
        //  1112: dup_x1         
        //  1113: swap           
        //  1114: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //  1117: astore          uris
        //  1119: aload           ivyFormatRequested
        //  1121: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1124: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //  1127: ifne            1134
        //  1130: iconst_1       
        //  1131: goto            1135
        //  1134: iconst_0       
        //  1135: ifeq            1318
        //  1138: aconst_null    
        //  1139: new             Lgroovy/lang/Reference;
        //  1142: dup_x1         
        //  1143: swap           
        //  1144: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //  1147: astore          uri
        //  1149: aload           4
        //  1151: ldc_w           46
        //  1154: aaload         
        //  1155: aload           uris
        //  1157: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1160: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1165: astore          17
        //  1167: aload           17
        //  1169: invokeinterface java/util/Iterator.hasNext:()Z
        //  1174: ifeq            1315
        //  1177: aload           17
        //  1179: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1184: aload           uri
        //  1186: swap           
        //  1187: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //  1190: aload           4
        //  1192: ldc_w           47
        //  1195: aaload         
        //  1196: aload           uri
        //  1198: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1201: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1206: ldc_w           "file"
        //  1209: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //  1212: ifeq            1272
        //  1215: aload           4
        //  1217: ldc_w           48
        //  1220: aaload         
        //  1221: aload           results
        //  1223: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1226: aload           4
        //  1228: ldc_w           49
        //  1231: aaload         
        //  1232: aload           4
        //  1234: ldc_w           50
        //  1237: aaload         
        //  1238: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure3.$get$$class$java$io$File:()Ljava/lang/Class;
        //  1241: aload           uri
        //  1243: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1246: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1251: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1256: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1261: dup            
        //  1262: aload           results
        //  1264: swap           
        //  1265: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //  1268: pop            
        //  1269: goto            1312
        //  1272: aload           4
        //  1274: ldc_w           51
        //  1277: aaload         
        //  1278: aload           results
        //  1280: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1283: aload           4
        //  1285: ldc_w           52
        //  1288: aaload         
        //  1289: aload           uri
        //  1291: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1294: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1299: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1304: dup            
        //  1305: aload           results
        //  1307: swap           
        //  1308: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //  1311: pop            
        //  1312: goto            1167
        //  1315: goto            1349
        //  1318: aload           4
        //  1320: ldc_w           53
        //  1323: aaload         
        //  1324: aload           depsInfo
        //  1326: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1329: new             Lorg/codehaus/groovy/tools/GrapeMain$_run_closure3_closure9;
        //  1332: dup            
        //  1333: aload_0         /* this */
        //  1334: aload_0         /* this */
        //  1335: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //  1338: aload           results
        //  1340: invokespecial   org/codehaus/groovy/tools/GrapeMain$_run_closure3_closure9.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //  1343: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1348: pop            
        //  1349: aload           results
        //  1351: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1354: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //  1357: ifeq            1457
        //  1360: aload           4
        //  1362: ldc_w           54
        //  1365: aaload         
        //  1366: aload_0         /* this */
        //  1367: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //  1370: dup            
        //  1371: iconst_3       
        //  1372: anewarray       Ljava/lang/Object;
        //  1375: dup            
        //  1376: iconst_0       
        //  1377: aload           before
        //  1379: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1382: aastore        
        //  1383: dup            
        //  1384: iconst_1       
        //  1385: aload           4
        //  1387: ldc_w           55
        //  1390: aaload         
        //  1391: aload           results
        //  1393: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1396: aload           between
        //  1398: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1401: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1406: aastore        
        //  1407: dup            
        //  1408: iconst_2       
        //  1409: aload           after
        //  1411: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1414: aastore        
        //  1415: iconst_4       
        //  1416: anewarray       Ljava/lang/String;
        //  1419: dup            
        //  1420: iconst_0       
        //  1421: ldc             ""
        //  1423: aastore        
        //  1424: dup            
        //  1425: iconst_1       
        //  1426: ldc             ""
        //  1428: aastore        
        //  1429: dup            
        //  1430: iconst_2       
        //  1431: ldc             ""
        //  1433: aastore        
        //  1434: dup            
        //  1435: iconst_3       
        //  1436: ldc             ""
        //  1438: aastore        
        //  1439: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //  1442: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //  1447: astore          16
        //  1449: nop            
        //  1450: nop            
        //  1451: aload           16
        //  1453: areturn        
        //  1454: goto            1479
        //  1457: aload           4
        //  1459: ldc_w           56
        //  1462: aaload         
        //  1463: aload_0         /* this */
        //  1464: ldc_w           "Nothing was resolved"
        //  1467: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //  1472: astore          16
        //  1474: nop            
        //  1475: nop            
        //  1476: aload           16
        //  1478: areturn        
        //  1479: goto            1615
        //  1482: new             Lgroovy/lang/Reference;
        //  1485: dup_x1         
        //  1486: swap           
        //  1487: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //  1490: astore          e
        //  1492: aload           4
        //  1494: ldc_w           57
        //  1497: aaload         
        //  1498: aload_0         /* this */
        //  1499: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //  1502: dup            
        //  1503: iconst_1       
        //  1504: anewarray       Ljava/lang/Object;
        //  1507: dup            
        //  1508: iconst_0       
        //  1509: aload           4
        //  1511: ldc_w           58
        //  1514: aaload         
        //  1515: aload           e
        //  1517: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1520: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1525: aastore        
        //  1526: iconst_2       
        //  1527: anewarray       Ljava/lang/String;
        //  1530: dup            
        //  1531: iconst_0       
        //  1532: ldc_w           "Error in resolve:\n\t"
        //  1535: aastore        
        //  1536: dup            
        //  1537: iconst_1       
        //  1538: ldc             ""
        //  1540: aastore        
        //  1541: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //  1544: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //  1549: pop            
        //  1550: aload           4
        //  1552: ldc_w           59
        //  1555: aaload         
        //  1556: aload           e
        //  1558: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //  1561: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1566: ldc_w           "unresolved dependency"
        //  1569: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.findRegex:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/regex/Matcher;
        //  1572: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //  1575: ifeq            1603
        //  1578: aload           4
        //  1580: ldc_w           60
        //  1583: aaload         
        //  1584: aload_0         /* this */
        //  1585: ldc_w           "Perhaps the grape is not installed?"
        //  1588: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //  1593: astore          14
        //  1595: nop            
        //  1596: nop            
        //  1597: aload           14
        //  1599: areturn        
        //  1600: goto            1611
        //  1603: aconst_null    
        //  1604: astore          14
        //  1606: nop            
        //  1607: nop            
        //  1608: aload           14
        //  1610: areturn        
        //  1611: nop            
        //  1612: goto            1615
        //  1615: nop            
        //  1616: goto            1624
        //  1619: astore          13
        //  1621: aload           13
        //  1623: athrow         
        //  1624: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  1035   1450   1482   1611   Ljava/lang/Exception;
        //  1451   1475   1482   1611   Ljava/lang/Exception;
        //  1476   1482   1482   1611   Ljava/lang/Exception;
        //  1035   1450   1619   1624   Any
        //  1451   1475   1619   1624   Any
        //  1476   1482   1619   1624   Any
        //  1482   1596   1619   1624   Any
        //  1597   1607   1619   1624   Any
        //  1608   1612   1619   1624   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object call(final Object arg, final Object cmd) {
        final Object arg2 = new Reference(arg);
        return $getCallSiteArray()[61].callCurrent(this, ((Reference<Object>)arg2).get(), cmd);
    }
    
    static {
        $const$3 = 0;
        $const$2 = 3;
        $const$1 = -1;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[62];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure3.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
