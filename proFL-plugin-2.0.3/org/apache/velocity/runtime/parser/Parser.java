// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

import java.util.Enumeration;
import org.apache.velocity.runtime.parser.node.ASTNotNode;
import org.apache.velocity.runtime.parser.node.ASTModNode;
import org.apache.velocity.runtime.parser.node.ASTDivNode;
import org.apache.velocity.runtime.parser.node.ASTMulNode;
import org.apache.velocity.runtime.parser.node.ASTSubtractNode;
import org.apache.velocity.runtime.parser.node.ASTAddNode;
import org.apache.velocity.runtime.parser.node.ASTGENode;
import org.apache.velocity.runtime.parser.node.ASTLENode;
import org.apache.velocity.runtime.parser.node.ASTGTNode;
import org.apache.velocity.runtime.parser.node.ASTLTNode;
import org.apache.velocity.runtime.parser.node.ASTNENode;
import org.apache.velocity.runtime.parser.node.ASTEQNode;
import org.apache.velocity.runtime.parser.node.ASTAndNode;
import org.apache.velocity.runtime.parser.node.ASTOrNode;
import org.apache.velocity.runtime.parser.node.ASTAssignment;
import org.apache.velocity.runtime.parser.node.ASTExpression;
import org.apache.velocity.runtime.parser.node.ASTStop;
import org.apache.velocity.runtime.parser.node.ASTSetDirective;
import org.apache.velocity.runtime.parser.node.ASTElseIfStatement;
import org.apache.velocity.runtime.parser.node.ASTElseStatement;
import org.apache.velocity.runtime.parser.node.ASTIfStatement;
import org.apache.velocity.runtime.parser.node.ASTText;
import org.apache.velocity.runtime.parser.node.ASTFalse;
import org.apache.velocity.runtime.parser.node.ASTTrue;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ASTMethod;
import org.apache.velocity.runtime.parser.node.ASTIntegerRange;
import org.apache.velocity.runtime.parser.node.ASTObjectArray;
import org.apache.velocity.runtime.parser.node.ASTMap;
import org.apache.velocity.runtime.directive.Macro;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTWord;
import org.apache.velocity.runtime.parser.node.ASTIdentifier;
import org.apache.velocity.runtime.parser.node.ASTStringLiteral;
import org.apache.velocity.runtime.parser.node.ASTIntegerLiteral;
import org.apache.velocity.runtime.parser.node.ASTFloatingPointLiteral;
import org.apache.velocity.runtime.parser.node.ASTComment;
import org.apache.velocity.runtime.parser.node.ASTEscape;
import org.apache.velocity.runtime.parser.node.ASTEscapedDirective;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.ASTprocess;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.directive.MacroParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import java.io.Reader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.Hashtable;

public class Parser implements ParserTreeConstants, ParserConstants
{
    protected JJTParserState jjtree;
    private Hashtable directives;
    String currentTemplateName;
    VelocityCharStream velcharstream;
    private RuntimeServices rsvc;
    public ParserTokenManager token_source;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    public boolean lookingAhead;
    private boolean jj_semLA;
    private int jj_gen;
    private final int[] jj_la1;
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private static int[] jj_la1_2;
    private final JJCalls[] jj_2_rtns;
    private boolean jj_rescan;
    private int jj_gc;
    private final LookaheadSuccess jj_ls;
    private Vector jj_expentries;
    private int[] jj_expentry;
    private int jj_kind;
    private int[] jj_lasttokens;
    private int jj_endpos;
    
    public Parser(final RuntimeServices rs) {
        this(new VelocityCharStream(new ByteArrayInputStream("\n".getBytes()), 1, 1));
        this.velcharstream = new VelocityCharStream(new ByteArrayInputStream("\n".getBytes()), 1, 1);
        this.rsvc = rs;
    }
    
    public SimpleNode parse(final Reader reader, final String templateName) throws ParseException {
        SimpleNode sn = null;
        this.currentTemplateName = templateName;
        try {
            this.token_source.clearStateVars();
            this.velcharstream.ReInit(reader, 1, 1);
            this.ReInit(this.velcharstream);
            sn = this.process();
        }
        catch (MacroParseException mee) {
            this.rsvc.getLog().error("Parser Error: #macro() : " + templateName, mee);
            throw mee;
        }
        catch (ParseException pe) {
            this.rsvc.getLog().error("Parser Exception: " + templateName, pe);
            throw new TemplateParseException(pe.currentToken, pe.expectedTokenSequences, pe.tokenImage, this.currentTemplateName);
        }
        catch (TokenMgrError tme) {
            throw new ParseException("Lexical error: " + tme.toString());
        }
        catch (Exception e) {
            this.rsvc.getLog().error("Parser Error: " + templateName, e);
        }
        this.currentTemplateName = "";
        return sn;
    }
    
    public void setDirectives(final Hashtable directives) {
        this.directives = directives;
    }
    
    public Directive getDirective(final String directive) {
        return this.directives.get(directive);
    }
    
    public boolean isDirective(final String directive) {
        return this.directives.containsKey(directive);
    }
    
    private String escapedDirective(final String strImage) {
        final int iLast = strImage.lastIndexOf("\\");
        final String strDirective = strImage.substring(iLast + 1);
        boolean bRecognizedDirective = false;
        if (this.isDirective(strDirective.substring(1))) {
            bRecognizedDirective = true;
        }
        else if (this.rsvc.isVelocimacro(strDirective.substring(1), this.currentTemplateName)) {
            bRecognizedDirective = true;
        }
        else if (strDirective.substring(1).equals("if") || strDirective.substring(1).equals("end") || strDirective.substring(1).equals("set") || strDirective.substring(1).equals("else") || strDirective.substring(1).equals("elseif") || strDirective.substring(1).equals("stop")) {
            bRecognizedDirective = true;
        }
        if (bRecognizedDirective) {
            return strImage.substring(0, iLast / 2) + strDirective;
        }
        return strImage;
    }
    
