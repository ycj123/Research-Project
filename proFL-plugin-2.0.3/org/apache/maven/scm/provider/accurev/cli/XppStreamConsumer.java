// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.nio.channels.Pipe;
import org.codehaus.plexus.util.xml.pull.MXParser;
import java.io.Reader;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import java.io.Writer;
import org.codehaus.plexus.util.cli.StreamConsumer;

public abstract class XppStreamConsumer extends Thread implements StreamConsumer
{
    private Writer writer;
    private XmlPullParser parser;
    private volatile boolean complete;
    private ScmLogger logger;
    private int lineCount;
    private Reader reader;
    
    public ScmLogger getLogger() {
        return this.logger;
    }
    
    public XppStreamConsumer(final ScmLogger logger) {
        this.parser = new MXParser();
        this.complete = false;
        this.lineCount = 0;
        this.logger = logger;
        try {
            final Pipe p = Pipe.open();
            final Pipe.SinkChannel sink = p.sink();
            final Pipe.SourceChannel source = p.source();
            this.writer = Channels.newWriter(sink, Charset.defaultCharset().name());
            this.reader = Channels.newReader(source, Charset.defaultCharset().name());
            this.parser.setInput(this.reader);
        }
        catch (Exception e) {
            logger.error("Exception initialising pipe", e);
        }
    }
    
    public final void consumeLine(final String line) {
        try {
            this.writer.append((CharSequence)line);
            if (this.lineCount == 0) {
                this.start();
            }
            ++this.lineCount;
            this.writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException("error pumping line to pipe", e);
        }
    }
    
    @Override
    public void run() {
        try {
            this.parse(this.parser);
        }
        catch (Exception e) {
            this.caughtParseException(e);
            synchronized (this) {
                try {
                    this.reader.close();
                }
                catch (IOException e2) {
                    this.getLogger().warn("Error closing pipe reader", e2);
                }
                this.complete = true;
                this.notifyAll();
            }
        }
        finally {
            synchronized (this) {
                try {
                    this.reader.close();
                }
                catch (IOException e3) {
                    this.getLogger().warn("Error closing pipe reader", e3);
                }
                this.complete = true;
                this.notifyAll();
            }
        }
    }
    
    protected void caughtParseException(final Exception e) {
        this.logger.warn("Exception parsing input", e);
    }
    
    protected void parse(final XmlPullParser p) throws XmlPullParserException, IOException {
        final List<String> tagPath = new ArrayList<String>();
        int eventType = p.getEventType();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Event " + eventType);
        }
        while (eventType != 1) {
            final int lastIndex = tagPath.size() - 1;
            switch (eventType) {
                case 0: {
                    break;
                }
                case 2: {
                    final String tagName = p.getName();
                    if (tagName != null) {
                        tagPath.add(tagName);
                        final int attributeCount = p.getAttributeCount();
                        final Map<String, String> attributes = new HashMap<String, String>(Math.max(attributeCount, 0));
                        for (int i = 0; i < attributeCount; ++i) {
                            attributes.put(p.getAttributeName(i), p.getAttributeValue(i));
                        }
                        this.startTag(tagPath, attributes);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (!p.isWhitespace()) {
                        final String text = p.getText();
                        this.text(tagPath, text);
                        break;
                    }
                    break;
                }
                case 3: {
                    final String tagName = p.getName();
                    if (lastIndex < 0 || !tagName.equals(tagPath.get(lastIndex))) {
                        this.logger.warn("Bad tag path: " + Arrays.toString(tagPath.toArray()));
                    }
                    this.endTag(tagPath);
                    tagPath.remove(lastIndex);
                    break;
                }
                default: {
                    this.logger.warn("Unexpected event type " + eventType);
                    break;
                }
            }
            p.next();
            eventType = p.getEventType();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Event " + eventType);
            }
        }
    }
    
    public void waitComplete() {
        Thread.yield();
        try {
            this.writer.close();
        }
        catch (IOException e1) {
            this.logger.warn("Exception flushing output", e1);
        }
        while (!this.isComplete()) {
            synchronized (this) {
                try {
                    if (this.isComplete()) {
                        continue;
                    }
                    this.wait(1000L);
                }
                catch (Exception e2) {
                    this.logger.warn(e2);
                }
            }
        }
    }
    
    private boolean isComplete() {
        return this.complete || this.lineCount == 0;
    }
    
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        if (this.logger.isDebugEnabled()) {
            final String tagName = getTagName(tagPath);
            this.logger.debug("START_TAG: " + tagName + "(" + attributes.size() + ")");
        }
    }
    
    protected static String getTagName(final List<String> tagPath) {
        return (tagPath.size() == 0) ? null : tagPath.get(tagPath.size() - 1);
    }
    
    protected void endTag(final List<String> tagPath) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("END_TAG: " + getTagName(tagPath));
        }
    }
    
    protected void text(final List<String> tagPath, final String text) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("TEXT: " + text);
        }
    }
}
