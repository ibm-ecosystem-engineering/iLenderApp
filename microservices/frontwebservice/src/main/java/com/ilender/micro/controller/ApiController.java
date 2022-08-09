package com.ilender.micro.controller;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.ApiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import java.net.InetAddress;

@Configuration
@CrossOrigin
@RestController
@RequestMapping({"/api"})

public class ApiController {

    @GetMapping("/apiServiceURL")
    @ResponseBody
    private ApiResponse getApiServiceURL() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setUrl1("test");
        apiResponse.setUrl2("test");
        apiResponse.setValue1(getHostName());

        LogUtil.logDebug("ApiController apiServiceURL user ---> " + apiResponse.getUrl1());
        LogUtil.logDebug("ApiController apiServiceURL user ---> " + apiResponse.getUrl1());
        LogUtil.logDebug("ApiController apiServiceURL hostpath ---> " + apiResponse.getValue1());
        LogUtil.logDebug("ApiController apiServiceURL hostpathDetails ---> " + apiResponse.getValue2());

        return apiResponse;
    }

    public static String getHostName() {
        InetAddress ip;
        String hostname = "";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostname;
    }

}
