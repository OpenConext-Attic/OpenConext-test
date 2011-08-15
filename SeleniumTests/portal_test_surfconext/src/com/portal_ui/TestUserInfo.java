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

public class TestUserInfo extends BaseTest {

    public void testSignIn() throws Exception {
        
        File f = new File("config" + File.separator + "config.txt");
        String username = Utils.getConfigEntry(f, "username");
        waitForElement("AddTab");
        verifyTrue(selenium.isTextPresent("Other tabs"));
        selenium.click("//li[@id='UserName']/strong");
        // check if the username is right (same as sign in)
        verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:"
                + username));
        selenium.click("PageContainer");
        selenium.click("link=Sign out");
        selenium.waitForPageToLoad("30000");
        // check if logout message is shown
        verifyTrue(selenium.isTextPresent("Local Logout"));
    };
}
