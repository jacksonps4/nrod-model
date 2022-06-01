package com.cwpad.rail.services;

import com.cwpad.rail.model.LocationIdentityType;

import java.util.regex.Pattern;

public class LocationIdentityTypeParser {
    private final Pattern crsCode = Pattern.compile("\\w{3}");
    private final Pattern tiploc = Pattern.compile("\\w{4,7}");
    private final Pattern stanox = Pattern.compile("\\d{5}");

    public LocationIdentityType parse(String locationIdentityType) {
        if (stanox.matcher(locationIdentityType).matches()) {
            return LocationIdentityType.STANOX;
        } else if (crsCode.matcher(locationIdentityType).matches()) {
            return LocationIdentityType.CRS_CODE;
        } else if (tiploc.matcher(locationIdentityType).matches()) {
            return LocationIdentityType.TIPLOC;
        } else {
            return null;
        }
    }
}
