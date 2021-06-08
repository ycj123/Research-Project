// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm;

public class ClassWriter implements ClassVisitor
{
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    static final byte[] a;
    ClassReader J;
    int b;
    int c;
    final ByteVector d;
    Item[] e;
    int f;
    final Item g;
    final Item h;
    final Item i;
    Item[] E;
    private short D;
    private int j;
    private int k;
    String F;
    private int l;
    private int m;
    private int n;
    private int[] o;
    private int p;
    private ByteVector q;
    private int r;
    private int s;
    private AnnotationWriter t;
    private AnnotationWriter u;
    private Attribute v;
    private int w;
    private ByteVector x;
    FieldWriter y;
    FieldWriter z;
    MethodWriter A;
    MethodWriter B;
    private final boolean H;
    private final boolean G;
    boolean I;
    
    public ClassWriter(final int n) {
        this.c = 1;
        this.d = new ByteVector();
        this.e = new Item[256];
        this.f = (int)(0.75 * this.e.length);
        this.g = new Item();
        this.h = new Item();
        this.i = new Item();
        this.H = ((n & 0x1) != 0x0);
        this.G = ((n & 0x2) != 0x0);
    }
    
    public ClassWriter(final ClassReader j, final int n) {
        this(n);
        j.a(this);
        this.J = j;
    }
    
    public void visit(final int b, final int j, final String f, final String s, final String s2, final String[] array) {
        this.b = b;
        this.j = j;
        this.k = this.newClass(f);
        this.F = f;
        if (s != null) {
            this.l = this.newUTF8(s);
        }
        this.m = ((s2 == null) ? 0 : this.newClass(s2));
        if (array != null && array.length > 0) {
            this.n = array.length;
            this.o = new int[this.n];
            for (int i = 0; i < this.n; ++i) {
                this.o[i] = this.newClass(array[i]);
            }
        }
    }
    
