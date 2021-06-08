// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import ch.ethz.ssh2.sftp.ErrorCodes;
import java.io.IOException;

public class SFTPException extends IOException
{
    private static final long serialVersionUID = 578654644222421811L;
    private final String sftpErrorMessage;
    private final int sftpErrorCode;
    
    private static String constructMessage(final String s, final int errorCode) {
        final String[] detail = ErrorCodes.getDescription(errorCode);
        if (detail == null) {
            return String.valueOf(s) + " (UNKNOW SFTP ERROR CODE)";
        }
        return String.valueOf(s) + " (" + detail[0] + ": " + detail[1] + ")";
    }
    
    SFTPException(final String msg, final int errorCode) {
        super(constructMessage(msg, errorCode));
        this.sftpErrorMessage = msg;
        this.sftpErrorCode = errorCode;
    }
    
    public String getServerErrorMessage() {
        return this.sftpErrorMessage;
    }
    
    public int getServerErrorCode() {
        return this.sftpErrorCode;
    }
    
    public String getServerErrorCodeSymbol() {
        final String[] detail = ErrorCodes.getDescription(this.sftpErrorCode);
        if (detail == null) {
            return "UNKNOW SFTP ERROR CODE " + this.sftpErrorCode;
        }
        return detail[0];
    }
    
    public String getServerErrorCodeVerbose() {
        final String[] detail = ErrorCodes.getDescription(this.sftpErrorCode);
        if (detail == null) {
            return "The error code " + this.sftpErrorCode + " is unknown.";
        }
        return detail[1];
    }
}
