// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.io.File;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.io.PrintWriter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class RecordCommand$_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2;
    private static /* synthetic */ Class $class$java$util$Date;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$java$io$PrintWriter;
    
    public RecordCommand$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[1].callCurrent(this, "Not recording");
        }
        $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].call("// CLOSED: ", $getCallSiteArray[5].callConstructor($get$$class$java$util$Date())));
        $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this));
        $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this));
        ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.castToType(null, $get$$class$java$io$PrintWriter()), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2(), this, "writer");
        $getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty($getCallSiteArray[12].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[13].callGroovyObjectGetProperty(this), $getCallSiteArray[14].call($getCallSiteArray[15].callGroovyObjectGetProperty(this)) }, new String[] { "Recording stopped; session saved as: ", " (", " bytes)" }));
        final Object tmp = $getCallSiteArray[16].callGroovyObjectGetProperty(this);
        ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.castToType(null, $get$$class$java$io$File()), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2(), this, "file");
        return tmp;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[17].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RecordCommand$_closure2.$callSiteArray == null || ($createCallSiteArray = RecordCommand$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RecordCommand$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RecordCommand$_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RecordCommand$_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2() {
        Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2;
        if (($class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2 = RecordCommand$_closure2.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2 = (RecordCommand$_closure2.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2 = class$("org.codehaus.groovy.tools.shell.commands.RecordCommand$_closure2"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure2;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Date() {
        Class $class$java$util$Date;
        if (($class$java$util$Date = RecordCommand$_closure2.$class$java$util$Date) == null) {
            $class$java$util$Date = (RecordCommand$_closure2.$class$java$util$Date = class$("java.util.Date"));
        }
        return $class$java$util$Date;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = RecordCommand$_closure2.$class$java$io$File) == null) {
            $class$java$io$File = (RecordCommand$_closure2.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$PrintWriter() {
        Class $class$java$io$PrintWriter;
        if (($class$java$io$PrintWriter = RecordCommand$_closure2.$class$java$io$PrintWriter) == null) {
            $class$java$io$PrintWriter = (RecordCommand$_closure2.$class$java$io$PrintWriter = class$("java.io.PrintWriter"));
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
