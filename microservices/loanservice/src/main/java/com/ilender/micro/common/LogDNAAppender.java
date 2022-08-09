package com.ilender.micro.common;
import ch.qos.logback.classic.spi.ILoggingEvent;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.goebl.david.Response;
import com.goebl.david.Webb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map.Entry;

@Component
public class LogDNAAppender extends UnsynchronizedAppenderBase<ILoggingEvent>
{
    /** relying on the guard in the parent implementation to prevent recursive calls, hoping there is another ERROR destination */
    Logger emergencyLog = LoggerFactory.getLogger(LogDNAAppender.class);

    /*
     * configurables ( plus ingestKey )
     */
    private String appName;
    private boolean includeStacktrace = true;
    private boolean sendMDC = true;
    private String logDnaUrl;
    private String logDnaKey;

    private String hostname;
    private Webb http = null;

    private String getLogDNAURL() {
//        return System.getenv("logdnaUrl");
        return "https://aaaa.com/logs/ingest";
    }

    private String getLogDNAToken() {
        return System.getenv("logdnaToken");
    }

    public LogDNAAppender() {
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            this.hostname = "localhost";
            e.printStackTrace();
        }

    }
    private Webb getHttp() {
        if (this.http == null) {
            Webb webb = Webb.create();
//            webb.setBaseUri(getLogDNAURL());
            webb.setBaseUri("https://aaaa.com/logs/ingest");

            webb.setDefaultHeader(Webb.HDR_USER_AGENT, "LogDNA Appender (95point2)");
            webb.setDefaultHeader(Webb.HDR_AUTHORIZATION, "Basic " + "bbbb");
            this.http = webb;
        }
        return this.http;
    }
    private Webb getHttp111() {
        if (this.http == null) {
            Webb webb = Webb.create();
            webb.setBaseUri("https://aaaa.com/logs/ingest");
            webb.setDefaultHeader(Webb.HDR_USER_AGENT, "LogDNA Appender (95point2)");
            webb.setDefaultHeader(Webb.HDR_AUTHORIZATION, "Basic " + "ccc");
            this.http = webb;
        }
        return this.http;
    }
    @Override
    protected void append(ILoggingEvent ev)
    {
        /* not interested in consuming our own filth */
        if(ev.getLoggerName().equals(LogDNAAppender.class.getName())){
            return;
        }

        StringBuilder sb = new StringBuilder()
                .append("[").append(ev.getThreadName()).append("] ")
                .append(ev.getLoggerName())
                .append(" -- ")
                .append(ev.getFormattedMessage());

        if(ev.getThrowableProxy() != null && this.includeStacktrace){
            IThrowableProxy tp = ev.getThrowableProxy();
            sb.append("\n\n").append(tp.getClassName()).append(": ").append(tp.getMessage());
            for(StackTraceElementProxy ste : tp.getStackTraceElementProxyArray()){
                sb.append("\n\t").append(ste.getSTEAsString());
            }
        }

        try
        {
            JSONObject payload = new JSONObject();
            JSONArray lines = new JSONArray();
            payload.put("lines", lines );

            JSONObject line = new JSONObject();
            line.put("timestamp", ev.getTimeStamp());
            line.put("level", ev.getLevel().toString());
            line.put("app", this.appName);
            line.put("line", sb.toString());

            JSONObject meta = new JSONObject();
            meta.put("logger", ev.getLoggerName());
            line.put("meta", meta);

            if(this.sendMDC && !ev.getMDCPropertyMap().isEmpty()){
                for(Entry<String,String> entry : ev.getMDCPropertyMap().entrySet()){
                    meta.put(entry.getKey(), entry.getValue());
                }
            }


            lines.put(line);

            HashMap<String,Object> params = new HashMap<>();
            params.put("hostname", this.hostname);
            params.put("now", System.currentTimeMillis());

            Response<JSONObject> response = getHttp().post("?hostname=" + encode(this.hostname) + "&now=" + encode(String.valueOf(System.currentTimeMillis())) )
                    .body(payload)
                    .retry(3, true)
                    .asJsonObject();

            if(!response.isSuccess()){
                String msg = "Error posting to LogDNA ";
                msg += response.getStatusCode() + " ";
                try{ msg += response.getStatusLine(); }
                catch(Exception e){
                    e.printStackTrace();
                }
                emergencyLog.error(msg);
            }
        }
        catch (JSONException e) {
            emergencyLog.error(e.getMessage(), e);
        }
    }




    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setSendMDC(boolean sendMDC) {
        this.sendMDC = sendMDC;
    }

    public void setLogDnaUrl(String logDnaUrl) {
        this.logDnaUrl = logDnaUrl;
    }

    public void setLogDnaKey(String logDnaKey) {
        this.logDnaKey = logDnaKey;
    }

    public void setIncludeStacktrace(boolean includeStacktrace) {
        this.includeStacktrace = includeStacktrace;
    }

    private static String encode(String str) {
        try
        {
            return URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return str;
        }
    }
}
