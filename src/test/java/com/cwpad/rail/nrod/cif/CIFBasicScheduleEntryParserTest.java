package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class CIFBasicScheduleEntryParserTest {
    private CIFBasicScheduleEntryParser cifBasicScheduleEntryParser;
    private CIFBasicScheduleRecord record;

    @Before
    public void setUp() {
        cifBasicScheduleEntryParser = new CIFBasicScheduleEntryParser();
        record = cifBasicScheduleEntryParser.parse(
                "BSNC834231505171512060000001 PXX1B462246122154000 HST    110      B S C        P");
    }

    @Test
    public void transactionType() {
        assertEquals(CIFTransactionType.NEW, record.getCifTransactionType());
    }

    @Test
    public void trainUid() {
        assertEquals("C83423", record.getTrainUid());
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
        assertEquals(TrainCategory.XX, record.getTrainCategory());
    }

    @Test
    public void trainIdentity() {
        assertEquals("1B46", record.getIdentity());
    }

    @Test
    public void headCode() {
        assertEquals("2246", record.getHeadCode());
    }

    @Test
    public void trainServiceCode() {
        assertEquals("22154000", record.getServiceCode());
    }

    @Test
    public void powerType() {
        assertEquals(PowerType.HST, record.getPowerType());
    }

    @Test
    public void timingLoad() {
        assertNull(record.getTimingLoad());
    }

    @Test
    public void speed() {
        assertEquals(Integer.valueOf(110), record.getMaxSpeed());
    }

    @Test
    public void operatingCharacteristics() {
        assertNull(record.getOperatingCharacteristics());
    }

    @Test
    public void seatingClass() {
        assertEquals(SeatingClass.B, record.getSeatingClass());
    }

    @Test
    public void sleepers() {
        assertNull(record.getSleepers());
    }

    @Test
    public void reservations() {
        assertEquals(Reservations.S, record.getReservations());
    }

    @Test
    public void cateringCode() {
        assertThat(record.getCatering(), Matchers.contains(Catering.C));
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
