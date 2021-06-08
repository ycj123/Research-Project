// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.blame;

import java.text.ParseException;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class IntegrityBlameConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<BlameLine> blameList;
    private SimpleDateFormat dateFormat;
    
    public IntegrityBlameConsumer(final ScmLogger logger) {
        this.logger = logger;
        this.blameList = new ArrayList<BlameLine>();
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy z");
    }
    
    public void consumeLine(final String line) {
        this.logger.debug(line);
        if (null != line && line.trim().length() > 0) {
            final String[] tokens = StringUtils.split(line, "\t");
            if (tokens.length != 3) {
                this.logger.warn("Failed to parse line: " + line);
            }
            else {
                try {
                    this.blameList.add(new BlameLine(this.dateFormat.parse(tokens[0]), tokens[1], tokens[2]));
                }
                catch (ParseException e) {
                    this.logger.error("Failed to date string: " + tokens[0]);
                }
            }
        }
    }
    
    public List<BlameLine> getBlameList() {
        return this.blameList;
    }
}
