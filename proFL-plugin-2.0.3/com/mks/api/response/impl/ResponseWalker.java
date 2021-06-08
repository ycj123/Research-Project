// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.util.ResponseUtil;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.APIInternalError;
import com.mks.api.response.FieldContainer;
import com.mks.api.response.WorkItemContainer;
import java.util.Iterator;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import com.mks.api.util.EscapedStringTokenizer;
import java.util.Collection;
import com.mks.api.IntegrationPointFactory;
import java.util.ArrayList;
import com.mks.api.util.MKSLogger;
import com.mks.api.response.ValueList;
import com.mks.api.response.APIException;
import com.mks.api.response.Result;
import com.mks.api.response.Field;
import com.mks.api.response.ItemList;
import com.mks.api.response.Item;
import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import java.util.List;
import com.mks.api.response.Response;

public class ResponseWalker
{
    private Response response;
    private Object current;
    private List parents;
    private Object value;
    private static final char LIST_DELIMITER = '\u0001';
    private static final char RECORD_DELIMITER = '\u0002';
    private SubRoutine subRoutine;
    private WorkItem workItem;
    private Item item;
    private ItemList itemList;
    private Field field;
    private Result result;
    private APIException exception;
    private ValueList list;
    private Object currentWorkingObject;
    private int srIdx;
    private int wiIdx;
    private int ilIdx;
    private int iIdx;
    private int fIdx;
    private int lIdx;
    public static final String DIR_DELIM = ";";
    public static final String VAL_DELIM = "=";
    public static final String FIRST = "first";
    public static final String NEXT = "next";
    public static final String LAST = "last";
    public static final String PARENT = "parent";
    public static final String RESPONSE = "response";
    public static final String WORK_ITEM = "workitem";
    public static final String SUB_ROUTINE = "subroutine";
    public static final String ITEM_LIST = "itemlist";
    public static final String ITEM = "item";
    public static final String FIELD = "field";
    public static final String LIST = "list";
    public static final String RESULT = "result";
    public static final String EXCEPTION = "exception";
    public static final String PRINT = "print";
    private MKSLogger apiLogger;
    
    public ResponseWalker(final Response response) {
        this.response = response;
        this.srIdx = -1;
        this.wiIdx = -1;
        this.ilIdx = -1;
        this.iIdx = -1;
        this.fIdx = -1;
        this.lIdx = -1;
        this.parents = new ArrayList();
        this.current = this.response;
        this.apiLogger = IntegrationPointFactory.getLogger();
        this.currentWorkingObject = this.response;
    }
    
    private CurrentState captureStartState() {
        final CurrentState cs = new CurrentState(this.current, this.currentWorkingObject, this.parents);
        cs.srIdx = this.srIdx;
        cs.wiIdx = this.wiIdx;
        cs.ilIdx = this.ilIdx;
        cs.iIdx = this.iIdx;
        cs.fIdx = this.fIdx;
        cs.lIdx = this.lIdx;
        return cs;
    }
    
    private void restoreStartState(final CurrentState currentState) {
        this.current = currentState.currentPtr;
        this.parents = new ArrayList(currentState.parents);
        this.srIdx = currentState.srIdx;
        this.wiIdx = currentState.wiIdx;
        this.ilIdx = currentState.ilIdx;
        this.iIdx = currentState.iIdx;
        this.fIdx = currentState.fIdx;
        this.lIdx = currentState.lIdx;
        this.currentWorkingObject = currentState.currentWorkingPtr;
    }
    
