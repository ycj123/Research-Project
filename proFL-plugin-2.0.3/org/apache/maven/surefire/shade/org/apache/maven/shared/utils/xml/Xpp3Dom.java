// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml;

import java.io.Writer;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Collections;
import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Xpp3Dom implements Iterable<Xpp3Dom>
{
    private static final long serialVersionUID = 2567894443061173996L;
    private String name;
    private String value;
    private Map<String, String> attributes;
    private final List<Xpp3Dom> childList;
    private final Map<String, Xpp3Dom> childMap;
    private Xpp3Dom parent;
    public static final String CHILDREN_COMBINATION_MODE_ATTRIBUTE = "combine.children";
    private static final String CHILDREN_COMBINATION_MERGE = "merge";
    public static final String CHILDREN_COMBINATION_APPEND = "append";
    private static final String DEFAULT_CHILDREN_COMBINATION_MODE = "merge";
    public static final String SELF_COMBINATION_MODE_ATTRIBUTE = "combine.self";
    public static final String SELF_COMBINATION_OVERRIDE = "override";
    public static final String SELF_COMBINATION_MERGE = "merge";
    private static final String DEFAULT_SELF_COMBINATION_MODE = "merge";
    private static final String[] EMPTY_STRING_ARRAY;
    private static final Xpp3Dom[] EMPTY_DOM_ARRAY;
    
    public Xpp3Dom(final String name) {
        this.name = name;
        this.childList = new ArrayList<Xpp3Dom>();
        this.childMap = new HashMap<String, Xpp3Dom>();
    }
    
    public Xpp3Dom(final Xpp3Dom source) {
        this(source, source.getName());
    }
    
    public Xpp3Dom(@Nonnull final Xpp3Dom src, final String name) {
        this.name = name;
        final int size = src.getChildCount();
        this.childList = new ArrayList<Xpp3Dom>(size);
        this.childMap = new HashMap<String, Xpp3Dom>();
        this.setValue(src.getValue());
        for (final String attributeName : src.getAttributeNames()) {
            this.setAttribute(attributeName, src.getAttribute(attributeName));
        }
        for (final Xpp3Dom xpp3Dom : src.getChildren()) {
            this.addChild(new Xpp3Dom(xpp3Dom));
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    @Nonnull
    public String getValue() {
        return this.value;
    }
    
    public void setValue(@Nonnull final String value) {
        this.value = value;
    }
    
    public String[] getAttributeNames() {
        final boolean isNothing = this.attributes == null || this.attributes.isEmpty();
        return isNothing ? Xpp3Dom.EMPTY_STRING_ARRAY : this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }
    
    public String getAttribute(final String name) {
        return (this.attributes != null) ? this.attributes.get(name) : null;
    }
    
    public void setAttribute(@Nonnull final String name, @Nonnull final String value) {
        if (value == null) {
            throw new NullPointerException("value can not be null");
        }
        if (name == null) {
            throw new NullPointerException("name can not be null");
        }
        if (this.attributes == null) {
            this.attributes = new HashMap<String, String>();
        }
        this.attributes.put(name, value);
    }
    
    public Xpp3Dom getChild(final int i) {
        return this.childList.get(i);
    }
    
    public Xpp3Dom getChild(final String name) {
        return this.childMap.get(name);
    }
    
    public void addChild(final Xpp3Dom child) {
        child.setParent(this);
        this.childList.add(child);
        this.childMap.put(child.getName(), child);
    }
    
    public Xpp3Dom[] getChildren() {
        final boolean isNothing = this.childList == null || this.childList.isEmpty();
        return isNothing ? Xpp3Dom.EMPTY_DOM_ARRAY : this.childList.toArray(new Xpp3Dom[this.childList.size()]);
    }
    
    private List<Xpp3Dom> getChildrenList() {
        final boolean isNothing = this.childList == null || this.childList.isEmpty();
        return isNothing ? Collections.emptyList() : this.childList;
    }
    
    public Xpp3Dom[] getChildren(final String name) {
        final List<Xpp3Dom> children = this.getChildrenList(name);
        return children.toArray(new Xpp3Dom[children.size()]);
    }
    
    private List<Xpp3Dom> getChildrenList(final String name) {
        if (this.childList == null) {
            return Collections.emptyList();
        }
        final ArrayList<Xpp3Dom> children = new ArrayList<Xpp3Dom>();
        for (final Xpp3Dom aChildList : this.childList) {
            if (name.equals(aChildList.getName())) {
                children.add(aChildList);
            }
        }
        return children;
    }
    
    public int getChildCount() {
        if (this.childList == null) {
            return 0;
        }
        return this.childList.size();
    }
    
    public void removeChild(final int i) {
        final Xpp3Dom child = this.childList.remove(i);
        this.childMap.values().remove(child);
        child.setParent(null);
    }
    
    public Xpp3Dom getParent() {
        return this.parent;
    }
    
    public void setParent(final Xpp3Dom parent) {
        this.parent = parent;
    }
    
    private static Xpp3Dom merge(final Xpp3Dom dominant, final Xpp3Dom recessive, final Boolean childMergeOverride) {
        if (recessive == null || isCombineSelfOverride(dominant)) {
            return dominant;
        }
        if (isEmpty(dominant.getValue())) {
            dominant.setValue(recessive.getValue());
        }
        for (final String attr : recessive.getAttributeNames()) {
            if (isEmpty(dominant.getAttribute(attr))) {
                dominant.setAttribute(attr, recessive.getAttribute(attr));
            }
        }
        if (recessive.getChildCount() > 0) {
            final boolean mergeChildren = isMergeChildren(dominant, childMergeOverride);
            if (mergeChildren) {
                final Map<String, Iterator<Xpp3Dom>> commonChildren = getCommonChildren(dominant, recessive);
                for (final Xpp3Dom recessiveChild : recessive) {
                    final Iterator<Xpp3Dom> it = commonChildren.get(recessiveChild.getName());
                    if (it == null) {
                        dominant.addChild(new Xpp3Dom(recessiveChild));
                    }
                    else {
                        if (!it.hasNext()) {
                            continue;
                        }
                        final Xpp3Dom dominantChild = it.next();
                        merge(dominantChild, recessiveChild, childMergeOverride);
                    }
                }
            }
            else {
                final Xpp3Dom[] dominantChildren = dominant.getChildren();
                dominant.childList.clear();
                for (final Xpp3Dom child : recessive) {
                    dominant.addChild(new Xpp3Dom(child));
                }
                for (final Xpp3Dom aDominantChildren : dominantChildren) {
                    dominant.addChild(aDominantChildren);
                }
            }
        }
        return dominant;
    }
    
    private static Map<String, Iterator<Xpp3Dom>> getCommonChildren(final Xpp3Dom dominant, final Xpp3Dom recessive) {
        final Map<String, Iterator<Xpp3Dom>> commonChildren = new HashMap<String, Iterator<Xpp3Dom>>();
        for (final String childName : recessive.childMap.keySet()) {
            final List<Xpp3Dom> dominantChildren = dominant.getChildrenList(childName);
            if (dominantChildren.size() > 0) {
                commonChildren.put(childName, dominantChildren.iterator());
            }
        }
        return commonChildren;
    }
    
    private static boolean isMergeChildren(final Xpp3Dom dominant, final Boolean override) {
        return (override != null) ? override : (!isMergeChildren(dominant));
    }
    
    private static boolean isMergeChildren(final Xpp3Dom dominant) {
        return "append".equals(dominant.getAttribute("combine.children"));
    }
    
    private static boolean isCombineSelfOverride(final Xpp3Dom xpp3Dom) {
        final String selfMergeMode = xpp3Dom.getAttribute("combine.self");
        return "override".equals(selfMergeMode);
    }
    
    public static Xpp3Dom mergeXpp3Dom(final Xpp3Dom dominant, final Xpp3Dom recessive, final Boolean childMergeOverride) {
        return (dominant != null) ? merge(dominant, recessive, childMergeOverride) : recessive;
    }
    
    public static Xpp3Dom mergeXpp3Dom(final Xpp3Dom dominant, final Xpp3Dom recessive) {
        return (dominant != null) ? merge(dominant, recessive, null) : recessive;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Xpp3Dom)) {
            return false;
        }
        final Xpp3Dom dom = (Xpp3Dom)obj;
        if (this.name == null) {
            if (dom.name != null) {
                return false;
            }
        }
        else if (!this.name.equals(dom.name)) {
            return false;
        }
        if (this.value == null) {
            if (dom.value != null) {
                return false;
            }
        }
        else if (!this.value.equals(dom.value)) {
            return false;
        }
        if (this.attributes == null) {
            if (dom.attributes != null) {
                return false;
            }
        }
        else if (!this.attributes.equals(dom.attributes)) {
            return false;
        }
        if (this.childList == null) {
            if (dom.childList != null) {
                return false;
            }
        }
        else if (!this.childList.equals(dom.childList)) {
            return false;
        }
        return true;
        b = false;
        return b;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 37 * result + ((this.value != null) ? this.value.hashCode() : 0);
        result = 37 * result + ((this.attributes != null) ? this.attributes.hashCode() : 0);
        result = 37 * result + ((this.childList != null) ? this.childList.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        final StringWriter writer = new StringWriter();
        Xpp3DomWriter.write(this.getPrettyPrintXMLWriter(writer), this);
        return writer.toString();
    }
    
    public String toUnescapedString() {
        final StringWriter writer = new StringWriter();
        Xpp3DomWriter.write(this.getPrettyPrintXMLWriter(writer), this, false);
        return writer.toString();
    }
    
    private PrettyPrintXMLWriter getPrettyPrintXMLWriter(final StringWriter writer) {
        return new PrettyPrintXMLWriter(writer, "UTF-8", null);
    }
    
    public static boolean isNotEmpty(final String str) {
        return str != null && str.length() > 0;
    }
    
    public static boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0;
    }
    
    public Iterator<Xpp3Dom> iterator() {
        return this.getChildrenList().iterator();
    }
    
    static {
        EMPTY_STRING_ARRAY = new String[0];
        EMPTY_DOM_ARRAY = new Xpp3Dom[0];
    }
}
