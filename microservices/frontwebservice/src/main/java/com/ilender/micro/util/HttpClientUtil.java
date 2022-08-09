package com.ilender.micro.util;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import com.ilender.micro.common.LogUtil;


public class HttpClientUtil {

    public static String doGet(String url) throws Exception {

        LogUtil.logDebug("HttpClientUtil : doGet started : " + url);


        if ( url !=null && ! url.isEmpty()  ) {
            if ( url.startsWith("http://") || url.startsWith("https://")  ) {
                url = url;
            }
        }

        String ip = InetAddress.getLocalHost().getHostAddress();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(60000); //set timeout to 60 seconds
        con.setReadTimeout(60000);

        populateForHttps(con);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-length", "0");
        con.setUseCaches(false);
        con.setAllowUserInteraction(false);
        con.connect();

        String responseBody = readContent(con);

        con.disconnect();

        LogUtil.logDebug("HttpClientUtil : doGet end : " + responseBody);

        return responseBody;
    }

    private static void populateForHttps(HttpURLConnection con) {
        try {
            if (con instanceof HttpsURLConnection) {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllManager(), new java.security.SecureRandom());
                HttpsURLConnection secureConn=(HttpsURLConnection)con;
                secureConn.setSSLSocketFactory(sslContext.getSocketFactory());
                secureConn.setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static String readContent(HttpURLConnection connection) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
//                sb.append("ln");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = sb.toString();
        return result;
    }

        public static String downloadResponse111(InputStream stream) throws IOException {
        String charset = null;

        if (stream == null)
            return null;
        BufferedInputStream bis = new BufferedInputStream(stream);

        byte[] b = new byte[1024];
        String content = null;
        int size = 0;
        StringBuffer sb = new StringBuffer();
        while ((size = bis.read(b)) != -1) {
            if (charset != null)
                sb.append(new String(b, 0, size, charset));
            else
                sb.append(new String(b, 0, size));
        }
        bis.close();
        content = sb.toString();

        return content;
    }

    private static TrustManager[] trustAllManager() {

        TrustManager[] certMgr = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[] {};
                    }
                } };


        return certMgr;
    }

}
