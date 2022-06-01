package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CIFAssociationEntryParserTest {
    private CIFAssociationEntryParser parser;
    private CIFAssociationRecord record;

    @Before
    public void setUp() {
        parser = new CIFAssociationEntryParser();
        record = parser.parse("AANC62092C835851505171512060000001NPSNTNG     TO                               P");
    }

    @Test
    public void transactionType() {
        assertEquals(CIFTransactionType.NEW, record.getTransactionType());
    }

    @Test
    public void mainTrainUid() {
        assertEquals("C62092", record.getMainTrainUid());
    }

    @Test
    public void associatedTrainUid() {
        assertEquals("C83585", record.getAssociatedTrainUid());
    }

    @Test
    public void associationStartDate() {
        assertEquals(LocalDate.of(2015, 5, 17), record.getAssociationStartDate());
    }

    @Test
    public void associationEndDate() {
        assertEquals(LocalDate.of(2015, 12, 6), record.getAssociationEndDate());
    }

    @Test
    public void associationDays() {
        assertThat(record.getAssociationDays(), Matchers.contains(DayOfWeek.SUNDAY));
    }

    @Test
    public void associationCategory() {
        assertEquals(ScheduleAssociationCategory.NP, record.getAssociationCategory());
    }

    @Test
    public void associationDateIndicator() {
        assertEquals(ScheduleAssociationDateIndicator.S, record.getScheduleAssociationDateIndicator());
    }

    @Test
    public void tiploc() {
        assertEquals("NTNG", record.getTiploc());
    }

    @Test
    public void baseLocationSuffix() {
        assertEquals(' ', record.getBaseLocationSuffix());
    }

    @Test
    public void associatedLocationSuffix() {
        assertEquals(' ', record.getAssociatedLocationSuffix());
    }

    @Test
    public void associationType() {
        assertEquals(ScheduleAssociationType.O, record.getAssociationType());
    }

    @Test
    public void stpIndicator() {
        assertEquals(StpIndicator.P, record.getStpIndicator());
    }
}
