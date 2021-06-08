// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class QName implements Serializable
{
    private static final String EMPTY_STRING;
    private String namespaceURI;
    private String localPart;
    private String prefix;
    
    public QName(final String localPart) {
        this(QName.EMPTY_STRING, localPart, QName.EMPTY_STRING);
    }
    
    public QName(final String namespaceURI, final String localPart) {
        this(namespaceURI, localPart, QName.EMPTY_STRING);
    }
    
    public QName(final String namespaceURI, final String localPart, final String prefix) {
        this.namespaceURI = ((namespaceURI == null) ? QName.EMPTY_STRING : namespaceURI.trim().intern());
        if (localPart == null) {
            throw new IllegalArgumentException("invalid QName local part");
        }
        this.localPart = localPart.trim().intern();
        if (prefix == null) {
            throw new IllegalArgumentException("invalid QName prefix");
        }
        this.prefix = prefix.trim().intern();
    }
    
    public String getNamespaceURI() {
        return this.namespaceURI;
    }
    
    public String getLocalPart() {
        return this.localPart;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getQualifiedName() {
        return this.prefix.equals(QName.EMPTY_STRING) ? this.localPart : (this.prefix + ':' + this.localPart);
    }
    
    @Override
    public String toString() {
        return this.namespaceURI.equals(QName.EMPTY_STRING) ? this.localPart : ('{' + this.namespaceURI + '}' + this.localPart);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof QName) {
            final QName qName = (QName)o;
            return this.namespaceURI.equals(qName.namespaceURI) && this.localPart.equals(qName.localPart);
        }
        if (!(o instanceof String)) {
            return false;
        }
        final String string = (String)o;
        if (string.length() == 0) {
            return false;
        }
        final int lastColonIndex = string.lastIndexOf(":");
        if (lastColonIndex < 0 || lastColonIndex == string.length() - 1) {
            return false;
        }
        final String stringPrefix = string.substring(0, lastColonIndex);
        final String stringLocalPart = string.substring(lastColonIndex + 1);
        return (stringPrefix.equals(this.prefix) || stringPrefix.equals(this.namespaceURI)) && this.localPart.equals(stringLocalPart);
    }
    
    public boolean matches(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof QName) {
            final QName qName = (QName)o;
            return (this.namespaceURI.equals(qName.namespaceURI) || this.namespaceURI.equals("*") || qName.namespaceURI.equals("*")) && (this.localPart.equals(qName.localPart) || this.localPart.equals("*") || qName.localPart.equals("*"));
        }
        if (o instanceof String) {
            final String string = (String)o;
            if (string.length() == 0) {
                return false;
            }
            final int lastColonIndex = string.lastIndexOf(":");
            if (lastColonIndex < 0 && this.prefix.length() == 0) {
                return string.equals(this.localPart);
            }
            if (lastColonIndex < 0 || lastColonIndex == string.length() - 1) {
                return false;
            }
            final String stringPrefix = string.substring(0, lastColonIndex);
            final String stringLocalPart = string.substring(lastColonIndex + 1);
            if (stringPrefix.equals(this.prefix) || stringPrefix.equals(this.namespaceURI) || stringPrefix.equals("*")) {
                return this.localPart.equals(stringLocalPart) || stringLocalPart.equals("*");
            }
        }
        return false;
    }
    
    public static QName valueOf(final String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("invalid QName literal");
        }
        if (s.charAt(0) != '{') {
            return new QName(s);
        }
        final int i = s.indexOf(125);
        if (i == -1) {
            throw new IllegalArgumentException("invalid QName literal");
        }
        if (i == s.length() - 1) {
            throw new IllegalArgumentException("invalid QName literal");
        }
        return new QName(s.substring(1, i), s.substring(i + 1));
    }
    
    @Override
    public int hashCode() {
        int result = this.namespaceURI.hashCode();
        result = 29 * result + this.localPart.hashCode();
        return result;
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.namespaceURI = this.namespaceURI.intern();
        this.localPart = this.localPart.intern();
        this.prefix = this.prefix.intern();
    }
    
    static {
        EMPTY_STRING = "".intern();
    }
}
