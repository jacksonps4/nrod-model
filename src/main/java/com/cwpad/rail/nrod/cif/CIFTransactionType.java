package com.cwpad.rail.nrod.cif;

enum CIFTransactionType {
    NEW("N"), DELETE("D"), REVISE("R");

    private final String type;

    CIFTransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static CIFTransactionType parse(String type) {
        for (CIFTransactionType transactionType : CIFTransactionType.values()) {
            if (transactionType.getType().equals(type)) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction type " + type);
    }
}
