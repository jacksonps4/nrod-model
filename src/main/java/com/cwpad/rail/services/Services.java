package com.cwpad.rail.services;

import java.io.IOException;

public enum Services {
    INSTANCE;

    public TrainOperatingCompanyRepository newTrainOperatingCompanyRepository() {
        try {
            return new TrainOperatingCompanyRepositoryImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
