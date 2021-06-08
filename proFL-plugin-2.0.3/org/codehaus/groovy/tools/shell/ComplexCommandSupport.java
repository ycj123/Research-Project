// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;

public abstract class ComplexCommandSupport extends CommandSupport
{
    protected List functions;
    protected String defaultFunction;
    private Object do_all;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204731;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    
    public ComplexCommandSupport(final Shell shell, final String name, final String shortcut) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = name;
        arguments[2] = shortcut;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.do_all = new ComplexCommandSupport$_closure1(this, this);
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
        final Object c = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor()));
        $getCallSiteArray[1].call(this.functions, new ComplexCommandSupport$_createCompletors_closure2(this, this, (Reference<Object>)c));
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { ((Reference<Object>)c).get(), null }), $get$$class$java$util$List());
    }
    
    public Object execute(List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(args, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert args != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[2].call(args), ComplexCommandSupport.$const$0)) {
            if (DefaultTypeTransformation.booleanUnbox(this.defaultFunction)) {
                args = ScriptBytecodeAdapter.createList(new Object[] { this.defaultFunction });
            }
            else {
                $getCallSiteArray[3].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[4].callGroovyObjectGetProperty(this) }, new String[] { "Command '", "' requires at least one argument" }));
            }
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callCurrent(this, args), $get$$class$java$lang$Object());
    }
    
    protected Object executeFunction(List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(args, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert args != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder2.record(recorder2.record(recorder2.record(this.functions, -1), -1), 8))) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert functions", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        final Object fname = $getCallSiteArray[6].call(args, ComplexCommandSupport.$const$0);
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[7].call(args), ComplexCommandSupport.$const$1)) {
            args = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(args, ScriptBytecodeAdapter.createRange(ComplexCommandSupport.$const$1, ComplexCommandSupport.$const$2, true)), $get$$class$java$util$List());
        }
        else {
            args = ScriptBytecodeAdapter.createList(new Object[0]);
        }
        if (ScriptBytecodeAdapter.isCase(fname, this.functions)) {
            final Object func = $getCallSiteArray[9].callCurrent(this, fname);
            $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { fname, args }, new String[] { "Invoking function '", "' w/args: ", "" }));
            return $getCallSiteArray[12].call(func, args);
        }
        return $getCallSiteArray[13].callCurrent(this, new GStringImpl(new Object[] { fname }, new String[] { "Unknown function name: ", "" }));
    }
    
    protected Closure loadFunction(final String name) {
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
        //    12: aload_1         /* name */
        //    13: aload_3        
        //    14: swap           
        //    15: ldc             8
        //    17: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.record:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    20: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //    23: ifeq            33
        //    26: aload_3        
        //    27: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    30: goto            52
        //    33: ldc_w           "assert name"
        //    36: aload_3        
        //    37: invokestatic    org/codehaus/groovy/transform/powerassert/AssertionRenderer.render:(Ljava/lang/String;Lorg/codehaus/groovy/transform/powerassert/ValueRecorder;)Ljava/lang/String;
        //    40: aconst_null    
        //    41: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.assertFailed:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    44: goto            52
        //    47: aload_3        
        //    48: invokevirtual   org/codehaus/groovy/transform/powerassert/ValueRecorder.clear:()V
        //    51: athrow         
        //    52: invokestatic    org/codehaus/groovy/tools/shell/ComplexCommandSupport.$get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport:()Ljava/lang/Class;
        //    55: aload_0         /* this */
        //    56: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //    59: dup            
        //    60: iconst_1       
        //    61: anewarray       Ljava/lang/Object;
        //    64: dup            
        //    65: iconst_0       
        //    66: aload_1         /* name */
        //    67: aastore        
        //    68: iconst_2       
        //    69: anewarray       Ljava/lang/String;
        //    72: dup            
        //    73: iconst_0       
        //    74: ldc_w           "do_"
        //    77: aastore        
        //    78: dup            
        //    79: iconst_1       
        //    80: ldc             ""
        //    82: aastore        
        //    83: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //    86: invokestatic    org/codehaus/groovy/tools/shell/ComplexCommandSupport.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    89: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    92: checkcast       Ljava/lang/String;
        //    95: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.getGroovyObjectProperty:(Ljava/lang/Class;Lgroovy/lang/GroovyObject;Ljava/lang/String;)Ljava/lang/Object;
        //    98: invokestatic    org/codehaus/groovy/tools/shell/ComplexCommandSupport.$get$$class$groovy$lang$Closure:()Ljava/lang/Class;
        //   101: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   104: checkcast       Lgroovy/lang/Closure;
        //   107: astore          4
        //   109: nop            
        //   110: nop            
        //   111: aload           4
        //   113: areturn        
        //   114: goto            181
        //   117: astore          e
        //   119: aload_2        
        //   120: ldc_w           14
        //   123: aaload         
        //   124: aload_0         /* this */
        //   125: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   128: dup            
        //   129: iconst_1       
        //   130: anewarray       Ljava/lang/Object;
        //   133: dup            
        //   134: iconst_0       
        //   135: aload           e
        //   137: aastore        
        //   138: iconst_2       
        //   139: anewarray       Ljava/lang/String;
        //   142: dup            
        //   143: iconst_0       
        //   144: ldc_w           "Failed to load delgate function: "
        //   147: aastore        
        //   148: dup            
        //   149: iconst_1       
        //   150: ldc             ""
        //   152: aastore        
        //   153: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   156: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   161: invokestatic    org/codehaus/groovy/tools/shell/ComplexCommandSupport.$get$$class$groovy$lang$Closure:()Ljava/lang/Class;
        //   164: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   167: checkcast       Lgroovy/lang/Closure;
        //   170: astore          5
        //   172: nop            
        //   173: nop            
        //   174: aload           5
        //   176: areturn        
        //   177: nop            
        //   178: goto            181
        //   181: nop            
        //   182: goto            190
        //   185: astore          4
        //   187: aload           4
        //   189: athrow         
        //   190: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  52     110    117    177    Lgroovy/lang/MissingPropertyException;
        //  111    117    117    177    Lgroovy/lang/MissingPropertyException;
        //  52     110    185    190    Any
        //  111    117    185    190    Any
        //  117    173    185    190    Any
        //  174    178    185    190    Any
        //  12     44     47     52     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ComplexCommandSupport.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ComplexCommandSupport.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ComplexCommandSupport.__timeStamp__239_neverHappen1292524204731 = 0L;
        ComplexCommandSupport.__timeStamp = 1292524204731L;
        $const$2 = -1;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public Object getDo_all() {
        return this.do_all;
    }
    
    public void setDo_all(final Object do_all) {
        this.do_all = do_all;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ComplexCommandSupport.$callSiteArray == null || ($createCallSiteArray = ComplexCommandSupport.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ComplexCommandSupport.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ComplexCommandSupport.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ComplexCommandSupport.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ComplexCommandSupport.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ComplexCommandSupport.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = (ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = class$("org.codehaus.groovy.tools.shell.ComplexCommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ComplexCommandSupport.$class$java$util$List) == null) {
            $class$java$util$List = (ComplexCommandSupport.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = (ComplexCommandSupport.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = class$("org.codehaus.groovy.tools.shell.util.SimpleCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
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
