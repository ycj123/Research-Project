// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import groovy.lang.GroovyObject;

public class IvyGrabRecord implements GroovyObject
{
    private ModuleRevisionId mrid;
    private List<String> conf;
    private boolean changing;
    private boolean transitive;
    private boolean force;
    private String classifier;
    private String ext;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Long $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Long $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Long $const$4;
    private static final /* synthetic */ Integer $const$5;
    private static final /* synthetic */ Integer $const$6;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202603;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$grape$IvyGrabRecord;
    
    public IvyGrabRecord() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    @Override
    public int hashCode() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].call($getCallSiteArray[4].call($getCallSiteArray[5].call($getCallSiteArray[6].call(this.mrid), $getCallSiteArray[7].call(this.conf)), DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.changing)) ? IvyGrabRecord.$const$0 : IvyGrabRecord.$const$1), DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.transitive)) ? IvyGrabRecord.$const$2 : IvyGrabRecord.$const$3), DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.force)) ? IvyGrabRecord.$const$4 : IvyGrabRecord.$const$5), DefaultTypeTransformation.booleanUnbox(this.classifier) ? $getCallSiteArray[8].call(this.classifier) : IvyGrabRecord.$const$6), DefaultTypeTransformation.booleanUnbox(this.ext) ? $getCallSiteArray[9].call(this.ext) : IvyGrabRecord.$const$6), $get$$class$java$lang$Integer()));
    }
    
    @Override
    public boolean equals(final Object o) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[10].callGetProperty(o), $get$$class$groovy$grape$IvyGrabRecord()) && ScriptBytecodeAdapter.compareEqual(DefaultTypeTransformation.box(this.changing), $getCallSiteArray[11].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(DefaultTypeTransformation.box(this.transitive), $getCallSiteArray[12].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(DefaultTypeTransformation.box(this.force), $getCallSiteArray[13].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(this.mrid, $getCallSiteArray[14].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(this.conf, $getCallSiteArray[15].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(this.classifier, $getCallSiteArray[16].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual(this.ext, $getCallSiteArray[17].callGetProperty(o))) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$grape$IvyGrabRecord()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = IvyGrabRecord.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (IvyGrabRecord.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        IvyGrabRecord.__timeStamp__239_neverHappen1292524202603 = 0L;
        IvyGrabRecord.__timeStamp = 1292524202603L;
        $const$6 = 0;
        $const$5 = 2004318071;
        $const$4 = 3435973836L;
        $const$3 = 1717986918;
        $const$2 = 3149642683L;
        $const$1 = 1431655765;
        $const$0 = 2863311530L;
    }
    
    public ModuleRevisionId getMrid() {
        return this.mrid;
    }
    
    public void setMrid(final ModuleRevisionId mrid) {
        this.mrid = mrid;
    }
    
    public List<String> getConf() {
        return this.conf;
    }
    
    public void setConf(final List<String> conf) {
        this.conf = conf;
    }
    
    public boolean getChanging() {
        return this.changing;
    }
    
    public boolean isChanging() {
        return this.changing;
    }
    
    public void setChanging(final boolean changing) {
        this.changing = changing;
    }
    
    public boolean getTransitive() {
        return this.transitive;
    }
    
    public boolean isTransitive() {
        return this.transitive;
    }
    
    public void setTransitive(final boolean transitive) {
        this.transitive = transitive;
    }
    
    public boolean getForce() {
        return this.force;
    }
    
    public boolean isForce() {
        return this.force;
    }
    
    public void setForce(final boolean force) {
        this.force = force;
    }
    
    public String getClassifier() {
        return this.classifier;
    }
    
    public void setClassifier(final String classifier) {
        this.classifier = classifier;
    }
    
    public String getExt() {
        return this.ext;
    }
    
    public void setExt(final String ext) {
        this.ext = ext;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$IvyGrabRecord(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (IvyGrabRecord.$callSiteArray == null || ($createCallSiteArray = IvyGrabRecord.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            IvyGrabRecord.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = IvyGrabRecord.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (IvyGrabRecord.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = IvyGrabRecord.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (IvyGrabRecord.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = IvyGrabRecord.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (IvyGrabRecord.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$IvyGrabRecord() {
        Class $class$groovy$grape$IvyGrabRecord;
        if (($class$groovy$grape$IvyGrabRecord = IvyGrabRecord.$class$groovy$grape$IvyGrabRecord) == null) {
            $class$groovy$grape$IvyGrabRecord = (IvyGrabRecord.$class$groovy$grape$IvyGrabRecord = class$("groovy.grape.IvyGrabRecord"));
        }
        return $class$groovy$grape$IvyGrabRecord;
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