    public void walk(final String directives) throws CommandException {
        final EscapedStringTokenizer st = new EscapedStringTokenizer(directives, ";", false);
        final CurrentState cs = this.captureStartState();
        String token = null;
        this.apiLogger.message(this, "API", 10, "Directives: " + directives);
        try {
            while (st.hasMoreTokens()) {
                token = st.nextToken();
                if (token.equals("response")) {
                    this.apiLogger.message(this, "API", 10, "Resetting ResponseWalker pointers.");
                    this.parents.clear();
                    this.current = this.response;
                }
                else {
                    final EscapedStringTokenizer ist = new EscapedStringTokenizer(token, "=", false);
                    final String targetType = ist.nextToken();
                    final String targetId = ist.hasMoreTokens() ? ist.nextToken() : "";
                    this.apiLogger.message(this, "API", 10, "Directive: " + token);
                    if (targetType.equals("print")) {
                        this.printCurrentNode();
                    }
                    else if (targetType.equals("parent")) {
                        this.setCurrent(this.parents.remove(this.parents.size() - 1));
                    }
                    else {
                        this.checkPointers(targetType);
                        if (this.current == this.subRoutine) {
                            this.walkSubRoutine(targetType, targetId);
                        }
                        else if (this.current == this.workItem) {
                            this.walkWorkItem(targetType, targetId);
                        }
                        else if (this.current == this.item) {
                            this.walkItem(targetType, targetId);
                        }
                        else if (this.current == this.itemList) {
                            this.walkItemList(targetType, targetId);
                        }
                        else if (this.current == this.list) {
                            this.walkList(targetType, targetId);
                        }
                        else if (this.current == this.result) {
                            this.walkResult(targetType, targetId);
                        }
                        else if (this.current == this.exception) {
                            this.walkException(targetType, targetId);
                        }
                        else if (this.current == this.field) {
                            this.walkField(targetType, targetId);
                        }
                        else {
                            if (this.current != this.response) {
                                continue;
                            }
                            this.walkResponse(targetType, targetId);
                        }
                    }
                }
            }
        }
        catch (CommandException ex) {
            this.restoreStartState(cs);
            this.apiLogger.exception(this, "API", 0, ex);
            throw ex;
        }
        catch (NoSuchElementException ex3) {
            this.restoreStartState(cs);
            final String msg = "Invalid directive: " + token;
            this.apiLogger.message(this, "API", 0, msg);
            throw new InvalidDirectiveException(msg);
        }
        catch (IndexOutOfBoundsException ex2) {
            this.restoreStartState(cs);
            this.apiLogger.exception(this, "API", 0, ex2);
            throw new InvalidDirectiveException(ex2.getMessage());
        }
        catch (Throwable t) {
            this.restoreStartState(cs);
            this.apiLogger.exception(this, "API", 0, t);
            throw new CommandException(t);
        }
    }
    
    public String getValue() throws CommandException {
        if (this.value == null) {
            final String msg = "Invalid node to retrieve a value from.";
            throw new InvalidValueException(msg);
        }
        if (this.value instanceof Field) {
            return this.getFieldValue((Field)this.value);
        }
        if (this.value instanceof Item) {
            return ((Item)this.value).getId();
        }
        return this.value.toString();
    }
    
    public Object getCurrentObject() throws CommandException {
        if (this.currentWorkingObject != null) {
            return this.currentWorkingObject;
        }
        final String msg = "Invalid node to retrieve a value from.";
        throw new InvalidValueException(msg);
    }
    
    private String getFieldValue(final Field field) throws CommandException {
        Object v = field.getValue();
        if (v instanceof ItemList || v instanceof ValueList) {
            final StringBuffer sb = new StringBuffer(1024);
            final Iterator it = ((List)v).iterator();
            if (it.hasNext()) {
                final Object o = it.next();
                if (o instanceof Item) {
                    sb.append(((Item)o).getId());
                }
                else {
                    sb.append(o);
                }
            }
            while (it.hasNext()) {
                sb.append('\u0001');
                final Object o = it.next();
                if (o instanceof Item) {
                    sb.append(((Item)o).getId());
                }
                else {
                    sb.append(o);
                }
            }
            return sb.toString();
        }
        if (v != null) {
            if (v instanceof Date) {
                final SimpleDateFormat sdf = new SimpleDateFormat();
                final StringBuffer sb2 = new StringBuffer(27);
                sdf.applyPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");
                v = sdf.format((Date)v, sb2, new FieldPosition(0));
            }
            else if (v instanceof Item) {
                return ((Item)v).getId();
            }
            return v.toString();
        }
        final String msg = "Field value: <null>";
        throw new InvalidValueException(msg);
    }
    
