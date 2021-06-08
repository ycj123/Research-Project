// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class HttpParser
{
    private static final Log LOG;
    
    private HttpParser() {
    }
    
    public static byte[] readRawLine(final InputStream inputStream) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc             "enter HttpParser.readRawLine()"
        //     5: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.trace:(Ljava/lang/Object;)V
        //    10: new             Ljava/io/ByteArrayOutputStream;
        //    13: dup            
        //    14: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    17: astore_1        /* buf */
        //    18: goto            35
        //    21: aload_1         /* buf */
        //    22: iload_2        
        //    23: invokevirtual   java/io/ByteArrayOutputStream.write:(I)V
        //    26: iload_2        
        //    27: bipush          10
        //    29: if_icmpne       35
        //    32: goto            44
        //    35: aload_0         /* inputStream */
        //    36: invokevirtual   java/io/InputStream.read:()I
        //    39: dup            
        //    40: istore_2        /* ch */
        //    41: ifge            21
        //    44: aload_1         /* buf */
        //    45: invokevirtual   java/io/ByteArrayOutputStream.size:()I
        //    48: ifne            53
        //    51: aconst_null    
        //    52: areturn        
        //    53: aload_1         /* buf */
        //    54: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    57: areturn        
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
    
    public static String readLine(final InputStream inputStream) throws IOException {
        HttpParser.LOG.trace("enter HttpParser.readLine()");
        final byte[] rawdata = readRawLine(inputStream);
        if (rawdata == null) {
            return null;
        }
        final int len = rawdata.length;
        int offset = 0;
        if (len > 0 && rawdata[len - 1] == 10) {
            ++offset;
            if (len > 1 && rawdata[len - 2] == 13) {
                ++offset;
            }
        }
        return HttpConstants.getString(rawdata, 0, len - offset);
    }
    
    public static Header[] parseHeaders(final InputStream is) throws IOException, HttpException {
        HttpParser.LOG.trace("enter HeaderParser.parseHeaders(HttpConnection, HeaderGroup)");
        final ArrayList headers = new ArrayList();
        String name = null;
        StringBuffer value = null;
        while (true) {
            final String line = readLine(is);
            if (line == null || line.length() < 1) {
                if (name != null) {
                    headers.add(new Header(name, value.toString()));
                }
                return headers.toArray(new Header[headers.size()]);
            }
            if (line.charAt(0) == ' ' || line.charAt(0) == '\t') {
                if (value == null) {
                    continue;
                }
                value.append(' ');
                value.append(line.trim());
            }
            else {
                if (name != null) {
                    headers.add(new Header(name, value.toString()));
                }
                final int colon = line.indexOf(":");
                if (colon < 0) {
                    throw new HttpException("Unable to parse header: " + line);
                }
                name = line.substring(0, colon).trim();
                value = new StringBuffer(line.substring(colon + 1).trim());
            }
        }
    }
    
    static {
        LOG = LogFactory.getLog(HttpParser.class);
    }
}
