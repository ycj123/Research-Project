// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.ast.expr.RegexExpression;
import java.util.List;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Stack;
import java.io.Writer;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.ast.GroovyClassVisitor;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.control.CompilationUnit;

public class AstNodeToScriptVisitor extends PrimaryClassNodeOperation implements GroovyCodeVisitor, GroovyClassVisitor, GroovyObject
{
    private Writer _out;
    private Stack<String> classNameStack;
    private String _indent;
    private boolean readyToIndent;
    private boolean showScriptFreeForm;
    private boolean showScriptClass;
    private boolean scriptHasBeenVisited;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204115;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Stack;
    private static /* synthetic */ Class $class$java$lang$UnsupportedOperationException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
    private static /* synthetic */ Class $class$java$lang$reflect$Modifier;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$io$Writer;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$Expression;
    
    public AstNodeToScriptVisitor(final Writer writer, final boolean showScriptFreeForm, final boolean showScriptClass) {
        this.classNameStack = (Stack<String>)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$java$util$Stack()), $get$$class$java$util$Stack());
        this._indent = (String)ScriptBytecodeAdapter.castToType("", $get$$class$java$lang$String());
        this.readyToIndent = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this._out = (Writer)ScriptBytecodeAdapter.castToType(writer, $get$$class$java$io$Writer());
        this.showScriptFreeForm = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(showScriptFreeForm));
        this.showScriptClass = DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(showScriptClass));
        this.scriptHasBeenVisited = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
    }
    
    public AstNodeToScriptVisitor(final Writer writer, final boolean showScriptFreeForm) {
        $getCallSiteArray();
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = new Object[3])))));
        arguments[0] = ScriptBytecodeAdapter.createPojoWrapper(writer, $get$$class$java$io$Writer());
        arguments[1] = ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(showScriptFreeForm), $get$$class$java$lang$Boolean()), Boolean.TYPE);
        arguments[2] = ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (Object[])arguments[0])))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Writer)array[0]);
                break;
            }
            case 1: {
                this((Writer)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            case 2: {
                this((Writer)array4[0], DefaultTypeTransformation.booleanUnbox(array5[1]), DefaultTypeTransformation.booleanUnbox(array6[2]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    public AstNodeToScriptVisitor(final Writer writer) {
        $getCallSiteArray();
        Object[] array6;
        Object[] array5;
        Object[] array4;
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = (array4 = (array5 = (array6 = new Object[3])))));
        arguments[0] = ScriptBytecodeAdapter.createPojoWrapper(writer, $get$$class$java$io$Writer());
        arguments[1] = ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE);
        arguments[2] = ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (array4 = (array5 = (array6 = (Object[])arguments[0])))));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                this((Writer)array[0]);
                break;
            }
            case 1: {
                this((Writer)array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            case 2: {
                this((Writer)array4[0], DefaultTypeTransformation.booleanUnbox(array5[1]), DefaultTypeTransformation.booleanUnbox(array6[2]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.showScriptFreeForm)) && !DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.scriptHasBeenVisited))) ? Boolean.TRUE : Boolean.FALSE)) {
            this.scriptHasBeenVisited = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
            $getCallSiteArray[1].callSafe($getCallSiteArray[2].callSafe($getCallSiteArray[3].callSafe(source)), this);
        }
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.showScriptClass)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(classNode))) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[5].callCurrent(this, classNode);
        }
    }
    
    public void print(final Object parameter) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object output = $getCallSiteArray[6].call(parameter);
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.readyToIndent))) {
            $getCallSiteArray[7].call(this._out, this._indent);
            this.readyToIndent = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
            while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(output, " "))) {
                output = $getCallSiteArray[9].call(output, ScriptBytecodeAdapter.createRange(AstNodeToScriptVisitor.$const$0, AstNodeToScriptVisitor.$const$1, true));
            }
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[10].call($getCallSiteArray[11].call(this._out), " ")) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call(output, " "))) {
            output = $getCallSiteArray[13].call(output, ScriptBytecodeAdapter.createRange(AstNodeToScriptVisitor.$const$0, AstNodeToScriptVisitor.$const$1, true));
            goto Label_0189;
        }
        $getCallSiteArray[14].call(this._out, output);
    }
    
    public Object println(final Object parameter) {
        throw (Throwable)$getCallSiteArray()[15].callConstructor($get$$class$java$lang$UnsupportedOperationException(), "Wrong API");
    }
    
    public Object indented(final Closure block) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String startingIndent = this._indent;
        this._indent = (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[16].call(this._indent, "    "), $get$$class$java$lang$String()), $get$$class$java$lang$String());
        $getCallSiteArray[17].call(block);
        final String object = startingIndent;
        this._indent = (String)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$String());
        return object;
    }
    
    public Object printLineBreak() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[18].call($getCallSiteArray[19].call(this._out), "\n"))) {
            $getCallSiteArray[20].call(this._out, "\n");
        }
        final Boolean true = Boolean.TRUE;
        this.readyToIndent = DefaultTypeTransformation.booleanUnbox(true);
        return true;
    }
    
    public Object printDoubleBreak() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[21].call($getCallSiteArray[22].call(this._out), "\n\n"))) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[23].call($getCallSiteArray[24].call(this._out), "\n"))) {
                $getCallSiteArray[25].call(this._out, "\n");
            }
            else {
                $getCallSiteArray[26].call(this._out, "\n");
                $getCallSiteArray[27].call(this._out, "\n");
            }
        }
        final Boolean true = Boolean.TRUE;
        this.readyToIndent = DefaultTypeTransformation.booleanUnbox(true);
        return true;
    }
    
    public Object AstNodeToScriptAdapter(final Writer out) {
        $getCallSiteArray();
        this._out = (Writer)ScriptBytecodeAdapter.castToType(out, $get$$class$java$io$Writer());
        return out;
    }
    
    public void visitClass(final ClassNode node) {
        final ClassNode node2 = (ClassNode)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[28].call(this.classNameStack, $getCallSiteArray[29].callGetProperty(((Reference<Object>)node2).get()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[30].callGetPropertySafe(((Reference<Object>)node2).get()))) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[31].call($getCallSiteArray[32].callGetProperty($getCallSiteArray[33].callGetProperty(((Reference<Object>)node2).get())), "."))) {
                $getCallSiteArray[34].callCurrent(this, $getCallSiteArray[35].call($getCallSiteArray[36].callGetProperty($getCallSiteArray[37].callGetProperty(((Reference<Object>)node2).get())), ScriptBytecodeAdapter.createRange(AstNodeToScriptVisitor.$const$2, AstNodeToScriptVisitor.$const$3, true)));
            }
            else {
                $getCallSiteArray[38].callCurrent(this, $getCallSiteArray[39].callGetProperty($getCallSiteArray[40].callGetProperty(((Reference<Object>)node2).get())));
            }
            $getCallSiteArray[41].callCurrent(this);
        }
        $getCallSiteArray[42].callCurrent(this, $getCallSiteArray[43].callGetProperty(((Reference<Object>)node2).get()));
        $getCallSiteArray[44].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[45].callGetProperty(((Reference<Object>)node2).get()) }, new String[] { "class ", "" }));
        $getCallSiteArray[46].callCurrent(this, $getCallSiteArray[47].callGetPropertySafe(((Reference<Object>)node2).get()));
        final Boolean first = (Boolean)new Reference(Boolean.TRUE);
        $getCallSiteArray[48].callSafe($getCallSiteArray[49].callGetProperty(((Reference<Object>)node2).get()), new AstNodeToScriptVisitor$_visitClass_closure1(this, this, (Reference<Object>)first));
        $getCallSiteArray[50].callCurrent(this, " extends ");
        $getCallSiteArray[51].callCurrent(this, $getCallSiteArray[52].callGetProperty(((Reference<Object>)node2).get()));
        $getCallSiteArray[53].callCurrent(this, " { ");
        $getCallSiteArray[54].callCurrent(this);
        $getCallSiteArray[55].callCurrent(this, new AstNodeToScriptVisitor$_visitClass_closure2(this, this, (Reference<Object>)node2));
        $getCallSiteArray[56].callCurrent(this, "}");
        $getCallSiteArray[57].callCurrent(this);
        $getCallSiteArray[58].call(this.classNameStack);
    }
    
    private void visitGenerics(final GenericsType... generics) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(generics)) {
            $getCallSiteArray[59].callCurrent(this, "<");
            final Boolean first = (Boolean)new Reference(Boolean.TRUE);
            $getCallSiteArray[60].call(generics, new AstNodeToScriptVisitor$_visitGenerics_closure3(this, this, (Reference<Object>)first));
            $getCallSiteArray[61].callCurrent(this, ">");
        }
    }
    
    public void visitConstructor(final ConstructorNode node) {
        $getCallSiteArray()[62].callCurrent(this, node);
    }
    
    private String visitParameters(final Object parameters) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Boolean first = (Boolean)new Reference(Boolean.TRUE);
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[63].call(parameters, new AstNodeToScriptVisitor$_visitParameters_closure4(this, this, (Reference<Object>)first)), $get$$class$java$lang$String());
    }
    
    public void visitMethod(final MethodNode node) {
        final MethodNode node2 = (MethodNode)new Reference(node);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[64].callCurrent(this, $getCallSiteArray[65].callGetProperty(((Reference<Object>)node2).get()));
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[66].callGetProperty(((Reference<Object>)node2).get()), "<init>")) {
            $getCallSiteArray[67].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[68].call(this.classNameStack) }, new String[] { "", "(" }));
            $getCallSiteArray[69].callCurrent(this, $getCallSiteArray[70].callGetProperty(((Reference<Object>)node2).get()));
            $getCallSiteArray[71].callCurrent(this, ") {");
            $getCallSiteArray[72].callCurrent(this);
        }
        else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[73].callGetProperty(((Reference<Object>)node2).get()), "<clinit>")) {
            $getCallSiteArray[74].callCurrent(this, "static { ");
            $getCallSiteArray[75].callCurrent(this);
        }
        else {
            $getCallSiteArray[76].callCurrent(this, $getCallSiteArray[77].callGetProperty(((Reference<Object>)node2).get()));
            $getCallSiteArray[78].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[79].callGetProperty(((Reference<Object>)node2).get()) }, new String[] { " ", "(" }));
            $getCallSiteArray[80].callCurrent(this, $getCallSiteArray[81].callGetProperty(((Reference<Object>)node2).get()));
            $getCallSiteArray[82].callCurrent(this, ")");
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[83].callGetProperty(((Reference<Object>)node2).get()))) {
                final Boolean first = (Boolean)new Reference(Boolean.TRUE);
                $getCallSiteArray[84].callCurrent(this, " throws ");
                $getCallSiteArray[85].call($getCallSiteArray[86].callGetProperty(((Reference<Object>)node2).get()), new AstNodeToScriptVisitor$_visitMethod_closure5(this, this, (Reference<Object>)first));
            }
            $getCallSiteArray[87].callCurrent(this, " {");
            $getCallSiteArray[88].callCurrent(this);
        }
        $getCallSiteArray[89].callCurrent(this, new AstNodeToScriptVisitor$_visitMethod_closure6(this, this, (Reference<Object>)node2));
        $getCallSiteArray[90].callCurrent(this);
        $getCallSiteArray[91].callCurrent(this, "}");
        $getCallSiteArray[92].callCurrent(this);
    }
    
    private Object visitModifiers(final int modifiers) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[93].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[94].callCurrent(this, "abstract ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[95].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[96].callCurrent(this, "final ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[97].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[98].callCurrent(this, "interface ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[99].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[100].callCurrent(this, "native ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[101].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[102].callCurrent(this, "private ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[103].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[104].callCurrent(this, "protected ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[105].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[106].callCurrent(this, "public ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[107].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[108].callCurrent(this, "static ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[109].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[110].callCurrent(this, "synchronized ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[111].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            $getCallSiteArray[112].callCurrent(this, "transient ");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[113].call($get$$class$java$lang$reflect$Modifier(), DefaultTypeTransformation.box(modifiers)))) {
            return $getCallSiteArray[114].callCurrent(this, "volatile ");
        }
        return null;
    }
    
    public void visitField(final FieldNode node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[115].callSafe($getCallSiteArray[116].callGetPropertySafe(node), new AstNodeToScriptVisitor$_visitField_closure7(this, this));
        $getCallSiteArray[117].callCurrent(this, $getCallSiteArray[118].callGetProperty(node));
        $getCallSiteArray[119].callCurrent(this, $getCallSiteArray[120].callGetProperty(node));
        $getCallSiteArray[121].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[122].callGetProperty(node) }, new String[] { " ", " " }));
        $getCallSiteArray[123].callCurrent(this);
    }
    
    public void visitAnnotationNode(final AnnotationNode node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[124].callCurrent(this, $getCallSiteArray[125].call("@", $getCallSiteArray[126].callGetPropertySafe($getCallSiteArray[127].callGetPropertySafe(node))));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[128].callGetPropertySafe(node))) {
            $getCallSiteArray[129].callCurrent(this, "(");
            final Boolean first = (Boolean)new Reference(Boolean.TRUE);
            $getCallSiteArray[130].call($getCallSiteArray[131].callGetProperty(node), new AstNodeToScriptVisitor$_visitAnnotationNode_closure8(this, this, (Reference<Object>)first));
            $getCallSiteArray[132].callCurrent(this, ")");
        }
    }
    
    public void visitProperty(final PropertyNode node) {
        $getCallSiteArray();
    }
    
    public void visitBlockStatement(final BlockStatement block) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[133].callSafe($getCallSiteArray[134].callGetPropertySafe(block), new AstNodeToScriptVisitor$_visitBlockStatement_closure9(this, this));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[135].call($getCallSiteArray[136].call(this._out), "\n"))) {
            $getCallSiteArray[137].callCurrent(this);
        }
    }
    
    public void visitForLoop(final ForStatement statement) {
        final ForStatement statement2 = (ForStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[138].callCurrent(this, "for (");
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[139].callGetPropertySafe(((Reference<Object>)statement2).get()), $getCallSiteArray[140].callGetProperty($get$$class$org$codehaus$groovy$ast$stmt$ForStatement()))) {
            $getCallSiteArray[141].callCurrent(this, ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[142].callGetProperty(((Reference<Object>)statement2).get()) }));
            $getCallSiteArray[143].callCurrent(this, " : ");
        }
        if ($getCallSiteArray[144].callGetPropertySafe(((Reference<Object>)statement2).get()) instanceof ListExpression) {
            $getCallSiteArray[145].callSafe($getCallSiteArray[146].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        }
        else {
            $getCallSiteArray[147].callSafe($getCallSiteArray[148].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        }
        $getCallSiteArray[149].callCurrent(this, ") {");
        $getCallSiteArray[150].callCurrent(this);
        $getCallSiteArray[151].callCurrent(this, new AstNodeToScriptVisitor$_visitForLoop_closure10(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[152].callCurrent(this, "}");
        $getCallSiteArray[153].callCurrent(this);
    }
    
    public void visitIfElse(final IfStatement ifElse) {
        final IfStatement ifElse2 = (IfStatement)new Reference(ifElse);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[154].callCurrent(this, "if (");
        $getCallSiteArray[155].callSafe($getCallSiteArray[156].callGetPropertySafe(((Reference<Object>)ifElse2).get()), this);
        $getCallSiteArray[157].callCurrent(this, ") {");
        $getCallSiteArray[158].callCurrent(this);
        $getCallSiteArray[159].callCurrent(this, new AstNodeToScriptVisitor$_visitIfElse_closure11(this, this, (Reference<Object>)ifElse2));
        $getCallSiteArray[160].callCurrent(this);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[161].callGetPropertySafe(((Reference<Object>)ifElse2).get())) && !($getCallSiteArray[162].callGetProperty(((Reference<Object>)ifElse2).get()) instanceof EmptyStatement)) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[163].callCurrent(this, "} else {");
            $getCallSiteArray[164].callCurrent(this);
            $getCallSiteArray[165].callCurrent(this, new AstNodeToScriptVisitor$_visitIfElse_closure12(this, this, (Reference<Object>)ifElse2));
            $getCallSiteArray[166].callCurrent(this);
        }
        $getCallSiteArray[167].callCurrent(this, "}");
        $getCallSiteArray[168].callCurrent(this);
    }
    
    public void visitExpressionStatement(final ExpressionStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[169].call($getCallSiteArray[170].callGetProperty(statement), this);
    }
    
    public void visitReturnStatement(final ReturnStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[171].callCurrent(this);
        $getCallSiteArray[172].callCurrent(this, "return ");
        $getCallSiteArray[173].call($getCallSiteArray[174].call(statement), this);
        $getCallSiteArray[175].callCurrent(this);
    }
    
    public void visitSwitch(final SwitchStatement statement) {
        final SwitchStatement statement2 = (SwitchStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[176].callCurrent(this, "switch (");
        $getCallSiteArray[177].callSafe($getCallSiteArray[178].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        $getCallSiteArray[179].callCurrent(this, ") {");
        $getCallSiteArray[180].callCurrent(this);
        $getCallSiteArray[181].callCurrent(this, new AstNodeToScriptVisitor$_visitSwitch_closure13(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[182].callCurrent(this, "}");
        $getCallSiteArray[183].callCurrent(this);
    }
    
    public void visitCaseStatement(final CaseStatement statement) {
        final CaseStatement statement2 = (CaseStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[184].callCurrent(this, "case ");
        $getCallSiteArray[185].callSafe($getCallSiteArray[186].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        $getCallSiteArray[187].callCurrent(this, ":");
        $getCallSiteArray[188].callCurrent(this);
        $getCallSiteArray[189].callCurrent(this, new AstNodeToScriptVisitor$_visitCaseStatement_closure14(this, this, (Reference<Object>)statement2));
    }
    
    public void visitBreakStatement(final BreakStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[190].callCurrent(this, "break");
        $getCallSiteArray[191].callCurrent(this);
    }
    
    public void visitContinueStatement(final ContinueStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[192].callCurrent(this, "continue");
        $getCallSiteArray[193].callCurrent(this);
    }
    
    public void visitMethodCallExpression(final MethodCallExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Expression objectExp = (Expression)ScriptBytecodeAdapter.castToType($getCallSiteArray[194].call(expression), $get$$class$org$codehaus$groovy$ast$expr$Expression());
        if (objectExp instanceof VariableExpression) {
            $getCallSiteArray[195].callCurrent(this, objectExp, Boolean.FALSE);
        }
        else {
            $getCallSiteArray[196].call(objectExp, this);
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[197].callGetProperty(expression))) {
            $getCallSiteArray[198].callCurrent(this, "*");
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[199].callGetProperty(expression))) {
            $getCallSiteArray[200].callCurrent(this, "?");
        }
        $getCallSiteArray[201].callCurrent(this, ".");
        final Expression method = (Expression)ScriptBytecodeAdapter.castToType($getCallSiteArray[202].call(expression), $get$$class$org$codehaus$groovy$ast$expr$Expression());
        if (method instanceof ConstantExpression) {
            $getCallSiteArray[203].callCurrent(this, method, Boolean.TRUE);
        }
        else {
            $getCallSiteArray[204].call(method, this);
        }
        $getCallSiteArray[205].call($getCallSiteArray[206].call(expression), this);
    }
    
    public void visitStaticMethodCallExpression(final StaticMethodCallExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[207].callCurrent(this, $getCallSiteArray[208].call($getCallSiteArray[209].call($getCallSiteArray[210].callGetPropertySafe($getCallSiteArray[211].callGetPropertySafe(expression)), "."), $getCallSiteArray[212].callGetPropertySafe(expression)));
        if ($getCallSiteArray[213].callGetPropertySafe(expression) instanceof VariableExpression) {
            $getCallSiteArray[214].callCurrent(this, "(");
            $getCallSiteArray[215].callSafe($getCallSiteArray[216].callGetPropertySafe(expression), this);
            $getCallSiteArray[217].callCurrent(this, ")");
        }
        else {
            $getCallSiteArray[218].callSafe($getCallSiteArray[219].callGetPropertySafe(expression), this);
        }
    }
    
    public void visitConstructorCallExpression(final ConstructorCallExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[220].callSafe(expression))) {
            $getCallSiteArray[221].callCurrent(this, "super");
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[222].callSafe(expression))) {
            $getCallSiteArray[223].callCurrent(this, "this ");
        }
        else {
            $getCallSiteArray[224].callCurrent(this, "new ");
            $getCallSiteArray[225].callCurrent(this, $getCallSiteArray[226].callGetPropertySafe(expression));
        }
        $getCallSiteArray[227].callSafe($getCallSiteArray[228].callGetPropertySafe(expression), this);
    }
    
    public void visitBinaryExpression(final BinaryExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[229].callSafe($getCallSiteArray[230].callGetPropertySafe(expression), this);
        $getCallSiteArray[231].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[232].callGetProperty($getCallSiteArray[233].callGetProperty(expression)) }, new String[] { " ", " " }));
        $getCallSiteArray[234].call($getCallSiteArray[235].callGetProperty(expression), this);
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[236].callGetPropertySafe($getCallSiteArray[237].callGetPropertySafe(expression)), "[")) {
            $getCallSiteArray[238].callCurrent(this, "]");
        }
    }
    
    public void visitPostfixExpression(final PostfixExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[239].callCurrent(this, "(");
        $getCallSiteArray[240].callSafe($getCallSiteArray[241].callGetPropertySafe(expression), this);
        $getCallSiteArray[242].callCurrent(this, ")");
        $getCallSiteArray[243].callCurrent(this, $getCallSiteArray[244].callGetPropertySafe($getCallSiteArray[245].callGetPropertySafe(expression)));
    }
    
    public void visitPrefixExpression(final PrefixExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[246].callCurrent(this, $getCallSiteArray[247].callGetPropertySafe($getCallSiteArray[248].callGetPropertySafe(expression)));
        $getCallSiteArray[249].callCurrent(this, "(");
        $getCallSiteArray[250].callSafe($getCallSiteArray[251].callGetPropertySafe(expression), this);
        $getCallSiteArray[252].callCurrent(this, ")");
    }
    
    public void visitClosureExpression(final ClosureExpression expression) {
        final ClosureExpression expression2 = (ClosureExpression)new Reference(expression);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[253].callCurrent(this, "{ ");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[254].callGetPropertySafe(((Reference<Object>)expression2).get()))) {
            $getCallSiteArray[255].callCurrent(this, $getCallSiteArray[256].callGetPropertySafe(((Reference<Object>)expression2).get()));
            $getCallSiteArray[257].callCurrent(this, " ->");
        }
        $getCallSiteArray[258].callCurrent(this);
        $getCallSiteArray[259].callCurrent(this, new AstNodeToScriptVisitor$_visitClosureExpression_closure15(this, this, (Reference<Object>)expression2));
        $getCallSiteArray[260].callCurrent(this, "}");
    }
    
    public void visitTupleExpression(final TupleExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[261].callCurrent(this, "(");
        $getCallSiteArray[262].callCurrent(this, $getCallSiteArray[263].callGetPropertySafe(expression));
        $getCallSiteArray[264].callCurrent(this, ")");
    }
    
    public void visitRangeExpression(final RangeExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[265].callCurrent(this, "(");
        $getCallSiteArray[266].callSafe($getCallSiteArray[267].callGetPropertySafe(expression), this);
        $getCallSiteArray[268].callCurrent(this, "..");
        $getCallSiteArray[269].callSafe($getCallSiteArray[270].callGetPropertySafe(expression), this);
        $getCallSiteArray[271].callCurrent(this, ")");
    }
    
    public void visitPropertyExpression(final PropertyExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[272].callSafe($getCallSiteArray[273].callGetPropertySafe(expression), this);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[274].callGetPropertySafe(expression))) {
            $getCallSiteArray[275].callCurrent(this, "*");
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[276].callSafe(expression))) {
            $getCallSiteArray[277].callCurrent(this, "?");
        }
        $getCallSiteArray[278].callCurrent(this, ".");
        if ($getCallSiteArray[279].callGetPropertySafe(expression) instanceof ConstantExpression) {
            $getCallSiteArray[280].callCurrent(this, $getCallSiteArray[281].callGetPropertySafe(expression), Boolean.TRUE);
        }
        else {
            $getCallSiteArray[282].callSafe($getCallSiteArray[283].callGetPropertySafe(expression), this);
        }
    }
    
    public void visitAttributeExpression(final AttributeExpression attributeExpression) {
        $getCallSiteArray()[284].callCurrent(this, attributeExpression);
    }
    
    public void visitFieldExpression(final FieldExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[285].callCurrent(this, $getCallSiteArray[286].callGetPropertySafe($getCallSiteArray[287].callGetPropertySafe(expression)));
    }
    
    public void visitConstantExpression(final ConstantExpression expression, final boolean unwrapQuotes) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(($getCallSiteArray[288].callGetProperty(expression) instanceof String && !DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(unwrapQuotes))) ? Boolean.TRUE : Boolean.FALSE)) {
            final Object escaped = $getCallSiteArray[289].call($getCallSiteArray[290].call(ScriptBytecodeAdapter.castToType($getCallSiteArray[291].callGetProperty(expression), $get$$class$java$lang$String()), "\n", "\\\\n"), "'", "\\\\'");
            $getCallSiteArray[292].callCurrent(this, new GStringImpl(new Object[] { escaped }, new String[] { "'", "'" }));
        }
        else {
            $getCallSiteArray[293].callCurrent(this, $getCallSiteArray[294].callGetProperty(expression));
        }
    }
    
    public void visitClassExpression(final ClassExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[295].callCurrent(this, $getCallSiteArray[296].callGetProperty(expression));
    }
    
    public void visitVariableExpression(final VariableExpression expression, final boolean spacePad) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(spacePad))) {
            $getCallSiteArray[297].callCurrent(this, $getCallSiteArray[298].call($getCallSiteArray[299].call(" ", $getCallSiteArray[300].callGetProperty(expression)), " "));
        }
        else {
            $getCallSiteArray[301].callCurrent(this, $getCallSiteArray[302].callGetProperty(expression));
        }
    }
    
    public void visitDeclarationExpression(final DeclarationExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if ($getCallSiteArray[303].callGetPropertySafe(expression) instanceof ArgumentListExpression) {
            $getCallSiteArray[304].callCurrent(this, "def ");
            $getCallSiteArray[305].callCurrent(this, $getCallSiteArray[306].callGetPropertySafe(expression), Boolean.TRUE);
            $getCallSiteArray[307].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[308].callGetProperty($getCallSiteArray[309].callGetProperty(expression)) }, new String[] { " ", " " }));
            $getCallSiteArray[310].call($getCallSiteArray[311].callGetProperty(expression), this);
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[312].callGetPropertySafe($getCallSiteArray[313].callGetPropertySafe(expression)), "[")) {
                $getCallSiteArray[314].callCurrent(this, "]");
            }
        }
        else {
            $getCallSiteArray[315].callCurrent(this, $getCallSiteArray[316].callGetPropertySafe($getCallSiteArray[317].callGetPropertySafe(expression)));
            $getCallSiteArray[318].callCurrent(this, expression);
        }
    }
    
    public void visitGStringExpression(final GStringExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[319].callCurrent(this, $getCallSiteArray[320].call($getCallSiteArray[321].call("\"", $getCallSiteArray[322].callGetProperty(expression)), "\""));
    }
    
    public void visitSpreadExpression(final SpreadExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[323].callCurrent(this, "*");
        $getCallSiteArray[324].callSafe($getCallSiteArray[325].callGetPropertySafe(expression), this);
    }
    
    public void visitNotExpression(final NotExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[326].callCurrent(this, "!(");
        $getCallSiteArray[327].callSafe($getCallSiteArray[328].callGetPropertySafe(expression), this);
        $getCallSiteArray[329].callCurrent(this, ")");
    }
    
    public void visitUnaryMinusExpression(final UnaryMinusExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[330].callCurrent(this, "-(");
        $getCallSiteArray[331].callSafe($getCallSiteArray[332].callGetPropertySafe(expression), this);
        $getCallSiteArray[333].callCurrent(this, ")");
    }
    
    public void visitUnaryPlusExpression(final UnaryPlusExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[334].callCurrent(this, "+(");
        $getCallSiteArray[335].callSafe($getCallSiteArray[336].callGetPropertySafe(expression), this);
        $getCallSiteArray[337].callCurrent(this, ")");
    }
    
    public void visitCastExpression(final CastExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[338].callCurrent(this, "((");
        $getCallSiteArray[339].callSafe($getCallSiteArray[340].callGetPropertySafe(expression), this);
        $getCallSiteArray[341].callCurrent(this, ") as ");
        $getCallSiteArray[342].callCurrent(this, $getCallSiteArray[343].callGetPropertySafe(expression));
        $getCallSiteArray[344].callCurrent(this, ")");
    }
    
    public void visitType(final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = $getCallSiteArray[345].callGetProperty(classNode);
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex(name, "^\\[+L")) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[346].call(name, ";"))) ? Boolean.TRUE : Boolean.FALSE)) {
            final Integer numDimensions = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[347].call(name, "L"), $get$$class$java$lang$Integer());
            $getCallSiteArray[348].callCurrent(this, $getCallSiteArray[349].call(new GStringImpl(new Object[] { $getCallSiteArray[350].call($getCallSiteArray[351].callGetProperty(classNode), ScriptBytecodeAdapter.createRange($getCallSiteArray[352].call(numDimensions, AstNodeToScriptVisitor.$const$0), AstNodeToScriptVisitor.$const$3, true)) }, new String[] { "", "" }), $getCallSiteArray[353].call("[]", numDimensions)));
        }
        else {
            $getCallSiteArray[354].callCurrent(this, name);
        }
        $getCallSiteArray[355].callCurrent(this, $getCallSiteArray[356].callGetPropertySafe(classNode));
    }
    
    public void visitArgumentlistExpression(final ArgumentListExpression expression, final boolean showTypes) {
        final Boolean showTypes2 = (Boolean)new Reference(DefaultTypeTransformation.box(showTypes));
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[357].callCurrent(this, "(");
        final Integer count = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[358].callSafe($getCallSiteArray[359].callGetPropertySafe(expression)), $get$$class$java$lang$Integer()));
        $getCallSiteArray[360].call($getCallSiteArray[361].callGetProperty(expression), new AstNodeToScriptVisitor$_visitArgumentlistExpression_closure16(this, this, (Reference<Object>)count, (Reference<Object>)showTypes2));
        $getCallSiteArray[362].callCurrent(this, ")");
    }
    
    public void visitBytecodeExpression(final BytecodeExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[363].callCurrent(this, "/*BytecodeExpression*/");
        $getCallSiteArray[364].callCurrent(this);
    }
    
    public void visitMapExpression(final MapExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[365].callCurrent(this, "[");
        $getCallSiteArray[366].callCurrent(this, $getCallSiteArray[367].callGetPropertySafe(expression));
        $getCallSiteArray[368].callCurrent(this, "]");
    }
    
    public void visitMapEntryExpression(final MapEntryExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if ($getCallSiteArray[369].callGetPropertySafe(expression) instanceof SpreadMapExpression) {
            $getCallSiteArray[370].callCurrent(this, "*");
        }
        else {
            $getCallSiteArray[371].callSafe($getCallSiteArray[372].callGetPropertySafe(expression), this);
        }
        $getCallSiteArray[373].callCurrent(this, ": ");
        $getCallSiteArray[374].callSafe($getCallSiteArray[375].callGetPropertySafe(expression), this);
    }
    
    public void visitListExpression(final ListExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[376].callCurrent(this, "[");
        $getCallSiteArray[377].callCurrent(this, $getCallSiteArray[378].callGetPropertySafe(expression));
        $getCallSiteArray[379].callCurrent(this, "]");
    }
    
    public void visitTryCatchFinally(final TryCatchStatement statement) {
        final TryCatchStatement statement2 = (TryCatchStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[380].callCurrent(this, "try {");
        $getCallSiteArray[381].callCurrent(this);
        $getCallSiteArray[382].callCurrent(this, new AstNodeToScriptVisitor$_visitTryCatchFinally_closure17(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[383].callCurrent(this);
        $getCallSiteArray[384].callCurrent(this, "} ");
        $getCallSiteArray[385].callCurrent(this);
        $getCallSiteArray[386].callSafe($getCallSiteArray[387].callGetPropertySafe(((Reference<Object>)statement2).get()), new AstNodeToScriptVisitor$_visitTryCatchFinally_closure18(this, this));
        $getCallSiteArray[388].callCurrent(this, "finally { ");
        $getCallSiteArray[389].callCurrent(this);
        $getCallSiteArray[390].callCurrent(this, new AstNodeToScriptVisitor$_visitTryCatchFinally_closure19(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[391].callCurrent(this, "} ");
        $getCallSiteArray[392].callCurrent(this);
    }
    
    public void visitThrowStatement(final ThrowStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[393].callCurrent(this, "throw ");
        $getCallSiteArray[394].callSafe($getCallSiteArray[395].callGetPropertySafe(statement), this);
        $getCallSiteArray[396].callCurrent(this);
    }
    
    public void visitSynchronizedStatement(final SynchronizedStatement statement) {
        final SynchronizedStatement statement2 = (SynchronizedStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[397].callCurrent(this, "synchronized (");
        $getCallSiteArray[398].callSafe($getCallSiteArray[399].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        $getCallSiteArray[400].callCurrent(this, ") {");
        $getCallSiteArray[401].callCurrent(this);
        $getCallSiteArray[402].callCurrent(this, new AstNodeToScriptVisitor$_visitSynchronizedStatement_closure20(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[403].callCurrent(this, "}");
    }
    
    public void visitTernaryExpression(final TernaryExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[404].callSafe($getCallSiteArray[405].callGetPropertySafe(expression), this);
        $getCallSiteArray[406].callCurrent(this, " ? ");
        $getCallSiteArray[407].callSafe($getCallSiteArray[408].callGetPropertySafe(expression), this);
        $getCallSiteArray[409].callCurrent(this, " : ");
        $getCallSiteArray[410].callSafe($getCallSiteArray[411].callGetPropertySafe(expression), this);
    }
    
    public void visitShortTernaryExpression(final ElvisOperatorExpression expression) {
        $getCallSiteArray()[412].callCurrent(this, expression);
    }
    
    public void visitBooleanExpression(final BooleanExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[413].callSafe($getCallSiteArray[414].callGetPropertySafe(expression), this);
    }
    
    public void visitWhileLoop(final WhileStatement statement) {
        final WhileStatement statement2 = (WhileStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[415].callCurrent(this, "while (");
        $getCallSiteArray[416].callSafe($getCallSiteArray[417].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        $getCallSiteArray[418].callCurrent(this, ") {");
        $getCallSiteArray[419].callCurrent(this);
        $getCallSiteArray[420].callCurrent(this, new AstNodeToScriptVisitor$_visitWhileLoop_closure21(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[421].callCurrent(this);
        $getCallSiteArray[422].callCurrent(this, "}");
        $getCallSiteArray[423].callCurrent(this);
    }
    
    public void visitDoWhileLoop(final DoWhileStatement statement) {
        final DoWhileStatement statement2 = (DoWhileStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[424].callCurrent(this, "do {");
        $getCallSiteArray[425].callCurrent(this);
        $getCallSiteArray[426].callCurrent(this, new AstNodeToScriptVisitor$_visitDoWhileLoop_closure22(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[427].callCurrent(this, "} while (");
        $getCallSiteArray[428].callSafe($getCallSiteArray[429].callGetPropertySafe(((Reference<Object>)statement2).get()), this);
        $getCallSiteArray[430].callCurrent(this, ")");
        $getCallSiteArray[431].callCurrent(this);
    }
    
    public void visitCatchStatement(final CatchStatement statement) {
        final CatchStatement statement2 = (CatchStatement)new Reference(statement);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[432].callCurrent(this, "catch (");
        $getCallSiteArray[433].callCurrent(this, ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[434].callGetProperty(((Reference<Object>)statement2).get()) }));
        $getCallSiteArray[435].callCurrent(this, ") {");
        $getCallSiteArray[436].callCurrent(this);
        $getCallSiteArray[437].callCurrent(this, new AstNodeToScriptVisitor$_visitCatchStatement_closure23(this, this, (Reference<Object>)statement2));
        $getCallSiteArray[438].callCurrent(this, "} ");
        $getCallSiteArray[439].callCurrent(this);
    }
    
    public void visitBitwiseNegationExpression(final BitwiseNegationExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[440].callCurrent(this, "~(");
        $getCallSiteArray[441].callSafe($getCallSiteArray[442].callGetPropertySafe(expression), this);
        $getCallSiteArray[443].callCurrent(this, ") ");
    }
    
    public void visitAssertStatement(final AssertStatement statement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[444].callCurrent(this, "assert ");
        $getCallSiteArray[445].callSafe($getCallSiteArray[446].callGetPropertySafe(statement), this);
        $getCallSiteArray[447].callCurrent(this, " : ");
        $getCallSiteArray[448].callSafe($getCallSiteArray[449].callGetPropertySafe(statement), this);
    }
    
    public void visitClosureListExpression(final ClosureListExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Boolean first = (Boolean)new Reference(Boolean.TRUE);
        $getCallSiteArray[450].callSafe($getCallSiteArray[451].callGetPropertySafe(expression), new AstNodeToScriptVisitor$_visitClosureListExpression_closure24(this, this, (Reference<Object>)first));
    }
    
    public void visitMethodPointerExpression(final MethodPointerExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[452].callSafe($getCallSiteArray[453].callGetPropertySafe(expression), this);
        $getCallSiteArray[454].callCurrent(this, ".&");
        $getCallSiteArray[455].callSafe($getCallSiteArray[456].callGetPropertySafe(expression), this);
    }
    
    public void visitArrayExpression(final ArrayExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[457].callCurrent(this, "new ");
        $getCallSiteArray[458].callCurrent(this, $getCallSiteArray[459].callGetPropertySafe(expression));
        $getCallSiteArray[460].callCurrent(this, "[");
        $getCallSiteArray[461].callCurrent(this, $getCallSiteArray[462].callGetPropertySafe(expression));
        $getCallSiteArray[463].callCurrent(this, "]");
    }
    
    private void visitExpressionsAndCommaSeparate(final List<? super Expression> expressions) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Boolean first = (Boolean)new Reference(Boolean.TRUE);
        $getCallSiteArray[464].callSafe(expressions, new AstNodeToScriptVisitor$_visitExpressionsAndCommaSeparate_closure25(this, this, (Reference<Object>)first));
    }
    
    public void visitSpreadMapExpression(final SpreadMapExpression expression) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[465].callCurrent(this, "*:");
        $getCallSiteArray[466].callSafe($getCallSiteArray[467].callGetPropertySafe(expression), this);
    }
    
    @Deprecated
    public void visitRegexExpression(final RegexExpression node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[468].callCurrent(this, $getCallSiteArray[469].call($getCallSiteArray[470].call("/", $getCallSiteArray[471].callGetProperty(node)), "/"));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstNodeToScriptVisitor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstNodeToScriptVisitor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public void visitConstantExpression(final ConstantExpression expression) {
        $getCallSiteArray()[472].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(expression, $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.FALSE, Boolean.TYPE));
    }
    
    public void visitVariableExpression(final VariableExpression expression) {
        $getCallSiteArray()[473].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(expression, $get$$class$org$codehaus$groovy$ast$expr$VariableExpression()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE));
    }
    
    public void visitArgumentlistExpression(final ArgumentListExpression expression) {
        $getCallSiteArray()[474].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(expression, $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.FALSE, Boolean.TYPE));
    }
    
    static {
        AstNodeToScriptVisitor.__timeStamp__239_neverHappen1292524204115 = 0L;
        AstNodeToScriptVisitor.__timeStamp = 1292524204115L;
        $const$3 = -2;
        $const$2 = 0;
        $const$1 = -1;
        $const$0 = 1;
    }
    
    public Stack<String> getClassNameStack() {
        return this.classNameStack;
    }
    
    public void setClassNameStack(final Stack<String> classNameStack) {
        this.classNameStack = classNameStack;
    }
    
    public String get_indent() {
        return this._indent;
    }
    
    public void set_indent(final String indent) {
        this._indent = indent;
    }
    
    public boolean getReadyToIndent() {
        return this.readyToIndent;
    }
    
    public boolean isReadyToIndent() {
        return this.readyToIndent;
    }
    
    public void setReadyToIndent(final boolean readyToIndent) {
        this.readyToIndent = readyToIndent;
    }
    
    public boolean getShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public boolean isShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public void setShowScriptFreeForm(final boolean showScriptFreeForm) {
        this.showScriptFreeForm = showScriptFreeForm;
    }
    
    public boolean getShowScriptClass() {
        return this.showScriptClass;
    }
    
    public boolean isShowScriptClass() {
        return this.showScriptClass;
    }
    
    public void setShowScriptClass(final boolean showScriptClass) {
        this.showScriptClass = showScriptClass;
    }
    
    public boolean getScriptHasBeenVisited() {
        return this.scriptHasBeenVisited;
    }
    
    public boolean isScriptHasBeenVisited() {
        return this.scriptHasBeenVisited;
    }
    
    public void setScriptHasBeenVisited(final boolean scriptHasBeenVisited) {
        this.scriptHasBeenVisited = scriptHasBeenVisited;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[475];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptVisitor.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptVisitor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptVisitor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Stack() {
        Class $class$java$util$Stack;
        if (($class$java$util$Stack = AstNodeToScriptVisitor.$class$java$util$Stack) == null) {
            $class$java$util$Stack = (AstNodeToScriptVisitor.$class$java$util$Stack = class$("java.util.Stack"));
        }
        return $class$java$util$Stack;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$UnsupportedOperationException() {
        Class $class$java$lang$UnsupportedOperationException;
        if (($class$java$lang$UnsupportedOperationException = AstNodeToScriptVisitor.$class$java$lang$UnsupportedOperationException) == null) {
            $class$java$lang$UnsupportedOperationException = (AstNodeToScriptVisitor.$class$java$lang$UnsupportedOperationException = class$("java.lang.UnsupportedOperationException"));
        }
        return $class$java$lang$UnsupportedOperationException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$VariableExpression() {
        Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
        if (($class$org$codehaus$groovy$ast$expr$VariableExpression = AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$VariableExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$VariableExpression = (AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$VariableExpression = class$("org.codehaus.groovy.ast.expr.VariableExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$VariableExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$reflect$Modifier() {
        Class $class$java$lang$reflect$Modifier;
        if (($class$java$lang$reflect$Modifier = AstNodeToScriptVisitor.$class$java$lang$reflect$Modifier) == null) {
            $class$java$lang$reflect$Modifier = (AstNodeToScriptVisitor.$class$java$lang$reflect$Modifier = class$("java.lang.reflect.Modifier"));
        }
        return $class$java$lang$reflect$Modifier;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstNodeToScriptVisitor.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstNodeToScriptVisitor.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArgumentListExpression = AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArgumentListExpression = (AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression = class$("org.codehaus.groovy.ast.expr.ArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstantExpression = AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$ConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstantExpression = (AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$ConstantExpression = class$("org.codehaus.groovy.ast.expr.ConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ForStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ForStatement = AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$stmt$ForStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ForStatement = (AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$stmt$ForStatement = class$("org.codehaus.groovy.ast.stmt.ForStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ForStatement;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstNodeToScriptVisitor.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstNodeToScriptVisitor.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstNodeToScriptVisitor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstNodeToScriptVisitor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$Writer() {
        Class $class$java$io$Writer;
        if (($class$java$io$Writer = AstNodeToScriptVisitor.$class$java$io$Writer) == null) {
            $class$java$io$Writer = (AstNodeToScriptVisitor.$class$java$io$Writer = class$("java.io.Writer"));
        }
        return $class$java$io$Writer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor() {
        Class $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
        if (($class$groovy$inspect$swingui$AstNodeToScriptVisitor = AstNodeToScriptVisitor.$class$groovy$inspect$swingui$AstNodeToScriptVisitor) == null) {
            $class$groovy$inspect$swingui$AstNodeToScriptVisitor = (AstNodeToScriptVisitor.$class$groovy$inspect$swingui$AstNodeToScriptVisitor = class$("groovy.inspect.swingui.AstNodeToScriptVisitor"));
        }
        return $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptVisitor.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptVisitor.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$Expression() {
        Class $class$org$codehaus$groovy$ast$expr$Expression;
        if (($class$org$codehaus$groovy$ast$expr$Expression = AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$Expression) == null) {
            $class$org$codehaus$groovy$ast$expr$Expression = (AstNodeToScriptVisitor.$class$org$codehaus$groovy$ast$expr$Expression = class$("org.codehaus.groovy.ast.expr.Expression"));
        }
        return $class$org$codehaus$groovy$ast$expr$Expression;
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
