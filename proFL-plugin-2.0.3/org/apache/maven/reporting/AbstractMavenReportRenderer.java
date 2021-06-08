// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reporting;

import java.util.Collections;
import java.util.ArrayList;
import org.mudebug.prapr.reloc.commons.validator.EmailValidator;
import org.mudebug.prapr.reloc.commons.validator.UrlValidator;
import java.util.Properties;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.util.HtmlTools;
import org.apache.maven.doxia.sink.Sink;

public abstract class AbstractMavenReportRenderer implements MavenReportRenderer
{
    protected Sink sink;
    private int section;
    
    public AbstractMavenReportRenderer(final Sink sink) {
        this.sink = sink;
    }
    
    public void render() {
        this.sink.head();
        this.sink.title();
        this.text(this.getTitle());
        this.sink.title_();
        this.sink.head_();
        this.sink.body();
        this.renderBody();
        this.sink.body_();
        this.sink.flush();
        this.sink.close();
    }
    
    protected void startSection(final String name) {
        ++this.section;
        this.sink.anchor(HtmlTools.encodeId(name));
        this.sink.anchor_();
        switch (this.section) {
            case 1: {
                this.sink.section1();
                this.sink.sectionTitle1();
                break;
            }
            case 2: {
                this.sink.section2();
                this.sink.sectionTitle2();
                break;
            }
            case 3: {
                this.sink.section3();
                this.sink.sectionTitle3();
                break;
            }
            case 4: {
                this.sink.section4();
                this.sink.sectionTitle4();
                break;
            }
            case 5: {
                this.sink.section5();
                this.sink.sectionTitle5();
                break;
            }
        }
        this.text(name);
        switch (this.section) {
            case 1: {
                this.sink.sectionTitle1_();
                break;
            }
            case 2: {
                this.sink.sectionTitle2_();
                break;
            }
            case 3: {
                this.sink.sectionTitle3_();
                break;
            }
            case 4: {
                this.sink.sectionTitle4_();
                break;
            }
            case 5: {
                this.sink.sectionTitle5_();
                break;
            }
        }
    }
    
    protected void endSection() {
        switch (this.section) {
            case 1: {
                this.sink.section1_();
                break;
            }
            case 2: {
                this.sink.section2_();
                break;
            }
            case 3: {
                this.sink.section3_();
                break;
            }
            case 4: {
                this.sink.section4_();
                break;
            }
            case 5: {
                this.sink.section5_();
                break;
            }
        }
        --this.section;
        if (this.section < 0) {
            throw new IllegalStateException("Too many closing sections");
        }
    }
    
    protected void startTable() {
        this.sink.table();
    }
    
    protected void endTable() {
        this.sink.table_();
    }
    
    protected void tableHeaderCell(final String text) {
        this.sink.tableHeaderCell();
        this.text(text);
        this.sink.tableHeaderCell_();
    }
    
    protected void tableCell(final String text) {
        this.tableCell(text, false);
    }
    
    protected void tableCell(final String text, final boolean asHtml) {
        this.sink.tableCell();
        if (asHtml) {
            this.sink.rawText(text);
        }
        else {
            this.linkPatternedText(text);
        }
        this.sink.tableCell_();
    }
    
    protected void tableRow(final String[] content) {
        this.sink.tableRow();
        if (content != null) {
            for (int i = 0; i < content.length; ++i) {
                this.tableCell(content[i]);
            }
        }
        this.sink.tableRow_();
    }
    
    protected void tableHeader(final String[] content) {
        this.sink.tableRow();
        if (content != null) {
            for (int i = 0; i < content.length; ++i) {
                this.tableHeaderCell(content[i]);
            }
        }
        this.sink.tableRow_();
    }
    
    protected void tableCaption(final String caption) {
        this.sink.tableCaption();
        this.text(caption);
        this.sink.tableCaption_();
    }
    
    protected void paragraph(final String paragraph) {
        this.sink.paragraph();
        this.text(paragraph);
        this.sink.paragraph_();
    }
    
    protected void link(final String href, final String name) {
        this.sink.link(href);
        this.text(name);
        this.sink.link_();
    }
    
    protected void text(final String text) {
        if (StringUtils.isEmpty(text)) {
            this.sink.text("-");
        }
        else {
            this.sink.text(text);
        }
    }
    
    protected void verbatimText(final String text) {
        this.sink.verbatim(true);
        this.text(text);
        this.sink.verbatim_();
    }
    
    protected void verbatimLink(final String text, final String href) {
        if (StringUtils.isEmpty(href)) {
            this.verbatimText(text);
        }
        else {
            this.sink.verbatim(true);
            this.link(href, text);
            this.sink.verbatim_();
        }
    }
    
