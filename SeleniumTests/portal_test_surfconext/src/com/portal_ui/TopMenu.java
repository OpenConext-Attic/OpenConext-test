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

public class TopMenu extends BaseTest {

    public void testTopMenu() throws Exception {

        File f = new File("config" + File.separator + "config.txt");
        waitForElement("AddTab");
        selenium.click("link=About");
        verifyTrue(selenium.isTextPresent("Version"));
        verifyTrue(selenium.isTextPresent("Copyright"));
        verifyTrue(selenium.isTextPresent("Terms of Service"));
        selenium.click("//div[8]/div[3]/div/button");
        selenium.click("//li[@id='UserName']/strong");
        selenium.click("//li[@id='UserName']/strong");
        verifyTrue(selenium.isTextPresent("test.surfguest.nl"));
        selenium.click("//li[@id='UserName']/strong");
        verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:" + Utils.getConfigEntry(f, "username")));
        selenium.click("link=Help");
        waitForElement("//div[7]/div[3]/div/button");
        verifyTrue(selenium.isTextPresent("Getting started in just 5 stepsclose"));
        selenium.click("//div[7]/div[3]/div/button");
        }
}
