// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import java.io.File;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import javax.swing.filechooser.FileFilter;

public class GroovyFileFilter extends FileFilter implements GroovyObject
{
    private static final Object GROOVY_SOURCE_EXTENSIONS;
    private static final Object GROOVY_SOURCE_EXT_DESC;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204582;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$ui$GroovyFileFilter;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public GroovyFileFilter() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    @Override
    public boolean accept(final File f) {
        final File f2 = (File)new Reference(f);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(((Reference<Object>)f2).get()))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(GroovyFileFilter.GROOVY_SOURCE_EXTENSIONS, new GroovyFileFilter$_accept_closure1(this, this, (Reference<Object>)f2))) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public String getDescription() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { GroovyFileFilter.GROOVY_SOURCE_EXT_DESC }, new String[] { "Groovy Source Files (", ")" }), $get$$class$java$lang$String());
    }
    
    public static String getExtension(final Object f) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object ext = null;
        final Object s = $getCallSiteArray[2].call(f);
        final Object i = $getCallSiteArray[3].call(s, ".");
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareGreaterThan(i, GroovyFileFilter.$const$0) && ScriptBytecodeAdapter.compareLessThan(i, $getCallSiteArray[4].call($getCallSiteArray[5].call(s), GroovyFileFilter.$const$1))) ? Boolean.TRUE : Boolean.FALSE)) {
            ext = $getCallSiteArray[6].call($getCallSiteArray[7].call(s, i));
        }
        return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { ext }, new String[] { "*", "" }), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$GroovyFileFilter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GroovyFileFilter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GroovyFileFilter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        GroovyFileFilter.__timeStamp__239_neverHappen1292524204582 = 0L;
        GroovyFileFilter.__timeStamp = 1292524204582L;
        $const$1 = 1;
        $const$0 = 0;
        GROOVY_SOURCE_EXTENSIONS = ScriptBytecodeAdapter.createList(new Object[] { "*.groovy", "*.gvy", "*.gy", "*.gsh" });
        GROOVY_SOURCE_EXT_DESC = $getCallSiteArray()[8].call(GroovyFileFilter.GROOVY_SOURCE_EXTENSIONS, ",");
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$GroovyFileFilter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GroovyFileFilter.$callSiteArray == null || ($createCallSiteArray = GroovyFileFilter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GroovyFileFilter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GroovyFileFilter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GroovyFileFilter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$GroovyFileFilter() {
        Class $class$groovy$ui$GroovyFileFilter;
        if (($class$groovy$ui$GroovyFileFilter = GroovyFileFilter.$class$groovy$ui$GroovyFileFilter) == null) {
            $class$groovy$ui$GroovyFileFilter = (GroovyFileFilter.$class$groovy$ui$GroovyFileFilter = class$("groovy.ui.GroovyFileFilter"));
        }
        return $class$groovy$ui$GroovyFileFilter;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = GroovyFileFilter.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (GroovyFileFilter.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GroovyFileFilter.$class$java$lang$String) == null) {
            $class$java$lang$String = (GroovyFileFilter.$class$java$lang$String = class$("java.lang.String"));
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
