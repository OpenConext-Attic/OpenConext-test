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

public class DragTab extends BaseTest {

    public void testDragTab() throws Exception {

        // Add tab
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "DragTab");
        waitForElement("//div[3]/div[3]/div/button[1]");
        selenium.click("//div[3]/div[3]/div/button[1]");

        // Drag tab
        selenium.click("AllTabsButton");
        selenium.waitForPageToLoad("30000");
        selenium.dragAndDrop("//*[@title='DragTab']", "400,30");

        // Delete tab
        selenium.click("//*[@class='remove']");
        selenium.click("//*[@class='remove']");
        waitForElement("//div[4]/div[3]/div/button[1]");
        selenium.click("//div[4]/div[3]/div/button[1]");
        /* verifyFalse(selenium.isTextPresent("DragTab")); */
    }
}

