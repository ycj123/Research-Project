// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class CommandAlias extends CommandSupport implements GroovyObject
{
    private final String targetName;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204708;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandAlias;
    
    public CommandAlias(final Shell shell, final String name, final String shortcut, final String target) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = name;
        arguments[2] = shortcut;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                final ValueRecorder recorder = new ValueRecorder();
                try {
                    if (DefaultTypeTransformation.booleanUnbox(recorder.record(target, 8))) {
                        recorder.clear();
                    }
                    else {
                        ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert target", recorder), null);
                    }
                }
                finally {
                    recorder.clear();
                }
                this.targetName = (String)ScriptBytecodeAdapter.castToType(target, $get$$class$java$lang$String());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Command getTarget() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object command = $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), this.targetName);
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(command, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 16);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert command != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        return (Command)ScriptBytecodeAdapter.castToType(command, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    protected List createCompletors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this)), $get$$class$java$util$List());
    }
    
    public String getDescription() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), "info.alias_to", this.targetName), $get$$class$java$lang$String());
    }
    
    public String getUsage() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this)), $get$$class$java$lang$String());
    }
    
    public String getHelp() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callGroovyObjectGetProperty(this)), $get$$class$java$lang$String());
    }
    
    public boolean getHidden() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[10].callGetProperty($getCallSiteArray[11].callGroovyObjectGetProperty(this)), $get$$class$java$lang$Boolean()));
    }
    
    public Object execute(final List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGroovyObjectGetProperty(this), args), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$CommandAlias()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CommandAlias.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CommandAlias.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CommandAlias.__timeStamp__239_neverHappen1292524204708 = 0L;
        CommandAlias.__timeStamp = 1292524204708L;
    }
    
    public final String getTargetName() {
        return this.targetName;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$CommandAlias(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CommandAlias.$callSiteArray == null || ($createCallSiteArray = CommandAlias.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CommandAlias.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CommandAlias.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CommandAlias.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = CommandAlias.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (CommandAlias.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CommandAlias.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CommandAlias.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = CommandAlias.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (CommandAlias.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = CommandAlias.$class$java$util$List) == null) {
            $class$java$util$List = (CommandAlias.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = CommandAlias.$class$java$lang$String) == null) {
            $class$java$lang$String = (CommandAlias.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = CommandAlias.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (CommandAlias.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandAlias() {
        Class $class$org$codehaus$groovy$tools$shell$CommandAlias;
        if (($class$org$codehaus$groovy$tools$shell$CommandAlias = CommandAlias.$class$org$codehaus$groovy$tools$shell$CommandAlias) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandAlias = (CommandAlias.$class$org$codehaus$groovy$tools$shell$CommandAlias = class$("org.codehaus.groovy.tools.shell.CommandAlias"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandAlias;
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
