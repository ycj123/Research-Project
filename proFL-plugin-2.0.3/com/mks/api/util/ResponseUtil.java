// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.APIError;
import java.util.Enumeration;
import com.mks.api.common.XMLResponseDef;
import com.mks.api.response.ValueList;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import com.mks.api.response.Item;
import com.mks.api.response.ItemList;
import java.util.List;
import com.mks.api.response.Field;
import com.mks.api.response.Result;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.APIException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import com.mks.api.response.Response;

public class ResponseUtil
{
    public static void printResponse(final Response response, final int indent, final PrintStream out) {
        printResponse(response, indent, new PrintWriter(out, true));
    }
    
    public static void printResponse(final Response response, final int indent, final PrintStream out, final boolean recurse) {
        printResponse(response, indent, new PrintWriter(out, true), recurse);
    }
    
    public static void printResponse(final Response response, final int indent, final PrintWriter out) {
        printResponse(response, indent, out, true);
    }
    
    public static void printResponse(final Response response, final int indent, final PrintWriter out, final boolean recurse) {
        final String ind = getIndent(indent);
        out.println();
        out.println("Response:");
        out.println(ind + "App. Name    = " + response.getApplicationName());
        out.println(ind + "Command Name = " + response.getCommandName());
        if (recurse) {
            final SubRoutineIterator sri = response.getSubRoutines();
            while (sri.hasNext()) {
                try {
                    printSubRoutine(sri.next(), indent + 1, out);
                }
                catch (APIException ex2) {
                    printSubRoutine(sri.getLast(), indent + 1, out);
                }
            }
            if (Boolean.getBoolean("com.mks.api.response.showConnection")) {
                out.println(ind + "Connection Host = " + response.getConnectionHostname());
                out.println(ind + "Connection Port = " + response.getConnectionPort());
                out.println(ind + "Connection User = " + response.getConnectionUsername());
            }
            final WorkItemIterator wii = response.getWorkItems();
            while (wii.hasNext()) {
                try {
                    printWorkItem(wii.next(), indent + 1, out);
                }
                catch (APIException ex3) {
                    printWorkItem(wii.getLast(), indent + 1, out);
                }
            }
            try {
                final Result res = response.getResult();
                if (res != null) {
                    printResult(res, indent + 1, out);
                }
                final APIException ex = response.getAPIException();
                if (ex != null) {
                    printAPIException("Main Exception:", ex, indent + 1, out);
                }
                out.println(ind + "Exit Code    = " + response.getExitCode());
            }
            catch (InterruptedException ex4) {}
            out.println();
        }
    }
    
    public static void printField(final Field f, final int indent, final PrintStream out) {
        printField(f, indent, new PrintWriter(out, true));
    }
    
    public static void printField(final Field f, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        String dataType = f.getDataType();
        final boolean thOutput = Boolean.getBoolean("com.mks.api.util.internal.TestHarnessOutput");
        if (thOutput && dataType != null) {
            if (dataType.equals("java.lang.Boolean")) {
                dataType = "boolean";
            }
            else if (dataType.equals("java.util.Date")) {
                dataType = "datetime";
            }
            else if (dataType.equals("java.lang.Double")) {
                dataType = "double";
            }
            else if (dataType.equals("java.lang.Float")) {
                dataType = "float";
            }
            else if (dataType.equals("java.lang.Integer")) {
                dataType = "int";
            }
            else if (dataType.equals("com.mks.api.response.Item")) {
                dataType = "item";
            }
            else if (dataType.equals("com.mks.api.response.ItemList")) {
                dataType = "itemlist";
            }
            else if (dataType.equals("java.lang.Long")) {
                dataType = "long";
            }
            else if (dataType.equals("java.lang.String")) {
                dataType = "string";
            }
            else if (dataType.equals("com.mks.api.response.ValueList")) {
                dataType = "valuelist";
            }
        }
        out.println(getIndent(indent - 1) + "Field:");
        out.println(ind + "Name      = " + f.getName());
        if (f.getDisplayName() != null && !f.getDisplayName().equals(f.getName())) {
            out.println(ind + "Display Name = " + f.getDisplayName());
        }
        out.println(ind + "Data Type = " + dataType);
        final Object value = f.getValue();
        if (value instanceof List) {
            if (value instanceof ItemList) {
                printItemList((ItemList)value, indent + 1, out);
            }
            else {
                printList((List)value, indent + 1, out);
            }
        }
        else if (value instanceof Item) {
            printItem((Item)value, indent + 1, out);
        }
        else if (thOutput && value instanceof Date) {
            final Date date = (Date)value;
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            final StringBuffer sb = new StringBuffer();
            sb.append(cal.get(2) + 1);
            sb.append("/");
            final int dom = cal.get(5) + 1;
            sb.append(((dom < 10) ? "0" : "") + dom);
            sb.append("/");
            sb.append(cal.get(1));
            sb.append(" ");
            final int hour = cal.get(11);
            sb.append(((hour < 10) ? "0" : "") + hour);
            sb.append(":");
            final int min = cal.get(12);
            sb.append(((min < 10) ? "0" : "") + min);
            sb.append(":");
            final int sec = cal.get(13);
            sb.append(((sec < 10) ? "0" : "") + sec);
            out.println(ind + "Value     = " + sb.toString());
        }
        else {
            out.println(ind + "Value     = " + value);
        }
        if (f.getValue() != null && !(f.getValue() instanceof List) && !f.getValueAsString().equals(f.getValue().toString())) {
            out.println(ind + "Display Value = " + f.getValueAsString());
        }
    }
    
