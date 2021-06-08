// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command;

import java.util.HashMap;
import org.apache.maven.scm.ScmFileStatus;
import java.util.Map;

public class PerforceVerbMapper
{
    private static final Map<String, ScmFileStatus> VERB;
    
    public static ScmFileStatus toStatus(final String verb) {
        ScmFileStatus stat = PerforceVerbMapper.VERB.get(verb);
        if (stat == null) {
            System.err.println("No such verb: " + verb);
            return ScmFileStatus.UNKNOWN;
        }
        if (stat == ScmFileStatus.UNKNOWN) {
            stat = null;
        }
        return stat;
    }
    
    static {
        (VERB = new HashMap<String, ScmFileStatus>()).put("add", ScmFileStatus.ADDED);
        PerforceVerbMapper.VERB.put("added", ScmFileStatus.ADDED);
        PerforceVerbMapper.VERB.put("delete", ScmFileStatus.DELETED);
        PerforceVerbMapper.VERB.put("deleted", ScmFileStatus.DELETED);
        PerforceVerbMapper.VERB.put("edit", ScmFileStatus.MODIFIED);
        PerforceVerbMapper.VERB.put("edited", ScmFileStatus.MODIFIED);
        PerforceVerbMapper.VERB.put("updating", ScmFileStatus.UPDATED);
        PerforceVerbMapper.VERB.put("updated", ScmFileStatus.UPDATED);
        PerforceVerbMapper.VERB.put("refreshing", ScmFileStatus.UNKNOWN);
    }
}
