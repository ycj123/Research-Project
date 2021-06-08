// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import jline.ArgumentCompletor$ArgumentDelimiter;
import jline.Completor;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyClassLoader;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import jline.ArgumentCompletor;

public class ImportCommandCompletor extends ArgumentCompletor implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204805;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$jline$ArgumentCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    private static /* synthetic */ Class $class$jline$NullCompletor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
    
    public ImportCommandCompletor(final GroovyClassLoader classLoader) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array7;
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = (array7 = new Object[] { null }))))));
        arguments[0] = ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor(), classLoader), $getCallSiteArray[1].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor(), "as"), $getCallSiteArray[2].callConstructor($get$$class$jline$NullCompletor()) });
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 5, $get$$class$jline$ArgumentCompletor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (Object[])arguments[0]))))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((List)array[0]);
                break;
            }
            case 1: {
                super((Completor)array2[0]);
                break;
            }
            case 2: {
                super((Completor)array3[0], (ArgumentCompletor$ArgumentDelimiter)array4[1]);
                break;
            }
            case 3: {
                super((Completor[])array5[0]);
                break;
            }
            case 4: {
                super((Completor[])array6[0], (ArgumentCompletor$ArgumentDelimiter)array7[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ImportCommandCompletor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ImportCommandCompletor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ImportCommandCompletor.__timeStamp__239_neverHappen1292524204805 = 0L;
        ImportCommandCompletor.__timeStamp = 1292524204805L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ImportCommandCompletor.$callSiteArray == null || ($createCallSiteArray = ImportCommandCompletor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ImportCommandCompletor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
        if (($class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = (ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor = class$("org.codehaus.groovy.tools.shell.commands.ImportCommandCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ImportCommandCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ImportCommandCompletor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ImportCommandCompletor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$jline$ArgumentCompletor() {
        Class $class$jline$ArgumentCompletor;
        if (($class$jline$ArgumentCompletor = ImportCommandCompletor.$class$jline$ArgumentCompletor) == null) {
            $class$jline$ArgumentCompletor = (ImportCommandCompletor.$class$jline$ArgumentCompletor = class$("jline.ArgumentCompletor"));
        }
        return $class$jline$ArgumentCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = (ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$util$SimpleCompletor = class$("org.codehaus.groovy.tools.shell.util.SimpleCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$SimpleCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$jline$NullCompletor() {
        Class $class$jline$NullCompletor;
        if (($class$jline$NullCompletor = ImportCommandCompletor.$class$jline$NullCompletor) == null) {
            $class$jline$NullCompletor = (ImportCommandCompletor.$class$jline$NullCompletor = class$("jline.NullCompletor"));
        }
        return $class$jline$NullCompletor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor() {
        Class $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
        if (($class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor) == null) {
            $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = (ImportCommandCompletor.$class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor = class$("org.codehaus.groovy.tools.shell.util.ClassNameCompletor"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$ClassNameCompletor;
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
