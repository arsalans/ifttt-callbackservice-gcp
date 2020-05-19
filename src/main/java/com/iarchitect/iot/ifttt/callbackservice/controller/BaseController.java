package com.iarchitect.iot.ifttt.callbackservice.controller;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class BaseController {

    public boolean isNightTime() {
        boolean isNightTime = false;
        DateTime dtToronto = getTorontoDateTime();
        int hourOfDay = dtToronto.getHourOfDay();
        if ((hourOfDay >= 0 && hourOfDay <= 8) || (hourOfDay >= 20)) {
            isNightTime = true;
        }
        return isNightTime;
    }

    public DateTime getTorontoDateTime() {
        DateTime dateTime = new DateTime();
        return dateTime.withZone(DateTimeZone.forID("America/Toronto"));
    }


}
