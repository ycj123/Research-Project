// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Map;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.LookAndFeel;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class LookAndFeelHelper$_lookAndFeel_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> lafInstance;
    private Reference<Object> possibleAttributes;
    private Reference<Object> lafClassName;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$LookAndFeel;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public LookAndFeelHelper$_lookAndFeel_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> lafInstance, final Reference<Object> possibleAttributes, final Reference<Object> lafClassName) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.lafInstance = lafInstance;
        this.possibleAttributes = possibleAttributes;
        this.lafClassName = lafClassName;
    }
    
    public Object doCall(final Object k, final Object v) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lgroovy/lang/Reference;
        //     4: dup_x1         
        //     5: swap           
        //     6: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //     9: astore_3        /* k */
        //    10: aload_2         /* v */
        //    11: new             Lgroovy/lang/Reference;
        //    14: dup_x1         
        //    15: swap           
        //    16: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //    19: astore          v
        //    21: invokestatic    groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.$getCallSiteArray:()[Lorg/codehaus/groovy/runtime/callsite/CallSite;
        //    24: astore          5
        //    26: aload           5
        //    28: ldc             0
        //    30: aaload         
        //    31: aload_0         /* this */
        //    32: getfield        groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.possibleAttributes:Lgroovy/lang/Reference;
        //    35: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    38: aload_3         /* k */
        //    39: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    42: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    47: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    50: ifeq            100
        //    53: aload           5
        //    55: ldc             1
        //    57: aaload         
        //    58: aload           5
        //    60: ldc             2
        //    62: aaload         
        //    63: aload_0         /* this */
        //    64: getfield        groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.possibleAttributes:Lgroovy/lang/Reference;
        //    67: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    70: aload_3         /* k */
        //    71: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    74: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    79: aload_0         /* this */
        //    80: getfield        groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.lafInstance:Lgroovy/lang/Reference;
        //    83: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    86: aload           v
        //    88: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    91: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    96: areturn        
        //    97: goto            249
        //   100: aload           v
        //   102: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   105: dup            
        //   106: invokestatic    groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.$get$$class$groovy$swing$LookAndFeelHelper$_lookAndFeel_closure7:()Ljava/lang/Class;
        //   109: aload_0         /* this */
        //   110: getfield        groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.lafInstance:Lgroovy/lang/Reference;
        //   113: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   116: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   119: dup            
        //   120: iconst_1       
        //   121: anewarray       Ljava/lang/Object;
        //   124: dup            
        //   125: iconst_0       
        //   126: aload_3         /* k */
        //   127: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   130: aastore        
        //   131: iconst_2       
        //   132: anewarray       Ljava/lang/String;
        //   135: dup            
        //   136: iconst_0       
        //   137: ldc             ""
        //   139: aastore        
        //   140: dup            
        //   141: iconst_1       
        //   142: ldc             ""
        //   144: aastore        
        //   145: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   148: invokestatic    groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.$get$$class$java$lang$String:()Ljava/lang/Class;
        //   151: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   154: checkcast       Ljava/lang/String;
        //   157: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setProperty:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V
        //   160: astore          6
        //   162: nop            
        //   163: nop            
        //   164: aload           6
        //   166: areturn        
        //   167: goto            240
        //   170: astore          mpe
        //   172: aload           5
        //   174: ldc             3
        //   176: aaload         
        //   177: invokestatic    groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.$get$$class$java$lang$RuntimeException:()Ljava/lang/Class;
        //   180: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   183: dup            
        //   184: iconst_2       
        //   185: anewarray       Ljava/lang/Object;
        //   188: dup            
        //   189: iconst_0       
        //   190: aload_0         /* this */
        //   191: getfield        groovy/swing/LookAndFeelHelper$_lookAndFeel_closure7.lafClassName:Lgroovy/lang/Reference;
        //   194: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   197: aastore        
        //   198: dup            
        //   199: iconst_1       
        //   200: aload_3         /* k */
        //   201: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   204: aastore        
        //   205: iconst_3       
        //   206: anewarray       Ljava/lang/String;
        //   209: dup            
        //   210: iconst_0       
        //   211: ldc             "SwingBuilder initialization for the Look and Feel Class "
        //   213: aastore        
        //   214: dup            
        //   215: iconst_1       
        //   216: ldc             " does accept the attribute "
        //   218: aastore        
        //   219: dup            
        //   220: iconst_2       
        //   221: ldc             ""
        //   223: aastore        
        //   224: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   227: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   232: checkcast       Ljava/lang/Throwable;
        //   235: athrow         
        //   236: nop            
        //   237: goto            240
        //   240: nop            
        //   241: goto            249
        //   244: astore          6
        //   246: aload           6
        //   248: athrow         
        //   249: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  100    163    170    236    Lgroovy/lang/MissingPropertyException;
        //  164    170    170    236    Lgroovy/lang/MissingPropertyException;
        //  100    163    244    249    Any
        //  164    170    244    249    Any
        //  170    237    244    249    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object call(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public LookAndFeel getLafInstance() {
        $getCallSiteArray();
        return (LookAndFeel)ScriptBytecodeAdapter.castToType(this.lafInstance.get(), $get$$class$javax$swing$LookAndFeel());
    }
    
    public Map getPossibleAttributes() {
        $getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(this.possibleAttributes.get(), $get$$class$java$util$Map());
    }
    
    public String getLafClassName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.lafClassName.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$LookAndFeelHelper$_lookAndFeel_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LookAndFeelHelper$_lookAndFeel_closure7.$callSiteArray == null || ($createCallSiteArray = LookAndFeelHelper$_lookAndFeel_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LookAndFeelHelper$_lookAndFeel_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$LookAndFeel() {
        Class $class$javax$swing$LookAndFeel;
        if (($class$javax$swing$LookAndFeel = LookAndFeelHelper$_lookAndFeel_closure7.$class$javax$swing$LookAndFeel) == null) {
            $class$javax$swing$LookAndFeel = (LookAndFeelHelper$_lookAndFeel_closure7.$class$javax$swing$LookAndFeel = class$("javax.swing.LookAndFeel"));
        }
        return $class$javax$swing$LookAndFeel;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = LookAndFeelHelper$_lookAndFeel_closure7.$class$java$lang$String) == null) {
            $class$java$lang$String = (LookAndFeelHelper$_lookAndFeel_closure7.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = LookAndFeelHelper$_lookAndFeel_closure7.$class$java$util$Map) == null) {
            $class$java$util$Map = (LookAndFeelHelper$_lookAndFeel_closure7.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
