package com.ilender.micro.service;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.HttpClientUtil;
import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;

@Configuration
@Service
public class RestUtilService {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> callExchange (String url, HttpMethod httpmethod, HttpEntity requestInfo) {
        ResponseEntity<String> response = restTemplate.exchange(url, httpmethod,  requestInfo, String.class);
        return response;
    }


    public Object callGet(String url, Class outputInfoClass) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");
        Object outputInfo =callHttpClient(url);
        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);
        return outputInfo;
    }

    public Object callGetList(String url, Class outputInfoClass) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");
        Object outputInfo =callHttpClient(url);
        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);
        return outputInfo;
    }

    public Object callGet(String url) {
        LogUtil.logDebug("RestUtilService : callGet started");
        Object outputInfo =callHttpClient(url);
        LogUtil.logDebug("RestUtilService : callGet completed outputInfo : " + outputInfo);
        return outputInfo;
    }

    public Object callGetWithId(String url, Object inputInfo) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);

        Object outputInfo = "";
        try {
            url = url + "/" + URLEncoder.encode(jsonString, "UTF-8");
            LogUtil.logDebug("RestUtilService : callGetForObject url  1: " + url);

            URI uri = new URI(url);

            outputInfo =callHttpClient(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callGetForObject input jsonString  : " + jsonString);
        LogUtil.logDebug("RestUtilService : callGetForObject url  : " + url);
        LogUtil.logDebug("RestUtilService : callGetWithId completed outputInfo : " + outputInfo);

        return outputInfo;
    }


    public Object callGetList(String url, Object inputInfo, Class outputInfoClass) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);

        Object outputInfo = "";
        try {
            url = url + "/" + URLEncoder.encode(jsonString, "UTF-8");
            LogUtil.logDebug("RestUtilService : callGetForObject url  1: " + url);

            URI uri = new URI(url);

            outputInfo =callHttpClient(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callGetForObject input jsonString  : " + jsonString);
        LogUtil.logDebug("RestUtilService : callGetForObject url  : " + url);
        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);

        return outputInfo;
    }

    public Object callGetList111(String url, Object inputInfo, Class outputInfoClass) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);

        Object outputInfo = "";
        try {
            url = url + "/" + URLEncoder.encode(jsonString, "UTF-8");
            LogUtil.logDebug("RestUtilService : callGetForObject url  1: " + url);

            URI uri = new URI(url);

            outputInfo =callHttpClient(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callGetForObject input jsonString  : " + jsonString);
        LogUtil.logDebug("RestUtilService : callGetForObject url  : " + url);

        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);

        return outputInfo;
    }

    public Object callGet(String url, Object inputInfo, Class outputInfoClass) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);

        Object outputInfo = "";
        try {
            url = url + "/" + URLEncoder.encode(jsonString, "UTF-8");
            LogUtil.logDebug("RestUtilService : callGetForObject url  1: " + url);

            URI uri = new URI(url);

            outputInfo =callHttpClient(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callGetForObject input jsonString  : " + jsonString);
        LogUtil.logDebug("RestUtilService : callGetForObject url  : " + url);
        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);

        return outputInfo;
    }

    public Object callPost (String url, Object requestInfo, Class responseClass) {

        LogUtil.logDebug("RestUtilService : callPost started");

        LogUtil.logDebug("RestUtilService : callPost url :" + url);
        LogUtil.logDebug("RestUtilService : callPost request :" + requestInfo);

        String jsonString = ConversionUtil.objectToJsonString(requestInfo);
        LogUtil.logDebug("RestUtilService : callPost jsonString  : " + jsonString);

        HttpEntity<Object> entity = createHttpEntity(requestInfo);

        String responseString = null;
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            responseString = response.getBody();

            LogUtil.logDebug("RestUtilService : callPost responseString 1: " + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object responseObject =  ConversionUtil.jsonToObject(responseString, responseClass);
        LogUtil.logDebug("RestUtilService : callPost responseObject : " + responseObject);

        return responseObject;
    }
    public Object callPost (String url, Object requestInfo) {

        LogUtil.logDebug("RestUtilService : callPost started");

        LogUtil.logDebug("RestUtilService : callPost url :" + url);
        LogUtil.logDebug("RestUtilService : callPost request :" + requestInfo);

        String jsonString = ConversionUtil.objectToJsonString(requestInfo);
        LogUtil.logDebug("RestUtilService : callPost jsonString  : " + jsonString);

        HttpEntity<Object> entity = createHttpEntity(requestInfo);

        String responseString = null;
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            responseString = response.getBody();

            LogUtil.logDebug("RestUtilService : callPost responseString 1: " + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }

    public Object callDelete(String url, Object inputInfo) {
        LogUtil.logDebug("RestUtilService : callGetForObject started");

        String jsonString = ConversionUtil.objectToJsonString(inputInfo);

        String jsonResonse = "";
        try {
            url = url + "/" + URLEncoder.encode(jsonString, "UTF-8");
            LogUtil.logDebug("RestUtilService : callGetForObject url  1: " + url);

            URI uri = new URI(url);

            restTemplate.delete(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callGetForObject input jsonString  : " + jsonString);
        LogUtil.logDebug("RestUtilService : callGetForObject url  : " + url);
        LogUtil.logDebug("RestUtilService : callGetForObject completed jsonResonse : " + jsonResonse);

        Object outputInfo = "success";
        LogUtil.logDebug("RestUtilService : callGetForObject completed outputInfo : " + outputInfo);

        return outputInfo;
    }

    public Object callHttpClient(String url) {
        LogUtil.logDebug("RestUtilService : callHttpClient started");

        Object result = null;
        try {
            LogUtil.logDebug("RestUtilService : callHttpClient url  1: " + url);

            result = HttpClientUtil.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.logDebug("RestUtilService : callHttpClient completed result : " + result);

        return result;
    }


    private static HttpEntity createHttpEntity(Object inputInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(inputInfo, headers);
    }
}
