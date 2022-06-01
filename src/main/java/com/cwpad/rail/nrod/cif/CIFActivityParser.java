package com.cwpad.rail.nrod.cif;


import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

class CIFActivityParser {
    Set<Activity> parse(String activities) {
        Set<Activity> a = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            int beginIndex = i * 2;
            String activity = activities.substring(beginIndex, beginIndex + 2).trim();
            if (activity.length() > 0) {
                a.add(Activity.parse(activity));
            }
        }
        if (a.size() > 0) {
            return EnumSet.copyOf(a);
        } else {
            return EnumSet.noneOf(Activity.class);
        }
    }
}
