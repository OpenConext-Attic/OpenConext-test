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

public class DragDrop extends BaseTest {

    public void testDragDrop() throws Exception {
        // Sign in with SURFguest test account
        selenium.click("//div[@id='TabContainer']/h1");
        selenium.click("ButtonShowAll");
        selenium.click("//*[@class='SURFnetGuests']");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        selenium.type("username", Utils.getConfigEntry(f, "username"));
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

        // Test drag & drop
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "add gadget");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "surfteams");
        selenium.click("SubmitGadgetSearch");
        verifyTrue(selenium.isTextPresent("SURFteams"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("gadgets-gadget-title-bar-0");
        selenium.dragAndDrop("gadgets-gadget-title-bar-0", "600,0");
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "surfteams");
        selenium.click("SubmitGadgetSearch");
        verifyTrue(selenium.isTextPresent("SURFteams"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("gadgets-gadget-title-bar-0");
        selenium.dragAndDrop("gadgets-gadget-title-bar-0", "600,10");
        selenium.click("link=x");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
    }
}

