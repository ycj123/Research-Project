// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovyjarjarcommonscli.Options;
import java.io.PrintWriter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> commands;
    private Reference<Object> options;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$apache$commons$cli$Options;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$apache$commons$cli$HelpFormatter;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    
    public GrapeMain$_run_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> commands, final Reference<Object> options) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.commands = commands;
        this.options = options;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Integer spacesLen = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].call(this.commands.get()), new GrapeMain$_run_closure4_closure10(this, this.getThisObject()))), GrapeMain$_run_closure4.$const$0), $get$$class$java$lang$Integer()));
        final String spaces = (String)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(" ", ((Reference<Object>)spacesLen).get()), $get$$class$java$lang$String()));
        final CallSite callSite = $getCallSiteArray[5];
        final Class $get$$class$java$io$PrintWriter = $get$$class$java$io$PrintWriter();
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGroovyObjectGetProperty(this))))) {
            o = $getCallSiteArray[9].callGetProperty($get$$class$java$lang$System());
        }
        final PrintWriter pw = (PrintWriter)callSite.callConstructor($get$$class$java$io$PrintWriter, o);
        $getCallSiteArray[10].call($getCallSiteArray[11].callConstructor($get$$class$org$apache$commons$cli$HelpFormatter()), ArrayUtil.createArray(pw, GrapeMain$_run_closure4.$const$1, "grape [options] <command> [args]\n", "options:", this.options.get(), GrapeMain$_run_closure4.$const$2, GrapeMain$_run_closure4.$const$3, null, Boolean.TRUE));
        $getCallSiteArray[12].call(pw);
        $getCallSiteArray[13].callCurrent(this, "");
        $getCallSiteArray[14].callCurrent(this, "commands:");
        $getCallSiteArray[15].call(this.commands.get(), new GrapeMain$_run_closure4_closure11(this, this.getThisObject(), (Reference<Object>)spacesLen, (Reference<Object>)spaces));
        return $getCallSiteArray[16].callCurrent(this, "");
    }
    
    public Object getCommands() {
        $getCallSiteArray();
        return this.commands.get();
    }
    
    public Options getOptions() {
        $getCallSiteArray();
        return (Options)ScriptBytecodeAdapter.castToType(this.options.get(), $get$$class$org$apache$commons$cli$Options());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[17].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$3 = 4;
        $const$2 = 2;
        $const$1 = 80;
        $const$0 = 3;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure4.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = GrapeMain$_run_closure4.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (GrapeMain$_run_closure4.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GrapeMain$_run_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GrapeMain$_run_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = GrapeMain$_run_closure4.$class$java$lang$System) == null) {
            $class$java$lang$System = (GrapeMain$_run_closure4.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$Options() {
        Class $class$org$apache$commons$cli$Options;
        if (($class$org$apache$commons$cli$Options = GrapeMain$_run_closure4.$class$org$apache$commons$cli$Options) == null) {
            $class$org$apache$commons$cli$Options = (GrapeMain$_run_closure4.$class$org$apache$commons$cli$Options = class$("groovyjarjarcommonscli.Options"));
        }
        return $class$org$apache$commons$cli$Options;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GrapeMain$_run_closure4.$class$java$lang$String) == null) {
            $class$java$lang$String = (GrapeMain$_run_closure4.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$HelpFormatter() {
        Class $class$org$apache$commons$cli$HelpFormatter;
        if (($class$org$apache$commons$cli$HelpFormatter = GrapeMain$_run_closure4.$class$org$apache$commons$cli$HelpFormatter) == null) {
            $class$org$apache$commons$cli$HelpFormatter = (GrapeMain$_run_closure4.$class$org$apache$commons$cli$HelpFormatter = class$("groovyjarjarcommonscli.HelpFormatter"));
        }
        return $class$org$apache$commons$cli$HelpFormatter;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = GrapeMain$_run_closure4.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (GrapeMain$_run_closure4.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
        }
        return $class$java$io$PrintWriter;
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
