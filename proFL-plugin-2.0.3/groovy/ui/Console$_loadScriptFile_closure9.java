// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.io.File;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_loadScriptFile_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> file;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$io$File;
    
    public Console$_loadScriptFile_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> file) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.file = file;
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
        //    12: aload_0         /* this */
        //    13: getfield        groovy/ui/Console$_loadScriptFile_closure9.file:Lgroovy/lang/Reference;
        //    16: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    19: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    24: ldc             "\n"
        //    26: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    31: dup            
        //    32: invokestatic    groovy/ui/Console$_loadScriptFile_closure9.$get$$class$groovy$ui$Console$_loadScriptFile_closure9:()Ljava/lang/Class;
        //    35: aload_0         /* this */
        //    36: ldc             "consoleText"
        //    38: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //    41: pop            
        //    42: aload_0         /* this */
        //    43: getfield        groovy/ui/Console$_loadScriptFile_closure9.file:Lgroovy/lang/Reference;
        //    46: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //    49: dup            
        //    50: invokestatic    groovy/ui/Console$_loadScriptFile_closure9.$get$$class$groovy$ui$Console$_loadScriptFile_closure9:()Ljava/lang/Class;
        //    53: aload_0         /* this */
        //    54: ldc             "scriptFile"
        //    56: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //    59: pop            
        //    60: aload_2        
        //    61: ldc             2
        //    63: aaload         
        //    64: aload_2        
        //    65: ldc             3
        //    67: aaload         
        //    68: aload_0         /* this */
        //    69: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    74: new             Lgroovy/ui/Console$_loadScriptFile_closure9_closure27;
        //    77: dup            
        //    78: aload_0         /* this */
        //    79: aload_0         /* this */
        //    80: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    83: invokespecial   groovy/ui/Console$_loadScriptFile_closure9_closure27.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    86: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    91: astore_3       
        //    92: nop            
        //    93: aload_2        
        //    94: ldc             4
        //    96: aaload         
        //    97: aload_2        
        //    98: ldc             5
        //   100: aaload         
        //   101: aload_0         /* this */
        //   102: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   107: new             Lgroovy/ui/Console$_loadScriptFile_closure9_closure28;
        //   110: dup            
        //   111: aload_0         /* this */
        //   112: aload_0         /* this */
        //   113: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   116: invokespecial   groovy/ui/Console$_loadScriptFile_closure9_closure28.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   119: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   124: pop            
        //   125: aload_2        
        //   126: ldc             6
        //   128: aaload         
        //   129: aload_2        
        //   130: ldc             7
        //   132: aaload         
        //   133: aload_0         /* this */
        //   134: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   139: aload_2        
        //   140: ldc             8
        //   142: aaload         
        //   143: aload_0         /* this */
        //   144: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   149: ldc             "requestFocusInWindow"
        //   151: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   154: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   159: pop            
        //   160: aload_2        
        //   161: ldc             9
        //   163: aaload         
        //   164: aload_2        
        //   165: ldc             10
        //   167: aaload         
        //   168: aload_0         /* this */
        //   169: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   174: aload_2        
        //   175: ldc             11
        //   177: aaload         
        //   178: aload_0         /* this */
        //   179: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   184: ldc             "requestFocusInWindow"
        //   186: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   189: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   194: pop            
        //   195: nop            
        //   196: aload_3        
        //   197: areturn        
        //   198: goto            201
        //   201: aload_2        
        //   202: ldc             12
        //   204: aaload         
        //   205: aload_2        
        //   206: ldc             13
        //   208: aaload         
        //   209: aload_0         /* this */
        //   210: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   215: new             Lgroovy/ui/Console$_loadScriptFile_closure9_closure28;
        //   218: dup            
        //   219: aload_0         /* this */
        //   220: aload_0         /* this */
        //   221: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   224: invokespecial   groovy/ui/Console$_loadScriptFile_closure9_closure28.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   227: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   232: pop            
        //   233: aload_2        
        //   234: ldc             14
        //   236: aaload         
        //   237: aload_2        
        //   238: ldc             15
        //   240: aaload         
        //   241: aload_0         /* this */
        //   242: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   247: aload_2        
        //   248: ldc             16
        //   250: aaload         
        //   251: aload_0         /* this */
        //   252: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   257: ldc             "requestFocusInWindow"
        //   259: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   262: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   267: pop            
        //   268: aload_2        
        //   269: ldc             17
        //   271: aaload         
        //   272: aload_2        
        //   273: ldc             18
        //   275: aaload         
        //   276: aload_0         /* this */
        //   277: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   282: aload_2        
        //   283: ldc             19
        //   285: aaload         
        //   286: aload_0         /* this */
        //   287: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   292: ldc             "requestFocusInWindow"
        //   294: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   297: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   302: pop            
        //   303: nop            
        //   304: goto            412
        //   307: astore_3       
        //   308: aload_2        
        //   309: ldc             20
        //   311: aaload         
        //   312: aload_2        
        //   313: ldc             21
        //   315: aaload         
        //   316: aload_0         /* this */
        //   317: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   322: new             Lgroovy/ui/Console$_loadScriptFile_closure9_closure28;
        //   325: dup            
        //   326: aload_0         /* this */
        //   327: aload_0         /* this */
        //   328: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   331: invokespecial   groovy/ui/Console$_loadScriptFile_closure9_closure28.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   334: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   339: pop            
        //   340: aload_2        
        //   341: ldc             22
        //   343: aaload         
        //   344: aload_2        
        //   345: ldc             23
        //   347: aaload         
        //   348: aload_0         /* this */
        //   349: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   354: aload_2        
        //   355: ldc             24
        //   357: aaload         
        //   358: aload_0         /* this */
        //   359: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   364: ldc             "requestFocusInWindow"
        //   366: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   369: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   374: pop            
        //   375: aload_2        
        //   376: ldc             25
        //   378: aaload         
        //   379: aload_2        
        //   380: ldc             26
        //   382: aaload         
        //   383: aload_0         /* this */
        //   384: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   389: aload_2        
        //   390: ldc             27
        //   392: aaload         
        //   393: aload_0         /* this */
        //   394: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   399: ldc             "requestFocusInWindow"
        //   401: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getMethodPointer:(Ljava/lang/Object;Ljava/lang/String;)Lgroovy/lang/Closure;
        //   404: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   409: pop            
        //   410: aload_3        
        //   411: athrow         
        //   412: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      93     307    412    Any
        //  196    201    307    412    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public File getFile() {
        $getCallSiteArray();
        return (File)ScriptBytecodeAdapter.castToType(this.file.get(), $get$$class$java$io$File());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[28].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[29];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_loadScriptFile_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_loadScriptFile_closure9.$callSiteArray == null || ($createCallSiteArray = Console$_loadScriptFile_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_loadScriptFile_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_loadScriptFile_closure9.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_loadScriptFile_closure9.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Console$_loadScriptFile_closure9.$class$java$io$File) == null) {
            $class$java$io$File = (Console$_loadScriptFile_closure9.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
