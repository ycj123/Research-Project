// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.common;

public final class XMLResponseDef
{
    public static String XML_PROLOG;
    public static String XML_ROOT_TAG;
    public static String XML_CONNECTION_TAG;
    public static String XML_EXITCODE_TAG;
    public static String XML_RESULT_TAG;
    public static String XML_RESULT_FIELD;
    public static String XML_SUBITEM_TAG;
    public static String XML_FIELD_TAG;
    public static String XML_SUBOPERATION_TAG;
    public static String XML_SELECTION_TAG;
    public static String XML_EXCEPTION_TAG;
    public static String XML_WORKITEM_TAG;
    public static String XML_LIST_TAG;
    public static String XML_VALUE_TAG;
    public static String XML_TOKEN_VALUE_TAG;
    public static String XML_DISPLAY_VALUE_TAG;
    public static String XML_MESSAGE_TAG;
    public static String XML_APIVERSION_ATTR;
    public static String XML_COMMAND_ATTR;
    public static String XML_COMMAND_APP_ATTR;
    public static String XML_COMMAND_VENDOR_ATTR;
    public static String XML_CONNECTION_HOST_ATTR;
    public static String XML_CONNECTION_PORT_ATTR;
    public static String XML_CONNECTION_USER_ATTR;
    public static String XML_SUBOPERATION_ATTR;
    public static String XML_ID_ATTR;
    public static String XML_SELECTION_TYPE_ATTR;
    public static String XML_ELEMENT_TYPE_ATTR;
    public static String XML_ITEMLIST_ATTR;
    public static String XML_CONTEXT_ATTR;
    public static String XML_DISPLAYID_ATTR;
    public static String XML_MODELTYPE_ATTR;
    public static String XML_TARGET_MODELTYPE_ATTR;
    public static String XML_PARENT_ATTR;
    public static String XML_BEFORE_ATTR;
    public static String XML_AFTER_ATTR;
    public static String XML_SEGMENT_ATTR;
    public static String XML_RELATIONSHIP_FLAG_ATTR;
    public static String XML_EXCEPTIONCLASS_ATTR;
    public static String XML_EXCEPTION_NAME;
    public static String XML_EXCEPTION_INTERNAL_NAME;
    public static String XML_EXCEPTION_ITEM_ID;
    public static String XML_EXCEPTION_ITEM_MODELTYPE;
    public static String XML_EXCEPTION_ITEM_CONTEXT;
    public static String XML_NAME_ATTR;
    public static String XML_DISP_NAME_ATTR;
    public static String XML_DATATYPE_ATTR;
    public static String XML_DATATYPE_INT;
    public static String XML_DATATYPE_STRING;
    public static String XML_DATATYPE_BOOLEAN;
    public static String XML_DATATYPE_DOUBLE;
    public static String XML_DATATYPE_FLOAT;
    public static String XML_DATATYPE_LONG;
    public static String XML_DATATYPE_TIMESTAMP;
    
    private XMLResponseDef() {
    }
    
    static {
        XMLResponseDef.XML_PROLOG = "<?xml version=\"1.0\"?>";
        XMLResponseDef.XML_ROOT_TAG = "Response";
        XMLResponseDef.XML_CONNECTION_TAG = "App-Connection";
        XMLResponseDef.XML_EXITCODE_TAG = "ExitCode";
        XMLResponseDef.XML_RESULT_TAG = "Result";
        XMLResponseDef.XML_RESULT_FIELD = "resultant";
        XMLResponseDef.XML_SUBITEM_TAG = "Item";
        XMLResponseDef.XML_FIELD_TAG = "Field";
        XMLResponseDef.XML_SUBOPERATION_TAG = "SubRtn";
        XMLResponseDef.XML_SELECTION_TAG = "WorkItems";
        XMLResponseDef.XML_EXCEPTION_TAG = "Exception";
        XMLResponseDef.XML_WORKITEM_TAG = "WorkItem";
        XMLResponseDef.XML_LIST_TAG = "List";
        XMLResponseDef.XML_VALUE_TAG = "Value";
        XMLResponseDef.XML_TOKEN_VALUE_TAG = "TokenValue";
        XMLResponseDef.XML_DISPLAY_VALUE_TAG = "DisplayValue";
        XMLResponseDef.XML_MESSAGE_TAG = "Message";
        XMLResponseDef.XML_APIVERSION_ATTR = "version";
        XMLResponseDef.XML_COMMAND_ATTR = "command";
        XMLResponseDef.XML_COMMAND_APP_ATTR = "app";
        XMLResponseDef.XML_COMMAND_VENDOR_ATTR = "vendor";
        XMLResponseDef.XML_CONNECTION_HOST_ATTR = "server";
        XMLResponseDef.XML_CONNECTION_PORT_ATTR = "port";
        XMLResponseDef.XML_CONNECTION_USER_ATTR = "userID";
        XMLResponseDef.XML_SUBOPERATION_ATTR = "routine";
        XMLResponseDef.XML_ID_ATTR = "id";
        XMLResponseDef.XML_SELECTION_TYPE_ATTR = "selectionType";
        XMLResponseDef.XML_ELEMENT_TYPE_ATTR = "elementType";
        XMLResponseDef.XML_ITEMLIST_ATTR = "item";
        XMLResponseDef.XML_CONTEXT_ATTR = "context";
        XMLResponseDef.XML_DISPLAYID_ATTR = "displayId";
        XMLResponseDef.XML_MODELTYPE_ATTR = "modelType";
        XMLResponseDef.XML_TARGET_MODELTYPE_ATTR = "target.modelType";
        XMLResponseDef.XML_PARENT_ATTR = "parent";
        XMLResponseDef.XML_BEFORE_ATTR = "sibling.before";
        XMLResponseDef.XML_AFTER_ATTR = "sibling.after";
        XMLResponseDef.XML_SEGMENT_ATTR = "segment";
        XMLResponseDef.XML_RELATIONSHIP_FLAG_ATTR = "relationshipFlags";
        XMLResponseDef.XML_EXCEPTIONCLASS_ATTR = "class";
        XMLResponseDef.XML_EXCEPTION_NAME = "exception-name";
        XMLResponseDef.XML_EXCEPTION_INTERNAL_NAME = "internal-classname";
        XMLResponseDef.XML_EXCEPTION_ITEM_ID = "item-id";
        XMLResponseDef.XML_EXCEPTION_ITEM_MODELTYPE = "item-modelType";
        XMLResponseDef.XML_EXCEPTION_ITEM_CONTEXT = "item-context";
        XMLResponseDef.XML_NAME_ATTR = "name";
        XMLResponseDef.XML_DISP_NAME_ATTR = "displayName";
        XMLResponseDef.XML_DATATYPE_ATTR = "dataType";
        XMLResponseDef.XML_DATATYPE_INT = "int";
        XMLResponseDef.XML_DATATYPE_STRING = "string";
        XMLResponseDef.XML_DATATYPE_BOOLEAN = "boolean";
        XMLResponseDef.XML_DATATYPE_DOUBLE = "double";
        XMLResponseDef.XML_DATATYPE_FLOAT = "float";
        XMLResponseDef.XML_DATATYPE_LONG = "long";
        XMLResponseDef.XML_DATATYPE_TIMESTAMP = "datetime";
    }
}
