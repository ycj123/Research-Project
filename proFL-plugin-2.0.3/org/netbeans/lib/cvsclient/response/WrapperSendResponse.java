// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.util.StringPattern;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.util.LinkedHashMap;
import java.util.Collections;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import org.netbeans.lib.cvsclient.util.SimpleStringPattern;
import java.util.StringTokenizer;
import java.util.Map;

public class WrapperSendResponse implements Response
{
    public static Map parseWrappers(final String str) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str);
        final SimpleStringPattern key = new SimpleStringPattern(stringTokenizer.nextToken());
        Map<SimpleStringPattern, KeywordSubstitutionOptions> singletonMap = null;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            String s = stringTokenizer.nextToken();
            if (nextToken.equals("-k")) {
                final int index = s.indexOf(39);
                final int lastIndex = s.lastIndexOf(39);
                if (index >= 0 && lastIndex >= 0) {
                    s = s.substring(index + 1, lastIndex);
                }
                final KeywordSubstitutionOptions keywordSubstOption = KeywordSubstitutionOptions.findKeywordSubstOption(s);
                if (singletonMap == null) {
                    if (!stringTokenizer.hasMoreTokens()) {
                        singletonMap = Collections.singletonMap(key, keywordSubstOption);
                    }
                    else {
                        singletonMap = new LinkedHashMap<SimpleStringPattern, KeywordSubstitutionOptions>();
                        singletonMap.put(key, keywordSubstOption);
                    }
                }
                else {
                    singletonMap.put(key, keywordSubstOption);
                }
            }
        }
        return singletonMap;
    }
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final Map wrappers = parseWrappers(loggedDataInputStream.readLine());
            for (final StringPattern stringPattern : wrappers.keySet()) {
                responseServices.addWrapper(stringPattern, wrappers.get(stringPattern));
            }
        }
        catch (EOFException ex) {
            throw new ResponseException(ex, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (IOException ex2) {
            throw new ResponseException(ex2);
        }
        catch (NoSuchElementException ex3) {
            throw new ResponseException(ex3);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
