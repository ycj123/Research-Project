// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

public class Namespace
{
    private String uri;
    private String prefix;
    
    public Namespace() {
    }
    
    public Namespace(final String uri) {
        this.uri = uri.trim();
    }
    
    public Namespace(final String uri, final String prefix) {
        this.uri = uri.trim();
        this.prefix = prefix.trim();
    }
    
    public QName get(final String localName) {
        if (this.uri == null || this.uri.length() <= 0) {
            return new QName(localName);
        }
        if (this.prefix != null) {
            return new QName(this.uri, localName, this.prefix);
        }
        return new QName(this.uri, localName);
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getUri() {
        return this.uri;
    }
}
