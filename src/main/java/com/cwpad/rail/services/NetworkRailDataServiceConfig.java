package com.cwpad.rail.services;

public class NetworkRailDataServiceConfig {
    public String getCifUrl() {
        String cifUrl = System.getProperty("rail.refdata.cif.url");
        return (cifUrl != null && cifUrl.trim().length() > 0) ? cifUrl :
                "https://datafeeds.networkrail.co.uk/ntrod/CifFileAuthenticate?type=CIF_ALL_FULL_DAILY&day=toc-full.CIF.gz";
    }

    public String getCorpusUrl() {
        String corpusUrl = System.getProperty("rail.refdata.corpus.url");
        return (corpusUrl != null && corpusUrl.trim().length() > 0) ? corpusUrl :
                "https://datafeeds.networkrail.co.uk/ntrod/SupportingFileAuthenticate?type=CORPUS";
    }

    public String getSmartUrl() {
        String smartUrl = System.getProperty("rail.refdata.smart.url");
        return (smartUrl != null && smartUrl.trim().length() > 0) ? smartUrl :
            "https://datafeeds.networkrail.co.uk/ntrod/SupportingFileAuthenticate?type=SMART";
    }

    public String getTpUrl() {
        String tpUrl = System.getProperty("rail.refdata.tp.url");
        return (tpUrl != null && tpUrl.trim().length() > 0) ? tpUrl :
            "https://wiki.openraildata.com/images/6/67/20170830_ReferenceData.gz";
    }

    public String getStaticUsername() {
        return System.getProperty("rail.refdata.username");
    }

    public String getStaticPassword() {
        return System.getProperty("rail.refdata.password");
    }
}
