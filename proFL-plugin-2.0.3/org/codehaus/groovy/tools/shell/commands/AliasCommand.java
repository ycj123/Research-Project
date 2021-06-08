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

public class AliasCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204789;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$AliasCommand;
    
    public AliasCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "alias";
        arguments[2] = "\\a";
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
        if (ScriptBytecodeAdapter.compareLessThan($getCallSiteArray[0].call(args), AliasCommand.$const$0)) {
            $getCallSiteArray[1].callCurrent(this, "Command 'alias' requires at least 2 arguments");
        }
        final String name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(args, AliasCommand.$const$1), $get$$class$java$lang$String());
        final List target = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(args, ScriptBytecodeAdapter.createRange(AliasCommand.$const$2, AliasCommand.$const$3, true)), $get$$class$java$util$List());
        Object command = $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), name);
        if (DefaultTypeTransformation.booleanUnbox(command)) {
            if (command instanceof AliasTargetProxyCommand) {
                $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { name }, new String[] { "Rebinding alias: ", "" }));
                $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), command);
            }
            else {
                $getCallSiteArray[10].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[11].callGetProperty(command) }, new String[] { "Can not rebind non-user aliased command: ", "" }));
            }
        }
        $getCallSiteArray[12].call($getCallSiteArray[13].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { name, target }, new String[] { "Creating alias '", "' to: ", "" }));
        command = $getCallSiteArray[14].call($getCallSiteArray[15].callGroovyObjectGetProperty(this), $getCallSiteArray[16].callConstructor($get$$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand(), $getCallSiteArray[17].callGroovyObjectGetProperty(this), name, target));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[18].callGroovyObjectGetProperty($getCallSiteArray[19].callGroovyObjectGetProperty(this)))) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call($getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGroovyObjectGetProperty($getCallSiteArray[23].callGroovyObjectGetProperty(this))), command), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$AliasCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AliasCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AliasCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AliasCommand.__timeStamp__239_neverHappen1292524204789 = 0L;
        AliasCommand.__timeStamp = 1292524204789L;
        $const$3 = -1;
        $const$2 = 1;
        $const$1 = 0;
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$AliasCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AliasCommand.$callSiteArray == null || ($createCallSiteArray = AliasCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AliasCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AliasCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AliasCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = AliasCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = (AliasCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand = class$("org.codehaus.groovy.tools.shell.commands.AliasTargetProxyCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$AliasTargetProxyCommand;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = AliasCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (AliasCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AliasCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AliasCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AliasCommand.$class$java$util$List) == null) {
            $class$java$util$List = (AliasCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AliasCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (AliasCommand.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$AliasCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$AliasCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$AliasCommand = AliasCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$AliasCommand = (AliasCommand.$class$org$codehaus$groovy$tools$shell$commands$AliasCommand = class$("org.codehaus.groovy.tools.shell.commands.AliasCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$AliasCommand;
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
