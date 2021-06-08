// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.WeakHashMap;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.MetaClass;
import java.math.BigInteger;
import java.math.BigDecimal;
import groovy.lang.Script;
import java.util.regex.Pattern;
import groovy.lang.Range;
import java.util.Map;
import java.util.List;
import groovy.lang.GString;
import groovy.lang.Closure;
import groovy.lang.Reference;
import org.codehaus.groovy.vmplugin.VMPluginFactory;
import java.lang.ref.SoftReference;

public class ClassHelper
{
    private static final Class[] classes;
    private static final String[] primitiveClassNames;
    public static final ClassNode DYNAMIC_TYPE;
    public static final ClassNode OBJECT_TYPE;
    public static final ClassNode VOID_TYPE;
    public static final ClassNode CLOSURE_TYPE;
    public static final ClassNode GSTRING_TYPE;
    public static final ClassNode LIST_TYPE;
    public static final ClassNode MAP_TYPE;
    public static final ClassNode RANGE_TYPE;
    public static final ClassNode PATTERN_TYPE;
    public static final ClassNode STRING_TYPE;
    public static final ClassNode SCRIPT_TYPE;
    public static final ClassNode REFERENCE_TYPE;
    public static final ClassNode boolean_TYPE;
    public static final ClassNode char_TYPE;
    public static final ClassNode byte_TYPE;
    public static final ClassNode int_TYPE;
    public static final ClassNode long_TYPE;
    public static final ClassNode short_TYPE;
    public static final ClassNode double_TYPE;
    public static final ClassNode float_TYPE;
    public static final ClassNode Byte_TYPE;
    public static final ClassNode Short_TYPE;
    public static final ClassNode Integer_TYPE;
    public static final ClassNode Long_TYPE;
    public static final ClassNode Character_TYPE;
    public static final ClassNode Float_TYPE;
    public static final ClassNode Double_TYPE;
    public static final ClassNode Boolean_TYPE;
    public static final ClassNode BigInteger_TYPE;
    public static final ClassNode BigDecimal_TYPE;
    public static final ClassNode void_WRAPPER_TYPE;
    public static final ClassNode CLASS_Type;
    public static final ClassNode METACLASS_TYPE;
    public static final ClassNode GENERATED_CLOSURE_Type;
    public static final ClassNode Enum_Type;
    public static final ClassNode Annotation_TYPE;
    public static final ClassNode ELEMENT_TYPE_TYPE;
    private static ClassNode[] types;
    private static ClassNode[] numbers;
    protected static final ClassNode[] EMPTY_TYPE_ARRAY;
    public static final String OBJECT = "java.lang.Object";
    
    public static ClassNode makeCached(final Class c) {
        final SoftReference<ClassNode> classNodeSoftReference = ClassHelperCache.classCache.get(c);
        ClassNode classNode;
        if (classNodeSoftReference == null || (classNode = classNodeSoftReference.get()) == null) {
            classNode = new ClassNode(c);
            ClassHelperCache.classCache.put(c, new SoftReference<ClassNode>(classNode));
            VMPluginFactory.getPlugin().setAdditionalClassInformation(classNode);
        }
        return classNode;
    }
    
    public static ClassNode[] make(final Class[] classes) {
        final ClassNode[] cns = new ClassNode[classes.length];
        for (int i = 0; i < cns.length; ++i) {
            cns[i] = make(classes[i]);
        }
        return cns;
    }
    
    public static ClassNode make(final Class c) {
        return make(c, true);
    }
    
    public static ClassNode make(final Class c, final boolean includeGenerics) {
        for (int i = 0; i < ClassHelper.classes.length; ++i) {
            if (c == ClassHelper.classes[i]) {
                return ClassHelper.types[i];
            }
        }
        if (c.isArray()) {
            final ClassNode cn = make(c.getComponentType(), includeGenerics);
            return cn.makeArray();
        }
        return makeWithoutCaching(c, includeGenerics);
    }
    
    public static ClassNode makeWithoutCaching(final Class c) {
        return makeWithoutCaching(c, true);
    }
    
    public static ClassNode makeWithoutCaching(final Class c, final boolean includeGenerics) {
        if (c.isArray()) {
            final ClassNode cn = makeWithoutCaching(c.getComponentType(), includeGenerics);
            return cn.makeArray();
        }
        final ClassNode cached = makeCached(c);
        if (includeGenerics) {
            return cached;
        }
        final ClassNode t = makeWithoutCaching(c.getName());
        t.setRedirect(cached);
        return t;
    }
    
    public static ClassNode makeWithoutCaching(final String name) {
        final ClassNode cn = new ClassNode(name, 1, ClassHelper.OBJECT_TYPE);
        cn.isPrimaryNode = false;
        return cn;
    }
    
