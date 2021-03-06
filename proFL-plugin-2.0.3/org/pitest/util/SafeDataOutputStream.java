// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.util;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;

public class SafeDataOutputStream
{
    private final DataOutputStream dos;
    
    public SafeDataOutputStream(final OutputStream os) {
        this.dos = new DataOutputStream(os);
    }
    
    public void writeInt(final int value) {
        try {
            this.dos.writeInt(value);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void writeString(final String str) {
        try {
            final byte[] data = str.getBytes("UTF-8");
            this.writeBytes(data);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void writeBytes(final byte[] data) {
        try {
            this.dos.writeInt(data.length);
            this.dos.write(data);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public <T extends Serializable> void write(final T value) {
        try {
            this.writeBytes(this.toByteArray(value));
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void flush() {
        try {
            this.dos.flush();
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void close() {
        try {
            this.dos.close();
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void writeByte(final byte b) {
        try {
            this.dos.writeByte(b);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void writeBoolean(final boolean b) {
        try {
            this.dos.writeBoolean(b);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    public void writeLong(final long l) {
        try {
            this.dos.writeLong(l);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    private byte[] toByteArray(final Serializable value) throws IOException {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            final ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(value);
            out.flush();
            return bos.toByteArray();
        }
    }
}
