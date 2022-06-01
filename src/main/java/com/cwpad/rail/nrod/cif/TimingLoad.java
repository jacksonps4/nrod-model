package com.cwpad.rail.nrod.cif;

import java.util.regex.Pattern;

public class TimingLoad {
    private static final Pattern EMU = Pattern.compile("\\d{1,3}");
    private static final Pattern HAULED = Pattern.compile("\\d{1,4}");

    private TimingLoadType type;
    private Integer emuCode;
    private Integer hauledTrainPlannedLoad;

    public TimingLoad(String timingLoad) {
        for (TimingLoadType type : TimingLoadType.values()) {
            if (type.toString().equals(timingLoad)) {
                this.type = type;
                break;
            }
        }
        if (type == null) {
            if (EMU.matcher(timingLoad).matches()) {
                this.type = TimingLoadType.EMU;
                this.emuCode = Integer.parseInt(timingLoad);
            } else if (HAULED.matcher(timingLoad).matches()) {
                this.type = TimingLoadType.HAULED;
                this.hauledTrainPlannedLoad = Integer.parseInt(timingLoad);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public String timingLoadType() {
        return type.toString();
    }

    public Integer getEmuCode() {
        return emuCode;
    }

    public Integer getHauledTrainPlannedLoad() {
        return hauledTrainPlannedLoad;
    }
}
