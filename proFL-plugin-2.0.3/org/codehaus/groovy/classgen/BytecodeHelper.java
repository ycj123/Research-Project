// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.MethodNode;
import groovyjarjarasm.asm.Label;
import org.codehaus.groovy.ast.FieldNode;
import java.math.BigInteger;
import java.math.BigDecimal;
import org.codehaus.groovy.ast.Parameter;
import groovyjarjarasm.asm.Type;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Opcodes;

public class BytecodeHelper implements Opcodes
{
    private MethodVisitor mv;
    
    public MethodVisitor getMethodVisitor() {
        return this.mv;
    }
    
    public BytecodeHelper(final MethodVisitor mv) {
        this.mv = mv;
    }
    
    public void quickBoxIfNecessary(final ClassNode type) {
        final String descr = getTypeDescription(type);
        if (type == ClassHelper.boolean_TYPE) {
            this.boxBoolean();
        }
        else if (ClassHelper.isPrimitiveType(type) && type != ClassHelper.VOID_TYPE) {
            final ClassNode wrapper = ClassHelper.getWrapper(type);
            final String internName = getClassInternalName(wrapper);
            this.mv.visitTypeInsn(187, internName);
            this.mv.visitInsn(89);
            if (type == ClassHelper.double_TYPE || type == ClassHelper.long_TYPE) {
                this.mv.visitInsn(94);
                this.mv.visitInsn(88);
            }
            else {
                this.mv.visitInsn(93);
                this.mv.visitInsn(88);
            }
            this.mv.visitMethodInsn(183, internName, "<init>", "(" + descr + ")V");
        }
    }
    
    public void quickUnboxIfNecessary(final ClassNode type) {
        if (ClassHelper.isPrimitiveType(type) && type != ClassHelper.VOID_TYPE) {
            final ClassNode wrapper = ClassHelper.getWrapper(type);
            final String internName = getClassInternalName(wrapper);
            if (type == ClassHelper.boolean_TYPE) {
                this.mv.visitTypeInsn(192, internName);
                this.mv.visitMethodInsn(182, internName, type.getName() + "Value", "()" + getTypeDescription(type));
            }
            else {
                this.mv.visitTypeInsn(192, "java/lang/Number");
                this.mv.visitMethodInsn(182, "java/lang/Number", type.getName() + "Value", "()" + getTypeDescription(type));
            }
        }
    }
    
    public void box(final Class type) {
        if (ReflectionCache.getCachedClass(type).isPrimitive && type != Void.TYPE) {
            final String returnString = "(" + getTypeDescription(type) + ")Ljava/lang/Object;";
            this.mv.visitMethodInsn(184, getClassInternalName(DefaultTypeTransformation.class.getName()), "box", returnString);
        }
    }
    
    public void box(final ClassNode type) {
        if (type.isPrimaryClassNode()) {
            return;
        }
        this.box(type.getTypeClass());
    }
    
    public void unbox(final Class type) {
        if (type.isPrimitive() && type != Void.TYPE) {
            final String returnString = "(Ljava/lang/Object;)" + getTypeDescription(type);
            this.mv.visitMethodInsn(184, getClassInternalName(DefaultTypeTransformation.class.getName()), type.getName() + "Unbox", returnString);
        }
    }
    
    public void unbox(final ClassNode type) {
        if (type.isPrimaryClassNode()) {
            return;
        }
        this.unbox(type.getTypeClass());
    }
    
    public static String getClassInternalName(final ClassNode t) {
        if (t.isPrimaryClassNode()) {
            return getClassInternalName(t.getName());
        }
        return getClassInternalName(t.getTypeClass());
    }
    
    public static String getClassInternalName(final Class t) {
        return Type.getInternalName(t);
    }
    
    public static String getClassInternalName(final String name) {
        return name.replace('.', '/');
    }
    
    public static String getMethodDescriptor(final ClassNode returnType, final Parameter[] parameters) {
        final StringBuffer buffer = new StringBuffer("(");
        for (int i = 0; i < parameters.length; ++i) {
            buffer.append(getTypeDescription(parameters[i].getType()));
        }
        buffer.append(")");
        buffer.append(getTypeDescription(returnType));
        return buffer.toString();
    }
    
