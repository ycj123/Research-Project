// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.editors;

import java.io.File;
import java.util.Date;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class EditorsFileInfoContainer extends FileInfoContainer
{
    private final String client;
    private final Date date;
    private final File file;
    private final String user;
    
    EditorsFileInfoContainer(final File file, final String user, final Date date, final String client) {
        this.file = file;
        this.user = user;
        this.date = date;
        this.client = client;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public String getClient() {
        return this.client;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String getUser() {
        return this.user;
    }
}
