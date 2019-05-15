package com.sap.sample.contentAssembly;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

public class ContentFetchProcess {

    private static final Logger LOGGER = Logger.getLogger(ContentFetchProcess.class.getName());

    private void download(String websiteURL) throws IOException {
        try {
            URL url = new URL(websiteURL);
            int i = 0;
            while(i < 5) {
                BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter("Download.html"));
                String line;
                while ((line = readr.readLine()) != null) {
                    writer.write(line);
                }
                readr.close();
                writer.close();
                LOGGER.info("Content is fetched and downloded successfully!");
                i++;
            }
            LOGGER.info("Content is fetched and downloded successfully!");
        } catch (MalformedURLException mue) {
            LOGGER.info("Malformed URL is manipaulated..");
            mue.printStackTrace();
        } catch (IOException ie) {
            LOGGER.info("IO Exception Occured");
            ie.printStackTrace();
        }
    }

    public void execute(HashMap<String, String> parameter) {
        LOGGER.info("Content Fetch Process is invoked !");
        if (parameter != null && parameter.size() > 0) {
            String webURL = parameter.get("PROCESS_CONTENT_FETCH_URL");
            try {
                download(webURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
