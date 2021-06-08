// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Closure;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public abstract class ShellRunner implements Runnable, GroovyObject
{
    protected final Logger log;
    private final Shell shell;
    private boolean running;
    private boolean breakOnNull;
    private Closure errorHandler;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204066;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ShellRunner;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Shell;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public ShellRunner(final Shell shell) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.running = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.breakOnNull = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        this.errorHandler = (Closure)ScriptBytecodeAdapter.castToType(new ShellRunner$_closure1(this, this), $get$$class$groovy$lang$Closure());
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
        this.shell = (Shell)ScriptBytecodeAdapter.castToType(shell, $get$$class$org$codehaus$groovy$tools$shell$Shell());
    }
    
    public void run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[2].call(this.log, "Running");
        this.running = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        while (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.running))) {
            try {
                this.running = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].callCurrent(this));
            }
            catch (ExitNotification n) {
                throw n;
            }
            catch (Throwable t) {
                $getCallSiteArray[4].call(this.log, new GStringImpl(new Object[] { t }, new String[] { "Work failed: ", "" }), t);
                if (!DefaultTypeTransformation.booleanUnbox(this.errorHandler)) {
                    continue;
                }
                $getCallSiteArray[5].call(this.errorHandler, t);
            }
        }
        $getCallSiteArray[6].call(this.log, "Finished");
    }
    
    protected boolean work() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object line = $getCallSiteArray[7].callCurrent(this);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGetProperty(this.log))) {
            $getCallSiteArray[9].call(this.log, new GStringImpl(new Object[] { line }, new String[] { "Read line: ", "" }));
        }
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(line, null) && DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.breakOnNull))) ? Boolean.TRUE : Boolean.FALSE)) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
        }
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[10].call($getCallSiteArray[11].call(line)), ShellRunner.$const$0)) {
            final Object result = $getCallSiteArray[12].call(this.shell, line);
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    protected abstract String readLine();
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$ShellRunner()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ShellRunner.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ShellRunner.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
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
        ShellRunner.__timeStamp__239_neverHappen1292524204066 = 0L;
        ShellRunner.__timeStamp = 1292524204066L;
        $const$0 = 0;
    }
    
    public final Shell getShell() {
        return this.shell;
    }
    
    public boolean getRunning() {
        return this.running;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
    }
    
    public boolean getBreakOnNull() {
        return this.breakOnNull;
    }
    
    public boolean isBreakOnNull() {
        return this.breakOnNull;
    }
    
    public void setBreakOnNull(final boolean breakOnNull) {
        this.breakOnNull = breakOnNull;
    }
    
    public Closure getErrorHandler() {
        return this.errorHandler;
    }
    
    public void setErrorHandler(final Closure errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ShellRunner(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShellRunner.$callSiteArray == null || ($createCallSiteArray = ShellRunner.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShellRunner.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ShellRunner.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ShellRunner.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = ShellRunner.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (ShellRunner.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ShellRunner() {
        Class $class$org$codehaus$groovy$tools$shell$ShellRunner;
        if (($class$org$codehaus$groovy$tools$shell$ShellRunner = ShellRunner.$class$org$codehaus$groovy$tools$shell$ShellRunner) == null) {
            $class$org$codehaus$groovy$tools$shell$ShellRunner = (ShellRunner.$class$org$codehaus$groovy$tools$shell$ShellRunner = class$("org.codehaus.groovy.tools.shell.ShellRunner"));
        }
        return $class$org$codehaus$groovy$tools$shell$ShellRunner;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = ShellRunner.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (ShellRunner.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Shell() {
        Class $class$org$codehaus$groovy$tools$shell$Shell;
        if (($class$org$codehaus$groovy$tools$shell$Shell = ShellRunner.$class$org$codehaus$groovy$tools$shell$Shell) == null) {
            $class$org$codehaus$groovy$tools$shell$Shell = (ShellRunner.$class$org$codehaus$groovy$tools$shell$Shell = class$("org.codehaus.groovy.tools.shell.Shell"));
        }
        return $class$org$codehaus$groovy$tools$shell$Shell;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = ShellRunner.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (ShellRunner.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
