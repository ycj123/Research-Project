// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class FileNameByRegexFinder implements IFileNameFinder, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203643;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$groovy$util$FileNameByRegexFinder;
    
    public FileNameByRegexFinder() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public List getFileNames(final String basedir, final String pattern) {
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callCurrent(this, basedir, pattern, ""), $get$$class$java$util$List());
    }
    
    public List getFileNames(final String basedir, final String pattern, final String excludesPattern) {
        final String pattern2 = (String)new Reference(pattern);
        final String excludesPattern2 = (String)new Reference(excludesPattern);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object result = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        $getCallSiteArray[1].call($getCallSiteArray[2].callConstructor($get$$class$java$io$File(), basedir), new FileNameByRegexFinder$_getFileNames_closure1(this, this, (Reference<Object>)result, (Reference<Object>)pattern2, (Reference<Object>)excludesPattern2));
        return (List)ScriptBytecodeAdapter.castToType(((Reference<Object>)result).get(), $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$FileNameByRegexFinder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = FileNameByRegexFinder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (FileNameByRegexFinder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        FileNameByRegexFinder.__timeStamp__239_neverHappen1292524203643 = 0L;
        FileNameByRegexFinder.__timeStamp = 1292524203643L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$FileNameByRegexFinder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (FileNameByRegexFinder.$callSiteArray == null || ($createCallSiteArray = FileNameByRegexFinder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            FileNameByRegexFinder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = FileNameByRegexFinder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (FileNameByRegexFinder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = FileNameByRegexFinder.$class$java$util$List) == null) {
            $class$java$util$List = (FileNameByRegexFinder.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = FileNameByRegexFinder.$class$java$io$File) == null) {
            $class$java$io$File = (FileNameByRegexFinder.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FileNameByRegexFinder() {
        Class $class$groovy$util$FileNameByRegexFinder;
        if (($class$groovy$util$FileNameByRegexFinder = FileNameByRegexFinder.$class$groovy$util$FileNameByRegexFinder) == null) {
            $class$groovy$util$FileNameByRegexFinder = (FileNameByRegexFinder.$class$groovy$util$FileNameByRegexFinder = class$("groovy.util.FileNameByRegexFinder"));
        }
        return $class$groovy$util$FileNameByRegexFinder;
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
