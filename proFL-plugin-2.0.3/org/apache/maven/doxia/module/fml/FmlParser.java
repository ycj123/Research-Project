// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.fml;

import java.io.StringReader;
import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.util.HtmlTools;
import org.apache.maven.doxia.module.fml.model.Faq;
import org.apache.maven.doxia.module.fml.model.Part;
import org.apache.maven.doxia.module.fml.model.Faqs;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.apache.maven.doxia.parser.ParseException;
import org.codehaus.plexus.util.xml.pull.MXParser;
import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;
import org.apache.maven.doxia.parser.Parser;

public class FmlParser implements Parser
{
    public void parse(final Reader reader, final Sink sink) throws ParseException {
        Faqs faqs;
        try {
            final XmlPullParser parser = new MXParser();
            parser.setInput(reader);
            faqs = this.parseFml(parser, sink);
        }
        catch (XmlPullParserException ex) {
            throw new ParseException("Error parsing the model: " + ex.getMessage(), ex);
        }
        catch (IOException ex2) {
            throw new ParseException("Error reading the model: " + ex2.getMessage(), ex2);
        }
        try {
            this.createSink(faqs, sink);
        }
        catch (XmlPullParserException e) {
            throw new ParseException("Error creating sink: " + e.getMessage(), e);
        }
        catch (IOException e2) {
            throw new ParseException("Error writing to sink: " + e2.getMessage(), e2);
        }
    }
    
    public int getType() {
        return 2;
    }
    
