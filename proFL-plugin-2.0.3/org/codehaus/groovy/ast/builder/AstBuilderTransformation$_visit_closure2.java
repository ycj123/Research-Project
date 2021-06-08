// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.ast.ClassNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBuilderTransformation$_visit_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> transformer;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AstBuilderTransformation$_visit_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> transformer) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.transformer = transformer;
    }
    
    public Object doCall(final ClassNode classNode) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lgroovy/lang/Reference;
        //     4: dup_x1         
        //     5: swap           
        //     6: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //     9: astore_2        /* classNode */
        //    10: invokestatic    org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2.$getCallSiteArray:()[Lorg/codehaus/groovy/runtime/callsite/CallSite;
        //    13: astore_3       
        //    14: aload_3        
        //    15: ldc             0
        //    17: aaload         
        //    18: aload_3        
        //    19: ldc             1
        //    21: aaload         
        //    22: aload_2         /* classNode */
        //    23: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    26: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    31: new             Lorg/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure4;
        //    34: dup            
        //    35: aload_0         /* this */
        //    36: aload_0         /* this */
        //    37: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    40: aload_0         /* this */
        //    41: getfield        org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2.transformer:Lgroovy/lang/Reference;
        //    44: astore          transformer
        //    46: aload           transformer
        //    48: invokespecial   org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure4.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //    51: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    56: pop            
        //    57: aload_3        
        //    58: ldc             2
        //    60: aaload         
        //    61: aload_3        
        //    62: ldc             3
        //    64: aaload         
        //    65: aload_2         /* classNode */
        //    66: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    69: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    74: new             Lorg/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure5;
        //    77: dup            
        //    78: aload_0         /* this */
        //    79: aload_0         /* this */
        //    80: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    83: aload           transformer
        //    85: invokespecial   org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure5.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //    88: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    93: pop            
        //    94: goto            103
        //    97: astore          ignored
        //    99: nop            
        //   100: goto            103
        //   103: nop            
        //   104: goto            112
        //   107: astore          5
        //   109: aload           5
        //   111: athrow         
        //   112: aload_3        
        //   113: ldc             4
        //   115: aaload         
        //   116: aload_3        
        //   117: ldc             5
        //   119: aaload         
        //   120: aload_2         /* classNode */
        //   121: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   124: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   129: new             Lorg/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure6;
        //   132: dup            
        //   133: aload_0         /* this */
        //   134: aload_0         /* this */
        //   135: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   138: aload           transformer
        //   140: invokespecial   org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure6.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   143: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   148: pop            
        //   149: aload_3        
        //   150: ldc             6
        //   152: aaload         
        //   153: aload_3        
        //   154: ldc             7
        //   156: aaload         
        //   157: aload_2         /* classNode */
        //   158: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   161: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   166: new             Lorg/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure7;
        //   169: dup            
        //   170: aload_0         /* this */
        //   171: aload_0         /* this */
        //   172: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   175: aload           transformer
        //   177: invokespecial   org/codehaus/groovy/ast/builder/AstBuilderTransformation$_visit_closure2_closure7.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   180: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   185: astore          6
        //   187: nop            
        //   188: nop            
        //   189: aload           6
        //   191: areturn        
        //   192: goto            209
        //   195: astore          ignored
        //   197: aconst_null    
        //   198: astore          7
        //   200: nop            
        //   201: nop            
        //   202: aload           7
        //   204: areturn        
        //   205: nop            
        //   206: goto            209
        //   209: nop            
        //   210: goto            218
        //   213: astore          6
        //   215: aload           6
        //   217: athrow         
        //   218: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  57     97     97     103    Lgroovy/lang/MissingPropertyException;
        //  57     97     107    112    Any
        //  97     100    107    112    Any
        //  149    188    195    205    Lgroovy/lang/MissingPropertyException;
        //  189    195    195    205    Lgroovy/lang/MissingPropertyException;
        //  149    188    213    218    Any
        //  189    195    213    218    Any
        //  195    201    213    218    Any
        //  202    206    213    218    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object call(final ClassNode classNode) {
        final ClassNode classNode2 = (ClassNode)new Reference(classNode);
        return $getCallSiteArray()[8].callCurrent(this, ((Reference<Object>)classNode2).get());
    }
    
    public Object getTransformer() {
        $getCallSiteArray();
        return this.transformer.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderTransformation$_visit_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderTransformation$_visit_closure2.$callSiteArray == null || ($createCallSiteArray = AstBuilderTransformation$_visit_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderTransformation$_visit_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
