package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CIFScheduleLookupServiceTiplocPassTest {
    private CIFScheduleLookupService scheduleLookupService;
    private List<TrainSchedule> schedule;

    @Before
    public void setUp() throws IOException {
        scheduleLookupService = new CIFScheduleLookupService(TrainOperatingCompanyRepository.instance());

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (Reader r = new InputStreamReader(cl.getResourceAsStream("com/cwpad/rail/nrod/cif/Y58497"))) {
           scheduleLookupService.bootstrap(r);
        }

        schedule = scheduleLookupService.findByTiplocAndDepartureTime("DRHM", LocalDateTime.of(2015, 10, 14, 5, 39), true);
    }

    @Test
    public void departureStation() {
        assertEquals("NWCSTLE", schedule.get(0).getOriginTiploc());
    }

    @Test
    public void departureTime() {
        assertEquals(LocalTime.of(5, 25), schedule.get(0).getOriginDepartureTime());
    }
}
