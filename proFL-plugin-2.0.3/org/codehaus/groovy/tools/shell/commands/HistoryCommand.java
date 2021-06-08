// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.tools.shell.BufferManager;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.List;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.ComplexCommandSupport;

public class HistoryCommand extends ComplexCommandSupport
{
    private Object do_show;
    private Object do_clear;
    private Object do_flush;
    private Object do_recall;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205477;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$HistoryCommand;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    
    public HistoryCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "history";
        arguments[2] = "\\H";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.do_show = new HistoryCommand$_closure1(this, this);
                this.do_clear = new HistoryCommand$_closure2(this, this);
                this.do_flush = new HistoryCommand$_closure3(this, this);
                this.do_recall = new HistoryCommand$_closure4(this, this);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.createList(new Object[] { "show", "clear", "flush", "recall" }), $get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand(), this, "functions");
                ScriptBytecodeAdapter.setGroovyObjectProperty("show", $get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand(), this, "defaultFunction");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected List createCompletors() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object loader = new HistoryCommand$_createCompletors_closure5(this, this);
        return (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor(), loader), null }), $get$$class$java$util$List());
    }
    
    @Override
    public Object execute(final List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[2].callCurrent(this, "Shell does not appear to be interactive; Can not query history");
        }
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport(), this, "execute", new Object[] { args });
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HistoryCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HistoryCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HistoryCommand.__timeStamp__239_neverHappen1292524205477 = 0L;
        HistoryCommand.__timeStamp = 1292524205477L;
    }
    
    public Object getDo_show() {
        return this.do_show;
    }
    
    public void setDo_show(final Object do_show) {
        this.do_show = do_show;
    }
    
    public Object getDo_clear() {
        return this.do_clear;
    }
    
    public void setDo_clear(final Object do_clear) {
        this.do_clear = do_clear;
    }
    
    public Object getDo_flush() {
        return this.do_flush;
    }
    
    public void setDo_flush(final Object do_flush) {
        this.do_flush = do_flush;
    }
    
    public Object getDo_recall() {
        return this.do_recall;
    }
    
    public void setDo_recall(final Object do_recall) {
        this.do_recall = do_recall;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HistoryCommand.$callSiteArray == null || ($createCallSiteArray = HistoryCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HistoryCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HistoryCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HistoryCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HistoryCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HistoryCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = HistoryCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = (HistoryCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = class$("org.codehaus.groovy.tools.shell.ComplexCommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$HistoryCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$HistoryCommand = HistoryCommand.$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$HistoryCommand = (HistoryCommand.$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand = class$("org.codehaus.groovy.tools.shell.commands.HistoryCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$HistoryCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = HistoryCommand.$class$java$util$List) == null) {
            $class$java$util$List = (HistoryCommand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = HistoryCommand.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = (HistoryCommand.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = class$("org.codehaus.groovy.tools.shell.util.SimpleCompletor"));
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
