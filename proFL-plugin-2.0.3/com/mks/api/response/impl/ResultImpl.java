// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.Field;
import com.mks.api.util.MKSLogger;
import java.util.NoSuchElementException;
import com.mks.api.common.XMLResponseDef;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.Item;
import com.mks.api.response.modifiable.ModifiableResult;

public class ResultImpl extends AbstractFieldContainer implements ModifiableResult
{
    private String message;
    
    protected ResultImpl() {
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String msg) {
        this.message = msg;
    }
    
    public Item getPrimaryValue() {
        final Item value = null;
        final MKSLogger apiLogger = IntegrationPointFactory.getLogger();
        if (this.contains(XMLResponseDef.XML_RESULT_FIELD)) {
            final Field f = this.getField(XMLResponseDef.XML_RESULT_FIELD);
            if (f.getDataType().equals("com.mks.api.response.Item")) {
                return f.getItem();
            }
            final String msg = "Result value is not an Item.  It is: " + f.getDataType();
            apiLogger.message(this, "API", 0, msg);
        }
        else {
            final String msg2 = "Result field not present!";
            apiLogger.message(this, "API", 0, msg2);
        }
        final String msg2 = "No primary value for the Result.";
        apiLogger.message(this, "API", 0, msg2);
        throw new NoSuchElementException(msg2);
    }
}
