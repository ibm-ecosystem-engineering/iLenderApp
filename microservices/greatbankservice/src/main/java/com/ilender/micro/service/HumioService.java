package com.ilender.micro.service;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.HumioInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ilender.micro.common.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class HumioService {

    public static String SERVICE_NAME_FOR_LOG = "greatbankservice";

    @Value("${prop.humio.url}")
    private String humioUrl;

    @Value("${prop.humio.token}")
    private String humioToken;

    @Value("${prop.log.host}")
    private String logHost;

    @Autowired
    RestUtilService restUtilService;

    @Autowired
    RestTemplate restTemplate;

    private Map fields = null;

    public void logError(String msg) {
        HumioInfo info =getError(msg);
        callPost(humioUrl, humioToken, info);
    }

    public void logInfo(String msg) {
        HumioInfo info = getInfo(msg);
        callPost(humioUrl, humioToken, info);
    }

    private void callPost(String apiUrl, String accessToken, Object inputInfo) {
        // LogUtil.logDebug("HumioService: callPost: apiUrl : " + apiUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add ("Authorization", "Bearer " + accessToken);
        headers.add ("Host", logHost);

        // LogUtil.logDebug(" Json to Humio 1:");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);
        jsonString = "[" + jsonString + "]";

        // LogUtil.logDebug(" Json to Humio 2:" + jsonString);

        HttpEntity<String> entity =  new HttpEntity(jsonString, headers);
        try {
            ResponseEntity<String> response = restUtilService.callExchange(apiUrl, HttpMethod.POST, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HumioInfo getError(String msg) {
        // msg = DateUtil.currenTimeForLog() + " " +  SERVICE_NAME_FOR_LOG + " error : " + msg;
        msg = "Error : " + msg;

        HumioInfo info = new HumioInfo();
        info.getMessages().add(msg);
        info.setFields(getFields());
        return info;
    }

    private HumioInfo getInfo(String msg) {
        // msg = DateUtil.currenTimeForLog() + " " +  SERVICE_NAME_FOR_LOG + " info : " + msg;
        msg = "Info : " + msg;

        HumioInfo info = new HumioInfo();
        info.getMessages().add(msg);
        info.setFields(getFields());
        return info;
    }

    public String getEnv(String key) {
        String result = System.getenv(key);
        if (StringUtil.isNullOrEmpty(result)) {
            result = SERVICE_NAME_FOR_LOG;
        }
        return result;
    }

    private Map getFields() {
        if (fields == null) {
            Map tempFields = new HashMap<String, String>();
            tempFields.put ("kubernetes.namespace_name", getEnv("MY_NAMESPACE_NAME"));
            tempFields.put ("kubernetes.container_name", getEnv("MY_CONTAINER_NAME"));
            tempFields.put ("kubernetes.container_image",getEnv("MY_CONTAINER_IMAGE"));
            tempFields.put ("kubernetes.host", getEnv("MY_HOST"));
            tempFields.put ("kubernetes.labels.app", getEnv("MY_LABELS_APP"));
            tempFields.put ("kubernetes.pod_name", getEnv("MY_POD_NAME"));
            tempFields.put ("kubernetes.pod_id", getEnv("MY_POD_ID"));
            tempFields.put ("Host", logHost);
            fields = tempFields;
        }
        return fields;
    }

}
