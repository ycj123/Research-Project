// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import jline.Completor;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;
import jline.MultiCompletor;

public class CommandsMultiCompletor extends MultiCompletor implements GroovyObject
{
    protected final Logger log;
    private List list;
    private boolean dirty;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204777;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$jline$MultiCompletor;
    private static /* synthetic */ Class array$$class$jline$Completor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
    
    public CommandsMultiCompletor() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.list = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.dirty = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object leftShift(final Command command) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(command, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert command", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final Object c = $getCallSiteArray[2].callGetProperty(command);
        if (DefaultTypeTransformation.booleanUnbox(c)) {
            $getCallSiteArray[3].call(this.list, c);
            $getCallSiteArray[4].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[5].call(this.list), $getCallSiteArray[6].callGetProperty(command) }, new String[] { "Added completor[", "] for command: ", "" }));
            final Boolean true = Boolean.TRUE;
            this.dirty = DefaultTypeTransformation.booleanUnbox(true);
            return true;
        }
        return null;
    }
    
    public void refresh() {
        $getCallSiteArray()[7].call(this.log, "Refreshing the completor list");
        ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.asType(this.list, $get$array$$class$jline$Completor()), $get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor(), this, "completors");
        this.dirty = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
    }
    
    public int complete(final String buffer, final int pos, final List cand) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(buffer, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 15);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert buffer != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.dirty))) {
            $getCallSiteArray[8].callCurrent(this);
        }
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$jline$MultiCompletor(), this, "complete", new Object[] { buffer, DefaultTypeTransformation.box(pos), cand }), $get$$class$java$lang$Integer()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CommandsMultiCompletor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CommandsMultiCompletor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CommandsMultiCompletor.__timeStamp__239_neverHappen1292524204777 = 0L;
        CommandsMultiCompletor.__timeStamp = 1292524204777L;
    }
    
    public List getList() {
        return this.list;
    }
    
    public void setList(final List list) {
        this.list = list;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CommandsMultiCompletor.$callSiteArray == null || ($createCallSiteArray = CommandsMultiCompletor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CommandsMultiCompletor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = CommandsMultiCompletor.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (CommandsMultiCompletor.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CommandsMultiCompletor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CommandsMultiCompletor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$jline$MultiCompletor() {
        Class $class$jline$MultiCompletor;
        if (($class$jline$MultiCompletor = CommandsMultiCompletor.$class$jline$MultiCompletor) == null) {
            $class$jline$MultiCompletor = (CommandsMultiCompletor.$class$jline$MultiCompletor = class$("jline.MultiCompletor"));
        }
        return $class$jline$MultiCompletor;
    }
    
    private static /* synthetic */ Class $get$array$$class$jline$Completor() {
        Class array$$class$jline$Completor;
        if ((array$$class$jline$Completor = CommandsMultiCompletor.array$$class$jline$Completor) == null) {
            array$$class$jline$Completor = (CommandsMultiCompletor.array$$class$jline$Completor = class$("[Ljline.Completor;"));
        }
        return array$$class$jline$Completor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = CommandsMultiCompletor.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (CommandsMultiCompletor.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = CommandsMultiCompletor.$class$java$util$List) == null) {
            $class$java$util$List = (CommandsMultiCompletor.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
        if (($class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = CommandsMultiCompletor.$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = (CommandsMultiCompletor.$class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor = class$("org.codehaus.groovy.tools.shell.CommandsMultiCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandsMultiCompletor;
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
