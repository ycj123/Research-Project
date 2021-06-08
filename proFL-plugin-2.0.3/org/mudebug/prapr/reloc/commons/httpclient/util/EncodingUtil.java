// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.util;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.URIException;
import org.mudebug.prapr.reloc.commons.httpclient.NameValuePair;
import java.util.BitSet;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class EncodingUtil
{
    private static final Log LOG;
    private static final BitSet WWW_FORM_URL;
    
    public static String formUrlEncode(final NameValuePair[] pairs, final String charset) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pairs.length; ++i) {
            if (pairs[i].getName() != null) {
                if (i > 0) {
                    buf.append("&");
                }
                String queryName = pairs[i].getName();
                try {
                    queryName = URIUtil.encode(queryName, EncodingUtil.WWW_FORM_URL, charset).replace(' ', '+');
                }
                catch (URIException urie) {
                    EncodingUtil.LOG.error("Error encoding pair name: " + queryName, urie);
                }
                buf.append(queryName);
                buf.append("=");
                if (pairs[i].getValue() != null) {
                    String queryValue = pairs[i].getValue();
                    try {
                        queryValue = URIUtil.encode(queryValue, EncodingUtil.WWW_FORM_URL, charset).replace(' ', '+');
                    }
                    catch (URIException urie2) {
                        EncodingUtil.LOG.error("Error encoding pair value: " + queryValue, urie2);
                    }
                    buf.append(queryValue);
                }
            }
        }
        return buf.toString();
    }
    
    private EncodingUtil() {
    }
    
    static {
        LOG = LogFactory.getLog(EncodingUtil.class);
        WWW_FORM_URL = new BitSet(256);
        for (int i = 97; i <= 122; ++i) {
            EncodingUtil.WWW_FORM_URL.set(i);
        }
        for (int j = 65; j <= 90; ++j) {
            EncodingUtil.WWW_FORM_URL.set(j);
        }
        for (int k = 48; k <= 57; ++k) {
            EncodingUtil.WWW_FORM_URL.set(k);
        }
        EncodingUtil.WWW_FORM_URL.set(32);
        EncodingUtil.WWW_FORM_URL.set(45);
        EncodingUtil.WWW_FORM_URL.set(95);
        EncodingUtil.WWW_FORM_URL.set(46);
        EncodingUtil.WWW_FORM_URL.set(42);
    }
}
