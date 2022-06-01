package com.cwpad.rail.nrod.cif;

public enum TrainMovementType {
    TRAIN_ACTIVATION("0001"), TRAIN_CANCELLATION("0002"), TRAIN_MOVEMENT("0003"),
    TRAIN_REINSTATEMENT("0005"), CHANGE_OF_ORIGIN("0006"), CHANGE_OF_IDENTITY("0007");

    private final String messageType;

    TrainMovementType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public static TrainMovementType parse(String msgType) {
        for (TrainMovementType type : TrainMovementType.values()) {
            if (type.getMessageType().equals(msgType)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Message type not found: " + msgType);
    }
}
