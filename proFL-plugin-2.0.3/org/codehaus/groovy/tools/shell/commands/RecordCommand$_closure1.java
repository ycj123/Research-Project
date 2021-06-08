// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.io.PrintWriter;
import java.io.File;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class RecordCommand$_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1;
    private static /* synthetic */ Class $class$java$util$Date;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    
    public RecordCommand$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object args) {
        final Object args2 = new Reference(args);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[1].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[2].callGroovyObjectGetProperty(this) }, new String[] { "Already recording to: ", "" }));
        }
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[3].call(((Reference<Object>)args2).get()), RecordCommand$_closure1.$const$0)) {
            ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($get$$class$java$io$File(), "groovysh-", ".txt"), $get$$class$java$io$File()), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1(), this, "file");
        }
        else {
            ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[5].callConstructor($get$$class$java$io$File(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[6].call(((Reference<Object>)args2).get(), RecordCommand$_closure1.$const$1), $get$$class$java$lang$String()), $get$$class$java$lang$String())), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1(), this, "file");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[9].call($getCallSiteArray[10].callGetProperty($getCallSiteArray[11].callGroovyObjectGetProperty(this)));
        }
        ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGroovyObjectGetProperty(this)), $get$$class$java$io$PrintWriter()), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1(), this, "writer");
        $getCallSiteArray[14].call($getCallSiteArray[15].callGroovyObjectGetProperty(this), $getCallSiteArray[16].call("// OPENED: ", $getCallSiteArray[17].callConstructor($get$$class$java$util$Date())));
        $getCallSiteArray[18].call($getCallSiteArray[19].callGroovyObjectGetProperty(this));
        $getCallSiteArray[20].call($getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[23].callGroovyObjectGetProperty(this) }, new String[] { "Recording session to: ", "" }));
        return $getCallSiteArray[24].callGroovyObjectGetProperty(this);
    }
    
    static {
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[25];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RecordCommand$_closure1.$callSiteArray == null || ($createCallSiteArray = RecordCommand$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RecordCommand$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1() {
        Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1;
        if (($class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1 = RecordCommand$_closure1.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1 = (RecordCommand$_closure1.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1 = class$("org.codehaus.groovy.tools.shell.commands.RecordCommand$_closure1"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure1;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Date() {
        Class $class$java$util$Date;
        if (($class$java$util$Date = RecordCommand$_closure1.$class$java$util$Date) == null) {
            $class$java$util$Date = (RecordCommand$_closure1.$class$java$util$Date = class$("java.util.Date"));
        }
        return $class$java$util$Date;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = RecordCommand$_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (RecordCommand$_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = RecordCommand$_closure1.$class$java$io$File) == null) {
            $class$java$io$File = (RecordCommand$_closure1.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = RecordCommand$_closure1.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (RecordCommand$_closure1.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
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
