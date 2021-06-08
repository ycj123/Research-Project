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

public class SaveCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204818;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$SaveCommand;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$jline$FileNameCompletor;
    
    public SaveCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "save";
        arguments[2] = "\\s";
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
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray()[0].callConstructor($get$$class$jline$FileNameCompletor()), null }), $get$$class$java$util$List());
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
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[1].call(args), SaveCommand.$const$0)) {
            $getCallSiteArray[2].callCurrent(this, "Command 'save' requires a single file argument");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this)), "Buffer is empty");
            return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
        }
        final Object file = $getCallSiteArray[8].callConstructor($get$$class$java$io$File(), new GStringImpl(new Object[] { $getCallSiteArray[9].call(args, SaveCommand.$const$1) }, new String[] { "", "" }));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].callGetProperty($getCallSiteArray[11].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[12].call($getCallSiteArray[13].callGetProperty($getCallSiteArray[14].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { file }, new String[] { "Saving current buffer to file: ", "" }));
        }
        final Object dir = $getCallSiteArray[15].callGetProperty(file);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(dir) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call(dir))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[17].call($getCallSiteArray[18].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { dir }, new String[] { "Creating parent directory path: ", "" }));
            $getCallSiteArray[19].call(dir);
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call(file, $getCallSiteArray[21].call($getCallSiteArray[22].callGroovyObjectGetProperty(this), $getCallSiteArray[23].callGroovyObjectGetProperty(this))), $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$SaveCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SaveCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SaveCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        SaveCommand.__timeStamp__239_neverHappen1292524204818 = 0L;
        SaveCommand.__timeStamp = 1292524204818L;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[24];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$SaveCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SaveCommand.$callSiteArray == null || ($createCallSiteArray = SaveCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SaveCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SaveCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SaveCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = SaveCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (SaveCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SaveCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SaveCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = SaveCommand.$class$java$util$List) == null) {
            $class$java$util$List = (SaveCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$SaveCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$SaveCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$SaveCommand = SaveCommand.$class$org$codehaus$groovy$tools$shell$commands$SaveCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$SaveCommand = (SaveCommand.$class$org$codehaus$groovy$tools$shell$commands$SaveCommand = class$("org.codehaus.groovy.tools.shell.commands.SaveCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$SaveCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = SaveCommand.$class$java$io$File) == null) {
            $class$java$io$File = (SaveCommand.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$jline$FileNameCompletor() {
        Class $class$jline$FileNameCompletor;
        if (($class$jline$FileNameCompletor = SaveCommand.$class$jline$FileNameCompletor) == null) {
            $class$jline$FileNameCompletor = (SaveCommand.$class$jline$FileNameCompletor = class$("jline.FileNameCompletor"));
        }
        return $class$jline$FileNameCompletor;
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
