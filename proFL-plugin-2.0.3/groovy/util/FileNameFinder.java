// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.util.Map;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class FileNameFinder implements IFileNameFinder, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203646;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$util$AntBuilder;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$util$FileNameFinder;
    private static /* synthetic */ Class $class$java$util$List;
    
    public FileNameFinder() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public List getFileNames(final String basedir, final String pattern) {
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "dir", basedir, "includes", pattern })), $get$$class$java$util$List());
    }
    
    public List getFileNames(final String basedir, final String pattern, final String excludesPattern) {
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "dir", basedir, "includes", pattern, "excludes", excludesPattern })), $get$$class$java$util$List());
    }
    
    public List getFileNames(final Map args) {
        final Map args2 = (Map)new Reference(args);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ant = $getCallSiteArray[2].callConstructor($get$$class$groovy$util$AntBuilder());
        final Object scanner = $getCallSiteArray[3].call(ant, new FileNameFinder$_getFileNames_closure1(this, this, (Reference<Object>)args2));
        final Object fls = ScriptBytecodeAdapter.createList(new Object[0]);
        Object f = null;
        final Object call = $getCallSiteArray[4].call(scanner);
        while (((Iterator)call).hasNext()) {
            f = ((Iterator<Object>)call).next();
            $getCallSiteArray[5].call(fls, $getCallSiteArray[6].call(f));
        }
        return (List)ScriptBytecodeAdapter.castToType(fls, $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$FileNameFinder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = FileNameFinder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (FileNameFinder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        FileNameFinder.__timeStamp__239_neverHappen1292524203646 = 0L;
        FileNameFinder.__timeStamp = 1292524203646L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$FileNameFinder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (FileNameFinder.$callSiteArray == null || ($createCallSiteArray = FileNameFinder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            FileNameFinder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$AntBuilder() {
        Class $class$groovy$util$AntBuilder;
        if (($class$groovy$util$AntBuilder = FileNameFinder.$class$groovy$util$AntBuilder) == null) {
            $class$groovy$util$AntBuilder = (FileNameFinder.$class$groovy$util$AntBuilder = class$("groovy.util.AntBuilder"));
        }
        return $class$groovy$util$AntBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = FileNameFinder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (FileNameFinder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FileNameFinder() {
        Class $class$groovy$util$FileNameFinder;
        if (($class$groovy$util$FileNameFinder = FileNameFinder.$class$groovy$util$FileNameFinder) == null) {
            $class$groovy$util$FileNameFinder = (FileNameFinder.$class$groovy$util$FileNameFinder = class$("groovy.util.FileNameFinder"));
        }
        return $class$groovy$util$FileNameFinder;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = FileNameFinder.$class$java$util$List) == null) {
            $class$java$util$List = (FileNameFinder.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
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