    public static String getMethodDescriptor(final Class returnType, final Class[] paramTypes) {
        final StringBuffer buffer = new StringBuffer("(");
        for (int i = 0; i < paramTypes.length; ++i) {
            buffer.append(getTypeDescription(paramTypes[i]));
        }
        buffer.append(")");
        buffer.append(getTypeDescription(returnType));
        return buffer.toString();
    }
    
    public static String getTypeDescription(final Class c) {
        return Type.getDescriptor(c);
    }
    
    public static String getClassLoadingTypeDescription(ClassNode c) {
        final StringBuffer buf = new StringBuffer();
        boolean array = false;
        while (c.isArray()) {
            buf.append('[');
            c = c.getComponentType();
            array = true;
        }
        if (ClassHelper.isPrimitiveType(c)) {
            buf.append(getTypeDescription(c));
        }
        else {
            if (array) {
                buf.append('L');
            }
            buf.append(c.getName());
            if (array) {
                buf.append(';');
            }
        }
        return buf.toString();
    }
    
    public static String getTypeDescription(final ClassNode c) {
        return getTypeDescription(c, true);
    }
    
    private static String getTypeDescription(final ClassNode c, final boolean end) {
        final StringBuffer buf = new StringBuffer();
        ClassNode d;
        for (d = c; !ClassHelper.isPrimitiveType(d); d = d.getComponentType()) {
            if (!d.isArray()) {
                buf.append('L');
                final String name = d.getName();
                for (int len = name.length(), i = 0; i < len; ++i) {
                    final char car = name.charAt(i);
                    buf.append((car == '.') ? '/' : car);
                }
                if (end) {
                    buf.append(';');
                }
                return buf.toString();
            }
            buf.append('[');
        }
        char car2;
        if (d == ClassHelper.int_TYPE) {
            car2 = 'I';
        }
        else if (d == ClassHelper.VOID_TYPE) {
            car2 = 'V';
        }
        else if (d == ClassHelper.boolean_TYPE) {
            car2 = 'Z';
        }
        else if (d == ClassHelper.byte_TYPE) {
            car2 = 'B';
        }
        else if (d == ClassHelper.char_TYPE) {
            car2 = 'C';
        }
        else if (d == ClassHelper.short_TYPE) {
            car2 = 'S';
        }
        else if (d == ClassHelper.double_TYPE) {
            car2 = 'D';
        }
        else if (d == ClassHelper.float_TYPE) {
            car2 = 'F';
        }
        else {
            car2 = 'J';
        }
        buf.append(car2);
        return buf.toString();
    }
    
    public static String[] getClassInternalNames(final ClassNode[] names) {
        final int size = names.length;
        final String[] answer = new String[size];
        for (int i = 0; i < size; ++i) {
            answer[i] = getClassInternalName(names[i]);
        }
        return answer;
    }
    
    protected void pushConstant(final boolean value) {
        if (value) {
            this.mv.visitInsn(4);
        }
        else {
            this.mv.visitInsn(3);
        }
    }
    
    public void pushConstant(final int value) {
        switch (value) {
            case 0: {
                this.mv.visitInsn(3);
                break;
            }
            case 1: {
                this.mv.visitInsn(4);
                break;
            }
            case 2: {
                this.mv.visitInsn(5);
                break;
            }
            case 3: {
                this.mv.visitInsn(6);
                break;
            }
            case 4: {
                this.mv.visitInsn(7);
                break;
            }
            case 5: {
                this.mv.visitInsn(8);
                break;
            }
            default: {
                if (value >= -128 && value <= 127) {
                    this.mv.visitIntInsn(16, value);
                    break;
                }
                if (value >= -32768 && value <= 32767) {
                    this.mv.visitIntInsn(17, value);
                    break;
                }
                this.mv.visitLdcInsn(value);
                break;
            }
        }
    }
    
    public void doCast(final Class type) {
        if (type != Object.class) {
            if (type.isPrimitive() && type != Void.TYPE) {
                this.unbox(type);
            }
            else {
                this.mv.visitTypeInsn(192, type.isArray() ? getTypeDescription(type) : getClassInternalName(type.getName()));
            }
        }
    }
    
