// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.BitSet;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class HeaderElement extends NameValuePair
{
    private static final Log LOG;
    private static final BitSet SEPARATORS;
    private static final BitSet TOKEN_CHAR;
    private static final BitSet UNSAFE_CHAR;
    private NameValuePair[] parameters;
    
    public HeaderElement() {
        this(null, null, null);
    }
    
    public HeaderElement(final String name, final String value) {
        this(name, value, null);
    }
    
    public HeaderElement(final String name, final String value, final NameValuePair[] parameters) {
        super(name, value);
        this.parameters = null;
        this.setParameters(parameters);
    }
    
    public NameValuePair[] getParameters() {
        return this.parameters;
    }
    
    protected void setParameters(final NameValuePair[] pairs) {
        this.parameters = pairs;
    }
    
    public static final HeaderElement[] parse(final String headerValue) throws HttpException {
        HeaderElement.LOG.trace("enter HeaderElement.parse(String)");
        if (headerValue == null) {
            return null;
        }
        final Vector elements = new Vector();
        final StringTokenizer tokenizer = new StringTokenizer(headerValue.trim(), ",");
        while (tokenizer.countTokens() > 0) {
            String nextToken = tokenizer.nextToken();
            try {
                while (hasOddNumberOfQuotationMarks(nextToken)) {
                    nextToken = nextToken + "," + tokenizer.nextToken();
                }
            }
            catch (NoSuchElementException exception) {
                throw new HttpException("Bad header format: wrong number of quotation marks");
            }
            try {
                if (tokenizer.hasMoreTokens()) {
                    final String s = nextToken.toLowerCase();
                    if (s.endsWith("mon") || s.endsWith("tue") || s.endsWith("wed") || s.endsWith("thu") || s.endsWith("fri") || s.endsWith("sat") || s.endsWith("sun") || s.endsWith("monday") || s.endsWith("tuesday") || s.endsWith("wednesday") || s.endsWith("thursday") || s.endsWith("friday") || s.endsWith("saturday") || s.endsWith("sunday")) {
                        nextToken = nextToken + "," + tokenizer.nextToken();
                    }
                }
            }
            catch (NoSuchElementException exception) {
                throw new HttpException("Bad header format: parsing with wrong header elements");
            }
            String tmp = nextToken.trim();
            if (!tmp.endsWith(";")) {
                tmp += ";";
            }
            final char[] header = tmp.toCharArray();
            boolean inAString = false;
            int startPos = 0;
            final HeaderElement element = new HeaderElement();
            final Vector paramlist = new Vector();
            for (int i = 0; i < header.length; ++i) {
                if (header[i] == ';' && !inAString) {
                    final NameValuePair pair = parsePair(header, startPos, i);
                    if (pair == null) {
                        throw new HttpException("Bad header format: empty name/value pair in" + nextToken);
                    }
                    if (startPos == 0) {
                        element.setName(pair.getName());
                        element.setValue(pair.getValue());
                    }
                    else {
                        paramlist.addElement(pair);
                    }
                    startPos = i + 1;
                }
                else if (header[i] == '\"' && (!inAString || i <= 0 || header[i - 1] != '\\')) {
                    inAString = !inAString;
                }
            }
            if (paramlist.size() > 0) {
                final NameValuePair[] tmp2 = new NameValuePair[paramlist.size()];
                paramlist.copyInto(tmp2);
                element.setParameters(tmp2);
                paramlist.removeAllElements();
            }
            elements.addElement(element);
        }
        final HeaderElement[] headerElements = new HeaderElement[elements.size()];
        elements.copyInto(headerElements);
        return headerElements;
    }
    
    private static final boolean hasOddNumberOfQuotationMarks(final String string) {
        boolean odd = false;
        int start = -1;
        while ((start = string.indexOf(34, start + 1)) != -1) {
            odd = !odd;
        }
        return odd;
    }
    
    private static final NameValuePair parsePair(final char[] header, final int start, final int end) {
        HeaderElement.LOG.trace("enter HeaderElement.parsePair(char[], int, int)");
        NameValuePair pair = null;
        String name = new String(header, start, end - start).trim();
        String value = null;
        final int index = name.indexOf("=");
        if (index >= 0) {
            if (index + 1 < name.length()) {
                value = name.substring(index + 1).trim();
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
            }
            name = name.substring(0, index).trim();
        }
        pair = new NameValuePair(name, value);
        return pair;
    }
    
    public NameValuePair getParameterByName(final String name) {
        if (name == null) {
            throw new NullPointerException("Name is null");
        }
        NameValuePair found = null;
        final NameValuePair[] parameters = this.getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; ++i) {
                final NameValuePair current = parameters[i];
                if (current.getName().equalsIgnoreCase(name)) {
                    found = current;
                    break;
                }
            }
        }
        return found;
    }
    
    static {
        LOG = LogFactory.getLog(HeaderElement.class);
        SEPARATORS = new BitSet(128);
        TOKEN_CHAR = new BitSet(128);
        UNSAFE_CHAR = new BitSet(128);
        HeaderElement.SEPARATORS.set(40);
        HeaderElement.SEPARATORS.set(41);
        HeaderElement.SEPARATORS.set(60);
        HeaderElement.SEPARATORS.set(62);
        HeaderElement.SEPARATORS.set(64);
        HeaderElement.SEPARATORS.set(44);
        HeaderElement.SEPARATORS.set(59);
        HeaderElement.SEPARATORS.set(58);
        HeaderElement.SEPARATORS.set(92);
        HeaderElement.SEPARATORS.set(34);
        HeaderElement.SEPARATORS.set(47);
        HeaderElement.SEPARATORS.set(91);
        HeaderElement.SEPARATORS.set(93);
        HeaderElement.SEPARATORS.set(63);
        HeaderElement.SEPARATORS.set(61);
        HeaderElement.SEPARATORS.set(123);
        HeaderElement.SEPARATORS.set(125);
        HeaderElement.SEPARATORS.set(32);
        HeaderElement.SEPARATORS.set(9);
        for (int ch = 32; ch < 127; ++ch) {
            HeaderElement.TOKEN_CHAR.set(ch);
        }
        HeaderElement.TOKEN_CHAR.xor(HeaderElement.SEPARATORS);
        for (int ch2 = 0; ch2 < 32; ++ch2) {
            HeaderElement.UNSAFE_CHAR.set(ch2);
        }
        HeaderElement.UNSAFE_CHAR.set(32);
        HeaderElement.UNSAFE_CHAR.set(60);
        HeaderElement.UNSAFE_CHAR.set(62);
        HeaderElement.UNSAFE_CHAR.set(34);
        HeaderElement.UNSAFE_CHAR.set(123);
        HeaderElement.UNSAFE_CHAR.set(125);
        HeaderElement.UNSAFE_CHAR.set(124);
        HeaderElement.UNSAFE_CHAR.set(92);
        HeaderElement.UNSAFE_CHAR.set(94);
        HeaderElement.UNSAFE_CHAR.set(126);
        HeaderElement.UNSAFE_CHAR.set(91);
        HeaderElement.UNSAFE_CHAR.set(93);
        HeaderElement.UNSAFE_CHAR.set(96);
        HeaderElement.UNSAFE_CHAR.set(127);
    }
}
