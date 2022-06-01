package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class CIFBasicScheduleEntryParserScenario2Test {
    private CIFBasicScheduleEntryParser cifBasicScheduleEntryParser;
    private CIFBasicScheduleRecord record;

    @Before
    public void setUp() {
        cifBasicScheduleEntryParser = new CIFBasicScheduleEntryParser();
        record = cifBasicScheduleEntryParser.parse(
                "BSNP048441505171512060000001 PEE5C04    125467001ZDMUS   075D                  P");
    }

    @Test
    public void transactionType() {
        assertEquals(CIFTransactionType.NEW, record.getCifTransactionType());
    }

    @Test
    public void trainUid() {
        assertEquals("P04844", record.getTrainUid());
    }

    @Test
    public void runsFrom() {
        assertEquals(LocalDate.of(2015, 5, 17), record.getRunsFrom());
    }

    @Test
    public void runsTo() {
        assertEquals(LocalDate.of(2015, 12, 6), record.getRunsTo());
    }

    @Test
    public void daysRun() {
        assertThat(record.getDaysRun(), Matchers.contains(DayOfWeek.SUNDAY));
    }

    @Test
    public void bankHolidayRunning() {
        assertNull(record.getBankHolidayRunning());
    }

    @Test
    public void trainStatus() {
        assertEquals(TrainStatus.P, record.getTrainStatus());
    }

    @Test
    public void trainCategory() {
        assertEquals(TrainCategory.EE, record.getTrainCategory());
    }

    @Test
    public void trainIdentity() {
        assertEquals("5C04", record.getIdentity());
    }

    @Test
    public void headCode() {
        assertNull(record.getHeadCode());
    }

    @Test
    public void trainServiceCode() {
        assertEquals("25467001", record.getServiceCode());
    }

    @Test
    public void powerType() {
        assertEquals(PowerType.DMU, record.getPowerType());
    }

    @Test
    public void timingLoad() {
        assertEquals("S", record.getTimingLoad().timingLoadType());
    }

    @Test
    public void speed() {
        assertEquals(Integer.valueOf(75), record.getMaxSpeed());
    }

    @Test
    public void operatingCharacteristics() {
        assertThat(record.getOperatingCharacteristics(),
                Matchers.contains(OperatingCharacteristic.D));
    }

    @Test
    public void seatingClass() {
        assertNull(record.getSeatingClass());
    }

    @Test
    public void sleepers() {
        assertNull(record.getSleepers());
    }

    @Test
    public void reservations() {
        assertNull(record.getReservations());
    }

    @Test
    public void cateringCode() {
        assertNull(record.getCatering());
    }

    @Test
    public void serviceBranding() {
        assertNull(record.getServiceBranding());
    }

    @Test
    public void stpIndicator() {
        assertEquals(StpIndicator.P, record.getStpIndicator());
    }
}
