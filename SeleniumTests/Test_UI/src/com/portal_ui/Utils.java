package com.portal_ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    /**
     * Read a configuration value from a file
     * 
     * @param configFile
     *            the file to read from
     * @param requestKey
     *            the configuration key to request
     * @return the value belonging to the requested key
     * @throws IOException
     *             if reading from the configuration file fails or file is
     *             broken
     */

    public static String getConfigEntry(File configFile, String requestKey) throws IOException {
        if (configFile == null || !configFile.exists())
            return null;
        FileReader f = new FileReader(configFile);
        BufferedReader b = new BufferedReader(f);
        while (b.ready()) {
            String line = b.readLine();
            /* we ignore lines that start with # */
            if (line.trim().length() == 0)
                continue;
            if (line.trim().startsWith("#"))
                continue;
            String[] keyValue = line.split("=");
            if (keyValue.length != 2)
                throw new IOException("broken configuration file");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            if (requestKey.equals(key))
                return value;
        }
        /* we were unable to find the requested key */
        return null;
    }

}
