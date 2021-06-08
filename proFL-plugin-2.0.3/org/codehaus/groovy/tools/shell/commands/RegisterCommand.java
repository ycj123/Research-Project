// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.tools.shell.BufferManager;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.tools.shell.Command;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class RegisterCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204815;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$RegisterCommand;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public RegisterCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "register";
        arguments[2] = "\\rc";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Object execute(final List args) {
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
        if (ScriptBytecodeAdapter.compareLessThan($getCallSiteArray[0].call(args), RegisterCommand.$const$0)) {
            $getCallSiteArray[1].callCurrent(this, "Command 'register' requires at least 1 arguments");
        }
        final String classname = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(args, RegisterCommand.$const$1), $get$$class$java$lang$String());
        final Class type = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].callCurrent(this), classname), $get$$class$java$lang$Class());
        Command command = (Command)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$tools$shell$Command());
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[5].call(args), RegisterCommand.$const$0)) {
            command = (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(type, $getCallSiteArray[7].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$Command());
        }
        else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[8].call(args), RegisterCommand.$const$2)) {
            command = (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(type, $getCallSiteArray[10].callGroovyObjectGetProperty(this), $getCallSiteArray[11].call(args, RegisterCommand.$const$0), null), $get$$class$org$codehaus$groovy$tools$shell$Command());
        }
        else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[12].call(args), RegisterCommand.$const$3)) {
            command = (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray[13].call(type, $getCallSiteArray[14].callGroovyObjectGetProperty(this), $getCallSiteArray[15].call(args, RegisterCommand.$const$0), $getCallSiteArray[16].call(args, RegisterCommand.$const$2)), $get$$class$org$codehaus$groovy$tools$shell$Command());
        }
        final Object oldcommand = $getCallSiteArray[17].call($getCallSiteArray[18].callGroovyObjectGetProperty(this), $getCallSiteArray[19].callGetProperty(command));
        if (DefaultTypeTransformation.booleanUnbox(oldcommand)) {
            $getCallSiteArray[20].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[21].callGetProperty(command) }, new String[] { "Can not rebind command: ", "" }));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[22].callGetProperty($getCallSiteArray[23].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[24].call($getCallSiteArray[25].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[26].callGetProperty(command), command }, new String[] { "Created command '", "': ", "" }));
        }
        command = (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray[27].call($getCallSiteArray[28].callGroovyObjectGetProperty(this), command), $get$$class$org$codehaus$groovy$tools$shell$Command());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[29].callGroovyObjectGetProperty($getCallSiteArray[30].callGroovyObjectGetProperty(this)))) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[31].call($getCallSiteArray[32].callGetProperty($getCallSiteArray[33].callGroovyObjectGetProperty($getCallSiteArray[34].callGroovyObjectGetProperty(this))), command), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$RegisterCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RegisterCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RegisterCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RegisterCommand.__timeStamp__239_neverHappen1292524204815 = 0L;
        RegisterCommand.__timeStamp = 1292524204815L;
        $const$3 = 3;
        $const$2 = 2;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[35];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RegisterCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RegisterCommand.$callSiteArray == null || ($createCallSiteArray = RegisterCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RegisterCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RegisterCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RegisterCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = RegisterCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (RegisterCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RegisterCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RegisterCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$RegisterCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$RegisterCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$RegisterCommand = RegisterCommand.$class$org$codehaus$groovy$tools$shell$commands$RegisterCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$RegisterCommand = (RegisterCommand.$class$org$codehaus$groovy$tools$shell$commands$RegisterCommand = class$("org.codehaus.groovy.tools.shell.commands.RegisterCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$RegisterCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = RegisterCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (RegisterCommand.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = RegisterCommand.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (RegisterCommand.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = RegisterCommand.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (RegisterCommand.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
