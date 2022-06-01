package com.cwpad.rail.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class NetworkRailStaticDataService {
    private byte[] corpusData;
    private byte[] tpData;

    NetworkRailDataServiceConfig config;

    public void init() {
        loadCorpusData();
        loadTrainPlanningData();
    }

    public void updateCorpusData() {
        loadCorpusData();
    }

    void loadCorpusData() {
        String corpusUrl = config.getCorpusUrl();
        NetworkRailStaticReader reader = new NetworkRailStaticReader();
        corpusData = reader.readUrlData(config.getStaticUsername(), config.getStaticPassword(), corpusUrl);
    }

    public void updateTrainPlanningData() {
        loadTrainPlanningData();
    }

    void loadTrainPlanningData() {
        NetworkRailStaticReader reader = new NetworkRailStaticReader();
        tpData = reader.readUrlData(config.getStaticUsername(), config.getStaticPassword(), config.getTpUrl());
    }

    public InputStream getCorpusData() {
        return new ByteArrayInputStream(corpusData);
    }

    public InputStream getTpData() {
        return new ByteArrayInputStream(tpData);
    }
}
