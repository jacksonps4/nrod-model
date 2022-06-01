package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CIFScheduleLookupServiceUidMultipleTest {
    private CIFScheduleLookupService scheduleLookupService;
    private List<TrainSchedule> schedules;

    @Before
    public void setUp() throws IOException {
        scheduleLookupService = new CIFScheduleLookupService(TrainOperatingCompanyRepository.instance());

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        byte[] b = new byte[1024 * 64];
        StringBuilder scheduleData = new StringBuilder();
        loadScheduleFromResource(cl, b, scheduleData, "com/cwpad/rail/nrod/cif/Y60072");
        loadScheduleFromResource(cl, b, scheduleData, "com/cwpad/rail/nrod/cif/G01467");
        scheduleLookupService.bootstrap(new StringReader(scheduleData.toString()));

        schedules = scheduleLookupService.findByUid(LocalDate.of(2017, 1, 4), "Y60072", "G01467");
    }

    private void loadScheduleFromResource(ClassLoader cl, byte[] b, StringBuilder scheduleData, String resource) throws IOException {
        try (InputStream in = cl.getResourceAsStream(resource)) {
            for (int read; (read = in.read(b)) > -1; ) {
                scheduleData.append(new String(b, 0, read));
            }
        }
    }

    @Test
    public void present() {
        assertEquals(2, schedules.size());
    }

    @Test
    public void departureStations() {
        assertThat(schedules.stream()
                .map(schedule -> schedule.getOriginTiploc())
                .collect(Collectors.toList()), Matchers.containsInAnyOrder("NWCSTLE", "IPSWICH"));
    }
}
