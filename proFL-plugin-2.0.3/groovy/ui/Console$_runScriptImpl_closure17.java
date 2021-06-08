// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

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

class Console$_runScriptImpl_closure17 extends Closure implements GeneratedClosure
{
    private Reference<Object> record;
    private Reference<Object> selected;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    
    public Console$_runScriptImpl_closure17(final Object _outerInstance, final Object _thisObject, final Reference<Object> record, final Reference<Object> selected) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.record = record;
        this.selected = selected;
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
        //     8: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //    11: new             Lgroovy/ui/Console$_runScriptImpl_closure17_closure31;
        //    14: dup            
        //    15: aload_0         /* this */
        //    16: aload_0         /* this */
        //    17: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //    20: invokespecial   groovy/ui/Console$_runScriptImpl_closure17_closure31.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
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
        //    43: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetPropertySafe:(Ljava/lang/Object;)Ljava/lang/Object;
        //    48: dup            
        //    49: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    52: ifeq            58
        //    55: goto            120
        //    58: pop            
        //    59: aload_2        
        //    60: ldc             3
        //    62: aaload         
        //    63: aload_2        
        //    64: ldc             4
        //    66: aaload         
        //    67: aload_0         /* this */
        //    68: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    73: aload_2        
        //    74: ldc             5
        //    76: aaload         
        //    77: aload_0         /* this */
        //    78: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    83: astore_3       
        //    84: aload_2        
        //    85: ldc             6
        //    87: aaload         
        //    88: aload_2        
        //    89: ldc             7
        //    91: aaload         
        //    92: aload_0         /* this */
        //    93: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    98: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   103: dup            
        //   104: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   107: aload_0         /* this */
        //   108: ldc             "scriptNameCounter"
        //   110: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   113: pop            
        //   114: aload_3        
        //   115: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   120: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$java$lang$String:()Ljava/lang/Class;
        //   123: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   126: checkcast       Ljava/lang/String;
        //   129: astore          name
        //   131: aload_2        
        //   132: ldc             8
        //   134: aaload         
        //   135: aload_0         /* this */
        //   136: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   141: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   144: ifeq            161
        //   147: aload_2        
        //   148: ldc             9
        //   150: aaload         
        //   151: aload_0         /* this */
        //   152: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;)Ljava/lang/Object;
        //   157: pop            
        //   158: goto            161
        //   161: aload_2        
        //   162: ldc             10
        //   164: aaload         
        //   165: aload_2        
        //   166: ldc             11
        //   168: aaload         
        //   169: aload_0         /* this */
        //   170: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   175: aload_2        
        //   176: ldc             12
        //   178: aaload         
        //   179: aload_0         /* this */
        //   180: getfield        groovy/ui/Console$_runScriptImpl_closure17.record:Lgroovy/lang/Reference;
        //   183: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   186: aload_0         /* this */
        //   187: getfield        groovy/ui/Console$_runScriptImpl_closure17.selected:Lgroovy/lang/Reference;
        //   190: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   193: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   198: aload           name
        //   200: iconst_0       
        //   201: anewarray       Ljava/lang/Object;
        //   204: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createList:([Ljava/lang/Object;)Ljava/util/List;
        //   207: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   212: new             Lgroovy/lang/Reference;
        //   215: dup_x1         
        //   216: swap           
        //   217: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   220: astore_3        /* result */
        //   221: aload_2        
        //   222: ldc             13
        //   224: aaload         
        //   225: aload_0         /* this */
        //   226: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   231: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   234: ifeq            251
        //   237: aload_2        
        //   238: ldc             14
        //   240: aaload         
        //   241: aload_0         /* this */
        //   242: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;)Ljava/lang/Object;
        //   247: pop            
        //   248: goto            251
        //   251: aload_2        
        //   252: ldc             15
        //   254: aaload         
        //   255: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //   258: new             Lgroovy/ui/Console$_runScriptImpl_closure17_closure32;
        //   261: dup            
        //   262: aload_0         /* this */
        //   263: aload_0         /* this */
        //   264: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   267: aload_3         /* result */
        //   268: invokespecial   groovy/ui/Console$_runScriptImpl_closure17_closure32.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   271: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   276: astore          5
        //   278: nop            
        //   279: aconst_null    
        //   280: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   283: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   286: checkcast       Ljava/lang/Thread;
        //   289: dup            
        //   290: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   293: aload_0         /* this */
        //   294: ldc             "runThread"
        //   296: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   299: pop            
        //   300: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   303: dup            
        //   304: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   307: aload_0         /* this */
        //   308: ldc             "scriptRunning"
        //   310: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   313: pop            
        //   314: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   317: dup            
        //   318: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   321: aload_2        
        //   322: ldc             16
        //   324: aaload         
        //   325: aload_0         /* this */
        //   326: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   331: ldc             "enabled"
        //   333: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setProperty:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V
        //   336: pop            
        //   337: nop            
        //   338: aload           5
        //   340: areturn        
        //   341: goto            486
        //   344: new             Lgroovy/lang/Reference;
        //   347: dup_x1         
        //   348: swap           
        //   349: invokespecial   groovy/lang/Reference.<init>:(Ljava/lang/Object;)V
        //   352: astore          t
        //   354: aload           t
        //   356: invokevirtual   groovy/lang/Reference.get:()Ljava/lang/Object;
        //   359: instanceof      Ljava/lang/StackOverflowError;
        //   362: ifeq            393
        //   365: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   368: dup            
        //   369: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   372: aload_0         /* this */
        //   373: ldc             "stackOverFlowError"
        //   375: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   378: pop            
        //   379: aload_2        
        //   380: ldc             17
        //   382: aaload         
        //   383: aload_0         /* this */
        //   384: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;)Ljava/lang/Object;
        //   389: pop            
        //   390: goto            393
        //   393: aload_2        
        //   394: ldc             18
        //   396: aaload         
        //   397: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$javax$swing$SwingUtilities:()Ljava/lang/Class;
        //   400: new             Lgroovy/ui/Console$_runScriptImpl_closure17_closure33;
        //   403: dup            
        //   404: aload_0         /* this */
        //   405: aload_0         /* this */
        //   406: invokevirtual   groovy/lang/Closure.getThisObject:()Ljava/lang/Object;
        //   409: aload           t
        //   411: invokespecial   groovy/ui/Console$_runScriptImpl_closure17_closure33.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lgroovy/lang/Reference;)V
        //   414: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   419: astore_3       
        //   420: nop            
        //   421: aconst_null    
        //   422: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   425: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   428: checkcast       Ljava/lang/Thread;
        //   431: dup            
        //   432: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   435: aload_0         /* this */
        //   436: ldc             "runThread"
        //   438: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   441: pop            
        //   442: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   445: dup            
        //   446: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   449: aload_0         /* this */
        //   450: ldc             "scriptRunning"
        //   452: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   455: pop            
        //   456: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   459: dup            
        //   460: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   463: aload_2        
        //   464: ldc             19
        //   466: aaload         
        //   467: aload_0         /* this */
        //   468: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   473: ldc             "enabled"
        //   475: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setProperty:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V
        //   478: pop            
        //   479: nop            
        //   480: aload_3        
        //   481: areturn        
        //   482: nop            
        //   483: goto            486
        //   486: aconst_null    
        //   487: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   490: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   493: checkcast       Ljava/lang/Thread;
        //   496: dup            
        //   497: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   500: aload_0         /* this */
        //   501: ldc             "runThread"
        //   503: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   506: pop            
        //   507: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   510: dup            
        //   511: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   514: aload_0         /* this */
        //   515: ldc             "scriptRunning"
        //   517: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   520: pop            
        //   521: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   524: dup            
        //   525: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   528: aload_2        
        //   529: ldc             20
        //   531: aaload         
        //   532: aload_0         /* this */
        //   533: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   538: ldc             "enabled"
        //   540: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setProperty:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V
        //   543: pop            
        //   544: nop            
        //   545: goto            611
        //   548: astore          4
        //   550: aconst_null    
        //   551: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$java$lang$Thread:()Ljava/lang/Class;
        //   554: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   557: checkcast       Ljava/lang/Thread;
        //   560: dup            
        //   561: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   564: aload_0         /* this */
        //   565: ldc             "runThread"
        //   567: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   570: pop            
        //   571: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   574: dup            
        //   575: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   578: aload_0         /* this */
        //   579: ldc             "scriptRunning"
        //   581: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setGroovyObjectProperty:(Ljava/lang/Object;Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)V
        //   584: pop            
        //   585: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   588: dup            
        //   589: invokestatic    groovy/ui/Console$_runScriptImpl_closure17.$get$$class$groovy$ui$Console$_runScriptImpl_closure17:()Ljava/lang/Class;
        //   592: aload_2        
        //   593: ldc             21
        //   595: aaload         
        //   596: aload_0         /* this */
        //   597: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   602: ldc             "enabled"
        //   604: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.setProperty:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V
        //   607: pop            
        //   608: aload           4
        //   610: athrow         
        //   611: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      279    344    482    Ljava/lang/Throwable;
        //  338    344    344    482    Ljava/lang/Throwable;
        //  4      279    548    611    Any
        //  338    344    548    611    Any
        //  344    421    548    611    Any
        //  480    483    548    611    Any
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
    
    public boolean getSelected() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(this.selected.get(), $get$$class$java$lang$Boolean()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[22].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_runScriptImpl_closure17(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_runScriptImpl_closure17.$callSiteArray == null || ($createCallSiteArray = Console$_runScriptImpl_closure17.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_runScriptImpl_closure17.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Console$_runScriptImpl_closure17.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Console$_runScriptImpl_closure17.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Console$_runScriptImpl_closure17.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Console$_runScriptImpl_closure17.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
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
