// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyClassLoader;
import jline.History;
import java.util.Map;
import groovy.lang.Binding;
import groovy.lang.Reference;
import jline.Completor;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public abstract class CommandSupport implements Command, GroovyObject
{
    protected static final String NEWLINE;
    protected final Logger log;
    protected final MessageSource messages;
    private final String name;
    private final String shortcut;
    protected final Shell shell;
    protected final IO io;
    protected CommandRegistry registry;
    private final List aliases;
    private boolean hidden;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203957;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$jline$History;
    private static /* synthetic */ Class $class$groovy$lang$Binding;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$jline$Completor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandException;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$jline$NullCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandAlias;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$jline$ArgumentCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$BufferManager;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Shell;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    protected CommandSupport(final Shell shell, final String name, final String shortcut) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.messages = (MessageSource)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$MessageSource(), $getCallSiteArray[1].callGroovyObjectGetProperty(this), $get$$class$org$codehaus$groovy$tools$shell$CommandSupport()), $get$$class$org$codehaus$groovy$tools$shell$util$MessageSource());
        this.aliases = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.hidden = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(shell, 8))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert shell", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder2.record(name, 8))) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert name", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        final ValueRecorder recorder3 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder3.record(shortcut, 8))) {
                recorder3.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert shortcut", recorder3), null);
            }
        }
        finally {
            recorder3.clear();
        }
        this.log = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[3].callGroovyObjectGetProperty(this), name), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.shell = (Shell)ScriptBytecodeAdapter.castToType(shell, $get$$class$org$codehaus$groovy$tools$shell$Shell());
        this.io = (IO)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callGroovyObjectGetProperty(shell), $get$$class$org$codehaus$groovy$tools$shell$IO());
        this.name = (String)ScriptBytecodeAdapter.castToType(name, $get$$class$java$lang$String());
        this.shortcut = (String)ScriptBytecodeAdapter.castToType(shortcut, $get$$class$java$lang$String());
    }
    
    public String getDescription() {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[5].call(this.messages, "command.description"), $get$$class$java$lang$String());
    }
    
    public String getUsage() {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[6].call(this.messages, "command.usage"), $get$$class$java$lang$String());
    }
    
    public String getHelp() {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[7].call(this.messages, "command.help"), $get$$class$java$lang$String());
    }
    
    protected List createCompletors() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(null, $get$$class$java$util$List());
    }
    
    public Completor getCompletor() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.hidden))) {
            return (Completor)ScriptBytecodeAdapter.castToType(null, $get$$class$jline$Completor());
        }
        final Object list = new Reference(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[8].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor(), this.name, this.shortcut) }));
        final Object completors = $getCallSiteArray[9].callCurrent(this);
        if (DefaultTypeTransformation.booleanUnbox(completors)) {
            $getCallSiteArray[10].call(completors, new CommandSupport$_getCompletor_closure1(this, this, (Reference<Object>)list));
        }
        else {
            $getCallSiteArray[11].call(((Reference<Object>)list).get(), $getCallSiteArray[12].callConstructor($get$$class$jline$NullCompletor()));
        }
        return (Completor)ScriptBytecodeAdapter.castToType($getCallSiteArray[13].callConstructor($get$$class$jline$ArgumentCompletor(), ((Reference<Object>)list).get()), $get$$class$jline$Completor());
    }
    
    protected void alias(final String name, final String shortcut) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[14].call(this.aliases, $getCallSiteArray[15].callConstructor($get$$class$org$codehaus$groovy$tools$shell$CommandAlias(), this.shell, name, shortcut, this.name));
    }
    
    protected void fail(final String msg) {
        throw (Throwable)$getCallSiteArray()[16].callConstructor($get$$class$org$codehaus$groovy$tools$shell$CommandException(), this, msg);
    }
    
    protected void fail(final String msg, final Throwable cause) {
        throw (Throwable)$getCallSiteArray()[17].callConstructor($get$$class$org$codehaus$groovy$tools$shell$CommandException(), this, msg, cause);
    }
    
    protected void assertNoArguments(final List args) {
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
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[18].call(args), CommandSupport.$const$0)) {
            $getCallSiteArray[19].callCurrent(this, $getCallSiteArray[20].call(this.messages, "error.unexpected_args", $getCallSiteArray[21].call(args, " ")));
        }
    }
    
    protected BufferManager getBuffers() {
        return (BufferManager)ScriptBytecodeAdapter.castToType($getCallSiteArray()[22].callGroovyObjectGetProperty(this.shell), $get$$class$org$codehaus$groovy$tools$shell$BufferManager());
    }
    
    protected List getBuffer() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[23].call($getCallSiteArray[24].callGroovyObjectGetProperty(this.shell)), $get$$class$java$util$List());
    }
    
    protected List getImports() {
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray()[25].callGroovyObjectGetProperty(this.shell), $get$$class$java$util$List());
    }
    
    protected Binding getBinding() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (Binding)ScriptBytecodeAdapter.castToType($getCallSiteArray[26].callGetProperty($getCallSiteArray[27].callGroovyObjectGetProperty(this.shell)), $get$$class$groovy$lang$Binding());
    }
    
    protected Map getVariables() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray[28].callGroovyObjectGetProperty($getCallSiteArray[29].callGroovyObjectGetProperty(this)), $get$$class$java$util$Map());
    }
    
    protected History getHistory() {
        return (History)ScriptBytecodeAdapter.castToType($getCallSiteArray()[30].callGroovyObjectGetProperty(this.shell), $get$$class$jline$History());
    }
    
    protected GroovyClassLoader getClassLoader() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (GroovyClassLoader)ScriptBytecodeAdapter.castToType($getCallSiteArray[31].callGetProperty($getCallSiteArray[32].callGroovyObjectGetProperty(this.shell)), $get$$class$groovy$lang$GroovyClassLoader());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$CommandSupport()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CommandSupport.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CommandSupport.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public MetaClass getMetaClass() {
        final MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
    
    public Object invokeMethod(final String s, final Object o) {
        return this.getMetaClass().invokeMethod(this, s, o);
    }
    
    public Object getProperty(final String s) {
        return this.getMetaClass().getProperty(this, s);
    }
    
    public void setProperty(final String s, final Object o) {
        this.getMetaClass().setProperty(this, s, o);
    }
    
    static {
        CommandSupport.__timeStamp__239_neverHappen1292524203957 = 0L;
        CommandSupport.__timeStamp = 1292524203957L;
        $const$0 = 0;
        NEWLINE = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[33].call($getCallSiteArray()[34].callGetProperty($get$$class$java$lang$System()), "line.separator"), $get$$class$java$lang$String());
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final String getShortcut() {
        return this.shortcut;
    }
    
    public final List getAliases() {
        return this.aliases;
    }
    
    public boolean getHidden() {
        return this.hidden;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[35];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$CommandSupport(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CommandSupport.$callSiteArray == null || ($createCallSiteArray = CommandSupport.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CommandSupport.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$jline$History() {
        Class $class$jline$History;
        if (($class$jline$History = CommandSupport.$class$jline$History) == null) {
            $class$jline$History = (CommandSupport.$class$jline$History = class$("jline.History"));
        }
        return $class$jline$History;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Binding() {
        Class $class$groovy$lang$Binding;
        if (($class$groovy$lang$Binding = CommandSupport.$class$groovy$lang$Binding) == null) {
            $class$groovy$lang$Binding = (CommandSupport.$class$groovy$lang$Binding = class$("groovy.lang.Binding"));
        }
        return $class$groovy$lang$Binding;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = CommandSupport.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (CommandSupport.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$jline$Completor() {
        Class $class$jline$Completor;
        if (($class$jline$Completor = CommandSupport.$class$jline$Completor) == null) {
            $class$jline$Completor = (CommandSupport.$class$jline$Completor = class$("jline.Completor"));
        }
        return $class$jline$Completor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandException() {
        Class $class$org$codehaus$groovy$tools$shell$CommandException;
        if (($class$org$codehaus$groovy$tools$shell$CommandException = CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandException) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandException = (CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandException = class$("org.codehaus.groovy.tools.shell.CommandException"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = CommandSupport.$class$java$util$List) == null) {
            $class$java$util$List = (CommandSupport.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = CommandSupport.$class$java$lang$String) == null) {
            $class$java$lang$String = (CommandSupport.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = CommandSupport.$class$java$util$Map) == null) {
            $class$java$util$Map = (CommandSupport.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$jline$NullCompletor() {
        Class $class$jline$NullCompletor;
        if (($class$jline$NullCompletor = CommandSupport.$class$jline$NullCompletor) == null) {
            $class$jline$NullCompletor = (CommandSupport.$class$jline$NullCompletor = class$("jline.NullCompletor"));
        }
        return $class$jline$NullCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandAlias() {
        Class $class$org$codehaus$groovy$tools$shell$CommandAlias;
        if (($class$org$codehaus$groovy$tools$shell$CommandAlias = CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandAlias) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandAlias = (CommandSupport.$class$org$codehaus$groovy$tools$shell$CommandAlias = class$("org.codehaus.groovy.tools.shell.CommandAlias"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandAlias;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$MessageSource() {
        Class $class$org$codehaus$groovy$tools$shell$util$MessageSource;
        if (($class$org$codehaus$groovy$tools$shell$util$MessageSource = CommandSupport.$class$org$codehaus$groovy$tools$shell$util$MessageSource) == null) {
            $class$org$codehaus$groovy$tools$shell$util$MessageSource = (CommandSupport.$class$org$codehaus$groovy$tools$shell$util$MessageSource = class$("org.codehaus.groovy.tools.shell.util.MessageSource"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$MessageSource;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CommandSupport.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CommandSupport.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = CommandSupport.$class$java$lang$System) == null) {
            $class$java$lang$System = (CommandSupport.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = CommandSupport.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (CommandSupport.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$jline$ArgumentCompletor() {
        Class $class$jline$ArgumentCompletor;
        if (($class$jline$ArgumentCompletor = CommandSupport.$class$jline$ArgumentCompletor) == null) {
            $class$jline$ArgumentCompletor = (CommandSupport.$class$jline$ArgumentCompletor = class$("jline.ArgumentCompletor"));
        }
        return $class$jline$ArgumentCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$BufferManager() {
        Class $class$org$codehaus$groovy$tools$shell$BufferManager;
        if (($class$org$codehaus$groovy$tools$shell$BufferManager = CommandSupport.$class$org$codehaus$groovy$tools$shell$BufferManager) == null) {
            $class$org$codehaus$groovy$tools$shell$BufferManager = (CommandSupport.$class$org$codehaus$groovy$tools$shell$BufferManager = class$("org.codehaus.groovy.tools.shell.BufferManager"));
        }
        return $class$org$codehaus$groovy$tools$shell$BufferManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Shell() {
        Class $class$org$codehaus$groovy$tools$shell$Shell;
        if (($class$org$codehaus$groovy$tools$shell$Shell = CommandSupport.$class$org$codehaus$groovy$tools$shell$Shell) == null) {
            $class$org$codehaus$groovy$tools$shell$Shell = (CommandSupport.$class$org$codehaus$groovy$tools$shell$Shell = class$("org.codehaus.groovy.tools.shell.Shell"));
        }
        return $class$org$codehaus$groovy$tools$shell$Shell;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = CommandSupport.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = (CommandSupport.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = class$("org.codehaus.groovy.tools.shell.util.SimpleCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO() {
        Class $class$org$codehaus$groovy$tools$shell$IO;
        if (($class$org$codehaus$groovy$tools$shell$IO = CommandSupport.$class$org$codehaus$groovy$tools$shell$IO) == null) {
            $class$org$codehaus$groovy$tools$shell$IO = (CommandSupport.$class$org$codehaus$groovy$tools$shell$IO = class$("org.codehaus.groovy.tools.shell.IO"));
        }
        return $class$org$codehaus$groovy$tools$shell$IO;
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
