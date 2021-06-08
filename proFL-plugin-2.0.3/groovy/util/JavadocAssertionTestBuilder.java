// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.LinkedHashMap;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.regex.Pattern;
import groovy.lang.GroovyObject;

public class JavadocAssertionTestBuilder implements GroovyObject
{
    private static Pattern javadocPattern;
    private static Pattern assertionPattern;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203649;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    private static /* synthetic */ Class $class$groovy$util$JavadocAssertionTestBuilder;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$java$util$LinkedHashMap;
    
    public JavadocAssertionTestBuilder() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Class buildTest(final String filename, final String code) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Class test = (Class)ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Class());
        final List assertionTags = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callCurrent(this, code), $get$$class$java$util$List());
        if (DefaultTypeTransformation.booleanUnbox(assertionTags)) {
            final String testName = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callCurrent(this, filename), $get$$class$java$lang$String());
            final Map lineNumberToAssertions = (Map)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callCurrent(this, code, assertionTags), $get$$class$java$util$Map());
            final List testMethods = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].callCurrent(this, lineNumberToAssertions, filename), $get$$class$java$util$List());
            final String testCode = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callCurrent(this, testName, testMethods), $get$$class$java$lang$String());
            test = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callCurrent(this, testCode), $get$$class$java$lang$Class());
        }
        return (Class)ScriptBytecodeAdapter.castToType(test, $get$$class$java$lang$Class());
    }
    
    private List getAssertionTags(final String code) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final List assertions = (List)new Reference($getCallSiteArray[6].callConstructor($get$$class$java$util$ArrayList()));
        $getCallSiteArray[7].call(code, JavadocAssertionTestBuilder.javadocPattern, new JavadocAssertionTestBuilder$_getAssertionTags_closure1(this, this, (Reference<Object>)assertions));
        return (List)ScriptBytecodeAdapter.castToType(((Reference)assertions).get(), $get$$class$java$util$List());
    }
    
    private String getTestName(final String filename) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String filenameWithoutPath = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].callGetProperty($getCallSiteArray[9].callConstructor($get$$class$java$io$File(), filename)), $get$$class$java$lang$String());
        final String testName = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call($getCallSiteArray[11].call(filenameWithoutPath, JavadocAssertionTestBuilder.$const$0, $getCallSiteArray[12].call(filenameWithoutPath, ".")), "JavadocAssertionTest"), $get$$class$java$lang$String());
        return (String)ScriptBytecodeAdapter.castToType(testName, $get$$class$java$lang$String());
    }
    
    private Map getLineNumberToAssertionsMap(final String code, final List assertionTags) {
        final String code2 = (String)new Reference(code);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Map lineNumberToAssertions = (Map)new Reference(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$LinkedHashMap()));
        final Integer codeIndex = (Integer)new Reference(JavadocAssertionTestBuilder.$const$0);
        $getCallSiteArray[13].call(assertionTags, new JavadocAssertionTestBuilder$_getLineNumberToAssertionsMap_closure2(this, this, (Reference<Object>)lineNumberToAssertions, (Reference<Object>)codeIndex, (Reference<Object>)code2));
        return (Map)ScriptBytecodeAdapter.castToType(((Reference)lineNumberToAssertions).get(), $get$$class$java$util$Map());
    }
    
    private String getAssertion(final String tag) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String tagInner = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[14].call(tag, $getCallSiteArray[15].call($getCallSiteArray[16].call(tag, ">"), JavadocAssertionTestBuilder.$const$1), $getCallSiteArray[17].call(tag, "<")), $get$$class$java$lang$String());
        final String htmlAssertion = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[18].call(tagInner, "(?m)^\\s*\\*", ""), $get$$class$java$lang$String());
        final String assertion = (String)new Reference(htmlAssertion);
        $getCallSiteArray[19].call(ScriptBytecodeAdapter.createMap(new Object[] { "nbsp", " ", "gt", ">", "lt", "<", "quot", "\"", "apos", "'", "at", "@", "ndash", "-", "amp", "&" }), new JavadocAssertionTestBuilder$_getAssertion_closure3(this, this, (Reference<Object>)assertion));
        return (String)ScriptBytecodeAdapter.castToType(((Reference<Object>)assertion).get(), $get$$class$java$lang$String());
    }
    
    private List getTestMethods(final Map lineNumberToAssertions, final String filename) {
        final String filename2 = (String)new Reference(filename);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final List testMethods = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call($getCallSiteArray[21].call(lineNumberToAssertions, new JavadocAssertionTestBuilder$_getTestMethods_closure4(this, this, (Reference<Object>)filename2))), $get$$class$java$util$List());
        return (List)ScriptBytecodeAdapter.castToType(testMethods, $get$$class$java$util$List());
    }
    
    private String basename(final String fullPath) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object path = $getCallSiteArray[22].callConstructor($get$$class$java$io$File(), fullPath);
        final Object fullName = $getCallSiteArray[23].callGetProperty(path);
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[24].call(fullName, JavadocAssertionTestBuilder.$const$0, $getCallSiteArray[25].call(fullName, ".")), $get$$class$java$lang$String());
    }
    
    private String getTestMethodCodeForAssertion(final String suffix, final String assertion, final String basename) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { basename, suffix, $getCallSiteArray[26].call(ScriptBytecodeAdapter.asType($getCallSiteArray[27].call(assertion, "UTF-8"), $get$$class$java$util$List()), ", ") }, new String[] { "\n            public void testAssertionFrom", "Line", "() {\n                byte[] bytes = [ ", " ] as byte[]\n                Eval.me(new String(bytes, \"UTF-8\"))\n            }\n        " }), $get$$class$java$lang$String());
    }
    
    private String getTestCode(final String testName, final List testMethods) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[28].call($getCallSiteArray[29].call(new GStringImpl(new Object[] { testName }, new String[] { "\n            class ", " extends junit.framework.TestCase {\n                " }), $getCallSiteArray[30].call(testMethods, "\r\n")), "\n            }\n        "), $get$$class$java$lang$String());
    }
    
    private Class createClass(final String testCode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[31].call($getCallSiteArray[32].callConstructor($get$$class$groovy$lang$GroovyClassLoader()), testCode), $get$$class$java$lang$Class());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$JavadocAssertionTestBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JavadocAssertionTestBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JavadocAssertionTestBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JavadocAssertionTestBuilder.__timeStamp__239_neverHappen1292524203649 = 0L;
        JavadocAssertionTestBuilder.__timeStamp = 1292524203649L;
        $const$1 = 1;
        $const$0 = 0;
        JavadocAssertionTestBuilder.javadocPattern = (Pattern)ScriptBytecodeAdapter.castToType($getCallSiteArray()[33].call($get$$class$java$util$regex$Pattern(), "(?ims)/\\*\\*.*?\\*/"), $get$$class$java$util$regex$Pattern());
        JavadocAssertionTestBuilder.assertionPattern = (Pattern)ScriptBytecodeAdapter.castToType($getCallSiteArray()[34].call($get$$class$java$util$regex$Pattern(), "(?ims)<([a-z]+)\\s+class\\s*=\\s*['\"]groovyTestCase['\"]\\s*>.*?<\\s*/\\s*\\1>"), $get$$class$java$util$regex$Pattern());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[35];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$JavadocAssertionTestBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JavadocAssertionTestBuilder.$callSiteArray == null || ($createCallSiteArray = JavadocAssertionTestBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JavadocAssertionTestBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JavadocAssertionTestBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JavadocAssertionTestBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = JavadocAssertionTestBuilder.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (JavadocAssertionTestBuilder.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$JavadocAssertionTestBuilder() {
        Class $class$groovy$util$JavadocAssertionTestBuilder;
        if (($class$groovy$util$JavadocAssertionTestBuilder = JavadocAssertionTestBuilder.$class$groovy$util$JavadocAssertionTestBuilder) == null) {
            $class$groovy$util$JavadocAssertionTestBuilder = (JavadocAssertionTestBuilder.$class$groovy$util$JavadocAssertionTestBuilder = class$("groovy.util.JavadocAssertionTestBuilder"));
        }
        return $class$groovy$util$JavadocAssertionTestBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = JavadocAssertionTestBuilder.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (JavadocAssertionTestBuilder.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = JavadocAssertionTestBuilder.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (JavadocAssertionTestBuilder.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = JavadocAssertionTestBuilder.$class$java$util$List) == null) {
            $class$java$util$List = (JavadocAssertionTestBuilder.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = JavadocAssertionTestBuilder.$class$java$lang$String) == null) {
            $class$java$lang$String = (JavadocAssertionTestBuilder.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = JavadocAssertionTestBuilder.$class$java$io$File) == null) {
            $class$java$io$File = (JavadocAssertionTestBuilder.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = JavadocAssertionTestBuilder.$class$java$util$Map) == null) {
            $class$java$util$Map = (JavadocAssertionTestBuilder.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = JavadocAssertionTestBuilder.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (JavadocAssertionTestBuilder.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedHashMap() {
        Class $class$java$util$LinkedHashMap;
        if (($class$java$util$LinkedHashMap = JavadocAssertionTestBuilder.$class$java$util$LinkedHashMap) == null) {
            $class$java$util$LinkedHashMap = (JavadocAssertionTestBuilder.$class$java$util$LinkedHashMap = class$("java.util.LinkedHashMap"));
        }
        return $class$java$util$LinkedHashMap;
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
