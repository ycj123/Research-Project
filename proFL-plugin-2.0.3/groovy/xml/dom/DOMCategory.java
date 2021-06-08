// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.dom;

import java.util.Collection;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import groovy.xml.DOMBuilder;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.Closure;
import org.w3c.dom.Text;
import groovy.xml.QName;
import java.util.Map;
import org.w3c.dom.Document;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Iterator;
import org.codehaus.groovy.runtime.XmlGroovyMethods;
import java.util.Arrays;
import groovy.lang.IntRange;
import org.w3c.dom.Attr;
import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class DOMCategory
{
    private static boolean trimWhitespace;
    
    public static Object get(final Element element, final String elementName) {
        return xgetAt(element, elementName);
    }
    
    public static Object get(final NodeList nodeList, final String elementName) {
        if (nodeList instanceof Element) {
            return xgetAt((Element)nodeList, elementName);
        }
        return xgetAt(nodeList, elementName);
    }
    
    public static Object get(final NamedNodeMap nodeMap, final String elementName) {
        return xgetAt(nodeMap, elementName);
    }
    
    private static Object xgetAt(final Element element, final String elementName) {
        if ("..".equals(elementName)) {
            return parent(element);
        }
        if ("**".equals(elementName)) {
            return depthFirst(element);
        }
        if (elementName.startsWith("@")) {
            return element.getAttribute(elementName.substring(1));
        }
        return getChildElements(element, elementName);
    }
    
    private static Object xgetAt(final NodeList nodeList, final String elementName) {
        final List results = new ArrayList();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            final Node node = nodeList.item(i);
            if (node instanceof Element) {
                addResult(results, get((Element)node, elementName));
            }
        }
        if (elementName.startsWith("@")) {
            return results;
        }
        return new NodeListsHolder(results);
    }
    
    public static NamedNodeMap attributes(final Element element) {
        return element.getAttributes();
    }
    
    private static String xgetAt(final NamedNodeMap namedNodeMap, final String elementName) {
        final Attr a = (Attr)namedNodeMap.getNamedItem(elementName);
        return a.getValue();
    }
    
    public static int size(final NamedNodeMap namedNodeMap) {
        return namedNodeMap.getLength();
    }
    
    public static Node getAt(final Node o, final int i) {
        return nodeGetAt(o, i);
    }
    
    public static Node getAt(final NodeListsHolder o, final int i) {
        return nodeGetAt(o, i);
    }
    
    public static Node getAt(final NodesHolder o, final int i) {
        return nodeGetAt(o, i);
    }
    
    public static NodeList getAt(final Node o, final IntRange r) {
        return nodesGetAt(o, r);
    }
    
    public static NodeList getAt(final NodeListsHolder o, final IntRange r) {
        return nodesGetAt(o, r);
    }
    
    public static NodeList getAt(final NodesHolder o, final IntRange r) {
        return nodesGetAt(o, r);
    }
    
    private static Node nodeGetAt(final Object o, final int i) {
        if (o instanceof Element) {
            final Node n = xgetAt((Element)o, i);
            if (n != null) {
                return n;
            }
        }
        if (o instanceof NodeList) {
            return xgetAt((NodeList)o, i);
        }
        return null;
    }
    
    private static NodeList nodesGetAt(final Object o, final IntRange r) {
        if (o instanceof Element) {
            final NodeList n = xgetAt((Element)o, r);
            if (n != null) {
                return n;
            }
        }
        if (o instanceof NodeList) {
            return xgetAt((NodeList)o, r);
        }
        return null;
    }
    
    private static Node xgetAt(final Element element, final int i) {
        if (hasChildElements(element, "*")) {
            final NodeList nodeList = getChildElements(element, "*");
            return xgetAt(nodeList, i);
        }
        return null;
    }
    
    private static Node xgetAt(final NodeList nodeList, int i) {
        if (i < 0) {
            i += nodeList.getLength();
        }
        if (i >= 0 && i < nodeList.getLength()) {
            return nodeList.item(i);
        }
        return null;
    }
    
    private static NodeList xgetAt(final Element element, final IntRange r) {
        if (hasChildElements(element, "*")) {
            final NodeList nodeList = getChildElements(element, "*");
            return xgetAt(nodeList, r);
        }
        return null;
    }
    
    private static NodeList xgetAt(final NodeList nodeList, IntRange r) {
        int from = r.getFromInt();
        int to = r.getToInt();
        if (from == to) {
            return new NodesHolder((List)Arrays.asList(xgetAt(nodeList, from)));
        }
        if (from < 0) {
            from += nodeList.getLength();
        }
        if (to < 0) {
            to += nodeList.getLength();
        }
        if (from > to) {
            r = (r.isReverse() ? new IntRange(to, from) : new IntRange(from, to));
            from = r.getFromInt();
            to = r.getToInt();
        }
        final List<Node> nodes = new ArrayList<Node>(to - from + 1);
        if (r.isReverse()) {
            for (int i = to; i >= from; --i) {
                nodes.add(nodeList.item(i));
            }
        }
        else {
            for (int i = from; i <= to; ++i) {
                nodes.add(nodeList.item(i));
            }
        }
        return new NodesHolder((List)nodes);
    }
    
    public static String name(final Element element) {
        return element.getNodeName();
    }
    
    public static Node parent(final Node node) {
        return node.getParentNode();
    }
    
    public static String text(final Node node) {
        if (node.getNodeType() == 3) {
            return node.getNodeValue();
        }
        if (node.hasChildNodes()) {
            return text(node.getChildNodes());
        }
        return "";
    }
    
    public static String text(final NodeList nodeList) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            sb.append(text(nodeList.item(i)));
        }
        return sb.toString();
    }
    
    public static List list(final NodeList self) {
        final List answer = new ArrayList();
        final Iterator it = XmlGroovyMethods.iterator(self);
        while (it.hasNext()) {
            answer.add(it.next());
        }
        return answer;
    }
    
    public static NodeList depthFirst(final Element self) {
        final List result = new ArrayList();
        result.add(createNodeList(self));
        result.add(self.getElementsByTagName("*"));
        return new NodeListsHolder(result);
    }
    
    public static void setValue(final Element self, final String value) {
        self.getFirstChild().setNodeValue(value);
    }
    
    public static void putAt(final Element self, final String property, final Object value) {
        if (property.startsWith("@")) {
            final String attributeName = property.substring(1);
            final Document doc = self.getOwnerDocument();
            final Attr newAttr = doc.createAttribute(attributeName);
            newAttr.setValue(value.toString());
            self.setAttributeNode(newAttr);
            return;
        }
        InvokerHelper.setProperty(self, property, value);
    }
    
    public static Element appendNode(final Element self, final Object name) {
        return appendNode(self, name, (String)null);
    }
    
    public static Element appendNode(final Element self, final Object name, final Map attributes) {
        return appendNode(self, name, attributes, null);
    }
    
    public static Element appendNode(final Element self, final Object name, final String value) {
        final Document doc = self.getOwnerDocument();
        Element newChild;
        if (name instanceof QName) {
            final QName qn = (QName)name;
            newChild = doc.createElementNS(qn.getNamespaceURI(), qn.getQualifiedName());
        }
        else {
            newChild = doc.createElement(name.toString());
        }
        if (value != null) {
            final Text text = doc.createTextNode(value);
            newChild.appendChild(text);
        }
        self.appendChild(newChild);
        return newChild;
    }
    
    public static Element appendNode(final Element self, final Object name, final Map attributes, final String value) {
        final Element result = appendNode(self, name, value);
        for (final Object o : attributes.entrySet()) {
            final Map.Entry e = (Map.Entry)o;
            putAt(result, "@" + e.getKey().toString(), e.getValue());
        }
        return result;
    }
    
    public static Element replaceNode(final NodesHolder self, final Closure c) {
        if (self.getLength() <= 0 || self.getLength() > 1) {
            throw new GroovyRuntimeException("replaceNode() can only be used to replace a single element.");
        }
        return replaceNode((Element)self.item(0), c);
    }
    
    public static Element replaceNode(final Element self, final Closure c) {
        final DOMBuilder b = new DOMBuilder(self.getOwnerDocument());
        Element newNode = (Element)b.invokeMethod("rootNode", c);
        Node n;
        for (n = newNode.getFirstChild(); n != null && n.getNodeType() != 1; n = n.getNextSibling()) {}
        if (n == null) {
            throw new GroovyRuntimeException("Replacement node must be an element.");
        }
        newNode = (Element)n;
        self.getParentNode().replaceChild(newNode, self);
        return newNode;
    }
    
    public static void plus(final Element self, final Closure c) {
        final Node parent = self.getParentNode();
        final Node beforeNode = self.getNextSibling();
        final DOMBuilder b = new DOMBuilder(self.getOwnerDocument());
        final Element newNodes = (Element)b.invokeMethod("rootNode", c);
        final Iterator<Node> iter = XmlGroovyMethods.iterator(children(newNodes));
        while (iter.hasNext()) {
            parent.insertBefore(iter.next(), beforeNode);
        }
    }
    
    public static void plus(final NodeList self, final Closure c) {
        for (int i = 0; i < self.getLength(); ++i) {
            plus((Element)self.item(i), c);
        }
    }
    
    private static NodeList createNodeList(final Element self) {
        final List first = new ArrayList();
        first.add(self);
        return new NodesHolder(first);
    }
    
    public static NodeList breadthFirst(final Element self) {
        final List result = new ArrayList();
        for (NodeList thisLevel = createNodeList(self); thisLevel.getLength() > 0; thisLevel = getNextLevel(thisLevel)) {
            result.add(thisLevel);
        }
        return new NodeListsHolder(result);
    }
    
    private static NodeList getNextLevel(final NodeList thisLevel) {
        final List result = new ArrayList();
        for (int i = 0; i < thisLevel.getLength(); ++i) {
            final Node n = thisLevel.item(i);
            if (n instanceof Element) {
                result.add(getChildElements((Element)n, "*"));
            }
        }
        return new NodeListsHolder(result);
    }
    
    public static NodeList children(final Element self) {
        return getChildElements(self, "*");
    }
    
    private static boolean hasChildElements(final Element self, final String elementName) {
        return getChildElements(self, elementName).getLength() > 0;
    }
    
    private static NodeList getChildElements(final Element self, final String elementName) {
        final List result = new ArrayList();
        final NodeList nodeList = self.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            final Node node = nodeList.item(i);
            if (node.getNodeType() == 1) {
                final Element child = (Element)node;
                if ("*".equals(elementName) || child.getTagName().equals(elementName)) {
                    result.add(child);
                }
            }
            else if (node.getNodeType() == 3) {
                String value = node.getNodeValue();
                if (DOMCategory.trimWhitespace) {
                    value = value.trim();
                }
                if ("*".equals(elementName) && value.length() > 0) {
                    node.setNodeValue(value);
                    result.add(node);
                }
            }
        }
        return new NodesHolder(result);
    }
    
    public static String toString(final Object o) {
        if (o instanceof Node && ((Node)o).getNodeType() == 3) {
            return ((Node)o).getNodeValue();
        }
        if (o instanceof NodeList) {
            return toString((NodeList)o);
        }
        return o.toString();
    }
    
    public static Object xpath(final Node self, final String expression, final javax.xml.namespace.QName returnType) {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            return xpath.evaluate(expression, self, returnType);
        }
        catch (XPathExpressionException e) {
            throw new GroovyRuntimeException(e);
        }
    }
    
    public static String xpath(final Node self, final String expression) {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            return xpath.evaluate(expression, self);
        }
        catch (XPathExpressionException e) {
            throw new GroovyRuntimeException(e);
        }
    }
    
    private static String toString(final NodeList self) {
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        final Iterator it = XmlGroovyMethods.iterator(self);
        while (it.hasNext()) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(it.next().toString());
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static int size(final NodeList self) {
        return self.getLength();
    }
    
    public static boolean isEmpty(final NodeList self) {
        return size(self) == 0;
    }
    
    private static void addResult(final List results, final Object result) {
        if (result != null) {
            if (result instanceof Collection) {
                results.addAll((Collection<?>)result);
            }
            else {
                results.add(result);
            }
        }
    }
    
    static {
        DOMCategory.trimWhitespace = true;
    }
    
    private static final class NodeListsHolder implements NodeList
    {
        private List nodeLists;
        
        private NodeListsHolder(final List nodeLists) {
            this.nodeLists = nodeLists;
        }
        
        public int getLength() {
            int length = 0;
            for (int i = 0; i < this.nodeLists.size(); ++i) {
                final NodeList nl = this.nodeLists.get(i);
                length += nl.getLength();
            }
            return length;
        }
        
        public Node item(final int index) {
            int relativeIndex = index;
            for (int i = 0; i < this.nodeLists.size(); ++i) {
                final NodeList nl = this.nodeLists.get(i);
                if (relativeIndex < nl.getLength()) {
                    return nl.item(relativeIndex);
                }
                relativeIndex -= nl.getLength();
            }
            return null;
        }
        
        @Override
        public String toString() {
            return toString(this);
        }
    }
    
    private static final class NodesHolder implements NodeList
    {
        private List nodes;
        
        private NodesHolder(final List nodes) {
            this.nodes = nodes;
        }
        
        public int getLength() {
            return this.nodes.size();
        }
        
        public Node item(final int index) {
            if (index < 0 || index >= this.getLength()) {
                return null;
            }
            return this.nodes.get(index);
        }
    }
}
