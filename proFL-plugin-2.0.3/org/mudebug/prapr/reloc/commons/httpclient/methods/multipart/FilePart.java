// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods.multipart;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.File;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class FilePart extends PartBase
{
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    private static final Log LOG;
    protected static final String FILE_NAME = "; filename=";
    protected static final byte[] FILE_NAME_BYTES;
    private PartSource source;
    
    public FilePart(final String name, final PartSource partSource, final String contentType, final String charset) {
        super(name, (contentType == null) ? "application/octet-stream" : contentType, (charset == null) ? "ISO-8859-1" : charset, "binary");
        if (partSource == null) {
            throw new IllegalArgumentException("Source may not be null");
        }
        if (partSource.getLength() < 0L) {
            throw new IllegalArgumentException("Source length must be >= 0");
        }
        this.source = partSource;
    }
    
    public FilePart(final String name, final PartSource partSource) {
        this(name, partSource, null, null);
    }
    
    public FilePart(final String name, final File file) throws FileNotFoundException {
        this(name, new FilePartSource(file), null, null);
    }
    
    public FilePart(final String name, final File file, final String contentType, final String charset) throws FileNotFoundException {
        this(name, new FilePartSource(file), contentType, charset);
    }
    
    public FilePart(final String name, final String fileName, final File file) throws FileNotFoundException {
        this(name, new FilePartSource(fileName, file), null, null);
    }
    
    public FilePart(final String name, final String fileName, final File file, final String contentType, final String charset) throws FileNotFoundException {
        this(name, new FilePartSource(fileName, file), contentType, charset);
    }
    
    protected void sendDispositionHeader(final OutputStream out) throws IOException {
        FilePart.LOG.trace("enter sendDispositionHeader(OutputStream out)");
        super.sendDispositionHeader(out);
        final String filename = this.source.getFileName();
        if (filename != null) {
            out.write(FilePart.FILE_NAME_BYTES);
            out.write(Part.QUOTE_BYTES);
            out.write(HttpConstants.getAsciiBytes(filename));
            out.write(Part.QUOTE_BYTES);
        }
    }
    
    protected void sendData(final OutputStream out) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc             "enter sendData(OutputStream out)"
        //     5: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.trace:(Ljava/lang/Object;)V
        //    10: aload_0         /* this */
        //    11: invokevirtual   org/mudebug/prapr/reloc/commons/httpclient/methods/multipart/FilePart.lengthOfData:()J
        //    14: lconst_0       
        //    15: lcmp           
        //    16: ifne            30
        //    19: getstatic       org/mudebug/prapr/reloc/commons/httpclient/methods/multipart/FilePart.LOG:Lorg/mudebug/prapr/reloc/commons/logging/Log;
        //    22: ldc             "No data to send."
        //    24: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.debug:(Ljava/lang/Object;)V
        //    29: return         
        //    30: sipush          4096
        //    33: newarray        B
        //    35: astore_2        /* tmp */
        //    36: aload_0         /* this */
        //    37: getfield        org/mudebug/prapr/reloc/commons/httpclient/methods/multipart/FilePart.source:Lorg/mudebug/prapr/reloc/commons/httpclient/methods/multipart/PartSource;
        //    40: invokeinterface org/mudebug/prapr/reloc/commons/httpclient/methods/multipart/PartSource.createInputStream:()Ljava/io/InputStream;
        //    45: astore_3        /* instream */
        //    46: goto            57
        //    49: aload_1         /* out */
        //    50: aload_2         /* tmp */
        //    51: iconst_0       
        //    52: iload           4
        //    54: invokevirtual   java/io/OutputStream.write:([BII)V
        //    57: aload_3         /* instream */
        //    58: aload_2         /* tmp */
        //    59: invokevirtual   java/io/InputStream.read:([B)I
        //    62: dup            
        //    63: istore          len
        //    65: ifge            49
        //    68: jsr             82
        //    71: goto            90
        //    74: astore          5
        //    76: jsr             82
        //    79: aload           5
        //    81: athrow         
        //    82: astore          6
        //    84: aload_3         /* instream */
        //    85: invokevirtual   java/io/InputStream.close:()V
        //    88: ret             6
        //    90: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  46     74     74     82     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2895)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected PartSource getSource() {
        FilePart.LOG.trace("enter getSource()");
        return this.source;
    }
    
    protected long lengthOfData() throws IOException {
        FilePart.LOG.trace("enter lengthOfData()");
        return this.source.getLength();
    }
    
    static {
        LOG = LogFactory.getLog(FilePart.class);
        FILE_NAME_BYTES = HttpConstants.getAsciiBytes("; filename=");
    }
}
