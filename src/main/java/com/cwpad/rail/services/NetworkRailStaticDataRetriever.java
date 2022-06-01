package com.cwpad.rail.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class NetworkRailStaticDataRetriever {
    private final String url;
    private final String username;
    private final String password;
    private final boolean compression;

    public NetworkRailStaticDataRetriever(String url, String username, String password) {
        this(url, username, password, false);
    }

    public NetworkRailStaticDataRetriever(String url, String username, String password, boolean compression) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.compression = compression;
    }

    public InputStream read() {
        try {
            URLConnection connection = request(url, username, password);
            InputStream in = connection.getInputStream();
            if (compression) {
                in = new GZIPInputStream(in);
            }
            return in;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URLConnection request(String requestUrl, String username, String password) throws IOException {
        return request(requestUrl, username, password, null);
    }

    private URLConnection request(String requestUrl, String username, String password, String cookie) throws IOException {
        URL url = new URL(requestUrl);
        URLConnection c = url.openConnection();
        if (c instanceof HttpURLConnection) {
            HttpURLConnection conn = (HttpURLConnection) c;
            conn.addRequestProperty("Accept", "*/*");
            if (cookie != null) {
                conn.addRequestProperty("Cookie", cookie);
            }

            if (url.getPath().startsWith("/ntrod/spring_security_login")) {
                char[] b = new char[1024 * 64];
                StringBuilder loginPage = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()))) {
                    for (int read; (read = reader.read(b)) > -1; ) {
                        loginPage.append(new String(b, 0, read));
                    }
                }
                String loginPageData = loginPage.toString();
                String action = formAction(loginPageData);
                String method = formRequestMethod(loginPageData);

                URL loginUrl = new URL(url.getProtocol(), url.getHost(), action);
                HttpURLConnection loginConnection = (HttpURLConnection) loginUrl.openConnection();
                loginConnection.setDoOutput(true);
                loginConnection.setRequestMethod(method);
                loginConnection.addRequestProperty("Accept", "*/*");
                loginConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                loginConnection.addRequestProperty("Referrer", url.toExternalForm());
                loginConnection.addRequestProperty("Cookie", cookie);

                Writer writer = new BufferedWriter(new OutputStreamWriter(loginConnection.getOutputStream()));
                StringBuilder loginData = new StringBuilder();
                loginData.append("j_username=" + URLEncoder.encode(username, StandardCharsets.UTF_8.toString()));
                loginData.append("&j_password=" + URLEncoder.encode(password, StandardCharsets.UTF_8.toString()));
                loginData.append("&submit=Login");
                writer.write(loginData.toString());
                writer.flush();

                conn = loginConnection;
            }
            conn.setInstanceFollowRedirects(false);

            if (conn.getResponseCode() == 302) {
                String redirectLocation = conn.getHeaderField("Location");
                String setCookie = conn.getHeaderField("Set-Cookie");
                if (setCookie != null && setCookie.trim().length() > 0) {
                    cookie = setCookie.substring(0, setCookie.indexOf(' '));
                }
                return request(redirectLocation, username, password, cookie);
            }
            if (conn.getResponseCode() != 200) {
                throw new IOException("Failed to download schedule: HTTP server " + conn.getResponseCode());
            }
        }

        return c;
    }

    private String generateAuthorization(String username, String password) {
        return "Basic " + new String(Base64.getEncoder().encode(username.concat(":").concat(password).getBytes()));
    }

    String formAction(String html) {
        return Pattern.compile(".*action='([^']*)'.*", Pattern.MULTILINE | Pattern.DOTALL)
                .matcher(html)
                .replaceAll("$1");
    }

    String formRequestMethod(String html) {
        return Pattern.compile(".*method='([^']*)'.*", Pattern.MULTILINE | Pattern.DOTALL)
                .matcher(html)
                .replaceAll("$1");
    }
}
