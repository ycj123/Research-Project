// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.collection;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.io.Serializable;

public abstract class AbstractSerializableCollectionDecorator extends AbstractCollectionDecorator implements Serializable
{
    private static final long serialVersionUID = 6249888059822088500L;
    
    protected AbstractSerializableCollectionDecorator(final Collection coll) {
        super(coll);
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
