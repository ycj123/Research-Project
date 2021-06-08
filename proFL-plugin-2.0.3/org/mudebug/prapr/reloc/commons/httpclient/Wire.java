// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.logging.Log;

class Wire
{
    public static Wire HEADER_WIRE;
    public static Wire CONTENT_WIRE;
    private Log log;
    
    private Wire(final Log log) {
        this.log = log;
    }
    
    private void wire(final String header, final InputStream instream) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuffer.<init>:()V
        //     7: astore_3        /* buffer */
        //     8: goto            127
        //    11: iload           4
        //    13: bipush          13
        //    15: if_icmpne       28
        //    18: aload_3         /* buffer */
        //    19: ldc             "[\\r]"
        //    21: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    24: pop            
        //    25: goto            127
        //    28: iload           4
        //    30: bipush          10
        //    32: if_icmpne       78
        //    35: aload_3         /* buffer */
        //    36: ldc             "[\\n]\""
        //    38: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    41: pop            
        //    42: aload_3         /* buffer */
        //    43: iconst_0       
        //    44: ldc             "\""
        //    46: invokevirtual   java/lang/StringBuffer.insert:(ILjava/lang/String;)Ljava/lang/StringBuffer;
        //    49: pop            
        //    50: aload_3         /* buffer */
        //    51: iconst_0       
        //    52: aload_1         /* header */
        //    53: invokevirtual   java/lang/StringBuffer.insert:(ILjava/lang/String;)Ljava/lang/StringBuffer;
        //    56: pop            
        //    57: aload_0         /* this */
        //    58: getfield        org/mudebug/prapr/reloc/commons/httpclient/Wire.log:Lorg/mudebug/prapr/reloc/commons/logging/Log;
        //    61: aload_3         /* buffer */
        //    62: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    65: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.debug:(Ljava/lang/Object;)V
        //    70: aload_3         /* buffer */
        //    71: iconst_0       
        //    72: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //    75: goto            127
        //    78: iload           4
        //    80: bipush          32
        //    82: if_icmplt       92
        //    85: iload           4
        //    87: bipush          127
        //    89: if_icmple       119
        //    92: aload_3         /* buffer */
        //    93: ldc             "[0x"
        //    95: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    98: pop            
        //    99: aload_3         /* buffer */
        //   100: iload           4
        //   102: invokestatic    java/lang/Integer.toHexString:(I)Ljava/lang/String;
        //   105: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   108: pop            
        //   109: aload_3         /* buffer */
        //   110: ldc             "]"
        //   112: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   115: pop            
        //   116: goto            127
        //   119: aload_3         /* buffer */
        //   120: iload           4
        //   122: i2c            
        //   123: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   126: pop            
        //   127: aload_2         /* instream */
        //   128: invokevirtual   java/io/InputStream.read:()I
        //   131: dup            
        //   132: istore          ch
        //   134: iconst_m1      
        //   135: if_icmpne       11
        //   138: aload_3         /* buffer */
        //   139: invokevirtual   java/lang/StringBuffer.length:()I
        //   142: ifle            180
        //   145: aload_3         /* buffer */
        //   146: ldc             "\""
        //   148: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   151: pop            
        //   152: aload_3         /* buffer */
        //   153: iconst_0       
        //   154: ldc             "\""
        //   156: invokevirtual   java/lang/StringBuffer.insert:(ILjava/lang/String;)Ljava/lang/StringBuffer;
        //   159: pop            
        //   160: aload_3         /* buffer */
        //   161: iconst_0       
        //   162: aload_1         /* header */
        //   163: invokevirtual   java/lang/StringBuffer.insert:(ILjava/lang/String;)Ljava/lang/StringBuffer;
        //   166: pop            
        //   167: aload_0         /* this */
        //   168: getfield        org/mudebug/prapr/reloc/commons/httpclient/Wire.log:Lorg/mudebug/prapr/reloc/commons/logging/Log;
        //   171: aload_3         /* buffer */
        //   172: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   175: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.debug:(Ljava/lang/Object;)V
        //   180: return         
        //    Exceptions:
        //  throws java.io.IOException
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
    
    public boolean enabled() {
        return this.log.isDebugEnabled();
    }
    
    public void output(final InputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        this.wire(">> ", outstream);
    }
    
    public void input(final InputStream instream) throws IOException {
        if (instream == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        this.wire("<< ", instream);
    }
    
    public void output(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        this.wire(">> ", new ByteArrayInputStream(b, off, len));
    }
    
    public void input(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        this.wire("<< ", new ByteArrayInputStream(b, off, len));
    }
    
    public void output(final byte[] b) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        this.wire(">> ", new ByteArrayInputStream(b));
    }
    
    public void input(final byte[] b) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        this.wire("<< ", new ByteArrayInputStream(b));
    }
    
    public void output(final int b) throws IOException {
        this.output(new byte[] { (byte)b });
    }
    
    public void input(final int b) throws IOException {
        this.input(new byte[] { (byte)b });
    }
    
    public void output(final String s) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        this.output(s.getBytes());
    }
    
    public void input(final String s) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        this.input(s.getBytes());
    }
    
    static {
        Wire.HEADER_WIRE = new Wire(LogFactory.getLog("httpclient.wire.header"));
        Wire.CONTENT_WIRE = new Wire(LogFactory.getLog("httpclient.wire.content"));
    }
}
