package com.iarchitect.iot.ifttt.callbackservice.controller;


import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
//@EnableWebMvc
public class PingController extends BaseController {

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public Map<String, String> ping() {
        Map<String, String> pong = new HashMap<>();
        pong.put("pong", "Hello, World!");
        return pong;
    }

    @RequestMapping(path = "/system/time", method = RequestMethod.GET)
    public Map<String, DateTime> getSystemTime() {
        Map<String, DateTime> dateMap = new HashMap<>();
        dateMap.put("systemTime", new DateTime());
        return dateMap;
    }

    @RequestMapping(path = "/toronto/time", method = RequestMethod.GET)
    public Map<String, DateTime> getTorontoTime() {
        Map<String, DateTime> dateMap = new HashMap<>();
        dateMap.put("torontoTime", getTorontoDateTime());
        return dateMap;
    }

    @RequestMapping(path = "/isNightTime", method = RequestMethod.GET)
    public Map<String, Boolean> getIsNightTime() {
        Map<String, Boolean> dateMap = new HashMap<>();
        dateMap.put("nightTime", isNightTime());
        return dateMap;
    }
}
