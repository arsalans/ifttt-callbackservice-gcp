package com.iarchitect.iot.ifttt.callbackservice.controller;

import org.joda.time.DateTime;

public class BaseController {

    public boolean isNightTime() {
        boolean isNightTime = false;
        int hourOfDay = new DateTime().getHourOfDay();
        if ((hourOfDay >= 0 && hourOfDay <= 8) || (hourOfDay >= 20)) {
            isNightTime = true;
        }
        return isNightTime;
    }


}