    protected void javaScript(final String jsCode) {
        this.sink.rawText("<script type=\"text/javascript\">\n" + jsCode + "</script>");
    }
    
    public void linkPatternedText(final String text) {
        if (StringUtils.isEmpty(text)) {
            this.text(text);
        }
        else {
            final List segments = applyPattern(text);
            if (segments == null) {
                this.text(text);
            }
            else {
                final Iterator it = segments.iterator();
                while (it.hasNext()) {
                    final String name = it.next();
                    final String href = it.next();
                    if (href == null) {
                        this.text(name);
                    }
                    else if (getValidHref(href) != null) {
                        this.link(getValidHref(href), name);
                    }
                    else {
                        this.text(href);
                    }
                }
            }
        }
    }
    
    protected static String createLinkPatternedText(final String text, final String href) {
        if (text == null) {
            return text;
        }
        if (href == null) {
            return text;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("{").append(text).append(", ").append(href).append("}");
        return sb.toString();
    }
    
    protected static String propertiesToString(final Properties props) {
        final StringBuffer sb = new StringBuffer();
        if (props == null || props.isEmpty()) {
            return sb.toString();
        }
        final Iterator i = props.keySet().iterator();
        while (i.hasNext()) {
            final String key = i.next();
            sb.append(key).append("=").append(props.get(key));
            if (i.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    private static String getValidHref(String href) {
        if (StringUtils.isEmpty(href)) {
            return null;
        }
        href = href.trim();
        final String[] schemes = { "http", "https" };
        final UrlValidator urlValidator = new UrlValidator(schemes);
        if (EmailValidator.getInstance().isValid(href) || (href.indexOf("?") != -1 && EmailValidator.getInstance().isValid(href.substring(0, href.indexOf("?"))))) {
            return "mailto:" + href;
        }
        if (href.toLowerCase().startsWith("mailto:")) {
            return href;
        }
        if (urlValidator.isValid(href)) {
            return href;
        }
        String hrefTmp;
        if (!href.endsWith("/")) {
            hrefTmp = href + "/index.html";
        }
        else {
            hrefTmp = href + "index.html";
        }
        if (urlValidator.isValid(hrefTmp)) {
            return href;
        }
        if (!href.startsWith("./")) {
            return null;
        }
        if (href.length() > 2) {
            return href.substring(2, href.length());
        }
        return ".";
    }
    
    private static List applyPattern(final String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        final List segments = new ArrayList();
        if (text.indexOf("${") != -1) {
            final int lastComma = text.lastIndexOf(",");
            final int lastSemi = text.lastIndexOf("}");
            if (lastComma != -1 && lastSemi != -1) {
                segments.add(text.substring(lastComma + 1, lastSemi).trim());
                segments.add(null);
            }
            else {
                segments.add(text);
                segments.add(null);
            }
            return segments;
        }
        boolean inQuote = false;
        int braceStack = 0;
        int lastOffset = 0;
        for (int i = 0; i < text.length(); ++i) {
            final char ch = text.charAt(i);
            if (ch == '\'' && !inQuote && braceStack == 0) {
                if (i + 1 < text.length() && text.charAt(i + 1) == '\'') {
                    ++i;
                    segments.add(text.substring(lastOffset, i));
                    segments.add(null);
                    lastOffset = i + 1;
                }
                else {
                    inQuote = true;
                }
            }
            else {
                switch (ch) {
                    case '{': {
                        if (!inQuote) {
                            if (braceStack == 0) {
                                if (i != 0) {
                                    segments.add(text.substring(lastOffset, i));
                                    segments.add(null);
                                }
                                lastOffset = i + 1;
                            }
                            ++braceStack;
                            break;
                        }
                        break;
                    }
                    case '}': {
                        if (!inQuote && --braceStack == 0) {
                            final String subString = text.substring(lastOffset, i);
                            lastOffset = i + 1;
                            final int lastComma2 = subString.lastIndexOf(",");
                            if (lastComma2 != -1) {
                                segments.add(subString.substring(0, lastComma2).trim());
                                segments.add(subString.substring(lastComma2 + 1).trim());
                            }
                            else {
                                segments.add(subString);
                                segments.add(null);
                            }
                            break;
                        }
                        break;
                    }
                    case '\'': {
                        inQuote = false;
                        break;
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(text.substring(lastOffset, text.length()))) {
            segments.add(text.substring(lastOffset, text.length()));
            segments.add(null);
        }
        if (braceStack != 0) {
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        }
        if (inQuote) {}
        return Collections.unmodifiableList((List<?>)segments);
    }
    
    public abstract String getTitle();
    
    protected abstract void renderBody();
}
