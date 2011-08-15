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

public class TestTabs extends BaseTest {
    public void testTabs() throws Exception {
        // Add one tab "First tab"
        waitForElement("AddTab");
        selenium.click("AddTab");
        waitForElement("AddTabDialog");
        selenium.type("InputTitle", "First tab");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("First tab"));
        // Add second tab
        selenium.type("InputTitle", "Second tab");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("Second tab"));
        
        /*
        // Cancel delete tab
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[3]/div[3]/div/button[2]");
        selenium.click("//div[3]/div[3]/div/button[2]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("Second tab"));
        
        //Add tab without a name
        waitForElement("AddTab");
        selenium.click("AddTab");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");
        verifyTrue(selenium
                .isTextPresent("A tab needs to have a name. Please add a name for this tab."));
        waitForElement("//div[3]/div[3]/div/button[2]");
        selenium.click("//div[3]/div[3]/div/button[2]");
                
        // Delete all tabs
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("First tab"));
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");
        waitForElement("//div[@id='TabContainer']/h1");
        verifyTrue(selenium.isTextPresent("Other tabs"));
        */
        
        //cleanup
		super.deleteAllTabs();
    }
}
