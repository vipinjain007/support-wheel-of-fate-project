package com.swof.constants;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    
    public static final Map<String,DayOfWeek> DAY_OF_WEEK = new HashMap<String, DayOfWeek>() {{
        put("1",DayOfWeek.MONDAY );
        put("2",DayOfWeek.TUESDAY );
        put("3",DayOfWeek.WEDNESDAY );
        put("4",DayOfWeek.THURSDAY );
        put("5",DayOfWeek.FRIDAY );
        put("6",DayOfWeek.SATURDAY );
        put("7",DayOfWeek.SUNDAY );
        
    }};
}