    public void visitSource(final String s, final String s2) {
        if (s != null) {
            this.p = this.newUTF8(s);
        }
        if (s2 != null) {
            this.q = new ByteVector().putUTF8(s2);
        }
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        this.r = this.newClass(s);
        if (s2 != null && s3 != null) {
            this.s = this.newNameType(s2, s3);
        }
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.newUTF8(s)).putShort(0);
        final AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (b) {
            annotationWriter.g = this.t;
            this.t = annotationWriter;
        }
        else {
            annotationWriter.g = this.u;
            this.u = annotationWriter;
        }
        return annotationWriter;
    }
    
    public void visitAttribute(final Attribute v) {
        v.a = this.v;
        this.v = v;
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        if (this.x == null) {
            this.x = new ByteVector();
        }
        ++this.w;
        this.x.putShort((s == null) ? 0 : this.newClass(s));
        this.x.putShort((s2 == null) ? 0 : this.newClass(s2));
        this.x.putShort((s3 == null) ? 0 : this.newUTF8(s3));
        this.x.putShort(n);
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        return new FieldWriter(this, n, s, s2, s3, o);
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        return new MethodWriter(this, n, s, s2, s3, array, this.H, this.G);
    }
    
    public void visitEnd() {
    }
    
    public byte[] toByteArray() {
        int n = 24 + 2 * this.n;
        int n2 = 0;
        for (FieldWriter fieldWriter = this.y; fieldWriter != null; fieldWriter = fieldWriter.a) {
            ++n2;
            n += fieldWriter.a();
        }
        int n3 = 0;
        for (MethodWriter methodWriter = this.A; methodWriter != null; methodWriter = methodWriter.a) {
            ++n3;
            n += methodWriter.a();
        }
        int n4 = 0;
        if (this.l != 0) {
            ++n4;
            n += 8;
            this.newUTF8("Signature");
        }
        if (this.p != 0) {
            ++n4;
            n += 8;
            this.newUTF8("SourceFile");
        }
        if (this.q != null) {
            ++n4;
            n += this.q.b + 4;
            this.newUTF8("SourceDebugExtension");
        }
        if (this.r != 0) {
            ++n4;
            n += 10;
            this.newUTF8("EnclosingMethod");
        }
        if ((this.j & 0x20000) != 0x0) {
            ++n4;
            n += 6;
            this.newUTF8("Deprecated");
        }
        if ((this.j & 0x1000) != 0x0 && (this.b & 0xFFFF) < 49) {
            ++n4;
            n += 6;
            this.newUTF8("Synthetic");
        }
        if (this.x != null) {
            ++n4;
            n += 8 + this.x.b;
            this.newUTF8("InnerClasses");
        }
        if (this.t != null) {
            ++n4;
            n += 8 + this.t.a();
            this.newUTF8("RuntimeVisibleAnnotations");
        }
        if (this.u != null) {
            ++n4;
            n += 8 + this.u.a();
            this.newUTF8("RuntimeInvisibleAnnotations");
        }
        if (this.v != null) {
            n4 += this.v.a();
            n += this.v.a(this, null, 0, -1, -1);
        }
        final ByteVector byteVector = new ByteVector(n + this.d.b);
        byteVector.putInt(-889275714).putInt(this.b);
        byteVector.putShort(this.c).putByteArray(this.d.a, 0, this.d.b);
        byteVector.putShort(this.j).putShort(this.k).putShort(this.m);
        byteVector.putShort(this.n);
        for (int i = 0; i < this.n; ++i) {
            byteVector.putShort(this.o[i]);
        }
        byteVector.putShort(n2);
        for (FieldWriter fieldWriter2 = this.y; fieldWriter2 != null; fieldWriter2 = fieldWriter2.a) {
            fieldWriter2.a(byteVector);
        }
        byteVector.putShort(n3);
        for (MethodWriter methodWriter2 = this.A; methodWriter2 != null; methodWriter2 = methodWriter2.a) {
            methodWriter2.a(byteVector);
        }
        byteVector.putShort(n4);
        if (this.l != 0) {
            byteVector.putShort(this.newUTF8("Signature")).putInt(2).putShort(this.l);
        }
        if (this.p != 0) {
            byteVector.putShort(this.newUTF8("SourceFile")).putInt(2).putShort(this.p);
        }
        if (this.q != null) {
            final int n5 = this.q.b - 2;
            byteVector.putShort(this.newUTF8("SourceDebugExtension")).putInt(n5);
            byteVector.putByteArray(this.q.a, 2, n5);
        }
        if (this.r != 0) {
            byteVector.putShort(this.newUTF8("EnclosingMethod")).putInt(4);
            byteVector.putShort(this.r).putShort(this.s);
        }
        if ((this.j & 0x20000) != 0x0) {
            byteVector.putShort(this.newUTF8("Deprecated")).putInt(0);
        }
        if ((this.j & 0x1000) != 0x0 && (this.b & 0xFFFF) < 49) {
            byteVector.putShort(this.newUTF8("Synthetic")).putInt(0);
        }
        if (this.x != null) {
            byteVector.putShort(this.newUTF8("InnerClasses"));
            byteVector.putInt(this.x.b + 2).putShort(this.w);
            byteVector.putByteArray(this.x.a, 0, this.x.b);
        }
        if (this.t != null) {
            byteVector.putShort(this.newUTF8("RuntimeVisibleAnnotations"));
            this.t.a(byteVector);
        }
        if (this.u != null) {
            byteVector.putShort(this.newUTF8("RuntimeInvisibleAnnotations"));
            this.u.a(byteVector);
        }
        if (this.v != null) {
            this.v.a(this, null, 0, -1, -1, byteVector);
        }
        if (this.I) {
            final ClassWriter classWriter = new ClassWriter(2);
            new ClassReader(byteVector.a).accept(classWriter, 4);
            return classWriter.toByteArray();
        }
        return byteVector.a;
    }
    
    Item a(final Object obj) {
        if (obj instanceof Integer) {
            return this.a((int)obj);
        }
        if (obj instanceof Byte) {
            return this.a((int)obj);
        }
        if (obj instanceof Character) {
            return this.a((char)obj);
        }
        if (obj instanceof Short) {
            return this.a((int)obj);
        }
        if (obj instanceof Boolean) {
            return this.a(((boolean)obj) ? 1 : 0);
        }
        if (obj instanceof Float) {
            return this.a((float)obj);
        }
        if (obj instanceof Long) {
            return this.a((long)obj);
        }
        if (obj instanceof Double) {
            return this.a((double)obj);
        }
        if (obj instanceof String) {
            return this.b((String)obj);
        }
        if (obj instanceof Type) {
            final Type type = (Type)obj;
            return this.a((type.getSort() == 10) ? type.getInternalName() : type.getDescriptor());
        }
        throw new IllegalArgumentException("value " + obj);
    }
    
    public int newConst(final Object o) {
        return this.a(o).a;
    }
    
    public int newUTF8(final String s) {
        this.g.a(1, s, null, null);
        Item a = this.a(this.g);
        if (a == null) {
            this.d.putByte(1).putUTF8(s);
            a = new Item(this.c++, this.g);
            this.b(a);
        }
        return a.a;
    }
    
    Item a(final String s) {
        this.h.a(7, s, null, null);
        Item a = this.a(this.h);
        if (a == null) {
            this.d.b(7, this.newUTF8(s));
            a = new Item(this.c++, this.h);
            this.b(a);
        }
        return a;
    }
    
    public int newClass(final String s) {
        return this.a(s).a;
    }
    
    Item a(final String s, final String s2, final String s3) {
        this.i.a(9, s, s2, s3);
        Item a = this.a(this.i);
        if (a == null) {
            this.a(9, this.newClass(s), this.newNameType(s2, s3));
            a = new Item(this.c++, this.i);
            this.b(a);
        }
        return a;
    }
    
    public int newField(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3).a;
    }
    
    Item a(final String s, final String s2, final String s3, final boolean b) {
        final int n = b ? 11 : 10;
        this.i.a(n, s, s2, s3);
        Item a = this.a(this.i);
        if (a == null) {
            this.a(n, this.newClass(s), this.newNameType(s2, s3));
            a = new Item(this.c++, this.i);
            this.b(a);
        }
        return a;
    }
    
    public int newMethod(final String s, final String s2, final String s3, final boolean b) {
        return this.a(s, s2, s3, b).a;
    }
    
    Item a(final int n) {
        this.g.a(n);
        Item a = this.a(this.g);
        if (a == null) {
            this.d.putByte(3).putInt(n);
            a = new Item(this.c++, this.g);
            this.b(a);
        }
        return a;
    }
    
    Item a(final float n) {
        this.g.a(n);
        Item a = this.a(this.g);
        if (a == null) {
            this.d.putByte(4).putInt(this.g.c);
            a = new Item(this.c++, this.g);
            this.b(a);
        }
        return a;
    }
    
    Item a(final long n) {
        this.g.a(n);
        Item a = this.a(this.g);
        if (a == null) {
            this.d.putByte(5).putLong(n);
            a = new Item(this.c, this.g);
            this.b(a);
            this.c += 2;
        }
        return a;
    }
    
    Item a(final double n) {
        this.g.a(n);
        Item a = this.a(this.g);
        if (a == null) {
            this.d.putByte(6).putLong(this.g.d);
            a = new Item(this.c, this.g);
            this.b(a);
            this.c += 2;
        }
        return a;
    }
    
    private Item b(final String s) {
        this.h.a(8, s, null, null);
        Item a = this.a(this.h);
        if (a == null) {
            this.d.b(8, this.newUTF8(s));
            a = new Item(this.c++, this.h);
            this.b(a);
        }
        return a;
    }
    
    public int newNameType(final String s, final String s2) {
        return this.a(s, s2).a;
    }
    
    Item a(final String s, final String s2) {
        this.h.a(12, s, s2, null);
        Item a = this.a(this.h);
        if (a == null) {
            this.a(12, this.newUTF8(s), this.newUTF8(s2));
            a = new Item(this.c++, this.h);
            this.b(a);
        }
        return a;
    }
    
    int c(final String s) {
        this.g.a(13, s, null, null);
        Item item = this.a(this.g);
        if (item == null) {
            item = this.c(this.g);
        }
        return item.a;
    }
    
    int a(final String g, final int c) {
        this.g.b = 14;
        this.g.c = c;
        this.g.g = g;
        this.g.j = (Integer.MAX_VALUE & 14 + g.hashCode() + c);
        Item item = this.a(this.g);
        if (item == null) {
            item = this.c(this.g);
        }
        return item.a;
    }
    
    private Item c(final Item item) {
        ++this.D;
        final Item item2 = new Item(this.D, this.g);
        this.b(item2);
        if (this.E == null) {
            this.E = new Item[16];
        }
        if (this.D == this.E.length) {
            final Item[] e = new Item[2 * this.E.length];
            System.arraycopy(this.E, 0, e, 0, this.E.length);
            this.E = e;
        }
        return this.E[this.D] = item2;
    }
    
    int a(final int n, final int n2) {
        this.h.b = 15;
        this.h.d = ((long)n | (long)n2 << 32);
        this.h.j = (Integer.MAX_VALUE & 15 + n + n2);
        Item a = this.a(this.h);
        if (a == null) {
            this.h.c = this.c(this.getCommonSuperClass(this.E[n].g, this.E[n2].g));
            a = new Item(0, this.h);
            this.b(a);
        }
        return a.c;
    }
    
    protected String getCommonSuperClass(final String s, final String s2) {
        Class<?> clazz;
        Class<?> forName;
        try {
            clazz = Class.forName(s.replace('/', '.'));
            forName = Class.forName(s2.replace('/', '.'));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
        if (clazz.isAssignableFrom(forName)) {
            return s;
        }
        if (forName.isAssignableFrom(clazz)) {
            return s2;
        }
        if (clazz.isInterface() || forName.isInterface()) {
            return "java/lang/Object";
        }
        do {
            clazz = clazz.getSuperclass();
        } while (!clazz.isAssignableFrom(forName));
        return clazz.getName().replace('.', '/');
    }
    
    private Item a(final Item item) {
        Item k;
        for (k = this.e[item.j % this.e.length]; k != null && (k.b != item.b || !item.a(k)); k = k.k) {}
        return k;
    }
    
    private void b(final Item item) {
        if (this.c > this.f) {
            final int length = this.e.length;
            final int n = length * 2 + 1;
            final Item[] e = new Item[n];
            for (int i = length - 1; i >= 0; --i) {
                Item k;
                for (Item item2 = this.e[i]; item2 != null; item2 = k) {
                    final int n2 = item2.j % e.length;
                    k = item2.k;
                    item2.k = e[n2];
                    e[n2] = item2;
                }
            }
            this.e = e;
            this.f = (int)(n * 0.75);
        }
        final int n3 = item.j % this.e.length;
        item.k = this.e[n3];
        this.e[n3] = item;
    }
    
    private void a(final int n, final int n2, final int n3) {
        this.d.b(n, n2).putShort(n3);
    }
    
    static {
        final byte[] a2 = new byte[220];
        final String s = "AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHHFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII";
        for (int i = 0; i < a2.length; ++i) {
            a2[i] = (byte)(s.charAt(i) - 'A');
        }
        a = a2;
    }
}
