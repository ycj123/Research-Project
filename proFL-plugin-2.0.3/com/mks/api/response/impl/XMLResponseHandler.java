// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.modifiable.ModifiableResult;
import com.mks.api.response.Result;
import com.mks.api.response.modifiable.ModifiableValueList;
import com.mks.api.response.ValueList;
import java.util.List;
import java.text.ParsePosition;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.mks.api.response.modifiable.ModifiableItem;
import com.mks.api.response.Item;
import com.mks.api.response.modifiable.ModifiableField;
import com.mks.api.response.Field;
import com.mks.api.response.modifiable.ModifiableWorkItem;
import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.APIInternalError;
import com.mks.api.common.XMLResponseDef;
import java.text.MessageFormat;
import java.io.IOException;
import com.mks.connect.VersionMismatchException;
import com.mks.connect.InvalidAppException;
import com.mks.api.response.Response;
import com.mks.api.CmdRunner;
import org.xmlpull.v1.XmlPullParserException;
import com.mks.api.response.APIExceptionFactory;
import org.xmlpull.v1.XmlPullParserFactory;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.util.MKSLogger;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;

public class XMLResponseHandler
{
    private static final String READ_START_TAG_MSG = "Read in start tag: <{0}>";
    private static final String CREATING_RESPONSE_MSG = "Creating Response({0}, {1})";
    private static final String CREATING_WORK_ITEM_MSG = "Creating WorkItem({0}, {1}, {2})";
    private static final String CREATING_FIELD_MSG = "Creating Field({0})";
    private static final String SETTING_FIELD_VALUE_MSG = "Setting the Field value to: \"{0}\"";
    private static final String CREATING_ITEM_MSG = "Creating Item({0}, {1}, {2})";
    private static final String PARSING_VALUE_MSG = "Parsing a {0} value: {1}";
    private static final String UNKNOWN_DATA_TYPE_MSG = "Unknown value dataType: {0}";
    private static final String CREATING_LIST_MSG = "Creating a List of type: \"{0}\"";
    private static final String CREATING_RESULT_MSG = "Creating Result()";
    private static final String SETTING_RESULT_DATA_MSG = "Setting Result message value to: \"{0}\"";
    private static final String CREATING_SUB_ROUTINE_MSG = "Creating SubRoutine({0})";
    private static final String CREATING_API_EXCEPTION_MSG = "Creating APIException({0})";
    private static final String SETTING_API_EXCEPTION_DATA_MSG = "Setting APIException message value to: \"{0}\"";
    private static final String PARSING_EXIT_CODE_MSG = "Parsing exit code value: \"{0}\"";
    private static final String DOUBLE_EXCEPTION_MSG = "Trying to parse another Exception tag at the {0} level.";
    private static final String INVALID_DATA_STREAM_ERROR_MSG = "Invalid data stream received from the IntegrationPoint.";
    private ModifiableXMLResponse response;
    private XmlPullParser xpp;
    private InputStream is;
    private XMLResponseFactory responseFactory;
    private int eventType;
    private boolean readResponseConnection;
    private boolean readResponseWorkItemsSelectionType;
    private boolean readWorkItems;
    private boolean readPreSubRoutines;
    private boolean readPostSubRoutines;
    private boolean isInterrupted;
    private MKSLogger apiLogger;
    
