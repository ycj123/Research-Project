// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Groovysh$_closure2 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Interpreter;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$java$lang$Throwable;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public Groovysh$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Throwable cause) {
        final Throwable cause2 = (Throwable)new Reference(cause);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(((Reference<Object>)cause2).get(), 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 14);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert cause != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGetProperty(((Reference<Object>)cause2).get())) }, new String[] { "@|bold,red ERROR|@ ", ":" }));
        $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[8].callGetProperty(((Reference<Object>)cause2).get()) }, new String[] { "@|bold,red ", "|@" }));
        $getCallSiteArray[9].callCurrent(this, ((Reference<Object>)cause2).get());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].callGetProperty($getCallSiteArray[11].callGroovyObjectGetProperty(this)))) {
            return $getCallSiteArray[12].call($getCallSiteArray[13].callGroovyObjectGetProperty(this), ((Reference<Object>)cause2).get());
        }
        final Boolean sanitize = (Boolean)ScriptBytecodeAdapter.castToType($getCallSiteArray[14].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$util$Preferences()), $get$$class$java$lang$Boolean());
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[15].callGetProperty($getCallSiteArray[16].callGroovyObjectGetProperty(this))) && DefaultTypeTransformation.booleanUnbox(sanitize)) ? Boolean.TRUE : Boolean.FALSE)) {
            ((Reference<Throwable>)cause2).set((Throwable)ScriptBytecodeAdapter.castToType($getCallSiteArray[17].call($get$$class$org$codehaus$groovy$runtime$StackTraceUtils(), ((Reference<Object>)cause2).get()), $get$$class$java$lang$Throwable()));
        }
        final Object trace = new Reference($getCallSiteArray[18].callGetProperty(((Reference<Object>)cause2).get()));
        final Object buff = new Reference($getCallSiteArray[19].callConstructor($get$$class$java$lang$StringBuffer()));
        final Object e = new Reference(null);
        final Object call = $getCallSiteArray[20].call(((Reference<Object>)trace).get());
        while (((Iterator)call).hasNext()) {
            ((Reference<Object>)e).set(((Iterator<T>)call).next());
            $getCallSiteArray[21].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { $getCallSiteArray[22].callGetProperty(((Reference<Object>)e).get()), $getCallSiteArray[23].callGetProperty(((Reference<Object>)e).get()) }, new String[] { "        @|bold at|@ ", ".", " (@|bold " }));
            $getCallSiteArray[24].call(((Reference<Object>)buff).get(), DefaultTypeTransformation.booleanUnbox($getCallSiteArray[25].callGetProperty(((Reference<Object>)e).get())) ? "Native Method" : (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[26].callGetProperty(((Reference<Object>)e).get()), null) && ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[27].callGetProperty(((Reference<Object>)e).get()), Groovysh$_closure2.$const$0)) ? Boolean.TRUE : Boolean.FALSE) ? new GStringImpl(new Object[] { $getCallSiteArray[28].callGetProperty(((Reference<Object>)e).get()), $getCallSiteArray[29].callGetProperty(((Reference<Object>)e).get()) }, new String[] { "", ":", "" }) : (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[30].callGetProperty(((Reference<Object>)e).get()), null) ? $getCallSiteArray[31].callGetProperty(((Reference<Object>)e).get()) : "Unknown Source")));
            $getCallSiteArray[32].call(((Reference<Object>)buff).get(), "|@)");
            $getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty($getCallSiteArray[35].callGroovyObjectGetProperty(this)), ((Reference<Object>)buff).get());
            $getCallSiteArray[36].call(((Reference<Object>)buff).get(), Groovysh$_closure2.$const$1);
            if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[37].callGetProperty(((Reference<Object>)e).get()), $getCallSiteArray[38].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$Interpreter())) && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[39].callGetProperty(((Reference<Object>)e).get()), "run")) ? Boolean.TRUE : Boolean.FALSE)) {
                $getCallSiteArray[40].call($getCallSiteArray[41].callGetProperty($getCallSiteArray[42].callGroovyObjectGetProperty(this)), "        @|bold ...|@");
                break;
            }
        }
        return null;
    }
    
    public Object call(final Throwable cause) {
        final Throwable cause2 = (Throwable)new Reference(cause);
        return $getCallSiteArray()[43].callCurrent(this, ((Reference<Object>)cause2).get());
    }
    
    static {
        $const$1 = 0;
        $const$0 = -1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[44];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Groovysh$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Groovysh$_closure2.$callSiteArray == null || ($createCallSiteArray = Groovysh$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Groovysh$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = Groovysh$_closure2.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (Groovysh$_closure2.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Interpreter() {
        Class $class$org$codehaus$groovy$tools$shell$Interpreter;
        if (($class$org$codehaus$groovy$tools$shell$Interpreter = Groovysh$_closure2.$class$org$codehaus$groovy$tools$shell$Interpreter) == null) {
            $class$org$codehaus$groovy$tools$shell$Interpreter = (Groovysh$_closure2.$class$org$codehaus$groovy$tools$shell$Interpreter = class$("org.codehaus.groovy.tools.shell.Interpreter"));
        }
        return $class$org$codehaus$groovy$tools$shell$Interpreter;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = Groovysh$_closure2.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (Groovysh$_closure2.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Throwable() {
        Class $class$java$lang$Throwable;
        if (($class$java$lang$Throwable = Groovysh$_closure2.$class$java$lang$Throwable) == null) {
            $class$java$lang$Throwable = (Groovysh$_closure2.$class$java$lang$Throwable = class$("java.lang.Throwable"));
        }
        return $class$java$lang$Throwable;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$StackTraceUtils() {
        Class $class$org$codehaus$groovy$runtime$StackTraceUtils;
        if (($class$org$codehaus$groovy$runtime$StackTraceUtils = Groovysh$_closure2.$class$org$codehaus$groovy$runtime$StackTraceUtils) == null) {
            $class$org$codehaus$groovy$runtime$StackTraceUtils = (Groovysh$_closure2.$class$org$codehaus$groovy$runtime$StackTraceUtils = class$("org.codehaus.groovy.runtime.StackTraceUtils"));
        }
        return $class$org$codehaus$groovy$runtime$StackTraceUtils;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = Groovysh$_closure2.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (Groovysh$_closure2.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
