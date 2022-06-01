package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.RailNetworkLocation;
import com.cwpad.rail.services.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CIFTiplocRepositoryIT {
    private static CIFTiplocRepository repository;
    private static String location;
    private final Logger logger = Logger.getInstance(getClass());

    @BeforeClass
    public static void setUp() throws IOException {
        repository = new CIFTiplocRepository();

        location = System.getProperty("cifFile");
        if (location != null && location.trim().length() > 0) {
            repository.bootstrap(new InputStreamReader(new GZIPInputStream(new FileInputStream(new File(location)))));
        }
    }

    @Test
    public void newcastleStanox() {
        if (location == null || location.trim().length() == 0) {
            logger.warn("Not running integration test as no input file specified.");
            return;
        }

        RailNetworkLocation railNetworkLocation = repository.lookupByStanox(12931).get();
        assertEquals("NCL", railNetworkLocation.getShortCode());
    }

    @Test
    public void kingsCrossSearch() {
        if (location == null || location.trim().length() == 0) {
            logger.warn("Not running integration test as no input file specified.");
            return;
        }

        List<RailNetworkLocation> locations = repository.search("Kings Cross");
        assertTrue(locations.size() > 0);
    }
}
