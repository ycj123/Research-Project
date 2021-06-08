// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.Closure;
import groovy.xml.QName;
import groovy.lang.GroovySystem;
import java.util.List;
import java.util.Iterator;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;
import java.util.Collection;
import java.util.ArrayList;

public class NodeList extends ArrayList
{
    public NodeList() {
    }
    
    public NodeList(final Collection collection) {
        super(collection);
    }
    
    public NodeList(final int size) {
        super(size);
    }
    
    protected static void setMetaClass(final Class nodelistClass, final MetaClass metaClass) {
        final MetaClass newMetaClass = new DelegatingMetaClass(metaClass) {
            @Override
            public Object getAttribute(final Object object, final String attribute) {
                final NodeList nl = (NodeList)object;
                final Iterator it = nl.iterator();
                final List result = new ArrayList();
                while (it.hasNext()) {
                    final Node node = it.next();
                    result.add(node.attributes().get(attribute));
                }
                return result;
            }
            
            @Override
            public void setAttribute(final Object object, final String attribute, final Object newValue) {
                for (final Object o : (NodeList)object) {
                    final Node node = (Node)o;
                    node.attributes().put(attribute, newValue);
                }
            }
            
            @Override
            public Object getProperty(final Object object, final String property) {
                if (object instanceof NodeList) {
                    final NodeList nl = (NodeList)object;
                    return nl.getAt(property);
                }
                return super.getProperty(object, property);
            }
        };
        GroovySystem.getMetaClassRegistry().setMetaClass(nodelistClass, newMetaClass);
    }
    
    public NodeList getAt(final String name) {
        final NodeList answer = new NodeList();
        for (final Object child : this) {
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                final Object temp = childNode.get(name);
                if (temp instanceof Collection) {
                    answer.addAll((Collection<?>)temp);
                }
                else {
                    answer.add(temp);
                }
            }
        }
        return answer;
    }
    
    public NodeList getAt(final QName name) {
        final NodeList answer = new NodeList();
        for (final Object child : this) {
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                final NodeList temp = childNode.getAt(name);
                answer.addAll(temp);
            }
        }
        return answer;
    }
    
    public String text() {
        String previousText = null;
        StringBuffer buffer = null;
        for (final Object child : this) {
            String text = null;
            if (child instanceof String) {
                text = (String)child;
            }
            else if (child instanceof Node) {
                text = ((Node)child).text();
            }
            if (text != null) {
                if (previousText == null) {
                    previousText = text;
                }
                else {
                    if (buffer == null) {
                        buffer = new StringBuffer();
                        buffer.append(previousText);
                    }
                    buffer.append(text);
                }
            }
        }
        if (buffer != null) {
            return buffer.toString();
        }
        if (previousText != null) {
            return previousText;
        }
        return "";
    }
    
    public Node replaceNode(final Closure c) {
        if (this.size() <= 0 || this.size() > 1) {
            throw new GroovyRuntimeException("replaceNode() can only be used to replace a single node.");
        }
        return this.get(0).replaceNode(c);
    }
    
    public void plus(final Closure c) {
        for (final Object o : this) {
            ((Node)o).plus(c);
        }
    }
    
    static {
        setMetaClass(NodeList.class, GroovySystem.getMetaClassRegistry().getMetaClass(NodeList.class));
    }
}
