package com.cwpad.rail.nrod.cif;

import java.util.Optional;

abstract class CIFTiplocEntryParser extends CIFBaseEntryParser {
    protected final void parseTiplocRecord(String recordData, CIFTiplocRecord record) {
        record.setTiploc(recordData.substring(2, 9).trim());
        record.setCapitalsIdentification(recordData.substring(9, 11));
        record.setNationalLocationCode(Integer.parseInt(recordData.substring(11, 17)));

        String nlcCheckChar = recordData.substring(17, 18).trim();
        if (nlcCheckChar.length() > 0) {
            record.setNlcCheckCharacter(Optional.of(Character.valueOf(nlcCheckChar.charAt(0))));
        }
        String tpsDescription = recordData.substring(18, 44).trim();
        if (tpsDescription.length() > 0) {
            record.setTpsDescription(Optional.of(tpsDescription));
        } else {
            record.setTpsDescription(Optional.empty());
        }
        String stanox = recordData.substring(44, 49).trim();
        if (!"00000".equals(stanox)) {
            record.setStanox(Optional.of(Integer.parseInt(stanox)));
        } else {
            record.setStanox(Optional.empty());
        }

        Integer postOfficeLocationCode = Integer.parseInt(recordData.substring(49, 53).trim());
        if (postOfficeLocationCode.intValue() > 0) {
            record.setPostOfficeLocationCode(Optional.of(postOfficeLocationCode));
        } else {
            record.setPostOfficeLocationCode(Optional.empty());
        }

        String crsCode = recordData.substring(53, 56).trim();
        if (crsCode.length() > 0) {
            record.setCrsCode(Optional.of(crsCode));
        } else {
            record.setCrsCode(Optional.empty());
        }

        String description = recordData.substring(56, 72).trim();
        if (description.length() > 0) {
            record.setDescription(Optional.of(description));
        } else {
            record.setDescription(Optional.empty());
        }
    }
}
