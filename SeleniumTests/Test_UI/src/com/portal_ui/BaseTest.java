package com.portal_ui;

import java.io.File;
import com.thoughtworks.selenium.Wait;

import com.thoughtworks.selenium.*;

public class BaseTest extends SeleneseTestCase {
    @Override
    public void setUp() throws Exception {
        File f = new File("config" + File.separator + "config.txt");
        String url = Utils.getConfigEntry(f, "url");
        String browser = Utils.getConfigEntry(f, "browser");
        String speed = Utils.getConfigEntry(f, "speed");
        String path = Utils.getConfigEntry(f, "path");
        setUp(url, "*" + browser);
        selenium.setSpeed(speed);
        selenium.windowMaximize();
        selenium.open(url + "/" + path);
    }

    public void waitForElement(final String waitingElement) {
        new Wait() {
            @Override
            public boolean until() {
                return selenium.isElementPresent(waitingElement);
            }
        }.wait("Timeout while waiting for element " + waitingElement);
    }

}
