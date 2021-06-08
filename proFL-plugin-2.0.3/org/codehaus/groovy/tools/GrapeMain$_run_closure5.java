// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovyjarjarcommonscli.CommandLine;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> cmd;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$commons$cli$CommandLine;
    private static /* synthetic */ Class $class$org$apache$ivy$util$DefaultMessageLogger;
    private static /* synthetic */ Class $class$org$apache$ivy$util$Message;
    
    public GrapeMain$_run_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> cmd) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.cmd = cmd;
    }
    
    public Object doCall(final int defaultLevel) {
        final Integer defaultLevel2 = (Integer)new Reference(DefaultTypeTransformation.box(defaultLevel));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(this.cmd.get(), "q"))) {
            return $getCallSiteArray[1].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[2].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), $getCallSiteArray[3].callGetProperty($get$$class$org$apache$ivy$util$Message())));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(this.cmd.get(), "w"))) {
            return $getCallSiteArray[5].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[6].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), $getCallSiteArray[7].callGetProperty($get$$class$org$apache$ivy$util$Message())));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(this.cmd.get(), "i"))) {
            return $getCallSiteArray[9].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[10].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), $getCallSiteArray[11].callGetProperty($get$$class$org$apache$ivy$util$Message())));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call(this.cmd.get(), "V"))) {
            return $getCallSiteArray[13].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[14].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), $getCallSiteArray[15].callGetProperty($get$$class$org$apache$ivy$util$Message())));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call(this.cmd.get(), "d"))) {
            return $getCallSiteArray[17].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[18].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), $getCallSiteArray[19].callGetProperty($get$$class$org$apache$ivy$util$Message())));
        }
        return $getCallSiteArray[20].call($get$$class$org$apache$ivy$util$Message(), $getCallSiteArray[21].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), ((Reference<Object>)defaultLevel2).get()));
    }
    
    public Object call(final int defaultLevel) {
        final Integer defaultLevel2 = (Integer)new Reference(DefaultTypeTransformation.box(defaultLevel));
        return $getCallSiteArray()[22].callCurrent(this, ((Reference<Object>)defaultLevel2).get());
    }
    
    public CommandLine getCmd() {
        $getCallSiteArray();
        return (CommandLine)ScriptBytecodeAdapter.castToType(this.cmd.get(), $get$$class$org$apache$commons$cli$CommandLine());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[23].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(GrapeMain$_run_closure5.$const$0, Integer.TYPE));
    }
    
    static {
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure5.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$CommandLine() {
        Class $class$org$apache$commons$cli$CommandLine;
        if (($class$org$apache$commons$cli$CommandLine = GrapeMain$_run_closure5.$class$org$apache$commons$cli$CommandLine) == null) {
            $class$org$apache$commons$cli$CommandLine = (GrapeMain$_run_closure5.$class$org$apache$commons$cli$CommandLine = class$("groovyjarjarcommonscli.CommandLine"));
        }
        return $class$org$apache$commons$cli$CommandLine;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$util$DefaultMessageLogger() {
        Class $class$org$apache$ivy$util$DefaultMessageLogger;
        if (($class$org$apache$ivy$util$DefaultMessageLogger = GrapeMain$_run_closure5.$class$org$apache$ivy$util$DefaultMessageLogger) == null) {
            $class$org$apache$ivy$util$DefaultMessageLogger = (GrapeMain$_run_closure5.$class$org$apache$ivy$util$DefaultMessageLogger = class$("org.apache.ivy.util.DefaultMessageLogger"));
        }
        return $class$org$apache$ivy$util$DefaultMessageLogger;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$util$Message() {
        Class $class$org$apache$ivy$util$Message;
        if (($class$org$apache$ivy$util$Message = GrapeMain$_run_closure5.$class$org$apache$ivy$util$Message) == null) {
            $class$org$apache$ivy$util$Message = (GrapeMain$_run_closure5.$class$org$apache$ivy$util$Message = class$("org.apache.ivy.util.Message"));
        }
        return $class$org$apache$ivy$util$Message;
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
