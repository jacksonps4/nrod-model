package com.cwpad.rail.nrod.cif;

import java.time.LocalTime;

class CIFIntermediateLocationRecord extends CIFLocationRecord {
    private LocalTime scheduledArrival;
    private LocalTime scheduledPass;
    private LocalTime publicArrival;

    public CIFIntermediateLocationRecord() {
        super(CIFRecordType.LI);
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public LocalTime getScheduledPass() {
        return scheduledPass;
    }

    public void setScheduledPass(LocalTime scheduledPass) {
        this.scheduledPass = scheduledPass;
    }

    public LocalTime getPublicArrival() {
        return publicArrival;
    }

    public void setPublicArrival(LocalTime publicArrival) {
        this.publicArrival = publicArrival;
    }

}
