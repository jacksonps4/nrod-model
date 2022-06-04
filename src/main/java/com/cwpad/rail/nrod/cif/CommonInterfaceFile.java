package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.services.LocationLookupService;
import com.cwpad.rail.services.LocationSearchService;
import com.cwpad.rail.services.ScheduleLookupService;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;

public enum CommonInterfaceFile {
    INSTANCE;

    public ScheduleLookupService newScheduleLookupService(TrainOperatingCompanyRepository trainOperatingCompanyRepository) {
        return new CIFScheduleLookupService(trainOperatingCompanyRepository);
    }

    public LocationLookupService newLocationLookupService() {
        return new CIFTiplocRepository();
    }

    public LocationSearchService newLocationSearchService() {
        return new CIFTiplocRepository();
    }
}
