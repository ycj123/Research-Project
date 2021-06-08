// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.plugin.io.xpp3;

import org.codehaus.plexus.util.xml.pull.MXParser;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import org.apache.maven.usability.plugin.ExpressionDocumentation;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import org.apache.maven.usability.plugin.Expression;
import java.text.ParsePosition;
import java.text.DateFormat;
import java.util.Date;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;

public class ParamdocXpp3Reader
{
    private boolean addDefaultEntities;
    
    public ParamdocXpp3Reader() {
        this.addDefaultEntities = true;
    }
    
    public boolean getAddDefaultEntities() {
        return this.addDefaultEntities;
    }
    
    public boolean getBooleanValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        return s != null && Boolean.valueOf(s);
    }
    
    public char getCharacterValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        if (s != null) {
            return s.charAt(0);
        }
        return '\0';
    }
    
    public Date getDateValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        if (s != null) {
            final DateFormat dateParser = DateFormat.getDateTimeInstance(0, 0);
            return dateParser.parse(s, new ParsePosition(0));
        }
        return null;
    }
    
    public double getDoubleValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Double.valueOf(s);
            }
            catch (NumberFormatException e) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, null);
                }
            }
        }
        return 0.0;
    }
    
    public float getFloatValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Float.valueOf(s);
            }
            catch (NumberFormatException e) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, null);
                }
            }
        }
        return 0.0f;
    }
    
    public int getIntegerValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Integer.valueOf(s);
            }
            catch (NumberFormatException e) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be an integer", parser, null);
                }
            }
        }
        return 0;
    }
    
    public long getLongValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Long.valueOf(s);
            }
            catch (NumberFormatException e) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a long integer", parser, null);
                }
            }
        }
        return 0L;
    }
    
    public String getRequiredAttributeValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s == null && strict) {
            throw new XmlPullParserException("Missing required value for attribute '" + attribute + "'", parser, null);
        }
        return s;
    }
    
    public short getShortValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Short.valueOf(s);
            }
            catch (NumberFormatException e) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a short integer", parser, null);
                }
            }
        }
        return 0;
    }
    
    public String getTrimmedValue(String s) {
        if (s != null) {
            s = s.trim();
        }
        return s;
    }
    
    private Expression parseExpression(final String tagName, final XmlPullParser parser, final boolean strict, final String encoding) throws IOException, XmlPullParserException {
        final Expression expression = new Expression();
        expression.setModelEncoding(encoding);
        final Set parsed = new HashSet();
        while (parser.nextTag() == 2) {
            if (parser.getName().equals("syntax")) {
                if (parsed.contains("syntax")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("syntax");
                expression.setSyntax(this.getTrimmedValue(parser.nextText()));
            }
            else if (parser.getName().equals("description")) {
                if (parsed.contains("description")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("description");
                expression.setDescription(this.getTrimmedValue(parser.nextText()));
            }
            else if (parser.getName().equals("configuration")) {
                if (parsed.contains("configuration")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("configuration");
                expression.setConfiguration(this.getTrimmedValue(parser.nextText()));
            }
            else if (parser.getName().equals("cliOptions")) {
                if (parsed.contains("cliOptions")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("cliOptions");
                while (parser.nextTag() == 2) {
                    if (parser.getName().equals("cliOption")) {
                        String key = null;
                        String value = null;
                        while (parser.nextTag() == 2) {
                            if (parser.getName().equals("key")) {
                                key = parser.nextText();
                            }
                            else if (parser.getName().equals("value")) {
                                value = parser.nextText().trim();
                            }
                            else {
                                parser.nextText();
                            }
                        }
                        expression.addCliOption(key, value);
                    }
                    parser.next();
                }
            }
            else if (parser.getName().equals("apiMethods")) {
                if (parsed.contains("apiMethods")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("apiMethods");
                while (parser.nextTag() == 2) {
                    if (parser.getName().equals("apiMethod")) {
                        String key = null;
                        String value = null;
                        while (parser.nextTag() == 2) {
                            if (parser.getName().equals("key")) {
                                key = parser.nextText();
                            }
                            else if (parser.getName().equals("value")) {
                                value = parser.nextText().trim();
                            }
                            else {
                                parser.nextText();
                            }
                        }
                        expression.addApiMethod(key, value);
                    }
                    parser.next();
                }
            }
            else if (parser.getName().equals("deprecation")) {
                if (parsed.contains("deprecation")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("deprecation");
                expression.setDeprecation(this.getTrimmedValue(parser.nextText()));
            }
            else if (parser.getName().equals("ban")) {
                if (parsed.contains("ban")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("ban");
                expression.setBan(this.getTrimmedValue(parser.nextText()));
            }
            else if (parser.getName().equals("editable")) {
                if (parsed.contains("editable")) {
                    throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                }
                parsed.add("editable");
                expression.setEditable(this.getBooleanValue(this.getTrimmedValue(parser.nextText()), "editable", parser));
            }
            else {
                if (strict) {
                    throw new XmlPullParserException("Unrecognised tag: '" + parser.getName() + "'", parser, null);
                }
                continue;
            }
        }
        return expression;
    }
    
    private ExpressionDocumentation parseExpressionDocumentation(final String tagName, final XmlPullParser parser, final boolean strict, final String encoding) throws IOException, XmlPullParserException {
        final ExpressionDocumentation expressionDocumentation = new ExpressionDocumentation();
        expressionDocumentation.setModelEncoding(encoding);
        final Set parsed = new HashSet();
        int eventType = parser.getEventType();
        boolean foundRoot = false;
        while (eventType != 1) {
            if (eventType == 2) {
                if (parser.getName().equals(tagName)) {
                    foundRoot = true;
                }
                else if (parser.getName().equals("expressions")) {
                    if (parsed.contains("expressions")) {
                        throw new XmlPullParserException("Duplicated tag: '" + parser.getName() + "'", parser, null);
                    }
                    parsed.add("expressions");
                    final List expressions = new ArrayList();
                    expressionDocumentation.setExpressions(expressions);
                    while (parser.nextTag() == 2) {
                        if (parser.getName().equals("expression")) {
                            expressions.add(this.parseExpression("expression", parser, strict, encoding));
                        }
                        else {
                            parser.nextText();
                        }
                    }
                }
                else if (foundRoot && strict) {
                    throw new XmlPullParserException("Unrecognised tag: '" + parser.getName() + "'", parser, null);
                }
            }
            eventType = parser.next();
        }
        return expressionDocumentation;
    }
    
    public ExpressionDocumentation read(final Reader reader, final boolean strict) throws IOException, XmlPullParserException {
        final XmlPullParser parser = new MXParser();
        parser.setInput(reader);
        if (this.addDefaultEntities) {
            parser.defineEntityReplacementText("nbsp", " ");
            parser.defineEntityReplacementText("iexcl", "¡");
            parser.defineEntityReplacementText("cent", "¢");
            parser.defineEntityReplacementText("pound", "£");
            parser.defineEntityReplacementText("curren", "¤");
            parser.defineEntityReplacementText("yen", "¥");
            parser.defineEntityReplacementText("brvbar", "¦");
            parser.defineEntityReplacementText("sect", "§");
            parser.defineEntityReplacementText("uml", "¨");
            parser.defineEntityReplacementText("copy", "©");
            parser.defineEntityReplacementText("ordf", "ª");
            parser.defineEntityReplacementText("laquo", "«");
            parser.defineEntityReplacementText("not", "¬");
            parser.defineEntityReplacementText("shy", "\u00ad");
            parser.defineEntityReplacementText("reg", "®");
            parser.defineEntityReplacementText("macr", "¯");
            parser.defineEntityReplacementText("deg", "°");
            parser.defineEntityReplacementText("plusmn", "±");
            parser.defineEntityReplacementText("sup2", "²");
            parser.defineEntityReplacementText("sup3", "³");
            parser.defineEntityReplacementText("acute", "´");
            parser.defineEntityReplacementText("micro", "µ");
            parser.defineEntityReplacementText("para", "¶");
            parser.defineEntityReplacementText("middot", "·");
            parser.defineEntityReplacementText("cedil", "¸");
            parser.defineEntityReplacementText("sup1", "¹");
            parser.defineEntityReplacementText("ordm", "º");
            parser.defineEntityReplacementText("raquo", "»");
            parser.defineEntityReplacementText("frac14", "¼");
            parser.defineEntityReplacementText("frac12", "½");
            parser.defineEntityReplacementText("frac34", "¾");
            parser.defineEntityReplacementText("iquest", "¿");
            parser.defineEntityReplacementText("Agrave", "\u00c0");
            parser.defineEntityReplacementText("Aacute", "\u00c1");
            parser.defineEntityReplacementText("Acirc", "\u00c2");
            parser.defineEntityReplacementText("Atilde", "\u00c3");
            parser.defineEntityReplacementText("Auml", "\u00c4");
            parser.defineEntityReplacementText("Aring", "\u00c5");
            parser.defineEntityReplacementText("AElig", "\u00c6");
            parser.defineEntityReplacementText("Ccedil", "\u00c7");
            parser.defineEntityReplacementText("Egrave", "\u00c8");
            parser.defineEntityReplacementText("Eacute", "\u00c9");
            parser.defineEntityReplacementText("Ecirc", "\u00ca");
            parser.defineEntityReplacementText("Euml", "\u00cb");
            parser.defineEntityReplacementText("Igrave", "\u00cc");
            parser.defineEntityReplacementText("Iacute", "\u00cd");
            parser.defineEntityReplacementText("Icirc", "\u00ce");
            parser.defineEntityReplacementText("Iuml", "\u00cf");
            parser.defineEntityReplacementText("ETH", "\u00d0");
            parser.defineEntityReplacementText("Ntilde", "\u00d1");
            parser.defineEntityReplacementText("Ograve", "\u00d2");
            parser.defineEntityReplacementText("Oacute", "\u00d3");
            parser.defineEntityReplacementText("Ocirc", "\u00d4");
            parser.defineEntityReplacementText("Otilde", "\u00d5");
            parser.defineEntityReplacementText("Ouml", "\u00d6");
            parser.defineEntityReplacementText("times", "\u00d7");
            parser.defineEntityReplacementText("Oslash", "\u00d8");
            parser.defineEntityReplacementText("Ugrave", "\u00d9");
            parser.defineEntityReplacementText("Uacute", "\u00da");
            parser.defineEntityReplacementText("Ucirc", "\u00db");
            parser.defineEntityReplacementText("Uuml", "\u00dc");
            parser.defineEntityReplacementText("Yacute", "\u00dd");
            parser.defineEntityReplacementText("THORN", "\u00de");
            parser.defineEntityReplacementText("szlig", "\u00df");
            parser.defineEntityReplacementText("agrave", "\u00e0");
            parser.defineEntityReplacementText("aacute", "\u00e1");
            parser.defineEntityReplacementText("acirc", "\u00e2");
            parser.defineEntityReplacementText("atilde", "\u00e3");
            parser.defineEntityReplacementText("auml", "\u00e4");
            parser.defineEntityReplacementText("aring", "\u00e5");
            parser.defineEntityReplacementText("aelig", "\u00e6");
            parser.defineEntityReplacementText("ccedil", "\u00e7");
            parser.defineEntityReplacementText("egrave", "\u00e8");
            parser.defineEntityReplacementText("eacute", "\u00e9");
            parser.defineEntityReplacementText("ecirc", "\u00ea");
            parser.defineEntityReplacementText("euml", "\u00eb");
            parser.defineEntityReplacementText("igrave", "\u00ec");
            parser.defineEntityReplacementText("iacute", "\u00ed");
            parser.defineEntityReplacementText("icirc", "\u00ee");
            parser.defineEntityReplacementText("iuml", "\u00ef");
            parser.defineEntityReplacementText("eth", "\u00f0");
            parser.defineEntityReplacementText("ntilde", "\u00f1");
            parser.defineEntityReplacementText("ograve", "\u00f2");
            parser.defineEntityReplacementText("oacute", "\u00f3");
            parser.defineEntityReplacementText("ocirc", "\u00f4");
            parser.defineEntityReplacementText("otilde", "\u00f5");
            parser.defineEntityReplacementText("ouml", "\u00f6");
            parser.defineEntityReplacementText("divide", "\u00f7");
            parser.defineEntityReplacementText("oslash", "\u00f8");
            parser.defineEntityReplacementText("ugrave", "\u00f9");
            parser.defineEntityReplacementText("uacute", "\u00fa");
            parser.defineEntityReplacementText("ucirc", "\u00fb");
            parser.defineEntityReplacementText("uuml", "\u00fc");
            parser.defineEntityReplacementText("yacute", "\u00fd");
            parser.defineEntityReplacementText("thorn", "\u00fe");
            parser.defineEntityReplacementText("yuml", "\u00ff");
            parser.defineEntityReplacementText("OElig", "\u0152");
            parser.defineEntityReplacementText("oelig", "\u0153");
            parser.defineEntityReplacementText("Scaron", "\u0160");
            parser.defineEntityReplacementText("scaron", "\u0161");
            parser.defineEntityReplacementText("Yuml", "\u0178");
            parser.defineEntityReplacementText("circ", "\u02c6");
            parser.defineEntityReplacementText("tilde", "\u02dc");
            parser.defineEntityReplacementText("ensp", "\u2002");
            parser.defineEntityReplacementText("emsp", "\u2003");
            parser.defineEntityReplacementText("thinsp", "\u2009");
            parser.defineEntityReplacementText("zwnj", "\u200c");
            parser.defineEntityReplacementText("zwj", "\u200d");
            parser.defineEntityReplacementText("lrm", "\u200e");
            parser.defineEntityReplacementText("rlm", "\u200f");
            parser.defineEntityReplacementText("ndash", "\u2013");
            parser.defineEntityReplacementText("mdash", "\u2014");
            parser.defineEntityReplacementText("lsquo", "\u2018");
            parser.defineEntityReplacementText("rsquo", "\u2019");
            parser.defineEntityReplacementText("sbquo", "\u201a");
            parser.defineEntityReplacementText("ldquo", "\u201c");
            parser.defineEntityReplacementText("rdquo", "\u201d");
            parser.defineEntityReplacementText("bdquo", "\u201e");
            parser.defineEntityReplacementText("dagger", "\u2020");
            parser.defineEntityReplacementText("Dagger", "\u2021");
            parser.defineEntityReplacementText("permil", "\u2030");
            parser.defineEntityReplacementText("lsaquo", "\u2039");
            parser.defineEntityReplacementText("rsaquo", "\u203a");
            parser.defineEntityReplacementText("euro", "\u20ac");
            parser.defineEntityReplacementText("fnof", "\u0192");
            parser.defineEntityReplacementText("Alpha", "\u0391");
            parser.defineEntityReplacementText("Beta", "\u0392");
            parser.defineEntityReplacementText("Gamma", "\u0393");
            parser.defineEntityReplacementText("Delta", "\u0394");
            parser.defineEntityReplacementText("Epsilon", "\u0395");
            parser.defineEntityReplacementText("Zeta", "\u0396");
            parser.defineEntityReplacementText("Eta", "\u0397");
            parser.defineEntityReplacementText("Theta", "\u0398");
            parser.defineEntityReplacementText("Iota", "\u0399");
            parser.defineEntityReplacementText("Kappa", "\u039a");
            parser.defineEntityReplacementText("Lambda", "\u039b");
            parser.defineEntityReplacementText("Mu", "\u039c");
            parser.defineEntityReplacementText("Nu", "\u039d");
            parser.defineEntityReplacementText("Xi", "\u039e");
            parser.defineEntityReplacementText("Omicron", "\u039f");
            parser.defineEntityReplacementText("Pi", "\u03a0");
            parser.defineEntityReplacementText("Rho", "\u03a1");
            parser.defineEntityReplacementText("Sigma", "\u03a3");
            parser.defineEntityReplacementText("Tau", "\u03a4");
            parser.defineEntityReplacementText("Upsilon", "\u03a5");
            parser.defineEntityReplacementText("Phi", "\u03a6");
            parser.defineEntityReplacementText("Chi", "\u03a7");
            parser.defineEntityReplacementText("Psi", "\u03a8");
            parser.defineEntityReplacementText("Omega", "\u03a9");
            parser.defineEntityReplacementText("alpha", "\u03b1");
            parser.defineEntityReplacementText("beta", "\u03b2");
            parser.defineEntityReplacementText("gamma", "\u03b3");
            parser.defineEntityReplacementText("delta", "\u03b4");
            parser.defineEntityReplacementText("epsilon", "\u03b5");
            parser.defineEntityReplacementText("zeta", "\u03b6");
            parser.defineEntityReplacementText("eta", "\u03b7");
            parser.defineEntityReplacementText("theta", "\u03b8");
            parser.defineEntityReplacementText("iota", "\u03b9");
            parser.defineEntityReplacementText("kappa", "\u03ba");
            parser.defineEntityReplacementText("lambda", "\u03bb");
            parser.defineEntityReplacementText("mu", "\u03bc");
            parser.defineEntityReplacementText("nu", "\u03bd");
            parser.defineEntityReplacementText("xi", "\u03be");
            parser.defineEntityReplacementText("omicron", "\u03bf");
            parser.defineEntityReplacementText("pi", "\u03c0");
            parser.defineEntityReplacementText("rho", "\u03c1");
            parser.defineEntityReplacementText("sigmaf", "\u03c2");
            parser.defineEntityReplacementText("sigma", "\u03c3");
            parser.defineEntityReplacementText("tau", "\u03c4");
            parser.defineEntityReplacementText("upsilon", "\u03c5");
            parser.defineEntityReplacementText("phi", "\u03c6");
            parser.defineEntityReplacementText("chi", "\u03c7");
            parser.defineEntityReplacementText("psi", "\u03c8");
            parser.defineEntityReplacementText("omega", "\u03c9");
            parser.defineEntityReplacementText("thetasym", "\u03d1");
            parser.defineEntityReplacementText("upsih", "\u03d2");
            parser.defineEntityReplacementText("piv", "\u03d6");
            parser.defineEntityReplacementText("bull", "\u2022");
            parser.defineEntityReplacementText("hellip", "\u2026");
            parser.defineEntityReplacementText("prime", "\u2032");
            parser.defineEntityReplacementText("Prime", "\u2033");
            parser.defineEntityReplacementText("oline", "\u203e");
            parser.defineEntityReplacementText("frasl", "\u2044");
            parser.defineEntityReplacementText("weierp", "\u2118");
            parser.defineEntityReplacementText("image", "\u2111");
            parser.defineEntityReplacementText("real", "\u211c");
            parser.defineEntityReplacementText("trade", "\u2122");
            parser.defineEntityReplacementText("alefsym", "\u2135");
            parser.defineEntityReplacementText("larr", "\u2190");
            parser.defineEntityReplacementText("uarr", "\u2191");
            parser.defineEntityReplacementText("rarr", "\u2192");
            parser.defineEntityReplacementText("darr", "\u2193");
            parser.defineEntityReplacementText("harr", "\u2194");
            parser.defineEntityReplacementText("crarr", "\u21b5");
            parser.defineEntityReplacementText("lArr", "\u21d0");
            parser.defineEntityReplacementText("uArr", "\u21d1");
            parser.defineEntityReplacementText("rArr", "\u21d2");
            parser.defineEntityReplacementText("dArr", "\u21d3");
            parser.defineEntityReplacementText("hArr", "\u21d4");
            parser.defineEntityReplacementText("forall", "\u2200");
            parser.defineEntityReplacementText("part", "\u2202");
            parser.defineEntityReplacementText("exist", "\u2203");
            parser.defineEntityReplacementText("empty", "\u2205");
            parser.defineEntityReplacementText("nabla", "\u2207");
            parser.defineEntityReplacementText("isin", "\u2208");
            parser.defineEntityReplacementText("notin", "\u2209");
            parser.defineEntityReplacementText("ni", "\u220b");
            parser.defineEntityReplacementText("prod", "\u220f");
            parser.defineEntityReplacementText("sum", "\u2211");
            parser.defineEntityReplacementText("minus", "\u2212");
            parser.defineEntityReplacementText("lowast", "\u2217");
            parser.defineEntityReplacementText("radic", "\u221a");
            parser.defineEntityReplacementText("prop", "\u221d");
            parser.defineEntityReplacementText("infin", "\u221e");
            parser.defineEntityReplacementText("ang", "\u2220");
            parser.defineEntityReplacementText("and", "\u2227");
            parser.defineEntityReplacementText("or", "\u2228");
            parser.defineEntityReplacementText("cap", "\u2229");
            parser.defineEntityReplacementText("cup", "\u222a");
            parser.defineEntityReplacementText("int", "\u222b");
            parser.defineEntityReplacementText("there4", "\u2234");
            parser.defineEntityReplacementText("sim", "\u223c");
            parser.defineEntityReplacementText("cong", "\u2245");
            parser.defineEntityReplacementText("asymp", "\u2248");
            parser.defineEntityReplacementText("ne", "\u2260");
            parser.defineEntityReplacementText("equiv", "\u2261");
            parser.defineEntityReplacementText("le", "\u2264");
            parser.defineEntityReplacementText("ge", "\u2265");
            parser.defineEntityReplacementText("sub", "\u2282");
            parser.defineEntityReplacementText("sup", "\u2283");
            parser.defineEntityReplacementText("nsub", "\u2284");
            parser.defineEntityReplacementText("sube", "\u2286");
            parser.defineEntityReplacementText("supe", "\u2287");
            parser.defineEntityReplacementText("oplus", "\u2295");
            parser.defineEntityReplacementText("otimes", "\u2297");
            parser.defineEntityReplacementText("perp", "\u22a5");
            parser.defineEntityReplacementText("sdot", "\u22c5");
            parser.defineEntityReplacementText("lceil", "\u2308");
            parser.defineEntityReplacementText("rceil", "\u2309");
            parser.defineEntityReplacementText("lfloor", "\u230a");
            parser.defineEntityReplacementText("rfloor", "\u230b");
            parser.defineEntityReplacementText("lang", "\u2329");
            parser.defineEntityReplacementText("rang", "\u232a");
            parser.defineEntityReplacementText("loz", "\u25ca");
            parser.defineEntityReplacementText("spades", "\u2660");
            parser.defineEntityReplacementText("clubs", "\u2663");
            parser.defineEntityReplacementText("hearts", "\u2665");
            parser.defineEntityReplacementText("diams", "\u2666");
        }
        parser.next();
        final String encoding = parser.getInputEncoding();
        return this.parseExpressionDocumentation("paramdoc", parser, strict, encoding);
    }
    
    public ExpressionDocumentation read(final Reader reader) throws IOException, XmlPullParserException {
        return this.read(reader, true);
    }
    
    public void setAddDefaultEntities(final boolean addDefaultEntities) {
        this.addDefaultEntities = addDefaultEntities;
    }
}
