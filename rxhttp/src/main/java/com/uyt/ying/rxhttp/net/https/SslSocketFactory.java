package com.uyt.ying.rxhttp.net.https;




import com.uyt.ying.rxhttp.net.common.ApiConfig;
import com.uyt.ying.rxhttp.net.utils.AppContextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * created  by xxxx on 2019/9/11.
 */
public class SslSocketFactory {

    /**
     * HTTPS单向认证
     *
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(ApiConfig.getSslSocketConfigure().getCertificateType());
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream input : certificates) {
                String iAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(iAlias, certificateFactory.generateCertificate(input));
                try {
                    if (null != input) {
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SSLContext sslContext = SSLContext.getInstance(ApiConfig.getSslSocketConfigure().getProtocolType());
            TrustManagerFactory managerF = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            managerF.init(keyStore);
            sslContext.init(null, managerF.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTPS双向认证
     *
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory() {

        try {
            KeyStore keyStore = KeyStore.getInstance(ApiConfig.getSslSocketConfigure().getKeystoreType());
            KeyStore trustStore = KeyStore.getInstance(ApiConfig.getSslSocketConfigure().getKeystoreType());
            InputStream keyInput = AppContextUtils.getContext().getAssets().open(ApiConfig.getSslSocketConfigure().getClientPriKey());
            InputStream trustInput = AppContextUtils.getContext().getAssets().open(ApiConfig.getSslSocketConfigure().getTrustPubKey());
            keyStore.load(keyInput, ApiConfig.getSslSocketConfigure().getClientBKSPassword().toCharArray());
            trustStore.load(trustInput, ApiConfig.getSslSocketConfigure().getTruststoreBKSPassword().toCharArray());

            try {
                if (null != keyInput) {
                    keyInput.close();
                }
                if (null != keyInput) {
                    trustInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            SSLContext sslContext = SSLContext.getInstance(ApiConfig.getSslSocketConfigure().getProtocolType());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(ApiConfig.getSslSocketConfigure().getCertificateType());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(ApiConfig.getSslSocketConfigure().getCertificateType());
            trustManagerFactory.init(trustStore);
            keyManagerFactory.init(keyStore, ApiConfig.getSslSocketConfigure().getClientBKSPassword().toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
