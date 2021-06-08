// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.beans.PropertyChangeListener;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.awt.event.ActionEvent;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import javax.swing.text.AttributeSet;
import groovy.lang.GroovyObject;
import javax.swing.AbstractAction;

public class AutoIndentAction extends AbstractAction implements GroovyObject
{
    private AttributeSet simpleAttributeSet;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204585;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$text$SimpleAttributeSet;
    private static /* synthetic */ Class $class$javax$swing$text$AttributeSet;
    private static /* synthetic */ Class $class$groovy$ui$text$AutoIndentAction;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public AutoIndentAction() {
        this.simpleAttributeSet = (AttributeSet)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$javax$swing$text$SimpleAttributeSet()), $get$$class$javax$swing$text$AttributeSet());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public void actionPerformed(final ActionEvent evt) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object inputArea = $getCallSiteArray[1].callGetProperty(evt);
        final Object rootElement = $getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGetProperty(inputArea));
        final Object cursorPos = $getCallSiteArray[4].call(inputArea);
        final Integer rowNum = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(rootElement, cursorPos), $get$$class$java$lang$Integer());
        final Object rowElement = $getCallSiteArray[6].call(rootElement, rowNum);
        final Integer startOffset = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(rowElement), $get$$class$java$lang$Integer());
        final Integer endOffset = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(rowElement), $get$$class$java$lang$Integer());
        final String rowContent = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call($getCallSiteArray[10].callGetProperty(inputArea), startOffset, $getCallSiteArray[11].call(endOffset, startOffset)), $get$$class$java$lang$String());
        final String contentBeforeCursor = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGetProperty(inputArea), startOffset, $getCallSiteArray[14].call(cursorPos, startOffset)), $get$$class$java$lang$String());
        final String whitespaceStr = (String)new Reference("");
        final Object matcher = ScriptBytecodeAdapter.findRegex(rowContent, "(?m)^(\\s*).*\\n$");
        $getCallSiteArray[15].call(matcher, new AutoIndentAction$_actionPerformed_closure1(this, this, (Reference<Object>)whitespaceStr));
        if (ScriptBytecodeAdapter.matchRegex(contentBeforeCursor, "(\\s)*")) {
            ((Reference<String>)whitespaceStr).set(contentBeforeCursor);
        }
        $getCallSiteArray[16].call($getCallSiteArray[17].callGetProperty(inputArea), cursorPos, $getCallSiteArray[18].call("\n", ((Reference<Object>)whitespaceStr).get()), this.simpleAttributeSet);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$text$AutoIndentAction()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AutoIndentAction.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AutoIndentAction.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AutoIndentAction.__timeStamp__239_neverHappen1292524204585 = 0L;
        AutoIndentAction.__timeStamp = 1292524204585L;
    }
    
    public AttributeSet getSimpleAttributeSet() {
        return this.simpleAttributeSet;
    }
    
    public void setSimpleAttributeSet(final AttributeSet simpleAttributeSet) {
        this.simpleAttributeSet = simpleAttributeSet;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$text$AutoIndentAction(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AutoIndentAction.$callSiteArray == null || ($createCallSiteArray = AutoIndentAction.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AutoIndentAction.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AutoIndentAction.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AutoIndentAction.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AutoIndentAction.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AutoIndentAction.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$SimpleAttributeSet() {
        Class $class$javax$swing$text$SimpleAttributeSet;
        if (($class$javax$swing$text$SimpleAttributeSet = AutoIndentAction.$class$javax$swing$text$SimpleAttributeSet) == null) {
            $class$javax$swing$text$SimpleAttributeSet = (AutoIndentAction.$class$javax$swing$text$SimpleAttributeSet = class$("javax.swing.text.SimpleAttributeSet"));
        }
        return $class$javax$swing$text$SimpleAttributeSet;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$AttributeSet() {
        Class $class$javax$swing$text$AttributeSet;
        if (($class$javax$swing$text$AttributeSet = AutoIndentAction.$class$javax$swing$text$AttributeSet) == null) {
            $class$javax$swing$text$AttributeSet = (AutoIndentAction.$class$javax$swing$text$AttributeSet = class$("javax.swing.text.AttributeSet"));
        }
        return $class$javax$swing$text$AttributeSet;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$text$AutoIndentAction() {
        Class $class$groovy$ui$text$AutoIndentAction;
        if (($class$groovy$ui$text$AutoIndentAction = AutoIndentAction.$class$groovy$ui$text$AutoIndentAction) == null) {
            $class$groovy$ui$text$AutoIndentAction = (AutoIndentAction.$class$groovy$ui$text$AutoIndentAction = class$("groovy.ui.text.AutoIndentAction"));
        }
        return $class$groovy$ui$text$AutoIndentAction;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AutoIndentAction.$class$java$lang$String) == null) {
            $class$java$lang$String = (AutoIndentAction.$class$java$lang$String = class$("java.lang.String"));
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
