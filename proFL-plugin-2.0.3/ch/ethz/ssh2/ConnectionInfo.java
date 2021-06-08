// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public class ConnectionInfo
{
    public String keyExchangeAlgorithm;
    public String clientToServerCryptoAlgorithm;
    public String serverToClientCryptoAlgorithm;
    public String clientToServerMACAlgorithm;
    public String serverToClientMACAlgorithm;
    public String serverHostKeyAlgorithm;
    public byte[] serverHostKey;
    public int keyExchangeCounter;
    
    public ConnectionInfo() {
        this.keyExchangeCounter = 0;
    }
}