    public String getRecordValue() throws CommandException {
        if (this.currentWorkingObject == null) {
            final String msg = "Response object model pointer not set!";
            throw new InvalidValueException(msg);
        }
        try {
            final StringBuffer sb = new StringBuffer(1024);
            if (this.currentWorkingObject instanceof WorkItemContainer) {
                final WorkItemIterator wii = ((WorkItemContainer)this.currentWorkingObject).getWorkItems();
                WorkItem wi = null;
                if (wii.hasNext()) {
                    try {
                        wi = wii.next();
                    }
                    catch (APIException ex2) {
                        wi = wii.getLast();
                    }
                    sb.append(wi.getId());
                }
                while (wii.hasNext()) {
                    try {
                        wi = wii.next();
                    }
                    catch (APIException ex2) {
                        wi = wii.getLast();
                    }
                    sb.append('\u0002');
                    sb.append(wi.getId());
                }
            }
            else {
                if (!(this.currentWorkingObject instanceof FieldContainer)) {
                    final String msg2 = "Cannot return record.  Invalid target node: " + this.currentWorkingObject;
                    throw new InvalidValueException(msg2);
                }
                final Iterator it = ((FieldContainer)this.currentWorkingObject).getFields();
                if (it.hasNext()) {
                    try {
                        sb.append(this.getFieldValue(it.next()));
                    }
                    catch (InvalidValueException ex4) {}
                }
                while (it.hasNext()) {
                    sb.append('\u0002');
                    try {
                        sb.append(this.getFieldValue(it.next()));
                    }
                    catch (InvalidValueException ex3) {}
                }
            }
            return sb.toString();
        }
        catch (APIInternalError ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new InvalidValueException(ex);
        }
    }
    
