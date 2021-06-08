// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import junit.framework.TestResult;
import groovy.lang.GroovyCodeSource;
import groovy.lang.Script;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import java.util.List;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Closure;
import java.util.Map;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyShell;
import groovy.lang.GroovyObject;

public class GroovyShellTestCase extends GroovyTestCase implements GroovyObject
{
    protected GroovyShell shell;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205702;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$groovy$util$GroovyTestCase;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$groovy$util$GroovyShellTestCase;
    private static /* synthetic */ Class $class$groovy$lang$GroovyShell;
    
    public GroovyShellTestCase() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    protected void setUp() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuper0($get$$class$groovy$util$GroovyTestCase(), this, "setUp");
        this.shell = (GroovyShell)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callCurrent(this), $get$$class$groovy$lang$GroovyShell()), $get$$class$groovy$lang$GroovyShell());
    }
    
    protected void tearDown() {
        $getCallSiteArray();
        this.shell = (GroovyShell)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$GroovyShell()), $get$$class$groovy$lang$GroovyShell());
        ScriptBytecodeAdapter.invokeMethodOnSuper0($get$$class$groovy$util$GroovyTestCase(), this, "tearDown");
    }
    
    protected GroovyShell createNewShell() {
        return (GroovyShell)ScriptBytecodeAdapter.castToType($getCallSiteArray()[1].callConstructor($get$$class$groovy$lang$GroovyShell()), $get$$class$groovy$lang$GroovyShell());
    }
    
    protected Object withBinding(final Map map, final Closure closure) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_3       
        //     4: aload_3        
        //     5: ldc             2
        //     7: aaload         
        //     8: aload_0         /* this */
        //     9: getfield        groovy/util/GroovyShellTestCase.shell:Lgroovy/lang/GroovyShell;
        //    12: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    17: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$groovy$lang$Binding:()Ljava/lang/Class;
        //    20: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    23: checkcast       Lgroovy/lang/Binding;
        //    26: astore          binding
        //    28: aload_3        
        //    29: ldc             3
        //    31: aaload         
        //    32: aload           binding
        //    34: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    39: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$java$util$Map:()Ljava/lang/Class;
        //    42: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    45: checkcast       Ljava/util/Map;
        //    48: astore          bmap
        //    50: aload_3        
        //    51: ldc             4
        //    53: aaload         
        //    54: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$java$util$HashMap:()Ljava/lang/Class;
        //    57: aload           bmap
        //    59: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    64: astore          vars
        //    66: aload_3        
        //    67: ldc             5
        //    69: aaload         
        //    70: aload           bmap
        //    72: aload_1         /* map */
        //    73: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    78: pop            
        //    79: aload_3        
        //    80: ldc             6
        //    82: aaload         
        //    83: aload_2         /* closure */
        //    84: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    89: astore          7
        //    91: nop            
        //    92: aload_3        
        //    93: ldc             7
        //    95: aaload         
        //    96: aload           bmap
        //    98: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   103: pop            
        //   104: aload_3        
        //   105: ldc             8
        //   107: aaload         
        //   108: aload           bmap
        //   110: aload           vars
        //   112: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   117: pop            
        //   118: nop            
        //   119: aload           7
        //   121: areturn        
        //   122: goto            125
        //   125: aload_3        
        //   126: ldc             9
        //   128: aaload         
        //   129: aload           bmap
        //   131: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   136: pop            
        //   137: aload_3        
        //   138: ldc             10
        //   140: aaload         
        //   141: aload           bmap
        //   143: aload_3        
        //   144: ldc             11
        //   146: aaload         
        //   147: aload_0         /* this */
        //   148: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   153: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: nop            
        //   160: goto            202
        //   163: astore          6
        //   165: aload_3        
        //   166: ldc             12
        //   168: aaload         
        //   169: aload           bmap
        //   171: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   176: pop            
        //   177: aload_3        
        //   178: ldc             13
        //   180: aaload         
        //   181: aload           bmap
        //   183: aload_3        
        //   184: ldc             14
        //   186: aaload         
        //   187: aload_0         /* this */
        //   188: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   193: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   198: pop            
        //   199: aload           6
        //   201: athrow         
        //   202: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  50     92     163    202    Any
        //  119    125    163    202    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected Object withBinding(final Map map, final String script) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_3       
        //     4: aload_3        
        //     5: ldc             15
        //     7: aaload         
        //     8: aload_0         /* this */
        //     9: getfield        groovy/util/GroovyShellTestCase.shell:Lgroovy/lang/GroovyShell;
        //    12: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    17: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$groovy$lang$Binding:()Ljava/lang/Class;
        //    20: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    23: checkcast       Lgroovy/lang/Binding;
        //    26: astore          binding
        //    28: aload_3        
        //    29: ldc             16
        //    31: aaload         
        //    32: aload           binding
        //    34: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    39: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$java$util$Map:()Ljava/lang/Class;
        //    42: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    45: checkcast       Ljava/util/Map;
        //    48: astore          bmap
        //    50: aload_3        
        //    51: ldc             17
        //    53: aaload         
        //    54: invokestatic    groovy/util/GroovyShellTestCase.$get$$class$java$util$HashMap:()Ljava/lang/Class;
        //    57: aload           bmap
        //    59: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    64: astore          vars
        //    66: aload_3        
        //    67: ldc             18
        //    69: aaload         
        //    70: aload           bmap
        //    72: aload_1         /* map */
        //    73: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    78: pop            
        //    79: aload_3        
        //    80: ldc             19
        //    82: aaload         
        //    83: aload_0         /* this */
        //    84: aload_2         /* script */
        //    85: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //    90: astore          7
        //    92: nop            
        //    93: aload_3        
        //    94: ldc             20
        //    96: aaload         
        //    97: aload           bmap
        //    99: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   104: pop            
        //   105: aload_3        
        //   106: ldc             21
        //   108: aaload         
        //   109: aload           bmap
        //   111: aload           vars
        //   113: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   118: pop            
        //   119: nop            
        //   120: aload           7
        //   122: areturn        
        //   123: goto            126
        //   126: aload_3        
        //   127: ldc             22
        //   129: aaload         
        //   130: aload           bmap
        //   132: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   137: pop            
        //   138: aload_3        
        //   139: ldc             23
        //   141: aaload         
        //   142: aload           bmap
        //   144: aload_3        
        //   145: ldc             24
        //   147: aaload         
        //   148: aload_0         /* this */
        //   149: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   154: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   159: pop            
        //   160: nop            
        //   161: goto            203
        //   164: astore          6
        //   166: aload_3        
        //   167: ldc             25
        //   169: aaload         
        //   170: aload           bmap
        //   172: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   177: pop            
        //   178: aload_3        
        //   179: ldc             26
        //   181: aaload         
        //   182: aload           bmap
        //   184: aload_3        
        //   185: ldc             27
        //   187: aaload         
        //   188: aload_0         /* this */
        //   189: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   194: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   199: pop            
        //   200: aload           6
        //   202: athrow         
        //   203: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  50     93     164    203    Any
        //  120    126    164    203    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$GroovyShellTestCase()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GroovyShellTestCase.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GroovyShellTestCase.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public Object run(final File param0, final List param1) throws CompilationFailedException, IOException {
        return $getCallSiteArray()[28].call(this.shell, param0, param1);
    }
    
    public Object run(final String param0, final String param1, final List param2) throws CompilationFailedException {
        return $getCallSiteArray()[29].call(this.shell, param0, param1, param2);
    }
    
    public Object run(final File param0, final String... param1) throws CompilationFailedException, IOException {
        return $getCallSiteArray()[30].call(this.shell, param0, param1);
    }
    
    public Object run(final String param0, final String param1, final String... param2) throws CompilationFailedException {
        return $getCallSiteArray()[31].call(this.shell, param0, param1, param2);
    }
    
    public Object run(final Reader param0, final String param1, final String... param2) throws CompilationFailedException {
        return $getCallSiteArray()[32].call(this.shell, param0, param1, param2);
    }
    
    public Object run(final InputStream param0, final String param1, final String... param2) throws CompilationFailedException {
        return $getCallSiteArray()[33].call(this.shell, param0, param1, param2);
    }
    
    public GroovyClassLoader getClassLoader() {
        return (GroovyClassLoader)ScriptBytecodeAdapter.castToType($getCallSiteArray()[34].call(this.shell), $get$$class$groovy$lang$GroovyClassLoader());
    }
    
    public Binding getContext() {
        return (Binding)ScriptBytecodeAdapter.castToType($getCallSiteArray()[35].call(this.shell), $get$$class$groovy$lang$Binding());
    }
    
    public Script parse(final Reader param0, final String param1) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[36].call(this.shell, param0, param1), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final InputStream param0, final String param1) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[37].call(this.shell, param0, param1), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final GroovyCodeSource param0) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[38].call(this.shell, param0), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final File param0) throws CompilationFailedException, IOException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[39].call(this.shell, param0), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final String param0) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[40].call(this.shell, param0), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final String param0, final String param1) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[41].call(this.shell, param0, param1), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final Reader param0) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[42].call(this.shell, param0), $get$$class$groovy$lang$Script());
    }
    
    public Script parse(final InputStream param0) throws CompilationFailedException {
        return (Script)ScriptBytecodeAdapter.castToType($getCallSiteArray()[43].call(this.shell, param0), $get$$class$groovy$lang$Script());
    }
    
    public Object getVariable(final String param0) {
        return $getCallSiteArray()[44].call(this.shell, param0);
    }
    
    public Object evaluate(final GroovyCodeSource param0) throws CompilationFailedException {
        return $getCallSiteArray()[45].call(this.shell, param0);
    }
    
    public Object evaluate(final String param0) throws CompilationFailedException {
        return $getCallSiteArray()[46].call(this.shell, param0);
    }
    
    public Object evaluate(final String param0, final String param1) throws CompilationFailedException {
        return $getCallSiteArray()[47].call(this.shell, param0, param1);
    }
    
    public Object evaluate(final String param0, final String param1, final String param2) throws CompilationFailedException {
        return $getCallSiteArray()[48].call(this.shell, param0, param1, param2);
    }
    
    public Object evaluate(final File param0) throws CompilationFailedException, IOException {
        return $getCallSiteArray()[49].call(this.shell, param0);
    }
    
    public Object evaluate(final Reader param0) throws CompilationFailedException {
        return $getCallSiteArray()[50].call(this.shell, param0);
    }
    
    public Object evaluate(final Reader param0, final String param1) throws CompilationFailedException {
        return $getCallSiteArray()[51].call(this.shell, param0, param1);
    }
    
    public Object evaluate(final InputStream param0) throws CompilationFailedException {
        return $getCallSiteArray()[52].call(this.shell, param0);
    }
    
    public Object evaluate(final InputStream param0, final String param1) throws CompilationFailedException {
        return $getCallSiteArray()[53].call(this.shell, param0, param1);
    }
    
    public void setVariable(final String param0, final Object param1) {
        $getCallSiteArray()[54].call(this.shell, param0, param1);
    }
    
    public void resetLoadedClasses() {
        $getCallSiteArray()[55].call(this.shell);
    }
    
    static {
        GroovyShellTestCase.__timeStamp__239_neverHappen1292524205702 = 0L;
        GroovyShellTestCase.__timeStamp = 1292524205702L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[56];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$GroovyShellTestCase(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GroovyShellTestCase.$callSiteArray == null || ($createCallSiteArray = GroovyShellTestCase.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GroovyShellTestCase.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = GroovyShellTestCase.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (GroovyShellTestCase.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GroovyShellTestCase.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GroovyShellTestCase.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = GroovyShellTestCase.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (GroovyShellTestCase.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$GroovyTestCase() {
        Class $class$groovy$util$GroovyTestCase;
        if (($class$groovy$util$GroovyTestCase = GroovyShellTestCase.$class$groovy$util$GroovyTestCase) == null) {
            $class$groovy$util$GroovyTestCase = (GroovyShellTestCase.$class$groovy$util$GroovyTestCase = class$("groovy.util.GroovyTestCase"));
        }
        return $class$groovy$util$GroovyTestCase;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = GroovyShellTestCase.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (GroovyShellTestCase.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$GroovyShellTestCase() {
        Class $class$groovy$util$GroovyShellTestCase;
        if (($class$groovy$util$GroovyShellTestCase = GroovyShellTestCase.$class$groovy$util$GroovyShellTestCase) == null) {
            $class$groovy$util$GroovyShellTestCase = (GroovyShellTestCase.$class$groovy$util$GroovyShellTestCase = class$("groovy.util.GroovyShellTestCase"));
        }
        return $class$groovy$util$GroovyShellTestCase;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyShell() {
        Class $class$groovy$lang$GroovyShell;
        if (($class$groovy$lang$GroovyShell = GroovyShellTestCase.$class$groovy$lang$GroovyShell) == null) {
            $class$groovy$lang$GroovyShell = (GroovyShellTestCase.$class$groovy$lang$GroovyShell = class$("groovy.lang.GroovyShell"));
        }
        return $class$groovy$lang$GroovyShell;
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