    public XMLResponseHandler(final InputStream is, final String encoding) {
        this.is = is;
        this.responseFactory = XMLResponseFactory.getInstance();
        this.apiLogger = IntegrationPointFactory.getLogger();
        try {
            final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(false);
            (this.xpp = factory.newPullParser()).setInput(this.is, encoding);
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
    }
    
    public Response getResponse(final CmdRunner cmdRunner, final String cc, final boolean readCompletely) {
        while (this.eventType != 2 && this.eventType != 1) {
            try {
                this.eventType = this.xpp.next();
                continue;
            }
            catch (XmlPullParserException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
                continue;
            }
            catch (InvalidAppException ex2) {
                this.apiLogger.exception(this, "API", 0, ex2);
                throw new UnsupportedApplicationError();
            }
            catch (VersionMismatchException ex3) {
                this.apiLogger.exception(this, "API", 0, ex3);
                throw new UnsupportedVersionError(ex3.getMessage());
            }
            catch (IOException ex4) {
                this.apiLogger.exception(this, "API", 0, ex4);
                final ApplicationConnectionError ace = new ApplicationConnectionError(ex4.getMessage());
                throw ace;
            }
            break;
        }
        if (this.eventType == 2) {
            final String tagName = this.xpp.getName();
            String msg = MessageFormat.format("Read in start tag: <{0}>", tagName);
            this.apiLogger.message(this, "API", 0, msg);
            if (tagName.equals(XMLResponseDef.XML_ROOT_TAG)) {
                final String app = this.xpp.getAttributeValue(null, XMLResponseDef.XML_COMMAND_APP_ATTR);
                final String command = this.xpp.getAttributeValue(null, XMLResponseDef.XML_COMMAND_ATTR);
                msg = MessageFormat.format("Creating Response({0}, {1})", app, command);
                this.apiLogger.message(this, "API", 5, msg);
                (this.response = this.responseFactory.createXMLResponse(cmdRunner, app, command)).setXMLResponseHandler(this);
                ((ResponseImpl)this.response).setCommandString(cc);
                try {
                    this.xpp.next();
                    this.findStartTag();
                }
                catch (XmlPullParserException ex5) {
                    this.apiLogger.exception(this, "API", 0, ex5);
                    APIExceptionFactory.createAPIException("APIInternalError", (String)null);
                }
                catch (IOException ex6) {
                    this.apiLogger.exception(this, "API", 0, ex6);
                    final ApplicationConnectionError ace2 = new ApplicationConnectionError(ex6.getMessage());
                    throw ace2;
                }
            }
            if (readCompletely) {
                try {
                    SubRoutine sr = null;
                    while ((sr = this.getSubRoutine()) != null) {
                        this.response.add(sr);
                        this.eventType = this.xpp.next();
                    }
                    this.readResponseConnectionAttributes();
                    if (this.readResponseWorkItemsTagAttributes()) {
                        WorkItem wi = null;
                        while ((wi = this.getWorkItem()) != null) {
                            this.response.add(wi);
                            this.eventType = this.xpp.next();
                        }
                    }
                    this.response.setResult(this.getResult(true));
                    this.response.setAPIException(this.getException());
                    this.response.setExitCode(this.getExitCode());
                }
                catch (XmlPullParserException ex) {
                    this.apiLogger.exception(this, "API", 0, ex);
                    APIExceptionFactory.createAPIException("APIInternalError", (String)null);
                }
                catch (IOException ex4) {
                    this.apiLogger.exception(this, "API", 0, ex4);
                    final ApplicationConnectionError ace = new ApplicationConnectionError(ex4.getMessage());
                    throw ace;
                }
            }
            return this.response;
        }
        final String msg2 = "Invalid data stream received from the IntegrationPoint.";
        final APIInternalError err = new APIInternalError(msg2);
        this.apiLogger.exception(this, "API", 0, err);
        throw err;
    }
    
    protected void readResponseConnectionAttributes() {
        if (this.readResponseConnection) {
            return;
        }
        final String tag = this.xpp.getName();
        if (tag != null && tag.equals(XMLResponseDef.XML_CONNECTION_TAG)) {
            this.response.setConnectionHostname(this.xpp.getAttributeValue(null, XMLResponseDef.XML_CONNECTION_HOST_ATTR));
            try {
                this.response.setConnectionPort(Integer.parseInt(this.xpp.getAttributeValue(null, XMLResponseDef.XML_CONNECTION_PORT_ATTR)));
            }
            catch (NumberFormatException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
            this.response.setConnectionUsername(this.xpp.getAttributeValue(null, XMLResponseDef.XML_CONNECTION_USER_ATTR));
            try {
                this.eventType = this.xpp.next();
                this.findStartTag();
            }
            catch (XmlPullParserException ex2) {
                this.apiLogger.exception(this, "API", 0, ex2);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
            catch (IOException ex3) {
                this.apiLogger.exception(this, "API", 0, ex3);
                final ApplicationConnectionError ace = new ApplicationConnectionError(ex3.getMessage());
                throw ace;
            }
            this.readResponseConnection = true;
            this.readPreSubRoutines = true;
        }
    }
    
    protected boolean readResponseWorkItemsTagAttributes() {
        if (this.readResponseWorkItemsSelectionType) {
            return true;
        }
        final String tag = this.xpp.getName();
        if (tag != null && tag.equals(XMLResponseDef.XML_SELECTION_TAG)) {
            final String selectionType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_SELECTION_TYPE_ATTR);
            this.response.setWorkItemSelectionType(selectionType);
            try {
                this.eventType = this.xpp.next();
                this.findStartTag();
            }
            catch (XmlPullParserException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
            catch (IOException ex2) {
                this.apiLogger.exception(this, "API", 0, ex2);
                final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
                throw ace;
            }
            this.readResponseWorkItemsSelectionType = true;
            this.readResponseConnection = true;
            return this.readPreSubRoutines = true;
        }
        return false;
    }
    
    protected WorkItem getWorkItem() {
        final int et = this.findStartTag();
        final String tag = this.xpp.getName();
        if (et == 1 || tag == null || !tag.equals(XMLResponseDef.XML_WORKITEM_TAG)) {
            return null;
        }
        ModifiableWorkItem workItem = null;
        try {
            final String id = this.xpp.getAttributeValue(null, XMLResponseDef.XML_ID_ATTR);
            final String context = this.xpp.getAttributeValue(null, XMLResponseDef.XML_CONTEXT_ATTR);
            final String modelType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_MODELTYPE_ATTR);
            final String msg = MessageFormat.format("Creating WorkItem({0}, {1}, {2})", id, context, modelType);
            this.apiLogger.message(this, "API", 5, msg);
            workItem = this.responseFactory.createWorkItem(id, context, modelType);
            if (workItem instanceof ItemImpl) {
                this.addItemContextAttributes((ItemImpl)workItem);
            }
            boolean workItemExceptionRead = false;
            int eventType = Integer.MIN_VALUE;
            while (true) {
                eventType = this.xpp.next();
                final String tagName = this.xpp.getName();
                if (eventType == 3 && tagName.equals(XMLResponseDef.XML_WORKITEM_TAG)) {
                    break;
                }
                if (eventType != 2) {
                    continue;
                }
                if (tagName.equals(XMLResponseDef.XML_SUBOPERATION_TAG)) {
                    workItem.add(this.getSubRoutine());
                }
                else if (tagName.equals(XMLResponseDef.XML_FIELD_TAG)) {
                    workItem.add(this.getField());
                }
                else if (tagName.equals(XMLResponseDef.XML_RESULT_TAG)) {
                    workItem.setResult(this.getResult(false));
                }
                else {
                    if (!tagName.equals(XMLResponseDef.XML_EXCEPTION_TAG)) {
                        continue;
                    }
                    if (!workItemExceptionRead) {
                        workItem.setAPIException(this.getException());
                        workItemExceptionRead = true;
                    }
                    else {
                        final String logMsg = MessageFormat.format("Trying to parse another Exception tag at the {0} level.", "WorkItem");
                        this.apiLogger.message(this, "API", 0, logMsg);
                    }
                }
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        return workItem;
    }
    
    protected Field getField() {
        final String tag = this.xpp.getName();
        if (tag == null || !tag.equals(XMLResponseDef.XML_FIELD_TAG)) {
            return null;
        }
        ModifiableField field = null;
        try {
            final String name = this.xpp.getAttributeValue(null, XMLResponseDef.XML_NAME_ATTR);
            final String displayName = this.xpp.getAttributeValue(null, XMLResponseDef.XML_DISP_NAME_ATTR);
            String msg = MessageFormat.format("Creating Field({0})", name);
            this.apiLogger.message(this, "API", 5, msg);
            if (displayName != null) {
                field = this.responseFactory.createField(name, displayName);
            }
            else {
                field = this.responseFactory.createField(name);
            }
            int eventType = Integer.MIN_VALUE;
            while (true) {
                eventType = this.xpp.next();
                final String tagName = this.xpp.getName();
                if (eventType == 3 && tagName.equals(XMLResponseDef.XML_FIELD_TAG)) {
                    break;
                }
                if (eventType != 2) {
                    continue;
                }
                if (tagName.equals(XMLResponseDef.XML_VALUE_TAG)) {
                    final Object[] values = this.getValue(field);
                    msg = MessageFormat.format("Setting the Field value to: \"{0}\"", values[0]);
                    this.apiLogger.message(this, "API", 10, msg);
                    field.setValue(values[0]);
                    if (values.length <= 1) {
                        continue;
                    }
                    field.setDisplayValue(values[1].toString());
                }
                else if (tagName.equals(XMLResponseDef.XML_LIST_TAG)) {
                    field.setValue(this.getList(field));
                }
                else {
                    if (!tagName.equals(XMLResponseDef.XML_SUBITEM_TAG)) {
                        continue;
                    }
                    field.setValue(this.getItem());
                    field.setDataType("com.mks.api.response.Item");
                }
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        return field;
    }
    
    protected Item getItem() {
        final String tag = this.xpp.getName();
        if (tag == null || !tag.equals(XMLResponseDef.XML_SUBITEM_TAG)) {
            return null;
        }
        final String id = this.xpp.getAttributeValue(null, XMLResponseDef.XML_ID_ATTR);
        final String context = this.xpp.getAttributeValue(null, XMLResponseDef.XML_CONTEXT_ATTR);
        final String modelType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_MODELTYPE_ATTR);
        final String msg = MessageFormat.format("Creating Item({0}, {1}, {2})", id, context, modelType);
        this.apiLogger.message(this, "API", 5, msg);
        final ModifiableItem item = this.responseFactory.createItem(id, context, modelType);
        if (item instanceof ItemImpl) {
            this.addItemContextAttributes((ItemImpl)item);
        }
        while (true) {
            int eventType = Integer.MIN_VALUE;
            try {
                eventType = this.xpp.next();
            }
            catch (XmlPullParserException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
            catch (IOException ex2) {
                this.apiLogger.exception(this, "API", 0, ex2);
                final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
                throw ace;
            }
            final String tagName = this.xpp.getName();
            if (eventType == 3 && tagName.equals(XMLResponseDef.XML_SUBITEM_TAG)) {
                break;
            }
            if (eventType != 2 || !tagName.equals(XMLResponseDef.XML_FIELD_TAG)) {
                continue;
            }
            item.add(this.getField());
        }
        return item;
    }
    
    protected Object[] getValue(final ModifiableField f) {
        final String tag = this.xpp.getName();
        if (tag == null || !tag.equals(XMLResponseDef.XML_VALUE_TAG)) {
            return new Object[] { null };
        }
        Object tokValObj = null;
        String dispObj = null;
        try {
            final String dataType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_DATATYPE_ATTR);
            final String modelType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_MODELTYPE_ATTR);
            int eventType = Integer.MIN_VALUE;
            String prevTag = tag;
            eventType = this.xpp.next();
            String currTag = this.xpp.getName();
            while (true) {
                if (eventType == 4) {
                    final String text = this.xpp.getText();
                    eventType = this.xpp.next();
                    currTag = this.xpp.getName();
                    if (eventType == 3) {
                        if ((currTag.equals(XMLResponseDef.XML_VALUE_TAG) || currTag.equals(XMLResponseDef.XML_TOKEN_VALUE_TAG)) && prevTag.equals(currTag)) {
                            tokValObj = this.parseValue(text, dataType, modelType, f);
                        }
                        else if (currTag.equals(XMLResponseDef.XML_DISPLAY_VALUE_TAG) && prevTag.equals(currTag)) {
                            dispObj = text;
                        }
                    }
                }
                if (currTag.equals(XMLResponseDef.XML_VALUE_TAG)) {
                    break;
                }
                prevTag = currTag;
                eventType = this.xpp.next();
                currTag = this.xpp.getName();
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        return (dispObj == null) ? new Object[] { tokValObj } : new Object[] { tokValObj, dispObj };
    }
    
    private Object parseValue(final String value, final String dataType, final String modelType, final ModifiableField f) {
        Object objValue = null;
        f.setModelType(modelType);
        String msg = MessageFormat.format("Parsing a {0} value: {1}", dataType, value);
        this.apiLogger.message(this, "API", 10, msg);
        if (dataType == null) {
            objValue = null;
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_STRING)) {
            objValue = value;
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_BOOLEAN)) {
            objValue = new Boolean(value);
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_TIMESTAMP)) {
            final SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            sdf.applyPattern("yyyy-MM-dd'T'HH:mm:ss");
            objValue = sdf.parse(value, new ParsePosition(0));
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_DOUBLE)) {
            try {
                objValue = new Double(value);
            }
            catch (NumberFormatException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_FLOAT)) {
            try {
                objValue = new Float(value);
            }
            catch (NumberFormatException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_INT)) {
            try {
                objValue = new Integer(value);
            }
            catch (NumberFormatException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
        }
        else if (dataType.equals(XMLResponseDef.XML_DATATYPE_LONG)) {
            try {
                objValue = new Long(value);
            }
            catch (NumberFormatException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
        }
        else {
            msg = MessageFormat.format("Unknown value dataType: {0}", dataType);
            this.apiLogger.message(this, "API", 0, msg);
            APIExceptionFactory.createAPIException("APIInternalError", msg);
        }
        if (objValue != null) {
            f.setDataType(objValue.getClass().getName());
        }
        else {
            f.setDataType(null);
        }
        return objValue;
    }
    
    protected List getList(final ModifiableField f) {
        final String tag = this.xpp.getName();
        if (tag == null || !tag.equals(XMLResponseDef.XML_LIST_TAG)) {
            return null;
        }
        String type = this.xpp.getAttributeValue(null, XMLResponseDef.XML_ELEMENT_TYPE_ATTR);
        if (type == null) {
            type = "value";
        }
        final String msg = MessageFormat.format("Creating a List of type: \"{0}\"", type);
        this.apiLogger.message(this, "API", 5, msg);
        List list = null;
        if (type != null && type.equals(XMLResponseDef.XML_ITEMLIST_ATTR)) {
            list = this.responseFactory.createItemList();
        }
        else {
            list = this.responseFactory.createValueList();
        }
        while (true) {
            int eventType = Integer.MIN_VALUE;
            try {
                eventType = this.xpp.next();
            }
            catch (XmlPullParserException ex) {
                this.apiLogger.exception(this, "API", 0, ex);
                APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            }
            catch (IOException ex2) {
                this.apiLogger.exception(this, "API", 0, ex2);
                final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
                throw ace;
            }
            final String tagName = this.xpp.getName();
            if (eventType == 3 && tagName.equals(XMLResponseDef.XML_LIST_TAG)) {
                break;
            }
            if (eventType != 2) {
                continue;
            }
            if (tagName.equals(XMLResponseDef.XML_VALUE_TAG)) {
                final Object[] valueSet = this.getValue(f);
                final Object value = valueSet[0];
                if (((ValueList)list).getDataType() == null && value != null) {
                    ((ModifiableValueList)list).setDataType(value.getClass().getName());
                }
                list.add(value);
                if (!(list instanceof ModifiableValueList) || valueSet.length <= 1) {
                    continue;
                }
                ((ModifiableValueList)list).setDisplayValueOf(value, (String)valueSet[1]);
            }
            else {
                if (!tagName.equals(XMLResponseDef.XML_SUBITEM_TAG)) {
                    continue;
                }
                list.add(this.getItem());
            }
        }
        if (type != null && type.equals(XMLResponseDef.XML_ITEMLIST_ATTR)) {
            f.setDataType("com.mks.api.response.ItemList");
        }
        else {
            f.setDataType("com.mks.api.response.ValueList");
        }
        return list;
    }
    
    protected Result getResult(final boolean mainResult) {
        final int et = this.findStartTag();
        final String tag = this.xpp.getName();
        if (et == 1 || tag == null || !tag.equals(XMLResponseDef.XML_RESULT_TAG)) {
            return null;
        }
        this.apiLogger.message(this, "API", 5, "Creating Result()");
        final ModifiableResult result = this.responseFactory.createResult();
        try {
            int eventType = Integer.MIN_VALUE;
            while (true) {
                eventType = this.xpp.next();
                final String tagName = this.xpp.getName();
                if (eventType == 3 && tagName.equals(XMLResponseDef.XML_RESULT_TAG)) {
                    break;
                }
                if (eventType != 2) {
                    continue;
                }
                if (tagName.equals(XMLResponseDef.XML_FIELD_TAG)) {
                    result.add(this.getField());
                }
                else {
                    if (!tagName.equals(XMLResponseDef.XML_MESSAGE_TAG)) {
                        continue;
                    }
                    final String msg = this.xpp.nextText();
                    final String logMsg = MessageFormat.format("Setting Result message value to: \"{0}\"", msg);
                    this.apiLogger.message(this, "API", 10, logMsg);
                    result.setMessage(msg);
                }
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        if (mainResult) {
            this.readPreSubRoutines = true;
            this.readPostSubRoutines = true;
        }
        return result;
    }
    
    protected SubRoutine getSubRoutine() {
        final int et = this.findStartTag();
        final String tag = this.xpp.getName();
        if (et == 1 || tag == null || !tag.equals(XMLResponseDef.XML_SUBOPERATION_TAG)) {
            return null;
        }
        ModifiableXMLSubRoutine subRoutine = null;
        try {
            final String routine = this.xpp.getAttributeValue(null, XMLResponseDef.XML_SUBOPERATION_ATTR);
            final String msg = MessageFormat.format("Creating SubRoutine({0})", routine);
            this.apiLogger.message(this, "API", 5, msg);
            subRoutine = this.responseFactory.createXMLSubRoutine(routine);
            subRoutine.setXMLResponseHandler(this);
            boolean subRoutineExceptionRead = false;
            while (true) {
                int eventType = Integer.MIN_VALUE;
                eventType = this.xpp.next();
                final String tagName = this.xpp.getName();
                if (eventType == 3 && tagName.equals(XMLResponseDef.XML_SUBOPERATION_TAG)) {
                    break;
                }
                if (eventType != 2) {
                    continue;
                }
                if (tagName.equals(XMLResponseDef.XML_SUBOPERATION_TAG)) {
                    subRoutine.add(this.getSubRoutine());
                }
                else if (tagName.equals(XMLResponseDef.XML_SELECTION_TAG)) {
                    final String selectionType = this.xpp.getAttributeValue(null, XMLResponseDef.XML_SELECTION_TYPE_ATTR);
                    subRoutine.setWorkItemSelectionType(selectionType);
                }
                else if (tagName.equals(XMLResponseDef.XML_WORKITEM_TAG)) {
                    subRoutine.add(this.getWorkItem());
                }
                else if (tagName.equals(XMLResponseDef.XML_RESULT_TAG)) {
                    subRoutine.setResult(this.getResult(false));
                }
                else {
                    if (!tagName.equals(XMLResponseDef.XML_EXCEPTION_TAG)) {
                        continue;
                    }
                    if (!subRoutineExceptionRead) {
                        subRoutine.setAPIException(this.getException());
                        subRoutineExceptionRead = true;
                    }
                    else {
                        final String logMsg = MessageFormat.format("Trying to parse another Exception tag at the {0} level.", "SubRoutine");
                        this.apiLogger.message(this, "API", 0, logMsg);
                    }
                }
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        return subRoutine;
    }
    
    protected APIException getException() {
        final int et = this.findStartTag();
        final String tag = this.xpp.getName();
        if (et == 1 || tag == null || !tag.equals(XMLResponseDef.XML_EXCEPTION_TAG)) {
            return null;
        }
        final String exName = this.xpp.getAttributeValue(null, XMLResponseDef.XML_EXCEPTIONCLASS_ATTR);
        String logMsg = MessageFormat.format("Creating APIException({0})", exName);
        this.apiLogger.message(this, "API", 5, logMsg);
        final APIException apiException = APIExceptionFactory.createAPIException(exName, this.response);
        try {
            int eventType = Integer.MIN_VALUE;
            while (true) {
                eventType = this.xpp.next();
                final String tagName = this.xpp.getName();
                if (eventType == 3 && tagName.equals(XMLResponseDef.XML_EXCEPTION_TAG)) {
                    break;
                }
                if (eventType != 2) {
                    continue;
                }
                if (tagName.equals(XMLResponseDef.XML_FIELD_TAG)) {
                    apiException.add(this.getField());
                }
                else {
                    if (!tagName.equals(XMLResponseDef.XML_MESSAGE_TAG)) {
                        continue;
                    }
                    final String msg = this.xpp.nextText();
                    logMsg = MessageFormat.format("Setting APIException message value to: \"{0}\"", msg);
                    this.apiLogger.message(this, "API", 10, logMsg);
                    apiException.setMessage(msg);
                }
            }
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
        return apiException;
    }
    
    protected int getExitCode() {
        int et = this.findStartTag();
        int exitCode = Integer.MIN_VALUE;
        final String tag = this.xpp.getName();
        if (et == 1 || tag == null || !tag.equals(XMLResponseDef.XML_EXITCODE_TAG)) {
            return exitCode;
        }
        try {
            final String strExitCode = this.xpp.nextText();
            final String msg = MessageFormat.format("Parsing exit code value: \"{0}\"", strExitCode);
            this.apiLogger.message(this, "API", 10, msg);
            exitCode = Integer.parseInt(strExitCode);
            for (et = this.xpp.getEventType(); et != 1; et = this.xpp.next()) {}
        }
        catch (NumberFormatException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (XmlPullParserException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
        }
        catch (IOException ex3) {
            this.apiLogger.exception(this, "API", 0, ex3);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex3.getMessage());
            throw ace;
        }
        return exitCode;
    }
    
    protected void interrupt() {
        if (this.isInterrupted) {
            return;
        }
        try {
            this.is.close();
            this.isInterrupted = true;
            this.readPreSubRoutines = true;
            this.readWorkItems = true;
            this.readPostSubRoutines = true;
        }
        catch (IOException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
        }
    }
    
    private int findStartTag() {
        if (this.isInterrupted) {
            return 1;
        }
        try {
            int et = this.xpp.getEventType();
            while (et != 2 && et != 1) {
                et = this.xpp.next();
                if (et == 3 && this.xpp.getName().equals(XMLResponseDef.XML_SELECTION_TAG)) {
                    this.readWorkItems = true;
                }
            }
            return et;
        }
        catch (XmlPullParserException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            APIExceptionFactory.createAPIException("APIInternalError", (String)null);
            return 1;
        }
        catch (IOException ex2) {
            this.apiLogger.exception(this, "API", 0, ex2);
            final ApplicationConnectionError ace = new ApplicationConnectionError(ex2.getMessage());
            throw ace;
        }
    }
    
    private void addItemContextAttributes(final ItemImpl item) {
        for (int numAttributes = this.xpp.getAttributeCount(), i = 0; i < numAttributes; ++i) {
            final String attributeName = this.xpp.getAttributeName(i);
            final String attributeValue = this.xpp.getAttributeValue(i);
            item.addContext(attributeName, attributeValue);
        }
    }
    
    protected boolean getReadPreSubRoutines() {
        return this.readPreSubRoutines;
    }
    
    protected boolean getReadWorkItems() {
        return this.readWorkItems;
    }
    
    protected boolean getReadPostSubRoutines() {
        return this.readPostSubRoutines;
    }
    
    protected boolean isInterrupted() {
        return this.isInterrupted;
    }
}
