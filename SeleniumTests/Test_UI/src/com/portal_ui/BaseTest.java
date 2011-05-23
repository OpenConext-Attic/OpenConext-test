/*
 * Copyright 2011 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.portal_ui;

import java.io.File;

import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.Wait;

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
