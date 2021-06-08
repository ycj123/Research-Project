// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class StringUtil implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204104;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$util$StringUtil;
    
    public StringUtil() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static String tr(final String text, final String source, final String replacement) {
        final String source2 = (String)new Reference(source);
        final String replacement2 = (String)new Reference(replacement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(text) && DefaultTypeTransformation.booleanUnbox(((Reference<Object>)source2).get())) ? Boolean.FALSE : Boolean.TRUE)) {
            return (String)ScriptBytecodeAdapter.castToType(text, $get$$class$java$lang$String());
        }
        ((Reference<String>)source2).set((String)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callStatic($get$$class$org$codehaus$groovy$util$StringUtil(), ((Reference<Object>)source2).get()), $get$$class$java$lang$String()));
        ((Reference<String>)replacement2).set((String)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callStatic($get$$class$org$codehaus$groovy$util$StringUtil(), ((Reference<Object>)replacement2).get()), $get$$class$java$lang$String()));
        ((Reference<String>)replacement2).set((String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(((Reference<Object>)replacement2).get(), $getCallSiteArray[3].call(((Reference<Object>)source2).get()), $getCallSiteArray[4].call(((Reference<Object>)replacement2).get(), $getCallSiteArray[5].call($getCallSiteArray[6].call(((Reference<Object>)replacement2).get()), StringUtil.$const$0))), $get$$class$java$lang$String()));
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call($getCallSiteArray[8].call(text, new StringUtil$_tr_closure1($get$$class$org$codehaus$groovy$util$StringUtil(), $get$$class$org$codehaus$groovy$util$StringUtil(), (Reference<Object>)replacement2, (Reference<Object>)source2))), $get$$class$java$lang$String());
    }
    
    private static String expandHyphen(final String text) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(text, "-"))) {
            return (String)ScriptBytecodeAdapter.castToType(text, $get$$class$java$lang$String());
        }
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(text, "(.)-(.)", new StringUtil$_expandHyphen_closure2($get$$class$org$codehaus$groovy$util$StringUtil(), $get$$class$org$codehaus$groovy$util$StringUtil())), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$util$StringUtil()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = StringUtil.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (StringUtil.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        StringUtil.__timeStamp__239_neverHappen1292524204104 = 0L;
        StringUtil.__timeStamp = 1292524204104L;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$util$StringUtil(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StringUtil.$callSiteArray == null || ($createCallSiteArray = StringUtil.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StringUtil.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = StringUtil.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (StringUtil.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = StringUtil.$class$java$lang$String) == null) {
            $class$java$lang$String = (StringUtil.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$util$StringUtil() {
        Class $class$org$codehaus$groovy$util$StringUtil;
        if (($class$org$codehaus$groovy$util$StringUtil = StringUtil.$class$org$codehaus$groovy$util$StringUtil) == null) {
            $class$org$codehaus$groovy$util$StringUtil = (StringUtil.$class$org$codehaus$groovy$util$StringUtil = class$("org.codehaus.groovy.util.StringUtil"));
        }
        return $class$org$codehaus$groovy$util$StringUtil;
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
