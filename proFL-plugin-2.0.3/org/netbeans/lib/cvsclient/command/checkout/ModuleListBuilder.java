// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.checkout;

import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class ModuleListBuilder implements Builder
{
    private ModuleListInformation moduleInformation;
    private final EventManager eventManager;
    private final CheckoutCommand checkoutCommand;
    
    public ModuleListBuilder(final EventManager eventManager, final CheckoutCommand checkoutCommand) {
        this.eventManager = eventManager;
        this.checkoutCommand = checkoutCommand;
    }
    
    public void outputDone() {
        if (this.moduleInformation != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.moduleInformation));
            this.moduleInformation = null;
        }
    }
    
    public void parseLine(String replace, final boolean b) {
        replace = replace.replace('\t', ' ');
        if (!replace.startsWith(" ")) {
            this.processModule(replace, true);
        }
        else {
            this.processModule(replace, false);
        }
    }
    
    protected void processModule(final String str, final boolean b) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str, " ", false);
        if (b) {
            this.outputDone();
            (this.moduleInformation = new ModuleListInformation()).setModuleName(stringTokenizer.nextToken());
            if (this.checkoutCommand.isShowModulesWithStatus()) {
                this.moduleInformation.setModuleStatus(stringTokenizer.nextToken());
            }
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.startsWith("-")) {
                this.moduleInformation.setType(nextToken);
            }
            else {
                this.moduleInformation.addPath(nextToken);
            }
        }
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
