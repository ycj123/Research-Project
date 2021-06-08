// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class CommandException extends Exception implements GroovyObject
{
    private final Command command;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205473;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandException;
    private static /* synthetic */ Class $class$java$lang$Exception;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    
    public CommandException(final Command command, final String msg) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[] { null })));
        arguments[0] = msg;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 4, $get$$class$java$lang$Exception());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                break;
            }
            case 1: {
                super((String)array[0]);
                break;
            }
            case 2: {
                super((String)array2[0], (Throwable)array3[1]);
                break;
            }
            case 3: {
                super((Throwable)array4[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(command, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert command", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.command = (Command)ScriptBytecodeAdapter.castToType(command, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    public CommandException(final Command command, final String msg, final Throwable cause) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[2])));
        arguments[0] = msg;
        arguments[1] = cause;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 4, $get$$class$java$lang$Exception());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                break;
            }
            case 1: {
                super((String)array[0]);
                break;
            }
            case 2: {
                super((String)array2[0], (Throwable)array3[1]);
                break;
            }
            case 3: {
                super((Throwable)array4[0]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(command, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert command", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.command = (Command)ScriptBytecodeAdapter.castToType(command, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$CommandException()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CommandException.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CommandException.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CommandException.__timeStamp__239_neverHappen1292524205473 = 0L;
        CommandException.__timeStamp = 1292524205473L;
    }
    
    public final Command getCommand() {
        return this.command;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$CommandException(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CommandException.$callSiteArray == null || ($createCallSiteArray = CommandException.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CommandException.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CommandException.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CommandException.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandException() {
        Class $class$org$codehaus$groovy$tools$shell$CommandException;
        if (($class$org$codehaus$groovy$tools$shell$CommandException = CommandException.$class$org$codehaus$groovy$tools$shell$CommandException) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandException = (CommandException.$class$org$codehaus$groovy$tools$shell$CommandException = class$("org.codehaus.groovy.tools.shell.CommandException"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Exception() {
        Class $class$java$lang$Exception;
        if (($class$java$lang$Exception = CommandException.$class$java$lang$Exception) == null) {
            $class$java$lang$Exception = (CommandException.$class$java$lang$Exception = class$("java.lang.Exception"));
        }
        return $class$java$lang$Exception;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = CommandException.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (CommandException.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
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
