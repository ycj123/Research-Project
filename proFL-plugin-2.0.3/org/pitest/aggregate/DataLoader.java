// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import java.util.List;
import org.w3c.dom.NamedNodeMap;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Collection;
import java.io.File;
import java.util.Set;

abstract class DataLoader<T>
{
    private static final String CANNOT_CLOSE_ERR = "Unable to close input stream";
    private final Set<File> filesToLoad;
    
    protected DataLoader(final Collection<File> filesToLoad) {
        if (filesToLoad == null || filesToLoad.isEmpty()) {
            throw new IllegalArgumentException("Null or empty filesToLoad");
        }
        this.filesToLoad = Collections.unmodifiableSet((Set<? extends File>)new HashSet<File>(filesToLoad));
    }
    
    public Set<T> loadData() throws ReportAggregationException {
        final Set<T> data = new HashSet<T>();
        for (final File file : this.filesToLoad) {
            data.addAll((Collection<? extends T>)this.loadData(file));
        }
        return data;
    }
    
    protected abstract T mapToData(final Map<String, Object> p0);
    
    Set<T> loadData(final File dataLocation) throws ReportAggregationException {
        if (!dataLocation.exists() || !dataLocation.isFile()) {
            throw new ReportAggregationException(dataLocation.getAbsolutePath() + " does not exist or is not a file");
        }
        final Set<T> data = new HashSet<T>();
        try {
            final InputStream inputStream = new BufferedInputStream(new FileInputStream(dataLocation));
            final Document doc = readDocument(inputStream);
            final Node docNode = doc.getFirstChild();
            final NodeList nodeList = docNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); ++i) {
                final Node itemNode = nodeList.item(i);
                if (itemNode.getNodeType() == 1) {
                    data.add(this.mapToData(nodeMap(itemNode)));
                }
            }
            return data;
        }
        catch (IOException e) {
            throw new ReportAggregationException("Could not read file: " + dataLocation.getAbsolutePath(), e);
        }
    }
    
    static Document readDocument(final InputStream inputStream) throws ReportAggregationException {
        try {
            final DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return docBuilder.parse(inputStream);
        }
        catch (IOException e) {
            throw new ReportAggregationException(e.getMessage(), e);
        }
        catch (SAXException e2) {
            throw new ReportAggregationException(e2.getMessage(), e2);
        }
        catch (ParserConfigurationException e3) {
            throw new ReportAggregationException(e3.getMessage(), e3);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e4) {
                throw new ReportAggregationException("Unable to close input stream", e4);
            }
        }
    }
    
    static Map<String, Object> nodeMap(final Node node) {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        final NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); ++i) {
            final Node attr = attrs.item(i);
            final String tc = attr.getTextContent().trim();
            if (!tc.isEmpty()) {
                map.put(attr.getNodeName(), tc);
            }
        }
        final NodeList children = node.getChildNodes();
        for (int j = 0; j < children.getLength(); ++j) {
            final Node child = children.item(j);
            if (child.getNodeType() == 1) {
                final String tc2 = child.getTextContent().trim();
                if (!tc2.isEmpty()) {
                    map.put(child.getNodeName(), tc2);
                }
                else {
                    final List<String> tests = new ArrayList<String>();
                    final NodeList testNodeList = child.getChildNodes();
                    for (int k = 0; k < testNodeList.getLength(); ++k) {
                        final Node testNode = testNodeList.item(k);
                        if (testNode.getNodeType() == 1) {
                            final NamedNodeMap testAttrs = testNode.getAttributes();
                            for (int l = 0; l < testAttrs.getLength(); ++l) {
                                final Node attr2 = testAttrs.item(l);
                                final String tn = attr2.getTextContent().trim();
                                if (!tn.isEmpty()) {
                                    tests.add(tn);
                                }
                            }
                        }
                    }
                    if (!tests.isEmpty()) {
                        map.put(child.getNodeName(), tests);
                    }
                }
            }
        }
        return map;
    }
}