    public static void printResult(final Result res, final int indent, final PrintStream out) {
        printResult(res, indent, new PrintWriter(out, true));
    }
    
    public static void printResult(final Result res, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "Result:");
        out.println(ind + "Message    = " + res.getMessage());
        final Iterator it = res.getFields();
        while (it.hasNext()) {
            printField(it.next(), indent + 1, out);
        }
    }
    
    public static void printList(final List list, final int indent, final PrintStream out) {
        printList(list, indent, new PrintWriter(out, true));
    }
    
    public static void printList(final List list, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "List:");
        for (final Object value : list) {
            out.print(ind + "Value = " + value);
            if (list instanceof ValueList) {
                final String displayValue = ((ValueList)list).getDisplayValueOf(value);
                if (displayValue != null) {
                    out.print(", Display Value = " + displayValue);
                }
            }
            out.println();
        }
    }
    
    public static void printItemList(final ItemList list, final int indent, final PrintStream out) {
        printItemList(list, indent, new PrintWriter(out, true));
    }
    
    public static void printItemList(final ItemList list, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "ItemList:");
        final Iterator it = list.getItems();
        while (it.hasNext()) {
            printItem(it.next(), indent + 1, out);
        }
    }
    
    public static void printItem(final Item item, final int indent, final PrintStream out) {
        printItem(item, indent, new PrintWriter(out, true));
    }
    
    public static void printItem(final Item item, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "Item:");
        out.println(ind + "Id         = " + item.getId());
        out.println(ind + "Context    = " + item.getContext());
        out.println(ind + "Model Type = " + item.getModelType());
        if (Boolean.getBoolean("com.mks.api.response.showAllProperties")) {
            final Enumeration keys = item.getContextKeys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                final String value = item.getContext(key);
                if (!key.equals(XMLResponseDef.XML_ID_ATTR) && !key.equals(XMLResponseDef.XML_CONTEXT_ATTR)) {
                    if (key.equals(XMLResponseDef.XML_MODELTYPE_ATTR)) {
                        continue;
                    }
                    if (key.equals(XMLResponseDef.XML_DISPLAYID_ATTR)) {
                        out.println(ind + "Display Id = " + value);
                    }
                    else {
                        out.println(ind + key + " = " + value);
                    }
                }
            }
        }
        final Iterator it = item.getFields();
        while (it.hasNext()) {
            printField(it.next(), indent + 1, out);
        }
    }
    
    public static void printAPIError(final APIError err, final int indent, final PrintStream out) {
        printAPIError("APIError:", err, indent, new PrintWriter(out, true));
    }
    
    public static void printAPIError(final String label, final APIError err, final int indent, final PrintStream out) {
        printAPIError(label, err, indent, new PrintWriter(out, true));
    }
    
    public static void printAPIError(final APIError err, final int indent, final PrintWriter out) {
        printAPIError("APIError:", err, indent, out);
    }
    
    public static void printAPIError(final String label, final APIError err, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + label);
        out.println(ind + "Class   = " + err.getClass().getName());
        out.println(ind + "Message = " + err.getMessage());
        final Iterator it = err.getFields();
        while (it.hasNext()) {
            printField(it.next(), indent + 1, out);
        }
        err.printStackTrace(out);
    }
    
    public static void printAPIException(final APIException ex, final int indent, final PrintStream out) {
        printAPIException("APIException:", ex, indent, new PrintWriter(out, true));
    }
    
    public static void printAPIException(final APIException ex, final int indent, final PrintWriter out) {
        printAPIException("APIException:", ex, indent, out);
    }
    
    public static void printAPIException(final String label, final APIException ex, final int indent, final PrintStream out) {
        printAPIException(label, ex, indent, new PrintWriter(out, true));
    }
    
    public static void printAPIException(final String label, final APIException ex, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + label);
        out.println(ind + "Class   = " + ex.getClass().getName());
        out.println(ind + "Message = " + ex.getMessage());
        final Iterator it = ex.getFields();
        while (it.hasNext()) {
            printField(it.next(), indent + 1, out);
        }
        ex.printStackTrace(out);
    }
    
    public static void printSubRoutine(final SubRoutine sr, final int indent, final PrintStream out) {
        printSubRoutine(sr, indent, new PrintWriter(out, true));
    }
    
    public static void printSubRoutine(final SubRoutine sr, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "Sub-Routine:");
        out.println(ind + "Routine = " + sr.getRoutine());
        final SubRoutineIterator sri = sr.getSubRoutines();
        while (sri.hasNext()) {
            try {
                printSubRoutine(sri.next(), indent + 1, out);
            }
            catch (APIException ex2) {
                printSubRoutine(sri.getLast(), indent + 1, out);
            }
        }
        final WorkItemIterator wii = sr.getWorkItems();
        while (wii.hasNext()) {
            try {
                printWorkItem(wii.next(), indent + 1, out);
            }
            catch (APIException ex3) {
                printWorkItem(wii.getLast(), indent + 1, out);
            }
        }
        final Result res = sr.getResult();
        if (res != null) {
            printResult(res, indent + 1, out);
        }
        final APIException ex = sr.getAPIException();
        if (ex != null) {
            printAPIException("Sub-Routine Exception:", ex, indent + 1, out);
        }
    }
    
    public static void printWorkItem(final WorkItem wi, final int indent, final PrintStream out) {
        printWorkItem(wi, indent, new PrintWriter(out, true));
    }
    
    public static void printWorkItem(final WorkItem wi, final int indent, final PrintWriter out) {
        final String ind = getIndent(indent);
        out.println(getIndent(indent - 1) + "Work Item:");
        out.println(ind + "Id         = " + wi.getId());
        out.println(ind + "Context    = " + wi.getContext());
        out.println(ind + "Model Type = " + wi.getModelType());
        if (Boolean.getBoolean("com.mks.api.response.showAllProperties")) {
            final Enumeration keys = wi.getContextKeys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                final String value = wi.getContext(key);
                if (!key.equals(XMLResponseDef.XML_ID_ATTR) && !key.equals(XMLResponseDef.XML_CONTEXT_ATTR)) {
                    if (key.equals(XMLResponseDef.XML_MODELTYPE_ATTR)) {
                        continue;
                    }
                    if (key.equals(XMLResponseDef.XML_DISPLAYID_ATTR)) {
                        out.println(ind + "Display Id = " + value);
                    }
                    else {
                        out.println(ind + key + " = " + value);
                    }
                }
            }
        }
        final SubRoutineIterator sri = wi.getSubRoutines();
        while (sri.hasNext()) {
            try {
                printSubRoutine(sri.next(), indent + 1, out);
            }
            catch (APIException ex2) {
                printSubRoutine(sri.getLast(), indent + 1, out);
            }
        }
        final Iterator it = wi.getFields();
        while (it.hasNext()) {
            printField(it.next(), indent + 1, out);
        }
        final Result res = wi.getResult();
        if (res != null) {
            printResult(res, indent + 1, out);
        }
        final APIException ex = wi.getAPIException();
        if (ex != null) {
            printAPIException("Work Item Exception:", ex, indent + 1, out);
        }
    }
    
    private static String getIndent(final int size) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; ++i) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
