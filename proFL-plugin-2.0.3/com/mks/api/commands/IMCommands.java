// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.response.InvalidCommandOptionException;
import java.util.Iterator;
import java.util.List;
import com.mks.api.response.Field;
import com.mks.api.response.WorkItem;
import java.util.NoSuchElementException;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Item;
import com.mks.api.response.Result;
import com.mks.api.response.Response;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

public class IMCommands extends MKSCommands
{
    public IMCommands(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    public void imConnect() throws APIException {
        final Command cmd = new Command("im", "connect");
        cmd.addOption(new Option("g"));
        this.runAPICommand(cmd);
    }
    
    public void imEditIssue(final int issueId) throws APIException {
        final Command cmd = new Command("im", "editissue");
        cmd.addOption(new Option("g"));
        final String issue = String.valueOf(issueId);
        if (issue != null && issue.length() > 0) {
            cmd.addSelection(issue);
            this.runAPICommand(cmd);
            return;
        }
        throw new InvalidCommandSelectionException("IMCommands.imEditIssue: parameter 'issueId' is invalid.");
    }
    
    public void imViewIssue(final int issueId) throws APIException {
        final Command cmd = new Command("im", "viewissue");
        cmd.addOption(new Option("g"));
        final String issue = String.valueOf(issueId);
        if (issue != null && issue.length() > 0) {
            cmd.addSelection(issue);
            this.runAPICommand(cmd);
            return;
        }
        throw new InvalidCommandSelectionException("IMCommands.imViewIssue: parameter 'issueId' is invalid.");
    }
    
    public String imCreateIssue() throws APIException {
        String newIssueid = "";
        final Command cmd = new Command("im", "createissue");
        cmd.addOption(new Option("g"));
        final Response response = this.runAPICommand(cmd);
        Result result = null;
        Item item = null;
        if (response != null) {
            result = response.getResult();
        }
        if (result != null) {
            item = result.getPrimaryValue();
        }
        if (item != null) {
            newIssueid = item.getId();
        }
        return newIssueid;
    }
    
    public void imCopyIssue(final int issueId) throws APIException {
        final Command cmd = new Command("im", "copyissue");
        cmd.addOption(new Option("g"));
        cmd.addOption(new Option("link"));
        cmd.addOption(new Option("copyfields"));
        final String issue = String.valueOf(issueId);
        if (issue != null && issue.length() > 0) {
            cmd.addSelection(issue);
            this.runAPICommand(cmd);
            return;
        }
        throw new InvalidCommandSelectionException("IMCommands.imCopyIssue: parameter 'issueId' is invalid.");
    }
    
    public void imViewCP(final String cpId) throws APIException {
        if (cpId == null || cpId.length() == 0) {
            throw new InvalidCommandSelectionException("IMCommands.imViewCP: parameter 'cpId' cannot be null or empty.");
        }
        final Command cmd = new Command("im", "viewcp");
        cmd.addOption(new Option("g"));
        cmd.addSelection(cpId);
        this.runAPICommand(cmd);
    }
    
    public String[] imGetQueries() throws APIException {
        String[] result = new String[0];
        final Command cmd = new Command("im", "queries");
        cmd.addOption(new Option("showVisibleOnly"));
        cmd.addOption(new Option("fields", "name"));
        cmd.addOption(new Option("settingsUI", "gui"));
        final Response response = this.runAPICommand(cmd);
        result = new String[response.getWorkItemListSize()];
        int index = 0;
        final WorkItemIterator it = response.getWorkItems();
        while (it.hasNext()) {
            result[index] = it.next().getField("name").getValueAsString();
            ++index;
        }
        return result;
    }
    
    public String[] imGetQueryFields(final String query) throws APIException {
        String[] result = new String[0];
        final Command cmd = new Command("im", "queries");
        cmd.addOption(new Option("showVisibleOnly"));
        cmd.addOption(new Option("fields", "fields"));
        cmd.addOption(new Option("settingsUI", "gui"));
        if (query != null & query.length() > 0) {
            cmd.addSelection(query);
        }
        Response response = null;
        try {
            response = this.runAPICommand(cmd);
            final WorkItem wi = response.getWorkItem(query);
            final Field columns = wi.getField("fields");
            final List list = columns.getList();
            result = new String[list.size()];
            final Iterator it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                result[index] = it.next().getId();
                ++index;
            }
        }
        catch (NoSuchElementException ex) {}
        return result;
    }
    
