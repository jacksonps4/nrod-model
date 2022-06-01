package com.cwpad.rail.nrod.cif;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class CIFTiplocRepositoryMemoryUsageIT {
    private static CIFTiplocRepository repository;

    public static void main(String[] args) throws IOException {
        repository = new CIFTiplocRepository();
        repository.bootstrap(new InputStreamReader(new GZIPInputStream(new FileInputStream(new File("/Users/chrisw/Development/Resources/NetworkRail/CIF_ALL_FULL_DAILY-toc-full-20170102.CIF.gz")))));

        System.gc();
        System.out.println("Press <ENTER> to QUIT");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