    private void walkResponse(final String target, final String id) throws CommandException {
        try {
            if (this.isResultOrException("response", target, id)) {
                return;
            }
            final boolean lookupById = this.lookupById(target, id);
            if (target.equals("subroutine")) {
                final String msg = "Walking Response->SubRoutine(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.subRoutine = this.response.getSubRoutine(id);
                    this.current = this.subRoutine;
                    this.currentWorkingObject = this.subRoutine;
                }
                else {
                    if (this.srIdx == Integer.MAX_VALUE) {
                        this.srIdx = this.response.getSubRoutineListSize() - 1;
                    }
                    this.parents.add(this.response);
                    this.current = this.response;
                    final SubRoutineIterator it = this.response.getSubRoutines();
                    for (int i = 0; i < this.srIdx; ++i) {
                        this.subRoutine = it.next();
                    }
                    this.currentWorkingObject = this.subRoutine;
                }
            }
            else {
                if (!target.equals("workitem")) {
                    final String msg = "Invalid directive: Response->" + target;
                    this.apiLogger.message(this, "API", 0, msg);
                    throw new InvalidDirectiveException(msg);
                }
                final String msg = "Walking Response->WorkItem(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.workItem = this.response.getWorkItem(id);
                    this.current = this.workItem;
                    this.currentWorkingObject = this.workItem;
                }
                else {
                    if (this.wiIdx == Integer.MAX_VALUE) {
                        this.wiIdx = this.response.getWorkItemListSize() - 1;
                    }
                    this.parents.add(this.response);
                    this.current = this.response;
                    final WorkItemIterator it2 = this.response.getWorkItems();
                    for (int i = 0; i < this.wiIdx; ++i) {
                        this.workItem = it2.next();
                    }
                    this.currentWorkingObject = this.workItem;
                }
                this.value = this.workItem;
            }
        }
        catch (APIInternalError ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new CommandException(ex);
        }
        catch (APIException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            throw new CommandException(ex2);
        }
    }
    
    private void walkSubRoutine(final String target, final String id) throws CommandException {
        try {
            if (this.isResultOrException("subroutine", target, id)) {
                return;
            }
            final boolean lookupById = this.lookupById(target, id);
            if (target.equals("subroutine")) {
                final String msg = "Walking SubRoutine->SubRoutine(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.parents.add(this.subRoutine);
                    this.subRoutine = this.subRoutine.getSubRoutine(id);
                    this.current = this.subRoutine;
                    this.currentWorkingObject = this.subRoutine;
                }
                else {
                    if (this.srIdx == Integer.MAX_VALUE) {
                        this.srIdx = this.subRoutine.getSubRoutineListSize() - 1;
                    }
                    this.parents.add(this.subRoutine);
                    this.current = this.subRoutine;
                    final SubRoutineIterator it = this.subRoutine.getSubRoutines();
                    for (int i = 0; i < this.srIdx; ++i) {
                        this.subRoutine = it.next();
                    }
                    this.currentWorkingObject = this.subRoutine;
                }
            }
            else {
                if (!target.equals("workitem")) {
                    final String msg = "Invalid directive: SubRoutine->" + target;
                    this.apiLogger.message(this, "API", 0, msg);
                    throw new InvalidDirectiveException(msg);
                }
                final String msg = "Walking SubRoutine->WorkItem(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.parents.add(this.subRoutine);
                    this.workItem = this.subRoutine.getWorkItem(id);
                    this.current = this.workItem;
                    this.currentWorkingObject = this.workItem;
                }
                else {
                    if (this.wiIdx == Integer.MAX_VALUE) {
                        this.wiIdx = this.subRoutine.getWorkItemListSize() - 1;
                    }
                    this.parents.add(this.subRoutine);
                    this.current = this.subRoutine;
                    final WorkItemIterator it2 = this.subRoutine.getWorkItems();
                    for (int i = 0; i < this.wiIdx; ++i) {
                        this.workItem = it2.next();
                    }
                    this.currentWorkingObject = this.workItem;
                }
                this.value = this.workItem;
            }
        }
        catch (APIInternalError ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new CommandException(ex);
        }
        catch (APIException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            throw new CommandException(ex2);
        }
    }
    
    private void walkWorkItem(final String target, final String id) throws CommandException {
        try {
            if (this.isResultOrException("workitem", target, id)) {
                return;
            }
            final boolean lookupById = this.lookupById(target, id);
            if (target.equals("subroutine")) {
                final String msg = "Walking WorkItem->SubRoutine(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.parents.add(this.workItem);
                    this.subRoutine = this.workItem.getSubRoutine(id);
                    this.current = this.subRoutine;
                    this.currentWorkingObject = this.subRoutine;
                }
                else {
                    if (this.srIdx == Integer.MAX_VALUE) {
                        this.srIdx = this.workItem.getSubRoutineListSize() - 1;
                    }
                    this.parents.add(this.workItem);
                    this.current = this.workItem;
                    final SubRoutineIterator it = this.workItem.getSubRoutines();
                    for (int i = 0; i < this.srIdx; ++i) {
                        this.subRoutine = it.next();
                    }
                    this.currentWorkingObject = this.subRoutine;
                }
            }
            else {
                if (!target.equals("field")) {
                    final String msg = "Invalid directive: WorkItem->" + target;
                    this.apiLogger.message(this, "API", 0, msg);
                    throw new InvalidDirectiveException(msg);
                }
                final String msg = "Walking WorkItem->Field(" + id + ")";
                this.apiLogger.message(this, "API", 10, msg);
                if (lookupById) {
                    this.parents.add(this.workItem);
                    this.field = this.workItem.getField(id);
                    this.current = this.field;
                    this.currentWorkingObject = this.field;
                }
                else {
                    if (this.fIdx == Integer.MAX_VALUE) {
                        this.fIdx = this.workItem.getFieldListSize() - 1;
                    }
                    this.parents.add(this.workItem);
                    this.current = this.workItem;
                    final Iterator it2 = this.workItem.getFields();
                    for (int i = 0; i < this.fIdx; ++i) {
                        this.field = it2.next();
                    }
                    this.currentWorkingObject = this.field;
                }
                this.value = this.field;
            }
        }
        catch (APIInternalError ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new CommandException(ex);
        }
        catch (APIException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            throw new CommandException(ex2);
        }
    }
    
    private void walkItemList(final String target, final String id) throws CommandException {
        final boolean lookupById = this.lookupById(target, id);
        if (target.equals("item")) {
            final String msg = "Walking ItemList->Item(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            if (lookupById) {
                this.parents.add(this.itemList);
                this.item = this.itemList.getItem(id);
                this.current = this.item;
                this.currentWorkingObject = this.item;
            }
            else {
                if (this.iIdx == Integer.MAX_VALUE) {
                    this.iIdx = this.itemList.getItemListSize() - 1;
                }
                this.parents.add(this.itemList);
                final Iterator it = this.itemList.getItems();
                for (int i = 0; i < this.iIdx; ++i) {
                    this.item = it.next();
                }
                this.current = this.itemList;
                this.currentWorkingObject = this.item;
            }
            this.value = this.item;
            return;
        }
        final String msg = "Invalid directive: ItemList->" + target;
        this.apiLogger.message(this, "API", 0, msg);
        throw new InvalidDirectiveException(msg);
    }
    
    private void walkList(final String target, final String id) throws CommandException {
        if (this.lookupById(target, id)) {
            final String msg = "Invalid directive: List->" + target;
            this.apiLogger.message(this, "API", 0, msg);
            throw new InvalidDirectiveException(msg);
        }
        if (target.equals("list")) {
            final String msg = "Walking List(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            if (this.lIdx == Integer.MAX_VALUE) {
                this.lIdx = this.list.size() - 1;
            }
            this.value = this.list.get(this.lIdx);
            this.currentWorkingObject = this.value;
            return;
        }
        final String msg = "Invalid directive: List->" + target;
        this.apiLogger.message(this, "API", 0, msg);
        throw new InvalidDirectiveException(msg);
    }
    
    private void walkItem(final String target, final String id) throws CommandException {
        final boolean lookupById = this.lookupById(target, id);
        if (target.equals("field")) {
            final String msg = "Walking Item->Field(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            if (lookupById) {
                this.parents.add(this.item);
                this.field = this.item.getField(id);
            }
            else {
                if (this.fIdx == Integer.MAX_VALUE) {
                    this.fIdx = this.item.getFieldListSize() - 1;
                }
                this.parents.add(this.item);
                final Iterator it = this.item.getFields();
                for (int i = 0; i < this.fIdx; ++i) {
                    this.field = it.next();
                }
            }
            this.current = this.field;
            this.value = this.field;
            this.currentWorkingObject = this.value;
            return;
        }
        final String msg = "Invalid directive: Item->" + target;
        this.apiLogger.message(this, "API", 0, msg);
        throw new InvalidDirectiveException(msg);
    }
    
    private void walkField(final String target, final String id) throws CommandException {
        if (target.equals("list") || target.equals("itemlist")) {
            final String msg = "Walking Field->List(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            this.parents.add(this.field);
            final List l = this.field.getList();
            if (l instanceof ItemList) {
                this.itemList = (ItemList)l;
                this.current = this.itemList;
                this.currentWorkingObject = this.itemList;
            }
            else {
                this.list = (ValueList)l;
                this.current = this.list;
                this.currentWorkingObject = this.list;
            }
        }
        else {
            if (!target.equals("item")) {
                final String msg = "Invalid directive: Field->" + target;
                this.apiLogger.message(this, "API", 0, msg);
                throw new InvalidDirectiveException(msg);
            }
            final String msg = "Walking Field->Item(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            this.parents.add(this.field);
            this.item = this.field.getItem();
            this.current = this.item;
            this.currentWorkingObject = this.value;
        }
    }
    
    private void walkResult(final String target, final String id) throws CommandException {
        final boolean lookupById = this.lookupById(target, id);
        if (target.equals("field")) {
            final String msg = "Walking Result->Field(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            if (lookupById) {
                this.parents.add(this.result);
                this.field = this.result.getField(id);
            }
            else {
                if (this.fIdx == Integer.MAX_VALUE) {
                    this.fIdx = this.result.getFieldListSize() - 1;
                }
                this.parents.add(this.result);
                final Iterator it = this.result.getFields();
                for (int i = 0; i < this.fIdx; ++i) {
                    this.field = it.next();
                }
            }
            this.current = this.field;
            this.value = this.field;
            this.currentWorkingObject = this.field;
            return;
        }
        final String msg = "Invalid directive: Result->" + target;
        this.apiLogger.message(this, "API", 0, msg);
        throw new InvalidDirectiveException(msg);
    }
    
    private void walkException(final String target, final String id) throws CommandException {
        final boolean lookupById = this.lookupById(target, id);
        if (target.equals("field")) {
            final String msg = "Walking Exception->Field(" + id + ")";
            this.apiLogger.message(this, "API", 10, msg);
            if (lookupById) {
                this.parents.add(this.exception);
                this.field = this.exception.getField(id);
            }
            else {
                if (this.fIdx == Integer.MAX_VALUE) {
                    this.fIdx = this.exception.getFieldListSize() - 1;
                }
                this.parents.add(this.result);
                final Iterator it = this.exception.getFields();
                for (int i = 0; i < this.fIdx; ++i) {
                    this.field = it.next();
                }
            }
            this.current = this.field;
            this.value = this.field;
            this.currentWorkingObject = this.field;
            return;
        }
        final String msg = "Invalid directive: APIException->" + target;
        this.apiLogger.message(this, "API", 0, msg);
        throw new InvalidDirectiveException(msg);
    }
    
    private boolean isResultOrException(final String source, final String target, final String id) throws CommandException {
        try {
            if (target.equals("result")) {
                if (source.equals("response")) {
                    final String msg = "Walking Response->Result(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.response);
                    this.result = this.response.getResult();
                }
                else if (source.equals("subroutine")) {
                    final String msg = "Walking SubRoutine->Result(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.subRoutine);
                    this.result = this.subRoutine.getResult();
                }
                else if (source.equals("workitem")) {
                    final String msg = "Walking WorkItem->Result(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.workItem);
                    this.result = this.workItem.getResult();
                }
                if (this.result != null) {
                    this.currentWorkingObject = this.result;
                    return true;
                }
            }
            else if (target.equals("exception")) {
                if (source.equals("response")) {
                    final String msg = "Walking Response->Exception(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.response);
                    this.exception = this.response.getAPIException();
                }
                else if (source.equals("subroutine")) {
                    final String msg = "Walking SubRoutine->Exception(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.subRoutine);
                    this.exception = this.subRoutine.getAPIException();
                }
                else if (source.equals("workitem")) {
                    final String msg = "Walking WorkItem->Exception(" + id + ")";
                    this.apiLogger.message(this, "API", 10, msg);
                    this.parents.add(this.workItem);
                    this.exception = this.workItem.getAPIException();
                }
                if (this.exception != null) {
                    this.currentWorkingObject = this.exception;
                    return true;
                }
            }
            else if (id == null) {
                final String msg = "Invalid directive: " + target + "=null";
                this.apiLogger.message(this, "API", 0, msg);
                throw new InvalidDirectiveException(msg);
            }
            return false;
        }
        catch (InterruptedException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new CommandException(ex);
        }
        catch (APIInternalError ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            throw new CommandException(ex2);
        }
    }
    
    private boolean lookupById(final String target, final String id) {
        if (id.equals("first")) {
            final String msg = "Getting first " + target;
            this.apiLogger.message(this, "API", 10, msg);
            if (target.equals("subroutine")) {
                this.srIdx = 1;
            }
            else if (target.equals("workitem")) {
                this.wiIdx = 1;
            }
            else if (target.equals("itemlist")) {
                this.ilIdx = 1;
            }
            else if (target.equals("item")) {
                this.iIdx = 1;
            }
            else if (target.equals("field")) {
                this.fIdx = 1;
            }
            else if (target.equals("list")) {
                this.lIdx = 1;
            }
        }
        else if (id.equals("last")) {
            final String msg = "Getting last " + target;
            this.apiLogger.message(this, "API", 10, msg);
            if (target.equals("subroutine")) {
                this.srIdx = Integer.MAX_VALUE;
            }
            else if (target.equals("workitem")) {
                this.wiIdx = Integer.MAX_VALUE;
            }
            else if (target.equals("itemlist")) {
                this.ilIdx = Integer.MAX_VALUE;
            }
            else if (target.equals("item")) {
                this.iIdx = Integer.MAX_VALUE;
            }
            else if (target.equals("field")) {
                this.fIdx = Integer.MAX_VALUE;
            }
            else if (target.equals("list")) {
                this.lIdx = Integer.MAX_VALUE;
            }
        }
        else {
            if (!id.equals("next")) {
                return true;
            }
            final String msg = "Getting next " + target;
            this.apiLogger.message(this, "API", 10, msg);
            if (target.equals("subroutine")) {
                ++this.srIdx;
            }
            else if (target.equals("workitem")) {
                ++this.wiIdx;
            }
            else if (target.equals("itemlist")) {
                ++this.ilIdx;
            }
            else if (target.equals("item")) {
                ++this.iIdx;
            }
            else if (target.equals("field")) {
                ++this.fIdx;
            }
            else if (target.equals("list")) {
                ++this.lIdx;
            }
        }
        return false;
    }
    
    private void setCurrent(final Object parent) {
        if (parent instanceof Response) {
            this.response = (Response)parent;
            this.current = this.response;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to Response");
        }
        else if (parent instanceof SubRoutine) {
            this.subRoutine = (SubRoutine)parent;
            this.current = this.subRoutine;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to SubRoutine");
        }
        else if (parent instanceof WorkItem) {
            this.workItem = (WorkItem)parent;
            this.current = this.workItem;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to WorkItem");
        }
        else if (parent instanceof ItemList) {
            this.itemList = (ItemList)parent;
            this.current = this.itemList;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to ItemList");
        }
        else if (parent instanceof Item) {
            this.item = (Item)parent;
            this.current = this.item;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to Item");
        }
        else if (parent instanceof ValueList) {
            this.list = (ValueList)parent;
            this.current = this.list;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to List");
        }
        else if (parent instanceof Field) {
            this.field = (Field)parent;
            this.current = this.field;
            this.currentWorkingObject = this.current;
            this.apiLogger.message(this, "API", 10, "Setting current pointer to Field");
        }
    }
    
    private void checkPointers(final String targetType) {
        final Object saved = this.current;
        if (targetType.equals("item")) {
            if (this.current == this.workItem || this.current == this.item) {
                this.current = this.field;
            }
            else if (this.current == this.field && this.itemList != null) {
                this.current = this.itemList;
            }
        }
        else if (targetType.equals("list")) {
            if (this.current != this.field) {
                this.current = this.field;
            }
        }
        else if (targetType.equals("itemlist")) {
            if (this.current != this.field && this.current != this.workItem) {
                if (this.workItem != null) {
                    this.current = this.workItem;
                }
                else {
                    this.current = this.field;
                }
            }
        }
        else if (targetType.equals("field")) {
            if (this.current == this.response || this.current == this.subRoutine) {
                if (this.workItem != null) {
                    this.current = this.workItem;
                }
                else {
                    this.current = this.result;
                }
            }
            else if (this.current == this.itemList) {
                this.current = this.item;
            }
        }
        if (this.current == null) {
            this.apiLogger.message(this, "API", 10, "Tried to reset the current pointer to null for target " + targetType);
            this.current = saved;
        }
    }
    
    private void printCurrentNode() {
        if (this.current == this.subRoutine) {
            ResponseUtil.printSubRoutine(this.subRoutine, 1, System.out);
        }
        else if (this.current == this.workItem) {
            ResponseUtil.printWorkItem(this.workItem, 1, System.out);
        }
        else if (this.current == this.item) {
            ResponseUtil.printItem(this.item, 1, System.out);
        }
        else if (this.current == this.itemList) {
            ResponseUtil.printItemList(this.itemList, 1, System.out);
        }
        else if (this.current == this.list) {
            ResponseUtil.printList(this.list, 1, System.out);
        }
        else if (this.current == this.result) {
            ResponseUtil.printResult(this.result, 1, System.out);
        }
        else if (this.current == this.exception) {
            ResponseUtil.printAPIException(this.exception, 1, System.out);
        }
        else if (this.current == this.field) {
            ResponseUtil.printField(this.field, 1, System.out);
        }
        else if (this.current == this.response) {
            ResponseUtil.printResponse(this.response, 1, System.out, true);
        }
    }
    
    private class CurrentState
    {
        public int srIdx;
        public int wiIdx;
        public int ilIdx;
        public int iIdx;
        public int fIdx;
        public int lIdx;
        public Object currentPtr;
        public Object currentWorkingPtr;
        public List parents;
        
        public CurrentState(final Object cp, final Object cwp, final List p) {
            this.currentPtr = cp;
            this.currentWorkingPtr = cwp;
            this.parents = new ArrayList(p);
        }
    }
}
