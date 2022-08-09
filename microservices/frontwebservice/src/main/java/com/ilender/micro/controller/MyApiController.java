package com.ilender.micro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin
@Controller
@RequestMapping({"/api"})
public class MyApiController {

    int i=0;

    @GetMapping("/weather1")
    @ResponseBody
    private String getWeather() {
        i++;
        String result = "";
        if ( i==1 ) {
            result = "Cold ...";
        } else if (i == 2) {
            result = "Hot ...";
        } else if (i == 3) {
            result = "Rain ...";
        } else {
            result = "Cloud ...";
            i=1;
        }
        return result;
    }

    @GetMapping("/time1")
    @ResponseBody
    private String getTime() {
        String result = "Time is : " + new Date().toString();
        return result;
    }

    @RequestMapping("/weather")
    public String weatherUI(Model model) {
        model.addAttribute("value1", getWeather());
        return "weather-ui";
    }

    @RequestMapping("/time")
    public String timeUI(Model model) {
        model.addAttribute("value1", getTime());
        return "time-ui";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "home-ui";
    }

}
