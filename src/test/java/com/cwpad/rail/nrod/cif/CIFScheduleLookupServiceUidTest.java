package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CIFScheduleLookupServiceUidTest {
    private CIFScheduleLookupService scheduleLookupService;
    private List<TrainSchedule> schedule;

    @Before
    public void setUp() throws IOException {
        scheduleLookupService = new CIFScheduleLookupService(TrainOperatingCompanyRepository.instance());

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (Reader r = new InputStreamReader(cl.getResourceAsStream("com/cwpad/rail/nrod/cif/Y58497"))) {
           scheduleLookupService.bootstrap(r);
        }

        schedule = scheduleLookupService.findByUid("Y58497", LocalDate.of(2015, 10, 14));
    }

    @Test
    public void present() {
        assertEquals(1, schedule.size());
    }

    @Test
    public void departureStation() {
        assertEquals("NWCSTLE", schedule.get(0).getOriginTiploc());
    }
}