    public void doCast(final ClassNode type) {
        if (type == ClassHelper.OBJECT_TYPE) {
            return;
        }
        if (ClassHelper.isPrimitiveType(type) && type != ClassHelper.VOID_TYPE) {
            this.unbox(type);
        }
        else {
            this.mv.visitTypeInsn(192, type.isArray() ? getTypeDescription(type) : getClassInternalName(type));
        }
    }
    
    public void load(final ClassNode type, final int idx) {
        if (type == ClassHelper.double_TYPE) {
            this.mv.visitVarInsn(24, idx);
        }
        else if (type == ClassHelper.float_TYPE) {
            this.mv.visitVarInsn(23, idx);
        }
        else if (type == ClassHelper.long_TYPE) {
            this.mv.visitVarInsn(22, idx);
        }
        else if (type == ClassHelper.boolean_TYPE || type == ClassHelper.char_TYPE || type == ClassHelper.byte_TYPE || type == ClassHelper.int_TYPE || type == ClassHelper.short_TYPE) {
            this.mv.visitVarInsn(21, idx);
        }
        else {
            this.mv.visitVarInsn(25, idx);
        }
    }
    
    public void load(final Variable v) {
        this.load(v.getType(), v.getIndex());
    }
    
    public void store(final Variable v, final boolean markStart) {
        final ClassNode type = v.getType();
        this.unbox(type);
        final int idx = v.getIndex();
        if (type == ClassHelper.double_TYPE) {
            this.mv.visitVarInsn(57, idx);
        }
        else if (type == ClassHelper.float_TYPE) {
            this.mv.visitVarInsn(56, idx);
        }
        else if (type == ClassHelper.long_TYPE) {
            this.mv.visitVarInsn(55, idx);
        }
        else if (type == ClassHelper.boolean_TYPE || type == ClassHelper.char_TYPE || type == ClassHelper.byte_TYPE || type == ClassHelper.int_TYPE || type == ClassHelper.short_TYPE) {
            this.mv.visitVarInsn(54, idx);
        }
        else {
            this.mv.visitVarInsn(58, idx);
        }
    }
    
    public void store(final Variable v) {
        this.store(v, false);
    }
    
    void loadConstant(final Object value) {
        if (value == null) {
            this.mv.visitInsn(1);
        }
        else if (value instanceof String) {
            this.mv.visitLdcInsn(value);
        }
        else if (value instanceof Character) {
            final String className = "java/lang/Character";
            this.mv.visitTypeInsn(187, className);
            this.mv.visitInsn(89);
            this.mv.visitLdcInsn(value);
            final String methodType = "(C)V";
            this.mv.visitMethodInsn(183, className, "<init>", methodType);
        }
        else if (value instanceof Number) {
            final Number n = (Number)value;
            final String className2 = getClassInternalName(value.getClass().getName());
            if (n instanceof BigDecimal || n instanceof BigInteger) {
                this.mv.visitTypeInsn(187, className2);
                this.mv.visitInsn(89);
                this.mv.visitLdcInsn(n.toString());
                this.mv.visitMethodInsn(183, className2, "<init>", "(Ljava/lang/String;)V");
            }
            else {
                String methodType2;
                if (n instanceof Integer) {
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(I)Ljava/lang/Integer;";
                }
                else if (n instanceof Double) {
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(D)Ljava/lang/Double;";
                }
                else if (n instanceof Float) {
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(F)Ljava/lang/Float;";
                }
                else if (n instanceof Long) {
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(J)Ljava/lang/Long;";
                }
                else if (n instanceof Short) {
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(S)Ljava/lang/Short;";
                }
                else {
                    if (!(n instanceof Byte)) {
                        throw new ClassGeneratorException("Cannot generate bytecode for constant: " + value + " of type: " + value.getClass().getName() + ".  Numeric constant type not supported.");
                    }
                    this.mv.visitLdcInsn(n);
                    methodType2 = "(B)Ljava/lang/Byte;";
                }
                this.mv.visitMethodInsn(184, className2, "valueOf", methodType2);
            }
        }
        else if (value instanceof Boolean) {
            final Boolean bool = (Boolean)value;
            final String text = bool ? "TRUE" : "FALSE";
            this.mv.visitFieldInsn(178, "java/lang/Boolean", text, "Ljava/lang/Boolean;");
        }
        else {
            if (!(value instanceof Class)) {
                throw new ClassGeneratorException("Cannot generate bytecode for constant: " + value + " of type: " + value.getClass().getName());
            }
            final Class vc = (Class)value;
            if (!vc.getName().equals("java.lang.Void")) {
                throw new ClassGeneratorException("Cannot generate bytecode for constant: " + value + " of type: " + value.getClass().getName());
            }
        }
    }
    
