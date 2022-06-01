package com.cwpad.rail.nrod.cif;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

class CIFParser implements Iterable<CIFRecord> {
    private final BufferedReader reader;

    public CIFParser(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public Iterator<CIFRecord> iterator() {
        return new Iterator<CIFRecord>() {
            private String record;

            @Override
            public boolean hasNext() {
                try {
                    record = reader.readLine();
                } catch (IOException e) {
                    return false;
                }
                return record != null;
            }

            @Override
            public CIFRecord next() {
                CIFRecordParser cifRecordParser = new CIFRecordParser(record);
                return cifRecordParser.readEntry();
            }
        };
    }
}
