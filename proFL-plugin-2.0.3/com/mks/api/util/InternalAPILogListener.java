// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import com.mks.api.response.APIException;
import java.util.Enumeration;
import java.util.Properties;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.text.MessageFormat;
import java.util.Map;
import java.io.Writer;

class InternalAPILogListener
{
    private static final String BAD_TYPE = "Bad log configuration type: {0}";
    private static final String BAD_ACTION = "Bad log configuration action: {0}";
    private static final String BAD_INTEGER = "Failure creating integer from {0}";
    private Writer writer;
    private Map messageCategoryIncludes;
    private Map messageCategoryExcludes;
    private Map exceptionCategoryIncludes;
    private Map exceptionCategoryExcludes;
    private Map messageFormats;
    private MessageFormat defaultMessageFormat;
    private Map exceptionFormats;
    private MessageFormat defaultExceptionFormat;
    public static final int MESSAGE = 0;
    public static final int EXCEPTION = 1;
    
    protected InternalAPILogListener() {
        this.messageCategoryIncludes = new Hashtable();
        this.messageCategoryExcludes = new Hashtable();
        this.exceptionCategoryIncludes = new Hashtable();
        this.exceptionCategoryExcludes = new Hashtable();
        this.messageFormats = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.defaultMessageFormat = new MessageFormat("{2}({3}): {4}" + System.getProperty("line.separator"));
        this.exceptionFormats = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.defaultExceptionFormat = new MessageFormat("{2}({3}): {4}: {5}" + System.getProperty("line.separator"));
    }
    
    public InternalAPILogListener(final Writer writer) {
        this.messageCategoryIncludes = new Hashtable();
        this.messageCategoryExcludes = new Hashtable();
        this.exceptionCategoryIncludes = new Hashtable();
        this.exceptionCategoryExcludes = new Hashtable();
        this.messageFormats = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.defaultMessageFormat = new MessageFormat("{2}({3}): {4}" + System.getProperty("line.separator"));
        this.exceptionFormats = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.defaultExceptionFormat = new MessageFormat("{2}({3}): {4}: {5}" + System.getProperty("line.separator"));
        this.writer = writer;
    }
    
    public boolean willLogMessage(final String category, final int level) {
        return this.checkFilters(0, category, level);
    }
    
    public boolean willLogException(final String category, final int level) {
        return this.checkFilters(1, category, level);
    }
    
    private boolean checkFilters(final int filterType, final String category, final int level) {
        Map categoryIncludes;
        Map categoryExcludes;
        if (filterType == 0) {
            categoryIncludes = this.messageCategoryIncludes;
            categoryExcludes = this.messageCategoryExcludes;
        }
        else {
            categoryIncludes = this.exceptionCategoryIncludes;
            categoryExcludes = this.exceptionCategoryExcludes;
        }
        if (!categoryExcludes.isEmpty()) {
            final Integer lvl = categoryExcludes.get(category);
            if (lvl != null && level >= lvl) {
                return false;
            }
        }
        if (!categoryIncludes.isEmpty()) {
            final Integer lvl = categoryIncludes.get(category);
            if (lvl == null) {
                return false;
            }
            if (level > lvl) {
                return false;
            }
        }
        return true;
    }
    
    public void logMessage(final Class klass, final Object obj, final String category, final int level, final Object threadData, final String message) {
        if (!this.checkFilters(0, category, level)) {
            return;
        }
        MessageFormat format = this.messageFormats.get(category);
        if (format == null) {
            format = this.defaultMessageFormat;
        }
        try {
            this.writer.write(format.format(new Object[] { (klass != null) ? klass.getName() : "", (obj != null) ? obj.toString() : "", (category != null) ? category : "", new Integer(level), message, new Date(), threadData }));
            this.writer.flush();
        }
        catch (IOException ex) {}
    }
    
    public void logException(final Class klass, final Object obj, final String category, final int level, final Object threadData, final Throwable exception) {
        if (!this.checkFilters(1, category, level)) {
            return;
        }
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        pw.flush();
        final String trace = sw.toString();
        MessageFormat format = this.exceptionFormats.get(category);
        if (format == null) {
            format = this.defaultExceptionFormat;
        }
        final String msg = exception.getLocalizedMessage();
        try {
            this.writer.write(format.format(new Object[] { (klass != null) ? klass.getName() : "", (obj != null) ? obj.toString() : "", (category != null) ? category : "", new Integer(level), exception.getClass().getName(), (msg != null) ? msg : "No Message", trace, new Date(), threadData }));
            this.writer.flush();
        }
        catch (IOException ex) {}
    }
    
    public void addCategoryIncludeFilter(final int filterType, final String category, final int level) {
        if (filterType == 0) {
            this.put(this.messageCategoryIncludes, category, level);
        }
        else {
            this.put(this.exceptionCategoryIncludes, category, level);
        }
    }
    
