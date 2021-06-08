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
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class ExitCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204799;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ExitCommand;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ExitNotification;
    
    public ExitCommand(final Shell shell) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "exit";
        arguments[2] = "\\x";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                $getCallSiteArray[0].callCurrent(this, "quit", "\\q");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public Object execute(final List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[1].callCurrent(this, args);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), $getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this), "info.bye"));
        }
        throw (Throwable)$getCallSiteArray[9].callConstructor($get$$class$org$codehaus$groovy$tools$shell$ExitNotification(), ExitCommand.$const$0);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$ExitCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ExitCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ExitCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ExitCommand.__timeStamp__239_neverHappen1292524204799 = 0L;
        ExitCommand.__timeStamp = 1292524204799L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ExitCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ExitCommand.$callSiteArray == null || ($createCallSiteArray = ExitCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ExitCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ExitCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ExitCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = ExitCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (ExitCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ExitCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ExitCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$ExitCommand = ExitCommand.$class$org$codehaus$groovy$tools$shell$commands$ExitCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ExitCommand = (ExitCommand.$class$org$codehaus$groovy$tools$shell$commands$ExitCommand = class$("org.codehaus.groovy.tools.shell.commands.ExitCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ExitCommand;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ExitNotification() {
        Class $class$org$codehaus$groovy$tools$shell$ExitNotification;
        if (($class$org$codehaus$groovy$tools$shell$ExitNotification = ExitCommand.$class$org$codehaus$groovy$tools$shell$ExitNotification) == null) {
            $class$org$codehaus$groovy$tools$shell$ExitNotification = (ExitCommand.$class$org$codehaus$groovy$tools$shell$ExitNotification = class$("org.codehaus.groovy.tools.shell.ExitNotification"));
        }
        return $class$org$codehaus$groovy$tools$shell$ExitNotification;
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
