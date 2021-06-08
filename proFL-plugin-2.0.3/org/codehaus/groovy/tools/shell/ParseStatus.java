// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public final class ParseStatus implements GroovyObject
{
    private final ParseCode code;
    private final Throwable cause;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204049;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ParseCode;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ParseStatus;
    private static /* synthetic */ Class $class$java$lang$Throwable;
    
    public ParseStatus(final ParseCode code, final Throwable cause) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.code = (ParseCode)ScriptBytecodeAdapter.castToType(code, $get$$class$org$codehaus$groovy$tools$shell$ParseCode());
        this.cause = (Throwable)ScriptBytecodeAdapter.castToType(cause, $get$$class$java$lang$Throwable());
    }
    
    public ParseStatus(final ParseCode code) {
        $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[2])));
        arguments[0] = code;
        arguments[1] = null;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$org$codehaus$groovy$tools$shell$ParseStatus());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Throwable)array[0]);
                break;
            }
            case 1: {
                this((ParseCode)array2[0]);
                break;
            }
            case 2: {
                this((ParseCode)array3[0], (Throwable)array4[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public ParseStatus(final Throwable cause) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = new Object[2])));
        arguments[0] = $getCallSiteArray[0].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$ParseCode());
        arguments[1] = cause;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$org$codehaus$groovy$tools$shell$ParseStatus());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (Object[])arguments[0])));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Throwable)array[0]);
                break;
            }
            case 1: {
                this((ParseCode)array2[0]);
                break;
            }
            case 2: {
                this((ParseCode)array3[0], (Throwable)array4[1]);
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$ParseStatus()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ParseStatus.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ParseStatus.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ParseStatus.__timeStamp__239_neverHappen1292524204049 = 0L;
        ParseStatus.__timeStamp = 1292524204049L;
    }
    
    public final ParseCode getCode() {
        return this.code;
    }
    
    public final Throwable getCause() {
        return this.cause;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ParseStatus(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ParseStatus.$callSiteArray == null || ($createCallSiteArray = ParseStatus.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ParseStatus.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ParseCode() {
        Class $class$org$codehaus$groovy$tools$shell$ParseCode;
        if (($class$org$codehaus$groovy$tools$shell$ParseCode = ParseStatus.$class$org$codehaus$groovy$tools$shell$ParseCode) == null) {
            $class$org$codehaus$groovy$tools$shell$ParseCode = (ParseStatus.$class$org$codehaus$groovy$tools$shell$ParseCode = class$("org.codehaus.groovy.tools.shell.ParseCode"));
        }
        return $class$org$codehaus$groovy$tools$shell$ParseCode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ParseStatus.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ParseStatus.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ParseStatus() {
        Class $class$org$codehaus$groovy$tools$shell$ParseStatus;
        if (($class$org$codehaus$groovy$tools$shell$ParseStatus = ParseStatus.$class$org$codehaus$groovy$tools$shell$ParseStatus) == null) {
            $class$org$codehaus$groovy$tools$shell$ParseStatus = (ParseStatus.$class$org$codehaus$groovy$tools$shell$ParseStatus = class$("org.codehaus.groovy.tools.shell.ParseStatus"));
        }
        return $class$org$codehaus$groovy$tools$shell$ParseStatus;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Throwable() {
        Class $class$java$lang$Throwable;
        if (($class$java$lang$Throwable = ParseStatus.$class$java$lang$Throwable) == null) {
            $class$java$lang$Throwable = (ParseStatus.$class$java$lang$Throwable = class$("java.lang.Throwable"));
        }
        return $class$java$lang$Throwable;
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
