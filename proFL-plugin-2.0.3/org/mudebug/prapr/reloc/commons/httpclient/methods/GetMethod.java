// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.net.URLEncoder;
import org.mudebug.prapr.reloc.commons.httpclient.HttpException;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import java.io.File;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;

public class GetMethod extends HttpMethodBase
{
    private static final Log LOG;
    private static final String TEMP_DIR = "temp/";
    private File fileData;
    private String tempDir;
    private String tempFile;
    private boolean useDisk;
    
    public GetMethod() {
        this.tempDir = "temp/";
        this.tempFile = null;
        this.useDisk = false;
        this.setFollowRedirects(true);
    }
    
    public GetMethod(final String uri) {
        super(uri);
        this.tempDir = "temp/";
        this.tempFile = null;
        this.useDisk = false;
        GetMethod.LOG.trace("enter GetMethod(String)");
        this.setFollowRedirects(true);
    }
    
    public GetMethod(final String path, final String tempDir) {
        super(path);
        this.tempDir = "temp/";
        this.tempFile = null;
        this.useDisk = false;
        GetMethod.LOG.trace("enter GetMethod(String, String)");
        this.setUseDisk(true);
        this.setTempDir(tempDir);
        this.setFollowRedirects(true);
    }
    
    public GetMethod(final String path, final String tempDir, final String tempFile) {
        super(path);
        this.tempDir = "temp/";
        this.tempFile = null;
        this.useDisk = false;
        GetMethod.LOG.trace("enter GetMethod(String, String, String)");
        this.setUseDisk(true);
        this.setTempDir(tempDir);
        this.setTempFile(tempFile);
        this.setFollowRedirects(true);
    }
    
    public GetMethod(final String path, final File fileData) {
        this(path);
        GetMethod.LOG.trace("enter GetMethod(String, File)");
        this.useDisk = true;
        this.fileData = fileData;
        this.setFollowRedirects(true);
    }
    
    public void setFileData(final File fileData) {
        this.checkNotUsed();
        this.fileData = fileData;
    }
    
    public File getFileData() {
        return this.fileData;
    }
    
    public String getName() {
        return "GET";
    }
    
    public void setTempDir(final String tempDir) {
        this.checkNotUsed();
        this.tempDir = tempDir;
        this.setUseDisk(true);
    }
    
    public String getTempDir() {
        return this.tempDir;
    }
    
    public void setTempFile(final String tempFile) {
        this.checkNotUsed();
        this.tempFile = tempFile;
    }
    
    public String getTempFile() {
        return this.tempFile;
    }
    
    public void setUseDisk(final boolean useDisk) {
        this.checkNotUsed();
        this.useDisk = useDisk;
    }
    
    public boolean getUseDisk() {
        return this.useDisk;
    }
    
    public void recycle() {
        GetMethod.LOG.trace("enter GetMethod.recycle()");
        super.recycle();
        this.fileData = null;
        this.setFollowRedirects(true);
    }
    
    protected void readResponseBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc             "enter GetMethod.readResponseBody(HttpState, HttpConnection)"
        //     5: invokeinterface org/mudebug/prapr/reloc/commons/logging/Log.trace:(Ljava/lang/Object;)V
        //    10: aload_0         /* this */
        //    11: aload_1         /* state */
        //    12: aload_2         /* conn */
        //    13: invokespecial   org/mudebug/prapr/reloc/commons/httpclient/HttpMethodBase.readResponseBody:(Lorg/mudebug/prapr/reloc/commons/httpclient/HttpState;Lorg/mudebug/prapr/reloc/commons/httpclient/HttpConnection;)V
        //    16: aconst_null    
        //    17: astore_3        /* out */
        //    18: aload_0         /* this */
        //    19: getfield        org/mudebug/prapr/reloc/commons/httpclient/methods/GetMethod.useDisk:Z
        //    22: ifeq            99
        //    25: new             Ljava/io/FileOutputStream;
        //    28: dup            
        //    29: aload_0         /* this */
        //    30: invokespecial   org/mudebug/prapr/reloc/commons/httpclient/methods/GetMethod.createTempFile:()Ljava/io/File;
        //    33: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    36: astore_3        /* out */
        //    37: aload_0         /* this */
        //    38: invokevirtual   org/mudebug/prapr/reloc/commons/httpclient/HttpMethodBase.getResponseBodyAsStream:()Ljava/io/InputStream;
        //    41: astore          in
        //    43: sipush          10000
        //    46: newarray        B
        //    48: astore          buffer
        //    50: goto            62
        //    53: aload_3         /* out */
        //    54: aload           buffer
        //    56: iconst_0       
        //    57: iload           6
        //    59: invokevirtual   java/io/OutputStream.write:([BII)V
        //    62: aload           in
        //    64: aload           buffer
        //    66: invokevirtual   java/io/InputStream.read:([B)I
        //    69: dup            
        //    70: istore          len
        //    72: ifgt            53
        //    75: aload           in
        //    77: invokevirtual   java/io/InputStream.close:()V
        //    80: aload_3         /* out */
        //    81: invokevirtual   java/io/OutputStream.close:()V
        //    84: aload_0         /* this */
        //    85: new             Ljava/io/FileInputStream;
        //    88: dup            
        //    89: aload_0         /* this */
        //    90: invokespecial   org/mudebug/prapr/reloc/commons/httpclient/methods/GetMethod.createTempFile:()Ljava/io/File;
        //    93: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    96: invokevirtual   org/mudebug/prapr/reloc/commons/httpclient/HttpMethodBase.setResponseStream:(Ljava/io/InputStream;)V
        //    99: return         
        //    Exceptions:
        //  throws java.io.IOException
        //  throws org.mudebug.prapr.reloc.commons.httpclient.HttpException
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
    
    private File createTempFile() {
        if (this.fileData == null) {
            final File dir = new File(this.tempDir);
            dir.deleteOnExit();
            dir.mkdirs();
            String tempFileName = null;
            if (this.tempFile == null) {
                String encodedPath = URLEncoder.encode(this.getPath());
                final int length = encodedPath.length();
                if (length > 200) {
                    encodedPath = encodedPath.substring(length - 190, length);
                }
                tempFileName = System.currentTimeMillis() + "-" + encodedPath + ".tmp";
            }
            else {
                tempFileName = this.tempFile;
            }
            this.fileData = new File(this.tempDir, tempFileName);
            (this.fileData = new File(this.tempDir, tempFileName)).deleteOnExit();
        }
        return this.fileData;
    }
    
    static {
        LOG = LogFactory.getLog(GetMethod.class);
    }
}