    public String[] imGetColumnSets() throws APIException {
        String[] result = new String[0];
        final Command cmd = new Command("im", "columnsets");
        cmd.addOption(new Option("fields", "name"));
        cmd.addOption(new Option("settingsUI", "gui"));
        final Response response = this.runAPICommand(cmd);
        final WorkItemIterator it = response.getWorkItems();
        result = new String[response.getWorkItemListSize()];
        int i = 0;
        while (it.hasNext()) {
            result[i] = it.next().getField("name").getValueAsString();
            ++i;
        }
        return result;
    }
    
    public String[] imGetColumnsetFields(final String columnset) throws APIException {
        String[] result = new String[0];
        final Command cmd = new Command("im", "viewcolumnset");
        cmd.addOption(new Option("settingsUI", "gui"));
        if (columnset != null & columnset.length() > 0) {
            cmd.addSelection(columnset);
        }
        Response response = null;
        try {
            response = this.runAPICommand(cmd);
            final WorkItem wi = response.getWorkItem(columnset);
            final Field columns = wi.getField("fields");
            final List list = columns.getList();
            result = new String[list.size()];
            final Iterator it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                result[index] = it.next().getId();
                ++index;
            }
        }
        catch (NoSuchElementException nsee) {
            if (!columnset.equalsIgnoreCase("default")) {
                result = this.imGetColumnsetFields("default");
            }
        }
        return result;
    }
    
    public void launchMKSGUI() throws APIException {
        final Command cmd = new Command("im", "gui");
        this.runAPICommand(cmd);
    }
    
    public WorkItem[] imGetIssuesViewContents(final String query, final String[] fields) throws APIException {
        if (query == null || query.length() == 0) {
            throw new InvalidCommandOptionException("IMCommands.imGetIssuesViewContents: parameter 'query' cannot be null or empty.");
        }
        if (fields == null || fields.length == 0) {
            throw new InvalidCommandOptionException("IMCommands.imGetIssuesViewContents: parameter 'fields' cannot be null or empty.");
        }
        WorkItem[] issues = new WorkItem[0];
        final Command cmd = new Command("im", "issues");
        cmd.addOption(new Option("settingsUI", "gui"));
        cmd.addOption(new Option("query", query));
        final StringBuffer fieldsValue = new StringBuffer();
        for (int i = 0; i < fields.length; ++i) {
            final String field = fields[i];
            if (fieldsValue.length() > 0) {
                fieldsValue.append(",");
            }
            fieldsValue.append(field);
        }
        cmd.addOption(new Option("fields", fieldsValue.toString()));
        final Response response = this.runAPICommand(cmd);
        issues = new WorkItem[response.getWorkItemListSize()];
        int index = 0;
        final WorkItemIterator items = response.getWorkItems();
        while (items.hasNext()) {
            issues[index] = items.next();
            ++index;
        }
        return issues;
    }
    
    public WorkItem[] imGetImplementerCPViewContents(final int issueId) throws APIException {
        WorkItem[] cps = new WorkItem[0];
        final Command cmd = new Command("im", "viewcp");
        cmd.addOption(new Option("filter", "type:implementer"));
        final String issue = String.valueOf(issueId);
        if (issue != null && issue.length() > 0) {
            cmd.addSelection(issue);
            final Response response = this.runAPICommand(cmd);
            cps = new WorkItem[response.getWorkItemListSize()];
            int index = 0;
            final WorkItemIterator items = response.getWorkItems();
            while (items.hasNext()) {
                cps[index] = items.next();
                ++index;
            }
            return cps;
        }
        throw new InvalidCommandSelectionException("IMCommands.imGetImplementerCPViewContents: parameter 'issueId' is invalid.");
    }
}