    public final SimpleNode process() throws ParseException {
        final ASTprocess jjtn000 = new ASTprocess(this, 0);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            while (true) {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 8:
                    case 9:
                    case 11:
                    case 12:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 23:
                    case 24:
                    case 27:
                    case 47:
                    case 50:
                    case 52:
                    case 53:
                    case 57:
                    case 58:
                    case 62:
                    case 63:
                    case 64:
                    case 65: {
                        this.Statement();
                        continue;
                    }
                    default: {
                        this.jj_la1[0] = this.jj_gen;
                        this.jj_consume_token(0);
                        this.jjtree.closeNodeScope(jjtn000, true);
                        jjtc000 = false;
                        return jjtn000;
                    }
                }
            }
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Statement() throws ParseException {
        Label_0415: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 47: {
                    this.IfStatement();
                    break;
                }
                case 50: {
                    this.StopStatement();
                    break;
                }
                default: {
                    this.jj_la1[1] = this.jj_gen;
                    if (this.jj_2_1(2)) {
                        this.Reference();
                        break;
                    }
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 18:
                        case 23:
                        case 24: {
                            this.Comment();
                            break Label_0415;
                        }
                        case 12: {
                            this.SetDirective();
                            break Label_0415;
                        }
                        case 11: {
                            this.EscapedDirective();
                            break Label_0415;
                        }
                        case 19: {
                            this.Escape();
                            break Label_0415;
                        }
                        case 57:
                        case 58: {
                            this.Directive();
                            break Label_0415;
                        }
                        case 8:
                        case 9:
                        case 20:
                        case 21:
                        case 27:
                        case 52:
                        case 53:
                        case 63:
                        case 64:
                        case 65: {
                            this.Text();
                            break Label_0415;
                        }
                        default: {
                            this.jj_la1[2] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final void EscapedDirective() throws ParseException {
        final ASTEscapedDirective jjtn000 = new ASTEscapedDirective(this, 2);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            Token t = null;
            t = this.jj_consume_token(11);
            this.jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            t.image = this.escapedDirective(t.image);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Escape() throws ParseException {
        final ASTEscape jjtn000 = new ASTEscape(this, 3);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            Token t = null;
            int count = 0;
            boolean control = false;
            do {
                t = this.jj_consume_token(19);
                ++count;
            } while (this.jj_2_2(2));
            this.jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            switch (t.next.kind) {
                case 46:
                case 47:
                case 48:
                case 49:
                case 50: {
                    control = true;
                    break;
                }
            }
            if (this.isDirective(t.next.image.substring(1))) {
                control = true;
            }
            else if (this.rsvc.isVelocimacro(t.next.image.substring(1), this.currentTemplateName)) {
                control = true;
            }
            jjtn000.val = "";
            for (int i = 0; i < count; ++i) {
                final StringBuffer sb = new StringBuffer();
                final ASTEscape astEscape = jjtn000;
                astEscape.val = sb.append(astEscape.val).append(control ? "\\" : "\\\\").toString();
            }
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Comment() throws ParseException {
        final ASTComment jjtn000 = new ASTComment(this, 4);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            Label_0183: {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 18: {
                        this.jj_consume_token(18);
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 22: {
                                this.jj_consume_token(22);
                                break Label_0183;
                            }
                            default: {
                                this.jj_la1[3] = this.jj_gen;
                                break Label_0183;
                            }
                        }
                        break;
                    }
                    case 24: {
                        this.jj_consume_token(24);
                        break;
                    }
                    case 23: {
                        this.jj_consume_token(23);
                        break;
                    }
                    default: {
                        this.jj_la1[4] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
            }
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void FloatingPointLiteral() throws ParseException {
        final ASTFloatingPointLiteral jjtn000 = new ASTFloatingPointLiteral(this, 5);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(53);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void IntegerLiteral() throws ParseException {
        final ASTIntegerLiteral jjtn000 = new ASTIntegerLiteral(this, 6);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(52);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void StringLiteral() throws ParseException {
        final ASTStringLiteral jjtn000 = new ASTStringLiteral(this, 7);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(27);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Identifier() throws ParseException {
        final ASTIdentifier jjtn000 = new ASTIdentifier(this, 8);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(62);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Word() throws ParseException {
        final ASTWord jjtn000 = new ASTWord(this, 9);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(57);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final int DirectiveArg() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 62:
            case 64: {
                this.Reference();
                return 16;
            }
            case 57: {
                this.Word();
                return 9;
            }
            case 27: {
                this.StringLiteral();
                return 7;
            }
            case 52: {
                this.IntegerLiteral();
                return 6;
            }
            default: {
                this.jj_la1[5] = this.jj_gen;
                if (this.jj_2_3(Integer.MAX_VALUE)) {
                    this.IntegerRange();
                    return 14;
                }
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 53: {
                        this.FloatingPointLiteral();
                        return 5;
                    }
                    case 6: {
                        this.Map();
                        return 12;
                    }
                    case 1: {
                        this.ObjectArray();
                        return 13;
                    }
                    case 28: {
                        this.True();
                        return 17;
                    }
                    case 29: {
                        this.False();
                        return 18;
                    }
                    default: {
                        this.jj_la1[6] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                break;
            }
        }
    }
    
    public final SimpleNode Directive() throws ParseException {
        final ASTDirective jjtn000 = new ASTDirective(this, 10);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        Token t = null;
        int argPos = 0;
        boolean isVM = false;
        boolean doItNow = false;
        try {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 57: {
                    t = this.jj_consume_token(57);
                    break;
                }
                case 58: {
                    t = this.jj_consume_token(58);
                    break;
                }
                default: {
                    this.jj_la1[7] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            String directiveName;
            if (t.kind == 58) {
                directiveName = t.image.substring(2, t.image.length() - 1);
            }
            else {
                directiveName = t.image.substring(1);
            }
            final Directive d = this.directives.get(directiveName);
            if (directiveName.equals("macro")) {
                doItNow = true;
            }
            jjtn000.setDirectiveName(directiveName);
            int directiveType;
            if (d == null) {
                isVM = this.rsvc.isVelocimacro(directiveName, this.currentTemplateName);
                if (!isVM) {
                    this.token_source.stateStackPop();
                    this.token_source.inDirective = false;
                    return jjtn000;
                }
                directiveType = 2;
            }
            else {
                directiveType = d.getType();
            }
            this.token_source.SwitchTo(0);
            argPos = 0;
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[8] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(8);
            while (this.jj_2_4(2)) {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 26: {
                        this.jj_consume_token(26);
                        break;
                    }
                    default: {
                        this.jj_la1[9] = this.jj_gen;
                        break;
                    }
                }
                Label_0531: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 3: {
                            this.jj_consume_token(3);
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 26: {
                                    this.jj_consume_token(26);
                                    break Label_0531;
                                }
                                default: {
                                    this.jj_la1[10] = this.jj_gen;
                                    break Label_0531;
                                }
                            }
                            break;
                        }
                        default: {
                            this.jj_la1[11] = this.jj_gen;
                            break;
                        }
                    }
                }
                final int argType = this.DirectiveArg();
                if (argType == 9) {
                    if (!doItNow || argPos != 0) {
                        if ((!t.image.equals("#foreach") && !t.image.equals("#{foreach}")) || argPos != 1) {
                            throw new MacroParseException("Invalid arg #" + argPos + " in " + (isVM ? "VM " : "directive ") + t.image, this.currentTemplateName, t);
                        }
                    }
                }
                else if (doItNow && argPos == 0) {
                    throw new MacroParseException("Invalid first arg in #macro() directive - must be a word token (no ' or \" surrounding)", this.currentTemplateName, t);
                }
                ++argPos;
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[12] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(9);
            if (directiveType == 2) {
                return jjtn000;
            }
            final ASTBlock jjtn2 = new ASTBlock(this, 11);
            boolean jjtc2 = true;
            this.jjtree.openNodeScope(jjtn2);
            Label_1191: {
                try {
                    while (true) {
                        this.Statement();
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 8:
                            case 9:
                            case 11:
                            case 12:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 23:
                            case 24:
                            case 27:
                            case 47:
                            case 50:
                            case 52:
                            case 53:
                            case 57:
                            case 58:
                            case 62:
                            case 63:
                            case 64:
                            case 65: {
                                continue;
                            }
                            default: {
                                this.jj_la1[13] = this.jj_gen;
                                break Label_1191;
                            }
                        }
                    }
                }
                catch (Throwable jjte001) {
                    if (jjtc2) {
                        this.jjtree.clearNodeScope(jjtn2);
                        jjtc2 = false;
                    }
                    else {
                        this.jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                        throw (RuntimeException)jjte001;
                    }
                    if (jjte001 instanceof ParseException) {
                        throw (ParseException)jjte001;
                    }
                    throw (Error)jjte001;
                }
                finally {
                    if (jjtc2) {
                        this.jjtree.closeNodeScope(jjtn2, true);
                    }
                }
            }
            this.jj_consume_token(46);
            this.jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            if (doItNow) {
                Macro.processAndRegister(this.rsvc, t, jjtn000, this.currentTemplateName);
            }
            return jjtn000;
        }
        catch (Throwable jjte2) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte2 instanceof RuntimeException) {
                throw (RuntimeException)jjte2;
            }
            if (jjte2 instanceof ParseException) {
                throw (ParseException)jjte2;
            }
            throw (Error)jjte2;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Map() throws ParseException {
        final ASTMap jjtn000 = new ASTMap(this, 12);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(6);
            Label_0185: {
                if (this.jj_2_5(2)) {
                    this.Parameter();
                    this.jj_consume_token(5);
                    this.Parameter();
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 3: {
                                this.jj_consume_token(3);
                                this.Parameter();
                                this.jj_consume_token(5);
                                this.Parameter();
                                continue;
                            }
                            default: {
                                this.jj_la1[14] = this.jj_gen;
                                break Label_0185;
                            }
                        }
                    }
                }
                else {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 26: {
                            this.jj_consume_token(26);
                            break;
                        }
                        default: {
                            this.jj_la1[15] = this.jj_gen;
                            break;
                        }
                    }
                }
            }
            this.jj_consume_token(7);
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void ObjectArray() throws ParseException {
        final ASTObjectArray jjtn000 = new ASTObjectArray(this, 13);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(1);
            Label_0217: {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 1:
                    case 6:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 52:
                    case 53:
                    case 62:
                    case 64: {
                        this.Parameter();
                        while (true) {
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 3: {
                                    this.jj_consume_token(3);
                                    this.Parameter();
                                    continue;
                                }
                                default: {
                                    this.jj_la1[16] = this.jj_gen;
                                    break Label_0217;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        this.jj_la1[17] = this.jj_gen;
                        break;
                    }
                }
            }
            this.jj_consume_token(2);
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void IntegerRange() throws ParseException {
        final ASTIntegerRange jjtn000 = new ASTIntegerRange(this, 14);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(1);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[18] = this.jj_gen;
                    break;
                }
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 62:
                case 64: {
                    this.Reference();
                    break;
                }
                case 52: {
                    this.IntegerLiteral();
                    break;
                }
                default: {
                    this.jj_la1[19] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[20] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(4);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[21] = this.jj_gen;
                    break;
                }
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 62:
                case 64: {
                    this.Reference();
                    break;
                }
                case 52: {
                    this.IntegerLiteral();
                    break;
                }
                default: {
                    this.jj_la1[22] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[23] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(2);
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Parameter() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[24] = this.jj_gen;
                break;
            }
        }
        Label_0299: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 27: {
                    this.StringLiteral();
                    break;
                }
                case 52: {
                    this.IntegerLiteral();
                    break;
                }
                default: {
                    this.jj_la1[25] = this.jj_gen;
                    if (this.jj_2_6(Integer.MAX_VALUE)) {
                        this.IntegerRange();
                        break;
                    }
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 6: {
                            this.Map();
                            break Label_0299;
                        }
                        case 1: {
                            this.ObjectArray();
                            break Label_0299;
                        }
                        case 28: {
                            this.True();
                            break Label_0299;
                        }
                        case 29: {
                            this.False();
                            break Label_0299;
                        }
                        case 62:
                        case 64: {
                            this.Reference();
                            break Label_0299;
                        }
                        case 53: {
                            this.FloatingPointLiteral();
                            break Label_0299;
                        }
                        default: {
                            this.jj_la1[26] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
            }
        }
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[27] = this.jj_gen;
                break;
            }
        }
    }
    
    public final void Method() throws ParseException {
        final ASTMethod jjtn000 = new ASTMethod(this, 15);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.Identifier();
            this.jj_consume_token(8);
            Label_0221: {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 1:
                    case 6:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 52:
                    case 53:
                    case 62:
                    case 64: {
                        this.Parameter();
                        while (true) {
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 3: {
                                    this.jj_consume_token(3);
                                    this.Parameter();
                                    continue;
                                }
                                default: {
                                    this.jj_la1[28] = this.jj_gen;
                                    break Label_0221;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        this.jj_la1[29] = this.jj_gen;
                        break;
                    }
                }
            }
            this.jj_consume_token(10);
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Reference() throws ParseException {
        final ASTReference jjtn000 = new ASTReference(this, 16);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 62: {
                    this.jj_consume_token(62);
                    while (this.jj_2_7(2)) {
                        this.jj_consume_token(63);
                        if (this.jj_2_8(3)) {
                            this.Method();
                        }
                        else {
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 62: {
                                    this.Identifier();
                                    continue;
                                }
                                default: {
                                    this.jj_la1[30] = this.jj_gen;
                                    this.jj_consume_token(-1);
                                    throw new ParseException();
                                }
                            }
                        }
                    }
                    break;
                }
                case 64: {
                    this.jj_consume_token(64);
                    this.jj_consume_token(62);
                    while (this.jj_2_9(2)) {
                        this.jj_consume_token(63);
                        if (this.jj_2_10(3)) {
                            this.Method();
                        }
                        else {
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 62: {
                                    this.Identifier();
                                    continue;
                                }
                                default: {
                                    this.jj_la1[31] = this.jj_gen;
                                    this.jj_consume_token(-1);
                                    throw new ParseException();
                                }
                            }
                        }
                    }
                    this.jj_consume_token(65);
                    break;
                }
                default: {
                    this.jj_la1[32] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void True() throws ParseException {
        final ASTTrue jjtn000 = new ASTTrue(this, 17);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(28);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void False() throws ParseException {
        final ASTFalse jjtn000 = new ASTFalse(this, 18);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(29);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Text() throws ParseException {
        final ASTText jjtn000 = new ASTText(this, 19);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 21: {
                    this.jj_consume_token(21);
                    break;
                }
                case 63: {
                    this.jj_consume_token(63);
                    break;
                }
                case 9: {
                    this.jj_consume_token(9);
                    break;
                }
                case 8: {
                    this.jj_consume_token(8);
                    break;
                }
                case 52: {
                    this.jj_consume_token(52);
                    break;
                }
                case 53: {
                    this.jj_consume_token(53);
                    break;
                }
                case 27: {
                    this.jj_consume_token(27);
                    break;
                }
                case 20: {
                    this.jj_consume_token(20);
                    break;
                }
                case 64: {
                    this.jj_consume_token(64);
                    break;
                }
                case 65: {
                    this.jj_consume_token(65);
                    break;
                }
                default: {
                    this.jj_la1[33] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void IfStatement() throws ParseException {
        final ASTIfStatement jjtn000 = new ASTIfStatement(this, 20);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(47);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[34] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(8);
            this.Expression();
            this.jj_consume_token(9);
            final ASTBlock jjtn2 = new ASTBlock(this, 11);
            boolean jjtc2 = true;
            this.jjtree.openNodeScope(jjtn2);
            Label_0515: {
                try {
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 8:
                            case 9:
                            case 11:
                            case 12:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 23:
                            case 24:
                            case 27:
                            case 47:
                            case 50:
                            case 52:
                            case 53:
                            case 57:
                            case 58:
                            case 62:
                            case 63:
                            case 64:
                            case 65: {
                                this.Statement();
                                continue;
                            }
                            default: {
                                this.jj_la1[35] = this.jj_gen;
                                break Label_0515;
                            }
                        }
                    }
                }
                catch (Throwable jjte001) {
                    if (jjtc2) {
                        this.jjtree.clearNodeScope(jjtn2);
                        jjtc2 = false;
                    }
                    else {
                        this.jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                        throw (RuntimeException)jjte001;
                    }
                    if (jjte001 instanceof ParseException) {
                        throw (ParseException)jjte001;
                    }
                    throw (Error)jjte001;
                }
                finally {
                    if (jjtc2) {
                        this.jjtree.closeNodeScope(jjtn2, true);
                    }
                }
            }
            Label_0620: {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 48: {
                        while (true) {
                            this.ElseIfStatement();
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 48: {
                                    continue;
                                }
                                default: {
                                    this.jj_la1[36] = this.jj_gen;
                                    break Label_0620;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        this.jj_la1[37] = this.jj_gen;
                        break;
                    }
                }
            }
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 49: {
                    this.ElseStatement();
                    break;
                }
                default: {
                    this.jj_la1[38] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(46);
        }
        catch (Throwable jjte2) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte2 instanceof RuntimeException) {
                throw (RuntimeException)jjte2;
            }
            if (jjte2 instanceof ParseException) {
                throw (ParseException)jjte2;
            }
            throw (Error)jjte2;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void ElseStatement() throws ParseException {
        final ASTElseStatement jjtn000 = new ASTElseStatement(this, 21);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(49);
            final ASTBlock jjtn2 = new ASTBlock(this, 11);
            boolean jjtc2 = true;
            this.jjtree.openNodeScope(jjtn2);
            Label_0439: {
                try {
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 8:
                            case 9:
                            case 11:
                            case 12:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 23:
                            case 24:
                            case 27:
                            case 47:
                            case 50:
                            case 52:
                            case 53:
                            case 57:
                            case 58:
                            case 62:
                            case 63:
                            case 64:
                            case 65: {
                                this.Statement();
                                continue;
                            }
                            default: {
                                this.jj_la1[39] = this.jj_gen;
                                break Label_0439;
                            }
                        }
                    }
                }
                catch (Throwable jjte001) {
                    if (jjtc2) {
                        this.jjtree.clearNodeScope(jjtn2);
                        jjtc2 = false;
                    }
                    else {
                        this.jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                        throw (RuntimeException)jjte001;
                    }
                    if (jjte001 instanceof ParseException) {
                        throw (ParseException)jjte001;
                    }
                    throw (Error)jjte001;
                }
                finally {
                    if (jjtc2) {
                        this.jjtree.closeNodeScope(jjtn2, true);
                    }
                }
            }
        }
        catch (Throwable jjte2) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte2 instanceof RuntimeException) {
                throw (RuntimeException)jjte2;
            }
            if (jjte2 instanceof ParseException) {
                throw (ParseException)jjte2;
            }
            throw (Error)jjte2;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void ElseIfStatement() throws ParseException {
        final ASTElseIfStatement jjtn000 = new ASTElseIfStatement(this, 22);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(48);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[40] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(8);
            this.Expression();
            this.jj_consume_token(9);
            final ASTBlock jjtn2 = new ASTBlock(this, 11);
            boolean jjtc2 = true;
            this.jjtree.openNodeScope(jjtn2);
            Label_0515: {
                try {
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 8:
                            case 9:
                            case 11:
                            case 12:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 23:
                            case 24:
                            case 27:
                            case 47:
                            case 50:
                            case 52:
                            case 53:
                            case 57:
                            case 58:
                            case 62:
                            case 63:
                            case 64:
                            case 65: {
                                this.Statement();
                                continue;
                            }
                            default: {
                                this.jj_la1[41] = this.jj_gen;
                                break Label_0515;
                            }
                        }
                    }
                }
                catch (Throwable jjte001) {
                    if (jjtc2) {
                        this.jjtree.clearNodeScope(jjtn2);
                        jjtc2 = false;
                    }
                    else {
                        this.jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                        throw (RuntimeException)jjte001;
                    }
                    if (jjte001 instanceof ParseException) {
                        throw (ParseException)jjte001;
                    }
                    throw (Error)jjte001;
                }
                finally {
                    if (jjtc2) {
                        this.jjtree.closeNodeScope(jjtn2, true);
                    }
                }
            }
        }
        catch (Throwable jjte2) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte2 instanceof RuntimeException) {
                throw (RuntimeException)jjte2;
            }
            if (jjte2 instanceof ParseException) {
                throw (ParseException)jjte2;
            }
            throw (Error)jjte2;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void SetDirective() throws ParseException {
        final ASTSetDirective jjtn000 = new ASTSetDirective(this, 23);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(12);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[42] = this.jj_gen;
                    break;
                }
            }
            this.Reference();
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[43] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(45);
            this.Expression();
            this.jj_consume_token(9);
            this.token_source.inSet = false;
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 30: {
                    this.jj_consume_token(30);
                    break;
                }
                default: {
                    this.jj_la1[44] = this.jj_gen;
                    break;
                }
            }
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void StopStatement() throws ParseException {
        final ASTStop jjtn000 = new ASTStop(this, 24);
        final boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(50);
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, 0);
            }
        }
    }
    
    public final void Expression() throws ParseException {
        final ASTExpression jjtn000 = new ASTExpression(this, 25);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.ConditionalOrExpression();
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
    
    public final void Assignment() throws ParseException {
        final ASTAssignment jjtn000 = new ASTAssignment(this, 26);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.PrimaryExpression();
            this.jj_consume_token(45);
            this.Expression();
        }
        catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            }
            else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            throw (Error)jjte000;
        }
        finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope(jjtn000, 2);
            }
        }
    }
    
    public final void ConditionalOrExpression() throws ParseException {
        this.ConditionalAndExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 37: {
                    this.jj_consume_token(37);
                    final ASTOrNode jjtn001 = new ASTOrNode(this, 27);
                    boolean jjtc001 = true;
                    this.jjtree.openNodeScope(jjtn001);
                    try {
                        this.ConditionalAndExpression();
                    }
                    catch (Throwable jjte001) {
                        if (jjtc001) {
                            this.jjtree.clearNodeScope(jjtn001);
                            jjtc001 = false;
                        }
                        else {
                            this.jjtree.popNode();
                        }
                        if (jjte001 instanceof RuntimeException) {
                            throw (RuntimeException)jjte001;
                        }
                        if (jjte001 instanceof ParseException) {
                            throw (ParseException)jjte001;
                        }
                        throw (Error)jjte001;
                    }
                    finally {
                        if (jjtc001) {
                            this.jjtree.closeNodeScope(jjtn001, 2);
                        }
                    }
                    continue;
                }
                default: {
                    this.jj_la1[45] = this.jj_gen;
                }
            }
        }
    }
    
    public final void ConditionalAndExpression() throws ParseException {
        this.EqualityExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 36: {
                    this.jj_consume_token(36);
                    final ASTAndNode jjtn001 = new ASTAndNode(this, 28);
                    boolean jjtc001 = true;
                    this.jjtree.openNodeScope(jjtn001);
                    try {
                        this.EqualityExpression();
                    }
                    catch (Throwable jjte001) {
                        if (jjtc001) {
                            this.jjtree.clearNodeScope(jjtn001);
                            jjtc001 = false;
                        }
                        else {
                            this.jjtree.popNode();
                        }
                        if (jjte001 instanceof RuntimeException) {
                            throw (RuntimeException)jjte001;
                        }
                        if (jjte001 instanceof ParseException) {
                            throw (ParseException)jjte001;
                        }
                        throw (Error)jjte001;
                    }
                    finally {
                        if (jjtc001) {
                            this.jjtree.closeNodeScope(jjtn001, 2);
                        }
                    }
                    continue;
                }
                default: {
                    this.jj_la1[46] = this.jj_gen;
                }
            }
        }
    }
    
    public final void EqualityExpression() throws ParseException {
        this.RelationalExpression();
    Label_0365:
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 42:
                case 43: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 42: {
                            this.jj_consume_token(42);
                            final ASTEQNode jjtn001 = new ASTEQNode(this, 29);
                            boolean jjtc001 = true;
                            this.jjtree.openNodeScope(jjtn001);
                            try {
                                this.RelationalExpression();
                                continue;
                            }
                            catch (Throwable jjte001) {
                                if (jjtc001) {
                                    this.jjtree.clearNodeScope(jjtn001);
                                    jjtc001 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte001 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte001;
                                }
                                if (jjte001 instanceof ParseException) {
                                    throw (ParseException)jjte001;
                                }
                                throw (Error)jjte001;
                            }
                            finally {
                                if (jjtc001) {
                                    this.jjtree.closeNodeScope(jjtn001, 2);
                                }
                            }
                        }
                        case 43: {
                            this.jj_consume_token(43);
                            final ASTNENode jjtn2 = new ASTNENode(this, 30);
                            boolean jjtc2 = true;
                            this.jjtree.openNodeScope(jjtn2);
                            try {
                                this.RelationalExpression();
                                continue;
                            }
                            catch (Throwable jjte2) {
                                if (jjtc2) {
                                    this.jjtree.clearNodeScope(jjtn2);
                                    jjtc2 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte2 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte2;
                                }
                                if (jjte2 instanceof ParseException) {
                                    throw (ParseException)jjte2;
                                }
                                throw (Error)jjte2;
                            }
                            finally {
                                if (jjtc2) {
                                    this.jjtree.closeNodeScope(jjtn2, 2);
                                }
                            }
                        }
                        default: {
                            break Label_0365;
                        }
                    }
                    break;
                }
                default: {
                    this.jj_la1[47] = this.jj_gen;
                    return;
                }
            }
        }
        this.jj_la1[48] = this.jj_gen;
        this.jj_consume_token(-1);
        throw new ParseException();
    }
    
    public final void RelationalExpression() throws ParseException {
        this.AdditiveExpression();
    Label_0647:
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 38:
                case 39:
                case 40:
                case 41: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 38: {
                            this.jj_consume_token(38);
                            final ASTLTNode jjtn001 = new ASTLTNode(this, 31);
                            boolean jjtc001 = true;
                            this.jjtree.openNodeScope(jjtn001);
                            try {
                                this.AdditiveExpression();
                                continue;
                            }
                            catch (Throwable jjte001) {
                                if (jjtc001) {
                                    this.jjtree.clearNodeScope(jjtn001);
                                    jjtc001 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte001 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte001;
                                }
                                if (jjte001 instanceof ParseException) {
                                    throw (ParseException)jjte001;
                                }
                                throw (Error)jjte001;
                            }
                            finally {
                                if (jjtc001) {
                                    this.jjtree.closeNodeScope(jjtn001, 2);
                                }
                            }
                        }
                        case 40: {
                            this.jj_consume_token(40);
                            final ASTGTNode jjtn2 = new ASTGTNode(this, 32);
                            boolean jjtc2 = true;
                            this.jjtree.openNodeScope(jjtn2);
                            try {
                                this.AdditiveExpression();
                                continue;
                            }
                            catch (Throwable jjte2) {
                                if (jjtc2) {
                                    this.jjtree.clearNodeScope(jjtn2);
                                    jjtc2 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte2 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte2;
                                }
                                if (jjte2 instanceof ParseException) {
                                    throw (ParseException)jjte2;
                                }
                                throw (Error)jjte2;
                            }
                            finally {
                                if (jjtc2) {
                                    this.jjtree.closeNodeScope(jjtn2, 2);
                                }
                            }
                        }
                        case 39: {
                            this.jj_consume_token(39);
                            final ASTLENode jjtn3 = new ASTLENode(this, 33);
                            boolean jjtc3 = true;
                            this.jjtree.openNodeScope(jjtn3);
                            try {
                                this.AdditiveExpression();
                                continue;
                            }
                            catch (Throwable jjte3) {
                                if (jjtc3) {
                                    this.jjtree.clearNodeScope(jjtn3);
                                    jjtc3 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte3 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte3;
                                }
                                if (jjte3 instanceof ParseException) {
                                    throw (ParseException)jjte3;
                                }
                                throw (Error)jjte3;
                            }
                            finally {
                                if (jjtc3) {
                                    this.jjtree.closeNodeScope(jjtn3, 2);
                                }
                            }
                        }
                        case 41: {
                            this.jj_consume_token(41);
                            final ASTGENode jjtn4 = new ASTGENode(this, 34);
                            boolean jjtc4 = true;
                            this.jjtree.openNodeScope(jjtn4);
                            try {
                                this.AdditiveExpression();
                                continue;
                            }
                            catch (Throwable jjte4) {
                                if (jjtc4) {
                                    this.jjtree.clearNodeScope(jjtn4);
                                    jjtc4 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte4 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte4;
                                }
                                if (jjte4 instanceof ParseException) {
                                    throw (ParseException)jjte4;
                                }
                                throw (Error)jjte4;
                            }
                            finally {
                                if (jjtc4) {
                                    this.jjtree.closeNodeScope(jjtn4, 2);
                                }
                            }
                        }
                        default: {
                            break Label_0647;
                        }
                    }
                    break;
                }
                default: {
                    this.jj_la1[49] = this.jj_gen;
                    return;
                }
            }
        }
        this.jj_la1[50] = this.jj_gen;
        this.jj_consume_token(-1);
        throw new ParseException();
    }
    
    public final void AdditiveExpression() throws ParseException {
        this.MultiplicativeExpression();
    Label_0365:
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 31:
                case 32: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 32: {
                            this.jj_consume_token(32);
                            final ASTAddNode jjtn001 = new ASTAddNode(this, 35);
                            boolean jjtc001 = true;
                            this.jjtree.openNodeScope(jjtn001);
                            try {
                                this.MultiplicativeExpression();
                                continue;
                            }
                            catch (Throwable jjte001) {
                                if (jjtc001) {
                                    this.jjtree.clearNodeScope(jjtn001);
                                    jjtc001 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte001 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte001;
                                }
                                if (jjte001 instanceof ParseException) {
                                    throw (ParseException)jjte001;
                                }
                                throw (Error)jjte001;
                            }
                            finally {
                                if (jjtc001) {
                                    this.jjtree.closeNodeScope(jjtn001, 2);
                                }
                            }
                        }
                        case 31: {
                            this.jj_consume_token(31);
                            final ASTSubtractNode jjtn2 = new ASTSubtractNode(this, 36);
                            boolean jjtc2 = true;
                            this.jjtree.openNodeScope(jjtn2);
                            try {
                                this.MultiplicativeExpression();
                                continue;
                            }
                            catch (Throwable jjte2) {
                                if (jjtc2) {
                                    this.jjtree.clearNodeScope(jjtn2);
                                    jjtc2 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte2 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte2;
                                }
                                if (jjte2 instanceof ParseException) {
                                    throw (ParseException)jjte2;
                                }
                                throw (Error)jjte2;
                            }
                            finally {
                                if (jjtc2) {
                                    this.jjtree.closeNodeScope(jjtn2, 2);
                                }
                            }
                        }
                        default: {
                            break Label_0365;
                        }
                    }
                    break;
                }
                default: {
                    this.jj_la1[51] = this.jj_gen;
                    return;
                }
            }
        }
        this.jj_la1[52] = this.jj_gen;
        this.jj_consume_token(-1);
        throw new ParseException();
    }
    
    public final void MultiplicativeExpression() throws ParseException {
        this.UnaryExpression();
    Label_0502:
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 33:
                case 34:
                case 35: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 33: {
                            this.jj_consume_token(33);
                            final ASTMulNode jjtn001 = new ASTMulNode(this, 37);
                            boolean jjtc001 = true;
                            this.jjtree.openNodeScope(jjtn001);
                            try {
                                this.UnaryExpression();
                                continue;
                            }
                            catch (Throwable jjte001) {
                                if (jjtc001) {
                                    this.jjtree.clearNodeScope(jjtn001);
                                    jjtc001 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte001 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte001;
                                }
                                if (jjte001 instanceof ParseException) {
                                    throw (ParseException)jjte001;
                                }
                                throw (Error)jjte001;
                            }
                            finally {
                                if (jjtc001) {
                                    this.jjtree.closeNodeScope(jjtn001, 2);
                                }
                            }
                        }
                        case 34: {
                            this.jj_consume_token(34);
                            final ASTDivNode jjtn2 = new ASTDivNode(this, 38);
                            boolean jjtc2 = true;
                            this.jjtree.openNodeScope(jjtn2);
                            try {
                                this.UnaryExpression();
                                continue;
                            }
                            catch (Throwable jjte2) {
                                if (jjtc2) {
                                    this.jjtree.clearNodeScope(jjtn2);
                                    jjtc2 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte2 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte2;
                                }
                                if (jjte2 instanceof ParseException) {
                                    throw (ParseException)jjte2;
                                }
                                throw (Error)jjte2;
                            }
                            finally {
                                if (jjtc2) {
                                    this.jjtree.closeNodeScope(jjtn2, 2);
                                }
                            }
                        }
                        case 35: {
                            this.jj_consume_token(35);
                            final ASTModNode jjtn3 = new ASTModNode(this, 39);
                            boolean jjtc3 = true;
                            this.jjtree.openNodeScope(jjtn3);
                            try {
                                this.UnaryExpression();
                                continue;
                            }
                            catch (Throwable jjte3) {
                                if (jjtc3) {
                                    this.jjtree.clearNodeScope(jjtn3);
                                    jjtc3 = false;
                                }
                                else {
                                    this.jjtree.popNode();
                                }
                                if (jjte3 instanceof RuntimeException) {
                                    throw (RuntimeException)jjte3;
                                }
                                if (jjte3 instanceof ParseException) {
                                    throw (ParseException)jjte3;
                                }
                                throw (Error)jjte3;
                            }
                            finally {
                                if (jjtc3) {
                                    this.jjtree.closeNodeScope(jjtn3, 2);
                                }
                            }
                        }
                        default: {
                            break Label_0502;
                        }
                    }
                    break;
                }
                default: {
                    this.jj_la1[53] = this.jj_gen;
                    return;
                }
            }
        }
        this.jj_la1[54] = this.jj_gen;
        this.jj_consume_token(-1);
        throw new ParseException();
    }
    
    public final void UnaryExpression() throws ParseException {
        if (this.jj_2_11(2)) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 26: {
                    this.jj_consume_token(26);
                    break;
                }
                default: {
                    this.jj_la1[55] = this.jj_gen;
                    break;
                }
            }
            this.jj_consume_token(44);
            final ASTNotNode jjtn001 = new ASTNotNode(this, 40);
            boolean jjtc001 = true;
            this.jjtree.openNodeScope(jjtn001);
            try {
                this.UnaryExpression();
            }
            catch (Throwable jjte001) {
                if (jjtc001) {
                    this.jjtree.clearNodeScope(jjtn001);
                    jjtc001 = false;
                }
                else {
                    this.jjtree.popNode();
                }
                if (jjte001 instanceof RuntimeException) {
                    throw (RuntimeException)jjte001;
                }
                if (jjte001 instanceof ParseException) {
                    throw (ParseException)jjte001;
                }
                throw (Error)jjte001;
            }
            finally {
                if (jjtc001) {
                    this.jjtree.closeNodeScope(jjtn001, 1);
                }
            }
        }
        else {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 1:
                case 6:
                case 8:
                case 26:
                case 27:
                case 28:
                case 29:
                case 52:
                case 53:
                case 62:
                case 64: {
                    this.PrimaryExpression();
                    break;
                }
                default: {
                    this.jj_la1[56] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
    }
    
    public final void PrimaryExpression() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[57] = this.jj_gen;
                break;
            }
        }
        Label_0329: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 27: {
                    this.StringLiteral();
                    break;
                }
                case 62:
                case 64: {
                    this.Reference();
                    break;
                }
                case 52: {
                    this.IntegerLiteral();
                    break;
                }
                default: {
                    this.jj_la1[58] = this.jj_gen;
                    if (this.jj_2_12(Integer.MAX_VALUE)) {
                        this.IntegerRange();
                        break;
                    }
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 53: {
                            this.FloatingPointLiteral();
                            break Label_0329;
                        }
                        case 6: {
                            this.Map();
                            break Label_0329;
                        }
                        case 1: {
                            this.ObjectArray();
                            break Label_0329;
                        }
                        case 28: {
                            this.True();
                            break Label_0329;
                        }
                        case 29: {
                            this.False();
                            break Label_0329;
                        }
                        case 8: {
                            this.jj_consume_token(8);
                            this.Expression();
                            this.jj_consume_token(9);
                            break Label_0329;
                        }
                        default: {
                            this.jj_la1[59] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
            }
        }
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[60] = this.jj_gen;
                break;
            }
        }
    }
    
    private final boolean jj_2_1(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_1();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(0, xla);
        }
    }
    
    private final boolean jj_2_2(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_2();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(1, xla);
        }
    }
    
    private final boolean jj_2_3(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_3();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(2, xla);
        }
    }
    
    private final boolean jj_2_4(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_4();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(3, xla);
        }
    }
    
    private final boolean jj_2_5(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_5();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(4, xla);
        }
    }
    
    private final boolean jj_2_6(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_6();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(5, xla);
        }
    }
    
    private final boolean jj_2_7(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_7();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(6, xla);
        }
    }
    
    private final boolean jj_2_8(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_8();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(7, xla);
        }
    }
    
    private final boolean jj_2_9(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_9();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(8, xla);
        }
    }
    
    private final boolean jj_2_10(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_10();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(9, xla);
        }
    }
    
    private final boolean jj_2_11(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_11();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(10, xla);
        }
    }
    
    private final boolean jj_2_12(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_12();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(11, xla);
        }
    }
    
    private final boolean jj_3R_82() {
        return this.jj_scan_token(3) || this.jj_3R_25();
    }
    
    private final boolean jj_3_8() {
        return this.jj_3R_29();
    }
    
    private final boolean jj_3R_26() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_66() {
        return this.jj_scan_token(29);
    }
    
    private final boolean jj_3R_65() {
        return this.jj_scan_token(28);
    }
    
    private final boolean jj_3_9() {
        if (this.jj_scan_token(63)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_10()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_30()) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean jj_3R_57() {
        if (this.jj_3R_25()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_82());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3_7() {
        if (this.jj_scan_token(63)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_8()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_28()) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean jj_3_2() {
        return this.jj_scan_token(19);
    }
    
    private final boolean jj_3R_35() {
        if (this.jj_scan_token(64)) {
            return true;
        }
        if (this.jj_scan_token(62)) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_9());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(65);
    }
    
    private final boolean jj_3_12() {
        if (this.jj_scan_token(1)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_32()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_33()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(4);
    }
    
    private final boolean jj_3R_34() {
        if (this.jj_scan_token(62)) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_7());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_81() {
        return this.jj_scan_token(8);
    }
    
    private final boolean jj_3R_80() {
        return this.jj_3R_66();
    }
    
    private final boolean jj_3R_79() {
        return this.jj_3R_65();
    }
    
    private final boolean jj_3R_20() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_34()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_35()) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean jj_3R_78() {
        return this.jj_3R_64();
    }
    
    private final boolean jj_3R_77() {
        return this.jj_3R_63();
    }
    
    private final boolean jj_3R_76() {
        return this.jj_3R_62();
    }
    
    private final boolean jj_3R_75() {
        return this.jj_3R_61();
    }
    
    private final boolean jj_3R_74() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_73() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3_6() {
        if (this.jj_scan_token(1)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_26()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_27()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(4);
    }
    
    private final boolean jj_3R_29() {
        if (this.jj_3R_56()) {
            return true;
        }
        if (this.jj_scan_token(8)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_57()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(10);
    }
    
    private final boolean jj_3R_72() {
        return this.jj_3R_60();
    }
    
    private final boolean jj_3R_67() {
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_72()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_73()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_74()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_75()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_76()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_77()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_78()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_79()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_80()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3R_81()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_55() {
        return this.jj_3R_62();
    }
    
    private final boolean jj_3R_54() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_53() {
        return this.jj_3R_66();
    }
    
    private final boolean jj_3R_85() {
        return this.jj_scan_token(3) || this.jj_3R_25() || this.jj_scan_token(5) || this.jj_3R_25();
    }
    
    private final boolean jj_3R_52() {
        return this.jj_3R_65();
    }
    
    private final boolean jj_3R_31() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_11()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_58()) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean jj_3_11() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(44) || this.jj_3R_31();
    }
    
    private final boolean jj_3R_58() {
        return this.jj_3R_67();
    }
    
    private final boolean jj_3R_51() {
        return this.jj_3R_64();
    }
    
    private final boolean jj_3R_50() {
        return this.jj_3R_63();
    }
    
    private final boolean jj_3R_49() {
        return this.jj_3R_61();
    }
    
    private final boolean jj_3R_48() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_47() {
        return this.jj_3R_60();
    }
    
    private final boolean jj_3R_22() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_84() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_69() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_86() {
        return this.jj_scan_token(3) || this.jj_3R_25();
    }
    
    private final boolean jj_3R_25() {
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_47()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_48()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_49()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_50()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_51()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_52()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_53()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_54()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_55()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private final boolean jj_3_1() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_21() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_83() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_68() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_71() {
        if (this.jj_3R_25()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_86());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_61() {
        if (this.jj_scan_token(1)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_68()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_69()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(4)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_83()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_84()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(2);
    }
    
    private final boolean jj_3R_64() {
        if (this.jj_scan_token(1)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_71()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(2);
    }
    
    private final boolean jj_3R_46() {
        return this.jj_3R_66();
    }
    
    private final boolean jj_3R_70() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private final boolean jj_3_5() {
        if (this.jj_3R_25()) {
            return true;
        }
        if (this.jj_scan_token(5)) {
            return true;
        }
        if (this.jj_3R_25()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_85());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_45() {
        return this.jj_3R_65();
    }
    
    private final boolean jj_3R_63() {
        if (this.jj_scan_token(6)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_5()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_70()) {
                return true;
            }
        }
        return this.jj_scan_token(7);
    }
    
    private final boolean jj_3_3() {
        if (this.jj_scan_token(1)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_21()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_22()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(4);
    }
    
    private final boolean jj_3R_44() {
        return this.jj_3R_64();
    }
    
    private final boolean jj_3R_43() {
        return this.jj_3R_63();
    }
    
    private final boolean jj_3R_42() {
        return this.jj_3R_62();
    }
    
    private final boolean jj_3R_41() {
        return this.jj_3R_61();
    }
    
    private final boolean jj_3R_40() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_39() {
        return this.jj_3R_60();
    }
    
    private final boolean jj_3R_23() {
        if (this.jj_scan_token(3)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private final boolean jj_3R_38() {
        return this.jj_3R_59();
    }
    
    private final boolean jj_3R_37() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_24() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_37()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_38()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_39()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_40()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_41()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_42()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_43()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_44()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_45()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3R_46()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_59() {
        return this.jj_scan_token(57);
    }
    
    private final boolean jj_3R_56() {
        return this.jj_scan_token(62);
    }
    
    private final boolean jj_3_4() {
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_23()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_24();
    }
    
    private final boolean jj_3R_60() {
        return this.jj_scan_token(27);
    }
    
    private final boolean jj_3R_30() {
        return this.jj_3R_56();
    }
    
    private final boolean jj_3R_36() {
        return this.jj_scan_token(52);
    }
    
    private final boolean jj_3R_28() {
        return this.jj_3R_56();
    }
    
    private final boolean jj_3R_62() {
        return this.jj_scan_token(53);
    }
    
    private final boolean jj_3R_33() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3R_32() {
        return this.jj_3R_20();
    }
    
    private final boolean jj_3R_27() {
        return this.jj_3R_36();
    }
    
    private final boolean jj_3_10() {
        return this.jj_3R_29();
    }
    
    private static void jj_la1_0() {
        Parser.jj_la1_0 = new int[] { 163322624, 0, 163322624, 4194304, 25427968, 134217728, 805306434, 0, 67108864, 67108864, 67108864, 8, 67108864, 163322624, 8, 67108864, 8, 1006633026, 67108864, 0, 67108864, 67108864, 0, 67108864, 67108864, 134217728, 805306434, 67108864, 8, 1006633026, 0, 0, 0, 137364224, 67108864, 163322624, 0, 0, 0, 163322624, 67108864, 163322624, 67108864, 67108864, 1073741824, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0, 67108864, 1006633282, 67108864, 134217728, 805306690, 67108864 };
    }
    
    private static void jj_la1_1() {
        Parser.jj_la1_1 = new int[] { -969637888, 294912, -2043674624, 0, 0, 1108344832, 2097152, 100663296, 0, 0, 0, 0, 0, -969637888, 0, 0, 0, 1076887552, 0, 1074790400, 0, 0, 1074790400, 0, 0, 1048576, 1075838976, 0, 0, 1076887552, 1073741824, 1073741824, 1073741824, -2144337920, 0, -969637888, 65536, 65536, 131072, -969637888, 0, -969637888, 0, 0, 0, 32, 16, 3072, 3072, 960, 960, 1, 1, 14, 14, 0, 1076887552, 0, 1074790400, 2097152, 0 };
    }
    
    private static void jj_la1_2() {
        Parser.jj_la1_2 = new int[] { 3, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 };
    }
    
    public Parser(final CharStream stream) {
        this.jjtree = new JJTParserState();
        this.directives = new Hashtable(0);
        this.currentTemplateName = "";
        this.velcharstream = null;
        this.rsvc = null;
        this.lookingAhead = false;
        this.jj_la1 = new int[61];
        this.jj_2_rtns = new JJCalls[12];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new Vector();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.token_source = new ParserTokenManager(stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 61; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final CharStream stream) {
        this.token_source.ReInit(stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jjtree.reset();
        this.jj_gen = 0;
        for (int i = 0; i < 61; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public Parser(final ParserTokenManager tm) {
        this.jjtree = new JJTParserState();
        this.directives = new Hashtable(0);
        this.currentTemplateName = "";
        this.velcharstream = null;
        this.rsvc = null;
        this.lookingAhead = false;
        this.jj_la1 = new int[61];
        this.jj_2_rtns = new JJCalls[12];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new Vector();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 61; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final ParserTokenManager tm) {
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jjtree.reset();
        this.jj_gen = 0;
        for (int i = 0; i < 61; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    private final Token jj_consume_token(final int kind) throws ParseException {
        final Token oldToken;
        if ((oldToken = this.token).next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            ++this.jj_gen;
            if (++this.jj_gc > 100) {
                this.jj_gc = 0;
                for (int i = 0; i < this.jj_2_rtns.length; ++i) {
                    for (JJCalls c = this.jj_2_rtns[i]; c != null; c = c.next) {
                        if (c.gen < this.jj_gen) {
                            c.first = null;
                        }
                    }
                }
            }
            return this.token;
        }
        this.token = oldToken;
        this.jj_kind = kind;
        throw this.generateParseException();
    }
    
    private final boolean jj_scan_token(final int kind) {
        if (this.jj_scanpos == this.jj_lastpos) {
            --this.jj_la;
            if (this.jj_scanpos.next == null) {
                final Token jj_scanpos = this.jj_scanpos;
                final Token nextToken = this.token_source.getNextToken();
                jj_scanpos.next = nextToken;
                this.jj_scanpos = nextToken;
                this.jj_lastpos = nextToken;
            }
            else {
                final Token next = this.jj_scanpos.next;
                this.jj_scanpos = next;
                this.jj_lastpos = next;
            }
        }
        else {
            this.jj_scanpos = this.jj_scanpos.next;
        }
        if (this.jj_rescan) {
            int i = 0;
            Token tok;
            for (tok = this.token; tok != null && tok != this.jj_scanpos; tok = tok.next) {
                ++i;
            }
            if (tok != null) {
                this.jj_add_error_token(kind, i);
            }
        }
        if (this.jj_scanpos.kind != kind) {
            return true;
        }
        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }
    
    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }
    
    public final Token getToken(final int index) {
        Token t = this.lookingAhead ? this.jj_scanpos : this.token;
        for (int i = 0; i < index; ++i) {
            if (t.next != null) {
                t = t.next;
            }
            else {
                final Token token = t;
                final Token nextToken = this.token_source.getNextToken();
                token.next = nextToken;
                t = nextToken;
            }
        }
        return t;
    }
    
    private final int jj_ntk() {
        final Token next = this.token.next;
        this.jj_nt = next;
        if (next == null) {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            return this.jj_ntk = nextToken.kind;
        }
        return this.jj_ntk = this.jj_nt.kind;
    }
    
    private void jj_add_error_token(final int kind, final int pos) {
        if (pos >= 100) {
            return;
        }
        if (pos == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = kind;
        }
        else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];
            for (int i = 0; i < this.jj_endpos; ++i) {
                this.jj_expentry[i] = this.jj_lasttokens[i];
            }
            boolean exists = false;
            final Enumeration e = this.jj_expentries.elements();
            while (e.hasMoreElements()) {
                final int[] oldentry = e.nextElement();
                if (oldentry.length == this.jj_expentry.length) {
                    exists = true;
                    for (int j = 0; j < this.jj_expentry.length; ++j) {
                        if (oldentry[j] != this.jj_expentry[j]) {
                            exists = false;
                            break;
                        }
                    }
                    if (exists) {
                        break;
                    }
                    continue;
                }
            }
            if (!exists) {
                this.jj_expentries.addElement(this.jj_expentry);
            }
            if (pos != 0) {
                this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind;
            }
        }
    }
    
    public ParseException generateParseException() {
        this.jj_expentries.removeAllElements();
        final boolean[] la1tokens = new boolean[68];
        for (int i = 0; i < 68; ++i) {
            la1tokens[i] = false;
        }
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }
        for (int i = 0; i < 61; ++i) {
            if (this.jj_la1[i] == this.jj_gen) {
                for (int j = 0; j < 32; ++j) {
                    if ((Parser.jj_la1_0[i] & 1 << j) != 0x0) {
                        la1tokens[j] = true;
                    }
                    if ((Parser.jj_la1_1[i] & 1 << j) != 0x0) {
                        la1tokens[32 + j] = true;
                    }
                    if ((Parser.jj_la1_2[i] & 1 << j) != 0x0) {
                        la1tokens[64 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 68; ++i) {
            if (la1tokens[i]) {
                (this.jj_expentry = new int[1])[0] = i;
                this.jj_expentries.addElement(this.jj_expentry);
            }
        }
        this.jj_endpos = 0;
        this.jj_rescan_token();
        this.jj_add_error_token(0, 0);
        final int[][] exptokseq = new int[this.jj_expentries.size()][];
        for (int k = 0; k < this.jj_expentries.size(); ++k) {
            exptokseq[k] = this.jj_expentries.elementAt(k);
        }
        return new ParseException(this.token, exptokseq, ParserConstants.tokenImage);
    }
    
    public final void enable_tracing() {
    }
    
    public final void disable_tracing() {
    }
    
    private final void jj_rescan_token() {
        this.jj_rescan = true;
        for (int i = 0; i < 12; ++i) {
            try {
                JJCalls p = this.jj_2_rtns[i];
                do {
                    if (p.gen > this.jj_gen) {
                        this.jj_la = p.arg;
                        final Token first = p.first;
                        this.jj_scanpos = first;
                        this.jj_lastpos = first;
                        switch (i) {
                            case 0: {
                                this.jj_3_1();
                                break;
                            }
                            case 1: {
                                this.jj_3_2();
                                break;
                            }
                            case 2: {
                                this.jj_3_3();
                                break;
                            }
                            case 3: {
                                this.jj_3_4();
                                break;
                            }
                            case 4: {
                                this.jj_3_5();
                                break;
                            }
                            case 5: {
                                this.jj_3_6();
                                break;
                            }
                            case 6: {
                                this.jj_3_7();
                                break;
                            }
                            case 7: {
                                this.jj_3_8();
                                break;
                            }
                            case 8: {
                                this.jj_3_9();
                                break;
                            }
                            case 9: {
                                this.jj_3_10();
                                break;
                            }
                            case 10: {
                                this.jj_3_11();
                                break;
                            }
                            case 11: {
                                this.jj_3_12();
                                break;
                            }
                        }
                    }
                    p = p.next;
                } while (p != null);
            }
            catch (LookaheadSuccess lookaheadSuccess) {}
        }
        this.jj_rescan = false;
    }
    
    private final void jj_save(final int index, final int xla) {
        JJCalls p;
        for (p = this.jj_2_rtns[index]; p.gen > this.jj_gen; p = p.next) {
            if (p.next == null) {
                final JJCalls jjCalls = p;
                final JJCalls next = new JJCalls();
                jjCalls.next = next;
                p = next;
                break;
            }
        }
        p.gen = this.jj_gen + xla - this.jj_la;
        p.first = this.token;
        p.arg = xla;
    }
    
    static {
        jj_la1_0();
        jj_la1_1();
        jj_la1_2();
    }
    
    private static final class LookaheadSuccess extends Error
    {
    }
    
    static final class JJCalls
    {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }
}
