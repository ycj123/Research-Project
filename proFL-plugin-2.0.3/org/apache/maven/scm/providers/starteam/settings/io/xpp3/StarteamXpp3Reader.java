// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.starteam.settings.io.xpp3;

import java.util.HashSet;
import org.codehaus.plexus.util.ReaderFactory;
import java.io.InputStream;
import org.codehaus.plexus.util.xml.pull.MXParser;
import org.codehaus.plexus.util.xml.pull.EntityReplacementMap;
import org.apache.maven.scm.providers.starteam.settings.Settings;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.util.Set;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;

public class StarteamXpp3Reader
{
    private boolean addDefaultEntities;
    
    public StarteamXpp3Reader() {
        this.addDefaultEntities = true;
    }
    
    private boolean checkFieldWithDuplicate(final XmlPullParser parser, final String tagName, final String alias, final Set parsed) throws XmlPullParserException {
        if (!parser.getName().equals(tagName) && !parser.getName().equals(alias)) {
            return false;
        }
        if (!parsed.add(tagName)) {
            throw new XmlPullParserException("Duplicated tag: '" + tagName + "'", parser, null);
        }
        return true;
    }
    
    private void checkUnknownAttribute(final XmlPullParser parser, final String attribute, final String tagName, final boolean strict) throws XmlPullParserException, IOException {
        if (strict) {
            throw new XmlPullParserException("Unknown attribute '" + attribute + "' for tag '" + tagName + "'", parser, null);
        }
    }
    
    private void checkUnknownElement(final XmlPullParser parser, final boolean strict) throws XmlPullParserException, IOException {
        if (strict) {
            throw new XmlPullParserException("Unrecognised tag: '" + parser.getName() + "'", parser, null);
        }
        int unrecognizedTagCount = 1;
        while (unrecognizedTagCount > 0) {
            final int eventType = parser.next();
            if (eventType == 2) {
                ++unrecognizedTagCount;
            }
            else {
                if (eventType != 3) {
                    continue;
                }
                --unrecognizedTagCount;
            }
        }
    }
    
    public boolean getAddDefaultEntities() {
        return this.addDefaultEntities;
    }
    
