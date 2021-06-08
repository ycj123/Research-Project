// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.io.StringWriter;
import groovy.text.GStringTemplateEngine;
import groovy.lang.Writable;
import groovy.text.Template;
import java.util.List;
import org.codehaus.groovy.control.CompilationUnit;
import groovy.lang.GroovyCodeSource;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.control.CompilationFailedException;
import groovy.lang.Reference;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.net.URL;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyClassLoader;
import java.util.Properties;
import groovy.lang.GroovyObject;

public class ScriptToTreeNodeAdapter implements GroovyObject
{
    private static Properties classNameToStringForm;
    private boolean showScriptFreeForm;
    private boolean showScriptClass;
    private final GroovyClassLoader classLoader;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202765;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$text$Template;
    private static /* synthetic */ Class $class$javax$swing$tree$TreeNode;
    private static /* synthetic */ Class $class$java$io$StringWriter;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$groovy$util$ConfigSlurper;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$ClassLoader;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$lang$Writable;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilerConfiguration;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$groovy$text$GStringTemplateEngine;
    private static /* synthetic */ Class $class$groovy$lang$GroovyCodeSource;
    private static /* synthetic */ Class $class$java$util$Properties;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilationUnit;
    private static /* synthetic */ Class $class$java$net$URL;
    
