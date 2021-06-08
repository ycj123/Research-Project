// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;

public class ResponseFactory
{
    private final Map responseInstancesMap;
    private String previousResponse;
    
    public ResponseFactory() {
        this.previousResponse = null;
        (this.responseInstancesMap = new HashMap()).put("E", new ErrorMessageResponse());
        this.responseInstancesMap.put("M", new MessageResponse());
        this.responseInstancesMap.put("Mbinary", new MessageBinaryResponse());
        this.responseInstancesMap.put("MT", new MessageTaggedResponse());
        this.responseInstancesMap.put("Updated", new UpdatedResponse());
        this.responseInstancesMap.put("Update-existing", new UpdatedResponse());
        this.responseInstancesMap.put("Created", new CreatedResponse());
        this.responseInstancesMap.put("Rcs-diff", new RcsDiffResponse());
        this.responseInstancesMap.put("Checked-in", new CheckedInResponse());
        this.responseInstancesMap.put("New-entry", new NewEntryResponse());
        this.responseInstancesMap.put("ok", new OKResponse());
        this.responseInstancesMap.put("error", new ErrorResponse());
        this.responseInstancesMap.put("Set-static-directory", new SetStaticDirectoryResponse());
        this.responseInstancesMap.put("Clear-static-directory", new ClearStaticDirectoryResponse());
        this.responseInstancesMap.put("Set-sticky", new SetStickyResponse());
        this.responseInstancesMap.put("Clear-sticky", new ClearStickyResponse());
        this.responseInstancesMap.put("Valid-requests", new ValidRequestsResponse());
        this.responseInstancesMap.put("Merged", new MergedResponse());
        this.responseInstancesMap.put("Notified", new NotifiedResponse());
        this.responseInstancesMap.put("Removed", new RemovedResponse());
        this.responseInstancesMap.put("Remove-entry", new RemoveEntryResponse());
        this.responseInstancesMap.put("Copy-file", new CopyFileResponse());
        this.responseInstancesMap.put("Mod-time", new ModTimeResponse());
        this.responseInstancesMap.put("Template", new TemplateResponse());
        this.responseInstancesMap.put("Module-expansion", new ModuleExpansionResponse());
        this.responseInstancesMap.put("Wrapper-rcsOption", new WrapperSendResponse());
    }
    
    public Response createResponse(final String s) {
        final Response response = this.responseInstancesMap.get(s);
        if (response != null) {
            this.previousResponse = s;
            return response;
        }
        if (this.previousResponse != null && this.previousResponse.equals("M")) {
            return new MessageResponse(s);
        }
        this.previousResponse = null;
        final IllegalArgumentException2 illegalArgumentException2 = new IllegalArgumentException2("Unhandled response: " + s + ".");
        final String property = System.getProperty("Env-CVS_SERVER");
        String string;
        if (property == null) {
            string = "";
        }
        else {
            string = "=" + property;
        }
        final String property2 = System.getProperty("Env-CVS_EXE");
        String string2;
        if (property2 == null) {
            string2 = "";
        }
        else {
            string2 = "=" + property2;
        }
        illegalArgumentException2.setLocalizedMessage(MessageFormat.format(ResourceBundle.getBundle("org.netbeans.lib.cvsclient.response.Bundle").getString("BK0001"), s, string, string2));
        throw illegalArgumentException2;
    }
    
    private static class IllegalArgumentException2 extends IllegalArgumentException
    {
        private String localizedMessage;
        
        public IllegalArgumentException2(final String s) {
            super(s);
        }
        
        public String getLocalizedMessage() {
            return this.localizedMessage;
        }
        
        private void setLocalizedMessage(final String localizedMessage) {
            this.localizedMessage = localizedMessage;
        }
    }
}
