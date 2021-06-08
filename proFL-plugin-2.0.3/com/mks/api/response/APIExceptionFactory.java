// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import com.mks.api.util.MKSLogger;
import com.mks.api.IntegrationPointFactory;
import java.text.MessageFormat;

public class APIExceptionFactory
{
    private static final String EXCEPTION_CLASS_PREFIX = "com.mks.api.response.";
    private static final String UNKNOWN_EXCEPTION_CLASS_MSG = "Unknown exception class: {0}";
    private static final String EXCEPTION_CLASS_CONSTRUCTION_ERROR_MSG = "Error creating the exception object for: {0}";
    
    public static APIException createAPIException(final String className, final Response response) {
        APIException apiException = null;
        final Object obj = getAPIExceptionObject(className);
        if (obj instanceof APIError) {
            final APIError err = (APIError)obj;
            throw err;
        }
        apiException = (APIException)obj;
        apiException.setResponse(response);
        return apiException;
    }
    
    public static APIException createAPIException(final String className, final String msg) {
        final Object obj = getAPIExceptionObject(className);
        if (obj instanceof APIError) {
            final APIError err = (APIError)obj;
            err.setMessage(msg);
            throw err;
        }
        final APIException apiException = (APIException)obj;
        apiException.setMessage(msg);
        return apiException;
    }
    
    private static APIException handleException(final String msgKey, final String className, final Exception ex) {
        final String logMsg = MessageFormat.format(msgKey, className);
        final MKSLogger apiLogger = IntegrationPointFactory.getLogger();
        apiLogger.message(APIExceptionFactory.class, "API", 0, logMsg);
        apiLogger.exception(APIExceptionFactory.class, "API", 0, ex);
        final APIException apiException = new APIException();
        apiException.addField("original-exception", "com.mks.api.response." + className);
        apiException.setMessage(ex.getMessage());
        return apiException;
    }
    
    private static Object getAPIExceptionObject(final String className) {
        Object obj = null;
        try {
            final Class clazz = Class.forName("com.mks.api.response." + className);
            obj = clazz.newInstance();
            if (obj instanceof APIError) {
                final APIError err = (APIError)obj;
                err.setShowStackTrace(false);
            }
            else {
                final APIException apiException = (APIException)obj;
                apiException.setShowStackTrace(false);
            }
        }
        catch (ClassNotFoundException ex) {
            obj = handleException("Unknown exception class: {0}", className, ex);
        }
        catch (InstantiationException ex2) {
            obj = handleException("Error creating the exception object for: {0}", className, ex2);
        }
        catch (IllegalAccessException ex3) {
            obj = handleException("Error creating the exception object for: {0}", className, ex3);
        }
        catch (Exception ex4) {
            obj = handleException("Unknown exception class: {0}", className, ex4);
        }
        return obj;
    }
}
