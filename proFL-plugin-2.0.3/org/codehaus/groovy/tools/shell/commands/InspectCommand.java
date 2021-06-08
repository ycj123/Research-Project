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
import java.awt.HeadlessException;
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

public class InspectCommand extends CommandSupport
{
    private Object lafInitialized;
    private Object headless;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204809;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$awt$Frame;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ObjectBrowser;
    private static /* synthetic */ Class $class$javax$swing$UIManager;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommand;
    
    public InspectCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "inspect";
        arguments[2] = "\\n";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.lafInitialized = Boolean.FALSE;
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
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), null }), $get$$class$java$util$List());
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
        $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { args }, new String[] { "Inspecting w/args: ", "" }));
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[4].call(args), InspectCommand.$const$0)) {
            $getCallSiteArray[5].callCurrent(this, $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), "error.unexpected_args", $getCallSiteArray[8].call(args, " ")));
        }
        Object subject = null;
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[9].call(args), InspectCommand.$const$0)) {
            subject = $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty($getCallSiteArray[12].callGroovyObjectGetProperty(this)), $getCallSiteArray[13].call(args, InspectCommand.$const$1));
        }
        else {
            subject = $getCallSiteArray[14].call($getCallSiteArray[15].callGroovyObjectGetProperty($getCallSiteArray[16].callGroovyObjectGetProperty(this)), "_");
        }
        if (!DefaultTypeTransformation.booleanUnbox(subject)) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[17].call($getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callGroovyObjectGetProperty(this)), "Subject is null; nothing to inspect"), $get$$class$java$lang$Object());
        }
        if (!DefaultTypeTransformation.booleanUnbox(this.lafInitialized)) {
            this.lafInitialized = Boolean.TRUE;
            try {
                $getCallSiteArray[20].call($get$$class$javax$swing$UIManager(), $getCallSiteArray[21].call($get$$class$javax$swing$UIManager()));
                $getCallSiteArray[22].call($getCallSiteArray[23].callConstructor($get$$class$java$awt$Frame()));
                this.headless = Boolean.FALSE;
            }
            catch (HeadlessException he) {
                this.headless = Boolean.TRUE;
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(this.headless)) {
            $getCallSiteArray[24].call($getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGroovyObjectGetProperty(this)), "@|red ERROR:|@ Running in AWT Headless mode, 'inspect' is not available.");
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[27].callGetProperty($getCallSiteArray[28].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[29].call($getCallSiteArray[30].callGetProperty($getCallSiteArray[31].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { subject }, new String[] { "Launching object browser to inspect: ", "" }));
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[32].call($get$$class$groovy$inspect$swingui$ObjectBrowser(), subject), $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = InspectCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (InspectCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        InspectCommand.__timeStamp__239_neverHappen1292524204809 = 0L;
        InspectCommand.__timeStamp = 1292524204809L;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    public Object getLafInitialized() {
        return this.lafInitialized;
    }
    
    public void setLafInitialized(final Object lafInitialized) {
        this.lafInitialized = lafInitialized;
    }
    
    public Object getHeadless() {
        return this.headless;
    }
    
    public void setHeadless(final Object headless) {
        this.headless = headless;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[33];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (InspectCommand.$callSiteArray == null || ($createCallSiteArray = InspectCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            InspectCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = InspectCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (InspectCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = InspectCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (InspectCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Frame() {
        Class $class$java$awt$Frame;
        if (($class$java$awt$Frame = InspectCommand.$class$java$awt$Frame) == null) {
            $class$java$awt$Frame = (InspectCommand.$class$java$awt$Frame = class$("java.awt.Frame"));
        }
        return $class$java$awt$Frame;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = InspectCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (InspectCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ObjectBrowser() {
        Class $class$groovy$inspect$swingui$ObjectBrowser;
        if (($class$groovy$inspect$swingui$ObjectBrowser = InspectCommand.$class$groovy$inspect$swingui$ObjectBrowser) == null) {
            $class$groovy$inspect$swingui$ObjectBrowser = (InspectCommand.$class$groovy$inspect$swingui$ObjectBrowser = class$("groovy.inspect.swingui.ObjectBrowser"));
        }
        return $class$groovy$inspect$swingui$ObjectBrowser;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$UIManager() {
        Class $class$javax$swing$UIManager;
        if (($class$javax$swing$UIManager = InspectCommand.$class$javax$swing$UIManager) == null) {
            $class$javax$swing$UIManager = (InspectCommand.$class$javax$swing$UIManager = class$("javax.swing.UIManager"));
        }
        return $class$javax$swing$UIManager;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = InspectCommand.$class$java$util$List) == null) {
            $class$java$util$List = (InspectCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = InspectCommand.$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = (InspectCommand.$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.InspectCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$InspectCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$InspectCommand = InspectCommand.$class$org$codehaus$groovy$tools$shell$commands$InspectCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$InspectCommand = (InspectCommand.$class$org$codehaus$groovy$tools$shell$commands$InspectCommand = class$("org.codehaus.groovy.tools.shell.commands.InspectCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$InspectCommand;
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
