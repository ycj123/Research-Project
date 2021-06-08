// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.plugin;

import java.net.URLClassLoader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.usability.plugin.io.xpp3.ParamdocXpp3Reader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExpressionDocumenter
{
    private static final String[] EXPRESSION_ROOTS;
    private static final String EXPRESSION_DOCO_ROOTPATH = "META-INF/maven/plugin-expressions/";
    private static Map expressionDocumentation;
    
    public static Map load() throws ExpressionDocumentationException {
        if (ExpressionDocumenter.expressionDocumentation == null) {
            ExpressionDocumenter.expressionDocumentation = new HashMap();
            final ClassLoader docLoader = initializeDocLoader();
            for (int i = 0; i < ExpressionDocumenter.EXPRESSION_ROOTS.length; ++i) {
                InputStream docStream = null;
                try {
                    docStream = docLoader.getResourceAsStream("META-INF/maven/plugin-expressions/" + ExpressionDocumenter.EXPRESSION_ROOTS[i] + ".paramdoc.xml");
                    if (docStream != null) {
                        final Map doco = parseExpressionDocumentation(docStream);
                        ExpressionDocumenter.expressionDocumentation.putAll(doco);
                    }
                }
                catch (IOException e) {
                    throw new ExpressionDocumentationException("Failed to read documentation for expression root: " + ExpressionDocumenter.EXPRESSION_ROOTS[i], e);
                }
                catch (XmlPullParserException e2) {
                    throw new ExpressionDocumentationException("Failed to parse documentation for expression root: " + ExpressionDocumenter.EXPRESSION_ROOTS[i], e2);
                }
                finally {
                    IOUtil.close(docStream);
                }
            }
        }
        return ExpressionDocumenter.expressionDocumentation;
    }
    
    private static Map parseExpressionDocumentation(final InputStream docStream) throws IOException, XmlPullParserException {
        final Reader reader = new BufferedReader(new InputStreamReader(docStream));
        final ParamdocXpp3Reader paramdocReader = new ParamdocXpp3Reader();
        final ExpressionDocumentation documentation = paramdocReader.read(reader, true);
        final List expressions = documentation.getExpressions();
        final Map bySyntax = new HashMap();
        if (expressions != null && !expressions.isEmpty()) {
            for (final Expression expr : expressions) {
                bySyntax.put(expr.getSyntax(), expr);
            }
        }
        return bySyntax;
    }
    
    private static ClassLoader initializeDocLoader() throws ExpressionDocumentationException {
        final String myResourcePath = ExpressionDocumenter.class.getName().replace('.', '/') + ".class";
        final URL myResource = ExpressionDocumenter.class.getClassLoader().getResource(myResourcePath);
        String myClasspathEntry = myResource.getPath();
        myClasspathEntry = myClasspathEntry.substring(0, myClasspathEntry.length() - (myResourcePath.length() + 2));
        if (myClasspathEntry.startsWith("file:")) {
            myClasspathEntry = myClasspathEntry.substring("file:".length());
        }
        URL docResource;
        try {
            docResource = new File(myClasspathEntry).toURL();
        }
        catch (MalformedURLException e) {
            throw new ExpressionDocumentationException("Cannot construct expression documentation classpath resource base.", e);
        }
        return new URLClassLoader(new URL[] { docResource });
    }
    
    static {
        EXPRESSION_ROOTS = new String[] { "project", "settings", "session", "plugin", "rootless" };
    }
}