    public void addCategoryExcludeFilter(final int filterType, final String category, final int level) {
        if (filterType == 0) {
            this.put(this.messageCategoryExcludes, category, level);
        }
        else {
            this.put(this.exceptionCategoryExcludes, category, level);
        }
    }
    
    private void put(final Map m, final String category, final int level) {
        m.put(category, new Integer(level));
    }
    
    public void removeCategoryIncludeFilter(final int filterType, final String category) {
        if (filterType == 0) {
            this.remove(this.messageCategoryIncludes, category);
        }
        else {
            this.remove(this.exceptionCategoryIncludes, category);
        }
    }
    
    public void removeCategoryExcludeFilter(final int filterType, final String category) {
        if (filterType == 0) {
            this.remove(this.messageCategoryExcludes, category);
        }
        else {
            this.remove(this.exceptionCategoryExcludes, category);
        }
    }
    
    private void remove(final Map m, final String category) {
        m.remove(category);
    }
    
    public synchronized void removeAllCategoryFilters(final int filterType) {
        if (filterType == 0) {
            this.messageCategoryIncludes.clear();
            this.messageCategoryExcludes.clear();
        }
        else {
            this.exceptionCategoryIncludes.clear();
            this.exceptionCategoryExcludes.clear();
        }
    }
    
    public void setMessageFormat(final String category, final String format) {
        this.messageFormats.put(category, new MessageFormat(format));
    }
    
    public void setDefaultMessageFormat(final String format) {
        this.defaultMessageFormat = new MessageFormat(format);
    }
    
    public void setExceptionFormat(final String category, final String format) {
        this.exceptionFormats.put(category, new MessageFormat(format));
    }
    
    public void setDefaultExceptionFormat(final String format) {
        this.defaultExceptionFormat = new MessageFormat(format);
    }
    
    public void setWriter(final Writer writer) {
        this.writer = writer;
    }
    
    public void configure(final Properties settings, String prefix) throws APIException {
        if (prefix != null && !prefix.endsWith(".")) {
            prefix += ".";
        }
        final Enumeration keys = settings.propertyNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (prefix == null || key.startsWith(prefix)) {
                final String val = settings.getProperty(key);
                key = key.substring(prefix.length());
                final ConfigurationRequest cr = new ConfigurationRequest(key, val);
                cr.execute();
            }
        }
    }
    
    private class ConfigurationRequest
    {
        private final String[] Types;
        private final String[] Actions;
        private final int MessageType = 0;
        private final int ExceptionType = 1;
        private final int DefaultFormat = 0;
        private final int Format = 1;
        private final int ExcludeCategory = 2;
        private final int IncludeCategory = 3;
        private int type;
        private int action;
        private String modifier;
        private String value;
        
        public ConfigurationRequest(String key, final String val) throws APIException {
            this.Types = new String[] { "message.", "exception." };
            this.Actions = new String[] { "defaultFormat", "format.", "excludeCategory.", "includeCategory." };
            int i;
            for (i = 0; i < this.Types.length; ++i) {
                if (key.startsWith(this.Types[i])) {
                    key = key.substring(this.Types[i].length());
                    this.type = i;
                    break;
                }
            }
            if (i == this.Types.length) {
                final String[] args = { key };
                throw new APIException(MessageFormat.format("Bad log configuration type: {0}", (Object[])args));
            }
            for (i = 0; i < this.Actions.length; ++i) {
                if (key.startsWith(this.Actions[i])) {
                    key = key.substring(this.Actions[i].length());
                    this.action = i;
                    break;
                }
            }
            if (i == this.Actions.length) {
                final String[] args = { key };
                throw new APIException(MessageFormat.format("Bad log configuration action: {0}", (Object[])args));
            }
            this.modifier = key;
            this.value = val;
        }
        
        public void execute() throws APIException {
            int intValue = 0;
            Label_0059: {
                if (this.action != 2) {
                    if (this.action != 3) {
                        break Label_0059;
                    }
                }
                try {
                    intValue = Integer.parseInt(this.value);
                }
                catch (NumberFormatException ex) {
                    final String[] args = { this.value };
                    throw new APIException(MessageFormat.format("Failure creating integer from {0}", (Object[])args));
                }
            }
            switch (this.action) {
                case 0: {
                    switch (this.type) {
                        case 0: {
                            InternalAPILogListener.this.setDefaultMessageFormat(this.value);
                            break;
                        }
                        case 1: {
                            InternalAPILogListener.this.setDefaultExceptionFormat(this.value);
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (this.type) {
                        case 0: {
                            InternalAPILogListener.this.setMessageFormat(this.modifier, this.value);
                            break;
                        }
                        case 1: {
                            InternalAPILogListener.this.setExceptionFormat(this.modifier, this.value);
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    InternalAPILogListener.this.addCategoryExcludeFilter(this.type, this.modifier, intValue);
                    break;
                }
                case 3: {
                    InternalAPILogListener.this.addCategoryIncludeFilter(this.type, this.modifier, intValue);
                    break;
                }
            }
        }
    }
}