    public static ClassNode make(final String name) {
        if (name == null || name.length() == 0) {
            return ClassHelper.DYNAMIC_TYPE;
        }
        for (int i = 0; i < ClassHelper.primitiveClassNames.length; ++i) {
            if (ClassHelper.primitiveClassNames[i].equals(name)) {
                return ClassHelper.types[i];
            }
        }
        for (int i = 0; i < ClassHelper.classes.length; ++i) {
            final String cname = ClassHelper.classes[i].getName();
            if (name.equals(cname)) {
                return ClassHelper.types[i];
            }
        }
        return makeWithoutCaching(name);
    }
    
    public static ClassNode getWrapper(ClassNode cn) {
        cn = cn.redirect();
        if (!isPrimitiveType(cn)) {
            return cn;
        }
        if (cn == ClassHelper.boolean_TYPE) {
            return ClassHelper.Boolean_TYPE;
        }
        if (cn == ClassHelper.byte_TYPE) {
            return ClassHelper.Byte_TYPE;
        }
        if (cn == ClassHelper.char_TYPE) {
            return ClassHelper.Character_TYPE;
        }
        if (cn == ClassHelper.short_TYPE) {
            return ClassHelper.Short_TYPE;
        }
        if (cn == ClassHelper.int_TYPE) {
            return ClassHelper.Integer_TYPE;
        }
        if (cn == ClassHelper.long_TYPE) {
            return ClassHelper.Long_TYPE;
        }
        if (cn == ClassHelper.float_TYPE) {
            return ClassHelper.Float_TYPE;
        }
        if (cn == ClassHelper.double_TYPE) {
            return ClassHelper.Double_TYPE;
        }
        if (cn == ClassHelper.VOID_TYPE) {
            return ClassHelper.void_WRAPPER_TYPE;
        }
        return cn;
    }
    
    public static ClassNode getUnwrapper(ClassNode cn) {
        cn = cn.redirect();
        if (isPrimitiveType(cn)) {
            return cn;
        }
        if (cn == ClassHelper.Boolean_TYPE) {
            return ClassHelper.boolean_TYPE;
        }
        if (cn == ClassHelper.Byte_TYPE) {
            return ClassHelper.byte_TYPE;
        }
        if (cn == ClassHelper.Character_TYPE) {
            return ClassHelper.char_TYPE;
        }
        if (cn == ClassHelper.Short_TYPE) {
            return ClassHelper.short_TYPE;
        }
        if (cn == ClassHelper.Integer_TYPE) {
            return ClassHelper.int_TYPE;
        }
        if (cn == ClassHelper.Long_TYPE) {
            return ClassHelper.long_TYPE;
        }
        if (cn == ClassHelper.Float_TYPE) {
            return ClassHelper.float_TYPE;
        }
        if (cn == ClassHelper.Double_TYPE) {
            return ClassHelper.double_TYPE;
        }
        return cn;
    }
    
    public static boolean isPrimitiveType(final ClassNode cn) {
        return cn == ClassHelper.boolean_TYPE || cn == ClassHelper.char_TYPE || cn == ClassHelper.byte_TYPE || cn == ClassHelper.short_TYPE || cn == ClassHelper.int_TYPE || cn == ClassHelper.long_TYPE || cn == ClassHelper.float_TYPE || cn == ClassHelper.double_TYPE || cn == ClassHelper.VOID_TYPE;
    }
    
    public static boolean isNumberType(final ClassNode cn) {
        return cn == ClassHelper.Byte_TYPE || cn == ClassHelper.Short_TYPE || cn == ClassHelper.Integer_TYPE || cn == ClassHelper.Long_TYPE || cn == ClassHelper.Float_TYPE || cn == ClassHelper.Double_TYPE || cn == ClassHelper.byte_TYPE || cn == ClassHelper.short_TYPE || cn == ClassHelper.int_TYPE || cn == ClassHelper.long_TYPE || cn == ClassHelper.float_TYPE || cn == ClassHelper.double_TYPE;
    }
    
    public static ClassNode makeReference() {
        return make(Reference.class);
    }
    
    public static boolean isCachedType(final ClassNode type) {
        for (final ClassNode cachedType : ClassHelper.types) {
            if (cachedType == type) {
                return true;
            }
        }
        return false;
    }
    
