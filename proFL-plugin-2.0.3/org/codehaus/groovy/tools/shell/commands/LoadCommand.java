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
import java.net.MalformedURLException;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.net.URL;
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

public class LoadCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204812;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$jline$FileNameCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$LoadCommand;
    private static /* synthetic */ Class $class$java$net$URL;
    
    public LoadCommand(final Shell shell) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "load";
        arguments[2] = "\\l";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                $getCallSiteArray[0].callCurrent(this, ".", "\\.");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected List createCompletors() {
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray()[1].callConstructor($get$$class$jline$FileNameCompletor()) }), $get$$class$java$util$List());
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
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[2].call(args), LoadCommand.$const$0)) {
            $getCallSiteArray[3].callCurrent(this, "Command 'load' requires at least one argument");
        }
        Object source = null;
        final Object call = $getCallSiteArray[4].call(args);
        while (((Iterator)call).hasNext()) {
            source = ((Iterator<Object>)call).next();
            URL url = (URL)ScriptBytecodeAdapter.castToType(null, $get$$class$java$net$URL());
            $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { url }, new String[] { "Attempting to load: ", "" }));
            try {
                url = (URL)$getCallSiteArray[7].callConstructor($get$$class$java$net$URL(), new GStringImpl(new Object[] { source }, new String[] { "", "" }));
            }
            catch (MalformedURLException e) {
                final Object file = $getCallSiteArray[8].callConstructor($get$$class$java$io$File(), new GStringImpl(new Object[] { source }, new String[] { "", "" }));
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(file))) {
                    $getCallSiteArray[10].callCurrent(this, new GStringImpl(new Object[] { file }, new String[] { "File not found: ", "" }));
                }
                url = (URL)ScriptBytecodeAdapter.castToType($getCallSiteArray[11].call($getCallSiteArray[12].call(file)), $get$$class$java$net$URL());
            }
            $getCallSiteArray[13].callCurrent(this, url);
        }
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    public void load(final URL url) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(url, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 12);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert url != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].callGetProperty($getCallSiteArray[15].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[16].call($getCallSiteArray[17].callGetProperty($getCallSiteArray[18].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { url }, new String[] { "Loading: ", "" }));
        }
        $getCallSiteArray[19].call(url, new LoadCommand$_load_closure1(this, this));
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$LoadCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = LoadCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (LoadCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        LoadCommand.__timeStamp__239_neverHappen1292524204812 = 0L;
        LoadCommand.__timeStamp = 1292524204812L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[20];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$LoadCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LoadCommand.$callSiteArray == null || ($createCallSiteArray = LoadCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LoadCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = LoadCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (LoadCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = LoadCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (LoadCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = LoadCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (LoadCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = LoadCommand.$class$java$util$List) == null) {
            $class$java$util$List = (LoadCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = LoadCommand.$class$java$io$File) == null) {
            $class$java$io$File = (LoadCommand.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$jline$FileNameCompletor() {
        Class $class$jline$FileNameCompletor;
        if (($class$jline$FileNameCompletor = LoadCommand.$class$jline$FileNameCompletor) == null) {
            $class$jline$FileNameCompletor = (LoadCommand.$class$jline$FileNameCompletor = class$("jline.FileNameCompletor"));
        }
        return $class$jline$FileNameCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$LoadCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$LoadCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$LoadCommand = LoadCommand.$class$org$codehaus$groovy$tools$shell$commands$LoadCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$LoadCommand = (LoadCommand.$class$org$codehaus$groovy$tools$shell$commands$LoadCommand = class$("org.codehaus.groovy.tools.shell.commands.LoadCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$LoadCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$net$URL() {
        Class $class$java$net$URL;
        if (($class$java$net$URL = LoadCommand.$class$java$net$URL) == null) {
            $class$java$net$URL = (LoadCommand.$class$java$net$URL = class$("java.net.URL"));
        }
        return $class$java$net$URL;
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
