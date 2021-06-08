// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Enumeration;
import junit.framework.TestResult;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Collection;
import groovy.lang.Reference;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import junit.framework.Test;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import junit.framework.TestSuite;

public class JavadocAssertionTestSuite extends TestSuite implements GroovyObject
{
    public static final String SYSPROP_SRC_DIR;
    public static final String SYSPROP_SRC_PATTERN;
    public static final String SYSPROP_SRC_EXCLUDES_PATTERN;
    private static JavadocAssertionTestBuilder testBuilder;
    private static IFileNameFinder finder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204589;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$util$FileNameFinder;
    private static /* synthetic */ Class $class$junit$framework$Test;
    private static /* synthetic */ Class $class$groovy$util$JavadocAssertionTestBuilder;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$junit$textui$TestRunner;
    private static /* synthetic */ Class $class$java$util$Collection;
    private static /* synthetic */ Class $class$groovy$util$JavadocAssertionTestSuite;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    
    public JavadocAssertionTestSuite() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static Test suite() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String basedir = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$java$lang$System(), JavadocAssertionTestSuite.SYSPROP_SRC_DIR, "./src/"), $get$$class$java$lang$String());
        return (Test)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), basedir), $get$$class$junit$framework$Test());
    }
    
    public static Test suite(final String basedir) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String includePattern = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call($get$$class$java$lang$System(), JavadocAssertionTestSuite.SYSPROP_SRC_PATTERN, "**/*.java,**/*.groovy"), $get$$class$java$lang$String());
        return (Test)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), basedir, includePattern), $get$$class$junit$framework$Test());
    }
    
    public static Test suite(final String basedir, final String includePattern) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String excludePattern = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($get$$class$java$lang$System(), JavadocAssertionTestSuite.SYSPROP_SRC_EXCLUDES_PATTERN, ""), $get$$class$java$lang$String());
        return (Test)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), basedir, includePattern, excludePattern), $get$$class$junit$framework$Test());
    }
    
    public static Test suite(final String basedir, final String includePattern, final String excludePattern) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder.record($getCallSiteArray[6].call(recorder.record($getCallSiteArray[7].callConstructor($get$$class$java$io$File(), recorder.record(basedir, 17)), 8)), 26))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert new File(basedir).exists()", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final TestSuite suite = (TestSuite)new Reference($getCallSiteArray[8].callConstructor($get$$class$groovy$util$JavadocAssertionTestSuite()));
        final Collection filenames = (Collection)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(JavadocAssertionTestSuite.finder, ScriptBytecodeAdapter.createMap(new Object[] { "dir", basedir, "includes", includePattern, "excludes", excludePattern })), $get$$class$java$util$Collection());
        $getCallSiteArray[10].call(filenames, new JavadocAssertionTestSuite$_suite_closure1($get$$class$groovy$util$JavadocAssertionTestSuite(), $get$$class$groovy$util$JavadocAssertionTestSuite(), (Reference<Object>)suite));
        return (Test)ScriptBytecodeAdapter.castToType(((Reference<Object>)suite).get(), $get$$class$junit$framework$Test());
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callGetProperty = $getCallSiteArray[11].callGetProperty(args);
        if (ScriptBytecodeAdapter.isCase(callGetProperty, JavadocAssertionTestSuite.$const$0)) {
            $getCallSiteArray[12].call($get$$class$junit$textui$TestRunner(), $getCallSiteArray[13].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), $getCallSiteArray[14].call(args, JavadocAssertionTestSuite.$const$1), $getCallSiteArray[15].call(args, JavadocAssertionTestSuite.$const$2), $getCallSiteArray[16].call(args, JavadocAssertionTestSuite.$const$3)));
        }
        else if (ScriptBytecodeAdapter.isCase(callGetProperty, JavadocAssertionTestSuite.$const$3)) {
            $getCallSiteArray[17].call($get$$class$junit$textui$TestRunner(), $getCallSiteArray[18].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), $getCallSiteArray[19].call(args, JavadocAssertionTestSuite.$const$1), $getCallSiteArray[20].call(args, JavadocAssertionTestSuite.$const$2)));
        }
        else if (ScriptBytecodeAdapter.isCase(callGetProperty, JavadocAssertionTestSuite.$const$2)) {
            $getCallSiteArray[21].call($get$$class$junit$textui$TestRunner(), $getCallSiteArray[22].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite(), $getCallSiteArray[23].call(args, JavadocAssertionTestSuite.$const$1)));
        }
        else {
            $getCallSiteArray[24].call($get$$class$junit$textui$TestRunner(), $getCallSiteArray[25].callStatic($get$$class$groovy$util$JavadocAssertionTestSuite()));
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$JavadocAssertionTestSuite()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JavadocAssertionTestSuite.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JavadocAssertionTestSuite.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JavadocAssertionTestSuite.__timeStamp__239_neverHappen1292524204589 = 0L;
        JavadocAssertionTestSuite.__timeStamp = 1292524204589L;
        $const$3 = 2;
        $const$2 = 1;
        $const$1 = 0;
        $const$0 = 3;
        SYSPROP_SRC_EXCLUDES_PATTERN = "javadocAssertion.src.excludesPattern";
        SYSPROP_SRC_PATTERN = "javadocAssertion.src.pattern";
        SYSPROP_SRC_DIR = "javadocAssertion.src.dir";
        JavadocAssertionTestSuite.testBuilder = (JavadocAssertionTestBuilder)$getCallSiteArray()[26].callConstructor($get$$class$groovy$util$JavadocAssertionTestBuilder());
        JavadocAssertionTestSuite.finder = (IFileNameFinder)$getCallSiteArray()[27].callConstructor($get$$class$groovy$util$FileNameFinder());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[28];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestSuite(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestSuite.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestSuite.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestSuite.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JavadocAssertionTestSuite.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JavadocAssertionTestSuite.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$FileNameFinder() {
        Class $class$groovy$util$FileNameFinder;
        if (($class$groovy$util$FileNameFinder = JavadocAssertionTestSuite.$class$groovy$util$FileNameFinder) == null) {
            $class$groovy$util$FileNameFinder = (JavadocAssertionTestSuite.$class$groovy$util$FileNameFinder = class$("groovy.util.FileNameFinder"));
        }
        return $class$groovy$util$FileNameFinder;
    }
    
    private static /* synthetic */ Class $get$$class$junit$framework$Test() {
        Class $class$junit$framework$Test;
        if (($class$junit$framework$Test = JavadocAssertionTestSuite.$class$junit$framework$Test) == null) {
            $class$junit$framework$Test = (JavadocAssertionTestSuite.$class$junit$framework$Test = class$("junit.framework.Test"));
        }
        return $class$junit$framework$Test;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$JavadocAssertionTestBuilder() {
        Class $class$groovy$util$JavadocAssertionTestBuilder;
        if (($class$groovy$util$JavadocAssertionTestBuilder = JavadocAssertionTestSuite.$class$groovy$util$JavadocAssertionTestBuilder) == null) {
            $class$groovy$util$JavadocAssertionTestBuilder = (JavadocAssertionTestSuite.$class$groovy$util$JavadocAssertionTestBuilder = class$("groovy.util.JavadocAssertionTestBuilder"));
        }
        return $class$groovy$util$JavadocAssertionTestBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = JavadocAssertionTestSuite.$class$java$lang$System) == null) {
            $class$java$lang$System = (JavadocAssertionTestSuite.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$junit$textui$TestRunner() {
        Class $class$junit$textui$TestRunner;
        if (($class$junit$textui$TestRunner = JavadocAssertionTestSuite.$class$junit$textui$TestRunner) == null) {
            $class$junit$textui$TestRunner = (JavadocAssertionTestSuite.$class$junit$textui$TestRunner = class$("junit.textui.TestRunner"));
        }
        return $class$junit$textui$TestRunner;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Collection() {
        Class $class$java$util$Collection;
        if (($class$java$util$Collection = JavadocAssertionTestSuite.$class$java$util$Collection) == null) {
            $class$java$util$Collection = (JavadocAssertionTestSuite.$class$java$util$Collection = class$("java.util.Collection"));
        }
        return $class$java$util$Collection;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$JavadocAssertionTestSuite() {
        Class $class$groovy$util$JavadocAssertionTestSuite;
        if (($class$groovy$util$JavadocAssertionTestSuite = JavadocAssertionTestSuite.$class$groovy$util$JavadocAssertionTestSuite) == null) {
            $class$groovy$util$JavadocAssertionTestSuite = (JavadocAssertionTestSuite.$class$groovy$util$JavadocAssertionTestSuite = class$("groovy.util.JavadocAssertionTestSuite"));
        }
        return $class$groovy$util$JavadocAssertionTestSuite;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestSuite.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestSuite.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = JavadocAssertionTestSuite.$class$java$io$File) == null) {
            $class$java$io$File = (JavadocAssertionTestSuite.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
