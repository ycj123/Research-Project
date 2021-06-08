// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class Packets
{
    public static final int SSH_MSG_DISCONNECT = 1;
    public static final int SSH_MSG_IGNORE = 2;
    public static final int SSH_MSG_UNIMPLEMENTED = 3;
    public static final int SSH_MSG_DEBUG = 4;
    public static final int SSH_MSG_SERVICE_REQUEST = 5;
    public static final int SSH_MSG_SERVICE_ACCEPT = 6;
    public static final int SSH_MSG_KEXINIT = 20;
    public static final int SSH_MSG_NEWKEYS = 21;
    public static final int SSH_MSG_KEXDH_INIT = 30;
    public static final int SSH_MSG_KEXDH_REPLY = 31;
    public static final int SSH_MSG_KEX_DH_GEX_REQUEST_OLD = 30;
    public static final int SSH_MSG_KEX_DH_GEX_REQUEST = 34;
    public static final int SSH_MSG_KEX_DH_GEX_GROUP = 31;
    public static final int SSH_MSG_KEX_DH_GEX_INIT = 32;
    public static final int SSH_MSG_KEX_DH_GEX_REPLY = 33;
    public static final int SSH_MSG_USERAUTH_REQUEST = 50;
    public static final int SSH_MSG_USERAUTH_FAILURE = 51;
    public static final int SSH_MSG_USERAUTH_SUCCESS = 52;
    public static final int SSH_MSG_USERAUTH_BANNER = 53;
    public static final int SSH_MSG_USERAUTH_INFO_REQUEST = 60;
    public static final int SSH_MSG_USERAUTH_INFO_RESPONSE = 61;
    public static final int SSH_MSG_GLOBAL_REQUEST = 80;
    public static final int SSH_MSG_REQUEST_SUCCESS = 81;
    public static final int SSH_MSG_REQUEST_FAILURE = 82;
    public static final int SSH_MSG_CHANNEL_OPEN = 90;
    public static final int SSH_MSG_CHANNEL_OPEN_CONFIRMATION = 91;
    public static final int SSH_MSG_CHANNEL_OPEN_FAILURE = 92;
    public static final int SSH_MSG_CHANNEL_WINDOW_ADJUST = 93;
    public static final int SSH_MSG_CHANNEL_DATA = 94;
    public static final int SSH_MSG_CHANNEL_EXTENDED_DATA = 95;
    public static final int SSH_MSG_CHANNEL_EOF = 96;
    public static final int SSH_MSG_CHANNEL_CLOSE = 97;
    public static final int SSH_MSG_CHANNEL_REQUEST = 98;
    public static final int SSH_MSG_CHANNEL_SUCCESS = 99;
    public static final int SSH_MSG_CHANNEL_FAILURE = 100;
    public static final int SSH_EXTENDED_DATA_STDERR = 1;
    public static final int SSH_DISCONNECT_HOST_NOT_ALLOWED_TO_CONNECT = 1;
    public static final int SSH_DISCONNECT_PROTOCOL_ERROR = 2;
    public static final int SSH_DISCONNECT_KEY_EXCHANGE_FAILED = 3;
    public static final int SSH_DISCONNECT_RESERVED = 4;
    public static final int SSH_DISCONNECT_MAC_ERROR = 5;
    public static final int SSH_DISCONNECT_COMPRESSION_ERROR = 6;
    public static final int SSH_DISCONNECT_SERVICE_NOT_AVAILABLE = 7;
    public static final int SSH_DISCONNECT_PROTOCOL_VERSION_NOT_SUPPORTED = 8;
    public static final int SSH_DISCONNECT_HOST_KEY_NOT_VERIFIABLE = 9;
    public static final int SSH_DISCONNECT_CONNECTION_LOST = 10;
    public static final int SSH_DISCONNECT_BY_APPLICATION = 11;
    public static final int SSH_DISCONNECT_TOO_MANY_CONNECTIONS = 12;
    public static final int SSH_DISCONNECT_AUTH_CANCELLED_BY_USER = 13;
    public static final int SSH_DISCONNECT_NO_MORE_AUTH_METHODS_AVAILABLE = 14;
    public static final int SSH_DISCONNECT_ILLEGAL_USER_NAME = 15;
    public static final int SSH_OPEN_ADMINISTRATIVELY_PROHIBITED = 1;
    public static final int SSH_OPEN_CONNECT_FAILED = 2;
    public static final int SSH_OPEN_UNKNOWN_CHANNEL_TYPE = 3;
    public static final int SSH_OPEN_RESOURCE_SHORTAGE = 4;
    private static final String[] reverseNames;
    
    static {
        (reverseNames = new String[101])[1] = "SSH_MSG_DISCONNECT";
        Packets.reverseNames[2] = "SSH_MSG_IGNORE";
        Packets.reverseNames[3] = "SSH_MSG_UNIMPLEMENTED";
        Packets.reverseNames[4] = "SSH_MSG_DEBUG";
        Packets.reverseNames[5] = "SSH_MSG_SERVICE_REQUEST";
        Packets.reverseNames[6] = "SSH_MSG_SERVICE_ACCEPT";
        Packets.reverseNames[20] = "SSH_MSG_KEXINIT";
        Packets.reverseNames[21] = "SSH_MSG_NEWKEYS";
        Packets.reverseNames[30] = "SSH_MSG_KEXDH_INIT";
        Packets.reverseNames[31] = "SSH_MSG_KEXDH_REPLY/SSH_MSG_KEX_DH_GEX_GROUP";
        Packets.reverseNames[32] = "SSH_MSG_KEX_DH_GEX_INIT";
        Packets.reverseNames[33] = "SSH_MSG_KEX_DH_GEX_REPLY";
        Packets.reverseNames[34] = "SSH_MSG_KEX_DH_GEX_REQUEST";
        Packets.reverseNames[50] = "SSH_MSG_USERAUTH_REQUEST";
        Packets.reverseNames[51] = "SSH_MSG_USERAUTH_FAILURE";
        Packets.reverseNames[52] = "SSH_MSG_USERAUTH_SUCCESS";
        Packets.reverseNames[53] = "SSH_MSG_USERAUTH_BANNER";
        Packets.reverseNames[60] = "SSH_MSG_USERAUTH_INFO_REQUEST";
        Packets.reverseNames[61] = "SSH_MSG_USERAUTH_INFO_RESPONSE";
        Packets.reverseNames[80] = "SSH_MSG_GLOBAL_REQUEST";
        Packets.reverseNames[81] = "SSH_MSG_REQUEST_SUCCESS";
        Packets.reverseNames[82] = "SSH_MSG_REQUEST_FAILURE";
        Packets.reverseNames[90] = "SSH_MSG_CHANNEL_OPEN";
        Packets.reverseNames[91] = "SSH_MSG_CHANNEL_OPEN_CONFIRMATION";
        Packets.reverseNames[92] = "SSH_MSG_CHANNEL_OPEN_FAILURE";
        Packets.reverseNames[93] = "SSH_MSG_CHANNEL_WINDOW_ADJUST";
        Packets.reverseNames[94] = "SSH_MSG_CHANNEL_DATA";
        Packets.reverseNames[95] = "SSH_MSG_CHANNEL_EXTENDED_DATA";
        Packets.reverseNames[96] = "SSH_MSG_CHANNEL_EOF";
        Packets.reverseNames[97] = "SSH_MSG_CHANNEL_CLOSE";
        Packets.reverseNames[98] = "SSH_MSG_CHANNEL_REQUEST";
        Packets.reverseNames[99] = "SSH_MSG_CHANNEL_SUCCESS";
        Packets.reverseNames[100] = "SSH_MSG_CHANNEL_FAILURE";
    }
    
    public static final String getMessageName(final int type) {
        String res = null;
        if (type >= 0 && type < Packets.reverseNames.length) {
            res = Packets.reverseNames[type];
        }
        return (res == null) ? ("UNKNOWN MSG " + type) : res;
    }
}
