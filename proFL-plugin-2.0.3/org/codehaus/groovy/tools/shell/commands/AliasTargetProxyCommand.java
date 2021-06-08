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
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class AliasTargetProxyCommand extends CommandSupport
{
    private static int counter;
    private final List args;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204791;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public AliasTargetProxyCommand(final Shell shell, final String name, final List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = name;
        final int n = 2;
        final CallSite callSite = $getCallSiteArray[0];
        final String s = "\\a";
        final Object box = DefaultTypeTransformation.box(AliasTargetProxyCommand.counter);
        AliasTargetProxyCommand.counter = DefaultTypeTransformation.intUnbox($getCallSiteArray[1].call(DefaultTypeTransformation.box(AliasTargetProxyCommand.counter)));
        arguments[n] = callSite.call(s, box);
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
                    if (DefaultTypeTransformation.booleanUnbox(recorder.record(args, 8))) {
                        recorder.clear();
                    }
                    else {
                        ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert args", recorder), null);
                    }
                }
                finally {
                    recorder.clear();
                }
                this.args = (List)ScriptBytecodeAdapter.castToType(args, $get$$class$java$util$List());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public String getDescription() {
        return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { $getCallSiteArray()[2].call(this.args, " ") }, new String[] { "User defined alias to: @|bold ", "|@" }), $get$$class$java$lang$String());
    }
    
    public String getUsage() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType("", $get$$class$java$lang$String());
    }
    
    public String getHelp() {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[3].callGroovyObjectGetProperty(this), $get$$class$java$lang$String());
    }
    
    public Object execute(List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        args = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(this.args, args), $get$$class$java$util$List());
        $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { args }, new String[] { "Executing with args: ", "" }));
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this), $getCallSiteArray[9].call(args, " ")), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AliasTargetProxyCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AliasTargetProxyCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AliasTargetProxyCommand.__timeStamp__239_neverHappen1292524204791 = 0L;
        AliasTargetProxyCommand.__timeStamp = 1292524204791L;
        $const$0 = 0;
        AliasTargetProxyCommand.counter = DefaultTypeTransformation.intUnbox(0);
    }
    
    public final List getArgs() {
        return this.args;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AliasTargetProxyCommand.$callSiteArray == null || ($createCallSiteArray = AliasTargetProxyCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AliasTargetProxyCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AliasTargetProxyCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AliasTargetProxyCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = AliasTargetProxyCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = (AliasTargetProxyCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = class$("org.codehaus.groovy.tools.shell.commands.AliasTargetProxyCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = AliasTargetProxyCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (AliasTargetProxyCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AliasTargetProxyCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AliasTargetProxyCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AliasTargetProxyCommand.$class$java$util$List) == null) {
            $class$java$util$List = (AliasTargetProxyCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AliasTargetProxyCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (AliasTargetProxyCommand.$class$java$lang$String = class$("java.lang.String"));
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
