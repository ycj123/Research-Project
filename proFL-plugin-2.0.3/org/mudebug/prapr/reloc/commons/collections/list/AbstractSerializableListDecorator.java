// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import java.util.Collection;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.Serializable;

public abstract class AbstractSerializableListDecorator extends AbstractListDecorator implements Serializable
{
    private static final long serialVersionUID = 2684959196747496299L;
    
    protected AbstractSerializableListDecorator(final List list) {
        super(list);
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(super.collection);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.collection = (Collection)in.readObject();
    }
}
