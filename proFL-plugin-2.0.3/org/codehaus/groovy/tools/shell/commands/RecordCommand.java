// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import jline.Completor;
import java.util.Map;
import jline.History;
import java.util.List;
import org.codehaus.groovy.tools.shell.BufferManager;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.io.PrintWriter;
import java.io.File;
import org.codehaus.groovy.tools.shell.ComplexCommandSupport;

public class RecordCommand extends ComplexCommandSupport
{
    private File file;
    private PrintWriter writer;
    private Object do_start;
    private Object do_stop;
    private Object do_status;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205533;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public RecordCommand(final Shell shell) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "record";
        arguments[2] = "\\r";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.do_start = new RecordCommand$_closure1(this, this);
                this.do_stop = new RecordCommand$_closure2(this, this);
                this.do_status = new RecordCommand$_closure3(this, this);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.createList(new Object[] { "start", "stop", "status" }), $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand(), this, "functions");
                ScriptBytecodeAdapter.setGroovyObjectProperty("status", $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand(), this, "defaultFunction");
                $getCallSiteArray[0].callCurrent(this, new RecordCommand$_closure4(this, this));
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public boolean isRecording() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual(this.file, null) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public Object recordInput(final String line) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(line, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 13);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert line != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[2].call(this.writer, line);
            return $getCallSiteArray[3].call(this.writer);
        }
        return null;
    }
    
    public Object recordResult(final Object result) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[5].call(this.writer, new GStringImpl(new Object[] { $getCallSiteArray[6].call($get$$class$java$lang$String(), result) }, new String[] { "// RESULT: ", "" }));
            return $getCallSiteArray[7].call(this.writer);
        }
        return null;
    }
    
    public Object recordError(final Throwable cause) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(cause, 8), null);
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
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[9].call(this.writer, new GStringImpl(new Object[] { cause }, new String[] { "// ERROR: ", "" }));
            $getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty(cause), new RecordCommand$_recordError_closure5(this, this));
            return $getCallSiteArray[12].call(this.writer);
        }
        return null;
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RecordCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RecordCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RecordCommand.__timeStamp__239_neverHappen1292524205533 = 0L;
        RecordCommand.__timeStamp = 1292524205533L;
    }
    
    public Object getDo_start() {
        return this.do_start;
    }
    
    public void setDo_start(final Object do_start) {
        this.do_start = do_start;
    }
    
    public Object getDo_stop() {
        return this.do_stop;
    }
    
    public void setDo_stop(final Object do_stop) {
        this.do_stop = do_stop;
    }
    
    public Object getDo_status() {
        return this.do_status;
    }
    
    public void setDo_status(final Object do_status) {
        this.do_status = do_status;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RecordCommand.$callSiteArray == null || ($createCallSiteArray = RecordCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RecordCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RecordCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RecordCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$RecordCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$RecordCommand = RecordCommand.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$RecordCommand = (RecordCommand.$class$org$codehaus$groovy$tools$shell$commands$RecordCommand = class$("org.codehaus.groovy.tools.shell.commands.RecordCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$RecordCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = RecordCommand.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (RecordCommand.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = RecordCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = (RecordCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = class$("org.codehaus.groovy.tools.shell.ComplexCommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = RecordCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (RecordCommand.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
