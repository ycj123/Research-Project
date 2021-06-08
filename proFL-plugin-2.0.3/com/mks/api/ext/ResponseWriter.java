// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

import java.util.NoSuchElementException;
import java.util.HashMap;
import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.APIException;
import com.mks.api.response.Result;
import com.mks.api.response.Response;
import java.util.Iterator;
import com.mks.api.response.Field;
import com.mks.api.response.Item;
import java.util.ArrayList;
import com.mks.api.response.ItemList;
import java.util.Collection;
import java.util.Map;
import java.util.List;

public abstract class ResponseWriter
{
    public abstract void writeConnection(final String p0, final int p1, final String p2);
    
    public abstract void startSubRoutine(final String p0);
    
    public abstract void endSubRoutine();
    
    public abstract void startWorkItem(final String p0, final String p1, final String p2);
    
    public abstract void startWorkItem(final Object p0);
    
    public abstract void endWorkItem();
    
    protected abstract void writeItemField(final String p0, final String p1, final String p2, final String p3, final String p4, final List p5);
    
    protected abstract void writeObjectField(final String p0, final String p1, final Object p2, final String p3);
    
    public abstract void writeItemField(final String p0, final String p1, final String p2, final String p3, final String p4, final Map p5);
    
    public abstract void writeItemField(final String p0, final String p1, final Object p2, final Map p3);
    
    public abstract void writeItemListField(final String p0, final String p1, final Collection p2);
    
    public final void writeField(final String field, final Object value) {
        this.writeField(field, null, value, null);
    }
    
    public final void writeField(final String field, final String displayName, final Object value) {
        this.writeField(field, displayName, value, null);
    }
    
    public final void writeField(final String field, final String displayName, final Object value, final String displayValue) {
        if (value instanceof Map) {
            this.writeItemField(field, displayName, "fieldmap", null, "Map", (Map)value);
        }
        else if (value instanceof ItemList) {
            final ItemList list = (ItemList)value;
            final List items = new ArrayList(list.getItemListSize());
            final Iterator it = list.getItems();
            while (it.hasNext()) {
                items.add(it.next());
            }
            this.writeItemListField(field, displayName, items);
        }
        else if (value instanceof Item) {
            final Item item = (Item)value;
            final List itemFields = new ArrayList(item.getFieldListSize());
            final Iterator it = item.getFields();
            while (it.hasNext()) {
                final Field itemField = it.next();
                itemFields.add(itemField);
            }
            this.writeItemField(field, displayName, item.getId(), item.getContext(), item.getModelType(), itemFields);
        }
        else {
            this.writeObjectField(field, displayName, value, displayValue);
        }
    }
    
    public final void writeItemField(final String field, final String itemID, final String context, final String itemType, final Map itemFields) {
        this.writeItemField(field, field, itemID, context, itemType, itemFields);
    }
    
    public final void writeItemField(final String field, final Object item, final Map itemFields) {
        this.writeItemField(field, field, item, itemFields);
    }
    
    public abstract void startItemField(final String p0, final String p1, final String p2, final String p3, final String p4);
    
    public final void startItemField(final String field, final String itemID, final String context, final String modelType) {
        this.startItemField(field, field, itemID, context, modelType);
    }
    
    public final void startItemField(final String field, final Object item) {
        this.startItemField(field, field, item);
    }
    
    public abstract void startItemField(final String p0, final String p1, final Object p2);
    
    public abstract void endItemField();
    
    public final void writeItemListField(final String field, final Collection items) {
        this.writeItemListField(field, field, items);
    }
    
    public final void writeField(final String field, final int value) {
        this.writeObjectField(field, null, new Integer(value), null);
    }
    
    public final void writeField(final String field, final boolean value) {
        this.writeObjectField(field, null, value, null);
    }
    
    public final void writeField(final String field, final float value) {
        this.writeObjectField(field, null, new Float(value), null);
    }
    
    public final void writeField(final String field, final double value) {
        this.writeObjectField(field, null, new Double(value), null);
    }
    
    public final void writeField(final String field, final long value) {
        this.writeObjectField(field, null, new Long(value), null);
    }
    
    public abstract void writeResult(final String p0, final String p1, final String p2, final String p3, final Map p4);
    
    public final void writeResult(final String message, final Object resultant) {
        this.writeResult(message, resultant, null);
    }
    
    public abstract void writeResult(final String p0, final Object p1, final Map p2);
    
    public abstract void writeException(final Exception p0);
    
    public final void writeAPIObj(final Response response) throws InterruptedException {
        if (response == null) {
            return;
        }
        this.write(response.getSubRoutines());
        this.writeConnection(response.getConnectionHostname(), response.getConnectionPort(), response.getConnectionUsername());
        this.write(response.getWorkItems());
        final Result result = response.getResult();
        if (result != null) {
            this.writeAPIObj(result);
        }
        final APIException ex = response.getAPIException();
        if (ex != null) {
            this.writeException(ex);
        }
    }
    
    public final void writeAsSubRoutine(final Response response) throws InterruptedException {
        if (response == null) {
            return;
        }
        this.startSubRoutine(response.getCommandName());
        this.writeAPIObj(response);
        this.endSubRoutine();
    }
    
    private void write(final SubRoutineIterator sri) {
        while (sri.hasNext()) {
            try {
                this.writeAPIObj(sri.next());
            }
            catch (APIException ex) {
                this.writeAPIObj(sri.getLast());
            }
        }
    }
    
    private void write(final WorkItemIterator wii) {
        while (wii.hasNext()) {
            try {
                this.writeAPIObj(wii.next());
            }
            catch (APIException ex) {
                this.writeAPIObj(wii.getLast());
            }
        }
    }
    
    public final void writeAPIObj(final SubRoutine sr) {
        if (sr == null) {
            return;
        }
        this.startSubRoutine(sr.getRoutine());
        this.write(sr.getSubRoutines());
        this.write(sr.getWorkItems());
        final Result result = sr.getResult();
        if (result != null) {
            this.writeAPIObj(result);
        }
        final APIException ex = sr.getAPIException();
        if (ex != null) {
            this.writeException(ex);
        }
        this.endSubRoutine();
    }
    
    public final void writeAPIObj(final WorkItem wi) {
        this.writeAPIObj(wi, null);
    }
    
    public final void writeAPIObj(final WorkItem wi, final Field field) {
        if (wi == null) {
            return;
        }
        this.startWorkItem(wi);
        this.write(wi.getSubRoutines());
        final Iterator it = wi.getFields();
        while (it.hasNext()) {
            this.writeAPIObj(it.next());
        }
        if (field != null) {
            this.writeAPIObj(field);
        }
        final Result result = wi.getResult();
        if (result != null) {
            this.writeAPIObj(result);
        }
        final APIException ex = wi.getAPIException();
        if (ex != null) {
            this.writeException(ex);
        }
        this.endWorkItem();
    }
    
    public final void writeAPIObj(final Field f) {
        if (f != null) {
            this.writeField(f.getName(), f.getDisplayName(), f.getValue(), f.getValueAsString());
        }
    }
    
    public final void writeAPIObj(final Result r) {
        if (r == null) {
            return;
        }
        Item resultant = null;
        final Map resultFields = new HashMap(r.getFieldListSize());
        try {
            resultant = r.getPrimaryValue();
        }
        catch (NoSuchElementException ex) {}
        final Iterator it = r.getFields();
        while (it.hasNext()) {
            final Field f = it.next();
            if (f.getValue() != resultant) {
                resultFields.put(f.getName(), f.getValue());
            }
        }
        this.writeResult(r.getMessage(), resultant, resultFields);
    }
}
