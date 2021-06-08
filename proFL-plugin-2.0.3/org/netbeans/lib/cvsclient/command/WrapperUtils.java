// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.util.HashMap;
import org.netbeans.lib.cvsclient.ClientServices;
import java.io.StringReader;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import org.netbeans.lib.cvsclient.util.SimpleStringPattern;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;

public class WrapperUtils
{
    private static void parseWrappers(final BufferedReader bufferedReader, final Map map) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(line);
            final SimpleStringPattern simpleStringPattern = new SimpleStringPattern(stringTokenizer.nextToken());
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
                    if (map.containsKey(simpleStringPattern)) {
                        continue;
                    }
                    map.put(simpleStringPattern, keywordSubstOption);
                }
            }
        }
    }
    
    public static void readWrappersFromFile(final File file, final Map map) throws IOException, FileNotFoundException {
        parseWrappers(new BufferedReader(new FileReader(file)), map);
    }
    
    public static void readWrappersFromProperty(final String key, final Map map) throws IOException {
        final String property = System.getProperty(key);
        if (property != null) {
            parseWrappers(new BufferedReader(new StringReader(property)), map);
        }
    }
    
    public static Map mergeWrapperMap(final ClientServices clientServices) throws CommandException {
        Object o = null;
        final HashMap hashMap = new HashMap(clientServices.getWrappersMap());
        try {
            final File file = new File(new File(System.getProperty("user.home")), "./cvswrappers");
            o = CommandException.getLocalMessage("WrapperUtils.clientDotWrapper.text");
            if (file.exists()) {
                readWrappersFromFile(file, hashMap);
            }
            o = CommandException.getLocalMessage("WrapperUtils.environmentWrapper.text");
            readWrappersFromProperty("Env-CVSWRAPPERS", hashMap);
        }
        catch (FileNotFoundException ex2) {}
        catch (Exception ex) {
            throw new CommandException(ex, CommandException.getLocalMessage("WrapperUtils.wrapperError.text", new Object[] { o }));
        }
        return hashMap;
    }
}
