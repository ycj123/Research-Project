// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.MixinNode;
import org.codehaus.groovy.ast.ClassNode;

public class EnumHelper
{
    private static final int FS = 24;
    private static final int PUBLIC_FS = 25;
    
    public static ClassNode makeEnumNode(String name, int modifiers, final ClassNode[] interfaces, final ClassNode outerClass) {
        modifiers = (modifiers | 0x10 | 0x4000);
        ClassNode enumClass;
        if (outerClass == null) {
            enumClass = new ClassNode(name, modifiers, null, interfaces, MixinNode.EMPTY_ARRAY);
        }
        else {
            name = outerClass.getName() + "$" + name;
            enumClass = new InnerClassNode(outerClass, name, modifiers, null, interfaces, MixinNode.EMPTY_ARRAY);
        }
        final GenericsType gt = new GenericsType(enumClass);
        final ClassNode superClass = ClassHelper.makeWithoutCaching("java.lang.Enum");
        superClass.setGenericsTypes(new GenericsType[] { gt });
        enumClass.setSuperClass(superClass);
        superClass.setRedirect(ClassHelper.Enum_Type);
        return enumClass;
    }
    
    public static void addEnumConstant(final ClassNode enumClass, final String name, Expression init) {
        final int modifiers = 16409;
        if (init != null && !(init instanceof ListExpression)) {
            final ListExpression list = new ListExpression();
            list.addExpression(init);
            init = list;
        }
        final FieldNode fn = new FieldNode(name, modifiers, enumClass, enumClass, init);
        enumClass.addField(fn);
    }
}
