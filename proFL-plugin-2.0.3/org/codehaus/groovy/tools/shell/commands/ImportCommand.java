// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.tools.shell.BufferManager;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class ImportCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204806;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommand;
    
    public ImportCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "import";
        arguments[2] = "\\i";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected List createCompletors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor(), $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)))), null }), $get$$class$java$util$List());
    }
    
    public Object execute(final List args) {
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
        //    12: aload_1         /* args */
        //    13: aload_3        
        //    14: swap           
        //    15: ldc             8
        //    17: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.record:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    20: aconst_null    
        //    21: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareNotEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    24: dup            
        //    25: ifeq            34
        //    28: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    31: goto            37
        //    34: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    37: aload_3        
        //    38: swap           
        //    39: ldc             13
        //    41: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.record:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    44: pop            
        //    45: ifeq            55
        //    48: aload_3        
        //    49: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    52: goto            73
        //    55: ldc             "assert args != null"
        //    57: aload_3        
        //    58: invokestatic    org/codehaus/groovy/transform/powerassert/AssertionRenderer.render:(Ljava/lang/String;Lorg/codehaus/groovy/transform/powerassert/ValueRecorder;)Ljava/lang/String;
        //    61: aconst_null    
        //    62: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.assertFailed:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    65: goto            73
        //    68: aload_3        
        //    69: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    72: athrow         
        //    73: aload_2        
        //    74: ldc             4
        //    76: aaload         
        //    77: aload_1         /* args */
        //    78: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    83: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    86: ifeq            105
        //    89: aload_2        
        //    90: ldc             5
        //    92: aaload         
        //    93: aload_0         /* this */
        //    94: ldc             "Command 'import' requires one or more arguments"
        //    96: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   101: pop            
        //   102: goto            105
        //   105: iconst_1       
        //   106: anewarray       Ljava/lang/Object;
        //   109: dup            
        //   110: iconst_0       
        //   111: aload_2        
        //   112: ldc             6
        //   114: aaload         
        //   115: ldc             "import "
        //   117: aload_2        
        //   118: ldc             7
        //   120: aaload         
        //   121: aload_1         /* args */
        //   122: ldc             " "
        //   124: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   129: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   134: aastore        
        //   135: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createList:([Ljava/lang/Object;)Ljava/util/List;
        //   138: astore          buff
        //   140: aload_2        
        //   141: ldc             8
        //   143: aaload         
        //   144: aload           buff
        //   146: ldc             "def dummp = false"
        //   148: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   153: pop            
        //   154: aconst_null    
        //   155: astore          type
        //   157: aload_2        
        //   158: ldc             9
        //   160: aaload         
        //   161: aload_2        
        //   162: ldc             10
        //   164: aaload         
        //   165: aload_0         /* this */
        //   166: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   171: aload_2        
        //   172: ldc             11
        //   174: aaload         
        //   175: aload           buff
        //   177: aload_2        
        //   178: ldc             12
        //   180: aaload         
        //   181: aload_0         /* this */
        //   182: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   187: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   192: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   197: dup            
        //   198: astore          type
        //   200: pop            
        //   201: aload_2        
        //   202: ldc             13
        //   204: aaload         
        //   205: aload_2        
        //   206: ldc             14
        //   208: aaload         
        //   209: aload_0         /* this */
        //   210: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   215: aload_2        
        //   216: ldc             15
        //   218: aaload         
        //   219: aload           buff
        //   221: getstatic       org/codehaus/groovy/tools/shell/commands/ImportCommand.$const$0:Ljava/lang/Integer;
        //   224: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   229: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   234: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   237: ifeq            265
        //   240: aload_2        
        //   241: ldc             16
        //   243: aaload         
        //   244: aload_2        
        //   245: ldc             17
        //   247: aaload         
        //   248: aload_0         /* this */
        //   249: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   254: ldc             "Removed duplicate import from list"
        //   256: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   261: pop            
        //   262: goto            265
        //   265: aload_2        
        //   266: ldc             18
        //   268: aaload         
        //   269: aload_2        
        //   270: ldc             19
        //   272: aaload         
        //   273: aload_0         /* this */
        //   274: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   279: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   282: dup            
        //   283: iconst_1       
        //   284: anewarray       Ljava/lang/Object;
        //   287: dup            
        //   288: iconst_0       
        //   289: aload_2        
        //   290: ldc             20
        //   292: aaload         
        //   293: aload           buff
        //   295: getstatic       org/codehaus/groovy/tools/shell/commands/ImportCommand.$const$0:Ljava/lang/Integer;
        //   298: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   303: aastore        
        //   304: iconst_2       
        //   305: anewarray       Ljava/lang/String;
        //   308: dup            
        //   309: iconst_0       
        //   310: ldc             "Adding import: "
        //   312: aastore        
        //   313: dup            
        //   314: iconst_1       
        //   315: ldc             ""
        //   317: aastore        
        //   318: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   321: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   326: pop            
        //   327: aload_2        
        //   328: ldc             21
        //   330: aaload         
        //   331: aload_2        
        //   332: ldc             22
        //   334: aaload         
        //   335: aload_0         /* this */
        //   336: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   341: aload_2        
        //   342: ldc             23
        //   344: aaload         
        //   345: aload           buff
        //   347: getstatic       org/codehaus/groovy/tools/shell/commands/ImportCommand.$const$0:Ljava/lang/Integer;
        //   350: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   355: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   360: invokestatic    org/codehaus/groovy/tools/shell/commands/ImportCommand.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   363: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   366: checkcast       Ljava/lang/Object;
        //   369: astore          6
        //   371: nop            
        //   372: aload_2        
        //   373: ldc             24
        //   375: aaload         
        //   376: aload_2        
        //   377: ldc             25
        //   379: aaload         
        //   380: aload_2        
        //   381: ldc             26
        //   383: aaload         
        //   384: aload_0         /* this */
        //   385: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   390: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   395: aload_2        
        //   396: ldc             27
        //   398: aaload         
        //   399: aload           type
        //   401: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetPropertySafe:(Ljava/lang/Object;)Ljava/lang/Object;
        //   406: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   411: pop            
        //   412: nop            
        //   413: aload           6
        //   415: areturn        
        //   416: goto            580
        //   419: astore          e
        //   421: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   424: dup            
        //   425: iconst_2       
        //   426: anewarray       Ljava/lang/Object;
        //   429: dup            
        //   430: iconst_0       
        //   431: aload_2        
        //   432: ldc             28
        //   434: aaload         
        //   435: aload           buff
        //   437: getstatic       org/codehaus/groovy/tools/shell/commands/ImportCommand.$const$0:Ljava/lang/Integer;
        //   440: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   445: aastore        
        //   446: dup            
        //   447: iconst_1       
        //   448: aload_2        
        //   449: ldc             29
        //   451: aaload         
        //   452: aload           e
        //   454: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   459: aastore        
        //   460: iconst_3       
        //   461: anewarray       Ljava/lang/String;
        //   464: dup            
        //   465: iconst_0       
        //   466: ldc             "Invalid import definition: '"
        //   468: aastore        
        //   469: dup            
        //   470: iconst_1       
        //   471: ldc             "'; reason: "
        //   473: aastore        
        //   474: dup            
        //   475: iconst_2       
        //   476: ldc             ""
        //   478: aastore        
        //   479: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   482: astore          msg
        //   484: aload_2        
        //   485: ldc             30
        //   487: aaload         
        //   488: aload_2        
        //   489: ldc             31
        //   491: aaload         
        //   492: aload_0         /* this */
        //   493: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   498: aload           msg
        //   500: aload           e
        //   502: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   507: pop            
        //   508: aload_2        
        //   509: ldc             32
        //   511: aaload         
        //   512: aload_0         /* this */
        //   513: aload           msg
        //   515: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   520: invokestatic    org/codehaus/groovy/tools/shell/commands/ImportCommand.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   523: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   526: checkcast       Ljava/lang/Object;
        //   529: astore          8
        //   531: nop            
        //   532: aload_2        
        //   533: ldc             33
        //   535: aaload         
        //   536: aload_2        
        //   537: ldc             34
        //   539: aaload         
        //   540: aload_2        
        //   541: ldc             35
        //   543: aaload         
        //   544: aload_0         /* this */
        //   545: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   550: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   555: aload_2        
        //   556: ldc             36
        //   558: aaload         
        //   559: aload           type
        //   561: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetPropertySafe:(Ljava/lang/Object;)Ljava/lang/Object;
        //   566: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   571: pop            
        //   572: nop            
        //   573: aload           8
        //   575: areturn        
        //   576: nop            
        //   577: goto            580
        //   580: aload_2        
        //   581: ldc             37
        //   583: aaload         
        //   584: aload_2        
        //   585: ldc             38
        //   587: aaload         
        //   588: aload_2        
        //   589: ldc             39
        //   591: aaload         
        //   592: aload_0         /* this */
        //   593: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   598: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   603: aload_2        
        //   604: ldc             40
        //   606: aaload         
        //   607: aload           type
        //   609: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetPropertySafe:(Ljava/lang/Object;)Ljava/lang/Object;
        //   614: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   619: pop            
        //   620: nop            
        //   621: goto            669
        //   624: astore          6
        //   626: aload_2        
        //   627: ldc             41
        //   629: aaload         
        //   630: aload_2        
        //   631: ldc             42
        //   633: aaload         
        //   634: aload_2        
        //   635: ldc             43
        //   637: aaload         
        //   638: aload_0         /* this */
        //   639: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   644: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   649: aload_2        
        //   650: ldc             44
        //   652: aaload         
        //   653: aload           type
        //   655: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetPropertySafe:(Ljava/lang/Object;)Ljava/lang/Object;
        //   660: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   665: pop            
        //   666: aload           6
        //   668: athrow         
        //   669: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  157    372    419    576    Lorg/codehaus/groovy/control/CompilationFailedException;
        //  413    419    419    576    Lorg/codehaus/groovy/control/CompilationFailedException;
        //  157    372    624    669    Any
        //  413    419    624    669    Any
        //  419    532    624    669    Any
        //  573    577    624    669    Any
        //  12     65     68     73     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ImportCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ImportCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ImportCommand.__timeStamp__239_neverHappen1292524204806 = 0L;
        ImportCommand.__timeStamp = 1292524204806L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[45];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ImportCommand.$callSiteArray == null || ($createCallSiteArray = ImportCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ImportCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = ImportCommand.$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = (ImportCommand.$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.ImportCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ImportCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ImportCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = ImportCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (ImportCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ImportCommand.$class$java$util$List) == null) {
            $class$java$util$List = (ImportCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$ImportCommand = ImportCommand.$class$org$codehaus$groovy$tools$shell$commands$ImportCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ImportCommand = (ImportCommand.$class$org$codehaus$groovy$tools$shell$commands$ImportCommand = class$("org.codehaus.groovy.tools.shell.commands.ImportCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ImportCommand;
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
