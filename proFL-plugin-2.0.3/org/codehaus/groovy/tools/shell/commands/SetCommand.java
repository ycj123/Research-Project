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
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class SetCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204840;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$SetCommand;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    
    public SetCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "set";
        arguments[2] = "\\=";
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
    
    @Override
    protected List createCompletors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object loader = new SetCommand$_createCompletors_closure1(this, this);
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor(), loader), null }), $get$$class$java$util$List());
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
        if (!ScriptBytecodeAdapter.compareEqual($getCallSiteArray[1].call(args), SetCommand.$const$0)) {
            if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[11].call(args), SetCommand.$const$1)) {
                $getCallSiteArray[12].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[13].callGroovyObjectGetProperty(this) }, new String[] { "Command '", "' requires arguments: <name> [<value>]" }));
            }
            final Object name = $getCallSiteArray[14].call(args, SetCommand.$const$0);
            Object value = null;
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[15].call(args), SetCommand.$const$2)) {
                value = Boolean.TRUE;
            }
            else {
                value = $getCallSiteArray[16].call(args, SetCommand.$const$2);
            }
            $getCallSiteArray[17].call($getCallSiteArray[18].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { name, value }, new String[] { "Setting preference: ", "=", "" }));
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[19].call($get$$class$org$codehaus$groovy$tools$shell$util$Preferences(), name, $getCallSiteArray[20].call($get$$class$java$lang$String(), value)), $get$$class$java$lang$Object());
        }
        final Object keys = $getCallSiteArray[2].call($get$$class$org$codehaus$groovy$tools$shell$util$Preferences());
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[3].call(keys), SetCommand.$const$0)) {
            $getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), "No preferences are set");
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callGroovyObjectGetProperty(this)), "Preferences:");
        $getCallSiteArray[10].call(keys, new SetCommand$_execute_closure2(this, this));
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$SetCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SetCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SetCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        SetCommand.__timeStamp__239_neverHappen1292524204840 = 0L;
        SetCommand.__timeStamp = 1292524204840L;
        $const$2 = 1;
        $const$1 = 2;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[21];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$SetCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SetCommand.$callSiteArray == null || ($createCallSiteArray = SetCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SetCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SetCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SetCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = SetCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (SetCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SetCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SetCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = SetCommand.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (SetCommand.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = SetCommand.$class$java$util$List) == null) {
            $class$java$util$List = (SetCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$SetCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$SetCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$SetCommand = SetCommand.$class$org$codehaus$groovy$tools$shell$commands$SetCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$SetCommand = (SetCommand.$class$org$codehaus$groovy$tools$shell$commands$SetCommand = class$("org.codehaus.groovy.tools.shell.commands.SetCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$SetCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = SetCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (SetCommand.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = SetCommand.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = (SetCommand.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = class$("org.codehaus.groovy.tools.shell.util.SimpleCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
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
