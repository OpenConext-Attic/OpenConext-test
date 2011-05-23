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

public class DragTab extends BaseTest {

    public void testDragTab() throws Exception {
        // Login
        /* selenium.click("//div[@id='TabContainer']/h1"); */
        selenium.open("/coin/");
        selenium.click("ButtonShowAll");
        selenium.click("//*[@class='SURFnetGuests']");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        selenium.type("username", Utils.getConfigEntry(f, "username"));
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

        // Add tab
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "DragTab");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");

        // Drag tab
        selenium.click("AllTabsButton");
        selenium.waitForPageToLoad("30000");
        selenium.dragAndDrop("//*[@title='DragTab']", "400,30");

        // Delete tab
        selenium.click("//*[@class='remove']");
        selenium.click("//*[@class='remove']");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        /* verifyFalse(selenium.isTextPresent("DragTab")); */
    }
}

