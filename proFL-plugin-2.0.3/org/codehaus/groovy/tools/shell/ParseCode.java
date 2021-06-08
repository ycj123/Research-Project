// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public final class ParseCode implements GroovyObject
{
    private static final ParseCode COMPLETE;
    private static final ParseCode INCOMPLETE;
    private static final ParseCode ERROR;
    private final int code;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204047;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ParseCode;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$String;
    
    private ParseCode(final int code) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.code = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(code));
    }
    
    public String toString() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(this.code), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$ParseCode()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ParseCode.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ParseCode.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ParseCode.__timeStamp__239_neverHappen1292524204047 = 0L;
        ParseCode.__timeStamp = 1292524204047L;
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
        COMPLETE = (ParseCode)$getCallSiteArray()[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$ParseCode(), 0);
        INCOMPLETE = (ParseCode)$getCallSiteArray()[1].callConstructor($get$$class$org$codehaus$groovy$tools$shell$ParseCode(), 1);
        ERROR = (ParseCode)$getCallSiteArray()[2].callConstructor($get$$class$org$codehaus$groovy$tools$shell$ParseCode(), 2);
    }
    
    public static final ParseCode getCOMPLETE() {
        return ParseCode.COMPLETE;
    }
    
    public static final ParseCode getINCOMPLETE() {
        return ParseCode.INCOMPLETE;
    }
    
    public static final ParseCode getERROR() {
        return ParseCode.ERROR;
    }
    
    public final int getCode() {
        return this.code;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ParseCode(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ParseCode.$callSiteArray == null || ($createCallSiteArray = ParseCode.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ParseCode.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ParseCode() {
        Class $class$org$codehaus$groovy$tools$shell$ParseCode;
        if (($class$org$codehaus$groovy$tools$shell$ParseCode = ParseCode.$class$org$codehaus$groovy$tools$shell$ParseCode) == null) {
            $class$org$codehaus$groovy$tools$shell$ParseCode = (ParseCode.$class$org$codehaus$groovy$tools$shell$ParseCode = class$("org.codehaus.groovy.tools.shell.ParseCode"));
        }
        return $class$org$codehaus$groovy$tools$shell$ParseCode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ParseCode.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ParseCode.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ParseCode.$class$java$lang$String) == null) {
            $class$java$lang$String = (ParseCode.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
