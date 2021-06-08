// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.Date;
import java.util.Map;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.Stream;
import java.util.List;

public class StreamsConsumer extends XppStreamConsumer
{
    private List<Stream> streams;
    
    public StreamsConsumer(final ScmLogger logger, final List<Stream> streams) {
        super(logger);
        this.streams = streams;
    }
    
    @Override
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if ("stream".equals(tagName)) {
            final String name = attributes.get("name");
            final long streamId = Long.parseLong(attributes.get("streamNumber"));
            final String basis = attributes.get("basis");
            final String basisStreamNumber = attributes.get("basisStreamNumber");
            final long basisStreamId = (basisStreamNumber == null) ? 0L : Long.parseLong(basisStreamNumber);
            final String depot = attributes.get("depotName");
            final Date startTime = new Date(Long.parseLong(attributes.get("startTime")) * 1000L);
            final String streamType = attributes.get("type");
            this.streams.add(new Stream(name, streamId, basis, basisStreamId, depot, startTime, streamType));
        }
    }
}
