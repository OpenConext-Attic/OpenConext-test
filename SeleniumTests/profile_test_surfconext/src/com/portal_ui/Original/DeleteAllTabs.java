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

package com.portal_ui.Original;

import java.io.File;

import com.portal_ui.BaseTest;

public class DeleteAllTabs extends BaseTest {
    
    public void testDeleteAllTabs() throws Exception {
        // Sign in with SURFguest test account
        selenium.open("/coin/");
        selenium.click("ButtonShowAll");
        selenium.click("//*[@class='SURFnetGuests']");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        String username = Utils.getConfigEntry(f, "username");
        selenium.type("username", username);
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

           waitForElement("AddTab");
           int count = selenium.getXpathCount("//li/a/span[1]").intValue(); // usually 20 or more
                   for (int i = 1; i < count; i++) {
                           if(selenium.isElementPresent("//li/a/span[1]")) {
                               waitForElement("//li/a/span[1]");
                               selenium.click("//li/a/span[1]");
                               waitForElement("link=x");
                               selenium.click("link=x");
                               waitForElement("//div[10]/div[3]/div/button[1]");
                               selenium.click("//div[10]/div[3]/div/button[1]");
                                  }

                   } 
     
    };
}