    @Deprecated
    public void loadVar(final Variable variable) {
        final int index = variable.getIndex();
        if (variable.isHolder()) {
            this.mv.visitVarInsn(25, index);
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "get", "()Ljava/lang/Object;");
        }
        else {
            this.load(variable);
            if (variable != Variable.THIS_VARIABLE && variable != Variable.SUPER_VARIABLE) {
                this.box(variable.getType());
            }
        }
    }
    
    public void loadVar(final Variable variable, final boolean useReferenceDirectly) {
        final int index = variable.getIndex();
        if (variable.isHolder()) {
            this.mv.visitVarInsn(25, index);
            if (!useReferenceDirectly) {
                this.mv.visitMethodInsn(182, "groovy/lang/Reference", "get", "()Ljava/lang/Object;");
            }
        }
        else {
            this.load(variable);
            if (variable != Variable.THIS_VARIABLE && variable != Variable.SUPER_VARIABLE) {
                this.box(variable.getType());
            }
        }
    }
    
    public void storeVar(final Variable variable) {
        final int index = variable.getIndex();
        if (variable.isHolder()) {
            this.mv.visitVarInsn(25, index);
            this.mv.visitInsn(95);
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "set", "(Ljava/lang/Object;)V");
        }
        else {
            this.store(variable, false);
        }
    }
    
    public void putField(final FieldNode fld) {
        this.putField(fld, getClassInternalName(fld.getOwner()));
    }
    
    public void putField(final FieldNode fld, final String ownerName) {
        this.mv.visitFieldInsn(181, ownerName, fld.getName(), getTypeDescription(fld.getType()));
    }
    
    public void swapObjectWith(final ClassNode type) {
        if (type == ClassHelper.long_TYPE || type == ClassHelper.double_TYPE) {
            this.mv.visitInsn(91);
            this.mv.visitInsn(87);
        }
        else {
            this.mv.visitInsn(95);
        }
    }
    
    public void swapWithObject(final ClassNode type) {
        if (type == ClassHelper.long_TYPE || type == ClassHelper.double_TYPE) {
            this.mv.visitInsn(93);
            this.mv.visitInsn(88);
        }
        else {
            this.mv.visitInsn(95);
        }
    }
    
    public static ClassNode boxOnPrimitive(final ClassNode type) {
        if (!type.isArray()) {
            return ClassHelper.getWrapper(type);
        }
        return boxOnPrimitive(type.getComponentType()).makeArray();
    }
    
    public void boxBoolean() {
        final Label l0 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.mv.visitFieldInsn(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        final Label l2 = new Label();
        this.mv.visitJumpInsn(167, l2);
        this.mv.visitLabel(l0);
        this.mv.visitFieldInsn(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.mv.visitLabel(l2);
    }
    
    public void negateBoolean() {
        final Label endLabel = new Label();
        final Label falseLabel = new Label();
        this.mv.visitJumpInsn(154, falseLabel);
        this.mv.visitInsn(4);
        this.mv.visitJumpInsn(167, endLabel);
        this.mv.visitLabel(falseLabel);
        this.mv.visitInsn(3);
        this.mv.visitLabel(endLabel);
    }
    
    public void mark(final String msg) {
        this.mv.visitLdcInsn(msg);
        this.mv.visitInsn(87);
    }
    
    public static String formatNameForClassLoading(String name) {
        if (name.equals("int") || name.equals("long") || name.equals("short") || name.equals("float") || name.equals("double") || name.equals("byte") || name.equals("char") || name.equals("boolean") || name.equals("void")) {
            return name;
        }
        if (name == null) {
            return "java.lang.Object;";
        }
        if (name.startsWith("[")) {
            return name.replace('/', '.');
        }
        if (name.startsWith("L")) {
            name = name.substring(1);
            if (name.endsWith(";")) {
                name = name.substring(0, name.length() - 1);
            }
            return name.replace('/', '.');
        }
        String prefix = "";
        if (!name.endsWith("[]")) {
            return name.replace('/', '.');
        }
        prefix = "[";
        name = name.substring(0, name.length() - 2);
        if (name.equals("int")) {
            return prefix + "I";
        }
        if (name.equals("long")) {
            return prefix + "J";
        }
        if (name.equals("short")) {
            return prefix + "S";
        }
        if (name.equals("float")) {
            return prefix + "F";
        }
        if (name.equals("double")) {
            return prefix + "D";
        }
        if (name.equals("byte")) {
            return prefix + "B";
        }
        if (name.equals("char")) {
            return prefix + "C";
        }
        if (name.equals("boolean")) {
            return prefix + "Z";
        }
        return prefix + "L" + name.replace('/', '.') + ";";
    }
    
    public void dup() {
        this.mv.visitInsn(89);
    }
    
    public void doReturn(final ClassNode returnType) {
        if (returnType == ClassHelper.double_TYPE) {
            this.mv.visitInsn(175);
        }
        else if (returnType == ClassHelper.float_TYPE) {
            this.mv.visitInsn(174);
        }
        else if (returnType == ClassHelper.long_TYPE) {
            this.mv.visitInsn(173);
        }
        else if (returnType == ClassHelper.boolean_TYPE || returnType == ClassHelper.char_TYPE || returnType == ClassHelper.byte_TYPE || returnType == ClassHelper.int_TYPE || returnType == ClassHelper.short_TYPE) {
            this.mv.visitInsn(172);
        }
        else if (returnType == ClassHelper.VOID_TYPE) {
            this.mv.visitInsn(177);
        }
        else {
            this.mv.visitInsn(176);
        }
    }
    
    private static boolean hasGenerics(final Parameter[] param) {
        if (param.length == 0) {
            return false;
        }
        for (int i = 0; i < param.length; ++i) {
            final ClassNode type = param[i].getType();
            if (hasGenerics(type)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean hasGenerics(final ClassNode type) {
        return type.isArray() ? hasGenerics(type.getComponentType()) : (type.getGenericsTypes() != null);
    }
    
    public static String getGenericsMethodSignature(final MethodNode node) {
        final GenericsType[] generics = node.getGenericsTypes();
        final Parameter[] param = node.getParameters();
        final ClassNode returnType = node.getReturnType();
        if (generics == null && !hasGenerics(param) && !hasGenerics(returnType)) {
            return null;
        }
        final StringBuffer ret = new StringBuffer(100);
        getGenericsTypeSpec(ret, generics);
        final GenericsType[] paramTypes = new GenericsType[param.length];
        for (int i = 0; i < param.length; ++i) {
            final ClassNode pType = param[i].getType();
            if (pType.getGenericsTypes() == null || !pType.isGenericsPlaceHolder()) {
                paramTypes[i] = new GenericsType(pType);
            }
            else {
                paramTypes[i] = pType.getGenericsTypes()[0];
            }
        }
        addSubTypes(ret, paramTypes, "(", ")");
        addSubTypes(ret, new GenericsType[] { new GenericsType(returnType) }, "", "");
        return ret.toString();
    }
    
    private static boolean usesGenericsInClassSignature(final ClassNode node) {
        if (!node.isUsingGenerics()) {
            return false;
        }
        if (hasGenerics(node)) {
            return true;
        }
        final ClassNode sclass = node.getUnresolvedSuperClass(false);
        if (sclass.isUsingGenerics()) {
            return true;
        }
        final ClassNode[] interfaces = node.getInterfaces();
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i].isUsingGenerics()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String getGenericsSignature(final ClassNode node) {
        if (!usesGenericsInClassSignature(node)) {
            return null;
        }
        final GenericsType[] genericsTypes = node.getGenericsTypes();
        final StringBuffer ret = new StringBuffer(100);
        getGenericsTypeSpec(ret, genericsTypes);
        final GenericsType extendsPart = new GenericsType(node.getUnresolvedSuperClass(false));
        writeGenericsBounds(ret, extendsPart, true);
        final ClassNode[] interfaces = node.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            final GenericsType interfacePart = new GenericsType(interfaces[i]);
            writeGenericsBounds(ret, interfacePart, false);
        }
        return ret.toString();
    }
    
    private static void getGenericsTypeSpec(final StringBuffer ret, final GenericsType[] genericsTypes) {
        if (genericsTypes == null) {
            return;
        }
        ret.append('<');
        for (int i = 0; i < genericsTypes.length; ++i) {
            final String name = genericsTypes[i].getName();
            ret.append(name);
            ret.append(':');
            writeGenericsBounds(ret, genericsTypes[i], true);
        }
        ret.append('>');
    }
    
    public static String getGenericsBounds(final ClassNode type) {
        final GenericsType[] genericsTypes = type.getGenericsTypes();
        if (genericsTypes == null) {
            return null;
        }
        final StringBuffer ret = new StringBuffer(100);
        if (type.isGenericsPlaceHolder()) {
            addSubTypes(ret, type.getGenericsTypes(), "", "");
        }
        else {
            final GenericsType gt = new GenericsType(type);
            writeGenericsBounds(ret, gt, false);
        }
        return ret.toString();
    }
    
    private static void writeGenericsBoundType(final StringBuffer ret, final ClassNode printType, final boolean writeInterfaceMarker) {
        if (writeInterfaceMarker && printType.isInterface()) {
            ret.append(":");
        }
        if (printType.equals(ClassHelper.OBJECT_TYPE) && printType.getGenericsTypes() != null) {
            ret.append("T");
            ret.append(printType.getGenericsTypes()[0].getName());
            ret.append(";");
        }
        else {
            ret.append(getTypeDescription(printType, false));
            addSubTypes(ret, printType.getGenericsTypes(), "<", ">");
            if (!ClassHelper.isPrimitiveType(printType)) {
                ret.append(";");
            }
        }
    }
    
    private static void writeGenericsBounds(final StringBuffer ret, final GenericsType type, final boolean writeInterfaceMarker) {
        if (type.getUpperBounds() != null) {
            final ClassNode[] bounds = type.getUpperBounds();
            for (int i = 0; i < bounds.length; ++i) {
                writeGenericsBoundType(ret, bounds[i], writeInterfaceMarker);
            }
        }
        else if (type.getLowerBound() != null) {
            writeGenericsBoundType(ret, type.getLowerBound(), writeInterfaceMarker);
        }
        else {
            writeGenericsBoundType(ret, type.getType(), writeInterfaceMarker);
        }
    }
    
    private static void addSubTypes(final StringBuffer ret, final GenericsType[] types, final String start, final String end) {
        if (types == null) {
            return;
        }
        ret.append(start);
        for (int i = 0; i < types.length; ++i) {
            if (types[i].getType().isArray()) {
                ret.append("[");
                addSubTypes(ret, new GenericsType[] { new GenericsType(types[i].getType().getComponentType()) }, "", "");
            }
            else if (types[i].isPlaceholder()) {
                ret.append('T');
                final String name = types[i].getName();
                ret.append(name);
                ret.append(';');
            }
            else if (types[i].isWildcard()) {
                if (types[i].getUpperBounds() != null) {
                    ret.append('+');
                    writeGenericsBounds(ret, types[i], false);
                }
                else if (types[i].getLowerBound() != null) {
                    ret.append('-');
                    writeGenericsBounds(ret, types[i], false);
                }
                else {
                    ret.append('*');
                }
            }
            else {
                writeGenericsBounds(ret, types[i], false);
            }
        }
        ret.append(end);
    }
}
