// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_decompile_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> source;
    private Reference<Object> phaseId;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public AstBrowser$_decompile_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> source, final Reference<Object> phaseId) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.source = source;
        this.phaseId = phaseId;
    }
    
    public Object doCall(final Object it) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: aload_2        
        //     5: ldc             0
        //     7: aaload         
        //     8: aload_2        
        //     9: ldc             1
        //    11: aaload         
        //    12: invokestatic    groovy/inspect/swingui/AstBrowser$_decompile_closure5.$get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter:()Ljava/lang/Class;
        //    15: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    20: aload_0         /* this */
        //    21: getfield        groovy/inspect/swingui/AstBrowser$_decompile_closure5.source:Lgroovy/lang/Reference;
        //    24: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    27: aload_0         /* this */
        //    28: getfield        groovy/inspect/swingui/AstBrowser$_decompile_closure5.phaseId:Lgroovy/lang/Reference;
        //    31: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    34: aload_2        
        //    35: ldc             2
        //    37: aaload         
        //    38: aload_0         /* this */
        //    39: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    44: aload_2        
        //    45: ldc             3
        //    47: aaload         
        //    48: aload_0         /* this */
        //    49: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    54: aload_2        
        //    55: ldc             4
        //    57: aaload         
        //    58: aload_0         /* this */
        //    59: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    64: invokestatic    org/codehaus/groovy/runtime/ArrayUtil.createArray:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;
        //    67: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    72: invokestatic    groovy/inspect/swingui/AstBrowser$_decompile_closure5.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    75: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    78: checkcast       Ljava/lang/String;
        //    81: new             Lgroovy/lang/Reference;
        //    84: dup_x1         
        //    85: swap           
        //    86: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //    89: astore_3        /* result */
        //    90: aload_2        
        //    91: ldc             5
        //    93: aaload         
        //    94: aload_2        
        //    95: ldc             6
        //    97: aaload         
        //    98: aload_0         /* this */
        //    99: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   104: new             Lgroovy/inspect/swingui/AstBrowser$_decompile_closure5_closure34;
        //   107: dup            
        //   108: aload_0         /* this */
        //   109: aload_0         /* this */
        //   110: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   113: aload_3         /* result */
        //   114: invokespecial   groovy/inspect/swingui/AstBrowser$_decompile_closure5_closure34.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   117: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   122: astore          4
        //   124: nop            
        //   125: nop            
        //   126: aload           4
        //   128: areturn        
        //   129: goto            186
        //   132: new             Lgroovy/lang/Reference;
        //   135: dup_x1         
        //   136: swap           
        //   137: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   140: astore_3        /* t */
        //   141: aload_2        
        //   142: ldc             7
        //   144: aaload         
        //   145: aload_2        
        //   146: ldc             8
        //   148: aaload         
        //   149: aload_0         /* this */
        //   150: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   155: new             Lgroovy/inspect/swingui/AstBrowser$_decompile_closure5_closure35;
        //   158: dup            
        //   159: aload_0         /* this */
        //   160: aload_0         /* this */
        //   161: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   164: aload_3         /* t */
        //   165: invokespecial   groovy/inspect/swingui/AstBrowser$_decompile_closure5_closure35.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   168: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   173: pop            
        //   174: aload_3         /* t */
        //   175: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   178: checkcast       Ljava/lang/Throwable;
        //   181: athrow         
        //   182: nop            
        //   183: goto            186
        //   186: nop            
        //   187: goto            193
        //   190: astore_3       
        //   191: aload_3        
        //   192: athrow         
        //   193: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      125    132    182    Ljava/lang/Throwable;
        //  126    132    132    182    Ljava/lang/Throwable;
        //  4      125    190    193    Any
        //  126    132    190    193    Any
        //  132    183    190    193    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    public Object getSource() {
        $getCallSiteArray();
        return this.source.get();
    }
    
    public Object getPhaseId() {
        $getCallSiteArray();
        return this.phaseId.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_decompile_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_decompile_closure5.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_decompile_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_decompile_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_decompile_closure5.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_decompile_closure5.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
