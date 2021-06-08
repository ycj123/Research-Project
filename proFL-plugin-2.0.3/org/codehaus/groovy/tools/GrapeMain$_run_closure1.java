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

class GrapeMain$_run_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public GrapeMain$_run_closure1(final Object _outerInstance, final Object _thisObject) {
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
        //    10: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure1.$getCallSiteArray:()[Lorg/codehaus/groovy/runtime/callsite/CallSite;
        //    13: astore          4
        //    15: aload           4
        //    17: ldc             0
        //    19: aaload         
        //    20: aload_3         /* arg */
        //    21: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    24: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    29: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$0:Ljava/lang/Integer;
        //    32: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareGreaterThan:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    35: ifeq            44
        //    38: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    41: goto            70
        //    44: aload           4
        //    46: ldc             1
        //    48: aaload         
        //    49: aload_3         /* arg */
        //    50: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    53: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    58: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$1:Ljava/lang/Integer;
        //    61: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareLessThan:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    64: ifne            38
        //    67: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    70: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    73: ifeq            95
        //    76: aload           4
        //    78: ldc             2
        //    80: aaload         
        //    81: aload_0         /* this */
        //    82: ldc             "install requires two to four arguments, <group> <module> [<version>] [<classifier>]"
        //    84: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //    89: pop            
        //    90: aconst_null    
        //    91: areturn        
        //    92: goto            95
        //    95: ldc             "*"
        //    97: new             Lgroovy/lang/Reference;
        //   100: dup_x1         
        //   101: swap           
        //   102: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   105: astore          ver
        //   107: aload           4
        //   109: ldc             3
        //   111: aaload         
        //   112: aload_3         /* arg */
        //   113: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   116: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   121: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$2:Ljava/lang/Integer;
        //   124: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareGreaterThanEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   127: ifeq            158
        //   130: aload           4
        //   132: ldc             4
        //   134: aaload         
        //   135: aload_3         /* arg */
        //   136: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   139: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$1:Ljava/lang/Integer;
        //   142: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   147: dup            
        //   148: aload           ver
        //   150: swap           
        //   151: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   154: pop            
        //   155: goto            158
        //   158: aconst_null    
        //   159: new             Lgroovy/lang/Reference;
        //   162: dup_x1         
        //   163: swap           
        //   164: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   167: astore          classifier
        //   169: aload           4
        //   171: ldc             5
        //   173: aaload         
        //   174: aload_3         /* arg */
        //   175: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   178: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   183: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$0:Ljava/lang/Integer;
        //   186: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareGreaterThanEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   189: ifeq            220
        //   192: aload           4
        //   194: ldc             6
        //   196: aaload         
        //   197: aload_3         /* arg */
        //   198: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   201: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$2:Ljava/lang/Integer;
        //   204: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   209: dup            
        //   210: aload           classifier
        //   212: swap           
        //   213: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //   216: pop            
        //   217: goto            220
        //   220: aload           4
        //   222: ldc             7
        //   224: aaload         
        //   225: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure1.$get$$class$groovy$grape$Grape:()Ljava/lang/Class;
        //   228: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   233: pop            
        //   234: aload           4
        //   236: ldc             8
        //   238: aaload         
        //   239: aload_0         /* this */
        //   240: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;)Ljava/lang/Object;
        //   245: pop            
        //   246: aload           4
        //   248: ldc             9
        //   250: aaload         
        //   251: invokestatic    org/codehaus/groovy/tools/GrapeMain$_run_closure1.$get$$class$groovy$grape$Grape:()Ljava/lang/Class;
        //   254: bipush          12
        //   256: anewarray       Ljava/lang/Object;
        //   259: dup            
        //   260: iconst_0       
        //   261: ldc             "autoDownload"
        //   263: aastore        
        //   264: dup            
        //   265: iconst_1       
        //   266: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   269: aastore        
        //   270: dup            
        //   271: iconst_2       
        //   272: ldc             "group"
        //   274: aastore        
        //   275: dup            
        //   276: iconst_3       
        //   277: aload           4
        //   279: ldc             10
        //   281: aaload         
        //   282: aload_3         /* arg */
        //   283: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   286: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$3:Ljava/lang/Integer;
        //   289: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   294: aastore        
        //   295: dup            
        //   296: iconst_4       
        //   297: ldc             "module"
        //   299: aastore        
        //   300: dup            
        //   301: iconst_5       
        //   302: aload           4
        //   304: ldc             11
        //   306: aaload         
        //   307: aload_3         /* arg */
        //   308: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   311: getstatic       org/codehaus/groovy/tools/GrapeMain$_run_closure1.$const$4:Ljava/lang/Integer;
        //   314: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   319: aastore        
        //   320: dup            
        //   321: bipush          6
        //   323: ldc             "version"
        //   325: aastore        
        //   326: dup            
        //   327: bipush          7
        //   329: aload           ver
        //   331: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   334: aastore        
        //   335: dup            
        //   336: bipush          8
        //   338: ldc             "classifier"
        //   340: aastore        
        //   341: dup            
        //   342: bipush          9
        //   344: aload           classifier
        //   346: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   349: aastore        
        //   350: dup            
        //   351: bipush          10
        //   353: ldc             "noExceptions"
        //   355: aastore        
        //   356: dup            
        //   357: bipush          11
        //   359: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   362: aastore        
        //   363: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //   366: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   371: astore          7
        //   373: nop            
        //   374: nop            
        //   375: aload           7
        //   377: areturn        
        //   378: goto            444
        //   381: astore          e
        //   383: aload           4
        //   385: ldc             12
        //   387: aaload         
        //   388: aload_0         /* this */
        //   389: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   392: dup            
        //   393: iconst_1       
        //   394: anewarray       Ljava/lang/Object;
        //   397: dup            
        //   398: iconst_0       
        //   399: aload           4
        //   401: ldc             13
        //   403: aaload         
        //   404: aload_0         /* this */
        //   405: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   410: aastore        
        //   411: iconst_2       
        //   412: anewarray       Ljava/lang/String;
        //   415: dup            
        //   416: iconst_0       
        //   417: ldc             "An error occured : "
        //   419: aastore        
        //   420: dup            
        //   421: iconst_1       
        //   422: ldc             ""
        //   424: aastore        
        //   425: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   428: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   433: astore          8
        //   435: nop            
        //   436: nop            
        //   437: aload           8
        //   439: areturn        
        //   440: nop            
        //   441: goto            444
        //   444: nop            
        //   445: goto            453
        //   448: astore          7
        //   450: aload           7
        //   452: athrow         
        //   453: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  246    374    381    440    Ljava/lang/Exception;
        //  375    381    381    440    Ljava/lang/Exception;
        //  246    374    448    453    Any
        //  375    381    448    453    Any
        //  381    436    448    453    Any
        //  437    441    448    453    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object call(final Object arg, final Object cmd) {
        final Object arg2 = new Reference(arg);
        return $getCallSiteArray()[14].callCurrent(this, ((Reference<Object>)arg2).get(), cmd);
    }
    
    static {
        $const$4 = 2;
        $const$3 = 1;
        $const$2 = 4;
        $const$1 = 3;
        $const$0 = 5;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure1.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