    public ScriptToTreeNodeAdapter(final Object classLoader, final Object showScriptFreeForm, final Object showScriptClass) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.classLoader = (GroovyClassLoader)ScriptBytecodeAdapter.castToType(classLoader, $get$$class$groovy$lang$GroovyClassLoader());
        this.showScriptFreeForm = DefaultTypeTransformation.booleanUnbox(showScriptFreeForm);
        this.showScriptClass = DefaultTypeTransformation.booleanUnbox(showScriptClass);
    }
    
    static {
        ScriptToTreeNodeAdapter.__timeStamp__239_neverHappen1292524202765 = 0L;
        ScriptToTreeNodeAdapter.__timeStamp = 1292524202765L;
        final URL url = (URL)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].call($get$$class$java$lang$ClassLoader(), "groovy/inspect/swingui/AstBrowserProperties.groovy"), $get$$class$java$net$URL());
        final Object config = $getCallSiteArray()[1].call($getCallSiteArray()[2].callConstructor($get$$class$groovy$util$ConfigSlurper()), url);
        ScriptToTreeNodeAdapter.classNameToStringForm = (Properties)ScriptBytecodeAdapter.castToType($getCallSiteArray()[3].call(config), $get$$class$java$util$Properties());
        final String home = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[4].call($get$$class$java$lang$System(), "user.home"), $get$$class$java$lang$String());
        if (DefaultTypeTransformation.booleanUnbox(home)) {
            final File userFile = (File)$getCallSiteArray()[5].callConstructor($get$$class$java$io$File(), $getCallSiteArray()[6].call($getCallSiteArray()[7].call(home, $getCallSiteArray()[8].callGetProperty($get$$class$java$io$File())), ".groovy/AstBrowserProperties.groovy"));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray()[9].call(userFile))) {
                final Object customConfig = $getCallSiteArray()[10].call($getCallSiteArray()[11].callConstructor($get$$class$groovy$util$ConfigSlurper()), $getCallSiteArray()[12].call(userFile));
                $getCallSiteArray()[13].call(ScriptToTreeNodeAdapter.classNameToStringForm, $getCallSiteArray()[14].call(customConfig));
            }
        }
    }
    
    public TreeNode compile(final String script, final int compilePhase) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object scriptName = $getCallSiteArray[15].call($getCallSiteArray[16].call("script", $getCallSiteArray[17].call($get$$class$java$lang$System())), ".groovy");
        final GroovyCodeSource codeSource = (GroovyCodeSource)$getCallSiteArray[18].callConstructor($get$$class$groovy$lang$GroovyCodeSource(), script, scriptName, "/groovy/script");
        final CompilationUnit cu = (CompilationUnit)$getCallSiteArray[19].callConstructor($get$$class$org$codehaus$groovy$control$CompilationUnit(), $getCallSiteArray[20].callGetProperty($get$$class$org$codehaus$groovy$control$CompilerConfiguration()), $getCallSiteArray[21].callGetProperty(codeSource), this.classLoader);
        final TreeNodeBuildingNodeOperation operation = (TreeNodeBuildingNodeOperation)new Reference($getCallSiteArray[22].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation(), this, DefaultTypeTransformation.box(this.showScriptFreeForm), DefaultTypeTransformation.box(this.showScriptClass)));
        $getCallSiteArray[23].call(cu, ((Reference<Object>)operation).get(), DefaultTypeTransformation.box(compilePhase));
        $getCallSiteArray[24].call(cu, $getCallSiteArray[25].call(codeSource), script);
        try {
            $getCallSiteArray[26].call(cu, DefaultTypeTransformation.box(compilePhase));
        }
        catch (CompilationFailedException cfe) {
            $getCallSiteArray[27].call($getCallSiteArray[28].callGetProperty(((Reference<Object>)operation).get()), $getCallSiteArray[29].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Unable to produce AST for this phase due to earlier compilation error:"));
            $getCallSiteArray[30].call($getCallSiteArray[31].callGetProperty(cfe), new ScriptToTreeNodeAdapter$_compile_closure1(this, this, (Reference<Object>)operation));
            $getCallSiteArray[32].call($getCallSiteArray[33].callGetProperty(((Reference<Object>)operation).get()), $getCallSiteArray[34].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Fix the above error(s) and then press Refresh"));
        }
        catch (Throwable t) {
            $getCallSiteArray[35].call($getCallSiteArray[36].callGetProperty(((Reference<Object>)operation).get()), $getCallSiteArray[37].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Unable to produce AST for this phase due to an error:"));
            $getCallSiteArray[38].call($getCallSiteArray[39].callGetProperty(((Reference<Object>)operation).get()), $getCallSiteArray[40].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), t));
            $getCallSiteArray[41].call($getCallSiteArray[42].callGetProperty(((Reference<Object>)operation).get()), $getCallSiteArray[43].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Fix the above error(s) and then press Refresh"));
        }
        return (TreeNode)ScriptBytecodeAdapter.castToType($getCallSiteArray[44].callGetProperty(((Reference<Object>)operation).get()), $get$$class$javax$swing$tree$TreeNode());
    }
    
    public TreeNode make(final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (TreeNode)ScriptBytecodeAdapter.castToType($getCallSiteArray[45].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeWithProperties(), $getCallSiteArray[46].callCurrent(this, node), $getCallSiteArray[47].callCurrent(this, node)), $get$$class$javax$swing$tree$TreeNode());
    }
    
    private List<List<String>> getPropertyTable(final Object node) {
        final Object node2 = new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List<List<String>>)ScriptBytecodeAdapter.castToType($getCallSiteArray[48].callSafe($getCallSiteArray[49].callSafe($getCallSiteArray[50].callSafe($getCallSiteArray[51].callGetProperty($getCallSiteArray[52].callGetProperty(((Reference<Object>)node2).get())), new ScriptToTreeNodeAdapter$_getPropertyTable_closure2(this, this)), new ScriptToTreeNodeAdapter$_getPropertyTable_closure3(this, this, (Reference<Object>)node2)), new ScriptToTreeNodeAdapter$_getPropertyTable_closure4(this, this)), $get$$class$java$util$List());
    }
    
    private String getStringForm(final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[53].call(ScriptToTreeNodeAdapter.classNameToStringForm, $getCallSiteArray[54].callGetProperty($getCallSiteArray[55].callGetProperty(node))))) {
            final GStringTemplateEngine engine = (GStringTemplateEngine)$getCallSiteArray[56].callConstructor($get$$class$groovy$text$GStringTemplateEngine());
            final Object script = $getCallSiteArray[57].call(ScriptToTreeNodeAdapter.classNameToStringForm, $getCallSiteArray[58].callGetProperty($getCallSiteArray[59].callGetProperty(node)));
            final Template template = (Template)ScriptBytecodeAdapter.castToType($getCallSiteArray[60].call(engine, script), $get$$class$groovy$text$Template());
            final Writable writable = (Writable)ScriptBytecodeAdapter.castToType($getCallSiteArray[61].call(template, ScriptBytecodeAdapter.createMap(new Object[] { "expression", node })), $get$$class$groovy$lang$Writable());
            final StringWriter result = (StringWriter)$getCallSiteArray[62].callConstructor($get$$class$java$io$StringWriter());
            $getCallSiteArray[63].call(writable, result);
            return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[64].call(result), $get$$class$java$lang$String());
        }
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[65].callGetProperty($getCallSiteArray[66].callGetProperty(node)), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ScriptToTreeNodeAdapter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ScriptToTreeNodeAdapter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public static Properties getClassNameToStringForm() {
        return ScriptToTreeNodeAdapter.classNameToStringForm;
    }
    
    public static void setClassNameToStringForm(final Properties classNameToStringForm) {
        ScriptToTreeNodeAdapter.classNameToStringForm = classNameToStringForm;
    }
    
    public boolean getShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public boolean isShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public void setShowScriptFreeForm(final boolean showScriptFreeForm) {
        this.showScriptFreeForm = showScriptFreeForm;
    }
    
    public boolean getShowScriptClass() {
        return this.showScriptClass;
    }
    
    public boolean isShowScriptClass() {
        return this.showScriptClass;
    }
    
    public void setShowScriptClass(final boolean showScriptClass) {
        this.showScriptClass = showScriptClass;
    }
    
    public final GroovyClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[67];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ScriptToTreeNodeAdapter.$callSiteArray == null || ($createCallSiteArray = ScriptToTreeNodeAdapter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ScriptToTreeNodeAdapter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$text$Template() {
        Class $class$groovy$text$Template;
        if (($class$groovy$text$Template = ScriptToTreeNodeAdapter.$class$groovy$text$Template) == null) {
            $class$groovy$text$Template = (ScriptToTreeNodeAdapter.$class$groovy$text$Template = class$("groovy.text.Template"));
        }
        return $class$groovy$text$Template;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$TreeNode() {
        Class $class$javax$swing$tree$TreeNode;
        if (($class$javax$swing$tree$TreeNode = ScriptToTreeNodeAdapter.$class$javax$swing$tree$TreeNode) == null) {
            $class$javax$swing$tree$TreeNode = (ScriptToTreeNodeAdapter.$class$javax$swing$tree$TreeNode = class$("javax.swing.tree.TreeNode"));
        }
        return $class$javax$swing$tree$TreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$StringWriter() {
        Class $class$java$io$StringWriter;
        if (($class$java$io$StringWriter = ScriptToTreeNodeAdapter.$class$java$io$StringWriter) == null) {
            $class$java$io$StringWriter = (ScriptToTreeNodeAdapter.$class$java$io$StringWriter = class$("java.io.StringWriter"));
        }
        return $class$java$io$StringWriter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = ScriptToTreeNodeAdapter.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (ScriptToTreeNodeAdapter.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigSlurper() {
        Class $class$groovy$util$ConfigSlurper;
        if (($class$groovy$util$ConfigSlurper = ScriptToTreeNodeAdapter.$class$groovy$util$ConfigSlurper) == null) {
            $class$groovy$util$ConfigSlurper = (ScriptToTreeNodeAdapter.$class$groovy$util$ConfigSlurper = class$("groovy.util.ConfigSlurper"));
        }
        return $class$groovy$util$ConfigSlurper;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = ScriptToTreeNodeAdapter.$class$java$util$List) == null) {
            $class$java$util$List = (ScriptToTreeNodeAdapter.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = ScriptToTreeNodeAdapter.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (ScriptToTreeNodeAdapter.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ScriptToTreeNodeAdapter.$class$java$lang$String) == null) {
            $class$java$lang$String = (ScriptToTreeNodeAdapter.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$ClassLoader() {
        Class $class$java$lang$ClassLoader;
        if (($class$java$lang$ClassLoader = ScriptToTreeNodeAdapter.$class$java$lang$ClassLoader) == null) {
            $class$java$lang$ClassLoader = (ScriptToTreeNodeAdapter.$class$java$lang$ClassLoader = class$("java.lang.ClassLoader"));
        }
        return $class$java$lang$ClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = ScriptToTreeNodeAdapter.$class$java$io$File) == null) {
            $class$java$io$File = (ScriptToTreeNodeAdapter.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = (ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = class$("groovy.inspect.swingui.TreeNodeBuildingNodeOperation"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeWithProperties() {
        Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
        if (($class$groovy$inspect$swingui$TreeNodeWithProperties = ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$TreeNodeWithProperties) == null) {
            $class$groovy$inspect$swingui$TreeNodeWithProperties = (ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$TreeNodeWithProperties = class$("groovy.inspect.swingui.TreeNodeWithProperties"));
        }
        return $class$groovy$inspect$swingui$TreeNodeWithProperties;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter() {
        Class $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
        if (($class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter) == null) {
            $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = (ScriptToTreeNodeAdapter.$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = class$("groovy.inspect.swingui.ScriptToTreeNodeAdapter"));
        }
        return $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ScriptToTreeNodeAdapter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ScriptToTreeNodeAdapter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Writable() {
        Class $class$groovy$lang$Writable;
        if (($class$groovy$lang$Writable = ScriptToTreeNodeAdapter.$class$groovy$lang$Writable) == null) {
            $class$groovy$lang$Writable = (ScriptToTreeNodeAdapter.$class$groovy$lang$Writable = class$("groovy.lang.Writable"));
        }
        return $class$groovy$lang$Writable;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilerConfiguration() {
        Class $class$org$codehaus$groovy$control$CompilerConfiguration;
        if (($class$org$codehaus$groovy$control$CompilerConfiguration = ScriptToTreeNodeAdapter.$class$org$codehaus$groovy$control$CompilerConfiguration) == null) {
            $class$org$codehaus$groovy$control$CompilerConfiguration = (ScriptToTreeNodeAdapter.$class$org$codehaus$groovy$control$CompilerConfiguration = class$("org.codehaus.groovy.control.CompilerConfiguration"));
        }
        return $class$org$codehaus$groovy$control$CompilerConfiguration;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = ScriptToTreeNodeAdapter.$class$java$lang$System) == null) {
            $class$java$lang$System = (ScriptToTreeNodeAdapter.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$text$GStringTemplateEngine() {
        Class $class$groovy$text$GStringTemplateEngine;
        if (($class$groovy$text$GStringTemplateEngine = ScriptToTreeNodeAdapter.$class$groovy$text$GStringTemplateEngine) == null) {
            $class$groovy$text$GStringTemplateEngine = (ScriptToTreeNodeAdapter.$class$groovy$text$GStringTemplateEngine = class$("groovy.text.GStringTemplateEngine"));
        }
        return $class$groovy$text$GStringTemplateEngine;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyCodeSource() {
        Class $class$groovy$lang$GroovyCodeSource;
        if (($class$groovy$lang$GroovyCodeSource = ScriptToTreeNodeAdapter.$class$groovy$lang$GroovyCodeSource) == null) {
            $class$groovy$lang$GroovyCodeSource = (ScriptToTreeNodeAdapter.$class$groovy$lang$GroovyCodeSource = class$("groovy.lang.GroovyCodeSource"));
        }
        return $class$groovy$lang$GroovyCodeSource;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Properties() {
        Class $class$java$util$Properties;
        if (($class$java$util$Properties = ScriptToTreeNodeAdapter.$class$java$util$Properties) == null) {
            $class$java$util$Properties = (ScriptToTreeNodeAdapter.$class$java$util$Properties = class$("java.util.Properties"));
        }
        return $class$java$util$Properties;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilationUnit() {
        Class $class$org$codehaus$groovy$control$CompilationUnit;
        if (($class$org$codehaus$groovy$control$CompilationUnit = ScriptToTreeNodeAdapter.$class$org$codehaus$groovy$control$CompilationUnit) == null) {
            $class$org$codehaus$groovy$control$CompilationUnit = (ScriptToTreeNodeAdapter.$class$org$codehaus$groovy$control$CompilationUnit = class$("org.codehaus.groovy.control.CompilationUnit"));
        }
        return $class$org$codehaus$groovy$control$CompilationUnit;
    }
    
    private static /* synthetic */ Class $get$$class$java$net$URL() {
        Class $class$java$net$URL;
        if (($class$java$net$URL = ScriptToTreeNodeAdapter.$class$java$net$URL) == null) {
            $class$java$net$URL = (ScriptToTreeNodeAdapter.$class$java$net$URL = class$("java.net.URL"));
        }
        return $class$java$net$URL;
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
