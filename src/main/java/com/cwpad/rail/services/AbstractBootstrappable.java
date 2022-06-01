package com.cwpad.rail.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;

public abstract class AbstractBootstrappable implements Bootstrappable {
    private final Logger logger = Logger.getInstance(getClass());
    private final AtomicBoolean interrupted = new AtomicBoolean(false);

    public void bootstrap(URL url) {
        try {
            URLConnection conn = url.openConnection();
            try (Reader reader = new InputStreamReader(new GZIPInputStream(conn.getInputStream()))) {
                logger.info("Bootstrapping service: " + getClass().getName());
                bootstrap(reader);
                logger.info("Finished bootstrapping service: " + getClass().getName());
            }
        } catch (IOException e ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void interrupt() {
        interrupted.set(true);
    }

    @Override
    public boolean isInterrupted() {
        return interrupted.get();
    }
}
