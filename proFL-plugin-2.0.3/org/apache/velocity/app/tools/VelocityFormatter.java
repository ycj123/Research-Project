// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.tools;

import java.util.List;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Date;
import org.apache.velocity.context.Context;

public class VelocityFormatter
{
    Context context;
    
    public VelocityFormatter(final Context context) {
        this.context = null;
        this.context = context;
    }
    
    public String formatShortDate(final Date date) {
        return DateFormat.getDateInstance(3).format(date);
    }
    
    public String formatLongDate(final Date date) {
        return DateFormat.getDateInstance(1).format(date);
    }
    
    public String formatShortDateTime(final Date date) {
        return DateFormat.getDateTimeInstance(3, 3).format(date);
    }
    
    public String formatLongDateTime(final Date date) {
        return DateFormat.getDateTimeInstance(1, 1).format(date);
    }
    
    public String formatArray(final Object array) {
        return this.formatArray(array, ", ", " and ");
    }
    
    public String formatArray(final Object array, final String delim) {
        return this.formatArray(array, delim, delim);
    }
    
    public String formatArray(final Object array, final String delim, final String finaldelim) {
        final StringBuffer sb = new StringBuffer();
        for (int arrayLen = Array.getLength(array), i = 0; i < arrayLen; ++i) {
            sb.append(Array.get(array, i).toString());
            if (i < arrayLen - 2) {
                sb.append(delim);
            }
            else if (i < arrayLen - 1) {
                sb.append(finaldelim);
            }
        }
        return sb.toString();
    }
    
    public String formatVector(final List list) {
        return this.formatVector(list, ", ", " and ");
    }
    
    public String formatVector(final List list, final String delim) {
        return this.formatVector(list, delim, delim);
    }
    
    public String formatVector(final List list, final String delim, final String finaldelim) {
        final StringBuffer sb = new StringBuffer();
        for (int size = list.size(), i = 0; i < size; ++i) {
            sb.append(list.get(i));
            if (i < size - 2) {
                sb.append(delim);
            }
            else if (i < size - 1) {
                sb.append(finaldelim);
            }
        }
        return sb.toString();
    }
    
    public String limitLen(final int maxlen, final String string) {
        return this.limitLen(maxlen, string, "...");
    }
    
    public String limitLen(final int maxlen, final String string, final String suffix) {
        String ret = string;
        if (string.length() > maxlen) {
            ret = string.substring(0, maxlen - suffix.length()) + suffix;
        }
        return ret;
    }
    
    public String makeAlternator(final String name, final String alt1, final String alt2) {
        final String[] alternates = { alt1, alt2 };
        this.context.put(name, new VelocityAlternator(alternates));
        return "";
    }
    
    public String makeAlternator(final String name, final String alt1, final String alt2, final String alt3) {
        final String[] alternates = { alt1, alt2, alt3 };
        this.context.put(name, new VelocityAlternator(alternates));
        return "";
    }
    
    public String makeAlternator(final String name, final String alt1, final String alt2, final String alt3, final String alt4) {
        final String[] alternates = { alt1, alt2, alt3, alt4 };
        this.context.put(name, new VelocityAlternator(alternates));
        return "";
    }
    
    public String makeAutoAlternator(final String name, final String alt1, final String alt2) {
        final String[] alternates = { alt1, alt2 };
        this.context.put(name, new VelocityAutoAlternator(alternates));
        return "";
    }
    
    public Object isNull(final Object o, final Object dflt) {
        if (o == null) {
            return dflt;
        }
        return o;
    }
    
    public class VelocityAlternator
    {
        protected String[] alternates;
        protected int current;
        
        public VelocityAlternator(final String[] alternates) {
            this.alternates = null;
            this.current = 0;
            this.alternates = alternates;
        }
        
        public String alternate() {
            ++this.current;
            this.current %= this.alternates.length;
            return "";
        }
        
        public String toString() {
            return this.alternates[this.current];
        }
    }
    
    public class VelocityAutoAlternator extends VelocityAlternator
    {
        public VelocityAutoAlternator(final String[] alternates) {
            super(alternates);
        }
        
        public final String toString() {
            final String s = this.alternates[this.current];
            this.alternate();
            return s;
        }
    }
}
