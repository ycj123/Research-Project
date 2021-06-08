// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity;

import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Response;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.APIException;

public class ExceptionHandler
{
    private String message;
    private String command;
    private int exitCode;
    
    public ExceptionHandler(final APIException ex) {
        final Response response = ex.getResponse();
        ex.printStackTrace();
        if (null == response) {
            this.message = ex.getMessage();
            this.command = new String();
            this.exitCode = -1;
        }
        else {
            this.command = response.getCommandString();
            try {
                this.exitCode = response.getExitCode();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
                this.exitCode = -1;
            }
            final WorkItemIterator wit = response.getWorkItems();
            try {
                while (wit.hasNext()) {
                    wit.next();
                }
                if (ex.getMessage() != null) {
                    this.message = ex.getMessage();
                }
            }
            catch (APIException ae) {
                final String curMessage = ae.getMessage();
                if (curMessage != null) {
                    this.message = curMessage;
                }
                ae.printStackTrace();
            }
        }
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public int getExitCode() {
        return this.exitCode;
    }
}
