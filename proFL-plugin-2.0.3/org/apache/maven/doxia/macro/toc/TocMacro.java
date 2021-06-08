// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.toc;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.util.HtmlTools;
import java.util.Iterator;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.macro.MacroExecutionException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.maven.doxia.index.IndexingSink;
import org.apache.maven.doxia.index.IndexEntry;
import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.macro.AbstractMacro;

public class TocMacro extends AbstractMacro
{
    private int section;
    private int fromDepth;
    private int toDepth;
    private static final int DEFAULT_DEPTH = 5;
    
    public void execute(final Sink sink, final MacroRequest request) throws MacroExecutionException {
        final String source = (String)request.getParameter("sourceContent");
        final Parser parser = (Parser)request.getParameter("parser");
        this.section = getInt(request, "section", 0);
        if (this.section != 0) {
            this.fromDepth = getInt(request, "fromDepth", 0);
            this.toDepth = getInt(request, "toDepth", 5);
        }
        else {
            this.fromDepth = 0;
            this.toDepth = 5;
        }
        final IndexEntry index = new IndexEntry("index");
        final IndexingSink tocSink = new IndexingSink(index);
        try {
            parser.parse(new StringReader(source), tocSink);
            if (index.getChildEntries().size() > 0) {
                if (this.fromDepth < this.section || this.section == 0) {
                    sink.list();
                }
                int i = 1;
                for (final IndexEntry sectionIndex : index.getChildEntries()) {
                    if (i == this.section || this.section == 0) {
                        this.writeSubSectionN(sink, sectionIndex, i);
                    }
                    ++i;
                }
                if (this.fromDepth < this.section || this.section == 0) {
                    sink.list_();
                }
            }
        }
        catch (ParseException e) {
            throw new MacroExecutionException("ParseException: " + e.getMessage(), e);
        }
    }
    
    private void writeSubSectionN(final Sink sink, final IndexEntry sectionIndex, final int n) {
        if (this.fromDepth < n) {
            sink.listItem();
            sink.link("#" + HtmlTools.encodeId(sectionIndex.getId()));
            sink.text(sectionIndex.getTitle());
            sink.link_();
        }
        if (this.toDepth >= n && sectionIndex.getChildEntries().size() > 0) {
            if (this.fromDepth < n + 1) {
                sink.list();
            }
            for (final IndexEntry subsectionIndex : sectionIndex.getChildEntries()) {
                if (n == 5) {
                    sink.listItem();
                    sink.link("#" + HtmlTools.encodeId(subsectionIndex.getId()));
                    sink.text(subsectionIndex.getTitle());
                    sink.link_();
                    sink.listItem_();
                }
                else {
                    this.writeSubSectionN(sink, subsectionIndex, n + 1);
                }
            }
            if (this.fromDepth < n + 1) {
                sink.list_();
            }
        }
        if (this.fromDepth < n) {
            sink.listItem_();
        }
    }
    
    private static int getInt(final MacroRequest request, final String parameter, final int defaultValue) throws MacroExecutionException {
        final String value = (String)request.getParameter(parameter);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        int i;
        try {
            i = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
        if (i < 0) {
            throw new MacroExecutionException("The " + parameter + "=" + i + " should be positive.");
        }
        return i;
    }
}