    private boolean getBooleanValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        return this.getBooleanValue(s, attribute, parser, null);
    }
    
    private boolean getBooleanValue(final String s, final String attribute, final XmlPullParser parser, final String defaultValue) throws XmlPullParserException {
        if (s != null && s.length() != 0) {
            return Boolean.valueOf(s);
        }
        return defaultValue != null && Boolean.valueOf(defaultValue);
    }
    
    private byte getByteValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Byte.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a byte", parser, nfe);
                }
            }
        }
        return 0;
    }
    
    private char getCharacterValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        if (s != null) {
            return s.charAt(0);
        }
        return '\0';
    }
    
    private Date getDateValue(final String s, final String attribute, final XmlPullParser parser) throws XmlPullParserException {
        return this.getDateValue(s, attribute, null, parser);
    }
    
    private Date getDateValue(final String s, final String attribute, final String dateFormat, final XmlPullParser parser) throws XmlPullParserException {
        if (s != null) {
            String effectiveDateFormat;
            if ((effectiveDateFormat = dateFormat) == null) {
                effectiveDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
            }
            if ("long".equals(effectiveDateFormat)) {
                try {
                    return new Date(Long.parseLong(s));
                }
                catch (NumberFormatException e) {
                    throw new XmlPullParserException(e.getMessage(), parser, e);
                }
            }
            try {
                final DateFormat dateParser = new SimpleDateFormat(effectiveDateFormat, Locale.US);
                return dateParser.parse(s);
            }
            catch (ParseException e2) {
                throw new XmlPullParserException(e2.getMessage(), parser, e2);
            }
        }
        return null;
    }
    
    private double getDoubleValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Double.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, nfe);
                }
            }
        }
        return 0.0;
    }
    
    private float getFloatValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Float.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, nfe);
                }
            }
        }
        return 0.0f;
    }
    
    private int getIntegerValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Integer.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be an integer", parser, nfe);
                }
            }
        }
        return 0;
    }
    
    private long getLongValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Long.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a long integer", parser, nfe);
                }
            }
        }
        return 0L;
    }
    
    private String getRequiredAttributeValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s == null && strict) {
            throw new XmlPullParserException("Missing required value for attribute '" + attribute + "'", parser, null);
        }
        return s;
    }
    
    private short getShortValue(final String s, final String attribute, final XmlPullParser parser, final boolean strict) throws XmlPullParserException {
        if (s != null) {
            try {
                return Short.valueOf(s);
            }
            catch (NumberFormatException nfe) {
                if (strict) {
                    throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a short integer", parser, nfe);
                }
            }
        }
        return 0;
    }
    
    private String getTrimmedValue(String s) {
        if (s != null) {
            s = s.trim();
        }
        return s;
    }
    
    private int nextTag(final XmlPullParser parser) throws IOException, XmlPullParserException {
        int eventType = parser.next();
        if (eventType == 4) {
            eventType = parser.next();
        }
        if (eventType != 2 && eventType != 3) {
            throw new XmlPullParserException("expected START_TAG or END_TAG not " + XmlPullParser.TYPES[eventType], parser, null);
        }
        return eventType;
    }
    
    public Settings read(final Reader reader, final boolean strict) throws IOException, XmlPullParserException {
        final XmlPullParser parser = this.addDefaultEntities ? new MXParser(EntityReplacementMap.defaultEntityReplacementMap) : new MXParser();
        parser.setInput(reader);
        return this.read(parser, strict);
    }
    
    public Settings read(final Reader reader) throws IOException, XmlPullParserException {
        return this.read(reader, true);
    }
    
    public Settings read(final InputStream in, final boolean strict) throws IOException, XmlPullParserException {
        return this.read(ReaderFactory.newXmlReader(in), strict);
    }
    
    public Settings read(final InputStream in) throws IOException, XmlPullParserException {
        return this.read(ReaderFactory.newXmlReader(in));
    }
    
    private Settings parseSettings(final XmlPullParser parser, final boolean strict) throws IOException, XmlPullParserException {
        final String tagName = parser.getName();
        final Settings settings = new Settings();
        for (int i = parser.getAttributeCount() - 1; i >= 0; --i) {
            final String name = parser.getAttributeName(i);
            final String value = parser.getAttributeValue(i);
            if (name.indexOf(58) < 0) {
                if (!"xmlns".equals(name)) {
                    this.checkUnknownAttribute(parser, name, tagName, strict);
                }
            }
        }
        final Set parsed = new HashSet();
        while ((strict ? parser.nextTag() : this.nextTag(parser)) == 2) {
            if (this.checkFieldWithDuplicate(parser, "compressionEnable", null, parsed)) {
                settings.setCompressionEnable(this.getBooleanValue(this.getTrimmedValue(parser.nextText()), "compressionEnable", parser, "false"));
            }
            else if (this.checkFieldWithDuplicate(parser, "eol", null, parsed)) {
                settings.setEol(this.getTrimmedValue(parser.nextText()));
            }
            else {
                this.checkUnknownElement(parser, strict);
            }
        }
        return settings;
    }
    
    private Settings read(final XmlPullParser parser, final boolean strict) throws IOException, XmlPullParserException {
        int eventType = parser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                if (strict && !"starteam-settings".equals(parser.getName())) {
                    throw new XmlPullParserException("Expected root element 'starteam-settings' but found '" + parser.getName() + "'", parser, null);
                }
                final Settings settings = this.parseSettings(parser, strict);
                settings.setModelEncoding(parser.getInputEncoding());
                return settings;
            }
            else {
                eventType = parser.next();
            }
        }
        throw new XmlPullParserException("Expected root element 'starteam-settings' but found no element at all: invalid XML document", parser, null);
    }
    
    public void setAddDefaultEntities(final boolean addDefaultEntities) {
        this.addDefaultEntities = addDefaultEntities;
    }
}
