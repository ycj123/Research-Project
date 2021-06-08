// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import org.apache.maven.artifact.Artifact;
import java.util.Iterator;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.io.File;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ChecksumCalculator
{
    private static final String HEX = "0123456789ABCDEF";
    private final List<Object> checksumItems;
    
    public ChecksumCalculator() {
        this.checksumItems = new ArrayList<Object>();
    }
    
    private void appendObject(final Object item) {
        this.checksumItems.add(item);
    }
    
    public void add(final boolean value) {
        this.checksumItems.add(value ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public void add(final int value) {
        this.checksumItems.add(value);
    }
    
    public void add(final double value) {
        this.checksumItems.add(value);
    }
    
    public void add(final Map<?, ?> map) {
        if (map != null) {
            this.appendObject(map.toString());
        }
    }
    
    public void add(final String string) {
        this.appendObject(string);
    }
    
    public void add(final File workingDirectory) {
        this.appendObject(workingDirectory);
    }
    
    public void add(final ArtifactRepository localRepository) {
        this.appendObject(localRepository);
    }
    
    public void add(final List<?> items) {
        if (items != null) {
            for (final Object item : items) {
                this.appendObject(item);
            }
        }
        else {
            this.appendObject(null);
        }
    }
    
    public void add(final Object[] items) {
        if (items != null) {
            for (final Object item : items) {
                this.appendObject(item);
            }
        }
        else {
            this.appendObject(null);
        }
    }
    
    public void add(final Artifact artifact) {
        this.appendObject((artifact != null) ? artifact.getId() : null);
    }
    
    public void add(final Boolean aBoolean) {
        this.appendObject(aBoolean);
    }
    
    private static String asHexString(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        final StringBuilder result = new StringBuilder(2 * bytes.length);
        for (final byte b : bytes) {
            result.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4)).append("0123456789ABCDEF".charAt(b & 0xF));
        }
        return result.toString();
    }
    
    private String getConfig() {
        final StringBuilder result = new StringBuilder();
        for (final Object checksumItem : this.checksumItems) {
            result.append((checksumItem != null) ? checksumItem.toString() : "null");
        }
        return result.toString();
    }
    
    public String getSha1() {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-1");
            final String configValue = this.getConfig();
            md.update(configValue.getBytes("iso-8859-1"), 0, configValue.length());
            final byte[] sha1hash = md.digest();
            return asHexString(sha1hash);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}
