// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.apt;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.markup.TextMarkup;

public interface AptMarkup extends TextMarkup
{
    public static final char BACKSLASH = '\\';
    public static final char COLON = ':';
    public static final char COMMENT = '~';
    public static final char NUMBERING = '1';
    public static final char NUMBERING_LOWER_ALPHA_CHAR = 'a';
    public static final char NUMBERING_LOWER_ROMAN_CHAR = 'i';
    public static final char NUMBERING_UPPER_ALPHA_CHAR = 'A';
    public static final char NUMBERING_UPPER_ROMAN_CHAR = 'I';
    public static final char PAGE_BREAK = '\f';
    public static final char PERCENT = '%';
    public static final char TAB = '\t';
    public static final String ANCHOR_END_MARKUP = String.valueOf('}');
    public static final String ANCHOR_START_MARKUP = String.valueOf('{');
    public static final String BOLD_END_MARKUP = StringUtils.repeat(String.valueOf('>'), 2);
    public static final String BOLD_START_MARKUP = StringUtils.repeat(String.valueOf('<'), 2);
    public static final String BOXED_VERBATIM_START_MARKUP = String.valueOf('+') + StringUtils.repeat(String.valueOf('-'), 6) + String.valueOf('+');
    public static final String HEADER_START_MARKUP = String.valueOf(' ') + StringUtils.repeat(String.valueOf('-'), 5);
    public static final String HORIZONTAL_RULE_MARKUP = StringUtils.repeat(String.valueOf('='), 8);
    public static final String ITALIC_END_MARKUP = String.valueOf('>');
    public static final String ITALIC_START_MARKUP = String.valueOf('<');
    public static final String LINK_END_MARKUP = StringUtils.repeat(String.valueOf('}'), 2);
    public static final String LINK_START_1_MARKUP = StringUtils.repeat(String.valueOf('{'), 3);
    public static final String LINK_START_2_MARKUP = String.valueOf('}');
    public static final String LIST_END_MARKUP = String.valueOf('[') + String.valueOf(']');
    public static final String LIST_START_MARKUP = String.valueOf('*');
    public static final String MONOSPACED_END_MARKUP = StringUtils.repeat(String.valueOf('>'), 3);
    public static final String MONOSPACED_START_MARKUP = StringUtils.repeat(String.valueOf('<'), 3);
    public static final String NON_BOXED_VERBATIM_START_MARKUP = StringUtils.repeat(String.valueOf('-'), 6);
    public static final String NON_BREAKING_SPACE_MARKUP = String.valueOf('\\') + String.valueOf(' ');
    public static final String PAGE_BREAK_MARKUP = String.valueOf('\f');
    public static final String SECTION_TITLE_START_MARKUP = String.valueOf('*');
    public static final String TABLE_CELL_SEPARATOR_MARKUP = String.valueOf('|');
    public static final String TABLE_COL_CENTERED_ALIGNED_MARKUP = StringUtils.repeat(String.valueOf('-'), 2) + String.valueOf('*');
    public static final String TABLE_COL_LEFT_ALIGNED_MARKUP = StringUtils.repeat(String.valueOf('-'), 2) + String.valueOf('+');
    public static final String TABLE_COL_RIGHT_ALIGNED_MARKUP = StringUtils.repeat(String.valueOf('-'), 2) + String.valueOf(':');
    public static final String TABLE_ROW_SEPARATOR_MARKUP = String.valueOf('|');
    public static final String TABLE_ROW_START_MARKUP = String.valueOf('*') + StringUtils.repeat(String.valueOf('-'), 2);
    public static final String BOXED_VERBATIM_END_MARKUP = AptMarkup.BOXED_VERBATIM_START_MARKUP;
    public static final String NON_BOXED_VERBATIM_END_MARKUP = AptMarkup.NON_BOXED_VERBATIM_START_MARKUP;
}
