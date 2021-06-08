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
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyObject;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class DisplayCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204795;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$DisplayCommand;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public DisplayCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "display";
        arguments[2] = "\\d";
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
    
    public Object execute(final List args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, args);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this)))) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(this)), "Buffer is empty"), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), $getCallSiteArray[8].callGroovyObjectGetProperty(this)), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$DisplayCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = DisplayCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (DisplayCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        DisplayCommand.__timeStamp__239_neverHappen1292524204795 = 0L;
        DisplayCommand.__timeStamp = 1292524204795L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$DisplayCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (DisplayCommand.$callSiteArray == null || ($createCallSiteArray = DisplayCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            DisplayCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = DisplayCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (DisplayCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$DisplayCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$DisplayCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$DisplayCommand = DisplayCommand.$class$org$codehaus$groovy$tools$shell$commands$DisplayCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$DisplayCommand = (DisplayCommand.$class$org$codehaus$groovy$tools$shell$commands$DisplayCommand = class$("org.codehaus.groovy.tools.shell.commands.DisplayCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$DisplayCommand;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = DisplayCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (DisplayCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = DisplayCommand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (DisplayCommand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
