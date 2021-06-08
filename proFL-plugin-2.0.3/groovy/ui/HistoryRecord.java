// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class HistoryRecord implements GroovyObject
{
    private String allText;
    private int selectionStart;
    private int selectionEnd;
    private String scriptName;
    private Object result;
    private Throwable exception;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203530;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$ui$HistoryRecord;
    
    public HistoryRecord() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public String getTextToRun(final boolean useSelection) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(useSelection)) && ScriptBytecodeAdapter.compareNotEqual(DefaultTypeTransformation.box(this.selectionStart), DefaultTypeTransformation.box(this.selectionEnd))) ? Boolean.TRUE : Boolean.FALSE)) {
            return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(this.allText, ScriptBytecodeAdapter.createRange(DefaultTypeTransformation.box(this.selectionStart), DefaultTypeTransformation.box(this.selectionEnd), false)), $get$$class$java$lang$String());
        }
        return (String)ScriptBytecodeAdapter.castToType(this.allText, $get$$class$java$lang$String());
    }
    
    public Object getValue() {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(this.exception) ? this.exception : this.result, $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$HistoryRecord()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = HistoryRecord.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (HistoryRecord.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        HistoryRecord.__timeStamp__239_neverHappen1292524203530 = 0L;
        HistoryRecord.__timeStamp = 1292524203530L;
    }
    
    public String getAllText() {
        return this.allText;
    }
    
    public void setAllText(final String allText) {
        this.allText = allText;
    }
    
    public int getSelectionStart() {
        return this.selectionStart;
    }
    
    public void setSelectionStart(final int selectionStart) {
        this.selectionStart = selectionStart;
    }
    
    public int getSelectionEnd() {
        return this.selectionEnd;
    }
    
    public void setSelectionEnd(final int selectionEnd) {
        this.selectionEnd = selectionEnd;
    }
    
    public String getScriptName() {
        return this.scriptName;
    }
    
    public void setScriptName(final String scriptName) {
        this.scriptName = scriptName;
    }
    
    public Object getResult() {
        return this.result;
    }
    
    public void setResult(final Object result) {
        this.result = result;
    }
    
    public Throwable getException() {
        return this.exception;
    }
    
    public void setException(final Throwable exception) {
        this.exception = exception;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$HistoryRecord(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HistoryRecord.$callSiteArray == null || ($createCallSiteArray = HistoryRecord.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HistoryRecord.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = HistoryRecord.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (HistoryRecord.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HistoryRecord.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HistoryRecord.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = HistoryRecord.$class$java$lang$String) == null) {
            $class$java$lang$String = (HistoryRecord.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$HistoryRecord() {
        Class $class$groovy$ui$HistoryRecord;
        if (($class$groovy$ui$HistoryRecord = HistoryRecord.$class$groovy$ui$HistoryRecord) == null) {
            $class$groovy$ui$HistoryRecord = (HistoryRecord.$class$groovy$ui$HistoryRecord = class$("groovy.ui.HistoryRecord"));
        }
        return $class$groovy$ui$HistoryRecord;
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
