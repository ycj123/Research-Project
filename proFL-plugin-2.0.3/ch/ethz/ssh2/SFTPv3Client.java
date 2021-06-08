// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.util.Vector;
import ch.ethz.ssh2.packets.TypesReader;
import ch.ethz.ssh2.packets.TypesWriter;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SFTPv3Client
{
    final Connection conn;
    final Session sess;
    final PrintStream debug;
    boolean flag_closed;
    InputStream is;
    OutputStream os;
    int protocol_version;
    HashMap server_extensions;
    int next_request_id;
    String charsetName;
    
    public SFTPv3Client(final Connection conn, final PrintStream debug) throws IOException {
        this.flag_closed = false;
        this.protocol_version = 0;
        this.server_extensions = new HashMap();
        this.next_request_id = 1000;
        this.charsetName = null;
        if (conn == null) {
            throw new IllegalArgumentException("Cannot accept null argument!");
        }
        this.conn = conn;
        if ((this.debug = debug) != null) {
            debug.println("Opening session and starting SFTP subsystem.");
        }
        (this.sess = conn.openSession()).startSubSystem("sftp");
        this.is = this.sess.getStdout();
        this.os = new BufferedOutputStream(this.sess.getStdin(), 2048);
        if (this.is == null || this.os == null) {
            throw new IOException("There is a problem with the streams of the underlying channel.");
        }
        this.init();
    }
    
    public SFTPv3Client(final Connection conn) throws IOException {
        this(conn, null);
    }
    
    public void setCharset(final String charset) throws IOException {
        if (charset == null) {
            this.charsetName = charset;
            return;
        }
        try {
            Charset.forName(charset);
        }
        catch (Exception e) {
            throw (IOException)new IOException("This charset is not supported").initCause(e);
        }
        this.charsetName = charset;
    }
    
    public String getCharset() {
        return this.charsetName;
    }
    
    private final void checkHandleValidAndOpen(final SFTPv3FileHandle handle) throws IOException {
        if (handle.client != this) {
            throw new IOException("The file handle was created with another SFTPv3FileHandle instance.");
        }
        if (handle.isClosed) {
            throw new IOException("The file handle is closed.");
        }
    }
    
    private final void sendMessage(final int type, final int requestId, final byte[] msg, final int off, final int len) throws IOException {
        int msglen = len + 1;
        if (type != 1) {
            msglen += 4;
        }
        this.os.write(msglen >> 24);
        this.os.write(msglen >> 16);
        this.os.write(msglen >> 8);
        this.os.write(msglen);
        this.os.write(type);
        if (type != 1) {
            this.os.write(requestId >> 24);
            this.os.write(requestId >> 16);
            this.os.write(requestId >> 8);
            this.os.write(requestId);
        }
        this.os.write(msg, off, len);
        this.os.flush();
    }
    
    private final void sendMessage(final int type, final int requestId, final byte[] msg) throws IOException {
        this.sendMessage(type, requestId, msg, 0, msg.length);
    }
    
    private final void readBytes(final byte[] buff, int pos, int len) throws IOException {
        while (len > 0) {
            final int count = this.is.read(buff, pos, len);
            if (count < 0) {
                throw new IOException("Unexpected end of sftp stream.");
            }
            if (count == 0 || count > len) {
                throw new IOException("Underlying stream implementation is bogus!");
            }
            len -= count;
            pos += count;
        }
    }
    
    private final byte[] receiveMessage(final int maxlen) throws IOException {
        final byte[] msglen = new byte[4];
        this.readBytes(msglen, 0, 4);
        final int len = (msglen[0] & 0xFF) << 24 | (msglen[1] & 0xFF) << 16 | (msglen[2] & 0xFF) << 8 | (msglen[3] & 0xFF);
        if (len > maxlen || len <= 0) {
            throw new IOException("Illegal sftp packet len: " + len);
        }
        final byte[] msg = new byte[len];
        this.readBytes(msg, 0, len);
        return msg;
    }
    
    private final int generateNextRequestID() {
        synchronized (this) {
            return this.next_request_id++;
        }
    }
    
    private final void closeHandle(final byte[] handle) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(handle, 0, handle.length);
        this.sendMessage(4, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    private SFTPv3FileAttributes readAttrs(final TypesReader tr) throws IOException {
        final SFTPv3FileAttributes fa = new SFTPv3FileAttributes();
        final int flags = tr.readUINT32();
        if ((flags & 0x1) != 0x0) {
            if (this.debug != null) {
                this.debug.println("SSH_FILEXFER_ATTR_SIZE");
            }
            fa.size = new Long(tr.readUINT64());
        }
        if ((flags & 0x2) != 0x0) {
            if (this.debug != null) {
                this.debug.println("SSH_FILEXFER_ATTR_V3_UIDGID");
            }
            fa.uid = new Integer(tr.readUINT32());
            fa.gid = new Integer(tr.readUINT32());
        }
        if ((flags & 0x4) != 0x0) {
            if (this.debug != null) {
                this.debug.println("SSH_FILEXFER_ATTR_PERMISSIONS");
            }
            fa.permissions = new Integer(tr.readUINT32());
        }
        if ((flags & 0x8) != 0x0) {
            if (this.debug != null) {
                this.debug.println("SSH_FILEXFER_ATTR_V3_ACMODTIME");
            }
            fa.atime = new Integer(tr.readUINT32());
            fa.mtime = new Integer(tr.readUINT32());
        }
        if ((flags & Integer.MIN_VALUE) != 0x0) {
            int count = tr.readUINT32();
            if (this.debug != null) {
                this.debug.println("SSH_FILEXFER_ATTR_EXTENDED (" + count + ")");
            }
            while (count > 0) {
                tr.readByteString();
                tr.readByteString();
                --count;
            }
        }
        return fa;
    }
    
    public SFTPv3FileAttributes fstat(final SFTPv3FileHandle handle) throws IOException {
        this.checkHandleValidAndOpen(handle);
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(handle.fileHandle, 0, handle.fileHandle.length);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_FSTAT...");
            this.debug.flush();
        }
        this.sendMessage(8, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        if (this.debug != null) {
            this.debug.println("Got REPLY.");
            this.debug.flush();
        }
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 105) {
            return this.readAttrs(tr);
        }
        if (t != 101) {
            throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
        }
        final int errorCode = tr.readUINT32();
        throw new SFTPException(tr.readString(), errorCode);
    }
    
    private SFTPv3FileAttributes statBoth(final String path, final int statMethod) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(path, this.charsetName);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_STAT/SSH_FXP_LSTAT...");
            this.debug.flush();
        }
        this.sendMessage(statMethod, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        if (this.debug != null) {
            this.debug.println("Got REPLY.");
            this.debug.flush();
        }
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 105) {
            return this.readAttrs(tr);
        }
        if (t != 101) {
            throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
        }
        final int errorCode = tr.readUINT32();
        throw new SFTPException(tr.readString(), errorCode);
    }
    
    public SFTPv3FileAttributes stat(final String path) throws IOException {
        return this.statBoth(path, 17);
    }
    
    public SFTPv3FileAttributes lstat(final String path) throws IOException {
        return this.statBoth(path, 7);
    }
    
    public String readLink(final String path) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(path, this.charsetName);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_READLINK...");
            this.debug.flush();
        }
        this.sendMessage(19, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        if (this.debug != null) {
            this.debug.println("Got REPLY.");
            this.debug.flush();
        }
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 104) {
            final int count = tr.readUINT32();
            if (count != 1) {
                throw new IOException("The server sent an invalid SSH_FXP_NAME packet.");
            }
            return tr.readString(this.charsetName);
        }
        else {
            if (t != 101) {
                throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
            }
            final int errorCode = tr.readUINT32();
            throw new SFTPException(tr.readString(), errorCode);
        }
    }
    
    private void expectStatusOKMessage(final int id) throws IOException {
        final byte[] resp = this.receiveMessage(34000);
        if (this.debug != null) {
            this.debug.println("Got REPLY.");
            this.debug.flush();
        }
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t != 101) {
            throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
        }
        final int errorCode = tr.readUINT32();
        if (errorCode == 0) {
            return;
        }
        throw new SFTPException(tr.readString(), errorCode);
    }
    
    public void setstat(final String path, final SFTPv3FileAttributes attr) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(path, this.charsetName);
        tw.writeBytes(this.createAttrs(attr));
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_SETSTAT...");
            this.debug.flush();
        }
        this.sendMessage(9, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public void fsetstat(final SFTPv3FileHandle handle, final SFTPv3FileAttributes attr) throws IOException {
        this.checkHandleValidAndOpen(handle);
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(handle.fileHandle, 0, handle.fileHandle.length);
        tw.writeBytes(this.createAttrs(attr));
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_FSETSTAT...");
            this.debug.flush();
        }
        this.sendMessage(10, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public void createSymlink(final String src, final String target) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(target, this.charsetName);
        tw.writeString(src, this.charsetName);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_SYMLINK...");
            this.debug.flush();
        }
        this.sendMessage(20, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public String canonicalPath(final String path) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(path, this.charsetName);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_REALPATH...");
            this.debug.flush();
        }
        this.sendMessage(16, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        if (this.debug != null) {
            this.debug.println("Got REPLY.");
            this.debug.flush();
        }
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 104) {
            final int count = tr.readUINT32();
            if (count != 1) {
                throw new IOException("The server sent an invalid SSH_FXP_NAME packet.");
            }
            return tr.readString(this.charsetName);
        }
        else {
            if (t != 101) {
                throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
            }
            final int errorCode = tr.readUINT32();
            throw new SFTPException(tr.readString(), errorCode);
        }
    }
    
    private final Vector scanDirectory(final byte[] handle) throws IOException {
        final Vector files = new Vector();
        while (true) {
            final int req_id = this.generateNextRequestID();
            final TypesWriter tw = new TypesWriter();
            tw.writeString(handle, 0, handle.length);
            if (this.debug != null) {
                this.debug.println("Sending SSH_FXP_READDIR...");
                this.debug.flush();
            }
            this.sendMessage(12, req_id, tw.getBytes());
            final byte[] resp = this.receiveMessage(34000);
            if (this.debug != null) {
                this.debug.println("Got REPLY.");
                this.debug.flush();
            }
            final TypesReader tr = new TypesReader(resp);
            final int t = tr.readByte();
            final int rep_id = tr.readUINT32();
            if (rep_id != req_id) {
                throw new IOException("The server sent an invalid id field.");
            }
            if (t == 104) {
                int count = tr.readUINT32();
                if (this.debug != null) {
                    this.debug.println("Parsing " + count + " name entries...");
                }
                while (count > 0) {
                    final SFTPv3DirectoryEntry dirEnt = new SFTPv3DirectoryEntry();
                    dirEnt.filename = tr.readString(this.charsetName);
                    dirEnt.longEntry = tr.readString(this.charsetName);
                    dirEnt.attributes = this.readAttrs(tr);
                    files.addElement(dirEnt);
                    if (this.debug != null) {
                        this.debug.println("File: '" + dirEnt.filename + "'");
                    }
                    --count;
                }
            }
            else {
                if (t != 101) {
                    throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
                }
                final int errorCode = tr.readUINT32();
                if (errorCode == 1) {
                    return files;
                }
                throw new SFTPException(tr.readString(), errorCode);
            }
        }
    }
    
    private final byte[] openDirectory(final String path) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(path, this.charsetName);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_OPENDIR...");
            this.debug.flush();
        }
        this.sendMessage(11, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 102) {
            if (this.debug != null) {
                this.debug.println("Got SSH_FXP_HANDLE.");
                this.debug.flush();
            }
            final byte[] handle = tr.readByteString();
            return handle;
        }
        if (t != 101) {
            throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
        }
        final int errorCode = tr.readUINT32();
        final String errorMessage = tr.readString();
        throw new SFTPException(errorMessage, errorCode);
    }
    
    private final String expandString(final byte[] b, final int off, final int len) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            final int c = b[off + i] & 0xFF;
            if (c >= 32 && c <= 126) {
                sb.append((char)c);
            }
            else {
                sb.append("{0x" + Integer.toHexString(c) + "}");
            }
        }
        return sb.toString();
    }
    
    private void init() throws IOException {
        final int client_version = 3;
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_INIT (3)...");
        }
        final TypesWriter tw = new TypesWriter();
        tw.writeUINT32(3);
        this.sendMessage(1, 0, tw.getBytes());
        if (this.debug != null) {
            this.debug.println("Waiting for SSH_FXP_VERSION...");
        }
        final TypesReader tr = new TypesReader(this.receiveMessage(34000));
        final int type = tr.readByte();
        if (type != 2) {
            throw new IOException("The server did not send a SSH_FXP_VERSION packet (got " + type + ")");
        }
        this.protocol_version = tr.readUINT32();
        if (this.debug != null) {
            this.debug.println("SSH_FXP_VERSION: protocol_version = " + this.protocol_version);
        }
        if (this.protocol_version != 3) {
            throw new IOException("Server version " + this.protocol_version + " is currently not supported");
        }
        while (tr.remain() != 0) {
            final String name = tr.readString();
            final byte[] value = tr.readByteString();
            this.server_extensions.put(name, value);
            if (this.debug != null) {
                this.debug.println("SSH_FXP_VERSION: extension: " + name + " = '" + this.expandString(value, 0, value.length) + "'");
            }
        }
    }
    
    public int getProtocolVersion() {
        return this.protocol_version;
    }
    
    public void close() {
        this.sess.close();
    }
    
    public Vector ls(final String dirName) throws IOException {
        final byte[] handle = this.openDirectory(dirName);
        final Vector result = this.scanDirectory(handle);
        this.closeHandle(handle);
        return result;
    }
    
    public void mkdir(final String dirName, final int posixPermissions) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(dirName, this.charsetName);
        tw.writeUINT32(4);
        tw.writeUINT32(posixPermissions);
        this.sendMessage(14, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public void rm(final String fileName) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(fileName, this.charsetName);
        this.sendMessage(13, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public void rmdir(final String dirName) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(dirName, this.charsetName);
        this.sendMessage(15, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public void mv(final String oldPath, final String newPath) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(oldPath, this.charsetName);
        tw.writeString(newPath, this.charsetName);
        this.sendMessage(18, req_id, tw.getBytes());
        this.expectStatusOKMessage(req_id);
    }
    
    public SFTPv3FileHandle openFileRO(final String fileName) throws IOException {
        return this.openFile(fileName, 1, null);
    }
    
    public SFTPv3FileHandle openFileRW(final String fileName) throws IOException {
        return this.openFile(fileName, 3, null);
    }
    
    public SFTPv3FileHandle createFile(final String fileName) throws IOException {
        return this.createFile(fileName, null);
    }
    
    public SFTPv3FileHandle createFile(final String fileName, final SFTPv3FileAttributes attr) throws IOException {
        return this.openFile(fileName, 11, attr);
    }
    
    public SFTPv3FileHandle createFileTruncate(final String fileName) throws IOException {
        return this.createFileTruncate(fileName, null);
    }
    
    public SFTPv3FileHandle createFileTruncate(final String fileName, final SFTPv3FileAttributes attr) throws IOException {
        return this.openFile(fileName, 27, attr);
    }
    
    private byte[] createAttrs(final SFTPv3FileAttributes attr) {
        final TypesWriter tw = new TypesWriter();
        int attrFlags = 0;
        if (attr == null) {
            tw.writeUINT32(0);
        }
        else {
            if (attr.size != null) {
                attrFlags |= 0x1;
            }
            if (attr.uid != null && attr.gid != null) {
                attrFlags |= 0x2;
            }
            if (attr.permissions != null) {
                attrFlags |= 0x4;
            }
            if (attr.atime != null && attr.mtime != null) {
                attrFlags |= 0x8;
            }
            tw.writeUINT32(attrFlags);
            if (attr.size != null) {
                tw.writeUINT64(attr.size);
            }
            if (attr.uid != null && attr.gid != null) {
                tw.writeUINT32(attr.uid);
                tw.writeUINT32(attr.gid);
            }
            if (attr.permissions != null) {
                tw.writeUINT32(attr.permissions);
            }
            if (attr.atime != null && attr.mtime != null) {
                tw.writeUINT32(attr.atime);
                tw.writeUINT32(attr.mtime);
            }
        }
        return tw.getBytes();
    }
    
    private SFTPv3FileHandle openFile(final String fileName, final int flags, final SFTPv3FileAttributes attr) throws IOException {
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(fileName, this.charsetName);
        tw.writeUINT32(flags);
        tw.writeBytes(this.createAttrs(attr));
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_OPEN...");
            this.debug.flush();
        }
        this.sendMessage(3, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 102) {
            if (this.debug != null) {
                this.debug.println("Got SSH_FXP_HANDLE.");
                this.debug.flush();
            }
            return new SFTPv3FileHandle(this, tr.readByteString());
        }
        if (t != 101) {
            throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
        }
        final int errorCode = tr.readUINT32();
        final String errorMessage = tr.readString();
        throw new SFTPException(errorMessage, errorCode);
    }
    
    public int read(final SFTPv3FileHandle handle, final long fileOffset, final byte[] dst, final int dstoff, final int len) throws IOException {
        this.checkHandleValidAndOpen(handle);
        if (len > 32768 || len <= 0) {
            throw new IllegalArgumentException("invalid len argument");
        }
        final int req_id = this.generateNextRequestID();
        final TypesWriter tw = new TypesWriter();
        tw.writeString(handle.fileHandle, 0, handle.fileHandle.length);
        tw.writeUINT64(fileOffset);
        tw.writeUINT32(len);
        if (this.debug != null) {
            this.debug.println("Sending SSH_FXP_READ...");
            this.debug.flush();
        }
        this.sendMessage(5, req_id, tw.getBytes());
        final byte[] resp = this.receiveMessage(34000);
        final TypesReader tr = new TypesReader(resp);
        final int t = tr.readByte();
        final int rep_id = tr.readUINT32();
        if (rep_id != req_id) {
            throw new IOException("The server sent an invalid id field.");
        }
        if (t == 103) {
            if (this.debug != null) {
                this.debug.println("Got SSH_FXP_DATA...");
                this.debug.flush();
            }
            final int readLen = tr.readUINT32();
            if (readLen < 0 || readLen > len) {
                throw new IOException("The server sent an invalid length field.");
            }
            tr.readBytes(dst, dstoff, readLen);
            return readLen;
        }
        else {
            if (t != 101) {
                throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
            }
            final int errorCode = tr.readUINT32();
            if (errorCode == 1) {
                if (this.debug != null) {
                    this.debug.println("Got SSH_FX_EOF.");
                    this.debug.flush();
                }
                return -1;
            }
            final String errorMessage = tr.readString();
            throw new SFTPException(errorMessage, errorCode);
        }
    }
    
    public void write(final SFTPv3FileHandle handle, long fileOffset, final byte[] src, int srcoff, int len) throws IOException {
        this.checkHandleValidAndOpen(handle);
        if (len < 0) {
            while (len > 0) {
                int writeRequestLen = len;
                if (writeRequestLen > 32768) {
                    writeRequestLen = 32768;
                }
                final int req_id = this.generateNextRequestID();
                final TypesWriter tw = new TypesWriter();
                tw.writeString(handle.fileHandle, 0, handle.fileHandle.length);
                tw.writeUINT64(fileOffset);
                tw.writeString(src, srcoff, writeRequestLen);
                if (this.debug != null) {
                    this.debug.println("Sending SSH_FXP_WRITE...");
                    this.debug.flush();
                }
                this.sendMessage(6, req_id, tw.getBytes());
                fileOffset += writeRequestLen;
                srcoff += writeRequestLen;
                len -= writeRequestLen;
                final byte[] resp = this.receiveMessage(34000);
                final TypesReader tr = new TypesReader(resp);
                final int t = tr.readByte();
                final int rep_id = tr.readUINT32();
                if (rep_id != req_id) {
                    throw new IOException("The server sent an invalid id field.");
                }
                if (t != 101) {
                    throw new IOException("The SFTP server sent an unexpected packet type (" + t + ")");
                }
                final int errorCode = tr.readUINT32();
                if (errorCode == 0) {
                    continue;
                }
                final String errorMessage = tr.readString();
                throw new SFTPException(errorMessage, errorCode);
            }
        }
    }
    
    public void closeFile(final SFTPv3FileHandle handle) throws IOException {
        if (handle == null) {
            throw new IllegalArgumentException("the handle argument may not be null");
        }
        try {
            if (!handle.isClosed) {
                this.closeHandle(handle.fileHandle);
            }
        }
        finally {
            handle.isClosed = true;
        }
        handle.isClosed = true;
    }
}
