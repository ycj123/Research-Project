// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.io.File;
import groovy.lang.GString;
import java.net.URL;
import org.codehaus.groovy.runtime.GStringImpl;
import java.awt.Image;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class ImageIconFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204466;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$ImageIcon;
    private static /* synthetic */ Class $class$groovy$swing$factory$ImageIconFactory;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    
    public ImageIconFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, Object value, final Map attributes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(attributes, "image"))) {
                value = $getCallSiteArray[1].call(attributes, "image");
                if (!(value instanceof Image)) {
                    throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " image: attributes must be of type java.awt.Image" }));
                }
            }
            else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(attributes, "url"))) {
                value = $getCallSiteArray[4].call(attributes, "url");
                if (!(value instanceof URL)) {
                    throw (Throwable)$getCallSiteArray[5].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " url: attributes must be of type java.net.URL" }));
                }
            }
            else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(attributes, "file"))) {
                value = $getCallSiteArray[7].call(attributes, "file");
                if (value instanceof GString) {
                    value = ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
                }
                if (value instanceof File) {
                    value = $getCallSiteArray[8].call(value);
                }
                else if (!(value instanceof String)) {
                    throw (Throwable)$getCallSiteArray[9].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "In ", " file: attributes must be of type java.io.File or a string" }));
                }
            }
        }
        else if (value instanceof GString) {
            value = ScriptBytecodeAdapter.asType(value, $get$$class$java$lang$String());
        }
        Object resource = null;
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual(value, null) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].call(attributes, "resource"))) ? Boolean.TRUE : Boolean.FALSE)) {
            resource = $getCallSiteArray[11].call(attributes, "resource");
        }
        else if (DefaultTypeTransformation.booleanUnbox((value instanceof String && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call($getCallSiteArray[13].callConstructor($get$$class$java$io$File(), value)))) ? Boolean.TRUE : Boolean.FALSE)) {
            resource = value;
        }
        if (ScriptBytecodeAdapter.compareNotEqual(resource, null)) {
            Object klass = $getCallSiteArray[14].callGetProperty($getCallSiteArray[15].callGroovyObjectGetProperty(builder));
            final Object origValue = value;
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call(attributes, "class"))) {
                klass = $getCallSiteArray[17].call(attributes, "class");
            }
            if (ScriptBytecodeAdapter.compareEqual(klass, null)) {
                klass = $get$$class$groovy$swing$factory$ImageIconFactory();
            }
            else if (!(klass instanceof Class)) {
                klass = $getCallSiteArray[18].callGetProperty(klass);
            }
            value = $getCallSiteArray[19].call(klass, resource);
            if (ScriptBytecodeAdapter.compareEqual(value, null)) {
                throw (Throwable)$getCallSiteArray[20].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name, origValue }, new String[] { "In ", " the value argument '", "' does not refer to a file or a class resource" }));
            }
        }
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)$getCallSiteArray[21].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { name }, new String[] { "", " has neither a value argument or one of image:, url:, file:, or resource:" }));
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[22].call(attributes, "description"))) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[23].callConstructor($get$$class$javax$swing$ImageIcon(), value, $getCallSiteArray[24].call(attributes, "description")), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType($getCallSiteArray[25].callConstructor($get$$class$javax$swing$ImageIcon(), value), $get$$class$java$lang$Object());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$ImageIconFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = ImageIconFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (ImageIconFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        ImageIconFactory.__timeStamp__239_neverHappen1292524204466 = 0L;
        ImageIconFactory.__timeStamp = 1292524204466L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[26];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$ImageIconFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ImageIconFactory.$callSiteArray == null || ($createCallSiteArray = ImageIconFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ImageIconFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ImageIcon() {
        Class $class$javax$swing$ImageIcon;
        if (($class$javax$swing$ImageIcon = ImageIconFactory.$class$javax$swing$ImageIcon) == null) {
            $class$javax$swing$ImageIcon = (ImageIconFactory.$class$javax$swing$ImageIcon = class$("javax.swing.ImageIcon"));
        }
        return $class$javax$swing$ImageIcon;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$ImageIconFactory() {
        Class $class$groovy$swing$factory$ImageIconFactory;
        if (($class$groovy$swing$factory$ImageIconFactory = ImageIconFactory.$class$groovy$swing$factory$ImageIconFactory) == null) {
            $class$groovy$swing$factory$ImageIconFactory = (ImageIconFactory.$class$groovy$swing$factory$ImageIconFactory = class$("groovy.swing.factory.ImageIconFactory"));
        }
        return $class$groovy$swing$factory$ImageIconFactory;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = ImageIconFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (ImageIconFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ImageIconFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ImageIconFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = ImageIconFactory.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (ImageIconFactory.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = ImageIconFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (ImageIconFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = ImageIconFactory.$class$java$io$File) == null) {
            $class$java$io$File = (ImageIconFactory.$class$java$io$File = class$("java.io.File"));
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
