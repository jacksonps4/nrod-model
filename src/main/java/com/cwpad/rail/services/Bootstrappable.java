package com.cwpad.rail.services;

import java.io.Reader;
import java.net.URL;

public interface Bootstrappable {
    void bootstrap(Reader reader);
    void bootstrap(URL url);
    void interrupt();
    boolean isInterrupted();
}
