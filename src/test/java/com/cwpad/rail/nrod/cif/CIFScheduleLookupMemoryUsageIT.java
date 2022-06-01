package com.cwpad.rail.nrod.cif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CIFScheduleLookupMemoryUsageIT extends AbstractCIFScheduleLookupServiceIT {
    public static void main(String[] args) throws IOException {
        System.setProperty("cifFile", "/Users/chrisw/Development/Resources/NetworkRail/CIF_ALL_FULL_DAILY-toc-full-20170102.CIF.gz");
        CIFScheduleLookupMemoryUsageIT.setUp();

        System.gc();
        System.out.println("Press <ENTER> to QUIT");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
