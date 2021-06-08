// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.PrintWriter;
import java.util.Comparator;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import groovy.lang.Reference;
import org.mudebug.prapr.reloc.commons.cli.Options;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class HelpFormatter extends org.mudebug.prapr.reloc.commons.cli.HelpFormatter implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204866;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    private static /* synthetic */ Class $class$jline$Terminal;
    
    public HelpFormatter() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        ScriptBytecodeAdapter.setGroovyObjectProperty(HelpFormatter.$const$0, $get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter(), this, "defaultLeftPad");
        ScriptBytecodeAdapter.setGroovyObjectProperty(HelpFormatter.$const$1, $get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter(), this, "defaultDescPad");
    }
    
    public int getDefaultWidth() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGetProperty($get$$class$jline$Terminal())), HelpFormatter.$const$2), $get$$class$java$lang$Integer()));
    }
    
    protected StringBuffer renderOptions(final StringBuffer sb, final int width, final Options options, final int leftPad, final int descPad) {
        final StringBuffer sb2 = (StringBuffer)new Reference(sb);
        final Integer width2 = (Integer)new Reference(DefaultTypeTransformation.box(width));
        final Integer descPad2 = (Integer)new Reference(DefaultTypeTransformation.box(descPad));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(((Reference<Object>)sb2).get(), 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 11);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert sb != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        final ValueRecorder recorder2 = new ValueRecorder();
        try {
            if (DefaultTypeTransformation.booleanUnbox(recorder2.record(options, 8))) {
                recorder2.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert options", recorder2), null);
            }
        }
        finally {
            recorder2.clear();
        }
        final List prefixes = (List)new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final String lpad = (String)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(" ", DefaultTypeTransformation.box(leftPad)), $get$$class$java$lang$String()));
        final List opts = (List)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call($getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(options)), new HelpFormatter$_renderOptions_closure1(this, this)), $get$$class$java$util$List()));
        $getCallSiteArray[7].call(((Reference)opts).get(), new HelpFormatter$_renderOptions_closure2(this, this, (Reference<Object>)lpad, (Reference<Object>)prefixes));
        final Integer maxPrefix = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call($getCallSiteArray[9].call(((Reference)prefixes).get(), new HelpFormatter$_renderOptions_closure3(this, this))), $get$$class$java$lang$Integer()));
        final String dpad = (String)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(" ", ((Reference<Object>)descPad2).get()), $get$$class$java$lang$String()));
        $getCallSiteArray[11].call(((Reference)opts).get(), new HelpFormatter$_renderOptions_closure4(this, this, (Reference<Object>)opts, (Reference<Object>)descPad2, (Reference<Object>)dpad, (Reference<Object>)width2, (Reference<Object>)sb2, (Reference<Object>)maxPrefix, (Reference<Object>)prefixes));
        return (StringBuffer)ScriptBytecodeAdapter.castToType(((Reference<Object>)sb2).get(), $get$$class$java$lang$StringBuffer());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HelpFormatter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HelpFormatter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HelpFormatter.__timeStamp__239_neverHappen1292524204866 = 0L;
        HelpFormatter.__timeStamp = 1292524204866L;
        $const$2 = 1;
        $const$1 = 4;
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpFormatter.$callSiteArray == null || ($createCallSiteArray = HelpFormatter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpFormatter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = HelpFormatter.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (HelpFormatter.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HelpFormatter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HelpFormatter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter() {
        Class $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
        if (($class$org$codehaus$groovy$tools$shell$util$HelpFormatter = HelpFormatter.$class$org$codehaus$groovy$tools$shell$util$HelpFormatter) == null) {
            $class$org$codehaus$groovy$tools$shell$util$HelpFormatter = (HelpFormatter.$class$org$codehaus$groovy$tools$shell$util$HelpFormatter = class$("org.codehaus.groovy.tools.shell.util.HelpFormatter"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$HelpFormatter;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = HelpFormatter.$class$java$util$List) == null) {
            $class$java$util$List = (HelpFormatter.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = HelpFormatter.$class$java$lang$String) == null) {
            $class$java$lang$String = (HelpFormatter.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = HelpFormatter.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (HelpFormatter.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
    }
    
    private static /* synthetic */ Class $get$$class$jline$Terminal() {
        Class $class$jline$Terminal;
        if (($class$jline$Terminal = HelpFormatter.$class$jline$Terminal) == null) {
            $class$jline$Terminal = (HelpFormatter.$class$jline$Terminal = class$("jline.Terminal"));
        }
        return $class$jline$Terminal;
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
