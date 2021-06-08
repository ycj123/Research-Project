// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.io.File;
import jline.History;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Closure;
import jline.ConsoleReader;
import groovy.lang.GroovyObject;

public class InteractiveShellRunner extends ShellRunner implements Runnable, GroovyObject
{
    private final ConsoleReader reader;
    private final Closure prompt;
    private final CommandsMultiCompletor completor;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204783;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ShellRunner;
    private static /* synthetic */ Class $class$jline$ConsoleReader;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
    
    public InteractiveShellRunner(final Shell shell, final Closure prompt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = new Object[] { shell };
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$ShellRunner());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array = (Object[])arguments[0];
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                this.prompt = (Closure)ScriptBytecodeAdapter.castToType(prompt, $get$$class$groovy$lang$Closure());
                this.reader = (ConsoleReader)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$jline$ConsoleReader(), $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(shell)), $getCallSiteArray[3].callConstructor($get$$class$java$io$PrintWriter(), $getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(shell)), Boolean.TRUE)), $get$$class$jline$ConsoleReader());
                this.completor = (CommandsMultiCompletor)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].callConstructor($get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor()), $get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor());
                $getCallSiteArray[7].call(this.reader, this.completor);
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public void run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object command = null;
        final Object call = $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty($getCallSiteArray[10].callGroovyObjectGetProperty(this)));
        while (((Iterator)call).hasNext()) {
            command = ((Iterator<Object>)call).next();
            $getCallSiteArray[11].call(this.completor, command);
        }
        $getCallSiteArray[12].call(this.completor);
        ScriptBytecodeAdapter.invokeMethodOnSuper0($get$$class$org$codehaus$groovy$tools$shell$ShellRunner(), this, "run");
    }
    
    public void setHistory(final History history) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setProperty(history, $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner(), this.reader, "history");
    }
    
    public void setHistoryFile(final File file) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object dir = $getCallSiteArray[13].callGetProperty(file);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(dir))) {
            $getCallSiteArray[15].call(dir);
            $getCallSiteArray[16].call($getCallSiteArray[17].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { dir }, new String[] { "Created base directory for history file: ", "" }));
        }
        $getCallSiteArray[18].call($getCallSiteArray[19].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { file }, new String[] { "Using history file: ", "" }));
        ScriptBytecodeAdapter.setProperty(file, $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner(), $getCallSiteArray[20].callGetProperty(this.reader), "historyFile");
    }
    
    protected String readLine() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: aload_1        
        //     5: ldc             21
        //     7: aaload         
        //     8: aload_0         /* this */
        //     9: getfield        org/codehaus/groovy/tools/shell/InteractiveShellRunner.reader:Ljline/ConsoleReader;
        //    12: aload_1        
        //    13: ldc             22
        //    15: aaload         
        //    16: aload_0         /* this */
        //    17: getfield        org/codehaus/groovy/tools/shell/InteractiveShellRunner.prompt:Lgroovy/lang/Closure;
        //    20: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    25: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    30: invokestatic    org/codehaus/groovy/tools/shell/InteractiveShellRunner.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    33: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    36: checkcast       Ljava/lang/String;
        //    39: astore_2       
        //    40: nop            
        //    41: nop            
        //    42: aload_2        
        //    43: areturn        
        //    44: goto            91
        //    47: astore_2       
        //    48: aload_1        
        //    49: ldc             23
        //    51: aaload         
        //    52: aload_1        
        //    53: ldc             24
        //    55: aaload         
        //    56: aload_0         /* this */
        //    57: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    62: ldc             "HACK: Try and work around GROOVY-2152 for now"
        //    64: aload_2        
        //    65: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    70: pop            
        //    71: ldc             ""
        //    73: invokestatic    org/codehaus/groovy/tools/shell/InteractiveShellRunner.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    76: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    79: checkcast       Ljava/lang/String;
        //    82: astore_3       
        //    83: nop            
        //    84: nop            
        //    85: aload_3        
        //    86: areturn        
        //    87: nop            
        //    88: goto            91
        //    91: nop            
        //    92: goto            98
        //    95: astore_2       
        //    96: aload_2        
        //    97: athrow         
        //    98: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  4      41     47     87     Ljava/lang/StringIndexOutOfBoundsException;
        //  42     47     47     87     Ljava/lang/StringIndexOutOfBoundsException;
        //  4      41     95     98     Any
        //  42     47     95     98     Any
        //  47     84     95     98     Any
        //  85     88     95     98     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = InteractiveShellRunner.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (InteractiveShellRunner.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        InteractiveShellRunner.__timeStamp__239_neverHappen1292524204783 = 0L;
        InteractiveShellRunner.__timeStamp = 1292524204783L;
    }
    
    public final ConsoleReader getReader() {
        return this.reader;
    }
    
    public final Closure getPrompt() {
        return this.prompt;
    }
    
    public final CommandsMultiCompletor getCompletor() {
        return this.completor;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[25];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (InteractiveShellRunner.$callSiteArray == null || ($createCallSiteArray = InteractiveShellRunner.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            InteractiveShellRunner.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner() {
        Class $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
        if (($class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner) == null) {
            $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = (InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$InteractiveShellRunner = class$("org.codehaus.groovy.tools.shell.InteractiveShellRunner"));
        }
        return $class$org$codehaus$groovy$tools$shell$InteractiveShellRunner;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = InteractiveShellRunner.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (InteractiveShellRunner.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ShellRunner() {
        Class $class$org$codehaus$groovy$tools$shell$ShellRunner;
        if (($class$org$codehaus$groovy$tools$shell$ShellRunner = InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$ShellRunner) == null) {
            $class$org$codehaus$groovy$tools$shell$ShellRunner = (InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$ShellRunner = class$("org.codehaus.groovy.tools.shell.ShellRunner"));
        }
        return $class$org$codehaus$groovy$tools$shell$ShellRunner;
    }
    
    private static /* synthetic */ Class $get$$class$jline$ConsoleReader() {
        Class $class$jline$ConsoleReader;
        if (($class$jline$ConsoleReader = InteractiveShellRunner.$class$jline$ConsoleReader) == null) {
            $class$jline$ConsoleReader = (InteractiveShellRunner.$class$jline$ConsoleReader = class$("jline.ConsoleReader"));
        }
        return $class$jline$ConsoleReader;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = InteractiveShellRunner.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (InteractiveShellRunner.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = InteractiveShellRunner.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (InteractiveShellRunner.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
        }
        return $class$java$io$PrintWriter;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
        if (($class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = (InteractiveShellRunner.$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = class$("org.codehaus.groovy.tools.shell.CommandsMultiCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
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
