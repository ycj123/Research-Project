// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.Option;
import com.mks.api.SelectionList;
import com.mks.api.response.WorkItem;
import com.mks.api.util.MKSLogger;
import com.mks.api.CmdRunner;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.Response;
import com.mks.api.Command;
import com.mks.api.Session;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;
import com.mks.api.OptionList;

abstract class CommandBase implements ICommandBase
{
    private static final String LOG_CATEGORY = "COMMANDS";
    private static final String NO = "no";
    protected boolean interactive;
    private OptionList options;
    private CmdRunnerCreator cmdRunnerCreator;
    
    protected CommandBase(final CmdRunnerCreator session) throws APIException {
        this.interactive = false;
        this.options = null;
        validateConnection(session);
        this.cmdRunnerCreator = session;
        this.options = new OptionList();
    }
    
    static void validateConnection(final CmdRunnerCreator cmdRunnerCreator) throws APIException {
        if (cmdRunnerCreator instanceof Session) {
            final Command cmd = new Command("api", "ping");
            runAPICommand(cmdRunnerCreator, cmd, false);
        }
    }
    
    protected final CmdRunnerCreator getCmdRunnerCreator() {
        return this.cmdRunnerCreator;
    }
    
    protected final Response runAPICommand(final Command command) throws APIException {
        return runAPICommand(this.getCmdRunnerCreator(), command, false);
    }
    
    static Response runAPICommand(final CmdRunnerCreator cmdRunnerCreator, final Command command, final boolean useStreamedResponse) throws APIException {
        CmdRunner runner = null;
        Response response = null;
        try {
            runner = cmdRunnerCreator.createCmdRunner();
            final String[] cmdArray = command.toStringArray();
            final StringBuffer sb = new StringBuffer("Executing: ");
            for (int i = 0; i < cmdArray.length; ++i) {
                String arg = cmdArray[i];
                if (arg.toLowerCase().startsWith("--password")) {
                    arg = "--password=XXXX";
                }
                if (i != 0) {
                    sb.append(" ");
                }
                sb.append(arg);
            }
            IntegrationPointFactory.getLogger().message("COMMANDS", 10, sb.toString());
            response = (useStreamedResponse ? runner.executeWithInterim(command, false) : runner.execute(command));
        }
        catch (APIException ex) {
            final MKSLogger logger = IntegrationPointFactory.getLogger();
            response = ex.getResponse();
            if (response == null || response.getWorkItemListSize() < 1) {
                logger.exception("COMMANDS", ex);
                throw ex;
            }
            if (response.getWorkItemListSize() == 1) {
                APIException wix = null;
                try {
                    final WorkItem wi = response.getWorkItems().next();
                    wix = wi.getAPIException();
                }
                catch (APIException ex2) {
                    wix = ex2;
                }
                if (wix != null) {
                    logger.exception("COMMANDS", wix);
                    throw wix;
                }
            }
        }
        finally {
            if (!useStreamedResponse) {
                runner.release();
            }
        }
        return response;
    }
    
    protected abstract Response execute(final SelectionList p0) throws APIException;
    
    public final Response execute(final String[] selection, final boolean isInteractive) throws APIException {
        this.interactive = isInteractive;
        final SelectionList selectionList = new SelectionList();
        if (selection != null) {
            for (int i = 0; i < selection.length; ++i) {
                selectionList.add(selection[i]);
            }
        }
        return this.execute(selectionList);
    }
    
    public final Response execute(final String selection, final boolean isInteractive) throws APIException {
        return this.execute(new String[] { selection }, isInteractive);
    }
    
    public final Response execute(final boolean isInteractive) throws APIException {
        return this.execute((String[])null, isInteractive);
    }
    
    protected Option createBinaryOption(final String optionName, final boolean value) {
        String opt = optionName;
        if (!value) {
            opt = "no" + optionName;
        }
        return new Option(opt);
    }
    
    public final void addOptionList(final OptionList options) {
        if (options != null) {
            this.options.add(options);
        }
    }
    
    public final OptionList getBaseOptions() {
        final OptionList optionClone = new OptionList();
        optionClone.add(this.options);
        return optionClone;
    }
}
