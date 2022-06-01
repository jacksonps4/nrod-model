package com.cwpad.rail.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.zip.GZIPInputStream;

public class NetworkRailStaticReader {
    private final Logger logger = Logger.getInstance(getClass());

    public byte[] readUrlData(String username, String password, String url) {
        logger.info("Loading data from " + url);
        NetworkRailStaticDataRetriever retriever = new NetworkRailStaticDataRetriever(url,
                username, password, false);
        byte[] b = new byte[1024 * 1024 * 4];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int totalData = 0;
        InputStream in = null;
        try {
            in = retriever.read();
            URI uri = URI.create(url);
            if (uri.toString().toUpperCase().endsWith(".GZ")) {
                in = new GZIPInputStream(in);
            }
            for (int read; (read = in.read(b)) > -1; ) {
                bos.write(b, 0, read);
                totalData += read;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
        }
        logger.info(String.format("Completed loading %d bytes from %s", totalData, url));
        return bos.toByteArray();
    }
}
