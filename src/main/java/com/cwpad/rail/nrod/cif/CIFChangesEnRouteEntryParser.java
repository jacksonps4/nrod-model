package com.cwpad.rail.nrod.cif;

class CIFChangesEnRouteEntryParser extends CIFBaseEntryParser {
    @Override
    public CIFChangesEnRouteRecord parse(String recordData) {
        checkType(CIFRecordType.CR, recordData);

        CIFChangesEnRouteRecord record = new CIFChangesEnRouteRecord();
        record.setTiploc(recordData.substring(2, 9).trim());
        record.setTrainCategory(TrainCategory.valueOf(recordData.substring(10, 12)));
        record.setIdentity(recordData.substring(12, 16));

        String headCode = recordData.substring(16, 20).trim();
        if (headCode.length() > 0) {
            record.setHeadCode(headCode);
        }

        String serviceCode = recordData.substring(21, 29).trim();
        if (serviceCode.length() > 0) {
            record.setServiceCode(serviceCode);
        }

        // portionId

        String powerType = recordData.substring(30, 33).trim();
        if (powerType.length() > 0) {
            record.setPowerType(PowerType.valueOf(powerType));
        }

        String timingLoad = recordData.substring(33, 37).trim();
        if (timingLoad.length() > 0) {
            record.setTimingLoad(new TimingLoad(timingLoad));
        }

        String maxSpeed = recordData.substring(37, 40).trim();
        if (maxSpeed.length() > 0) {
            record.setMaxSpeed(Integer.parseInt(maxSpeed));
        }

        String operatingCharacteristics = recordData.substring(40, 46).trim();
        if (operatingCharacteristics.length() > 0) {
            char[] characteristics = operatingCharacteristics.toCharArray();
            for (char characteristic : characteristics) {
                record.addOperatingCharacteristic(
                        OperatingCharacteristic.valueOf(String.valueOf(characteristic)));
            }
        }

        String seatingClass = recordData.substring(46, 47).trim();
        if (seatingClass.length() > 0) {
            record.setSeatingClass(SeatingClass.valueOf(seatingClass));
        }

        String sleepers = recordData.substring(47, 48).trim();
        if (sleepers.length() > 0) {
            record.setSleepers(Sleepers.valueOf(sleepers));
        }

        String reservations = recordData.substring(48, 49).trim();
        if (reservations.length() > 0) {
            record.setReservations(Reservations.valueOf(reservations));
        }

        String cateringCodes = recordData.substring(50, 54).trim();
        if (cateringCodes.length() > 0) {
            char[] cc = cateringCodes.toCharArray();
            for (char cateringCode : cc) {
                record.addCatering(Catering.valueOf(String.valueOf(cateringCode)));
            }
        }

        String serviceBranding = recordData.substring(54, 58).trim();
        if (serviceBranding.length() > 0) {
            char[] sb = serviceBranding.toCharArray();
            for (char serviceBrand : sb) {
                record.addServiceBranding(ServiceBranding.valueOf(String.valueOf(serviceBrand)));
            }
        }

        String uicCode = recordData.substring(62, 67).trim();
        if (uicCode.length() > 0) {
            record.setUicCode(uicCode);
        }
        return record;
    }
}
