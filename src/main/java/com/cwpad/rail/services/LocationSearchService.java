package com.cwpad.rail.services;

import com.cwpad.rail.model.LocationSearchRequest;
import com.cwpad.rail.model.RailNetworkLocation;

import java.util.List;

public interface LocationSearchService extends Bootstrappable {
    List<RailNetworkLocation> search(String term);
    List<RailNetworkLocation> search(LocationSearchRequest request);
}
