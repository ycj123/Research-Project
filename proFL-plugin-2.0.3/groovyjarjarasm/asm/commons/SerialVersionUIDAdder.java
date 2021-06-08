// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import java.security.MessageDigest;
import java.io.IOException;
import java.io.DataOutput;
import java.util.Arrays;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import groovyjarjarasm.asm.FieldVisitor;
import groovyjarjarasm.asm.MethodVisitor;
import java.util.ArrayList;
import groovyjarjarasm.asm.ClassVisitor;
import java.util.Collection;
import groovyjarjarasm.asm.ClassAdapter;

public class SerialVersionUIDAdder extends ClassAdapter
{
    protected boolean computeSVUID;
    protected boolean hasSVUID;
    protected int access;
    protected String name;
    protected String[] interfaces;
    protected Collection svuidFields;
    protected boolean hasStaticInitializer;
    protected Collection svuidConstructors;
    protected Collection svuidMethods;
    
    public SerialVersionUIDAdder(final ClassVisitor classVisitor) {
        super(classVisitor);
        this.svuidFields = new ArrayList();
        this.svuidConstructors = new ArrayList();
        this.svuidMethods = new ArrayList();
    }
    
    public void visit(final int n, final int access, final String name, final String s, final String s2, final String[] interfaces) {
        this.computeSVUID = ((access & 0x200) == 0x0);
        if (this.computeSVUID) {
            this.name = name;
            this.access = access;
            this.interfaces = interfaces;
        }
        super.visit(n, access, name, s, s2, interfaces);
    }
    
    public MethodVisitor visitMethod(final int n, final String anObject, final String s, final String s2, final String[] array) {
        if (this.computeSVUID) {
            if ("<clinit>".equals(anObject)) {
                this.hasStaticInitializer = true;
            }
            final int n2 = n & 0xD3F;
            if ((n & 0x2) == 0x0) {
                if ("<init>".equals(anObject)) {
                    this.svuidConstructors.add(new SerialVersionUIDAdder$Item(anObject, n2, s));
                }
                else if (!"<clinit>".equals(anObject)) {
                    this.svuidMethods.add(new SerialVersionUIDAdder$Item(anObject, n2, s));
                }
            }
        }
        return this.cv.visitMethod(n, anObject, s, s2, array);
    }
    
    public FieldVisitor visitField(final int n, final String anObject, final String s, final String s2, final Object o) {
        if (this.computeSVUID) {
            if ("serialVersionUID".equals(anObject)) {
                this.computeSVUID = false;
                this.hasSVUID = true;
            }
            final int n2 = n & 0xDF;
            if ((n & 0x2) == 0x0 || (n & 0x88) == 0x0) {
                this.svuidFields.add(new SerialVersionUIDAdder$Item(anObject, n2, s));
            }
        }
        return super.visitField(n, anObject, s, s2, o);
    }
    
    public void visitInnerClass(final String s, final String prefix, final String suffix, final int access) {
        if (prefix != null && suffix != null && this.name != null) {
            final int length = this.name.length();
            final int length2 = suffix.length();
            final int length3 = prefix.length();
            if (length == length3 + 1 + length2 && this.name.startsWith(prefix) && this.name.endsWith(suffix) && this.name.charAt(length3) == '$') {
                this.access = access;
            }
        }
        super.visitInnerClass(s, prefix, suffix, access);
    }
    
    public void visitEnd() {
        if (this.computeSVUID && !this.hasSVUID) {
            try {
                this.cv.visitField(24, "serialVersionUID", "J", null, new Long(this.computeSVUID()));
            }
            catch (Throwable cause) {
                throw new RuntimeException("Error while computing SVUID for " + this.name, cause);
            }
        }
        super.visitEnd();
    }
    
    protected long computeSVUID() throws IOException {
        DataOutputStream dataOutputStream = null;
        long n = 0L;
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(this.name.replace('/', '.'));
            dataOutputStream.writeInt(this.access & 0x611);
            Arrays.sort(this.interfaces);
            for (int i = 0; i < this.interfaces.length; ++i) {
                dataOutputStream.writeUTF(this.interfaces[i].replace('/', '.'));
            }
            writeItems(this.svuidFields, dataOutputStream, false);
            if (this.hasStaticInitializer) {
                dataOutputStream.writeUTF("<clinit>");
                dataOutputStream.writeInt(8);
                dataOutputStream.writeUTF("()V");
            }
            writeItems(this.svuidConstructors, dataOutputStream, true);
            writeItems(this.svuidMethods, dataOutputStream, true);
            dataOutputStream.flush();
            final byte[] computeSHAdigest = this.computeSHAdigest(out.toByteArray());
            for (int j = Math.min(computeSHAdigest.length, 8) - 1; j >= 0; --j) {
                n = (n << 8 | (long)(computeSHAdigest[j] & 0xFF));
            }
        }
        finally {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        }
        return n;
    }
    
    protected byte[] computeSHAdigest(final byte[] input) {
        try {
            return MessageDigest.getInstance("SHA").digest(input);
        }
        catch (Exception ex) {
            throw new UnsupportedOperationException(ex.toString());
        }
    }
    
    private static void writeItems(final Collection collection, final DataOutput dataOutput, final boolean b) throws IOException {
        final int size = collection.size();
        final SerialVersionUIDAdder$Item[] a = collection.toArray(new SerialVersionUIDAdder$Item[size]);
        Arrays.sort(a);
        for (int i = 0; i < size; ++i) {
            dataOutput.writeUTF(a[i].name);
            dataOutput.writeInt(a[i].access);
            dataOutput.writeUTF(b ? a[i].desc.replace('/', '.') : a[i].desc);
        }
    }
}
