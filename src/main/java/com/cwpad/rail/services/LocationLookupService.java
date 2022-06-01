package com.cwpad.rail.services;

import com.cwpad.rail.model.LocationLookupRequest;
import com.cwpad.rail.model.RailNetworkLocation;

import java.util.Optional;

public interface LocationLookupService extends Bootstrappable {
    default Optional<RailNetworkLocation> lookupByShortCode(String stationShortCode) {
        LocationLookupRequest req = new LocationLookupRequest();
        req.setCrsCode(stationShortCode);
        return lookup(req);
    }

    default Optional<RailNetworkLocation> lookupByTiploc(String tiploc) {
        LocationLookupRequest req = new LocationLookupRequest();
        req.setTiploc(tiploc);
        return lookup(req);
    }

    default Optional<RailNetworkLocation> lookupByStanox(int stanox) {
        LocationLookupRequest req = new LocationLookupRequest();
        req.setStanox(stanox);
        return lookup(req);
    }

    default Optional<RailNetworkLocation> lookup(String identifer) {
        LocationLookupRequest req = new LocationLookupRequest();
        req.setIdentifier(identifer);
        return lookup(req);
    }

    Optional<RailNetworkLocation> lookup(LocationLookupRequest request);
}
