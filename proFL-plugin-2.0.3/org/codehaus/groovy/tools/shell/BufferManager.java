// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.transform.powerassert.AssertionRenderer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.powerassert.ValueRecorder;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public class BufferManager implements GroovyObject
{
    protected final Logger log;
    private final List buffers;
    private int selected;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203942;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$BufferManager;
    
    public BufferManager() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.log = (Logger)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$util$Logger()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
        this.buffers = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[2].callCurrent(this);
    }
    
    public void reset() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[3].call(this.buffers);
        $getCallSiteArray[4].callCurrent(this, Boolean.TRUE);
        $getCallSiteArray[5].call(this.log, "Buffers reset");
    }
    
    public List current() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean b = !DefaultTypeTransformation.booleanUnbox(recorder.record($getCallSiteArray[6].call(recorder.record(recorder.record(recorder.record(this.buffers, -1), -1), 9)), 17));
            recorder.record(b ? Boolean.TRUE : Boolean.FALSE, 8);
            if (b) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert !buffers.isEmpty()", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(this.buffers, DefaultTypeTransformation.box(this.selected)), $get$$class$java$util$List());
    }
    
    public void select(final int index) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareGreaterThanEqual = ScriptBytecodeAdapter.compareGreaterThanEqual(recorder.record(DefaultTypeTransformation.box(index), 8), BufferManager.$const$0);
            recorder.record(compareGreaterThanEqual ? Boolean.TRUE : Boolean.FALSE, 14);
            Boolean value = null;
            Label_0141: {
                if (compareGreaterThanEqual) {
                    final boolean compareLessThan = ScriptBytecodeAdapter.compareLessThan(recorder.record(DefaultTypeTransformation.box(index), 22), recorder.record($getCallSiteArray[8].call(recorder.record(recorder.record(recorder.record(this.buffers, -1), -1), 30)), 38));
                    recorder.record(compareLessThan ? Boolean.TRUE : Boolean.FALSE, 28);
                    if (compareLessThan) {
                        value = Boolean.TRUE;
                        break Label_0141;
                    }
                }
                value = Boolean.FALSE;
            }
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(value, 19))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert index >= 0 && index < buffers.size()", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        this.selected = DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.box(index));
    }
    
    public int create(final boolean select) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[9].call(this.buffers, ScriptBytecodeAdapter.createList(new Object[0]));
        final Object i = $getCallSiteArray[10].call($getCallSiteArray[11].call(this.buffers), BufferManager.$const$1);
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(select))) {
            $getCallSiteArray[12].callCurrent(this, i);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].callGetProperty(this.log))) {
            $getCallSiteArray[14].call(this.log, new GStringImpl(new Object[] { i }, new String[] { "Created new buffer with index: ", "" }));
        }
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(i, $get$$class$java$lang$Integer()));
    }
    
    public void delete(final int index) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareGreaterThanEqual = ScriptBytecodeAdapter.compareGreaterThanEqual(recorder.record(DefaultTypeTransformation.box(index), 8), BufferManager.$const$0);
            recorder.record(compareGreaterThanEqual ? Boolean.TRUE : Boolean.FALSE, 14);
            Boolean value = null;
            Label_0141: {
                if (compareGreaterThanEqual) {
                    final boolean compareLessThan = ScriptBytecodeAdapter.compareLessThan(recorder.record(DefaultTypeTransformation.box(index), 22), recorder.record($getCallSiteArray[15].call(recorder.record(recorder.record(recorder.record(this.buffers, -1), -1), 30)), 38));
                    recorder.record(compareLessThan ? Boolean.TRUE : Boolean.FALSE, 28);
                    if (compareLessThan) {
                        value = Boolean.TRUE;
                        break Label_0141;
                    }
                }
                value = Boolean.FALSE;
            }
            if (DefaultTypeTransformation.booleanUnbox(recorder.record(value, 19))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert index >= 0 && index < buffers.size()", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        $getCallSiteArray[16].call(this.buffers, DefaultTypeTransformation.box(index));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[17].callGetProperty(this.log))) {
            $getCallSiteArray[18].call(this.log, new GStringImpl(new Object[] { DefaultTypeTransformation.box(index) }, new String[] { "Deleted buffer with index: ", "" }));
        }
    }
    
    public int size() {
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray()[19].call(this.buffers), $get$$class$java$lang$Integer()));
    }
    
    public void deleteSelected() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[20].callCurrent(this, DefaultTypeTransformation.box(this.selected));
        final Object i = $getCallSiteArray[21].call(DefaultTypeTransformation.box(this.selected), BufferManager.$const$1);
        if (ScriptBytecodeAdapter.compareLessThan(i, BufferManager.$const$0)) {
            $getCallSiteArray[22].callCurrent(this, BufferManager.$const$0);
        }
        else {
            $getCallSiteArray[23].callCurrent(this, i);
        }
    }
    
    public void clearSelected() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[24].call($getCallSiteArray[25].callCurrent(this));
    }
    
    public void updateSelected(final List buffer) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ValueRecorder recorder = new ValueRecorder();
        try {
            final boolean compareNotEqual = ScriptBytecodeAdapter.compareNotEqual(recorder.record(buffer, 8), null);
            recorder.record(compareNotEqual ? Boolean.TRUE : Boolean.FALSE, 15);
            if (compareNotEqual) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert buffer != null", recorder), null);
            }
        }
        finally {
            recorder.clear();
        }
        $getCallSiteArray[26].call(this.buffers, DefaultTypeTransformation.box(this.selected), buffer);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$BufferManager()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = BufferManager.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (BufferManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        BufferManager.__timeStamp__239_neverHappen1292524203942 = 0L;
        BufferManager.__timeStamp = 1292524203942L;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public final List getBuffers() {
        return this.buffers;
    }
    
    public int getSelected() {
        return this.selected;
    }
    
    public void setSelected(final int selected) {
        this.selected = selected;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[27];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$BufferManager(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BufferManager.$callSiteArray == null || ($createCallSiteArray = BufferManager.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BufferManager.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = BufferManager.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (BufferManager.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = BufferManager.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (BufferManager.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = BufferManager.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (BufferManager.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = BufferManager.$class$java$util$List) == null) {
            $class$java$util$List = (BufferManager.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$BufferManager() {
        Class $class$org$codehaus$groovy$tools$shell$BufferManager;
        if (($class$org$codehaus$groovy$tools$shell$BufferManager = BufferManager.$class$org$codehaus$groovy$tools$shell$BufferManager) == null) {
            $class$org$codehaus$groovy$tools$shell$BufferManager = (BufferManager.$class$org$codehaus$groovy$tools$shell$BufferManager = class$("org.codehaus.groovy.tools.shell.BufferManager"));
        }
        return $class$org$codehaus$groovy$tools$shell$BufferManager;
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
