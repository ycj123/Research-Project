// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_compile_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> model;
    private Reference<Object> compilePhase;
    private Reference<Object> script;
    private Reference<Object> jTree;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public AstBrowser$_compile_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> model, final Reference<Object> compilePhase, final Reference<Object> script, final Reference<Object> jTree) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.model = model;
        this.compilePhase = compilePhase;
        this.script = script;
        this.jTree = jTree;
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
        //     8: invokestatic    groovy/inspect/swingui/AstBrowser$_compile_closure7.$get$$class$groovy$inspect$swingui$SwingTreeNodeMaker:()Ljava/lang/Class;
        //    11: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;)Ljava/lang/Object;
        //    16: astore_3        /* nodeMaker */
        //    17: aload_2        
        //    18: ldc             1
        //    20: aaload         
        //    21: invokestatic    groovy/inspect/swingui/AstBrowser$_compile_closure7.$get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter:()Ljava/lang/Class;
        //    24: aload_2        
        //    25: ldc             2
        //    27: aaload         
        //    28: aload_0         /* this */
        //    29: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    34: aload_2        
        //    35: ldc             3
        //    37: aaload         
        //    38: aload_0         /* this */
        //    39: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    44: aload_2        
        //    45: ldc             4
        //    47: aaload         
        //    48: aload_0         /* this */
        //    49: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    54: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    59: astore          adapter
        //    61: aload_2        
        //    62: ldc             5
        //    64: aaload         
        //    65: aload           adapter
        //    67: aload_0         /* this */
        //    68: getfield        groovy/inspect/swingui/AstBrowser$_compile_closure7.script:Lgroovy/lang/Reference;
        //    71: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    74: aload_0         /* this */
        //    75: getfield        groovy/inspect/swingui/AstBrowser$_compile_closure7.compilePhase:Lgroovy/lang/Reference;
        //    78: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    81: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    86: new             Lgroovy/lang/Reference;
        //    89: dup_x1         
        //    90: swap           
        //    91: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //    94: astore          result
        //    96: aload_2        
        //    97: ldc             6
        //    99: aaload         
        //   100: aload_2        
        //   101: ldc             7
        //   103: aaload         
        //   104: aload_0         /* this */
        //   105: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   110: new             Lgroovy/inspect/swingui/AstBrowser$_compile_closure7_closure36;
        //   113: dup            
        //   114: aload_0         /* this */
        //   115: aload_0         /* this */
        //   116: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   119: aload           result
        //   121: aload_0         /* this */
        //   122: getfield        groovy/inspect/swingui/AstBrowser$_compile_closure7.model:Lgroovy/lang/Reference;
        //   125: astore          model
        //   127: aload           model
        //   129: aload_0         /* this */
        //   130: getfield        groovy/inspect/swingui/AstBrowser$_compile_closure7.jTree:Lgroovy/lang/Reference;
        //   133: astore          jTree
        //   135: aload           jTree
        //   137: invokespecial   groovy/inspect/swingui/AstBrowser$_compile_closure7_closure36.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;Lgroovy/lang/Reference;Lgroovy/lang/Reference;)V
        //   140: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   145: astore          8
        //   147: nop            
        //   148: nop            
        //   149: aload           8
        //   151: areturn        
        //   152: goto            216
        //   155: new             Lgroovy/lang/Reference;
        //   158: dup_x1         
        //   159: swap           
        //   160: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   163: astore_3        /* t */
        //   164: aload_2        
        //   165: ldc             8
        //   167: aaload         
        //   168: aload_2        
        //   169: ldc             9
        //   171: aaload         
        //   172: aload_0         /* this */
        //   173: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   178: new             Lgroovy/inspect/swingui/AstBrowser$_compile_closure7_closure37;
        //   181: dup            
        //   182: aload_0         /* this */
        //   183: aload_0         /* this */
        //   184: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   187: aload_0         /* this */
        //   188: getfield        groovy/inspect/swingui/AstBrowser$_compile_closure7.jTree:Lgroovy/lang/Reference;
        //   191: astore          jTree
        //   193: aload           jTree
        //   195: invokespecial   groovy/inspect/swingui/AstBrowser$_compile_closure7_closure37.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   198: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   203: pop            
        //   204: aload_3         /* t */
        //   205: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   208: checkcast       Ljava/lang/Throwable;
        //   211: athrow         
        //   212: nop            
        //   213: goto            216
        //   216: nop            
        //   217: goto            223
        //   220: astore_3       
        //   221: aload_3        
        //   222: athrow         
        //   223: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      148    155    212    Ljava/lang/Throwable;
        //  149    155    155    212    Ljava/lang/Throwable;
        //  4      148    220    223    Any
        //  149    155    220    223    Any
        //  155    213    220    223    Any
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
    
    public Object getModel() {
        $getCallSiteArray();
        return this.model.get();
    }
    
    public int getCompilePhase() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.compilePhase.get(), $get$$class$java$lang$Integer()));
    }
    
    public String getScript() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.script.get(), $get$$class$java$lang$String());
    }
    
    public Object getjTree() {
        $getCallSiteArray();
        return this.jTree.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[10].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_compile_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_compile_closure7.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_compile_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_compile_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstBrowser$_compile_closure7.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstBrowser$_compile_closure7.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_compile_closure7.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_compile_closure7.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstBrowser$_compile_closure7.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstBrowser$_compile_closure7.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
