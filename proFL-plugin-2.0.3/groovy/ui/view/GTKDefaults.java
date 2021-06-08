// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class GTKDefaults extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205381;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$view$GTKDefaults;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    
    public GTKDefaults() {
        $getCallSiteArray();
    }
    
    public GTKDefaults(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$groovy$ui$view$GTKDefaults(), args);
    }
    
    @Override
    public Object run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: aload_1        
        //     5: ldc             1
        //     7: aaload         
        //     8: aload_0         /* this */
        //     9: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$Defaults:()Ljava/lang/Class;
        //    12: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //    17: pop            
        //    18: aload_1        
        //    19: ldc             2
        //    21: aaload         
        //    22: aload_1        
        //    23: ldc             3
        //    25: aaload         
        //    26: aload_1        
        //    27: ldc             4
        //    29: aaload         
        //    30: aload_0         /* this */
        //    31: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    36: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    41: aload_1        
        //    42: ldc             5
        //    44: aaload         
        //    45: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$javax$swing$text$StyleConstants:()Ljava/lang/Class;
        //    48: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    53: ldc             "DejaVu Sans Mono"
        //    55: astore_2       
        //    56: aload_2        
        //    57: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    62: pop            
        //    63: aload_2        
        //    64: pop            
        //    65: aload_1        
        //    66: ldc             6
        //    68: aaload         
        //    69: aload_1        
        //    70: ldc             7
        //    72: aaload         
        //    73: aload_1        
        //    74: ldc             8
        //    76: aaload         
        //    77: aload_0         /* this */
        //    78: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    83: aload_1        
        //    84: ldc             9
        //    86: aaload         
        //    87: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$javax$swing$text$StyleContext:()Ljava/lang/Class;
        //    90: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    95: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   100: aload_1        
        //   101: ldc             10
        //   103: aaload         
        //   104: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$javax$swing$text$StyleConstants:()Ljava/lang/Class;
        //   107: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   112: ldc             "DejaVu Sans Mono"
        //   114: astore_2       
        //   115: aload_2        
        //   116: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   121: pop            
        //   122: aload_2        
        //   123: pop            
        //   124: aload_1        
        //   125: ldc             11
        //   127: aaload         
        //   128: aload_1        
        //   129: ldc             12
        //   131: aaload         
        //   132: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$java$lang$System:()Ljava/lang/Class;
        //   135: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   140: ldc             "java.version"
        //   142: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   147: ldc             "^1\\.5"
        //   149: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.findRegex:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/regex/Matcher;
        //   152: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   155: ifeq            254
        //   158: aload_1        
        //   159: ldc             13
        //   161: aaload         
        //   162: aload_0         /* this */
        //   163: iconst_2       
        //   164: anewarray       Ljava/lang/Object;
        //   167: dup            
        //   168: iconst_0       
        //   169: ldc             "boldFonts"
        //   171: aastore        
        //   172: dup            
        //   173: iconst_1       
        //   174: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   177: aastore        
        //   178: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //   181: ldc             "metal"
        //   183: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   188: pop            
        //   189: aload_1        
        //   190: ldc             14
        //   192: aaload         
        //   193: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$org$codehaus$groovy$runtime$InvokerHelper:()Ljava/lang/Class;
        //   196: ldc             "com.sun.java.swing.SwingUtilities2"
        //   198: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$java$lang$Class:()Ljava/lang/Class;
        //   201: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.asType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   204: checkcast       Ljava/lang/Class;
        //   207: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$java$lang$Class:()Ljava/lang/Class;
        //   210: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createPojoWrapper:(Ljava/lang/Object;Ljava/lang/Class;)Lorg/codehaus/groovy/runtime/wrappers/Wrapper;
        //   213: ldc             "AA_TEXT_PROPERTY_KEY"
        //   215: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   220: dup            
        //   221: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   224: aload_0         /* this */
        //   225: ldc             "key"
        //   227: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   230: pop            
        //   231: aload_1        
        //   232: ldc             15
        //   234: aaload         
        //   235: aload_0         /* this */
        //   236: new             Lgroovy/ui/view/GTKDefaults$_run_closure1;
        //   239: dup            
        //   240: aload_0         /* this */
        //   241: aload_0         /* this */
        //   242: invokespecial   groovy/ui/view/GTKDefaults$_run_closure1.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   245: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   250: pop            
        //   251: goto            254
        //   254: aload_1        
        //   255: ldc             16
        //   257: aaload         
        //   258: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$java$awt$print$PrinterJob:()Ljava/lang/Class;
        //   261: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   266: dup            
        //   267: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   270: aload_0         /* this */
        //   271: ldc             "pj"
        //   273: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   276: pop            
        //   277: aload_1        
        //   278: ldc             17
        //   280: aaload         
        //   281: aload_1        
        //   282: ldc             18
        //   284: aaload         
        //   285: aload_0         /* this */
        //   286: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   291: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   296: dup            
        //   297: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   300: aload_0         /* this */
        //   301: ldc             "ps"
        //   303: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   306: pop            
        //   307: aload_1        
        //   308: ldc             19
        //   310: aaload         
        //   311: aload_1        
        //   312: ldc             20
        //   314: aaload         
        //   315: aload_0         /* this */
        //   316: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   321: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   326: pop            
        //   327: aload_1        
        //   328: ldc             21
        //   330: aaload         
        //   331: aload_1        
        //   332: ldc             22
        //   334: aaload         
        //   335: aload_1        
        //   336: ldc             23
        //   338: aaload         
        //   339: aload_0         /* this */
        //   340: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   345: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   350: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$java$util$List:()Ljava/lang/Class;
        //   353: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.asType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   356: checkcast       Ljava/util/List;
        //   359: new             Lgroovy/ui/view/GTKDefaults$_run_closure2;
        //   362: dup            
        //   363: aload_0         /* this */
        //   364: aload_0         /* this */
        //   365: invokespecial   groovy/ui/view/GTKDefaults$_run_closure2.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   368: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   373: dup            
        //   374: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   377: aload_0         /* this */
        //   378: ldc             "docFlav"
        //   380: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   383: pop            
        //   384: aload_1        
        //   385: ldc             24
        //   387: aaload         
        //   388: aload_1        
        //   389: ldc             25
        //   391: aaload         
        //   392: aload_0         /* this */
        //   393: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   398: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   403: dup            
        //   404: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   407: aload_0         /* this */
        //   408: ldc             "attrset"
        //   410: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   413: pop            
        //   414: aload_1        
        //   415: ldc             26
        //   417: aaload         
        //   418: aload_1        
        //   419: ldc             27
        //   421: aaload         
        //   422: aload_0         /* this */
        //   423: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   428: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$javax$print$attribute$standard$OrientationRequested:()Ljava/lang/Class;
        //   431: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   436: dup            
        //   437: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   440: ifeq            446
        //   443: goto            469
        //   446: pop            
        //   447: aload_1        
        //   448: ldc             28
        //   450: aaload         
        //   451: aload_1        
        //   452: ldc             29
        //   454: aaload         
        //   455: aload_0         /* this */
        //   456: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   461: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$javax$print$attribute$standard$OrientationRequested:()Ljava/lang/Class;
        //   464: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   469: dup            
        //   470: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   473: aload_0         /* this */
        //   474: ldc             "orient"
        //   476: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   479: pop            
        //   480: aload_1        
        //   481: ldc             30
        //   483: aaload         
        //   484: aload_1        
        //   485: ldc             31
        //   487: aaload         
        //   488: aload_0         /* this */
        //   489: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   494: aload_1        
        //   495: ldc             32
        //   497: aaload         
        //   498: aload_0         /* this */
        //   499: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   504: aload_1        
        //   505: ldc             33
        //   507: aaload         
        //   508: aload_0         /* this */
        //   509: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   514: aload_1        
        //   515: ldc             34
        //   517: aaload         
        //   518: aload_0         /* this */
        //   519: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   524: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   529: astore_2       
        //   530: nop            
        //   531: nop            
        //   532: aload_2        
        //   533: areturn        
        //   534: goto            665
        //   537: astore_2       
        //   538: aload_1        
        //   539: ldc             35
        //   541: aaload         
        //   542: aload_0         /* this */
        //   543: bipush          12
        //   545: anewarray       Ljava/lang/Object;
        //   548: dup            
        //   549: iconst_0       
        //   550: ldc             "name"
        //   552: aastore        
        //   553: dup            
        //   554: iconst_1       
        //   555: ldc             "Print..."
        //   557: aastore        
        //   558: dup            
        //   559: iconst_2       
        //   560: ldc             "closure"
        //   562: aastore        
        //   563: dup            
        //   564: iconst_3       
        //   565: aload_1        
        //   566: ldc             36
        //   568: aaload         
        //   569: aload_0         /* this */
        //   570: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   575: ldc             "print"
        //   577: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   580: aastore        
        //   581: dup            
        //   582: iconst_4       
        //   583: ldc             "mnemonic"
        //   585: aastore        
        //   586: dup            
        //   587: iconst_5       
        //   588: ldc             "P"
        //   590: aastore        
        //   591: dup            
        //   592: bipush          6
        //   594: ldc             "accelerator"
        //   596: aastore        
        //   597: dup            
        //   598: bipush          7
        //   600: aload_1        
        //   601: ldc             37
        //   603: aaload         
        //   604: aload_0         /* this */
        //   605: ldc             "P"
        //   607: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   612: aastore        
        //   613: dup            
        //   614: bipush          8
        //   616: ldc             "shortDescription"
        //   618: aastore        
        //   619: dup            
        //   620: bipush          9
        //   622: ldc             "Printing does not work in Java with this version of CUPS"
        //   624: aastore        
        //   625: dup            
        //   626: bipush          10
        //   628: ldc             "enabled"
        //   630: aastore        
        //   631: dup            
        //   632: bipush          11
        //   634: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   637: aastore        
        //   638: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //   641: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   646: dup            
        //   647: invokestatic    groovy/ui/view/GTKDefaults.$get$$class$groovy$ui$view$GTKDefaults:()Ljava/lang/Class;
        //   650: aload_0         /* this */
        //   651: ldc             "printAction"
        //   653: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   656: astore_3       
        //   657: nop            
        //   658: nop            
        //   659: aload_3        
        //   660: areturn        
        //   661: nop            
        //   662: goto            665
        //   665: nop            
        //   666: goto            672
        //   669: astore_2       
        //   670: aload_2        
        //   671: athrow         
        //   672: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  254    531    537    661    Ljava/lang/NullPointerException;
        //  532    537    537    661    Ljava/lang/NullPointerException;
        //  254    531    669    672    Any
        //  532    537    669    672    Any
        //  537    658    669    672    Any
        //  659    662    669    672    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        GTKDefaults.__timeStamp__239_neverHappen1292524205381 = 0L;
        GTKDefaults.__timeStamp = 1292524205381L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[38];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$GTKDefaults(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GTKDefaults.$callSiteArray == null || ($createCallSiteArray = GTKDefaults.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GTKDefaults.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$GTKDefaults() {
        Class $class$groovy$ui$view$GTKDefaults;
        if (($class$groovy$ui$view$GTKDefaults = GTKDefaults.$class$groovy$ui$view$GTKDefaults) == null) {
            $class$groovy$ui$view$GTKDefaults = (GTKDefaults.$class$groovy$ui$view$GTKDefaults = class$("groovy.ui.view.GTKDefaults"));
        }
        return $class$groovy$ui$view$GTKDefaults;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = GTKDefaults.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (GTKDefaults.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = GTKDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (GTKDefaults.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
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