    static {
        classes = new Class[] { Object.class, Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE, Void.TYPE, Closure.class, GString.class, List.class, Map.class, Range.class, Pattern.class, Script.class, String.class, Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Double.class, Float.class, BigDecimal.class, BigInteger.class, Void.class, Reference.class, Class.class, MetaClass.class };
        primitiveClassNames = new String[] { "", "boolean", "char", "byte", "short", "int", "long", "double", "float", "void" };
        DYNAMIC_TYPE = makeCached(Object.class);
        OBJECT_TYPE = ClassHelper.DYNAMIC_TYPE;
        VOID_TYPE = makeCached(Void.TYPE);
        CLOSURE_TYPE = makeCached(Closure.class);
        GSTRING_TYPE = makeCached(GString.class);
        LIST_TYPE = makeWithoutCaching(List.class);
        MAP_TYPE = makeWithoutCaching(Map.class);
        RANGE_TYPE = makeCached(Range.class);
        PATTERN_TYPE = makeCached(Pattern.class);
        STRING_TYPE = makeCached(String.class);
        SCRIPT_TYPE = makeCached(Script.class);
        REFERENCE_TYPE = makeWithoutCaching(Reference.class);
        boolean_TYPE = makeCached(Boolean.TYPE);
        char_TYPE = makeCached(Character.TYPE);
        byte_TYPE = makeCached(Byte.TYPE);
        int_TYPE = makeCached(Integer.TYPE);
        long_TYPE = makeCached(Long.TYPE);
        short_TYPE = makeCached(Short.TYPE);
        double_TYPE = makeCached(Double.TYPE);
        float_TYPE = makeCached(Float.TYPE);
        Byte_TYPE = makeCached(Byte.class);
        Short_TYPE = makeCached(Short.class);
        Integer_TYPE = makeCached(Integer.class);
        Long_TYPE = makeCached(Long.class);
        Character_TYPE = makeCached(Character.class);
        Float_TYPE = makeCached(Float.class);
        Double_TYPE = makeCached(Double.class);
        Boolean_TYPE = makeCached(Boolean.class);
        BigInteger_TYPE = makeCached(BigInteger.class);
        BigDecimal_TYPE = makeCached(BigDecimal.class);
        void_WRAPPER_TYPE = makeCached(Void.class);
        CLASS_Type = makeWithoutCaching(Class.class);
        METACLASS_TYPE = makeCached(MetaClass.class);
        GENERATED_CLOSURE_Type = makeCached(GeneratedClosure.class);
        Enum_Type = new ClassNode("java.lang.Enum", 0, ClassHelper.OBJECT_TYPE);
        Annotation_TYPE = new ClassNode("java.lang.annotation.Annotation", 0, ClassHelper.OBJECT_TYPE);
        ELEMENT_TYPE_TYPE = new ClassNode("java.lang.annotation.ElementType", 0, ClassHelper.Enum_Type);
        ClassHelper.Enum_Type.isPrimaryNode = false;
        ClassHelper.Annotation_TYPE.isPrimaryNode = false;
        ClassHelper.types = new ClassNode[] { ClassHelper.OBJECT_TYPE, ClassHelper.boolean_TYPE, ClassHelper.char_TYPE, ClassHelper.byte_TYPE, ClassHelper.short_TYPE, ClassHelper.int_TYPE, ClassHelper.long_TYPE, ClassHelper.double_TYPE, ClassHelper.float_TYPE, ClassHelper.VOID_TYPE, ClassHelper.CLOSURE_TYPE, ClassHelper.GSTRING_TYPE, ClassHelper.LIST_TYPE, ClassHelper.MAP_TYPE, ClassHelper.RANGE_TYPE, ClassHelper.PATTERN_TYPE, ClassHelper.SCRIPT_TYPE, ClassHelper.STRING_TYPE, ClassHelper.Boolean_TYPE, ClassHelper.Character_TYPE, ClassHelper.Byte_TYPE, ClassHelper.Short_TYPE, ClassHelper.Integer_TYPE, ClassHelper.Long_TYPE, ClassHelper.Double_TYPE, ClassHelper.Float_TYPE, ClassHelper.BigDecimal_TYPE, ClassHelper.BigInteger_TYPE, ClassHelper.void_WRAPPER_TYPE, ClassHelper.REFERENCE_TYPE, ClassHelper.CLASS_Type, ClassHelper.METACLASS_TYPE, ClassHelper.GENERATED_CLOSURE_Type, ClassHelper.Enum_Type, ClassHelper.Annotation_TYPE };
        ClassHelper.numbers = new ClassNode[] { ClassHelper.char_TYPE, ClassHelper.byte_TYPE, ClassHelper.short_TYPE, ClassHelper.int_TYPE, ClassHelper.long_TYPE, ClassHelper.double_TYPE, ClassHelper.float_TYPE, ClassHelper.Short_TYPE, ClassHelper.Byte_TYPE, ClassHelper.Character_TYPE, ClassHelper.Integer_TYPE, ClassHelper.Float_TYPE, ClassHelper.Long_TYPE, ClassHelper.Double_TYPE, ClassHelper.BigInteger_TYPE, ClassHelper.BigDecimal_TYPE };
        EMPTY_TYPE_ARRAY = new ClassNode[0];
    }
    
    static class ClassHelperCache
    {
        static Map<Class, SoftReference<ClassNode>> classCache;
        
        static {
            ClassHelperCache.classCache = new WeakHashMap<Class, SoftReference<ClassNode>>();
        }
    }
}