    public Faqs parseFml(final XmlPullParser parser, final Sink sink) throws IOException, XmlPullParserException {
        final Faqs faqs = new Faqs();
        Part currentPart = null;
        Faq currentFaq = null;
        boolean inQuestion = false;
        boolean inAnswer = false;
        StringBuffer buffer = null;
        for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.nextToken()) {
            if (eventType == 2) {
                if (parser.getName().equals("faqs")) {
                    final String title = parser.getAttributeValue(null, "title");
                    if (title != null) {
                        faqs.setTitle(title);
                    }
                    final String toplink = parser.getAttributeValue(null, "toplink");
                    if (toplink != null) {
                        if (toplink.equalsIgnoreCase("true")) {
                            faqs.setToplink(true);
                        }
                        else {
                            faqs.setToplink(false);
                        }
                    }
                }
                else if (parser.getName().equals("part")) {
                    currentPart = new Part();
                    currentPart.setId(parser.getAttributeValue(null, "id"));
                }
                else if (parser.getName().equals("title")) {
                    currentPart.setTitle(parser.nextText().trim());
                }
                else if (parser.getName().equals("faq")) {
                    currentFaq = new Faq();
                    currentFaq.setId(parser.getAttributeValue(null, "id"));
                }
                if (parser.getName().equals("question")) {
                    buffer = new StringBuffer();
                    inQuestion = true;
                }
                else if (parser.getName().equals("answer")) {
                    buffer = new StringBuffer();
                    inAnswer = true;
                }
                else if (inQuestion || inAnswer) {
                    buffer.append("<");
                    buffer.append(parser.getName());
                    for (int count = parser.getAttributeCount(), i = 0; i < count; ++i) {
                        buffer.append(" ");
                        buffer.append(parser.getAttributeName(i));
                        buffer.append("=");
                        buffer.append("\"");
                        buffer.append(HtmlTools.escapeHTML(parser.getAttributeValue(i)));
                        buffer.append("\"");
                    }
                    buffer.append(">");
                }
            }
            else if (eventType == 3) {
                if (!parser.getName().equals("faqs")) {
                    if (parser.getName().equals("part")) {
                        faqs.addPart(currentPart);
                        currentPart = null;
                    }
                    else if (parser.getName().equals("faq")) {
                        currentPart.addFaq(currentFaq);
                        currentFaq = null;
                    }
                }
                if (parser.getName().equals("question")) {
                    currentFaq.setQuestion(buffer.toString());
                    inQuestion = false;
                }
                else if (parser.getName().equals("answer")) {
                    currentFaq.setAnswer(buffer.toString());
                    inAnswer = false;
                }
                else if (inQuestion || inAnswer) {
                    if (buffer.charAt(buffer.length() - 1) == ' ') {
                        buffer.deleteCharAt(buffer.length() - 1);
                    }
                    buffer.append("</");
                    buffer.append(parser.getName());
                    buffer.append(">");
                }
            }
            else if (eventType == 5) {
                if (buffer != null && parser.getText() != null) {
                    buffer.append("<![CDATA[");
                    buffer.append(parser.getText());
                    buffer.append("]]>");
                }
            }
            else if (eventType == 4) {
                if (buffer != null && parser.getText() != null) {
                    buffer.append(parser.getText());
                }
            }
            else if (eventType == 6 && buffer != null && parser.getText() != null) {
                buffer.append(HtmlTools.escapeHTML(parser.getText()));
            }
        }
        return faqs;
    }
    
    private void createSink(final Faqs faqs, final Sink sink) throws IOException, XmlPullParserException {
        sink.head();
        sink.title();
        sink.text(faqs.getTitle());
        sink.title_();
        sink.head_();
        sink.body();
        sink.section1();
        sink.sectionTitle1();
        sink.anchor("top");
        sink.text(faqs.getTitle());
        sink.anchor_();
        sink.sectionTitle1_();
        for (final Part part : faqs.getParts()) {
            if (StringUtils.isNotEmpty(part.getTitle())) {
                sink.paragraph();
                sink.bold();
                sink.text(part.getTitle());
                sink.bold_();
                sink.paragraph_();
            }
            sink.numberedList(0);
            for (final Faq faq : part.getFaqs()) {
                sink.numberedListItem();
                sink.link("#" + HtmlTools.encodeId(faq.getId()));
                sink.rawText(faq.getQuestion());
                sink.link_();
                sink.numberedListItem_();
            }
            sink.numberedList_();
        }
        sink.section1_();
        for (final Part part : faqs.getParts()) {
            if (StringUtils.isNotEmpty(part.getTitle())) {
                sink.section1();
                sink.sectionTitle1();
                sink.text(part.getTitle());
                sink.sectionTitle1_();
            }
            sink.definitionList();
            final Iterator faqIterator = part.getFaqs().iterator();
            while (faqIterator.hasNext()) {
                final Faq faq = faqIterator.next();
                sink.definedTerm();
                sink.anchor(faq.getId());
                sink.rawText(faq.getQuestion());
                sink.anchor_();
                sink.definedTerm_();
                sink.definition();
                sink.paragraph();
                this.writeAnswer(sink, faq.getAnswer());
                sink.paragraph_();
                if (faqs.isToplink()) {
                    this.writeTopLink(sink);
                }
                if (faqIterator.hasNext()) {
                    sink.horizontalRule();
                }
                sink.definition_();
            }
            sink.definitionList_();
            if (StringUtils.isNotEmpty(part.getTitle())) {
                sink.section1_();
            }
        }
        sink.body_();
    }
    
    private void writeAnswer(final Sink sink, final String answer) throws IOException, XmlPullParserException {
        final int startSource = answer.indexOf("<source>");
        if (startSource != -1) {
            this.writeAnswerWithSource(sink, answer);
        }
        else {
            sink.rawText(answer);
        }
    }
    
    private void writeTopLink(final Sink sink) {
        sink.rawText("<table border=\"0\">");
        sink.rawText("<tr><td align=\"right\">");
        sink.link("#top");
        sink.text("[top]");
        sink.link_();
        sink.rawText("</td></tr>");
        sink.rawText("</table>");
    }
    
    private void writeAnswerWithSource(final Sink sink, final String answer) throws IOException, XmlPullParserException {
        final XmlPullParser parser = new MXParser();
        parser.setInput(new StringReader("<answer>" + answer + "</answer>"));
        int countSource = 0;
        for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.nextToken()) {
            if (eventType == 2) {
                if (parser.getName().equals("source") && countSource == 0) {
                    sink.verbatim(true);
                    ++countSource;
                }
                else if (parser.getName().equals("source")) {
                    sink.rawText(HtmlTools.escapeHTML("<" + parser.getName() + ">"));
                    ++countSource;
                }
                else if (!parser.getName().equals("answer")) {
                    if (countSource > 0) {
                        sink.rawText(HtmlTools.escapeHTML("<" + parser.getName() + ">"));
                    }
                    else {
                        final StringBuffer buffer = new StringBuffer();
                        buffer.append("<" + parser.getName());
                        for (int count = parser.getAttributeCount(), i = 0; i < count; ++i) {
                            buffer.append(" ");
                            buffer.append(parser.getAttributeName(i));
                            buffer.append("=");
                            buffer.append("\"");
                            buffer.append(HtmlTools.escapeHTML(parser.getAttributeValue(i)));
                            buffer.append("\"");
                        }
                        buffer.append(">");
                        sink.rawText(buffer.toString());
                    }
                }
            }
            else if (eventType == 3) {
                if (parser.getName().equals("source") && countSource == 1) {
                    --countSource;
                    sink.verbatim_();
                }
                else if (parser.getName().equals("source")) {
                    sink.rawText(HtmlTools.escapeHTML("</" + parser.getName() + ">"));
                    --countSource;
                }
                else if (!parser.getName().equals("answer")) {
                    if (countSource > 0) {
                        sink.rawText(HtmlTools.escapeHTML("</" + parser.getName() + ">"));
                    }
                    else {
                        sink.rawText("</" + parser.getName() + ">");
                    }
                }
            }
            else if (eventType == 5) {
                sink.rawText(HtmlTools.escapeHTML(parser.getText()));
            }
            else if (eventType == 4) {
                sink.rawText(HtmlTools.escapeHTML(parser.getText()));
            }
            else if (eventType == 6) {
                sink.rawText(HtmlTools.escapeHTML(parser.getText()));
            }
        }
    }
}
