// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import jline.Completor;
import java.util.Map;
import jline.History;
import java.util.List;
import org.codehaus.groovy.tools.shell.BufferManager;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.ComplexCommandSupport;

public class ShadowCommand extends ComplexCommandSupport
{
    private Object do_debug;
    private Object do_verbose;
    private Object do_info;
    private Object do_this;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205539;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    
    public ShadowCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "shadow";
        arguments[2] = "\\&";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.do_debug = new ShadowCommand$_closure1(this, this);
                this.do_verbose = new ShadowCommand$_closure2(this, this);
                this.do_info = new ShadowCommand$_closure3(this, this);
                this.do_this = new ShadowCommand$_closure4(this, this);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.TRUE, $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand(), this, "hidden");
                ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.createList(new Object[] { "debug", "verbose", "info", "this" }), $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand(), this, "functions");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ShadowCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ShadowCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ShadowCommand.__timeStamp__239_neverHappen1292524205539 = 0L;
        ShadowCommand.__timeStamp = 1292524205539L;
    }
    
    public Object getDo_debug() {
        return this.do_debug;
    }
    
    public void setDo_debug(final Object do_debug) {
        this.do_debug = do_debug;
    }
    
    public Object getDo_verbose() {
        return this.do_verbose;
    }
    
    public void setDo_verbose(final Object do_verbose) {
        this.do_verbose = do_verbose;
    }
    
    public Object getDo_info() {
        return this.do_info;
    }
    
    public void setDo_info(final Object do_info) {
        this.do_info = do_info;
    }
    
    public Object getDo_this() {
        return this.do_this;
    }
    
    public void setDo_this(final Object do_this) {
        this.do_this = do_this;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShadowCommand.$callSiteArray == null || ($createCallSiteArray = ShadowCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShadowCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ShadowCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ShadowCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$ShadowCommand = ShadowCommand.$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand = (ShadowCommand.$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand = class$("org.codehaus.groovy.tools.shell.commands.ShadowCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = ShadowCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = (ShadowCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = class$("org.codehaus.groovy.tools.shell.ComplexCommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
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
