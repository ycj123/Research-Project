// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

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

class Console$_compileScript_closure18 extends Closure implements GeneratedClosure
{
    private Reference<Object> record;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public Console$_compileScript_closure18(final Object _outerInstance, final Object _thisObject, final Reference<Object> record) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.record = record;
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
        //     8: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //    11: new             Lgroovy/ui/Console$_compileScript_closure18_closure34;
        //    14: dup            
        //    15: aload_0         /* this */
        //    16: aload_0         /* this */
        //    17: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    20: invokespecial   groovy/ui/Console$_compileScript_closure18_closure34.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    23: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    28: pop            
        //    29: aload_2        
        //    30: ldc             1
        //    32: aaload         
        //    33: aload_2        
        //    34: ldc             2
        //    36: aaload         
        //    37: aload_0         /* this */
        //    38: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    43: aload_2        
        //    44: ldc             3
        //    46: aaload         
        //    47: aload_0         /* this */
        //    48: getfield        groovy/ui/Console$_compileScript_closure18.record:Lgroovy/lang/Reference;
        //    51: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    54: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    59: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    64: pop            
        //    65: aload_2        
        //    66: ldc             4
        //    68: aaload         
        //    69: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //    72: new             Lgroovy/ui/Console$_compileScript_closure18_closure35;
        //    75: dup            
        //    76: aload_0         /* this */
        //    77: aload_0         /* this */
        //    78: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    81: invokespecial   groovy/ui/Console$_compileScript_closure18_closure35.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    84: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    89: astore_3       
        //    90: nop            
        //    91: aconst_null    
        //    92: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //    95: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    98: checkcast       Ljava/lang/Thread;
        //   101: dup            
        //   102: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$groovy$ui$Console$_compileScript_closure18:()Ljava/lang/Class;
        //   105: aload_0         /* this */
        //   106: ldc             "runThread"
        //   108: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   111: pop            
        //   112: nop            
        //   113: aload_3        
        //   114: areturn        
        //   115: goto            184
        //   118: new             Lgroovy/lang/Reference;
        //   121: dup_x1         
        //   122: swap           
        //   123: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   126: astore_3       
        //   127: aload_2        
        //   128: ldc             5
        //   130: aaload         
        //   131: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //   134: new             Lgroovy/ui/Console$_compileScript_closure18_closure36;
        //   137: dup            
        //   138: aload_0         /* this */
        //   139: aload_0         /* this */
        //   140: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   143: aload_3        
        //   144: invokespecial   groovy/ui/Console$_compileScript_closure18_closure36.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   147: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   152: astore          4
        //   154: nop            
        //   155: aconst_null    
        //   156: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   159: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   162: checkcast       Ljava/lang/Thread;
        //   165: dup            
        //   166: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$groovy$ui$Console$_compileScript_closure18:()Ljava/lang/Class;
        //   169: aload_0         /* this */
        //   170: ldc             "runThread"
        //   172: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   175: pop            
        //   176: nop            
        //   177: aload           4
        //   179: areturn        
        //   180: nop            
        //   181: goto            184
        //   184: aconst_null    
        //   185: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   188: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   191: checkcast       Ljava/lang/Thread;
        //   194: dup            
        //   195: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$groovy$ui$Console$_compileScript_closure18:()Ljava/lang/Class;
        //   198: aload_0         /* this */
        //   199: ldc             "runThread"
        //   201: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   204: pop            
        //   205: nop            
        //   206: goto            233
        //   209: astore_3       
        //   210: aconst_null    
        //   211: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   214: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   217: checkcast       Ljava/lang/Thread;
        //   220: dup            
        //   221: invokestatic    groovy/ui/Console$_compileScript_closure18.$get$$class$groovy$ui$Console$_compileScript_closure18:()Ljava/lang/Class;
        //   224: aload_0         /* this */
        //   225: ldc             "runThread"
        //   227: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   230: pop            
        //   231: aload_3        
        //   232: athrow         
        //   233: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      91     118    180    Ljava/lang/Throwable;
        //  113    118    118    180    Ljava/lang/Throwable;
        //  4      91     209    233    Any
        //  113    118    209    233    Any
        //  118    155    209    233    Any
        //  177    181    209    233    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object getRecord() {
        $getCallSiteArray();
        return this.record.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_compileScript_closure18(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_compileScript_closure18.$callSiteArray == null || ($createCallSiteArray = Console$_compileScript_closure18.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_compileScript_closure18.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_compileScript_closure18.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_compileScript_closure18.$class$java$lang$Object = class$("java.lang.Object"));
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
