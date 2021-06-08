// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.tools.shell.BufferManager;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.tools.shell.Command;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class HelpCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204802;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommand;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    
    public HelpCommand(final Shell shell) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "help";
        arguments[2] = "\\h";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                $getCallSiteArray[0].callCurrent(this, "?", "\\?");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected List createCompletors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[1].callConstructor($get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor(), $getCallSiteArray[2].callGroovyObjectGetProperty(this)), null }), $get$$class$java$util$List());
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
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[3].call(args), HelpCommand.$const$0)) {
            $getCallSiteArray[4].callCurrent(this, $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), "error.unexpected_args", $getCallSiteArray[7].call(args, " ")));
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[8].call(args), HelpCommand.$const$0)) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].call(args, HelpCommand.$const$1)), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[11].callCurrent(this), $get$$class$java$lang$Object());
    }
    
    private void help(final String name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(name, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert name", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Command command = (Command)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGroovyObjectGetProperty(this), name), $get$$class$org$codehaus$groovy$tools$shell$Command());
        if (!DefaultTypeTransformation.booleanUnbox(command)) {
            $getCallSiteArray[14].callCurrent(this, new GStringImpl(new Object[] { name }, new String[] { "No such command: ", "" }));
        }
        $getCallSiteArray[15].call($getCallSiteArray[16].callGetProperty($getCallSiteArray[17].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[18].call($getCallSiteArray[19].callGetProperty($getCallSiteArray[20].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[21].callGetProperty(command), $getCallSiteArray[22].callGetProperty(command) }, new String[] { "usage: @|bold ", "|@ ", "" }));
        $getCallSiteArray[23].call($getCallSiteArray[24].callGetProperty($getCallSiteArray[25].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[26].call($getCallSiteArray[27].callGetProperty($getCallSiteArray[28].callGroovyObjectGetProperty(this)), $getCallSiteArray[29].callGetProperty(command));
        $getCallSiteArray[30].call($getCallSiteArray[31].callGetProperty($getCallSiteArray[32].callGroovyObjectGetProperty(this)));
    }
    
    private void list() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Integer maxName = HelpCommand.$const$1;
        Integer maxShortcut = (Integer)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Integer());
        Object command = null;
        final Object call = $getCallSiteArray[33].call($getCallSiteArray[34].callGroovyObjectGetProperty(this));
        while (((Iterator)call).hasNext()) {
            command = ((Iterator<Object>)call).next();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[35].callGetProperty(command))) {
                continue;
            }
            if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[36].call($getCallSiteArray[37].callGetProperty(command)), maxName)) {
                maxName = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[38].call($getCallSiteArray[39].callGetProperty(command)), $get$$class$java$lang$Integer());
            }
            if (!ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[40].call($getCallSiteArray[41].callGetProperty(command)), maxShortcut)) {
                continue;
            }
            maxShortcut = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[42].call($getCallSiteArray[43].callGetProperty(command)), $get$$class$java$lang$Integer());
        }
        $getCallSiteArray[44].call($getCallSiteArray[45].callGetProperty($getCallSiteArray[46].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[47].call($getCallSiteArray[48].callGetProperty($getCallSiteArray[49].callGroovyObjectGetProperty(this)), "For information about @|green Groovy|@, visit:");
        $getCallSiteArray[50].call($getCallSiteArray[51].callGetProperty($getCallSiteArray[52].callGroovyObjectGetProperty(this)), "    @|cyan http://groovy.codehaus.org|@ ");
        $getCallSiteArray[53].call($getCallSiteArray[54].callGetProperty($getCallSiteArray[55].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[56].call($getCallSiteArray[57].callGetProperty($getCallSiteArray[58].callGroovyObjectGetProperty(this)), "Available commands:");
        command = null;
        final Object call2 = $getCallSiteArray[59].call($getCallSiteArray[60].callGroovyObjectGetProperty(this));
        while (((Iterator)call2).hasNext()) {
            command = ((Iterator<Object>)call2).next();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[61].callGetProperty(command))) {
                continue;
            }
            final Object n = $getCallSiteArray[62].call($getCallSiteArray[63].callGetProperty(command), maxName, " ");
            final Object s = $getCallSiteArray[64].call($getCallSiteArray[65].callGetProperty(command), maxShortcut, " ");
            final Object d = $getCallSiteArray[66].callGetProperty(command);
            $getCallSiteArray[67].call($getCallSiteArray[68].callGetProperty($getCallSiteArray[69].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { n, s, d }, new String[] { "  @|bold ", "|@  (@|bold ", "|@) ", "" }));
        }
        $getCallSiteArray[70].call($getCallSiteArray[71].callGetProperty($getCallSiteArray[72].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[73].call($getCallSiteArray[74].callGetProperty($getCallSiteArray[75].callGroovyObjectGetProperty(this)), "For help on a specific command type:");
        $getCallSiteArray[76].call($getCallSiteArray[77].callGetProperty($getCallSiteArray[78].callGroovyObjectGetProperty(this)), "    help @|bold command|@ ");
        $getCallSiteArray[79].call($getCallSiteArray[80].callGetProperty($getCallSiteArray[81].callGroovyObjectGetProperty(this)));
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HelpCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HelpCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HelpCommand.__timeStamp__239_neverHappen1292524204802 = 0L;
        HelpCommand.__timeStamp = 1292524204802L;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[82];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpCommand.$callSiteArray == null || ($createCallSiteArray = HelpCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$HelpCommand = HelpCommand.$class$org$codehaus$groovy$tools$shell$commands$HelpCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$HelpCommand = (HelpCommand.$class$org$codehaus$groovy$tools$shell$commands$HelpCommand = class$("org.codehaus.groovy.tools.shell.commands.HelpCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$HelpCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = HelpCommand.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (HelpCommand.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HelpCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HelpCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = HelpCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (HelpCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HelpCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HelpCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = HelpCommand.$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = (HelpCommand.$class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.HelpCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$HelpCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = HelpCommand.$class$java$util$List) == null) {
            $class$java$util$List = (HelpCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = HelpCommand.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (HelpCommand.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
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
