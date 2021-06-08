// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Iterator;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Set;
import java.util.List;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public class CommandRegistry implements GroovyObject
{
    protected final Logger log;
    private final List commands;
    private final Set names;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203950;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    private static /* synthetic */ Class $class$java$util$Iterator;
    private static /* synthetic */ Class $class$java$util$TreeSet;
    
    public CommandRegistry() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.commands = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.names = (Set)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$java$util$TreeSet()), $get$$class$java$util$Set());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Command register(final Command command) {
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
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(this.names, $getCallSiteArray[3].callGetProperty(command)))) {
            ScriptBytecodeAdapter.assertFailed("names.contains(command.name)", new GStringImpl(new Object[] { $getCallSiteArray[4].callGetProperty(command) }, new String[] { "Duplicate comamnd name: ", "" }));
        }
        $getCallSiteArray[5].call(this.names, $getCallSiteArray[6].callGetProperty(command));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(this.names, $getCallSiteArray[8].callGetProperty(command)))) {
            ScriptBytecodeAdapter.assertFailed("names.contains(command.shortcut)", new GStringImpl(new Object[] { $getCallSiteArray[9].callGetProperty(command) }, new String[] { "Duplicate command shortcut: ", "" }));
        }
        $getCallSiteArray[10].call(this.names, $getCallSiteArray[11].callGetProperty(command));
        $getCallSiteArray[12].call(this.commands, command);
        ScriptBytecodeAdapter.setProperty(this, $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry(), command, "registry");
        $getCallSiteArray[13].callSafe($getCallSiteArray[14].callGetProperty(command), new CommandRegistry$_register_closure1(this, this));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[15].callGetProperty(this.log))) {
            $getCallSiteArray[16].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[17].callGetProperty(command) }, new String[] { "Registered command: ", "" }));
        }
        return (Command)ScriptBytecodeAdapter.castToType(command, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    public Object leftShift(final Command command) {
        return $getCallSiteArray()[18].callCurrent(this, command);
    }
    
    public Command find(final String name) {
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
        Object c = null;
        final Object call = $getCallSiteArray[19].call(this.commands);
        while (((Iterator)call).hasNext()) {
            c = ((Iterator<Object>)call).next();
            if (ScriptBytecodeAdapter.isCase(name, ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[20].callGetProperty(c), $getCallSiteArray[21].callGetProperty(c) }))) {
                return (Command)ScriptBytecodeAdapter.castToType(c, $get$$class$org$codehaus$groovy$tools$shell$Command());
            }
        }
        return (Command)ScriptBytecodeAdapter.castToType(null, $get$$class$org$codehaus$groovy$tools$shell$Command());
    }
    
    public void remove(final Command command) {
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
        $getCallSiteArray[22].call(this.commands, command);
        $getCallSiteArray[23].call(this.names, $getCallSiteArray[24].callGetProperty(command));
        $getCallSiteArray[25].call(this.names, $getCallSiteArray[26].callGetProperty(command));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[27].callGetProperty(this.log))) {
            $getCallSiteArray[28].call(this.log, new GStringImpl(new Object[] { $getCallSiteArray[29].callGetProperty(command) }, new String[] { "Removed command: ", "" }));
        }
    }
    
    public List commands() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.commands, $get$$class$java$util$List());
    }
    
    public Object getProperty(final String name) {
        return $getCallSiteArray()[30].callCurrent(this, name);
    }
    
    public Iterator iterator() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (Iterator)ScriptBytecodeAdapter.castToType($getCallSiteArray[31].call($getCallSiteArray[32].callCurrent(this)), $get$$class$java$util$Iterator());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CommandRegistry.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CommandRegistry.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CommandRegistry.__timeStamp__239_neverHappen1292524203950 = 0L;
        CommandRegistry.__timeStamp = 1292524203950L;
    }
    
    public final List getCommands() {
        return this.commands;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[33];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$CommandRegistry(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CommandRegistry.$callSiteArray == null || ($createCallSiteArray = CommandRegistry.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CommandRegistry.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CommandRegistry.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CommandRegistry.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandRegistry() {
        Class $class$org$codehaus$groovy$tools$shell$CommandRegistry;
        if (($class$org$codehaus$groovy$tools$shell$CommandRegistry = CommandRegistry.$class$org$codehaus$groovy$tools$shell$CommandRegistry) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandRegistry = (CommandRegistry.$class$org$codehaus$groovy$tools$shell$CommandRegistry = class$("org.codehaus.groovy.tools.shell.CommandRegistry"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandRegistry;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = CommandRegistry.$class$java$util$Set) == null) {
            $class$java$util$Set = (CommandRegistry.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = CommandRegistry.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (CommandRegistry.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = CommandRegistry.$class$java$util$List) == null) {
            $class$java$util$List = (CommandRegistry.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = CommandRegistry.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (CommandRegistry.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Iterator() {
        Class $class$java$util$Iterator;
        if (($class$java$util$Iterator = CommandRegistry.$class$java$util$Iterator) == null) {
            $class$java$util$Iterator = (CommandRegistry.$class$java$util$Iterator = class$("java.util.Iterator"));
        }
        return $class$java$util$Iterator;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$TreeSet() {
        Class $class$java$util$TreeSet;
        if (($class$java$util$TreeSet = CommandRegistry.$class$java$util$TreeSet) == null) {
            $class$java$util$TreeSet = (CommandRegistry.$class$java$util$TreeSet = class$("java.util.TreeSet"));
        }
        return $class$java$util$TreeSet;
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
