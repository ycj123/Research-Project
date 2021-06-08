// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.management.NotificationFilterSupport;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxEmitterFactory$_newInstance_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> server;
    private Reference<Object> emitter;
    private Reference<Object> filter;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$NotificationFilterSupport;
    
    public JmxEmitterFactory$_newInstance_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> server, final Reference<Object> emitter, final Reference<Object> filter) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.server = server;
        this.emitter = emitter;
        this.filter = filter;
    }
    
    public Object doCall(final Object l) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lgroovy/lang/Reference;
        //     4: dup_x1         
        //     5: swap           
        //     6: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //     9: astore_2        /* l */
        //    10: invokestatic    groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.$getCallSiteArray:()[Lorg/codehaus/groovy/runtime/callsite/CallSite;
        //    13: astore_3       
        //    14: aload_2         /* l */
        //    15: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    18: new             Lgroovy/lang/Reference;
        //    21: dup_x1         
        //    22: swap           
        //    23: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //    26: astore          listener
        //    28: aload           listener
        //    30: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    33: instanceof      Ljava/lang/String;
        //    36: ifeq            66
        //    39: aload_3        
        //    40: ldc             0
        //    42: aaload         
        //    43: invokestatic    groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.$get$$class$javax$management$ObjectName:()Ljava/lang/Class;
        //    46: aload_2         /* l */
        //    47: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    50: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    55: dup            
        //    56: aload           listener
        //    58: swap           
        //    59: invokevirtual   groovy/lang/Reference.set:(Ljava/lang/Object;)V
        //    62: pop            
        //    63: goto            66
        //    66: aload           listener
        //    68: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    71: instanceof      Ljavax/management/ObjectName;
        //    74: ifeq            123
        //    77: aload_3        
        //    78: ldc             1
        //    80: aaload         
        //    81: aload_0         /* this */
        //    82: getfield        groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.server:Lgroovy/lang/Reference;
        //    85: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    88: aload_0         /* this */
        //    89: getfield        groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.name:Lgroovy/lang/Reference;
        //    92: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    95: aload           listener
        //    97: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   100: aload_0         /* this */
        //   101: getfield        groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.filter:Lgroovy/lang/Reference;
        //   104: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   107: aconst_null    
        //   108: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   113: astore          5
        //   115: nop            
        //   116: nop            
        //   117: aload           5
        //   119: areturn        
        //   120: goto            159
        //   123: aload_3        
        //   124: ldc             2
        //   126: aaload         
        //   127: aload_0         /* this */
        //   128: getfield        groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.emitter:Lgroovy/lang/Reference;
        //   131: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   134: aload           listener
        //   136: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   139: aload_0         /* this */
        //   140: getfield        groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.filter:Lgroovy/lang/Reference;
        //   143: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   146: aconst_null    
        //   147: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   152: astore          5
        //   154: nop            
        //   155: nop            
        //   156: aload           5
        //   158: areturn        
        //   159: goto            197
        //   162: new             Lgroovy/lang/Reference;
        //   165: dup_x1         
        //   166: swap           
        //   167: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   170: astore          e
        //   172: aload_3        
        //   173: ldc             3
        //   175: aaload         
        //   176: invokestatic    groovy/jmx/builder/JmxEmitterFactory$_newInstance_closure1.$get$$class$groovy$jmx$builder$JmxBuilderException:()Ljava/lang/Class;
        //   179: aload           e
        //   181: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   184: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   189: checkcast       Ljava/lang/Throwable;
        //   192: athrow         
        //   193: nop            
        //   194: goto            197
        //   197: nop            
        //   198: goto            206
        //   201: astore          5
        //   203: aload           5
        //   205: athrow         
        //   206: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  28     116    162    193    Ljava/lang/Exception;
        //  117    155    162    193    Ljava/lang/Exception;
        //  156    162    162    193    Ljava/lang/Exception;
        //  28     116    201    206    Any
        //  117    155    201    206    Any
        //  156    162    201    206    Any
        //  162    194    201    206    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object getName() {
        $getCallSiteArray();
        return this.name.get();
    }
    
    public Object getServer() {
        $getCallSiteArray();
        return this.server.get();
    }
    
    public Object getEmitter() {
        $getCallSiteArray();
        return this.emitter.get();
    }
    
    public NotificationFilterSupport getFilter() {
        $getCallSiteArray();
        return (NotificationFilterSupport)ScriptBytecodeAdapter.castToType(this.filter.get(), $get$$class$javax$management$NotificationFilterSupport());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxEmitterFactory$_newInstance_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxEmitterFactory$_newInstance_closure1.$callSiteArray == null || ($createCallSiteArray = JmxEmitterFactory$_newInstance_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxEmitterFactory$_newInstance_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$NotificationFilterSupport() {
        Class $class$javax$management$NotificationFilterSupport;
        if (($class$javax$management$NotificationFilterSupport = JmxEmitterFactory$_newInstance_closure1.$class$javax$management$NotificationFilterSupport) == null) {
            $class$javax$management$NotificationFilterSupport = (JmxEmitterFactory$_newInstance_closure1.$class$javax$management$NotificationFilterSupport = class$("javax.management.NotificationFilterSupport"));
        }
        return $class$javax$management$NotificationFilterSupport;
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
