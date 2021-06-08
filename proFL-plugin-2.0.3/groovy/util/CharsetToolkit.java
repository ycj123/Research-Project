// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Collection;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.nio.charset.Charset;

public class CharsetToolkit
{
    private byte[] buffer;
    private Charset defaultCharset;
    private Charset charset;
    private boolean enforce8Bit;
    private final File file;
    private static final byte[] EMPTY_BYTE_ARRAY;
    
    public CharsetToolkit(final File file) throws IOException {
        this.enforce8Bit = true;
        this.file = file;
        this.defaultCharset = getDefaultSystemCharset();
        this.charset = null;
        final InputStream input = new FileInputStream(file);
        try {
            final byte[] bytes = new byte[4096];
            final int bytesRead = input.read(bytes);
            if (bytesRead == -1) {
                this.buffer = CharsetToolkit.EMPTY_BYTE_ARRAY;
            }
            else if (bytesRead < 4096) {
                final byte[] bytesToGuess = new byte[bytesRead];
                System.arraycopy(bytes, 0, bytesToGuess, 0, bytesRead);
                this.buffer = bytesToGuess;
            }
            else {
                this.buffer = bytes;
            }
        }
        finally {
            try {
                input.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public void setDefaultCharset(final Charset defaultCharset) {
        if (defaultCharset != null) {
            this.defaultCharset = defaultCharset;
        }
        else {
            this.defaultCharset = getDefaultSystemCharset();
        }
    }
    
    public Charset getCharset() {
        if (this.charset == null) {
            this.charset = this.guessEncoding();
        }
        return this.charset;
    }
    
    public void setEnforce8Bit(final boolean enforce) {
        this.enforce8Bit = enforce;
    }
    
    public boolean getEnforce8Bit() {
        return this.enforce8Bit;
    }
    
    public Charset getDefaultCharset() {
        return this.defaultCharset;
    }
    
    private Charset guessEncoding() {
        if (this.hasUTF8Bom()) {
            return Charset.forName("UTF-8");
        }
        if (this.hasUTF16LEBom()) {
            return Charset.forName("UTF-16LE");
        }
        if (this.hasUTF16BEBom()) {
            return Charset.forName("UTF-16BE");
        }
        boolean highOrderBit = false;
        boolean validU8Char = true;
        for (int length = this.buffer.length, i = 0; i < length - 6; ++i) {
            final byte b0 = this.buffer[i];
            final byte b2 = this.buffer[i + 1];
            final byte b3 = this.buffer[i + 2];
            final byte b4 = this.buffer[i + 3];
            final byte b5 = this.buffer[i + 4];
            final byte b6 = this.buffer[i + 5];
            if (b0 < 0) {
                highOrderBit = true;
                if (isTwoBytesSequence(b0)) {
                    if (!isContinuationChar(b2)) {
                        validU8Char = false;
                    }
                    else {
                        ++i;
                    }
                }
                else if (isThreeBytesSequence(b0)) {
                    if (!isContinuationChar(b2) || !isContinuationChar(b3)) {
                        validU8Char = false;
                    }
                    else {
                        i += 2;
                    }
                }
                else if (isFourBytesSequence(b0)) {
                    if (!isContinuationChar(b2) || !isContinuationChar(b3) || !isContinuationChar(b4)) {
                        validU8Char = false;
                    }
                    else {
                        i += 3;
                    }
                }
                else if (isFiveBytesSequence(b0)) {
                    if (!isContinuationChar(b2) || !isContinuationChar(b3) || !isContinuationChar(b4) || !isContinuationChar(b5)) {
                        validU8Char = false;
                    }
                    else {
                        i += 4;
                    }
                }
                else if (isSixBytesSequence(b0)) {
                    if (!isContinuationChar(b2) || !isContinuationChar(b3) || !isContinuationChar(b4) || !isContinuationChar(b5) || !isContinuationChar(b6)) {
                        validU8Char = false;
                    }
                    else {
                        i += 5;
                    }
                }
                else {
                    validU8Char = false;
                }
            }
            if (!validU8Char) {
                break;
            }
        }
        if (!highOrderBit) {
            if (this.enforce8Bit) {
                return this.defaultCharset;
            }
            return Charset.forName("US-ASCII");
        }
        else {
            if (validU8Char) {
                return Charset.forName("UTF-8");
            }
            return this.defaultCharset;
        }
    }
    
    private static boolean isContinuationChar(final byte b) {
        return -128 <= b && b <= -65;
    }
    
    private static boolean isTwoBytesSequence(final byte b) {
        return -64 <= b && b <= -33;
    }
    
    private static boolean isThreeBytesSequence(final byte b) {
        return -32 <= b && b <= -17;
    }
    
    private static boolean isFourBytesSequence(final byte b) {
        return -16 <= b && b <= -9;
    }
    
    private static boolean isFiveBytesSequence(final byte b) {
        return -8 <= b && b <= -5;
    }
    
    private static boolean isSixBytesSequence(final byte b) {
        return -4 <= b && b <= -3;
    }
    
    public static Charset getDefaultSystemCharset() {
        return Charset.forName(System.getProperty("file.encoding"));
    }
    
    public boolean hasUTF8Bom() {
        return this.buffer.length >= 3 && this.buffer[0] == -17 && this.buffer[1] == -69 && this.buffer[2] == -65;
    }
    
    public boolean hasUTF16LEBom() {
        return this.buffer.length >= 2 && this.buffer[0] == -1 && this.buffer[1] == -2;
    }
    
    public boolean hasUTF16BEBom() {
        return this.buffer.length >= 2 && this.buffer[0] == -2 && this.buffer[1] == -1;
    }
    
    public BufferedReader getReader() throws FileNotFoundException {
        final LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(this.file), this.getCharset()));
        if (!this.hasUTF8Bom() && !this.hasUTF16LEBom()) {
            if (!this.hasUTF16BEBom()) {
                return reader;
            }
        }
        try {
            reader.read();
        }
        catch (IOException ex) {}
        return reader;
    }
    
    public static Charset[] getAvailableCharsets() {
        final Collection collection = Charset.availableCharsets().values();
        return collection.toArray(new Charset[collection.size()]);
    }
    
    static {
        EMPTY_BYTE_ARRAY = new byte[0];
    }
}
