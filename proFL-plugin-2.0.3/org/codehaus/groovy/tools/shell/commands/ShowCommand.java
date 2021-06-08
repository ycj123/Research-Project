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

public class ShowCommand extends ComplexCommandSupport
{
    private Object do_variables;
    private Object do_classes;
    private Object do_imports;
    private Object do_preferences;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205543;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ShowCommand;
    
    public ShowCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "show";
        arguments[2] = "\\S";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.do_variables = new ShowCommand$_closure1(this, this);
                this.do_classes = new ShowCommand$_closure2(this, this);
                this.do_imports = new ShowCommand$_closure3(this, this);
                this.do_preferences = new ShowCommand$_closure4(this, this);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                ScriptBytecodeAdapter.setGroovyObjectProperty(ScriptBytecodeAdapter.createList(new Object[] { "variables", "classes", "imports", "preferences", "all" }), $get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand(), this, "functions");
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ShowCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ShowCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ShowCommand.__timeStamp__239_neverHappen1292524205543 = 0L;
        ShowCommand.__timeStamp = 1292524205543L;
    }
    
    public Object getDo_variables() {
        return this.do_variables;
    }
    
    public void setDo_variables(final Object do_variables) {
        this.do_variables = do_variables;
    }
    
    public Object getDo_classes() {
        return this.do_classes;
    }
    
    public void setDo_classes(final Object do_classes) {
        this.do_classes = do_classes;
    }
    
    public Object getDo_imports() {
        return this.do_imports;
    }
    
    public void setDo_imports(final Object do_imports) {
        this.do_imports = do_imports;
    }
    
    public Object getDo_preferences() {
        return this.do_preferences;
    }
    
    public void setDo_preferences(final Object do_preferences) {
        this.do_preferences = do_preferences;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShowCommand.$callSiteArray == null || ($createCallSiteArray = ShowCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShowCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ShowCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ShowCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = ShowCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = (ShowCommand.$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport = class$("org.codehaus.groovy.tools.shell.ComplexCommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$ComplexCommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ShowCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$ShowCommand = ShowCommand.$class$org$codehaus$groovy$tools$shell$commands$ShowCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ShowCommand = (ShowCommand.$class$org$codehaus$groovy$tools$shell$commands$ShowCommand = class$("org.codehaus.groovy.tools.shell.commands.ShowCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ShowCommand;
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
